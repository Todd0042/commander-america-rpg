package com.voicerpg.engine.model

import androidx.compose.ui.graphics.Color
import com.voicerpg.engine.model.SpellSchool

enum class HeroClass(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val startingHp: Int,
    val startingMp: Int,
    val startingSpeed: Int,
    val startingBaseAttack: Int,
    val preferredSchool: SpellSchool,
    val starterSpellIds: List<String>
) {
    COMMANDER(
        id = "commander",
        title = "Commander-in-Chief",
        subtitle = "Patriotic Sovereign & Master of the Deal",
        description = "Wields Tremendous Strike, Art of the Deal, Build The Wall, and Obama Did It First to obliterate political opposition.",
        startingHp = 5000,
        startingMp = 1776,
        startingSpeed = 70,
        startingBaseAttack = 50,
        preferredSchool = SpellSchool.PHYSICAL,
        starterSpellIds = listOf("ca_tremendous_strike", "ca_art_of_the_deal", "ca_obama_did_it_first", "ca_build_the_wall", "ca_rally_patriots")
    ),
    ELEMENTALIST(
        id = "elementalist",
        title = "Elementalist",
        subtitle = "Master of Primordial Elements",
        description = "Wields blistering fire, piercing frost, and crackling storm incantations. High magical burst damage.",
        startingHp = 240,
        startingMp = 160,
        startingSpeed = 70,
        startingBaseAttack = 22,
        preferredSchool = SpellSchool.PYROMANCY,
        starterSpellIds = listOf("fireball", "frost_spike", "chain_lightning")
    ),
    BATTLEMAGE(
        id = "battlemage",
        title = "Battlemage",
        subtitle = "Frontline Spellblade Vanguard",
        description = "Combines heavy martial fortitude with devastating close-range elemental inflections. High vitality and defense.",
        startingHp = 320,
        startingMp = 110,
        startingSpeed = 60,
        startingBaseAttack = 28,
        preferredSchool = SpellSchool.PHYSICAL,
        starterSpellIds = listOf("flame_strike", "arcane_barrier", "thunder_cleave")
    ),
    CHANTER(
        id = "chanter",
        title = "Chanter",
        subtitle = "Acoustic Chronomancer",
        description = "Manipulates battle cadence, accelerates turn gauges, and doubles vocal resonance bonuses. High agility and tactical speed.",
        startingHp = 220,
        startingMp = 180,
        startingSpeed = 80,
        startingBaseAttack = 18,
        preferredSchool = SpellSchool.ELECTROMANCY,
        starterSpellIds = listOf("temporal_stasis", "haste_cadence", "resonant_surge")
    ),
    SHADOWWEAVER(
        id = "shadowweaver",
        title = "Shadowweaver",
        subtitle = "Umbral Occultist",
        description = "Strikes from the blind spots of the Blight with precision critical strikes and dark life-draining sibilants.",
        startingHp = 230,
        startingMp = 140,
        startingSpeed = 85,
        startingBaseAttack = 26,
        preferredSchool = SpellSchool.SHADOW,
        starterSpellIds = listOf("shadow_spike", "void_drain", "umbral_veil")
    )
}

enum class AuraColor(
    val id: String,
    val displayName: String,
    val hexColor: String,
    val description: String
) {
    SOLAR_GOLD("sun", "Solar Gold", "#FFD700", "Radiates commanding presence"),
    SAPPHIRE_FROST("frost", "Sapphire Frost", "#00E5FF", "Crisp analytical chill"),
    CRIMSON_EMBER("fire", "Crimson Ember", "#FF1744", "Fierce passionate resonance"),
    CRIMSON_PYRE("fire", "Crimson Pyre", "#FF1744", "Fierce passionate resonance"),
    EMERALD_LIFE("nature", "Emerald Life", "#00E676", "Vibrant grounding tone"),
    EMERALD_GROVE("nature", "Emerald Grove", "#00E676", "Vibrant grounding tone"),
    AMETHYST_VOID("shadow", "Amethyst Void", "#E040FB", "Mysterious umbral frequency")
}

