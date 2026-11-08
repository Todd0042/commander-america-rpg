# Parody Combat Movesets & Spell Tables Plan (`PARODY_COMBAT_MOVESETS_PLAN.md`)

> [!IMPORTANT]
> **Engine Compatibility & Fact-Grounded Satire Mandate**
> Every move in this document maps directly to the existing Kotlin combat engine schemas in [`TemplateHero.kt`](../../app/src/main/java/com/voicerpg/engine/template/TemplateHero.kt) (`Spell` / `SpellSchool` / `StatusEffectId`) and [`MovesetTable.kt`](../../app/src/main/java/com/voicerpg/engine/combat/MovesetTable.kt) (`EnemyMove` / `PhaseRule`). Every attack, buff, debuff, voice chant, and ultimate is 100% rooted in [`FACTUAL_VERBATIM_DATABASE.md`](../../docs/story-design/FACTUAL_VERBATIM_DATABASE.md) and [`VINDICATION_TRIUMPHS_DATABASE.md`](../../docs/story-design/VINDICATION_TRIUMPHS_DATABASE.md).

---

## 1. Engine Schema Quick Reference

- **Spell Schools (`SpellSchool`):**
  - `PHYSICAL` ( kinetic props, podiums, paper towels, sinks, waffle cones)
  - `CRYOMANCY` (ice cream cones, Sub-Zero freezers, brain freezes, chilly stares)
  - `PYROMANCY` (hot sauce, pants-on-fire fact-checks, tanning beds, dumpster fires)
  - `ELECTROMANCY` (teleprompter glitches, unpermitted rooftop strobe signs, viral tweets, microphone feedback)
  - `HOLY` (Vindication Triumphs, declassified scrolls, aviators, courtroom exonerations)
  - `SHADOW` (subpoenas, BleachBit wipes, hair-dye drips, dark-money super PACs)
  - `NATURE` (coconut trees, bushes, gluten-free cardamom, raw milk/cattle futures)
- **Status Effects (`StatusEffectId`):**
  - `BURN`, `CHILL`, `OVERLOAD` (Stun/Confusion), `CORRODE`, `POISON`, `BLEED`, `WEAKEN`, `ROOT`, `BLESS`, `GUARD`.

---

## 2. Joe Naptime (Joe Biden) — *The Ice-Cream Cryomancer & Verbatim Audio Bard*

**Role:**
- **Track B:** Playable Standard-Bearer in **Act III (2020–2022)** & **Act IV Part 1 (2023–July 2024)**.
- **Track A:** Act III Midterm Boss & Act IV First Debate Boss (`boss_joe_naptime`).

> [!NOTE]
> **Core Design Mandate for Joe Naptime:**
> 1. **Signature Randomized Real-Audio "Gibberish" Move (`naptime_verbatim_gibberish`):** Triggers `SfxManager` to select and play an actual historical audio clip at random from a dedicated pool of verbatim Joe Biden recordings (`assets/audio/quotes/template/biden_gibberish_01.ogg` through `10.ogg`).
> 2. **Primarily Ice-Cream-Based Moveset:** Almost his entire kit revolves around chocolate chip waffle cones, Baskin-Robbins runs, Wilmington soft-serve, and brain freezes (`CRYOMANCY`).

### 2.1 The Randomized Verbatim Audio Pool (`naptime_verbatim_gibberish`)
When Joe Naptime casts **Verbatim Teleprompter Freestyle** (either via player voice chant or enemy boss AI), the audio engine randomly rolls 1 of 10 real archival audio clips and applies a matching chaotic effect:

