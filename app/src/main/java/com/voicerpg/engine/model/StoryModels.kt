package com.voicerpg.engine.model

import androidx.compose.ui.graphics.Color
import com.voicerpg.engine.ui.theme.FrostCyan
import com.voicerpg.engine.ui.theme.HolyYellow
import com.voicerpg.engine.ui.theme.LogosGold
import com.voicerpg.engine.ui.theme.ShadowPurple
import kotlin.random.Random

enum class SpeakerSide {
    LEFT,
    RIGHT,
    CENTER_NARRATOR
}

data class DialogueSpeaker(
    val id: String,
    val name: String,
    val title: String,
    val portraitAsset: String?,
    val themeColor: Color,
    val ttsPitch: Float = 1.0f,
    val samplePhrase: String? = null,
    val gender: String? = null,
    val allowedVoiceRegions: List<String> = emptyList()
) {
    val isNarrator: Boolean get() = id.startsWith("narrator") || id == "narrator"

    companion object {
        val AETHEL = DialogueSpeaker(
            id = "aethel",
            name = "Aethel",
            title = "Elemental Invocator",
            portraitAsset = "portraits/aethel.jpg",
            themeColor = FrostCyan,
            ttsPitch = 1.15f
        )

        val CEDRIC = DialogueSpeaker(
            id = "cedric",
            name = "Sir Cedric",
            title = "Oathkeeper Templar",
            portraitAsset = "portraits/cedric.jpg",
            themeColor = HolyYellow,
            ttsPitch = 0.85f
        )

        val NARRATOR_KAROLINE = DialogueSpeaker(
            id = "narrator_karoline",
            name = "Karoline Mic-Drop",
            title = "Gen-Z Briefing Enforcer",
            portraitAsset = "portraits/narrator_karoline.jpg",
            themeColor = Color(0xFFFFD54F),
            ttsPitch = 1.12f
        )

        val NARRATOR_KAYLEIGH = DialogueSpeaker(
            id = "narrator_kayleigh",
            name = "Kayleigh Binder-Tabs",
            title = "Color-Coded Receipt Anchor",
            portraitAsset = "portraits/narrator_kayleigh.jpg",
            themeColor = Color(0xFF4FC3F7),
            ttsPitch = 1.02f
        )

        val NARRATOR_HULK = DialogueSpeaker(
            id = "narrator_hulk",
            name = "Hulkster 24-Pythons",
            title = "Shirt-Ripping RNC Legend",
            portraitAsset = "portraits/narrator_hulk.jpg",
            themeColor = Color(0xFFFF5252),
            ttsPitch = 0.78f
        )

        // Track B Counterpart Narrators (Real-life counterparts to Kayleigh, Karoline, and Hulkster)
        val NARRATOR_PSAKI = DialogueSpeaker(
            id = "narrator_psaki",
            name = "Jen Circle-Back",
            title = "High Priestess of Circling Back",
            portraitAsset = "portraits/template/narrator_psaki_template.jpg",
            themeColor = Color(0xFF4FC3F7),
            ttsPitch = 1.05f
        )

        val NARRATOR_KJP = DialogueSpeaker(
            id = "narrator_kjp",
            name = "Karine Hatch-Act",
            title = "Historic Binder Guard",
            portraitAsset = "portraits/template/narrator_kjp_template.jpg",
            themeColor = Color(0xFFCE93D8),
            ttsPitch = 1.10f
        )

        val NARRATOR_OPRAH = DialogueSpeaker(
            id = "narrator_oprah",
            name = "Oprah Free-Car",
            title = "Under-Your-Seat DNC Hypeman",
            portraitAsset = "portraits/template/narrator_oprah_template.jpg",
            themeColor = Color(0xFFFFCA28),
            ttsPitch = 0.92f
        )

        val NARRATOR = NARRATOR_KAYLEIGH

        val SHADOW_WISP = DialogueSpeaker(
            id = "shadow_wisp",
            name = "Corrupted Wisp",
            title = "Blighted Husk",
            portraitAsset = "portraits/shadow_wisp.jpg",
            themeColor = ShadowPurple,
            ttsPitch = 0.70f
        )

        val LYRA = DialogueSpeaker(
            id = "lyra",
            name = "Lyra",
            title = "Grove Warden",
            portraitAsset = "portraits/lyra.jpg",
            themeColor = Color(0xFFA5D6A7),
            ttsPitch = 1.20f
        )

        val ZEPHYR = DialogueSpeaker(
            id = "zephyr",
            name = "Zephyr",
            title = "Shadowblade",
            portraitAsset = "portraits/zephyr.jpg",
            themeColor = Color(0xFFCE93D8),
            ttsPitch = 0.95f
        )

        val MALAKOR = DialogueSpeaker(
            id = "malakor",
            name = "Grand Inquisitor Malakor",
            title = "The Mute Sovereign",
            portraitAsset = "portraits/malakor.jpg",
            themeColor = Color(0xFFEF5350),
            ttsPitch = 0.60f
        )

        val VAELOR = DialogueSpeaker(
            id = "vaelor",
            name = "Commander Vaelor",
            title = "The Voice-Thief",
            portraitAsset = "portraits/vaelor.jpg",
            themeColor = Color(0xFFB0BEC5),
            ttsPitch = 0.75f
        )

        val GALAHAULT = DialogueSpeaker(
            id = "galahault",
            name = "Sir Galahault",
            title = "The Obsidian Grandmaster",
            portraitAsset = "portraits/galahault.jpg",
            themeColor = HolyYellow,
            ttsPitch = 0.78f
        )

        val NOCTURNE = DialogueSpeaker(
            id = "nocturne",
            name = "Master Nocturne",
            title = "Spymaster of the Mute",
            portraitAsset = "portraits/nocturne.jpg",
            themeColor = ShadowPurple,
            ttsPitch = 0.70f
        )

        val OUROS = DialogueSpeaker(
            id = "ouros",
            name = "Clockwork Warmaster Ouros",
            title = "Iron Bastion Sovereign",
            portraitAsset = "portraits/ouros.jpg",
            themeColor = Color(0xFFFFB74D),
            ttsPitch = 0.65f
        )

        val DRYAD_MATRON = DialogueSpeaker(
            id = "dryad_matron",
            name = "The Elder Dryad",
            title = "Voice of the Emerald Choir",
            portraitAsset = "portraits/dryad_matron.jpg",
            themeColor = Color(0xFF66BB6A),
            ttsPitch = 1.05f
        )

        val COMMANDER_AMERICA = DialogueSpeaker(
            id = "commander_america",
            name = "Commander America",
            title = "Golden Escalator Brawler",
            portraitAsset = "portraits/commander_america.jpg",
            themeColor = Color(0xFFFFD700),
            ttsPitch = 0.90f
        )

        val ELON = DialogueSpeaker(
            id = "elon",
            name = "Musk-X",
            title = "Porcelain Sink Technoking",
            portraitAsset = "portraits/elon.jpg",
            themeColor = Color(0xFF00E5FF),
            ttsPitch = 1.05f
        )

        val BIDEN = DialogueSpeaker(
            id = "biden",
            name = "Joe Naptime",
            title = "Two-Scoop Chocolate-Chip Sovereign",
            portraitAsset = "portraits/biden.jpg",
            themeColor = Color(0xFF90CAF9),
            ttsPitch = 0.65f
        )

        val KAMALA = DialogueSpeaker(
            id = "kamala",
            name = "Kamala Coconut",
            title = "Triple-Circle Venn Mystic",
            portraitAsset = "portraits/kamala.jpg",
            themeColor = Color(0xFFCE93D8),
            ttsPitch = 1.25f
        )

        val OBAMA = DialogueSpeaker(
            id = "obama",
            name = "Barry Teleprompter",
            title = "Tan-Suit Dijon Oracle",
            portraitAsset = "portraits/obama.jpg",
            themeColor = Color(0xFF4FC3F7),
            ttsPitch = 0.95f
        )

        val ROCKET_MAN = DialogueSpeaker(
            id = "rocket_man",
            name = "Rocket Man",
            title = "Chairman of the Crimson Spire",
            portraitAsset = "portraits/rocket_man.jpg",
            themeColor = Color(0xFFEF5350),
            ttsPitch = 0.70f
        )

        val MEDIA_ANCHOR = DialogueSpeaker(
            id = "media_anchor",
            name = "Chyron McSpin",
            title = "Breaking-News Outrage Pundit",
            portraitAsset = "portraits/media_anchor.jpg",
            themeColor = Color(0xFFB0BEC5),
            ttsPitch = 1.10f
        )

        val ESTABLISHMENT_RIVAL = DialogueSpeaker(
            id = "establishment_rival",
            name = "Low-Energy Jeb!",
            title = "$130M Guacamole Bowl Peddler",
            portraitAsset = "portraits/media_anchor.jpg",
            themeColor = Color(0xFF5C6BC0),
            ttsPitch = 0.85f
        )

        val PARTY_BOSS = DialogueSpeaker(
            id = "party_boss",
            name = "Super-Delegate Boss",
            title = "Smoke-Filled Backroom Broker",
            portraitAsset = "portraits/media_anchor.jpg",
            themeColor = Color(0xFFC62828),
            ttsPitch = 0.70f
        )

        val PELOSI = DialogueSpeaker(
            id = "pelosi",
            name = "Nancy Palooza",
            title = "Sub-Zero Call-Option Oracle",
            portraitAsset = "portraits/pelosi.jpg",
            themeColor = Color(0xFF81C784),
            ttsPitch = 0.80f
        )

        val MARCO = DialogueSpeaker(
            id = "marco",
            name = "Little Marco",
            title = "Emergency Water-Bottle Sipper",
            portraitAsset = "portraits/marco.jpg",
            themeColor = Color(0xFF42A5F5),
            ttsPitch = 1.05f
        )

        val VANCE = DialogueSpeaker(
            id = "vance",
            name = "JD Pants",
            title = "Whatever-Makes-Sense Donut Connoisseur",
            portraitAsset = "portraits/vance.jpg",
            themeColor = Color(0xFF5C6BC0),
            ttsPitch = 0.92f
        )

        val KASH = DialogueSpeaker(
            id = "kash",
            name = "Kash Subpoena",
            title = "Children's-Book Wizard Inquisitor",
            portraitAsset = "portraits/kash.jpg",
            themeColor = Color(0xFFAB47BC),
            ttsPitch = 0.98f
        )

        val KENNEDY = DialogueSpeaker(
            id = "kennedy",
            name = "Foghorn Kennedy",
            title = "Folksy Country-Fried Cross-Examiner",
            portraitAsset = "portraits/kennedy.jpg",
            themeColor = Color(0xFF78909C),
            ttsPitch = 0.88f
        )

        // New Parody Speakers for Dual-Track Rewrite
        val HILL_ROD = DialogueSpeaker(
            id = "hill_rod",
            name = "Hill-Rod",
            title = "Chappaqua Cloth-Wipe Technomancer",
            portraitAsset = "portraits/template/hill_rod_template.jpg",
            themeColor = Color(0xFF64B5F6),
            ttsPitch = 1.08f
        )

        val SPICY_SEAN = DialogueSpeaker(
            id = "spicy_sean",
            name = "Spicy Sean",
            title = "Hedge-Hiding Cinnamon Gum Bard",
            portraitAsset = "portraits/template/spicy_sean_template.jpg",
            themeColor = Color(0xFFAED581),
            ttsPitch = 1.14f
        )

        val ZACH_FERNS = DialogueSpeaker(
            id = "zach_ferns",
            name = "Zach Two-Ferns",
            title = "Deadpan Potted-Plant Interrogator",
            portraitAsset = "portraits/template/zach_ferns_template.jpg",
            themeColor = Color(0xFF81C784),
            ttsPitch = 0.88f
        )

        val BILLY_BUS = DialogueSpeaker(
            id = "billy_bus",
            name = "Billy Access-Bus",
            title = "2005 Hot-Mic Bus Giggler",
            portraitAsset = "portraits/template/billy_bus_template.jpg",
            themeColor = Color(0xFFFFB74D),
            ttsPitch = 1.15f
        )

        val COMEY_TALL = DialogueSpeaker(
            id = "comey_tall",
            name = "Director Jim-Tall",
            title = "6-Foot-8 Blue-Curtain Chameleon",
            portraitAsset = "portraits/template/comey_template.jpg",
            themeColor = Color(0xFF90A4AE),
            ttsPitch = 0.82f
        )

        val RUDY_GHOUL = DialogueSpeaker(
            id = "rudy_ghoul",
            name = "Rudy Ghoul",
            title = "Four-Seasons Hair-Dye Landscaper",
            portraitAsset = "portraits/template/rudy_ghoul_template.jpg",
            themeColor = Color(0xFFBA68C8),
            ttsPitch = 0.76f
        )

        val ZOHRAN_CARDAMOM = DialogueSpeaker(
            id = "zohran_cardamom",
            name = "Zohran Cardamom",
            title = "Fare-Free Chapati Rap Mayor",
            portraitAsset = "portraits/template/mamdani_template.jpg",
            themeColor = Color(0xFFFF8A65),
            ttsPitch = 1.02f
        )

        val MAYOR_SWAGGER = DialogueSpeaker(
            id = "mayor_swagger",
            name = "Mayor Swagger",
            title = "Istanbul-Upgrade Nightlife Czar",
            portraitAsset = "portraits/template/mayor_swagger_template.jpg",
            themeColor = Color(0xFF4DD0E1),
            ttsPitch = 0.90f
        )

        val THE_MOOCH = DialogueSpeaker(
            id = "the_mooch",
            name = "The 11-Day Mooch",
            title = "Front-Stabbing Wall Street Whirlwind",
            portraitAsset = "portraits/template/mooch_template.jpg",
            themeColor = Color(0xFFFF80AB),
            ttsPitch = 1.16f
        )
    }

    /**
     * The Chronicle occasionally breaks the fourth wall with a rare alternate portrait.
     * Roughly 1 in every 100 narrator displays picks the easter-egg art; otherwise the
     * standard chronicle portrait is used. Non-narrator speakers always use their fixed art.
     */
    fun effectivePortraitAsset(seed: Any? = null): String? {
        if (this != NARRATOR || portraitAsset == null) return portraitAsset
        val roll = if (seed != null) {
            val h = (seed.hashCode() xor 0x5deece66d.toInt())
            (h and 0x7fffffff) % 100
        } else {
            Random.nextInt(100)
        }
        return if (roll == 0) "portraits/narrator1.jpg" else portraitAsset
    }
}

