package com.voicerpg.engine.engine

import com.voicerpg.engine.model.DialogueChoice
import com.voicerpg.engine.model.DialogueNode
import com.voicerpg.engine.model.DialogueSpeaker
import com.voicerpg.engine.model.SpeakerSide
import com.voicerpg.engine.model.StoryScene

object StoryScript {

    // -------------------------------------------------------------------------
    // ACT I SCENES (Dual-Campaign: 4 Deep Chapters on Track A + 4 Deep Chapters on Track B)
    // -------------------------------------------------------------------------
    val SCENE_ACT1_CH1_ESCALATOR = StoryScene(
        id = "scene_act1_ch1_escalator",
        name = "Trump Tower Atrium & Golden Escalator",
        chapterTitle = "Act I - Ch 1: The \$50 Escalator & The Lion King Roast (2015)",
        backgroundAsset = "story/golden_escalator.jpg",
        initialNodeId = "act1_ch1_intro",
        ambientDescription = "Polished brass escalators gleam in Manhattan while fifty-dollar Extra Mile Casting actors adjust their campaign T-shirts under blinding news cameras."
    )

    // Legacy alias
    val SCENE_ACT1_ESCALATOR = SCENE_ACT1_CH1_ESCALATOR

    val SCENE_ACT1_CH2_DEBATE = StoryScene(
        id = "scene_act1_ch2_debate",
        name = "Cleveland Debate Stage & Tan Suit VIP Box",
        chapterTitle = "Act I - Ch 2: Guacamole Bowls, Tan Suits & Dijon Mustard (2015–2016)",
        backgroundAsset = "story/primary_debate.jpg",
        initialNodeId = "act1_ch2_intro",
        ambientDescription = "Sixteen focus-grouped podiums line the arena while a 2014 summer tan suit, a styrofoam latte, and a jar of Grey Poupon Dijon mustard gleam under the spotlights."
    )

    val SCENE_ACT1_CH3_TACO_BUS = StoryScene(
        id = "scene_act1_ch3_taco_bus",
        name = "Trump Tower Grill & The 2005 Access Bus Lot",
        chapterTitle = "Act I - Ch 3: Taco Bowls & The Tic-Tac Bus That Never Dies (2016)",
        backgroundAsset = "story/trump_tower_taco_bowl.jpg",
        initialNodeId = "act1_ch3_intro",
        ambientDescription = "A fiesta taco bowl sits atop a 1990s newspaper photo of Marla Maples while an Access Hollywood tour bus idles outside with a crate of Tic-Tacs."
    )

    val SCENE_ACT1_CH3_RALLY = StoryScene(
        id = "scene_act1_ch3_rally",
        name = "The Final 2016 Debate Arena",
        chapterTitle = "Act I - Ch 4: BleachBit, Pokémon Go & Election Night (Nov 2016)",
        backgroundAsset = "story/airport_rally.jpg",
        initialNodeId = "act1_ch4_intro",
        ambientDescription = "Chappaqua server racks, microfiber cloths, and boxes of Tic-Tacs collide on the final 2016 debate stage."
    )

    // Track B (Hill-Rod) Act I Scenes — 4 Deep Chapters
    val SCENE_ACT1_TB_CH1_SERVER = StoryScene(
        id = "scene_act1_tb_ch1_server",
        name = "Chappaqua Basement Server Room",
        chapterTitle = "Act I - Ch 1: With A Cloth & 1978 Cattle Futures (2015)",
        backgroundAsset = "story/chappaqua_server_room.jpg",
        initialNodeId = "act1_tb_ch1_intro",
        ambientDescription = "An off-the-books email server blinks in a Chappaqua basement beside 1978 Refco live-cattle futures receipts and a microfiber cloth."
    )

    val SCENE_ACT1_TB_CH2_FERNS = StoryScene(
        id = "scene_act1_tb_ch2_ferns",
        name = "170th St. Bronx Subway Turnstile & Between Two Ferns Set",
        chapterTitle = "Act I - Ch 2: Five MetroCard Swipes & Two Potted Ferns (2016)",
        backgroundAsset = "story/bronx_subway_turnstile.jpg",
        initialNodeId = "act1_tb_ch2_intro",
        ambientDescription = "A stubborn MTA subway turnstile flashes 'SWIPE AGAIN AT THIS TURNSTILE' next to two dusty plastic studio ferns."
    )

    val SCENE_ACT1_TB_CH3_BREAKFAST = StoryScene(
        id = "scene_act1_tb_ch3_breakfast",
        name = "The Breakfast Club Radio Booth & Virginia Pokémon Rally",
        chapterTitle = "Act I - Ch 3: Purse Hot Sauce, Pokémon Go & No Basement (2016)",
        backgroundAsset = "story/breakfast_club_studio.jpg",
        initialNodeId = "act1_tb_ch3_intro",
        ambientDescription = "A bottle of Ninja Squirrel hot sauce sits beside a smartphone running Pokémon Go and blueprints of a slab-foundation pizzeria."
    )

    val SCENE_ACT1_TB_CH4_JAVITS = StoryScene(
        id = "scene_act1_tb_ch4_javits",
        name = "Javits Center Glass Ceiling & Final Debate Stage",
        chapterTitle = "Act I - Ch 4: The Blue-Curtain Director & Election Night (Nov 2016)",
        backgroundAsset = "story/javits_glass_ceiling.jpg",
        initialNodeId = "act1_tb_ch4_intro",
        ambientDescription = "Beneath the literal glass ceiling of the Javits Center, a 6-foot-8 FBI Director tries to blend into a blue curtain."
    )

    // Legacy alias for Track B Ch 3
    val SCENE_ACT1_TB_CH3_JAVITS = SCENE_ACT1_TB_CH4_JAVITS

    // -------------------------------------------------------------------------
    // ACT II SCENES (Dual-Campaign: 5 Deep Chapters on Track A + 5 Deep Chapters on Track B)
    // -------------------------------------------------------------------------
    val SCENE_ACT2_CH1_MALL = StoryScene(
        id = "scene_act2_ch1_mall",
        name = "National Mall & White House Bushes",
        chapterTitle = "Act II - Ch 1: Bushes, Cinnamon Gum & The 11-Day Mooch (2017)",
        backgroundAsset = "story/national_mall.jpg",
        initialNodeId = "act2_ch1_intro",
        ambientDescription = "The Capitol steps gleam under a January sky while Spicy Sean hides in the White House bushes chewing 35 pieces of Orbit Cinnamon gum.",
        musicAsset = "audio/music/bgm_title.ogg"
    )