| Roll | Audio File Placeholder (`assets/audio/quotes/template/`) | Verbatim Transcript Played | Additional Proc Effect |
| :--- | :--- | :--- | :--- |
| **1** | `biden_gibberish_01_badakathcare.ogg` | *" Mobilizing... Badakathcare!"* | Inflicts `OVERLOAD` (Stun) + `CORRODE` on target as their brain tries to parse the syllable. |
| **2** | `biden_gibberish_02_truinanashabba.ogg` | *"I'll lead an effective strategy to mobilize truinanashabbadepressure!"* | AoE `WEAKEN` + `OVERLOAD` across all enemies. |
| **3** | `biden_gibberish_03_cornpop.ogg` | *"Corn Pop was a bad dude! And he ran a bunch of bad boys!"* | Summons a rusty 1962 pool chain (`PHYSICAL` bonus hit + `BLEED`). |
| **4** | `biden_gibberish_04_leg_hair.ogg` | *"I got hairy legs that turn—that-that-that turn blonde in the sun! And the kids used to come up and reach in the pool and rub my leg down..."* | Inflicts `ROOT` + `CHILL` from sheer awkwardness. |
| **5** | `biden_gibberish_05_repeat_the_line.ogg` | *"End of quote. Repeat the line."* | Causes Joe's *previous* ice cream move to cast a second time for 0 MP! |
| **6** | `biden_gibberish_06_beat_medicare.ogg` | *"We finally beat Medicare..."* | Deals heavy `ELECTROMANCY` splash damage to all combatants (including 5% recoil to self, followed by `GUARD`). |
| **7** | `biden_gibberish_07_america_one_word.ogg` | *"America is a nation that can be defined in a single word: Asufutimaehaehfutbw."* | Scrambles enemy turn order and inflicts `OVERLOAD`. |
| **8** | `biden_gibberish_08_dog_faced_pony.ogg` | *"You're a lying, dog-faced pony soldier!"* | Single-target critical `PHYSICAL` verbal slap + `WEAKEN`. |
| **9** | `biden_gibberish_09_you_know_the_thing.ogg` | *"We hold these truths to be self-evident. All men and women created by the—go—you know—you know the thing!"* | Restores 25% MP to self while confusing (`OVERLOAD`) the target. |
| **10** | `biden_gibberish_10_pushups_fat.ogg` | *"Look, fat, look, here's the deal—let's do push-ups together, man!"* | Grants Joe `BLESS` (+25% ATK) for 2 turns. |

### 2.2 Joe Naptime's Playable Spell Kit (Primarily Ice-Cream Based)

| Spell ID | Move Name | School | Power | MP | Target | Status / Special | Description & Voice Chant (`exampleChant` / `aliases`) |
| :--- | :--- | :--- | :---: | :---: | :---: | :--- | :--- |
| `naptime_gibberish` | **Verbatim Teleprompter Freestyle** | `ELECTROMANCY` | 1.65 | 16 | All Enemies | `OVERLOAD` + Random Audio Clip | Plays a random real archival audio clip of Joe's famous verbal tangents, stunning enemies with unparseable syntax. **Chant:** *"Mobilize truinanashabbadepressure you know the thing"* (`gibberish`, `badakathcare`, `corn pop`, `repeat the line`, `you know the thing`) |
| `naptime_choco_chip` | **Double-Scoop Chocolate Chip Cone** | `CRYOMANCY` | 1.40 | 12 | Self (Heal + Guard) | Heal + `GUARD` + `BLESS` | Licks a double-scoop chocolate chip waffle cone at a Baskin-Robbins counter while declaring *"Our economy is strong as hell, man."* **Chant:** *"Double scoop chocolate chip waffle cone"* (`chocolate chip`, `ice cream cone`, `double scoop`, `strong as hell`) |
| `naptime_brain_freeze` | **Wilmington Soft-Serve Brain Freeze** | `CRYOMANCY` | 1.55 | 18 | All Enemies | `CHILL` | Unleashes a blizzard of vanilla and chocolate-chip soft-serve across the stage, freezing enemy turn bars solid. **Chant:** *"Wilmington soft serve brain freeze blizzard"* (`brain freeze`, `soft serve`, `vanilla blizzard`, `ice cream storm`) |
| `naptime_waffle_barrage` | **Crunchy Waffle-Cone Shrapnel** | `CRYOMANCY` | 1.75 | 15 | Single | `BLEED` + `CHILL` | Hurls razor-sharp sugar cones dipped in frozen fudge at the opponent's podium. **Chant:** *"Hurl crunchy waffle cone shrapnel"* (`waffle cone`, `sugar cone`, `cone barrage`) |
| `naptime_jenis_raid` | **Borrowed Sub-Zero Pint Raid** | `CRYOMANCY` | 0.00 | 0 | Self | +45% MP Restore + `GUARD` | Sneaks a \$12 gourmet chocolate-chip pint out of Nancy Palooza's freezer to recharge stamina mid-debate. **Chant:** *"Raid the freezer for a chocolate chip pint"* (`freezer raid`, `pint raid`, `eat ice cream`, `restore mana`) |
| `naptime_aviator_sundae` | **Aviator Hot-Fudge Sundae** | `CRYOMANCY` | 1.85 | 22 | Single | Lifesteal (35%) + `CHILL` | Flips up silver Ray-Ban aviators over a towering hot-fudge ice cream sundae, draining enemy momentum while healing HP. **Chant:** *"Aviator shades and hot fudge sundae"* (`hot fudge sundae`, `aviator sundae`, `dark brandon sundae`) |
| `naptime_vindication_bus` | **No-Malarkey Campaign Bus EXONERATION** *(Vindication Ultimate)* | `HOLY` | 2.30 | 30 | All Enemies | `BLESS` + Clears Debuffs | Counters debunked smears (2020 Texas bus ramming / "88,000 armed IRS agents" / "banning 2 burgers a month") with a giant ice-cream-truckhorn blast of verified court dockets. **Chant:** *"No malarkey fact check exoneration"* (`no malarkey`, `vindication`, `fact check bus`) |

