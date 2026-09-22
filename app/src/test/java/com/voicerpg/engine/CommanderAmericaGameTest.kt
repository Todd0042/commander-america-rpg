package com.voicerpg.engine

import com.voicerpg.engine.audio.CombatNarrator
import com.voicerpg.engine.audio.SpeechManager
import com.voicerpg.engine.engine.IntentParser
import com.voicerpg.engine.engine.SaveManager
import com.voicerpg.engine.engine.StoryEncounters
import com.voicerpg.engine.engine.StoryScript
import com.voicerpg.engine.model.CombatPhase
import com.voicerpg.engine.model.GameSaveData
import com.voicerpg.engine.model.GameScreen
import com.voicerpg.engine.model.PlayerCustomization
import com.voicerpg.engine.model.SavedCharacterStats
import com.voicerpg.engine.template.TemplateHero
import com.voicerpg.engine.viewmodel.CombatViewModel
import com.voicerpg.engine.viewmodel.StoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CommanderAmericaGameTest {

    private val testScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private lateinit var speechManager: SpeechManager
    private lateinit var combatNarrator: CombatNarrator
    private lateinit var saveManager: SaveManager
    private lateinit var storyViewModel: StoryViewModel
    private lateinit var combatViewModel: CombatViewModel

    @Before
    fun setUp() {
        speechManager = SpeechManager()
        combatNarrator = CombatNarrator()
        saveManager = SaveManager()
        storyViewModel = StoryViewModel(
            speechManager = speechManager,
            combatNarrator = combatNarrator,
            saveManager = saveManager,
            scopeOverride = testScope
        )
        combatViewModel = CombatViewModel(
            speechManager = speechManager,
            combatNarrator = combatNarrator,
            scopeOverride = testScope
        )
    }

    @After
    fun tearDown() {
        combatViewModel.pauseAtb()
        combatViewModel.cleanup()
        storyViewModel.cancelPendingStoryMode()
        storyViewModel.cancelPendingAutoAdvance()
    }

    @Test
    fun testInitialHeroIsCommanderAmerica() {
        val combatState = combatViewModel.state.value
        assertEquals(1, combatState.party.size)
        val hero = combatState.party[0]
        assertEquals("Commander America", hero.name)
        assertEquals("Commander-in-Chief", hero.loreClass)
        assertTrue("Hero must have Tremendous Strike", hero.spells.any { it.id == "ca_tremendous_strike" })
        assertTrue("Hero must have Obama Did It First!", hero.spells.any { it.id == "ca_obama_did_it_first" })
        assertTrue("Hero must have Build The Wall", hero.spells.any { it.id == "ca_build_the_wall" })
        assertTrue("Hero must have Art of the Deal", hero.spells.any { it.id == "ca_art_of_the_deal" })
    }

    @Test
    fun testAct1CombatEncounterHasCommanderAmericaAndNoLoopOnVictory() {
        storyViewModel.startNewGameFlow()
        val custom = PlayerCustomization()
        storyViewModel.startNewGame(custom)

        // Verify initial story scene and node
        assertEquals("scene_act1_ch1_escalator", storyViewModel.state.value.currentScene.id)
        assertEquals("act1_intro", storyViewModel.state.value.currentNode.id)

        // Trigger the Act 1 Chapter 1 battle
        storyViewModel.triggerEncounter("act1_ch1_press_battle")
        assertEquals(GameScreen.COMBAT_ARENA, storyViewModel.state.value.gameScreen)
        assertEquals("act1_ch1_press_battle", storyViewModel.state.value.activeEncounter?.id)

        // Start combat in CombatViewModel with active encounter
        val encounter = storyViewModel.state.value.activeEncounter!!
        combatViewModel.startEncounter(encounter)
        combatViewModel.pauseAtb()

        // In combat, player character is Commander America
        val combatHero = combatViewModel.state.value.party.first { it.id == "hero" }
        assertEquals("Commander America", combatHero.name)
        assertEquals("Commander-in-Chief", combatHero.loreClass)

        // Simulate combat victory
        combatViewModel.onContinueStory = {
            storyViewModel.updatePartyStatsFromCombat(combatViewModel.state.value.party)
            storyViewModel.onCombatVictory()
        }
        combatViewModel.onContinueStory?.invoke()

        // Verify that after combat victory:
        // 1. Returned to story exploration
        assertEquals(GameScreen.STORY_EXPLORATION, storyViewModel.state.value.gameScreen)
        // 2. Transformed to post battle node, NOT back to trigger node (NO LOOP!)
        assertEquals("act1_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        // 3. Scene changed to Act I Chapter 2 Debate Arena
        assertEquals("scene_act1_ch2_debate", storyViewModel.state.value.currentScene.id)
        // 4. Hero stats persisted as Commander America
        val savedHero = storyViewModel.state.value.partyStats.first { it.id == "hero" }
        assertEquals("Commander America", savedHero.name)
    }

    @Test
    fun testAllEightActsDoNotLoopPostBattle() {
        val acts = listOf(
            "act1_ch1_press_battle" to "act1_ch1_post_battle",
            "act1_ch2_debate_battle" to "act1_ch2_post_battle",
            "act1_ch3_machine_battle" to "act1_ch3_post_battle",
            "act2_ch1_inauguration_battle" to "act2_ch1_post_battle",
            "act2_media_battle" to "act2_ch3_post_battle",
            "act3_ch1_mill_battle" to "act3_ch1_post_battle",
            "act3_ch3_intercept_battle" to "act3_ch3_post_battle",
            "act3_summit_battle" to "act3_ch4_post_battle",
            "act4_ch1_fisa_battle" to "act4_ch1_post_battle",
            "act4_ch3_subpoena_battle" to "act4_ch3_post_battle",
            "act4_hoax_battle" to "act4_ch4_post_battle",
            "act5_ch1_pipe_battle" to "act5_ch1_post_battle",
            "act5_ch2_inquest_battle" to "act5_ch2_post_battle",
            "act5_golem_battle" to "act5_ch4_post_battle",
            "act6_ch1_border_battle" to "act6_ch1_post_battle",
            "act6_ch3_bus_battle" to "act6_ch3_post_battle",
            "act6_kamala_battle" to "act6_ch5_post_battle",
            "act7_ch1_indictment_battle" to "act7_ch1_post_battle",
            "act7_ch2_contempt_battle" to "act7_ch2_post_battle",
            "act7_lawfare_battle" to "act7_ch5_post_battle",
            "act8_biden_final_battle" to "act8_post_battle"
        )

        for ((encounterId, expectedPostNodeId) in acts) {
            storyViewModel.triggerEncounter(encounterId)
            assertEquals("Should trigger $encounterId", encounterId, storyViewModel.state.value.activeEncounter?.id)
            storyViewModel.onCombatVictory()
            assertEquals("Post-battle node for $encounterId must be $expectedPostNodeId",
                expectedPostNodeId, storyViewModel.state.value.currentNode.id)
            assertNotEquals("Must not loop back to battle trigger",
                storyViewModel.state.value.currentNode.triggerBattleEncounterId, encounterId)
        }
    }

    @Test
    fun testAct1ThreeChaptersFullProgressionAndDebugWarps() {
        // 1. Start fresh game in Act I Chapter 1
        storyViewModel.startNewGameFlow()
        storyViewModel.startNewGame(PlayerCustomization())

        assertEquals("scene_act1_ch1_escalator", storyViewModel.state.value.currentScene.id)
        assertEquals("act1_intro", storyViewModel.state.value.currentNode.id)

        // Chapter 1 choice: Swagger -> Hulk -> Hub
        val swaggerChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_swagger" }
        storyViewModel.selectChoice(swaggerChoice)
        assertEquals("act1_ch1_swagger", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_hulk_cheer", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)

        // Hub inquiries: Deal, Wall, Swamp
        val dealChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_hub_deal" }
        storyViewModel.selectChoice(dealChoice)
        assertEquals("act1_ch1_lore_deal", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)

        // Proceed to lobby floor ambush and battle
        val proceedChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_hub_proceed" }
        storyViewModel.selectChoice(proceedChoice)
        assertEquals("act1_ch1_lobby_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act1_ch1_press_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Trigger and win Chapter 1 battle
        storyViewModel.triggerEncounter("act1_ch1_press_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act1_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_ch2_debate", storyViewModel.state.value.currentScene.id)

        // 2. Advance into Chapter 2 (Cleveland Debate)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_podium_clash", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_donor_expose", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_rival_interject", storyViewModel.state.value.currentNode.id)

        // Select counter-punch choice: roast Super PAC puppets
        val puppetChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a2_puppets" }
        storyViewModel.selectChoice(puppetChoice)
        assertEquals("act1_ch2_roast_pac", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_hulk_roar", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act1_ch2_debate_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Trigger and win Chapter 2 debate battle
        storyViewModel.triggerEncounter("act1_ch2_debate_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act1_ch2_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_ch3_rally", storyViewModel.state.value.currentScene.id)

        // 3. Advance into Chapter 3 (New Hampshire Rally & Primary Sweep)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch3_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch3_rally_speech", storyViewModel.state.value.currentNode.id)

        // Select speech choice: winning
        val winChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a3_win" }
        storyViewModel.selectChoice(winChoice)
        assertEquals("act1_ch3_speech_win", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch3_party_boss_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch3_boss_rebuttal", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch3_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act1_ch3_machine_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Trigger and win Chapter 3 finale battle
        storyViewModel.triggerEncounter("act1_ch3_machine_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act1_ch3_post_battle", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch3_victory_conclusion", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act2_ch1_mall", storyViewModel.state.value.currentScene.id)

        // Advance to hulk reaction and Act II Ch1 transition
        storyViewModel.advanceDialogue()
        assertEquals("act1_post_hulk", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch1_intro", storyViewModel.state.value.currentNode.id)

        // 4. Verify Debug Warps for all 3 Act I chapters
        storyViewModel.debugWarpToChapter("act1_ch1_intro")
        assertEquals("act1_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_ch1_escalator", storyViewModel.state.value.currentScene.id)
        assertEquals(1, storyViewModel.state.value.partyStats.size)
        assertEquals("hero", storyViewModel.state.value.partyStats[0].id)

        storyViewModel.debugWarpToChapter("act1_ch2_intro")
        assertEquals("act1_ch2_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_ch2_debate", storyViewModel.state.value.currentScene.id)
        assertEquals(1, storyViewModel.state.value.partyStats.size)
        assertEquals("hero", storyViewModel.state.value.partyStats[0].id)

        storyViewModel.debugWarpToChapter("act1_ch3_intro")
        assertEquals("act1_ch3_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_ch3_rally", storyViewModel.state.value.currentScene.id)
        assertEquals(1, storyViewModel.state.value.partyStats.size)
        assertEquals("hero", storyViewModel.state.value.partyStats[0].id)
    }

    @Test
    fun testPelosiInsiderTradingLichInAct2Encounter() {
        val act2Encounter = StoryEncounters.ACT2_MEDIA_BATTLE
        assertEquals("act2_media_battle", act2Encounter.id)
        val pelosi = act2Encounter.enemies.firstOrNull { it.id == "pelosi" }
        assertNotNull("Pelosi must be an enemy in Act 2", pelosi)
        assertTrue("Pelosi title should reference Stock Lich", pelosi!!.name.contains("Pelosi") && pelosi.subtitle.contains("Stock") || pelosi.name.contains("Stock"))
    }

    @Test
    fun testVoiceIntentParsingForCommanderAmericaSpells() {
        val hero = TemplateHero.createTemplateAdventurer(id = "hero")
        val enemies = StoryEncounters.ACT1_LOBBY_BATTLE.enemies

        // Tremendous Strike
        val strikeIntent = IntentParser.parse("tremendous strike the lobbyist!", hero.spells, enemies, listOf(hero))
        assertEquals("ca_tremendous_strike", strikeIntent.spell.id)

        // Obama Did It First
        val obamaIntent = IntentParser.parse("obama did it first!", hero.spells, enemies, listOf(hero))
        assertEquals("ca_obama_did_it_first", obamaIntent.spell.id)

        // Build The Wall
        val wallIntent = IntentParser.parse("build the wall", hero.spells, enemies, listOf(hero))
        assertEquals("ca_build_the_wall", wallIntent.spell.id)

        // Art of the Deal
        val dealIntent = IntentParser.parse("art of the deal", hero.spells, enemies, listOf(hero))
        assertEquals("ca_art_of_the_deal", dealIntent.spell.id)
    }

    @Test
    fun testLegacySaveMigrationUpgradesAethelToCommanderAmerica() {
        // Create legacy save data with "Aethel"
        val legacySave = GameSaveData(
            player = PlayerCustomization(name = "Aethel"),
            currentSceneId = "scene_act1_escalator",
            currentNodeId = "act1_intro",
            partyStats = listOf(
                SavedCharacterStats(
                    id = "hero",
                    name = "Aethel",
                    loreClass = "Elementalist",
                    currentHp = 240,
                    maxHp = 240,
                    currentMp = 140,
                    maxMp = 140,
                    speed = 70,
                    spellIds = listOf("fireball")
                )
            )
        )
        saveManager.save(legacySave, slot = 1)

        // Load game via StoryViewModel
        val vm = StoryViewModel(
            speechManager = speechManager,
            combatNarrator = combatNarrator,
            saveManager = saveManager,
            scopeOverride = testScope
        )

        assertEquals("Commander America", vm.state.value.player.name)
        val heroStats = vm.state.value.partyStats.first { it.id == "hero" }
        assertEquals("Commander America", heroStats.name)
        assertEquals("Commander-in-Chief", heroStats.loreClass)
        assertTrue(heroStats.spellIds.contains("ca_tremendous_strike"))
    }

    @Test
    fun testCombatInactiveByDefaultAndNoMenuNarration() {
        // 1. Fresh CombatViewModel must start INACTIVE with 0 enemies and inactive combat narrator
        assertFalse(combatNarrator.isCombatActive.value)
        assertEquals(CombatPhase.INACTIVE, combatViewModel.state.value.phase)
        assertTrue(combatViewModel.state.value.enemies.isEmpty())

        // 2. tickAtb when inactive does nothing
        combatViewModel.tickAtb()
        assertEquals(CombatPhase.INACTIVE, combatViewModel.state.value.phase)
        assertFalse(combatNarrator.isSpeaking.value)

        // 3. Applying player customization in menus does not activate combat or charge ATB
        combatViewModel.applyPlayerCustomization(
            customization = storyViewModel.state.value.player,
            savedStats = storyViewModel.state.value.partyStats
        )
        assertEquals(CombatPhase.INACTIVE, combatViewModel.state.value.phase)
        val hero = combatViewModel.state.value.party.first { it.id == "hero" }
        assertEquals(0f, hero.atbGauge, 0.001f)

        // 4. CombatNarrator methods must silently no-op when combat is not active
        var turnCallbackFired = false
        combatNarrator.narratePlayerTurn(hero, emptyList()) {
            turnCallbackFired = true
        }
        assertTrue("Callback must immediately invoke without speaking", turnCallbackFired)
        assertFalse(combatNarrator.isSpeaking.value)

        var enemyCallbackFired = false
        combatNarrator.narrateEnemyAction("Lobbyist", "Hero", 20) {
            enemyCallbackFired = true
        }
        assertTrue(enemyCallbackFired)
        assertFalse(combatNarrator.isSpeaking.value)

        // 5. Starting an encounter activates combat and sets ATB_WAITING
        combatViewModel.startEncounter(StoryEncounters.ACT1_LOBBY_BATTLE)
        assertTrue(combatNarrator.isCombatActive.value)
        assertEquals(CombatPhase.ATB_WAITING, combatViewModel.state.value.phase)
        assertFalse(combatViewModel.state.value.enemies.isEmpty())

        // 6. Pausing ATB deactivates combat
        combatViewModel.pauseAtb()
        assertFalse(combatNarrator.isCombatActive.value)
        assertEquals(CombatPhase.INACTIVE, combatViewModel.state.value.phase)
    }

    @Test
    fun testPureStoryModePrefersAoeWhenTwoOrMoreEnemiesAlive() {
        val hero = TemplateHero.createTemplateAdventurer(id = "hero")
        val aoeSpell = hero.spells.first { it.hitsAll && !it.isHeal && !it.isGuard }
        assertEquals("ca_art_of_the_deal", aoeSpell.id)
        assertTrue(aoeSpell.hitsAll)

        // Case 1: 2 enemies alive on field -> auto battle must choose AOE spell
        val twoEnemies = listOf(
            com.voicerpg.engine.model.Enemy("e1", "K-Street Lobbyist", "Vanguard", currentHp = 100, maxHp = 100, baseAttack = 10),
            com.voicerpg.engine.model.Enemy("e2", "Cable Pundit Spectre", "Sniper", currentHp = 100, maxHp = 100, baseAttack = 10)
        )
        combatViewModel.startEncounter(listOf(hero), twoEnemies, com.voicerpg.engine.model.BattleEnvironment.DUNGEON)
        combatViewModel.pauseAtb()
        combatViewModel.setPlayerInputPhaseForTesting("hero")

        val chosenWithTwo = combatViewModel.triggerAutoHeroAction(hero)
        assertNotNull("Auto hero action should select a spell", chosenWithTwo)
        assertTrue("When 2+ enemies are alive, auto battle must choose an AOE spell", chosenWithTwo!!.hitsAll)
        assertTrue(chosenWithTwo.id == "ca_executive_action" || chosenWithTwo.id == "ca_art_of_the_deal")

        // Case 2: Only 1 enemy alive on field -> auto battle should select high-power single target spell
        val oneEnemy = listOf(
            com.voicerpg.engine.model.Enemy("e1", "K-Street Lobbyist", "Vanguard", currentHp = 100, maxHp = 100, baseAttack = 10)
        )
        combatViewModel.startEncounter(listOf(hero), oneEnemy, com.voicerpg.engine.model.BattleEnvironment.DUNGEON)
        combatViewModel.pauseAtb()
        combatViewModel.setPlayerInputPhaseForTesting("hero")

        val chosenWithOne = combatViewModel.triggerAutoHeroAction(hero)
        assertNotNull(chosenWithOne)
        assertFalse("When only 1 enemy remains, auto battle should not prefer AOE spell", chosenWithOne!!.hitsAll)
        assertEquals("ca_obama_did_it_first", chosenWithOne.id)
    }

    @Test
    fun testFiveHeroProgressionAcrossActs() {
        assertEquals(1, StoryEncounters.ACT1_LOBBY_BATTLE.initialParty?.size)
        assertEquals("hero", StoryEncounters.ACT1_LOBBY_BATTLE.initialParty!![0].id)

        // Act 2: Marco joins (2 heroes)
        val act2Party = StoryEncounters.ACT2_MEDIA_BATTLE.initialParty!!
        assertEquals(2, act2Party.size)
        assertEquals("hero", act2Party[0].id)
        assertEquals("marco", act2Party[1].id)

        // Act 3: Vance joins (3 heroes)
        val act3Party = StoryEncounters.ACT3_SUMMIT_BATTLE.initialParty!!
        assertEquals(3, act3Party.size)
        assertEquals("vance", act3Party[2].id)

        // Act 4: Kash joins (4 heroes)
        val act4Party = StoryEncounters.ACT4_HOAX_BATTLE.initialParty!!
        assertEquals(4, act4Party.size)
        assertEquals("kash", act4Party[3].id)

        // Act 5..8: Senator Kennedy joins (Full 5-hero supergroup)
        val act5Party = StoryEncounters.ACT5_GOLEM_BATTLE.initialParty!!
        assertEquals(5, act5Party.size)
        assertEquals("hero", act5Party[0].id)
        assertEquals("marco", act5Party[1].id)
        assertEquals("vance", act5Party[2].id)
        assertEquals("kash", act5Party[3].id)
        assertEquals("kennedy", act5Party[4].id)

        assertEquals(5, StoryEncounters.ACT6_KAMALA_BATTLE.initialParty?.size)
        assertEquals(5, StoryEncounters.ACT7_LAWFARE_BATTLE.initialParty?.size)
        assertEquals(5, StoryEncounters.ACT8_BIDEN_FINAL_BATTLE.initialParty?.size)
    }

    @Test
    fun testAllCompanionsHaveExactFourSpellCategories() {
        val companions = listOf(
            TemplateHero.createMarcoRubio(),
            TemplateHero.createJDVance(),
            TemplateHero.createKashPatel(),
            TemplateHero.createJohnKennedy()
        )

        for (comp in companions) {
            assertEquals("${comp.name} must have exactly 4 spells", 4, comp.spells.size)

            // 1. Single-target damaging spell with stun
            val singleTargetStun = comp.spells.filter { !it.hitsAll && !it.isHeal && it.manaRestorePct == 0f && it.status == "OVERLOAD" }
            assertEquals("${comp.name} must have 1 single target stun spell", 1, singleTargetStun.size)

            // 2. AoE damaging spell
            val aoeSpell = comp.spells.filter { it.hitsAll && !it.isHeal && it.manaRestorePct == 0f }
            assertEquals("${comp.name} must have 1 AoE damage spell", 1, aoeSpell.size)

            // 3. Heal spell
            val healSpell = comp.spells.filter { it.isHeal }
            assertEquals("${comp.name} must have 1 heal spell", 1, healSpell.size)

            // 4. Mana restore spell
            val manaSpell = comp.spells.filter { it.manaRestorePct > 0f }
            assertEquals("${comp.name} must have 1 mana restore spell", 1, manaSpell.size)
        }
    }

    @Test
    fun testRecruitmentDialogueNodesFlow() {
        // Verify Act 2 Marco recruitment node exists and leads towards battle
        assertNotNull(StoryScript.ALL_NODES["act2_marco_joins"])
        assertEquals("marco", StoryScript.ALL_NODES["act2_marco_joins"]!!.speaker.id)

        // Verify Act 3 Vance recruitment node exists
        assertNotNull(StoryScript.ALL_NODES["act3_vance_joins"])
        assertEquals("vance", StoryScript.ALL_NODES["act3_vance_joins"]!!.speaker.id)

        // Verify Act 4 Kash recruitment node exists
        assertNotNull(StoryScript.ALL_NODES["act4_kash_joins"])
        assertEquals("kash", StoryScript.ALL_NODES["act4_kash_joins"]!!.speaker.id)

        // Verify Act 5 Kennedy recruitment node exists
        assertNotNull(StoryScript.ALL_NODES["act5_kennedy_joins"])
        assertEquals("kennedy", StoryScript.ALL_NODES["act5_kennedy_joins"]!!.speaker.id)
    }

    @Test
    fun testIntentParserTwoTierSpellMatchingAndWrongCharacterDetection() {
        val fullParty = StoryEncounters.createFullAmericaParty()
        val trump = fullParty.first { it.id == "hero" }
        val marco = fullParty.first { it.id == "marco" }
        val vance = fullParty.first { it.id == "vance" }
        val kash = fullParty.first { it.id == "kash" }
        val kennedy = fullParty.first { it.id == "kennedy" }

        // 1. When Trump is active and casts his own spell
        val trumpTremendous = IntentParser.parse("tremendous strike", trump.spells, emptyList(), fullParty)
        assertFalse(trumpTremendous.isWrongCharacterSpell)
        assertEquals(TemplateHero.TREMENDOUS_STRIKE.id, trumpTremendous.spell.id)

        // 2. When Trump is active but user tries to cast Marco's "maximum sanctions"
        val trumpCastingSanctions = IntentParser.parse("maximum sanctions", trump.spells, emptyList(), fullParty)
        assertTrue(trumpCastingSanctions.isWrongCharacterSpell)
        assertEquals("marco", trumpCastingSanctions.wrongCharacterOwnerId)
        assertEquals(TemplateHero.MAXIMUM_SANCTIONS.id, trumpCastingSanctions.spell.id)

        // 3. When Vance is active and casts his own "Appalachian Slam"
        val vanceSlam = IntentParser.parse("appalachian slam", vance.spells, emptyList(), fullParty)
        assertFalse(vanceSlam.isWrongCharacterSpell)
        assertEquals(TemplateHero.APPALACHIAN_SLAM.id, vanceSlam.spell.id)

        // 4. When Vance is active but user tries to cast Trump's "Presidential Veto"
        val vanceCastingVeto = IntentParser.parse("presidential veto", vance.spells, emptyList(), fullParty)
        assertTrue(vanceCastingVeto.isWrongCharacterSpell)
        assertEquals("hero", vanceCastingVeto.wrongCharacterOwnerId)
        assertEquals(TemplateHero.PRESIDENTIAL_VETO.id, vanceCastingVeto.spell.id)

        // 5. When Kash is active and casts his own "Declassify now"
        val kashDeclassify = IntentParser.parse("declassify now", kash.spells, emptyList(), fullParty)
        assertFalse(kashDeclassify.isWrongCharacterSpell)
        assertEquals(TemplateHero.DECLASSIFY_NOW.id, kashDeclassify.spell.id)

        // 6. When Kennedy is active and casts his own "Devastating one liner"
        val kennedyOneLiner = IntentParser.parse("devastating one-liner", kennedy.spells, emptyList(), fullParty)
        assertFalse(kennedyOneLiner.isWrongCharacterSpell)
        assertEquals(TemplateHero.DEVASTATING_ONELINER.id, kennedyOneLiner.spell.id)

        // 7. When Kennedy is active but user tries to cast Kash's "Plug the leaks"
        val kennedyCastingLeaks = IntentParser.parse("plug the leaks", kennedy.spells, emptyList(), fullParty)
        assertTrue(kennedyCastingLeaks.isWrongCharacterSpell)
        assertEquals("kash", kennedyCastingLeaks.wrongCharacterOwnerId)
        assertEquals(TemplateHero.LEAK_PLUG.id, kennedyCastingLeaks.spell.id)
    }

    @Test
    fun testAct2ThreeChaptersFullProgressionAndDebugWarps() {
        // -----------------------------------------------------------------------
        // 1. Warp into Act II Chapter 1: Inaugural Address (National Mall)
        // -----------------------------------------------------------------------
        storyViewModel.debugWarpToChapter("act2_ch1_intro")
        assertEquals("act2_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act2_ch1_mall", storyViewModel.state.value.currentScene.id)
        // Act II Ch1 is solo — Marco has NOT joined yet
        assertEquals(1, storyViewModel.state.value.partyStats.size)
        assertEquals("hero", storyViewModel.state.value.partyStats[0].id)
        assertFalse("Marco must NOT be recruited at Ch1 warp",
            storyViewModel.state.value.narrativeFlags["marco_recruited"] == true)

        // Advance through Ch1 hub to spin crew ambush → battle trigger
        storyViewModel.advanceDialogue() // → act2_ch1_address_hub
        assertEquals("act2_ch1_address_hub", storyViewModel.state.value.currentNode.id)

        // Select "proceed" to skip hub lore and go straight to the ambush
        val proceedChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a2c1_proceed" }
        storyViewModel.selectChoice(proceedChoice)
        assertEquals("act2_ch1_spin_crew_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act2_ch1_inauguration_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Trigger and win Ch1 battle
        storyViewModel.triggerEncounter("act2_ch1_inauguration_battle")
        assertEquals(GameScreen.COMBAT_ARENA, storyViewModel.state.value.gameScreen)
        assertEquals("act2_ch1_inauguration_battle", storyViewModel.state.value.activeEncounter?.id)
        storyViewModel.onCombatVictory()
        assertEquals("act2_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act2_ch2_oval", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 2. Act II Chapter 2: The Oval Citadel & The Executive Pen
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch2_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch2_resolute_hub", storyViewModel.state.value.currentNode.id)

        // Select "marco" choice to trigger Marco's arrival
        val marcoChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a2c2_marco" }
        storyViewModel.selectChoice(marcoChoice)
        assertEquals("act2_ch2_marco_arrives", storyViewModel.state.value.currentNode.id)
        assertEquals("marco", storyViewModel.state.value.currentNode.speaker.id)

        storyViewModel.advanceDialogue()
        assertEquals("act2_ch2_hero_marco_reply", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch2_hulk_marco", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch2_victory_advance", storyViewModel.state.value.currentNode.id)
        // This node transitions to Ch3 Rotunda
        assertEquals("scene_act2_ch3_rotunda", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 3. Act II Chapter 3: The Stock-Trading Lich (Capitol Rotunda)
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch3_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch3_pelosi_enter", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch3_hero_callout", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch3_marco_expose", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch3_pelosi_counter", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch3_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act2_media_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        storyViewModel.triggerEncounter("act2_media_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act2_ch3_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act3_ch1_steel_mill", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 4. Debug warp assertions for Act II
        // -----------------------------------------------------------------------
        // Ch1 warp → solo Commander America, no Marco
        storyViewModel.debugWarpToChapter("act2_ch1_intro")
        assertEquals("scene_act2_ch1_mall", storyViewModel.state.value.currentScene.id)
        assertEquals(1, storyViewModel.state.value.partyStats.size)
        assertEquals("hero", storyViewModel.state.value.partyStats[0].id)

        // Ch2 warp → Marco should be seeded (index 5)
        storyViewModel.debugWarpToChapter("act2_ch2_intro")
        assertEquals("scene_act2_ch2_oval", storyViewModel.state.value.currentScene.id)
        assertTrue("Marco must be seeded for Ch2 warp",
            storyViewModel.state.value.narrativeFlags["marco_recruited"] == true)
        val ch2Party = storyViewModel.state.value.partyStats
        assertTrue("Party must contain hero", ch2Party.any { it.id == "hero" })
        assertTrue("Party must contain marco for Ch2 warp", ch2Party.any { it.id == "marco" })

        // Ch3 warp → Marco seeded, no Vance
        storyViewModel.debugWarpToChapter("act2_ch3_intro")
        assertEquals("scene_act2_ch3_rotunda", storyViewModel.state.value.currentScene.id)
        assertTrue("Marco must be seeded for Ch3 warp",
            storyViewModel.state.value.narrativeFlags["marco_recruited"] == true)
        assertFalse("Vance must NOT be seeded for Ch3 warp",
            storyViewModel.state.value.narrativeFlags["vance_recruited"] == true)
    }

    @Test
    fun testAct2EncountersAndPartyRoster() {
        // Ch1 Inauguration Battle — solo Commander America
        val inaugBattle = StoryEncounters.ACT2_CH1_INAUGURATION_BATTLE
        assertEquals("act2_ch1_inauguration_battle", inaugBattle.id)
        val spinAnchor = inaugBattle.enemies.firstOrNull { it.id == "aerial_spin_anchor" }
        assertNotNull("Aerial Spin Anchor must be an enemy in Ch1 battle", spinAnchor)
        assertTrue("Aerial Spin Anchor must be the boss", spinAnchor!!.isBoss)
        assertEquals(1, inaugBattle.initialParty?.size)
        assertEquals("hero", inaugBattle.initialParty!![0].id)

        // Ch3 Media Battle — Commander America + Marco
        val mediaBattle = StoryEncounters.ACT2_MEDIA_BATTLE
        assertEquals("act2_media_battle", mediaBattle.id)
        val pelosi = mediaBattle.enemies.firstOrNull { it.id == "pelosi" }
        assertNotNull("Pelosi must be an enemy in the Act 2 Media Battle", pelosi)
        assertEquals(2, mediaBattle.initialParty?.size)
        assertEquals("hero", mediaBattle.initialParty!![0].id)
        assertEquals("marco", mediaBattle.initialParty!![1].id)

        // Verify all Act II encounters are in ALL_ENCOUNTERS
        val allIds = StoryEncounters.ALL_ENCOUNTERS.map { it.id }
        assertTrue("act2_ch1_inauguration_battle must be in ALL_ENCOUNTERS",
            allIds.contains("act2_ch1_inauguration_battle"))
        assertTrue("act2_media_battle must be in ALL_ENCOUNTERS",
            allIds.contains("act2_media_battle"))
    }

    @Test
    fun testAct3FourChaptersFullProgressionAndDebugWarps() {
        // -----------------------------------------------------------------------
        // 1. Warp into Act III Chapter 1: The Ohio Steel Mill Awakening
        // -----------------------------------------------------------------------
        storyViewModel.debugWarpToChapter("act3_ch1_intro")
        assertEquals("act3_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act3_ch1_steel_mill", storyViewModel.state.value.currentScene.id)
        // In Act III warp, Vance is seeded (index 6)
        val ch1Party = storyViewModel.state.value.partyStats
        assertTrue("Party must contain hero", ch1Party.any { it.id == "hero" })
        assertTrue("Party must contain marco", ch1Party.any { it.id == "marco" })
        assertTrue("Party must contain vance", ch1Party.any { it.id == "vance" })
        assertFalse("Kash must NOT be seeded for Act 3 warp",
            storyViewModel.state.value.narrativeFlags["kash_recruited"] == true)

        // Advance to Ch1 hub
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch1_mill_hub", storyViewModel.state.value.currentNode.id)

        // Select "vance" choice
        val vanceChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a3c1_vance" }
        storyViewModel.selectChoice(vanceChoice)
        assertEquals("act3_ch1_vance_arrives", storyViewModel.state.value.currentNode.id)
        assertEquals("vance", storyViewModel.state.value.currentNode.speaker.id)

        storyViewModel.advanceDialogue()
        assertEquals("act3_ch1_hero_vance_reply", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch1_union_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act3_ch1_mill_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch1 battle
        storyViewModel.triggerEncounter("act3_ch1_mill_battle")
        assertEquals(GameScreen.COMBAT_ARENA, storyViewModel.state.value.gameScreen)
        assertEquals("act3_ch1_mill_battle", storyViewModel.state.value.activeEncounter?.id)
        storyViewModel.onCombatVictory()
        assertEquals("act3_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act3_ch2_usmca", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 2. Act III Chapter 2: The USMCA Renegotiation Showdown
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch2_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch2_table_hub", storyViewModel.state.value.currentNode.id)

        // Select "sign" choice
        val signChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a3c2_sign" }
        storyViewModel.selectChoice(signChoice)
        assertEquals("act3_ch2_sign_deal", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch2_hulk_celebrate", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch2_post_battle_transition", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act3_ch3_af1", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 3. Act III Chapter 3: The Diplomatic Flight on Air Force One
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch3_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch3_briefing_hub", storyViewModel.state.value.currentNode.id)

        // Select "interceptors" choice
        val interceptChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a3c3_interceptors" }
        storyViewModel.selectChoice(interceptChoice)
        assertEquals("act3_ch3_interceptor_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch3_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act3_ch3_intercept_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch3 battle
        storyViewModel.triggerEncounter("act3_ch3_intercept_battle")
        assertEquals(GameScreen.COMBAT_ARENA, storyViewModel.state.value.gameScreen)
        assertEquals("act3_ch3_intercept_battle", storyViewModel.state.value.activeEncounter?.id)
        storyViewModel.onCombatVictory()
        assertEquals("act3_ch3_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act3_ch4_summit", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 4. Act III Chapter 4: The Crimson Summit of Peace
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch4_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch4_summit_hub", storyViewModel.state.value.currentNode.id)

        // Select "fight" challenge choice
        val fightChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a3c4_fight" }
        storyViewModel.selectChoice(fightChoice)
        assertEquals("act3_ch4_fight_challenge", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch4_rocket_reaction", storyViewModel.state.value.currentNode.id)
        assertEquals("rocket_man", storyViewModel.state.value.currentNode.speaker.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch4_vance_battle_hype", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch4_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act3_summit_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch4 Boss battle
        storyViewModel.triggerEncounter("act3_summit_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act3_ch4_post_battle", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch4_victory_speech", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch4_hulk_close", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act4_ch1_fisa", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 5. Debug warps for all Act III chapters
        // -----------------------------------------------------------------------
        val act3Targets = listOf(
            "act3_ch1_intro" to "scene_act3_ch1_steel_mill",
            "act3_ch2_intro" to "scene_act3_ch2_usmca",
            "act3_ch3_intro" to "scene_act3_ch3_af1",
            "act3_ch4_intro" to "scene_act3_ch4_summit"
        )
        for ((nodeId, expectedSceneId) in act3Targets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
            val party = storyViewModel.state.value.partyStats
            assertTrue("Party must have hero at $nodeId", party.any { it.id == "hero" })
            assertTrue("Party must have marco at $nodeId", party.any { it.id == "marco" })
            assertTrue("Party must have vance at $nodeId", party.any { it.id == "vance" })
            assertFalse("Party must NOT have kash at $nodeId", party.any { it.id == "kash" })
        }
    }

    @Test
    fun testAct3EncountersAndPartyRoster() {
        // Ch1 Mill Battle — Hero, Marco, Vance
        val millBattle = StoryEncounters.ACT3_CH1_MILL_BATTLE
        assertEquals("act3_ch1_mill_battle", millBattle.id)
        assertEquals(3, millBattle.initialParty?.size)
        val cartelLawyer = millBattle.enemies.firstOrNull { it.id == "cartel_lawyer" }
        assertNotNull("Globalist Trade Cartel Envoy must be an enemy", cartelLawyer)
        assertTrue("Cartel Envoy is boss", cartelLawyer!!.isBoss)

        // Ch3 Intercept Battle — Hero, Marco, Vance
        val interceptBattle = StoryEncounters.ACT3_CH3_INTERCEPT_BATTLE
        assertEquals("act3_ch3_intercept_battle", interceptBattle.id)
        assertEquals(3, interceptBattle.initialParty?.size)
        val interceptCommander = interceptBattle.enemies.firstOrNull { it.id == "intercept_commander" }
        assertNotNull("Intercept Commander must be in Ch3 battle", interceptCommander)
        assertTrue("Intercept Commander is boss", interceptCommander!!.isBoss)

        // Ch4 Summit Duel — Rocket Man
        val summitBattle = StoryEncounters.ACT3_SUMMIT_BATTLE
        assertEquals("act3_summit_battle", summitBattle.id)
        assertEquals(3, summitBattle.initialParty?.size)
        val rocketMan = summitBattle.enemies.firstOrNull { it.id == "rocket_man" }
        assertNotNull("Rocket Man must be present", rocketMan)

        // Verify in ALL_ENCOUNTERS
        val allIds = StoryEncounters.ALL_ENCOUNTERS.map { it.id }
        assertTrue("act3_ch1_mill_battle must be in ALL_ENCOUNTERS", allIds.contains("act3_ch1_mill_battle"))
        assertTrue("act3_ch3_intercept_battle must be in ALL_ENCOUNTERS", allIds.contains("act3_ch3_intercept_battle"))
        assertTrue("act3_summit_battle must be in ALL_ENCOUNTERS", allIds.contains("act3_summit_battle"))
    }

    @Test
    fun testAct4FourChaptersFullProgressionAndDebugWarps() {
        // -----------------------------------------------------------------------
        // 1. Warp into Act IV Chapter 1: The FISA Warrant Labyrinth
        // -----------------------------------------------------------------------
        storyViewModel.debugWarpToChapter("act4_ch1_intro")
        assertEquals("act4_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act4_ch1_fisa", storyViewModel.state.value.currentScene.id)
        // In Act IV warp, Kash is seeded (index 7)
        val ch1Party = storyViewModel.state.value.partyStats
        assertTrue("Party must contain hero", ch1Party.any { it.id == "hero" })
        assertTrue("Party must contain marco", ch1Party.any { it.id == "marco" })
        assertTrue("Party must contain vance", ch1Party.any { it.id == "vance" })
        assertTrue("Party must contain kash", ch1Party.any { it.id == "kash" })
        assertFalse("Kennedy must NOT be seeded for Act 4 warp",
            storyViewModel.state.value.narrativeFlags["kennedy_recruited"] == true)

        // Advance to Ch1 hub
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch1_fisa_hub", storyViewModel.state.value.currentNode.id)

        // Select "kash" choice to trigger Kash's arrival
        val kashChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a4c1_kash" }
        storyViewModel.selectChoice(kashChoice)
        assertEquals("act4_ch1_kash_arrives", storyViewModel.state.value.currentNode.id)
        assertEquals("kash", storyViewModel.state.value.currentNode.speaker.id)

        storyViewModel.advanceDialogue()
        assertEquals("act4_ch1_hero_kash_reply", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch1_bureau_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act4_ch1_fisa_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch1 battle
        storyViewModel.triggerEncounter("act4_ch1_fisa_battle")
        assertEquals(GameScreen.COMBAT_ARENA, storyViewModel.state.value.gameScreen)
        assertEquals("act4_ch1_fisa_battle", storyViewModel.state.value.activeEncounter?.id)
        storyViewModel.onCombatVictory()
        assertEquals("act4_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act4_ch2_binder", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 2. Act IV Chapter 2: The Unredacted Binder Inquest
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch2_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch2_vault_hub", storyViewModel.state.value.currentNode.id)

        // Select "proceed" choice
        val proceedChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a4c2_proceed" }
        storyViewModel.selectChoice(proceedChoice)
        assertEquals("act4_ch2_advance_to_court", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch2_hulk_march", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act4_ch3_court", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 3. Act IV Chapter 3: The Phantom Subpoena Ambush
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch3_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch3_steps_hub", storyViewModel.state.value.currentNode.id)

        // Select "swat" choice
        val swatChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a4c3_swat" }
        storyViewModel.selectChoice(swatChoice)
        assertEquals("act4_ch3_subpoena_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch3_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act4_ch3_subpoena_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch3 battle
        storyViewModel.triggerEncounter("act4_ch3_subpoena_battle")
        assertEquals(GameScreen.COMBAT_ARENA, storyViewModel.state.value.gameScreen)
        assertEquals("act4_ch3_subpoena_battle", storyViewModel.state.value.activeEncounter?.id)
        storyViewModel.onCombatVictory()
        assertEquals("act4_ch3_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act4_ch4_hearing", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 4. Act IV Chapter 4: The Mueller Illusion Hoax Climax
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch4_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch4_hearing_hub", storyViewModel.state.value.currentNode.id)

        // Select "fight" challenge choice
        val fightChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a4c4_fight" }
        storyViewModel.selectChoice(fightChoice)
        assertEquals("act4_ch4_phantom_confront", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch4_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act4_hoax_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch4 Boss battle
        storyViewModel.triggerEncounter("act4_hoax_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act4_ch4_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act5_ch1_arena", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 5. Debug warps for all Act IV chapters
        // -----------------------------------------------------------------------
        val act4Targets = listOf(
            "act4_ch1_intro" to "scene_act4_ch1_fisa",
            "act4_ch2_intro" to "scene_act4_ch2_binder",
            "act4_ch3_intro" to "scene_act4_ch3_court",
            "act4_ch4_intro" to "scene_act4_ch4_hearing"
        )
        for ((nodeId, expectedSceneId) in act4Targets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
            val party = storyViewModel.state.value.partyStats
            assertTrue("Party must have hero at $nodeId", party.any { it.id == "hero" })
            assertTrue("Party must have marco at $nodeId", party.any { it.id == "marco" })
            assertTrue("Party must have vance at $nodeId", party.any { it.id == "vance" })
            assertTrue("Party must have kash at $nodeId", party.any { it.id == "kash" })
            assertFalse("Party must NOT have kennedy at $nodeId", party.any { it.id == "kennedy" })
        }
    }

    @Test
    fun testAct4EncountersAndPartyRoster() {
        // Ch1 FISA Battle — Hero, Marco, Vance, Kash
        val fisaBattle = StoryEncounters.ACT4_CH1_FISA_BATTLE
        assertEquals("act4_ch1_fisa_battle", fisaBattle.id)
        assertEquals(4, fisaBattle.initialParty?.size)
        val archivistBoss = fisaBattle.enemies.firstOrNull { it.id == "archivist_boss" }
        assertNotNull("Archivist Boss must be present", archivistBoss)
        assertTrue("Archivist Boss is boss", archivistBoss!!.isBoss)

        // Ch3 Subpoena Battle — Hero, Marco, Vance, Kash
        val subpoenaBattle = StoryEncounters.ACT4_CH3_SUBPOENA_BATTLE
        assertEquals("act4_ch3_subpoena_battle", subpoenaBattle.id)
        assertEquals(4, subpoenaBattle.initialParty?.size)
        val processServerBoss = subpoenaBattle.enemies.firstOrNull { it.id == "process_server_boss" }
        assertNotNull("Process Server Boss must be present", processServerBoss)
        assertTrue("Process Server Boss is boss", processServerBoss!!.isBoss)

        // Ch4 Hoax Battle — Mueller Ghost
        val hoaxBattle = StoryEncounters.ACT4_HOAX_BATTLE
        assertEquals("act4_hoax_battle", hoaxBattle.id)
        assertEquals(4, hoaxBattle.initialParty?.size)
        val muellerGhost = hoaxBattle.enemies.firstOrNull { it.id == "mueller_ghost" }
        assertNotNull("Mueller Ghost must be present", muellerGhost)

        // Verify in ALL_ENCOUNTERS
        val allIds = StoryEncounters.ALL_ENCOUNTERS.map { it.id }
        assertTrue("act4_ch1_fisa_battle must be in ALL_ENCOUNTERS", allIds.contains("act4_ch1_fisa_battle"))
        assertTrue("act4_ch3_subpoena_battle must be in ALL_ENCOUNTERS", allIds.contains("act4_ch3_subpoena_battle"))
        assertTrue("act4_hoax_battle must be in ALL_ENCOUNTERS", allIds.contains("act4_hoax_battle"))
    }

    @Test
    fun testAct5FourChaptersFullProgressionAndDebugWarps() {
        // -----------------------------------------------------------------------
        // 1. Warp into Act V Chapter 1: The 3:00 AM Water Pipe Mystery
        // -----------------------------------------------------------------------
        storyViewModel.debugWarpToChapter("act5_ch1_intro")
        assertEquals("act5_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act5_ch1_arena", storyViewModel.state.value.currentScene.id)
        // In Act V Ch1 warp, party has 4 heroes (Hero, Marco, Vance, Kash) — Kennedy hasn't joined yet
        val ch1Party = storyViewModel.state.value.partyStats
        assertEquals(4, ch1Party.size)
        assertTrue("Party must contain hero", ch1Party.any { it.id == "hero" })
        assertTrue("Party must contain marco", ch1Party.any { it.id == "marco" })
        assertTrue("Party must contain vance", ch1Party.any { it.id == "vance" })
        assertTrue("Party must contain kash", ch1Party.any { it.id == "kash" })
        assertFalse("Kennedy must NOT be seeded for Act 5 Ch1 warp",
            storyViewModel.state.value.narrativeFlags["kennedy_recruited"] == true)

        // Advance to Ch1 hub
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch1_arena_hub", storyViewModel.state.value.currentNode.id)

        // Select "confront" choice
        val confrontChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a5c1_confront" }
        storyViewModel.selectChoice(confrontChoice)
        assertEquals("act5_ch1_golem_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act5_ch1_pipe_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch1 battle
        storyViewModel.triggerEncounter("act5_ch1_pipe_battle")
        assertEquals(GameScreen.COMBAT_ARENA, storyViewModel.state.value.gameScreen)
        assertEquals("act5_ch1_pipe_battle", storyViewModel.state.value.activeEncounter?.id)
        storyViewModel.onCombatVictory()
        assertEquals("act5_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act5_ch2_senate", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 2. Act V Chapter 2: The Senate Inquest & Senator Kennedy Joins
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch2_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch2_senate_hub", storyViewModel.state.value.currentNode.id)

        // Select "kennedy" choice to recruit Senator Kennedy
        val kennedyChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a5c2_kennedy" }
        storyViewModel.selectChoice(kennedyChoice)
        assertEquals("act5_ch2_kennedy_arrives", storyViewModel.state.value.currentNode.id)
        assertEquals("kennedy", storyViewModel.state.value.currentNode.speaker.id)

        storyViewModel.advanceDialogue()
        assertEquals("act5_ch2_hero_kennedy_reply", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch2_stonewall_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch2_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act5_ch2_inquest_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch2 battle
        storyViewModel.triggerEncounter("act5_ch2_inquest_battle")
        assertEquals(GameScreen.COMBAT_ARENA, storyViewModel.state.value.gameScreen)
        assertEquals("act5_ch2_inquest_battle", storyViewModel.state.value.activeEncounter?.id)
        storyViewModel.onCombatVictory()
        assertEquals("act5_ch2_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act5_ch3_terrace", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 3. Act V Chapter 3: The Southern White House (Mar-a-Lago Terrace)
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch3_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch3_terrace_hub", storyViewModel.state.value.currentNode.id)

        // Select "briefing" choice
        val briefingChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a5c3_briefing" }
        storyViewModel.selectChoice(briefingChoice)
        assertEquals("act5_ch3_war_room", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch3_hero_closets", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act5_ch4_closets", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 4. Act V Chapter 4: The Deep State Raid on the Golden Closets
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch4_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch4_vault_hub", storyViewModel.state.value.currentNode.id)

        // Select "battle" choice
        val battleChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a5c4_battle" }
        storyViewModel.selectChoice(battleChoice)
        assertEquals("act5_ch4_raid_confront", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch4_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act5_golem_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch4 Boss battle
        storyViewModel.triggerEncounter("act5_golem_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act5_ch4_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act6_ch1_border", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 5. Debug warps for all Act V chapters
        // -----------------------------------------------------------------------
        // Ch1: 4 heroes (Kennedy not seeded)
        storyViewModel.debugWarpToChapter("act5_ch1_intro")
        assertEquals("act5_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act5_ch1_arena", storyViewModel.state.value.currentScene.id)
        val ch1WarpParty = storyViewModel.state.value.partyStats
        assertEquals(4, ch1WarpParty.size)
        assertFalse(ch1WarpParty.any { it.id == "kennedy" })

        // Ch2, Ch3, Ch4: 5 heroes (Kennedy seeded)
        val act5FullTargets = listOf(
            "act5_ch2_intro" to "scene_act5_ch2_senate",
            "act5_ch3_intro" to "scene_act5_ch3_terrace",
            "act5_ch4_intro" to "scene_act5_ch4_closets"
        )
        for ((nodeId, expectedSceneId) in act5FullTargets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
            val party = storyViewModel.state.value.partyStats
            assertEquals("Party size must be 5 at $nodeId", 5, party.size)
            assertTrue("Party must have hero at $nodeId", party.any { it.id == "hero" })
            assertTrue("Party must have marco at $nodeId", party.any { it.id == "marco" })
            assertTrue("Party must have vance at $nodeId", party.any { it.id == "vance" })
            assertTrue("Party must have kash at $nodeId", party.any { it.id == "kash" })
            assertTrue("Party must have kennedy at $nodeId", party.any { it.id == "kennedy" })
        }
    }

    @Test
    fun testAct5EncountersAndPartyRoster() {
        // Ch1 Pipe Battle — Hero, Marco, Vance, Kash (4 heroes)
        val pipeBattle = StoryEncounters.ACT5_CH1_PIPE_BATTLE
        assertEquals("act5_ch1_pipe_battle", pipeBattle.id)
        assertEquals(4, pipeBattle.initialParty?.size)
        val burstPipe = pipeBattle.enemies.firstOrNull { it.id == "burst_pipe" }
        assertNotNull("Burst Pipe Illusionist must be present", burstPipe)
        assertTrue("Burst Pipe is boss", burstPipe!!.isBoss)

        // Ch2 Inquest Battle — All 5 heroes including Kennedy
        val inquestBattle = StoryEncounters.ACT5_CH2_INQUEST_BATTLE
        assertEquals("act5_ch2_inquest_battle", inquestBattle.id)
        assertEquals(5, inquestBattle.initialParty?.size)
        val stonewallDirector = inquestBattle.enemies.firstOrNull { it.id == "stonewall_director" }
        assertNotNull("Stonewall Director must be present", stonewallDirector)
        assertTrue("Stonewall Director is boss", stonewallDirector!!.isBoss)

        // Ch4 Golem Battle — All 5 heroes
        val golemBattle = StoryEncounters.ACT5_GOLEM_BATTLE
        assertEquals("act5_golem_battle", golemBattle.id)
        assertEquals(5, golemBattle.initialParty?.size)
        val ballotGolem = golemBattle.enemies.firstOrNull { it.id == "ballot_golem" }
        assertNotNull("Ballot Golem must be present", ballotGolem)

        // Verify in ALL_ENCOUNTERS
        val allIds = StoryEncounters.ALL_ENCOUNTERS.map { it.id }
        assertTrue("act5_ch1_pipe_battle must be in ALL_ENCOUNTERS", allIds.contains("act5_ch1_pipe_battle"))
        assertTrue("act5_ch2_inquest_battle must be in ALL_ENCOUNTERS", allIds.contains("act5_ch2_inquest_battle"))
        assertTrue("act5_golem_battle must be in ALL_ENCOUNTERS", allIds.contains("act5_golem_battle"))
    }

    @Test
    fun testAct6FiveChaptersFullProgressionAndDebugWarps() {
        // -----------------------------------------------------------------------
        // 1. Act VI Chapter 1: The Border Czar Inspection (The Missing Border Wall Gap)
        // -----------------------------------------------------------------------
        storyViewModel.debugWarpToChapter("act6_ch1_intro")
        assertEquals("act6_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act6_ch1_border", storyViewModel.state.value.currentScene.id)
        val ch1Party = storyViewModel.state.value.partyStats
        assertEquals(5, ch1Party.size)
        assertTrue(ch1Party.any { it.id == "hero" })
        assertTrue(ch1Party.any { it.id == "marco" })
        assertTrue(ch1Party.any { it.id == "vance" })
        assertTrue(ch1Party.any { it.id == "kash" })
        assertTrue(ch1Party.any { it.id == "kennedy" })

        storyViewModel.advanceDialogue()
        assertEquals("act6_ch1_border_arrival", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch1_border_hub", storyViewModel.state.value.currentNode.id)

        // Select proceed
        val proceedChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a6c1_proceed" }
        storyViewModel.selectChoice(proceedChoice)
        assertEquals("act6_ch1_cartel_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act6_ch1_border_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch1 battle
        storyViewModel.triggerEncounter("act6_ch1_border_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act6_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act6_ch2_venn", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 2. Act VI Chapter 2: The Venn Diagram Fog
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch2_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch2_venn_hub", storyViewModel.state.value.currentNode.id)

        // Select dispel choice
        val dispelChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a6c2_dispel" }
        storyViewModel.selectChoice(dispelChoice)
        assertEquals("act6_ch2_dispel_fog", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch2_hero_dispel", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch3_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act6_ch3_bus", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 3. Act VI Chapter 3: The Electric School Bus Mire
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch3_bus_hub", storyViewModel.state.value.currentNode.id)

        // Select confront
        val confrontChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a6c3_confront" }
        storyViewModel.selectChoice(confrontChoice)
        assertEquals("act6_ch3_golem_confront", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch3_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act6_ch3_bus_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch3 battle
        storyViewModel.triggerEncounter("act6_ch3_bus_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act6_ch3_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act6_ch4_coconut", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 4. Act VI Chapter 4: The Coconut Tree Grove
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch4_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch4_grove_hub", storyViewModel.state.value.currentNode.id)

        // Select throne choice
        val throneChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a6c4_throne" }
        storyViewModel.selectChoice(throneChoice)
        assertEquals("act6_ch4_advance_throne", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch5_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act6_ch5_unburdened", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 5. Act VI Chapter 5: The Unburdened Vice-Sovereign Confrontation
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch5_throne_hub", storyViewModel.state.value.currentNode.id)

        // Select battle choice
        val battleChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a6c5_battle" }
        storyViewModel.selectChoice(battleChoice)
        assertEquals("act6_ch5_climax_battle", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act6_ch5_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act6_kamala_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch5 Boss battle
        storyViewModel.triggerEncounter("act6_kamala_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act6_ch5_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act7_ch1_gauntlet", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 6. Debug warps for all Act VI chapters
        // -----------------------------------------------------------------------
        val act6FullTargets = listOf(
            "act6_ch1_intro" to "scene_act6_ch1_border",
            "act6_ch2_intro" to "scene_act6_ch2_venn",
            "act6_ch3_intro" to "scene_act6_ch3_bus",
            "act6_ch4_intro" to "scene_act6_ch4_coconut",
            "act6_ch5_intro" to "scene_act6_ch5_unburdened"
        )
        for ((nodeId, expectedSceneId) in act6FullTargets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
            val party = storyViewModel.state.value.partyStats
            assertEquals("Party size must be 5 at $nodeId", 5, party.size)
            assertTrue("Party must have hero at $nodeId", party.any { it.id == "hero" })
            assertTrue("Party must have marco at $nodeId", party.any { it.id == "marco" })
            assertTrue("Party must have vance at $nodeId", party.any { it.id == "vance" })
            assertTrue("Party must have kash at $nodeId", party.any { it.id == "kash" })
            assertTrue("Party must have kennedy at $nodeId", party.any { it.id == "kennedy" })
        }
    }

    @Test
    fun testAct6EncountersAndPartyRoster() {
        // Ch1 Border Battle
        val borderBattle = StoryEncounters.ACT6_CH1_BORDER_BATTLE
        assertEquals("act6_ch1_border_battle", borderBattle.id)
        assertEquals(5, borderBattle.initialParty?.size)
        val liaison = borderBattle.enemies.firstOrNull { it.id == "border_czar_liaison" }
        assertNotNull("Catch-and-Release Liaison must be present", liaison)
        assertTrue("Catch-and-Release Liaison is boss", liaison!!.isBoss)

        // Ch3 Bus Battle
        val busBattle = StoryEncounters.ACT6_CH3_BUS_BATTLE
        assertEquals("act6_ch3_bus_battle", busBattle.id)
        assertEquals(5, busBattle.initialParty?.size)
        val batteryGolem = busBattle.enemies.firstOrNull { it.id == "battery_golem" }
        assertNotNull("Stranded Battery Golem must be present", batteryGolem)
        assertTrue("Stranded Battery Golem is boss", batteryGolem!!.isBoss)

        // Ch5 Kamala Boss Battle
        val kamalaBattle = StoryEncounters.ACT6_KAMALA_BATTLE
        assertEquals("act6_kamala_battle", kamalaBattle.id)
        assertEquals(5, kamalaBattle.initialParty?.size)
        val kamala = kamalaBattle.enemies.firstOrNull { it.id == "kamala" }
        assertNotNull("Kamala must be present", kamala)

        // Verify in ALL_ENCOUNTERS
        val allIds = StoryEncounters.ALL_ENCOUNTERS.map { it.id }
        assertTrue("act6_ch1_border_battle must be in ALL_ENCOUNTERS", allIds.contains("act6_ch1_border_battle"))
        assertTrue("act6_ch3_bus_battle must be in ALL_ENCOUNTERS", allIds.contains("act6_ch3_bus_battle"))
        assertTrue("act6_kamala_battle must be in ALL_ENCOUNTERS", allIds.contains("act6_kamala_battle"))
    }

    @Test
    fun testAct7FiveChaptersFullProgressionAndDebugWarps() {
        // -----------------------------------------------------------------------
        // 1. Act VII Chapter 1: The 91 Indictment Gauntlet
        // -----------------------------------------------------------------------
        storyViewModel.debugWarpToChapter("act7_ch1_intro")
        assertEquals("act7_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act7_ch1_gauntlet", storyViewModel.state.value.currentScene.id)
        val ch1Party = storyViewModel.state.value.partyStats
        assertEquals(5, ch1Party.size)
        assertTrue(ch1Party.any { it.id == "hero" })
        assertTrue(ch1Party.any { it.id == "marco" })
        assertTrue(ch1Party.any { it.id == "vance" })
        assertTrue(ch1Party.any { it.id == "kash" })
        assertTrue(ch1Party.any { it.id == "kennedy" })

        storyViewModel.advanceDialogue()
        assertEquals("act7_ch1_court_arrival", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch1_gauntlet_hub", storyViewModel.state.value.currentNode.id)

        // Select proceed
        val proceedChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a7c1_proceed" }
        storyViewModel.selectChoice(proceedChoice)
        assertEquals("act7_ch1_prosecutor_ambush", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act7_ch1_indictment_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch1 battle
        storyViewModel.triggerEncounter("act7_ch1_indictment_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act7_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act7_ch2_gag", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 2. Act VII Chapter 2: The Gag Order Guillotine
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch2_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch2_gag_hub", storyViewModel.state.value.currentNode.id)

        // Select shatter choice
        val shatterChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a7c2_shatter" }
        storyViewModel.selectChoice(shatterChoice)
        assertEquals("act7_ch2_contempt_confront", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch2_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act7_ch2_contempt_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch2 battle
        storyViewModel.triggerEncounter("act7_ch2_contempt_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act7_ch2_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act7_ch3_butler", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 3. Act VII Chapter 3: The Butler Fairgrounds (July 13, 2024)
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch3_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch3_rally_hub", storyViewModel.state.value.currentNode.id)

        // Select advance choice
        val advancePodiumChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a7c3_advance" }
        storyViewModel.selectChoice(advancePodiumChoice)
        assertEquals("act7_ch3_take_podium", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch4_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act7_ch4_steel_ear", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 4. Act VII Chapter 4: Steel Ear & Fist Defiance
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch4_dogpile", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch4_shoes", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch4_fist_hub", storyViewModel.state.value.currentNode.id)

        // Select advance to Milwaukee
        val advanceMkeChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a7c4_advance" }
        storyViewModel.selectChoice(advanceMkeChoice)
        assertEquals("act7_ch4_advance_milwaukee", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch5_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act7_ch5_milwaukee", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 5. Act VII Chapter 5: The Milwaukee Arena Coronation
        // -----------------------------------------------------------------------
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch5_coronation_hub", storyViewModel.state.value.currentNode.id)

        // Select battle choice
        val battleChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a7c5_battle" }
        storyViewModel.selectChoice(battleChoice)
        assertEquals("act7_ch5_lawfare_confront", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act7_ch5_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act7_lawfare_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        // Win Ch5 Boss battle
        storyViewModel.triggerEncounter("act7_lawfare_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act7_ch5_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act8_citadel_final", storyViewModel.state.value.currentScene.id)

        // -----------------------------------------------------------------------
        // 6. Debug warps for all Act VII chapters
        // -----------------------------------------------------------------------
        val act7FullTargets = listOf(
            "act7_ch1_intro" to "scene_act7_ch1_gauntlet",
            "act7_ch2_intro" to "scene_act7_ch2_gag",
            "act7_ch3_intro" to "scene_act7_ch3_butler",
            "act7_ch4_intro" to "scene_act7_ch4_steel_ear",
            "act7_ch5_intro" to "scene_act7_ch5_milwaukee"
        )
        for ((nodeId, expectedSceneId) in act7FullTargets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
            val party = storyViewModel.state.value.partyStats
            assertEquals("Party size must be 5 at $nodeId", 5, party.size)
            assertTrue("Party must have hero at $nodeId", party.any { it.id == "hero" })
            assertTrue("Party must have marco at $nodeId", party.any { it.id == "marco" })
            assertTrue("Party must have vance at $nodeId", party.any { it.id == "vance" })
            assertTrue("Party must have kash at $nodeId", party.any { it.id == "kash" })
            assertTrue("Party must have kennedy at $nodeId", party.any { it.id == "kennedy" })
        }
    }

    @Test
    fun testAct7EncountersAndPartyRoster() {
        // Ch1 Indictment Battle
        val indictmentBattle = StoryEncounters.ACT7_CH1_INDICTMENT_BATTLE
        assertEquals("act7_ch1_indictment_battle", indictmentBattle.id)
        assertEquals(5, indictmentBattle.initialParty?.size)
        val prosecutor = indictmentBattle.enemies.firstOrNull { it.id == "partisan_prosecutor" }
        assertNotNull("Partisan Prosecutor must be present", prosecutor)
        assertTrue("Partisan Prosecutor is boss", prosecutor!!.isBoss)

        // Ch2 Contempt Battle
        val contemptBattle = StoryEncounters.ACT7_CH2_CONTEMPT_BATTLE
        assertEquals("act7_ch2_contempt_battle", contemptBattle.id)
        assertEquals(5, contemptBattle.initialParty?.size)
        val magistrate = contemptBattle.enemies.firstOrNull { it.id == "gag_magistrate" }
        assertNotNull("Gag Magistrate must be present", magistrate)
        assertTrue("Gag Magistrate is boss", magistrate!!.isBoss)

        // Ch5 Lawfare Boss Battle
        val lawfareBattle = StoryEncounters.ACT7_LAWFARE_BATTLE
        assertEquals("act7_lawfare_battle", lawfareBattle.id)
        assertEquals(5, lawfareBattle.initialParty?.size)
        val lawfareLich = lawfareBattle.enemies.firstOrNull { it.id == "lawfare_lich" }
        assertNotNull("Lawfare Lich must be present", lawfareLich)

        // Verify in ALL_ENCOUNTERS
        val allIds = StoryEncounters.ALL_ENCOUNTERS.map { it.id }
        assertTrue("act7_ch1_indictment_battle must be in ALL_ENCOUNTERS", allIds.contains("act7_ch1_indictment_battle"))
        assertTrue("act7_ch2_contempt_battle must be in ALL_ENCOUNTERS", allIds.contains("act7_ch2_contempt_battle"))
        assertTrue("act7_lawfare_battle must be in ALL_ENCOUNTERS", allIds.contains("act7_lawfare_battle"))
    }

    @Test
    fun testAllSpellsAtMaximumResonanceDebugPresetsResolveCorrectly() {
        val fullParty = StoryEncounters.createFullAmericaParty()
        val tiers = listOf(
            com.voicerpg.engine.model.ResonanceTier.BASIC,
            com.voicerpg.engine.model.ResonanceTier.ADEPT,
            com.voicerpg.engine.model.ResonanceTier.MASTER,
            com.voicerpg.engine.model.ResonanceTier.MYTHIC,
            com.voicerpg.engine.model.ResonanceTier.TRANSCENDENTAL
        )

        for (hero in fullParty) {
            for (spell in hero.spells) {
                for (tier in tiers) {
                    val preset = com.voicerpg.engine.engine.SpellChantPresets.getPreset(spell, tier)
                    val parsed = IntentParser.parse(
                        utterance = preset.chantText,
                        availableSpells = hero.spells,
                        activeEnemies = emptyList(),
                        party = fullParty
                    )

                    assertFalse(
                        "Hero ${hero.name}'s spell '${spell.name}' at tier $tier was falsely marked as wrong character spell! Chanted: '${preset.chantText}'",
                        parsed.isWrongCharacterSpell
                    )
                    assertEquals(
                        "Hero ${hero.name}'s spell '${spell.name}' at tier $tier did not resolve to correct spell ID! Chanted: '${preset.chantText}'",
                        spell.id,
                        parsed.spell.id
                    )
                }
            }
        }
    }
}
