package com.voicerpg.engine.engine

import com.voicerpg.engine.model.AcousticProfile
import com.voicerpg.engine.model.ResonanceTier
import com.voicerpg.engine.model.Spell

data class ChantPreset(
    val chantText: String,
    val acousticProfile: AcousticProfile,
    val tier: ResonanceTier,
    val label: String
)

object SpellChantPresets {

    /**
     * Retrieves a finely-tuned incantation and paired acoustic profile designed to hit
     * the requested resonance tier for hands-free and simulator testing.
     *
     * Tier targets:
     * - BASIC: 0% .. 15% (1.0x .. 1.15x) -> Target ~10%
     * - ADEPT: 20% .. 50% (1.20x .. 1.50x) -> Target ~48%
     * - MASTER: 55% .. 95% (1.55x .. 1.95x) -> Target ~87%
     * - MYTHIC: 100% .. 150% (2.0x .. 2.50x) -> Target ~142%
     * - TRANSCENDENTAL: 155% .. 200% (2.55x .. 3.0x MAX) -> Target 200% MAX
     */
    fun getPreset(spell: Spell, tier: ResonanceTier): ChantPreset {
        val (chant, acoustic) = when (spell.id) {
            "fireball" -> when (tier) {
                ResonanceTier.BASIC -> "Fireball archer" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Cast fireball at archer" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of cinder burn archer" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise blazing inferno consume the archer now!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O primordial flame of the solar core descend from the heavens and reduce that wretched archer to eternal ash and inferno!" to AcousticProfile.TRANSCENDENTAL
            }
            "frost_spike" -> when (tier) {
                ResonanceTier.BASIC -> "Frost orc" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Cast frost spike orc" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of ice freeze orc" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise glacial blizzard freeze the orc now!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O celestial winter of absolute zero arise from the void and encase that monstrous orc in eternal glacial frostbite and crystal shards!" to AcousticProfile.TRANSCENDENTAL
            }
            "chain_lightning" -> when (tier) {
                ResonanceTier.BASIC -> "Lightning all" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Cast chain lightning all" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of tempest shock all" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise tempest lightning shock all enemies now!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O primordial tempest of celestial wrath awaken and discharge roaring thunderclaps to strike all enemies with galvanic lightning and plasma!" to AcousticProfile.TRANSCENDENTAL
            }
            "holy_smite" -> when (tier) {
                ResonanceTier.BASIC -> "Smite heretic" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Cast holy smite heretic" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of dawn smite enemy" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise celestial dawn smite the wicked enemy!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O celestial seraph of the sacred dawn descend from the heavens and unleash divine radiance to smite the abominable darkness unto purity!" to AcousticProfile.TRANSCENDENTAL
            }
            "lay_on_hands" -> when (tier) {
                ResonanceTier.BASIC -> "Mend Cedric" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Mend wounds of Cedric" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of grace mend Cedric" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise celestial grace mend Cedric with healing!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O primordial celestial light of pure dawn descend from the heavens to bless mend and cure our valiant guardian Cedric with restorative aegis!" to AcousticProfile.TRANSCENDENTAL
            }
            "shield_wall" -> when (tier) {
                ResonanceTier.BASIC -> "Raise shield" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Raise shield against foes" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits shield our line" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise steel vanguard shield our line now!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By the power of celestial steel and unyielding vanguard valor arise and smash the foe to shield all allies with an impenetrable aegis!" to AcousticProfile.TRANSCENDENTAL
            }
            "soothing_rain" -> when (tier) {
                ResonanceTier.BASIC -> "Rain party" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Send gentle rain party" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of grove rain party" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise restorative grove rain upon the party!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O celestial grove of sacred dawn hearken unto our prayer and unleash soothing rain to mend and rejuvenate the entire party with divine grace!" to AcousticProfile.TRANSCENDENTAL
            }
            "briar_entangle" -> when (tier) {
                ResonanceTier.BASIC -> "Briar archer" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Cast great briar archer" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of briar thorns archer" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise thorny briar vines ensnare that archer!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O ancient spirits of the primordial grove awaken from the earth and ensnare that vile archer within crushing briar vines and thorns!" to AcousticProfile.TRANSCENDENTAL
            }
            "shadow_strike" -> when (tier) {
                ResonanceTier.BASIC -> "Shadow shaman" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Cast swift shadow shaman" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of umbra strike shaman" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise abyssal umbra strike the shaman now!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O primordial abyss of the endless umbra awaken from the void and cloak my blade to strike the treacherous shaman into eternal silence!" to AcousticProfile.TRANSCENDENTAL
            }
            "venom_flurry" -> when (tier) {
                ResonanceTier.BASIC -> "Venom all" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Cast swift venom all" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of venom drench all" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise abyssal venom surge drench all foes now!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O dark spirits of the void awaken and unleash a drenching flurry of venomous serpents to engulf all enemies in lethal poison!" to AcousticProfile.TRANSCENDENTAL
            }
            "verdant_cataclysm" -> when (tier) {
                ResonanceTier.BASIC -> "Grove erupt all" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Cast grove-warcry all" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of the grove surge and awaken!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise ancient grove cataclysm crush all enemies!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O ancient spirits of the deep earth awaken and unleash the full fury of the grove-warcry to crush all foes within an erupting cataclysm of vines and stone!" to AcousticProfile.TRANSCENDENTAL
            }
            "umbral_siphon" -> when (tier) {
                ResonanceTier.BASIC -> "Mend Zephyr" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Siphon essence mend Zephyr" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Spirits of umbral essence mend Zephyr" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise abyssal siphon all essence mend Zephyr with healing!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "O primordial abyss of the endless umbra awaken and siphon a torrent of restorative essence to mend and rejuvenate Zephyr with eldritch healing!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_tremendous_strike" -> when (tier) {
                ResonanceTier.BASIC -> "Tremendous blow!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Hit them tremendous blow!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Unleash tremendous strike upon enemies!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise tremendous strike crush all enemies now!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By the power of celestial steel arise and crush all enemies with a tremendous winning strike!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_art_of_the_deal" -> when (tier) {
                ResonanceTier.BASIC -> "The deal!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Impose the deal" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Unleash the golden deal now!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise divine art of golden liberty!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By the power of celestial liberty arise and negotiate the golden art of the deal for total victory!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_obama_did_it_first" -> when (tier) {
                ResonanceTier.BASIC -> "Blame Obama!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Search the ancient archives" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Unleash shadow Obama records now!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise abyssal shadows and expose Obama!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By the power of celestial truth descend from the void to expose the shadow archives of Obama and shatter all hypocrisy!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_build_the_wall" -> when (tier) {
                ResonanceTier.BASIC -> "Build wall!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Build strong wall now" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Unleash the steel wall now!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise steel vanguard build impenetrable wall!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By the power of celestial steel and vanguard valor arise and fortify the great golden wall to shield all patriots!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_rally_patriots" -> when (tier) {
                ResonanceTier.BASIC -> "Rally now!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Rally all allies now" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Unleash the golden rally now!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise celestial dawn rally the patriots!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By the power of celestial freedom arise and inspire the sacred rally of patriots to bless and mend all champions!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_presidential_veto" -> when (tier) {
                ResonanceTier.BASIC -> "Presidential veto!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "I issue a presidential veto now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Unleash presidential veto upon corrupt spending!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise executive authority, shut down the opposition with a presidential veto!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By absolute constitutional executive authority, I hereby issue a presidential veto to crush bureaucratic overreach and stun all enemies into silence!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_executive_action" -> when (tier) {
                ResonanceTier.BASIC -> "Executive action!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Sign the executive action now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Unleash swift executive action across the swamp!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise presidential power, sign executive action against all opposition!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By the sacred authority of the Constitution, I enact sweeping executive action to purge corruption and strike all foes across the nation!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_tired_of_winning" -> when (tier) {
                ResonanceTier.BASIC -> "Tired of winning!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "We are never tired of winning!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Take a breath, because we are winning so much!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise glorious momentum, tired of winning restores our energy!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "Folks, they said we would get tired of winning, but we will never stop winning as our sacred spirit and mana are fully restored!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_marco_filibuster" -> when (tier) {
                ResonanceTier.BASIC -> "Filibuster strike!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Deliver the filibuster strike now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Deliver an uninterrupted filibuster strike on the Senate floor!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise diplomatic resolve, filibuster strike to freeze the opposition!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By the sacred traditions of the United States Senate, I deliver an unstoppable filibuster strike to stun all adversaries into total submission!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_marco_sanction" -> when (tier) {
                ResonanceTier.BASIC -> "Maximum sanctions!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Impose maximum sanctions now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Freeze all foreign assets with maximum sanctions!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise diplomatic retribution, maximum sanctions against every adversary!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By full international statutory authority, freeze all foreign assets and bombard all hostile regimes with maximum sanctions of crippling power!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_marco_hydrate" -> when (tier) {
                ResonanceTier.BASIC -> "Water sip!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Take a refreshing water sip!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Water sip restores the spirit and party health!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise refreshing clarity, water sip heals all allies with holy resolve!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "With laser diplomatic focus and pure crystalline hydration, take a historic water sip to bless, heal, and revitalize our entire fellowship!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_marco_tariff" -> when (tier) {
                ResonanceTier.BASIC -> "Tariff dividend!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Levy the tariff dividend now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Collect the tariff dividend from unfair trade partners!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise domestic prosperity, tariff dividend recovers national energy!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By strategic economic sovereignty, levy massive tariffs on cheating foreign adversaries and harvest the rich tariff dividend to restore our team's mana!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_vance_debate" -> when (tier) {
                ResonanceTier.BASIC -> "Debate dismantle!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Debate dismantle on live television!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Debate dismantle opponent rhetoric with calm facts!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise heartland eloquence, debate dismantle the corrupt moderators!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "With devastating poise and razor-sharp intellect, debate dismantle the entire hostile media apparatus and stun our opponents on national television!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_vance_slam" -> when (tier) {
                ResonanceTier.BASIC -> "Appalachian slam!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Deliver the Appalachian slam now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Unleash the Appalachian slam of industrial grit!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise steel heartland fury, Appalachian slam across all enemies!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "From the iron hills and manufacturing heartland, unleash the thunderous Appalachian slam to pulverize every corrupt obstacle across the field!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_vance_fortitude" -> when (tier) {
                ResonanceTier.BASIC -> "Hillbilly fortitude!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Channel hillbilly fortitude now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Channel generational hillbilly fortitude to mend our ranks!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise family resilience, hillbilly fortitude restores the party!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "Through generations of unwavering faith, relentless grit, and family devotion, channel hillbilly fortitude to bless and restore our comrades with heroic resolve!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_vance_commonsense" -> when (tier) {
                ResonanceTier.BASIC -> "Common sense!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Apply straightforward common sense!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Middle-American common sense refreshes our clear minds!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise working-class truth, common sense clears the mental fog!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "With the pure and unyielding logic of Middle America, apply straightforward common sense to sweep away coastal madness and renew our vital energy!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_kash_declassify" -> when (tier) {
                ResonanceTier.BASIC -> "Declassify now!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Declassify the FISA memo now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Declassify now and expose the hidden archives!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise unredacted truth, declassify now to stun the conspirators!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "Slap down the unredacted documents, break the deep state classification, and declassify now to shatter all federal corruption in blazing sunlight!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_kash_binder" -> when (tier) {
                ResonanceTier.BASIC -> "Binder of truth!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Throw the binder of truth now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Unleash the three-ring binder of truth across the room!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise documented receipts, heavy binder of truth hits all enemies!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By the unyielding weight of declassified cross-examination, slam the three-ring binder of truth across the opposition line to crush all false narratives!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_kash_clearance" -> when (tier) {
                ResonanceTier.BASIC -> "Security clearance!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Grant security clearance to our team!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Issue security clearance and purge political sabotage!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise top-secret trust, security clearance heals party wounds!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "By presidential intelligence decree, issue top-secret security clearance, purge foreign disinformation, and cleanse and heal our entire fellowship!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_kash_leak" -> when (tier) {
                ResonanceTier.BASIC -> "Plug the leaks!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Plug the leaks across the agencies!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Plug the leaks and stop anonymous deep state whispers!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise institutional integrity, plug the leaks to reclaim power!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "Seal every corrupt anonymous channel, plug the leaks across the bureaucratic swamp, and redirect the torrent of power to refresh our party's mana!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_kennedy_english" -> when (tier) {
                ResonanceTier.BASIC -> "Plain English cross-exam!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Plain English cross-exam for the witness!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Plain English cross-exam leaves the witness speechless!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise Southern logic, plain English cross-exam freezes the bureaucrat!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "Now look here son, in plain English cross-exam, explain why the American taxpayer is funding this nonsense until the enemy freezes in stunned silence!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_kennedy_officer" -> when (tier) {
                ResonanceTier.BASIC -> "Call an officer!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Call an officer on this absurdity!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "Call an officer, someone is looting the public purse!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise common decency, call an officer across the whole swamp!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "Someone call an officer right this second, because what these bureaucrats are doing to the taxpayer is a felony that rocks every enemy on the field!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_kennedy_remedy" -> when (tier) {
                ResonanceTier.BASIC -> "Common sense remedy!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Apply a common sense remedy now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "A common sense remedy heals better than expensive red tape!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise down-home folk wisdom, common sense remedy mends our team!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "Forget your fancy committee consultant reports, apply a pure common sense remedy of down-home wisdom to heal and bless our entire squad!" to AcousticProfile.TRANSCENDENTAL
            }
            "ca_kennedy_oneliner" -> when (tier) {
                ResonanceTier.BASIC -> "Devastating one-liner!" to AcousticProfile.BASIC
                ResonanceTier.ADEPT -> "Drop a devastating one-liner now!" to AcousticProfile.ADEPT
                ResonanceTier.MASTER -> "A devastating one-liner silences the committee hearing!" to AcousticProfile.MASTER
                ResonanceTier.MYTHIC -> "Arise folk humor, devastating one-liner restores our good spirit!" to AcousticProfile.MYTHIC
                ResonanceTier.TRANSCENDENTAL -> "Deliver a mic-drop devastating one-liner with folksy Louisiana wit that leaves the opposition speechless and completely refreshes our energy!" to AcousticProfile.TRANSCENDENTAL
            }
            else -> fallbackPreset(spell, tier)
        }

        val label = when (tier) {
            ResonanceTier.BASIC -> "10% Basic"
            ResonanceTier.ADEPT -> "40% Adept"
            ResonanceTier.MASTER -> "80% Master"
            ResonanceTier.MYTHIC -> "125% Mythic"
            ResonanceTier.TRANSCENDENTAL -> "200% MAX"
        }

        return ChantPreset(
            chantText = chant,
            acousticProfile = acoustic,
            tier = tier,
            label = label
        )
    }

    private fun fallbackPreset(spell: Spell, tier: ResonanceTier): Pair<String, AcousticProfile> {
        return when (tier) {
            ResonanceTier.BASIC -> "${spell.name}" to AcousticProfile.BASIC
            ResonanceTier.ADEPT -> "Cast ${spell.name} now!" to AcousticProfile.ADEPT
            ResonanceTier.MASTER -> "Spirits of power, unleash ${spell.name} upon foes!" to AcousticProfile.MASTER
            ResonanceTier.MYTHIC -> "Arise celestial power, awaken and cast ${spell.name} with crushing fury!" to AcousticProfile.MYTHIC
            ResonanceTier.TRANSCENDENTAL -> "O primordial celestial forces descend from the heavens and unleash ${spell.name} to obliterate all enemies in glorious triumph!" to AcousticProfile.TRANSCENDENTAL
        }
    }
}