---

## 3. Commander America (Donald Trump) — *The Golden Podium Brawler*

**Role:**
- **Track A:** Playable Protagonist across **Acts I–V (2015–2025)**.
- **Track B:** Major Act I, Act III, and Act IV Boss (`boss_commander_america`).

| Spell ID | Move Name | School | Power | MP | Target | Status / Special | Fact Basis & Voice Chant (`exampleChant`) |
| :--- | :--- | :--- | :---: | :---: | :---: | :--- | :--- |
| `cmd_bounty_jumper` | **San Juan Paper-Towel Jump Shot** | `PHYSICAL` | 1.45 | 10 | All Enemies | `WEAKEN` | Launches soft, absorbent Bounty rolls into the crowd with a charismatic basketball follow-through (Oct 2017). **Chant:** *"Shoot the paper towel jump shot"* (`paper towels`, `bounty jump shot`, `free throw`) |
| `cmd_sharpie_cone` | **Category-5 Sharpie Reality Loop** | `SHADOW` | 1.70 | 16 | Single | `CORRODE` + `OVERLOAD` | Draws a black Sharpie bubble onto the National Hurricane Center map (Sept 2019), bending trajectories to hit Alabama and the target. **Chant:** *"Extend the hurricane cone with my Sharpie"* (`sharpie map`, `hurricane sharpie`, `black sharpie`) |
| `cmd_diet_coke_button` | **Red Wooden Valet Button** | `CRYOMANCY` | 1.35 | 0 | Self | Heal + +35% MP + `BLESS` | Presses the nuclear-looking red wooden box on the Resolute Desk to summon a silver platter with an ice-cold Diet Coke. **Chant:** *"Press the red Diet Coke button"* (`diet coke`, `red button`, `silver platter`) |
| `cmd_covfefe_tweet` | **12:06 AM Covfefe Broadcast** | `ELECTROMANCY` | 1.65 | 18 | All Enemies | `OVERLOAD` | Fires *"Despite the constant negative press covfefe"* into the midnight ether (May 2017), forcing enemies to argue what it means. **Chant:** *"Despite the constant negative press covfefe"* (`covfefe`, `midnight tweet`) |
| `cmd_15_flushes` | **Low-Flow 15-Flush Cyclone** | `NATURE` | 1.80 | 20 | All Enemies | `CORRODE` + `WEAKEN` | *"People are flushing toilets 10 times, 15 times, as opposed to once!"* Unleashes a high-pressure plumbing vortex (Dec 2019). **Chant:** *"Flush it ten times fifteen times"* (`fifteen flushes`, `low flow toilet`, `water pressure`) |
| `cmd_lecter_sharks` | **Late Great Hannibal & The Electric Boat** | `ELECTROMANCY` | 1.95 | 24 | Single | `OVERLOAD` + `BURN` | Traps the enemy between a sinking electric boat battery and a shark 10 yards away while praising *"the late, great Hannibal Lecter"* (2024 rallies). **Chant:** *"Electric boat battery or the shark and Hannibal Lecter"* (`hannibal lecter`, `shark battery`, `electric boat`) |
| `cmd_vindication_vol1` | **Volume I Exoneration Slam** *(Vindication Ultimate)* | `HOLY` | 2.40 | 30 | All Enemies | `GUARD` + `BLESS` | Drops the 448-page Mueller Report Volume I (*"did not establish conspiracy"*) and the full Charlottesville *"condemned totally"* transcript onto the battlefield. **Chant:** *"Total exoneration Volume One slam"* (`total exoneration`, `mueller volume one`, `read the transcript`) |

---

## 4. Playable Track B Standard-Bearers (The Relay Roster)

