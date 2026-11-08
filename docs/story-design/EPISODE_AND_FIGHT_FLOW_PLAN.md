# Pure Story & Fight Blueprint: Zero-Noise Mechanics & Episode Outline (`EPISODE_AND_FIGHT_FLOW_PLAN.md`)

> [!IMPORTANT]
> **Core Game Philosophy: Zero RPG Noise — 100% Story & Fight Sequences**
> Per project direction, **XP, leveling up, gold/money, random grinding encounters, and item shops are completely eliminated.**
> - The entire point of the game is **hilarious, fact-grounded story scenes** and **memorable turn-based/voice-cast boss fights**.
> - Players never have to grind, manage inventory clutter, or worry about saving money.

---

## PART 1 (Option 2): The "Zero-Noise" Fight & Baton-Pass Architecture

By stripping out XP, gold, and gear shops, the game flows like an interactive playable comedy series (think *South Park* or *Ace Attorney* meets classic turn-based combat).

### 1.1 How Progression Works Without XP or Money
1. **Automatic Full Recovery Between Fights:**
   - Every time a fight ends, your character's **HP and MP automatically reset to 100%**. Every fight is designed as a standalone, self-contained comedy set-piece where you can go all-out with your best moves.
2. **Pre-Tuned Stats Per Act (No Grinding Ever):**
   - Instead of gaining XP to raise numbers, each Act sets the active protagonist and boss to clean, balanced baseline stats (e.g., **100 HP / 100 MP** across the board, or fixed Act-appropriate health pools) so strategy is about **which move/quote you use and when**, not whether you farmed enough numbers.
3. **Story-Unlocked Moves ("Signature Moments"):**
   - Characters start their Act with their core 4–5 signature moves unlocked immediately.
   - Their **Vindication Ultimate** (or a special mid-Act comedy move) unlocks dynamically during the story scene right when a famous historical accusation or event happens!
4. **Seamless "Baton Pass" on Track B (Left Campaign):**
   - Because there is no XP, gold, or equipment inventory to transfer, switching characters on Track B is **instant and frictionless**:
     - **Act I (2015–16):** Play as **Hill-Rod** with her 6 signature moves.
     - **Act II (2017–20):** The story cuts to Capitol Hill; you immediately control **Nancy Palooza** with her full Sub-Zero / Speech-Rip moveset.
     - **Act III (2020–22):** The baton passes to **Joe Naptime** with his randomized real-audio Gibberish move and Ice-Cream Cryomancy kit.
     - **Act IV (2023–24):** Play Part 1 as **Joe Naptime**, then experience the July 2024 "Torch Pass" scene mid-Act to take control of **Kamala Coconut** for Part 2.
     - **Act V (2024–26):** The baton passes to NYC Mayor **Zohran Cardamom** with his *Mr. Cardamom* rap & fare-free bus kit.

### 1.2 What Needs to Be Disabled in the Engine Later (When Coding Resumes)
When you are ready to touch code, stripping out the RPG noise is super clean:
- **Hide XP / Gold / Level UI badges** in the top HUD bar and post-combat victory screen (`BattleResult` simply advances directly to the `nextSceneId` in `StoryScript.kt`).
- **Remove Shop & Rest Nodes:** Every node in `StoryScript.kt` is purely a **Story Scene** (dialogue + comedy choices) or a **Boss/Set-Piece Fight** (`combatTrigger`).
- **Hero Swap Helper (`setActiveProtagonist(heroId)`):** A 1-line helper at the start of each Act on Track B that swaps the player's portrait, name, and 6-spell list in `GameState` to the current Act's standard-bearer.

---

## PART 2 (Option 1): Act-by-Act Episode & Fight Sequence Outline (2015–2026)

Every Act is structured as a tight **3-to-4 Episode arc** per campaign track. Every scene pulls directly from [`FACTUAL_VERBATIM_DATABASE.md`](../../docs/story-design/FACTUAL_VERBATIM_DATABASE.md) and [`VINDICATION_TRIUMPHS_DATABASE.md`](../../docs/story-design/VINDICATION_TRIUMPHS_DATABASE.md).

