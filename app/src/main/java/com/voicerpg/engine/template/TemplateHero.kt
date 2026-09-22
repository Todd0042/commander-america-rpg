package com.voicerpg.engine.template

import androidx.compose.ui.graphics.Color
import com.voicerpg.engine.model.PartyMember
import com.voicerpg.engine.model.Spell
import com.voicerpg.engine.model.SpellSchool

/**
 * Playable Heroes and Spells for Commander America and his 4 Companions.
 * - Commander America: Commander-in-Chief (Expanded Presidential Arsenal)
 * - Marco Rubio: Sanctions Sorcerer (Filibuster, Sanctions, Hydrate, Tariffs)
 * - JD Vance: Rustbelt Vanguard (Debate, Appalachian Slam, Fortitude, Common Sense)
 * - Kash Patel: Intel Inquisitor (Declassify, Binder of Truth, Security Clearance, Plug Leaks)
 * - RFK Jr.: Grand Health Alchemist (Seed Oils, Chronic Disease Purge, Raw Milk, Electrolytes)
 */
object TemplateHero {

    // =========================================================================
    // 1. COMMANDER AMERICA SPELLS (Expanded Presidential Kit)
    // =========================================================================

    val TREMENDOUS_STRIKE = Spell(
        id = "ca_tremendous_strike",
        name = "Tremendous Strike",
        school = SpellSchool.PHYSICAL,
        basePower = 65,
        mpCost = 0,
        description = "A tremendously powerful patriotic strike that pulverizes political obstruction.",
        exampleChant = "Tremendous strike!",
        aliases = listOf("tremendous", "strike", "tremendous strike", "smash", "punch", "tremendous blow", "victory strike")
    )

    val ART_OF_THE_DEAL = Spell(
        id = "ca_art_of_the_deal",
        name = "Art of the Deal",
        school = SpellSchool.HOLY,
        basePower = 50,
        mpCost = 20,
        hitsAll = true,
        description = "Imposes unmatched negotiation power, damaging and confusing all enemies.",
        exampleChant = "Art of the deal!",
        aliases = listOf("art of the deal", "deal", "negotiate", "trade", "billion")
    )

    val OBAMA_DID_IT_FIRST = Spell(
        id = "ca_obama_did_it_first",
        name = "Obama Did It First!",
        school = SpellSchool.SHADOW,
        basePower = 80,
        mpCost = 25,
        status = "OVERLOAD",
        description = "Exposes historical receipts! Inflicts devastating damage and stuns the target with hypocrisy paralysis.",
        exampleChant = "Obama did it first!",
        aliases = listOf("obama did it first", "obama", "receipts", "hypocrisy", "check the dates", "2014")
    )

    val PRESIDENTIAL_VETO = Spell(
        id = "ca_presidential_veto",
        name = "Presidential Veto",
        school = SpellSchool.PHYSICAL,
        basePower = 75,
        mpCost = 20,
        status = "OVERLOAD",
        description = "Vetoes enemy actions with constitutional authority! Inflicts heavy damage and Stun.",
        exampleChant = "Presidential veto!",
        aliases = listOf("presidential veto", "veto", "shut down", "deny", "block", "override")
    )

    val EXECUTIVE_ACTION = Spell(
        id = "ca_executive_action",
        name = "Executive Action",
        school = SpellSchool.HOLY,
        basePower = 90,
        mpCost = 35,
        hitsAll = true,
        description = "Issues an immediate sweeping presidential order that strikes all opposition with immense force.",
        exampleChant = "Executive action!",
        aliases = listOf("executive action", "decree", "order", "executive order", "signing")
    )

    val BUILD_THE_WALL = Spell(
        id = "ca_build_the_wall",
        name = "Build The Wall",
        school = SpellSchool.PHYSICAL,
        basePower = 0,
        mpCost = 15,
        isGuard = true,
        description = "Erects a golden border barrier, reducing incoming damage.",
        exampleChant = "Build the wall!",
        aliases = listOf("build the wall", "wall", "border", "barrier", "fortify")
    )

    val RALLY_THE_PATRIOTS = Spell(
        id = "ca_rally_patriots",
        name = "Rally The Patriots",
        school = SpellSchool.HOLY,
        basePower = 70,
        mpCost = 30,
        isHeal = true,
        description = "Restores massive HP to Commander America and the entire party.",
        exampleChant = "Rally the patriots!",
        aliases = listOf("rally", "patriots", "rally the patriots", "heal", "maga", "freedom")
    )