### 4.1 Act I (2015–2016): Hill-Rod (Hillary Clinton) — *The Server Technomancer*
| Spell ID | Move Name | School | Power | MP | Target | Status / Special | Fact Basis & Voice Chant |
| :--- | :--- | :--- | :---: | :---: | :---: | :--- | :--- |
| `hill_pokemon_go` | **Pokémon Go-To-The-Polls** | `ELECTROMANCY` | 1.45 | 12 | All Enemies | `WEAKEN` + `OVERLOAD` | Attempts to code a smartphone app that makes creatures appear in voting booths (July 2016), dealing cringe-wave damage. **Chant:** *"Pokemon Go to the polls"* (`pokemon go`, `go to the polls`) |
| `hill_cloth_wipe` | **Chappaqua Cloth Wipe** | `SHADOW` | 1.50 | 14 | Self / Single | Clears Debuffs + `BLESS` + `CORRODE` | *"What, like with a cloth or something?"* (Aug 2015). Wipes all negative status effects off herself and deletes enemy buffs. **Chant:** *"Wipe the server with a cloth"* (`with a cloth`, `bleachbit`, `wipe server`) |
| `hill_hot_sauce` | **Purse Ninja Red Pepper** | `PYROMANCY` | 1.65 | 15 | Single | `BURN` | Pulls a bottle of Ninja Squirrel hot sauce from her handbag on *The Breakfast Club* (April 2016), scorching the target while swearing it isn't pandering. **Chant:** *"Hot sauce in my bag right now"* (`hot sauce`, `purse hot sauce`, `breakfast club`) |
| `hill_two_ferns` | **Between Two Potted Ferns** | `NATURE` | 1.80 | 20 | Single | `ROOT` + `POISON` | Summons Zach Galifianakis between two ferns to cut to a rival's campaign commercial mid-interview (Sept 2016). **Chant:** *"Cut to between two ferns"* (`between two ferns`, `galifianakis`) |
| `hill_cattle_futures` | **\$1,000 Cattle Futures Stampede** | `PHYSICAL` | 1.95 | 22 | All Enemies | `BLEED` | Multiplies 1,000 into 100,000 via 1978–79 Refco live-cattle contracts by reading the *Wall Street Journal*. **Chant:** *"One thousand dollar cattle futures stampede"* (`cattle futures`, `wall street journal`) |
| `hill_vindication_11hr` | **11-Hour Benghazi Marathon Shield** *(Vindication Ultimate)* | `HOLY` | 2.25 | 28 | All Enemies | `GUARD` + `BLESS` | Survives an 11-hour live committee grilling and debunks the Comet Ping Pong basement smear with zero indictment charges. **Chant:** *"Eleven hour hearing shoulder shrug"* (`eleven hour hearing`, `no basement`, `comeys july statement`) |

### 4.2 Act II (2017–2020): Nancy Palooza (Nancy Pelosi) — *The Sub-Zero Market Oracle*
| Spell ID | Move Name | School | Power | MP | Target | Status / Special | Fact Basis & Voice Chant |
| :--- | :--- | :--- | :---: | :---: | :---: | :--- | :--- |
| `nancy_sotu_shred` | **Pre-Scored Speech Rip** | `PHYSICAL` | 1.55 | 12 | Single | `BLEED` + `CORRODE` | Stands up behind the podium and tears a thick stack of speech pages in half with surgical precision (Feb 2020). **Chant:** *"Rip the State of the Union speech in half"* (`rip the speech`, `tear pages`, `sotu shred`) |
| `nancy_subzero_flex` | **\$24,000 Dual Sub-Zero Freezer Flex** | `CRYOMANCY` | 1.40 | 14 | Self | Heal + +40% MP + `GUARD` | Opens two towering stainless-steel Sub-Zero freezers on *The Late Late Show* (April 2020) stocked with gourmet Jeni's pints. **Chant:** *"Open the dual Sub Zero ice cream freezers"* (`sub zero freezer`, `jenis ice cream`, `late late show`) |
| `nancy_sideways_clap` | **Condescending Sideways Seal Clap** | `HOLY` | 1.65 | 16 | All Enemies | `WEAKEN` + `OVERLOAD` | Extends both arms forward at a 45-degree angle and claps directly into the opponent's soul (Feb 2019). **Chant:** *"Condescending sideways applause"* (`sideways clap`, `pelosi clap`, `seal clap`) |
| `nancy_pass_to_find_out` | **Mystery Omnibus Scroll** | `SHADOW` | 1.80 | 20 | Single | Random `POISON` / `BURN` / `OVERLOAD` | *"We have to pass the bill so that you can find out what is in it, away from the fog of the controversy"* (March 2010). **Chant:** *"Pass the bill to find out what is in it"* (`pass the bill`, `find out whats in it`) |
| `nancy_deep_itm_calls` | **Deep-In-The-Money Call Option** | `ELECTROMANCY` | 2.00 | 24 | All Enemies | Lifesteal (30%) + `BLESS` | Executes a \$5M Block VII Periodic Transaction Report on Big Tech LEAPS (*"We are a free-market economy"* — Dec 2021). **Chant:** *"Free market Periodic Transaction Report"* (`call options`, `free market economy`, `stock trade`) |
| `nancy_vindication_nvda` | **The \$341k Pre-CHIPS Act Loss Receipt** *(Vindication Ultimate)* | `HOLY` | 2.35 | 28 | All Enemies | `BLESS` + Clears Debuffs | Slams down the July 26, 2022 disclosure proving Paul *sold* 25,000 Nvidia shares at a \$341,365 loss before the CHIPS Act vote, plus the unedited C-SPAN video debunking the 2020 "drunk slurring" deepfake. **Chant:** *"Three hundred forty one thousand dollar loss receipt"* (`chips act loss`, `unedited cspan`, `fact check trade`) |