---

### ACT I: The Escalator & The Server (2015–2016)

#### Track A (Playing as Commander America)
- **Episode 1.1 — The Golden Escalator Descent (June 2015):**
  - *Story Scene:* Descending the Trump Tower escalator. Dealing with the "paid $50 actor" FEC rumors and the 2005 *Access Hollywood* Tic-Tac bus tape fallout.
  - *Vindication Moment:* Slams down the **1990 *Playboy* / 1998 *People* "Dumb Republicans" Hoax Rebuke** (proving the viral meme quote was completely fabricated).
- **Episode 1.2 — The Primary Brawl & The Server Basement:**
  - *Story Scene:* Debating Hill-Rod over why 33,000 emails were scrubbed with BleachBit.
  - *Fight Sequence 1 (`boss_barry_teleprompter` — Warmup Exhibition):* **Barry Teleprompter** steps in wearing his August 2014 **Tan Suit**, ordering a **Dijon Mustard Burger**, and dropping the mic (*"At least I will go down as a president"*).
- **Episode 1.3 — The 2016 General Election Finale:**
  - *Story Scene:* Hill-Rod tries to appeal to youth voters with *Pokémon Go-to-the-Polls* and pulls hot sauce from her purse on *The Breakfast Club*.
  - *Boss Fight 2 (`boss_hill_rod`):* **Hill-Rod** uses *Chappaqua Cloth Wipe*, *Pokémon Go-To-The-Polls*, and *Between Two Potted Ferns*.

#### Track B (Playing as Hill-Rod — Standard-Bearer #1)
- **Episode 1.1 — Chappaqua Server Room & *Between Two Ferns*:**
  - *Story Scene:* Press conference where Hill-Rod shrugs *"What, like with a cloth or something?"* followed by Zach Galifianakis ambushing her between two potted ferns with a Trump campaign ad.
  - *Fight Sequence 1 (`boss_spicy_sean` — Press Corps Skirmish):* **Spicy Sean** charges out of the White House bushes chewing 2.5 packs of Orbit Cinnamon gum.
- **Episode 1.2 — The 11-Hour Hearing & The Basement Myth:**
  - *Story Scene:* Surviving the marathon 11-hour Benghazi hearing with the iconic shoulder shrug.
  - *Vindication Moment:* Unlocks **11-Hour Marathon Shield**, debunking the Comet Ping Pong basement hoax (a pizzeria with no basement) and the fabricated "acid-washed hard drives with hammers" rumor.
- **Episode 1.3 — Election Night Showdown (Nov 2016):**
  - *Boss Fight 2 (`boss_commander_america` — Act I Version):* **Commander America** battles from behind a golden podium using *Tremendous Accordion Hands* and *Red Wooden Valet Button*.

---

### ACT II: Covfefe, Sub-Zero Freezers & The Nunes Scroll (2017–2020)

#### Track A (Playing as Commander America + Companion Kash Subpoena)
- **Episode 2.1 — Crowd Sizes, Covfefe & San Juan Paper Towels (2017):**
  - *Story Scene:* **Spicy Sean** declares the *"largest audience to ever witness an inauguration, period!"* before retreating into the White House hedges in the dark. Commander America travels to Puerto Rico and shoots free-throws with Bounty paper towel rolls, then tweets *"Despite the constant negative press covfefe"* at 12:06 AM.
  - *Vindication Moment:* Debunks the viral **"Gorilla Channel" parody excerpt** from *Fire and Fury* that people mistook for real news.
- **Episode 2.2 — Hurricane Dorian Sharpie & 15 Toilet Flushes (2018–2019):**
  - *Story Scene:* Modifying the National Hurricane Center map with a black Sharpie so it hits Alabama, complaining about low-flow toilets requiring *"10 times, 15 times"* flushes, and staring directly into the August 2017 solar eclipse without glasses.
  - *Companion Intro:* **Kash Subpoena** joins the party carrying his 4-page Nunes FISA Scroll and pitching his illustrated children's book (*The Plot Against the King* starring "Wizard Kash").
  - *Fight Sequence 1 (`boss_nancy_palooza` — SOTU Clash):* **Nancy Palooza** does the **45-Degree Sideways Seal Clap** and rips the State of the Union speech in half behind your back.
