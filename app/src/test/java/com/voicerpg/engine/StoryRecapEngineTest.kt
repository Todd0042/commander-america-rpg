package com.voicerpg.engine

import com.voicerpg.engine.engine.StoryRecapEngine
import com.voicerpg.engine.engine.StoryScript
import com.voicerpg.engine.model.SavedCharacterStats
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StoryRecapEngineTest {

    @Test
    fun testPrologueRecap() {
        val scene = StoryScript.SCENE_ACT1_ESCALATOR
        val node = StoryScript.ALL_NODES["act1_intro"]!!
        val flags = emptyMap<String, Boolean>()
        val party = listOf(
            SavedCharacterStats(id = "commander_america", name = "Commander America", loreClass = "Commander-in-Chief", currentHp = 350, maxHp = 350, currentMp = 120, maxMp = 120, speed = 70)
        )

        val recap = StoryRecapEngine.buildRecap(scene, node, flags, party)
        assertNotNull(recap)
        assertEquals("The Golden Tower Escalator", recap.currentSceneName)
    }

    @Test
    fun testAct8Recap() {
        val scene = StoryScript.SCENE_ACT8_CITADEL_FINAL
        val node = StoryScript.ALL_NODES["act8_intro"]!!
        val flags = mapOf("act7_lawfare_defeated" to true)
        val party = listOf(
            SavedCharacterStats(id = "commander_america", name = "Commander America", loreClass = "Commander-in-Chief", currentHp = 350, maxHp = 350, currentMp = 120, maxMp = 120, speed = 70)
        )

        val recap = StoryRecapEngine.buildRecap(scene, node, flags, party)
        assertNotNull(recap)
        assertEquals("The Final Malarkey Citadel", recap.currentSceneName)
    }
}