data class DialogueChoice(
    val id: String,
    val text: String,
    val voiceKeywords: List<String>,
    val nextNodeId: String,
    val completionFlag: String? = null,
    val requiredFlags: List<String> = emptyList(),
    val metricDeltas: Map<String, Float> = emptyMap(),
    val assignHeroClassId: String? = null,
    val triggerMiniGameId: String? = null
) {
    fun isCompleted(narrativeFlags: Map<String, Boolean>): Boolean {
        if (completionFlag != null) {
            return narrativeFlags[completionFlag] == true ||
                   narrativeFlags["choice_done_$id"] == true ||
                   narrativeFlags[id] == true
        }
        return narrativeFlags["choice_done_$id"] == true
    }
}

data class HubCompletion(
    val requiredFlags: List<String> = emptyList(),
    val redirectToNodeId: String = ""
)

data class DialogueNode(
    val id: String,
    val speaker: DialogueSpeaker,
    val side: SpeakerSide,
    val text: String,
    val revisitText: String? = null,
    val choices: List<DialogueChoice> = emptyList(),
    val nextNodeId: String? = null,
    val triggerBattleEncounterId: String? = null,
    val triggerMiniGameId: String? = null,
    val changeSceneId: String? = null,
    val setFlagOnEnter: String? = null,
    val hubCompletion: HubCompletion? = null,
    val healPartyOnEnter: String? = null,
    val metricDeltas: Map<String, Float> = emptyMap(),
    val metricOverrides: Map<String, Float> = emptyMap(),
    val assignHeroClassId: String? = null,
    val allowIncidents: Boolean = true,
    val blacklistIncidents: Boolean = false
)

