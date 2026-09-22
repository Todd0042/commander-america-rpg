package com.voicerpg.engine.engine

import androidx.compose.ui.graphics.Color
import com.voicerpg.engine.model.DialogueSpeaker
import com.voicerpg.engine.model.Enemy
import com.voicerpg.engine.model.SpellSchool

data class TutorialSpellDef(
    val name: String,
    val school: SpellSchool,
    val basePower: Int,
    val mpCost: Int,
    val isHeal: Boolean = false,
    val isGuard: Boolean = false,
    val hitsAll: Boolean = false,
    val manaRestorePct: Float = 0f,
    val description: String,
    val aliases: List<String> = emptyList()
)

data class TutorialCharacterDef(
    val id: String,
    val name: String,
    val title: String,
    val speaker: DialogueSpeaker,
    val loreClass: String,
    val maxHp: Int,
    val maxMp: Int,
    val speed: Int,
    val avatarTint: Color,
    val spells: List<TutorialSpellDef>
)

sealed class TutorialBattleStep {
    data class IntroPopup(
        val text: String,
        val speaker: DialogueSpeaker = DialogueSpeaker.NARRATOR_HULK
    ) : TutorialBattleStep()

    data class CharacterJoin(
        val characterDef: TutorialCharacterDef,
        val text: String
    ) : TutorialBattleStep()

    data class SpellOverview(
        val characterDef: TutorialCharacterDef,
        val text: String
    ) : TutorialBattleStep()

    data class PracticeCast(
        val characterId: String,
        val spellName: String,
        val school: SpellSchool,
        val promptText: String,
        val isResonanceDemo: Boolean = false,
        val resonanceDemoIndex: Int = 0
    ) : TutorialBattleStep()

    data class AutoAction(
        val characterId: String,
        val actionText: String,
        val resultText: String
    ) : TutorialBattleStep()

    data class EnemyAttackDemo(
        val introText: String,
        val resultText: String
    ) : TutorialBattleStep()

    data class CompletionPopup(
        val text: String
    ) : TutorialBattleStep()
}

object TutorialBattleContent {

    val CHARACTERS: List<TutorialCharacterDef> = listOf(
        TutorialCharacterDef(
            id = "commander",
            name = "Commander America",
            title = "Commander-in-Chief",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            loreClass = "Commander-in-Chief",
            maxHp = 5000,
            maxMp = 1776,
            speed = 70,
            avatarTint = Color(0xFFFFD700),
            spells = listOf(
                TutorialSpellDef(
                    name = "Tremendous Strike",
                    school = SpellSchool.PHYSICAL,
                    basePower = 65,
                    mpCost = 0,
                    description = "A tremendously powerful patriotic strike that pulverizes political obstruction.",
                    aliases = listOf("tremendous", "strike", "smash", "punch", "tremendous blow")
                ),
                TutorialSpellDef(
                    name = "Art of the Deal",
                    school = SpellSchool.HOLY,
                    basePower = 50,
                    mpCost = 20,
                    hitsAll = true,
                    description = "Imposes unmatched negotiation power, damaging all opposing targets.",
                    aliases = listOf("art of the deal", "deal", "negotiate", "trade", "billion")
                ),
                TutorialSpellDef(
                    name = "Obama Did It First!",
                    school = SpellSchool.SHADOW,
                    basePower = 80,
                    mpCost = 25,
                    description = "Exposes historical receipts! Inflicts devastating damage and stuns the target.",
                    aliases = listOf("obama did it first", "obama", "receipts", "hypocrisy")
                ),
                TutorialSpellDef(
                    name = "Tired of Winning",
                    school = SpellSchool.HOLY,
                    basePower = 0,
                    mpCost = 0,
                    manaRestorePct = 0.35f,
                    description = "Commander America pauses because there is simply so much winning, recovering mana.",
                    aliases = listOf("tired of winning", "winning", "win", "winning streak")
                )
            )
        ),
        TutorialCharacterDef(
            id = "marco",
            name = "Marco Rubio",
            title = "Sanctions Sorcerer",
            speaker = DialogueSpeaker.MARCO,
            loreClass = "Sanctions Sorcerer",
            maxHp = 320,
            maxMp = 160,
            speed = 65,
            avatarTint = Color(0xFF81D4FA),
            spells = listOf(
                TutorialSpellDef(
                    name = "Sanctions Blizzard",
                    school = SpellSchool.PHYSICAL,
                    basePower = 75,
                    mpCost = 18,
                    description = "Freezes rogue accounts and restricts diplomatic capital with biting penalties.",
                    aliases = listOf("sanctions blizzard", "sanctions", "blizzard", "freeze accounts")
                ),
                TutorialSpellDef(
                    name = "Hydrate & Rebut",
                    school = SpellSchool.HOLY,
                    basePower = 70,
                    mpCost = 15,
                    isHeal = true,
                    hitsAll = true,
                    description = "Pauses for a sip of water, recovering HP for the entire party.",
                    aliases = listOf("hydrate", "water", "rebut", "heal")
                ),
                TutorialSpellDef(
                    name = "Water Bottle Chug",
                    school = SpellSchool.HOLY,
                    basePower = 0,
                    mpCost = 0,
                    manaRestorePct = 0.35f,
                    description = "Takes a deep sip of refreshing Florida spring water to restore mana.",
                    aliases = listOf("water bottle", "chug", "sip", "drink")
                )
            )
        )
    )

