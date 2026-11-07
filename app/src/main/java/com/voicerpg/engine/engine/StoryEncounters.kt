package com.voicerpg.engine.engine

import androidx.compose.ui.graphics.Color
import com.voicerpg.engine.model.BattleEnvironment
import com.voicerpg.engine.model.Enemy
import com.voicerpg.engine.model.EncounterDefinition
import com.voicerpg.engine.model.PartyMember
import com.voicerpg.engine.model.Spell
import com.voicerpg.engine.model.SpellSchool
import com.voicerpg.engine.template.TemplateHero

object StoryEncounters {

    // Party spell sets (single source of truth; mirrors docs/combat-design/data/spells.json)
    // Aethel's breath discipline: free, costs the turn, restores 35% of max MP regardless of
    // chant quality (flat by design - mana sustain must never multiply the resonance axis).
    val attuneSpell = Spell(
        "attune", "Attune", SpellSchool.PHYSICAL, basePower = 0, mpCost = 0,
        manaRestorePct = 0.35f,
        aliases = listOf("attune", "tune", "breathe", "breath", "concentrate", "center", "centre", "steady", "gather", "recover mana", "restore mana"),
        description = "Steady the inner ear; a third of your mana returns",
        exampleChant = "Attune"
    )

    val aethelSpells = listOf(
        Spell("fireball", "Fireball", SpellSchool.PYROMANCY, basePower = 65, mpCost = 15, status = "BURN", description = "Roaring sphere of flame", exampleChant = "Fireball archer"),
        Spell("frost_spike", "Frost Spike", SpellSchool.CRYOMANCY, basePower = 58, mpCost = 12, status = "CHILL", description = "Piercing icicle", exampleChant = "Glacial frost spike the orc!"),
        Spell("chain_lightning", "Chain Lightning", SpellSchool.ELECTROMANCY, basePower = 55, mpCost = 20, hitsAll = true, status = "OVERLOAD", description = "Arcing lightning storm", exampleChant = "Tempest lightning strike all enemies!"),
        attuneSpell
    )

    val steadyBreathSpell = Spell(
        "steady_breath", "Steady Breath", SpellSchool.PHYSICAL, basePower = 0, mpCost = 0, manaRestorePct = 0.35f,
        aliases = listOf("attune", "breathe", "breath", "steady", "concentrate", "center", "centre", "recover", "restore mana"),
        description = "Templar lung-drill; a third of your mana returns", exampleChant = "Steady breath"
    )

    val deepRootSpell = Spell(
        "deep_root", "Deep Root", SpellSchool.NATURE, basePower = 0, mpCost = 0, manaRestorePct = 0.35f,
        aliases = listOf("attune", "breathe", "breath", "deep root", "route", "still", "concentrate", "center", "centre", "recover", "restore mana"),
        description = "Draw the grove's slow cadence up through your roots", exampleChant = "Deep root, warden"
    )

    val quietLungsSpell = Spell(
        "quiet_lungs", "Quiet Lungs", SpellSchool.SHADOW, basePower = 0, mpCost = 0, manaRestorePct = 0.35f,
        aliases = listOf("attune", "breathe", "breath", "hush", "lunge", "steady", "concentrate", "center", "centre", "recover", "restore mana"),
        description = "Silence itself becomes a reservoir", exampleChant = "Quiet lungs"
    )

    val holySmiteSpell = Spell("holy_smite", "Holy Smite", SpellSchool.HOLY, basePower = 70, mpCost = 14, description = "Righteous celestial blow", exampleChant = "By celestial dawn, smite the heretic!")
    val layOnHandsSpell = Spell("lay_on_hands", "Lay on Hands", SpellSchool.HOLY, basePower = 110, mpCost = 16, isHeal = true, hitsAll = true, description = "Restorative blessing", exampleChant = "Sacred radiance mend Cedric's wounds!")
    val shieldWallSpell = Spell("shield_wall", "Shield Wall", SpellSchool.PHYSICAL, basePower = 50, mpCost = 10, hitsAll = true, isGuard = true, status = "GUARD", description = "Vanguard protection", exampleChant = "Raise the golden aegis against the horde!")
    val aegisDawnSpell = Spell("aegis_dawn", "Aegis of the Dawn", SpellSchool.HOLY, basePower = 150, mpCost = 25, isHeal = true, hitsAll = true, isGuard = true, status = "GUARD", description = "Radiant invulnerability barrier", exampleChant = "By celestial dawn, raise the morning star!")

    val cedricStarterSpells = listOf(holySmiteSpell, layOnHandsSpell, shieldWallSpell, steadyBreathSpell)
    val cedricSpells = listOf(holySmiteSpell, layOnHandsSpell, shieldWallSpell, aegisDawnSpell, steadyBreathSpell)

    val soothingRainSpell = Spell("soothing_rain", "Soothing Rain", SpellSchool.NATURE, basePower = 65, mpCost = 18, isHeal = true, hitsAll = true, description = "Grove restorative mist", exampleChant = "Spirits of the grove, grant soothing rain upon our party!")
    val briarEntangleSpell = Spell("briar_entangle", "Briar Entangle", SpellSchool.NATURE, basePower = 60, mpCost = 12, status = "ROOT", description = "Thorny vines snare the foe", exampleChant = "Thorny vines and briars ensnare that archer!")
    val verdantCataclysmSpell = Spell("verdant_cataclysm", "Verdant Cataclysm", SpellSchool.NATURE, basePower = 50, mpCost = 25, hitsAll = true, status = "POISON", description = "Cataclysmic grove-cry shook the whole field", exampleChant = "Ancient roots of the deep earth awaken!")

    val lyraStarterSpells = listOf(soothingRainSpell, briarEntangleSpell, deepRootSpell)
    val lyraSpells = listOf(soothingRainSpell, briarEntangleSpell, verdantCataclysmSpell, deepRootSpell)

    val shadowStrikeSpell = Spell("shadow_strike", "Shadow Strike", SpellSchool.SHADOW, basePower = 75, mpCost = 12, description = "Lethal strike from behind", exampleChant = "From the silent umbra, strike the shaman's throat!")
    val venomFlurrySpell = Spell("venom_flurry", "Venom Flurry", SpellSchool.SHADOW, basePower = 52, mpCost = 15, hitsAll = true, status = "POISON", description = "Poisoned twin daggers", exampleChant = "Abyssal venom coat my blades!")
    val umbralSiphonSpell = Spell("umbral_siphon", "Umbral Siphon", SpellSchool.SHADOW, basePower = 60, mpCost = 14, status = "CORRODE", lifesteal = true, aliases = listOf("siphon", "drain"), description = "Siphons shadow essence to mend wounds", exampleChant = "Abyssal shadow siphon drain the shaman!")

    val zephyrStarterSpells = listOf(shadowStrikeSpell, venomFlurrySpell, quietLungsSpell)
    val zephyrSpells = listOf(shadowStrikeSpell, venomFlurrySpell, umbralSiphonSpell, quietLungsSpell)

    val ALL_COMPANION_SPELLS: Map<String, Spell> = mapOf(
        holySmiteSpell.id to holySmiteSpell,
        layOnHandsSpell.id to layOnHandsSpell,
        shieldWallSpell.id to shieldWallSpell,
        aegisDawnSpell.id to aegisDawnSpell,
        steadyBreathSpell.id to steadyBreathSpell,
        soothingRainSpell.id to soothingRainSpell,
        briarEntangleSpell.id to briarEntangleSpell,
        verdantCataclysmSpell.id to verdantCataclysmSpell,
        deepRootSpell.id to deepRootSpell,
        shadowStrikeSpell.id to shadowStrikeSpell,
        venomFlurrySpell.id to venomFlurrySpell,
        umbralSiphonSpell.id to umbralSiphonSpell,
        quietLungsSpell.id to quietLungsSpell,
        // Commander America Kit
        TemplateHero.TREMENDOUS_STRIKE.id to TemplateHero.TREMENDOUS_STRIKE,
        TemplateHero.ART_OF_THE_DEAL.id to TemplateHero.ART_OF_THE_DEAL,
        TemplateHero.OBAMA_DID_IT_FIRST.id to TemplateHero.OBAMA_DID_IT_FIRST,
        TemplateHero.PRESIDENTIAL_VETO.id to TemplateHero.PRESIDENTIAL_VETO,
        TemplateHero.EXECUTIVE_ACTION.id to TemplateHero.EXECUTIVE_ACTION,
        TemplateHero.BUILD_THE_WALL.id to TemplateHero.BUILD_THE_WALL,
        TemplateHero.RALLY_THE_PATRIOTS.id to TemplateHero.RALLY_THE_PATRIOTS,
        TemplateHero.TIRED_OF_WINNING.id to TemplateHero.TIRED_OF_WINNING,
        // Marco Rubio Kit
        TemplateHero.FILIBUSTER_STRIKE.id to TemplateHero.FILIBUSTER_STRIKE,
        TemplateHero.MAXIMUM_SANCTIONS.id to TemplateHero.MAXIMUM_SANCTIONS,
        TemplateHero.WATER_SIP_RESTORE.id to TemplateHero.WATER_SIP_RESTORE,
        TemplateHero.TARIFF_DIVIDEND.id to TemplateHero.TARIFF_DIVIDEND,
        // JD Vance Kit
        TemplateHero.DEBATE_DISMANTLE.id to TemplateHero.DEBATE_DISMANTLE,
        TemplateHero.APPALACHIAN_SLAM.id to TemplateHero.APPALACHIAN_SLAM,
        TemplateHero.HILLBILLY_FORTITUDE.id to TemplateHero.HILLBILLY_FORTITUDE,
        TemplateHero.COMMON_SENSE_RENEWAL.id to TemplateHero.COMMON_SENSE_RENEWAL,
        // Kash Patel Kit
        TemplateHero.DECLASSIFY_NOW.id to TemplateHero.DECLASSIFY_NOW,
        TemplateHero.BINDER_OF_TRUTH.id to TemplateHero.BINDER_OF_TRUTH,
        TemplateHero.SECURITY_CLEARANCE.id to TemplateHero.SECURITY_CLEARANCE,
        TemplateHero.LEAK_PLUG.id to TemplateHero.LEAK_PLUG,
        // Senator John Kennedy Kit
        TemplateHero.PLAIN_ENGLISH_EXAM.id to TemplateHero.PLAIN_ENGLISH_EXAM,
        TemplateHero.CALL_AN_OFFICER.id to TemplateHero.CALL_AN_OFFICER,
        TemplateHero.COMMON_SENSE_REMEDY.id to TemplateHero.COMMON_SENSE_REMEDY,
        TemplateHero.DEVASTATING_ONELINER.id to TemplateHero.DEVASTATING_ONELINER
    )

    fun createAct1Party(): List<PartyMember> = listOf(
        TemplateHero.createTemplateAdventurer(id = "hero")
    )

    fun createAct2Party(): List<PartyMember> = listOf(
        TemplateHero.createTemplateAdventurer(id = "hero"),
        TemplateHero.createMarcoRubio()
    )

    fun createAct3Party(): List<PartyMember> = listOf(
        TemplateHero.createTemplateAdventurer(id = "hero"),
        TemplateHero.createMarcoRubio(),
        TemplateHero.createJDVance()
    )

    fun createAct4Party(): List<PartyMember> = listOf(
        TemplateHero.createTemplateAdventurer(id = "hero"),
        TemplateHero.createMarcoRubio(),
        TemplateHero.createJDVance(),
        TemplateHero.createKashPatel()
    )

    fun createFullAmericaParty(): List<PartyMember> = listOf(
        TemplateHero.createTemplateAdventurer(id = "hero"),
        TemplateHero.createMarcoRubio(),
        TemplateHero.createJDVance(),
        TemplateHero.createKashPatel(),
        TemplateHero.createJohnKennedy()
    )

    fun createDuoParty(): List<PartyMember> = listOf(
        PartyMember("hero", "Aethel", "Elementalist", currentHp = 240, maxHp = 240, currentMp = 140, maxMp = 140, spells = aethelSpells, avatarTint = Color(0xFF90CAF9), speed = 70, atbGauge = 0.45f),
        PartyMember("cedric", "Sir Cedric", "Templar", currentHp = 310, maxHp = 420, currentMp = 80, maxMp = 80, spells = cedricStarterSpells, avatarTint = Color(0xFFFFD54F), speed = 55, atbGauge = 0.30f)
    )

    fun createTrioParty(): List<PartyMember> = listOf(
        PartyMember("hero", "Aethel", "Elementalist", currentHp = 240, maxHp = 240, currentMp = 140, maxMp = 140, spells = aethelSpells, avatarTint = Color(0xFF90CAF9), speed = 70, atbGauge = 0.45f),
        PartyMember("cedric", "Sir Cedric", "Templar", currentHp = 310, maxHp = 420, currentMp = 80, maxMp = 80, spells = cedricStarterSpells, avatarTint = Color(0xFFFFD54F), speed = 55, atbGauge = 0.25f),
        PartyMember("lyra", "Lyra", "Grove Warden", currentHp = 240, maxHp = 280, currentMp = 120, maxMp = 120, spells = lyraStarterSpells, avatarTint = Color(0xFFA5D6A7), speed = 65, atbGauge = 0.35f)
    )

    fun createStandardParty(): List<PartyMember> = listOf(
        PartyMember("hero", "Aethel", "Elementalist", currentHp = 240, maxHp = 240, currentMp = 140, maxMp = 140, spells = aethelSpells, avatarTint = Color(0xFF90CAF9), speed = 70, atbGauge = 0.45f),
        PartyMember("cedric", "Sir Cedric", "Templar", currentHp = 310, maxHp = 420, currentMp = 80, maxMp = 80, spells = cedricSpells, avatarTint = Color(0xFFFFD54F), speed = 55, atbGauge = 0.25f),
        PartyMember("lyra", "Lyra", "Grove Warden", currentHp = 240, maxHp = 280, currentMp = 120, maxMp = 120, spells = lyraSpells, avatarTint = Color(0xFFA5D6A7), speed = 65, atbGauge = 0.35f),
        PartyMember("zephyr", "Zephyr", "Shadowblade", currentHp = 250, maxHp = 250, currentMp = 90, maxMp = 90, spells = zephyrSpells, avatarTint = Color(0xFFCE93D8), speed = 85, atbGauge = 0.55f)
    )

    fun createQuadParty(): List<PartyMember> = createStandardParty()

    fun createZephyrMember(): PartyMember = PartyMember(
        id = "zephyr",
        name = "Zephyr",
        loreClass = "Shadowblade",
        currentHp = 250,
        maxHp = 250,
        currentMp = 90,
        maxMp = 90,
        spells = zephyrStarterSpells,
        avatarTint = Color(0xFFCE93D8),
        speed = 85,
        atbGauge = 1.0f
    )

    // 1. Prologue Solo (1 Hero vs 2 Enemies)
    val PROLOGUE_SOLO = EncounterDefinition(
        id = "prologue_solo",
        name = "Prologue: Whispers in the Fog",
        description = "Aethel awakens alone in the mist-veiled forest as shadowy wisps converge.",
        environment = BattleEnvironment.FOREST,
        enemies = listOf(
            Enemy("wisp_1", "Shadow Wisp Alpha", "Phantom", currentHp = 160, maxHp = 160, baseAttack = 14, isTargeted = true, spriteTint = Color(0xFF81D4FA), speed = 60, atbGauge = 0.35f),
            Enemy("wisp_2", "Shadow Wisp Beta", "Phantom", currentHp = 140, maxHp = 140, baseAttack = 12, isTargeted = false, spriteTint = Color(0xFFB39DDB), speed = 50, atbGauge = 0.15f)
        ),
        initialParty = listOf(
            PartyMember("hero", "Aethel", "Elementalist", currentHp = 240, maxHp = 240, currentMp = 140, maxMp = 140, spells = aethelSpells, avatarTint = Color(0xFF90CAF9), speed = 75, atbGauge = 0.50f)
        )
    )