### 4.3 Act IV Part 2 (July–Nov 2024): Kamala Coconut (Kamala Harris) — *The Venn-Diagram Mystic*
| Spell ID | Move Name | School | Power | MP | Target | Status / Special | Fact Basis & Voice Chant |
| :--- | :--- | :--- | :---: | :---: | :---: | :--- | :--- |
| `kamala_coconut_drop` | **Context of the Coconut Tree** | `NATURE` | 1.55 | 12 | Single | `OVERLOAD` + `ROOT` | *"You think you just fell out of a coconut tree? You exist in the context of all in which you live and what came before you!"* (May 2023). **Chant:** *"You think you just fell out of a coconut tree"* (`coconut tree`, `exist in the context`) |
| `kamala_unburdened` | **Unburdened By What Has Been** | `HOLY` | 1.40 | 14 | Self | Heal + Clears All Debuffs + `BLESS` | Chants her signature philosophical mantra from 50+ speeches, erasing all past turn damage/debuffs and looking toward what can be. **Chant:** *"What can be unburdened by what has been"* (`unburdened`, `what has been`) |
| `kamala_venn_diagram` | **Triple-Circle Venn Diagram Lock** | `ELECTROMANCY` | 1.75 | 18 | All Enemies | `ROOT` + `WEAKEN` | *"I love Venn diagrams! Always ask, is there a Venn diagram for this? Those three circles!"* (Oct 2022). Traps all enemies inside overlapping intersection circles. **Chant:** *"Trap them in the three circles of the Venn diagram"* (`venn diagram`, `three circles`) |
| `kamala_yellow_bus` | **Electric Yellow School Bus Charge** | `ELECTROMANCY` | 1.90 | 20 | All Enemies | `OVERLOAD` + `BURN` | *"Who doesn't love a yellow school bus, right? Can you raise your hand?!"* (Oct 2022). Rams the enemy line with a zero-emission yellow school bus. **Chant:** *"Who doesnt love a yellow school bus"* (`yellow school bus`, `school bus`) |
| `kamala_passage_of_time` | **Significance of the Passage of Time** | `SHADOW` | 1.70 | 16 | Single | `CHILL` (4-Turn Slow) | Repeats *"the significance of the passage of time"* four times in 30 seconds (March 2022), dilating time until the enemy skips a turn. **Chant:** *"The significance of the passage of time"* (`passage of time`, `significance of time`) |
| `kamala_vindication_studs` | **Tiffany Pearl Earring Counter-Flash** *(Vindication Ultimate)* | `HOLY` | 2.30 | 28 | All Enemies | `GUARD` + `BLESS` | Reveals her September 2024 debate earrings are standard solid-gold Tiffany Double Pearl Hinged Earrings (not Bluetooth spyware) and drops her 1982 Westmount High yearbook photo. **Chant:** *"Tiffany pearl earrings not Bluetooth spyware"* (`tiffany earrings`, `pearl earrings`, `yearbook photo`) |