- **Episode 2.3 — Four Seasons Total Landscaping (Nov 2020):**
  - *Story Scene:* **Rudy Ghoul** accidentally books a press conference at **Four Seasons Total Landscaping** between a crematorium and *Fantasy Island Adult Books* while hair dye drips down both cheeks like *My Cousin Vinny*.
  - *Vindication Moment:* Unlocks **Volume I Exoneration Slam** (the 448-page Mueller Report Volume I conclusion + the full Charlottesville *"condemned totally"* transcript).

#### Track B (Baton Pass $\rightarrow$ Playing as Nancy Palooza — Standard-Bearer #2)
- **Episode 2.1 — The Sideways Clap & Pre-Scored SOTU Rip:**
  - *Story Scene:* Taking the Speaker's gavel. Practicing the 45-degree sideways seal clap and pre-scoring pages of the State of the Union speech so they rip cleanly on live TV.
  - *Fight Sequence 1 (`boss_the_mooch` & `boss_spicy_sean`):* Facing the 11-day whirlwind of **The Mooch** and **Spicy Sean** in a neon green ruffled salsa shirt.
- **Episode 2.2 — Dual $24,000 Sub-Zero Freezers & The eSalon Setup (2020):**
  - *Story Scene:* Showing off her twin Sub-Zero freezers packed with gourmet Jeni's ice cream on *The Late Late Show* while claiming the eSalon blowout during lockdown was a *"setup."*
  - *Vindication Moment:* Unlocks **The \$341k Pre-CHIPS Act Loss Receipt**, slamming down the July 26, 2022 House clerk filing proving Paul actually *sold* 25,000 Nvidia shares at a \$341,365 loss before the CHIPS Act passed, plus the unedited C-SPAN video exposing the slowed-down "drunk slurring" deepfake.
- **Episode 2.3 — The Subpoena Inquisition:**
  - *Boss Fight 2 (`boss_kash_subpoena` & `boss_rudy_ghoul`):* **Kash Subpoena** waves his wizard staff hawking *The Plot Against the King* while **Rudy Ghoul** shouts *"I was tucking in my shirt!"* at Four Seasons Total Landscaping.

---

### ACT III: Ice Cream, Corn Pop & Badakathcare (2020–2022)

#### Track A (Playing as Commander America)
- **Episode 3.1 — Mar-a-Lago Bathroom Chandelier & Telepathic Declassification:**
  - *Story Scene:* Explaining how bankers boxes look great next to a gilded bathroom shower and chandelier, while **Kash Subpoena** swears every document was declassified by standing telepathic order—before pleading the Fifth until granted immunity.
- **Episode 3.2 — The Wilmington Ice-Cream Gauntlet:**
  - *Story Scene:* Tracking **Joe Naptime** through a trail of empty Baskin-Robbins waffle cones and Wilmington pool chains.
  - *Boss Fight (`boss_joe_naptime`):* **Joe Naptime** unleashes **Verbatim Teleprompter Freestyle** (playing real random audio clips of *"Badakathcare"*, *"Truinanashabbadepressure"*, *"Corn Pop was a bad dude"*, and *"Hairy legs that turn blonde in the sun"*) while healing with **Double-Scoop Chocolate Chip Cones**.

#### Track B (Baton Pass $\rightarrow$ Playing as Joe Naptime — Standard-Bearer #3)
- **Episode 3.1 — Corn Pop, Blonde Leg Hair & "You Know The Thing":**
  - *Story Scene:* Joe Naptime steps up to the podium wearing Ray-Ban aviators, recounting his 1962 showdown with Corn Pop at the Wilmington pool and challenging a voter: *"Look, fat, let's do push-ups together, man!"*
  - *Gameplay Highlight:* Players get full command of **Verbatim Teleprompter Freestyle** (playing the 10 randomized real archival audio clips) and his **Ice-Cream Cryomancy** moveset.