    val ENEMIES: List<Enemy> = listOf(
        Enemy(
            id = "cable_pundit",
            name = "Hostile Cable Pundit",
            subtitle = "Biased Anchor",
            currentHp = 2000,
            maxHp = 2000,
            baseAttack = 0,
            speed = 30,
            family = "FLESH"
        ),
        Enemy(
            id = "teleprompter_dummy",
            name = "Rigged Teleprompter",
            subtitle = "Talking Points Drone",
            currentHp = 2000,
            maxHp = 2000,
            baseAttack = 0,
            speed = 30,
            family = "FLESH"
        ),
        Enemy(
            id = "swamp_bureaucrat",
            name = "Swamp Red-Tape Clerk",
            subtitle = "Regulatory Obstructor",
            currentHp = 2000,
            maxHp = 2000,
            baseAttack = 0,
            speed = 30,
            family = "FLESH"
        )
    )

    val STEPS: List<TutorialBattleStep> = listOf(
        TutorialBattleStep.IntroPopup(
            text = "WELCOME TO PATRIOT BOOTCAMP, BROTHER! The media, the teleprompter sorcerers, and the deep swamp are everywhere! Commander America is here to teach you how to dominate the debate floor using the power of your VOICE!",
            speaker = DialogueSpeaker.NARRATOR_HULK
        ),
        TutorialBattleStep.IntroPopup(
            text = "In real combat, you don't push boring buttons — you SPEAK your incantations into the microphone! The more vivid and dramatic your speech, the higher your resonance bonus! Let's get down to business!",
            speaker = DialogueSpeaker.NARRATOR_HULK
        ),
        TutorialBattleStep.CharacterJoin(
            characterDef = CHARACTERS[0],
            text = "Commander America takes the debate podium! Equipped with maximum ratings, unmatched stamina, and presidential authority."
        ),
        TutorialBattleStep.SpellOverview(
            characterDef = CHARACTERS[0],
            text = "Here is Commander America's presidential arsenal."
        ),
        TutorialBattleStep.PracticeCast(
            characterId = "commander",
            spellName = "Tremendous Strike",
            school = SpellSchool.PHYSICAL,
            promptText = "Try casting Tremendous Strike! Say 'Tremendous Strike' into your microphone to blast the Hostile Cable Pundit!"
        ),
        TutorialBattleStep.PracticeCast(
            characterId = "commander",
            spellName = "Art of the Deal",
            school = SpellSchool.HOLY,
            promptText = "Art of the Deal hits EVERY opponent on the debate floor! Say 'Art of the Deal' to show them who owns the ratings!"
        ),
        TutorialBattleStep.AutoAction(
            characterId = "commander",
            actionText = "Every patriot has a zero-cost breath ability! Say 'Tired of Winning' to pause, bask in victory, and restore your mana without spending anything!",
            resultText = "Commander America's mana fully restored via Tired of Winning! You will never get tired of winning, brother!"
        ),
        TutorialBattleStep.CharacterJoin(
            characterDef = CHARACTERS[1],
            text = "Marco Rubio joins the debate stage as your Sanctions Sorcerer! Armed with leather briefcases, devastating sanctions, and emergency Florida hydration!"
        ),
        TutorialBattleStep.SpellOverview(
            characterDef = CHARACTERS[1],
            text = "Here are Marco Rubio's debate abilities."
        ),
        TutorialBattleStep.PracticeCast(
            characterId = "marco",
            spellName = "Sanctions Blizzard",
            school = SpellSchool.PHYSICAL,
            promptText = "Command Marco to cast Sanctions Blizzard! Say 'Sanctions Blizzard' to freeze the swamp's offshore bank accounts!"
        ),
        TutorialBattleStep.IntroPopup(
            text = "NOW WITNESS THE SECRET TO VICTORY: THE RESONANCE SYSTEM! Speak with real swagger and conviction to multiply your spell power! First, say just 'Tremendous Strike' — simple and plain.",
            speaker = DialogueSpeaker.NARRATOR_HULK
        ),
        TutorialBattleStep.PracticeCast(
            characterId = "commander",
            spellName = "Tremendous Strike",
            school = SpellSchool.PHYSICAL,
            promptText = "Say 'Tremendous Strike' now. Just the spell name.",
            isResonanceDemo = true,
            resonanceDemoIndex = 0
        ),
        TutorialBattleStep.PracticeCast(
            characterId = "commander",
            spellName = "Tremendous Strike",
            school = SpellSchool.PHYSICAL,
            promptText = "Now cast Tremendous Strike again, but this time use dramatic presidential authority! Say: 'By executive order and tremendous patriotic strength, pulverize this fake news broadcast!'",
            isResonanceDemo = true,
            resonanceDemoIndex = 1
        ),
        TutorialBattleStep.IntroPopup(
            text = "LOOK AT THAT DAMAGE MULTIPLIER, BROTHER! That elaborate patriotic chant unleashed massive bonus power! Eloquent conviction will always crush low-energy mumbling!",
            speaker = DialogueSpeaker.NARRATOR_HULK
        ),
        TutorialBattleStep.EnemyAttackDemo(
            introText = "Watch out! The fake news network is launching an anonymous attack piece! See how your patriotic coalition withstands it!",
            resultText = "Hostile pundits will hit back on their turn. Use healing spells, shields, and high-energy offense to win the debate every time!"
        ),
        TutorialBattleStep.CompletionPopup(
            text = "OUTSTANDING JOB, BROTHER! You have mastered voice combat and debate dominance! The golden escalator awaits — go take America back!"
        )
    )

    fun getCharacter(id: String): TutorialCharacterDef? =
        CHARACTERS.firstOrNull { it.id == id }

    val totalSteps: Int get() = STEPS.size
}