    val TIRED_OF_WINNING = Spell(
        id = "ca_tired_of_winning",
        name = "Tired of Winning",
        school = SpellSchool.HOLY,
        basePower = 0,
        mpCost = 0,
        manaRestorePct = 0.35f,
        description = "Commander America pauses because there is simply so much winning, recovering 35% MP.",
        exampleChant = "Tired of winning!",
        aliases = listOf("tired of winning", "winning", "win", "so much winning", "winning streak")
    )

    fun createTemplateAdventurer(
        id: String = "commander_america",
        name: String = "Commander America",
        currentHp: Int = 5000,
        maxHp: Int = 5000,
        currentMp: Int = 1776,
        maxMp: Int = 1776
    ): PartyMember {
        return PartyMember(
            id = id,
            name = name,
            loreClass = "Commander-in-Chief",
            currentHp = currentHp,
            maxHp = maxHp,
            currentMp = currentMp,
            maxMp = maxMp,
            speed = 70,
            defense = 35,
            avatarTint = Color(0xFFFFD700), // Glowing Gold
            spells = listOf(
                TREMENDOUS_STRIKE,
                ART_OF_THE_DEAL,
                OBAMA_DID_IT_FIRST,
                PRESIDENTIAL_VETO,
                EXECUTIVE_ACTION,
                BUILD_THE_WALL,
                RALLY_THE_PATRIOTS,
                TIRED_OF_WINNING
            )
        )
    }

    // =========================================================================
    // 2. MARCO RUBIO (Sanctions Sorcerer - Joins Act II)
    // =========================================================================

    val FILIBUSTER_STRIKE = Spell(
        id = "ca_marco_filibuster",
        name = "Filibuster Strike",
        school = SpellSchool.PHYSICAL,
        basePower = 60,
        mpCost = 15,
        status = "OVERLOAD",
        description = "Delivers an uninterrupted diplomatic marathon speech, stunning the target into submission.",
        exampleChant = "Filibuster strike!",
        aliases = listOf("filibuster", "filibuster strike", "marathon speech", "marco filibuster", "speech")
    )

    val MAXIMUM_SANCTIONS = Spell(
        id = "ca_marco_sanction",
        name = "Maximum Sanctions",
        school = SpellSchool.SHADOW,
        basePower = 55,
        mpCost = 25,
        hitsAll = true,
        description = "Freezes foreign assets and bombards all foes with crippling economic sanctions.",
        exampleChant = "Maximum sanctions!",
        aliases = listOf("sanctions", "maximum sanctions", "embargo", "asset freeze", "sanction all")
    )

    val WATER_SIP_RESTORE = Spell(
        id = "ca_marco_hydrate",
        name = "Water Sip",
        school = SpellSchool.HOLY,
        basePower = 65,
        mpCost = 20,
        isHeal = true,
        description = "Takes a quick, refreshing sip of bottled water, restoring substantial HP to the party.",
        exampleChant = "Water sip!",
        aliases = listOf("water sip", "hydrate", "water bottle", "rubio sip", "water", "sip")
    )

    val TARIFF_DIVIDEND = Spell(
        id = "ca_marco_tariff",
        name = "Tariff Dividend",
        school = SpellSchool.PHYSICAL,
        basePower = 0,
        mpCost = 0,
        manaRestorePct = 0.35f,
        description = "Levies strategic tariffs on foreign adversaries, recovering 35% MP.",
        exampleChant = "Tariff dividend!",
        aliases = listOf("tariff dividend", "tariff", "dividends", "import tax", "levy")
    )

    val MARCO_SPELLS = listOf(
        FILIBUSTER_STRIKE,
        MAXIMUM_SANCTIONS,
        WATER_SIP_RESTORE,
        TARIFF_DIVIDEND
    )

    fun createMarcoRubio(
        id: String = "marco",
        name: String = "Marco Rubio",
        currentHp: Int = 3800,
        maxHp: Int = 3800,
        currentMp: Int = 1200,
        maxMp: Int = 1200
    ): PartyMember {
        return PartyMember(
            id = id,
            name = name,
            loreClass = "Sanctions Sorcerer",
            currentHp = currentHp,
            maxHp = maxHp,
            currentMp = currentMp,
            maxMp = maxMp,
            speed = 65,
            defense = 28,
            avatarTint = Color(0xFF42A5F5), // Diplomatic Blue
            spells = MARCO_SPELLS
        )
    }