### 4.4 Act V (2024–2026): Zohran Cardamom (NYC Mayor Zohran Mamdani) — *The Fare-Free Rapper*
| Spell ID | Move Name | School | Power | MP | Target | Status / Special | Fact Basis & Voice Chant |
| :--- | :--- | :--- | :---: | :---: | :---: | :--- | :--- |
| `zohran_nani_drop` | **Mr. Cardamom *Nani* Beat Drop** | `PYROMANCY` | 1.55 | 12 | All Enemies | `BURN` + `WEAKEN` | Drops his 2019 *Mr. Cardamom* hip-hop single wearing an apron while Madhur Jaffrey trash-talks the opposing party in a chapati food truck. **Chant:** *"Drop the Mister Cardamom Nani beat"* (`mister cardamom`, `nani beat`, `chapati truck`) |
| `zohran_free_q70` | **Fare-Free Q70 Bus Stampede** | `PHYSICAL` | 1.80 | 18 | All Enemies | `OVERLOAD` | Opens the back doors of every MTA bus in Queens (*"Freeze the Rent & Free the Buses"*), running over turnstiles and opponents alike. **Chant:** *"Free the MTA buses and freeze the rent"* (`free buses`, `freeze the rent`, `q70 bus`) |
| `zohran_point_privilege` | **DSA Point of Personal Privilege** | `SHADOW` | 1.45 | 14 | Single | `ROOT` + `WEAKEN` | Calls a Point of Personal Privilege against gendered language and loud clapping/whispering, silencing enemy vocal attacks for 2 turns. **Chant:** *"Point of personal privilege no loud clapping"* (`personal privilege`, `jazz hands`, `no whispering`) |
| `zohran_city_groceries` | **Municipal Bodega Price Freeze** | `CRYOMANCY` | 1.50 | 15 | Self | Heal + `GUARD` + +30% MP | Summons a city-owned municipal grocery cart stocked with subsidized Astoria produce and halal cart white sauce. **Chant:** *"City owned municipal grocery cart"* (`city groceries`, `bodega freeze`, `white sauce`) |
| `zohran_hunger_strike` | **15-Day Broadway Medallion Fast** | `HOLY` | 2.15 | 24 | Single | `BLESS` + Heavy Counter | Channels his 15-day water-only hunger strike alongside NYC taxi drivers (Oct–Nov 2021), converting missing HP into massive damage. **Chant:** *"Fifteen day taxi medallion hunger strike"* (`hunger strike`, `taxi medallion`) |

---

## 5. Major Companions & Bosses (Dual-Campaign Crossover Roster)

### 5.1 Kash Subpoena (Kash Patel) — *The Children's Book Inquisitor*
**Role:** Track A Companion / Subpoena Summon; Track B Act II & Act V Mid-Boss (`boss_kash_subpoena`).
| Move ID / Name | School | Kind | Power | Target / Status | Fact Basis (from `FACTUAL_VERBATIM_DATABASE.md`) |
| :--- | :--- | :--- | :---: | :--- | :--- |
| **Wizard Kash & The King's Plot Pitch** | `HOLY` | Attack + Buff | 1.55 | Single + Self `BLESS` | Stops mid-battle to hawk his 3-part Brave Books illustrated children's trilogy (*The Plot Against the King*), waving a wizard staff with a $19.99 QR code. |
| **Subpoena Boomerang** | `SHADOW` | Attack | 1.45 | Single (`BLEED`) | Hurls a stack of House Intel subpoenas—only to get hit back by a Grand Jury subpoena forcing him to plead the Fifth until granted immunity (Nov 2022). |
| **Four-Page Nunes Memo Scroll** | `PYROMANCY` | AoE Attack | 1.70 | All (`BURN` + `CORRODE`) | Unrolls the February 2018 four-page Carter Page FISA memo, scorching bureaucracy shields. |
| **60-Name Appendix Target List** | `SHADOW` | Debuff | 1.35 | All (`WEAKEN` + `ROOT`) | Reads aloud from the 60-name alphabetical appendix of *Government Gangsters* (2023), marking all targets for investigation. |
| **Telepathic Palindrome Declassification** | `ELECTROMANCY` | Guard / Counter | 1.60 | Self `GUARD` + `OVERLOAD` | Claims every binder on the battlefield was already declassified by standing mental order, reflecting magic attacks. |
| **J. Edgar Hoover Museum Eviction** *(Ultimate)* | `PHYSICAL` | Heavy AoE | 2.10 | All (`OVERLOAD`) | Turns the FBI headquarters building into a "Museum of the Deep State" on Day One and sends everyone on patrol across America. |

