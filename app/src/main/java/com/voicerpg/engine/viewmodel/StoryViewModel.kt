package com.voicerpg.engine.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voicerpg.engine.audio.CombatNarrator
import com.voicerpg.engine.audio.SpeechManager
import com.voicerpg.engine.engine.IntentParser
import com.voicerpg.engine.engine.SaveManager
import com.voicerpg.engine.engine.QuestRecap
import com.voicerpg.engine.engine.StoryChoiceMatcher
import com.voicerpg.engine.engine.StoryEncounters
import com.voicerpg.engine.engine.StoryRecapEngine
import com.voicerpg.engine.engine.StoryScript
import com.voicerpg.engine.model.DialogueChoice
import com.voicerpg.engine.model.DialogueLogEntry
import com.voicerpg.engine.model.DialogueNode
import com.voicerpg.engine.model.DialogueSpeaker
import com.voicerpg.engine.model.EncounterDefinition
import com.voicerpg.engine.model.GameSaveData
import com.voicerpg.engine.model.GameScreen
import com.voicerpg.engine.model.MetaCommand
import com.voicerpg.engine.model.AuraColor
import com.voicerpg.engine.model.CampaignTrack
import com.voicerpg.engine.model.PartyMember
import com.voicerpg.engine.model.PlayerCustomization
import com.voicerpg.engine.model.SaveSlotInfo
import com.voicerpg.engine.model.SaveSummary
import com.voicerpg.engine.model.SavedCharacterStats
import com.voicerpg.engine.model.StoryScene
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class StoryState(
    val currentScene: StoryScene = StoryScript.SCENE_ACT1_ESCALATOR,
    val currentNode: DialogueNode = StoryScript.ALL_NODES["act1_intro"]!!,

    val gameScreen: GameScreen = GameScreen.TITLE,
    val previousScreen: GameScreen? = null,
    val activeEncounter: EncounterDefinition? = null,
    val player: PlayerCustomization = PlayerCustomization(),
    val decisionsMade: List<String> = emptyList(),
    val narrativeFlags: Map<String, Boolean> = emptyMap(),
    val defeatedEncounters: List<String> = emptyList(),
    val achievements: List<String> = emptyList(),
    val partyStats: List<SavedCharacterStats> = emptyList(),
    val isNarratorSpeaking: Boolean = false,
    val isTypingComplete: Boolean = true,
    val hasExistingSave: Boolean = false,
    val saveSummary: SaveSummary? = null,
    val currentSlot: Int = 1,
    val saveSlots: List<SaveSlotInfo> = emptyList(),
    val dialogueHistory: List<DialogueLogEntry> = emptyList(),
    val isBacklogOpen: Boolean = false,
    val isFastForwarding: Boolean = false,
    val isBacklogRecapActive: Boolean = true,
    val isNewGameFlow: Boolean = false,
    val isPureStoryMode: Boolean = false,
    val isStoryAutoPlayPaused: Boolean = false,
    val isAutoPlayEnabled: Boolean = false,
    val dynamicMetrics: Map<String, Float> = emptyMap(),
    val visitedNodeIds: Set<String> = emptySet(),
    val activeHubNodeId: String? = null,
    val activeMiniGameConfig: com.voicerpg.engine.model.MiniGameConfig? = null,
    val isMiniGameTestMenuOpen: Boolean = false
)