data class StoryScene(
    val id: String,
    val name: String,
    val chapterTitle: String,
    val backgroundAsset: String,
    val initialNodeId: String,
    val ambientDescription: String,
    val musicAsset: String = "audio/music/bgm_rally.ogg",
    val chapterIndex: Int = 0,
    val actNumber: Int = 1,
    val actTitle: String = "Act I",
    val milestoneTitle: String = "",
    val milestoneLocation: String = "",
    val milestoneSummary: String = "",
    val milestoneIcon: String = "✨",
    val defaultObjective: String = ""
)

enum class GameScreen {
    LANGUAGE_SELECTION,
    DIALECT_SELECTION,
    LANGUAGE_DOWNLOAD_STANDBY,
    TITLE,
    TUTORIAL,
    AUDIO_SETUP,
    CHARACTER_CREATION,
    STORY_EXPLORATION,
    COMBAT_ARENA,
    MINI_GAME,
    ASSET_VIEWER
}

data class SaveSummary(
    val slotIndex: Int = 1,
    val heroName: String,
    val heroClassTitle: String,
    val chapterTitle: String,
    val sceneName: String,
    val partySize: Int,
    val timestamp: Long,
    val dynamicMetrics: Map<String, Float> = emptyMap()
)

data class SaveSlotInfo(
    val slotIndex: Int,
    val summary: SaveSummary?
) {
    val isEmpty: Boolean get() = summary == null
}