### 5.2 JD Pants (JD Vance) — *The Donut-Shop Chameleon*
**Role:** Track A Act IV–V Companion; Track B Act IV Debate Boss (`boss_jd_pants`).
| Move ID / Name | School | Kind | Power | Target / Status | Fact Basis (from `FACTUAL_VERBATIM_DATABASE.md` & `VINDICATION_TRIUMPHS_DATABASE.md`) |
| :--- | :--- | :--- | :---: | :--- | :--- |
| **"Whatever Makes Sense" Glazed Assortment** | `NATURE` | Attack + Debuff | 1.50 | Single (`OVERLOAD`) | Walks into Holt's Sweet Shop (Aug 2024), asks every enemy *"How long have you worked here? Okay, good,"* and orders *"some sprinkle stuff, some glazed, whatever makes sense."* |
| **Childless Cat-Lady Provocation** | `SHADOW` | AoE Taunt | 1.65 | All (`BURN` + `WEAKEN`) | Broadcasts his July 2021 Fox News clip about *"a bunch of childless cat ladies,"* enraging the entire battlefield and drawing aggro. |
| **2016 Deleted Facebook Text Whiplash** | `ELECTROMANCY` | Morph Strike | 1.75 | Single (`CORRODE`) | Rapidly shifts stances from 2016 *"Never Trump guy"* to 2024 MAGA heir apparent so fast it creates a sonic boom. |
| **Diet Mountain Dew Racist Toast** | `CRYOMANCY` | Heal + MP | 1.30 | Self (`BLESS` + +35% MP) | Cracks open a chilled Diet Mountain Dew (*"I had a Diet Mountain Dew yesterday and one today, and I'm sure they're going to call that racist too"* — July 2024). |
| **Swiss Cheese Phony Witwiz** | `PYROMANCY` | Attack | 1.60 | Single (`POISON`) | Orders a Philly cheesesteak at Pat's King of Steaks with Swiss cheese (Oct 2024), offending all Pennsylvania targets. |
| **1st Ed. Page 179 Fact-Check Slam** *(Vindication Ultimate)* | `HOLY` | AoE Counter | 2.25 | All (`BLESS` + Clears Debuffs) | Opens an actual 2016 1st-Edition hardcover of *Hillbilly Elegy* to pages 179–181 (a completely normal passage about working at a grocery store) and pulls out Usha's statement on his natural thick eyelashes to obliterate fabricated viral hoaxes. |

### 5.3 Musk-X (Elon Musk) — *The Ketamine Cyber-Technoking*
**Role:** Track A Act V Companion; Track B Act V Boss (`boss_musk_x`).
| Move ID / Name | School | Kind | Power | Target / Status | Fact Basis (from `FACTUAL_VERBATIM_DATABASE.md` & `VINDICATION_TRIUMPHS_DATABASE.md`) |
| :--- | :--- | :--- | :---: | :--- | :--- |
| **Porcelain Kitchen Sink Toss ("Let That Sink In")** | `PHYSICAL` | Heavy Strike | 1.75 | Single (`OVERLOAD`) | Carries an actual porcelain bathroom sink into HQ (Oct 2022) and hurls it at the enemy while Dad-laughing at his own pun. |
| **Franz Steel-Ball Window Shatter** | `PHYSICAL` | AoE Pierce | 1.65 | All (`BLEED` + `CORRODE`) | Throws a solid steel ball at "unbreakable" Cybertruck armor glass—shattering both windows while whispering *"Oh my f***ing God"* (Nov 2019). |
| **Butler Mid-Air Star-Jump ("Dark Gothic MAGA")** | `ELECTROMANCY` | Buff + AoE | 1.60 | All (`BLESS` + `OVERLOAD`) | Leaps into an X-shape mid-air exposing 4 inches of midriff in a black graphic tee (Oct 2024). |
| **Unpermitted Rooftop Giant Strobe X** | `ELECTROMANCY` | AoE Blind | 1.85 | All (`BURN` + `OVERLOAD`) | Installs a giant pulsing strobe-light `X` sign on the roof without city permits (July 2023), blinding every neighbor and opponent. |
| **FSD "Next Year" Rollover Loop** | `SHADOW` | Delay / Shield | 1.40 | Self (`GUARD`) | Promises Full Self-Driving will arrive *"definitely next year"* (2014–2024), deferring incoming damage to next year's fiscal quarter. |
| **\$28k Vintage 3-Stone Emerald Rebuke** *(Vindication Ultimate)* | `HOLY` | AoE Counter | 2.30 | All (`BLESS`) | Debunks the "apartheid slave emerald mine billionaire" myth with Walter Isaacson's verified biography receipts and the 1894 *New York Times* origin of "pedo guy." |

### 5.4 Rudy Ghoul, Spicy Sean, Barry Teleprompter & Mayor Swagger (Act Bosses)