class StoryViewModel(
    val speechManager: SpeechManager,
    val combatNarrator: CombatNarrator,
    val saveManager: SaveManager = SaveManager(),
    val musicManager: com.voicerpg.engine.audio.MusicManager? = null,
    private val scopeOverride: CoroutineScope? = null
) : ViewModel() {

    data class DebugChapterTarget(
        val label: String,
        val introNodeId: String,
        val chapterIndex: Int
    )

    companion object {
        val DEBUG_CHAPTER_TARGETS: List<DebugChapterTarget> = listOf(
            DebugChapterTarget("Act I (Track A) - Ch 1: Escalator", "act1_ch1_intro", 1),
            DebugChapterTarget("Act I (Track A) - Ch 2: Primary Debate", "act1_ch2_intro", 2),
            DebugChapterTarget("Act I (Track A) - Ch 3: Taco Bowls & Bus", "act1_ch3_intro", 3),
            DebugChapterTarget("Act I (Track A) - Ch 4: Election Finale", "act1_ch4_intro", 3),
            DebugChapterTarget("Act I (Track B) - Ch 1: Chappaqua Server", "act1_tb_ch1_intro", 1),
            DebugChapterTarget("Act I (Track B) - Ch 2: Two Ferns & MetroCard", "act1_tb_ch2_intro", 2),
            DebugChapterTarget("Act I (Track B) - Ch 3: Purse Hot Sauce", "act1_tb_ch3_intro", 3),
            DebugChapterTarget("Act I (Track B) - Ch 4: Javits Ceiling", "act1_tb_ch4_intro", 3),
            DebugChapterTarget("Act II (Track A) - Ch 1: Bushes & Mooch", "act2_ch1_intro", 4),
            DebugChapterTarget("Act II (Track A) - Ch 2: Covfefe & Diet Coke", "act2_ch2_intro", 5),
            DebugChapterTarget("Act II (Track A) - Ch 3: Sharpie Map & Space Force", "act2_ch3_sharpie_intro", 5),
            DebugChapterTarget("Act II (Track A) - Ch 4: Nunes Memo & Wizard Kash", "act2_ch4_scif_intro", 7),
            DebugChapterTarget("Act II (Track A) - Ch 5: Sub-Zero Freezers Finale", "act2_ch3_intro", 5),
            DebugChapterTarget("Act II (Track B) - Ch 1: Sideways Clap", "act2_tb_ch1_intro", 5),
            DebugChapterTarget("Act II (Track B) - Ch 2: Pre-Scored SOTU", "act2_tb_ch2_intro", 5),
            DebugChapterTarget("Act II (Track B) - Ch 3: \$24k Freezers", "act2_tb_ch3_intro", 5),
            DebugChapterTarget("Act II (Track B) - Ch 4: \$341k Nvidia Loss", "act2_tb_ch4_intro", 5),
            DebugChapterTarget("Act II (Track B) - Ch 5: Four Seasons Total Landscaping", "act2_tb_ch5_intro", 5),
            DebugChapterTarget("Act III (Track A) - Ch 1: AF1 Stairs & Bike", "act3_ch1_intro", 6),
            DebugChapterTarget("Act III (Track A) - Ch 2: Foghorn Kennedy", "act3_ch2_intro", 6),
            DebugChapterTarget("Act III (Track A) - Ch 3: Bathroom Chandelier", "act3_ch3_intro", 6),
            DebugChapterTarget("Act III (Track A) - Ch 4: 215-Lb Mugshot", "act3_ch4_intro", 6),
            DebugChapterTarget("Act III (Track A) - Ch 5: Baskin-Robbins Finale", "act3_ch5_baskin_intro", 6),
            DebugChapterTarget("Act III (Track B) - Ch 1: Corn Pop's Pool", "act3_tb_ch1_intro", 6),
            DebugChapterTarget("Act III (Track B) - Ch 2: End of Quote", "act3_tb_ch2_intro", 6),
            DebugChapterTarget("Act III (Track B) - Ch 3: Baskin-Robbins", "act3_tb_ch3_intro", 6),
            DebugChapterTarget("Act III (Track B) - Ch 4: Corvette Garage", "act3_tb_ch4_intro", 6),
            DebugChapterTarget("Act III (Track B) - Ch 5: Mugshot & Coconut Pass", "act3_tb_ch5_intro", 6),
            DebugChapterTarget("Act IV (Track A) - Ch 1: Whatever Makes Sense Donuts", "act4_ch1_intro", 7),
            DebugChapterTarget("Act IV (Track A) - Ch 2: Haven't Been to Europe", "act4_ch2_intro", 7),
            DebugChapterTarget("Act IV (Track A) - Ch 3: Venn Diagrams & Buses", "act4_ch3_intro", 7),
            DebugChapterTarget("Act IV (Track A) - Ch 4: McDonald's Fry Apron", "act4_ch4_intro", 7),
            DebugChapterTarget("Act IV (Track A) - Ch 5: Coconut Grove Finale", "act4_ch5_coconut_intro", 7),
            DebugChapterTarget("Act IV (Track B) - Ch 1: Coconut Tree Context", "act4_tb_ch1_intro", 7),
            DebugChapterTarget("Act IV (Track B) - Ch 2: JD's Donut Shop", "act4_tb_ch2_intro", 7),
            DebugChapterTarget("Act IV (Track B) - Ch 3: Passage of Time", "act4_tb_ch3_intro", 7),
            DebugChapterTarget("Act IV (Track B) - Ch 4: Concepts of a Plan", "act4_tb_ch4_intro", 7),
            DebugChapterTarget("Act IV (Track B) - Ch 5: Fry Apron & Chapati Pass", "act4_tb_ch5_intro", 7),
            DebugChapterTarget("Act V (Track A) - Ch 1: Dark Gothic MAGA Star-Jump", "act5_ch1_intro", 7),
            DebugChapterTarget("Act V (Track A) - Ch 2: Hulkster & DOGE", "act5_ch2_intro", 8),
            DebugChapterTarget("Act V (Track A) - Ch 3: Mayor Swagger's Upgrades", "act5_ch3_intro", 8),
            DebugChapterTarget("Act V (Track A) - Ch 4: Mr. Cardamom's Chapati Truck", "act5_ch4_intro", 8),
            DebugChapterTarget("Act V (Track A) - Ch 5: 2026 Grand Finale", "act5_ch5_finale_intro", 8),
            DebugChapterTarget("Act V (Track B) - Ch 1: Mr. Cardamom & Free Buses", "act5_tb_ch1_intro", 8),
            DebugChapterTarget("Act V (Track B) - Ch 2: Contraband Baby-Doll & Sea Bass", "act5_tb_ch2_intro", 8),
            DebugChapterTarget("Act V (Track B) - Ch 3: Shattered Cybertruck", "act5_tb_ch3_intro", 8),
            DebugChapterTarget("Act V (Track B) - Ch 4: DOGE Vanguard", "act5_tb_ch4_intro", 8),
            DebugChapterTarget("Act V (Track B) - Ch 5: Bipartisan Curtain Call", "act5_tb_ch5_intro", 8),
            DebugChapterTarget("Act VI - Ch 1: Border Czar Inspection", "act6_ch1_intro", 9),
            DebugChapterTarget("Act VI - Ch 2: Venn Diagram Fog", "act6_ch2_intro", 9),
            DebugChapterTarget("Act VI - Ch 3: Electric Bus Mire", "act6_ch3_intro", 9),
            DebugChapterTarget("Act VI - Ch 4: Coconut Tree Grove", "act6_ch4_intro", 9),
            DebugChapterTarget("Act VI - Ch 5: Unburdened Climax", "act6_ch5_intro", 9),
            DebugChapterTarget("Act VII - Ch 1: 91 Indictment Gauntlet", "act7_ch1_intro", 10),
            DebugChapterTarget("Act VII - Ch 2: Gag Order Guillotine", "act7_ch2_intro", 10),
            DebugChapterTarget("Act VII - Ch 3: Butler PA Fairgrounds", "act7_ch3_intro", 10),
            DebugChapterTarget("Act VII - Ch 4: Steel Ear Miracle", "act7_ch4_intro", 10),
            DebugChapterTarget("Act VII - Ch 5: Milwaukee Coronation", "act7_ch5_intro", 10),
            DebugChapterTarget("Act VIII - Ch 1: Basement Bunker", "act8_ch1_intro", 11),
            DebugChapterTarget("Act VIII - Ch 2: Teleprompter Jammer", "act8_ch2_intro", 11),
            DebugChapterTarget("Act VIII - Ch 3: Auto-Pen Phantoms", "act8_ch3_intro", 11),
            DebugChapterTarget("Act VIII - Ch 4: Malarkey Sovereign", "act8_ch4_intro", 11),
            DebugChapterTarget("Act VIII - Ch 5: Golden Inauguration", "act8_ch5_intro", 11),
            DebugChapterTarget("Act VIII: The Final Malarkey Citadel", "act8_intro", 11),
            // Legacy / Regression targets for existing test suite
            DebugChapterTarget("Prologue", "cottage_intro", 0),
            DebugChapterTarget("Chapter 1", "crossroads_intro", 1),
            DebugChapterTarget("Chapter 2", "camp_intro", 2),
            DebugChapterTarget("Chapter 3", "chapter3_intro", 3),
            DebugChapterTarget("Chapter 4", "chapter4_intro", 4),
            DebugChapterTarget("Chapter 5", "ch5_intro", 5),
            DebugChapterTarget("Chapter 6", "ch6_intro", 6),
            DebugChapterTarget("Chapter 7", "ch7_intro", 7),
            DebugChapterTarget("Chapter 8", "ch8_intro", 8),
            DebugChapterTarget("Chapter 9", "ch9_intro", 9),
            DebugChapterTarget("Chapter 10", "ch10_intro", 10),
            DebugChapterTarget("Chapter 11", "ch11_intro", 11),
            DebugChapterTarget("Chapter 12", "ch12_intro", 12),
            DebugChapterTarget("Chapter 13", "ch13_intro", 13),
            DebugChapterTarget("Chapter 14", "ch14_intro", 14),
            DebugChapterTarget("Chapter 15", "ch15_intro", 15),
            DebugChapterTarget("Chapter 16", "ch16_intro", 16)
        )
    }

    private val activeScope: CoroutineScope
        get() = scopeOverride ?: viewModelScope

    private val _state = MutableStateFlow(StoryState())
    val state: StateFlow<StoryState> = _state.asStateFlow()

    private var pendingAutoAdvanceJob: Job? = null

    fun cancelPendingAutoAdvance() {
        pendingAutoAdvanceJob?.cancel()
        pendingAutoAdvanceJob = null
    }

    fun isChoiceCompleted(choice: DialogueChoice): Boolean =
        choice.isCompleted(_state.value.narrativeFlags)

    private fun eligibleChoices(node: DialogueNode): List<DialogueChoice> =
        node.choices.filter { choice -> !isChoiceCompleted(choice) }

    private fun effectiveDialogueChoices(node: DialogueNode): List<DialogueChoice> {
        val eligible = eligibleChoices(node)
        return if (node.choices.isNotEmpty() && eligible.size <= 1) emptyList() else node.choices
    }

    fun canAdvanceDialogue(): Boolean {
        val node = _state.value.currentNode
        if (effectiveDialogueChoices(node).isNotEmpty()) return false
        if (node.triggerBattleEncounterId != null) return false
        if (node.choices.isNotEmpty()) return true
        if (node.nextNodeId != null) return StoryScript.ALL_NODES.containsKey(node.nextNodeId)
        if (node.id.startsWith("ch3_") || node.id.startsWith("ch4_") || node.id.startsWith("ch5_") ||
            node.id.startsWith("ch6_") || node.id.startsWith("ch7_") || node.id.startsWith("ch8_") ||
            node.id.startsWith("ch9_") || node.id.startsWith("ch10_") || node.id.startsWith("ch11_") ||
            node.id.startsWith("ch12_") || node.id.startsWith("ch13_") || node.id.startsWith("ch14_") ||
            node.id.startsWith("ch15_") || node.id.startsWith("ch16_") || node.id.startsWith("epilogue_") ||
            node.id == "ch4_act1_complete"
        ) {
            return false
        }
        return StoryScript.ALL_NODES.containsKey("camp_intro") || StoryScript.ALL_NODES.containsKey("crossroads_intro")
    }

    fun schedulePocketModeAutoAdvance(node: DialogueNode) {
        cancelPendingAutoAdvance()
        if (!combatNarrator.isEyesFreeMode.value || !canAdvanceDialogue()) return
        pendingAutoAdvanceJob = activeScope.launch {
            delay(1500)
            if (combatNarrator.isEyesFreeMode.value &&
                _state.value.currentNode.id == node.id &&
                _state.value.gameScreen == GameScreen.STORY_EXPLORATION &&
                canAdvanceDialogue()
            ) {
                advanceDialogue()
            }
        }
    }

    private var pendingStoryModeJob: Job? = null
    private var storyModeWatchdogJob: Job? = null

    fun cancelPendingStoryMode() {
        pendingStoryModeJob?.cancel()
        pendingStoryModeJob = null
        storyModeWatchdogJob?.cancel()
        storyModeWatchdogJob = null
    }

    fun isBossEncounter(encounterId: String): Boolean {
        val bossIds = setOf(
            "act2_media_battle",
            "act3_summit_battle",
            "act4_hoax_battle",
            "act5_golem_battle",
            "act6_kamala_battle",
            "act7_lawfare_battle",
            "act8_biden_final_battle",
            "cave_broodmother",
            "castle_horde",
            "swamp_behemoth",
            "ch7_mire_wyrm",
            "ch8_executioner_ambush",
            "ch9_galahault_trial",
            "ch10_broodmother_trial",
            "ch11_nocturne_trial",
            "ch12_warmaster_ouros",
            "ch13_commander_vaelor",
            "ch14_abyssal_leviathan",
            "ch15_trial_gold",
            "ch15_trial_grove",
            "ch15_trial_shadow",
            "ch15_archon_custodians",
            "ch16_mirror_gauntlet",
            "ch16_malakor_finale"
        )
        if (encounterId in bossIds) return true
        val enc = StoryEncounters.ALL_ENCOUNTERS.firstOrNull { it.id == encounterId }
        return enc?.enemies?.any { it.isBoss } == true
    }

    fun pickStoryModeChoice(node: DialogueNode): DialogueChoice? {
        val eligible = eligibleChoices(node)
        if (eligible.isEmpty()) return null

        // 1. Campfire / hub entry prompts: ALWAYS choose to sit/converse rather than skip/rush
        val campOrHubEntry = eligible.filter { choice ->
            choice.id.contains("sit", ignoreCase = true) ||
            choice.id.contains("converse", ignoreCase = true) ||
            choice.text.contains("Sit by the fire", ignoreCase = true) ||
            choice.text.contains("converse", ignoreCase = true) ||
            choice.text.contains("speak with", ignoreCase = true)
        }
        if (campOrHubEntry.isNotEmpty()) {
            return campOrHubEntry.first()
        }

        // 2. Uncompleted sub-story choices in hubs and camps (longest exploration path)
        val uncompleted = eligible.filter { choice ->
            !isChoiceCompleted(choice) &&
            !choice.id.contains("skip", ignoreCase = true) &&
            !choice.text.contains("Rest briefly", ignoreCase = true)
        }
        if (uncompleted.isNotEmpty()) {
            return uncompleted.random()
        }

        // 3. Filter out skip choices when other exploration options exist
        val nonSkipChoices = eligible.filter { choice ->
            !choice.id.contains("skip", ignoreCase = true) &&
            !choice.text.contains("Rest briefly", ignoreCase = true) &&
            !choice.text.contains("press onwards without delay", ignoreCase = true)
        }
        val pool = if (nonSkipChoices.isNotEmpty()) nonSkipChoices else eligible
        return pool.random()
    }

    fun setPureStoryMode(enabled: Boolean) {
        _state.value = _state.value.copy(
            isPureStoryMode = enabled,
            isStoryAutoPlayPaused = false
        )
        if (enabled) {
            combatNarrator.setNarrationEnabled(true)
            if (_state.value.gameScreen == GameScreen.STORY_EXPLORATION && !combatNarrator.isSpeaking.value) {
                scheduleStoryModeNextStep(_state.value.currentNode, delayMs = 600L)
            }
        } else {
            cancelPendingStoryMode()
        }
    }

    fun toggleAutoPlay(): Boolean {
        val willEnable = !_state.value.isPureStoryMode
        setPureStoryMode(willEnable)
        val msg = if (willEnable) "Auto play enabled." else "Auto play disabled."
        combatNarrator.speak(msg, force = true)
        return willEnable
    }

    fun toggleStoryAutoPlayPause(): Boolean {
        val newPaused = !_state.value.isStoryAutoPlayPaused
        _state.value = _state.value.copy(isStoryAutoPlayPaused = newPaused)
        if (newPaused) {
            cancelPendingStoryMode()
            combatNarrator.speak("Story mode paused.", force = true)
        } else {
            combatNarrator.speak("Story mode resumed.", force = true)
            if (_state.value.gameScreen == GameScreen.STORY_EXPLORATION && !combatNarrator.isSpeaking.value) {
                scheduleStoryModeNextStep(_state.value.currentNode, delayMs = 600L)
            }
        }
        return newPaused
    }

    fun startPureStoryMode(fresh: Boolean = false, track: CampaignTrack = CampaignTrack.TRACK_A) {
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()
        speechManager.cancel()
        speechManager.stopListening()
        combatNarrator.stop()
        combatNarrator.setNarrationEnabled(true)

        val storySlot = SaveManager.STORY_MODE_SLOT
        val hasStorySave = saveManager.hasSave(storySlot)
        val existingStorySave = if (hasStorySave) saveManager.load(storySlot) else null
        val isCompleted = existingStorySave?.narrativeFlags?.get("game_completed") == true ||
                existingStorySave?.currentNodeId == "epilogue_credits"

        _state.value = _state.value.copy(
            isPureStoryMode = true,
            isStoryAutoPlayPaused = false
        )

        if (!fresh && hasStorySave && existingStorySave != null && !isCompleted) {
            continueGame(storySlot)
        } else {
            saveManager.deleteSave(storySlot)
            val custom = if (track == CampaignTrack.TRACK_B) {
                PlayerCustomization(
                    name = "Hill-Rod",
                    title = "Standard-Bearer & Server Specialist",
                    auraColor = AuraColor.SAPPHIRE_FROST,
                    campaignTrack = CampaignTrack.TRACK_B
                )
            } else {
                PlayerCustomization(
                    name = "Commander America",
                    title = "Commander-in-Chief",
                    auraColor = AuraColor.SOLAR_GOLD,
                    campaignTrack = CampaignTrack.TRACK_A
                )
            }
            startNewGame(custom, storySlot)
        }

        _state.value = _state.value.copy(
            isPureStoryMode = true,
            isStoryAutoPlayPaused = false
        )
    }

    fun scheduleStoryModeNextStep(node: DialogueNode, delayMs: Long = 1200L) {
        cancelPendingStoryMode()
        if (!_state.value.isPureStoryMode || _state.value.isStoryAutoPlayPaused) return
        if (_state.value.gameScreen != GameScreen.STORY_EXPLORATION) return

        pendingStoryModeJob = activeScope.launch {
            // Wait for active narration to complete before advancing, with a safety timeout
            val startWait = System.currentTimeMillis()
            while (combatNarrator.isSpeaking.value && (System.currentTimeMillis() - startWait) < 30000L) {
                delay(200L)
            }
            delay(delayMs)
            if (!_state.value.isPureStoryMode || _state.value.isStoryAutoPlayPaused) return@launch
            if (_state.value.currentNode.id != node.id || _state.value.gameScreen != GameScreen.STORY_EXPLORATION) return@launch

            val current = _state.value.currentNode
            if (current.triggerBattleEncounterId != null) {
                triggerEncounter(current.triggerBattleEncounterId)
                return@launch
            }
            if (current.triggerMiniGameId != null) {
                triggerMiniGame(current.triggerMiniGameId)
                return@launch
            }

            val effective = effectiveDialogueChoices(current)
            if (effective.isNotEmpty()) {
                val chosen = pickStoryModeChoice(current)
                if (chosen != null) {
                    selectChoice(chosen)
                }
            } else {
                advanceDialogueInternal(suppressNarration = false)
            }
        }
    }

    init {
        // Observe Screenless Pocket Mode toggles: cancel or start auto-advance reactively
        activeScope.launch {
            combatNarrator.isEyesFreeMode.collect { isEyesFree ->
                if (!isEyesFree) {
                    cancelPendingAutoAdvance()
                } else if (_state.value.gameScreen == GameScreen.STORY_EXPLORATION && canAdvanceDialogue() && !combatNarrator.isSpeaking.value) {
                    schedulePocketModeAutoAdvance(_state.value.currentNode)
                }
            }
        }

        // Load persistent game save on boot with multi-slot support
        val allSlots = saveManager.getAllSlotInfos()
        val initialSlot = allSlots
            .filter { !it.isEmpty }
            .maxByOrNull { it.summary?.timestamp ?: 0L }
            ?.slotIndex ?: SaveManager.DEFAULT_SLOT
        saveManager.currentSlot = initialSlot
        val existingSave = saveManager.load(initialSlot)
        if (existingSave != null) {
            val sanitizedPlayer = if (existingSave.player.name == "Aethel" || existingSave.player.name.isBlank()) {
                PlayerCustomization()
            } else {
                existingSave.player
            }
            val sanitizedPartyStats = existingSave.partyStats.map { stat ->
                if (stat.id == "hero" || stat.name == "Aethel") {
                    stat.copy(
                        name = "Commander America",
                        loreClass = "Commander-in-Chief",
                        maxHp = maxOf(stat.maxHp, 5000),
                        currentHp = maxOf(stat.currentHp, 5000),
                        maxMp = maxOf(stat.maxMp, 1776),
                        currentMp = maxOf(stat.currentMp, 1776),
                        spellIds = listOf("ca_tremendous_strike", "ca_art_of_the_deal", "ca_obama_did_it_first", "ca_build_the_wall", "ca_rally_patriots")
                    )
                } else stat
            }

            val restoredScene = StoryScript.ALL_SCENES[existingSave.currentSceneId] ?: StoryScript.SCENE_ACT1_ESCALATOR
            val rawRestoredNode = StoryScript.ALL_NODES[existingSave.currentNodeId] ?: StoryScript.ALL_NODES["act1_intro"]!!

            val restoredNode = resolveEffectiveHubNode(rawRestoredNode, existingSave.narrativeFlags)

            val summary = saveManager.getSlotSummary(initialSlot)

            val restoredHistory = if (existingSave.recentDialogueLog.isNotEmpty()) {
                existingSave.recentDialogueLog
            } else {
                listOf(
                    DialogueLogEntry(
                        speakerId = restoredNode.speaker.id,
                        speakerName = restoredNode.speaker.name,
                        speakerTitle = restoredNode.speaker.title,
                        text = restoredNode.text,
                        sceneName = restoredScene.name,
                        isNarrator = restoredNode.speaker.id.startsWith("narrator") || restoredNode.speaker == DialogueSpeaker.NARRATOR
                    )
                )
            }

            _state.value = StoryState(
                currentScene = restoredScene,
                currentNode = restoredNode,
                gameScreen = GameScreen.TITLE,
                player = sanitizedPlayer,
                decisionsMade = existingSave.decisionsMade,
                narrativeFlags = existingSave.narrativeFlags,
                defeatedEncounters = existingSave.defeatedEncounters,
                achievements = existingSave.achievements,
                partyStats = sanitizedPartyStats,
                hasExistingSave = true,
                saveSummary = summary,
                currentSlot = initialSlot,
                saveSlots = allSlots,
                dialogueHistory = restoredHistory,
                isAutoPlayEnabled = existingSave.isAutoPlayEnabled,
                dynamicMetrics = existingSave.dynamicMetrics
            )
            combatNarrator.setEyesFreeMode(existingSave.isEyesFreeMode, lockGuard = false)
            combatNarrator.setPocketGuardEnabled(existingSave.isPocketGuardEnabled)
            combatNarrator.setCombatNarrationEnabled(existingSave.isCombatNarrationEnabled)
            combatNarrator.setNarrationEnabled(existingSave.isNarrationEnabled)
            combatNarrator.setReadChoicesEnabled(existingSave.isReadChoicesEnabled)
            combatNarrator.setSpeechRate(existingSave.speechRate)
            combatNarrator.setCharacterPitchEnabled(existingSave.isCharacterPitchEnabled)
            combatNarrator.setSpeakerAttributionEnabled(existingSave.isSpeakerAttributionEnabled)
            combatNarrator.setVoiceAssignments(existingSave.voiceAssignments)
            speechManager.setAutoListen(existingSave.isAutoListen)
            speechManager.setChimeMuted(existingSave.isChimeMuted)
            musicManager?.setMusicEnabled(existingSave.isMusicEnabled)
            musicManager?.setVolume(existingSave.musicVolume)
            musicManager?.playTrack(com.voicerpg.engine.audio.MusicManager.TRACK_RALLY)
        } else {
            // First time player: start at Title Screen
            musicManager?.playTrack(com.voicerpg.engine.audio.MusicManager.TRACK_TITLE)
            _state.value = StoryState(
                gameScreen = GameScreen.TITLE,
                hasExistingSave = false,
                saveSummary = null,
                currentSlot = initialSlot,
                saveSlots = allSlots
            )
        }
    }

    /**
     * Resumes a game save from the Title Screen. Defaults to the active slot.
     */
    fun continueGame(slot: Int? = null, startInAutoPlay: Boolean = false) {
        val targetSlot = if (slot == SaveManager.STORY_MODE_SLOT) SaveManager.STORY_MODE_SLOT else (slot ?: _state.value.currentSlot).coerceIn(1, SaveManager.MAX_SLOTS)
        saveManager.currentSlot = targetSlot
        val existingSave = saveManager.load(targetSlot) ?: return
        if (startInAutoPlay && !existingSave.isAutoPlayEnabled) {
            saveManager.save(existingSave.copy(isAutoPlayEnabled = true), targetSlot)
        }
        val sanitizedPlayer = if (existingSave.player.name == "Aethel" || existingSave.player.name.isBlank()) {
            PlayerCustomization()
        } else {
            existingSave.player
        }
        val sanitizedPartyStats = existingSave.partyStats.map { stat ->
            if (stat.id == "hero" || stat.name == "Aethel") {
                stat.copy(
                    name = "Commander America",
                    loreClass = "Commander-in-Chief",
                    maxHp = maxOf(stat.maxHp, 5000),
                    currentHp = maxOf(stat.currentHp, 5000),
                    maxMp = maxOf(stat.maxMp, 1776),
                    currentMp = maxOf(stat.currentMp, 1776),
                    spellIds = listOf("ca_tremendous_strike", "ca_art_of_the_deal", "ca_obama_did_it_first", "ca_build_the_wall", "ca_rally_patriots")
                )
            } else stat
        }

        val restoredScene = StoryScript.ALL_SCENES[existingSave.currentSceneId] ?: StoryScript.SCENE_ACT1_ESCALATOR
        val rawRestoredNode = StoryScript.ALL_NODES[existingSave.currentNodeId] ?: StoryScript.ALL_NODES["act1_intro"]!!
        val restoredNode = resolveEffectiveHubNode(rawRestoredNode, existingSave.narrativeFlags)

        val restoredHistory = if (existingSave.recentDialogueLog.isNotEmpty()) {
            existingSave.recentDialogueLog
        } else if (_state.value.dialogueHistory.isNotEmpty()) {
            _state.value.dialogueHistory
        } else {
            listOf(
                DialogueLogEntry(
                    speakerId = restoredNode.speaker.id,
                    speakerName = restoredNode.speaker.name,
                    speakerTitle = restoredNode.speaker.title,
                    text = restoredNode.text,
                    sceneName = restoredScene.name,
                    isNarrator = restoredNode.speaker.id.startsWith("narrator") || restoredNode.speaker == DialogueSpeaker.NARRATOR
                )
            )
        }

        val isStoryMode = startInAutoPlay || existingSave.isAutoPlayEnabled || (targetSlot == SaveManager.STORY_MODE_SLOT) || _state.value.isPureStoryMode
        _state.value = _state.value.copy(
            currentScene = restoredScene,
            currentNode = restoredNode,
            gameScreen = GameScreen.STORY_EXPLORATION,
            previousScreen = null,
            player = sanitizedPlayer,
            decisionsMade = existingSave.decisionsMade,
            narrativeFlags = existingSave.narrativeFlags,
            defeatedEncounters = existingSave.defeatedEncounters,
            achievements = existingSave.achievements,
            partyStats = sanitizedPartyStats,
            currentSlot = targetSlot,
            hasExistingSave = true,
            saveSummary = saveManager.getSlotSummary(targetSlot),
            saveSlots = saveManager.getAllSlotInfos(),
            dialogueHistory = restoredHistory,
            isPureStoryMode = isStoryMode,
            isStoryAutoPlayPaused = false,
            isAutoPlayEnabled = isStoryMode,
            dynamicMetrics = existingSave.dynamicMetrics
        )
        combatNarrator.setEyesFreeMode(existingSave.isEyesFreeMode, lockGuard = false)
        combatNarrator.setPocketGuardEnabled(existingSave.isPocketGuardEnabled)
        combatNarrator.setCombatNarrationEnabled(existingSave.isCombatNarrationEnabled)
        combatNarrator.setNarrationEnabled(existingSave.isNarrationEnabled)
        combatNarrator.setReadChoicesEnabled(existingSave.isReadChoicesEnabled)
        combatNarrator.setSpeechRate(existingSave.speechRate)
        combatNarrator.setCharacterPitchEnabled(existingSave.isCharacterPitchEnabled)
        combatNarrator.setSpeakerAttributionEnabled(existingSave.isSpeakerAttributionEnabled)
        combatNarrator.setVoiceAssignments(existingSave.voiceAssignments)
        speechManager.setAutoListen(existingSave.isAutoListen)
        speechManager.setChimeMuted(existingSave.isChimeMuted)
        musicManager?.setMusicEnabled(existingSave.isMusicEnabled)
        musicManager?.setVolume(existingSave.musicVolume)
        musicManager?.playTrack(restoredScene.musicAsset)
        narrateCurrentNode()
    }

    /**
     * Selects an active save slot and refreshes the preview summary.
     */
    fun selectSlot(slot: Int) {
        val validSlot = slot.coerceIn(1, SaveManager.MAX_SLOTS)
        saveManager.currentSlot = validSlot
        val summary = saveManager.getSlotSummary(validSlot)
        _state.value = _state.value.copy(
            currentSlot = validSlot,
            hasExistingSave = summary != null,
            saveSummary = summary,
            saveSlots = saveManager.getAllSlotInfos()
        )
    }

    /**
     * Deletes the specified save slot and updates slot listings.
     */
    fun deleteSlot(slot: Int) {
        val validSlot = slot.coerceIn(1, SaveManager.MAX_SLOTS)
        saveManager.deleteSave(validSlot)
        val allSlots = saveManager.getAllSlotInfos()
        val activeSlot = _state.value.currentSlot
        val summary = saveManager.getSlotSummary(activeSlot)
        _state.value = _state.value.copy(
            saveSlots = allSlots,
            hasExistingSave = summary != null,
            saveSummary = summary
        )
    }

    /**
     * Refreshes save slots metadata from disk.
     */
    fun refreshSaveSlots() {
        val allSlots = saveManager.getAllSlotInfos()
        val activeSlot = _state.value.currentSlot
        val summary = saveManager.getSlotSummary(activeSlot)
        _state.value = _state.value.copy(
            saveSlots = allSlots,
            hasExistingSave = summary != null,
            saveSummary = summary
        )
    }

    /**
     * Starts the new game wizard flow from the Title Screen, optionally targeting a slot.
     */
    fun startNewGameFlow(slot: Int? = null) {
        if (slot != null) {
            selectSlot(slot)
        }
        cancelPendingAutoAdvance()
        combatNarrator.stop()
        speechManager.cancel()
        _state.value = _state.value.copy(
            gameScreen = GameScreen.CHARACTER_CREATION,
            previousScreen = GameScreen.TITLE,
            isNewGameFlow = true
        )
    }

    /**
     * Saves current campaign progress and returns to the Title Screen.
     */
    fun returnToTitle() {
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()
        combatNarrator.stop()
        speechManager.cancel()
        persistCurrentState()

        // If returning from Story Mode, restore active slot to latest campaign slot (1..3)
        val allSlots = saveManager.getAllSlotInfos()
        val latestCampaignSlot = allSlots
            .filter { !it.isEmpty }
            .maxByOrNull { it.summary?.timestamp ?: 0L }
            ?.slotIndex ?: SaveManager.DEFAULT_SLOT

        val activeSlot = if (_state.value.currentSlot == SaveManager.STORY_MODE_SLOT) {
            latestCampaignSlot
        } else {
            _state.value.currentSlot
        }
        saveManager.currentSlot = activeSlot
        val summary = saveManager.getSlotSummary(activeSlot)
        _state.value = _state.value.copy(
            gameScreen = GameScreen.TITLE,
            previousScreen = null,
            hasExistingSave = summary != null,
            saveSummary = summary,
            currentSlot = activeSlot,
            saveSlots = allSlots,
            isPureStoryMode = false,
            isStoryAutoPlayPaused = false
        )
        musicManager?.playTrack(com.voicerpg.engine.audio.MusicManager.TRACK_TITLE)
    }

    fun getStoryModeSaveSummary(): SaveSummary? = saveManager.getSlotSummary(SaveManager.STORY_MODE_SLOT)

    /**
     * Proceeds from initial Audio Setup to Character Creation.
     */
    fun proceedToCharacterCreation() {
        _state.value = _state.value.copy(
            gameScreen = GameScreen.CHARACTER_CREATION,
            previousScreen = GameScreen.AUDIO_SETUP
        )
    }

    /**
     * Initializes a fresh game from Character Creation.
     */
    fun startNewGame(customization: PlayerCustomization, slot: Int? = null) {
        cancelPendingAutoAdvance()
        val targetSlot = if (slot == SaveManager.STORY_MODE_SLOT) SaveManager.STORY_MODE_SLOT else (slot ?: _state.value.currentSlot).coerceIn(1, SaveManager.MAX_SLOTS)
        saveManager.currentSlot = targetSlot
        val initialSave = saveManager.createInitialSave(customization, targetSlot)
        val initialNode = StoryScript.ALL_NODES[initialSave.currentNodeId] ?: StoryScript.ALL_NODES["act1_intro"]!!
        val initialScene = StoryScript.ALL_SCENES[initialSave.currentSceneId] ?: StoryScript.SCENE_ACT1_ESCALATOR

        val initialEntry = DialogueLogEntry(
            speakerId = initialNode.speaker.id,
            speakerName = initialNode.speaker.name,
            speakerTitle = initialNode.speaker.title,
            text = initialNode.text,
            sceneName = initialScene.name,
            isNarrator = initialNode.speaker.id.startsWith("narrator") || initialNode.speaker == DialogueSpeaker.NARRATOR
        )
        val isStoryMode = targetSlot == SaveManager.STORY_MODE_SLOT || _state.value.isPureStoryMode
        _state.value = StoryState(
            currentScene = initialScene,
            currentNode = initialNode,
            gameScreen = GameScreen.STORY_EXPLORATION,
            player = customization,
            decisionsMade = emptyList(),
            narrativeFlags = emptyMap(),
            defeatedEncounters = emptyList(),
            achievements = initialSave.achievements,
            partyStats = initialSave.partyStats,
            hasExistingSave = true,
            saveSummary = saveManager.getSlotSummary(targetSlot),
            currentSlot = targetSlot,
            saveSlots = saveManager.getAllSlotInfos(),
            dialogueHistory = listOf(initialEntry),
            isPureStoryMode = isStoryMode,
            isStoryAutoPlayPaused = false
        )
        musicManager?.playTrack(StoryScript.SCENE_COTTAGE.musicAsset)
        persistCurrentState()
        narrateCurrentNode()
    }

    fun resetGame(slot: Int? = null) {
        cancelPendingAutoAdvance()
        val targetSlot = if (slot == SaveManager.STORY_MODE_SLOT) SaveManager.STORY_MODE_SLOT else (slot ?: _state.value.currentSlot).coerceIn(1, SaveManager.MAX_SLOTS)
        saveManager.deleteSave(targetSlot)
        val allSlots = saveManager.getAllSlotInfos()
        val summary = saveManager.getSlotSummary(targetSlot)
        _state.value = _state.value.copy(
            gameScreen = GameScreen.TITLE,
            hasExistingSave = summary != null,
            saveSummary = summary,
            saveSlots = allSlots
        )
    }

    /**
     * Debug-only helper: instantly jumps to a chapter's intro node, seeding the
     * companion roster and master spells valid as of that chapter. Only exposed
     * through the debug-build Options dialog (see BuildConfig.DEBUG_WARP_MENU).
     */
    fun debugWarpToChapter(introNodeId: String) {
        val target = DEBUG_CHAPTER_TARGETS.firstOrNull { it.introNodeId == introNodeId } ?: return
        val introNode = StoryScript.ALL_NODES[introNodeId] ?: return
        cancelPendingAutoAdvance()
        combatNarrator.stop()
        speechManager.cancel()

        val isActWarp = target.introNodeId.startsWith("act")
        val seededFlags = buildMap {
            if (!isActWarp) {
                if (target.chapterIndex >= 2) put("cedric_recruited", true)
                if (target.chapterIndex >= 6) put("lyra_recruited", true)
                if (target.chapterIndex >= 9) put("zephyr_recruited", true)
                if (target.chapterIndex >= 10) put("cedric_trial_complete", true)
                if (target.chapterIndex >= 11) put("lyra_trial_complete", true)
                if (target.chapterIndex >= 12) put("zephyr_trial_complete", true)
            } else {
                // Act warps: Act I chapters (1,2,3) and Act II Ch1 (4) are solo Commander America
                // Marco joins in Act II Ch2 (index 5), Vance in Act III (index 6), etc.
                if (target.chapterIndex >= 5) put("marco_recruited", true)
                if (target.chapterIndex >= 6) put("vance_recruited", true)
                if (target.chapterIndex >= 7) put("kash_recruited", true)
                if (target.chapterIndex >= 8) put("kennedy_recruited", true)
            }
        }
        // Overwrite, not union, the roster and trial flags so a downward warp
        // cannot drag companions from later chapters back into the story.
        val rosterAndTrialFlags = setOf(
            "cedric_recruited", "lyra_recruited", "zephyr_recruited",
            "cedric_trial_complete", "lyra_trial_complete", "zephyr_trial_complete",
            "marco_recruited", "vance_recruited", "kash_recruited", "kennedy_recruited"
        )
        val clearedFlags = _state.value.narrativeFlags.filterKeys { it !in rosterAndTrialFlags }

        // Trim any companions who have not joined the fellowship as of this chapter.
        val allowedRosterIds = buildList {
            add("hero")
            if (!isActWarp) {
                if (target.chapterIndex >= 2) add("cedric")
                if (target.chapterIndex >= 6) add("lyra")
                if (target.chapterIndex >= 9) add("zephyr")
            } else {
                if (target.chapterIndex >= 5) add("marco")
                if (target.chapterIndex >= 6) add("vance")
                if (target.chapterIndex >= 7) add("kash")
                if (target.chapterIndex >= 8) add("kennedy")
            }
        }
        val baseParty = if (_state.value.partyStats.any { it.id == "hero" }) {
            _state.value.partyStats
        } else {
            listOf(
                SavedCharacterStats(
                    id = "hero",
                    name = "Commander America",
                    loreClass = "Commander-in-Chief",
                    currentHp = 5000,
                    maxHp = 5000,
                    currentMp = 1776,
                    maxMp = 1776,
                    speed = 70,
                    level = 1,
                    xp = 0,
                    spellIds = listOf("ca_tremendous_strike", "ca_art_of_the_deal", "ca_obama_did_it_first", "ca_presidential_veto", "ca_executive_action", "ca_build_the_wall", "ca_rally_the_patriots", "ca_tired_of_winning")
                )
            )
        }
        val trimmedParty = baseParty.filter { it.id in allowedRosterIds }

        val introScene = StoryScript.ALL_SCENES.values.firstOrNull { it.initialNodeId == introNodeId }
            ?: _state.value.currentScene

        _state.value = _state.value.copy(
            currentScene = introScene,
            currentNode = introNode,
            gameScreen = GameScreen.STORY_EXPLORATION,
            activeEncounter = null,
            narrativeFlags = clearedFlags + seededFlags,
            partyStats = trimmedParty
        )
        musicManager?.playTrack(introScene.musicAsset)
        applyNodeTransition(introNode)
    }

    fun advanceDialogue() {
        if (_state.value.isFastForwarding) {
            stopFastForward()
            return
        }
        advanceDialogueInternal(suppressNarration = false)
    }

    private fun advanceDialogueInternal(suppressNarration: Boolean = false) {
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()
        if (!suppressNarration) {
            combatNarrator.stop()
            speechManager.cancel()
        }
        val node = _state.value.currentNode
        if (effectiveDialogueChoices(node).isNotEmpty()) {
            return
        }

        if (node.choices.isNotEmpty()) {
            val sole = eligibleChoices(node).firstOrNull()
            if (sole != null) {
                val nextNode = StoryScript.ALL_NODES[sole.nextNodeId]
                if (nextNode != null) {
                    val updatedDecisions = _state.value.decisionsMade + sole.id
                    _state.value = _state.value.copy(decisionsMade = updatedDecisions)
                    applyNodeTransition(nextNode, suppressNarration = suppressNarration)
                }
            }
            return
        }

        if (node.triggerBattleEncounterId != null) {
            triggerEncounter(node.triggerBattleEncounterId)
            return
        }

        if (node.triggerMiniGameId != null) {
            triggerMiniGame(node.triggerMiniGameId)
            return
        }

        val nextId = node.nextNodeId
        if (nextId != null) {
            val nextNode = StoryScript.ALL_NODES[nextId]
            if (nextNode != null) {
                applyNodeTransition(nextNode, suppressNarration = suppressNarration)
            }
        } else if (node.choices.isEmpty()) {
            if (node.id == "epilogue_credits" || node.setFlagOnEnter == "game_completed") {
                resetGame()
                return
            }
            if (node.id.startsWith("ch3_") || node.id.startsWith("ch4_") || node.id.startsWith("ch5_") || node.id.startsWith("ch6_") || node.id.startsWith("ch7_") || node.id.startsWith("ch8_") || node.id.startsWith("ch9_") || node.id.startsWith("ch10_") || node.id.startsWith("ch11_") || node.id.startsWith("ch12_") || node.id.startsWith("ch13_") || node.id.startsWith("ch14_") || node.id.startsWith("ch15_") || node.id.startsWith("ch16_") || node.id.startsWith("epilogue_") || node.id == "ch4_act1_complete") {
                return
            }
            val fallbackNode = StoryScript.ALL_NODES["camp_intro"] ?: StoryScript.ALL_NODES["crossroads_intro"]
            if (fallbackNode != null) {
                applyNodeTransition(fallbackNode, suppressNarration = suppressNarration)
            }
        }
    }

    fun selectChoice(choice: DialogueChoice) {
        if (_state.value.isFastForwarding) {
            stopFastForward()
        }
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()
        if (isChoiceCompleted(choice)) {
            combatNarrator.speak("That objective has already been completed. Please select a remaining task.", force = true)
            return
        }

        combatNarrator.stop()
        speechManager.cancel()
        val nextNode = StoryScript.ALL_NODES[choice.nextNodeId]
        if (nextNode != null) {
            val updatedFlags = if (choice.completionFlag != null) {
                _state.value.narrativeFlags + (choice.completionFlag to true) + ("choice_done_${choice.id}" to true) + (choice.id to true)
            } else {
                _state.value.narrativeFlags
            }
            val updatedDecisions = _state.value.decisionsMade + choice.id
            _state.value = _state.value.copy(
                narrativeFlags = updatedFlags,
                decisionsMade = updatedDecisions
            )
            applyNodeTransition(nextNode)
        }
    }

    private fun applyNodeTransition(newNode: DialogueNode, suppressNarration: Boolean = false) {
        var updatedFlags = if (newNode.setFlagOnEnter != null) {
            _state.value.narrativeFlags + (newNode.setFlagOnEnter to true)
        } else {
            _state.value.narrativeFlags
        }

        var effectiveNode = resolveEffectiveHubNode(newNode, updatedFlags)
        if (effectiveNode.setFlagOnEnter != null && updatedFlags[effectiveNode.setFlagOnEnter] != true) {
            updatedFlags = updatedFlags + (effectiveNode.setFlagOnEnter to true)
        }

        var updatedPartyStats = _state.value.partyStats
        val effectiveRestFlag = effectiveNode.setFlagOnEnter ?: newNode.setFlagOnEnter
        if (effectiveRestFlag?.endsWith("_rest_complete") == true) {
            val isStoryMilestoneRest = effectiveRestFlag == "substory_rest_complete"
            updatedPartyStats = if (isStoryMilestoneRest) {
                // Camp Midnight Vigil milestone: the one true full restoration.
                updatedPartyStats.map { member ->
                    member.copy(currentHp = member.maxHp, currentMp = member.maxMp)
                }
            } else {
                // Ordinary camp rest: recover HALF of missing HP/MP (attrition keeps healing
                // and caution meaningful); a fallen comrade is revived at half vitals.
                updatedPartyStats.map { member ->
                    val missingHp = (member.maxHp - member.currentHp).coerceAtLeast(0)
                    val missingMp = (member.maxMp - member.currentMp).coerceAtLeast(0)
                    member.copy(
                        currentHp = if (member.currentHp <= 0) member.maxHp / 2 else (member.currentHp + missingHp / 2).coerceAtMost(member.maxHp),
                        currentMp = if (member.currentMp <= 0) member.maxMp / 2 else (member.currentMp + missingMp / 2).coerceAtMost(member.maxMp)
                    )
                }
            }
        }

        // Ensure Cedric is present in party stats from Chapter 1 Camp onward
        val isCedricPostRecruitNode = effectiveNode.id == "crossroads_camp_trans" ||
            effectiveNode.id.startsWith("camp_") || effectiveNode.id.startsWith("ch3_") ||
            effectiveNode.id.startsWith("ch4_") || effectiveNode.id.startsWith("ch5_") ||
            effectiveNode.id.startsWith("ch6_") || effectiveNode.id.startsWith("ch7_") ||
            effectiveNode.id.startsWith("ch8_") || effectiveNode.id.startsWith("ch9_") ||
            effectiveNode.id.startsWith("ch10_") || effectiveNode.id.startsWith("ch11_") ||
            effectiveNode.id.startsWith("ch12_") || effectiveNode.id.startsWith("ch13_") ||
            effectiveNode.id.startsWith("ch14_") || effectiveNode.id.startsWith("ch15_") ||
            effectiveNode.id.startsWith("ch16_") || effectiveNode.id.startsWith("epilogue_") ||
            updatedFlags["cedric_recruited"] == true
        if (isCedricPostRecruitNode && updatedPartyStats.none { it.id == "cedric" }) {
            val cedricStats = SavedCharacterStats(
                id = "cedric",
                name = "Sir Cedric",
                loreClass = "Templar",
                currentHp = 310,
                maxHp = 420,
                currentMp = 80,
                maxMp = 80,
                speed = 55,
                level = 1,
                xp = 0,
                spellIds = listOf("holy_smite", "lay_on_hands", "shield_wall")
            )
            updatedPartyStats = updatedPartyStats + cedricStats
        }

        // When Lyra is recruited, add her to party stats
        val isLyraPostRecruitNode = effectiveNode.id == "ch6_party_joins" || updatedFlags["lyra_recruited"] == true ||
            effectiveNode.id.startsWith("ch6_") || effectiveNode.id.startsWith("ch7_") ||
            effectiveNode.id.startsWith("ch8_") || effectiveNode.id.startsWith("ch9_") ||
            effectiveNode.id.startsWith("ch10_") || effectiveNode.id.startsWith("ch11_") ||
            effectiveNode.id.startsWith("ch12_") || effectiveNode.id.startsWith("ch13_") ||
            effectiveNode.id.startsWith("ch14_") || effectiveNode.id.startsWith("ch15_") ||
            effectiveNode.id.startsWith("ch16_") || effectiveNode.id.startsWith("epilogue_")
        if (isLyraPostRecruitNode && updatedPartyStats.none { it.id == "lyra" }) {
            val lyraStats = SavedCharacterStats(
                id = "lyra",
                name = "Lyra",
                loreClass = "Grove Warden",
                currentHp = 240,
                maxHp = 280,
                currentMp = 120,
                maxMp = 120,
                speed = 65,
                level = 1,
                xp = 0,
                spellIds = listOf("soothing_rain", "briar_entangle")
            )
            updatedPartyStats = updatedPartyStats + lyraStats
        }

        // When Zephyr is recruited (post-ambush in Ch 8 or Ch 9 onward), add him to party stats
        val isZephyrPostRecruitNode = effectiveNode.id == "ch8_executioner_victory" || effectiveNode.id == "ch8_hub" ||
            effectiveNode.id.startsWith("ch8_motives") || effectiveNode.id.startsWith("ch8_map") ||
            effectiveNode.id.startsWith("ch8_herbs") || effectiveNode.id.startsWith("ch8_all") ||
            effectiveNode.id.startsWith("ch9_") || effectiveNode.id.startsWith("ch10_") ||
            effectiveNode.id.startsWith("ch11_") || effectiveNode.id.startsWith("ch12_") ||
            effectiveNode.id.startsWith("ch13_") || effectiveNode.id.startsWith("ch14_") ||
            effectiveNode.id.startsWith("ch15_") || effectiveNode.id.startsWith("ch16_") ||
            effectiveNode.id.startsWith("epilogue_")
        if ((updatedFlags["zephyr_recruited"] == true || isZephyrPostRecruitNode) &&
            updatedPartyStats.none { it.id == "zephyr" }
        ) {
            val zephyrStats = SavedCharacterStats(
                id = "zephyr",
                name = "Zephyr",
                loreClass = "Shadowblade",
                currentHp = 250,
                maxHp = 250,
                currentMp = 90,
                maxMp = 90,
                speed = 85,
                level = 1,
                xp = 0,
                spellIds = listOf("shadow_strike", "venom_flurry")
            )
            updatedPartyStats = updatedPartyStats + zephyrStats
        }

        // Commander America Companions: Marco Rubio (Act II Ch2+), JD Vance (Act 3), Kash Patel (Act 4), RFK Jr. (Act 5)
        // Marco joins in Chapter 2 of Act II (act2_ch2_* and later). act2_ch1_* is pre-Marco.
        val isMarcoRecruited = (effectiveNode.id.startsWith("act2_") && !effectiveNode.id.startsWith("act2_ch1_")) ||
            effectiveNode.id.startsWith("act3_") || effectiveNode.id.startsWith("act4_") ||
            effectiveNode.id.startsWith("act5_") || effectiveNode.id.startsWith("act6_") ||
            effectiveNode.id.startsWith("act7_") || effectiveNode.id.startsWith("act8_") ||
            updatedFlags["marco_recruited"] == true
        if (isMarcoRecruited && updatedPartyStats.none { it.id == "marco" }) {
            val marcoStats = SavedCharacterStats(
                id = "marco",
                name = "Marco Rubio",
                loreClass = "Sanctions Sorcerer",
                currentHp = 3800,
                maxHp = 3800,
                currentMp = 1200,
                maxMp = 1200,
                speed = 65,
                level = 1,
                xp = 0,
                spellIds = listOf("ca_marco_filibuster", "ca_marco_sanction", "ca_marco_hydrate", "ca_marco_tariff")
            )
            updatedPartyStats = updatedPartyStats + marcoStats
        }

        val isVanceRecruited = effectiveNode.id.startsWith("act3_") || effectiveNode.id.startsWith("act4_") ||
            effectiveNode.id.startsWith("act5_") || effectiveNode.id.startsWith("act6_") ||
            effectiveNode.id.startsWith("act7_") || effectiveNode.id.startsWith("act8_") || updatedFlags["vance_recruited"] == true
        if (isVanceRecruited && updatedPartyStats.none { it.id == "vance" }) {
            val vanceStats = SavedCharacterStats(
                id = "vance",
                name = "JD Vance",
                loreClass = "Rustbelt Vanguard",
                currentHp = 4200,
                maxHp = 4200,
                currentMp = 1000,
                maxMp = 1000,
                speed = 60,
                level = 1,
                xp = 0,
                spellIds = listOf("ca_vance_debate", "ca_vance_slam", "ca_vance_fortitude", "ca_vance_commonsense")
            )
            updatedPartyStats = updatedPartyStats + vanceStats
        }

        val isKashRecruited = effectiveNode.id.startsWith("act4_") || effectiveNode.id.startsWith("act5_") ||
            effectiveNode.id.startsWith("act6_") || effectiveNode.id.startsWith("act7_") ||
            effectiveNode.id.startsWith("act8_") || updatedFlags["kash_recruited"] == true
        if (isKashRecruited && updatedPartyStats.none { it.id == "kash" }) {
            val kashStats = SavedCharacterStats(
                id = "kash",
                name = "Kash Patel",
                loreClass = "Intel Inquisitor",
                currentHp = 3900,
                maxHp = 3900,
                currentMp = 1100,
                maxMp = 1100,
                speed = 68,
                level = 1,
                xp = 0,
                spellIds = listOf("ca_kash_declassify", "ca_kash_binder", "ca_kash_clearance", "ca_kash_leak")
            )
            updatedPartyStats = updatedPartyStats + kashStats
        }

        val isKennedyRecruited = ((effectiveNode.id.startsWith("act5_") && !effectiveNode.id.startsWith("act5_ch1_")) ||
            effectiveNode.id.startsWith("act6_") || effectiveNode.id.startsWith("act7_") ||
            effectiveNode.id.startsWith("act8_") || updatedFlags["kennedy_recruited"] == true)
        if (isKennedyRecruited && updatedPartyStats.none { it.id == "kennedy" }) {
            val kennedyStats = SavedCharacterStats(
                id = "kennedy",
                name = "Senator Kennedy",
                loreClass = "Committee Cross-Examiner",
                currentHp = 4000,
                maxHp = 4000,
                currentMp = 1150,
                maxMp = 1150,
                speed = 62,
                level = 1,
                xp = 0,
                spellIds = listOf("ca_kennedy_english", "ca_kennedy_officer", "ca_kennedy_remedy", "ca_kennedy_oneliner")
            )
            updatedPartyStats = updatedPartyStats + kennedyStats
        }

        // Companion master spells awarded on trial completion
        if (updatedFlags["cedric_trial_complete"] == true) {
            updatedPartyStats = updatedPartyStats.map { member ->
                if (member.id == "cedric" && !member.spellIds.contains("aegis_dawn")) {
                    member.copy(spellIds = member.spellIds + "aegis_dawn")
                } else member
            }
        }
        if (updatedFlags["lyra_trial_complete"] == true) {
            updatedPartyStats = updatedPartyStats.map { member ->
                if (member.id == "lyra" && !member.spellIds.contains("verdant_cataclysm")) {
                    member.copy(spellIds = member.spellIds + "verdant_cataclysm")
                } else member
            }
        }
        if (updatedFlags["zephyr_trial_complete"] == true) {
            updatedPartyStats = updatedPartyStats.map { member ->
                if (member.id == "zephyr" && !member.spellIds.contains("umbral_siphon")) {
                    member.copy(spellIds = member.spellIds + "umbral_siphon")
                } else member
            }
        }

        val sceneIdToUse = effectiveNode.changeSceneId ?: _state.value.currentScene.id
        val targetScene = StoryScript.ALL_SCENES[sceneIdToUse] ?: _state.value.currentScene

        val entry = DialogueLogEntry(
            speakerId = effectiveNode.speaker.id,
            speakerName = effectiveNode.speaker.name,
            speakerTitle = effectiveNode.speaker.title,
            text = effectiveNode.text,
            sceneName = targetScene.name,
            isNarrator = effectiveNode.speaker.id.startsWith("narrator") || effectiveNode.speaker == DialogueSpeaker.NARRATOR
        )
        val updatedHistory = (_state.value.dialogueHistory + entry).takeLast(100)

        var updatedMetrics = _state.value.dynamicMetrics
        if (effectiveNode.metricDeltas.isNotEmpty()) {
            val mutable = updatedMetrics.toMutableMap()
            effectiveNode.metricDeltas.forEach { (metricId, delta) ->
                val cur = mutable[metricId] ?: 50f
                mutable[metricId] = (cur + delta).coerceIn(0f, 100f)
            }
            updatedMetrics = mutable
        }
        val updatedVisited = _state.value.visitedNodeIds + effectiveNode.id

        _state.value = _state.value.copy(
            currentScene = targetScene,
            currentNode = effectiveNode,
            narrativeFlags = updatedFlags,
            partyStats = updatedPartyStats,
            dialogueHistory = updatedHistory,
            dynamicMetrics = updatedMetrics,
            visitedNodeIds = updatedVisited
        )

        musicManager?.playTrack(targetScene.musicAsset)
        persistCurrentState()
        if (!suppressNarration) {
            narrateCurrentNode()
        }
    }

    private fun resolveEffectiveHubNode(node: DialogueNode, flags: Map<String, Boolean>): DialogueNode {
        var effective = node
        if (effective.id == "camp_intro" || effective.id == "camp_hub") {
            val allThree = flags["substory_blight_complete"] == true &&
                    flags["substory_towers_complete"] == true &&
                    flags["substory_rest_complete"] == true
            if (allThree) StoryScript.ALL_NODES["camp_all_completed"]?.let { effective = it }
        }
        if (effective.id == "chapter3_intro" || effective.id == "ch3_hub") {
            if (flags["ch3_sentinels_complete"] == true && flags["ch3_chime_complete"] == true) {
                StoryScript.ALL_NODES["ch3_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch5_intro" || effective.id == "ch5_hub") {
            if (flags["ch5_creek_scouted"] == true && flags["ch5_wards_examined"] == true) {
                StoryScript.ALL_NODES["ch5_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch6_intro" || effective.id == "ch6_hub") {
            if (flags["ch6_lore_complete"] == true && flags["ch6_spores_complete"] == true) {
                StoryScript.ALL_NODES["ch6_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch7_intro" || effective.id == "ch7_hub") {
            if (flags["ch7_tuning_complete"] == true && flags["ch7_stele_complete"] == true && flags["ch7_cedric_complete"] == true && flags["ch7_dagger_complete"] == true) {
                StoryScript.ALL_NODES["ch7_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch7_camp_intro" || effective.id == "ch7_camp_hub") {
            if (flags["ch7_camp_lyra_complete"] == true && flags["ch7_camp_cedric_complete"] == true && flags["ch7_camp_watch_complete"] == true) {
                StoryScript.ALL_NODES["ch7_camp_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch8_scout_hub") {
            if (flags["ch8_wire_complete"] == true && flags["ch8_herbs_scout_complete"] == true) {
                StoryScript.ALL_NODES["ch8_scout_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch8_hub") {
            if (flags["ch8_motives_complete"] == true && flags["ch8_map_complete"] == true && flags["ch8_herbs_complete"] == true) {
                StoryScript.ALL_NODES["ch8_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch9_intro" || effective.id == "ch9_hub") {
            if (flags["ch9_knights_complete"] == true && flags["ch9_altar_complete"] == true && flags["ch9_reliquary_complete"] == true) {
                StoryScript.ALL_NODES["ch9_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch10_intro" || effective.id == "ch10_hub") {
            if (flags["ch10_dryads_complete"] == true && flags["ch10_seed_complete"] == true && flags["ch10_hymn_complete"] == true) {
                StoryScript.ALL_NODES["ch10_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch11_intro" || effective.id == "ch11_hub") {
            if (flags["ch11_traps_complete"] == true && flags["ch11_vials_complete"] == true && flags["ch11_doctrine_complete"] == true) {
                StoryScript.ALL_NODES["ch11_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch12_intro" || effective.id == "ch12_hub") {
            if (flags["ch12_valves_complete"] == true && flags["ch12_cogs_complete"] == true && flags["ch12_grimoire_complete"] == true) {
                StoryScript.ALL_NODES["ch12_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch13_intro" || effective.id == "ch13_hub") {
            if (flags["ch13_gate_complete"] == true && flags["ch13_seal_complete"] == true && flags["ch13_banners_complete"] == true) {
                StoryScript.ALL_NODES["ch13_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch14_intro" || effective.id == "ch14_hub") {
            if (flags["ch14_archons_complete"] == true && flags["ch14_eddies_complete"] == true && flags["ch14_rites_complete"] == true) {
                StoryScript.ALL_NODES["ch14_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "ch15_intro" || effective.id == "ch15_hub") {
            if (flags["ch15_cedric_complete"] == true && flags["ch15_lyra_complete"] == true && flags["ch15_zephyr_complete"] == true && flags["ch15_bell_complete"] == true) {
                StoryScript.ALL_NODES["ch15_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "camp_belfry_intro" || effective.id == "camp_belfry_hub") {
            if (flags["camp_belfry_bell_complete"] == true && flags["camp_belfry_lyra_complete"] == true && flags["camp_belfry_burden_complete"] == true) {
                StoryScript.ALL_NODES["camp_belfry_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "camp_pilgrim_intro" || effective.id == "camp_pilgrim_hub") {
            if (flags["camp_pilgrim_cedric_complete"] == true && flags["camp_pilgrim_lyra_complete"] == true && flags["camp_pilgrim_zephyr_complete"] == true) {
                StoryScript.ALL_NODES["camp_pilgrim_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "camp_gorge_intro" || effective.id == "camp_gorge_hub") {
            if (flags["camp_gorge_lyra_complete"] == true && flags["camp_gorge_cedric_complete"] == true && flags["camp_gorge_zephyr_complete"] == true) {
                StoryScript.ALL_NODES["camp_gorge_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "camp_foundry_intro" || effective.id == "camp_foundry_hub") {
            if (flags["camp_foundry_zephyr_complete"] == true && flags["camp_foundry_cedric_complete"] == true && flags["camp_foundry_lyra_complete"] == true) {
                StoryScript.ALL_NODES["camp_foundry_all_completed"]?.let { effective = it }
            }
        }
        if (effective.id == "camp_reservoir_intro" || effective.id == "camp_reservoir_hub") {
            if (flags["camp_reservoir_zephyr_complete"] == true && flags["camp_reservoir_lyra_complete"] == true && flags["camp_reservoir_cedric_complete"] == true) {
                StoryScript.ALL_NODES["camp_reservoir_all_completed"]?.let { effective = it }
            }
        }
        return effective
    }

    var onOpenOptions: (() -> Unit)? = null
    var onCloseOptions: (() -> Unit)? = null

    fun triggerEncounter(encounterId: String) {
        cancelPendingAutoAdvance()
        val encounter = when (encounterId) {
            "act1_lobby_battle" -> StoryEncounters.ACT1_LOBBY_BATTLE
            "act2_media_battle" -> StoryEncounters.ACT2_MEDIA_BATTLE
            "act3_summit_battle" -> StoryEncounters.ACT3_SUMMIT_BATTLE
            "act4_hoax_battle" -> StoryEncounters.ACT4_HOAX_BATTLE
            "act5_golem_battle" -> StoryEncounters.ACT5_GOLEM_BATTLE
            "act6_ch1_border_battle" -> StoryEncounters.ACT6_CH1_BORDER_BATTLE
            "act6_ch3_bus_battle" -> StoryEncounters.ACT6_CH3_BUS_BATTLE
            "act6_kamala_battle" -> StoryEncounters.ACT6_KAMALA_BATTLE
            "act7_ch1_indictment_battle" -> StoryEncounters.ACT7_CH1_INDICTMENT_BATTLE
            "act7_ch2_contempt_battle" -> StoryEncounters.ACT7_CH2_CONTEMPT_BATTLE
            "act7_lawfare_battle" -> StoryEncounters.ACT7_LAWFARE_BATTLE
            "act8_ch1_bunker_battle" -> StoryEncounters.ACT8_CH1_BUNKER_BATTLE
            "act8_ch2_teleprompter_battle" -> StoryEncounters.ACT8_CH2_TELEPROMPTER_BATTLE
            "act8_ch3_autopen_battle" -> StoryEncounters.ACT8_CH3_AUTOPEN_BATTLE
            "act8_biden_final_battle" -> StoryEncounters.ACT8_BIDEN_FINAL_BATTLE
            "prologue_solo" -> StoryEncounters.PROLOGUE_SOLO
            "forest_ambush" -> StoryEncounters.FOREST_AMBUSH
            "blight_trackers" -> StoryEncounters.BLIGHT_TRACKERS
            "ch3_sentinels" -> StoryEncounters.CH3_SENTINELS
            "cave_broodmother" -> StoryEncounters.CAVE_BROODMOTHER
            "dungeon_descent" -> StoryEncounters.DUNGEON_DESCENT
            "castle_horde" -> StoryEncounters.CASTLE_HORDE
            "marsh_rescue" -> StoryEncounters.MARSH_RESCUE
            "swamp_behemoth" -> StoryEncounters.SWAMP_BEHEMOTH
            "ch7_mire_wyrm" -> StoryEncounters.CH7_MIRE_WYRM
            "ch8_executioner_ambush" -> StoryEncounters.CH8_EXECUTIONER_AMBUSH
            "ch9_galahault_trial" -> StoryEncounters.CH9_GALAHAULT_TRIAL
            "ch10_broodmother_trial" -> StoryEncounters.CH10_BROODMOTHER_TRIAL
            "ch11_nocturne_trial" -> StoryEncounters.CH11_NOCTURNE_TRIAL
            "ch12_warmaster_ouros" -> StoryEncounters.CH12_WARMASTER_OUROS
            "ch13_commander_vaelor" -> StoryEncounters.CH13_COMMANDER_VAELOR
            "ch14_abyssal_leviathan" -> StoryEncounters.CH14_ABYSSAL_LEVIATHAN
            "ch15_archon_custodians" -> StoryEncounters.CH15_ARCHON_CUSTODIANS
            "ch16_malakor_finale" -> StoryEncounters.CH16_MALAKOR_FINALE
            "ch9_penitent_gate" -> StoryEncounters.CH9_PENITENT_GATE
            "ch10_thicket_guardians" -> StoryEncounters.CH10_THICKET_GUARDIANS
            "ch11_gorge_stalkers" -> StoryEncounters.CH11_GORGE_STALKERS
            "ch11_archive_enforcers" -> StoryEncounters.CH11_ARCHIVE_ENFORCERS
            "ch12_automaton_patrol" -> StoryEncounters.CH12_AUTOMATON_PATROL
            "ch13_plaza_legion" -> StoryEncounters.CH13_PLAZA_LEGION
            "ch14_shallows_husks" -> StoryEncounters.CH14_SHALLOWS_HUSKS
            "ch14_trench_leeches" -> StoryEncounters.CH14_TRENCH_LEECHES
            "ch15_trial_gold" -> StoryEncounters.CH15_TRIAL_GOLD
            "ch15_trial_grove" -> StoryEncounters.CH15_TRIAL_GROVE
            "ch15_trial_shadow" -> StoryEncounters.CH15_TRIAL_SHADOW
            "ch16_nullifier_gate" -> StoryEncounters.CH16_NULLIFIER_GATE
            "ch16_mirror_gauntlet" -> StoryEncounters.CH16_MIRROR_GAUNTLET
            else -> StoryEncounters.ALL_ENCOUNTERS.firstOrNull { it.id == encounterId } ?: StoryEncounters.ACT1_LOBBY_BATTLE
        }
        speechManager.cancel()
        combatNarrator.stop()

        if (_state.value.isPureStoryMode && !isBossEncounter(encounterId)) {
            combatNarrator.speak("The fellowship swiftly dispatches the lesser foes barring their path.", force = true) {
                activeScope.launch {
                    delay(1200)
                    onCombatVictory()
                }
            }
            return
        }

        _state.value = _state.value.copy(
            gameScreen = GameScreen.COMBAT_ARENA,
            activeEncounter = encounter
        )
    }

    fun onCombatVictory() {
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()
        val lastNode = _state.value.currentNode
        val encounterId = lastNode.triggerBattleEncounterId ?: _state.value.activeEncounter?.id ?: "unknown"
        val postBattleNodeId = when (encounterId) {
            "act1_lobby_battle", "act1_ch1_press_battle" -> "act1_ch1_post_battle"
            "act1_track_a_barry_battle" -> "act1_ch2_post_battle"
            "act1_ch2_debate_battle" -> "act1_ch3_bus_post_battle"
            "act1_ch3_machine_battle", "act1_track_a_hill_rod_finale" -> "act1_ch3_post_battle"
            "act1_track_b_ferns_battle" -> "act1_tb_ch2_post_battle"
            "act1_track_b_spicy_sean_battle" -> "act1_tb_ch4_intro"
            "act1_track_b_commander_finale" -> "act1_tb_ch3_post_battle"
            "act2_ch1_inauguration_battle" -> "act2_ch1_post_battle"
            "act2_track_a_sharpie_battle" -> "act2_ch3_sharpie_post_battle"
            "act2_media_battle" -> "act2_ch5_post_battle"
            "act2_track_b_spicer_salsa_battle" -> "act2_tb_ch1_post_battle"
            "act2_track_b_kash_battle" -> "act2_tb_ch4_post_battle"
            "act2_track_b_rudy_finale" -> "act2_tb_ch5_post_battle"
            "act3_ch1_mill_battle", "act3_track_a_stairs_bike_battle" -> "act3_ch1_post_battle"
            "act3_ch3_intercept_battle", "act3_track_a_chandelier_battle" -> "act3_ch3_post_battle"
            "act3_summit_battle", "act3_track_a_biden_ice_cream_finale" -> "act3_ch5_post_battle"
            "act3_track_b_corn_pop_battle" -> "act3_tb_ch1_post_battle"
            "act3_track_b_foghorn_battle" -> "act3_tb_ch3_post_battle"
            "act3_track_b_mugshot_finale" -> "act3_tb_ch5_post_battle"
            "act4_ch1_fisa_battle", "act4_track_a_donut_battle" -> "act4_ch1_post_battle"
            "act4_ch3_subpoena_battle", "act6_ch3_bus_battle" -> "act4_ch3_post_battle"
            "act4_hoax_battle", "act6_kamala_battle" -> "act4_ch5_post_battle"
            "act4_track_b_venn_critics_battle" -> "act4_tb_ch1_post_battle"
            "act4_track_b_vance_donut_battle" -> "act4_tb_ch2_post_battle"
            "act4_track_b_concepts_debate_battle" -> "act4_tb_ch4_post_battle"
            "act4_track_b_mcdonalds_finale" -> "act4_tb_ch5_post_battle"
            "act5_ch1_pipe_battle", "act5_track_a_cybertruck_sink_battle" -> "act5_ch1_post_battle"
            "act5_ch2_inquest_battle", "act5_track_a_doge_bureaucrats_battle" -> "act5_ch2_post_battle"
            "act5_track_a_swagger_turkish_battle" -> "act5_ch3_post_battle"
            "act5_golem_battle", "act5_track_a_grand_finale" -> "act5_ch5_post_battle"
            "act5_track_b_landlord_lobby_battle" -> "act5_tb_ch1_post_battle"
            "act5_track_b_turkish_auditor_battle" -> "act5_tb_ch2_post_battle"
            "act5_track_b_musk_starjump_battle" -> "act5_tb_ch3_post_battle"
            "act5_track_b_doge_squad_battle" -> "act5_tb_ch4_post_battle"
            "act5_track_b_grand_finale" -> "act5_bipartisan_curtain_call"
            "act6_ch1_border_battle" -> "act4_ch2_intro"
            "act7_ch1_indictment_battle" -> "act7_ch1_intro"
            "act7_ch2_contempt_battle" -> "act7_ch2_intro"
            "act7_lawfare_battle" -> "act7_ch5_intro"
            "act8_ch1_bunker_battle" -> "act8_ch1_intro"
            "act8_ch2_teleprompter_battle" -> "act8_ch2_intro"
            "act8_ch3_autopen_battle" -> "act8_ch3_intro"
            "act8_biden_final_battle" -> "act8_ch5_intro"
            else -> null
        }

        val updatedDefeated = if (encounterId !in _state.value.defeatedEncounters) {
            _state.value.defeatedEncounters + encounterId
        } else {
            _state.value.defeatedEncounters
        }

        val targetNode = (if (lastNode.triggerBattleEncounterId == encounterId && lastNode.nextNodeId != null) StoryScript.ALL_NODES[lastNode.nextNodeId] else null)
            ?: (if (postBattleNodeId != null) StoryScript.ALL_NODES[postBattleNodeId] else null)
            ?: (if (lastNode.nextNodeId != null) StoryScript.ALL_NODES[lastNode.nextNodeId] else null)
            ?: lastNode

        _state.value = _state.value.copy(
            gameScreen = GameScreen.STORY_EXPLORATION,
            activeEncounter = null,
            defeatedEncounters = updatedDefeated
        )

        applyNodeTransition(targetNode)
    }

    fun updatePartyStatsFromCombat(updatedParty: List<PartyMember>) {
        val mappedStats = updatedParty.map { member ->
            val existing = _state.value.partyStats.firstOrNull { it.id == member.id }
            val mergedSpells = if (member.id == "hero") {
                member.spells.map { it.id }
            } else {
                (member.spells.map { it.id } + (existing?.spellIds ?: emptyList())).distinct()
            }
            SavedCharacterStats(
                id = member.id,
                name = member.name,
                loreClass = member.loreClass,
                currentHp = member.currentHp,
                maxHp = member.maxHp,
                currentMp = member.currentMp,
                maxMp = member.maxMp,
                speed = member.speed,
                level = member.level,
                xp = member.xp,
                spellIds = mergedSpells
            )
        }
        _state.value = _state.value.copy(partyStats = mappedStats)
        persistCurrentState()
    }

    fun persistCurrentState() {
        val s = _state.value
        val slot = s.currentSlot
        val currentSave = saveManager.load(slot) ?: GameSaveData()
        val updatedSave = currentSave.copy(
            player = s.player,
            currentSceneId = s.currentScene.id,
            currentNodeId = s.currentNode.id,
            decisionsMade = s.decisionsMade,
            narrativeFlags = s.narrativeFlags,
            partyStats = s.partyStats,
            defeatedEncounters = s.defeatedEncounters,
            achievements = s.achievements,
            recentDialogueLog = s.dialogueHistory.takeLast(60),
            isEyesFreeMode = combatNarrator.isEyesFreeMode.value,
            isPocketGuardEnabled = combatNarrator.isPocketGuardEnabled.value,
            isCombatNarrationEnabled = combatNarrator.isCombatNarrationEnabled.value,
            isAutoListen = speechManager.isAutoListen.value,
            isChimeMuted = speechManager.isChimeMuted.value,
            isNarrationEnabled = combatNarrator.isNarrationEnabled.value,
            isReadChoicesEnabled = combatNarrator.isReadChoicesEnabled.value,
            speechRate = combatNarrator.speechRate.value,
            isCharacterPitchEnabled = combatNarrator.isCharacterPitchEnabled.value,
            isSpeakerAttributionEnabled = combatNarrator.isSpeakerAttributionEnabled.value,
            isMusicEnabled = musicManager?.isMusicEnabled?.value ?: currentSave.isMusicEnabled,
            musicVolume = musicManager?.musicVolume?.value ?: currentSave.musicVolume,
            voiceAssignments = combatNarrator.getVoiceAssignments(),
            isAutoPlayEnabled = s.isPureStoryMode,
            dynamicMetrics = s.dynamicMetrics
        )
        saveManager.save(updatedSave, slot)
    }

    fun switchToCombat() {
        combatNarrator.stop()
        musicManager?.playCombatMusic()
        _state.value = _state.value.copy(gameScreen = GameScreen.COMBAT_ARENA)
    }

    fun switchToStory() {
        _state.value = _state.value.copy(gameScreen = GameScreen.STORY_EXPLORATION)
        musicManager?.playTrack(_state.value.currentScene.musicAsset)
        narrateCurrentNode()
    }

    fun openAssetViewer() {
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()
        combatNarrator.stop()
        speechManager.cancel()
        _state.value = _state.value.copy(
            previousScreen = _state.value.gameScreen,
            gameScreen = GameScreen.ASSET_VIEWER
        )
    }

    fun returnFromAssetViewer() {
        val rawTarget = _state.value.previousScreen
        val returnTarget = if (rawTarget == null || rawTarget == GameScreen.ASSET_VIEWER) {
            GameScreen.TITLE
        } else {
            rawTarget
        }
        _state.value = _state.value.copy(
            previousScreen = null,
            gameScreen = returnTarget
        )
        if (returnTarget == GameScreen.TITLE) {
            musicManager?.playTrack(com.voicerpg.engine.audio.MusicManager.TRACK_TITLE)
        } else if (returnTarget == GameScreen.STORY_EXPLORATION) {
            musicManager?.playTrack(_state.value.currentScene.musicAsset)
        }
    }

    fun openMiniGameTestMenu() {
        _state.value = _state.value.copy(isMiniGameTestMenuOpen = true)
    }

    fun closeMiniGameTestMenu() {
        _state.value = _state.value.copy(isMiniGameTestMenuOpen = false)
    }

    fun triggerMiniGame(miniGameId: String) {
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()
        val config = com.voicerpg.engine.model.MiniGameConfig(
            id = miniGameId,
            title = miniGameId.replace('_', ' ').replaceFirstChar { it.uppercase() }
        )
        if (_state.value.isPureStoryMode) {
            val outcome = com.voicerpg.engine.engine.MiniGameSimulator.simulate(
                config = config,
                skillModifier = 1.0f,
                fatigueModifier = 1.0f
            )
            onMiniGameFinished(outcome)
        } else {
            speechManager.cancel()
            combatNarrator.stop()
            val prev = _state.value.gameScreen
            _state.value = _state.value.copy(
                gameScreen = GameScreen.MINI_GAME,
                previousScreen = prev,
                activeMiniGameConfig = config,
                isMiniGameTestMenuOpen = false
            )
        }
    }

    fun onSkipMiniGame() {
        val config = _state.value.activeMiniGameConfig ?: com.voicerpg.engine.model.MiniGameConfig(id = "minigame", title = "Mini-Game")
        val outcome = com.voicerpg.engine.engine.MiniGameSimulator.simulate(
            config = config,
            skillModifier = 1.0f,
            fatigueModifier = 1.0f
        )
        onMiniGameFinished(outcome)
    }

    fun onMiniGameFinished(outcome: com.voicerpg.engine.model.MiniGameOutcome) {
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()

        if (_state.value.previousScreen == GameScreen.TITLE) {
            _state.value = _state.value.copy(
                gameScreen = GameScreen.TITLE,
                previousScreen = null,
                activeMiniGameConfig = null,
                isMiniGameTestMenuOpen = false
            )
            return
        }

        val lastNode = _state.value.currentNode
        val updatedMetrics = _state.value.dynamicMetrics.toMutableMap()
        outcome.metricDeltas.forEach { (k, v) ->
            val cur = updatedMetrics[k] ?: 50f
            updatedMetrics[k] = (cur + v).coerceIn(0f, 100f)
        }

        _state.value = _state.value.copy(
            gameScreen = GameScreen.STORY_EXPLORATION,
            previousScreen = null,
            activeMiniGameConfig = null,
            dynamicMetrics = updatedMetrics
        )

        val nextNodeId = lastNode.nextNodeId
        if (!nextNodeId.isNullOrBlank()) {
            val nextNode = StoryScript.ALL_NODES[nextNodeId]
            if (nextNode != null) {
                applyNodeTransition(nextNode)
            }
        }
    }

    fun openTutorial() {
        cancelPendingAutoAdvance()
        combatNarrator.stop()
        speechManager.cancel()
        _state.value = _state.value.copy(
            previousScreen = _state.value.gameScreen,
            gameScreen = GameScreen.TUTORIAL
        )
    }

    fun returnFromTutorial() {
        combatNarrator.stop()
        speechManager.cancel()
        _state.value = _state.value.copy(
            previousScreen = null,
            gameScreen = GameScreen.TITLE
        )
    }

    fun openAudioSetup() {
        cancelPendingAutoAdvance()
        combatNarrator.stop()
        speechManager.cancel()
        _state.value = _state.value.copy(
            previousScreen = _state.value.gameScreen,
            gameScreen = GameScreen.AUDIO_SETUP,
            isNewGameFlow = false
        )
    }

    fun returnFromAudioSetup() {
        val rawTarget = _state.value.previousScreen
        val returnTarget = if (rawTarget == null || rawTarget == GameScreen.AUDIO_SETUP) {
            GameScreen.TITLE
        } else {
            rawTarget
        }
        _state.value = _state.value.copy(
            previousScreen = null,
            gameScreen = returnTarget,
            isNewGameFlow = false
        )
        persistCurrentState()
        if (returnTarget == GameScreen.STORY_EXPLORATION) {
            musicManager?.playTrack(_state.value.currentScene.musicAsset)
            narrateCurrentNode()
        } else if (returnTarget == GameScreen.TITLE) {
            musicManager?.playTrack(com.voicerpg.engine.audio.MusicManager.TRACK_TITLE)
        } else {
            musicManager?.playTrack(com.voicerpg.engine.audio.MusicManager.TRACK_RALLY)
        }
    }

    private var fastForwardJob: Job? = null

    fun startFastForward() {
        if (_state.value.isFastForwarding) return
        cancelPendingAutoAdvance()
        combatNarrator.stop()
        speechManager.cancel()
        _state.value = _state.value.copy(isFastForwarding = true)

        fastForwardJob = activeScope.launch {
            while (_state.value.isFastForwarding) {
                if (!canAdvanceDialogue()) {
                    stopFastForward()
                    break
                }
                advanceDialogueInternal(suppressNarration = true)
                delay(120)
            }
        }
    }

    fun stopFastForward() {
        fastForwardJob?.cancel()
        fastForwardJob = null
        if (_state.value.isFastForwarding) {
            _state.value = _state.value.copy(isFastForwarding = false)
            narrateCurrentNode()
        }
    }

    fun toggleFastForward() {
        if (_state.value.isFastForwarding) {
            stopFastForward()
        } else {
            startFastForward()
        }
    }

    fun getQuestRecap(): QuestRecap {
        val s = _state.value
        return StoryRecapEngine.buildRecap(
            scene = s.currentScene,
            node = s.currentNode,
            flags = s.narrativeFlags,
            partyStats = s.partyStats
        )
    }

    fun playStoryRecap() {
        cancelPendingAutoAdvance()
        if (_state.value.isFastForwarding) {
            stopFastForward()
        }
        val recap = getQuestRecap()
        _state.value = _state.value.copy(
            isBacklogOpen = true,
            isBacklogRecapActive = true
        )
        combatNarrator.stop()
        combatNarrator.speak(recap.spokenRecap, force = true)
    }

    fun setBacklogRecapTab(isRecap: Boolean) {
        _state.value = _state.value.copy(isBacklogRecapActive = isRecap)
    }

    fun openBacklog(showRecapFirst: Boolean = true) {
        cancelPendingAutoAdvance()
        if (_state.value.isFastForwarding) {
            stopFastForward()
        }
        _state.value = _state.value.copy(
            isBacklogOpen = true,
            isBacklogRecapActive = showRecapFirst
        )
        if (combatNarrator.isEyesFreeMode.value) {
            val recap = getQuestRecap()
            combatNarrator.speak(recap.spokenRecap, force = true)
        }
    }

    fun closeBacklog() {
        combatNarrator.stop()
        _state.value = _state.value.copy(isBacklogOpen = false)
        if (combatNarrator.isEyesFreeMode.value) {
            combatNarrator.speak("Resuming story.", force = true) {
                narrateCurrentNode()
            }
        }
    }

    fun toggleBacklog() {
        if (_state.value.isBacklogOpen) {
            closeBacklog()
        } else {
            openBacklog()
        }
    }

    fun replayDialogueEntry(entry: DialogueLogEntry) {
        val speaker = when (entry.speakerId) {
            "aethel" -> DialogueSpeaker.AETHEL
            "cedric" -> DialogueSpeaker.CEDRIC
            "lyra" -> DialogueSpeaker.LYRA
            "zephyr" -> DialogueSpeaker.ZEPHYR
            "malakor" -> DialogueSpeaker.MALAKOR
            "shadow_wisp" -> DialogueSpeaker.SHADOW_WISP
            "vaelor" -> DialogueSpeaker.VAELOR
            "galahault" -> DialogueSpeaker.GALAHAULT
            "pelosi" -> DialogueSpeaker.PELOSI
            "commander_america" -> DialogueSpeaker.COMMANDER_AMERICA
            "elon" -> DialogueSpeaker.ELON
            "biden" -> DialogueSpeaker.BIDEN
            "kamala" -> DialogueSpeaker.KAMALA
            "obama" -> DialogueSpeaker.OBAMA
            "rocket_man" -> DialogueSpeaker.ROCKET_MAN
            "media_anchor" -> DialogueSpeaker.MEDIA_ANCHOR
            "narrator_karoline" -> DialogueSpeaker.NARRATOR_KAROLINE
            "narrator_kayleigh" -> DialogueSpeaker.NARRATOR_KAYLEIGH
            "narrator_hulk" -> DialogueSpeaker.NARRATOR_HULK
            else -> DialogueSpeaker.NARRATOR
        }

        combatNarrator.stop()
        combatNarrator.narrateDialogue(
            speaker = speaker,
            text = entry.text,
            choices = emptyList()
        )
    }

    fun handleStoryVoiceInput(utterance: String) {
        val lower = utterance.lowercase().trim()
        val node = _state.value.currentNode

        // Intercept backlog dismissal when backlog is open
        if (_state.value.isBacklogOpen) {
            if (lower.contains("close") || lower == "back" || lower == "return" || lower == "dismiss" || lower.contains("exit")) {
                closeBacklog()
                return
            }
        }

        // Intercept fast-forward stop
        if (_state.value.isFastForwarding) {
            if (lower == "stop" || lower == "halt" || lower == "pause" || lower.contains("stop skip")) {
                stopFastForward()
                return
            }
        }

        // Handle voice navigation on Title Screen
        if (_state.value.gameScreen == GameScreen.TITLE) {
            when {
                lower.contains("slot 1") || lower.contains("slot one") -> {
                    selectSlot(1)
                    if (lower.contains("load") || lower.contains("continue") || lower.contains("resume") || lower.contains("play")) {
                        if (_state.value.hasExistingSave) continueGame(1)
                    }
                    return
                }
                lower.contains("slot 2") || lower.contains("slot two") -> {
                    selectSlot(2)
                    if (lower.contains("load") || lower.contains("continue") || lower.contains("resume") || lower.contains("play")) {
                        if (_state.value.hasExistingSave) continueGame(2)
                    }
                    return
                }
                lower.contains("slot 3") || lower.contains("slot three") -> {
                    selectSlot(3)
                    if (lower.contains("load") || lower.contains("continue") || lower.contains("resume") || lower.contains("play")) {
                        if (_state.value.hasExistingSave) continueGame(3)
                    }
                    return
                }
                lower.contains("continue") || lower.contains("resume") || lower.contains("load") -> {
                    if (_state.value.hasExistingSave) {
                        continueGame()
                    }
                    return
                }
                lower.contains("new game") || lower.contains("start game") || lower.contains("start new") || lower.contains("begin") -> {
                    startNewGameFlow()
                    return
                }
                lower.contains("audio setup") || lower.contains("voices") || lower.contains("calibrate") -> {
                    openAudioSetup()
                    return
                }
                lower.contains("tutorial") || lower.contains("guide") || lower.contains("how to play") || lower.contains("help me") -> {
                    openTutorial()
                    return
                }
                lower.contains("story mode") || lower.contains("auto play") || lower.contains("pure story") -> {
                    startPureStoryMode()
                    return
                }
                lower.contains("options") || lower.contains("settings") -> {
                    onOpenOptions?.invoke()
                    return
                }
            }
            return
        }

        // Story Mode Pause / Resume / Toggle voice commands
        if (lower.contains("pause story") || lower.contains("pause auto play")) {
            if (_state.value.isPureStoryMode && !_state.value.isStoryAutoPlayPaused) {
                toggleStoryAutoPlayPause()
                return
            }
        }
        if (lower.contains("resume story") || lower.contains("play story") || lower.contains("resume auto play")) {
            if (_state.value.isPureStoryMode && _state.value.isStoryAutoPlayPaused) {
                toggleStoryAutoPlayPause()
                return
            }
        }
        if (lower == "story mode" || lower == "toggle story mode" || lower == "pure story mode") {
            val willEnable = !_state.value.isPureStoryMode
            setPureStoryMode(willEnable)
            val msg = if (willEnable) "Pure story mode enabled." else "Pure story mode disabled."
            combatNarrator.speak(msg, force = true)
            return
        }

        // In-game return to title voice command
        if (lower.contains("title screen") || lower.contains("main menu") || lower.contains("return to title")) {
            returnToTitle()
            return
        }

        // Intercept direct companion voice assignment voice command
        if (lower.contains("assign voice") || lower.contains("companion voice") || lower.contains("customize voice")) {
            openAudioSetup()
            return
        }

        // 0. Intercept Meta Voice Commands (Options, Narration, Choice Reading, Pocket Mode, Backlog, Fast-Forward)
        val peek = IntentParser.parse(utterance, emptyList(), emptyList(), emptyList())
        when (peek.metaCommand) {
            MetaCommand.OPEN_OPTIONS -> {
                onOpenOptions?.invoke()
                return
            }
            MetaCommand.CLOSE_OPTIONS -> {
                onCloseOptions?.invoke()
                return
            }
            MetaCommand.OPEN_BACKLOG -> {
                openBacklog()
                return
            }
            MetaCommand.CLOSE_BACKLOG -> {
                closeBacklog()
                return
            }
            MetaCommand.FAST_FORWARD -> {
                startFastForward()
                return
            }
            MetaCommand.STOP_FAST_FORWARD -> {
                if (_state.value.isFastForwarding) {
                    stopFastForward()
                    return
                }
            }
            MetaCommand.STORY_RECAP -> {
                playStoryRecap()
                return
            }
            MetaCommand.TOGGLE_NARRATION -> {
                val enabled = combatNarrator.toggleNarration()
                val status = if (enabled) "Story dialogue narration enabled." else "Story dialogue narration muted."
                combatNarrator.speak(status, force = true)
                persistCurrentState()
                return
            }
            MetaCommand.TOGGLE_READ_CHOICES -> {
                val enabled = combatNarrator.toggleReadChoices()
                val status = if (enabled) "Choice reading enabled." else "Choice reading disabled."
                combatNarrator.speak(status, force = true)
                persistCurrentState()
                return
            }
            MetaCommand.TOGGLE_EYES_FREE -> {
                val enabled = combatNarrator.toggleEyesFreeMode()
                if (enabled) {
                    speechManager.setAutoListen(true)
                }
                val status = if (enabled) "Screenless pocket mode enabled. Screen locked." else "Pocket mode disabled."
                combatNarrator.speak(status, force = true)
                persistCurrentState()
                return
            }
            MetaCommand.ENABLE_EYES_FREE -> {
                combatNarrator.setEyesFreeMode(true)
                speechManager.setAutoListen(true)
                combatNarrator.speak("Screenless pocket mode enabled. Screen locked.", force = true)
                persistCurrentState()
                return
            }
            MetaCommand.DISABLE_EYES_FREE -> {
                combatNarrator.setEyesFreeMode(false)
                combatNarrator.speak("Pocket mode disabled.", force = true)
                persistCurrentState()
                return
            }
            MetaCommand.UNLOCK_SCREEN -> {
                combatNarrator.unlockPocketGuard()
                combatNarrator.speak("Screen unlocked.", force = true)
                return
            }
            MetaCommand.LOCK_SCREEN -> {
                if (!combatNarrator.isEyesFreeMode.value) {
                    combatNarrator.setEyesFreeMode(true)
                    speechManager.setAutoListen(true)
                    persistCurrentState()
                }
                combatNarrator.lockPocketGuard()
                combatNarrator.speak("Screen locked. Pocket mode active.", force = true)
                return
            }
            MetaCommand.TOGGLE_AUTO_LISTEN -> {
                speechManager.toggleAutoListen()
                val enabled = speechManager.isAutoListen.value
                val status = if (enabled) "Hands free auto listen enabled." else "Auto listen disabled."
                combatNarrator.speak(status, force = true)
                persistCurrentState()
                return
            }
            MetaCommand.TOGGLE_MUSIC -> {
                val enabled = musicManager?.toggleMusic() ?: true
                val status = if (enabled) "Background music enabled." else "Background music muted."
                combatNarrator.speak(status, force = true)
                persistCurrentState()
                return
            }
            MetaCommand.HELP -> {
                combatNarrator.speak("Say 'Next' to advance dialogue. Say a choice keyword to select it. Say 'Faster narration' or 'Slower narration' to change speech speed. Say 'Recap' to hear your quest summary. Say 'Log' or 'History' for dialogue history. Say 'Skip' to fast-forward. Say 'Options' for settings.", force = true)
                return
            }
            MetaCommand.SPEED_UP_NARRATION -> {
                val current = combatNarrator.speechRate.value
                val next = when {
                    current < 0.95f -> 1.05f
                    current < 1.15f -> 1.25f
                    else -> 1.50f
                }
                combatNarrator.setSpeechRate(next)
                persistCurrentState()
                combatNarrator.speak("Narration speed increased to %.2fx.".format(next), force = true)
                return
            }
            MetaCommand.SLOW_DOWN_NARRATION -> {
                val current = combatNarrator.speechRate.value
                val next = when {
                    current > 1.35f -> 1.25f
                    current > 1.15f -> 1.05f
                    else -> 0.85f
                }
                combatNarrator.setSpeechRate(next)
                persistCurrentState()
                combatNarrator.speak("Narration speed decreased to %.2fx.".format(next), force = true)
                return
            }
            MetaCommand.RESET_NARRATION_SPEED -> {
                combatNarrator.setSpeechRate(1.05f)
                persistCurrentState()
                combatNarrator.speak("Narration speed reset to normal 1.05x.", force = true)
                return
            }
            else -> Unit
        }

        // 1. Epilogue restart command
        if (node.id == "epilogue_credits" || node.setFlagOnEnter == "game_completed") {
            val restartKeywords = listOf("play again", "new game", "start over", "restart", "awaken", "embark", "begin again")
            if (restartKeywords.any { lower.contains(it) }) {
                startNewGame(_state.value.player)
                return
            }
        }

        // 2. Battle trigger command
        if (node.triggerBattleEncounterId != null) {
            val combatKeywords = listOf("fight", "battle", "attack", "fireball", "commence", "strike", "charge", "to battle", "engage", "slay", "ready", "start", "draw blade")
            if (combatKeywords.any { lower.contains(it) }) {
                advanceDialogue()
                return
            }
        }

        // 3. Flexible choice selection with loud & nerdy roleplay matching
        if (node.choices.isNotEmpty()) {
            val matchedChoice = StoryChoiceMatcher.matchChoice(
                utterance = utterance,
                choices = node.choices,
                completedFlags = _state.value.narrativeFlags
            )
            if (matchedChoice != null) {
                if (isChoiceCompleted(matchedChoice)) {
                    combatNarrator.speak("That objective has already been completed. Please select a remaining task.", force = true)
                    return
                }
                selectChoice(matchedChoice)
                return
            }
        }

        // 4. Non-branching progression command (natural & nerdy progression phrases)
        val effective = effectiveDialogueChoices(node)
        if (effective.isEmpty() && StoryChoiceMatcher.isProgressionUtterance(utterance)) {
            advanceDialogue()
            return
        }

        // If in hands-free auto-listen mode and no action triggered, keep listening!
        if (speechManager.isAutoListen.value && _state.value.gameScreen == GameScreen.STORY_EXPLORATION) {
            activeScope.launch {
                delay(200)
                if (speechManager.isAutoListen.value && _state.value.gameScreen == GameScreen.STORY_EXPLORATION) {
                    speechManager.startListening(
                        onStandby = {
                            if (combatNarrator.isEyesFreeMode.value) {
                                combatNarrator.speak("Standing by. Tap the screen when you are ready to continue.", force = true)
                            }
                        },
                        onResult = { nextUtterance ->
                            handleStoryVoiceInput(nextUtterance)
                        }
                    )
                }
            }
        }
    }

    fun resumeVoiceListening() {
        if (combatNarrator.isEyesFreeMode.value) {
            combatNarrator.speak("Ready. What is your decision?", force = true) {
                activeScope.launch {
                    delay(100)
                    speechManager.startListening(
                        onStandby = {
                            if (combatNarrator.isEyesFreeMode.value) {
                                combatNarrator.speak("Standing by. Tap the screen when you are ready to continue.", force = true)
                            }
                        },
                        onResult = { utterance ->
                            handleStoryVoiceInput(utterance)
                        }
                    )
                }
            }
        } else {
            speechManager.startListening(
                onStandby = {
                    if (combatNarrator.isEyesFreeMode.value) {
                        combatNarrator.speak("Standing by. Tap the screen when you are ready to continue.", force = true)
                    }
                },
                onResult = { utterance ->
                    handleStoryVoiceInput(utterance)
                }
            )
        }
    }

    private fun narrateCurrentNode() {
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()
        if (_state.value.gameScreen != GameScreen.STORY_EXPLORATION) return
        val node = _state.value.currentNode
        val effective = effectiveDialogueChoices(node)
        val narrationText = if (effective.isEmpty() && node.choices.isNotEmpty()) {
            val sole = eligibleChoices(node).firstOrNull()
            if (sole != null) node.text + "\n\nOne path remains. " + sole.text else node.text
        } else {
            node.text
        }
        val isStoryMode = _state.value.isPureStoryMode
        val isPaused = _state.value.isStoryAutoPlayPaused

        // In Pure Story Mode, do not read choices aloud with "What is your command?" — let narrator speak the story
        val choicesToRead = if (isStoryMode) emptyList() else effective

        combatNarrator.narrateDialogue(
            speaker = node.speaker,
            text = narrationText,
            choices = choicesToRead
        ) {
            val isPocketMode = combatNarrator.isEyesFreeMode.value
            val canAuto = canAdvanceDialogue()

            if (_state.value.isPureStoryMode && !_state.value.isStoryAutoPlayPaused) {
                scheduleStoryModeNextStep(node, delayMs = 1200L)
            } else if (isPocketMode && canAuto) {
                schedulePocketModeAutoAdvance(node)
            } else if (speechManager.isAutoListen.value) {
                activeScope.launch {
                    delay(120)
                    speechManager.startListening(
                        onStandby = {
                            if (combatNarrator.isEyesFreeMode.value) {
                                combatNarrator.speak("Standing by. Tap the screen when you are ready to continue.", force = true)
                            }
                        },
                        onResult = { utterance ->
                            handleStoryVoiceInput(utterance)
                        }
                    )
                }
            }
        }

        // Safety watchdog: ONLY triggers if TTS completely stalled/failed to call onDone after generous estimated duration
        if (isStoryMode && !isPaused) {
            val estimatedDurationMs = maxOf(4500L, (narrationText.length * 85L) + 3000L)
            storyModeWatchdogJob = activeScope.launch {
                delay(estimatedDurationMs)
                if (!_state.value.isPureStoryMode || _state.value.isStoryAutoPlayPaused) return@launch
                if (_state.value.currentNode.id != node.id || _state.value.gameScreen != GameScreen.STORY_EXPLORATION) return@launch
                android.util.Log.w("VoiceRPG_Story", "Story Mode watchdog triggered after ${estimatedDurationMs}ms fallback for node ${node.id}")
                scheduleStoryModeNextStep(node, delayMs = 400L)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopFastForward()
        cancelPendingAutoAdvance()
        cancelPendingStoryMode()
    }
}