- **Episode 3.2 — Baskin-Robbins Inflation Economics ("Strong As Hell, Man"):**
  - *Story Scene:* Licking a chocolate chip cone at an Oregon Baskin-Robbins while telling reporters the economy is *"strong as hell"* and reading *"End of quote. Repeat the line."* straight off the teleprompter.
  - *Vindication Moment:* Unlocks **No-Malarkey Campaign Bus Exoneration**, debunking the "banning Americans to 2 burgers a month / 4 lbs of red meat" hoax and the "87,000 armed IRS agents kicking down doors" myth.
- **Episode 3.3 — Battle at the Podium:**
  - *Boss Fight (`boss_commander_america` — Act III Version):* Clash against Commander America's *Low-Flow 15-Flush Cyclone* and *Category-5 Sharpie Reality Loop*.

---

### ACT IV: Coconut Trees, Donut Shops & The Torch Pass (2023–2024)

#### Track A (Playing as Commander America + Companion JD Pants)
- **Episode 4.1 — The June 2024 Debate ("We Finally Beat Medicare"):**
  - *Story Scene:* The historic June 27, 2024 CNN debate where **Joe Naptime** freezes mid-sentence and declares *"We finally beat Medicare."*
  - *Fight Sequence 1 (`boss_joe_naptime` — Phase 2 Debate Version):* Joe battles with *Borrowed Sub-Zero Pint Raid* and random verbatim audio clips before passing the torch mid-Act!
- **Episode 4.2 — Holt's Sweet Shop & "Whatever Makes Sense":**
  - *Story Scene:* Companion **JD Pants** enters Holt's Sweet Shop in Valdosta, Georgia, awkwardly asking every staffer *"How long have you worked here? Okay, good"* and ordering *"some sprinkle stuff, some glazed, whatever makes sense"* while toasting with a "racist" Diet Mountain Dew.
  - *Vindication Moment (JD Pants):* Opens a physical 1st-Edition hardcover of *Hillbilly Elegy* to pages 179–181 and reads Usha's statement on his natural eyelashes to obliterate internet hoaxes.
- **Episode 4.3 — Coconut Trees, Sharks & Hannibal Lecter (Nov 2024):**
  - *Story Scene:* Commander America riffs on sinking electric boats, sharks 10 yards away, and *"the late, great Hannibal Lecter"* before facing the new nominee.
  - *Boss Fight 2 (`boss_kamala_coconut`):* **Kamala Coconut** attacks with *Context of the Coconut Tree*, *Triple-Circle Venn Diagram Lock*, and *Electric Yellow School Bus Charge*.

#### Track B (Playing as Joe Naptime $\rightarrow$ Mid-Act Torch Pass to Kamala Coconut — Standard-Bearer #4)
- **Episode 4.1 — Joe's Final Scoop ("Beat Medicare" $\rightarrow$ Passing the Cone):**
  - *Story Scene:* Playing as **Joe Naptime** for the June 2024 debate, firing off one last round of **Verbatim Teleprompter Freestyle** and **Aviator Hot-Fudge Sundae** before handing the campaign keys (and a melting chocolate chip cone) to **Kamala Coconut**.
- **Episode 4.2 — Falling Out of the Coconut Tree & Triple Venn Diagrams:**
  - *Story Scene:* Now playing as **Kamala Coconut**! She philosophical-laughs through *"You think you just fell out of a coconut tree?"*, traps her staff in overlapping three-circle Venn diagrams, and marvels at *"the significance of the passage of time."*
  - *Fight Sequence 1 (`boss_jd_pants`):* **JD Pants** attacks with *"Whatever Makes Sense" Glazed Assortment*, *Childless Cat-Lady Provocation*, and *2016 Deleted Facebook Text Whiplash*.
- **Episode 4.3 — Tiffany Pearl Earrings & The Debate Stage:**
  - *Vindication Moment:* Unlocks **Tiffany Pearl Earring Counter-Flash**, proving her debate earrings were standard solid-gold Tiffany pearl studs (not Nova H1 Bluetooth spy earphones) and dropping her 1982 Westmount High yearbook photo.
  - *Boss Fight 2 (`boss_commander_america` — Act IV Version):* Facing Commander America's *Late Great Hannibal & The Electric Boat* combo.

