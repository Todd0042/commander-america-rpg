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

    // 2a. Act II - Chapter 1: The Inaugural Address — Aerial Spin Crew Ambush
    val ACT2_CH1_INAUGURATION_BATTLE = EncounterDefinition(
        id = "act2_ch1_inauguration_battle",
        name = "Act II - Ch 1: The Inaugural Aerial Spin Crew",
        description = "Cable News Aerial Spin Crew and a Hostile Fact-Checker deploy fabricated crowd-size graphics to undermine the inauguration!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("aerial_spin_anchor", "Cable News Aerial Spin Anchor", "Sniper", currentHp = 320, maxHp = 320, baseAttack = 20, isBoss = true, isTargeted = true, spriteTint = Color(0xFFEF5350), speed = 68, atbGauge = 0.40f),
            Enemy("hostile_fact_checker", "Hostile Fisheye Fact-Checker", "Occultist", currentHp = 240, maxHp = 240, baseAttack = 18, isTargeted = false, spriteTint = Color(0xFF42A5F5), speed = 60, atbGauge = 0.30f)
        ),
        initialParty = createAct1Party()   // Ch1: solo Commander America before Marco joins
    )

    // 2b. Act II: Media Wars & The Insider Stock Lich (Chapter 3 Boss encounter)
    val ACT2_MEDIA_BATTLE = EncounterDefinition(
        id = "act2_media_battle",
        name = "Act II: Media Wars & The Insider Stock Lich",
        description = "Madam Speaker Pelosi and fact-checker ghouls attempt to crash the economy with insider trading spells!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("pelosi", "Madam Pelosi (Stock Lich)", "Occultist", currentHp = 450, maxHp = 450, baseAttack = 28, isTargeted = true, spriteTint = Color(0xFFAB47BC), speed = 65, atbGauge = 0.35f),
            Enemy("fact_checker", "Biased Fact-Checker Ghoul", "Sniper", currentHp = 240, maxHp = 240, baseAttack = 22, isTargeted = false, spriteTint = Color(0xFF42A5F5), speed = 58, atbGauge = 0.25f),
            Enemy("anonymous_source", "Anonymous Source Phantom", "Vanguard", currentHp = 260, maxHp = 260, baseAttack = 20, isTargeted = false, spriteTint = Color(0xFF7E57C2), speed = 50, atbGauge = 0.20f)
        ),
        initialParty = createAct2Party()
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

    // 4. Act IV: The Mueller Illusion Hoax
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
    val ACT5_GOLEM_BATTLE = EncounterDefinition(
        id = "act5_golem_battle",
        name = "Act V: Midnight Ballot Golem",
        description = "Spawned from 3 AM sorcery and mysterious water pipe leaks, the Ballot Golem rises!",
        environment = BattleEnvironment.CASTLE,
        enemies = listOf(
            Enemy("ballot_golem", "3 AM Midnight Ballot Golem", "Vanguard", currentHp = 700, maxHp = 700, baseAttack = 32, isTargeted = true, spriteTint = Color(0xFF546E7A), speed = 42, atbGauge = 0.25f),
            Enemy("burst_pipe", "Burst Pipe Illusionist", "Occultist", currentHp = 320, maxHp = 320, baseAttack = 25, isTargeted = false, spriteTint = Color(0xFF29B6F6), speed = 55, atbGauge = 0.35f)
        ),
        initialParty = createFullAmericaParty()
    )

    // 6. Act VI: The Word-Salad Wasteland
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

    // 7. Act VII: Sands of Resolve & Steel Ear
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

    // 8. Act VIII: Eradicating Malarkey - Final Boss
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
        ACT2_CH1_INAUGURATION_BATTLE,
        ACT2_MEDIA_BATTLE,
        ACT3_CH1_MILL_BATTLE,
        ACT3_CH3_INTERCEPT_BATTLE,
        ACT3_SUMMIT_BATTLE,
        ACT4_HOAX_BATTLE,
        ACT5_GOLEM_BATTLE,
        ACT6_KAMALA_BATTLE,
        ACT7_LAWFARE_BATTLE,
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