    // 2. Forest Ambush (2 Heroes vs 3 Enemies)
    val FOREST_AMBUSH = EncounterDefinition(
        id = "forest_ambush",
        name = "Chapter 1: The Old Way Shrine",
        description = "Sir Cedric joins Aethel to repel a blighted hunting party at the ancient shrine.",
        environment = BattleEnvironment.FOREST,
        enemies = listOf(
            Enemy("orc", "Blighted Orc", "Vanguard", currentHp = 340, maxHp = 340, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFFEF5350), speed = 50, atbGauge = 0.35f),
            Enemy("archer", "Corrupted Archer", "Sniper", currentHp = 240, maxHp = 240, baseAttack = 25, isTargeted = true, spriteTint = Color(0xFFAB47BC), speed = 65, atbGauge = 0.45f),
            Enemy("shaman", "Void Shaman", "Occultist", currentHp = 280, maxHp = 280, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF5C6BC0), speed = 55, atbGauge = 0.30f)
        ),
        initialParty = listOf(
            PartyMember("hero", "Aethel", "Elementalist", currentHp = 240, maxHp = 240, currentMp = 140, maxMp = 140, spells = aethelSpells, avatarTint = Color(0xFF90CAF9), speed = 70, atbGauge = 0.45f),
            PartyMember("cedric", "Sir Cedric", "Templar", currentHp = 310, maxHp = 420, currentMp = 80, maxMp = 80, spells = cedricStarterSpells, avatarTint = Color(0xFFFFD54F), speed = 55, atbGauge = 0.30f)
        )
    )

    // 3. Dungeon Descent (2 Heroes vs 3 Enemies with Mid-Battle Reinforcements!)
    val DUNGEON_DESCENT = EncounterDefinition(
        id = "dungeon_descent",
        name = "Chapter 4: Crypt of the Restless",
        description = "Deep in the forgotten catacombs, a Bone Acolyte commands skeletal legionnaires.",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("bone_knight", "Crypt Guard", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFCFD8DC), speed = 48, atbGauge = 0.30f),
            Enemy("bone_archer", "Skeletal Sniper", "Sniper", currentHp = 220, maxHp = 220, baseAttack = 26, isTargeted = true, spriteTint = Color(0xFFB0BEC5), speed = 68, atbGauge = 0.40f),
            Enemy("bone_acolyte", "Bone Acolyte", "Summoner", currentHp = 320, maxHp = 320, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF7E57C2), speed = 52, atbGauge = 0.20f)
        ),
        initialParty = createDuoParty()
    )

    // 4. Full 6-Enemy Horde Siege in Castle (2 Heroes vs 6 Enemies)
    val CASTLE_HORDE = EncounterDefinition(
        id = "castle_horde",
        name = "Chapter 4: The Solaria Bell Chamber",
        description = "The fellowship clashes against the royal vanguard garrison on the belfry parapets.",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            // Front Row (Indices 0, 2, 4)
            Enemy("ironclad_1", "Iron Vanguard Alpha", "Vanguard", currentHp = 320, maxHp = 320, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF90A4AE), speed = 48, atbGauge = 0.20f),
            Enemy("ironclad_2", "Iron Vanguard Beta", "Vanguard", currentHp = 320, maxHp = 320, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF78909C), speed = 52, atbGauge = 0.25f),
            Enemy("gate_captain", "Gate Captain", "Vanguard", currentHp = 380, maxHp = 380, baseAttack = 25, isTargeted = true, spriteTint = Color(0xFFE57373), speed = 55, atbGauge = 0.35f),
            // Back Row (Indices 1, 3, 5)
            Enemy("sniper_1", "Castle Arbalest A", "Sniper", currentHp = 210, maxHp = 210, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFBA68C8), speed = 66, atbGauge = 0.45f),
            Enemy("sniper_2", "Castle Arbalest B", "Sniper", currentHp = 210, maxHp = 210, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFAB47BC), speed = 64, atbGauge = 0.40f),
            Enemy("court_mage", "Court Warlock", "Occultist", currentHp = 260, maxHp = 260, baseAttack = 28, isTargeted = false, spriteTint = Color(0xFF3F51B5), speed = 58, atbGauge = 0.30f)
        ),
        initialParty = createDuoParty()
    )

    // 5. Cave Broodmother Boss Encounter (2 Heroes vs Boss + Mid-Fight Minion Summons)
    val CAVE_BROODMOTHER = EncounterDefinition(
        id = "cave_broodmother",
        name = "Boss Trial: The Chittering Queen",
        description = "In the subterranean abyss, the Broodmother summons toxic hatchlings whenever threatened.",
        environment = BattleEnvironment.CAVE,
        enemies = listOf(
            Enemy("broodmother", "Grave Broodmother", "Boss Summoner", currentHp = 780, maxHp = 780, baseAttack = 32, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFF5722), speed = 52, atbGauge = 0.40f)
        ),
        initialParty = createDuoParty()
    )

    // 6. Marsh Rescue: The Drowned Fane (Duo Party vs Void Briar Binder & Wardens)
    val MARSH_RESCUE = EncounterDefinition(
        id = "marsh_rescue",
        name = "Chapter 5: The Drowned Fane Rescue",
        description = "Aethel and Sir Cedric assault the corrupted wardens guarding Lyra's void-briar containment cage.",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("mire_ironclad", "Bog Ironclad", "Vanguard", currentHp = 380, maxHp = 380, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF558B2F), speed = 46, atbGauge = 0.25f),
            Enemy("mire_stalker", "Mire Stalker", "Sniper", currentHp = 220, maxHp = 220, baseAttack = 26, isTargeted = true, spriteTint = Color(0xFF7CB342), speed = 66, atbGauge = 0.40f),
            Enemy("void_binder", "Void Briar Binder", "Occultist", currentHp = 340, maxHp = 340, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF6A1B9A), speed = 52, atbGauge = 0.20f)
        ),
        initialParty = createDuoParty()
    )

    // 7. Swamp Encounter: The Weeping Willow Sanctuary (Trio Party vs Bog Behemoth Boss)
    val SWAMP_BEHEMOTH = EncounterDefinition(
        id = "swamp_behemoth",
        name = "Chapter 6: The Weeping Willow Sanctuary",
        description = "Lyra joins the fellowship to cleanse the colossal Weeping Willow of the corrupting Bog Behemoth.",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("behemoth", "Bog Behemoth", "Vanguard", currentHp = 540, maxHp = 540, baseAttack = 28, isBoss = true, isTargeted = true, spriteTint = Color(0xFF66BB6A), speed = 40, atbGauge = 0.25f),
            Enemy("leech_1", "Marsh Leech Alpha", "Sniper", currentHp = 170, maxHp = 170, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF81C784), speed = 68, atbGauge = 0.40f),
            Enemy("leech_2", "Marsh Leech Beta", "Sniper", currentHp = 170, maxHp = 170, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFFAED581), speed = 64, atbGauge = 0.35f)
        ),
        initialParty = listOf(
            PartyMember("hero", "Aethel", "Elementalist", currentHp = 240, maxHp = 240, currentMp = 140, maxMp = 140, spells = aethelSpells, avatarTint = Color(0xFF90CAF9), speed = 70, atbGauge = 0.45f),
            PartyMember("cedric", "Sir Cedric", "Templar", currentHp = 310, maxHp = 420, currentMp = 80, maxMp = 80, spells = cedricStarterSpells, avatarTint = Color(0xFFFFD54F), speed = 55, atbGauge = 0.25f),
            PartyMember("lyra", "Lyra", "Grove Warden", currentHp = 240, maxHp = 280, currentMp = 120, maxMp = 120, spells = lyraStarterSpells, avatarTint = Color(0xFFA5D6A7), speed = 65, atbGauge = 0.35f)
        )
    )

    // 7. Camp Reconnaissance: Blighted Trackers (Cedric + Aethel vs 2 Prowlers)
    val BLIGHT_TRACKERS = EncounterDefinition(
        id = "blight_trackers",
        name = "Camp Reconnaissance: The Blighted Prowlers",
        description = "Cedric and Aethel ambush two corrupted trackers stalking the camp perimeter.",
        environment = BattleEnvironment.FOREST,
        enemies = listOf(
            Enemy("tracker_1", "Obsidian Tracker", "Sniper", currentHp = 220, maxHp = 220, baseAttack = 18, isTargeted = true, spriteTint = Color(0xFFCE93D8), speed = 62, atbGauge = 0.35f),
            Enemy("tracker_2", "Blighted Stalker", "Vanguard", currentHp = 280, maxHp = 280, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFFEF5350), speed = 54, atbGauge = 0.30f)
        ),
        initialParty = listOf(
            PartyMember("hero", "Aethel", "Elementalist", currentHp = 240, maxHp = 240, currentMp = 140, maxMp = 140, spells = aethelSpells, avatarTint = Color(0xFF90CAF9), speed = 70, atbGauge = 0.45f),
            PartyMember("cedric", "Sir Cedric", "Templar", currentHp = 310, maxHp = 420, currentMp = 80, maxMp = 80, spells = cedricStarterSpells, avatarTint = Color(0xFFFFD54F), speed = 55, atbGauge = 0.30f)
        )
    )

    // 8. Aqueduct Reconnaissance: Corrupted Sentinels (Cedric + Aethel vs 2 Sentinels)
    val CH3_SENTINELS = EncounterDefinition(
        id = "ch3_sentinels",
        name = "Chapter 3: The Corrupted Sentinels",
        description = "Cedric and Aethel engage the razor-clawed sentinels guarding the aqueduct perimeter.",
        environment = BattleEnvironment.CAVE,
        enemies = listOf(
            Enemy("sentinel_1", "Aqueduct Sentinel", "Vanguard", currentHp = 260, maxHp = 260, baseAttack = 22, isTargeted = true, spriteTint = Color(0xFFEF5350), speed = 58, atbGauge = 0.35f),
            Enemy("sentinel_2", "Brood Stalker", "Sniper", currentHp = 210, maxHp = 210, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFAB47BC), speed = 66, atbGauge = 0.35f)
        ),
        initialParty = listOf(
            PartyMember("hero", "Aethel", "Elementalist", currentHp = 240, maxHp = 240, currentMp = 140, maxMp = 140, spells = aethelSpells, avatarTint = Color(0xFF90CAF9), speed = 70, atbGauge = 0.45f),
            PartyMember("cedric", "Sir Cedric", "Templar", currentHp = 310, maxHp = 420, currentMp = 80, maxMp = 80, spells = cedricStarterSpells, avatarTint = Color(0xFFFFD54F), speed = 55, atbGauge = 0.30f)
        )
    )

    // 9. Chapter 7: Guardian of the Silt Core (Trio Party vs Mire Wyrm + Silt Ghouls)
    val CH7_MIRE_WYRM = EncounterDefinition(
        id = "ch7_mire_wyrm",
        name = "Chapter 7: Guardian of the Silt Core",
        description = "Lyra strikes her botanical tuning fork, provoking the ancient silt wyrm guarding the submerged catacomb entrance.",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("mire_wyrm", "Ancient Mire Wyrm", "Boss Behemoth", currentHp = 580, maxHp = 580, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFF689F38), speed = 44, atbGauge = 0.25f),
            Enemy("ghoul_1", "Silt Ghoul Alpha", "Vanguard", currentHp = 160, maxHp = 160, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF8D6E63), speed = 58, atbGauge = 0.35f),
            Enemy("ghoul_2", "Silt Ghoul Beta", "Sniper", currentHp = 160, maxHp = 160, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFFA1887F), speed = 56, atbGauge = 0.30f),
            Enemy("ghoul_3", "Silt Ghoul Gamma", "Vanguard", currentHp = 160, maxHp = 160, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFFBCAAA4), speed = 54, atbGauge = 0.20f)
        ),
        initialParty = createTrioParty()
    )

    // 10. Chapter 8: The Shadowed Crags Ambush (Trio begins; Zephyr defects mid-battle!)
    val CH8_EXECUTIONER_AMBUSH = EncounterDefinition(
        id = "ch8_executioner_ambush",
        name = "Chapter 8: The Shadowed Crags Ambush",
        description = "Grand Executioner Kaelen and the Inquisition shadowblades spring a lethal trap in the rocky pass.",
        environment = BattleEnvironment.CAVE,
        enemies = listOf(
            Enemy("kaelen", "Executioner Kaelen", "Boss Assassin", currentHp = 640, maxHp = 640, baseAttack = 28, isBoss = true, isTargeted = true, spriteTint = Color(0xFFD32F2F), speed = 66, atbGauge = 0.35f),
            Enemy("blade_1", "Shadowblade Alpha", "Sniper", currentHp = 200, maxHp = 200, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF7E57C2), speed = 76, atbGauge = 0.45f),
            Enemy("blade_2", "Shadowblade Beta", "Sniper", currentHp = 200, maxHp = 200, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF9575CD), speed = 72, atbGauge = 0.40f)
        ),
        initialParty = createTrioParty()
    )

    // 11. Chapter 9: Sir Cedric's Required Trial — The Broken Vow of Dawn
    val CH9_GALAHAULT_TRIAL = EncounterDefinition(
        id = "ch9_galahault_trial",
        name = "Chapter 9: The Broken Vow of Dawn",
        description = "Cedric faces the petrified spirit of Grandmaster Galahault in the Mausoleum of the Sun.",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("galahault", "Sir Galahault", "Boss Paladin", currentHp = 750, maxHp = 750, baseAttack = 30, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFB300), speed = 52, atbGauge = 0.35f),
            Enemy("penitent_1", "Penitent Templar", "Vanguard", currentHp = 280, maxHp = 280, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFFB0BEC5), speed = 48, atbGauge = 0.25f),
            Enemy("penitent_2", "Chantry Cleric", "Occultist", currentHp = 240, maxHp = 240, baseAttack = 16, isTargeted = false, spriteTint = Color(0xFF81D4FA), speed = 60, atbGauge = 0.30f)
        ),
        initialParty = createQuadParty()
    )

    // 12. Chapter 10: Lyra's Required Trial — The Song of the Mute Grove
    val CH10_BROODMOTHER_TRIAL = EncounterDefinition(
        id = "ch10_broodmother_trial",
        name = "Chapter 10: The Song of the Mute Grove",
        description = "Lyra and the fellowship cleanse the corrupted obsidian silt choking the Emerald Choir spring.",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("broodmother", "Blighted Broodmother", "Boss Summoner", currentHp = 760, maxHp = 760, baseAttack = 30, isBoss = true, isTargeted = true, spriteTint = Color(0xFF43A047), speed = 54, atbGauge = 0.35f),
            Enemy("spider_1", "Corrupted Webweaver", "Sniper", currentHp = 180, maxHp = 180, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF81C784), speed = 68, atbGauge = 0.40f),
            Enemy("spider_2", "Toxic Hatchling", "Sniper", currentHp = 180, maxHp = 180, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFFAED581), speed = 65, atbGauge = 0.35f)
        ),
        initialParty = createQuadParty()
    )

    // 13. Chapter 11: Zephyr's Required Trial — The Silent Blade's Reckoning
    val CH11_NOCTURNE_TRIAL = EncounterDefinition(
        id = "ch11_nocturne_trial",
        name = "Chapter 11: The Silent Blade's Reckoning",
        description = "Zephyr faces his former master, Nocturne, in the misty depths of the Blind Gorge.",
        environment = BattleEnvironment.CAVE,
        enemies = listOf(
            Enemy("nocturne", "Master Nocturne", "Boss Assassin", currentHp = 780, maxHp = 780, baseAttack = 34, isBoss = true, isTargeted = true, spriteTint = Color(0xFF4A148C), speed = 88, atbGauge = 0.45f),
            Enemy("assassin_1", "Black Guild Stalker", "Sniper", currentHp = 220, maxHp = 220, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF9575CD), speed = 78, atbGauge = 0.35f),
            Enemy("assassin_2", "Umbral Cutthroat", "Sniper", currentHp = 220, maxHp = 220, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFB39DDB), speed = 74, atbGauge = 0.30f)
        ),
        initialParty = createQuadParty()
    )

    // 14. Chapter 12: Awakening the Third Bell (The Iron Belfry of Ouros)
    val CH12_WARMASTER_OUROS = EncounterDefinition(
        id = "ch12_warmaster_ouros",
        name = "Chapter 12: The Iron Belfry of Ouros",
        description = "The 4-hero fellowship storms the clockwork belfry to defeat Warmaster Ouros and chime Bell 3.",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            // Front Row
            Enemy("phalanx_1", "Clockwork Phalanx A", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF78909C), speed = 45, atbGauge = 0.20f),
            Enemy("phalanx_2", "Clockwork Phalanx B", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF607D8B), speed = 48, atbGauge = 0.25f),
            // Back Row
            Enemy("warmaster", "Warmaster Ouros", "Boss Automaton", currentHp = 880, maxHp = 880, baseAttack = 32, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFF9800), speed = 50, atbGauge = 0.35f),
            Enemy("arbalest_1", "Steam Arbalest A", "Sniper", currentHp = 230, maxHp = 230, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFFB0BEC5), speed = 65, atbGauge = 0.40f),
            Enemy("arbalest_2", "Steam Arbalest B", "Sniper", currentHp = 230, maxHp = 230, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFFCFD8DC), speed = 63, atbGauge = 0.35f)
        ),
        initialParty = createQuadParty()
    )

    // 15. Chapter 13: Breach of the Silent Citadel
    val CH13_COMMANDER_VAELOR = EncounterDefinition(
        id = "ch13_commander_vaelor",
        name = "Chapter 13: Breach of the Silent Citadel",
        description = "Commander Vaelor sounds the Void Horn to summon spectral legions before the capital gates.",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            // Front Row
            Enemy("sentinel_1", "Citadel Ironclad", "Vanguard", currentHp = 380, maxHp = 380, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF546E7A), speed = 48, atbGauge = 0.20f),
            Enemy("sentinel_2", "Obsidian Warden", "Vanguard", currentHp = 380, maxHp = 380, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF37474F), speed = 46, atbGauge = 0.25f),
            // Back Row
            Enemy("vaelor", "Commander Vaelor", "Boss Vanguard", currentHp = 980, maxHp = 980, baseAttack = 35, isBoss = true, isTargeted = true, spriteTint = Color(0xFFE53935), speed = 64, atbGauge = 0.35f),
            Enemy("reaper_1", "Spectral Reaper A", "Sniper", currentHp = 240, maxHp = 240, baseAttack = 28, isTargeted = false, spriteTint = Color(0xFF8E24AA), speed = 72, atbGauge = 0.40f),
            Enemy("reaper_2", "Spectral Reaper B", "Sniper", currentHp = 240, maxHp = 240, baseAttack = 28, isTargeted = false, spriteTint = Color(0xFFAB47BC), speed = 70, atbGauge = 0.35f)
        ),
        initialParty = createQuadParty()
    )

    // 16. Chapter 14: The Void Reservoir
    val CH14_ABYSSAL_LEVIATHAN = EncounterDefinition(
        id = "ch14_abyssal_leviathan",
        name = "Chapter 14: The Void Reservoir",
        description = "The Abyssal Leviathan rises from the lake of liquid silence, enveloping the arena in a sound-draining aura.",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("leviathan", "Abyssal Leviathan", "Boss Leviathan", currentHp = 1050, maxHp = 1050, baseAttack = 36, isBoss = true, isTargeted = true, spriteTint = Color(0xFF00695C), speed = 42, atbGauge = 0.25f),
            Enemy("tendril_1", "Void Tendril Alpha", "Sniper", currentHp = 220, maxHp = 220, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF00897B), speed = 62, atbGauge = 0.35f),
            Enemy("tendril_2", "Void Tendril Beta", "Sniper", currentHp = 220, maxHp = 220, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF26A69A), speed = 60, atbGauge = 0.30f),
            Enemy("tendril_3", "Void Tendril Gamma", "Sniper", currentHp = 220, maxHp = 220, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF80CBC4), speed = 58, atbGauge = 0.25f)
        ),
        initialParty = createQuadParty()
    )

    // 17. Chapter 15: Ascent of the Celestial Spire
    val CH15_ARCHON_CUSTODIANS = EncounterDefinition(
        id = "ch15_archon_custodians",
        name = "Chapter 15: Ascent of the Celestial Spire",
        description = "Golden archon custodians bar the stairway of solidified harmonic light.",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("custodian_1", "Archon Custodian A", "Vanguard", currentHp = 440, maxHp = 440, baseAttack = 30, isTargeted = true, spriteTint = Color(0xFFFFCA28), speed = 66, atbGauge = 0.35f),
            Enemy("custodian_2", "Archon Custodian B", "Vanguard", currentHp = 440, maxHp = 440, baseAttack = 30, isTargeted = false, spriteTint = Color(0xFFFFD54F), speed = 64, atbGauge = 0.30f),
            Enemy("spire_1", "Celestial Spire Alpha", "Occultist", currentHp = 320, maxHp = 320, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF80DEEA), speed = 55, atbGauge = 0.25f),
            Enemy("spire_2", "Celestial Spire Beta", "Occultist", currentHp = 320, maxHp = 320, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFFB2EBF2), speed = 52, atbGauge = 0.20f)
        ),
        initialParty = createQuadParty()
    )

    // 18. Chapter 16: The Primordial Syllable — Grand Finale (Grand Inquisitor Malakor)
    val CH16_MALAKOR_FINALE = EncounterDefinition(
        id = "ch16_malakor_finale",
        name = "Chapter 16: The Primordial Syllable",
        description = "The final confrontation against Grand Inquisitor Malakor, The Mute Sovereign, high above the cosmos.",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("malakor", "Grand Inquisitor Malakor", "The Mute Sovereign", currentHp = 1350, maxHp = 1350, baseAttack = 42, isBoss = true, isTargeted = true, spriteTint = Color(0xFFB71C1C), speed = 68, atbGauge = 0.40f),
            Enemy("nullifier_1", "Echo Nullifier I", "Sniper", currentHp = 200, maxHp = 200, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF78909C), speed = 56, atbGauge = 0.30f),
            Enemy("nullifier_2", "Echo Nullifier II", "Sniper", currentHp = 200, maxHp = 200, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF90A4AE), speed = 54, atbGauge = 0.25f),
            Enemy("nullifier_3", "Echo Nullifier III", "Sniper", currentHp = 200, maxHp = 200, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFFB0BEC5), speed = 52, atbGauge = 0.20f),
            Enemy("nullifier_4", "Echo Nullifier IV", "Sniper", currentHp = 200, maxHp = 200, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFFCFD8DC), speed = 50, atbGauge = 0.15f)
        ),
        initialParty = createQuadParty()
    )

    // 19. Chapter 9: Threshold Guardian of the Sun's Penitence
    val CH9_PENITENT_GATE = EncounterDefinition(
        id = "ch9_penitent_gate",
        name = "Chapter 9: Gate of the Sun's Penitence",
        description = "Vowbreakers entombed alive at the mausoleum gate rise to bar the way to Galahault's crypt.",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("vowbreaker_1", "Penitent Vowbreaker A", "Vanguard", currentHp = 320, maxHp = 320, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF90A4AE), speed = 46, atbGauge = 0.20f),
            Enemy("vowbreaker_2", "Penitent Vowbreaker B", "Vanguard", currentHp = 320, maxHp = 320, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF78909C), speed = 44, atbGauge = 0.25f),
            Enemy("gate_archon", "Grave Archon", "Occultist", currentHp = 300, maxHp = 300, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFFFFB300), speed = 58, atbGauge = 0.30f)
        ),
        initialParty = createQuadParty()
    )

    // 20. Chapter 10: Guardians of the Poisoned Thicket
    val CH10_THICKET_GUARDIANS = EncounterDefinition(
        id = "ch10_thicket_guardians",
        name = "Chapter 10: Guardians of the Poisoned Thicket",
        description = "Glass-vined colossi infected by the same obsidian venom that froze the Emerald Choir.",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("thicket_1", "Glassvine Colossus A", "Vanguard", currentHp = 340, maxHp = 340, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF66BB6A), speed = 44, atbGauge = 0.20f),
            Enemy("thicket_2", "Glassvine Colossus B", "Vanguard", currentHp = 340, maxHp = 340, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF43A047), speed = 46, atbGauge = 0.25f),
            Enemy("thorn_singer", "Thorn Choir Singer", "Occultist", currentHp = 280, maxHp = 280, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF2E7D32), speed = 56, atbGauge = 0.30f)
        ),
        initialParty = createQuadParty()
    )

    // 21. Chapter 11: Gorge Stalker Skirmish
    val CH11_GORGE_STALKERS = EncounterDefinition(
        id = "ch11_gorge_stalkers",
        name = "Chapter 11: The Gorge Stalker Skirmish",
        description = "Nocturne's masked assassins spring from the culverts of the Blind Gorge.",
        environment = BattleEnvironment.CAVE,
        enemies = listOf(
            Enemy("stalker_1", "Black Guild Stalker A", "Sniper", currentHp = 260, maxHp = 260, baseAttack = 24, isTargeted = true, spriteTint = Color(0xFF7E57C2), speed = 76, atbGauge = 0.40f),
            Enemy("stalker_2", "Black Guild Stalker B", "Sniper", currentHp = 260, maxHp = 260, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF9575CD), speed = 72, atbGauge = 0.35f),
            Enemy("garrote", "Guild Garrote", "Vanguard", currentHp = 320, maxHp = 320, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFF512DA8), speed = 58, atbGauge = 0.25f)
        ),
        initialParty = createQuadParty()
    )

    // 22. Chapter 11: Obsidian Archive Enforcers
    val CH11_ARCHIVE_ENFORCERS = EncounterDefinition(
        id = "ch11_archive_enforcers",
        name = "Chapter 11: The Obsidian Archive",
        description = "The guild's voiceless wardens defend the vault of stolen voices beneath the gorge.",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("enforcer_1", "Archive Enforcer I", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF455A64), speed = 46, atbGauge = 0.35f),
            Enemy("enforcer_2", "Archive Enforcer II", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF37474F), speed = 44, atbGauge = 0.35f),
            Enemy("enforcer_3", "Archive Enforcer III", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF263238), speed = 48, atbGauge = 0.30f),
            Enemy("vault_watcher", "Vault Watcher", "Occultist", currentHp = 300, maxHp = 300, baseAttack = 20, isTargeted = true, spriteTint = Color(0xFF4A148C), speed = 62, atbGauge = 0.45f)
        ),
        initialParty = createQuadParty()
    )

    // 23. Chapter 12: Automaton Patrol of the Ironworks
    val CH12_AUTOMATON_PATROL = EncounterDefinition(
        id = "ch12_automaton_patrol",
        name = "Chapter 12: The Automatic Watch",
        description = "Ouros's iron warden-automata converge across the catwalks of the Clockwork Bastion.",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("warden_1", "Iron Warden Alpha", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF78909C), speed = 44, atbGauge = 0.20f),
            Enemy("warden_2", "Iron Warden Beta", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF607D8B), speed = 46, atbGauge = 0.25f),
            Enemy("tripod", "Tripod Scorcher", "Sniper", currentHp = 280, maxHp = 280, baseAttack = 26, isTargeted = true, spriteTint = Color(0xFFFF9800), speed = 64, atbGauge = 0.35f)
        ),
        initialParty = createQuadParty()
    )

    // 24. Chapter 13: Legion of the Pilgrim's Plaza
    val CH13_PLAZA_LEGION = EncounterDefinition(
        id = "ch13_plaza_legion",
        name = "Chapter 13: The Pilgrim's Plaza Legion",
        description = "Vaelor's obsidian garrison stands ordered and waiting in the lamp-lit plaza of Sol-Aethel.",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("legionnaire_1", "Plaza Legionnaire A", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF546E7A), speed = 46, atbGauge = 0.20f),
            Enemy("legionnaire_2", "Plaza Legionnaire B", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF455A64), speed = 48, atbGauge = 0.25f),
            Enemy("legionnaire_3", "Plaza Legionnaire C", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF37474F), speed = 44, atbGauge = 0.15f),
            Enemy("obelisks", "Walking Obelisk", "Occultist", currentHp = 320, maxHp = 320, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF6A1B9A), speed = 56, atbGauge = 0.30f)
        ),
        initialParty = createQuadParty()
    )

    // 25. Chapter 14: Husks of the Silent Shallows
    val CH14_SHALLOWS_HUSKS = EncounterDefinition(
        id = "ch14_shallows_husks",
        name = "Chapter 14: Husks of the Silent Shallows",
        description = "Drowned pilgrims, their voices swallowed by the reservoir, drag themselves ashore to guard the lake.",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("husk_1", "Drowned Husk I", "Vanguard", currentHp = 330, maxHp = 330, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF00695C), speed = 46, atbGauge = 0.20f),
            Enemy("husk_2", "Drowned Husk II", "Vanguard", currentHp = 330, maxHp = 330, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF00897B), speed = 48, atbGauge = 0.25f),
            Enemy("husk_3", "Shrine Grasper", "Sniper", currentHp = 260, maxHp = 260, baseAttack = 20, isTargeted = true, spriteTint = Color(0xFF26A69A), speed = 62, atbGauge = 0.35f)
        ),
        initialParty = createQuadParty()
    )

    // 26. Chapter 14: Leeches of the Umbral Trench
    val CH14_TRENCH_LEECHES = EncounterDefinition(
        id = "ch14_trench_leeches",
        name = "Chapter 14: The Umbral Trench",
        description = "Blind trench leeches, each knotted with stolen voices, feed the Abyssal Leviathan's silence.",
        environment = BattleEnvironment.CAVE,
        enemies = listOf(
            Enemy("leech_1", "Umbral Leech I", "Vanguard", currentHp = 340, maxHp = 340, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF4B0082), speed = 40, atbGauge = 0.15f),
            Enemy("leech_2", "Umbral Leech II", "Vanguard", currentHp = 340, maxHp = 340, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF5C6BC0), speed = 42, atbGauge = 0.20f),
            Enemy("leech_3", "Chorus Leech", "Sniper", currentHp = 280, maxHp = 280, baseAttack = 26, isTargeted = true, spriteTint = Color(0xFF7E57C2), speed = 60, atbGauge = 0.30f),
            Enemy("leech_4", "Mote Drinker", "Occultist", currentHp = 300, maxHp = 300, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF9C27B0), speed = 54, atbGauge = 0.25f)
        ),
        initialParty = createQuadParty()
    )

    // 27. Chapter 15: Trial of the Gate of Gold
    val CH15_TRIAL_GOLD = EncounterDefinition(
        id = "ch15_trial_gold",
        name = "Chapter 15: Trial of the Gate of Gold",
        description = "Gilded reflections of every oath bought and bent tried to buy Sir Cedric's vow back.",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("merchant_wraith", "Gilded Creditor", "Occultist", currentHp = 320, maxHp = 320, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFFFFB300), speed = 58, atbGauge = 0.30f),
            Enemy("clad_1", "Glinted Vow Clade A", "Vanguard", currentHp = 340, maxHp = 340, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFFFC107), speed = 48, atbGauge = 0.25f),
            Enemy("clad_2", "Glinted Vow Clade B", "Vanguard", currentHp = 340, maxHp = 340, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFFFD54F), speed = 46, atbGauge = 0.20f),
            Enemy("clad_3", "Glinted Vow Clade C", "Sniper", currentHp = 300, maxHp = 300, baseAttack = 22, isTargeted = true, spriteTint = Color(0xFFFFE082), speed = 62, atbGauge = 0.35f)
        ),
        initialParty = createQuadParty()
    )

    // 28. Chapter 15: Trial of the Gate of the Grove
    val CH15_TRIAL_GROVE = EncounterDefinition(
        id = "ch15_trial_grove",
        name = "Chapter 15: Trial of the Gate of the Grove",
        description = "Thorn-vined guardians woven from Lyra's day of flight bar the stair of the grove.",
        environment = BattleEnvironment.FOREST,
        enemies = listOf(
            Enemy("thorn_1", "Thornvined Guardian A", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF66BB6A), speed = 46, atbGauge = 0.20f),
            Enemy("thorn_2", "Thornvined Guardian B", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF43A047), speed = 44, atbGauge = 0.15f),
            Enemy("thorn_3", "Root Lash", "Sniper", currentHp = 300, maxHp = 300, baseAttack = 22, isTargeted = true, spriteTint = Color(0xFF2E7D32), speed = 60, atbGauge = 0.25f),
            Enemy("thorn_4", "Canopy Warden", "Occultist", currentHp = 320, maxHp = 320, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF1B5E20), speed = 54, atbGauge = 0.30f)
        ),
        initialParty = createQuadParty()
    )

    // 29. Chapter 15: Trial of the Gate of Shadow
    val CH15_TRIAL_SHADOW = EncounterDefinition(
        id = "ch15_trial_shadow",
        name = "Chapter 15: Trial of the Gate of Shadow",
        description = "Every lesson of Nocturne made assassin has risen to claim Zephyr's place in the dark.",
        environment = BattleEnvironment.CAVE,
        enemies = listOf(
            Enemy("shade_1", "Nocturne Lesson I", "Sniper", currentHp = 300, maxHp = 300, baseAttack = 24, isTargeted = true, spriteTint = Color(0xFF4A148C), speed = 74, atbGauge = 0.40f),
            Enemy("shade_2", "Nocturne Lesson II", "Sniper", currentHp = 300, maxHp = 300, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF6A1B9A), speed = 70, atbGauge = 0.35f),
            Enemy("shade_3", "Umbral Ghost", "Vanguard", currentHp = 340, maxHp = 340, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFF311B92), speed = 52, atbGauge = 0.25f),
            Enemy("shade_4", "Echo of the Guild", "Occultist", currentHp = 300, maxHp = 300, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF4527A0), speed = 58, atbGauge = 0.30f)
        ),
        initialParty = createQuadParty()
    )

    // 30. Chapter 16: The Echo Nullifier Gate
    val CH16_NULLIFIER_GATE = EncounterDefinition(
        id = "ch16_nullifier_gate",
        name = "Chapter 16: The Echo Nullifier Gate",
        description = "Malakor's last gatekeepers step from the four shattered pillars of the Vestibule of Echoes.",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("null_gate_1", "Nullifier Warden I", "Vanguard", currentHp = 380, maxHp = 380, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFF455A64), speed = 48, atbGauge = 0.20f),
            Enemy("null_gate_2", "Nullifier Warden II", "Vanguard", currentHp = 380, maxHp = 380, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFF37474F), speed = 46, atbGauge = 0.25f),
            Enemy("null_gate_3", "Nullifier Sibilant", "Sniper", currentHp = 300, maxHp = 300, baseAttack = 24, isTargeted = true, spriteTint = Color(0xFF78909C), speed = 64, atbGauge = 0.35f),
            Enemy("null_gate_4", "Vestibule Archivist", "Occultist", currentHp = 340, maxHp = 340, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF90A4AE), speed = 56, atbGauge = 0.30f)
        ),
        initialParty = createQuadParty()
    )

    // 31. Chapter 16: The Mirror Gauntlet
    val CH16_MIRROR_GAUNTLET = EncounterDefinition(
        id = "ch16_mirror_gauntlet",
        name = "Chapter 16: The Mirror Gauntlet",
        description = "The doubt-shadows of the entire fellowship rise against their originals in the Mirror Gallery.",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("shadow_cedric", "Shadow of the Knight", "Vanguard", currentHp = 380, maxHp = 380, baseAttack = 26, isTargeted = true, spriteTint = Color(0xFFFFD54F), speed = 52, atbGauge = 0.30f),
            Enemy("shadow_lyra", "Shadow of the Warden", "Sniper", currentHp = 330, maxHp = 330, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFA5D6A7), speed = 62, atbGauge = 0.40f),
            Enemy("shadow_zephyr", "Shadow of the Blade", "Sniper", currentHp = 330, maxHp = 330, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFCE93D8), speed = 74, atbGauge = 0.45f),
            Enemy("shadow_aethel", "Shadow of the Invocator", "Occultist", currentHp = 360, maxHp = 360, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFF90CAF9), speed = 58, atbGauge = 0.35f),
            Enemy("shadow_wall", "Gallery of Doubts", "Vanguard", currentHp = 420, maxHp = 420, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF263238), speed = 40, atbGauge = 0.15f)
        ),
        initialParty = createQuadParty()
    )

    // =========================================================================
    // COMMANDER AMERICA 8-ACT ENCOUNTERS
    // =========================================================================

    // 1. Act I - Chapter 1: The Escalator Press Confrontation
    val ACT1_CH1_PRESS_BATTLE = EncounterDefinition(
        id = "act1_ch1_press_battle",
        name = "Act I - Ch 1: Escalator Press Confrontation",
        description = "Skeptical cable news anchors and K-Street retainer lobbyists attempt to block the Trump Tower press conference!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("pundit_producer", "Cable News Producer", "Sniper", currentHp = 220, maxHp = 220, baseAttack = 16, isTargeted = false, spriteTint = Color(0xFFEF5350), speed = 60, atbGauge = 0.35f),
            Enemy("lobbyist_1", "K-Street Retainer Lobbyist", "Vanguard", currentHp = 260, maxHp = 260, baseAttack = 18, isTargeted = true, spriteTint = Color(0xFF78909C), speed = 50, atbGauge = 0.25f)
        ),
        initialParty = createAct1Party()
    )

    // Legacy alias for Act I Ch 1 encounter
    val ACT1_LOBBY_BATTLE = ACT1_CH1_PRESS_BATTLE

    // 1b. Act I - Chapter 2: The Primary Debate Arena
    val ACT1_CH2_DEBATE_BATTLE = EncounterDefinition(
        id = "act1_ch2_debate_battle",
        name = "Act I - Ch 2: The Primary Debate Arena",
        description = "Low-Energy Dynasty rivals and focus-grouped Super PAC strategists attempt to ambush the debate podium!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("dynasty_candidate", "Low-Energy Dynasty Candidate", "Vanguard", currentHp = 420, maxHp = 420, baseAttack = 20, isBoss = true, isTargeted = true, spriteTint = Color(0xFF5C6BC0), speed = 48, atbGauge = 0.30f),
            Enemy("pac_strategist", "Super PAC Media Strategist", "Sniper", currentHp = 280, maxHp = 280, baseAttack = 16, isTargeted = false, spriteTint = Color(0xFFAB47BC), speed = 62, atbGauge = 0.35f),
            Enemy("consultant_checker", "Consultant Fact-Checker", "Occultist", currentHp = 260, maxHp = 260, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF42A5F5), speed = 55, atbGauge = 0.20f)
        ),
        initialParty = createAct1Party()
    )

    // 1c. Act I - Chapter 3: The New Hampshire Snowstorm & Machine Battle
    val ACT1_CH3_MACHINE_BATTLE = EncounterDefinition(
        id = "act1_ch3_machine_battle",
        name = "Act I - Ch 3: The Party Machine Showdown",
        description = "The Establishment Party Boss and delegate brokers attempt to steal the primary election in a midnight airport hangar showdown!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("party_boss", "Establishment Party Boss", "Vanguard", currentHp = 600, maxHp = 600, baseAttack = 25, isBoss = true, isTargeted = true, spriteTint = Color(0xFFC62828), speed = 52, atbGauge = 0.35f),
            Enemy("dark_money", "K-Street Dark Money Machine", "Sniper", currentHp = 350, maxHp = 350, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF37474F), speed = 58, atbGauge = 0.30f),
            Enemy("delegate_broker", "Smug Delegate Broker", "Occultist", currentHp = 300, maxHp = 300, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF7E57C2), speed = 65, atbGauge = 0.25f)
        ),
        initialParty = createAct1Party()
    )

    // -------------------------------------------------------------------------
    // Dual-Campaign Act I Spells & Encounters (Track A & Track B)
    // -------------------------------------------------------------------------
    val hillRodAct1Spells = listOf(
        Spell(
            id = "hill_pokemon_go",
            name = "Pokémon Go-To-The-Polls",
            school = SpellSchool.ELECTROMANCY,
            basePower = 65,
            mpCost = 12,
            hitsAll = true,
            status = "OVERLOAD",
            aliases = listOf("pokemon go", "go to the polls", "pokemon", "polls", "smartphone app"),
            description = "Pitches a cringe smartphone app to make Pokémon appear in voting booths (July 14, 2016)",
            exampleChant = "Pokemon Go to the polls"
        ),
        Spell(
            id = "hill_cloth_wipe",
            name = "Chappaqua Cloth Wipe",
            school = SpellSchool.SHADOW,
            basePower = 70,
            mpCost = 14,
            status = "CORRODE",
            lifesteal = true,
            aliases = listOf("with a cloth", "wipe the server", "cloth wipe", "bleachbit", "server wipe"),
            description = "\"What, like with a cloth or something?\" Scrubs 33,000 emails and drains enemy buffs",
            exampleChant = "Wipe the server with a cloth or something"
        ),
        Spell(
            id = "hill_hot_sauce",
            name = "Purse Ninja Hot Sauce",
            school = SpellSchool.PYROMANCY,
            basePower = 78,
            mpCost = 15,
            status = "BURN",
            aliases = listOf("hot sauce", "purse hot sauce", "breakfast club", "red pepper", "pandering"),
            description = "Pulls Ninja Squirrel hot sauce from her handbag on The Breakfast Club while swearing it isn't pandering",
            exampleChant = "Hot sauce in my bag right now"
        ),
        Spell(
            id = "hill_cattle_futures",
            name = "\$1,000 Cattle Futures Stampede",
            school = SpellSchool.PHYSICAL,
            basePower = 82,
            mpCost = 18,
            hitsAll = true,
            status = "BLEED",
            aliases = listOf("cattle futures", "wall street journal", "live cattle", "one thousand dollars", "hundred grand"),
            description = "Turns \$1,000 into \$100,000 via 1978 Refco live-cattle contracts by reading the Wall Street Journal",
            exampleChant = "One thousand dollar cattle futures stampede"
        ),
        Spell(
            id = "hill_vindication_11hr",
            name = "11-Hour Marathon Shield",
            school = SpellSchool.HOLY,
            basePower = 110,
            mpCost = 20,
            isHeal = true,
            hitsAll = true,
            isGuard = true,
            status = "GUARD",
            aliases = listOf("eleven hour hearing", "marathon shield", "no basement", "shoulder shrug", "vindication"),
            description = "Survives an 11-hour committee grilling with a shoulder shrug and debunks the slab-foundation pizzeria basement hoax",
            exampleChant = "Eleven hour hearing shoulder shrug"
        ),
        attuneSpell
    )

    fun createAct1TrackBParty(): List<PartyMember> = listOf(
        PartyMember(
            id = "hill_rod",
            name = "Hill-Rod",
            loreClass = "Chappaqua Server Technomancer",
            currentHp = 340,
            maxHp = 340,
            currentMp = 140,
            maxMp = 140,
            spells = hillRodAct1Spells,
            avatarTint = Color(0xFF64B5F6),
            speed = 72,
            atbGauge = 0.50f
        )
    )

    // Track A — Act I Warmup Exhibition: Barry Teleprompter (Tan Suit & Dijon Burger)
    val ACT1_TRACK_A_BARRY_BATTLE = EncounterDefinition(
        id = "act1_track_a_barry_battle",
        name = "Act I (Track A): Tan Suit & Dijon Exhibition",
        description = "Barry Teleprompter steps onto the stage in his August 2014 summer tan suit, armed with a Dijon mustard burger and a mic-drop!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("barry_tan_suit", "Barry Teleprompter (Tan Suit)", "Occultist", currentHp = 480, maxHp = 480, baseAttack = 22, isBoss = true, isTargeted = true, spriteTint = Color(0xFFD7CCC8), speed = 58, atbGauge = 0.35f),
            Enemy("dijon_waiter", "Poupon Mustard Sommelier", "Sniper", currentHp = 250, maxHp = 250, baseAttack = 16, isTargeted = false, spriteTint = Color(0xFFFFD54F), speed = 52, atbGauge = 0.25f)
        ),
        initialParty = createAct1Party()
    )

    // Track A — Act I Finale Boss: Hill-Rod (Chappaqua Server & Pokémon Go-To-The-Polls)
    val ACT1_TRACK_A_HILL_ROD_FINALE = EncounterDefinition(
        id = "act1_track_a_hill_rod_finale",
        name = "Act I Finale (Track A): The Chappaqua Server Sovereign",
        description = "Hill-Rod deploys BleachBit cloth wipes, Purse Hot Sauce, and Pokémon Go-To-The-Polls on Election Night 2016!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("hill_rod_boss", "Hill-Rod (Server Sovereign)", "Occultist", currentHp = 620, maxHp = 620, baseAttack = 25, isBoss = true, isTargeted = true, spriteTint = Color(0xFF64B5F6), speed = 60, atbGauge = 0.35f),
            Enemy("bleachbit_server", "Chappaqua Basement Server Rack", "Vanguard", currentHp = 320, maxHp = 320, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF90A4AE), speed = 46, atbGauge = 0.20f),
            Enemy("fern_intern", "Potted Fern Intern", "Sniper", currentHp = 260, maxHp = 260, baseAttack = 17, isTargeted = false, spriteTint = Color(0xFF81C784), speed = 56, atbGauge = 0.25f)
        ),
        initialParty = createAct1Party()
    )

    // Track B — Act I Skirmish 1: Zach Two-Ferns & The Potted Plant Ambush
    val ACT1_TRACK_B_FERNS_BATTLE = EncounterDefinition(
        id = "act1_track_b_ferns_battle",
        name = "Act I (Track B): Between Two Potted Ferns",
        description = "Zach Two-Ferns rolls a rival campaign commercial mid-interview while weaponized potted ferns close in!",
        environment = BattleEnvironment.FOREST,
        enemies = listOf(
            Enemy("zach_ferns_boss", "Zach Two-Ferns", "Occultist", currentHp = 420, maxHp = 420, baseAttack = 19, isBoss = true, isTargeted = true, spriteTint = Color(0xFF81C784), speed = 54, atbGauge = 0.30f),
            Enemy("potted_fern_1", "Hostile Studio Fern Left", "Vanguard", currentHp = 230, maxHp = 230, baseAttack = 15, isTargeted = false, spriteTint = Color(0xFF4CAF50), speed = 48, atbGauge = 0.20f)
        ),
        initialParty = createAct1TrackBParty()
    )

    // Track B — Act I Skirmish 2: Spicy Sean & Director Jim-Tall
    val ACT1_TRACK_B_SPICY_SEAN_BATTLE = EncounterDefinition(
        id = "act1_track_b_spicy_sean_battle",
        name = "Act I (Track B): Shrubbery & October Surprise",
        description = "Spicy Sean leaps out of the bushes chewing 2.5 packs of Orbit Cinnamon gum while Director Jim-Tall drops an October letter!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("spicy_sean_boss", "Spicy Sean (Hedge Brawler)", "Vanguard", currentHp = 460, maxHp = 460, baseAttack = 21, isBoss = true, isTargeted = true, spriteTint = Color(0xFFAED581), speed = 58, atbGauge = 0.35f),
            Enemy("comey_curtain", "Director Jim-Tall (Blue Curtain)", "Sniper", currentHp = 310, maxHp = 310, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF90A4AE), speed = 52, atbGauge = 0.25f)
        ),
        initialParty = createAct1TrackBParty()
    )

    // Track B — Act I Finale Boss: Commander America (Golden Podium Showdown)
    val ACT1_TRACK_B_COMMANDER_FINALE = EncounterDefinition(
        id = "act1_track_b_commander_finale",
        name = "Act I Finale (Track B): Golden Podium Showdown",
        description = "Commander America commands the stage with Accordion Hands, Diet Coke Valet Buttons, and Tic-Tac Bus counter-strikes!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("commander_boss_act1", "Commander America (2016)", "Vanguard", currentHp = 640, maxHp = 640, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFD700), speed = 60, atbGauge = 0.35f),
            Enemy("billy_bus_minion", "Billy Access-Bus Hot Mic", "Sniper", currentHp = 290, maxHp = 290, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFFFFB74D), speed = 56, atbGauge = 0.25f),
            Enemy("escalator_extra", "\$50 FEC Casting Extra", "Vanguard", currentHp = 270, maxHp = 270, baseAttack = 16, isTargeted = false, spriteTint = Color(0xFFB0BEC5), speed = 48, atbGauge = 0.20f)
        ),
        initialParty = createAct1TrackBParty()
    )

    // 2a. Act II - Chapter 1: The Inaugural Address — Aerial Spin Crew Ambush
    val ACT2_CH1_INAUGURATION_BATTLE = EncounterDefinition(
        id = "act2_ch1_inauguration_battle",
        name = "Act II (Track A) - Ch 1: Crowd-Size Spin & The 11-Day Mooch",
        description = "Cable News Aerial Spin Crew and The 11-Day Mooch tornado collide with Spicy Sean's podium!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("aerial_spin_anchor", "Cable News Aerial Spin Anchor", "Sniper", currentHp = 320, maxHp = 320, baseAttack = 20, isBoss = true, isTargeted = true, spriteTint = Color(0xFFEF5350), speed = 68, atbGauge = 0.40f),
            Enemy("hostile_fact_checker", "Hostile Fisheye Fact-Checker", "Occultist", currentHp = 240, maxHp = 240, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF42A5F5), speed = 60, atbGauge = 0.30f)
        ),
        initialParty = createAct1Party()
    )

    // 2b. Act II: Media Wars & The Sub-Zero Call-Option Oracle (Track A Finale Boss)
    val ACT2_MEDIA_BATTLE = EncounterDefinition(
        id = "act2_media_battle",
        name = "Act II Finale (Track A): Nancy Palooza & The Sub-Zero Vault",
        description = "Nancy Palooza unleashes 45-degree sideways seal claps, pre-scored SOTU speech rips, and twin \$24,000 Sub-Zero ice cream freezers!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("pelosi", "Nancy Palooza (Sub-Zero Oracle)", "Occultist", currentHp = 520, maxHp = 520, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFFAB47BC), speed = 65, atbGauge = 0.35f),
            Enemy("fact_checker", "Sub-Zero Jeni's Freezer Golem", "Vanguard", currentHp = 280, maxHp = 280, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF42A5F5), speed = 54, atbGauge = 0.25f),
            Enemy("anonymous_source", "eSalon Blowout Stylist", "Sniper", currentHp = 250, maxHp = 250, baseAttack = 19, isTargeted = false, spriteTint = Color(0xFF7E57C2), speed = 58, atbGauge = 0.20f)
        ),
        initialParty = createAct2Party()
    )

    // -------------------------------------------------------------------------
    // Dual-Campaign Act II Spells & Encounters (Track A & Track B: Nancy Palooza)
    // -------------------------------------------------------------------------
    val nancyAct2Spells = listOf(
        Spell(
            id = "nancy_sotu_shred",
            name = "Pre-Scored SOTU Speech Rip",
            school = SpellSchool.PHYSICAL,
            basePower = 74,
            mpCost = 12,
            status = "BLEED",
            aliases = listOf("rip the speech", "tear pages", "sotu shred", "pre scored", "state of the union"),
            description = "Secretly pre-scores starter notches under the desk, then stands up to rip the speech in half on live TV (Feb 4, 2020)",
            exampleChant = "Rip the State of the Union speech in half"
        ),
        Spell(
            id = "nancy_subzero_flex",
            name = "\$24,000 Dual Sub-Zero Freezer Flex",
            school = SpellSchool.CRYOMANCY,
            basePower = 115,
            mpCost = 16,
            isHeal = true,
            hitsAll = true,
            isGuard = true,
            status = "GUARD",
            aliases = listOf("sub zero freezer", "jenis ice cream", "late late show", "ice cream freezer", "twenty four thousand"),
            description = "Opens twin \$24,000 stainless-steel Sub-Zero freezers stocked with \$12 Jeni's pints on The Late Late Show (April 2020)",
            exampleChant = "Open the dual Sub Zero ice cream freezers"
        ),
        Spell(
            id = "nancy_sideways_clap",
            name = "45-Degree Sideways Seal Clap",
            school = SpellSchool.HOLY,
            basePower = 68,
            mpCost = 14,
            hitsAll = true,
            status = "OVERLOAD",
            aliases = listOf("sideways clap", "pelosi clap", "seal clap", "condescending clap"),
            description = "Extends both arms at a 45-degree angle and claps condescendingly right into the target's face (Feb 5, 2019)",
            exampleChant = "Condescending sideways applause"
        ),
        Spell(
            id = "nancy_pass_to_find_out",
            name = "Pass the Bill to Find Out",
            school = SpellSchool.SHADOW,
            basePower = 76,
            mpCost = 15,
            status = "CORRODE",
            lifesteal = true,
            aliases = listOf("pass the bill", "find out whats in it", "fog of controversy", "omnibus"),
            description = "\"We have to pass the bill so that you can find out what is in it, away from the fog of the controversy!\"",
            exampleChant = "Pass the bill to find out what is in it"
        ),
        Spell(
            id = "nancy_deep_itm_calls",
            name = "Deep-ITM Tech Call Option (`NANC`)",
            school = SpellSchool.ELECTROMANCY,
            basePower = 88,
            mpCost = 20,
            hitsAll = true,
            status = "BURN",
            aliases = listOf("call options", "free market economy", "stock trade", "periodic transaction", "nanc"),
            description = "\"We are a free-market economy. They should be able to participate in that!\" Exercises \$5M in Big Tech LEAPS",
            exampleChant = "Free market Periodic Transaction Report"
        ),
        Spell(
            id = "nancy_vindication_nvda",
            name = "\$341k Pre-CHIPS Act Loss Receipt",
            school = SpellSchool.HOLY,
            basePower = 95,
            mpCost = 22,
            hitsAll = true,
            status = "WEAKEN",
            aliases = listOf("chips act loss", "nvidia loss", "unedited cspan", "fact check trade", "three hundred forty one"),
            description = "Slams down the July 26, 2022 clerk filing proving Paul SOLD 25,000 Nvidia shares at a \$341,365 loss before the CHIPS Act vote!",
            exampleChant = "Three hundred forty one thousand dollar loss receipt"
        ),
        attuneSpell
    )

    fun createAct2TrackBParty(): List<PartyMember> = listOf(
        PartyMember(
            id = "nancy_palooza",
            name = "Nancy Palooza",
            loreClass = "Sub-Zero Call-Option Oracle",
            currentHp = 360,
            maxHp = 360,
            currentMp = 150,
            maxMp = 150,
            spells = nancyAct2Spells,
            avatarTint = Color(0xFF81C784),
            speed = 74,
            atbGauge = 0.50f
        )
    )

    // Track A — Act II Ch 3 Battle: NOAA Sharpie Map & 15-Flush Plumbing Inspectors
    val ACT2_TRACK_A_SHARPIE_BATTLE = EncounterDefinition(
        id = "act2_track_a_sharpie_battle",
        name = "Act II (Track A) - Ch 3: Hurricane Sharpie & 15 Flushes",
        description = "NOAA Weather-Map Fact-Checkers and Low-Flow Plumbing Inspectors challenge the Alabama Sharpie bubble and Bounty free-throws!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("noaa_meteorologist", "NOAA Cone-of-Uncertainty Chief", "Occultist", currentHp = 440, maxHp = 440, baseAttack = 22, isBoss = true, isTargeted = true, spriteTint = Color(0xFF29B6F6), speed = 60, atbGauge = 0.35f),
            Enemy("plumbing_inspector", "Low-Flow 15-Flush Regulator", "Vanguard", currentHp = 290, maxHp = 290, baseAttack = 19, isTargeted = false, spriteTint = Color(0xFF78909C), speed = 50, atbGauge = 0.25f)
        ),
        initialParty = createAct2Party()
    )

    // Track B — Act II Ch 1 Battle: Spicy Sean (Neon Salsa Shirt) & The 11-Day Mooch
    val ACT2_TRACK_B_SPICER_SALSA_BATTLE = EncounterDefinition(
        id = "act2_track_b_spicer_salsa_battle",
        name = "Act II (Track B) - Ch 1: White House Bushes & Neon Salsa",
        description = "Spicy Sean leaps out of the White House hedges in his neon-green Dancing with the Stars ruffled shirt alongside The 11-Day Mooch!",
        environment = BattleEnvironment.FOREST,
        enemies = listOf(
            Enemy("spicy_sean_salsa", "Spicy Sean (Neon Salsa Shirt)", "Vanguard", currentHp = 450, maxHp = 450, baseAttack = 21, isBoss = true, isTargeted = true, spriteTint = Color(0xFF76FF03), speed = 58, atbGauge = 0.35f),
            Enemy("the_mooch_boss", "The 11-Day Mooch Whirlwind", "Sniper", currentHp = 310, maxHp = 310, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFFFF80AB), speed = 72, atbGauge = 0.40f)
        ),
        initialParty = createAct2TrackBParty()
    )

    // Track B — Act II Ch 4 Battle: Kash Subpoena & Wizard Kash Merch Pitch
    val ACT2_TRACK_B_KASH_BATTLE = EncounterDefinition(
        id = "act2_track_b_kash_battle",
        name = "Act II (Track B) - Ch 4: Wizard Kash & The Nunes Scroll",
        description = "Kash Subpoena unrolls his 4-page FISA scroll while hawking his \$19.99 illustrated children's book, The Plot Against the King!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("kash_wizard_boss", "Kash Subpoena (Wizard Kash)", "Occultist", currentHp = 520, maxHp = 520, baseAttack = 24, isBoss = true, isTargeted = true, spriteTint = Color(0xFFAB47BC), speed = 64, atbGauge = 0.35f),
            Enemy("brave_book_golem", "Plot Against the King Merch Box", "Vanguard", currentHp = 300, maxHp = 300, baseAttack = 19, isTargeted = false, spriteTint = Color(0xFFFFCA28), speed = 50, atbGauge = 0.25f)
        ),
        initialParty = createAct2TrackBParty()
    )

    // Track B — Act II Ch 5 Finale Boss: Rudy Ghoul & Commander America at Four Seasons Total Landscaping
    val ACT2_TRACK_B_RUDY_FINALE = EncounterDefinition(
        id = "act2_track_b_rudy_finale",
        name = "Act II Finale (Track B): Four Seasons Total Landscaping",
        description = "Wedged between a crematorium and Fantasy Island Adult Books, Rudy Ghoul drips hair dye down both cheeks beside Commander America!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("rudy_ghoul_boss", "Rudy Ghoul (Melting Hair Dye)", "Occultist", currentHp = 580, maxHp = 580, baseAttack = 25, isBoss = true, isTargeted = true, spriteTint = Color(0xFF8D6E63), speed = 60, atbGauge = 0.35f),
            Enemy("commander_sharpie", "Commander America (Sharpie Map)", "Vanguard", currentHp = 560, maxHp = 560, baseAttack = 26, isBoss = true, isTargeted = false, spriteTint = Color(0xFFFFD700), speed = 58, atbGauge = 0.30f),
            Enemy("landscaping_mower", "Four Seasons Riding Mower", "Sniper", currentHp = 260, maxHp = 260, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF66BB6A), speed = 52, atbGauge = 0.20f)
        ),
        initialParty = createAct2TrackBParty()
    )

    // -------------------------------------------------------------------------
    // Dual-Campaign Act III Spells & Encounters (Track A & Track B: Joe Naptime)
    // -------------------------------------------------------------------------
    val bidenAct3Spells = listOf(
        Spell(
            id = "biden_gibberish_audio",
            name = "Real-Audio Gibberish (`Truinanashabbadepressure!`)",
            school = SpellSchool.ELECTROMANCY,
            basePower = 94,
            mpCost = 18,
            hitsAll = true,
            status = "OVERLOAD",
            aliases = listOf("gibberish", "truinanashabbadepressure", "badakathcare", "repeat the line", "corn pop", "beat medicare", "you know the thing"),
            description = "Plays a random real-life Biden audio quote (Truinanashabbadepressure, Badakathcare, Repeat the Line, Corn Pop, Beat Medicare) to stun all foes!",
            exampleChant = "Mobilize truinanashabbadepressure and badakathcare"
        ),
        Spell(
            id = "biden_double_scoop_cone",
            name = "Double-Scoop Waffle Cone Blitz",
            school = SpellSchool.CRYOMANCY,
            basePower = 76,
            mpCost = 12,
            status = "CHILL",
            aliases = listOf("double scoop", "waffle cone", "chocolate chip", "ice cream blitz", "two scoops"),
            description = "\"I came down because I heard there was chocolate chip ice cream!\" Hurls a freezing double-scoop waffle cone (May 27, 2021)",
            exampleChant = "Double scoop chocolate chip waffle cone"
        ),
        Spell(
            id = "biden_jenis_brain_freeze",
            name = "Jeni's Chocolate Chip Brain-Freeze",
            school = SpellSchool.CRYOMANCY,
            basePower = 86,
            mpCost = 18,
            hitsAll = true,
            status = "CHILL",
            aliases = listOf("brain freeze", "jenis chocolate chip", "refrigerator upstairs", "ice cream blizzard"),
            description = "\"I have a whole refrigerator full upstairs!\" Unleashes an AoE blizzard of Jeni's Splendid Chocolate Chip pints",
            exampleChant = "Whole refrigerator full of chocolate chip ice cream"
        ),
        Spell(
            id = "biden_baskin_robbins_lick",
            name = "\"Economy Strong As Hell\" Baskin-Robbins Lick",
            school = SpellSchool.CRYOMANCY,
            basePower = 120,
            mpCost = 16,
            isHeal = true,
            hitsAll = true,
            isGuard = true,
            status = "GUARD",
            aliases = listOf("economy strong as hell", "baskin robbins", "ice cream lick", "strong as hell", "portland cone"),
            description = "Licks a Baskin-Robbins chocolate chip waffle cone during 8.2% inflation while declaring \"Our economy is strong as hell, man!\" (Oct 15, 2022)",
            exampleChant = "Our economy is strong as hell Baskin Robbins cone"
        ),
        Spell(
            id = "biden_9am_lid_sundae",
            name = "9:00 AM Lid & Sundae Nap",
            school = SpellSchool.CRYOMANCY,
            basePower = 90,
            mpCost = 0,
            isHeal = true,
            manaRestorePct = 0.35f,
            aliases = listOf("call a lid", "sundae nap", "nine am lid", "delaware beach", "ice cream nap"),
            description = "Calls an early 9:00 AM press lid over a hot-fudge sundae, restoring HP and 35% MP",
            exampleChant = "Call a nine AM lid for a hot fudge sundae"
        ),
        Spell(
            id = "biden_fudge_ripple_meltdown",
            name = "Aviator Fudge-Ripple Meltdown",
            school = SpellSchool.CRYOMANCY,
            basePower = 90,
            mpCost = 20,
            hitsAll = true,
            status = "WEAKEN",
            aliases = listOf("fudge ripple", "aviator meltdown", "no malarkey", "melting cone", "two beers"),
            description = "Adjusts Ray-Ban aviators over a melting fudge-ripple cone while debunking the 'Two Beers a Week & Red Meat Ban' hoax!",
            exampleChant = "Aviator fudge ripple meltdown no malarkey"
        ),
        attuneSpell
    )

    fun createAct3TrackBParty(): List<PartyMember> = listOf(
        PartyMember(
            id = "joe_naptime",
            name = "Joe Naptime",
            loreClass = "Grand Ice-Cream Cryomancer",
            currentHp = 390,
            maxHp = 390,
            currentMp = 160,
            maxMp = 160,
            spells = bidenAct3Spells,
            avatarTint = Color(0xFFFFF59D),
            speed = 70,
            atbGauge = 0.50f
        )
    )

    fun createAct3KennedysParty(): List<PartyMember> = listOf(
        TemplateHero.createTemplateAdventurer(id = "hero"),
        TemplateHero.createMarcoRubio(),
        TemplateHero.createKashPatel(),
        TemplateHero.createJohnKennedy()
    )

    // Track A — Act III Ch 1 Battle: Air Force One Stairs & Rehoboth Toe-Cage Bicycle
    val ACT3_TRACK_A_STAIRS_BIKE_BATTLE = EncounterDefinition(
        id = "act3_track_a_stairs_bike_battle",
        name = "Act III (Track A) - Ch 1: Windy Stairs & Toe-Cage Bike",
        description = "Andrews Air Force Base wind gusts and a stationary Rehoboth Beach Trek bicycle with metal pedal toe-cages block the tarmac!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("af1_staircase_gust", "Air Force One Triple-Stumble Staircase", "Vanguard", currentHp = 480, maxHp = 480, baseAttack = 24, isBoss = true, isTargeted = true, spriteTint = Color(0xFF90CAF9), speed = 60, atbGauge = 0.35f),
            Enemy("rehoboth_toe_cage_bike", "Stationary Toe-Cage Trek Bicycle", "Sniper", currentHp = 330, maxHp = 330, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF80CBC4), speed = 56, atbGauge = 0.25f)
        ),
        initialParty = createAct3KennedysParty()
    )

    // Track A — Act III Ch 3 Battle: Mar-a-Lago Bathroom Chandelier & Corvette Garage Inspectors
    val ACT3_TRACK_A_CHANDELIER_BATTLE = EncounterDefinition(
        id = "act3_track_a_chandelier_battle",
        name = "Act III (Track A) - Ch 3: Bathroom Chandeliers & Corvette Boxes",
        description = "Subpoena Archivists inspect the Mar-a-Lago bathroom chandelier bankers boxes while ignoring the 1967 Corvette garage!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("chandelier_archivist", "Bathroom Chandelier Box Inspector", "Occultist", currentHp = 510, maxHp = 510, baseAttack = 25, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFD54F), speed = 62, atbGauge = 0.35f),
            Enemy("corvette_garage_drone", "Wilmington Corvette Garage Sentinel", "Vanguard", currentHp = 340, maxHp = 340, baseAttack = 21, isTargeted = false, spriteTint = Color(0xFF66BB6A), speed = 54, atbGauge = 0.25f)
        ),
        initialParty = createAct3KennedysParty()
    )

    // Track A — Act III Ch 5 Finale Boss: Joe Naptime at Baskin-Robbins ("Economy Strong As Hell")
    val ACT3_TRACK_A_BIDEN_ICE_CREAM_FINALE = EncounterDefinition(
        id = "act3_track_a_biden_ice_cream_finale",
        name = "Act III Finale (Track A): Baskin-Robbins Waffle Cone Showdown",
        description = "Joe Naptime licks a double-scoop chocolate chip waffle cone at Baskin-Robbins while reading 'End of quote, repeat the line' off the teleprompter!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("joe_naptime_baskin_boss", "Joe Naptime (Baskin-Robbins Cryomancer)", "Occultist", currentHp = 650, maxHp = 650, baseAttack = 28, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFF59D), speed = 64, atbGauge = 0.35f),
            Enemy("repeat_line_prompter", "'End of Quote, Repeat the Line' Teleprompter", "Sniper", currentHp = 350, maxHp = 350, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF4DD0E1), speed = 58, atbGauge = 0.25f),
            Enemy("two_scoop_golem", "Jeni's Double-Scoop Freezer Golem", "Vanguard", currentHp = 380, maxHp = 380, baseAttack = 21, isTargeted = false, spriteTint = Color(0xFFF48FB1), speed = 50, atbGauge = 0.20f)
        ),
        initialParty = createAct3KennedysParty()
    )

    // Track B — Act III Ch 1 Battle: Corn Pop's Rusty Rainwater Razor & The Rehoboth Toe-Cage Bike
    val ACT3_TRACK_B_CORN_POP_BATTLE = EncounterDefinition(
        id = "act3_track_b_corn_pop_battle",
        name = "Act III (Track B) - Ch 1: Corn Pop & The Toe-Cage Bicycle",
        description = "Corn Pop ('a bad dude who ran a bunch of bad boys') bangs rusty razors in a rainwater barrel beside a treacherous toe-cage bicycle!",
        environment = BattleEnvironment.FOREST,
        enemies = listOf(
            Enemy("corn_pop_boss", "Corn Pop (Rusty Rain-Barrel Dude)", "Vanguard", currentHp = 460, maxHp = 460, baseAttack = 21, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFB74D), speed = 58, atbGauge = 0.35f),
            Enemy("toe_cage_pedal", "Rehoboth Metal Toe-Cage Bike", "Sniper", currentHp = 290, maxHp = 290, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF90A4AE), speed = 54, atbGauge = 0.25f)
        ),
        initialParty = createAct3TrackBParty()
    )

    // Track B — Act III Ch 3 Battle: Senator Foghorn Kennedy ("Call A Crackhead!")
    val ACT3_TRACK_B_FOGHORN_BATTLE = EncounterDefinition(
        id = "act3_track_b_foghorn_battle",
        name = "Act III (Track B) - Ch 3: Foghorn Kennedy's Oxford Drawl",
        description = "Senator Foghorn Kennedy hides an Oxford First-Class degree behind folksy one-liners while asking judicial nominees about Article V!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("foghorn_kennedy_boss", "Foghorn Kennedy (Oxford Drawl)", "Occultist", currentHp = 520, maxHp = 520, baseAttack = 23, isBoss = true, isTargeted = true, spriteTint = Color(0xFF78909C), speed = 62, atbGauge = 0.35f),
            Enemy("article_five_binder", "Constitutional Article V Pop-Quiz", "Sniper", currentHp = 310, maxHp = 310, baseAttack = 19, isTargeted = false, spriteTint = Color(0xFFBA68C8), speed = 56, atbGauge = 0.25f)
        ),
        initialParty = createAct3TrackBParty()
    )

    // Track B — Act III Ch 5 Finale Boss: Commander America (215-Pound Mugshot) & The Bathroom Chandelier Box Golem
    val ACT3_TRACK_B_MUGSHOT_FINALE = EncounterDefinition(
        id = "act3_track_b_mugshot_finale",
        name = "Act III Finale (Track B): 215-Pound Mugshot & Bathroom Chandelier",
        description = "Commander America flashes his 'Never Surrender' Fulton County mugshot beside a gilded Mar-a-Lago bathroom chandelier stacked with bankers boxes!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("commander_mugshot_boss", "Commander America (215-Lb Mugshot)", "Vanguard", currentHp = 610, maxHp = 610, baseAttack = 25, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFD700), speed = 60, atbGauge = 0.35f),
            Enemy("bathroom_chandelier_golem", "Mar-a-Lago Chandelier Box Stack", "Occultist", currentHp = 350, maxHp = 350, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFFFFF176), speed = 52, atbGauge = 0.25f),
            Enemy("never_surrender_merch", "\$7.1M 'Never Surrender' Mug Press", "Sniper", currentHp = 290, maxHp = 290, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFFE57373), speed = 56, atbGauge = 0.20f)
        ),
        initialParty = createAct3TrackBParty()
    )

    // -------------------------------------------------------------------------
    // Dual-Campaign Act IV Spells & Encounters (Track A & Track B: Kamala Coconut)
    // -------------------------------------------------------------------------
    val kamalaAct4Spells = listOf(
        Spell(
            id = "kamala_coconut_tree",
            name = "Coconut Tree Context Drop",
            school = SpellSchool.NATURE,
            basePower = 78,
            mpCost = 14,
            status = "OVERLOAD",
            aliases = listOf("coconut tree", "fell out of a coconut tree", "context of all", "what came before"),
            description = "\"You think you just fell out of a coconut tree?! You exist in the context of all in which you live and what came before you!\" (May 10, 2023)",
            exampleChant = "You think you just fell out of a coconut tree"
        ),
        Spell(
            id = "kamala_unburdened_loop",
            name = "Unburdened By What Has Been",
            school = SpellSchool.HOLY,
            basePower = 120,
            mpCost = 16,
            isHeal = true,
            hitsAll = true,
            isGuard = true,
            status = "GUARD",
            aliases = listOf("unburdened", "what can be", "what has been", "unburdened by what has been"),
            description = "Repeats her signature philosophical mantra 47 times to unburden the party of damage and raise a rhetorical shield",
            exampleChant = "What can be unburdened by what has been"
        ),
        Spell(
            id = "kamala_venn_diagram",
            name = "Triple-Circle Venn Diagram Trap",
            school = SpellSchool.ELECTROMANCY,
            basePower = 82,
            mpCost = 16,
            hitsAll = true,
            status = "ROOT",
            aliases = listOf("venn diagram", "three circles", "love venn diagrams", "intersection"),
            description = "\"I love Venn diagrams! Always ask, is there a Venn diagram for this? Those three circles!\" Traps all foes in overlapping circles",
            exampleChant = "I love Venn diagrams and those three circles"
        ),
        Spell(
            id = "kamala_passage_of_time",
            name = "Significance of the Passage of Time (4x)",
            school = SpellSchool.SHADOW,
            basePower = 84,
            mpCost = 18,
            hitsAll = true,
            status = "CHILL",
            aliases = listOf("passage of time", "significance of the passage", "four times", "time loop"),
            description = "Repeats \"the significance of the passage of time\" four times in 32 seconds (March 2022), freezing enemy ATB gauges",
            exampleChant = "The significance of the passage of time"
        ),
        Spell(
            id = "kamala_yellow_school_bus",
            name = "Yellow Electric School Bus Charge",
            school = SpellSchool.ELECTROMANCY,
            basePower = 90,
            mpCost = 20,
            hitsAll = true,
            status = "BURN",
            aliases = listOf("yellow school bus", "love a yellow school bus", "raise your hand", "electric bus"),
            description = "\"Who doesn't love a yellow school bus, right?! Can you raise your hand if you love a yellow school bus?!\" (Oct 2022)",
            exampleChant = "Who does not love a yellow school bus"
        ),
        Spell(
            id = "kamala_vindication_tiffany",
            name = "Tiffany Pearl & CDCR Fact-Check",
            school = SpellSchool.HOLY,
            basePower = 96,
            mpCost = 22,
            hitsAll = true,
            status = "WEAKEN",
            aliases = listOf("tiffany earrings", "pearl earrings", "cdcr records", "im speaking", "vindication"),
            description = "Debunks the 'Bluetooth Earpiece' conspiracy with Tiffany South Sea Pearl photos and cites official CDCR records!",
            exampleChant = "Tiffany pearl earrings and official records"
        ),
        attuneSpell
    )

    fun createAct4TrackBParty(): List<PartyMember> = listOf(
        PartyMember(
            id = "kamala_coconut",
            name = "Kamala Coconut",
            loreClass = "Venn-Diagram Prosecutor",
            currentHp = 410,
            maxHp = 410,
            currentMp = 165,
            maxMp = 165,
            spells = kamalaAct4Spells,
            avatarTint = Color(0xFFBA68C8),
            speed = 75,
            atbGauge = 0.50f
        )
    )

    // Track A — Act IV Ch 1 Battle: Awkward Donut-Counter Clerk & Philly Swiss-Cheese Purist
    val ACT4_TRACK_A_DONUT_BATTLE = EncounterDefinition(
        id = "act4_track_a_donut_battle",
        name = "Act IV (Track A) - Ch 1: 'Whatever Makes Sense' Donuts & Swiss Cheese",
        description = "JD Pants joins the party at Holt's Sweet Shoppe as an unimpressed bakery counter and a Philly Swiss-cheese purist attack!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("donut_shop_case", "'Whatever Makes Sense' Glazed Case", "Vanguard", currentHp = 490, maxHp = 490, baseAttack = 25, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFB74D), speed = 60, atbGauge = 0.35f),
            Enemy("swiss_cheese_purist", "South Philly Swiss-Cheese Heckler", "Sniper", currentHp = 340, maxHp = 340, baseAttack = 21, isTargeted = false, spriteTint = Color(0xFFFFF176), speed = 56, atbGauge = 0.25f)
        ),
        initialParty = createFullAmericaParty()
    )

    // Track B — Act IV Ch 1 Battle: Hostile Rhetoric Critic & Uncontextualized Coconut
    val ACT4_TRACK_B_VENN_CRITICS_BATTLE = EncounterDefinition(
        id = "act4_track_b_venn_critics_battle",
        name = "Act IV (Track B) - Ch 1: Coconut Context & Venn Circles",
        description = "Kamala Coconut unleashes overlapping Venn diagrams and yellow school buses against skeptical pundits!",
        environment = BattleEnvironment.FOREST,
        enemies = listOf(
            Enemy("word_salad_critic", "Uncontextualized Pundit", "Occultist", currentHp = 470, maxHp = 470, baseAttack = 22, isBoss = true, isTargeted = true, spriteTint = Color(0xFF81C784), speed = 58, atbGauge = 0.35f),
            Enemy("teleprompter_glitch", "32-Day Teleprompter Glitch", "Sniper", currentHp = 310, maxHp = 310, baseAttack = 19, isTargeted = false, spriteTint = Color(0xFF4FC3F7), speed = 54, atbGauge = 0.25f)
        ),
        initialParty = createAct4TrackBParty()
    )

    // Track B — Act IV Ch 2 Battle: JD Pants at the Valdosta Donut Counter
    val ACT4_TRACK_B_VANCE_DONUT_BATTLE = EncounterDefinition(
        id = "act4_track_b_vance_donut_battle",
        name = "Act IV (Track B) - Ch 2: JD Pants & 'Whatever Makes Sense'",
        description = "JD Pants asks 'How long have you worked here? Okay, good' while hurling sprinkle donuts and CBS fact-check rules!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("jd_pants_boss", "JD Pants (Donut Interrogator)", "Vanguard", currentHp = 540, maxHp = 540, baseAttack = 24, isBoss = true, isTargeted = true, spriteTint = Color(0xFF5C6BC0), speed = 62, atbGauge = 0.35f),
            Enemy("sprinkle_stuff_box", "'Some Sprinkle Stuff' Donut Box", "Sniper", currentHp = 320, maxHp = 320, baseAttack = 19, isTargeted = false, spriteTint = Color(0xFFF48FB1), speed = 56, atbGauge = 0.25f)
        ),
        initialParty = createAct4TrackBParty()
    )

    // Track B — Act IV Ch 4 Battle: Philadelphia Debate Stage ("Concepts of a Plan")
    val ACT4_TRACK_B_CONCEPTS_DEBATE_BATTLE = EncounterDefinition(
        id = "act4_track_b_concepts_debate_battle",
        name = "Act IV (Track B) - Ch 4: 'Concepts of a Plan' & 'People on TV'",
        description = "On the Philadelphia debate stage, Commander America unveils 'Concepts of a Plan' and 'I saw it on television!'",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("concepts_of_plan_boss", "Commander America ('Concepts of a Plan')", "Vanguard", currentHp = 580, maxHp = 580, baseAttack = 25, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFD700), speed = 62, atbGauge = 0.35f),
            Enemy("people_on_tv_set", "'The People on Television' Monitor", "Occultist", currentHp = 330, maxHp = 330, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF42A5F5), speed = 56, atbGauge = 0.25f)
        ),
        initialParty = createAct4TrackBParty()
    )

    // Track B — Act IV Ch 5 Finale Boss: McDonald's Fry Apron & Orange Garbage Truck Vest
    val ACT4_TRACK_B_MCDONALDS_FINALE = EncounterDefinition(
        id = "act4_track_b_mcdonalds_finale",
        name = "Act IV Finale (Track B): Drive-Thru Fry Basket & Garbage Vest",
        description = "Commander America leans out a closed-set McDonald's drive-thru window in a fry apron and neon-orange garbage truck vest beside JD Pants!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("commander_fry_apron", "Commander America (Fry-Cook Apron)", "Vanguard", currentHp = 630, maxHp = 630, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFCA28), speed = 62, atbGauge = 0.35f),
            Enemy("garbage_truck_vest", "Neon-Orange Mack Garbage Truck", "Vanguard", currentHp = 370, maxHp = 370, baseAttack = 21, isTargeted = false, spriteTint = Color(0xFFFF7043), speed = 52, atbGauge = 0.25f),
            Enemy("vance_diet_dew", "JD Pants (Diet Mountain Dew)", "Sniper", currentHp = 320, maxHp = 320, baseAttack = 19, isTargeted = false, spriteTint = Color(0xFF66BB6A), speed = 58, atbGauge = 0.20f)
        ),
        initialParty = createAct4TrackBParty()
    )

    // -------------------------------------------------------------------------
    // Dual-Campaign Act V Spells & Encounters (Track A & Track B: Zohran Cardamom & Mayor Swagger)
    // -------------------------------------------------------------------------
    val zohranAct5Spells = listOf(
        Spell(
            id = "zohran_nani_chapati",
            name = "\"Mr. Cardamom\" Nani Chapati Drop",
            school = SpellSchool.PYROMANCY,
            basePower = 82,
            mpCost = 14,
            status = "BURN",
            aliases = listOf("mr cardamom", "young cardamom", "nani", "chapati", "madhur jaffrey"),
            description = "Puts on his 2019 'Mr. Cardamom' rap apron with Madhur Jaffrey and hurls a sizzling chapati from a Queens food truck!",
            exampleChant = "Mister Cardamom Nani chapati drop"
        ),
        Spell(
            id = "zohran_municipal_grocery",
            name = "5 City-Owned Grocery Stores",
            school = SpellSchool.NATURE,
            basePower = 125,
            mpCost = 16,
            isHeal = true,
            hitsAll = true,
            isGuard = true,
            status = "GUARD",
            aliases = listOf("city owned grocery", "municipal grocery", "five grocery stores", "astoria"),
            description = "Opens five city-run municipal grocery stores across all five boroughs, healing the party with wholesale milk",
            exampleChant = "Open five city owned municipal grocery stores"
        ),
        Spell(
            id = "zohran_freeze_the_rent",
            name = "Freeze the Rent & Free the Q70 Bus",
            school = SpellSchool.CRYOMANCY,
            basePower = 88,
            mpCost = 18,
            hitsAll = true,
            status = "CHILL",
            aliases = listOf("freeze the rent", "free the buses", "q70 bus", "rent stabilized"),
            description = "Films a symmetrical Wes Anderson TikTok in a thrifted suit to freeze enemy ATB gauges at 0% rent increase!",
            exampleChant = "Freeze the rent and free the buses"
        ),
        Spell(
            id = "zohran_bowdoin_stipend",
            name = "Bowdoin & Hollywood Trust-Fund Crit",
            school = SpellSchool.ELECTROMANCY,
            basePower = 92,
            mpCost = 20,
            hitsAll = true,
            status = "OVERLOAD",
            aliases = listOf("bowdoin", "bank street", "monsoon wedding", "faculty club", "trust fund"),
            description = "Channels \$60k/yr Bank Street prep-school rhetoric and Oscar-nominated family cinema into a high-budget AoE strike!",
            exampleChant = "Bank Street and Bowdoin campus stipend"
        ),
        Spell(
            id = "zohran_vindication_pilot",
            name = "MTA Fare-Free Pilot Receipt",
            school = SpellSchool.HOLY,
            basePower = 98,
            mpCost = 22,
            hitsAll = true,
            status = "WEAKEN",
            aliases = listOf("fare free pilot", "mta data", "bus assaults down", "vindication"),
            description = "Slams down official MTA pilot data showing fare-free routes boosted ridership 30% and cut driver assaults 38.9%!",
            exampleChant = "Official MTA fare free bus pilot data"
        ),
        attuneSpell
    )

    val mayorSwaggerSpells = listOf(
        Spell(
            id = "swagger_turkish_upgrade",
            name = "Turkish Airlines Lie-Flat Upgrade",
            school = SpellSchool.HOLY,
            basePower = 115,
            mpCost = 16,
            isHeal = true,
            hitsAll = true,
            aliases = listOf("turkish airlines", "business class", "istanbul", "i said thank you", "st regis"),
            description = "\"When someone upgrades you to a lie-flat suite to Istanbul, you say 'Thank you'!\" Restores party HP in luxury",
            exampleChant = "Turkish Airlines business class upgrade"
        ),
        Spell(
            id = "swagger_contraband_doll",
            name = "Contraband Baby-Doll Search",
            school = SpellSchool.SHADOW,
            basePower = 84,
            mpCost = 16,
            hitsAll = true,
            status = "OVERLOAD",
            aliases = listOf("baby doll", "jar of marbles", "contraband", "simple searches", "pillowcase"),
            description = "Pulls a crack pipe out of a marble jar and weed out of a baby doll's head from his viral NYPD instructional video!",
            exampleChant = "Search the baby doll and marble jar"
        ),
        Spell(
            id = "swagger_vegan_seabass",
            name = "\"Strictly Vegan\" Midnight Sea Bass",
            school = SpellSchool.NATURE,
            basePower = 86,
            mpCost = 18,
            status = "POISON",
            lifesteal = true,
            aliases = listOf("sea bass", "vegan", "plant based", "la baia", "fish"),
            description = "Preaches a strict plant-based diet by day while secretly devouring flaky Chilean sea bass at Osteria La Baia by night!",
            exampleChant = "Plant based midnight Chilean sea bass"
        ),
        Spell(
            id = "swagger_forgot_passcode",
            name = "Forgotten 6-Digit iPhone Passcode",
            school = SpellSchool.ELECTROMANCY,
            basePower = 0,
            mpCost = 0,
            manaRestorePct = 0.35f,
            aliases = listOf("forgot passcode", "six digits", "iphone lock", "changed password"),
            description = "Changes his iPhone passcode from 4 digits to 6 digits right before a subpoena and 'accidentally forgets' it, restoring 35% MP!",
            exampleChant = "Forgot my six digit iPhone passcode"
        )
    )

    fun createAct5TrackBParty(): List<PartyMember> = listOf(
        PartyMember(
            id = "zohran_cardamom",
            name = "Zohran Cardamom",
            loreClass = "Artisanal Chapati Socialist",
            currentHp = 430,
            maxHp = 430,
            currentMp = 175,
            maxMp = 175,
            spells = zohranAct5Spells,
            avatarTint = Color(0xFFFF7043),
            speed = 76,
            atbGauge = 0.50f
        ),
        PartyMember(
            id = "mayor_swagger",
            name = "Mayor Swagger",
            loreClass = "Istanbul Nightlife Envoy",
            currentHp = 420,
            maxHp = 420,
            currentMp = 150,
            maxMp = 150,
            spells = mayorSwaggerSpells,
            avatarTint = Color(0xFF26A69A),
            speed = 70,
            atbGauge = 0.40f
        )
    )

    // Track A — Act V Ch 1 Battle: Shattered Cybertruck Glass & SEC $420 Auditor
    val ACT5_TRACK_A_CYBERTRUCK_SINK_BATTLE = EncounterDefinition(
        id = "act5_track_a_cybertruck_sink_battle",
        name = "Act V (Track A) - Ch 1: Mid-Air Star-Jump & Porcelain Sink",
        description = "Musk-X leaps into an X-shape mid-air with a porcelain bathroom sink while a shattered-window Cybertruck and SEC $420 auditor attack!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("shattered_cybertruck", "Shattered Armor-Glass Cybertruck", "Vanguard", currentHp = 520, maxHp = 520, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFFB0BEC5), speed = 62, atbGauge = 0.35f),
            Enemy("sec_420_auditor", "SEC '\$420 Funding Secured' Auditor", "Occultist", currentHp = 360, maxHp = 360, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF42A5F5), speed = 58, atbGauge = 0.25f)
        ),
        initialParty = createFullAmericaParty()
    )

    // Track A — Act V Ch 2 Battle: Omnibus Pork-Barrel Golem & Blackmailing Ad-Agency Exec
    val ACT5_TRACK_A_DOGE_BUREAUCRATS_BATTLE = EncounterDefinition(
        id = "act5_track_a_doge_bureaucrats_battle",
        name = "Act V (Track A) - Ch 2: Hulkster Shirt-Rip & DOGE Audit",
        description = "Hulkster 24-Pythons rips his shirt in half as the Shiba Inu DOGE commission audits an Omnibus Pork-Barrel Golem!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("omnibus_pork_golem", "4,000-Page Omnibus Pork Golem", "Vanguard", currentHp = 550, maxHp = 550, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFFAB47BC), speed = 60, atbGauge = 0.35f),
            Enemy("dealbook_boycott_exec", "DealBook Ad-Boycott Executive", "Sniper", currentHp = 350, maxHp = 350, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFFEC407A), speed = 56, atbGauge = 0.25f)
        ),
        initialParty = createFullAmericaParty()
    )

    // Track A — Act V Ch 3 Battle: Mayor Swagger & The Turkish Airlines Lie-Flat Seat
    val ACT5_TRACK_A_SWAGGER_TURKISH_BATTLE = EncounterDefinition(
        id = "act5_track_a_swagger_turkish_battle",
        name = "Act V (Track A) - Ch 3: Mayor Swagger & Turkish Lie-Flat Suites",
        description = "Mayor Swagger pulls contraband out of a baby doll's head while sipping rat-free table water in a Turkish Airlines suite!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("mayor_swagger_boss", "Mayor Swagger (Istanbul Envoy)", "Occultist", currentHp = 580, maxHp = 580, baseAttack = 27, isBoss = true, isTargeted = true, spriteTint = Color(0xFF26A69A), speed = 64, atbGauge = 0.35f),
            Enemy("turkish_lie_flat_seat", "Turkish Airlines Lie-Flat Pod", "Vanguard", currentHp = 370, maxHp = 370, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFFEF5350), speed = 54, atbGauge = 0.25f)
        ),
        initialParty = createFullAmericaParty()
    )

    // Track A — Act V Ch 5 Grand Finale Boss: Zohran Cardamom ("Mr. Cardamom") & The All-Star Standard-Bearers
    val ACT5_TRACK_A_GRAND_FINALE = EncounterDefinition(
        id = "act5_track_a_grand_finale",
        name = "Act V Grand Finale (Track A): Mr. Cardamom & The All-Star Relay",
        description = "Zohran Cardamom ('Mr. Cardamom') flips sizzling chapatis alongside Kamala Coconut's Venn Diagrams and Joe Naptime's Double-Scoop Cone!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("zohran_cardamom_boss", "Zohran Cardamom ('Mr. Cardamom')", "Occultist", currentHp = 680, maxHp = 680, baseAttack = 29, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFF7043), speed = 66, atbGauge = 0.40f),
            Enemy("kamala_venn_ally", "Kamala Coconut (3-Circle Venn)", "Occultist", currentHp = 480, maxHp = 480, baseAttack = 24, isBoss = true, isTargeted = false, spriteTint = Color(0xFFBA68C8), speed = 62, atbGauge = 0.30f),
            Enemy("joe_naptime_ally", "Joe Naptime (2-Scoop Waffle Cone)", "Vanguard", currentHp = 480, maxHp = 480, baseAttack = 24, isBoss = true, isTargeted = false, spriteTint = Color(0xFFFFF59D), speed = 58, atbGauge = 0.25f)
        ),
        initialParty = createFullAmericaParty()
    )

    // Track B — Act V Ch 1 Battle: Real-Estate Rent-Board Lobbyist & Bodega Price-Tag Inspector
    val ACT5_TRACK_B_LANDLORD_LOBBY_BATTLE = EncounterDefinition(
        id = "act5_track_b_landlord_lobby_battle",
        name = "Act V (Track B) - Ch 1: 'Nani' Chapati Truck & Rent Freeze",
        description = "Zohran Cardamom ('Mr. Cardamom') and Mayor Swagger defend the Astoria chapati truck against Rent-Board Lobbyists!",
        environment = BattleEnvironment.FOREST,
        enemies = listOf(
            Enemy("rent_board_lobbyist", "Luxury Condo Rent-Hike Lobbyist", "Occultist", currentHp = 490, maxHp = 490, baseAttack = 24, isBoss = true, isTargeted = true, spriteTint = Color(0xFF8D6E63), speed = 60, atbGauge = 0.35f),
            Enemy("mta_turnstile_drone", "Q70 Bus Fare Inspector", "Sniper", currentHp = 330, maxHp = 330, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF42A5F5), speed = 56, atbGauge = 0.25f)
        ),
        initialParty = createAct5TrackBParty()
    )

    // Track B — Act V Ch 2 Battle: Subpoena Passcode Cracker & Brooklyn Rat-Summit Inspector
    val ACT5_TRACK_B_TURKISH_AUDITOR_BATTLE = EncounterDefinition(
        id = "act5_track_b_turkish_auditor_battle",
        name = "Act V (Track B) - Ch 2: 6-Digit Passcodes & Chilean Sea Bass",
        description = "Subpoena Passcode Crackers and Rat-Ticket Inspectors ambush Mayor Swagger outside Osteria La Baia!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("passcode_cracker", "6-Digit iPhone Passcode Cracker", "Sniper", currentHp = 510, maxHp = 510, baseAttack = 25, isBoss = true, isTargeted = true, spriteTint = Color(0xFF7E57C2), speed = 62, atbGauge = 0.35f),
            Enemy("rat_summit_inspector", "Brooklyn Townhouse Rat Inspector", "Vanguard", currentHp = 350, maxHp = 350, baseAttack = 21, isTargeted = false, spriteTint = Color(0xFF90A4AE), speed = 54, atbGauge = 0.25f)
        ),
        initialParty = createAct5TrackBParty()
    )

    // Track B — Act V Ch 3 Battle: Musk-X (Dark Gothic MAGA Star-Jump) & The Porcelain Sink
    val ACT5_TRACK_B_MUSK_STARJUMP_BATTLE = EncounterDefinition(
        id = "act5_track_b_musk_starjump_battle",
        name = "Act V (Track B) - Ch 3: Mid-Air 'X' Star-Jump & Porcelain Sink",
        description = "Musk-X leaps into an X-shape mid-air exposing four inches of midriff while hurling a porcelain bathroom sink!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("musk_x_starjump_boss", "Musk-X (Mid-Air 'X' Star-Jump)", "Occultist", currentHp = 580, maxHp = 580, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFF26C6DA), speed = 66, atbGauge = 0.40f),
            Enemy("porcelain_sink_golem", "'Let That Sink In' Porcelain Basin", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 21, isTargeted = false, spriteTint = Color(0xFFECEFF1), speed = 54, atbGauge = 0.25f)
        ),
        initialParty = createAct5TrackBParty()
    )

    // Track B — Act V Ch 4 Battle: The DOGE Subcommittee Trio (JD Pants, Wizard Kash & Foghorn Kennedy)
    val ACT5_TRACK_B_DOGE_SQUAD_BATTLE = EncounterDefinition(
        id = "act5_track_b_doge_squad_battle",
        name = "Act V (Track B) - Ch 4: Donuts, Wizard Books & Oxford Drawls",
        description = "JD Pants, Wizard Kash, and Senator Foghorn Kennedy defend the Shiba Inu DOGE hearing room!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("vance_doge_boss", "JD Pants ('Whatever Makes Sense')", "Vanguard", currentHp = 480, maxHp = 480, baseAttack = 24, isBoss = true, isTargeted = true, spriteTint = Color(0xFF5C6BC0), speed = 60, atbGauge = 0.35f),
            Enemy("kash_doge_boss", "Wizard Kash (\$19.99 King Book)", "Occultist", currentHp = 450, maxHp = 450, baseAttack = 23, isBoss = true, isTargeted = false, spriteTint = Color(0xFFAB47BC), speed = 62, atbGauge = 0.30f),
            Enemy("kennedy_doge_boss", "Foghorn Kennedy (Oxford Drawl)", "Sniper", currentHp = 450, maxHp = 450, baseAttack = 23, isBoss = true, isTargeted = false, spriteTint = Color(0xFF78909C), speed = 58, atbGauge = 0.25f)
        ),
        initialParty = createAct5TrackBParty()
    )

    // Track B — Act V Ch 5 Grand Finale Boss: Commander America, Musk-X & Hulkster 24-Pythons
    val ACT5_TRACK_B_GRAND_FINALE = EncounterDefinition(
        id = "act5_track_b_grand_finale",
        name = "Act V Grand Finale (Track B): Golden Podium & Dark Gothic MAGA",
        description = "Zohran Cardamom and Mayor Swagger clash with Commander America, Musk-X's Mid-Air Star-Jump, and the Diet Coke Valet Button!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("commander_finale_boss", "Commander America (Golden Podium)", "Vanguard", currentHp = 680, maxHp = 680, baseAttack = 28, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFD700), speed = 64, atbGauge = 0.40f),
            Enemy("musk_x_finale_ally", "Musk-X (Dark Gothic Star-Jump)", "Occultist", currentHp = 490, maxHp = 490, baseAttack = 25, isBoss = true, isTargeted = false, spriteTint = Color(0xFF26C6DA), speed = 66, atbGauge = 0.30f),
            Enemy("diet_coke_mecha", "Red Wooden Diet Coke Valet Turret", "Sniper", currentHp = 380, maxHp = 380, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFFEF5350), speed = 56, atbGauge = 0.25f)
        ),
        initialParty = createAct5TrackBParty()
    )

    // 3a. Act III - Chapter 1: The Ohio Steel Mill Awakening
    val ACT3_CH1_MILL_BATTLE = EncounterDefinition(
        id = "act3_ch1_mill_battle",
        name = "Act III - Ch 1: Globalist Trade Cartel",
        description = "Globalist Trade Cartel lawyers and WTO saboteurs deploy cease-and-desist filings to shut down the Mahoning Valley blast furnaces!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("cartel_lawyer", "Globalist Trade Cartel Envoy", "Occultist", currentHp = 380, maxHp = 380, baseAttack = 24, isBoss = true, isTargeted = true, spriteTint = Color(0xFF5E35B1), speed = 62, atbGauge = 0.35f),
            Enemy("wto_saboteur", "WTO Complaint Lobbyist", "Sniper", currentHp = 260, maxHp = 260, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF7E57C2), speed = 55, atbGauge = 0.25f)
        ),
        initialParty = createAct3Party() // Commander America, Marco Rubio, JD Vance
    )

    // 3b. Act III - Chapter 3: The Diplomatic Flight on Air Force One
    val ACT3_CH3_INTERCEPT_BATTLE = EncounterDefinition(
        id = "act3_ch3_intercept_battle",
        name = "Act III - Ch 3: Deep State Intercept Squadron",
        description = "Deep State interceptors transmit sabotaged diplomatic cables from 41,000 feet to derail the historic DMZ summit!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("intercept_commander", "Intercept Disinformation Director", "Sniper", currentHp = 420, maxHp = 420, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFFD81B60), speed = 66, atbGauge = 0.40f),
            Enemy("cable_saboteur", "Phantom Cable Leaker", "Occultist", currentHp = 280, maxHp = 280, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF8E24AA), speed = 58, atbGauge = 0.30f)
        ),
        initialParty = createAct3Party()
    )

    // 3c. Act III - Chapter 4: Summit of Peace Duel (Boss Encounter)
    val ACT3_SUMMIT_BATTLE = EncounterDefinition(
        id = "act3_summit_battle",
        name = "Act III: Summit of Peace Duel",
        description = "Chairman Rocket Man tests Commander America's handshake strength on the Crimson Summit!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("rocket_man", "Chairman Rocket Man", "Vanguard", currentHp = 550, maxHp = 550, baseAttack = 30, isTargeted = true, spriteTint = Color(0xFFD32F2F), speed = 55, atbGauge = 0.30f),
            Enemy("missile_battery", "Parade Missile Battery", "Sniper", currentHp = 300, maxHp = 300, baseAttack = 25, isTargeted = false, spriteTint = Color(0xFFFF7043), speed = 45, atbGauge = 0.20f)
        ),
        initialParty = createAct3Party()
    )

    // 4a. Act IV - Chapter 1: The FISA Warrant Labyrinth
    val ACT4_CH1_FISA_BATTLE = EncounterDefinition(
        id = "act4_ch1_fisa_battle",
        name = "Act IV - Ch 1: Archivist Phantoms",
        description = "Bureau Archivist Phantoms and unauthorized redaction officers attempt to shred the declassified FISA memos!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("archivist_boss", "Chief Bureau Archivist", "Occultist", currentHp = 460, maxHp = 460, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFF7E57C2), speed = 64, atbGauge = 0.35f),
            Enemy("redaction_guard", "Redaction Marker Sentinel", "Vanguard", currentHp = 320, maxHp = 320, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF546E7A), speed = 52, atbGauge = 0.25f)
        ),
        initialParty = createAct4Party() // Commander America, Marco Rubio, JD Vance, Kash Patel
    )

    // 4b. Act IV - Chapter 3: The Phantom Subpoena Ambush
    val ACT4_CH3_SUBPOENA_BATTLE = EncounterDefinition(
        id = "act4_ch3_subpoena_battle",
        name = "Act IV - Ch 3: Process Server Swarm",
        description = "Process Server Phantoms and lawfare retainers swarm the courthouse steps with reams of stamped subpoenas!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("process_server_boss", "Chief Process Server", "Sniper", currentHp = 480, maxHp = 480, baseAttack = 28, isBoss = true, isTargeted = true, spriteTint = Color(0xFFEC407A), speed = 68, atbGauge = 0.40f),
            Enemy("lawfare_clerk", "Lawfare Retainer Clerk", "Occultist", currentHp = 300, maxHp = 300, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFFAB47BC), speed = 56, atbGauge = 0.30f)
        ),
        initialParty = createAct4Party()
    )

    // 4c. Act IV - Chapter 4: The Mueller Illusion Hoax Climax (Boss Encounter)
    val ACT4_HOAX_BATTLE = EncounterDefinition(
        id = "act4_hoax_battle",
        name = "Act IV: The Mueller Illusion Hoax",
        description = "A spectral Russian collusion ghost and red-tape subpoenas fill the chamber with nothingburgers!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("mueller_ghost", "Phantom Mueller Hoax", "Occultist", currentHp = 600, maxHp = 600, baseAttack = 28, isTargeted = true, spriteTint = Color(0xFF90A4AE), speed = 60, atbGauge = 0.35f),
            Enemy("subpoena_swarm", "Swarm of Redacted Subpoenas", "Sniper", currentHp = 280, maxHp = 280, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFB0BEC5), speed = 68, atbGauge = 0.40f)
        ),
        initialParty = createAct4Party()
    )

    // 5. Act V: Midnight Ballot Golem
    // 5a. Act V - Chapter 1: The 3:00 AM Water Pipe Mystery
    val ACT5_CH1_PIPE_BATTLE = EncounterDefinition(
        id = "act5_ch1_pipe_battle",
        name = "Act V - Ch 1: The 3 AM Water Pipe Mystery",
        description = "The Burst Pipe Illusionist and Midnight Ballot Courier deploy vertical vote spikes under cover of darkness!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("burst_pipe", "Burst Pipe Illusionist", "Occultist", currentHp = 440, maxHp = 440, baseAttack = 26, isBoss = true, isTargeted = true, spriteTint = Color(0xFF29B6F6), speed = 58, atbGauge = 0.35f),
            Enemy("ballot_courier", "Midnight Ballot Courier", "Vanguard", currentHp = 320, maxHp = 320, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF546E7A), speed = 50, atbGauge = 0.25f)
        ),
        initialParty = createAct4Party() // Commander America, Marco Rubio, JD Vance, Kash Patel
    )

    // 5b. Act V - Chapter 2: The Senate Inquest
    val ACT5_CH2_INQUEST_BATTLE = EncounterDefinition(
        id = "act5_ch2_inquest_battle",
        name = "Act V - Ch 2: The Senate Inquest",
        description = "Stonewalling election officials and red-tape bureaucrats attempt to filibuster the Senate Judiciary hearing!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("stonewall_director", "Bureaucratic Stonewall Director", "Occultist", currentHp = 500, maxHp = 500, baseAttack = 28, isBoss = true, isTargeted = true, spriteTint = Color(0xFF7E57C2), speed = 60, atbGauge = 0.35f),
            Enemy("red_tape_gargoyle", "Red-Tape Stonewaller", "Vanguard", currentHp = 350, maxHp = 350, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF8D6E63), speed = 48, atbGauge = 0.25f)
        ),
        initialParty = createFullAmericaParty() // All 5 heroes including Senator Kennedy!
    )

    // 5c. Act V - Chapter 4: Midnight Ballot Golem & Raid Phantoms (Boss Encounter)
    val ACT5_GOLEM_BATTLE = EncounterDefinition(
        id = "act5_golem_battle",
        name = "Act V: Midnight Ballot Golem",
        description = "Spawned from 3 AM sorcery and Mar-a-Lago raid mandates, the Midnight Ballot Golem rises to seize the golden closets!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("ballot_golem", "3 AM Midnight Ballot Golem", "Vanguard", currentHp = 700, maxHp = 700, baseAttack = 32, isBoss = true, isTargeted = true, spriteTint = Color(0xFF546E7A), speed = 42, atbGauge = 0.25f),
            Enemy("raid_commander", "Mar-a-Lago Raid Commander", "Sniper", currentHp = 360, maxHp = 360, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFFEC407A), speed = 65, atbGauge = 0.35f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 6. Act VI: The Word-Salad Wasteland
    // 6a. Act VI - Chapter 1: The Border Czar Inspection
    val ACT6_CH1_BORDER_BATTLE = EncounterDefinition(
        id = "act6_ch1_border_battle",
        name = "Act VI - Ch 1: The Border Czar Inspection",
        description = "Cartel Smuggler Couriers and Catch-and-Release Liaisons attempt to swarm the unfinished gap in the southern border wall!",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("border_czar_liaison", "Catch-and-Release Liaison", "Occultist", currentHp = 520, maxHp = 520, baseAttack = 28, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFB300), speed = 62, atbGauge = 0.35f),
            Enemy("cartel_courier", "Cartel Smuggler Courier", "Sniper", currentHp = 340, maxHp = 340, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFFE53935), speed = 68, atbGauge = 0.40f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 6b. Act VI - Chapter 3: The Electric School Bus Mire
    val ACT6_CH3_BUS_BATTLE = EncounterDefinition(
        id = "act6_ch3_bus_battle",
        name = "Act VI - Ch 3: The Electric School Bus Mire",
        description = "A Stranded Battery Golem and Federal Green Mandate Drones attempt to siphon the party's energy into non-existent chargers!",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("battery_golem", "Stranded EV Battery Golem", "Vanguard", currentHp = 650, maxHp = 650, baseAttack = 30, isBoss = true, isTargeted = true, spriteTint = Color(0xFF26A69A), speed = 44, atbGauge = 0.25f),
            Enemy("mandate_drone", "Federal Green Mandate Drone", "Sniper", currentHp = 350, maxHp = 350, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFF81C784), speed = 66, atbGauge = 0.35f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 6c. Act VI - Chapter 5: The Word-Salad Wasteland (Kamala Boss Encounter)
    val ACT6_KAMALA_BATTLE = EncounterDefinition(
        id = "act6_kamala_battle",
        name = "Act VI: The Word-Salad Wasteland",
        description = "Kamala the Unburdened traps the hero in endless overlapping Venn diagrams and circular speeches!",
        environment = BattleEnvironment.SWAMP,
        enemies = listOf(
            Enemy("kamala", "Kamala the Unburdened", "Occultist", currentHp = 750, maxHp = 750, baseAttack = 34, isTargeted = true, spriteTint = Color(0xFFFFCA28), speed = 65, atbGauge = 0.40f),
            Enemy("yellow_venn", "Yellow Venn Loop", "Sniper", currentHp = 340, maxHp = 340, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFFFFEB3B), speed = 70, atbGauge = 0.45f),
            Enemy("blue_venn", "Blue Venn Loop", "Vanguard", currentHp = 360, maxHp = 360, baseAttack = 24, isTargeted = false, spriteTint = Color(0xFF42A5F5), speed = 50, atbGauge = 0.30f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 7. Act VII: The Lawfare Labyrinth & Steel Resolve
    // 7a. Act VII - Chapter 1: The 91 Indictment Gauntlet
    val ACT7_CH1_INDICTMENT_BATTLE = EncounterDefinition(
        id = "act7_ch1_indictment_battle",
        name = "Act VII - Ch 1: The 91 Indictment Gauntlet",
        description = "Partisan Prosecutors and Lawfare Docket Clerks attempt to bury Commander America beneath 91 fabricated counts!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("partisan_prosecutor", "Chief Partisan Prosecutor", "Occultist", currentHp = 560, maxHp = 560, baseAttack = 30, isBoss = true, isTargeted = true, spriteTint = Color(0xFF8E24AA), speed = 62, atbGauge = 0.35f),
            Enemy("docket_clerk", "Lawfare Docket Clerk", "Sniper", currentHp = 360, maxHp = 360, baseAttack = 26, isTargeted = false, spriteTint = Color(0xFF5D4037), speed = 66, atbGauge = 0.40f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 7b. Act VII - Chapter 2: The Gag Order Guillotine
    val ACT7_CH2_CONTEMPT_BATTLE = EncounterDefinition(
        id = "act7_ch2_contempt_battle",
        name = "Act VII - Ch 2: The Gag Order Guillotine",
        description = "A Gag Order Magistrate and Contempt Bailiffs attempt to silence the coalition with unconstitutional gag orders!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("gag_magistrate", "Gag Order Magistrate", "Vanguard", currentHp = 600, maxHp = 600, baseAttack = 32, isBoss = true, isTargeted = true, spriteTint = Color(0xFFC2185B), speed = 46, atbGauge = 0.25f),
            Enemy("contempt_bailiff", "Contempt Bailiff", "Sniper", currentHp = 380, maxHp = 380, baseAttack = 28, isTargeted = false, spriteTint = Color(0xFF37474F), speed = 64, atbGauge = 0.35f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 7c. Act VII - Chapter 5: The Milwaukee Arena Coronation (Lawfare Lich Boss Battle)
    val ACT7_LAWFARE_BATTLE = EncounterDefinition(
        id = "act7_lawfare_battle",
        name = "Act VII: Sands of Resolve & Steel Ear",
        description = "The Lawfare Grand Inquisitor attempts to overwhelm Commander America with 91 bogus indictments!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("lawfare_lich", "Lawfare Grand Inquisitor", "Occultist", currentHp = 850, maxHp = 850, baseAttack = 36, isTargeted = true, spriteTint = Color(0xFF8E24AA), speed = 60, atbGauge = 0.35f),
            Enemy("indictment_gargoyle_1", "Indictment Gargoyle #47", "Vanguard", currentHp = 400, maxHp = 400, baseAttack = 28, isTargeted = false, spriteTint = Color(0xFF5D4037), speed = 48, atbGauge = 0.25f),
            Enemy("indictment_gargoyle_2", "Indictment Gargoyle #91", "Sniper", currentHp = 380, maxHp = 380, baseAttack = 30, isTargeted = false, spriteTint = Color(0xFF4E342E), speed = 62, atbGauge = 0.30f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 8. Act VIII: Eradicating Malarkey
    // 8a. Act VIII - Chapter 1: The Basement Bunker Studio
    val ACT8_CH1_BUNKER_BATTLE = EncounterDefinition(
        id = "act8_ch1_bunker_battle",
        name = "Act VIII - Ch 1: Basement Bunker Studio",
        description = "A Virtual Studio Director and Green Screen Technicians try to keep the fake White House studio set running!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("studio_director", "Virtual Studio Director", "Occultist", currentHp = 620, maxHp = 620, baseAttack = 32, isBoss = true, isTargeted = true, spriteTint = Color(0xFF00897B), speed = 58, atbGauge = 0.35f),
            Enemy("screen_technician", "Green Screen Technician", "Sniper", currentHp = 380, maxHp = 380, baseAttack = 28, isTargeted = false, spriteTint = Color(0xFF43A047), speed = 65, atbGauge = 0.40f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 8b. Act VIII - Chapter 2: The Teleprompter Sorcery
    val ACT8_CH2_TELEPROMPTER_BATTLE = EncounterDefinition(
        id = "act8_ch2_teleprompter_battle",
        name = "Act VIII - Ch 2: The Teleprompter Sorcery",
        description = "A Teleprompter Sorcerer Phantom and Scriptwriter Aides beam contradictory talking points directly into the cameras!",
        environment = BattleEnvironment.DUNGEON,
        enemies = listOf(
            Enemy("teleprompter_sorcerer", "Teleprompter Sorcerer Phantom", "Occultist", currentHp = 660, maxHp = 660, baseAttack = 34, isBoss = true, isTargeted = true, spriteTint = Color(0xFF0288D1), speed = 60, atbGauge = 0.35f),
            Enemy("scriptwriter_aide", "Scriptwriter Aide", "Sniper", currentHp = 400, maxHp = 400, baseAttack = 30, isTargeted = false, spriteTint = Color(0xFF7E57C2), speed = 64, atbGauge = 0.38f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 8c. Act VIII - Chapter 3: The Mechanical Auto-Pen
    val ACT8_CH3_AUTOPEN_BATTLE = EncounterDefinition(
        id = "act8_ch3_autopen_battle",
        name = "Act VIII - Ch 3: The Mechanical Auto-Pen",
        description = "A Mechanical Auto-Pen Sovereign and Omnibus Mandate Drones attempt to sign endless emergency declarations without presidential consciousness!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("autopen_sovereign", "Mechanical Auto-Pen Sovereign", "Vanguard", currentHp = 720, maxHp = 720, baseAttack = 36, isBoss = true, isTargeted = true, spriteTint = Color(0xFFFFB300), speed = 50, atbGauge = 0.30f),
            Enemy("mandate_drone", "Omnibus Mandate Drone", "Sniper", currentHp = 420, maxHp = 420, baseAttack = 30, isTargeted = false, spriteTint = Color(0xFFFB8C00), speed = 66, atbGauge = 0.40f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 8d. Act VIII - Chapter 4: Eradicating Malarkey - Final Boss
    val ACT8_BIDEN_FINAL_BATTLE = EncounterDefinition(
        id = "act8_biden_final_battle",
        name = "Act VIII: Eradicating Malarkey - Final Boss",
        description = "Biden the Malarkey Sovereign summons vanilla ice cream cones and teleprompter phantoms in the cosmic throne room!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("biden", "Biden the Malarkey Sovereign", "Occultist", currentHp = 1200, maxHp = 1200, baseAttack = 40, isTargeted = true, spriteTint = Color(0xFFE91E63), speed = 45, atbGauge = 0.30f),
            Enemy("shadow_teleprompter", "Teleprompter Conduit", "Sniper", currentHp = 450, maxHp = 450, baseAttack = 32, isTargeted = false, spriteTint = Color(0xFF26A69A), speed = 65, atbGauge = 0.40f),
            Enemy("vanilla_cone_golem", "Two-Scoop Vanilla Golem", "Vanguard", currentHp = 500, maxHp = 500, baseAttack = 30, isTargeted = false, spriteTint = Color(0xFFFFF9C4), speed = 40, atbGauge = 0.20f)
        ),
        initialParty = createFullAmericaParty()
    )

    // Helper to generate reinforcement minions dynamically
    fun createMinion(idSuffix: String, name: String = "Blighted Minion", subtitle: String = "Minion", hp: Int = 180): Enemy {
        return Enemy(
            id = "minion_$idSuffix",
            name = name,
            subtitle = subtitle,
            currentHp = hp,
            maxHp = hp,
            baseAttack = 18,
            isTargeted = false,
            spriteTint = Color(0xFF80CBC4),
            speed = 55,
            atbGauge = 0f
        )
    }

    val ALL_ENCOUNTERS = listOf(
        ACT1_LOBBY_BATTLE,
        ACT1_CH1_PRESS_BATTLE,
        ACT1_CH2_DEBATE_BATTLE,
        ACT1_CH3_MACHINE_BATTLE,
        ACT1_TRACK_A_BARRY_BATTLE,
        ACT1_TRACK_A_HILL_ROD_FINALE,
        ACT1_TRACK_B_FERNS_BATTLE,
        ACT1_TRACK_B_SPICY_SEAN_BATTLE,
        ACT1_TRACK_B_COMMANDER_FINALE,
        ACT2_CH1_INAUGURATION_BATTLE,
        ACT2_MEDIA_BATTLE,
        ACT2_TRACK_A_SHARPIE_BATTLE,
        ACT2_TRACK_B_SPICER_SALSA_BATTLE,
        ACT2_TRACK_B_KASH_BATTLE,
        ACT2_TRACK_B_RUDY_FINALE,
        ACT3_CH1_MILL_BATTLE,
        ACT3_CH3_INTERCEPT_BATTLE,
        ACT3_SUMMIT_BATTLE,
        ACT3_TRACK_A_STAIRS_BIKE_BATTLE,
        ACT3_TRACK_A_CHANDELIER_BATTLE,
        ACT3_TRACK_A_BIDEN_ICE_CREAM_FINALE,
        ACT3_TRACK_B_CORN_POP_BATTLE,
        ACT3_TRACK_B_FOGHORN_BATTLE,
        ACT3_TRACK_B_MUGSHOT_FINALE,
        ACT4_CH1_FISA_BATTLE,
        ACT4_CH3_SUBPOENA_BATTLE,
        ACT4_HOAX_BATTLE,
        ACT4_TRACK_A_DONUT_BATTLE,
        ACT4_TRACK_B_VENN_CRITICS_BATTLE,
        ACT4_TRACK_B_VANCE_DONUT_BATTLE,
        ACT4_TRACK_B_CONCEPTS_DEBATE_BATTLE,
        ACT4_TRACK_B_MCDONALDS_FINALE,
        ACT5_TRACK_A_CYBERTRUCK_SINK_BATTLE,
        ACT5_TRACK_A_DOGE_BUREAUCRATS_BATTLE,
        ACT5_TRACK_A_SWAGGER_TURKISH_BATTLE,
        ACT5_TRACK_A_GRAND_FINALE,
        ACT5_TRACK_B_LANDLORD_LOBBY_BATTLE,
        ACT5_TRACK_B_TURKISH_AUDITOR_BATTLE,
        ACT5_TRACK_B_MUSK_STARJUMP_BATTLE,
        ACT5_TRACK_B_DOGE_SQUAD_BATTLE,
        ACT5_TRACK_B_GRAND_FINALE,
        ACT5_CH1_PIPE_BATTLE,
        ACT5_CH2_INQUEST_BATTLE,
        ACT5_GOLEM_BATTLE,
        ACT6_CH1_BORDER_BATTLE,
        ACT6_CH3_BUS_BATTLE,
        ACT6_KAMALA_BATTLE,
        ACT7_CH1_INDICTMENT_BATTLE,
        ACT7_CH2_CONTEMPT_BATTLE,
        ACT7_LAWFARE_BATTLE,
        ACT8_CH1_BUNKER_BATTLE,
        ACT8_CH2_TELEPROMPTER_BATTLE,
        ACT8_CH3_AUTOPEN_BATTLE,
        ACT8_BIDEN_FINAL_BATTLE,
        PROLOGUE_SOLO,
        FOREST_AMBUSH,
        DUNGEON_DESCENT,
        CASTLE_HORDE,
        CAVE_BROODMOTHER,
        MARSH_RESCUE,
        SWAMP_BEHEMOTH,
        BLIGHT_TRACKERS,
        CH3_SENTINELS,
        CH7_MIRE_WYRM,
        CH8_EXECUTIONER_AMBUSH,
        CH9_GALAHAULT_TRIAL,
        CH10_BROODMOTHER_TRIAL,
        CH11_NOCTURNE_TRIAL,
        CH12_WARMASTER_OUROS,
        CH13_COMMANDER_VAELOR,
        CH14_ABYSSAL_LEVIATHAN,
        CH15_ARCHON_CUSTODIANS,
        CH16_MALAKOR_FINALE,
        CH9_PENITENT_GATE,
        CH10_THICKET_GUARDIANS,
        CH11_GORGE_STALKERS,
        CH11_ARCHIVE_ENFORCERS,
        CH12_AUTOMATON_PATROL,
        CH13_PLAZA_LEGION,
        CH14_SHALLOWS_HUSKS,
        CH14_TRENCH_LEECHES,
        CH15_TRIAL_GOLD,
        CH15_TRIAL_GROVE,
        CH15_TRIAL_SHADOW,
        CH16_NULLIFIER_GATE,
        CH16_MIRROR_GAUNTLET
    )
}
