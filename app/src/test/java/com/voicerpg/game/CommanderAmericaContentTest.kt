package com.voicerpg.game

import com.voicerpg.engine.content.GameContent
import com.voicerpg.engine.engine.IntentParser
import com.voicerpg.engine.model.TargetSelection
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File

/**
 * Commander America's own guarantees, checked on its JSON content (the engine's contract tests cover
 * story-graph integrity, asset wiring and chant calibration for every game). Replaces the Kotlin-content
 * CommanderAmericaGameTest from before the engine convergence.
 */
class CommanderAmericaContentTest {

    private val assets = listOf(File("src/main/assets"), File("app/src/main/assets")).first { it.isDirectory }

    @Before
    fun load() {
        GameContent.reset()
        GameContent.initializeFromDirectory(File(assets, "game"))
    }

    @Test
    fun twoCampaignsTwoPerspectivesOnAPresetSelect() {
        val manifest = GameContent.manifest
        assertEquals("Commander America", manifest.gameTitle)
        assertTrue("character creation is the candidate select", manifest.isPresetSelect)
        val tracks = manifest.campaignTracks.associateBy { it.id }
        assertEquals(setOf("track_a", "track_b"), tracks.keys)
        assertEquals("Commander America", tracks.getValue("track_a").hero?.name)
        assertEquals("Hill-Rod", tracks.getValue("track_b").hero?.name)
        for (track in tracks.values) {
            assertNotNull("${track.id} start node", GameContent.nodes[track.startNodeId])
            assertNotNull("${track.id} start scene", GameContent.scenes[track.startSceneId])
            assertEquals("${track.id} opens in its own scene", track.startNodeId, GameContent.scenes.getValue(track.startSceneId).initialNodeId)
            val hero = track.hero!!
            assertNotNull("${track.id} hero class", GameContent.classes.firstOrNull { it.id == hero.heroClassId })
            assertTrue("${track.id} hero portrait exists", File(assets, hero.portraitAsset!!).isFile)
            hero.soundBites.forEach { assertTrue("$it exists", File(assets, it).isFile) }
        }
    }

    @Test
    fun trackAIsTheCoalitionTrackBIsTheRelay() {
        val encounters = GameContent.encounters.values
        val trackA = encounters.filter { it.id.startsWith("act") && !it.id.contains("track_b") && it.initialParty != null }
        val trackB = encounters.filter { it.id.contains("track_b") }
        assertTrue(trackA.isNotEmpty() && trackB.isNotEmpty())
        // Track A: Commander America leads every fight, the coalition grows to five.
        trackA.forEach { e -> assertTrue("${e.id} is led by the hero", e.initialParty!!.any { it.id == "hero" }) }
        assertEquals(setOf("hero", "marco", "vance", "kash", "kennedy"), trackA.flatMap { e -> e.initialParty!!.map { it.id } }.toSet())
        // Track B: a relay of standard-bearers, never Commander America.
        val relay = trackB.flatMap { e -> e.initialParty!!.map { it.id } }.toSet()
        assertEquals(setOf("hill_rod", "nancy_palooza", "joe_naptime", "kamala_coconut", "zohran_cardamom", "mayor_swagger"), relay)
    }

    @Test
    fun pelosiInsiderTradingLichAppearsInAct2() {
        val media = GameContent.encounters.getValue("act2_media_battle")
        assertTrue(media.enemies.any { it.id == "pelosi" })
    }

    @Test
    fun commanderSpellsResolveByVoice() {
        val hero = GameContent.characterById("hero")!!.toPartyMember()!!
        val parsed = IntentParser.parse("Tremendous strike the lobbyist", hero.spells, emptyList(), listOf(hero))
        assertEquals("ca_tremendous_strike", parsed.spell.id)
        val marco = GameContent.characterById("marco")!!.toPartyMember()!!
        val targeted = IntentParser.parse("Heal Rubio", hero.spells, emptyList(), listOf(hero, marco))
        assertEquals(TargetSelection.SPECIFIC_HERO, targeted.target)
        assertEquals("marco", targeted.targetHeroId)
    }

    @Test
    fun coalitionAnswersOutOfTurnInCharacter() {
        for (id in listOf("hero", "marco", "vance", "kash", "kennedy")) {
            val line = GameContent.characterById(id)?.outOfTurnLine
            assertNotNull("$id has an out-of-turn line", line)
            assertTrue("$id's line names the spell's owner", line!!.contains("{owner}"))
        }
    }

    @Test
    fun realAudioQuotesAreWiredAndPresent() {
        val clips = GameContent.characters.values.flatMap { it.soundBites } +
            GameContent.spells.values.flatMap { it.soundBites } +
            GameContent.enemyTemplates.values.flatMap { it.soundBites } +
            GameContent.enemyMovesets.values.flatMap { m -> m.moves.flatMap { it.soundBites } }
        assertTrue("Commander America quotes", GameContent.characterById("hero")!!.soundBites.isNotEmpty())
        assertTrue("Biden gibberish spell plays its clip", GameContent.spells.getValue("biden_gibberish_audio").soundBites.isNotEmpty())
        clips.distinct().forEach { assertTrue("$it exists", File(assets, it).isFile) }
    }

    @Test
    fun patrioticMusicIsConfigured() {
        val m = GameContent.manifest
        listOfNotNull(m.titleMusic, m.explorationMusic, m.combatMusic).also { assertEquals(3, it.size) }
            .forEach { assertTrue("$it exists", File(assets, it).isFile) }
    }

    @Test
    fun noFantasyLeftoversFromEchoes() {
        val fantasy = setOf("aethel", "cedric", "lyra", "zephyr", "malakor")
        assertTrue(GameContent.characters.keys.none { it in fantasy })
        assertTrue(GameContent.characters.values.mapNotNull { it.speaker.portraitAsset }.none { p -> fantasy.any { p.contains(it) } })
    }
}