    val SCENE_ACT2_CH2_OVAL = StoryScene(
        id = "scene_act2_ch2_oval",
        name = "The Oval Office & Truman Balcony",
        chapterTitle = "Act II - Ch 2: The Diet Coke Button, Covfefe & The Eclipse (2017)",
        backgroundAsset = "story/oval_citadel.jpg",
        initialNodeId = "act2_ch2_intro",
        ambientDescription = "A red wooden valet button sits on the Resolute Desk for Diet Coke emergencies while a solar eclipse darkens the Truman Balcony.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT2_CH3_SHARPIE = StoryScene(
        id = "scene_act2_ch3_sharpie",
        name = "San Juan Supply Gym & Oval Sharpie Easel",
        chapterTitle = "Act II - Ch 3: Paper-Towel Free Throws, Hurricane Sharpie & 15 Flushes (2017–2019)",
        backgroundAsset = "story/sharpie_hurricane.jpg",
        initialNodeId = "act2_ch3_sharpie_intro",
        ambientDescription = "Rolls of Bounty paper towels arc through the air beside an official NOAA hurricane poster extended into Alabama with a black Sharpie.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT2_CH4_SCIF = StoryScene(
        id = "scene_act2_ch4_scif",
        name = "House Intelligence Secure Vault (SCIF)",
        chapterTitle = "Act II - Ch 4: Enter Kash Subpoena, The Nunes Memo & Wizard Kash (2018–2019)",
        backgroundAsset = "story/scif_vault.jpg",
        initialNodeId = "act2_ch4_scif_intro",
        ambientDescription = "Acoustic vault doors seal the room as Kash Subpoena unrolls his four-page FISA memo alongside his 'Wizard Kash' children's book.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT2_CH3_ROTUNDA = StoryScene(
        id = "scene_act2_ch3_rotunda",
        name = "House Chamber & Sub-Zero Freezer Vault",
        chapterTitle = "Act II - Ch 5: Sideways Claps, Pre-Scored Speeches & \$24k Freezers (2020 Finale)",
        backgroundAsset = "story/capitol_rotunda.jpg",
        initialNodeId = "act2_ch3_intro",
        ambientDescription = "Marble columns tower above as Nancy Palooza pre-scores State of the Union pages beside twin \$24,000 Sub-Zero freezers.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT2_OVAL = StoryScene(
        id = "scene_act2_oval",
        name = "The Oval Citadel",
        chapterTitle = "Act II: Covfefe, Sub-Zero Freezers & The Nunes Scroll (2017–2020)",
        backgroundAsset = "story/oval_citadel.jpg",
        initialNodeId = "act2_ch1_intro",
        ambientDescription = "Gilded drapes frame the Oval Office where a Hurricane Dorian Sharpie map sits on an easel.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    // Track B (Nancy Palooza) Act II Scenes — 5 Deep Chapters
    val SCENE_ACT2_TB_CH1_BUSHES = StoryScene(
        id = "scene_act2_tb_ch1_bushes",
        name = "Speaker's Rostrum & White House Shrubbery",
        chapterTitle = "Act II - Ch 1: Sideways Seal Claps vs. Neon Salsa Ruffles (2017–2019)",
        backgroundAsset = "story/spicer_bushes.jpg",
        initialNodeId = "act2_tb_ch1_intro",
        ambientDescription = "Speaker Nancy Palooza's gavel gleams while Spicy Sean rustles inside the White House bushes wearing a neon-green ruffled salsa shirt.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT2_TB_CH2_SOTU = StoryScene(
        id = "scene_act2_tb_ch2_sotu",
        name = "House Rostrum — State of the Union Dais",
        chapterTitle = "Act II - Ch 2: Pre-Scoring the Speech & The 75%-Speed Video (2019–2020)",
        backgroundAsset = "story/capitol_rotunda.jpg",
        initialNodeId = "act2_tb_ch2_intro",
        ambientDescription = "A thick stack of State of the Union cardstock pages sits on the Speaker's desk with discreet starter notches pre-ripped into the margin.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT2_TB_CH3_SUBZERO = StoryScene(
        id = "scene_act2_tb_ch3_subzero",
        name = "San Francisco Sub-Zero Kitchen & eSalon",
        chapterTitle = "Act II - Ch 3: Twin \$24,000 Freezers & The Salon 'Setup' (April–Aug 2020)",
        backgroundAsset = "story/subzero_kitchen.jpg",
        initialNodeId = "act2_tb_ch3_intro",
        ambientDescription = "Twin \$24,000 stainless-steel Sub-Zero freezers hum with \$12 pints of Jeni's Splendid Ice Cream next to a neighborhood hair salon.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT2_TB_CH4_STOCKS = StoryScene(
        id = "scene_act2_tb_ch4_stocks",
        name = "House Clerk Periodic Transaction Report Vault",
        chapterTitle = "Act II - Ch 4: Deep-ITM Call Options & The \$341k Nvidia Loss (2020–2022)",
        backgroundAsset = "story/hearing_room.jpg",
        initialNodeId = "act2_tb_ch4_intro",
        ambientDescription = "Stock tickers flash alongside a July 26, 2022 House Clerk filing documenting a \$341,365 pre-CHIPS Act Nvidia loss.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT2_TB_CH5_FOUR_SEASONS = StoryScene(
        id = "scene_act2_tb_ch5_four_seasons",
        name = "Four Seasons Total Landscaping — Philadelphia",
        chapterTitle = "Act II - Ch 5: Between a Crematorium and an Adult Bookshop (Nov 2020 Finale)",
        backgroundAsset = "story/four_seasons_landscaping.jpg",
        initialNodeId = "act2_tb_ch5_intro",
        ambientDescription = "A garage door plastered with campaign signs stands wedged between the Delaware Valley Crematorium and Fantasy Island Adult Books.",
        musicAsset = "audio/music/bgm_title.ogg"
    )

    // -------------------------------------------------------------------------
    // ACT III SCENES (Dual-Campaign: 5 Deep Chapters on Track A + 5 Deep Chapters on Track B)
    // -------------------------------------------------------------------------
    val SCENE_ACT3_CH1_STEEL_MILL = StoryScene(
        id = "scene_act3_ch1_steel_mill",
        name = "Andrews Tarmac Stairs & Rehoboth Bike Path",
        chapterTitle = "Act III - Ch 1: Three Stumbles Up the Stairs & Toe-Cage Bikes (2021–2022)",
        backgroundAsset = "story/air_force_one.jpg",
        initialNodeId = "act3_ch1_intro",
        ambientDescription = "Wind gusts sweep across the Air Force One staircase beside a stationary Trek bicycle equipped with treacherous metal toe-cages.",
        musicAsset = "audio/music/bgm_title.ogg"
    )

    val SCENE_ACT3_CH2_USMCA = StoryScene(
        id = "scene_act3_ch2_usmca",
        name = "Senate Judiciary Committee Chamber",
        chapterTitle = "Act III - Ch 2: Enter Foghorn Kennedy & 'Call A Crackhead!' (2021–2023)",
        backgroundAsset = "story/senate_hearing.jpg",
        initialNodeId = "act3_ch2_intro",
        ambientDescription = "Senator Foghorn Kennedy hides an Oxford First-Class law degree behind a folksy Louisiana drawl as judicial nominees sweat under the lights.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT3_CH3_AF1 = StoryScene(
        id = "scene_act3_ch3_af1",
        name = "Mar-a-Lago Bathroom Chandelier & Corvette Garage",
        chapterTitle = "Act III - Ch 3: Bathroom Chandeliers vs. The 1967 Corvette Garage (2022–2023)",
        backgroundAsset = "story/storage_vault.jpg",
        initialNodeId = "act3_ch3_intro",
        ambientDescription = "Cardboard bankers boxes tower beneath a gilded Mar-a-Lago bathroom chandelier across from a locked Wilmington Corvette garage.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT3_CH4_SUMMIT = StoryScene(
        id = "scene_act3_ch4_summit",
        name = "Fulton County Booking Desk & Trump Force One",
        chapterTitle = "Act III - Ch 4: The '215-Pound' Mugshot & \$7.1M 'Never Surrender' Merch (Aug 2023)",
        backgroundAsset = "story/fulton_county_booking.jpg",
        initialNodeId = "act3_ch4_intro",
        ambientDescription = "Booking cameras flash as a 6-foot-3, 215-pound mugshot is immediately printed onto 'NEVER SURRENDER!' T-shirts and coffee mugs.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT3_CH5_BASKIN = StoryScene(
        id = "scene_act3_ch5_baskin",
        name = "Portland Baskin-Robbins & Teleprompter Stage",
        chapterTitle = "Act III - Ch 5: 'Economy Strong As Hell' & 'Repeat the Line' (2022–2023 Finale)",
        backgroundAsset = "story/baskin_robbins_portland.jpg",
        initialNodeId = "act3_ch5_baskin_intro",
        ambientDescription = "Joe Naptime licks a double-scoop chocolate chip waffle cone at Baskin-Robbins beside a teleprompter flashing 'End of quote. Repeat the line.'",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    val SCENE_ACT3_SUMMIT = StoryScene(
        id = "scene_act3_summit",
        name = "The Crimson Summit Spire",
        chapterTitle = "Act III: Ice Cream, Corn Pop & Badakathcare",
        backgroundAsset = "story/summit_table.jpg",
        initialNodeId = "act3_ch1_intro",
        ambientDescription = "Empty Baskin-Robbins waffle cones litter the stage."
    )

    // Track B (Joe Naptime) Act III Scenes — 5 Deep Chapters
    val SCENE_ACT3_TB_CH1_POOL = StoryScene(
        id = "scene_act3_tb_ch1_pool",
        name = "Wilmington Swimming Pool & Rehoboth Bike Trail",
        chapterTitle = "Act III - Ch 1: Corn Pop's Rusty Razor, Blonde Leg Hair & Toe Cages (2019–2022)",
        backgroundAsset = "story/rehoboth_bike_trail.jpg",
        initialNodeId = "act3_tb_ch1_intro",
        ambientDescription = "A 6-foot poolside chain and a rainwater barrel of rusty razors sit beside a Rehoboth Beach bicycle trail.",
        musicAsset = "audio/music/bgm_title.ogg"
    )

    val SCENE_ACT3_TB_CH2_TELEPROMPTER = StoryScene(
        id = "scene_act3_tb_ch2_teleprompter",
        name = "White House Teleprompter & Andrews Staircase",
        chapterTitle = "Act III - Ch 2: 'End of Quote, Repeat the Line' & Two Vindications (2021–2022)",
        backgroundAsset = "story/scranton_platform.jpg",
        initialNodeId = "act3_tb_ch2_intro",
        ambientDescription = "A giant glass teleprompter displays bracketed stage directions in 72-point font while wind gusts rattle the tarmac stairs.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT3_TB_CH3_BASKIN = StoryScene(
        id = "scene_act3_tb_ch3_baskin",
        name = "Portland Baskin-Robbins Ice-Cream Counter",
        chapterTitle = "Act III - Ch 3: 'Economy Strong As Hell' vs. Foghorn Kennedy (2022–2023)",
        backgroundAsset = "story/baskin_robbins_portland.jpg",
        initialNodeId = "act3_tb_ch3_intro",
        ambientDescription = "Thirty-one flavors of ice cream gleam under neon lights as Joe Naptime orders a double-scoop chocolate chip waffle cone.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT3_TB_CH4_GARAGE = StoryScene(
        id = "scene_act3_tb_ch4_garage",
        name = "Wilmington Locked 1967 Corvette Garage",
        chapterTitle = "Act III - Ch 4: The Corvette Garage & Real-Audio Gibberish Symphony (2023–2024)",
        backgroundAsset = "story/basement_bunker.jpg",
        initialNodeId = "act3_tb_ch4_intro",
        ambientDescription = "A green 1967 Chevrolet Corvette Stingray sits parked beside a crushed cardboard box of classified folders and a freezer of Jeni's pints.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT3_TB_CH5_MUGSHOT = StoryScene(
        id = "scene_act3_tb_ch5_mugshot",
        name = "Mar-a-Lago Chandelier Bathroom & Fulton Booking Desk",
        chapterTitle = "Act III - Ch 5: The '215-Lb' Mugshot & Passing the Coconut to Kamala (2023–2024 Finale)",
        backgroundAsset = "story/fulton_county_booking.jpg",
        initialNodeId = "act3_tb_ch5_intro",
        ambientDescription = "A gilded bathroom chandelier illuminates stacks of bankers boxes beside a 'NEVER SURRENDER!' mugshot T-shirt press.",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    // -------------------------------------------------------------------------
    // ACT IV SCENES (Dual-Campaign: 5 Deep Chapters on Track A + 5 Deep Chapters on Track B)
    // -------------------------------------------------------------------------
    val SCENE_ACT4_CH1_FISA = StoryScene(
        id = "scene_act4_ch1_fisa",
        name = "Holt's Sweet Shoppe — Valdosta, Georgia",
        chapterTitle = "Act IV - Ch 1: Enter JD Pants & 'Whatever Makes Sense' Donuts (Aug 2024)",
        backgroundAsset = "story/holt_donut_shop.jpg",
        initialNodeId = "act4_ch1_intro",
        ambientDescription = "Fluorescent bakery lights buzz as JD Pants asks 'How long have you worked here? Okay, good' over a tray of sprinkle donuts.",
        musicAsset = "audio/music/bgm_title.ogg"
    )

    val SCENE_ACT4_CH2_BINDER = StoryScene(
        id = "scene_act4_ch2_binder",
        name = "Lester Holt Interview Set & Border Wall Gap",
        chapterTitle = "Act IV - Ch 2: 'And I Haven't Been to Europe!' & Ukraine Preschool (2021–2024)",
        backgroundAsset = "story/border_wall.jpg",
        initialNodeId = "act4_ch2_intro",
        ambientDescription = "Lester Holt asks why nobody has visited the southern border while a globe highlights Europe and Ukraine.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT4_CH3_COURT = StoryScene(
        id = "scene_act4_ch3_court",
        name = "Electric Yellow School Bus Depot & Venn Mirage",
        chapterTitle = "Act IV - Ch 3: Venn Diagrams, Yellow School Buses & '32 Days!' (2022–2024)",
        backgroundAsset = "story/electric_bus.jpg",
        initialNodeId = "act4_ch3_intro",
        ambientDescription = "Three glowing Venn diagram circles hover above a yellow electric school bus and a glitching Flint teleprompter.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT4_CH4_HEARING = StoryScene(
        id = "scene_act4_ch4_hearing",
        name = "Feasterville McDonald's Fry Station & Green Bay Garbage Truck",
        chapterTitle = "Act IV - Ch 4: The McDonald's Fry Apron & Orange Garbage Vest (Oct 2024)",
        backgroundAsset = "story/mcdonalds_fry_station.jpg",
        initialNodeId = "act4_ch4_intro",
        ambientDescription = "Golden French-fry baskets sizzle beside a white Mack garbage truck and a high-visibility neon-orange safety vest.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT4_CH5_COCONUT_FINALE = StoryScene(
        id = "scene_act4_ch5_coconut_finale",
        name = "The Coconut Grove & Unburdened Throne",
        chapterTitle = "Act IV - Ch 5: Falling Out of a Coconut Tree & 'Unburdened' (Nov 2024 Finale)",
        backgroundAsset = "story/coconut_grove.jpg",
        initialNodeId = "act4_ch5_coconut_intro",
        ambientDescription = "Coconuts drop in the context of all in which you live and what came before you.",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    val SCENE_ACT4_MUELLER = StoryScene(
        id = "scene_act4_mueller",
        name = "The Special Counsel Sanctum",
        chapterTitle = "Act IV: Coconut Trees, Donut Shops & The Torch Pass",
        backgroundAsset = "story/hearing_room.jpg",
        initialNodeId = "act4_ch1_intro",
        ambientDescription = "Subpoenas flutter through the committee chamber."
    )

    // Track B (Kamala Coconut) Act IV Scenes — 5 Deep Chapters
    val SCENE_ACT4_TB_CH1_COCONUT = StoryScene(
        id = "scene_act4_tb_ch1_coconut",
        name = "The Context of the Coconut Grove",
        chapterTitle = "Act IV - Ch 1: Falling Out of a Coconut Tree & Venn Diagrams (2023–July 2024)",
        backgroundAsset = "story/coconut_grove.jpg",
        initialNodeId = "act4_tb_ch1_intro",
        ambientDescription = "Towering coconut palms sway in the context of all in which you live and what came before you.",
        musicAsset = "audio/music/bgm_title.ogg"
    )

    val SCENE_ACT4_TB_CH2_DONUTS = StoryScene(
        id = "scene_act4_tb_ch2_donuts",
        name = "Valdosta Donut Counter & South Philly Cheesesteak Window",
        chapterTitle = "Act IV - Ch 2: 'I'm Speaking!' vs. JD Pants's Donut Interrogation (Aug–Oct 2024)",
        backgroundAsset = "story/holt_donut_shop.jpg",
        initialNodeId = "act4_tb_ch2_intro",
        ambientDescription = "A box of 'some sprinkle stuff, whatever makes sense' glazed donuts sits next to a wedge of outlawed Philly Swiss cheese.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT4_TB_CH3_PASSAGE = StoryScene(
        id = "scene_act4_tb_ch3_passage",
        name = "The Unburdened Dais of the Passage of Time",
        chapterTitle = "Act IV - Ch 3: 'The Significance of the Passage of Time' & Tiffany Pearls (2022–2024)",
        backgroundAsset = "story/unburdened_throne.jpg",
        initialNodeId = "act4_tb_ch3_intro",
        ambientDescription = "Clocks tick four times in thirty-two seconds to mark the significance of the passage of time.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT4_TB_CH4_DEBATE = StoryScene(
        id = "scene_act4_tb_ch4_debate",
        name = "Philadelphia Debate Stage — National Constitution Center",
        chapterTitle = "Act IV - Ch 4: 'Concepts of a Plan' & 'I Saw It on Television!' (Sept 10, 2024)",
        backgroundAsset = "story/primary_debate.jpg",
        initialNodeId = "act4_tb_ch4_intro",
        ambientDescription = "Split-screen cameras capture a hand-on-chin courtroom expression as 'Concepts of a Plan' echoes across the stage.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT4_TB_CH5_MCDONALDS = StoryScene(
        id = "scene_act4_tb_ch5_mcdonalds",
        name = "Closed-Set Drive-Thru Window & Garbage Truck Tarmac",
        chapterTitle = "Act IV - Ch 5: The Fry Apron, The Garbage Vest & Passing the Chapati (Nov 2024 Finale)",
        backgroundAsset = "story/mcdonalds_fry_station.jpg",
        initialNodeId = "act4_tb_ch5_intro",
        ambientDescription = "A closed-set McDonald's drive-thru window gleams beside a neon-orange garbage truck vest and a Queens chapati food truck.",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    // -------------------------------------------------------------------------
    // ACT V SCENES (Dual-Campaign: 5 Deep Chapters on Track A + 5 Deep Chapters on Track B)
    // -------------------------------------------------------------------------
    val SCENE_ACT5_CH1_ARENA = StoryScene(
        id = "scene_act5_ch1_arena",
        name = "Butler Fairgrounds & Cybertruck Demo Stage",
        chapterTitle = "Act V - Ch 1: Dark Gothic MAGA Star-Jump & The Porcelain Sink (2024–2025)",
        backgroundAsset = "story/butler_fairgrounds.jpg",
        initialNodeId = "act5_ch1_intro",
        ambientDescription = "Musk-X leaps into a mid-air X-shape exposing four inches of midriff beside a porcelain bathroom sink and two shattered Cybertruck windows.",
        musicAsset = "audio/music/bgm_title.ogg"
    )

    val SCENE_ACT5_CH2_SENATE = StoryScene(
        id = "scene_act5_ch2_senate",
        name = "Milwaukee Convention Floor & DOGE Command",
        chapterTitle = "Act V - Ch 2: Hulkster's Shirt-Rip, 'Hey Bob!' & DOGE (2024–2025)",
        backgroundAsset = "story/rnc_milwaukee.jpg",
        initialNodeId = "act5_ch2_intro",
        ambientDescription = "Hulkster 24-Pythons rips two shirts in half on stage while a Shiba Inu DOGE banner gleams over federal budget spreadsheets.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT5_CH3_TERRACE = StoryScene(
        id = "scene_act5_ch3_terrace",
        name = "Turkish Airlines Lounge & Osteria La Baia",
        chapterTitle = "Act V - Ch 3: Mayor Swagger's Turkish Upgrades & 'Vegan' Sea Bass (2024–2025)",
        backgroundAsset = "story/maralago_terrace.jpg",
        initialNodeId = "act5_ch3_intro",
        ambientDescription = "Mayor Swagger sips New York tap water next to a Turkish Airlines lie-flat seat, a plate of midnight Chilean sea bass, and a marble-jar baby doll.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT5_CH4_CLOSETS = StoryScene(
        id = "scene_act5_ch4_closets",
        name = "Astoria Chapati Food Truck & Municipal Grocery",
        chapterTitle = "Act V - Ch 4: Enter 'Mr. Cardamom,' Nani's Apron & City-Owned Groceries (2025–2026)",
        backgroundAsset = "story/chapati_food_truck.jpg",
        initialNodeId = "act5_ch4_intro",
        ambientDescription = "Zohran Cardamom ('Mr. Cardamom') flips chapatis in an apron beside a 'FREEZE THE RENT & FREE Q70 BUS' sign and five city-owned grocery blueprints.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT5_CH5_FINALE = StoryScene(
        id = "scene_act5_ch5_finale",
        name = "The 2026 Bipartisan Grand Finale Arena",
        chapterTitle = "Act V - Ch 5: The 2026 Bipartisan Roast Finale (Grand Climax)",
        backgroundAsset = "story/golden_inauguration.jpg",
        initialNodeId = "act5_ch5_finale_intro",
        ambientDescription = "Confetti, chapatis, waffle cones, and Cybertruck steel balls rain across the Grand Finale stage.",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    val SCENE_ACT5_MARALAGO = StoryScene(
        id = "scene_act5_maralago",
        name = "The Mar-a-Lago Fortress",
        chapterTitle = "Act V: Star-Jumps, Mr. Cardamom & The 2026 Grand Finale",
        backgroundAsset = "story/maralago_terrace.jpg",
        initialNodeId = "act5_ch1_intro",
        ambientDescription = "Ocean breezes sweep across golden palm trees.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    // Track B (Zohran Cardamom + Mayor Swagger) Act V Scenes — 5 Deep Chapters
    val SCENE_ACT5_TB_CH1_CHAPATI = StoryScene(
        id = "scene_act5_tb_ch1_chapati",
        name = "Astoria Chapati Truck & Free Q70 Bus Stop",
        chapterTitle = "Act V - Ch 1: 'Mr. Cardamom,' Nani's Apron & 5 City Grocery Stores (2025)",
        backgroundAsset = "story/chapati_food_truck.jpg",
        initialNodeId = "act5_tb_ch1_intro",
        ambientDescription = "Sizzling chapatis, a bespoke thrifted suit, Madhur Jaffrey's apron, and an MTA Fare-Free Bus Pilot report.",
        musicAsset = "audio/music/bgm_title.ogg"
    )

    val SCENE_ACT5_TB_CH2_SWAGGER = StoryScene(
        id = "scene_act5_tb_ch2_swagger",
        name = "Gracie Mansion Kitchen & Turkish Business Class",
        chapterTitle = "Act V - Ch 2: Mayor Swagger's Contraband Baby-Doll & Midnight Sea Bass (2025)",
        backgroundAsset = "story/gag_hearing.jpg",
        initialNodeId = "act5_tb_ch2_intro",
        ambientDescription = "Mayor Swagger inspects a plastic baby doll and glass marble jar beside a locked 6-digit iPhone and Turkish Airlines boarding passes.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT5_TB_CH3_CYBERTRUCK = StoryScene(
        id = "scene_act5_tb_ch3_cybertruck",
        name = "Butler Star-Jump Stage & Shattered Cybertruck Lab",
        chapterTitle = "Act V - Ch 3: Roasting Musk-X's Mid-Air 'X' Jump & Porcelain Sink (2025–2026)",
        backgroundAsset = "story/butler_fairgrounds.jpg",
        initialNodeId = "act5_tb_ch3_intro",
        ambientDescription = "A porcelain bathroom sink sits next to a Cybertruck with two steel-ball-shattered 'Armor Glass' windows.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT5_TB_CH4_DOGE = StoryScene(
        id = "scene_act5_tb_ch4_doge",
        name = "Department of Government Efficiency (DOGE) Vault",
        chapterTitle = "Act V - Ch 4: Shiba Inu Bureaucracy vs. Sprinkle Donuts & Wizard Books (2026)",
        backgroundAsset = "story/senate_hearing.jpg",
        initialNodeId = "act5_tb_ch4_intro",
        ambientDescription = "A giant Shiba Inu meme coin logo hangs over JD Pants's donut box, Wizard Kash's children's book, and Foghorn Kennedy's gavel.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    val SCENE_ACT5_TB_CH5_FINALE = StoryScene(
        id = "scene_act5_tb_ch5_finale",
        name = "The 2026 Bipartisan Grand Finale Stage",
        chapterTitle = "Act V - Ch 5: Chapatis vs. Diet Coke Buttons — The Bipartisan Curtain Call (2026 Finale)",
        backgroundAsset = "story/golden_inauguration.jpg",
        initialNodeId = "act5_tb_ch5_intro",
        ambientDescription = "All six narrators and the entire bipartisan cast gather under golden spotlights for the final showdown.",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    val SCENE_ACT6_CH1_BORDER = StoryScene(
        id = "scene_act6_ch1_border",
        name = "The Southern Border Wall Gap",
        chapterTitle = "Act VI - Ch 1: We've Been To The Border",
        backgroundAsset = "story/border_wall.jpg",
        initialNodeId = "act6_ch1_intro",
        ambientDescription = "Lester Holt asks why nobody has visited the border yet."
    )

    val SCENE_ACT6_CH2_VENN = StoryScene(
        id = "scene_act6_ch2_venn",
        name = "The Triple-Circle Venn Mirage",
        chapterTitle = "Act VI - Ch 2: Those Three Circles",
        backgroundAsset = "story/venn_fog.jpg",
        initialNodeId = "act6_ch2_intro",
        ambientDescription = "Three overlapping glowing Venn diagram circles float in the mist."
    )

    val SCENE_ACT6_CH3_BUS = StoryScene(
        id = "scene_act6_ch3_bus",
        name = "The Electric Yellow School Bus Depot",
        chapterTitle = "Act VI - Ch 3: Who Doesn't Love A Yellow School Bus?",
        backgroundAsset = "story/electric_bus.jpg",
        initialNodeId = "act6_ch3_intro",
        ambientDescription = "Yellow electric school buses gleam under the sun."
    )

    val SCENE_ACT6_CH4_COCONUT = StoryScene(
        id = "scene_act6_ch4_coconut",
        name = "The Context of the Coconut Grove",
        chapterTitle = "Act VI - Ch 4: Falling Out of a Coconut Tree",
        backgroundAsset = "story/coconut_grove.jpg",
        initialNodeId = "act6_ch4_intro",
        ambientDescription = "Towering coconut palms sway in the context of all in which you live and what came before you."
    )

    val SCENE_ACT6_CH5_UNBURDENED = StoryScene(
        id = "scene_act6_ch5_unburdened",
        name = "The Unburdened Throne of What Can Be",
        chapterTitle = "Act VI - Ch 5: Unburdened By What Has Been",
        backgroundAsset = "story/unburdened_throne.jpg",
        initialNodeId = "act6_ch5_intro",
        ambientDescription = "A dais elevated by the significance of the passage of time."
    )

    val SCENE_ACT6_WASTELAND = StoryScene(
        id = "scene_act6_wasteland",
        name = "The Word-Salad Wasteland",
        chapterTitle = "Act VI: The Word-Salad Wasteland",
        backgroundAsset = "story/unburdened_throne.jpg",
        initialNodeId = "act6_ch1_intro",
        ambientDescription = "Floating Venn diagrams drift over the horizon."
    )

    val SCENE_ACT7_CH1_GAUNTLET = StoryScene(
        id = "scene_act7_ch1_gauntlet",
        name = "The Courtroom Steps of Manhattan",
        chapterTitle = "Act VII - Ch 1: The Docket Gauntlet",
        backgroundAsset = "story/courtroom_steps.jpg",
        initialNodeId = "act7_ch1_intro",
        ambientDescription = "Subpoenas and children's book flyers flutter across the marble steps."
    )

    val SCENE_ACT7_CH2_GAG = StoryScene(
        id = "scene_act7_ch2_gag",
        name = "The Hearing Chamber",
        chapterTitle = "Act VII - Ch 2: Pleading the Fifth for Immunity",
        backgroundAsset = "story/gag_hearing.jpg",
        initialNodeId = "act7_ch2_intro",
        ambientDescription = "A gavel echoes inside the grand jury chamber."
    )

    val SCENE_ACT7_CH3_BUTLER = StoryScene(
        id = "scene_act7_ch3_butler",
        name = "Butler Fairgrounds & Mid-Air Star-Jump",
        chapterTitle = "Act VII - Ch 3: Dark Gothic MAGA Star-Jump",
        backgroundAsset = "story/butler_fairgrounds.jpg",
        initialNodeId = "act7_ch3_intro",
        ambientDescription = "Musk-X leaps into an X-shape mid-air exposing four inches of midriff."
    )

    val SCENE_ACT7_CH4_STEEL_EAR = StoryScene(
        id = "scene_act7_ch4_steel_ear",
        name = "The Stage of Resolve",
        chapterTitle = "Act VII - Ch 4: Fight! Fight! Fight!",
        backgroundAsset = "story/steel_ear_miracle.jpg",
        initialNodeId = "act7_ch4_intro",
        ambientDescription = "Fist raised under the summer sky."
    )

    val SCENE_ACT7_CH5_MILWAUKEE = StoryScene(
        id = "scene_act7_ch5_milwaukee",
        name = "Fiserv Forum Convention Floor",
        chapterTitle = "Act VII - Ch 5: Hulkster Shirt-Rip Coronation",
        backgroundAsset = "story/rnc_milwaukee.jpg",
        initialNodeId = "act7_ch5_intro",
        ambientDescription = "Balloons drop as Hulkster 24-Pythons rips his shirt in half on stage."
    )

    val SCENE_ACT7_SANDS = StoryScene(
        id = "scene_act7_sands",
        name = "The Sands of Resolve",
        chapterTitle = "Act VII: Donut Shops, Star-Jumps & Conventions",
        backgroundAsset = "story/steel_ear_miracle.jpg",
        initialNodeId = "act7_ch1_intro",
        ambientDescription = "A box of 'whatever makes sense' glazed donuts sits on the counter."
    )

    val SCENE_ACT8_CH1_BUNKER = StoryScene(
        id = "scene_act8_ch1_bunker",
        name = "Wilmington Ice-Cream Freezer Bunker",
        chapterTitle = "Act VIII - Ch 1: Double-Scoop Chocolate Chip",
        backgroundAsset = "story/basement_bunker.jpg",
        initialNodeId = "act8_ch1_intro",
        ambientDescription = "Stacks of Jeni's Splendid Ice Cream pints hum inside a climate-controlled Wilmington basement."
    )

    val SCENE_ACT8_CH2_TELEPROMPTER = StoryScene(
        id = "scene_act8_ch2_teleprompter",
        name = "The Scranton Train Platform",
        chapterTitle = "Act VIII - Ch 2: End of Quote, Repeat the Line",
        backgroundAsset = "story/scranton_platform.jpg",
        initialNodeId = "act8_ch2_intro",
        ambientDescription = "A giant glass teleprompter flashes stage directions that get read aloud word for word."
    )

    val SCENE_ACT8_CH3_AUTOPEN = StoryScene(
        id = "scene_act8_ch3_autopen",
        name = "The Baskin-Robbins Inflation Counter",
        chapterTitle = "Act VIII - Ch 3: Economy Strong As Hell, Man",
        backgroundAsset = "story/autopen_factory.jpg",
        initialNodeId = "act8_ch3_intro",
        ambientDescription = "Waffle cones crunch as reporters shout questions about the Consumer Price Index."
    )

    val SCENE_ACT8_CH4_CITADEL = StoryScene(
        id = "scene_act8_ch4_citadel",
        name = "The Cosmic Ice-Cream Citadel",
        chapterTitle = "Act VIII - Ch 4: Truinanashabbadepressure Climax",
        backgroundAsset = "story/oval_citadel.jpg",
        initialNodeId = "act8_ch4_intro",
        ambientDescription = "Joe Naptime adjusts his Ray-Ban aviators over a towering hot-fudge sundae.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    val SCENE_ACT8_CH5_INAUGURATION = StoryScene(
        id = "scene_act8_ch5_inauguration",
        name = "The Grand Finale Stage",
        chapterTitle = "Act VIII - Ch 5: The Bipartisan Roast Finale",
        backgroundAsset = "story/golden_inauguration.jpg",
        initialNodeId = "act8_ch5_intro",
        ambientDescription = "Both sides of the aisle collide in a shower of confetti, subpoenas, waffle cones, and chapatis.",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    val SCENE_ACT8_CITADEL_FINAL = StoryScene(
        id = "scene_act8_citadel_final",
        name = "The Final Malarkey Citadel",
        chapterTitle = "Act VIII: Eradicating Malarkey - The Final Showdown",
        backgroundAsset = "story/golden_inauguration.jpg",
        initialNodeId = "act8_ch1_intro",
        ambientDescription = "A cosmic arena powered by infinite scoops of chocolate chip ice cream.",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    val SCENE_COTTAGE = SCENE_ACT1_ESCALATOR

    val ALL_SCENES = mapOf(
        SCENE_ACT1_CH1_ESCALATOR.id to SCENE_ACT1_CH1_ESCALATOR,
        "scene_act1_escalator" to SCENE_ACT1_CH1_ESCALATOR,
        SCENE_ACT1_CH2_DEBATE.id to SCENE_ACT1_CH2_DEBATE,
        SCENE_ACT1_CH3_TACO_BUS.id to SCENE_ACT1_CH3_TACO_BUS,
        SCENE_ACT1_CH3_RALLY.id to SCENE_ACT1_CH3_RALLY,
        SCENE_ACT1_TB_CH1_SERVER.id to SCENE_ACT1_TB_CH1_SERVER,
        SCENE_ACT1_TB_CH2_FERNS.id to SCENE_ACT1_TB_CH2_FERNS,
        SCENE_ACT1_TB_CH3_BREAKFAST.id to SCENE_ACT1_TB_CH3_BREAKFAST,
        SCENE_ACT1_TB_CH4_JAVITS.id to SCENE_ACT1_TB_CH4_JAVITS,
        "scene_act1_tb_ch3_javits" to SCENE_ACT1_TB_CH4_JAVITS,
        SCENE_ACT2_CH1_MALL.id to SCENE_ACT2_CH1_MALL,
        SCENE_ACT2_CH2_OVAL.id to SCENE_ACT2_CH2_OVAL,
        SCENE_ACT2_CH3_SHARPIE.id to SCENE_ACT2_CH3_SHARPIE,
        SCENE_ACT2_CH4_SCIF.id to SCENE_ACT2_CH4_SCIF,
        SCENE_ACT2_CH3_ROTUNDA.id to SCENE_ACT2_CH3_ROTUNDA,
        SCENE_ACT2_OVAL.id to SCENE_ACT2_CH1_MALL,
        SCENE_ACT2_TB_CH1_BUSHES.id to SCENE_ACT2_TB_CH1_BUSHES,
        SCENE_ACT2_TB_CH2_SOTU.id to SCENE_ACT2_TB_CH2_SOTU,
        SCENE_ACT2_TB_CH3_SUBZERO.id to SCENE_ACT2_TB_CH3_SUBZERO,
        SCENE_ACT2_TB_CH4_STOCKS.id to SCENE_ACT2_TB_CH4_STOCKS,
        SCENE_ACT2_TB_CH5_FOUR_SEASONS.id to SCENE_ACT2_TB_CH5_FOUR_SEASONS,
        SCENE_ACT3_CH1_STEEL_MILL.id to SCENE_ACT3_CH1_STEEL_MILL,
        SCENE_ACT3_CH2_USMCA.id to SCENE_ACT3_CH2_USMCA,
        SCENE_ACT3_CH3_AF1.id to SCENE_ACT3_CH3_AF1,
        SCENE_ACT3_CH4_SUMMIT.id to SCENE_ACT3_CH4_SUMMIT,
        SCENE_ACT3_CH5_BASKIN.id to SCENE_ACT3_CH5_BASKIN,
        SCENE_ACT3_SUMMIT.id to SCENE_ACT3_CH1_STEEL_MILL,
        SCENE_ACT3_TB_CH1_POOL.id to SCENE_ACT3_TB_CH1_POOL,
        SCENE_ACT3_TB_CH2_TELEPROMPTER.id to SCENE_ACT3_TB_CH2_TELEPROMPTER,
        SCENE_ACT3_TB_CH3_BASKIN.id to SCENE_ACT3_TB_CH3_BASKIN,
        SCENE_ACT3_TB_CH4_GARAGE.id to SCENE_ACT3_TB_CH4_GARAGE,
        SCENE_ACT3_TB_CH5_MUGSHOT.id to SCENE_ACT3_TB_CH5_MUGSHOT,
        SCENE_ACT4_CH1_FISA.id to SCENE_ACT4_CH1_FISA,
        SCENE_ACT4_CH2_BINDER.id to SCENE_ACT4_CH2_BINDER,
        SCENE_ACT4_CH3_COURT.id to SCENE_ACT4_CH3_COURT,
        SCENE_ACT4_CH4_HEARING.id to SCENE_ACT4_CH4_HEARING,
        SCENE_ACT4_CH5_COCONUT_FINALE.id to SCENE_ACT4_CH5_COCONUT_FINALE,
        SCENE_ACT4_MUELLER.id to SCENE_ACT4_CH1_FISA,
        SCENE_ACT4_TB_CH1_COCONUT.id to SCENE_ACT4_TB_CH1_COCONUT,
        SCENE_ACT4_TB_CH2_DONUTS.id to SCENE_ACT4_TB_CH2_DONUTS,
        SCENE_ACT4_TB_CH3_PASSAGE.id to SCENE_ACT4_TB_CH3_PASSAGE,
        SCENE_ACT4_TB_CH4_DEBATE.id to SCENE_ACT4_TB_CH4_DEBATE,
        SCENE_ACT4_TB_CH5_MCDONALDS.id to SCENE_ACT4_TB_CH5_MCDONALDS,
        SCENE_ACT5_CH1_ARENA.id to SCENE_ACT5_CH1_ARENA,
        SCENE_ACT5_CH2_SENATE.id to SCENE_ACT5_CH2_SENATE,
        SCENE_ACT5_CH3_TERRACE.id to SCENE_ACT5_CH3_TERRACE,
        SCENE_ACT5_CH4_CLOSETS.id to SCENE_ACT5_CH4_CLOSETS,
        SCENE_ACT5_CH5_FINALE.id to SCENE_ACT5_CH5_FINALE,
        SCENE_ACT5_MARALAGO.id to SCENE_ACT5_CH1_ARENA,
        SCENE_ACT5_TB_CH1_CHAPATI.id to SCENE_ACT5_TB_CH1_CHAPATI,
        SCENE_ACT5_TB_CH2_SWAGGER.id to SCENE_ACT5_TB_CH2_SWAGGER,
        SCENE_ACT5_TB_CH3_CYBERTRUCK.id to SCENE_ACT5_TB_CH3_CYBERTRUCK,
        SCENE_ACT5_TB_CH4_DOGE.id to SCENE_ACT5_TB_CH4_DOGE,
        SCENE_ACT5_TB_CH5_FINALE.id to SCENE_ACT5_TB_CH5_FINALE,
        SCENE_ACT6_CH1_BORDER.id to SCENE_ACT6_CH1_BORDER,
        SCENE_ACT6_CH2_VENN.id to SCENE_ACT6_CH2_VENN,
        SCENE_ACT6_CH3_BUS.id to SCENE_ACT6_CH3_BUS,
        SCENE_ACT6_CH4_COCONUT.id to SCENE_ACT6_CH4_COCONUT,
        SCENE_ACT6_CH5_UNBURDENED.id to SCENE_ACT6_CH5_UNBURDENED,
        SCENE_ACT6_WASTELAND.id to SCENE_ACT6_CH1_BORDER,
        SCENE_ACT7_CH1_GAUNTLET.id to SCENE_ACT7_CH1_GAUNTLET,
        SCENE_ACT7_CH2_GAG.id to SCENE_ACT7_CH2_GAG,
        SCENE_ACT7_CH3_BUTLER.id to SCENE_ACT7_CH3_BUTLER,
        SCENE_ACT7_CH4_STEEL_EAR.id to SCENE_ACT7_CH4_STEEL_EAR,
        SCENE_ACT7_CH5_MILWAUKEE.id to SCENE_ACT7_CH5_MILWAUKEE,
        SCENE_ACT7_SANDS.id to SCENE_ACT7_CH1_GAUNTLET,
        SCENE_ACT8_CH1_BUNKER.id to SCENE_ACT8_CH1_BUNKER,
        SCENE_ACT8_CH2_TELEPROMPTER.id to SCENE_ACT8_CH2_TELEPROMPTER,
        SCENE_ACT8_CH3_AUTOPEN.id to SCENE_ACT8_CH3_AUTOPEN,
        SCENE_ACT8_CH4_CITADEL.id to SCENE_ACT8_CH4_CITADEL,
        SCENE_ACT8_CH5_INAUGURATION.id to SCENE_ACT8_CH5_INAUGURATION,
        SCENE_ACT8_CITADEL_FINAL.id to SCENE_ACT8_CH1_BUNKER
    )

    // -------------------------------------------------------------------------
    // EXPANDED ACT I DUAL-CAMPAIGN DIALOGUE GRAPH (4 Deep Chapters Per Track)
    // Core Satire Rule: Neither side EVER lets a talking point go. Every time
    // someone is cornered on ANY topic, they drag back a 5-, 10-, or 37-year-old
    // scandal and beat it into the ground.
    // -------------------------------------------------------------------------
    val ALL_NODES: Map<String, DialogueNode> = listOf(

        // =========================================================================
        // ACT I PROLOGUE & TRACK A - CHAPTER 1: THE $50 ESCALATOR & THE LION KING ROAST
        // =========================================================================
        DialogueNode(
            id = "act1_ch1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "June 16, 2015. Trump Tower lobby! The cameras are flashing, the red ties are flapping, and Commander America is descending the brass escalator to launch the most outrageous presidential campaign in American history! Step up to the mic, Commander!",
            choices = listOf(
                DialogueChoice("a1_speech", "Drop the speech notes & fire from the hip!", listOf("speech", "announce", "president", "run", "america"), "act1_ch1_speech"),
                DialogueChoice("a1_swagger", "Flex the escalator swagger & magnificent hair!", listOf("swagger", "hair", "suit", "aura", "actors", "fifty"), "act1_ch1_swagger"),
                DialogueChoice("a1_media", "Roast the fake news camera crews in the lobby!", listOf("media", "camera", "fake news", "press", "dishonest"), "act1_ch1_media")
            )
        ),
        // Legacy alias for Act I intro
        DialogueNode(
            id = "act1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "June 16, 2015. Trump Tower lobby! The cameras are flashing, the red ties are flapping, and Commander America is descending the brass escalator to launch the most outrageous presidential campaign in American history! Step up to the mic, Commander!",
            choices = listOf(
                DialogueChoice("a1_speech", "Drop the speech notes & fire from the hip!", listOf("speech", "announce", "president", "run", "america"), "act1_ch1_speech"),
                DialogueChoice("a1_swagger", "Flex the escalator swagger & magnificent hair!", listOf("swagger", "hair", "suit", "aura", "actors", "fifty"), "act1_ch1_swagger"),
                DialogueChoice("a1_media", "Roast the fake news camera crews in the lobby!", listOf("media", "camera", "fake news", "press", "dishonest"), "act1_ch1_media")
            )
        ),

        DialogueNode(
            id = "act1_ch1_swagger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at this escalator, folks! Smooth as silk, 24-karat gold! The fake news is already crying that I paid people fifty bucks to cheer in the lobby. Fake news! I paid them seventy-five, plus a taco bowl voucher! Greatest deal in New York real estate history!",
            nextNodeId = "act1_ch1_hulk_cheer"
        ),
        DialogueNode(
            id = "act1_ch1_hulk_cheer",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH, BROTHER! The brass escalator is gliding down with 100% pure American swagger, jack! When Commander America steps off that moving staircase, what'cha gonna do?!",
            nextNodeId = "act1_ch1_psaki_interrupts"
        ),
        DialogueNode(
            id = "act1_ch1_psaki_interrupts",
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Excuse me—Jen Circle-Back here! We're circling back to those paid escalator actors in 2016, 2020, and 2024! We have an entire spreadsheet of people in neon t-shirts, and we will never let this go!",
            nextNodeId = "act1_ch1_karoline_note"
        ),
        DialogueNode(
            id = "act1_ch1_karoline_note",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Oh yeah, Jen?! While you're counting t-shirts, Hill-Rod is in her basement pouring bleach on a hard drive! Let's get to the podium!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_media",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at the cameras back there! Every single network is broadcasting this live because without me, their ratings are deader than a dial-up modem!",
            nextNodeId = "act1_ch1_karoline_note"
        ),
        DialogueNode(
            id = "act1_ch1_speech",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Our country is in serious trouble! We don't win anymore! Barry Teleprompter spent eight years giving speeches and eating fancy Dijon mustard while our factories closed down! We are going to build the wall, bring back the jobs, and make America win so much you'll get tired of winning!",
            nextNodeId = "act1_ch1_speech_followup"
        ),
        DialogueNode(
            id = "act1_ch1_speech_followup",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "Excuse me, Mr. Trump! I'm Chyron McSpin from Cable Outrage Tonight! The pundits say a billionaire reality star with golden hair can't just ride an escalator into the Oval Office!",
            nextNodeId = "act1_ch1_hub"
        ),

        // ACT I - CHAPTER 1 HUB: 3 Vindication Wins + Eternal Callbacks
        DialogueNode(
            id = "act1_ch1_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Commander America grips the golden podium. Chyron McSpin is ready with three hostile talking points. Dismantle the media spin before clearing the lobby!",
            choices = listOf(
                DialogueChoice("a1_hub_deal", "Roast the fake news gossip & remind them Oprah begged you to run!", listOf("deal", "trade", "jobs", "economy", "tariffs", "people magazine", "dumb", "hoax"), "act1_ch1_lore_deal", completionFlag = "a1_lore_deal"),
                DialogueChoice("a1_hub_border", "Lay down the law: The Great Border Wall & zero apologies!", listOf("wall", "border", "security", "door", "southern", "tax", "kid", "bus"), "act1_ch1_lore_wall", completionFlag = "a1_lore_wall"),
                DialogueChoice("a1_hub_swamp", "Expose the D.C. Swamp: Why Hill-Rod came to your wedding for the cake!", listOf("swamp", "drain", "lobbyist", "corrupt", "bureaucrat", "wedding", "cake"), "act1_ch1_lore_swamp", completionFlag = "a1_lore_swamp"),
                DialogueChoice("a1_hub_proceed", "Clear the lobby and confront Chyron McSpin's camera crew!", listOf("proceed", "lobby", "advance", "step", "floor"), "act1_ch1_lobby_ambush")
            )
        ),
        DialogueNode(
            id = "act1_ch1_lore_deal",
            setFlagOnEnter = "a1_lore_deal",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "You want to talk about gossip, Chyron?! Back in 1988, Oprah Winfrey sat me on her couch and begged me on live television to run for president! She said: 'Donald, you'd win!' And she was right! Isn't that right, Oprah?!",
            nextNodeId = "act1_ch1_lore_deal_oprah"
        ),
        DialogueNode(
            id = "act1_ch1_lore_deal_oprah",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "*Sweats under 1988 studio lights* OKAY, YES, HONEY, IT'S TRUE! I did ask him in 1988! BUT WHAT ABOUT PAYING THOSE ACTORS FIFTY BUCKS TO CHEER FOR YOUR GOLDEN HAIR, DONALD?!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_lore_wall",
            setFlagOnEnter = "a1_lore_wall",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "We are going to build a Great Wall across the southern border! A beautiful, tremendous wall made of steel and concrete, with a big, beautiful golden door right in the middle! And nobody builds walls better than Trump, believe me!",
            nextNodeId = "act1_ch1_lore_wall_callback"
        ),
        DialogueNode(
            id = "act1_ch1_lore_wall_callback",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "A wall?! With a gold door?! Our fact-checkers are having an aneurysm on live television! And we still haven't let go of the fifty-dollar escalator actors!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_lore_swamp",
            setFlagOnEnter = "a1_lore_swamp",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "You want to talk about corrupt politicians?! When I invited Hill-Rod to my wedding in Palm Beach, she showed up in the front row, ate two giant slices of my gold-foil wedding cake, and said: 'Donald, you throw the greatest parties in the world!' Now she pretends she doesn't know me! Total swamp behavior!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_lobby_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "Stop talking about wedding cake! Our K-Street lobbyists and cable news anchors are seizing the golden escalator right now!",
            nextNodeId = "act1_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act1_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Chyron McSpin's producer and a K-Street Retainer Lobbyist are swarming the brass handrail! Commander, hit 'em with Tremendous Strike and Art of the Deal!",
            triggerBattleEncounterId = "act1_ch1_press_battle",
            nextNodeId = "act1_ch1_post_battle"
        ),
        // Legacy aliases for Act 1 Ch 1 battle
        DialogueNode(
            id = "act1_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "Enough fact-checking! Our K-Street Retainer Lobbyists are seizing this golden escalator right now!",
            nextNodeId = "act1_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Chyron McSpin's producer and a K-Street Retainer Lobbyist are swarming the brass handrail! Commander, hit 'em with Tremendous Strike!",
            triggerBattleEncounterId = "act1_ch1_press_battle",
            nextNodeId = "act1_ch1_post_battle"
        ),
        DialogueNode(
            id = "act1_ch1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "They're fleeing down Fifth Avenue still shouting about the \$50 casting extras! Good thing we have zero XP grinding and zero gold clutter in this game—our HP and MP are 100% restored automatically! Onward to Chapter 2: The Cleveland Debate Stage!",
            changeSceneId = SCENE_ACT1_CH2_DEBATE.id,
            nextNodeId = "act1_ch2_intro"
        ),
        DialogueNode(
            id = "act1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "HP and MP 100% restored—onward to Chapter 2: The Cleveland Debate Stage!",
            changeSceneId = SCENE_ACT1_CH2_DEBATE.id,
            nextNodeId = "act1_ch2_intro"
        ),

        // =========================================================================
        // ACT I (TRACK A) - CHAPTER 2: GUACAMOLE BOWLS, TAN SUITS & DIJON MUSTARD
        // =========================================================================
        DialogueNode(
            id = "act1_ch2_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Inside the primary debate arena, Low-Energy Jeb! and Little Marco are rehearsing their focus-grouped soundbites while Barry Teleprompter watches from the VIP box wearing his infamous August 28, 2014 summer tan suit and sipping a 2014 Marine One styrofoam latte!",
            nextNodeId = "act1_ch2_jeb_opens"
        ),
        DialogueNode(
            id = "act1_ch2_jeb_opens",
            speaker = DialogueSpeaker.ESTABLISHMENT_RIVAL,
            side = SpeakerSide.RIGHT,
            text = "Now look, Donald... you can't insult your way to the presidency—especially after paying escalator actors \$50 a head! My Right to Rise Super PAC spent \$130 million, I put an exclamation point right after 'Jeb!', and my website sells a very sturdy \$75 'Guaca Bowle' dish. In Hanover, New Hampshire on February 3, 2016, I finished my speech and nobody made a sound, so I politely said: 'Please clap.'",
            choices = listOf(
                DialogueChoice("a2_guac", "Refuse to let go of 'Please Clap' & the \$75 Guacamole Bowl", listOf("please clap", "jeb", "low energy", "guacamole", "pac"), "act1_ch2_roast_jeb"),
                DialogueChoice("a2_marco_water", "Interrupt Little Marco's robotic 4x loop with a miniature Poland Spring bottle", listOf("marco", "water", "bottle", "thirsty", "sip"), "act1_ch2_roast_marco"),
                DialogueChoice("a2_barry_tan", "Point at Barry Teleprompter's 2014 Tan Suit, Styrofoam Latte Salute & 2009 Dijon Mustard", listOf("barry", "obama", "tan suit", "mustard", "dijon", "latte"), "act1_ch2_barry_enters")
            )
        ),
        DialogueNode(
            id = "act1_ch2_roast_jeb",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Folks, we are going to replay 'Please clap' and that \$75 Guaca Bowle for the next ten years! You spent \$130 million and got three delegates, Jeb—meanwhile Hill-Rod put \$1,000 into Arkansas live-cattle futures in 1978 and walked out with \$100,000 by 'reading the Wall Street Journal'! Maybe she should have managed your Super PAC!",
            nextNodeId = "act1_ch2_roast_marco"
        ),
        DialogueNode(
            id = "act1_ch2_roast_marco",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.RIGHT,
            text = "Donald, let's dispel once and for all with this fiction that Barack Obama doesn't know what he's doing—he knows EXACTLY what he's doing! Wait, Chris Christie just called me out for repeating my memorized 25-second speech—so let's dispel once and for all with this fiction that Barack Obama doesn't know what he's doing! *Lunges four feet sideways off-camera to take a frantic 2013 State-of-the-Union sip from a miniature Poland Spring water bottle*",
            nextNodeId = "act1_ch2_barry_enters"
        ),
        DialogueNode(
            id = "act1_ch2_barry_enters",
            speaker = DialogueSpeaker.OBAMA,
            side = SpeakerSide.RIGHT,
            text = "Now... let me be clear. *Uhhh...* you guys are STILL talking about my August 2014 tan suit, my September 2014 styrofoam cup Latte Salute stepping off Marine One, and the May 2009 afternoon at Ray's Hell Burger in Arlington when I asked: 'Do you have a spicy mustard, maybe a Dijon?' That was seven years ago, Donald! You still haven't gotten over the 2011 Correspondents' Dinner where I released my long-form Kapiʻolani Hospital birth certificate AND the 1961 Honolulu Advertiser newspaper clipping!",
            nextNodeId = "act1_ch2_barry_kimmel"
        ),
        DialogueNode(
            id = "act1_ch2_barry_kimmel",
            speaker = DialogueSpeaker.OBAMA,
            side = SpeakerSide.RIGHT,
            text = "And speaking of never letting things go—on Jimmy Kimmel Live in October 2016, I read your tweet aloud saying 'President Obama will go down as perhaps the worst president in the history of the United States!' And I looked straight into the camera and said: 'Well, @realDonaldTrump, at least I will go down as a president!' *Drops microphone onto a plate of Grey Poupon*",
            nextNodeId = "act1_ch2_commander_tan_rebuttal"
        ),
        DialogueNode(
            id = "act1_ch2_commander_tan_rebuttal",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Hold on, Barry—nobody is EVER letting that tan suit or that Dijon burger go! Congressman Peter King went on CNN for forty-eight hours straight calling that beige linen jacket an impeachable fashion crisis! And what about the 2009 MLB All-Star Game in St. Louis where you wore high-waisted mom jeans and lobbed the ceremonial first pitch 58 feet into the dirt?! Or when your teleprompter unplugged in Elkhart and you went 'if-if-if-if-if-if—okie-doke!'",
            nextNodeId = "act1_ch2_mumbai_vindication"
        ),
        DialogueNode(
            id = "act1_ch2_mumbai_vindication",
            speaker = DialogueSpeaker.OBAMA,
            side = SpeakerSide.RIGHT,
            text = "Look, folks on the internet claimed I spent two hundred million dollars a day visiting India with thirty-four aircraft carriers! Thirty-four warships?! I was giving a speech, not invading the subcontinent! You can tease the tan suit, but leave the fictional naval armada out of it!",
            nextNodeId = "act1_ch2_barry_battle_trigger"
        ),
        DialogueNode(
            id = "act1_ch2_barry_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE TAN SUIT, LATTE SALUTE & DIJON POUPON EXHIBITION MATCH, BROTHER! Barry Teleprompter and his Poupon Mustard Sommelier are stepping into the ring! Watch out for his 135-yard mom-jeans lob!",
            triggerBattleEncounterId = "act1_track_a_barry_battle",
            nextNodeId = "act1_ch2_post_battle"
        ),
        DialogueNode(
            id = "act1_ch2_post_battle",
            speaker = DialogueSpeaker.OBAMA,
            side = SpeakerSide.RIGHT,
            text = "Look... *uhhh...* make no mistake: that tan suit still breathes fantastically in August humidity, and Dijon is objectively superior to yellow ketchup-water. Oh, and by the way, Donald—did you pay those escalator actors \$50 a head before or after you popped a box of Tic-Tacs on the 2005 Access Hollywood bus?! See you in Chapter 3!",
            changeSceneId = SCENE_ACT1_CH3_TACO_BUS.id,
            nextNodeId = "act1_ch3_intro"
        ),

        // =========================================================================
        // ACT I (TRACK A) - CHAPTER 3: TACO BOWLS & THE 2005 TIC-TAC BUS THAT NEVER DIES
        // =========================================================================
        DialogueNode(
            id = "act1_ch3_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to Chapter 3 of Act I! It is May 5, 2016 inside Trump Tower Grill. Commander America is sitting at his desk over a stack of old newspapers—including a 1990s bikini photo of his second wife Marla Maples—giving a big thumbs-up over a deep-fried fiesta tortilla shell!",
            nextNodeId = "act1_ch3_taco_bowl_tweet"
        ),
        DialogueNode(
            id = "act1_ch3_taco_bowl_tweet",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Dictates tweet word for word on May 5, 2016* 'Happy #CincoDeMayo! The best taco bowls are made in Trump Tower Grill. I love Hispanics!' Look at that shell, folks—100% authentic Midtown Manhattan crunch! Surely nobody in the media will bring up anything from 2005 while I'm enjoying this taco bowl!",
            nextNodeId = "act1_ch3_billy_bus_crashes"
        ),
        DialogueNode(
            id = "act1_ch3_billy_bus_crashes",
            speaker = DialogueSpeaker.BILLY_BUS,
            side = SpeakerSide.RIGHT,
            text = "*Kicks open the restaurant doors wearing a live 2005 Access Hollywood lapel microphone* SURPRISE, DONALD! It's October 7, 2016, and somebody just leaked our September 2005 bus tape from the Days of Our Lives set! And guess what? Politics is going to replay this exact 45-second clip in 2016, 2017, 2020, 2023, and 2026!",
            nextNodeId = "act1_ch3_billy_verbatim"
        ),
        DialogueNode(
            id = "act1_ch3_billy_verbatim",
            speaker = DialogueSpeaker.BILLY_BUS,
            side = SpeakerSide.RIGHT,
            text = "*Hits play on the giant loudspeaker* 'I moved on her actually. You know, she was down on Palm Beach. I moved on her, and I failed. I'll admit it. I did try and f*** her. She was married... I took her out furniture—I moved on her like a b*tch, actually. She wanted to get some furniture. I said, \"I'll show you where they have some nice furniture.\" ... I better use some Tic-Tacs just in case I start kissing her. You know I'm automatically attracted to beautiful—I just start kissing them. It's like a magnet. Just kiss. I don't even wait. And when you're a star, they let you do it. You can do anything... Grab 'em by the p*ssy. You can do anything.'",
            choices = listOf(
                DialogueChoice("a3_tictac", "Defend the Tic-Tacs & the failed Raymour & Flanigan furniture-shopping trip", listOf("tic tac", "furniture", "bus", "locker room", "mints"), "act1_ch3_tictac_defense"),
                DialogueChoice("a3_whatabout", "Execute the Classic Political Whatabout: Pivot straight to Hill-Rod's BleachBit Cloth & 1978 Cattle Futures!", listOf("whatabout", "cloth", "bleachbit", "cattle", "server", "hillary"), "act1_ch3_whatabout_pivot")
            )
        ),
        DialogueNode(
            id = "act1_ch3_tictac_defense",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "First of all, folks, that was eleven years ago in 2005—the same year Hill-Rod was sitting in the front row at my Palm Beach wedding eating my cake! Second of all, listen to the tape: I said I tried to take Nancy O'Dell furniture shopping and I FAILED! What kind of supervillain brags on a hot mic about striking out at an ottoman warehouse after popping two white Tic-Tacs?!",
            nextNodeId = "act1_ch3_never_let_it_go"
        ),
        DialogueNode(
            id = "act1_ch3_whatabout_pivot",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "You want to replay a 2005 bus tape?! Fine! Then every single time you say 'Tic-Tac' or 'grab 'em by the p*ssy,' I am going to shout about Hill-Rod wiping 33,000 emails in Chappaqua 'with a cloth or something,' Paul Combetta asking Reddit how to BleachBit a VIP server, and turning \$1,000 into \$100,000 in 1978 Arkansas cattle futures!",
            nextNodeId = "act1_ch3_never_let_it_go"
        ),
        DialogueNode(
            id = "act1_ch3_never_let_it_go",
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "*Pops up on the studio monitor* Hi, Jen Circle-Back again! Just confirming that even if you ask us about highway potholes, interest rates, or the weather in Ohio for the next decade, we are going to circle right back to 'grab 'em by the p*ssy,' the \$50 escalator actors, and the Trump Tower Taco Bowl over Marla Maples' bikini photo!",
            nextNodeId = "act1_ch3_bus_battle_trigger"
        ),
        DialogueNode(
            id = "act1_ch3_bus_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Super PAC Media Strategists and Consultant Fact-Checkers are trying to weaponize the 2005 bus tape and the Taco Bowl tweet! Commander, clear the stage!",
            triggerBattleEncounterId = "act1_ch2_debate_battle",
            nextNodeId = "act1_ch3_bus_post_battle"
        ),
        DialogueNode(
            id = "act1_ch3_bus_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "We survived the October Surprise bus ambush—and our HP and MP are right back at 100%! Now let's march into Chapter 4: The Final 2016 General Election Showdown against Hill-Rod!",
            changeSceneId = SCENE_ACT1_CH3_RALLY.id,
            nextNodeId = "act1_ch4_intro"
        ),

        // =========================================================================
        // ACT I (TRACK A) - CHAPTER 4: BLEACHBIT, POKÉMON GO & ELECTION NIGHT FINALE
        // =========================================================================
        DialogueNode(
            id = "act1_ch4_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is Election Eve, November 2016! Hill-Rod arrives on the debate stage fresh off taking five swipes to get through a Bronx subway turnstile, pulling Ninja Squirrel hot sauce out of her purse on The Breakfast Club, and telling Virginia voters to 'Pokémon Go-to-the-Polls'!",
            nextNodeId = "act1_ch4_hill_rod_arrives"
        ),
        DialogueNode(
            id = "act1_ch4_hill_rod_arrives",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.RIGHT,
            text = "Good evening, Donald! Before we discuss foreign policy, I'd like to remind everyone for the 400th time today that in 2005 on an Access Hollywood bus you popped a handful of Tic-Tacs, failed to take a married woman furniture shopping, boasted that 'when you're a star they let you do it, grab 'em by the p*ssy,' and then in 2015 you paid Extra Mile Casting actors \$50 a head to clap at your escalator!",
            nextNodeId = "act1_ch4_commander_counter_barrage"
        ),
        DialogueNode(
            id = "act1_ch4_commander_counter_barrage",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Oh yeah, Hill-Rod?! Well before we discuss tax brackets, I'd like to remind everyone for the 500th time today that in 1978 you turned \$1,000 into \$100,000 in live-cattle futures by 'reading the Wall Street Journal,' in 2005 you ate two slices of cake at my wedding, in 2014 your IT guy 'stonetear' asked Reddit how to BleachBit 33,000 emails off a Chappaqua basement server before you asked 'What, like with a cloth or something?!', and in April 2016 it took you FIVE SWIPES to walk through a Bronx subway turnstile while carrying pandering hot sauce and shouting 'Pokémon Go-to-the-Polls!'",
            nextNodeId = "act1_ch4_comey_cameo"
        ),
        DialogueNode(
            id = "act1_ch4_comey_cameo",
            speaker = DialogueSpeaker.COMEY_TALL,
            side = SpeakerSide.RIGHT,
            text = "*Pokes his 6-foot-8 head out from behind a blue stage curtain* Excuse me, I'm Director Jim-Tall! Just wanted to remind everyone that in July I held a 15-minute press conference to call Hill-Rod 'extremely careless,' then on October 28 I dropped a bombshell letter about Anthony Weiner's laptop, and 48 hours ago I announced they were all just duplicates! Now I'm going to try to hide inside this blue curtain again so neither of you notices me!",
            nextNodeId = "act1_ch3_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act1_ch3_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE 2016 GENERAL ELECTION MAIN EVENT, BROTHER! Commander America's Golden Podium, Taco Bowls, and Tic-Tacs vs. Hill-Rod's Chappaqua Cloth Wipe, Purse Hot Sauce, and Pokémon Go-To-The-Polls! RING THE BELL!",
            triggerBattleEncounterId = "act1_track_a_hill_rod_finale",
            nextNodeId = "act1_ch3_post_battle"
        ),
        DialogueNode(
            id = "act1_ch3_post_battle",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "270 Electoral Votes certified! Commander America takes the win! Next stop: Washington, D.C. for four years of executive chaos!",
            nextNodeId = "act2_ch1_intro"
        ),

        // =========================================================================
        // ACT I (TRACK B) - CHAPTER 1: WITH A CLOTH & 1978 CATTLE FUTURES (PLAYING AS HILL-ROD)
        // =========================================================================
        DialogueNode(
            id = "act1_tb_ch1_intro",
            changeSceneId = SCENE_ACT1_TB_CH1_SERVER.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Live from Chappaqua, New York! A beige 1990s server tower in the basement is smoking, spark plugs are flying, and Hill-Rod is standing over the motherboard with a microfiber cloth and a bottle of Lysol!",
            nextNodeId = "act1_tb_ch1_kjp_oprah"
        ),
        DialogueNode(
            id = "act1_tb_ch1_kjp_oprah",
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "According to my binder, thirty-three thousand emails just evaporated into thin air! But don't panic, folks—Oprah is in the driveway with the megaphone!",
            nextNodeId = "act1_tb_ch1_oprah_hype"
        ),
        DialogueNode(
            id = "act1_tb_ch1_oprah_hype",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "YOU GET A LAWYER! YOU GET A MICROFIBER CLOTH! EVERYBODY LOOK UNDER YOUR SEATS FOR A CLAW HAMMER AND A BOTTLE OF BLEACHBIT!",
            nextNodeId = "act1_tb_ch1_spicer_heckles"
        ),
        DialogueNode(
            id = "act1_tb_ch1_spicer_heckles",
            speaker = DialogueSpeaker.SPICY_SEAN,
            side = SpeakerSide.RIGHT,
            text = "*Pops his head through the basement window, chewing 35 pieces of cinnamon gum at once* EXCUSE ME, MADAM SECRETARY! The press corps saw smoke coming out of your dryer vent! Did you or did you not just wash that hard drive in the kitchen sink?!",
            nextNodeId = "act1_tb_ch1_server_hub"
        ),
        DialogueNode(
            id = "act1_tb_ch1_server_hub",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "Will somebody please close that basement window?! I'm trying to delete my yoga schedule before the FBI knocks on the front door! Team, what's our spin?!",
            choices = listOf(
                DialogueChoice("tb1_cloth", "What, like with a cloth or something?!", listOf("cloth", "wipe", "server", "shrug", "stonetear"), "act1_tb_ch1_cloth_joke", completionFlag = "tb1_cloth_done"),
                DialogueChoice("tb1_cattle", "Remind them I made a fortune trading cows in 1978!", listOf("cattle", "futures", "wall street journal", "thousand", "tic tac"), "act1_tb_ch1_cattle_joke", completionFlag = "tb1_cattle_done"),
                DialogueChoice("tb1_vindication", "Smash the Blackberries with a hammer & do the 11-hour shrug!", listOf("vindication", "acid", "bleachbit", "benghazi", "eleven hours"), "act1_tb_ch1_vindication", completionFlag = "tb1_vind_done"),
                DialogueChoice("tb1_proceed", "Grab the pantsuits—we're heading to the Bronx subway!", listOf("proceed", "advance", "bronx", "ferns", "zach"), "act1_tb_ch2_intro")
            )
        ),
        DialogueNode(
            id = "act1_tb_ch1_cloth_joke",
            setFlagOnEnter = "tb1_cloth_done",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "I looked the reporter dead in the eye, smiled, and said: 'What, like with a cloth or something?!' I don't know how computers work! I thought you just sprayed Windex on the monitor and the emails went to heaven!",
            nextNodeId = "act1_tb_ch1_cloth_callback"
        ),
        DialogueNode(
            id = "act1_tb_ch1_cloth_callback",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Just so you know, Hill-Rod, the internet is going to replay 'with a cloth or something' until the heat death of the universe!",
            nextNodeId = "act1_tb_ch1_server_hub"
        ),
        DialogueNode(
            id = "act1_tb_ch1_cattle_joke",
            setFlagOnEnter = "tb1_cattle_done",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "Spicy Sean is screaming about 1978 cattle futures! Look, I turned a thousand bucks into a hundred grand trading cows! That wasn't luck—that was pure cow instinct! If Donald can ride a golden escalator, I can ride a golden steer!",
            nextNodeId = "act1_tb_ch1_server_hub"
        ),
        DialogueNode(
            id = "act1_tb_ch1_vindication",
            setFlagOnEnter = "tb1_vind_done",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "Donald claims I used 'expensive acid' on my hard drives! Honey, it's called BleachBit, and my tech guy downloaded it off Reddit at 3 AM! I testified before Congress for eleven straight hours without a single bathroom break, hit the shoulder shrug, and walked out in my pastel pantsuit. Untouchable!",
            nextNodeId = "act1_tb_ch1_server_hub"
        ),

        // =========================================================================
        // ACT I (TRACK B) - CHAPTER 2: 5 METROCARD SWIPES, CEDAR RAPIDS & TWO FERNS
        // =========================================================================
        DialogueNode(
            id = "act1_tb_ch2_intro",
            changeSceneId = SCENE_ACT1_TB_CH2_FERNS.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "To prove to voters that Hill-Rod hasn't been riding purely in armored Secret Service Suburbans since 1992, her campaign has scheduled three 'Everyday Normal Person' moments: a Snapchat video in Iowa, a subway ride at the 170th Street station in the Bronx, and an interview with Zach Two-Ferns!",
            nextNodeId = "act1_tb_ch2_cedar_rapids"
        ),
        DialogueNode(
            id = "act1_tb_ch2_cedar_rapids",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "*Holds up a smartphone in Iowa on August 10, 2015* 'I'm just chilling... in Cedar Rapids!' Nailed it! Thirty million views on Vine! Now let's step up to this New York City subway turnstile in the Bronx on April 7, 2016 and swipe this paper MetroCard in front of forty television cameras! *SWIPE ONE: 'PLEASE SWIPE AGAIN.' SWIPE TWO: 'SWIPE AGAIN AT THIS TURNSTILE.' SWIPE THREE: CLUNK. SWIPE FOUR: BEEP-ERROR.*",
            nextNodeId = "act1_tb_ch2_turnstile_heckle"
        ),
        DialogueNode(
            id = "act1_tb_ch2_turnstile_heckle",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "*Shouts from the subway platform* HEY MADAM SECRETARY! That's FIVE SWIPES to get through one Bronx subway turnstile! Did you accidentally wipe the magnetic strip on your MetroCard 'with a cloth or something' like your 33,000 Chappaqua emails?!",
            nextNodeId = "act1_tb_ch2_hill_turnstile_clapback"
        ),
        DialogueNode(
            id = "act1_tb_ch2_hill_turnstile_clapback",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "*Finally pushes through on the fifth swipe* Listen here, Chyron—at least I didn't ride the subway using brass tokens like Donald claimed, and at least I didn't ride an Access Hollywood bus in 2005 eating Tic-Tacs and yelling 'grab 'em by the p*ssy' after failing to buy a couch with Nancy O'Dell! Now get out of my way, I'm late for Zach Two-Ferns!",
            nextNodeId = "act1_tb_ch2_zach_interrogates"
        ),
        DialogueNode(
            id = "act1_tb_ch2_zach_interrogates",
            speaker = DialogueSpeaker.ZACH_FERNS,
            side = SpeakerSide.RIGHT,
            text = "*Sits deadpan between two dusty potted ferns on September 22, 2016* Secretary Hill-Rod, I'm so excited to meet the first female president... and for a younger generation, you'll also become their first white president. Also, when Commander America is elected, what do you think you'll wear to the inauguration? Hold that thought—let's take a quick break for our paid sponsor: a campaign commercial for Donald Trump!",
            nextNodeId = "act1_tb_ch2_hill_reacts_ferns"
        ),
        DialogueNode(
            id = "act1_tb_ch2_hill_reacts_ferns",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "Did you seriously just cut away in the middle of my interview to play a literal Donald Trump campaign commercial?! And now as we wrap up, you're asking me: 'This has been really great, we should stay in touch—what's the best way to reach you? Email?' EVEN THE COMEDY HOST WON'T LET THE EMAIL SERVER GO!",
            nextNodeId = "act1_tb_ch2_ferns_battle_trigger"
        ),
        DialogueNode(
            id = "act1_tb_ch2_ferns_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "ZACH TWO-FERNS JUST WEAPONIZED THE PUBLIC-ACCESS STUDIO PLANTS AND PLAYED ANOTHER TRUMP AD, HONEY! Hill-Rod, unleash Pokémon Go-To-The-Polls and your Chappaqua Cloth Wipe!",
            triggerBattleEncounterId = "act1_track_b_ferns_battle",
            nextNodeId = "act1_tb_ch2_post_battle"
        ),
        DialogueNode(
            id = "act1_tb_ch2_post_battle",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "That's the last time I sit between two ferns without checking the ad-buy sheet first! With HP and MP 100% restored, let's head to Chapter 3: The Breakfast Club radio studio and our Virginia Pokémon Go rally!",
            changeSceneId = SCENE_ACT1_TB_CH3_BREAKFAST.id,
            nextNodeId = "act1_tb_ch3_intro"
        ),

        // =========================================================================
        // ACT I (TRACK B) - CHAPTER 3: PURSE HOT SAUCE, POKÉMON GO & NO BASEMENT
        // =========================================================================
        DialogueNode(
            id = "act1_tb_ch3_intro",
            changeSceneId = SCENE_ACT1_TB_CH3_BREAKFAST.id,
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is April 18, 2016 inside The Breakfast Club radio booth in New York! DJ Envy and Charlamagne tha God ask Hill-Rod what one item she always carries in her handbag—right after Beyoncé dropped the lyric 'I got hot sauce in my bag, swag' in Formation!",
            nextNodeId = "act1_tb_ch3_breakfast_club"
        ),
        DialogueNode(
            id = "act1_tb_ch3_breakfast_club",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "The Breakfast Club host just looked me dead in the eye and asked why I keep hot sauce in my purse. Should I give him the focus-grouped answer, or dismantle the wildest rumors on the internet?!",
            choices = listOf(
                DialogueChoice("tb2_working", "Hot sauce in my purse & 'Pokémon Go-to-the-Polls!'", listOf("is it working", "okay", "hot sauce", "pandering", "pokemon"), "act1_tb_ch3_pokemon_rally", completionFlag = "tb3_hot_done"),
                DialogueChoice("tb2_pizzeria", "Debunk the pizza parlor secret basement rumor!", listOf("pizza", "basement", "comet", "vindication", "blueprint"), "act1_tb_ch3_pizzeria_vindication", completionFlag = "tb3_pizza_done"),
                DialogueChoice("tb3_uranium", "Laugh off the wild uranium smuggling conspiracy!", listOf("uranium", "russia", "cfius", "bribe"), "act1_tb_ch3_uranium_vindication", completionFlag = "tb3_uranium_done"),
                DialogueChoice("tb3_proceed", "Step outside where Spicy Sean & Director Jim-Tall are waiting!", listOf("proceed", "advance", "spicer", "comey", "bushes"), "act1_tb_ch3_spicer_comey_ambush")
            )
        ),
        DialogueNode(
            id = "act1_tb_ch3_pizzeria_vindication",
            setFlagOnEnter = "tb3_pizza_done",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "They claimed my campaign was running a secret cabal in the basement of a D.C. pizza parlor! Folks, that pizza parlor is built on flat concrete! It doesn't even have a crawlspace! I can't even get a pizza delivered to Chappaqua without a motorcade, and they think I have a pizza dungeon?!",
            nextNodeId = "act1_tb_ch3_breakfast_club"
        ),
        DialogueNode(
            id = "act1_tb_ch3_uranium_vindication",
            setFlagOnEnter = "tb3_uranium_done",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "They said I personally sold twenty percent of America's uranium to Russia in a handbag! I can barely figure out how to attach a PDF to an email, and you think I'm running an international nuclear smuggling ring?! Get real!",
            nextNodeId = "act1_tb_ch3_breakfast_club"
        ),
        DialogueNode(
            id = "act1_tb_ch3_pokemon_rally",
            setFlagOnEnter = "tb3_hot_done",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "Charlamagne looked at me and said: 'People are gonna think you're pandering.' And I leaned in and smiled: 'Okay... is it working?!' Then I flew straight to Virginia and told a room of bored teenagers: 'I don't know who made Pokémon Go, but I want them to... Pokémon Go-to-the-Polls!' Absolute comedy gold!",
            nextNodeId = "act1_tb_ch3_breakfast_club"
        ),
        DialogueNode(
            id = "act1_tb_ch3_spicer_comey_ambush",
            speaker = DialogueSpeaker.SPICY_SEAN,
            side = SpeakerSide.RIGHT,
            text = "*Leaps out of a row of ornamental hedges chewing 35 pieces of Orbit Cinnamon gum at once* HOLD IT RIGHT THERE, HILL-ROD! I'm Spicy Sean—I swallow two and a half packs of cinnamon gum whole before noon, and I'm still not letting go of your Chappaqua cloth wipe, your 5-swipe Bronx MetroCard, OR your 1978 Arkansas cattle futures! Oh, and look who's hiding behind that blue curtain!",
            nextNodeId = "act1_tb_ch3_comey_steps_out"
        ),
        DialogueNode(
            id = "act1_tb_ch3_comey_steps_out",
            speaker = DialogueSpeaker.COMEY_TALL,
            side = SpeakerSide.RIGHT,
            text = "*Slowly detaches his 6-foot-8 frame from a blue drape where he was trying to blend into the fabric* Good afternoon. In July 2016 I violated DOJ protocol to hold a 15-minute press conference calling you 'extremely careless' while recommending zero charges. Now it is October 28—eleven days before the election—and I am sending a vague letter to Congress announcing we found emails on Anthony Weiner's laptop! Have fun explaining that while Donald brings up your cloth wipe for the 900th time!",
            nextNodeId = "act1_tb_ch2_spicer_battle_trigger"
        ),
        DialogueNode(
            id = "act1_tb_ch2_spicer_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Spicy Sean's Cinnamon Gum Barrage and Director Jim-Tall's October Surprise Letter are blocking the path to Election Night! Hill-Rod, hit them with Purse Ninja Hot Sauce and \$1,000 Cattle Futures Stampede!",
            triggerBattleEncounterId = "act1_track_b_spicy_sean_battle",
            nextNodeId = "act1_tb_ch4_intro"
        ),

        // =========================================================================
        // ACT I (TRACK B) - CHAPTER 4: THE BLUE-CURTAIN DIRECTOR & ELECTION NIGHT FINALE
        // =========================================================================
        DialogueNode(
            id = "act1_tb_ch4_intro",
            changeSceneId = SCENE_ACT1_TB_CH4_JAVITS.id,
            speaker = DialogueSpeaker.COMEY_TALL,
            side = SpeakerSide.RIGHT,
            text = "*Pokes his 6-foot-8 head back out from behind the blue curtain on November 6, 2016—48 hours before Election Day* Oh, hi again, Secretary Hill-Rod! Funny story: our automated hashing software finished scanning Anthony Weiner's laptop in forty-eight hours and every single email was just a duplicate of the ones we already cleared back in July! My bad! Good luck in Wisconsin—which you haven't visited once since the April primary!",
            nextNodeId = "act1_tb_ch4_commander_confrontation"
        ),
        // Legacy alias for Track B Ch 3 intro
        DialogueNode(
            id = "act1_tb_ch3_intro",
            changeSceneId = SCENE_ACT1_TB_CH4_JAVITS.id,
            speaker = DialogueSpeaker.COMEY_TALL,
            side = SpeakerSide.RIGHT,
            text = "*Pokes his 6-foot-8 head out from behind the blue curtain on November 6, 2016* Our software finished scanning the laptop in 48 hours and every single email was just a duplicate! My bad!",
            nextNodeId = "act1_tb_ch4_commander_confrontation"
        ),
        DialogueNode(
            id = "act1_tb_ch4_commander_confrontation",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.RIGHT,
            text = "Look who finally swiped her MetroCard five times to get onto the Election Night stage, folks! Tell me, Hill-Rod—did you wipe that glass ceiling at the Javits Center 'with a cloth or something' like Paul Combetta ('stonetear') wiped your 33,000 Chappaqua emails with BleachBit?! Or are you still reading the 1978 Wall Street Journal between two potted ferns while yelling 'Pokémon Go-to-the-Polls'?!",
            choices = listOf(
                DialogueChoice("tb3_roast_bus", "Unload the Eternal Callback Barrage: 2005 Tic-Tac 'Grab 'Em' Bus + Failed Furniture Trip + \$50 Escalator Actors!", listOf("tic tac", "furniture", "escalator", "fifty", "bus", "grab"), "act1_tb_ch4_pre_finale"),
                DialogueChoice("tb3_roast_wedding", "Unload the Taco Bowl & Wedding Barrage: Marla Maples Newspaper Taco Bowl + 2005 Palm Beach Wedding Cake!", listOf("wedding", "taco bowl", "senate", "palm beach", "marla"), "act1_tb_ch4_pre_finale")
            )
        ),
        DialogueNode(
            id = "act1_tb_ch4_pre_finale",
            speaker = DialogueSpeaker.HILL_ROD,
            side = SpeakerSide.LEFT,
            text = "You want to talk about 1978 cattle futures and my Chappaqua cloth wipe, Donald?! Let's talk about you eating a fiesta taco bowl over a newspaper photo of your ex-wife in a bikini on Cinco de Mayo, paying Extra Mile Casting \$50 a head to clap at your escalator, inviting me to eat cake in the front row of your 2005 Palm Beach wedding, and riding around on a 2005 Access Hollywood bus popping Tic-Tacs and bragging 'grab 'em by the p*ssy' right after Nancy O'Dell turned down your trip to a strip-mall furniture store! Let's finish Act I right now!",
            nextNodeId = "act1_tb_ch3_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act1_tb_ch3_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE 2016 ELECTION NIGHT SHOWDOWN, EVERYBODY! Hill-Rod vs. Commander America, Billy Access-Bus, and the \$50 Casting Extra! Unleash Pokémon Go-To-The-Polls, Purse Ninja Hot Sauce, and the 11-Hour Marathon Shield!",
            triggerBattleEncounterId = "act1_track_b_commander_finale",
            nextNodeId = "act1_tb_ch3_post_battle"
        ),
        DialogueNode(
            id = "act1_tb_ch3_post_battle",
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "And that wraps up the 2016 race! The calendar flips to 2017, and in Washington, Speaker Nancy Palooza takes the spotlight!",
            nextNodeId = "act2_tb_ch1_intro"
        ),

        // =========================================================================
        // ACT II (TRACK A) - CHAPTER 1: BUSHES, CINNAMON GUM & THE 11-DAY MOOCH (JAN–JULY 2017)
        // =========================================================================
        DialogueNode(
            id = "act2_ch1_intro",
            changeSceneId = SCENE_ACT2_CH1_MALL.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to Act II (2017–2020): Five brand-new chapters of executive chaos! It is January 21, 2017 inside the West Wing. White House Press Secretary Spicy Sean has already chewed and swallowed 35 pieces of Orbit Cinnamon gum whole before noon—right after sneaking into the Eisenhower Executive Office Building at 8:00 PM to steal a mini-fridge from junior research staffers!",
            nextNodeId = "act2_ch1_spicer_podium"
        ),
        DialogueNode(
            id = "act2_ch1_spicer_podium",
            speaker = DialogueSpeaker.SPICY_SEAN,
            side = SpeakerSide.LEFT,
            text = "*Stands at the Brady Briefing Room lectern in a suit jacket two sizes too large, swallowing a wad of cinnamon gum whole* 'This was the largest audience to ever witness an inauguration—period—both in person and around the globe! Look at the white floor coverings on the National Mall grass! Nobody has ever used white floor coverings before!'",
            nextNodeId = "act2_ch1_mooch_arrives"
        ),
        DialogueNode(
            id = "act2_ch1_mooch_arrives",
            speaker = DialogueSpeaker.THE_MOOCH,
            side = SpeakerSide.RIGHT,
            text = "*Bursts through the West Wing French doors adjusting his aviators and cufflinks* Yo, step aside, Spicy! I'm The 11-Day Mooch, your new White House Communications Director! My official start date isn't until August 15, 2017, which gives me just enough time to call Ryan Lizza at The New Yorker on an unrecorded line, drop seventeen F-bombs about Reince Priebus and Steve Bannon's spinal flexibility, and get fired by Chief of Staff John Kelly in exactly ELEVEN DAYS!",
            nextNodeId = "act2_ch1_mall_hub"
        ),
        DialogueNode(
            id = "act2_ch1_mall_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at this communications team, folks—Spicy Sean is hiding in the dark behind the White House bushes yelling at reporters to turn their camera lights off, and The Mooch just set the land-speed record for getting fired before his first paycheck! How do we handle the press corps?",
            choices = listOf(
                DialogueChoice("a2c1_bushes", "Inspect Spicy Sean hiding in the White House bushes on May 9, 2017 ('Turn the lights off!')", listOf("bushes", "shrubs", "spicer", "lights", "comey"), "act2_ch1_spicer_bushes", completionFlag = "a2c1_bushes_done"),
                DialogueChoice("a2c1_mooch", "Listen to The 11-Day Mooch explain his New Yorker phone call & 11-day tenure", listOf("mooch", "scaramucci", "eleven days", "new yorker", "bannon"), "act2_ch1_mooch_rant", completionFlag = "a2c1_mooch_done"),
                DialogueChoice("a2c1_gorilla", "Laugh off the fake 17-hour 'Gorilla Channel' White House hoax!", listOf("gorilla", "channel", "vindication", "hoax", "tv"), "act2_ch1_gorilla_vindication", completionFlag = "a2c1_gorilla_done"),
                DialogueChoice("a2c1_battle", "Charge into the Briefing Room Battle against the Crowd-Size Fact-Checkers!", listOf("battle", "fight", "briefing", "proceed", "advance"), "act2_ch1_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act2_ch1_spicer_bushes",
            setFlagOnEnter = "a2c1_bushes_done",
            speaker = DialogueSpeaker.SPICY_SEAN,
            side = SpeakerSide.LEFT,
            text = "*Whispers from deep inside a dark row of White House holly bushes on the night of May 9, 2017* Listen, Commander, after you fired Director Jim-Tall while he was watching TV in Los Angeles—and he laughed thinking the breaking news chyron was a prank—twenty reporters chased me across the driveway! So naturally I crouched behind these bushes in pitch darkness and told them I'd only answer questions if they turned all their camera lights off! Also, nobody touches my stolen mini-fridge!",
            nextNodeId = "act2_ch1_mall_hub"
        ),
        DialogueNode(
            id = "act2_ch1_mooch_rant",
            setFlagOnEnter = "a2c1_mooch_done",
            speaker = DialogueSpeaker.THE_MOOCH,
            side = SpeakerSide.RIGHT,
            text = "Hey, I'm a straight shooter from Long Island! When I called Ryan Lizza at The New Yorker on July 26, 2017, did I scream that Reince Priebus is a 'f***ing paranoid schizophrenic, a paranoiac' and say 'I'm not Steve Bannon, I'm not trying to suck my own c***'?! Yes I did! And in my defense, eleven days is still a full " +
                "Scaramucci—the official unit of measurement for political survival!",
            nextNodeId = "act2_ch1_mall_hub"
        ),
        DialogueNode(
            id = "act2_ch1_gorilla_vindication",
            setFlagOnEnter = "a2c1_gorilla_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The fake news claimed I demanded a special White House TV broadcast of seventeen straight hours of fighting gorillas! Major anchors were nodding gravely on air: 'Yes, sources confirm the President watches gorilla combat.' It was an internet troll making fun of them! Although honestly, a 24-hour Gorilla Channel would blow CNN's ratings out of the water!",
            nextNodeId = "act2_ch1_mall_hub"
        ),
        DialogueNode(
            id = "act2_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THE BRIEFING ROOM DOORS ARE BLOWING OFF THE HINGES, BROTHER! Crowd-Size Fact-Checkers and Cable Chyron Operators are storming the podium! Unleash the 12:06 AM Covfefe Drop and the Red Diet Coke Button!",
            triggerBattleEncounterId = "act2_ch1_inauguration_battle",
            nextNodeId = "act2_ch1_post_battle"
        ),
        DialogueNode(
            id = "act2_ch1_post_battle",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Nice win, Commander! Our HP and MP are automatically back at 100%, and I packed extra Poland Spring bottles for Chapter 2 inside the Oval Office!",
            changeSceneId = SCENE_ACT2_CH2_OVAL.id,
            nextNodeId = "act2_ch2_intro"
        ),

        // Legacy compatibility node
        DialogueNode(
            id = "act2_marco_joins",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Little Marco reporting for duty with a full Poland Spring hydration pack!",
            nextNodeId = "act2_ch2_intro"
        ),

        // =========================================================================
        // ACT II (TRACK A) - CHAPTER 2: THE DIET COKE BUTTON, COVFEFE & THE ECLIPSE (MAY–NOV 2017)
        // =========================================================================
        DialogueNode(
            id = "act2_ch2_intro",
            changeSceneId = SCENE_ACT2_CH2_OVAL.id,
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside the Oval Office in the summer of 2017. Sitting on the historic Resolute Desk is a polished red wooden box with a brass button on top, while outside on the Truman Balcony the August 21, 2017 total solar eclipse is beginning to darken the sky!",
            nextNodeId = "act2_ch2_diet_coke_button"
        ),
        DialogueNode(
            id = "act2_ch2_diet_coke_button",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Taps the red wooden button on the Resolute Desk while reporters from the Associated Press and Financial Times flinch* Everyone gets a little nervous when I press this button, folks! They think it's the nuclear football—nope! Watch this: ten seconds later, a White House butler walks in carrying a single ice-cold Diet Coke on a silver platter! Twelve times a day! Peak executive efficiency!",
            nextNodeId = "act2_ch2_oval_hub"
        ),
        DialogueNode(
            id = "act2_ch2_oval_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Between Diet Coke deliveries, three iconic 2017 moments are unfolding simultaneously. Which executive event are we handling first?",
            choices = listOf(
                DialogueChoice("a2c2_covfefe", "Type the 12:06 AM May 31, 2017 'Despite the constant negative press covfefe' tweet!", listOf("covfefe", "tweet", "midnight", "press"), "act2_ch2_covfefe_node", completionFlag = "a2c2_covfefe_done"),
                DialogueChoice("a2c2_eclipse", "Step onto the Truman Balcony and stare bare-eyed straight into the Aug 21, 2017 Solar Eclipse!", listOf("eclipse", "sun", "glasses", "stare", "balcony"), "act2_ch2_eclipse_node", completionFlag = "a2c2_eclipse_done"),
                DialogueChoice("a2c2_koi", "Show how Abe emptied his Koi fish food box first in Tokyo!", listOf("koi", "fish", "japan", "shinzo abe", "vindication"), "act2_ch2_koi_vindication", completionFlag = "a2c2_koi_done"),
                DialogueChoice("a2c2_proceed", "Grab a box of Bounty paper towels & a black Sharpie for Chapter 3!", listOf("proceed", "advance", "sharpie", "towels", "chapter 3"), "act2_ch3_sharpie_intro")
            )
        ),
        DialogueNode(
            id = "act2_ch2_covfefe_node",
            setFlagOnEnter = "a2c2_covfefe_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Looks at his phone at 12:06 AM on May 31, 2017* 'Despite the constant negative press covfefe.' Then I fell asleep for six hours while the entire planet lost its mind! By 6:00 AM I tweeted: 'Who can figure out the true meaning of \"covfefe\" ??? Enjoy!' And then Spicy Sean marched out to the podium with a dead-serious face and told the press corps: 'The president and a small group of people know exactly what he meant!'",
            nextNodeId = "act2_ch2_oval_hub"
        ),
        DialogueNode(
            id = "act2_ch2_eclipse_node",
            setFlagOnEnter = "a2c2_eclipse_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Walks out onto the Truman Balcony on August 21, 2017 holding protective ISO-certified eclipse glasses in his right hand—then points his finger at the sky and squints BARE-EYED straight into the blinding sun* Suddenly a panicked White House aide beneath the balcony screams at the top of his lungs: 'DON'T LOOK!' Look, folks, the sun blinked first! Nobody has stronger retinas than me!",
            nextNodeId = "act2_ch2_oval_hub"
        ),
        DialogueNode(
            id = "act2_ch2_koi_vindication",
            setFlagOnEnter = "a2c2_koi_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Remember when the press had a four-day meltdown claiming I rudely dumped an entire wooden box of fish food into a Tokyo pond? Prime Minister Abe dumped his box in first, folks! He smiled, flipped the box, so I flipped my box! The media cropped out Abe and acted like I committed a war crime against ornamental carp!",
            nextNodeId = "act2_ch2_oval_hub"
        ),

        // =========================================================================
        // ACT II (TRACK A) - CHAPTER 3: PAPER-TOWEL FREE THROWS, HURRICANE SHARPIE & 15 FLUSHES (2017–2019)
        // =========================================================================
        DialogueNode(
            id = "act2_ch3_sharpie_intro",
            changeSceneId = SCENE_ACT2_CH3_SHARPIE.id,
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WELCOME TO CHAPTER 3 OF ACT II, BROTHER! From shooting NBA three-pointers with rolls of Bounty paper towels in San Juan, to redrawing Hurricane Dorian's cone into Alabama with a black Sharpie marker, to investigating why Americans have to flush low-flow toilets fifteen times—this chapter is pure non-stop 2017–2019 history!",
            nextNodeId = "act2_ch3_towel_shots"
        ),
        DialogueNode(
            id = "act2_ch3_towel_shots",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Squares his shoulders at Calvary Chapel in Guaynabo, Puerto Rico on October 3, 2017 and launches a jump-shot with a roll of Bounty paper towels into the crowd* Swish! Look at that form, folks—pure Steph Curry from the free-throw line! 'They had these lovely, soft towels. Very good towel!'",
            nextNodeId = "act2_ch3_sharpie_hub"
        ),
        DialogueNode(
            id = "act2_ch3_sharpie_hub",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "*Steps up with a National Weather Service map and an EPA plumbing binder* Hold on, Mr. President! First you shot paper towels like basketballs, now it's September 4, 2019 and you're holding up a Hurricane Dorian map with a hand-drawn black Sharpie loop over Alabama, and on December 6, 2019 you called a White House Small Business Roundtable to complain about flushing toilets 15 times!",
            choices = listOf(
                DialogueChoice("a2c3_sharpie", "Explain the Sept 4, 2019 Oval Office Black Sharpie Hurricane Dorian Map extending into Alabama!", listOf("sharpie", "alabama", "dorian", "hurricane", "map"), "act2_ch3_sharpie_node", completionFlag = "a2c3_sharpie_done"),
                DialogueChoice("a2c3_toilets", "Deliver the verbatim Dec 6, 2019 White House rant on flushing Low-Flow Toilets '10 times, 15 times!'", listOf("toilets", "flush", "fifteen", "water", "sinks"), "act2_ch3_toilets_node", completionFlag = "a2c3_toilets_done"),
                DialogueChoice("a2c3_meta_gag", "Watch Chyron McSpin lose the plumbing debate & pathetically try to pivot back to the 2005 Tic-Tac Bus!", listOf("tic tac", "pivot", "bus", "let it go", "old"), "act2_ch3_tactful_callback", completionFlag = "a2c3_meta_done"),
                DialogueChoice("a2c3_battle", "Battle the NOAA Sharpie Meteorologist & 15-Flush EPA Plumbing Inspector!", listOf("battle", "fight", "proceed", "advance"), "act2_ch3_sharpie_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act2_ch3_sharpie_node",
            setFlagOnEnter = "a2c3_sharpie_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Points at the August 29 National Hurricane Center poster on the Oval Office easel (Sept 4, 2019)* Look at that white forecast cone, folks—and look at that totally official, completely natural black permanent-marker bubble drawn onto the end of the Florida panhandle so it touches southeastern Alabama! What Sharpie?! I don't know anything about a Sharpie, other than it's a very bold, very decisive marker!",
            nextNodeId = "act2_ch3_sharpie_hub"
        ),
        DialogueNode(
            id = "act2_ch3_toilets_node",
            setFlagOnEnter = "a2c3_toilets_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Leans forward at the White House Small Business Roundtable on December 6, 2019* 'We have a situation where we're looking very strongly at sinks and showers and other elements of bathrooms where you turn the faucet on—in areas where there's tremendous amounts of water, where the water rushes out to sea because you could never handle it—and you don't get any water! People are flushing toilets 10 times, 15 times, as opposed to once! They end up using more water!'",
            nextNodeId = "act2_ch3_sharpie_hub"
        ),
        DialogueNode(
            id = "act2_ch3_tactful_callback",
            setFlagOnEnter = "a2c3_meta_done",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "*Stammers as his plumbing statistics fall apart* W-well, even if low-flow toilets do take three flushes... what about in 2005 on the Access Hollywood bus when you popped Tic-Tacs?!",
            nextNodeId = "act2_ch3_narrator_roasts_callback"
        ),
        DialogueNode(
            id = "act2_ch3_narrator_roasts_callback",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "*Laughs from the narrator desk* Did you see that?! It is December 2019—we are in the middle of an official White House roundtable about bathroom water pressure and hurricane markers—and the second Chyron runs out of material, he panics and reaches fourteen years into the past for a 2005 bus tape! Politicians and cable anchors genuinely never let anything go!",
            nextNodeId = "act2_ch3_sharpie_hub"
        ),
        DialogueNode(
            id = "act2_ch3_sharpie_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THE NOAA SHARPIE METEOROLOGIST AND THE 15-FLUSH EPA PLUMBING INSPECTOR ARE CHARGING THE OVAL OFFICE EASEL, BROTHER! Hit 'em with Bounty Paper-Towel Free-Throws and the Black Sharpie Cone Extension!",
            triggerBattleEncounterId = "act2_track_a_sharpie_battle",
            nextNodeId = "act2_ch3_sharpie_post_battle"
        ),
        DialogueNode(
            id = "act2_ch3_sharpie_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The plumbing flows freely, Alabama is officially safe from Hurricane Dorian, and our HP and MP are 100% restored! Now let's head down to the House Intelligence SCIF in Chapter 4 to recruit our new party companion: Kash Subpoena!",
            changeSceneId = SCENE_ACT2_CH4_SCIF.id,
            nextNodeId = "act2_ch4_scif_intro"
        ),

        // =========================================================================
        // ACT II (TRACK A) - CHAPTER 4: ENTER KASH SUBPOENA, THE NUNES MEMO & WIZARD KASH (2018–2019)
        // =========================================================================
        DialogueNode(
            id = "act2_ch4_scif_intro",
            changeSceneId = SCENE_ACT2_CH4_SCIF.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside the House Intelligence Secure Compartmented Information Facility (SCIF). Kicking open the acoustic vault door is your new party companion: Kash Subpoena—carrying his four-page February 2018 Nunes FISA Memo under one arm and a stack of \$19.99 illustrated children's books under the other!",
            nextNodeId = "act2_ch4_kash_recruitment"
        ),
        DialogueNode(
            id = "act2_ch4_kash_recruitment",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "*Slams a four-page memo and a glossy picture book onto the table* Commander! Kash Subpoena reporting for party duty! Not only did I co-author the four-page Nunes FISA Memo exposing the Carter Page warrant applications, I also wrote a three-part Brave Books children's fantasy trilogy called The Plot Against the King—where I literally had the illustrator draw me as a bearded medieval sorcerer named 'WIZARD KASH' who saves 'KING DONALD' using a magical Scroll of Truth!",
            nextNodeId = "act2_ch4_scif_hub"
        ),
        DialogueNode(
            id = "act2_ch4_scif_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Hold on, Kash—you wrote a children's bedtime book where I'm King Donald and you're a wizard in a pointy robe?! Let's inspect your kit and drop our biggest Vindication receipts of the term!",
            choices = listOf(
                DialogueChoice("a2c4_wizard", "Inspect Kash's \$19.99 'Wizard Kash' children's book & his exaggerated Benghazi resume!", listOf("wizard", "book", "king donald", "benghazi", "kash"), "act2_ch4_wizard_book_node", completionFlag = "a2c4_wizard_done"),
                DialogueChoice("a2c4_vindication", "Review the Mueller report findings & Charlottesville clip!", listOf("mueller", "vindication", "charlottesville", "transcript", "volume 1"), "act2_ch4_mueller_vindication", completionFlag = "a2c4_mueller_done"),
                DialogueChoice("a2c4_battle", "Test Kash Subpoena in combat against the SCIF Subpoena Bureaucrats!", listOf("battle", "fight", "scif", "proceed", "advance"), "act2_ch4_scif_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act2_ch4_wizard_book_node",
            setFlagOnEnter = "a2c4_wizard_done",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "*Opens The Plot Against the King to page 4* Look at the artwork, Commander: there's me as Wizard Kash holding a glowing staff, there's a magical slug named 'Dossier,' and for the villain in a 2022 children's book, I drew 'Hillary Queenton'! Oh, and don't ask my former DOJ colleagues about when I claimed I was the 'lead Benghazi trial prosecutor'—they got super technical and pointed out I was a junior staffer who showed up in the final week and wasn't even on the trial team!",
            nextNodeId = "act2_ch4_wizard_meta_gag"
        ),
        DialogueNode(
            id = "act2_ch4_wizard_meta_gag",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "*Chuckles at the desk* Wait a second, Kash—you published a children's picture book six years after the 2016 election and you STILL put Hillary on the cover as the villain?! In Washington, D.C., not even bedtime stories for first-graders are capable of letting old elections go!",
            nextNodeId = "act2_ch4_scif_hub"
        ),
        DialogueNode(
            id = "act2_ch4_mueller_vindication",
            setFlagOnEnter = "a2c4_mueller_done",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Two full years! Twenty-eight hundred subpoenas! Bob Mueller spent thirty million dollars digging through every single receipt, and what did he find? Zero! Absolute goose egg! And while we're at it, cable news spent seven years looping a six-second Charlottesville clip while deleting the part where the Boss condemned the extremists thirty seconds later! Complete media stitch-up!",
            nextNodeId = "act2_ch4_scif_hub"
        ),
        DialogueNode(
            id = "act2_ch4_scif_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WIZARD KASH JUST JOINED THE ACTIVE PARTY, BROTHER! Unleash his 4-Page Nunes FISA Scroll and King Donald's Wizard Book against the Subpoena Bureaucrats!",
            triggerBattleEncounterId = "act4_ch1_fisa_battle",
            nextNodeId = "act2_ch4_scif_post_battle"
        ),
        DialogueNode(
            id = "act2_ch4_scif_post_battle",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Boom! The SCIF is cleared, every binder is declassified, and our HP and MP are back at 100%! Now let's march into the House Chamber for Chapter 5—the Act II Finale against Speaker Nancy Palooza!",
            changeSceneId = SCENE_ACT2_CH3_ROTUNDA.id,
            nextNodeId = "act2_ch3_intro"
        ),

        // =========================================================================
        // ACT II (TRACK A) - CHAPTER 5: SIDEWAYS CLAPS, PRE-SCORED SPEECHES & $24K FREEZERS (2020 FINALE)
        // =========================================================================
        DialogueNode(
            id = "act2_ch3_intro",
            changeSceneId = SCENE_ACT2_CH3_ROTUNDA.id,
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside the House Chamber in 2020! Standing on the Speaker's dais behind Commander America is Speaker Nancy Palooza—flanked by two \$24,000 stainless-steel Sub-Zero freezers stuffed with \$12 pints of Jeni's Splendid Ice Cream, a stack of pre-scored State of the Union pages, and a fresh blowout from an unmasked visit to a locked-down San Francisco hair salon!",
            nextNodeId = "act2_ch5_nancy_greeting"
        ),
        DialogueNode(
            id = "act2_ch5_nancy_greeting",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.RIGHT,
            text = "*Extends both arms stiffly at a 45-degree angle and claps sideways right in Commander America's ear* Good evening, Mr. President! Remember earlier when I said 'We have to pass the bill so that you can find out what is in it, away from the fog of the controversy'?! Well tonight it's February 4, 2020, and while you were talking at the podium, C-SPAN cameras caught me discreetly pre-ripping starter notches into the top left corners of your State of the Union pages under the desk so I wouldn't look weak trying to tear thick cardstock on live television!",
            nextNodeId = "act2_ch5_commander_roasts_nancy"
        ),
        DialogueNode(
            id = "act2_ch5_commander_roasts_nancy",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at Crazy Nancy, folks! First she had to pre-cut my speech under the desk like an arts-and-crafts project because the paper was too tough for her, then during the April 2020 lockdown she went on James Corden's Late Late Show to flex TWO \$24,000 Sub-Zero freezers packed with gourmet \$12 Jeni's ice cream pints, and in August 2020 security cameras caught her strolling through eSalon in San Francisco with wet hair and NO MASK while every other salon in the city was bolted shut!",
            nextNodeId = "act2_ch5_nancy_salon_defense"
        ),
        DialogueNode(
            id = "act2_ch5_nancy_salon_defense",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.RIGHT,
            text = "*Gasps indignantly while clutching a pint of Jeni's Darkest Chocolate* Excuse me! As I told reporters on September 2, 2020: 'I take responsibility for trusting the word of a neighborhood salon that I've been to over the years many times... It turns out it was a setup! So I take responsibility for falling for a setup! And I think that this salon owes me an apology for setting me up!' Now watch me exercise my free-market deep-in-the-money tech call options!",
            nextNodeId = "act2_ch5_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act2_ch5_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE 2020 STATE OF THE UNION SHOWDOWN, BROTHER! Commander America, Little Marco, and Wizard Kash vs. Speaker Nancy Palooza's Pre-Scored Speech Rip, 45-Degree Sideways Seal Clap, and Twin \$24,000 Sub-Zero Freezers! RING THE BELL!",
            triggerBattleEncounterId = "act2_media_battle",
            nextNodeId = "act2_ch5_post_battle"
        ),
        DialogueNode(
            id = "act2_ch5_post_battle",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Victory in the House Chamber! The gavel falls on the 2020 State of the Union as the journey marches onward to Act III!",
            nextNodeId = "act3_ch1_intro"
        ),

        // =========================================================================
        // ACT II (TRACK B) - CHAPTER 1: SIDEWAYS SEAL CLAPS VS. NEON SALSA RUFFLES (PLAYING AS NANCY PALOOZA)
        // =========================================================================
        DialogueNode(
            id = "act2_tb_ch1_intro",
            changeSceneId = SCENE_ACT2_TB_CH1_BUSHES.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Washington, D.C., 2017 to 2020! Speaker Nancy Palooza strides down the Capitol hallways, power-heels clicking, armed with a fresh blowout, a killer gavel, and an eye for high-stakes tech stock options!",
            nextNodeId = "act2_tb_ch1_kjp_oprah"
        ),
        DialogueNode(
            id = "act2_tb_ch1_kjp_oprah",
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Flipping to Tab 2 of my historic binder: Nancy Palooza has reclaimed the Speaker's gavel, perfected the 45-degree sideways seal clap, and is walking past the White House shrubbery—wait, why are the bushes shaking to the beat of Destiny's Child?!",
            nextNodeId = "act2_tb_ch1_oprah_salsa"
        ),
        DialogueNode(
            id = "act2_tb_ch1_oprah_salsa",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "LOOK INSIDE THE HEDGES, EVERYBODY! SPICY SEAN JUST JUMPED OUT OF THE BUSHES WEARING HIS SEPTEMBER 2019 NEON-GREEN RUFFLED DANCING WITH THE STARS SALSA SHIRT, HONEY!",
            nextNodeId = "act2_tb_ch1_spicer_salsa_ambush"
        ),
        DialogueNode(
            id = "act2_tb_ch1_spicer_salsa_ambush",
            speaker = DialogueSpeaker.SPICY_SEAN,
            side = SpeakerSide.RIGHT,
            text = "*Leaps out of the White House bushes in a blinding neon-green ruffled sleeves shirt, shaking a pair of maracas while chewing 35 pieces of Orbit Cinnamon gum* HOLD IT RIGHT THERE, MADAM SPEAKER! On September 16, 2019 I debuted on Dancing with the Stars in this exact neon-green cabbage-ruffle shirt dancing the salsa to 'Bills, Bills, Bills' and scored a 12 out of 30! And speaking of bills—didn't you tell America 'We have to pass the bill so that you can find out what is in it'?!",
            nextNodeId = "act2_tb_ch1_nancy_clapback"
        ),
        DialogueNode(
            id = "act2_tb_ch1_nancy_clapback",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "*Extends her arms at a razor-sharp 45-degree angle and delivers the February 5, 2019 Sideways Seal Clap right at Spicy Sean's neon sleeves* Oh, bravo, Sean! Look at you—first you stole a mini-fridge from twenty-two-year-old staffers at 8:00 PM, then you hid in those exact bushes in the dark after the Comey firing shouting 'Turn the lights off!', and now you're dressed like a radioactive head of romaine lettuce alongside a Communications Director who got fired in eleven days!",
            nextNodeId = "act2_tb_ch1_mooch_chimes_in"
        ),
        DialogueNode(
            id = "act2_tb_ch1_mooch_chimes_in",
            speaker = DialogueSpeaker.THE_MOOCH,
            side = SpeakerSide.RIGHT,
            text = "Hey, Madam Speaker, show some respect! In those eleven days in July 2017, I called Ryan Lizza at The New Yorker, taught the entire country a brand-new anatomical yoga metaphor about Steve Bannon, and got escorted off the complex before my security badge finished printing! Let's dance, Spicy!",
            nextNodeId = "act2_tb_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act2_tb_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S NEON SALSA RUFFLES AND THE 11-DAY MOOCH VS. SPEAKER NANCY PALOOZA, EVERYBODY! Unleash the 45-Degree Sideways Seal Clap and Pass the Bill to Find Out!",
            triggerBattleEncounterId = "act2_track_b_spicer_salsa_battle",
            nextNodeId = "act2_tb_ch1_post_battle"
        ),
        DialogueNode(
            id = "act2_tb_ch1_post_battle",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "Back into the bushes with your stolen mini-fridge, Sean! With our HP and MP automatically restored to 100%, let's step up to the Speaker's rostrum for Chapter 2: The February 4, 2020 State of the Union!",
            changeSceneId = SCENE_ACT2_TB_CH2_SOTU.id,
            nextNodeId = "act2_tb_ch2_intro"
        ),

        // =========================================================================
        // ACT II (TRACK B) - CHAPTER 2: PRE-SCORING THE SPEECH & THE 75%-SPEED VIDEO (2019–2020)
        // =========================================================================
        DialogueNode(
            id = "act2_tb_ch2_intro",
            changeSceneId = SCENE_ACT2_TB_CH2_SOTU.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is February 4, 2020 on the raised rostrum of the House Chamber! Commander America just handed Nancy Palooza his printed State of the Union address, snubbed her outstretched handshake, and is currently holding forth about hurricane markers and bathroom water pressure!",
            nextNodeId = "act2_tb_ch2_sotu_hub"
        ),
        DialogueNode(
            id = "act2_tb_ch2_sotu_hub",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "*Tests the thickness of the State of the Union speech pages between her fingers* Wait a minute—the White House printed this speech on heavy-gauge archival cardstock! If I try to rip thirty pages of this cold at the end of the speech, my hands might get stuck on live television! How do we prepare the grand finale and crush the doctored video smear against us?",
            choices = listOf(
                DialogueChoice("tb2_prescore", "Secretly pre-score starter notches into the pages under the desk before ripping them on live TV!", listOf("pre score", "rip", "tear", "speech", "notches"), "act2_tb_ch2_prescore_node", completionFlag = "tb2_prescore_done"),
                DialogueChoice("tb2_vindication1", "Expose the slowed-down 'slurring' video smear!", listOf("drunk", "slurring", "slowed", "video", "vindication"), "act2_tb_ch2_slur_vindication", completionFlag = "tb2_slur_done"),
                DialogueChoice("tb2_meta", "Watch Commander America get mad at the speech rip & randomly bring up 2015 Chappaqua emails in 2020!", listOf("meta", "callback", "cloth", "let it go", "donald"), "act2_tb_ch2_meta_callback", completionFlag = "tb2_meta_done"),
                DialogueChoice("tb2_proceed", "Head home to San Francisco for Chapter 3: The \$24,000 Sub-Zero Freezers & eSalon!", listOf("proceed", "advance", "freezers", "ice cream", "salon"), "act2_tb_ch3_intro")
            )
        ),
        DialogueNode(
            id = "act2_tb_ch2_prescore_node",
            setFlagOnEnter = "tb2_prescore_done",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "*Lowers the stack of pages just below desk level while C-SPAN cameras zoom in, discreetly pinching and pre-ripping small starter notches into the top left margins* Always engineer your structural tear lines in advance! Then, the moment he finishes speaking, I stand up behind his shoulder, hold each pre-scored packet high for the cameras, and—*RRRRIP! RRRRIP!* And when reporters ask why I tore it up outside the chamber: 'It was the courteous thing to do considering the alternatives!'",
            nextNodeId = "act2_tb_ch2_sotu_hub"
        ),
        DialogueNode(
            id = "act2_tb_ch2_slur_vindication",
            setFlagOnEnter = "tb2_slur_done",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "Someone slowed down my press conference video by twenty-five percent and put it on Facebook claiming I was three martinis deep! Honey, I have never had a sip of alcohol in my entire life! My only high is dark chocolate and crushing rebellions on the House floor!",
            nextNodeId = "act2_tb_ch2_sotu_hub"
        ),
        DialogueNode(
            id = "act2_tb_ch2_meta_callback",
            setFlagOnEnter = "tb2_meta_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.RIGHT,
            text = "*Turns around at the podium glaring at the torn halves of his speech* She pre-ripped my speech under the desk, folks! Total violation! At least I didn't wipe my speech 'with a cloth or something' in a Chappaqua basement in 2015!",
            nextNodeId = "act2_tb_ch2_psaki_roasts_meta"
        ),
        DialogueNode(
            id = "act2_tb_ch2_psaki_roasts_meta",
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "*Shakes her head at the narrator desk* Let's pause and appreciate the commitment: it is February 2020, he is arguing with Nancy Palooza from California, and his reflex is STILL to shout about a 2015 email server belonging to an entirely different woman who retired in the woods four years ago! Nobody in politics ever lets anything go!",
            nextNodeId = "act2_tb_ch2_sotu_hub"
        ),

        // =========================================================================
        // ACT II (TRACK B) - CHAPTER 3: TWIN $24,000 FREEZERS & THE SALON 'SETUP' (APRIL–AUG 2020)
        // =========================================================================
        DialogueNode(
            id = "act2_tb_ch3_intro",
            changeSceneId = SCENE_ACT2_TB_CH3_SUBZERO.id,
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is April 14, 2020 in San Francisco! While the entire country is locked indoors baking sourdough bread during quarantine, Speaker Nancy Palooza has invited James Corden's Late Late Show cameras into her gourmet kitchen to show off how she's surviving lockdown!",
            nextNodeId = "act2_tb_ch3_subzero_hub"
        ),
        DialogueNode(
            id = "act2_tb_ch3_subzero_hub",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "*Stands proudly in front of two gleaming, side-by-side \$24,000 stainless-steel Sub-Zero refrigerator-freezers* Look at this frost-free symmetry! How should we showcase our quarantine essentials, explain our August 2020 hair salon adventure, and debunk two massive viral smears?",
            choices = listOf(
                DialogueChoice("tb3_icecream", "Open the twin \$24,000 Sub-Zero freezers & show off the \$12 pints of Jeni's Splendid Ice Cream!", listOf("freezers", "sub zero", "ice cream", "jenis", "corden"), "act2_tb_ch3_icecream_node", completionFlag = "tb3_icecream_done"),
                DialogueChoice("tb3_salon", "Explain the Aug 31, 2020 unmasked San Francisco eSalon blowout ('It was a setup! They owe ME an apology!')", listOf("salon", "setup", "hair", "mask", "apology"), "act2_tb_ch3_salon_node", completionFlag = "tb3_salon_done"),
                DialogueChoice("tb3_vind23", "Laugh off the fake 'Social Security Impeachment' & Florida mansion rumors!", listOf("social security", "mansion", "florida", "impeachment", "vindication"), "act2_tb_ch3_vindication_node", completionFlag = "tb3_vind23_done"),
                DialogueChoice("tb3_proceed", "Head to the House Clerk Stock Disclosure Vault for Chapter 4!", listOf("proceed", "advance", "stocks", "options", "nvidia"), "act2_tb_ch4_intro")
            )
        ),
        DialogueNode(
            id = "act2_tb_ch3_icecream_node",
            setFlagOnEnter = "tb3_icecream_done",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "*Pulls open the heavy stainless-steel Sub-Zero freezer drawer on national television (April 14, 2020), revealing dozens of neatly stacked \$12 pints of Jeni's Splendid Ice Cream* 'I like chocolate better than anything! Look—Darkest Chocolate, Chocolate Indulgence, Milkiest Chocolate! I don't know what I would have done if I didn't have my ice cream!'Wait—why are my own campaign consultants screaming that flexing \$24,000 appliances full of \$12 artisanal pints during a 14% unemployment spike is 'Marie Antoinette with a waffle cone'?!",
            nextNodeId = "act2_tb_ch3_subzero_hub"
        ),
        DialogueNode(
            id = "act2_tb_ch3_salon_node",
            setFlagOnEnter = "tb3_salon_done",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "*Adjusts her freshly blown-out hair on September 2, 2020* Look, yes, security footage from August 31 showed me walking through eSalon in San Francisco with wet hair and my mask around my neck at 3:08 PM while every salon in the city was legally closed for indoor service! So I held a press conference and explained the only logical conclusion: 'I take responsibility for trusting the word of a neighborhood salon that I've been to over the years many times... It turns out it was a setup! So I take responsibility for falling for a setup! And I think that this salon owes me an apology for setting me up!'",
            nextNodeId = "act2_tb_ch3_subzero_hub"
        ),
        DialogueNode(
            id = "act2_tb_ch3_vindication_node",
            setFlagOnEnter = "tb3_vind23_done",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "They claimed I raided the Social Security trust fund to pay for impeachment, and bought a twenty-five million dollar beachfront mansion in Florida! As if I would ever move to Florida! I get hives just looking at a golf cart!",
            nextNodeId = "act2_tb_ch3_subzero_hub"
        ),

        // =========================================================================
        // ACT II (TRACK B) - CHAPTER 4: DEEP-ITM CALL OPTIONS & THE $341K NVIDIA LOSS (2020–2022)
        // =========================================================================
        DialogueNode(
            id = "act2_tb_ch4_intro",
            changeSceneId = SCENE_ACT2_TB_CH4_STOCKS.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside the House Clerk's Periodic Transaction Report (PTR) Vault! On the wall, retail traders have launched an actual exchange-traded fund ticker—'NANC'—just to copy Paul and Nancy's deep-in-the-money tech call options on Alphabet, Visa, and Palo Alto Networks!",
            nextNodeId = "act2_tb_ch4_free_market"
        ),
        DialogueNode(
            id = "act2_tb_ch4_free_market",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "*Stands at the Capitol press podium on December 15, 2021 when reporter Bryan Metzger asks if members of Congress and their spouses should be banned from trading individual stocks while writing legislation* 'No! We are a free-market economy. They should be able to participate in that!'",
            nextNodeId = "act2_tb_ch4_stocks_hub"
        ),
        DialogueNode(
            id = "act2_tb_ch4_stocks_hub",
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "While the stock ticker flashes green on 2008 Visa IPO shares and \$5.3 million in Alphabet call options exercised a week before a House Judiciary antitrust vote, critics are accusing the Pelosis of two specific insider trades that actually never happened! Let's review the receipts before Wizard Kash attacks!",
            choices = listOf(
                DialogueChoice("tb4_options", "Review the real-life Visa IPO, Alphabet (\$5.3M), and Palo Alto Networks LEAPS call options!", listOf("options", "visa", "alphabet", "google", "nanc"), "act2_tb_ch4_options_node", completionFlag = "tb4_options_done"),
                DialogueChoice("tb4_nvidia_vind", "Review Paul's disastrous Nvidia loss & the fake 3M mask stock hoax!", listOf("nvidia", "chips act", "loss", "3m", "vindication"), "act2_tb_ch4_nvidia_vindication", completionFlag = "tb4_nvidia_done"),
                DialogueChoice("tb4_kash_battle", "Confront Kash Subpoena ('Wizard Kash') storming the vault with his \$19.99 children's book!", listOf("kash", "wizard", "battle", "fight", "proceed"), "act2_tb_ch4_kash_ambush")
            )
        ),
        DialogueNode(
            id = "act2_tb_ch4_options_node",
            setFlagOnEnter = "tb4_options_done",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "Look, I have zero involvement in Paul's timing! Is it a fun coincidence that in 2008 we got direct access to 20,000 pre-IPO shares of Visa right as the Credit Card Fair Fee Act stalled in the House, or that in June 2021 Paul exercised \$4.8 million in Alphabet call options for a \$5.3 million profit one week before the House Judiciary Committee voted on tech antitrust bills, or that our portfolio beat every hedge fund on Wall Street?! That's just the magic of San Francisco venture capital!",
            nextNodeId = "act2_tb_ch4_stocks_hub"
        ),
        DialogueNode(
            id = "act2_tb_ch4_nvidia_vindication",
            setFlagOnEnter = "tb4_nvidia_done",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "Everyone on the internet treats my husband Paul like an omniscient stock wizard. They screamed that he bought Nvidia right before the CHIPS Act. Look at the disclosure filing: he panicked under media pressure, sold it early, and lost three hundred and forty thousand dollars! The worst trade in tech history! If he was insider trading, he's the worst criminal on Wall Street!",
            nextNodeId = "act2_tb_ch4_stocks_hub"
        ),
        DialogueNode(
            id = "act2_tb_ch4_kash_ambush",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.RIGHT,
            text = "*Kicks open the House Clerk doors wearing a purple 'Wizard Kash' cape over his suit and waving his four-page Nunes Memo* FREEZE THOSE CALL OPTIONS, SPEAKER PALOOZA! I am Kash Subpoena—known to readers ages 4 to 8 as 'Wizard Kash' from my \$19.99 Brave Books classic The Plot Against the King! Every binder in this building is declassified!",
            nextNodeId = "act2_tb_ch4_kash_battle_trigger"
        ),
        DialogueNode(
            id = "act2_tb_ch4_kash_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WIZARD KASH AND HIS DECLASSIFIED BINDER GOLEM ARE ATTACKING THE STOCK VAULT, HONEY! Nancy Palooza, hit him with the \$24,000 Sub-Zero Freezer Flex and the \$341k Pre-CHIPS Act Loss Receipt!",
            triggerBattleEncounterId = "act2_track_b_kash_battle",
            nextNodeId = "act2_tb_ch4_post_battle"
        ),
        DialogueNode(
            id = "act2_tb_ch4_post_battle",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "Take your \$19.99 wizard book back to the gift shop, Kash! Our HP and MP are back at 100%, and we just got an urgent alert from northeast Philadelphia for Chapter 5: Commander America's legal team just booked the wrong 'Four Seasons'!",
            changeSceneId = SCENE_ACT2_TB_CH5_FOUR_SEASONS.id,
            nextNodeId = "act2_tb_ch5_intro"
        ),

        // =========================================================================
        // ACT II (TRACK B) - CHAPTER 5: BETWEEN A CREMATORIUM & AN ADULT BOOKSHOP (NOV 2020 FINALE)
        // =========================================================================
        DialogueNode(
            id = "act2_tb_ch5_intro",
            changeSceneId = SCENE_ACT2_TB_CH5_FOUR_SEASONS.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is 11:30 AM on November 7, 2020 at 7339 State Road in an industrial strip of northeast Philadelphia! After Commander America tweeted 'Big press conference today in Philadelphia at Four Seasons, 11:00am' and then corrected it to 'Four Seasons Total Landscaping,' reporters have gathered in front of a beige garage door wedged directly between the Delaware Valley Crematorium and Fantasy Island Adult Books!",
            nextNodeId = "act2_tb_ch5_rudy_arrives"
        ),
        DialogueNode(
            id = "act2_tb_ch5_rudy_arrives",
            speaker = DialogueSpeaker.RUDY_GHOUL,
            side = SpeakerSide.RIGHT,
            text = "*Stands between a stack of bags of topsoil and a yellow 'ADULT BOOKS & NOVELTIES' sign while dark brown hair dye begins melting in twin streams down both of his temples* Welcome to Four Seasons Total Landscaping! Wait—a reporter in the front row is telling me the Associated Press and every television network just called Pennsylvania while I was standing in front of this garage door?! *Raises both arms to the sky* 'All the networks! Wow! All the networks! We have to forget about the law! Judges don't count! Don't be ridiculous, networks don't get to decide elections!'",
            nextNodeId = "act2_tb_ch5_nancy_roasts_rudy"
        ),
        DialogueNode(
            id = "act2_tb_ch5_nancy_roasts_rudy",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "Rudy, look at yourself! You wanted the five-star Four Seasons Hotel on Arch Street in Center City, someone dialed the first 'Four Seasons' in the phone book, and rather than admit a typo, you drove the entire press corps to a mulching garage between a crematorium and a sex shop! And why is your hair leaking motor oil down your cheeks while you act out scenes from the 1992 Joe Pesci movie My Cousin Vinny?!",
            nextNodeId = "act2_tb_ch5_rudy_borat_defense"
        ),
        DialogueNode(
            id = "act2_tb_ch5_rudy_borat_defense",
            speaker = DialogueSpeaker.RUDY_GHOUL,
            side = SpeakerSide.RIGHT,
            text = "*Dabs a handkerchief across his forehead, smearing another streak of brown hair dye across his cheek* First of all, My Cousin Vinny is a masterpiece of constitutional jurisprudence—'How many fingers am I holding up?! Look at her, she's wearing thick glasses!' Second of all, regarding last month's Borat Subsequent Moviefilm hotel scene—as I tweeted on October 21, 2020: 'The Borat video is a complete fabrication. I was tucking in my shirt after taking off the recording equipment!' Now prepare to face the wrath of Four Seasons Total Landscaping!",
            nextNodeId = "act2_tb_ch5_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act2_tb_ch5_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE GRAND FINALE AT FOUR SEASONS TOTAL LANDSCAPING, EVERYBODY! Speaker Nancy Palooza vs. Rudy Ghoul, the Riding-Mower Mechanic, and the Melting Hair-Dye Blob! Unleash the Pre-Scored SOTU Speech Rip and Deep-ITM Call Options!",
            triggerBattleEncounterId = "act2_track_b_rudy_finale",
            nextNodeId = "act2_tb_ch5_post_battle"
        ),
        DialogueNode(
            id = "act2_tb_ch5_post_battle",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.LEFT,
            text = "*Reaches into her portable \$24,000 Sub-Zero freezer and pulls out a fresh double-scoop chocolate chip waffle cone* That wraps up 2020! Now it's January 2021, and it's time to pass this chocolate chip waffle cone to our next protagonist: Joe Naptime!",
            nextNodeId = "act2_tb_ch5_biden_handoff"
        ),
        DialogueNode(
            id = "act2_tb_ch5_biden_handoff",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Steps up wearing Ray-Ban aviators and takes the double-scoop waffle cone* Folks, my name is Joe Biden. I'm Dr. Jill Biden's husband, and I came down because I heard there was chocolate chip ice cream! In Act III, we're unleashing Corn Pop, Badakathcare, Truinanashabbadepressure, and 'End of quote, repeat the line!' Let's hit the road!",
            nextNodeId = "act3_tb_ch1_intro"
        ),

        // =========================================================================
        // ACT III (TRACK A) - CHAPTER 1: THREE STUMBLES UP THE STAIRS & TOE-CAGE BIKES (2021–2022)
        // =========================================================================
        DialogueNode(
            id = "act3_ch1_intro",
            changeSceneId = SCENE_ACT3_CH1_STEEL_MILL.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to Act III (2021–2023)! On Day One of January 2021, Joe Naptime evicted the red wooden Diet Coke valet button from the Resolute Desk and replaced it with a freezer full of Jeni's Splendid Chocolate Chip ice cream! Meanwhile, out on the tarmac at Joint Base Andrews on March 19, 2021, a brisk breeze is blowing across the stairs of Air Force One!",
            nextNodeId = "act3_ch1_stairs_bike_intro"
        ),
        // Legacy compatibility alias
        DialogueNode(
            id = "act3_intro",
            changeSceneId = SCENE_ACT3_CH1_STEEL_MILL.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to Act III (2021–2023): Three Stumbles Up the Stairs, Toe-Cage Bicycles & Bathroom Chandeliers!",
            nextNodeId = "act3_ch1_stairs_bike_intro"
        ),
        DialogueNode(
            id = "act3_ch1_stairs_bike_intro",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at the tarmac at Joint Base Andrews on March 19, 2021, folks! Joe Naptime tries to jog up the Air Force One staircase—he trips once, he trips a second time, and he drops all the way to his knees a THIRD time in four seconds before saluting at the top! And how did Karine Hatch-Act explain it to the press pool?!",
            nextNodeId = "act3_ch1_kjp_wind_excuse"
        ),
        DialogueNode(
            id = "act3_ch1_kjp_wind_excuse",
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "*Pops up holding an anemometer* Excuse me! As I told reporters aboard Air Force One on March 19, 2021: 'It's pretty windy outside! It's very windy. I almost fell coming up the steps myself! He is doing 100 percent fine!'",
            nextNodeId = "act3_ch1_tarmac_hub"
        ),
        DialogueNode(
            id = "act3_ch1_tarmac_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Sure, Karine—a 14-mile-per-hour breeze knocked him down three times going UP the stairs! And wait until you see what happens when he stops his Trek bicycle in Rehoboth Beach or searches a room for a congresswoman! What are we inspecting first?",
            choices = listOf(
                DialogueChoice("a3c1_bike", "Replay the June 18, 2022 Rehoboth Beach Stationary Trek Bicycle Toe-Cage Crash!", listOf("bike", "bicycle", "rehoboth", "toe cage", "crash"), "act3_ch1_bike_node", completionFlag = "a3c1_bike_done"),
                DialogueChoice("a3c1_jackie", "Replay the Sept 28, 2022 'Jackie, are you here? Where's Jackie?' Conference Moment!", listOf("jackie", "where is jackie", "top of mind", "conference"), "act3_ch1_jackie_node", completionFlag = "a3c1_jackie_done"),
                DialogueChoice("a3c1_battle", "Clear the tarmac against the Air Force One Staircase Gust & Stationary Toe-Cage Bicycle!", listOf("battle", "fight", "stairs", "proceed", "advance"), "act3_ch1_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act3_ch1_bike_node",
            setFlagOnEnter = "a3c1_bike_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Points at the Cape Henlopen State Park bike trail in Rehoboth Beach, Delaware (June 18, 2022)* Watch the physics here, folks: Joe rides up to the press pool on his Trek FX hybrid bike, comes to a COMPLETE STOP—zero miles per hour!—tries to pull his right sneaker out of the metal pedal toe-cage, and slowly tips over sideways into a bush like a giant timber tree! At least when he stood up he blamed the toe cages instead of Vladimir Putin!",
            nextNodeId = "act3_ch1_tarmac_hub"
        ),
        DialogueNode(
            id = "act3_ch1_jackie_node",
            setFlagOnEnter = "a3c1_jackie_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "And then on September 28, 2022 at the White House Conference on Hunger, right after watching a tribute video for Congresswoman Jackie Walorski—who had tragically passed away seven weeks earlier and for whom Joe had issued an official White House condolence statement—he looks around the room and says verbatim: 'Jackie, are you here? Where's Jackie? I think she was going to be here!' And Karine Hatch-Act spent twenty minutes at the podium claiming 'She was just top of mind!'",
            nextNodeId = "act3_ch1_tarmac_hub"
        ),
        DialogueNode(
            id = "act3_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WATCH OUT FOR THOSE METAL PEDAL TOE CAGES, BROTHER! The Air Force One Triple-Stumble Staircase and the Stationary Rehoboth Trek Bicycle are blocking the runway! Smash 'em flat!",
            triggerBattleEncounterId = "act3_track_a_stairs_bike_battle",
            nextNodeId = "act3_ch1_post_battle"
        ),
        DialogueNode(
            id = "act3_ch1_post_battle",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Runway cleared and our HP and MP are automatically restored to 100%! Now let's head into the Senate Judiciary Committee Chamber in Chapter 2 to recruit our fourth companion: Senator Foghorn Kennedy!",
            changeSceneId = SCENE_ACT3_CH2_USMCA.id,
            nextNodeId = "act3_ch2_intro"
        ),

        // =========================================================================
        // ACT III (TRACK A) - CHAPTER 2: ENTER FOGHORN KENNEDY & 'CALL A CRACKHEAD!' (2021–2023)
        // =========================================================================
        DialogueNode(
            id = "act3_ch2_intro",
            changeSceneId = SCENE_ACT3_CH2_USMCA.id,
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside the wood-paneled Senate Judiciary Committee Chamber. Leaning into his microphone with a slow, syrupy Louisiana drawl is your newest party member: Senator Foghorn Kennedy!",
            nextNodeId = "act3_ch2_kennedy_joins"
        ),
        DialogueNode(
            id = "act3_ch2_kennedy_joins",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "*Adjusts his reading glasses with a slow Southern drawl* Well howdy, Commander! Look, if frogs had wings, they wouldn't bump their tails on the ground, and if Washington bureaucrats had brains, they'd be dangerous! As I told voters in my September 2022 campaign commercial: 'Look, if you hate cops just because they're cops, the next time you get in trouble, call a crackhead!'",
            nextNodeId = "act3_ch2_kennedy_hub"
        ),
        // Legacy compatibility node
        DialogueNode(
            id = "act3_vance_joins",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Look, if you hate cops just because they're cops, the next time you get in trouble, call a crackhead!",
            nextNodeId = "act3_ch2_kennedy_hub"
        ),
        DialogueNode(
            id = "act3_ch2_kennedy_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Wait a second, Foghorn—half the country thinks you whittle porch rockers in a bayou bait shop, and Kayleigh just handed me your real-life academic transcript! Let's check out your secret Oxford degree and your greatest Senate hearing one-liners!",
            choices = listOf(
                DialogueChoice("a3c2_oxford", "Expose Foghorn Kennedy's secret Oxford University First-Class Honours degree & 2004 John Kerry endorsement!", listOf("oxford", "degree", "england", "democrat", "drawl"), "act3_ch2_oxford_node", completionFlag = "a3c2_oxford_done"),
                DialogueChoice("a3c2_nominee", "Replay the Jan 2023 Judicial Nominee Cross-Exam: 'Tell me what Article V of the Constitution does!'", listOf("article 5", "article 2", "constitution", "nominee", "judge"), "act3_ch2_nominee_node", completionFlag = "a3c2_nominee_done"),
                DialogueChoice("a3c2_outback", "Deliver the May 2023 'Eating Cat Food in a Tent Behind an Outback' & 'Weed-Whacker Haircut' one-liners!", listOf("cat food", "outback", "weed whacker", "one liner"), "act3_ch2_outback_node", completionFlag = "a3c2_outback_done"),
                DialogueChoice("a3c2_proceed", "Bring Foghorn Kennedy to the Mar-a-Lago Bathroom Chandelier Vault for Chapter 3!", listOf("proceed", "advance", "mar a lago", "chandelier", "bathroom"), "act3_ch3_intro")
            )
        ),
        DialogueNode(
            id = "act3_ch2_oxford_node",
            setFlagOnEnter = "a3c2_oxford_done",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "EQUAL-OPPORTUNITY FACT CHECK! Before Senator Foghorn Kennedy adopted his folksy country-grandpa persona and switched parties in 2007 (after running as a Democrat and endorsing John Kerry in 2004!), he graduated magna cum laude from Vanderbilt, served as Executive Editor of the Virginia Law Review, and went to England where he earned a First-Class Honours Bachelor of Civil Law degree from Magdalen College, Oxford University! He knows Latin maxims—he just prefers talking about possums and crackheads on cable TV!",
            nextNodeId = "act3_ch2_kennedy_hub"
        ),
        DialogueNode(
            id = "act3_ch2_nominee_node",
            setFlagOnEnter = "a3c2_nominee_done",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "*Peers over his spectacles at federal judicial nominee Charnelle Bjelkengren on January 25, 2023* 'Judge, tell me what Article V of the Constitution does.' And she says: 'Article V is not coming to mind at the moment.' So I ask gently: 'How about Article II?' And she says: 'Neither is Article II.' Folks, she was applying for a lifetime federal judgeship and couldn't name the Article that creates the Presidency or the amendment process!",
            nextNodeId = "act3_ch2_kennedy_hub"
        ),
        DialogueNode(
            id = "act3_ch2_outback_node",
            setFlagOnEnter = "a3c2_outback_done",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "And in May 2023 during a Senate DEA hearing on cartels, I pointed out the simple economic truth: 'Without the people of America, Mexico, figuratively speaking, would be eating cat food out of a can and living in a tent behind an Outback!' Now let's go inspect those cardboard boxes down in Palm Beach!",
            nextNodeId = "act3_ch2_kennedy_hub"
        ),

        // =========================================================================
        // ACT III (TRACK A) - CHAPTER 3: BATHROOM CHANDELIERS VS. THE 1967 CORVETTE GARAGE (2022–2023)
        // =========================================================================
        DialogueNode(
            id = "act3_ch3_intro",
            changeSceneId = SCENE_ACT3_CH3_AF1.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is 2022–2023: The Great Classified Cardboard Box War! On the left side of the screen is the gilded Lake Room bathroom at Mar-a-Lago, where dozens of cardboard bankers boxes are stacked next to the toilet beneath a crystal chandelier; on the right side is Joe Naptime's Wilmington garage, where classified Ukraine and Iran folders sit in a crushed box on the floor next to his green 1967 Chevrolet Corvette Stingray!",
            nextNodeId = "act3_ch3_chandelier_defense"
        ),
        DialogueNode(
            id = "act3_ch3_chandelier_defense",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at that Mar-a-Lago bathroom photo in the indictment, folks! Have you ever seen a more luxurious crystal chandelier hanging over a shower full of bankers boxes?! Plus, bathroom doors lock from the inside! Meanwhile, when reporters asked Joe Naptime why his classified VP folders were sitting in a broken box next to lawn fertilizer in his Delaware garage, what did he say?!",
            nextNodeId = "act3_ch3_box_hub"
        ),
        DialogueNode(
            id = "act3_ch3_box_hub",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Well, Commander, it looks like both of y'all treated the National Archives like a U-Haul self-storage unit! How are we handling the Subpoena Archivists?",
            choices = listOf(
                DialogueChoice("a3c3_corvette", "Play Joe Naptime's verbatim Jan 12, 2023 excuse: 'My Corvette's in a locked garage, okay?!'", listOf("corvette", "garage", "locked", "stingray", "delaware"), "act3_ch3_corvette_node", completionFlag = "a3c3_corvette_done"),
                DialogueChoice("a3c3_vindication", "Laugh off the 'Mar-a-Lago Nuclear Codes' headline hoax!", listOf("nuclear", "codes", "vindication", "mar a lago", "spies"), "act3_ch3_nuclear_vindication", completionFlag = "a3c3_nuclear_done"),
                DialogueChoice("a3c3_battle", "Battle the Bathroom Chandelier Box Inspector & Corvette Garage Sentinel!", listOf("battle", "fight", "chandelier", "proceed", "advance"), "act3_ch3_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act3_ch3_corvette_node",
            setFlagOnEnter = "a3c3_corvette_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "*Pops up on the monitor wearing aviators (Jan 12, 2023)* 'By the way, my Corvette's in a locked garage, okay? So it's not like they're sitting out on the street!' And when Special Counsel Robert Hur's report came out, he wrote that we also found classified Afghanistan notebooks in my basement den from 1977—hey, a guy's gotta have reading material when he's waxing the Stingray!",
            nextNodeId = "act3_ch3_box_hub"
        ),
        DialogueNode(
            id = "act3_ch3_nuclear_vindication",
            setFlagOnEnter = "a3c3_nuclear_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The fake news swore I had nuclear launch codes hidden behind the golden shower curtain at Mar-a-Lago! Selling warheads at the brunch omelet station! The indictment comes out: it's old magazine covers, autographed golf pictures, and newspaper clippings I refused to give back to the National Archives because they had my face on them!",
            nextNodeId = "act3_ch3_box_hub"
        ),
        DialogueNode(
            id = "act3_ch3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THE BATHROOM CHANDELIER BOX INSPECTOR AND THE WILMINGTON CORVETTE GARAGE SENTINEL ARE CHARGING IN, BROTHER! Foghorn Kennedy, hit 'em with Plain English Cross-Exam and Call an Officer!",
            triggerBattleEncounterId = "act3_track_a_chandelier_battle",
            nextNodeId = "act3_ch3_post_battle"
        ),
        DialogueNode(
            id = "act3_ch3_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The chandelier still sparkles, our HP and MP are 100% restored, and now it's August 24, 2023—time to fly to Atlanta in Chapter 4 for the Fulton County Mugshot heard 'round the world!",
            changeSceneId = SCENE_ACT3_CH4_SUMMIT.id,
            nextNodeId = "act3_ch4_intro"
        ),

        // =========================================================================
        // ACT III (TRACK A) - CHAPTER 4: THE '215-POUND' MUGSHOT & $7.1M 'NEVER SURRENDER' MERCH (AUG 2023)
        // =========================================================================
        DialogueNode(
            id = "act3_ch4_intro",
            changeSceneId = SCENE_ACT3_CH4_SUMMIT.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is August 24, 2023 at the Fulton County Jail on Rice Street in Atlanta, Georgia. Commander America has just stepped up to the booking camera for Booking #P01135809—after filling out his own height and weight form in advance!",
            nextNodeId = "act3_ch4_mugshot_pose"
        ),
        DialogueNode(
            id = "act3_ch4_mugshot_pose",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Tilts his chin down, raises one eyebrow in a fierce Stanley Kubrick scowl as the camera flashes, and hands the clerk his self-reported booking sheet* Read those official physical stats on the Fulton County booking sheet, folks: Height: 6 foot 3! Weight: 215 POUNDS! Hair: Strawberry or Blonde! That puts me at the exact same height and weight as Baltimore Ravens All-Pro quarterback Lamar Jackson!",
            nextNodeId = "act3_ch4_mugshot_hub"
        ),
        DialogueNode(
            id = "act3_ch4_mugshot_hub",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Well now, Commander, if you weigh 215 pounds, then I'm a ballerina for the Bolshoi Theatre! And look what your merch team is doing with that mugshot before the ink is even dry!",
            choices = listOf(
                DialogueChoice("a3c4_merch", "Launch the \$7.1 Million 'NEVER SURRENDER!' Mugshot T-Shirt & Coffee Mug Blitz!", listOf("merch", "never surrender", "mugshot", "seven million", "shirt"), "act3_ch4_merch_node", completionFlag = "a3c4_merch_done"),
                DialogueChoice("a3c4_meta", "Watch Chyron McSpin panic over the 215-lb weight & drag up the 2015 \$50 Escalator Actors eight years later!", listOf("chyron", "escalator", "let it go", "eight years", "meta"), "act3_ch4_tactful_meta", completionFlag = "a3c4_meta_done"),
                DialogueChoice("a3c4_proceed", "Fly to Portland's Baskin-Robbins for the Chapter 5 Finale against Joe Naptime!", listOf("proceed", "advance", "baskin robbins", "ice cream", "finale"), "act3_ch5_baskin_intro")
            )
        ),
        DialogueNode(
            id = "act3_ch4_merch_node",
            setFlagOnEnter = "a3c4_merch_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Within ninety minutes of leaving the Fulton County Jail on August 24, 2023, we slapped my scowling booking photo onto \$34 T-shirts, \$25 coffee mugs, and \$15 beverage coolers with two giant words underneath: 'NEVER SURRENDER!' Even though a mugshot is quite literally a photograph taken while you are surrendering to the Sheriff's Office, it raised \$7.1 MILLION in seventy-two hours—the biggest single-day fundraising haul of the entire campaign!",
            nextNodeId = "act3_ch4_mugshot_hub"
        ),
        DialogueNode(
            id = "act3_ch4_tactful_meta",
            setFlagOnEnter = "a3c4_meta_done",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "*Waves a bathroom scale outside the jail* You don't weigh 215 pounds, your mugshot just funded your entire primary campaign—and... and don't forget in June 2015 you paid Extra Mile Casting actors \$50 a head to clap at your escalator!",
            nextNodeId = "act3_ch4_kennedy_roasts_meta"
        ),
        DialogueNode(
            id = "act3_ch4_kennedy_roasts_meta",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "*Shakes his head with a slow chuckle* Son, it is August of 2023. Bringing up a 2015 department-store escalator at a 2023 mugshot booking is like showing up to a Louisiana crawfish boil with a Betamax tape of M*A*S*H. Y'all cable anchors hold onto old grudges tighter than a tick on a hound dog!",
            nextNodeId = "act3_ch4_mugshot_hub"
        ),

        // =========================================================================
        // ACT III (TRACK A) - CHAPTER 5: 'ECONOMY STRONG AS HELL' & 'REPEAT THE LINE' (2022–2023 FINALE)
        // =========================================================================
        DialogueNode(
            id = "act3_ch5_baskin_intro",
            changeSceneId = SCENE_ACT3_CH5_BASKIN.id,
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside a Baskin-Robbins ice-cream parlor in Portland, Oregon! Standing at the counter while Consumer Price Index inflation sits at a 40-year high of 8.2% is Joe Naptime—holding a double-scoop chocolate chip waffle cone in one hand and reading straight off a giant glass teleprompter!",
            nextNodeId = "act3_ch5_biden_cone_lick"
        ),
        DialogueNode(
            id = "act3_ch5_biden_cone_lick",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "*Takes a massive lick of his Baskin-Robbins chocolate chip waffle cone on October 15, 2022 as reporters ask about inflation and the global economy* 'I'm not concerned about the strength of the dollar—I'm concerned about the rest of the world! Our economy is strong as hell, man! Inflation is worldwide, it's worse off everywhere else than it is in the United States! By the way, don't eat the waitstaff!'",
            nextNodeId = "act3_ch5_repeat_the_line"
        ),
        DialogueNode(
            id = "act3_ch5_repeat_the_line",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "*Turns to his glass teleprompter from July 8, 2022 and reads every single word on the screen including the speechwriter's stage instructions* 'It is noteworthy that the percentage of women who register to vote and cast a ballot is consistently higher than the percentage of the men who do so. End of quote. Repeat the line. Women are not without electoral and, or, political—let me be precise: not and/or—or political power!'",
            nextNodeId = "act3_ch5_commander_roasts_biden"
        ),
        DialogueNode(
            id = "act3_ch5_commander_roasts_biden",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Did he just read 'End of quote, repeat the line' out loud off the teleprompter while eating a chocolate chip waffle cone during 8.2% inflation?! And now he's powering up his 10-clip Real-Audio Gibberish attack: 'Truinanashabbadepressure!' and 'Badakathcare!' Everybody brace for brain-freeze!",
            nextNodeId = "act3_ch5_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act3_ch5_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE SHOWDOWN AT BASKIN-ROBBINS, BROTHER! Commander America, Little Marco, Wizard Kash, and Foghorn Kennedy vs. Joe Naptime's Real-Audio Gibberish, Double-Scoop Waffle Cone Blitz, and the 'Repeat the Line' Teleprompter! RING THE BELL!",
            triggerBattleEncounterId = "act3_track_a_biden_ice_cream_finale",
            nextNodeId = "act3_ch5_post_battle"
        ),
        DialogueNode(
            id = "act3_ch5_post_battle",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The Baskin-Robbins counter is secured! The waffle cone has been conquered as the journey marches onward to Act IV!",
            nextNodeId = "act4_ch1_intro"
        ),

        // =========================================================================
        // ACT III (TRACK B) - CHAPTER 1: CORN POP'S RUSTY RAZOR, BLONDE LEG HAIR & TOE CAGES (PLAYING AS JOE NAPTIME)
        // =========================================================================
        DialogueNode(
            id = "act3_tb_ch1_intro",
            changeSceneId = SCENE_ACT3_TB_CH1_POOL.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Summer 2021! Joe Naptime slides into the Oval Office behind a pair of dark aviators, gripping a fresh double-scoop waffle cone of Jeni's Chocolate Chip ice cream and ready to take on the world!",
            nextNodeId = "act3_tb_ch1_oprah_cones"
        ),
        DialogueNode(
            id = "act3_tb_ch1_oprah_cones",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "YOU GET A DOUBLE-SCOOP CHOCOLATE CHIP WAFFLE CONE! YOU GET A PAIR OF RAY-BAN AVIATORS! AND EVERYBODY WATCH OUT FOR CORN POP AND THOSE REHOBOTH BEACH BICYCLE TOE CAGES, HONEY!",
            nextNodeId = "act3_tb_ch1_pool_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch1_pool_hub",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Adjusts his Ray-Ban aviators beside the Wilmington swimming pool while holding a double-scoop chocolate chip waffle cone in one hand and a Trek bicycle in the other* Folks, no malarkey! Before we take a ride down the Rehoboth Beach bike trail, let me tell you about my 1962 lifeguard days at the Prices Run pool!",
            choices = listOf(
                DialogueChoice("tb3c1_cornpop", "Tell the verbatim June 2017 'Corn Pop was a bad dude' & Rusty Rainwater-Barrel Razor story!", listOf("corn pop", "bad dude", "razor", "rain barrel", "chain"), "act3_tb_ch1_cornpop_node", completionFlag = "tb3c1_corn_done"),
                DialogueChoice("tb3c1_leghair", "Tell the verbatim 'I got hairy legs that turn blonde in the sun' & Roaches story!", listOf("hairy legs", "blonde", "sun", "pool", "roaches"), "act3_tb_ch1_leghair_node", completionFlag = "tb3c1_legs_done"),
                DialogueChoice("tb3c1_bikecrash", "Ride the Trek FX bike in Rehoboth Beach & get your right foot stuck in the metal pedal toe-cage!", listOf("bike", "toe cage", "rehoboth", "bicycle", "crash"), "act3_tb_ch1_bike_node", completionFlag = "tb3c1_bike_done"),
                DialogueChoice("tb3c1_battle", "Battle Corn Pop's Rain-Barrel Razor Crew & The Treacherous Toe-Cage Bicycle!", listOf("battle", "fight", "proceed", "advance"), "act3_tb_ch1_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act3_tb_ch1_cornpop_node",
            setFlagOnEnter = "tb3c1_corn_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Points a waffle cone at the diving board (June 26, 2017 verbatim)* 'Corn Pop was a bad dude! And he ran a bunch of bad boys! And I did and back in those days—to show how things have changed—one of the things you had to use, if you used Pomade in your hair, you had to wear a bathing cap! And so he was up on the board and wouldn't listen to me. I said, \"Hey, Esther, you! Off the board, or I'll come up and drag you off!\" Well, he came off, and he said, \"I'll meet you outside!\" He was waiting for me with three guys and straight razors that they had banged on the curb or put in a rainwater barrel to get rusty! So I walked out with six feet of metal chain wrapped in a towel and told him: \"You might cut me, Corn Pop, but I'm going to wrap this chain around your head!\" And then I apologized for calling him Esther Williams and we became friends!'",
            nextNodeId = "act3_tb_ch1_pool_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch1_leghair_node",
            setFlagOnEnter = "tb3c1_legs_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Grins nostalgically into the microphone (June 26, 2017 verbatim)* 'And by the way, you know, I sit on the stand, and it'd get hot! I got a lot of—I got hairy legs that turn—that, that, that, that, that turn—uh, blonde in the sun! And the kids used to come up and reach in the pool and rub my leg down so it was straight and then watch the hair come back up again! They'd look at it! So I learned about roaches, I learned about kids jumping on my lap, and I've loved kids jumping on my lap!'Meanwhile my campaign press aides in the back row are quietly hyperventilating into paper bags!",
            nextNodeId = "act3_tb_ch1_pool_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch1_bike_node",
            setFlagOnEnter = "tb3c1_bike_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Pedals up to the press pool at Cape Henlopen State Park on June 18, 2022, squeezes the handbrakes to a dead stop, tries to pull his right foot out of the plastic-and-metal pedal cage, and tips over sideways at zero miles per hour* WHOA! *Hops back up brushing off his elbow* 'I'm good! I got my foot caught in the toe cages!' Whoever invented pedal cages on a beach cruiser owes me a pint of Jeni's Chocolate Chip!",
            nextNodeId = "act3_tb_ch1_pool_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "CORN POP JUST STEPPED OUT OF THE POOL HOUSE WITH A RAINWATER-BARREL STRAIGHT RAZOR BESIDE A ROGUE TOE-CAGE BICYCLE, HONEY! Joe Naptime, unleash Real-Audio Gibberish (`Truinanashabbadepressure!`) and Double-Scoop Waffle Cone Blitz!",
            triggerBattleEncounterId = "act3_track_b_corn_pop_battle",
            nextNodeId = "act3_tb_ch1_post_battle"
        ),
        DialogueNode(
            id = "act3_tb_ch1_post_battle",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "See that, folks? Me and Corn Pop settled it over a waffle cone, and our HP and MP are 100% restored! Now let's head to the White House teleprompter stage for Chapter 2!",
            changeSceneId = SCENE_ACT3_TB_CH2_TELEPROMPTER.id,
            nextNodeId = "act3_tb_ch2_intro"
        ),

        // =========================================================================
        // ACT III (TRACK B) - CHAPTER 2: 'END OF QUOTE, REPEAT THE LINE' & TWO VINDICATIONS (2021–2022)
        // =========================================================================
        DialogueNode(
            id = "act3_tb_ch2_intro",
            changeSceneId = SCENE_ACT3_TB_CH2_TELEPROMPTER.id,
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is July 8, 2022 inside the Roosevelt Room! The speechwriting staff typed bracketed stage directions onto the teleprompter glass—'[End of quote. Repeat the line.]'—assuming nobody would ever read the brackets out loud!",
            nextNodeId = "act3_tb_ch2_prompter_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch2_prompter_hub",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Squints at the glowing teleprompter glass while holding a spoonful of Jeni's Splendid Chocolate Chip* Look, if they put words on the glass, I'm reading every single one of 'em! Let's handle our teleprompter moments and drop our first two verified Vindication Wins!",
            choices = listOf(
                DialogueChoice("tb3c2_repeat", "Read the bracketed stage directions verbatim: 'End of quote. Repeat the line.' (+ Andrews Wind Stairs!)", listOf("repeat the line", "end of quote", "teleprompter", "stairs", "wind"), "act3_tb_ch2_repeat_node", completionFlag = "tb3c2_rep_done"),
                DialogueChoice("tb3c2_vatican", "Laugh off the fake Vatican bathroom rumors!", listOf("vatican", "pope", "pants", "rumor", "vindication"), "act3_tb_ch2_vatican_vindication", completionFlag = "tb3c2_vat_done"),
                DialogueChoice("tb3c2_israel", "Expose the cropped 'Asleep with Israel's PM' clip!", listOf("asleep", "israel", "naftali bennett", "cspan", "cropped"), "act3_tb_ch2_israel_vindication", completionFlag = "tb3c2_isr_done"),
                DialogueChoice("tb3c2_proceed", "Head to Portland's Baskin-Robbins for Chapter 3 against Senator Foghorn Kennedy!", listOf("proceed", "advance", "baskin robbins", "kennedy", "ice cream"), "act3_tb_ch3_intro")
            )
        ),
        DialogueNode(
            id = "act3_tb_ch2_repeat_node",
            setFlagOnEnter = "tb3c2_rep_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Leans into the podium on July 8, 2022* 'It is noteworthy that the percentage of women who register to vote and cast a ballot is consistently higher than the percentage of the men who do so. End of quote. Repeat the line.' Hey, when the teleprompter tells you to repeat the line, you tell the audience it told you to repeat the line! Just like on March 19, 2021 when I tripped three times going UP the Air Force One stairs because the wind at Andrews was blowing against my knees!",
            nextNodeId = "act3_tb_ch2_prompter_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch2_vatican_vindication",
            setFlagOnEnter = "tb3c2_vat_done",
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Look, folks on Twitter started a rumor that Joe had a bathroom emergency with the Pope because their meeting went seventy-five minutes! The man was having tea with the Holy Father, discussing world peace and looking at rosaries! You think the Bishop of Rome is timing bathroom breaks with a stopwatch?! Malarkey!",
            nextNodeId = "act3_tb_ch2_prompter_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch2_israel_vindication",
            setFlagOnEnter = "tb3c2_isr_done",
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Cable channels cropped a ten-second video of Joe looking down at his notepad and claimed he fell fast asleep on live television next to the Prime Minister of Israel! The second Bennett stopped talking, Joe looked up and answered him! Joe rests his eyes, folks, but the radar is always spinning!",
            nextNodeId = "act3_tb_ch2_prompter_hub"
        ),

        // =========================================================================
        // ACT III (TRACK B) - CHAPTER 3: 'ECONOMY STRONG AS HELL' VS. FOGHORN KENNEDY (2022–2023)
        // =========================================================================
        DialogueNode(
            id = "act3_tb_ch3_intro",
            changeSceneId = SCENE_ACT3_TB_CH3_BASKIN.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is October 15, 2022 inside a Baskin-Robbins in Portland, Oregon! Inflation just hit a 40-year high of 8.2%, and Joe Naptime has stepped up to the glass freezer case to order his signature double-scoop chocolate chip waffle cone!",
            nextNodeId = "act3_tb_ch3_baskin_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch3_baskin_hub",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Holds up a towering double-scoop chocolate chip waffle cone* As I told the crowd in Cleveland on May 27, 2021: 'My name is Joe Biden. I'm Dr. Jill Biden's husband. And I eat Jeni's Chocolate Chip ice cream! I came down because I heard there was chocolate chip ice cream! By the way, I have a whole refrigerator full upstairs!' How do we handle the reporters and our next two Vindication Wins before Foghorn Kennedy walks in?",
            choices = listOf(
                DialogueChoice("tb3c3_strong", "Lick the waffle cone & tell reporters: 'Our economy is strong as hell, man!'", listOf("economy", "strong as hell", "baskin robbins", "lick", "inflation"), "act3_tb_ch3_strong_node", completionFlag = "tb3c3_str_done"),
                DialogueChoice("tb3c3_vind34", "Laugh off the 'Two Beers a Week' red meat ban & G7 skydiver rumors!", listOf("two beers", "red meat", "skydiver", "g7", "vindication"), "act3_tb_ch3_vindications_node", completionFlag = "tb3c3_vind_done"),
                DialogueChoice("tb3c3_kennedy", "Confront Senator Foghorn Kennedy strolling into the ice-cream shop!", listOf("kennedy", "foghorn", "battle", "fight", "proceed"), "act3_tb_ch3_kennedy_ambush")
            )
        ),
        DialogueNode(
            id = "act3_tb_ch3_strong_node",
            setFlagOnEnter = "tb3c3_str_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Takes a slow, deliberate lick of the chocolate chip waffle cone while reporters shout about 8.2% inflation (Oct 15, 2022)* 'I'm not concerned about the strength of the dollar—I'm concerned about the rest of the world! Our economy is strong as hell, man!' Look, nothing reassures working families paying \$5 for a dozen eggs quite like watching the President demolish a waffle cone at Baskin-Robbins!",
            nextNodeId = "act3_tb_ch3_baskin_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch3_vindications_node",
            setFlagOnEnter = "tb3c3_vind_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "First, cable news screamed that my climate plan was going to ban red meat and limit every American to one hamburger a month and two beers a week! Two beers a week?! Folks in Scranton would throw me in the river! Second, they cropped a video at the G7 summit to make it look like I was wandering off into the woods—I was walking over to give a thumbs-up to an Italian skydiver who just dropped out of an airplane! Give me a break!",
            nextNodeId = "act3_tb_ch3_baskin_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch3_kennedy_ambush",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.RIGHT,
            text = "*Saunters into the Baskin-Robbins adjusting his spectacles* Well now, Mr. President, watching you explain inflation over a waffle cone is like watching a mule try to do calculus! Look, your budget looks like somebody gave it a weed-whacker haircut—and if you hate fiscal restraint, the next time you're in trouble, call a crackhead!",
            nextNodeId = "act3_tb_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act3_tb_ch3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "SENATOR FOGHORN KENNEDY JUST BROUGHT HIS OXFORD LAW DEGREE AND HIS FOLKSY ONE-LINERS INTO BASKIN-ROBBINS, EVERYBODY! Joe Naptime, hit him with Jeni's Chocolate Chip Brain-Freeze and 'Economy Strong As Hell' Lick!",
            triggerBattleEncounterId = "act3_track_b_foghorn_battle",
            nextNodeId = "act3_tb_ch3_post_battle"
        ),
        DialogueNode(
            id = "act3_tb_ch3_post_battle",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "Take that Oxford degree back to Baton Rouge, John! Our HP and MP are 100% restored, so let's head back to Wilmington for Chapter 4: My locked 1967 Corvette garage and the Real-Audio Gibberish Symphony!",
            changeSceneId = SCENE_ACT3_TB_CH4_GARAGE.id,
            nextNodeId = "act3_tb_ch4_intro"
        ),

        // =========================================================================
        // ACT III (TRACK B) - CHAPTER 4: THE CORVETTE GARAGE & REAL-AUDIO GIBBERISH SYMPHONY (2023–2024)
        // =========================================================================
        DialogueNode(
            id = "act3_tb_ch4_intro",
            changeSceneId = SCENE_ACT3_TB_CH4_GARAGE.id,
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is January 12, 2023 inside Joe Naptime's Wilmington, Delaware garage! Parked in the center is his prized green 1967 Chevrolet Corvette Stingray, and sitting on the concrete floor right beside the rear tire is a crushed cardboard box containing classified documents from his Vice Presidential days!",
            nextNodeId = "act3_tb_ch4_garage_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch4_garage_hub",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Pats the fender of his 1967 Corvette Stingray while holding a pint of Jeni's Chocolate Chip* Listen, when Peter Doocy asked me why classified documents were sitting next to my Corvette, I gave him the most American answer in history! Let's review the Corvette defense, test our 10-clip Real-Audio Gibberish vocal warmups, and drop Vindication Win #5!",
            choices = listOf(
                DialogueChoice("tb3c4_corvette", "Deliver the verbatim Jan 12, 2023 quote: 'My Corvette's in a locked garage, okay? So it's not like they're sitting out on the street!'", listOf("corvette", "locked garage", "street", "doocy", "classified"), "act3_tb_ch4_corvette_node", completionFlag = "tb3c4_corv_done"),
                DialogueChoice("tb3c4_gibberish", "Test the 10-Clip Real-Audio Gibberish Move: Truinanashabbadepressure, Badakathcare & foothills of the Himalayas!", listOf("gibberish", "truinanashabbadepressure", "badakathcare", "himalayas", "audio"), "act3_tb_ch4_gibberish_node", completionFlag = "tb3c4_gib_done"),
                DialogueChoice("tb3c4_vind5", "Expose the 'Mar-a-Lago Hit Squad' paperwork hysteria!", listOf("fbi", "boilerplate", "mar a lago", "vindication", "warrant"), "act3_tb_ch4_vindication5_node", completionFlag = "tb3c4_vind5_done"),
                DialogueChoice("tb3c4_meta", "Watch Kash Subpoena peek into the garage & try to bring up Hillary's 2015 cloth wipe in 2023!", listOf("kash", "cloth", "let it go", "meta", "proceed"), "act3_tb_ch4_meta_callback")
            )
        ),
        DialogueNode(
            id = "act3_tb_ch4_corvette_node",
            setFlagOnEnter = "tb3c4_corv_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Lowers his aviators on January 12, 2023* 'By the way, my Corvette's in a locked garage, okay? So it's not like they're sitting out on the street! People know I take classified material seriously!' Okay, sure, Special Counsel Robert Hur later included a photo of the battered cardboard box sitting open next to a dog crate and a ladder—and described me as a 'sympathetic, well-meaning, elderly man with a poor memory'—which made me so mad I held an emergency 8:00 PM press conference where I immediately mixed up the President of Egypt with the President of Mexico!",
            nextNodeId = "act3_tb_ch4_garage_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch4_gibberish_node",
            setFlagOnEnter = "tb3c4_gib_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Steps up to the microphone and triggers his Real-Audio Gibberish sampler* Listen to these 100% real, unedited dictionary expansions, Jack: (1) 'I'll lead an effective strategy to mobilize truinanashabbadepressure!' (2) 'Barack and I think it's a right to have badakathcare!' (3) 'America is a nation that can be defined in a single word: Asufutimaehaehfutbw—excuse me, at the foothills of the Himalayas with Xi Jinping!' (4) 'You're a lying dog-faced pony soldier!' (5) 'All men and women are created by the—go—you know—you know the thing!' and (6) 'We finally beat Medicare!' Try translating THAT on a debate stage!",
            nextNodeId = "act3_tb_ch4_garage_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch4_vindication5_node",
            setFlagOnEnter = "tb3c4_vind5_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "They sent out emergency fundraising emails claiming I dispatched an FBI hit squad to Mar-a-Lago! It was standard DOJ paperwork! They put the exact same form on the warrant for my own garage in Delaware! If that was a hit squad, they were sent to take out my lawn mower!",
            nextNodeId = "act3_tb_ch4_garage_hub"
        ),
        DialogueNode(
            id = "act3_tb_ch4_meta_callback",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.RIGHT,
            text = "*Pokes his head through the Wilmington garage window wearing his Wizard Kash cape* Aha! Classified folders next to a 1967 Corvette! Which reminds me—in 2015 Hillary wiped 33,000 Chappaqua emails 'with a cloth or something'!",
            nextNodeId = "act3_tb_ch4_oprah_roasts_meta"
        ),
        DialogueNode(
            id = "act3_tb_ch4_oprah_roasts_meta",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "*Speaks into her golden megaphone* BOY, IT IS 2023 AND YOU ARE STANDING IN JOE BIDEN'S GARAGE IN DELAWARE! Hillary hasn't been on a ballot in seven years and you STILL brought a microfiber cloth to a Corvette inspection! Now let's head to the grand finale!",
            changeSceneId = SCENE_ACT3_TB_CH5_MUGSHOT.id,
            nextNodeId = "act3_tb_ch5_intro"
        ),

        // =========================================================================
        // ACT III (TRACK B) - CHAPTER 5: THE '215-LB' MUGSHOT & PASSING THE COCONUT TO KAMALA (2023–2024 FINALE)
        // =========================================================================
        DialogueNode(
            id = "act3_tb_ch5_intro",
            changeSceneId = SCENE_ACT3_TB_CH5_MUGSHOT.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are at the intersection of the Mar-a-Lago Bathroom Chandelier Vault and the Fulton County Booking Desk! Commander America is standing in front of thirty cardboard bankers boxes stacked in a gilded shower stall while handing out 'NEVER SURRENDER!' mugshot coffee mugs and claiming he weighs 215 pounds!",
            nextNodeId = "act3_tb_ch5_confrontation"
        ),
        DialogueNode(
            id = "act3_tb_ch5_confrontation",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "C'mon, man! Look at this malarkey: first you stacked thirty boxes of government records on the shower floor under a crystal bathroom chandelier at Mar-a-Lago, then on August 24, 2023 you walked into the Fulton County Jail, wrote down on the booking form that you're 6 foot 3 and 215 POUNDS—the exact weight of NFL quarterback Lamar Jackson—and sold \$7.1 million worth of T-shirts that say 'NEVER SURRENDER!' right underneath a photo of you literally surrendering at a police station!",
            nextNodeId = "act3_tb_ch5_commander_reply"
        ),
        DialogueNode(
            id = "act3_tb_ch5_commander_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.RIGHT,
            text = "That mugshot is the greatest work of art since the Mona Lisa, Joe—and 215 pounds is my fighting weight after eighteen holes at Bedminster! Meanwhile, you tripped three times going UP the stairs of Air Force One, tipped over on a stationary bicycle in Rehoboth Beach, read 'End of quote, repeat the line' off the teleprompter, and kept your classified folders in a crushed box next to your 1967 Corvette! Let's settle Act III right here!",
            nextNodeId = "act3_tb_ch5_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act3_tb_ch5_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE GRAND FINALE, EVERYBODY! Joe Naptime's Real-Audio Gibberish & Double-Scoop Ice-Cream Cryomancy vs. Commander America's 215-Pound Mugshot, the Mar-a-Lago Bathroom Chandelier Golem, and the \$7.1M Merch Press! RING THE BELL!",
            triggerBattleEncounterId = "act3_track_b_mugshot_finale",
            nextNodeId = "act3_tb_ch5_post_battle"
        ),
        DialogueNode(
            id = "act3_tb_ch5_post_battle",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.LEFT,
            text = "*Finishes his double-scoop chocolate chip waffle cone in Rehoboth Beach on Sunday afternoon, July 21, 2024, and taps 'Post' on X at 1:46 PM* Folks, after we finally beat Medicare on that June 27 debate stage—and George Clooney wrote an op-ed three weeks after I raised \$28 million at his Hollywood gala—it's time to pass the torch for Act IV to Vice President Kamala Coconut!",
            nextNodeId = "act3_tb_ch5_kamala_handoff"
        ),
        DialogueNode(
            id = "act3_tb_ch5_kamala_handoff",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "*Steps onto the stage laughing uproariously while gesturing with three overlapping circles* Ha-ha-ha-ha! Thank you, Joe! You think you just fell out of a coconut tree?! You exist in the context of all in which you live and what came before you! In Act IV, get ready for Venn diagrams, yellow school buses, 'The significance of the passage of time,' and JD Pants ordering 'whatever makes sense' at a Georgia donut shop! Let's get to work!",
            nextNodeId = "act4_tb_ch1_intro"
        ),

        // =========================================================================
        // ACT IV (TRACK A) - CHAPTER 1: ENTER JD PANTS & 'WHATEVER MAKES SENSE' DONUTS (AUG 2024)
        // =========================================================================
        DialogueNode(
            id = "act4_ch1_intro",
            changeSceneId = SCENE_ACT4_CH1_FISA.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to Act IV (2024): Five chapters of Coconut Trees, Venn Diagrams, Donut Shop Interrogations, and McDonald's Fry Baskets! It is August 22, 2024 inside Holt's Sweet Shoppe in Valdosta, Georgia, where your newest party companion—JD Pants—is attempting human small talk with a bakery clerk in front of twenty television cameras!",
            nextNodeId = "act4_ch1_vance_donuts"
        ),
        DialogueNode(
            id = "act4_ch1_vance_donuts",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "*Stands stiffly in a suit in front of the glass pastry case as the bakery worker asks not to be on camera* 'Okay, she doesn't want to be on film, guys, so just cut her out of anything. I'm JD Vance, I'm running for Vice President, good to see you.' And she just says: 'Okay.' So I try to break the ice: 'How long has this place been here?' ('About four years.') 'Okay, good. How long have you worked here?' ('A little over two years.') 'Okay, good... Just, uh, a lot of glazed here, some sprinkle stuff, some of these cinnamon rolls—just whatever makes sense!'",
            nextNodeId = "act4_ch1_donut_hub"
        ),
        // Legacy compatibility node
        DialogueNode(
            id = "act4_kash_joins",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Every binder is declassified! And welcome to the party, JD Pants!",
            nextNodeId = "act4_ch1_donut_hub"
        ),
        DialogueNode(
            id = "act4_ch1_donut_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Hold on, JD—you walked into a Georgia donut shop and ordered 'some sprinkle stuff, whatever makes sense' like an extraterrestrial reading a Wikipedia article about breakfast pastries?! Let's inspect your 2024 campaign trail highlights and your biggest Vindication Win!",
            choices = listOf(
                DialogueChoice("a4c1_swiss", "Replay the Aug 19, 2024 South Philly Pat's King of Steaks 'Why do you hate Swiss cheese?!' moment!", listOf("swiss cheese", "cheesesteak", "philly", "pats", "diet mountain dew"), "act4_ch1_swiss_node", completionFlag = "a4c1_swiss_done"),
                DialogueChoice("a4c1_rules", "Deliver the Oct 1, 2024 CBS VP Debate classic: 'The rules were that you guys weren't going to fact-check!'", listOf("rules", "fact check", "debate", "cbs", "moderators"), "act4_ch1_rules_node", completionFlag = "a4c1_rules_done"),
                DialogueChoice("a4c1_couch", "Laugh off the fake 'Couch Cushion' memoir rumor!", listOf("couch", "hillbilly elegy", "vindication", "associated press", "hoax"), "act4_ch1_couch_vindication", completionFlag = "a4c1_couch_done"),
                DialogueChoice("a4c1_battle", "Test JD Pants in combat against the 'Whatever Makes Sense' Glazed Case & Swiss-Cheese Heckler!", listOf("battle", "fight", "donuts", "proceed", "advance"), "act4_ch1_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act4_ch1_swiss_node",
            setFlagOnEnter = "a4c1_swiss_done",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "*Holds a can of Diet Mountain Dew outside Pat's King of Steaks in South Philadelphia (Aug 19, 2024)* Look, after John Kerry famously ruined his campaign in 2004 by asking for Swiss cheese at Pat's, I walked up to the window and asked the grill cook: 'I don't know, why don't you all dislike Swiss cheese? What's the story?!' before ordering a Whiz wit! Hey, at least I didn't drink a soy latte—I had a Diet Mountain Dew today, and the left is probably gonna call that racist too!",
            nextNodeId = "act4_ch1_donut_hub"
        ),
        DialogueNode(
            id = "act4_ch1_rules_node",
            setFlagOnEnter = "a4c1_rules_done",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "*Turns calmly to the CBS debate moderators on October 1, 2024 before they cut his microphone* 'Margaret, the rules were that you guys weren't going to fact-check, and since you're fact-checking me, I think it's important to say what's actually going on!' Only in 2024 can you win a debate round by politely scolding the referee for checking the rulebook!",
            nextNodeId = "act4_ch1_donut_hub"
        ),
        DialogueNode(
            id = "act4_ch1_couch_vindication",
            setFlagOnEnter = "a4c1_couch_done",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "An internet prankster claimed I confessed to romantic relations with a living room sofa in my book! It went so viral that the Associated Press had to publish an official bulletin: 'No, JD Vance did not touch the couch.' Pages 179 to 181 are about working in an Ohio textbook warehouse, folks! I like a comfortable sectional, but strictly for sitting!",
            nextNodeId = "act4_ch1_donut_hub"
        ),
        DialogueNode(
            id = "act4_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "JD PANTS IS IN THE ACTIVE PARTY, BROTHER! Ordering 'some sprinkle stuff, whatever makes sense' just triggered the Glazed Donut Case and the South Philly Swiss-Cheese Heckler! Unleash Debate Dismantle and Appalachian Slam!",
            triggerBattleEncounterId = "act4_track_a_donut_battle",
            nextNodeId = "act4_ch1_post_battle"
        ),
        DialogueNode(
            id = "act4_ch1_post_battle",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Okay, good! We boxed up two dozen glazed donuts, our HP and MP are back at 100%, and now let's head to Chapter 2—where Kamala Coconut is explaining to Lester Holt why she hasn't visited Europe!",
            changeSceneId = SCENE_ACT4_CH2_BINDER.id,
            nextNodeId = "act4_ch2_intro"
        ),

        // =========================================================================
        // ACT IV (TRACK A) - CHAPTER 2: 'AND I HAVEN'T BEEN TO EUROPE!' & UKRAINE PRESCHOOL (2021–2024)
        // =========================================================================
        DialogueNode(
            id = "act4_ch2_intro",
            changeSceneId = SCENE_ACT4_CH2_BINDER.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "After Sunday afternoon July 21, 2024—when Joe Naptime dropped out via a 1:46 PM X post from Rehoboth Beach—Vice President Kamala Coconut inherited the top of the ticket! Let's review her greatest geographic and chronological masterpieces!",
            nextNodeId = "act4_ch2_lester_holt"
        ),
        DialogueNode(
            id = "act4_ch2_lester_holt",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "*Sits across from NBC anchor Lester Holt on June 8, 2021 when he asks why she still hasn't visited the US-Mexico border as Border Czar* 'We've been to the border! We've been to the border!' And when Lester looks at me and says 'You haven't been to the border,' I give him the ultimate geographic checkmate: 'And I haven't been to Europe! *Laughs* And I mean, I don't... understand the point that you're making!'",
            nextNodeId = "act4_ch2_border_hub"
        ),
        DialogueNode(
            id = "act4_ch2_border_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Listen to that logic, folks: 'Why haven't you been to the southern border?' 'And I haven't been to Europe!'Wait until you hear how she explains the map of Eastern Europe and the concept of linear time!",
            choices = listOf(
                DialogueChoice("a4c2_ukraine", "Play her March 21, 2022 radio breakdown: 'Ukraine is a country in Europe. Russia is a bigger country.'", listOf("ukraine", "russia", "bigger country", "europe", "radio"), "act4_ch2_ukraine_node", completionFlag = "a4c2_ukraine_done"),
                DialogueChoice("a4c2_passage", "Play her March 2022 Sunset, Louisiana speech repeating 'the significance of the passage of time' 4 times in 32 seconds!", listOf("passage of time", "significance", "four times", "louisiana", "time"), "act4_ch2_passage_node", completionFlag = "a4c2_passage_done"),
                DialogueChoice("a4c2_proceed", "Follow the overlapping Venn diagrams and yellow school buses into Chapter 3!", listOf("proceed", "advance", "venn", "school bus", "chapter 3"), "act4_ch3_intro")
            )
        ),
        DialogueNode(
            id = "act4_ch2_ukraine_node",
            setFlagOnEnter = "a4c2_ukraine_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "*Leans into The Morning Hustle radio microphone on March 21, 2022 to break down international affairs in layman's terms* 'So, Ukraine is a country in Europe. It exists next to another country called Russia. Russia is a bigger country. Russia is a powerful country. Russia decided to invade a smaller country called Ukraine. So, basically, that's wrong!'",
            nextNodeId = "act4_ch2_border_hub"
        ),
        DialogueNode(
            id = "act4_ch2_passage_node",
            setFlagOnEnter = "a4c2_passage_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "*Nods sagely at the podium in Sunset, Louisiana (March 21, 2022)* 'The governor and I, and we were all doing a tour of the library here and talking about the significance of the passage of time, right, the significance of the passage of time. So, when you think about it, there is great significance to the passage of time in terms of what we need to do to lay these wires. What we need to do to create these jobs. And there is such great significance to the passage of time when we think about a day in the life of our children!' Four times in thirty-two seconds—new world record!",
            nextNodeId = "act4_ch2_border_hub"
        ),

        // =========================================================================
        // ACT IV (TRACK A) - CHAPTER 3: VENN DIAGRAMS, YELLOW SCHOOL BUSES & '32 DAYS!' (2022–2024)
        // =========================================================================
        DialogueNode(
            id = "act4_ch3_intro",
            changeSceneId = SCENE_ACT4_CH3_COURT.id,
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are at the Electric Yellow School Bus Depot, where three glowing Venn diagram circles are intersecting above a teleprompter in Flint, Michigan that just froze on the words '32 Days!'",
            nextNodeId = "act4_ch3_bus_venn_hub"
        ),
        DialogueNode(
            id = "act4_ch3_bus_venn_hub",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "*Raises both hands in front of a bright yellow school bus in Seattle (Oct 2022)* 'Who doesn't love a yellow school bus, right?! Can you raise your hand if you love a yellow school bus, right?! Just, there's something about the—and most of us, many of us, went to school on the yellow school bus, right?! And it's part of our experience growing up!'",
            choices = listOf(
                DialogueChoice("a4c3_venn", "Listen to Kamala's verbatim tribute to Three-Circle Venn Diagrams & the Flint '32 Days!' Teleprompter Freeze!", listOf("venn", "three circles", "32 days", "teleprompter", "flint"), "act4_ch3_venn_node", completionFlag = "a4c3_venn_done"),
                DialogueChoice("a4c3_bloodbath", "Roast the 'Bloodbath' auto tariff headline panic!", listOf("bloodbath", "auto industry", "tariffs", "vindication", "mexico"), "act4_ch3_bloodbath_vindication", completionFlag = "a4c3_blood_done"),
                DialogueChoice("a4c3_battle", "Battle the Yellow School Bus Evangelist & 32-Day Teleprompter Glitch!", listOf("battle", "fight", "bus", "proceed", "advance"), "act4_ch3_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act4_ch3_venn_node",
            setFlagOnEnter = "a4c3_venn_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "*Draws three overlapping circles in the air* 'Remember Venn diagrams, those three circles, right?! I love Venn diagrams! I really do, I love Venn diagrams! I'm always asking my staff, \"Is there a Venn diagram for this?\"' And when my teleprompter briefly went dark in Flint, Michigan on October 4, 2024: 'So 32 days... 32 days... 32 days! Okay, we got a little—32 days!'",
            nextNodeId = "act4_ch3_bus_venn_hub"
        ),
        DialogueNode(
            id = "act4_ch3_bloodbath_vindication",
            setFlagOnEnter = "a4c3_blood_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "I told an auto rally in Ohio that if we don't put tariffs on foreign cars, the auto industry is going to suffer an economic bloodbath! The next morning, the fake news had red sirens flashing: 'TRUMP THREATENS CIVIL WAR WITH TANKS!' I was talking about hatchbacks, folks! Subcompact sedans! They lost their minds for forty-eight hours over a Dodge Dart!",
            nextNodeId = "act4_ch3_bus_venn_hub"
        ),
        DialogueNode(
            id = "act4_ch3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "HERE COMES THE ELECTRIC YELLOW SCHOOL BUS AND THE THREE OVERLAPPING VENN CIRCLES, BROTHER! Clear the depot so we can get to our shift at the McDonald's fry station!",
            triggerBattleEncounterId = "act6_ch3_bus_battle",
            nextNodeId = "act4_ch3_post_battle"
        ),
        DialogueNode(
            id = "act4_ch3_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The yellow school bus is parked, our HP and MP are 100% restored, and now it's October 20, 2024—time to tie on a McDonald's apron and a neon-orange garbage truck vest in Chapter 4!",
            changeSceneId = SCENE_ACT4_CH4_HEARING.id,
            nextNodeId = "act4_ch4_intro"
        ),

        // =========================================================================
        // ACT IV (TRACK A) - CHAPTER 4: THE MCDONALD'S FRY APRON & ORANGE GARBAGE VEST (OCT 2024)
        // =========================================================================
        DialogueNode(
            id = "act4_ch4_intro",
            changeSceneId = SCENE_ACT4_CH4_HEARING.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is October 2024—the peak of campaign photo-op theater! First, on October 20 in Feasterville-Trevose, Pennsylvania, Commander America takes off his suit jacket, ties a yellow-trimmed McDonald's apron over his white dress shirt and red silk tie, and steps up to the deep fryer!",
            nextNodeId = "act4_ch4_fry_station"
        ),
        DialogueNode(
            id = "act4_ch4_fry_station",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Shakes a metal salt shaker over a sizzling basket of golden McDonald's French fries and uses the stainless-steel fry scoop to fill red cartons* Look at the wrist action on that salt shaker, folks! Remember, I'm a lifelong germaphobe—I love that you never touch the fry with your bare hands! And as I told the reporters outside the drive-thru window: 'I've now worked for 15 minutes more than Kamala at McDonald's!'",
            nextNodeId = "act4_ch4_photo_op_hub"
        ),
        DialogueNode(
            id = "act4_ch4_photo_op_hub",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Between the Pennsylvania fry station and the Green Bay garbage truck tarmac ten days later, we have mastered every blue-collar uniform in America! What are we checking out next?",
            choices = listOf(
                DialogueChoice("a4c4_garbage", "Climb into the Oct 30, 2024 Green Bay Mack Garbage Truck in a Neon-Orange Reflective Safety Vest!", listOf("garbage truck", "orange vest", "green bay", "wisconsin", "vest"), "act4_ch4_garbage_node", completionFlag = "a4c4_garb_done"),
                DialogueChoice("a4c4_meta", "Watch Chyron McSpin pull up to the McDonald's drive-thru & complain about the 2016 Trump Tower Taco Bowl!", listOf("chyron", "taco bowl", "let it go", "meta", "drive thru"), "act4_ch4_tactful_meta", completionFlag = "a4c4_meta_done"),
                DialogueChoice("a4c4_proceed", "Head to the Coconut Grove for the Chapter 5 Act IV Finale against Kamala Coconut!", listOf("proceed", "advance", "coconut", "finale", "chapter 5"), "act4_ch5_coconut_intro")
            )
        ),
        DialogueNode(
            id = "act4_ch4_garbage_node",
            setFlagOnEnter = "a4c4_garb_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "*Pulls on a blinding neon-orange reflective sanitation vest in Green Bay, Wisconsin on October 30, 2024 and climbs into the passenger seat of a massive white garbage truck* 'How do you like my garbage truck?! This truck is in honor of Kamala and Joe Biden!' And I loved the neon-orange safety vest so much that I left it on over my dress shirt for the entire 85-minute arena speech afterward!",
            nextNodeId = "act4_ch4_photo_op_hub"
        ),
        DialogueNode(
            id = "act4_ch4_tactful_meta",
            setFlagOnEnter = "a4c4_meta_done",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "*Sticks his microphone through the McDonald's drive-thru window* AHA! The franchise owner closed the parking lot for Secret Service screening so those drive-thru cars were pre-screened! And furthermore, on May 5, 2016 you ate a Cinco de Mayo Taco Bowl over a 1990s newspaper photo of Marla Maples!",
            nextNodeId = "act4_ch4_vance_roasts_meta"
        ),
        DialogueNode(
            id = "act4_ch4_vance_roasts_meta",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "*Leans out the drive-thru window holding a bag of fries* Chyron, how long have you worked at that network? Eight years? Okay, good. Bringing up a 2016 taco bowl at a 2024 McDonald's drive-thru is why nobody watches cable news anymore. Just take some fries, some sprinkle stuff, whatever makes sense!",
            nextNodeId = "act4_ch4_photo_op_hub"
        ),

        // =========================================================================
        // ACT IV (TRACK A) - CHAPTER 5: FALLING OUT OF A COCONUT TREE & 'UNBURDENED' (NOV 2024 FINALE)
        // =========================================================================
        DialogueNode(
            id = "act4_ch5_coconut_intro",
            changeSceneId = SCENE_ACT4_CH5_COCONUT_FINALE.id,
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We have reached the heart of the Coconut Grove! Standing atop the Unburdened Dais is Vice President Kamala Coconut—ready to drop the full philosophical weight of what can be, unburdened by what has been!",
            nextNodeId = "act4_ch5_kamala_speech"
        ),
        DialogueNode(
            id = "act4_ch5_kamala_speech",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "*Throw her head back with a booming laugh before turning deadly serious (May 10, 2023 verbatim)* 'My mother used to—she would give us a hard time sometimes, and she would say to us, \"I don't know what's wrong with you young people. You think you just fell out of a coconut tree?!\" *Laughs* You exist in the context of all in which you live and what came before you! And together, we will see what can be, unburdened by what has been!'",
            nextNodeId = "act4_ch5_commander_reply"
        ),
        DialogueNode(
            id = "act4_ch5_commander_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "We have all Five Party Heroes unlocked, folks—me in my McDonald's fry apron, Little Marco with his water bottle, Wizard Kash with his 4-page memo, Foghorn Kennedy with his Oxford drawl, and JD Pants with a box of 'whatever makes sense' glazed donuts! Let's unburden this coconut tree right now!",
            nextNodeId = "act4_ch5_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act4_ch5_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE MAIN EVENT, BROTHER! The Full 5-Hero Commander America Party vs. Kamala Coconut's Coconut Tree Context Drop, Triple-Circle Venn Diagram, and 'Unburdened By What Has Been'! RING THE BELL!",
            triggerBattleEncounterId = "act6_kamala_battle",
            nextNodeId = "act4_ch5_post_battle"
        ),
        DialogueNode(
            id = "act4_ch5_post_battle",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The Coconut Grove is cleared! The stage is set for the Grand Finale of Act V!",
            nextNodeId = "act5_ch1_intro"
        ),

        // =========================================================================
        // ACT IV (TRACK B) - CHAPTER 1: FALLING OUT OF A COCONUT TREE & VENN DIAGRAMS (PLAYING AS KAMALA COCONUT)
        // =========================================================================
        DialogueNode(
            id = "act4_tb_ch1_intro",
            changeSceneId = SCENE_ACT4_TB_CH1_COCONUT.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Sunday afternoon, July 21, 2024! Joe Naptime drops out from Rehoboth Beach, and Vice President Kamala Coconut takes the mic, completely unburdened by what has been!",
            nextNodeId = "act4_tb_ch1_oprah_townhall"
        ),
        DialogueNode(
            id = "act4_tb_ch1_oprah_townhall",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WELCOME TO OUR STAR-STUDDED LIVESTREAM TOWN HALL, HONEY! YOU GET A COCONUT TREE! YOU GET A THREE-CIRCLE VENN DIAGRAM! AND EVERYBODY IS UNBURDENED BY WHAT HAS BEEN!",
            nextNodeId = "act4_tb_ch1_coconut_hub"
        ),
        DialogueNode(
            id = "act4_tb_ch1_coconut_hub",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "*Stands beneath a swaying coconut palm holding a yellow school bus model and three glowing Venn diagram rings* Ha-ha-ha! Look, everything exists in the context of all in which you live and what came before you! How should we open our 107-day sprint and drop our first Vindication Win?",
            choices = listOf(
                DialogueChoice("tb4c1_coconut", "Deliver the verbatim May 10, 2023 'You think you just fell out of a coconut tree?!' & 'Unburdened' combo!", listOf("coconut tree", "unburdened", "context", "mother", "what can be"), "act4_tb_ch1_coconut_node", completionFlag = "tb4c1_coco_done"),
                DialogueChoice("tb4c1_montel", "Laugh off the Montel Williams red-carpet rumors!", listOf("montel", "williams", "vindication", "red carpet", "2001"), "act4_tb_ch1_montel_vindication", completionFlag = "tb4c1_mon_done"),
                DialogueChoice("tb4c1_battle", "Unleash Coconut Tree Context Drop & Venn Diagrams on the Uncontextualized Pundits!", listOf("battle", "fight", "proceed", "advance"), "act4_tb_ch1_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act4_tb_ch1_coconut_node",
            setFlagOnEnter = "tb4c1_coco_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "*Laughs heartily at the White House swearing-in ceremony (May 10, 2023)* 'My mother used to—she would give us a hard time sometimes, and she would say to us, \"I don't know what's wrong with you young people. You think you just fell out of a coconut tree?!\" *Laughs* You exist in the context of all in which you live and what came before you!' And when the GOP tried to make that clip an attack ad in July 2024, Gen-Z turned it into fifty million brat-green Charli XCX remixes overnight!",
            nextNodeId = "act4_tb_ch1_coconut_hub"
        ),
        DialogueNode(
            id = "act4_tb_ch1_montel_vindication",
            setFlagOnEnter = "tb4c1_mon_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "Conspiracy accounts claimed I got a political appointment from Montel Williams in 2001! Honey, Montel and I dated twenty-three years ago, walked a red carpet in Hollywood, and broke up like normal adults! There was no secret payoff, no scandal—just good old-fashioned early-2000s red carpet fashion!",
            nextNodeId = "act4_tb_ch1_coconut_hub"
        ),
        DialogueNode(
            id = "act4_tb_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "UNCONTEXTUALIZED PUNDITS AND A 32-DAY TELEPROMPTER GLITCH ARE BLOCKING THE STAGE, HONEY! Kamala Coconut, trap them in the Triple-Circle Venn Diagram!",
            triggerBattleEncounterId = "act4_track_b_venn_critics_battle",
            nextNodeId = "act4_tb_ch1_post_battle"
        ),
        DialogueNode(
            id = "act4_tb_ch1_post_battle",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "They have officially been unburdened by what has been, and our HP and MP are back at 100%! Now let's head to Valdosta, Georgia in Chapter 2—where JD Pants is currently interrogating a donut shop worker!",
            changeSceneId = SCENE_ACT4_TB_CH2_DONUTS.id,
            nextNodeId = "act4_tb_ch2_intro"
        ),

        // =========================================================================
        // ACT IV (TRACK B) - CHAPTER 2: 'I'M SPEAKING!' VS. JD PANTS'S DONUT INTERROGATION (AUG–OCT 2024)
        // =========================================================================
        DialogueNode(
            id = "act4_tb_ch2_intro",
            changeSceneId = SCENE_ACT4_TB_CH2_DONUTS.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside Holt's Sweet Shoppe in Valdosta, Georgia on August 22, 2024! Standing at the pastry counter is Republican VP nominee JD Pants—asking every employee how long the building has existed before ordering 'some sprinkle stuff, whatever makes sense'!",
            nextNodeId = "act4_tb_ch2_donut_hub"
        ),
        DialogueNode(
            id = "act4_tb_ch2_donut_hub",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "*Watches JD Pants awkwardly point at cinnamon rolls while holding a wedge of Swiss cheese outside Pat's King of Steaks* Let's roast JD's retail tour and drop Vindication Wins #2 and #3 before we battle him at the donut counter!",
            choices = listOf(
                DialogueChoice("tb4c2_roast_jd", "Roast JD Pants's 'How long have you worked here? Okay, good' donut order & his CBS fact-check complaint!", listOf("donuts", "how long", "okay good", "whatever makes sense", "fact check"), "act4_tb_ch2_roast_jd_node", completionFlag = "tb4c2_jd_done"),
                DialogueChoice("tb4c2_vind23", "Address the 'Tupac Alive' radio laugh & fake hit-and-run video!", listOf("tupac", "hit and run", "kbsf", "vindication", "hoax"), "act4_tb_ch2_vindications_node", completionFlag = "tb4c2_vind_done"),
                DialogueChoice("tb4c2_battle", "Confront JD Pants & his 'Some Sprinkle Stuff' Donut Box in combat!", listOf("battle", "fight", "vance", "proceed", "advance"), "act4_tb_ch2_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act4_tb_ch2_roast_jd_node",
            setFlagOnEnter = "tb4c2_jd_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "Listen to the man order a box of donuts: 'How long has this place been here? Okay, good. How long have you worked here? Okay, good. Just, uh, a lot of glazed here, some sprinkle stuff, whatever makes sense!' Who walks into a bakery and tells the cashier 'Give me whatever makes sense' like he's ordering motor oil for a lawnmower?! And three days earlier in South Philly he walked up to Pat's King of Steaks and asked why Philadelphia doesn't put Swiss cheese on a cheesesteak!",
            nextNodeId = "act4_tb_ch2_donut_hub"
        ),
        DialogueNode(
            id = "act4_tb_ch2_vindications_node",
            setFlagOnEnter = "tb4c2_vind_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "First, when they asked me who the best rapper alive was and I said Tupac, I laughed because West Coast loyalty runs deep! Pac's spirit is alive in California! Second, someone made up a fake TV station in San Francisco claiming I did a hit-and-run in 2011! Microsoft investigated and found a foreign troll farm hired an actress! I don't even drive—the Secret Service won't let me touch a steering wheel!",
            nextNodeId = "act4_tb_ch2_donut_hub"
        ),
        DialogueNode(
            id = "act4_tb_ch2_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "JD PANTS JUST THREW A BOX OF 'WHATEVER MAKES SENSE' SPRINKLE DONUTS AND A DIET MOUNTAIN DEW, EVERYBODY! Kamala Coconut, hit him with Yellow Electric School Bus Charge and Coconut Tree Context!",
            triggerBattleEncounterId = "act4_track_b_vance_donut_battle",
            nextNodeId = "act4_tb_ch2_post_battle"
        ),
        DialogueNode(
            id = "act4_tb_ch2_post_battle",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "Mr. Vice Presidential Nominee, I'm speaking! With our HP and MP automatically back at 100%, let's step up to the Unburdened Dais for Chapter 3: The Significance of the Passage of Time and our Tiffany Pearl Vindications!",
            changeSceneId = SCENE_ACT4_TB_CH3_PASSAGE.id,
            nextNodeId = "act4_tb_ch3_intro"
        ),

        // =========================================================================
        // ACT IV (TRACK B) - CHAPTER 3: 'THE SIGNIFICANCE OF THE PASSAGE OF TIME' & TIFFANY PEARLS (2022–2024)
        // =========================================================================
        DialogueNode(
            id = "act4_tb_ch3_intro",
            changeSceneId = SCENE_ACT4_TB_CH3_PASSAGE.id,
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside the Hall of the Passage of Time! Here, every speech loops back into itself like an infinity symbol while internet sleuths zoom in 4,000% on Kamala's pearl earrings!",
            nextNodeId = "act4_tb_ch3_passage_hub"
        ),
        DialogueNode(
            id = "act4_tb_ch3_passage_hub",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "*Touches her Tiffany & Co. South Sea Pearl earrings while gesturing toward a clock* When we think about the significance of the passage of time, right, there is great significance to the passage of time! Let's review our circular rhetoric and crush our final two viral smears!",
            choices = listOf(
                DialogueChoice("tb4c3_time", "Deliver the 'Significance of the Passage of Time' (4x), 'Haven't Been to Europe!' & 'Ukraine is a Country in Europe'!", listOf("passage of time", "europe", "ukraine", "lester holt", "four times"), "act4_tb_ch3_time_node", completionFlag = "tb4c3_time_done"),
                DialogueChoice("tb4c3_vind45", "Debunk the 'Bluetooth Pearl Earring' & prison stats rumors!", listOf("earrings", "bluetooth", "tiffany", "marijuana", "cdcr", "vindication"), "act4_tb_ch3_vind45_node", completionFlag = "tb4c3_vind_done"),
                DialogueChoice("tb4c3_meta", "Watch Kash Subpoena crash the seminar in 2024 & try to complain about Barack's 2014 Tan Suit!", listOf("kash", "tan suit", "meta", "let it go", "proceed"), "act4_tb_ch3_meta_callback")
            )
        ),
        DialogueNode(
            id = "act4_tb_ch3_time_node",
            setFlagOnEnter = "tb4c3_time_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "Look, whether I'm telling Lester Holt 'And I haven't been to Europe!', or explaining that 'Ukraine is a country in Europe next to another country called Russia,' or saying 'the significance of the passage of time' four times in thirty-two seconds in Sunset, Louisiana—nobody fills a two-minute cable segment with fewer concrete nouns than me!",
            nextNodeId = "act4_tb_ch3_passage_hub"
        ),
        DialogueNode(
            id = "act4_tb_ch3_vind45_node",
            setFlagOnEnter = "tb4c3_vind_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "First, conspiracy theorists zoomed in on my debate earrings and claimed they were high-tech Bluetooth earpieces broadcasting debate answers from the Pentagon! Honey, they're Tiffany pearl earrings! I've worn them for ten years! Second, opponents claimed I locked up thousands of people for weed in California—state records showed forty-five! If you're going to come for my record, at least get the math right!",
            nextNodeId = "act4_tb_ch3_passage_hub"
        ),
        DialogueNode(
            id = "act4_tb_ch3_meta_callback",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.RIGHT,
            text = "*Bursts through the side door waving a 2014 photograph* Forget Tiffany pearl earrings—what about August 28, 2014 when Barry Teleprompter wore a summer TAN SUIT to a press briefing and ordered Dijon mustard on a burger in 2009?!",
            nextNodeId = "act4_tb_ch3_psaki_roasts_meta"
        ),
        DialogueNode(
            id = "act4_tb_ch3_psaki_roasts_meta",
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "*Laughs at the narrator desk* Kash, it is the autumn of 2024! That tan suit is ten years old and the Dijon mustard burger is fifteen years old—that burger is old enough to have a learner's permit to drive a yellow school bus! Now let's head to Philadelphia for Chapter 4: The September 10, 2024 Debate Stage!",
            changeSceneId = SCENE_ACT4_TB_CH4_DEBATE.id,
            nextNodeId = "act4_tb_ch4_intro"
        ),

        // =========================================================================
        // ACT IV (TRACK B) - CHAPTER 4: 'CONCEPTS OF A PLAN' & 'I SAW IT ON TELEVISION!' (SEPT 10, 2024)
        // =========================================================================
        DialogueNode(
            id = "act4_tb_ch4_intro",
            changeSceneId = SCENE_ACT4_TB_CH4_DEBATE.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is September 10, 2024 at the National Constitution Center in Philadelphia! Kamala Coconut walks all the way behind Commander America's podium to force the first presidential debate handshake since 2016—and then drops the ultimate bait about his rally crowd sizes!",
            nextNodeId = "act4_tb_ch4_rally_bait"
        ),
        DialogueNode(
            id = "act4_tb_ch4_rally_bait",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "*Rests her chin thoughtfully on her hand for the split-screen camera and smiles* 'I'm going to actually do something really unusual, and I'm going to invite you to attend one of Donald Trump's rallies, because it's a really interesting thing to watch! And what you will also notice is that people start leaving his rallies early out of exhaustion and boredom!' Watch what happens three seconds after I say 'leaving his rallies early'!",
            nextNodeId = "act4_tb_ch4_trump_television"
        ),
        DialogueNode(
            id = "act4_tb_ch4_trump_television",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.RIGHT,
            text = "*Glares into the camera on September 10, 2024* 'First of all, nobody leaves my rallies! We have the biggest, most incredible rallies in the history of politics! And in Springfield, they're eating the dogs, the people that came in, they're eating the cats, they're eating—they're eating the pets of the people that live there!' And when ABC moderator David Muir claims the Springfield city manager found no credible reports: 'Well, I've seen people on television! The people on television say my dog was taken and used for food!'",
            nextNodeId = "act4_tb_ch4_concepts_of_a_plan"
        ),
        DialogueNode(
            id = "act4_tb_ch4_concepts_of_a_plan",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.RIGHT,
            text = "*Waves both accordion hands when moderator Linsey Davis asks if, after nine years since 2015, he has an actual healthcare plan* 'I have concepts of a plan! I'm not president right now! But if we come up with something, I would only change it if we come up with something that's better and less expensive!'",
            nextNodeId = "act4_tb_ch4_battle_trigger"
        ),
        DialogueNode(
            id = "act4_tb_ch4_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "HE JUST UNVEILED 'CONCEPTS OF A PLAN' AFTER NINE YEARS AND 'THE PEOPLE ON TELEVISION,' HONEY! Kamala Coconut, unleash Tiffany Pearl Fact-Check and Significance of the Passage of Time!",
            triggerBattleEncounterId = "act4_track_b_concepts_debate_battle",
            nextNodeId = "act4_tb_ch4_post_battle"
        ),
        DialogueNode(
            id = "act4_tb_ch4_post_battle",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "'Concepts of a plan' is going straight into the Political Hall of Fame! With our HP and MP 100% restored, let's head to the closed-set Pennsylvania McDonald's drive-thru and Green Bay garbage truck tarmac for the Chapter 5 Act IV Finale!",
            changeSceneId = SCENE_ACT4_TB_CH5_MCDONALDS.id,
            nextNodeId = "act4_tb_ch5_intro"
        ),

        // =========================================================================
        // ACT IV (TRACK B) - CHAPTER 5: THE FRY APRON, THE GARBAGE VEST & PASSING THE CHAPATI (NOV 2024 FINALE)
        // =========================================================================
        DialogueNode(
            id = "act4_tb_ch5_intro",
            changeSceneId = SCENE_ACT4_TB_CH5_MCDONALDS.id,
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "It is late October 2024! Commander America is leaning out of a closed-to-the-public McDonald's drive-thru window wearing a yellow-trimmed fry apron over his silk tie—right beside a white Green Bay garbage truck and a neon-orange reflective safety vest!",
            nextNodeId = "act4_tb_ch5_kamala_confronts"
        ),
        DialogueNode(
            id = "act4_tb_ch5_kamala_confronts",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "Look at this October photo-op double feature, Donald! First your advance team had a Pennsylvania McDonald's close its doors to the general public so you could scoop fries for pre-screened rehearsal cars while wearing a Brioni dress shirt and tie, and ten days later you nearly missed the door handle climbing into a custom-painted garbage truck in a neon-orange vest!",
            nextNodeId = "act4_tb_ch5_commander_reply"
        ),
        DialogueNode(
            id = "act4_tb_ch5_commander_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.RIGHT,
            text = "Those French fries were salted to perfection, Kamala—and my neon-orange garbage truck vest is high fashion! Meanwhile, you spent \$2.5 million on celebrity town halls, got stuck saying '32 days, 32 days' when your Flint teleprompter blinked, and told America we fell out of a coconut tree! Let's settle Act IV right now!",
            nextNodeId = "act4_tb_ch5_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act4_tb_ch5_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE 2024 SHOWDOWN, EVERYBODY! Kamala Coconut vs. Commander America's McDonald's Fry-Cook Apron, the Neon-Orange Mack Garbage Truck, and JD Pants's Diet Mountain Dew! RING THE BELL!",
            triggerBattleEncounterId = "act4_track_b_mcdonalds_finale",
            nextNodeId = "act4_tb_ch5_post_battle"
        ),
        DialogueNode(
            id = "act4_tb_ch5_post_battle",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.LEFT,
            text = "*Closes her three-circle Venn diagram binder as the calendar flips to 2025–2026* That wraps up 2024! And now, for the Grand Finale Act V (2025–2026), the story heads straight up I-95 to New York City—where democratic socialist Zohran Cardamom ('Mr. Cardamom') and swaggering table-water connoisseur Mayor Swagger are taking the stage!",
            nextNodeId = "act4_tb_ch5_zohran_handoff"
        ),
        DialogueNode(
            id = "act4_tb_ch5_zohran_handoff",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "*Steps out of an Astoria food truck wearing a bespoke suit and an apron, slapping a sizzling chapati onto the grill while 'Nani' plays on the speakers* Yo, peace everybody—it's Zohran Cardamom, aka Young Cardamom, aka Mr. Cardamom! In Act V (2025–2026), we're taking on Musk-X's Dark Gothic MAGA Mid-Air Star-Jump, Mayor Swagger's Turkish 'rat-free table water,' and Commander America in the Bipartisan Grand Finale! Let's roll!",
            nextNodeId = "act5_tb_ch1_intro"
        ),
        // =========================================================================
        // EXPANDED ACT V DUAL-CAMPAIGN DIALOGUE GRAPH (5 Deep Chapters Per Track)
        // Track A: Commander America + Full Party + Guest Summon Musk-X (2024–2026)
        // Track B: Standard-Bearer #5 Zohran Cardamom ("Mr. Cardamom") + Mayor Swagger (2025–2026)
        // =========================================================================

        // =========================================================================
        // ACT V (TRACK A) - CHAPTER 1: DARK GOTHIC MAGA STAR-JUMP & THE PORCELAIN SINK (2024–2025)
        // =========================================================================
        DialogueNode(
            id = "act5_ch1_intro",
            changeSceneId = SCENE_ACT5_CH1_ARENA.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to Act V (2024–2026): The Grand Finale Era! I'm Kayleigh Binder-Tabs, with Karoline Podium-Boss and Hulkster 24-Pythons! It is October 5, 2024 at the Butler Farm Show grounds in Pennsylvania—where Guest Tech-Support Summon Musk-X has just sprinted onto the stage in an all-black 'Dark Gothic MAGA' cap and leaped into a mid-air 'X' star-jump exposing four inches of billionaire midriff!",
            nextNodeId = "act5_ch1_musk_joins"
        ),
        DialogueNode(
            id = "act5_ch1_musk_joins",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.LEFT,
            text = "*Lands from a 2-foot vertical mid-air X-jump, tugging his black 'Occupy Mars' t-shirt back down over his stomach while carrying a porcelain bathroom sink* 'As you can see, I am not just MAGA—I am Dark, Gothic MAGA!' *Sets the porcelain sink down next to a stainless-steel Cybertruck with two shattered windows* And I brought the actual porcelain bathroom sink I carried into Twitter headquarters on October 26, 2022 just so I could tweet 'Entering Twitter HQ – let that sink in!' before firing the C-suite and renaming the entire bird app to my favorite letter of the alphabet: X!",
            nextNodeId = "act5_ch1_musk_hub"
        ),
        DialogueNode(
            id = "act5_ch1_musk_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Nobody jumps into the shape of a giant letter X in mid-air like Elon, folks! Before we battle the Shattered Cybertruck & Porcelain Sink QA Testers, what part of Musk-X's tech-support arsenal are we inspecting?",
            choices = listOf(
                DialogueChoice("a5c1_cybertruck", "Replay the Nov 2019 Cybertruck 'Armor Glass' steel-ball window smash & Don Lemon Ketamine Rx interview!", listOf("cybertruck", "armor glass", "window", "ketamine", "don lemon", "420"), "act5_ch1_cybertruck_node", completionFlag = "a5c1_cyb_done"),
                DialogueChoice("a5c1_vind", "Laugh off the 'Emerald Mine Heir' & 'Stranded Astronauts' rumors!", listOf("vindication", "emerald mine", "astronauts", "spacex", "boeing"), "act5_ch1_musk_vindication", completionFlag = "a5c1_vind_done"),
                DialogueChoice("a5c1_battle", "Test Musk-X's Dark Gothic MAGA Star-Jump in combat against the Shattered Cybertruck & Porcelain Sink!", listOf("battle", "fight", "sink", "proceed", "advance"), "act5_ch1_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act5_ch1_cybertruck_node",
            setFlagOnEnter = "a5c1_cyb_done",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.LEFT,
            text = "*Watches chief designer Franz von Holzhausen hurl a solid steel ball at the Cybertruck's 'unbreakable Armor Glass' driver window on November 21, 2019—loudly spider-webbing it on live worldwide stream* 'Oh my f***ing God. Well, maybe that was a little too hard!' *Throws the steel ball at the rear passenger window, smashing that one too* 'It didn't go through! That's a plus side! We'll fix it in post!' And when Don Lemon asked me in March 2024 about my biweekly prescribed ketamine for a 'negative chemical state,' I told him it's in the shareholders' best interest—right alongside my \$40 million '$420 Funding Secured' tweet!",
            nextNodeId = "act5_ch1_musk_hub"
        ),
        DialogueNode(
            id = "act5_ch1_musk_vindication",
            setFlagOnEnter = "a5c1_vind_done",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.LEFT,
            text = "People on Twitter love claiming I inherited a billionaire emerald mine! I moved to Canada with two grand in my pocket, cleaned boilers in Saskatchewan, graduated with a mountain of student debt, and slept on the floor of my server room eating cold pizza! And when Boeing's capsule left astronauts stranded in orbit, guess whose Dragon capsule NASA booked to bring them home?! SpaceX, baby!",
            nextNodeId = "act5_ch1_musk_hub"
        ),
        DialogueNode(
            id = "act5_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "TWO SHATTERED CYBERTRUCK WINDOWS AND A PORCELAIN BATHROOM SINK ARE CHARGING THE STAGE, BROTHER! Musk-X, hit 'em with the Dark Gothic MAGA Mid-Air Star-Jump!",
            triggerBattleEncounterId = "act5_track_a_cybertruck_sink_battle",
            nextNodeId = "act5_ch1_post_battle"
        ),
        DialogueNode(
            id = "act5_ch1_post_battle",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.LEFT,
            text = "Let that sink in! Our HP and MP are automatically restored to 100%, and now we're heading to the Fiserv Forum in Milwaukee and the Department of Government Efficiency ('DOGE') for Chapter 2!",
            changeSceneId = SCENE_ACT5_CH2_SENATE.id,
            nextNodeId = "act5_ch2_intro"
        ),

        // =========================================================================
        // ACT V (TRACK A) - CHAPTER 2: HULKSTER'S SHIRT-RIP, 'HEY BOB!' & DOGE (2024–2025)
        // =========================================================================
        DialogueNode(
            id = "act5_ch2_intro",
            changeSceneId = SCENE_ACT5_CH2_SENATE.id,
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WELCOME TO JULY 18, 2024 AT THE FISERV FORUM IN MILWAUKEE AND THE 'DOGE' HEADQUARTERS, BROTHER! Watch me step up to the convention podium in a black blazer, rip off my outer shirt to reveal a Trump-Vance shirt, and then rip THAT shirt in half too—LET TRUMPAMANIA RUN WILD, BROTHER!",
            nextNodeId = "act5_ch2_doge_hub"
        ),
        DialogueNode(
            id = "act5_ch2_doge_hub",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.LEFT,
            text = "*Waves a giant Shiba Inu meme coin banner over a pile of federal agency org charts* Welcome to the Department of Government Efficiency—specifically named 'DOGE' so the official acronym of US government reform matches a 2013 Shiba Inu meme coin I pumped on Saturday Night Live! What are we auditing first?",
            choices = listOf(
                DialogueChoice("a5c2_bob", "Replay Musk-X's Nov 29, 2023 DealBook 'Go f*** yourself... Hey, Bob, if you're in the audience!' to Disney CEO Bob Iger!", listOf("bob iger", "dealbook", "advertisers", "disney", "blackmail"), "act5_ch2_bob_node", completionFlag = "a5c2_bob_done"),
                DialogueChoice("a5c2_doge", "Inspect the Shiba Inu 'DOGE' acronym, Wizard Kash's children's book & Foghorn Kennedy's Oxford drawl!", listOf("doge", "shiba", "kash", "kennedy", "efficiency"), "act5_ch2_doge_node", completionFlag = "a5c2_doge_done"),
                DialogueChoice("a5c2_battle", "Unleash DOGE & Hulkster's 24-Inch Pythons on the Red-Tape Bureaucrats!", listOf("battle", "fight", "bureaucrats", "proceed", "advance"), "act5_ch2_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act5_ch2_bob_node",
            setFlagOnEnter = "a5c2_bob_done",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.LEFT,
            text = "*Pauses for four dramatic seconds in his black leather jacket at the New York Times DealBook Summit (November 29, 2023)* 'If somebody is going to try to blackmail me with advertising, blackmail me with money? Go f*** yourself.' *Pauses and squints into the front row* 'Go. F***. Yourself. Is that clear? I hope it is. Hey, Bob, if you're in the audience! That's how I feel, don't advertise!'",
            nextNodeId = "act5_ch2_doge_hub"
        ),
        DialogueNode(
            id = "act5_ch2_doge_node",
            setFlagOnEnter = "a5c2_doge_done",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "*Holds up his \$19.99 'Plot Against the King' wizard children's book beside Senator Foghorn Kennedy* Between Musk-X naming a federal efficiency commission after a Shiba Inu dog meme, me turning the FISA warrant fight into a pointy-hat wizard storybook, and Senator Kennedy telling witnesses 'Next time you're in trouble, call a crackhead,' the bureaucracy doesn't stand a chance!",
            nextNodeId = "act5_ch2_doge_hub"
        ),
        DialogueNode(
            id = "act5_ch2_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "A SWARM OF RED-TAPE SUBCOMMITTEE CHAIRS IS BLOCKING THE DOGE AUDIT! Commander America, Musk-X, and JD Pants—clear the floor!",
            triggerBattleEncounterId = "act5_track_a_doge_bureaucrats_battle",
            nextNodeId = "act5_ch2_post_battle"
        ),
        DialogueNode(
            id = "act5_ch2_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "DOGE just cut their HP to zero, and our party is back at 100%! Now let's fly to New York City for Chapter 3—where swaggering table-water connoisseur Mayor Swagger is waiting in a Turkish Airlines lie-flat business-class seat!",
            changeSceneId = SCENE_ACT5_CH3_TERRACE.id,
            nextNodeId = "act5_ch3_intro"
        ),

        // =========================================================================
        // ACT V (TRACK A) - CHAPTER 3: MAYOR SWAGGER'S TURKISH UPGRADES & 'VEGAN' SEA BASS (2024–2025)
        // =========================================================================
        DialogueNode(
            id = "act5_ch3_intro",
            changeSceneId = SCENE_ACT5_CH3_TERRACE.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to New York City—the 'Istanbul of America'! Reclining in a \$15,000 Turkish Airlines lie-flat business-class suite while eating a late-night fillet of 'strictly vegan' Chilean sea bass at Osteria La Baia is New York's self-proclaimed 'Swagger Man,' Mayor Swagger!",
            nextNodeId = "act5_ch3_swagger_speech"
        ),
        DialogueNode(
            id = "act5_ch3_swagger_speech",
            speaker = DialogueSpeaker.MAYOR_SWAGGER,
            side = SpeakerSide.RIGHT,
            text = "*Takes a sip of tap water from a champagne flute while wearing a custom suit* 'Wait a minute—this is table water from New York City! And when someone upgrades you on Turkish Airlines, you do what New Yorkers do: you say THANK YOU! Because a preliminary review of your planning always preludes the pre-planning!'",
            nextNodeId = "act5_ch3_swagger_hub"
        ),
        DialogueNode(
            id = "act5_ch3_swagger_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at Mayor Swagger, folks—they indicted him over Turkish Airlines seat upgrades, he changed his iPhone passcode from 4 digits to 6 digits and told the FBI he forgot it, and he filmed an instructional video pulling a revolver out of a plastic baby doll! Which of Mayor Swagger's greatest hits are we exposing first?",
            choices = listOf(
                DialogueChoice("a5c3_babydoll", "Inspect Mayor Swagger's viral NYPD 'Contraband Baby-Doll & Marble-Jar' bedroom search video!", listOf("baby doll", "marbles", "contraband", "pillowcase", "search"), "act5_ch3_babydoll_node", completionFlag = "a5c3_doll_done"),
                DialogueChoice("a5c3_seabass", "Expose his 'Strictly Plant-Based Vegan' Midnight Chilean Sea Bass at Osteria La Baia & 6-Digit iPhone Passcode!", listOf("vegan", "sea bass", "fish", "iphone", "passcode", "turkish"), "act5_ch3_seabass_node", completionFlag = "a5c3_fish_done"),
                DialogueChoice("a5c3_battle", "Battle Mayor Swagger & his Turkish Airlines Business-Class Upgrade Agent!", listOf("battle", "fight", "swagger", "proceed", "advance"), "act5_ch3_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act5_ch3_babydoll_node",
            setFlagOnEnter = "a5c3_doll_done",
            speaker = DialogueSpeaker.MAYOR_SWAGGER,
            side = SpeakerSide.RIGHT,
            text = "*Holds up a creepy plastic baby doll and a glass jar of marbles with deadpan seriousness* 'Look at this innocent-looking baby doll on your child's dresser. You pull the head off—BOOM, there's a loaded .38 revolver inside the doll! You look at this glass jar of decorative marbles—BOOM, crack cocaine rocks at the bottom! You check the pillowcase—BOOM, marijuana!'",
            nextNodeId = "act5_ch3_swagger_hub"
        ),
        DialogueNode(
            id = "act5_ch3_seabass_node",
            setFlagOnEnter = "a5c3_fish_done",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Wait a second, Mayor—you published an entire book about how you're a strict 100% plant-based vegan who lectures kids on school lunches, and yet the waiters at Osteria La Baia in Midtown Manhattan caught you ordering the \$48 Chilean sea bass at midnight! And when the FBI seized your iPhones in November 2023, you claimed you had just changed your passcode from four digits to six digits 'to prevent staffers from accidentally butt-dialing' and conveniently forgot the new six-digit code!",
            nextNodeId = "act5_ch3_swagger_hub"
        ),
        DialogueNode(
            id = "act5_ch3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "MAYOR SWAGGER JUST RECLINED HIS TURKISH AIRLINES LIE-FLAT SEAT AND PULLED OUT THE CONTRABAND BABY DOLL, BROTHER! Ring the bell!",
            triggerBattleEncounterId = "act5_track_a_swagger_turkish_battle",
            nextNodeId = "act5_ch3_post_battle"
        ),
        DialogueNode(
            id = "act5_ch3_post_battle",
            speaker = DialogueSpeaker.MAYOR_SWAGGER,
            side = SpeakerSide.RIGHT,
            text = "*Tips his cap with a grin* Hey, Commander—after what the DOJ put both of us through, I respect the hustle! Keep an eye on Queens in Chapter 4, though—because Ugandan-born rap producer turned democratic socialist 'Mr. Cardamom' just pulled up in an Astoria chapati truck to freeze the rent and make the Q70 bus free!",
            changeSceneId = SCENE_ACT5_CH4_CLOSETS.id,
            nextNodeId = "act5_ch4_intro"
        ),

        // =========================================================================
        // ACT V (TRACK A) - CHAPTER 4: ENTER 'MR. CARDAMOM,' NANI'S APRON & CITY-OWNED GROCERIES (2025–2026)
        // =========================================================================
        DialogueNode(
            id = "act5_ch4_intro",
            changeSceneId = SCENE_ACT5_CH4_CLOSETS.id,
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We have arrived in Astoria, Queens! Standing in front of a chapati food truck wearing a bespoke thrifted suit under a floral kitchen apron is Zohran Cardamom—formerly known in the 2016–2019 rap scene as 'Young Cardamom' and 'Mr. Cardamom'!",
            nextNodeId = "act5_ch4_zohran_speech"
        ),
        DialogueNode(
            id = "act5_ch4_zohran_speech",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.RIGHT,
            text = "*Flips a sizzling chapati on the griddle while a symmetrical Wes Anderson-style TikTok camera zooms in* 'Yo, peace! Before I entered City Hall to freeze the rent, make the buses fare-free, and open five city-owned municipal grocery stores across the five boroughs, I dropped the 2019 rap single \"Nani\" in an apron alongside cookbook legend and actress Madhur Jaffrey!'",
            nextNodeId = "act5_ch4_zohran_hub"
        ),
        DialogueNode(
            id = "act5_ch4_zohran_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Wait until you hear the full dossier on 'Mr. Cardamom,' folks! Let's roast his 'working-class revolution' résumé and watch Chyron McSpin embarrass himself one last time before the 2026 Grand Finale!",
            choices = listOf(
                DialogueChoice("a5c4_bowdoin", "Roast 'Mr. Cardamom's' \$60k/yr Bank Street School, Bowdoin College & Hollywood Director pedigree!", listOf("bowdoin", "bank street", "hollywood", "mira nair", "cardamom", "nani"), "act5_ch4_bowdoin_node", completionFlag = "a5c4_bow_done"),
                DialogueChoice("a5c4_grocery", "Roast his proposal for Five City-Owned Municipal Grocery Stores & Fare-Free MTA Buses!", listOf("grocery", "city owned", "buses", "q70", "rent"), "act5_ch4_grocery_node", completionFlag = "a5c4_groc_done"),
                DialogueChoice("a5c4_meta", "Watch Chyron McSpin jump out of a Queens bodega in 2026 still yelling about the 2005 Access Hollywood Bus Tape!", listOf("chyron", "bus tape", "2005", "meta", "let it go"), "act5_ch4_meta_callback", completionFlag = "a5c4_meta_done"),
                DialogueChoice("a5c4_proceed", "Advance to Chapter 5: The 2026 Bipartisan Grand Finale Showdown!", listOf("proceed", "advance", "finale", "chapter 5"), "act5_ch5_finale_intro")
            )
        ),
        DialogueNode(
            id = "act5_ch4_bowdoin_node",
            setFlagOnEnter = "a5c4_bow_done",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Let's read the 'proletariat' bio here, Zohran: Your mother is Oscar-nominated Hollywood director Mira Nair (Monsoon Wedding, Disney's Queen of Katwe—which you served as music supervisor on!), your father is an endowed Columbia University professor, you attended the \$60,000-a-year private Bank Street School for Children on the Upper West Side, and you graduated from elite Bowdoin College in Maine before LARPing as a socialist revolutionary in a \$2,000 suit!",
            nextNodeId = "act5_ch4_zohran_hub"
        ),
        DialogueNode(
            id = "act5_ch4_grocery_node",
            setFlagOnEnter = "a5c4_groc_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "And look at his big economic plan, folks—Five City-Owned Municipal Grocery Stores run by the New York City bureaucracy! Have you ever been to the DMV in Queens? Imagine waiting at Window 14B with a stamped triplicate voucher just to buy a bruised avocado and a carton of oat milk!",
            nextNodeId = "act5_ch4_zohran_hub"
        ),
        DialogueNode(
            id = "act5_ch4_meta_callback",
            setFlagOnEnter = "a5c4_meta_done",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "*Bursts out of a Queens subway entrance waving a faded VHS tape in the year 2026* STOP TALKING ABOUT MUNICIPAL GROCERY STORES! What about October 7, 2016—when we aired the 2005 Access Hollywood bus tape where Commander America popped Tic-Tacs with Billy Bush?!",
            nextNodeId = "act5_ch4_musk_roasts_meta"
        ),
        DialogueNode(
            id = "act5_ch4_musk_roasts_meta",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.LEFT,
            text = "*Laughs while holding his porcelain bathroom sink* Bro, it is the year 2026! That 2005 Billy Bush bus tape is TWENTY-ONE YEARS OLD—that tape is old enough to legally drink a beer at a bar and rent a Cybertruck! Let that sink in!",
            nextNodeId = "act5_ch4_zohran_hub"
        ),

        // =========================================================================
        // ACT V (TRACK A) - CHAPTER 5: THE 2026 BIPARTISAN GRAND FINALE (GRAND CLIMAX)
        // =========================================================================
        DialogueNode(
            id = "act5_ch5_finale_intro",
            changeSceneId = SCENE_ACT5_CH5_FINALE.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We have arrived at the 2026 Bipartisan Roast Arena! Across the stage stand Zohran Cardamom ('Mr. Cardamom') in his Nani rap apron, Kamala Coconut with her Three-Circle Venn Diagram, and Joe Naptime with his 10-clip Real-Audio Gibberish Megaphone and Double-Scoop Chocolate Chip Waffle Cone!",
            nextNodeId = "act5_ch5_boss_banter"
        ),
        DialogueNode(
            id = "act5_ch5_boss_banter",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.RIGHT,
            text = "Yo, Commander America and Musk-X! Between my Nani chapati drop, Kamala's Coconut Tree context, and Joe's truinanashabbadepressure ice-cream blizzard, we're freezing your rent and making this entire arena fare-free!",
            nextNodeId = "act5_ch5_commander_banter"
        ),
        DialogueNode(
            id = "act5_ch5_commander_banter",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "We've been through eleven years of escalators, Chappaqua servers, Covfefe tweets, \$24,000 freezers, Corn Pop chains, coconut trees, donut shops, and mid-air X-jumps, folks! Musk-X, JD Pants, Wizard Kash, Foghorn Kennedy, Little Marco—let's take home the Grand Finale!",
            nextNodeId = "act5_ch5_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act5_ch5_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THIS IS IT, BROTHER—THE GRAND FINALE! Commander America & Musk-X vs. Zohran Cardamom ('Mr. Cardamom'), Kamala Coconut, and Joe Naptime! LET EQUAL-OPPORTUNITY SATIRE RUN WILD!",
            triggerBattleEncounterId = "act5_track_a_grand_finale",
            nextNodeId = "act5_ch5_post_battle"
        ),
        DialogueNode(
            id = "act5_ch5_post_battle",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "CONGRATULATIONS! You have conquered the campaign! The stage is set for the Grand Finale Curtain Call!",
            nextNodeId = "act5_bipartisan_curtain_call"
        ),

        // =========================================================================
        // ACT V (TRACK B) - CHAPTER 1: 'MR. CARDAMOM,' NANI'S APRON & 5 CITY GROCERY STORES (PLAYING AS ZOHRAN CARDAMOM + MAYOR SWAGGER)
        // =========================================================================
        DialogueNode(
            id = "act5_tb_ch1_intro",
            changeSceneId = SCENE_ACT5_TB_CH1_CHAPATI.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to 2025 and 2026! Up in New York City, Ugandan-born rap producer turned democratic socialist Zohran Cardamom ('Mr. Cardamom') and swaggering table-water connoisseur Mayor Swagger take the stage!",
            nextNodeId = "act5_tb_ch1_oprah_welcome"
        ),
        DialogueNode(
            id = "act5_tb_ch1_oprah_welcome",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "YOU GET A HOT CHAPATI! YOU GET A FREE Q70 BUS RIDE! YOU GET A CITY-OWNED MUNICIPAL GROCERY STORE! AND YOU GET TURKISH AIRLINES LIE-FLAT UPGRADES, HONEY!",
            nextNodeId = "act5_tb_ch1_zohran_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch1_zohran_hub",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "*Adjusts his thrifted bespoke suit and ties on his 'Nani' rap apron beside the Astoria chapati truck* Yo, let's keep it 100% real before we battle the Luxury Condo Rent-Hike Lobbyists: I rap in an apron with Madhur Jaffrey, I grew up the son of an Oscar-nominated Hollywood director and a Columbia professor, and I have the official MTA receipt proving my free-bus pilot actually worked! What are we dropping first?",
            choices = listOf(
                DialogueChoice("tb5c1_nani", "Perform the 2019 'Mr. Cardamom – Nani' rap verse & self-roast the Bank Street / Bowdoin trust-fund résumé!", listOf("nani", "cardamom", "madhur jaffrey", "bowdoin", "bank street", "rap"), "act5_tb_ch1_nani_node", completionFlag = "tb5c1_nani_done"),
                DialogueChoice("tb5c1_mta_vind", "Show the MTA Free-Bus results & transparent background!", listOf("vindication", "mta", "bus", "q70", "pilot", "grocery"), "act5_tb_ch1_mta_vindication", completionFlag = "tb5c1_mta_done"),
                DialogueChoice("tb5c1_battle", "Unleash 'Mr. Cardamom' Nani Chapati Drop & Mayor Swagger on the Luxury Condo Rent-Hike Lobbyists!", listOf("battle", "fight", "lobbyists", "proceed", "advance"), "act5_tb_ch1_battle_trigger")
            )
        ),
        DialogueNode(
            id = "tb5c1_nani_node_alias",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "Yo, peace!",
            nextNodeId = "act5_tb_ch1_zohran_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch1_nani_node",
            setFlagOnEnter = "tb5c1_nani_done",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "*Raps on the steps of the chapati truck in an apron* Before I ever ran for office, I performed under the stage names 'Young Cardamom' and 'Mr. Cardamom,' curated the soundtrack for Disney's Queen of Katwe (directed by my mom Mira Nair!), and filmed the 2019 music video 'Nani' in an apron with cookbook legend Madhur Jaffrey! And look, yes—I went to the \$60,000-a-year Bank Street School and Bowdoin College before preaching the working-class revolution in a symmetrical Wes Anderson TikTok, so I'm roasting my own Ivy-adjacent résumé before JD Pants can!",
            nextNodeId = "act5_tb_ch1_zohran_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch1_mta_vindication",
            setFlagOnEnter = "tb5c1_mta_done",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "Pundits said my fare-free bus pilot in New York was going to cause subway chaos and bus brawls. Guess what the official MTA report showed? Ridership shot up thirty-eight percent, and bus driver assaults dropped by nearly forty percent! Turns out when you make the bus free, people just want to get to work on time!",
            nextNodeId = "act5_tb_ch1_zohran_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "LUXURY CONDO RENT-HIKE LOBBYISTS AND AN MTA SWIPE-CARD TURNSTILE ARE BLOCKING THE BUS LANE, EVERYBODY! Zohran Cardamom and Mayor Swagger, freeze their rent!",
            triggerBattleEncounterId = "act5_track_b_landlord_lobby_battle",
            nextNodeId = "act5_tb_ch1_post_battle"
        ),
        DialogueNode(
            id = "act5_tb_ch1_post_battle",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "Rent frozen, buses fare-free, and our HP and MP are automatically back at 100%! Now let's step inside Gracie Mansion and the Turkish Airlines First-Class Lounge for Chapter 2—where my co-op partner Mayor Swagger is demonstrating his NYPD Contraband Baby-Doll search!",
            changeSceneId = SCENE_ACT5_TB_CH2_SWAGGER.id,
            nextNodeId = "act5_tb_ch2_intro"
        ),

        // =========================================================================
        // ACT V (TRACK B) - CHAPTER 2: MAYOR SWAGGER'S CONTRABAND BABY-DOLL & MIDNIGHT SEA BASS (2025)
        // =========================================================================
        DialogueNode(
            id = "act5_tb_ch2_intro",
            changeSceneId = SCENE_ACT5_TB_CH2_SWAGGER.id,
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside Gracie Mansion with Mayor Swagger! On the table sit a plastic baby doll, a glass jar of marbles, a plate of midnight Chilean sea bass from Osteria La Baia, a locked 6-digit iPhone, and a stack of Turkish Airlines lie-flat boarding passes!",
            nextNodeId = "act5_tb_ch2_swagger_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch2_swagger_hub",
            speaker = DialogueSpeaker.MAYOR_SWAGGER,
            side = SpeakerSide.LEFT,
            text = "*Adjusts his collar with maximum swagger* Listen, Zohran—nobody survives New York politics without a little swagger, a glass of rat-free New York tap water, and a preliminary review of your planning that preludes the pre-planning! What are we showing the voters before we battle the Subpoena Auditors?",
            choices = listOf(
                DialogueChoice("tb5c2_swagger_hits", "Demonstrate the Contraband Baby-Doll search, 'Strictly Vegan' Midnight Sea Bass & 6-Digit iPhone Passcode!", listOf("baby doll", "sea bass", "vegan", "passcode", "turkish"), "act5_tb_ch2_swagger_hits_node", completionFlag = "tb5c2_swag_done"),
                DialogueChoice("tb5c2_vind245", "Debunk the 'Secret Penthouse' & 'Banned Bodegas' tabloid flyers!", listOf("vindication", "penthouse", "bodegas", "grocery", "astoria"), "act5_tb_ch2_vindications_node", completionFlag = "tb5c2_vind_done"),
                DialogueChoice("tb5c2_battle", "Team up to battle the Subpoena Passcode Cracker & Midnight Sea Bass Waiter!", listOf("battle", "fight", "auditor", "proceed", "advance"), "act5_tb_ch2_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act5_tb_ch2_swagger_hits_node",
            setFlagOnEnter = "tb5c2_swag_done",
            speaker = DialogueSpeaker.MAYOR_SWAGGER,
            side = SpeakerSide.LEFT,
            text = "*Pulls a prop revolver out of a plastic baby doll while taking a bite of Chilean sea bass* Look, I taught parents to check the baby doll and the marble jar! Yes, I wrote a whole book about being a strict plant-based vegan while secretly ordering the Chilean sea bass at Osteria La Baia at midnight! Yes, when Turkish Airlines upgraded me to a lie-flat bed I said 'Thank you'! And yes, I changed my iPhone passcode from 4 digits to 6 digits and forgot the code the exact day the feds asked for it!",
            nextNodeId = "act5_tb_ch2_swagger_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch2_vindications_node",
            setFlagOnEnter = "tb5c2_vind_done",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "The tabloids printed flyers claiming I live in a five-million-dollar Tribeca penthouse! I live in a rent-stabilized apartment in Astoria where the radiator sounds like a haunted percussion section! And no, my city grocery pilot isn't banning bodegas—bodegas are sacred NYC culture! I just want people to be able to afford milk and fresh vegetables!",
            nextNodeId = "act5_tb_ch2_swagger_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch2_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "A SUBPOENA PASSCODE CRACKER AND AN OSTERIA LA BAIA SEA-BASS WAITER ARE STORMING THE LOUNGE, HONEY! Zohran and Mayor Swagger, hit 'em with Turkish Airlines Upgrade and 5 City-Owned Grocery Stores!",
            triggerBattleEncounterId = "act5_track_b_turkish_auditor_battle",
            nextNodeId = "act5_tb_ch2_post_battle"
        ),
        DialogueNode(
            id = "act5_tb_ch2_post_battle",
            speaker = DialogueSpeaker.MAYOR_SWAGGER,
            side = SpeakerSide.LEFT,
            text = "That passcode is staying 6 digits forever, and our HP and MP are back at 100%! Now let's head to the Butler Star-Jump Stage & Cybertruck Lab in Chapter 3 to roast Musk-X!",
            changeSceneId = SCENE_ACT5_TB_CH3_CYBERTRUCK.id,
            nextNodeId = "act5_tb_ch3_intro"
        ),

        // =========================================================================
        // ACT V (TRACK B) - CHAPTER 3: ROASTING MUSK-X'S MID-AIR 'X' JUMP & PORCELAIN SINK (2025–2026)
        // =========================================================================
        DialogueNode(
            id = "act5_tb_ch3_intro",
            changeSceneId = SCENE_ACT5_TB_CH3_CYBERTRUCK.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are at the Cybertruck Demo Stage! Across from us stands billionaire Guest Summon Musk-X—wearing his black 'Dark Gothic MAGA' hat, clutching a porcelain bathroom sink, and standing beside a Cybertruck with two steel-ball-shattered windows!",
            nextNodeId = "act5_tb_ch3_musk_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch3_musk_hub",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "*Points his TikTok lapel mic at Musk-X* Look at the richest man on Earth: jumping into the air trying to form the letter X with his limbs while four inches of belly peek out under his 'Occupy Mars' shirt! What are we roasting first?",
            choices = listOf(
                DialogueChoice("tb5c3_starjump", "Roast Musk-X's Oct 5, 2024 Mid-Air 'X' Star-Jump, his porcelain bathroom sink & renaming Twitter to 'X'!", listOf("star jump", "midriff", "sink", "twitter", "gothic maga"), "act5_tb_ch3_starjump_node", completionFlag = "tb5c3_jump_done"),
                DialogueChoice("tb5c3_meta", "Watch Kash Subpoena barge onto the stage in 2026 trying to bring up Hillary's 1978 Cattle Futures!", listOf("kash", "cattle futures", "1978", "meta", "let it go"), "act5_tb_ch3_meta_callback", completionFlag = "tb5c3_meta_done"),
                DialogueChoice("tb5c3_battle", "Battle Musk-X's Dark Gothic MAGA Star-Jump & Shattered Cybertruck!", listOf("battle", "fight", "musk", "cybertruck", "proceed"), "act5_tb_ch3_battle_trigger")
            )
        ),
        DialogueNode(
            id = "act5_tb_ch3_starjump_node",
            setFlagOnEnter = "tb5c3_jump_done",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "Think about this sequence of events, Elon: First you paid \$44 billion for Twitter after trying to back out of your own \$54.20 weed-joke offer, then you physically carried a porcelain bathroom sink into the lobby just to tweet a pun my uncle wouldn't post on WhatsApp, then you threw away fifteen years of globally recognized 'tweet' branding for the single letter X, and then you smashed both of your own 'unbreakable' Cybertruck windows with a metal ball on live stream!",
            nextNodeId = "act5_tb_ch3_musk_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch3_meta_callback",
            setFlagOnEnter = "tb5c3_meta_done",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.RIGHT,
            text = "*Runs out from behind the shattered Cybertruck waving a yellowed 1978 ledger* Forget Cybertruck windows and mid-air star-jumps—what about 1978 when Hill-Rod turned \$1,000 into \$99,537 trading Arkansas cattle futures with Refco broker Red Bone?!",
            nextNodeId = "act5_tb_ch3_oprah_roasts_meta"
        ),
        DialogueNode(
            id = "act5_tb_ch3_oprah_roasts_meta",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "HONEY, IT IS THE YEAR 2026! THAT 1978 ARKANSAS CATTLE TRADE IS FORTY-EIGHT YEARS OLD! Zohran Cardamom wasn't even BORN until 1991—thirteen years AFTER those cows retired! Let the 1978 cows rest in peace!",
            nextNodeId = "act5_tb_ch3_musk_hub"
        ),
        DialogueNode(
            id = "act5_tb_ch3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Musk-X just leaped into a Dark Gothic MAGA Star-Jump and hurled a steel ball through the Cybertruck window! Zohran Cardamom and Mayor Swagger, engage!",
            triggerBattleEncounterId = "act5_track_b_musk_starjump_battle",
            nextNodeId = "act5_tb_ch3_post_battle"
        ),
        DialogueNode(
            id = "act5_tb_ch3_post_battle",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "We fixed that Cybertruck in post, and our HP and MP are back at 100%! Now let's storm the Department of Government Efficiency ('DOGE') in Chapter 4—where JD Pants, Wizard Kash, and Foghorn Kennedy are guarding the door!",
            changeSceneId = SCENE_ACT5_TB_CH4_DOGE.id,
            nextNodeId = "act5_tb_ch4_intro"
        ),

        // =========================================================================
        // ACT V (TRACK B) - CHAPTER 4: SHIBA INU BUREAUCRACY VS. SPRINKLE DONUTS & WIZARD BOOKS (2026)
        // =========================================================================
        DialogueNode(
            id = "act5_tb_ch4_intro",
            changeSceneId = SCENE_ACT5_TB_CH4_DOGE.id,
            speaker = DialogueSpeaker.NARRATOR_KJP,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside the Department of Government Efficiency ('DOGE') hearing chamber! Beneath a giant Shiba Inu meme banner stand JD Pants with a box of 'whatever makes sense' glazed donuts, Wizard Kash with his \$19.99 'Plot Against the King' children's book, and Senator Foghorn Kennedy with his Oxford law degree disguised as a Louisiana porch drawl!",
            nextNodeId = "act5_tb_ch4_vance_banter"
        ),
        DialogueNode(
            id = "act5_tb_ch4_vance_banter",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.RIGHT,
            text = "Hold it right there, 'Mr. Cardamom'! How long have you been a democratic socialist? Okay, good. How long has that chapati truck been parked outside? Okay, good. Between my sprinkle donuts, Kash's wizard book, and Senator Kennedy telling you to call a crackhead, your five city-owned grocery stores are getting DOGE'd!",
            nextNodeId = "act5_tb_ch4_swagger_reply"
        ),
        DialogueNode(
            id = "act5_tb_ch4_swagger_reply",
            speaker = DialogueSpeaker.MAYOR_SWAGGER,
            side = SpeakerSide.LEFT,
            text = "First of all, JD, nobody orders 'some sprinkle stuff, whatever makes sense' in a New York bakery without getting kicked out to New Jersey! Second, Zohran and I brought fresh chapatis, midnight sea bass, and the Contraband Baby-Doll! Let's clear the DOGE Vanguard!",
            nextNodeId = "act5_tb_ch4_battle_trigger"
        ),
        DialogueNode(
            id = "act5_tb_ch4_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE DOGE VANGUARD BOSS RUSH, HONEY! Zohran Cardamom & Mayor Swagger vs. JD Pants, Wizard Kash, and Senator Foghorn Kennedy! RING THE BELL!",
            triggerBattleEncounterId = "act5_track_b_doge_squad_battle",
            nextNodeId = "act5_tb_ch4_post_battle"
        ),
        DialogueNode(
            id = "act5_tb_ch4_post_battle",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "The DOGE Vanguard is down, our HP and MP are 100% restored, and only one battle remains in the entire game: Chapter 5, the 2026 Bipartisan Grand Finale against Commander America, Musk-X, and the Red Wooden Diet Coke Valet Button!",
            changeSceneId = SCENE_ACT5_TB_CH5_FINALE.id,
            nextNodeId = "act5_tb_ch5_intro"
        ),

        // =========================================================================
        // ACT V (TRACK B) - CHAPTER 5: CHAPATIS VS. DIET COKE BUTTONS — THE BIPARTISAN CURTAIN CALL (2026 FINALE)
        // =========================================================================
        DialogueNode(
            id = "act5_tb_ch5_intro",
            changeSceneId = SCENE_ACT5_TB_CH5_FINALE.id,
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to the 2026 Bipartisan Grand Finale Stage! Across the golden arena stand Commander America in his McDonald's Fry Apron and Neon-Orange Garbage Vest, Musk-X in his Dark Gothic MAGA hat, and the Red Wooden Diet Coke Valet Box!",
            nextNodeId = "act5_tb_ch5_commander_challenge"
        ),
        DialogueNode(
            id = "act5_tb_ch5_commander_challenge",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.RIGHT,
            text = "Look who made it to the Grand Finale, folks—'Mr. Cardamom' in his Nani rap apron and Mayor Swagger with his Turkish Airlines boarding pass and midnight sea bass! I've got twelve Diet Cokes on ice, a gold Sharpie, 'Concepts of a Plan,' and Musk-X jumping into a giant X behind me! Let's see what you've got!",
            nextNodeId = "act5_tb_ch5_zohran_response"
        ),
        DialogueNode(
            id = "act5_tb_ch5_zohran_response",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.LEFT,
            text = "For Hill-Rod's Chappaqua server, Nancy's \$24,000 Sub-Zero ice-cream freezers, Joe's 10-clip Badakathcare megaphone, Kamala's Coconut Tree context, Mayor Swagger's Contraband Baby-Doll, and my Astoria chapati truck—let's bring this house down!",
            nextNodeId = "act5_tb_ch5_finale_battle_trigger"
        ),
        DialogueNode(
            id = "act5_tb_ch5_finale_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "IT'S THE 2026 GRAND FINALE SHOWDOWN, EVERYBODY! Zohran Cardamom ('Mr. Cardamom') & Mayor Swagger vs. Commander America, Musk-X & the Diet Coke Valet Turret! RING THE FINAL BELL!",
            triggerBattleEncounterId = "act5_track_b_grand_finale",
            nextNodeId = "act5_bipartisan_curtain_call"
        ),

        // =========================================================================
        // UNIFIED 2026 BIPARTISAN CURTAIN-CALL EPILOGUE (ALL 6 NARRATORS + FULL CAST)
        // =========================================================================
        DialogueNode(
            id = "act5_bipartisan_curtain_call",
            changeSceneId = SCENE_ACT8_CH5_INAUGURATION.id,
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "LADIES AND GENTLEMEN, ALL FIVE ACTS (2015–2026) ARE COMPLETE! All six narrators—Kayleigh Binder-Tabs, Karoline Podium-Boss, Hulkster 24-Pythons, Jen Circle-Back, Karine Hatch-Act, and Oprah Free-Car—are sharing one giant desk as both sides of the aisle step out for the Bipartisan Curtain Call!",
            nextNodeId = "act5_curtain_call_psaki"
        ),
        DialogueNode(
            id = "act5_curtain_call_psaki",
            speaker = DialogueSpeaker.NARRATOR_PSAKI,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Over forty-eight chapters of political history, we proved two immutable laws of American politics: first, every single politician on both sides of the aisle generates better comedy in real life than fiction could ever invent; and second, neither side will EVER let a 10-, 20-, or 48-year-old talking point go!",
            nextNodeId = "act5_curtain_call_oprah"
        ),
        DialogueNode(
            id = "act5_curtain_call_oprah",
            speaker = DialogueSpeaker.NARRATOR_OPRAH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "YOU GET A DIET COKE BUTTON! YOU GET A \$24,000 SUB-ZERO ICE CREAM FREEZER! YOU GET A DOUBLE-SCOOP CHOCOLATE CHIP WAFFLE CONE! YOU GET A COCONUT TREE! YOU GET A DARK GOTHIC MAGA STAR-JUMP! AND YOU GET A SIZZLING NANI CHAPATI, EVERYBODY!",
            nextNodeId = "act8_ch5_hulk_finale"
        ),
        DialogueNode(
            id = "act5_kennedy_joins",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Next time you're in trouble, call a crackhead!",
            nextNodeId = "act6_ch1_intro"
        ),
        DialogueNode(
            id = "act6_ch1_intro",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "You think you just fell out of a coconut tree? You exist in the context of all in which you live and what came before you!",
            nextNodeId = "act6_ch1_border_hub"
        ),
        DialogueNode(
            id = "act6_ch1_border_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Act VI Hub:",
            choices = listOf(
                DialogueChoice("a6c1_excuse", "Haven't been to Europe either", listOf("europe", "excuse", "border"), "act6_ch1_czar_excuse", completionFlag = "a6c1_excuse_done"),
                DialogueChoice("a6c1_proceed", "Proceed to the Venn Diagram Mirage", listOf("proceed", "venn", "advance"), "act6_ch2_intro")
            )
        ),
        DialogueNode(
            id = "act6_ch1_czar_excuse",
            setFlagOnEnter = "a6c1_excuse_done",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "We've been to the border! And I haven't been to Europe! *Laughs* I don't understand the point that you're making!",
            nextNodeId = "act6_ch1_border_hub"
        ),
        DialogueNode(
            id = "act6_ch2_intro",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "I love Venn diagrams! Always ask, is there a Venn diagram for this? Those three circles!",
            nextNodeId = "act6_ch3_intro"
        ),
        DialogueNode(
            id = "act6_ch3_intro",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "Who doesn't love a yellow school bus, right? Can you raise your hand if you love a yellow school bus?!",
            nextNodeId = "act6_ch4_intro"
        ),
        DialogueNode(
            id = "act6_ch4_intro",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "The significance of the passage of time, right? The significance of the passage of time!",
            nextNodeId = "act6_ch5_intro"
        ),
        DialogueNode(
            id = "act6_ch5_intro",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "What can be, unburdened by what has been!",
            nextNodeId = "act7_ch1_intro"
        ),
        DialogueNode(
            id = "act7_ch1_intro",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.LEFT,
            text = "Entering HQ carrying a porcelain bathroom sink—let that sink in!",
            nextNodeId = "act7_ch1_gauntlet_hub"
        ),
        DialogueNode(
            id = "act7_ch1_gauntlet_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Act VII Hub:",
            choices = listOf(
                DialogueChoice("a7c1_mugshot", "Review the Fulton County Mugshot & Butler Star-Jump", listOf("mugshot", "pose", "defiance"), "act7_ch1_mugshot_defiance", completionFlag = "a7c1_mugshot_done"),
                DialogueChoice("a7c1_proceed", "Proceed to the next scene", listOf("proceed", "advance"), "act7_ch2_intro")
            )
        ),
        DialogueNode(
            id = "act7_ch1_mugshot_defiance",
            setFlagOnEnter = "a7c1_mugshot_done",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Never surrender, folks—and watch Musk-X do a mid-air X-jump behind the podium!",
            nextNodeId = "act7_ch1_gauntlet_hub"
        ),
        DialogueNode(
            id = "act7_ch2_intro",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Inside the hearing chamber!",
            nextNodeId = "act7_ch3_intro"
        ),
        DialogueNode(
            id = "act7_ch3_intro",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.LEFT,
            text = "I'm not just MAGA—I'm dark, gothic MAGA!",
            nextNodeId = "act7_ch4_intro"
        ),
        DialogueNode(
            id = "act7_ch4_intro",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Fight! Fight! Fight!",
            nextNodeId = "act7_ch5_intro"
        ),
        DialogueNode(
            id = "act7_ch5_intro",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "LET TRUMPAMANIA RUN WILD, BROTHER!",
            nextNodeId = "act8_ch1_intro"
        ),
        DialogueNode(
            id = "act8_ch1_intro",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "My name is Joe Biden. I'm Dr. Jill Biden's husband. And I eat Jeni's Chocolate Chip ice cream! I came down because I heard there was chocolate chip ice cream!",
            nextNodeId = "act8_ch1_bunker_hub"
        ),
        DialogueNode(
            id = "act8_ch1_bunker_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Act VIII Ice-Cream Bunker Hub:",
            choices = listOf(
                DialogueChoice("a8c1_lid", "Call a 9:00 AM lid for a double-scoop chocolate chip waffle cone", listOf("lid", "ice cream", "chocolate chip"), "act8_ch1_lid_called", completionFlag = "a8c1_lid_done"),
                DialogueChoice("a8c1_proceed", "Proceed to the Teleprompter Platform", listOf("proceed", "advance"), "act8_ch2_intro")
            )
        ),
        DialogueNode(
            id = "act8_ch1_lid_called",
            setFlagOnEnter = "a8c1_lid_done",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "Our economy is strong as hell, man! *Licks a Baskin-Robbins chocolate chip waffle cone*",
            nextNodeId = "act8_ch1_bunker_hub"
        ),
        DialogueNode(
            id = "act8_ch2_intro",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "It is noteworthy that the percentage of women who register to vote and cast a ballot is consistently higher than the percentage of the men who do so. End of quote. Repeat the line.",
            nextNodeId = "act8_ch3_intro"
        ),
        DialogueNode(
            id = "act8_ch3_intro",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "I'll lead an effective strategy to mobilize truinanashabbadepressure! And Badakathcare! We finally beat Medicare!",
            nextNodeId = "act8_ch4_intro"
        ),
        DialogueNode(
            id = "act8_ch4_intro",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "Corn Pop was a bad dude! And he ran a bunch of bad boys!",
            nextNodeId = "act8_ch5_intro"
        ),
        DialogueNode(
            id = "act8_ch5_intro",
            speaker = DialogueSpeaker.ZOHRAN_CARDAMOM,
            side = SpeakerSide.RIGHT,
            text = "Yo, it's Mr. Cardamom pulling up in the chapati truck with Nani! Freeze the rent and free the Q70 buses!",
            nextNodeId = "act8_ch5_hulk_finale"
        ),
        DialogueNode(
            id = "act8_ch5_hulk_finale",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THAT'S A WRAP ON ALL FIVE ACTS (2015–2026), BROTHER! 48 CHAPTERS OF 100% FACT-CHECKED, EQUAL-OPPORTUNITY BIPARTISAN SATIRE—THANK YOU FOR PLAYING COMMANDER AMERICA RPG!"
        ),
        DialogueNode(
            id = "act8_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Inside the Cosmic Ice-Cream Citadel, Joe Naptime clutches two melting scoops of chocolate chip ice cream.",
            choices = listOf(
                DialogueChoice("a8_confront", "Ask Joe about Corn Pop and Badakathcare", listOf("confront", "biden", "malarkey", "ice cream", "vanilla"), "act8_game_over_hulk"),
                DialogueChoice("a8_barack", "Ask Barry Teleprompter about his tan suit", listOf("barack", "shadows", "puppet", "obama", "teleprompter"), "act8_game_over_hulk")
            )
        ),
        DialogueNode(
            id = "act8_game_over_hulk",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WHATCHA GONNA DO WHEN EQUAL-OPPORTUNITY SATIRE RUNS WILD ON YOU, BROTHER?!"
        )
    ).associateBy { it.id }

    val INITIAL_SCENE: StoryScene get() = SCENE_ACT1_ESCALATOR
    val INITIAL_NODE: DialogueNode get() = ALL_NODES["act1_intro"] ?: ALL_NODES.values.first()

}
