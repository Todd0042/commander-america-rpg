package com.voicerpg.engine

import com.voicerpg.engine.audio.CombatNarrator
import com.voicerpg.engine.audio.SpeechManager
import com.voicerpg.engine.engine.StoryScript
import com.voicerpg.engine.model.DialogueChoice
import com.voicerpg.engine.model.DialogueSpeaker
import com.voicerpg.engine.model.GameScreen
import com.voicerpg.engine.model.PlayerCustomization
import com.voicerpg.engine.model.SpeakerSide
import com.voicerpg.engine.viewmodel.StoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class StoryDialogueTest {

    private lateinit var storyViewModel: StoryViewModel
    private lateinit var dummySpeech: SpeechManager
    private lateinit var dummyNarrator: CombatNarrator

    @Before
    fun setUp() {
        dummySpeech = SpeechManager()
        dummyNarrator = CombatNarrator()
        val testScope = CoroutineScope(Dispatchers.Default)
        storyViewModel = StoryViewModel(
            speechManager = dummySpeech,
            combatNarrator = dummyNarrator,
            scopeOverride = testScope
        )
        storyViewModel.startNewGame(PlayerCustomization(name = "Commander America"))
    }

    @After
    fun tearDown() {
        storyViewModel.cancelPendingAutoAdvance()
        storyViewModel.cancelPendingStoryMode()
    }

    @Test
    fun testInitialStoryState() {
        val state = storyViewModel.state.value
        assertEquals(StoryScript.SCENE_ACT1_ESCALATOR.id, state.currentScene.id)
        assertEquals(StoryScript.SCENE_ACT1_ESCALATOR.name, state.currentScene.name)
        assertEquals("act1_intro", state.currentNode.id)
        assertEquals(SpeakerSide.CENTER_NARRATOR, state.currentNode.side)
        assertEquals(3, state.currentNode.choices.size)
        assertEquals(GameScreen.STORY_EXPLORATION, state.gameScreen)
    }

    @Test
    fun testDialogueChoiceSelection() {
        val speechChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_speech" }
        storyViewModel.selectChoice(speechChoice)

        val state = storyViewModel.state.value
        assertEquals("act1_ch1_speech", state.currentNode.id)
        assertEquals(DialogueSpeaker.COMMANDER_AMERICA.name, state.currentNode.speaker.name)
        assertEquals(SpeakerSide.LEFT, state.currentNode.side)
        assertTrue(state.currentNode.text.contains("country is in serious trouble"))
    }

    @Test
    fun testVoiceKeywordChoiceResolution() {
        storyViewModel.handleStoryVoiceInput("deliver the unscripted announcement speech")
        assertEquals("act1_ch1_speech", storyViewModel.state.value.currentNode.id)
        assertEquals(SpeakerSide.LEFT, storyViewModel.state.value.currentNode.side)
    }

    @Test
    fun testScreenlessPocketModeAutoAdvanceOnDialogueWithoutChoices() {
        // Move to act1_ch1_swagger (no choices, nextNodeId = act1_ch1_hulk_cheer)
        val voiceNode = StoryScript.ALL_NODES["act1_ch1_swagger"]!!
        storyViewModel.selectChoice(DialogueChoice("to_swagger", "To Swagger", emptyList(), voiceNode.id))

        assertEquals("act1_ch1_swagger", storyViewModel.state.value.currentNode.id)
        assertTrue(storyViewModel.canAdvanceDialogue())

        // Enable Screenless Pocket Mode
        dummyNarrator.toggleEyesFreeMode()
        assertTrue(dummyNarrator.isEyesFreeMode.value)

        // Give the 1.5s auto advance job time to complete
        Thread.sleep(1700)

        // Dialogue must have automatically advanced to act1_ch1_hulk_cheer
        assertEquals("act1_ch1_hulk_cheer", storyViewModel.state.value.currentNode.id)
    }

    @Test
    fun testScreenlessPocketModeDoesNotAutoAdvanceWhenChoicesPresent() {
        // act1_intro has 3 choices
        assertEquals("act1_intro", storyViewModel.state.value.currentNode.id)
        assertFalse(storyViewModel.canAdvanceDialogue())

        // Enable Screenless Pocket Mode
        dummyNarrator.toggleEyesFreeMode()
        assertTrue(dummyNarrator.isEyesFreeMode.value)

        // Wait beyond 1.5s
        Thread.sleep(1700)

        // It must NOT advance because user decision is required
        assertEquals("act1_intro", storyViewModel.state.value.currentNode.id)
    }

    @Test
    fun testScreenlessPocketModeAutoAdvanceCancelledOnUserAction() {
        val voiceNode = StoryScript.ALL_NODES["act1_ch1_swagger"]!!
        storyViewModel.selectChoice(DialogueChoice("to_swagger", "To Swagger", emptyList(), voiceNode.id))
        assertEquals("act1_ch1_swagger", storyViewModel.state.value.currentNode.id)

        // Enable Pocket Mode which triggers auto-advance countdown
        dummyNarrator.toggleEyesFreeMode()
        assertTrue(dummyNarrator.isEyesFreeMode.value)

        // Ensure background collector scheduled the job
        Thread.sleep(100)

        // Cancel pending job (simulating options menu open or user cancel)
        storyViewModel.cancelPendingAutoAdvance()

        // Wait beyond 1.5s
        Thread.sleep(1700)

        // Dialogue must remain at act1_ch1_swagger because auto-advance was cancelled
        assertEquals("act1_ch1_swagger", storyViewModel.state.value.currentNode.id)
    }

    @Test
    fun testOpenAndReturnFromAudioSetup() {
        assertEquals(GameScreen.STORY_EXPLORATION, storyViewModel.state.value.gameScreen)
        assertNull(storyViewModel.state.value.previousScreen)

        // Open audio setup from in-game options
        storyViewModel.openAudioSetup()
        assertEquals(GameScreen.AUDIO_SETUP, storyViewModel.state.value.gameScreen)
        assertEquals(GameScreen.STORY_EXPLORATION, storyViewModel.state.value.previousScreen)

        // Return from audio setup back to game
        storyViewModel.returnFromAudioSetup()
        assertEquals(GameScreen.STORY_EXPLORATION, storyViewModel.state.value.gameScreen)
        assertNull(storyViewModel.state.value.previousScreen)
    }

    @Test
    fun testVoiceCommandOpensAudioSetup() {
        assertEquals(GameScreen.STORY_EXPLORATION, storyViewModel.state.value.gameScreen)

        // Utter voice command to assign voices
        storyViewModel.handleStoryVoiceInput("assign voices")
        assertEquals(GameScreen.AUDIO_SETUP, storyViewModel.state.value.gameScreen)
        assertEquals(GameScreen.STORY_EXPLORATION, storyViewModel.state.value.previousScreen)

        // Return
        storyViewModel.returnFromAudioSetup()
        assertEquals(GameScreen.STORY_EXPLORATION, storyViewModel.state.value.gameScreen)
    }

    @Test
    fun testNoDialogueNodeIsDeadEnd() {
        // Every node must branch via choices, trigger a battle, advance via nextNodeId,
        // or be a dedicated game-completion terminal node.
        val terminalNodeIds = setOf("act8_ch5_hulk_finale", "act8_game_over_hulk", "epilogue_credits")
        StoryScript.ALL_NODES.values.forEach { node ->
            if (node.choices.isEmpty() && node.triggerBattleEncounterId == null && node.id !in terminalNodeIds) {
                assertNotNull(
                    "Node '${node.id}' is a dead end: no choices, no battle trigger, and no nextNodeId",
                    node.nextNodeId
                )
            }
        }
    }

    @Test
    fun testDialogueBacklogAppendsEntries() {
        val initialHistory = storyViewModel.state.value.dialogueHistory
        assertTrue("Initial history should have the intro node", initialHistory.isNotEmpty())

        val speechChoice = storyViewModel.state.value.currentNode.choices.first { it.id == "a1_speech" }
        storyViewModel.selectChoice(speechChoice)

        val updatedHistory = storyViewModel.state.value.dialogueHistory
        assertTrue(updatedHistory.size >= 2)
        val lastEntry = updatedHistory.last()
        assertEquals(DialogueSpeaker.COMMANDER_AMERICA.name, lastEntry.speakerName)
        assertTrue(lastEntry.text.contains("country is in serious trouble"))
    }

    @Test
    fun testBacklogOpenCloseAndVoiceCommands() {
        assertFalse(storyViewModel.state.value.isBacklogOpen)

        storyViewModel.handleStoryVoiceInput("log")
        assertTrue(storyViewModel.state.value.isBacklogOpen)

        storyViewModel.handleStoryVoiceInput("close")
        assertFalse(storyViewModel.state.value.isBacklogOpen)

        storyViewModel.handleStoryVoiceInput("history")
        assertTrue(storyViewModel.state.value.isBacklogOpen)

        storyViewModel.closeBacklog()
        assertFalse(storyViewModel.state.value.isBacklogOpen)
    }

    @Test
    fun testFastForwardControls() {
        assertFalse(storyViewModel.state.value.isFastForwarding)

        storyViewModel.startFastForward()
        assertTrue(storyViewModel.state.value.isFastForwarding)

        storyViewModel.stopFastForward()
        assertFalse(storyViewModel.state.value.isFastForwarding)

        storyViewModel.handleStoryVoiceInput("skip")
        assertTrue(storyViewModel.state.value.isFastForwarding)

        storyViewModel.handleStoryVoiceInput("stop")
        assertFalse(storyViewModel.state.value.isFastForwarding)
    }

    @Test
    fun testRecapVoiceCommandOpensRecapTab() {
        assertFalse(storyViewModel.state.value.isBacklogOpen)

        storyViewModel.handleStoryVoiceInput("recap")
        assertTrue(storyViewModel.state.value.isBacklogOpen)
        assertTrue(storyViewModel.state.value.isBacklogRecapActive)

        val recap = storyViewModel.getQuestRecap()
        assertNotNull(recap)
        assertEquals(StoryScript.SCENE_ACT1_ESCALATOR.chapterTitle, recap.currentChapterTitle)

        storyViewModel.setBacklogRecapTab(false)
        assertFalse(storyViewModel.state.value.isBacklogRecapActive)

        storyViewModel.handleStoryVoiceInput("close")
        assertFalse(storyViewModel.state.value.isBacklogOpen)
    }

    @Test
    fun testNewGameFlowToCharacterCreation() {
        storyViewModel.returnToTitle()
        assertEquals(GameScreen.TITLE, storyViewModel.state.value.gameScreen)
        assertFalse(storyViewModel.state.value.isNewGameFlow)

        // User taps NEW GAME
        storyViewModel.startNewGameFlow()
        assertEquals(GameScreen.CHARACTER_CREATION, storyViewModel.state.value.gameScreen)
        assertEquals(GameScreen.TITLE, storyViewModel.state.value.previousScreen)
        assertTrue(storyViewModel.state.value.isNewGameFlow)
    }

    @Test
    fun testAudioSetupFromTitleAndReturn() {
        storyViewModel.returnToTitle()
        assertEquals(GameScreen.TITLE, storyViewModel.state.value.gameScreen)

        // User taps AUDIO SETUP from Title screen
        storyViewModel.openAudioSetup()
        assertEquals(GameScreen.AUDIO_SETUP, storyViewModel.state.value.gameScreen)
        assertEquals(GameScreen.TITLE, storyViewModel.state.value.previousScreen)
        assertFalse(storyViewModel.state.value.isNewGameFlow)

        // Returning from Audio Setup goes back to Title
        storyViewModel.returnFromAudioSetup()
        assertEquals(GameScreen.TITLE, storyViewModel.state.value.gameScreen)
        assertFalse(storyViewModel.state.value.isNewGameFlow)
    }
}