    // =========================================================================
    // 3. JD VANCE (Rustbelt Vanguard - Joins Act III)
    // =========================================================================

    val DEBATE_DISMANTLE = Spell(
        id = "ca_vance_debate",
        name = "Debate Dismantle",
        school = SpellSchool.ELECTROMANCY,
        basePower = 70,
        mpCost = 18,
        status = "OVERLOAD",
        description = "Calmly dissects opponent rhetoric on live television, stunning the target into silence.",
        exampleChant = "Debate dismantle!",
        aliases = listOf("debate dismantle", "debate", "dismantle", "dissect", "moderator", "fact check")
    )

    val APPALACHIAN_SLAM = Spell(
        id = "ca_vance_slam",
        name = "Appalachian Slam",
        school = SpellSchool.PHYSICAL,
        basePower = 55,
        mpCost = 25,
        hitsAll = true,
        description = "Unleashes the raw industrial grit of the heartland upon all enemies.",
        exampleChant = "Appalachian slam!",
        aliases = listOf("appalachian slam", "rustbelt slam", "heartland", "hillbilly slam", "slam")
    )

    val HILLBILLY_FORTITUDE = Spell(
        id = "ca_vance_fortitude",
        name = "Hillbilly Fortitude",
        school = SpellSchool.HOLY,
        basePower = 65,
        mpCost = 20,
        isHeal = true,
        description = "Channels generational resilience and family resolve to restore party HP.",
        exampleChant = "Hillbilly fortitude!",
        aliases = listOf("hillbilly fortitude", "fortitude", "resilience", "family resolve", "resolve")
    )

    val COMMON_SENSE_RENEWAL = Spell(
        id = "ca_vance_commonsense",
        name = "Common Sense",
        school = SpellSchool.PHYSICAL,
        basePower = 0,
        mpCost = 0,
        manaRestorePct = 0.35f,
        description = "Applies straightforward Middle-American logic, refreshing 35% MP.",
        exampleChant = "Common sense!",
        aliases = listOf("common sense", "renewal", "straight talk", "logic", "middle america")
    )

    val VANCE_SPELLS = listOf(
        DEBATE_DISMANTLE,
        APPALACHIAN_SLAM,
        HILLBILLY_FORTITUDE,
        COMMON_SENSE_RENEWAL
    )

    fun createJDVance(
        id: String = "vance",
        name: String = "JD Vance",
        currentHp: Int = 4200,
        maxHp: Int = 4200,
        currentMp: Int = 1000,
        maxMp: Int = 1000
    ): PartyMember {
        return PartyMember(
            id = id,
            name = name,
            loreClass = "Rustbelt Vanguard",
            currentHp = currentHp,
            maxHp = maxHp,
            currentMp = currentMp,
            maxMp = maxMp,
            speed = 60,
            defense = 32,
            avatarTint = Color(0xFF5C6BC0), // Indigo Vanguard
            spells = VANCE_SPELLS
        )
    }

    // =========================================================================
    // 4. KASH PATEL (Intel Inquisitor - Joins Act IV)
    // =========================================================================

    val DECLASSIFY_NOW = Spell(
        id = "ca_kash_declassify",
        name = "Declassify Now!",
        school = SpellSchool.SHADOW,
        basePower = 65,
        mpCost = 18,
        status = "OVERLOAD",
        description = "Slaps down unredacted evidence, shocking and stunning the target with undeniable truth.",
        exampleChant = "Declassify now!",
        aliases = listOf("declassify now", "declassify", "unredacted", "the memo", "fisa memo", "truth")
    )

    val BINDER_OF_TRUTH = Spell(
        id = "ca_kash_binder",
        name = "Binder of Truth",
        school = SpellSchool.PHYSICAL,
        basePower = 58,
        mpCost = 28,
        hitsAll = true,
        description = "Flings a heavy three-ring binder of declassified dossiers across all opposition.",
        exampleChant = "Binder of truth!",
        aliases = listOf("binder of truth", "binder", "dossier", "deep state binder", "evidence")
    )

    val SECURITY_CLEARANCE = Spell(
        id = "ca_kash_clearance",
        name = "Security Clearance",
        school = SpellSchool.HOLY,
        basePower = 60,
        mpCost = 20,
        isHeal = true,
        description = "Grants top-secret diplomatic clearance, cleansing fatigue and healing party wounds.",
        exampleChant = "Security clearance!",
        aliases = listOf("security clearance", "clearance", "top secret", "clean dossier", "classified")
    )