---

### ACT V: Ketamine Jumps, Wizard Books & Mr. Cardamom (2024–2026)

#### Track A (Playing as Commander America + Companions Musk-X & Kash Subpoena)
- **Episode 5.1 — "Let That Sink In" & The Butler Star-Jump:**
  - *Story Scene:* **Musk-X** walks in carrying a porcelain bathroom sink (*"Let that sink in"*), leaps into an X-shape mid-air exposing 4 inches of midriff (*"Dark, gothic MAGA"*), and accidentally shatters both "unbreakable" Cybertruck windows with a steel ball (*"Oh my f***ing God"*).
  - *Vindication Moment (Musk-X):* Unlocks **\$28k Vintage 3-Stone Emerald Rebuke** (Walter Isaacson's verified biography receipts debunking the "apartheid slave emerald mine billionaire" myth).
- **Episode 5.2 — Mayor Swagger's Turkish Airlines Upgrade (`istanbul`) & Bodega Showdown:**
  - *Story Scene:* Traveling to New York City where **Mayor Swagger** (Eric Adams) is typing `istanbul` into the Turkish Airlines app for $0 business-class upgrades while demonstrating how to search a teenager's bedroom backpack for *"everything from a simple vibrant gun to a crack pipe."*
  - *Fight Sequence 1 (`boss_mayor_swagger`):* Mayor Swagger battles with *Turkish Airlines 0-Dollar Upgrade* and *Fort Lee New Jersey "Vegan" Salmon Fillet*.
- **Episode 5.3 — Grand Finale: The Fare-Free Chapati Truck Battle (2025–2026):**
  - *Story Scene:* NYC Mayor **Zohran Cardamom** pulls up in a chapati food truck blasting his 2019 *Mr. Cardamom* rap single *"Nani"* with Madhur Jaffrey, calling a DSA *Point of Personal Privilege* against loud clapping and opening every Q70 bus door in Queens.
  - *Final Boss Fight (`boss_zohran_cardamom`):* Full satirical clash between Commander America / Musk-X / Kash Subpoena and Zohran Cardamom!

#### Track B (Baton Pass $\rightarrow$ Playing as Zohran Cardamom — Standard-Bearer #5)
- **Episode 5.1 — From *Mr. Cardamom* to City Hall (2025):**
  - *Story Scene:* **Zohran Cardamom** takes the baton in New York City, trading his *Mr. Cardamom* rap apron for the mayoral desk while **Mayor Swagger** sneaks out the back door to Fort Lee, New Jersey with a piece of "vegan" salmon and a Turkish Airlines boarding pass.
  - *Fight Sequence 1 (`boss_mayor_swagger`):* Defeating **Mayor Swagger** to claim City Hall.
- **Episode 5.2 — Freeze the Rent, Free the Buses & The Museum of the Deep State:**
  - *Story Scene:* Zohran freezes bodega prices and leads a 15-day water-only hunger strike outside City Hall when **Kash Subpoena** arrives to turn the federal building into a "Museum of the Deep State" and sell $19.99 copies of *The Plot Against the King*.
  - *Fight Sequence 2 (`boss_kash_subpoena` & `boss_jd_pants`):* Stopping Kash's 60-name appendix list and JD's Swiss-cheese Philly cheesesteak order.
- **Episode 5.3 — Grand Finale: The Unpermitted Rooftop `X` Sign (2025–2026):**
  - *Story Scene:* **Commander America** and **Musk-X** install a giant pulsing strobe-light `X` sign on top of Manhattan while promising Full Self-Driving will arrive *"definitely next year."*
  - *Final Boss Fight (`boss_commander_america` & `boss_musk_x`):* Zohran unleashes *Mr. Cardamom Nani Beat Drop*, *Fare-Free Q70 Bus Stampede*, and *DSA Point of Personal Privilege* in the ultimate cross-aisle finale!
