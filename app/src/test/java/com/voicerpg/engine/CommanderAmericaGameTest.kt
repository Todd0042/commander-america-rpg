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
            "act2_media_battle" to "act2_post_battle",
            "act3_summit_battle" to "act3_post_battle",
            "act4_hoax_battle" to "act4_post_battle",
            "act5_golem_battle" to "act5_post_battle",
            "act6_kamala_battle" to "act6_post_battle",
            "act7_lawfare_battle" to "act7_post_battle",
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
        assertEquals("scene_act2_oval", storyViewModel.state.value.currentScene.id)

        // Advance to hulk reaction and Act II transition
        storyViewModel.advanceDialogue()
        assertEquals("act1_post_hulk", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_intro", storyViewModel.state.value.currentNode.id)

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