    val LEAK_PLUG = Spell(
        id = "ca_kash_leak",
        name = "Plug the Leaks",
        school = SpellSchool.SHADOW,
        basePower = 0,
        mpCost = 0,
        manaRestorePct = 0.35f,
        description = "Plugs anonymous agency leaks and redirects the flow of power, regaining 35% MP.",
        exampleChant = "Plug the leaks!",
        aliases = listOf("plug the leaks", "plug leaks", "stop leaks", "containment", "plug")
    )

    val KASH_SPELLS = listOf(
        DECLASSIFY_NOW,
        BINDER_OF_TRUTH,
        SECURITY_CLEARANCE,
        LEAK_PLUG
    )

    fun createKashPatel(
        id: String = "kash",
        name: String = "Kash Patel",
        currentHp: Int = 3900,
        maxHp: Int = 3900,
        currentMp: Int = 1100,
        maxMp: Int = 1100
    ): PartyMember {
        return PartyMember(
            id = id,
            name = name,
            loreClass = "Intel Inquisitor",
            currentHp = currentHp,
            maxHp = maxHp,
            currentMp = currentMp,
            maxMp = maxMp,
            speed = 68,
            defense = 25,
            avatarTint = Color(0xFFAB47BC), // Intelligence Purple
            spells = KASH_SPELLS
        )
    }

    // =========================================================================
    // 5. SENATOR JOHN KENNEDY (Committee Cross-Examiner - Joins Act V)
    // =========================================================================

    val PLAIN_ENGLISH_EXAM = Spell(
        id = "ca_kennedy_english",
        name = "Plain English Cross-Exam",
        school = SpellSchool.PHYSICAL,
        basePower = 65,
        mpCost = 18,
        status = "OVERLOAD",
        description = "Grills the witness in relentless plain English until they freeze in stunned embarrassment.",
        exampleChant = "Plain English cross-exam!",
        aliases = listOf("plain english", "cross exam", "grill", "witness", "kennedy grill")
    )

    val CALL_AN_OFFICER = Spell(
        id = "ca_kennedy_officer",
        name = "Call an Officer",
        school = SpellSchool.SHADOW,
        basePower = 55,
        mpCost = 26,
        hitsAll = true,
        description = "Exposes absurd government waste and illegal absurdity across all enemies with scathing Southern humor.",
        exampleChant = "Call an officer!",
        aliases = listOf("call an officer", "bureaucratic waste", "squeal", "expose waste", "officer")
    )

    val COMMON_SENSE_REMEDY = Spell(
        id = "ca_kennedy_remedy",
        name = "Common Sense Remedy",
        school = SpellSchool.HOLY,
        basePower = 70,
        mpCost = 20,
        isHeal = true,
        description = "Applies straightforward folk wisdom to heal party wounds and clear confusion.",
        exampleChant = "Common sense remedy!",
        aliases = listOf("common sense remedy", "remedy", "folk wisdom", "southern remedy", "southern heal", "folk remedy")
    )

    val DEVASTATING_ONELINER = Spell(
        id = "ca_kennedy_oneliner",
        name = "Devastating One-Liner",
        school = SpellSchool.PHYSICAL,
        basePower = 0,
        mpCost = 0,
        manaRestorePct = 0.35f,
        description = "Delivers a mic-drop committee hearing punchline, recovering 35% MP.",
        exampleChant = "Devastating one-liner!",
        aliases = listOf("one liner", "mic drop", "punchline", "kennedy one liner", "devastating")
    )

    val KENNEDY_SPELLS = listOf(
        PLAIN_ENGLISH_EXAM,
        CALL_AN_OFFICER,
        COMMON_SENSE_REMEDY,
        DEVASTATING_ONELINER
    )

    fun createJohnKennedy(
        id: String = "kennedy",
        name: String = "Senator Kennedy",
        currentHp: Int = 4000,
        maxHp: Int = 4000,
        currentMp: Int = 1150,
        maxMp: Int = 1150
    ): PartyMember {
        return PartyMember(
            id = id,
            name = name,
            loreClass = "Committee Cross-Examiner",
            currentHp = currentHp,
            maxHp = maxHp,
            currentMp = currentMp,
            maxMp = maxMp,
            speed = 62,
            defense = 30,
            avatarTint = Color(0xFF78909C), // Slate Committee Silver
            spells = KENNEDY_SPELLS
        )
    }
}
