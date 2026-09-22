package com.voicerpg.engine

import com.voicerpg.engine.audio.CombatNarrator
import com.voicerpg.engine.audio.SpeechManager
import com.voicerpg.engine.engine.TutorialBattleContent
import com.voicerpg.engine.engine.TutorialBattleStep
import com.voicerpg.engine.model.ResonanceTier
import com.voicerpg.engine.viewmodel.ModalCardType
import com.voicerpg.engine.viewmodel.TutorialBattleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TutorialBattleTest {

    private lateinit var viewModel: TutorialBattleViewModel
    private lateinit var dummySpeech: SpeechManager
    private lateinit var dummyNarrator: CombatNarrator
    private val testScope = CoroutineScope(Dispatchers.Default)

    @Before
    fun setUp() {
        dummySpeech = SpeechManager()
        dummyNarrator = CombatNarrator()
        viewModel = TutorialBattleViewModel(
            speechManager = dummySpeech,
            combatNarrator = dummyNarrator,
            scopeOverride = testScope
        )
    }

    @Test
    fun testInitialTutorialState() {
        val state = viewModel.state.value
        assertEquals(0, state.stepIndex)
        assertTrue(state.isModalVisible)
        assertEquals(ModalCardType.NARRATOR_INTRO, state.modalType)
        assertEquals(1, state.party.size)
        assertEquals("commander", state.party[0].id)
        assertEquals("Commander America", state.party[0].name)
        assertEquals(3, state.enemies.size)
        assertEquals("cable_pundit", state.enemies[0].id)
        assertEquals("teleprompter_dummy", state.enemies[1].id)
        assertEquals("swamp_bureaucrat", state.enemies[2].id)
        assertEquals("cable_pundit", state.targetedEnemyId)
    }

    @Test
    fun testStepProgressionAndFellowshipJoin() {
        // Step 0: Intro 1
        assertEquals(0, viewModel.state.value.stepIndex)
        viewModel.advanceModal()

        // Step 1: Intro 2
        assertEquals(1, viewModel.state.value.stepIndex)
        viewModel.advanceModal()

        // Step 2: Commander America Join
        assertEquals(2, viewModel.state.value.stepIndex)
        assertEquals(ModalCardType.CHARACTER_JOIN, viewModel.state.value.modalType)
        assertEquals(1, viewModel.state.value.party.size)
        assertEquals("commander", viewModel.state.value.party[0].id)
        viewModel.advanceModal()

        // Step 3: Commander America Spell Overview
        assertEquals(3, viewModel.state.value.stepIndex)
        assertEquals(ModalCardType.SPELL_OVERVIEW, viewModel.state.value.modalType)
        viewModel.advanceModal()

        // Step 4: Practice Tremendous Strike
        assertEquals(4, viewModel.state.value.stepIndex)
        assertEquals(ModalCardType.PRACTICE_CAST_PROMPT, viewModel.state.value.modalType)
    }

    @Test
    fun testPracticeCastExecutionAndDamage() = runBlocking {
        // Fast-forward to step 4 (Practice Cast Tremendous Strike)
        while (viewModel.state.value.stepIndex < 4) {
            viewModel.advanceModal()
        }

        val initialPunditHp = viewModel.state.value.enemies.first { it.id == "cable_pundit" }.currentHp

        viewModel.castSpell("Tremendous Strike")

        // Wait for animation & resolution
        delay(1800)

        val updatedState = viewModel.state.value
        val damagedPundit = updatedState.enemies.first { it.id == "cable_pundit" }

        assertTrue("Cable Pundit HP should be reduced", damagedPundit.currentHp < initialPunditHp)
        assertEquals(ModalCardType.CAST_RESULT, updatedState.modalType)
        assertTrue(updatedState.isModalVisible)
        assertNotNull(updatedState.lastCastRecord)
    }

    @Test
    fun testResonanceComparisonDemo() = runBlocking {
        // Navigate to Resonance Demo Step 1 (Simple Tremendous Strike at step 11)
        while (viewModel.state.value.stepIndex < 11) {
            val curStep = viewModel.state.value.currentStep
            if (curStep is TutorialBattleStep.PracticeCast) {
                viewModel.castSpell(curStep.spellName)
                delay(1700)
                viewModel.advanceModal()
            } else if (curStep is TutorialBattleStep.AutoAction) {
                viewModel.advanceModal()
                delay(1200)
                viewModel.advanceModal()
            } else {
                viewModel.advanceModal()
            }
        }

        assertEquals(11, viewModel.state.value.stepIndex)
        val step11 = viewModel.state.value.currentStep as TutorialBattleStep.PracticeCast
        assertTrue(step11.isResonanceDemo)
        assertEquals(0, step11.resonanceDemoIndex)

        // Cast simple Tremendous Strike
        viewModel.castSpell("Tremendous Strike")
        delay(1800)

        val simpleRecord = viewModel.state.value.lastCastRecord
        assertNotNull(simpleRecord)
        assertEquals(ResonanceTier.BASIC, simpleRecord!!.resonanceTier)
        assertEquals(0, simpleRecord.bonusPercent)
        val simpleDamage = simpleRecord.damage

        // Advance to elaborate cast (step 12)
        viewModel.advanceModal()
        assertEquals(12, viewModel.state.value.stepIndex)
        val step12 = viewModel.state.value.currentStep as TutorialBattleStep.PracticeCast
        assertTrue(step12.isResonanceDemo)
        assertEquals(1, step12.resonanceDemoIndex)

        // Cast elaborate Tremendous Strike
        viewModel.castSpell("By executive order and tremendous patriotic strength, pulverize this fake news broadcast!")
        delay(1800)

        val elaborateRecord = viewModel.state.value.lastCastRecord
        assertNotNull(elaborateRecord)
        assertTrue(elaborateRecord!!.resonanceTier.ordinal >= ResonanceTier.MASTER.ordinal)
        assertTrue(elaborateRecord.bonusPercent >= 60)
        val elaborateDamage = elaborateRecord.damage

        assertTrue(
            "Elaborate chant ($elaborateDamage dmg) must deal significantly more damage than simple chant ($simpleDamage dmg)",
            elaborateDamage > simpleDamage
        )
    }

    @Test
    fun testAutoActionBreathRestoresMana() = runBlocking {
        // Fast-forward to step 6 (Commander Tired of Winning AutoAction)
        while (viewModel.state.value.stepIndex < 6) {
            val cur = viewModel.state.value.currentStep
            if (cur is TutorialBattleStep.PracticeCast) {
                viewModel.castSpell(cur.spellName)
                delay(1700)
                viewModel.advanceModal()
            } else {
                viewModel.advanceModal()
            }
        }

        assertEquals(6, viewModel.state.value.stepIndex)
        assertTrue(viewModel.state.value.currentStep is TutorialBattleStep.AutoAction)

        // Execute auto action
        viewModel.advanceModal()
        delay(1300)

        val state = viewModel.state.value
        assertEquals(ModalCardType.ACTION_RESULT, state.modalType)
        val commander = state.party.first { it.id == "commander" }
        assertEquals(commander.maxMp, commander.currentMp)
    }

    @Test
    fun testEnemyCounterAttackDemo() = runBlocking {
        // Fast-forward to step 14 (EnemyAttackDemo)
        while (viewModel.state.value.stepIndex < 14) {
            val cur = viewModel.state.value.currentStep
            if (cur is TutorialBattleStep.PracticeCast) {
                viewModel.castSpell(cur.spellName)
                delay(1700)
                viewModel.advanceModal()
            } else if (cur is TutorialBattleStep.AutoAction) {
                viewModel.advanceModal()
                delay(1200)
                viewModel.advanceModal()
            } else {
                viewModel.advanceModal()
            }
        }

        assertEquals(14, viewModel.state.value.stepIndex)
        assertTrue(viewModel.state.value.currentStep is TutorialBattleStep.EnemyAttackDemo)

        viewModel.advanceModal()
        delay(2200)

        val state = viewModel.state.value
        assertEquals(ModalCardType.ENEMY_ATTACK_RESULT, state.modalType)
        val targetHero = state.party.firstOrNull { it.id == "marco" } ?: state.party.first()
        assertTrue("Hero should have taken counterattack damage", targetHero.currentHp < targetHero.maxHp)
    }

    @Test
    fun testFullResetTutorial() {
        // Advance a few steps
        for (i in 0 until 5) {
            viewModel.advanceModal()
        }
        assertTrue(viewModel.state.value.stepIndex > 0)

        viewModel.resetTutorial()

        val resetState = viewModel.state.value
        assertEquals(0, resetState.stepIndex)
        assertEquals(1, resetState.party.size)
        assertEquals("commander", resetState.party[0].id)
        assertEquals(3, resetState.enemies.size)
        assertEquals(ModalCardType.NARRATOR_INTRO, resetState.modalType)
    }
}