enum class CampaignTrack(
    val id: String,
    val displayName: String,
    val subtitle: String,
    val startNodeId: String,
    val startSceneId: String
) {
    TRACK_A(
        id = "track_a",
        displayName = "Paint it Red",
        subtitle = "Commander America + Little Marco + Wizard Kash + Foghorn Kennedy + JD Pants + Musk-X",
        startNodeId = "act1_intro",
        startSceneId = "scene_act1_ch1_escalator"
    ),
    TRACK_B(
        id = "track_b",
        displayName = "Bewilder with Blue",
        subtitle = "Hill-Rod ➔ Nancy Palooza ➔ Joe Naptime ➔ Kamala Coconut ➔ Zohran Cardamom & Mayor Swagger",
        startNodeId = "act1_tb_ch1_intro",
        startSceneId = "scene_act1_tb_ch1_server"
    )
}

/**
 * Data-driven storyline track definition (e.g. Track A, Track B).
 * Games with multiple storylines or perspectives configure these in manifest or GameContent.
 */
data class CampaignTrackDef(
    val id: String = "track_a",
    val displayName: String = "Main Campaign",
    val subtitle: String = "",
    val startNodeId: String = "",
    val startSceneId: String = "",
    val rosterDescription: String = "",
    val badgeText: String = "1P",
    val voiceKeywords: List<String> = emptyList()
)

data class PlayerCustomization(
    val name: String = "Commander America",
    val heroClass: HeroClass = HeroClass.COMMANDER,
    val heroClassId: String = "commander",
    val title: String = "Commander-in-Chief",
    val auraColor: AuraColor = AuraColor.SOLAR_GOLD,
    val voiceAffinityBonusSchool: SpellSchool = SpellSchool.PHYSICAL,
    val campaignTrack: CampaignTrack = CampaignTrack.TRACK_A,
    val campaignTrackId: String = "track_a",
    val customPortraitAsset: String? = null
)

data class SavedCharacterStats(
    val id: String,
    val name: String,
    val loreClass: String,
    val currentHp: Int,
    val maxHp: Int,
    val currentMp: Int,
    val maxMp: Int,
    val speed: Int,
    val level: Int = 1,
    val xp: Int = 0,
    val spellIds: List<String> = emptyList()
) {
    companion object {
        fun fromPartyMember(member: PartyMember): SavedCharacterStats {
            return SavedCharacterStats(
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
                spellIds = member.spells.map { it.id }
            )
        }
    }
}

data class GameSaveData(
    val saveVersion: Int = 1,
    val saveTimestamp: Long = System.currentTimeMillis(),
    val timestamp: Long = saveTimestamp,

    // 1. Player Profile & Initial Loadout
    val player: PlayerCustomization = PlayerCustomization(),

    // 2. Story Progress & Current Scene/Dialogue
    val currentSceneId: String = "scene_act1_ch1_escalator",
    val currentNodeId: String = "act1_intro",

    // 3. Complete Decision History (every choice ever selected by player)
    val decisionsMade: List<String> = emptyList(),
    val narrativeFlags: Map<String, Boolean> = emptyMap(),
    val visitedNodeIds: List<String> = emptyList(),
    val recentDialogueLog: List<DialogueLogEntry> = emptyList(),

    // 4. Current Stats for all Party Members
    val partyStats: List<SavedCharacterStats> = emptyList(),

    // 5. Narrative Milestones
    val defeatedEncounters: List<String> = emptyList(),
    val unlockedCompanions: List<String> = emptyList(),
    val achievements: List<String> = emptyList(),
    val unlockedAchievements: List<String> = achievements,

    // 6. User Accessibility & Audio Options
    val isEyesFreeMode: Boolean = false,
    val isAutoListen: Boolean = false,
    val isAutoPlayEnabled: Boolean = false,
    val isChimeMuted: Boolean = true,
    val isNarrationEnabled: Boolean = true,
    val isCombatNarrationEnabled: Boolean = true,
    val isPocketGuardEnabled: Boolean = true,
    val isReadChoicesEnabled: Boolean = true,
    val isAutoReadHubChoices: Boolean = true,
    val speechRate: Float = 1.05f,
    val isCharacterPitchEnabled: Boolean = true,
    val isSpeakerAttributionEnabled: Boolean = true,
    val isMusicMuted: Boolean = false,
    val isMusicEnabled: Boolean = !isMusicMuted,
    val musicVolume: Float = 0.5f,
    val speechVolume: Float = 1.0f,

    // 7. Per-speaker TTS voice assignments (installed Android voice model names keyed by speaker id)
    val voiceAssignments: Map<String, String> = emptyMap(),

    // 8. Auto-Play & Dynamic Metrics
    val dynamicMetrics: Map<String, Float> = emptyMap(),

    // 9. Multilingual Localization Options
    val selectedLanguage: String = "en",
    val selectedRegion: String = ""
)