data class DialogueLogEntry(
    val speakerId: String,
    val speakerName: String,
    val speakerTitle: String,
    val text: String,
    val sceneName: String,
    val isNarrator: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun speakerThemeColor(): Color {
        return when (speakerId) {
            "aethel" -> DialogueSpeaker.AETHEL.themeColor
            "cedric" -> DialogueSpeaker.CEDRIC.themeColor
            "lyra" -> DialogueSpeaker.LYRA.themeColor
            "zephyr" -> DialogueSpeaker.ZEPHYR.themeColor
            "malakor" -> DialogueSpeaker.MALAKOR.themeColor
            "shadow_wisp" -> DialogueSpeaker.SHADOW_WISP.themeColor
            "vaelor" -> DialogueSpeaker.VAELOR.themeColor
            "galahault" -> DialogueSpeaker.GALAHAULT.themeColor
            "nocturne" -> DialogueSpeaker.NOCTURNE.themeColor
            "ouros" -> DialogueSpeaker.OUROS.themeColor
            "pelosi" -> DialogueSpeaker.PELOSI.themeColor
            "commander_america" -> DialogueSpeaker.COMMANDER_AMERICA.themeColor
            "elon" -> DialogueSpeaker.ELON.themeColor
            "biden" -> DialogueSpeaker.BIDEN.themeColor
            "kamala" -> DialogueSpeaker.KAMALA.themeColor
            "obama" -> DialogueSpeaker.OBAMA.themeColor
            "rocket_man" -> DialogueSpeaker.ROCKET_MAN.themeColor
            "media_anchor" -> DialogueSpeaker.MEDIA_ANCHOR.themeColor
            "establishment_rival" -> DialogueSpeaker.ESTABLISHMENT_RIVAL.themeColor
            "party_boss" -> DialogueSpeaker.PARTY_BOSS.themeColor
            "marco" -> DialogueSpeaker.MARCO.themeColor
            "vance" -> DialogueSpeaker.VANCE.themeColor
            "kash" -> DialogueSpeaker.KASH.themeColor
            "kennedy" -> DialogueSpeaker.KENNEDY.themeColor
            "narrator_karoline" -> DialogueSpeaker.NARRATOR_KAROLINE.themeColor
            "narrator_kayleigh" -> DialogueSpeaker.NARRATOR_KAYLEIGH.themeColor
            "narrator_hulk" -> DialogueSpeaker.NARRATOR_HULK.themeColor
            "narrator_psaki" -> DialogueSpeaker.NARRATOR_PSAKI.themeColor
            "narrator_kjp" -> DialogueSpeaker.NARRATOR_KJP.themeColor
            "narrator_oprah" -> DialogueSpeaker.NARRATOR_OPRAH.themeColor
            "hill_rod" -> DialogueSpeaker.HILL_ROD.themeColor
            "spicy_sean" -> DialogueSpeaker.SPICY_SEAN.themeColor
            "zach_ferns" -> DialogueSpeaker.ZACH_FERNS.themeColor
            "billy_bus" -> DialogueSpeaker.BILLY_BUS.themeColor
            "comey_tall" -> DialogueSpeaker.COMEY_TALL.themeColor
            "rudy_ghoul" -> DialogueSpeaker.RUDY_GHOUL.themeColor
            "zohran_cardamom" -> DialogueSpeaker.ZOHRAN_CARDAMOM.themeColor
            "mayor_swagger" -> DialogueSpeaker.MAYOR_SWAGGER.themeColor
            "the_mooch" -> DialogueSpeaker.THE_MOOCH.themeColor
            "narrator" -> DialogueSpeaker.NARRATOR.themeColor
            else -> if (isNarrator) LogosGold else Color.White
        }
    }

    fun speakerPortraitAsset(): String? {
        return when (speakerId) {
            "aethel" -> DialogueSpeaker.AETHEL.portraitAsset
            "cedric" -> DialogueSpeaker.CEDRIC.portraitAsset
            "lyra" -> DialogueSpeaker.LYRA.portraitAsset
            "zephyr" -> DialogueSpeaker.ZEPHYR.portraitAsset
            "malakor" -> DialogueSpeaker.MALAKOR.portraitAsset
            "shadow_wisp" -> DialogueSpeaker.SHADOW_WISP.portraitAsset
            "vaelor" -> DialogueSpeaker.VAELOR.portraitAsset
            "galahault" -> DialogueSpeaker.GALAHAULT.portraitAsset
            "nocturne" -> DialogueSpeaker.NOCTURNE.portraitAsset
            "ouros" -> DialogueSpeaker.OUROS.portraitAsset
            "dryad_matron" -> DialogueSpeaker.DRYAD_MATRON.portraitAsset
            "pelosi" -> DialogueSpeaker.PELOSI.portraitAsset
            "commander_america" -> DialogueSpeaker.COMMANDER_AMERICA.portraitAsset
            "elon" -> DialogueSpeaker.ELON.portraitAsset
            "biden" -> DialogueSpeaker.BIDEN.portraitAsset
            "kamala" -> DialogueSpeaker.KAMALA.portraitAsset
            "obama" -> DialogueSpeaker.OBAMA.portraitAsset
            "rocket_man" -> DialogueSpeaker.ROCKET_MAN.portraitAsset
            "media_anchor" -> DialogueSpeaker.MEDIA_ANCHOR.portraitAsset
            "establishment_rival" -> DialogueSpeaker.ESTABLISHMENT_RIVAL.portraitAsset
            "party_boss" -> DialogueSpeaker.PARTY_BOSS.portraitAsset
            "marco" -> DialogueSpeaker.MARCO.portraitAsset
            "vance" -> DialogueSpeaker.VANCE.portraitAsset
            "kash" -> DialogueSpeaker.KASH.portraitAsset
            "kennedy" -> DialogueSpeaker.KENNEDY.portraitAsset
            "narrator_karoline" -> DialogueSpeaker.NARRATOR_KAROLINE.portraitAsset
            "narrator_kayleigh" -> DialogueSpeaker.NARRATOR_KAYLEIGH.portraitAsset
            "narrator_hulk" -> DialogueSpeaker.NARRATOR_HULK.portraitAsset
            "narrator_psaki" -> DialogueSpeaker.NARRATOR_PSAKI.portraitAsset
            "narrator_kjp" -> DialogueSpeaker.NARRATOR_KJP.portraitAsset
            "narrator_oprah" -> DialogueSpeaker.NARRATOR_OPRAH.portraitAsset
            "hill_rod" -> DialogueSpeaker.HILL_ROD.portraitAsset
            "spicy_sean" -> DialogueSpeaker.SPICY_SEAN.portraitAsset
            "zach_ferns" -> DialogueSpeaker.ZACH_FERNS.portraitAsset
            "billy_bus" -> DialogueSpeaker.BILLY_BUS.portraitAsset
            "comey_tall" -> DialogueSpeaker.COMEY_TALL.portraitAsset
            "rudy_ghoul" -> DialogueSpeaker.RUDY_GHOUL.portraitAsset
            "zohran_cardamom" -> DialogueSpeaker.ZOHRAN_CARDAMOM.portraitAsset
            "mayor_swagger" -> DialogueSpeaker.MAYOR_SWAGGER.portraitAsset
            "the_mooch" -> DialogueSpeaker.THE_MOOCH.portraitAsset
            "narrator" -> DialogueSpeaker.NARRATOR.portraitAsset
            else -> null
        }
    }

}