| Boss ID & Parody Name | Signature Moves (Mapped to `MovesetTable.kt`) | Fact Basis |
| :--- | :--- | :--- |
| **`boss_spicy_sean` (Spicy Sean / Sean Spicer)** | 1. **Largest Audience Period** (`PYROMANCY` AoE + `BURN`)<br>2. **White House Hedge Retreat** (`NATURE` Self `GUARD` + Hide among the bushes, May 2017)<br>3. **Mini-Fridge Cinnamon Gum Swallow** (`POISON` Self-Heal, 2.5 packs before noon)<br>4. **Neon Green Ruffled Salsa Roll** (`PHYSICAL` Spin Attack from 2019 *DWTS*) | Jan 2017 briefing, May 2017 Comey firing bushes, *Washington Post* gum profile, *Dancing with the Stars* debut. |
| **`boss_rudy_ghoul` (Rudy Ghoul / Rudy Giuliani)** | 1. **Four Seasons Total Landscaping Booking** (`NATURE` Arena Shift between crematorium & adult bookshop, Nov 2020)<br>2. **My Cousin Vinny Dual-Streak Hair-Dye Drip** (`SHADOW` `POISON` + `CORRODE` down both cheeks)<br>3. **Borat Bedroom Shirt-Tuck Defense** (`PHYSICAL` Grapple, *"I was tucking in my shirt!"* Oct 2020)<br>4. **Scotch & Cigar Trial Accounting** (`PYROMANCY` AoE) | Nov 2020 Philadelphia press conference, Nov 19 DNC presser hair dye, *Borat Subsequent Moviefilm* hotel scene. |
| **`boss_barry_teleprompter` (Barry Teleprompter / Barack Obama)** | 1. **August 2014 Tan Suit Scandal Flash** (`HOLY` AoE Dazzle + `OVERLOAD`)<br>2. **Dijon Poupon Burger Request** (`NATURE` Buff + Elite Snobbery Taunt, May 2009)<br>3. **"If-If-If-If-If" Teleprompter Reboot** (`ELECTROMANCY` Multi-Hit Stutter)<br>4. **135-Yard Mom-Jeans Ceremonial Pitch** (`PHYSICAL` High-Arc Lob)<br>5. **Long-Form Kapiʻolani Certificate Slam** *(Vindication Ultimate)* | 2014 tan suit presser, 2009 Ray's Hell Burger order, 2009 All-Star Game pitch, 2011 birth certificate & *Honolulu Advertiser* announcement. |
| **`boss_mayor_swagger` (Mayor Swagger / Eric Adams)** | 1. **Turkish Airlines 0-Dollar Upgrade (`istanbul`)** (`ELECTROMANCY` Teleport + `BLESS`, Sept 2024 indictment)<br>2. **Knapsack Hidden Bedroom Compartment** (`SHADOW` Item Trap + `OVERLOAD` — *"Everything from a simple vibrant gun to a crack pipe"* Feb 2024)<br>3. **Fort Lee New Jersey "Vegan" Salmon Fillet** (`NATURE` Lifesteal Heal, *Politico* 2021 investigation)<br>4. **Swagger Nightlife VIP Rope** (`PHYSICAL` `ROOT`) | SDNY 57-page indictment (`istanbul` password), 2024 viral bedroom backpack search video, 2021 Fort Lee apartment & fish diet expose. |

---

## 6. Technical Wiring Blueprint (For Implementation Phase)

When you are ready to leave Planning Mode and touch Kotlin code, these movesets connect into the engine via 3 clean steps:

1. **Voice & Spell Registry (`TemplateHero.kt` & `ClassSpellLibrary.kt`):**
   - Register each playable act protagonist's 6–7 spells (`cmd_*`, `naptime_*`, `hill_*`, `nancy_*`, `kamala_*`, `zohran_*`) with their voice-recognition `exampleChant` and `aliases` arrays so players can either tap the move button or speak the actual quote into their mic (e.g., saying *"Double scoop chocolate chip"* or *"Badakathcare"*).
2. **Joe Naptime's Random Audio Hook (`SfxManager.kt` & `CombatEngine.kt`):**
   - Add `playRandomBidenGibberish()` to `SfxManager.kt`, which selects one of `assets/audio/quotes/template/biden_gibberish_01..10.ogg` and returns the matching subtitle string to display in the combat log.
3. **Enemy Moveset JSON & Generated Table (`docs/combat-design/data/movesets.json` & `MovesetTable.kt`):**
   - Add the boss entries (`boss_joe_naptime`, `boss_commander_america`, `boss_kash_subpoena`, `boss_jd_pants`, `boss_musk_x`, `boss_spicy_sean`, `boss_rudy_ghoul`, `boss_barry_teleprompter`, `boss_mayor_swagger`) with Phase 2 transitions at 50% HP (`hpBelow = 0.50`) that unlock their **Vindication Ultimate** or signature meltdown move.
