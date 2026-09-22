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
    val ttsPitch: Float = 1.0f
) {
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
            name = "Karoline (Press Sec)",
            title = "Press Secretary of Truth",
            portraitAsset = "portraits/narrator_karoline.jpg",
            themeColor = Color(0xFFFFD54F),
            ttsPitch = 1.12f
        )

        val NARRATOR_KAYLEIGH = DialogueSpeaker(
            id = "narrator_kayleigh",
            name = "Anchor Kayleigh",
            title = "Primetime Desk Anchor",
            portraitAsset = "portraits/narrator_kayleigh.jpg",
            themeColor = Color(0xFF4FC3F7),
            ttsPitch = 1.02f
        )

        val NARRATOR_HULK = DialogueSpeaker(
            id = "narrator_hulk",
            name = "The Hulkster",
            title = "Legend of the 24-Inch Pythons",
            portraitAsset = "portraits/narrator_hulk.jpg",
            themeColor = Color(0xFFFF5252),
            ttsPitch = 0.78f
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
            title = "Commander-in-Chief of Freedom",
            portraitAsset = "portraits/commander_america.jpg",
            themeColor = Color(0xFFFFD700),
            ttsPitch = 0.90f
        )

        val ELON = DialogueSpeaker(
            id = "elon",
            name = "Elon Musk",
            title = "The Chief Doge & Starship Sovereign",
            portraitAsset = "portraits/elon.jpg",
            themeColor = Color(0xFF00E5FF),
            ttsPitch = 1.05f
        )


        val BIDEN = DialogueSpeaker(
            id = "biden",
            name = "Final Boss Biden",
            title = "The Malarkey Sovereign",
            portraitAsset = "portraits/biden.jpg",
            themeColor = Color(0xFF90CAF9),
            ttsPitch = 0.65f
        )

        val KAMALA = DialogueSpeaker(
            id = "kamala",
            name = "Kamala",
            title = "The Unburdened Vice-Sovereign",
            portraitAsset = "portraits/kamala.jpg",
            themeColor = Color(0xFFCE93D8),
            ttsPitch = 1.25f
        )

        val OBAMA = DialogueSpeaker(
            id = "obama",
            name = "Barack",
            title = "The Teleprompter Sorcerer",
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
            name = "Cable News Anchor",
            title = "Smug Primetime Pundit",
            portraitAsset = "portraits/media_anchor.jpg",
            themeColor = Color(0xFFB0BEC5),
            ttsPitch = 1.10f
        )

        val ESTABLISHMENT_RIVAL = DialogueSpeaker(
            id = "establishment_rival",
            name = "Dynasty Rival",
            title = "Focus-Grouped Candidate",
            portraitAsset = "portraits/media_anchor.jpg",
            themeColor = Color(0xFF5C6BC0),
            ttsPitch = 0.85f
        )

        val PARTY_BOSS = DialogueSpeaker(
            id = "party_boss",
            name = "Establishment Party Boss",
            title = "Convention Delegate Broker",
            portraitAsset = "portraits/media_anchor.jpg",
            themeColor = Color(0xFFC62828),
            ttsPitch = 0.70f
        )

        val PELOSI = DialogueSpeaker(
            id = "pelosi",
            name = "Madam Speaker Pelosi",
            title = "The Stock-Trading Shadow Lich",
            portraitAsset = "portraits/pelosi.jpg",
            themeColor = Color(0xFF81C784),
            ttsPitch = 0.80f
        )

        val MARCO = DialogueSpeaker(
            id = "marco",
            name = "Marco Rubio",
            title = "Secretary of State",
            portraitAsset = "portraits/marco.jpg",
            themeColor = Color(0xFF42A5F5),
            ttsPitch = 1.05f
        )

        val VANCE = DialogueSpeaker(
            id = "vance",
            name = "JD Vance",
            title = "The Vice President",
            portraitAsset = "portraits/vance.jpg",
            themeColor = Color(0xFF5C6BC0),
            ttsPitch = 0.92f
        )

        val KASH = DialogueSpeaker(
            id = "kash",
            name = "Kash Patel",
            title = "FBI Director",
            portraitAsset = "portraits/kash.jpg",
            themeColor = Color(0xFFAB47BC),
            ttsPitch = 0.98f
        )

        val KENNEDY = DialogueSpeaker(
            id = "kennedy",
            name = "Senator Kennedy",
            title = "Committee Cross-Examiner",
            portraitAsset = "portraits/kennedy.jpg",
            themeColor = Color(0xFF78909C),
            ttsPitch = 0.88f
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
    val completionFlag: String? = null
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

data class DialogueNode(
    val id: String,
    val speaker: DialogueSpeaker,
    val side: SpeakerSide,
    val text: String,
    val choices: List<DialogueChoice> = emptyList(),
    val nextNodeId: String? = null,
    val triggerBattleEncounterId: String? = null,
    val changeSceneId: String? = null,
    val setFlagOnEnter: String? = null
)

data class StoryScene(
    val id: String,
    val name: String,
    val chapterTitle: String,
    val backgroundAsset: String,
    val initialNodeId: String,
    val ambientDescription: String,
    val musicAsset: String = "audio/music/bgm_rally.ogg"
)

enum class GameScreen {
    TITLE,
    TUTORIAL,
    AUDIO_SETUP,
    CHARACTER_CREATION,
    STORY_EXPLORATION,
    COMBAT_ARENA
}

data class SaveSummary(
    val slotIndex: Int = 1,
    val heroName: String,
    val heroClassTitle: String,
    val chapterTitle: String,
    val sceneName: String,
    val partySize: Int,
    val timestamp: Long
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
            "narrator" -> DialogueSpeaker.NARRATOR.portraitAsset
            else -> null
        }
    }

}

