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
            "act1_track_a_barry_battle" to "act1_ch2_post_battle",
            "act1_ch2_debate_battle" to "act1_ch3_bus_post_battle",
            "act1_track_a_hill_rod_finale" to "act1_ch3_post_battle",
            "act1_track_b_ferns_battle" to "act1_tb_ch2_post_battle",
            "act1_track_b_spicy_sean_battle" to "act1_tb_ch4_intro",
            "act1_track_b_commander_finale" to "act1_tb_ch3_post_battle",
            "act2_ch1_inauguration_battle" to "act2_ch1_post_battle",
            "act2_track_a_sharpie_battle" to "act2_ch3_sharpie_post_battle",
            "act2_media_battle" to "act2_ch5_post_battle",
            "act2_track_b_spicer_salsa_battle" to "act2_tb_ch1_post_battle",
            "act2_track_b_kash_battle" to "act2_tb_ch4_post_battle",
            "act2_track_b_rudy_finale" to "act2_tb_ch5_post_battle",
            "act3_track_a_stairs_bike_battle" to "act3_ch1_post_battle",
            "act3_track_a_chandelier_battle" to "act3_ch3_post_battle",
            "act3_track_a_biden_ice_cream_finale" to "act3_ch5_post_battle",
            "act3_track_b_corn_pop_battle" to "act3_tb_ch1_post_battle",
            "act3_track_b_foghorn_battle" to "act3_tb_ch3_post_battle",
            "act3_track_b_mugshot_finale" to "act3_tb_ch5_post_battle",
            "act4_track_a_donut_battle" to "act4_ch1_post_battle",
            "act6_ch3_bus_battle" to "act4_ch3_post_battle",
            "act6_kamala_battle" to "act4_ch5_post_battle",
            "act4_track_b_venn_critics_battle" to "act4_tb_ch1_post_battle",
            "act4_track_b_vance_donut_battle" to "act4_tb_ch2_post_battle",
            "act4_track_b_concepts_debate_battle" to "act4_tb_ch4_post_battle",
            "act4_track_b_mcdonalds_finale" to "act4_tb_ch5_post_battle",
            "act5_track_a_cybertruck_sink_battle" to "act5_ch1_post_battle",
            "act5_track_a_doge_bureaucrats_battle" to "act5_ch2_post_battle",
            "act5_track_a_swagger_turkish_battle" to "act5_ch3_post_battle",
            "act5_track_a_grand_finale" to "act5_ch5_post_battle",
            "act5_track_b_landlord_lobby_battle" to "act5_tb_ch1_post_battle",
            "act5_track_b_turkish_auditor_battle" to "act5_tb_ch2_post_battle",
            "act5_track_b_musk_starjump_battle" to "act5_tb_ch3_post_battle",
            "act5_track_b_doge_squad_battle" to "act5_tb_ch4_post_battle",
            "act5_track_b_grand_finale" to "act5_bipartisan_curtain_call"
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

        // Chapter 1 choice: Swagger -> Hulk -> Psaki -> Karoline -> Hub
        val swaggerChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_swagger" }
        storyViewModel.selectChoice(swaggerChoice)
        assertEquals("act1_ch1_swagger", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_hulk_cheer", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_psaki_interrupts", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_karoline_note", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)

        // Hub inquiry: Deal -> Oprah -> Hub
        val dealChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_hub_deal" }
        storyViewModel.selectChoice(dealChoice)
        assertEquals("act1_ch1_lore_deal", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_lore_deal_oprah", storyViewModel.state.value.currentNode.id)
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

        // 2. Advance into Chapter 2 (Cleveland Debate & Barry Tan Suit)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_jeb_opens", storyViewModel.state.value.currentNode.id)

        val guacChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a2_guac" }
        storyViewModel.selectChoice(guacChoice)
        assertEquals("act1_ch2_roast_jeb", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_roast_marco", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_barry_enters", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_barry_kimmel", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_commander_tan_rebuttal", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_mumbai_vindication", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch2_barry_battle_trigger", storyViewModel.state.value.currentNode.id)

        storyViewModel.triggerEncounter("act1_track_a_barry_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act1_ch2_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_ch3_taco_bus", storyViewModel.state.value.currentScene.id)

        // 3. Verify Debug Warps for Act I Track A & Track B chapters
        storyViewModel.debugWarpToChapter("act1_ch1_intro")
        assertEquals("act1_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_ch1_escalator", storyViewModel.state.value.currentScene.id)

        storyViewModel.debugWarpToChapter("act1_ch2_intro")
        assertEquals("act1_ch2_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_ch2_debate", storyViewModel.state.value.currentScene.id)

        storyViewModel.debugWarpToChapter("act1_ch3_intro")
        assertEquals("act1_ch3_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_ch3_taco_bus", storyViewModel.state.value.currentScene.id)

        storyViewModel.debugWarpToChapter("act1_tb_ch1_intro")
        assertEquals("act1_tb_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act1_tb_ch1_server", storyViewModel.state.value.currentScene.id)
    }

    @Test
    fun testPelosiInsiderTradingLichInAct2Encounter() {
        val act2Encounter = StoryEncounters.ACT2_MEDIA_BATTLE
        assertEquals("act2_media_battle", act2Encounter.id)
        val pelosi = act2Encounter.enemies.firstOrNull { it.id == "pelosi" }
        assertNotNull("Pelosi must be an enemy in Act 2", pelosi)
        assertTrue("Pelosi title should reference Nancy Palooza / Sub-Zero Freezer",
            pelosi!!.name.contains("Nancy Palooza") || pelosi.subtitle.contains("Sub-Zero"))
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
        // Verify Act 2 Marco recruitment node exists
        assertNotNull(StoryScript.ALL_NODES["act2_marco_joins"])
        assertEquals("marco", StoryScript.ALL_NODES["act2_marco_joins"]!!.speaker.id)

        // Verify Act 4 Vance recruitment node exists
        assertNotNull(StoryScript.ALL_NODES["act4_ch1_vance_donuts"])
        assertEquals("vance", StoryScript.ALL_NODES["act4_ch1_vance_donuts"]!!.speaker.id)

        // Verify Act 4 Kash alias recruitment node exists
        assertNotNull(StoryScript.ALL_NODES["act4_kash_joins"])
        assertEquals("kash", StoryScript.ALL_NODES["act4_kash_joins"]!!.speaker.id)

        // Verify Act 5 Kennedy & Musk-X recruitment nodes exist
        assertNotNull(StoryScript.ALL_NODES["act5_kennedy_joins"])
        assertEquals("kennedy", StoryScript.ALL_NODES["act5_kennedy_joins"]!!.speaker.id)
        assertNotNull(StoryScript.ALL_NODES["act5_ch1_musk_joins"])
        assertEquals("elon", StoryScript.ALL_NODES["act5_ch1_musk_joins"]!!.speaker.id)
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
        // 1. Warp into Act II Chapter 1 (Bushes, Cinnamon Gum & The 11-Day Mooch)
        storyViewModel.debugWarpToChapter("act2_ch1_intro")
        assertEquals("act2_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act2_ch1_mall", storyViewModel.state.value.currentScene.id)

        storyViewModel.advanceDialogue()
        assertEquals("act2_ch1_spicer_podium", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch1_mooch_arrives", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act2_ch1_mall_hub", storyViewModel.state.value.currentNode.id)

        val battleChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a2c1_battle" }
        storyViewModel.selectChoice(battleChoice)
        assertEquals("act2_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act2_ch1_inauguration_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        storyViewModel.triggerEncounter("act2_ch1_inauguration_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act2_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act2_ch2_oval", storyViewModel.state.value.currentScene.id)

        // 2. Verify Debug Warps across Act II Track A & Track B
        val act2Targets = listOf(
            "act2_ch1_intro" to "scene_act2_ch1_mall",
            "act2_ch2_intro" to "scene_act2_ch2_oval",
            "act2_ch3_sharpie_intro" to "scene_act2_ch3_sharpie",
            "act2_ch4_scif_intro" to "scene_act2_ch4_scif",
            "act2_ch3_intro" to "scene_act2_ch3_rotunda",
            "act2_tb_ch1_intro" to "scene_act2_tb_ch1_bushes",
            "act2_tb_ch3_intro" to "scene_act2_tb_ch3_subzero",
            "act2_tb_ch5_intro" to "scene_act2_tb_ch5_four_seasons"
        )
        for ((nodeId, expectedSceneId) in act2Targets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
        }
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
        // 1. Warp into Act III Chapter 1 (Air Force One Stairs & Rehoboth Bike Path)
        storyViewModel.debugWarpToChapter("act3_ch1_intro")
        assertEquals("act3_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act3_ch1_steel_mill", storyViewModel.state.value.currentScene.id)

        storyViewModel.advanceDialogue()
        assertEquals("act3_ch1_stairs_bike_intro", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch1_kjp_wind_excuse", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch1_tarmac_hub", storyViewModel.state.value.currentNode.id)

        val battleChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a3c1_battle" }
        storyViewModel.selectChoice(battleChoice)
        assertEquals("act3_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act3_track_a_stairs_bike_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        storyViewModel.triggerEncounter("act3_track_a_stairs_bike_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act3_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act3_ch2_usmca", storyViewModel.state.value.currentScene.id)

        // 2. Debug warps for Act III Track A & Track B chapters
        val act3Targets = listOf(
            "act3_ch1_intro" to "scene_act3_ch1_steel_mill",
            "act3_ch2_intro" to "scene_act3_ch2_usmca",
            "act3_ch3_intro" to "scene_act3_ch3_af1",
            "act3_ch4_intro" to "scene_act3_ch4_summit",
            "act3_ch5_baskin_intro" to "scene_act3_ch5_baskin",
            "act3_tb_ch1_intro" to "scene_act3_tb_ch1_pool",
            "act3_tb_ch3_intro" to "scene_act3_tb_ch3_baskin",
            "act3_tb_ch5_intro" to "scene_act3_tb_ch5_mugshot"
        )
        for ((nodeId, expectedSceneId) in act3Targets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
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
        // 1. Warp into Act IV Chapter 1 (Holt's Sweet Shoppe & JD Pants)
        storyViewModel.debugWarpToChapter("act4_ch1_intro")
        assertEquals("act4_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act4_ch1_fisa", storyViewModel.state.value.currentScene.id)

        storyViewModel.advanceDialogue()
        assertEquals("act4_ch1_vance_donuts", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch1_donut_hub", storyViewModel.state.value.currentNode.id)

        val battleChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a4c1_battle" }
        storyViewModel.selectChoice(battleChoice)
        assertEquals("act4_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act4_track_a_donut_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        storyViewModel.triggerEncounter("act4_track_a_donut_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act4_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act4_ch2_binder", storyViewModel.state.value.currentScene.id)

        // 2. Debug warps for Act IV Track A & Track B chapters
        val act4Targets = listOf(
            "act4_ch1_intro" to "scene_act4_ch1_fisa",
            "act4_ch2_intro" to "scene_act4_ch2_binder",
            "act4_ch3_intro" to "scene_act4_ch3_court",
            "act4_ch4_intro" to "scene_act4_ch4_hearing",
            "act4_ch5_coconut_intro" to "scene_act4_ch5_coconut_finale",
            "act4_tb_ch1_intro" to "scene_act4_tb_ch1_coconut",
            "act4_tb_ch2_intro" to "scene_act4_tb_ch2_donuts",
            "act4_tb_ch4_intro" to "scene_act4_tb_ch4_debate",
            "act4_tb_ch5_intro" to "scene_act4_tb_ch5_mcdonalds"
        )
        for ((nodeId, expectedSceneId) in act4Targets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
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
        // 1. Warp into Act V Chapter 1 (Dark Gothic MAGA Star-Jump & Porcelain Sink)
        storyViewModel.debugWarpToChapter("act5_ch1_intro")
        assertEquals("act5_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act5_ch1_arena", storyViewModel.state.value.currentScene.id)

        storyViewModel.advanceDialogue()
        assertEquals("act5_ch1_musk_joins", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch1_musk_hub", storyViewModel.state.value.currentNode.id)

        val battleChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a5c1_battle" }
        storyViewModel.selectChoice(battleChoice)
        assertEquals("act5_ch1_battle_trigger", storyViewModel.state.value.currentNode.id)
        assertEquals("act5_track_a_cybertruck_sink_battle", storyViewModel.state.value.currentNode.triggerBattleEncounterId)

        storyViewModel.triggerEncounter("act5_track_a_cybertruck_sink_battle")
        storyViewModel.onCombatVictory()
        assertEquals("act5_ch1_post_battle", storyViewModel.state.value.currentNode.id)
        assertEquals("scene_act5_ch2_senate", storyViewModel.state.value.currentScene.id)

        // 2. Debug warps for all Act V Track A & Track B chapters
        val act5Targets = listOf(
            "act5_ch1_intro" to "scene_act5_ch1_arena",
            "act5_ch2_intro" to "scene_act5_ch2_senate",
            "act5_ch3_intro" to "scene_act5_ch3_terrace",
            "act5_ch4_intro" to "scene_act5_ch4_closets",
            "act5_ch5_finale_intro" to "scene_act5_ch5_finale",
            "act5_tb_ch1_intro" to "scene_act5_tb_ch1_chapati",
            "act5_tb_ch2_intro" to "scene_act5_tb_ch2_swagger",
            "act5_tb_ch3_intro" to "scene_act5_tb_ch3_cybertruck",
            "act5_tb_ch4_intro" to "scene_act5_tb_ch4_doge",
            "act5_tb_ch5_intro" to "scene_act5_tb_ch5_finale"
        )
        for ((nodeId, expectedSceneId) in act5Targets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
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
    fun testAct8FiveChaptersFullProgressionAndDebugWarps() {
        val act8FullTargets = listOf(
            "act8_ch1_intro" to "scene_act8_ch1_bunker",
            "act8_ch2_intro" to "scene_act8_ch2_teleprompter",
            "act8_ch3_intro" to "scene_act8_ch3_autopen",
            "act8_ch4_intro" to "scene_act8_ch4_citadel",
            "act8_ch5_intro" to "scene_act8_ch5_inauguration"
        )
        for ((nodeId, expectedSceneId) in act8FullTargets) {
            storyViewModel.debugWarpToChapter(nodeId)
            assertEquals("Node for $nodeId", nodeId, storyViewModel.state.value.currentNode.id)
            assertEquals("Scene for $nodeId", expectedSceneId, storyViewModel.state.value.currentScene.id)
        }
    }

    @Test
    fun testAct8EncountersAndPartyRoster() {
        // Ch1 Bunker Battle
        val bunkerBattle = StoryEncounters.ACT8_CH1_BUNKER_BATTLE
        assertEquals("act8_ch1_bunker_battle", bunkerBattle.id)
        assertEquals(5, bunkerBattle.initialParty?.size)
        val studioDirector = bunkerBattle.enemies.firstOrNull { it.id == "studio_director" }
        assertNotNull("Studio Director must be present", studioDirector)
        assertTrue("Studio Director is boss", studioDirector!!.isBoss)

        // Ch2 Teleprompter Battle
        val teleprompterBattle = StoryEncounters.ACT8_CH2_TELEPROMPTER_BATTLE
        assertEquals("act8_ch2_teleprompter_battle", teleprompterBattle.id)
        assertEquals(5, teleprompterBattle.initialParty?.size)
        val sorcerer = teleprompterBattle.enemies.firstOrNull { it.id == "teleprompter_sorcerer" }
        assertNotNull("Teleprompter Sorcerer must be present", sorcerer)
        assertTrue("Teleprompter Sorcerer is boss", sorcerer!!.isBoss)

        // Ch3 Auto-Pen Battle
        val autopenBattle = StoryEncounters.ACT8_CH3_AUTOPEN_BATTLE
        assertEquals("act8_ch3_autopen_battle", autopenBattle.id)
        assertEquals(5, autopenBattle.initialParty?.size)
        val autopenSovereign = autopenBattle.enemies.firstOrNull { it.id == "autopen_sovereign" }
        assertNotNull("Auto-Pen Sovereign must be present", autopenSovereign)
        assertTrue("Auto-Pen Sovereign is boss", autopenSovereign!!.isBoss)

        // Ch4 Biden Final Boss Battle
        val bidenBattle = StoryEncounters.ACT8_BIDEN_FINAL_BATTLE
        assertEquals("act8_biden_final_battle", bidenBattle.id)
        assertEquals(5, bidenBattle.initialParty?.size)
        val biden = bidenBattle.enemies.firstOrNull { it.id == "biden" }
        assertNotNull("Biden must be present", biden)

        // Verify in ALL_ENCOUNTERS
        val allIds = StoryEncounters.ALL_ENCOUNTERS.map { it.id }
        assertTrue("act8_ch1_bunker_battle must be in ALL_ENCOUNTERS", allIds.contains("act8_ch1_bunker_battle"))
        assertTrue("act8_ch2_teleprompter_battle must be in ALL_ENCOUNTERS", allIds.contains("act8_ch2_teleprompter_battle"))
        assertTrue("act8_ch3_autopen_battle must be in ALL_ENCOUNTERS", allIds.contains("act8_ch3_autopen_battle"))
        assertTrue("act8_biden_final_battle must be in ALL_ENCOUNTERS", allIds.contains("act8_biden_final_battle"))
    }

    @Test
    fun testPlayerCustomizationDefaultsToCommanderInChiefAndSignatureSpells() {
        val defaultCustom = com.voicerpg.engine.model.PlayerCustomization()
        assertEquals("Commander America", defaultCustom.name)
        assertEquals(com.voicerpg.engine.model.HeroClass.COMMANDER, defaultCustom.heroClass)
        assertEquals("Commander-in-Chief", defaultCustom.title)
        assertEquals(com.voicerpg.engine.model.AuraColor.SOLAR_GOLD, defaultCustom.auraColor)

        // Class spell library must contain all 8 signature spells
        val spells = com.voicerpg.engine.engine.ClassSpellLibrary.getSpellsForClass(defaultCustom.heroClass)
        assertEquals(8, spells.size)
        val expectedSpellIds = listOf(
            "ca_tremendous_strike",
            "ca_art_of_the_deal",
            "ca_obama_did_it_first",
            "ca_presidential_veto",
            "ca_executive_action",
            "ca_build_the_wall",
            "ca_rally_patriots",
            "ca_tired_of_winning"
        )
        for (spellId in expectedSpellIds) {
            assertTrue("Spells must contain $spellId", spells.any { it.id == spellId })
        }

        // SaveManager initial save verifies Commander-in-Chief loadout
        val initialSave = saveManager.createInitialSave(defaultCustom, slot = 1)
        assertEquals("scene_act1_ch1_escalator", initialSave.currentSceneId)
        assertEquals("act1_intro", initialSave.currentNodeId)
        val heroStats = initialSave.partyStats.first { it.id == "hero" }
        assertEquals("Commander America", heroStats.name)
        assertEquals("Commander-in-Chief", heroStats.loreClass)
        assertEquals(5000, heroStats.currentHp)
        assertEquals(5000, heroStats.maxHp)
        assertEquals(1776, heroStats.currentMp)
        assertEquals(1776, heroStats.maxMp)
        assertEquals(70, heroStats.speed)
        assertEquals(8, heroStats.spellIds.size)

        val summary = saveManager.getSlotSummary(1)
        assertNotNull(summary)
        assertEquals("Commander America", summary!!.heroName)
        assertEquals("Commander-in-Chief", summary.heroClassTitle)
    }

    @Test
    fun testCharacterCreationSaveAndLoadRoundtrip() {
        val custom = com.voicerpg.engine.model.PlayerCustomization(
            name = "The Boss",
            heroClass = com.voicerpg.engine.model.HeroClass.COMMANDER,
            title = "Master of the Deal",
            auraColor = com.voicerpg.engine.model.AuraColor.CRIMSON_PYRE
        )
        val initialSave = saveManager.createInitialSave(custom, slot = 2)
        assertEquals("The Boss", initialSave.player.name)
        assertEquals("Master of the Deal", initialSave.player.title)
        assertEquals(com.voicerpg.engine.model.AuraColor.CRIMSON_PYRE, initialSave.player.auraColor)

        val loaded = saveManager.load(2)
        assertNotNull(loaded)
        assertEquals("The Boss", loaded!!.player.name)
        assertEquals("Master of the Deal", loaded.player.title)
        assertEquals(com.voicerpg.engine.model.HeroClass.COMMANDER, loaded.player.heroClass)
        assertEquals(com.voicerpg.engine.model.AuraColor.CRIMSON_PYRE, loaded.player.auraColor)
    }

    @Test
    fun testCampaignTrackATrackBSelectionFlow() {
        // Track A Start
        val trackACustom = com.voicerpg.engine.model.PlayerCustomization(
            name = "Commander Track A",
            campaignTrack = com.voicerpg.engine.model.CampaignTrack.TRACK_A
        )
        val saveA = saveManager.createInitialSave(trackACustom, slot = 1)
        assertEquals("act1_intro", saveA.currentNodeId)
        assertEquals(StoryScript.SCENE_ACT1_ESCALATOR.id, saveA.currentSceneId)

        storyViewModel.startNewGame(trackACustom, slot = 1)
        assertEquals("act1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals(StoryScript.SCENE_ACT1_ESCALATOR.id, storyViewModel.state.value.currentScene.id)

        // Track B Start
        val trackBCustom = com.voicerpg.engine.model.PlayerCustomization(
            name = "Hill-Rod Track B",
            campaignTrack = com.voicerpg.engine.model.CampaignTrack.TRACK_B
        )
        val saveB = saveManager.createInitialSave(trackBCustom, slot = 2)
        assertEquals("act1_tb_ch1_intro", saveB.currentNodeId)
        assertEquals(StoryScript.SCENE_ACT1_TB_CH1_SERVER.id, saveB.currentSceneId)

        storyViewModel.startNewGame(trackBCustom, slot = 2)
        assertEquals("act1_tb_ch1_intro", storyViewModel.state.value.currentNode.id)
        assertEquals(StoryScript.SCENE_ACT1_TB_CH1_SERVER.id, storyViewModel.state.value.currentScene.id)
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

    @Test
    fun testAllStoryScenesReferenceValidCustomBackgroundAssets() {
        val forbiddenKeywords = listOf(
            "clockwork", "bastion", "final_summit", "obsidian", "mausoleum", "rotting", "shadowed", "bell_chamber"
        )
        val assetsDir = java.io.File("src/main/assets")

        for ((sceneId, scene) in StoryScript.ALL_SCENES) {
            assertTrue("Scene $sceneId backgroundAsset should not be empty", scene.backgroundAsset.isNotEmpty())
            assertTrue("Scene $sceneId backgroundAsset should be in story/ folder: ${scene.backgroundAsset}", scene.backgroundAsset.startsWith("story/"))
            
            for (forbidden in forbiddenKeywords) {
                assertFalse(
                    "Scene $sceneId uses legacy fantasy asset '${scene.backgroundAsset}' containing '$forbidden'!",
                    scene.backgroundAsset.contains(forbidden)
                )
            }

            val assetFile = java.io.File(assetsDir, scene.backgroundAsset)
            assertTrue("Asset file for scene $sceneId should exist on disk: ${assetFile.absolutePath}", assetFile.exists())
            assertTrue("Asset file for scene $sceneId should not be empty: ${assetFile.absolutePath}", assetFile.length() > 0)
        }
    }

    @Test
    fun testAllPatrioticMusicTracksExistAndConfigured() {
        val assetsDir = java.io.File("src/main/assets")
        val tracks = listOf(
            com.voicerpg.engine.audio.MusicManager.TRACK_TITLE,
            com.voicerpg.engine.audio.MusicManager.TRACK_COMBAT,
            com.voicerpg.engine.audio.MusicManager.TRACK_RALLY,
            com.voicerpg.engine.audio.MusicManager.TRACK_OVAL,
            com.voicerpg.engine.audio.MusicManager.TRACK_INTRIGUE,
            com.voicerpg.engine.audio.MusicManager.TRACK_INAUGURATION
        )

        for (trackPath in tracks) {
            val file = java.io.File(assetsDir, trackPath)
            assertTrue("Music track file should exist: ${file.absolutePath}", file.exists())
            assertTrue("Music track file should not be empty: ${file.absolutePath}", file.length() > 1000)
        }
    }

    @Test
    fun testNoLegacyFantasyPortraitsAreReferencedByDialogueSpeakers() {
        val forbiddenNames = listOf(
            "aethel", "cedric", "lyra", "zephyr", "malakor", "vaelor", "galahault",
            "nocturne", "ouros", "dryad_matron", "shadow_wisp"
        )
        val speakers = listOf(
            com.voicerpg.engine.model.DialogueSpeaker.COMMANDER_AMERICA,
            com.voicerpg.engine.model.DialogueSpeaker.MARCO,
            com.voicerpg.engine.model.DialogueSpeaker.VANCE,
            com.voicerpg.engine.model.DialogueSpeaker.KASH,
            com.voicerpg.engine.model.DialogueSpeaker.KENNEDY,
            com.voicerpg.engine.model.DialogueSpeaker.ELON,
            com.voicerpg.engine.model.DialogueSpeaker.NARRATOR_KAYLEIGH,
            com.voicerpg.engine.model.DialogueSpeaker.NARRATOR_KAROLINE,
            com.voicerpg.engine.model.DialogueSpeaker.NARRATOR_HULK,
            com.voicerpg.engine.model.DialogueSpeaker.PELOSI,
            com.voicerpg.engine.model.DialogueSpeaker.KAMALA,
            com.voicerpg.engine.model.DialogueSpeaker.BIDEN,
            com.voicerpg.engine.model.DialogueSpeaker.OBAMA,
            com.voicerpg.engine.model.DialogueSpeaker.ROCKET_MAN,
            com.voicerpg.engine.model.DialogueSpeaker.PARTY_BOSS,
            com.voicerpg.engine.model.DialogueSpeaker.ESTABLISHMENT_RIVAL
        )

        val assetsDir = java.io.File("src/main/assets")
        for (speaker in speakers) {
            val portrait = speaker.portraitAsset
            assertNotNull("Speaker ${speaker.id} portrait must not be null", portrait)
            for (forbidden in forbiddenNames) {
                assertFalse(
                    "Speaker ${speaker.id} references forbidden fantasy portrait '$portrait'",
                    portrait?.contains(forbidden) == true
                )
            }
            val portraitFile = java.io.File(assetsDir, portrait!!)
            assertTrue("Portrait file for ${speaker.id} should exist: ${portraitFile.absolutePath}", portraitFile.exists())
        }
    }

    @Test
    fun testHubChoiceCompletionAndLockoutAct1() {
        // 1. Jump directly to Act 1 Chapter 1 Hub
        val hubNode = StoryScript.ALL_NODES["act1_ch1_hub"]!!
        val jumpChoice = com.voicerpg.engine.model.DialogueChoice("test_jump", "jump", emptyList(), hubNode.id)
        storyViewModel.selectChoice(jumpChoice)
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)

        // All 3 lore choices must initially be uncompleted
        val initialChoices = storyViewModel.state.value.currentNode.choices
        val dealChoice = initialChoices.first { it.id == "a1_hub_deal" }
        val borderChoice = initialChoices.first { it.id == "a1_hub_border" }
        val swampChoice = initialChoices.first { it.id == "a1_hub_swamp" }
        val proceedChoice = initialChoices.first { it.id == "a1_hub_proceed" }

        assertFalse(storyViewModel.isChoiceCompleted(dealChoice))
        assertFalse(storyViewModel.isChoiceCompleted(borderChoice))
        assertFalse(storyViewModel.isChoiceCompleted(swampChoice))
        assertFalse(storyViewModel.isChoiceCompleted(proceedChoice))

        // 2. Select option 1 (Trade deals)
        storyViewModel.selectChoice(dealChoice)
        assertEquals("act1_ch1_lore_deal", storyViewModel.state.value.currentNode.id)
        assertTrue(storyViewModel.state.value.narrativeFlags["a1_lore_deal"] == true)

        // Advance through Oprah 1988 follow-up back to the hub
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_lore_deal_oprah", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)

        // Verify trade deals choice is now marked completed
        val hubAfterChoice1 = storyViewModel.state.value.currentNode
        val updatedDealChoice = hubAfterChoice1.choices.first { it.id == "a1_hub_deal" }
        assertTrue(storyViewModel.isChoiceCompleted(updatedDealChoice))
        assertTrue(updatedDealChoice.isCompleted(storyViewModel.state.value.narrativeFlags))

        // 3. Verify attempting to select the completed choice again via click is locked out
        storyViewModel.selectChoice(updatedDealChoice)
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id) // Must stay on hub

        // 4. Verify attempting to select the completed choice via voice is locked out
        storyViewModel.handleStoryVoiceInput("let's talk about foreign trade deals and jobs")
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id) // Must stay on hub

        // 5. Select option 2 (Southern Border)
        val updatedBorderChoice = hubAfterChoice1.choices.first { it.id == "a1_hub_border" }
        storyViewModel.selectChoice(updatedBorderChoice)
        assertEquals("act1_ch1_lore_wall", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_lore_wall_callback", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)

        val hubAfterChoice2 = storyViewModel.state.value.currentNode
        assertTrue(storyViewModel.isChoiceCompleted(hubAfterChoice2.choices.first { it.id == "a1_hub_deal" }))
        assertTrue(storyViewModel.isChoiceCompleted(hubAfterChoice2.choices.first { it.id == "a1_hub_border" }))
        assertFalse(storyViewModel.isChoiceCompleted(hubAfterChoice2.choices.first { it.id == "a1_hub_swamp" }))

        // 6. Select option 3 (Drain the Swamp)
        val updatedSwampChoice = hubAfterChoice2.choices.first { it.id == "a1_hub_swamp" }
        storyViewModel.selectChoice(updatedSwampChoice)
        assertEquals("act1_ch1_lore_swamp", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)

        // All 3 lore choices are now completed! Only proceedChoice remains eligible
        val hubAllDone = storyViewModel.state.value.currentNode
        assertTrue(storyViewModel.isChoiceCompleted(hubAllDone.choices.first { it.id == "a1_hub_deal" }))
        assertTrue(storyViewModel.isChoiceCompleted(hubAllDone.choices.first { it.id == "a1_hub_border" }))
        assertTrue(storyViewModel.isChoiceCompleted(hubAllDone.choices.first { it.id == "a1_hub_swamp" }))
        assertFalse(storyViewModel.isChoiceCompleted(hubAllDone.choices.first { it.id == "a1_hub_proceed" }))

        // When only progression choice remains, advanceDialogue() progresses the story!
        assertTrue(storyViewModel.canAdvanceDialogue())
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_lobby_ambush", storyViewModel.state.value.currentNode.id)
    }

    @Test
    fun testHubChoiceCompletionAcrossActs6Through8() {
        // Act 3 Kennedy Hub
        val a3Hub = StoryScript.ALL_NODES["act3_ch2_kennedy_hub"]!!
        val jumpA3 = com.voicerpg.engine.model.DialogueChoice("jump_a3", "jump", emptyList(), a3Hub.id)
        storyViewModel.selectChoice(jumpA3)
        assertEquals("act3_ch2_kennedy_hub", storyViewModel.state.value.currentNode.id)

        val oxfordChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a3c2_oxford" }
        assertFalse(storyViewModel.isChoiceCompleted(oxfordChoice))
        storyViewModel.selectChoice(oxfordChoice)
        assertEquals("act3_ch2_oxford_node", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act3_ch2_kennedy_hub", storyViewModel.state.value.currentNode.id)
        assertTrue(storyViewModel.isChoiceCompleted(storyViewModel.state.value.currentNode.choices.first { it.id == "a3c2_oxford" }))

        // Act 4 Donut Hub
        val a4Hub = StoryScript.ALL_NODES["act4_ch1_donut_hub"]!!
        val jumpA4 = com.voicerpg.engine.model.DialogueChoice("jump_a4", "jump", emptyList(), a4Hub.id)
        storyViewModel.selectChoice(jumpA4)
        assertEquals("act4_ch1_donut_hub", storyViewModel.state.value.currentNode.id)

        val swissChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a4c1_swiss" }
        assertFalse(storyViewModel.isChoiceCompleted(swissChoice))
        storyViewModel.selectChoice(swissChoice)
        assertEquals("act4_ch1_swiss_node", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act4_ch1_donut_hub", storyViewModel.state.value.currentNode.id)
        assertTrue(storyViewModel.isChoiceCompleted(storyViewModel.state.value.currentNode.choices.first { it.id == "a4c1_swiss" }))

        // Act 5 Musk Hub
        val a5Hub = StoryScript.ALL_NODES["act5_ch1_musk_hub"]!!
        val jumpA5 = com.voicerpg.engine.model.DialogueChoice("jump_a5", "jump", emptyList(), a5Hub.id)
        storyViewModel.selectChoice(jumpA5)
        assertEquals("act5_ch1_musk_hub", storyViewModel.state.value.currentNode.id)

        val cyberChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a5c1_cybertruck" }
        assertFalse(storyViewModel.isChoiceCompleted(cyberChoice))
        storyViewModel.selectChoice(cyberChoice)
        assertEquals("act5_ch1_cybertruck_node", storyViewModel.state.value.currentNode.id)
        storyViewModel.advanceDialogue()
        assertEquals("act5_ch1_musk_hub", storyViewModel.state.value.currentNode.id)
        assertTrue(storyViewModel.isChoiceCompleted(storyViewModel.state.value.currentNode.choices.first { it.id == "a5c1_cybertruck" }))
    }

    @Test
    fun testHubChoiceCompletionPersistenceAndNewGameReset() {
        // Start fresh game and go to Act 1 Hub
        storyViewModel.startNewGame(PlayerCustomization(name = "Commander"))
        val hubNode = StoryScript.ALL_NODES["act1_ch1_hub"]!!
        storyViewModel.selectChoice(com.voicerpg.engine.model.DialogueChoice("jump", "jump", emptyList(), hubNode.id))
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)

        // Complete deal choice
        val dealChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_hub_deal" }
        storyViewModel.selectChoice(dealChoice)
        storyViewModel.advanceDialogue()
        storyViewModel.advanceDialogue()
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)
        assertTrue(storyViewModel.isChoiceCompleted(storyViewModel.state.value.currentNode.choices.first { it.id == "a1_hub_deal" }))

        // Save progress to current slot
        val currentSlot = storyViewModel.state.value.currentSlot
        storyViewModel.persistCurrentState()

        // Reload the game from slot
        storyViewModel.continueGame(currentSlot)
        assertEquals("act1_ch1_hub", storyViewModel.state.value.currentNode.id)
        val loadedDeal = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_hub_deal" }
        assertTrue("Completed choice must remain completed after reloading save", storyViewModel.isChoiceCompleted(loadedDeal))

        // Reset with new game
        storyViewModel.startNewGame(PlayerCustomization(name = "Commander Fresh"))
        val freshHub = StoryScript.ALL_NODES["act1_ch1_hub"]!!
        storyViewModel.selectChoice(com.voicerpg.engine.model.DialogueChoice("jump_fresh", "jump", emptyList(), freshHub.id))
        val freshDeal = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_hub_deal" }
        assertFalse("Completed choice must be reset to uncompleted on new game", storyViewModel.isChoiceCompleted(freshDeal))
    }

    @Test
    fun testRealAudioGibberishQuoteTriggerAndSfxSafety() {
        val sfx = com.voicerpg.engine.audio.SfxManager(null)
        // Verify headless safety: playing quote without context does not throw
        sfx.playBidenGibberish()
        sfx.playTrumpQuote()
        sfx.playObamaQuote()
        sfx.playSpicerQuote()
        sfx.playHillaryQuote()
        sfx.playKamalaQuote()
        sfx.playPressBriefingQuote()
        sfx.playCharacterQuote("commander_america")
        sfx.playCharacterQuote("joe_naptime")
        sfx.playCharacterQuote("barry_obama")
        sfx.playCharacterQuote("spicy_sean")
        sfx.playCharacterQuote("hill_rod")
        sfx.playCharacterQuote("kamala_vice")
        sfx.playCharacterQuote("press_psaki")
        sfx.playAssetAudio("audio/quotes/biden/biden_truinanashabbadepressure.ogg")
        sfx.playAssetAudio("audio/quotes/biden/biden_badakathcare.ogg")
        sfx.playAssetAudio("audio/quotes/trump/trump_greatness_birthright.ogg")
        sfx.playAssetAudio("audio/quotes/trump/trump_easily_win.ogg")
        sfx.playAssetAudio("audio/quotes/obama/obama_dont_boo_vote.ogg")
        sfx.playAssetAudio("audio/quotes/spicer/spicer_period.ogg")
        sfx.playAssetAudio("audio/quotes/hillary/hillary_congratulated_trump.ogg")
        sfx.playAssetAudio("audio/quotes/kamala/kamala_ride_a_bus.ogg")
        sfx.playAssetAudio("audio/quotes/press/psaki_legislative_action.ogg")
        sfx.playAssetAudio("audio/quotes/press/kayleigh_heroes.ogg")

        // Verify Act 3 Track B Joe Naptime party spells include real-audio gibberish
        val joeParty = StoryEncounters.createAct3TrackBParty()
        val joe = joeParty.first { it.id == "joe_naptime" }
        val gibberishSpell = joe.spells.firstOrNull { it.id == "biden_gibberish_audio" }
        assertNotNull("Joe Naptime must possess real-audio gibberish spell", gibberishSpell)
        assertTrue(gibberishSpell!!.aliases.contains("badakathcare"))
        assertTrue(gibberishSpell.aliases.contains("truinanashabbadepressure"))

        // Verify IntentParser matches phonetic/alias chant
        val parsed = IntentParser.parse("Cast Badakathcare", joe.spells, emptyList(), joeParty)
        assertEquals("biden_gibberish_audio", parsed.spell.id)
    }
}
