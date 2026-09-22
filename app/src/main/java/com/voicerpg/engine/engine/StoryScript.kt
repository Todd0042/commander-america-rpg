package com.voicerpg.engine.engine

import com.voicerpg.engine.model.DialogueChoice
import com.voicerpg.engine.model.DialogueNode
import com.voicerpg.engine.model.DialogueSpeaker
import com.voicerpg.engine.model.SpeakerSide
import com.voicerpg.engine.model.StoryScene

object StoryScript {

    // -------------------------------------------------------------------------
    // 8-Act Story Scenes
    // -------------------------------------------------------------------------
    val SCENE_ACT1_CH1_ESCALATOR = StoryScene(
        id = "scene_act1_ch1_escalator",
        name = "Trump Tower Atrium & Escalator",
        chapterTitle = "Act I - Ch 1: The Golden Descent (2015)",
        backgroundAsset = "story/golden_escalator.jpg",
        initialNodeId = "act1_ch1_intro",
        ambientDescription = "Golden light gleams off polished brass escalators as patriotic cheers echo through the marble atrium."
    )

    // Legacy alias
    val SCENE_ACT1_ESCALATOR = SCENE_ACT1_CH1_ESCALATOR

    val SCENE_ACT1_CH2_DEBATE = StoryScene(
        id = "scene_act1_ch2_debate",
        name = "Quicken Loans Arena Debate Stage",
        chapterTitle = "Act I - Ch 2: The Primary Debate Arena (2015)",
        backgroundAsset = "story/primary_debate.jpg",
        initialNodeId = "act1_ch2_intro",
        ambientDescription = "Blinding studio spotlights illuminate sixteen rival podiums and a hostile press panel."
    )

    val SCENE_ACT1_CH3_RALLY = StoryScene(
        id = "scene_act1_ch3_rally",
        name = "Manchester Airport Hangar",
        chapterTitle = "Act I - Ch 3: The Primary Sweep (2016)",
        backgroundAsset = "story/airport_rally.jpg",
        initialNodeId = "act1_ch3_intro",
        ambientDescription = "Sub-zero blizzard winds howl outside while fifteen thousand roaring patriots pack the heated hangar."
    )

    // Act II - Chapter 1: The Inaugural Address & Crowd Size Dispute (National Mall)
    val SCENE_ACT2_CH1_MALL = StoryScene(
        id = "scene_act2_ch1_mall",
        name = "National Mall & Capitol Steps",
        chapterTitle = "Act II - Ch 1: The Inaugural Address (Jan 20, 2017)",
        backgroundAsset = "story/national_mall.jpg",
        initialNodeId = "act2_ch1_intro",
        ambientDescription = "The Capitol steps gleam under a crisp January sky as tens of thousands of American patriots pack the National Mall.",
        musicAsset = "audio/music/bgm_title.ogg"
    )

    // Act II - Chapter 2: Entering the Oval Citadel (The Oval Office)
    val SCENE_ACT2_CH2_OVAL = StoryScene(
        id = "scene_act2_ch2_oval",
        name = "The Oval Office — Resolute Desk",
        chapterTitle = "Act II - Ch 2: The Executive Pen (Jan 2017)",
        backgroundAsset = "story/oval_citadel.jpg",
        initialNodeId = "act2_ch2_intro",
        ambientDescription = "Gold drapes replace the old burgundy curtains. The Resolute Desk is cleared of bobbleheads and ready for historic executive action.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    // Act II - Chapter 3: Confronting Madam Speaker (Capitol Rotunda)
    val SCENE_ACT2_CH3_ROTUNDA = StoryScene(
        id = "scene_act2_ch3_rotunda",
        name = "Capitol Rotunda & Committee Hall",
        chapterTitle = "Act II - Ch 3: The Stock-Trading Lich (2017)",
        backgroundAsset = "story/capitol_rotunda.jpg",
        initialNodeId = "act2_ch3_intro",
        ambientDescription = "Marble columns tower above as Pelosi's microchip options spike on the trading terminal — right before the regulatory vote.",
        musicAsset = "audio/music/bgm_intrigue.ogg"
    )

    // Legacy alias: the existing act2_oval still points to Ch1 (backward compat)
    val SCENE_ACT2_OVAL = StoryScene(
        id = "scene_act2_oval",
        name = "The Oval Citadel",
        chapterTitle = "Act II: The Inaugural Siege & Media Wars (2017)",
        backgroundAsset = "story/oval_citadel.jpg",
        initialNodeId = "act2_ch1_intro",
        ambientDescription = "Gilded presidential drapes frame the Oval Office where the Deep State swamp still lurks.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )


    // Act III - Chapter 1: The Ohio Steel Mill Awakening (Youngstown / Lordstown, OH)
    val SCENE_ACT3_CH1_STEEL_MILL = StoryScene(
        id = "scene_act3_ch1_steel_mill",
        name = "Lordstown Steel Mill — Mahoning Valley, Ohio",
        chapterTitle = "Act III - Ch 1: The Ohio Awakening (2018)",
        backgroundAsset = "story/steel_mill.jpg",
        initialNodeId = "act3_ch1_intro",
        ambientDescription = "Blast furnaces roar back to life along the Mahoning River as union workers in hardhats pack the mill floor holding American flags."
    )

    // Act III - Chapter 2: The USMCA Renegotiation Showdown
    val SCENE_ACT3_CH2_USMCA = StoryScene(
        id = "scene_act3_ch2_usmca",
        name = "USMCA Drafting Room — Washington, D.C.",
        chapterTitle = "Act III - Ch 2: The Trade War Table (2018)",
        backgroundAsset = "story/trade_room.jpg",
        initialNodeId = "act3_ch2_intro",
        ambientDescription = "Stacks of NAFTA binders line the walls, each one representing decades of outsourced American jobs. Time to rewrite the rules."
    )

    // Act III - Chapter 3: The Demilitarized Zone Flight (Air Force One)
    val SCENE_ACT3_CH3_AF1 = StoryScene(
        id = "scene_act3_ch3_af1",
        name = "Air Force One — En Route to the DMZ",
        chapterTitle = "Act III - Ch 3: The Diplomatic Flight (2018)",
        backgroundAsset = "story/air_force_one.jpg",
        initialNodeId = "act3_ch3_intro",
        ambientDescription = "Air Force One banks over the Pacific at 41,000 feet. Career diplomats in the press pool are visibly sweating."
    )

    // Act III - Chapter 4: The Crimson Summit of Peace (38th Parallel)
    val SCENE_ACT3_CH4_SUMMIT = StoryScene(
        id = "scene_act3_ch4_summit",
        name = "The 38th Parallel Summit Table",
        chapterTitle = "Act III - Ch 4: The Summit of Peace (2018)",
        backgroundAsset = "story/summit_table.jpg",
        initialNodeId = "act3_ch4_intro",
        ambientDescription = "A mahogany conference table sits precisely on the DMZ line. A tower of fast food bags anchors the center. Cameras roll for the entire planet."
    )

    // Legacy alias for the original single Act III scene
    val SCENE_ACT3_SUMMIT = StoryScene(
        id = "scene_act3_summit",
        name = "The Crimson Summit Spire",
        chapterTitle = "Act III: Rocket Man & The Summit of Peace (2018)",
        backgroundAsset = "story/summit_table.jpg",
        initialNodeId = "act3_ch1_intro",
        ambientDescription = "High above the demilitarized zone, red flags ripple in the wind near a table set with fast-food feasts."
    )

    // Act IV - Chapter 1: The FISA Warrant Labyrinth (Hoover Building Sub-Basement)
    val SCENE_ACT4_CH1_FISA = StoryScene(
        id = "scene_act4_ch1_fisa",
        name = "Hoover Building Sub-Basement Archives",
        chapterTitle = "Act IV - Ch 1: The FISA Labyrinth (2019)",
        backgroundAsset = "story/fisa_vault.jpg",
        initialNodeId = "act4_ch1_intro",
        ambientDescription = "Fluorescent bulbs buzz over rows of locked steel filing cabinets where midnight warrant applications are hidden away."
    )

    // Act IV - Chapter 2: The Unredacted Binder Inquest (House Intelligence Vault)
    val SCENE_ACT4_CH2_BINDER = StoryScene(
        id = "scene_act4_ch2_binder",
        name = "House Intelligence Secure Vault (SCIF)",
        chapterTitle = "Act IV - Ch 2: The Unredacted Vault (2019)",
        backgroundAsset = "story/scif_vault.jpg",
        initialNodeId = "act4_ch2_intro",
        ambientDescription = "Heavy acoustic isolation doors seal the room as thick black marker redactions are scrubbed from the original transcripts."
    )

    // Act IV - Chapter 3: The Phantom Subpoena Ambush (Federal Courtroom Steps)
    val SCENE_ACT4_CH3_COURT = StoryScene(
        id = "scene_act4_ch3_court",
        name = "Federal Courtroom Steps & Media Plaza",
        chapterTitle = "Act IV - Ch 3: The Subpoena Swarm (2019)",
        backgroundAsset = "story/courtroom_steps.jpg",
        initialNodeId = "act4_ch3_intro",
        ambientDescription = "Hundreds of flashbulbs pop as process servers and legal commentators brandish stacks of phantom subpoenas."
    )

    // Act IV - Chapter 4: The Mueller Illusion Hoax Climax (Special Counsel Hearing Room)
    val SCENE_ACT4_CH4_HEARING = StoryScene(
        id = "scene_act4_ch4_hearing",
        name = "Special Counsel Hearing Room",
        chapterTitle = "Act IV - Ch 4: The Nothingburger Climax (2019)",
        backgroundAsset = "story/hearing_room.jpg",
        initialNodeId = "act4_ch4_intro",
        ambientDescription = "The wooden gavel rests on the witness dais. Three years of cable hype culminate in zero evidence and total exoneration."
    )

    // Legacy alias for the original single Act IV scene
    val SCENE_ACT4_MUELLER = StoryScene(
        id = "scene_act4_mueller",
        name = "The Chamber of Phantom Hoaxes",
        chapterTitle = "Act IV: The Mueller Illusion Hoax (2019)",
        backgroundAsset = "story/hearing_room.jpg",
        initialNodeId = "act4_ch1_intro",
        ambientDescription = "Spectral paperwork and phantom subpoenas drift through the foggy tribunal hall."
    )

    // Act V - Chapter 1: The 3:00 AM Water Pipe Mystery (State Farm Arena Ballot Storage Room)
    val SCENE_ACT5_CH1_ARENA = StoryScene(
        id = "scene_act5_ch1_arena",
        name = "State Farm Arena — Ballot Floor",
        chapterTitle = "Act V - Ch 1: The 3 AM Water Pipe (2020)",
        backgroundAsset = "story/ballot_arena.jpg",
        initialNodeId = "act5_ch1_intro",
        ambientDescription = "Counting tables sit under dim arena lights after election observers were evacuated following a declared water pipe burst."
    )

    // Act V - Chapter 2: The Senate Inquest (Dirksen Senate Office Building)
    val SCENE_ACT5_CH2_SENATE = StoryScene(
        id = "scene_act5_ch2_senate",
        name = "Senate Committee Hearing Room",
        chapterTitle = "Act V - Ch 2: The Senate Inquest (2020)",
        backgroundAsset = "story/senate_hearing.jpg",
        initialNodeId = "act5_ch2_intro",
        ambientDescription = "Microphones feedback softly against oak dais panels as stonewalling election officials and bureaucratic witnesses squirm in their seats."
    )

    // Act V - Chapter 3: The Southern White House (Mar-a-Lago Terrace & Palm Beach)
    val SCENE_ACT5_CH3_TERRACE = StoryScene(
        id = "scene_act5_ch3_terrace",
        name = "Mar-a-Lago Terrace & Palm Beach",
        chapterTitle = "Act V - Ch 3: The Southern White House (2021)",
        backgroundAsset = "story/maralago_terrace.jpg",
        initialNodeId = "act5_ch3_intro",
        ambientDescription = "Warm Atlantic trade winds whisper through palm fronds outside the Grand Ballroom as world leaders and grassroots patriots arrive in Palm Beach."
    )

    // Act V - Chapter 4: The Deep State Raid on the Golden Closets (Mar-a-Lago Storage Vault)
    val SCENE_ACT5_CH4_CLOSETS = StoryScene(
        id = "scene_act5_ch4_closets",
        name = "Mar-a-Lago Storage Vault & Melania's Closet",
        chapterTitle = "Act V - Ch 4: The Golden Closets (2022)",
        backgroundAsset = "story/storage_vault.jpg",
        initialNodeId = "act5_ch4_intro",
        ambientDescription = "Dozens of armed federal agents in windbreakers surround stacks of storage boxes beside ornate chandeliers and presidential mementos."
    )

    // Legacy alias for the original single Act V scene
    val SCENE_ACT5_MARALAGO = StoryScene(
        id = "scene_act5_maralago",
        name = "The Mar-a-Lago Fortress",
        chapterTitle = "Act V: Midnight Sorcery & The Exile (2020-2021)",
        backgroundAsset = "story/maralago_terrace.jpg",
        initialNodeId = "act5_ch1_intro",
        ambientDescription = "Ocean breezes sweep across golden palm trees surrounding Commander America's exile retreat."
    )

    // Act VI - Chapter 1: The Border Czar Inspection (The Missing Border Wall Gap)
    val SCENE_ACT6_CH1_BORDER = StoryScene(
        id = "scene_act6_ch1_border",
        name = "The Southern Border Wall Gap",
        chapterTitle = "Act VI - Ch 1: The Border Czar Inspection (2022)",
        backgroundAsset = "story/border_wall.jpg",
        initialNodeId = "act6_ch1_intro",
        ambientDescription = "Rusted steel bollards stand unfinished beside stacks of unused wall panels where millions cross unchecked under the Border Czar's watch."
    )

    // Act VI - Chapter 2: The Venn Diagram Fog (Endless loops of unburdened circular logic)
    val SCENE_ACT6_CH2_VENN = StoryScene(
        id = "scene_act6_ch2_venn",
        name = "The Yellow & Blue Venn Mirage",
        chapterTitle = "Act VI - Ch 2: The Venn Diagram Fog (2023)",
        backgroundAsset = "story/venn_fog.jpg",
        initialNodeId = "act6_ch2_intro",
        ambientDescription = "Luminescent yellow and cyan overlapping circles float through swirling fog as circular logic echoes across the plains."
    )

    // Act VI - Chapter 3: The Electric School Bus Mire (Stranded EV Convoy)
    val SCENE_ACT6_CH3_BUS = StoryScene(
        id = "scene_act6_ch3_bus",
        name = "The Stranded EV Bus Depot",
        chapterTitle = "Act VI - Ch 3: The Electric Bus Mire (2023)",
        backgroundAsset = "story/electric_bus.jpg",
        initialNodeId = "act6_ch3_intro",
        ambientDescription = "Dozens of multi-million dollar electric school buses sit parked in deep mud with dead batteries and nowhere to plug in."
    )

    // Act VI - Chapter 4: The Coconut Tree Grove (Floating in the context of all in which you live)
    val SCENE_ACT6_CH4_COCONUT = StoryScene(
        id = "scene_act6_ch4_coconut",
        name = "The Gilded Coconut Grove",
        chapterTitle = "Act VI - Ch 4: The Coconut Tree Grove (2023)",
        backgroundAsset = "story/coconut_grove.jpg",
        initialNodeId = "act6_ch4_intro",
        ambientDescription = "Towering tropical palms sway gently as voices ask if you think you just fell out of a coconut tree."
    )

    // Act VI - Chapter 5: The Unburdened Vice-Sovereign Confrontation (Kamala Boss Battle)
    val SCENE_ACT6_CH5_UNBURDENED = StoryScene(
        id = "scene_act6_ch5_unburdened",
        name = "The Unburdened Throne of What Can Be",
        chapterTitle = "Act VI - Ch 5: The Unburdened Climax (2023)",
        backgroundAsset = "story/unburdened_throne.jpg",
        initialNodeId = "act6_ch5_intro",
        ambientDescription = "A shimmering dais elevated by the passage of time where Kamala the Unburdened delivers the final circular discourse."
    )

    // Legacy alias for the original single Act VI scene
    val SCENE_ACT6_WASTELAND = StoryScene(
        id = "scene_act6_wasteland",
        name = "The Word-Salad Wasteland",
        chapterTitle = "Act VI: The Word-Salad Wasteland (2022-2023)",
        backgroundAsset = "story/unburdened_throne.jpg",
        initialNodeId = "act6_ch1_intro",
        ambientDescription = "Floating venn diagrams and endless loops of circular logic drift over a misty swamp."
    )

    // Act VII - Chapter 1: The 91 Indictment Gauntlet (Fulton County & Lower Manhattan Courts)
    val SCENE_ACT7_CH1_GAUNTLET = StoryScene(
        id = "scene_act7_ch1_gauntlet",
        name = "The Courtroom Steps of Manhattan",
        chapterTitle = "Act VII - Ch 1: The 91 Indictment Gauntlet (2024)",
        backgroundAsset = "story/courtroom_steps.jpg",
        initialNodeId = "act7_ch1_intro",
        ambientDescription = "Reams of docket entries and 91 fabricated counts flutter across the courtroom marble steps."
    )

    // Act VII - Chapter 2: The Gag Order Guillotine (Contempt Hearing with Biased Judges)
    val SCENE_ACT7_CH2_GAG = StoryScene(
        id = "scene_act7_ch2_gag",
        name = "The Silenced Hearing Chamber",
        chapterTitle = "Act VII - Ch 2: The Gag Order Guillotine (2024)",
        backgroundAsset = "story/gag_hearing.jpg",
        initialNodeId = "act7_ch2_intro",
        ambientDescription = "A menacing gavel echoes in a darkened chamber where speaking the truth is punished by unconstitutional contempt orders."
    )

    // Act VII - Chapter 3: The Butler Fairgrounds (July 13, 2024)
    val SCENE_ACT7_CH3_BUTLER = StoryScene(
        id = "scene_act7_ch3_butler",
        name = "The Open Sky at Butler, PA",
        chapterTitle = "Act VII - Ch 3: The Butler Fairgrounds (July 13, 2024)",
        backgroundAsset = "story/butler_fairgrounds.jpg",
        initialNodeId = "act7_ch3_intro",
        ambientDescription = "The hot summer sun shines down over tens of thousands of cheering patriots gathered under an open sky."
    )

    // Act VII - Chapter 4: Steel Ear & Fist Defiance (2024)
    val SCENE_ACT7_CH4_STEEL_EAR = StoryScene(
        id = "scene_act7_ch4_steel_ear",
        name = "The Stage of Steel Resolve",
        chapterTitle = "Act VII - Ch 4: Steel Ear & Fist Defiance (2024)",
        backgroundAsset = "story/steel_ear_miracle.jpg",
        initialNodeId = "act7_ch4_intro",
        ambientDescription = "A defining moment in American history: blood on the cheek, fist raised to the heavens, shouting FIGHT! FIGHT! FIGHT!"
    )

    // Act VII - Chapter 5: The Milwaukee Arena Coronation (2024)
    val SCENE_ACT7_CH5_MILWAUKEE = StoryScene(
        id = "scene_act7_ch5_milwaukee",
        name = "Fiserv Forum Convention Floor",
        chapterTitle = "Act VII - Ch 5: The Milwaukee Arena Coronation (2024)",
        backgroundAsset = "story/rnc_milwaukee.jpg",
        initialNodeId = "act7_ch5_intro",
        ambientDescription = "Twenty thousand arena delegates wear symbolic white ear bandages as balloons and golden banners drop from the rafters."
    )

    // Legacy alias for Act VII
    val SCENE_ACT7_SANDS = StoryScene(
        id = "scene_act7_sands",
        name = "The Sands of Resolve",
        chapterTitle = "Act VII: Sands of Resolve & Steel Ear (2024)",
        backgroundAsset = "story/steel_ear_miracle.jpg",
        initialNodeId = "act7_ch1_intro",
        ambientDescription = "Desert winds howl across ancient ruins as legal writs float like cursed ravens."
    )

    // Act VIII - Chapter 1: The Basement Bunker Breach (Wilmington Virtual Studio)
    val SCENE_ACT8_CH1_BUNKER = StoryScene(
        id = "scene_act8_ch1_bunker",
        name = "Wilmington Virtual Broadcast Bunker",
        chapterTitle = "Act VIII - Ch 1: The Basement Bunker Breach",
        backgroundAsset = "story/basement_bunker.jpg",
        initialNodeId = "act8_ch1_intro",
        ambientDescription = "Green screens and teleprompter rigs hum softly in a climate-controlled Wilmington basement."
    )

    // Act VIII - Chapter 2: The Teleprompter Jammer (Scranton Train Platform)
    val SCENE_ACT8_CH2_TELEPROMPTER = StoryScene(
        id = "scene_act8_ch2_teleprompter",
        name = "The Scranton Train Platform",
        chapterTitle = "Act VIII - Ch 2: The Teleprompter Jammer",
        backgroundAsset = "story/scranton_platform.jpg",
        initialNodeId = "act8_ch2_intro",
        ambientDescription = "A windy train platform where giant scrolling glass teleprompters flash 'PAUSE FOR APPLAUSE'."
    )

    // Act VIII - Chapter 3: The Auto-Pen Phantoms (Executive Order Factory)
    val SCENE_ACT8_CH3_AUTOPEN = StoryScene(
        id = "scene_act8_ch3_autopen",
        name = "The Executive Order Factory",
        chapterTitle = "Act VIII - Ch 3: The Auto-Pen Phantoms",
        backgroundAsset = "story/autopen_factory.jpg",
        initialNodeId = "act8_ch3_intro",
        ambientDescription = "Mechanical robotic pens replicate signature after signature on trillions of dollars in omnibus mandates."
    )

    // Act VIII - Chapter 4: The Malarkey Sovereign Climax (Cosmic Oval Citadel)
    val SCENE_ACT8_CH4_CITADEL = StoryScene(
        id = "scene_act8_ch4_citadel",
        name = "The Cosmic Oval Citadel",
        chapterTitle = "Act VIII - Ch 4: The Malarkey Sovereign Climax",
        backgroundAsset = "story/oval_citadel.jpg",
        initialNodeId = "act8_ch4_intro",
        ambientDescription = "Inside the cosmic Oval Citadel, Biden the Malarkey Sovereign sits in his executive recliner clutching two melting scoops of vanilla ice cream.",
        musicAsset = "audio/music/bgm_oval.ogg"
    )

    // Act VIII - Chapter 5: The Golden Inauguration & Golden Age of America
    val SCENE_ACT8_CH5_INAUGURATION = StoryScene(
        id = "scene_act8_ch5_inauguration",
        name = "The Golden Capitol Steps & Pennsylvania Avenue",
        chapterTitle = "Act VIII - Ch 5: The Golden Inauguration",
        backgroundAsset = "story/golden_inauguration.jpg",
        initialNodeId = "act8_ch5_intro",
        ambientDescription = "Sunshine gleams over the National Mall as millions celebrate the dawn of the Golden Age of America.",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    // Legacy alias for Act VIII
    val SCENE_ACT8_CITADEL_FINAL = StoryScene(
        id = "scene_act8_citadel_final",
        name = "The Final Malarkey Citadel",
        chapterTitle = "Act VIII: Eradicating Malarkey - The Final Showdown",
        backgroundAsset = "story/golden_inauguration.jpg",
        initialNodeId = "act8_ch1_intro",
        ambientDescription = "A cosmic arena powered by infinite scoops of vanilla ice cream and stumbles.",
        musicAsset = "audio/music/bgm_inauguration.ogg"
    )

    val SCENE_COTTAGE = SCENE_ACT1_ESCALATOR

    val ALL_SCENES = mapOf(
        SCENE_ACT1_CH1_ESCALATOR.id to SCENE_ACT1_CH1_ESCALATOR,
        "scene_act1_escalator" to SCENE_ACT1_CH1_ESCALATOR,
        SCENE_ACT1_CH2_DEBATE.id to SCENE_ACT1_CH2_DEBATE,
        SCENE_ACT1_CH3_RALLY.id to SCENE_ACT1_CH3_RALLY,
        SCENE_ACT2_CH1_MALL.id to SCENE_ACT2_CH1_MALL,
        SCENE_ACT2_CH2_OVAL.id to SCENE_ACT2_CH2_OVAL,
        SCENE_ACT2_CH3_ROTUNDA.id to SCENE_ACT2_CH3_ROTUNDA,
        SCENE_ACT2_OVAL.id to SCENE_ACT2_CH1_MALL,   // legacy alias → ch1
        SCENE_ACT3_CH1_STEEL_MILL.id to SCENE_ACT3_CH1_STEEL_MILL,
        SCENE_ACT3_CH2_USMCA.id to SCENE_ACT3_CH2_USMCA,
        SCENE_ACT3_CH3_AF1.id to SCENE_ACT3_CH3_AF1,
        SCENE_ACT3_CH4_SUMMIT.id to SCENE_ACT3_CH4_SUMMIT,
        SCENE_ACT3_SUMMIT.id to SCENE_ACT3_CH1_STEEL_MILL,  // legacy alias → ch1
        SCENE_ACT4_CH1_FISA.id to SCENE_ACT4_CH1_FISA,
        SCENE_ACT4_CH2_BINDER.id to SCENE_ACT4_CH2_BINDER,
        SCENE_ACT4_CH3_COURT.id to SCENE_ACT4_CH3_COURT,
        SCENE_ACT4_CH4_HEARING.id to SCENE_ACT4_CH4_HEARING,
        SCENE_ACT4_MUELLER.id to SCENE_ACT4_CH1_FISA,       // legacy alias → ch1
        SCENE_ACT5_CH1_ARENA.id to SCENE_ACT5_CH1_ARENA,
        SCENE_ACT5_CH2_SENATE.id to SCENE_ACT5_CH2_SENATE,
        SCENE_ACT5_CH3_TERRACE.id to SCENE_ACT5_CH3_TERRACE,
        SCENE_ACT5_CH4_CLOSETS.id to SCENE_ACT5_CH4_CLOSETS,
        SCENE_ACT5_MARALAGO.id to SCENE_ACT5_CH1_ARENA,     // legacy alias → ch1
        SCENE_ACT6_CH1_BORDER.id to SCENE_ACT6_CH1_BORDER,
        SCENE_ACT6_CH2_VENN.id to SCENE_ACT6_CH2_VENN,
        SCENE_ACT6_CH3_BUS.id to SCENE_ACT6_CH3_BUS,
        SCENE_ACT6_CH4_COCONUT.id to SCENE_ACT6_CH4_COCONUT,
        SCENE_ACT6_CH5_UNBURDENED.id to SCENE_ACT6_CH5_UNBURDENED,
        SCENE_ACT6_WASTELAND.id to SCENE_ACT6_CH1_BORDER,   // legacy alias → ch1
        SCENE_ACT7_CH1_GAUNTLET.id to SCENE_ACT7_CH1_GAUNTLET,
        SCENE_ACT7_CH2_GAG.id to SCENE_ACT7_CH2_GAG,
        SCENE_ACT7_CH3_BUTLER.id to SCENE_ACT7_CH3_BUTLER,
        SCENE_ACT7_CH4_STEEL_EAR.id to SCENE_ACT7_CH4_STEEL_EAR,
        SCENE_ACT7_CH5_MILWAUKEE.id to SCENE_ACT7_CH5_MILWAUKEE,
        SCENE_ACT7_SANDS.id to SCENE_ACT7_CH1_GAUNTLET,     // legacy alias → ch1
        SCENE_ACT8_CH1_BUNKER.id to SCENE_ACT8_CH1_BUNKER,
        SCENE_ACT8_CH2_TELEPROMPTER.id to SCENE_ACT8_CH2_TELEPROMPTER,
        SCENE_ACT8_CH3_AUTOPEN.id to SCENE_ACT8_CH3_AUTOPEN,
        SCENE_ACT8_CH4_CITADEL.id to SCENE_ACT8_CH4_CITADEL,
        SCENE_ACT8_CH5_INAUGURATION.id to SCENE_ACT8_CH5_INAUGURATION,
        SCENE_ACT8_CITADEL_FINAL.id to SCENE_ACT8_CH1_BUNKER // legacy alias → ch1
    )

    // -------------------------------------------------------------------------
    // 8-Act Dialogue Graph
    // -------------------------------------------------------------------------
    val ALL_NODES: Map<String, DialogueNode> = listOf(

        // =========================================================================
        // ACT I - CHAPTER 1: THE GOLDEN DESCENT (JUNE 16, 2015)
        // =========================================================================
        DialogueNode(
            id = "act1_ch1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Good evening, America. We are broadcasting live from Trump Tower on Fifth Avenue. The national media dismissed this as a publicity stunt, but the atrium is packed to capacity with thousands of roaring, hard-working Americans. As golden light reflects off the escalator, Commander America begins his historic descent.",
            choices = listOf(
                DialogueChoice("a1_speech", "Deliver the unscripted announcement speech", listOf("speech", "announce", "president", "run", "america"), "act1_ch1_speech"),
                DialogueChoice("a1_swagger", "Showcase signature presidential swagger", listOf("swagger", "hair", "suit", "aura", "shades"), "act1_ch1_swagger"),
                DialogueChoice("a1_media", "Call out the dishonest camera crews in the back", listOf("media", "camera", "fake news", "press", "dishonest"), "act1_ch1_media")
            )
        ),
        // Legacy alias for Act I intro
        DialogueNode(
            id = "act1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Good evening, America. We are broadcasting live from Trump Tower on Fifth Avenue. The national media dismissed this as a publicity stunt, but the atrium is packed to capacity with thousands of roaring, hard-working Americans. As golden light reflects off the escalator, Commander America begins his historic descent.",
            choices = listOf(
                DialogueChoice("a1_speech", "Deliver the unscripted announcement speech", listOf("speech", "announce", "president", "run", "america"), "act1_ch1_speech"),
                DialogueChoice("a1_swagger", "Showcase signature presidential swagger", listOf("swagger", "hair", "suit", "aura", "shades"), "act1_ch1_swagger"),
                DialogueChoice("a1_media", "Call out the dishonest camera crews in the back", listOf("media", "camera", "fake news", "press", "dishonest"), "act1_ch1_media")
            )
        ),
        DialogueNode(
            id = "act1_ch1_swagger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at this magnificent atrium, folks! Tremendous! The fake news said nobody would show up, and we've got thousands spilling out onto Fifth Avenue! Look at that hair—100% real, no stunt doubles, absolutely flawless aura!",
            nextNodeId = "act1_ch1_hulk_cheer"
        ),
        DialogueNode(
            id = "act1_ch1_hulk_cheer",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH, BROTHER! That is the golden stride of a champion! Twenty-four-inch pythons of pure liberty flexin' on the globalist establishment! Tell 'em what time it is, Commander!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_media",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at the cameras back there! Look at them sweating! The red lights are on because their ratings are in the toilet, folks! They don't want to show the crowd, but they can't look away!",
            nextNodeId = "act1_ch1_karoline_note"
        ),
        DialogueNode(
            id = "act1_ch1_karoline_note",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Binder check confirmed! Cable viewership just hit record peaks because real Americans are tuning in to hear the unfiltered truth!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_speech",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Our country is in serious trouble. We don't win anymore. We lose on trade, we lose on the border, we lose to everyone! But that ends today. We are going to bring back our jobs, protect our borders, and put America First!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Commander America pauses on the mid-escalator landing to address the core pillars of the political revolution.",
            choices = listOf(
                DialogueChoice("a1_hub_deal", "Expose disastrous foreign trade deals", listOf("deal", "trade", "jobs", "economy", "tariffs"), "act1_ch1_lore_deal", completionFlag = "a1_lore_deal"),
                DialogueChoice("a1_hub_border", "Outline the southern border strategy", listOf("wall", "border", "security", "door", "southern"), "act1_ch1_lore_wall", completionFlag = "a1_lore_wall"),
                DialogueChoice("a1_hub_swamp", "Vow to drain the unelected D.C. swamp", listOf("swamp", "drain", "lobbyist", "corrupt", "bureaucrat"), "act1_ch1_lore_swamp", completionFlag = "a1_lore_swamp"),
                DialogueChoice("a1_hub_proceed", "Step onto the lobby floor and face the press", listOf("proceed", "lobby", "advance", "step", "floor"), "act1_ch1_lobby_ambush")
            )
        ),
        DialogueNode(
            id = "act1_ch1_lore_deal",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Our politicians get taken to the cleaners! They sign trade deals written by foreign lobbyists that gut our factories in Ohio, Pennsylvania, and Michigan. I wrote The Art of the Deal—we're renegotiating everything!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_lore_wall",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "A country without borders is not a country! We are going to build a great, powerful wall on the southern border. And it's going to have a big, beautiful golden door so people can come in legally!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_lore_swamp",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Career bureaucrats go to Washington on a $174k salary and retire four years later with a $50 million net worth and three K-Street lobbying contracts! We are going to drain the swamp, believe me!",
            nextNodeId = "act1_ch1_hub"
        ),
        DialogueNode(
            id = "act1_ch1_lobby_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "Wait right there! Our anonymous source consensus says you have a 1% chance in the polls! You are violating establishment protocol—shut down this microphone immediately!",
            nextNodeId = "act1_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act1_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The establishment media and corporate lobbyists are swarming the podium with cheap smears! Commander, hit 'em with Tremendous Strike and Art of the Deal!",
            triggerBattleEncounterId = "act1_ch1_press_battle",
            nextNodeId = "act1_ch1_post_battle"
        ),
        // Legacy alias for battle trigger & ambush
        DialogueNode(
            id = "act1_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "Wait right there! Our anonymous source consensus says you have a 1% chance in the polls! You are violating establishment protocol—shut down this microphone immediately!",
            nextNodeId = "act1_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The establishment media and corporate lobbyists are swarming the podium with cheap smears! Commander, hit 'em with Tremendous Strike and Art of the Deal!",
            triggerBattleEncounterId = "act1_ch1_press_battle",
            nextNodeId = "act1_ch1_post_battle"
        ),
        DialogueNode(
            id = "act1_ch1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at them scrambling for the exits! Their cheap microphones are broken and their phony polls just hit rock bottom! The launch is a massive success—now pack your bags, we're taking this fight straight to the primary debate in Cleveland!",
            changeSceneId = SCENE_ACT1_CH2_DEBATE.id,
            nextNodeId = "act1_ch2_intro"
        ),
        // Legacy alias
        DialogueNode(
            id = "act1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at them scrambling for the exits! Their cheap microphones are broken and their phony polls just hit rock bottom! Onward to the primary debate in Cleveland!",
            changeSceneId = SCENE_ACT1_CH2_DEBATE.id,
            nextNodeId = "act1_ch2_intro"
        ),

        // =========================================================================
        // ACT I - CHAPTER 2: THE PRIMARY DEBATE ARENA (AUGUST 2015)
        // =========================================================================
        DialogueNode(
            id = "act1_ch2_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are live from the Quicken Loans Arena in Cleveland, Ohio! The establishment has lined up sixteen focus-grouped career politicians, all wearing color-coordinated ties and reciting 30-second rehearsed soundbites. In the center podium stands Commander America, unscripted and ready to dismantle the field!",
            nextNodeId = "act1_ch2_podium_clash"
        ),
        DialogueNode(
            id = "act1_ch2_podium_clash",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "Commander, financial disclosures show you have donated to campaigns on both sides of the aisle in past decades! How do you answer for that?",
            nextNodeId = "act1_ch2_donor_expose"
        ),
        DialogueNode(
            id = "act1_ch2_donor_expose",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Of course I gave! As a private businessman, you have to give! When politicians call, they beg for money. And two years later, when you need them, they do whatever you ask! The entire system is broken and corrupt, and I know it better than anyone because I was on the inside!",
            nextNodeId = "act1_ch2_rival_interject"
        ),
        DialogueNode(
            id = "act1_ch2_rival_interject",
            speaker = DialogueSpeaker.ESTABLISHMENT_RIVAL,
            side = SpeakerSide.RIGHT,
            text = "Excuse me... that is highly inappropriate decorum. The donor class deserves our deepest reverence and gratitude. Here, take a commemorative focus-group brochure and please... please clap...",
            choices = listOf(
                DialogueChoice("a2_puppets", "Expose their $100M Super PAC puppet strings", listOf("puppets", "pac", "donors", "bought", "money"), "act1_ch2_roast_pac"),
                DialogueChoice("a2_rehearsed", "Mock their teleprompter hand gestures and soundbites", listOf("rehearsed", "soundbite", "teleprompter", "script", "robot"), "act1_ch2_roast_script"),
                DialogueChoice("a2_ratings", "Remind them who brought 24 million viewers to the debate", listOf("ratings", "viewers", "crowd", "popular", "record"), "act1_ch2_roast_ratings")
            )
        ),
        DialogueNode(
            id = "act1_ch2_roast_pac",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at this guy—total puppet! His Super PAC raised $100 million from Wall Street bankers, and he's reading cue cards prepared by fifty focus groups! He can't even blink without a consultant giving the thumbs up!",
            nextNodeId = "act1_ch2_hulk_roar"
        ),
        DialogueNode(
            id = "act1_ch2_roast_script",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "He practiced that 25-second rehearsed response in front of a mirror thirty times this morning! Same hand chop, same robotic smile! The American people are sick and tired of phonies reading scripts!",
            nextNodeId = "act1_ch2_hulk_roar"
        ),
        DialogueNode(
            id = "act1_ch2_roast_ratings",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Twenty-four million people are watching this debate tonight! The highest ratings in cable television history! They didn't tune in for low-energy lectures, they tuned in for a revolution!",
            nextNodeId = "act1_ch2_hulk_roar"
        ),
        DialogueNode(
            id = "act1_ch2_hulk_roar",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH! The arena roof just blew off, brother! The Low-Energy Dynasty is trembling behind their podium! Drop the leg on 'em, Commander!",
            nextNodeId = "act1_ch2_battle_trigger"
        ),
        DialogueNode(
            id = "act1_ch2_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The rival candidates and Super PAC fact-checkers are frantically coordinating an on-stage attack! Shut them down with Presidential Veto and Obama Did It First!",
            triggerBattleEncounterId = "act1_ch2_debate_battle",
            nextNodeId = "act1_ch2_post_battle"
        ),
        DialogueNode(
            id = "act1_ch2_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Total knockout! The focus-group pamphlets are flying everywhere and the dynasty rivals are packing up their campaign buses! Next stop: the freezing snows of New Hampshire for the primary sweep!",
            changeSceneId = SCENE_ACT1_CH3_RALLY.id,
            nextNodeId = "act1_ch3_intro"
        ),

        // =========================================================================
        // ACT I - CHAPTER 3: THE PRIMARY SWEEP (FEBRUARY 2016)
        // =========================================================================
        DialogueNode(
            id = "act1_ch3_intro",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH, BROTHER! Outside this Manchester airplane hangar, it's twenty below zero with two feet of blizzard snow! But inside? Fifteen thousand screaming patriots are packed to the rafters, brother! The energy in this hangar could power the entire eastern seaboard!",
            nextNodeId = "act1_ch3_rally_speech"
        ),
        DialogueNode(
            id = "act1_ch3_rally_speech",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "They told us we couldn't win here in New Hampshire. The establishment spent $150 million running negative TV commercials against us! But the people showed up through the blizzard! We are leading by thirty points! The forgotten men and women of this country will never be forgotten again!",
            choices = listOf(
                DialogueChoice("a3_revival", "Promise the revival of American manufacturing", listOf("manufacturing", "jobs", "factories", "revival", "industry"), "act1_ch3_speech_jobs"),
                DialogueChoice("a3_military", "Vow to rebuild our military and take care of veterans", listOf("military", "veterans", "strength", "vets", "heroes"), "act1_ch3_speech_vets"),
                DialogueChoice("a3_win", "Declare that America is going to start winning again", listOf("winning", "victory", "champion", "tired", "lead"), "act1_ch3_speech_win")
            )
        ),
        DialogueNode(
            id = "act1_ch3_speech_jobs",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "We are going to bring back our steel mills, bring back our manufacturing, and put our people back to work! No more shipping American prosperity overseas while towns across this state rust out!",
            nextNodeId = "act1_ch3_party_boss_ambush"
        ),
        DialogueNode(
            id = "act1_ch3_speech_vets",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Our great veterans have been treated like second-class citizens by corrupt D.C. bureaucrats! We're giving them choice, cutting the wait times, and building the strongest military this world has ever seen!",
            nextNodeId = "act1_ch3_party_boss_ambush"
        ),
        DialogueNode(
            id = "act1_ch3_speech_win",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "We are going to win so much! We're going to win on jobs, win on border security, win on foreign policy! You're going to come to me and say, 'Commander, please, we can't take all this winning!' And I'll say, 'No, we must keep winning!'",
            nextNodeId = "act1_ch3_party_boss_ambush"
        ),
        DialogueNode(
            id = "act1_ch3_party_boss_ambush",
            speaker = DialogueSpeaker.PARTY_BOSS,
            side = SpeakerSide.RIGHT,
            text = "Shut down the music! Shut down this rally! Even if you win every precinct, our secret party bylaws and backroom superdelegates will overturn the primary at the convention! The establishment decides who gets the crown!",
            nextNodeId = "act1_ch3_boss_rebuttal"
        ),
        DialogueNode(
            id = "act1_ch3_boss_rebuttal",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at this boss with his little binder of secret rules! You think you can steal the vote of fifteen thousand roaring patriots in this hangar? Not on my watch, pal! We are taking the primary right now!",
            nextNodeId = "act1_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act1_ch3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The Establishment Party Boss and his dark money machine are trying to stage a backroom coup! Commander, unleash your full power and claim the nomination!",
            triggerBattleEncounterId = "act1_ch3_machine_battle",
            nextNodeId = "act1_ch3_post_battle"
        ),
        DialogueNode(
            id = "act1_ch3_post_battle",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Decision Desk projection: Commander America has swept New Hampshire, South Carolina, and the Super Tuesday map! The establishment bosses have conceded, the rivals have suspended their campaigns, and the presidential nomination is secured!",
            nextNodeId = "act1_ch3_victory_conclusion"
        ),
        DialogueNode(
            id = "act1_ch3_victory_conclusion",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Tell the ground crew to spin up the jet engines on Trump-Force One! Next stop: Pennsylvania Avenue! The Oval Citadel awaits!",
            changeSceneId = SCENE_ACT2_CH1_MALL.id,
            nextNodeId = "act1_post_hulk"
        ),
        DialogueNode(
            id = "act1_post_hulk",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "ONE, TWO, THREE, HE PINNED 'EM, BROTHER! The primary is swept! To the Oval Citadel, brother! Woooo!",
            nextNodeId = "act2_ch1_intro"
        ),

        // =========================================================================
        // ACT II - CHAPTER 1: THE INAUGURAL ADDRESS & CROWD SIZE DISPUTE (JAN 20, 2017)
        // =========================================================================
        DialogueNode(
            id = "act2_ch1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Good afternoon, America — we are broadcasting live from the Capitol steps on this historic January 20th. Commander America has just been sworn in on the Lincoln Bible, and the National Mall is absolutely packed from the reflecting pool all the way to the Washington Monument. The cable networks are already cropping the aerial photography. Unbelievable.",
            nextNodeId = "act2_ch1_address_hub"
        ),
        // Legacy alias: act2_intro → ch1
        DialogueNode(
            id = "act2_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Good afternoon, America — we are broadcasting live from the Capitol steps on this historic January 20th. Commander America has just been sworn in on the Lincoln Bible, and the National Mall is absolutely packed. The cable networks are already cropping the aerial photography.",
            nextNodeId = "act2_ch1_address_hub"
        ),
        DialogueNode(
            id = "act2_ch1_address_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "This is a very historic moment, and I have a few things I want to address before we move into the Oval Citadel.",
            choices = listOf(
                DialogueChoice("a2c1_carnage", "Declare American Carnage is over — American Renewal begins NOW", listOf("carnage", "renewal", "stop", "forgotten", "over"), "act2_ch1_american_carnage", completionFlag = "a2c1_carnage"),
                DialogueChoice("a2c1_crowd", "Call out the cable networks cropping the aerial photography", listOf("crowd", "aerial", "camera", "photos", "networks", "crop"), "act2_ch1_crowd_call", completionFlag = "a2c1_crowd"),
                DialogueChoice("a2c1_media", "Announce the total eradication of American carnage in the media", listOf("media", "fake", "dishonest", "carnage", "pundit"), "act2_ch1_media_smack", completionFlag = "a2c1_media"),
                DialogueChoice("a2c1_proceed", "Take the oath, sign the first executive order, and march into the Oval", listOf("proceed", "march", "oval", "oath", "sign"), "act2_ch1_spin_crew_ambush")
            )
        ),
        DialogueNode(
            id = "act2_ch1_american_carnage",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "This American carnage stops right here and stops right now! The forgotten men and women of our country will be forgotten no longer. January 20th, 2017 will be remembered as the day the people became the rulers of this nation once again!",
            nextNodeId = "act2_ch1_address_hub"
        ),
        DialogueNode(
            id = "act2_ch1_crowd_call",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at those cameras refusing to turn around and show the crowd! Look at the mall — they cropped the aerial photography so you can't see the people stretching all the way back to the monument! Absolute dishonesty!",
            nextNodeId = "act2_ch1_hulk_crowd"
        ),
        DialogueNode(
            id = "act2_ch1_hulk_crowd",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "BROTHER! That is more patriots in one mall than I've seen in thirty years of body-slamming across all fifty states! The lens ain't wide enough to hold 'em all, dude!",
            nextNodeId = "act2_ch1_address_hub"
        ),
        DialogueNode(
            id = "act2_ch1_media_smack",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "I have the Nielsen ratings right here in this binder! The cable pundits had to resort to a fisheye lens and a fog machine to make the crowd look smaller. These are documented facts!",
            nextNodeId = "act2_ch1_address_hub"
        ),
        DialogueNode(
            id = "act2_ch1_spin_crew_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "HALT! The Aerial Spin Crew is deploying! Our anonymous source confirms the crowd was only seventeen people and a golden retriever! We are spinning this story into the stratosphere right now! Deploy the Hostile Fact-Checkers!",
            nextNodeId = "act2_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act2_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The Cable News Aerial Spin Crew is LIVE with fabricated crowd graphics! Commander — demolish them with Tremendous Strike before they edit the footage!",
            triggerBattleEncounterId = "act2_ch1_inauguration_battle",
            nextNodeId = "act2_ch1_post_battle"
        ),
        DialogueNode(
            id = "act2_ch1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Their fisheye lenses are shattered and the Spin Crew has retreated to the green room! Let the record show: the crowd was MASSIVE. Now — to the Oval Citadel! Time to replace those burgundy curtains with something GOLD.",
            changeSceneId = SCENE_ACT2_CH2_OVAL.id,
            nextNodeId = "act2_ch2_intro"
        ),

        // =========================================================================
        // ACT II - CHAPTER 2: ENTERING THE OVAL CITADEL & THE EXECUTIVE PEN (JAN 2017)
        // =========================================================================
        DialogueNode(
            id = "act2_ch2_intro",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH, BROTHER! The golden drapes are hung! The Resolute Desk is cleared! And Commander America is standing there with the biggest stack of executive orders I have ever seen in my life, dude! The Deep State bureaucrats are SHAKING!",
            nextNodeId = "act2_ch2_resolute_hub"
        ),
        DialogueNode(
            id = "act2_ch2_resolute_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Alright, we've got a lot of work to do. Three things on the agenda before I deal with the phone calls from seventeen foreign leaders all competing to be my best friend.",
            choices = listOf(
                DialogueChoice("a2c2_drapes", "Announce the gold drapes are a non-negotiable upgrade", listOf("drapes", "gold", "curtains", "upgrade", "decor"), "act2_ch2_drapes", completionFlag = "a2c2_drapes"),
                DialogueChoice("a2c2_deregulate", "Sign the first wave of deregulation orders with maximum flair", listOf("deregulate", "sign", "orders", "executive", "regulation", "pen"), "act2_ch2_deregulate", completionFlag = "a2c2_deregulate"),
                DialogueChoice("a2c2_tariffs", "Announce reciprocal tariffs on every country that's been ripping us off", listOf("tariffs", "trade", "reciprocal", "unfair", "china", "foreign"), "act2_ch2_tariffs", completionFlag = "a2c2_tariffs"),
                DialogueChoice("a2c2_marco", "Call Marco Rubio in — it's time to unlock the Sanctions Sorcerer", listOf("marco", "rubio", "sanctions", "call", "secretary", "state"), "act2_ch2_marco_arrives")
            )
        ),
        DialogueNode(
            id = "act2_ch2_drapes",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The burgundy curtains are gone. Absolutely gone. We replaced them with beautiful, full-length gold drapes that represent the golden age of America. Someone put that in the official record. This is important.",
            nextNodeId = "act2_ch2_resolute_hub"
        ),
        DialogueNode(
            id = "act2_ch2_deregulate",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "For every one new regulation we add, we cut TEN old ones. That's the deal. Ten to one. I'm signing this right now. *Clicks the sharpie.* Tremendous. This pen has more power than a thousand bureaucrat memos.",
            nextNodeId = "act2_ch2_karoline_count"
        ),
        DialogueNode(
            id = "act2_ch2_karoline_count",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Twenty-six executive orders signed in the first week — a record. My binder has run out of pages for the first time in recorded history.",
            nextNodeId = "act2_ch2_resolute_hub"
        ),
        DialogueNode(
            id = "act2_ch2_tariffs",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "China has been charging us 40% tariffs while we charge them virtually nothing for decades! That ends today. Reciprocal. If you charge us 40, we charge you 40. It's called being fair! Revolutionary concept, I know!",
            nextNodeId = "act2_ch2_resolute_hub"
        ),
        DialogueNode(
            id = "act2_ch2_marco_arrives",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Commander! Marco Rubio reporting for duty — confirmed as Secretary of State and fully operational Sanctions Sorcerer! I've got a leather briefcase full of maximum sanctions, a filibuster scroll that would make the Senate chamber shake, and... *holds up a cold water bottle* ...diplomatic hydration. Let's rearrange some foreign policy!",
            nextNodeId = "act2_ch2_hero_marco_reply"
        ),
        DialogueNode(
            id = "act2_ch2_hero_marco_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Marco! Tremendous! The Sanctions Sorcerer is in the Oval! You're going to be the greatest Secretary of State this country has ever seen — and believe me, I know a lot about great people. I'm surrounded by them. Now let's talk Iran, Venezuela, and China while we sign these orders.",
            nextNodeId = "act2_ch2_hulk_marco"
        ),
        DialogueNode(
            id = "act2_ch2_hulk_marco",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH! The Sanctions Sorcerer has entered the building, BROTHER! Rubio's got that diplomatic intensity in his eyes! The globalists are sweating through their Davos blazers right now!",
            nextNodeId = "act2_ch2_victory_advance"
        ),
        DialogueNode(
            id = "act2_ch2_victory_advance",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Breaking news: Madam Speaker Pelosi has been spotted in the Capitol Rotunda with a Bloomberg terminal, a stack of microchip company prospectuses, and a draft regulatory bill on her desk — simultaneously. We are racing to the Rotunda.",
            changeSceneId = SCENE_ACT2_CH3_ROTUNDA.id,
            nextNodeId = "act2_ch3_intro"
        ),

        // Legacy alias nodes (backward compat with existing tests / save migration)
        DialogueNode(
            id = "act2_archive",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at these old scrolls from 2014! Barack built the exact same border facilities! They clapped for him back then, but when I keep the country safe, they call it an outrage!",
            nextNodeId = "act2_karoline_receipts"
        ),
        DialogueNode(
            id = "act2_karoline_receipts",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "That is an indisputable fact! The press won't report it, but the receipts on those 2014 facilities are right here in the binder!",
            nextNodeId = "act2_ch3_intro"
        ),
        DialogueNode(
            id = "act2_marco_joins",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Hold on, Madam Speaker! You can't crash this economy on our watch! Commander, I brought maximum foreign sanctions, a filibuster that will freeze them in their tracks, and a cold bottle of water. Let's reclaim the chamber!",
            nextNodeId = "act2_hero_marco_reply"
        ),
        DialogueNode(
            id = "act2_hero_marco_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Marco, tremendous to have you! The Sanctions Sorcerer is on the team! Let's hit 'em with tariffs and the presidential veto!",
            nextNodeId = "act2_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act2_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH! RUBIO'S IN THE CORNER, BROTHER! The Sanctions Sorcerer has entered the ring! Say your prayers and hit that Tremendous Strike!",
            triggerBattleEncounterId = "act2_media_battle",
            nextNodeId = "act2_ch3_post_battle"
        ),

        // =========================================================================
        // ACT II - CHAPTER 3: CONFRONTING MADAM SPEAKER & THE STOCK-TRADING LICH (2017)
        // =========================================================================
        DialogueNode(
            id = "act2_ch3_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are LIVE inside the Capitol Rotunda. Madam Speaker Pelosi is standing beneath the dome with a Bloomberg terminal in one hand and a NVIDIA prospectus in the other — and the Semiconductor Oversight Regulatory Act is literally on a table behind her. This is not subtle. This is not even trying to be subtle.",
            nextNodeId = "act2_ch3_pelosi_enter"
        ),
        DialogueNode(
            id = "act2_ch3_pelosi_enter",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.RIGHT,
            text = "Oh, don't mind me... I'm simply... reorganizing my portfolio for purely *patriotic* reasons. The fact that I co-authored this regulatory framework last Tuesday and that the NVIDIA options expire THIS Friday is... a complete coincidence! Everything in Washington is a coincidence!",
            nextNodeId = "act2_ch3_hero_callout"
        ),
        DialogueNode(
            id = "act2_ch3_hero_callout",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "You purchased 500 semiconductor call options THREE DAYS before your own committee's bill passed! Your return rate on 'public service' is 17,000%! Nobody in American history has gotten RICH on a congressional salary! This isn't complicated!",
            nextNodeId = "act2_ch3_marco_expose"
        ),
        DialogueNode(
            id = "act2_ch3_marco_expose",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "I have the EDGAR filings right here. Cross-referencing the trade date against the committee markup schedule — there is a 72-hour window of maximum insider proximity. Maximum Sanctions, engaged! I'm freezing these accounts!",
            nextNodeId = "act2_ch3_pelosi_counter"
        ),
        DialogueNode(
            id = "act2_ch3_pelosi_counter",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.RIGHT,
            text = "How DARE you! I have been doing this for DECADES and nobody has ever had the audacity to READ THE FILINGS! Fact-checkers, DEPLOY! Anonymous sources, to your stations! I am invoking my right to be above accountability!",
            nextNodeId = "act2_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act2_pelosi_confront",
            speaker = DialogueSpeaker.PELOSI,
            side = SpeakerSide.RIGHT,
            text = "Mwahaha! I just timed the market perfectly before passing the bill! My stock portfolio is unstoppable! World chaos is fantastic for my quarterly returns!",
            nextNodeId = "act2_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act2_hero_pelosi_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "You're trading options on government policy! Total insider trading corruption! Check the dates on Barack's cages while you're at it!",
            nextNodeId = "act2_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act2_ch3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH! The Stock-Trading Shadow Lich is ACTIVATED, BROTHER! Anonymous sources are swarming the rotunda! Commander and the Sanctions Sorcerer — body-slam 'em with Tremendous Strike and Maximum Sanctions!",
            triggerBattleEncounterId = "act2_media_battle",
            nextNodeId = "act2_ch3_post_battle"
        ),
        DialogueNode(
            id = "act2_ch3_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Pelosi's insider trade ledger has been confiscated and her NVIDIA options were invalidated! The fact-checkers retreated to the green room and the anonymous sources went back to being anonymous! The Oval Citadel is secure — onward to international diplomacy!",
            changeSceneId = SCENE_ACT3_CH1_STEEL_MILL.id,
            nextNodeId = "act3_ch1_intro"
        ),
        // Legacy alias
        DialogueNode(
            id = "act2_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Pelosi's insider trade ledger was confiscated and her outrage storm shattered! The Oval Citadel is secure... for now!",
            changeSceneId = SCENE_ACT3_CH1_STEEL_MILL.id,
            nextNodeId = "act3_ch1_intro"
        ),

        // =========================================================================
        // ACT III - CHAPTER 1: THE OHIO STEEL MILL AWAKENING (2018)
        // =========================================================================
        DialogueNode(
            id = "act3_ch1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are live from the Lordstown Stamping Plant in Mahoning Valley, Ohio — a mill that the previous administration declared dead. Blast furnaces that haven't roared in years are lit again tonight. The workers didn't believe it was possible. Commander America made it happen.",
            nextNodeId = "act3_ch1_mill_hub"
        ),
        // Legacy alias
        DialogueNode(
            id = "act3_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are live from the Lordstown Stamping Plant in Mahoning Valley, Ohio — a mill that was declared dead. Tonight the blast furnaces roar back to life.",
            nextNodeId = "act3_ch1_mill_hub"
        ),
        DialogueNode(
            id = "act3_ch1_mill_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "These workers were told their jobs were gone forever. The experts said manufacturing was dead. The experts were wrong. We brought it back. We're just getting started.",
            choices = listOf(
                DialogueChoice("a3c1_steel", "Announce the steel and aluminum tariff to protect American industry", listOf("steel", "tariff", "aluminum", "protect", "industry"), "act3_ch1_steel_tariff", completionFlag = "a3c1_steel"),
                DialogueChoice("a3c1_nafta", "Call out NAFTA for gutting the Rust Belt over 25 years", listOf("nafta", "rust belt", "outsource", "gut", "jobs"), "act3_ch1_nafta_callout", completionFlag = "a3c1_nafta"),
                DialogueChoice("a3c1_vance", "Call for JD Vance — the son of the heartland — to join the fight", listOf("vance", "jd", "heartland", "ohio", "rustbelt"), "act3_ch1_vance_arrives")
            )
        ),
        DialogueNode(
            id = "act3_ch1_steel_tariff",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "25% tariff on foreign steel! 10% on aluminum! Other countries have been dumping their cheap product to destroy our mills for decades! That ends! American steel for American infrastructure!",
            nextNodeId = "act3_ch1_mill_hub"
        ),
        DialogueNode(
            id = "act3_ch1_nafta_callout",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "NAFTA was the worst trade deal in the history of the world! Negotiated by people who didn't know what they were doing! In 25 years it cost us 700,000 manufacturing jobs! We're ripping it up and replacing it with something fantastic!",
            nextNodeId = "act3_ch1_karoline_confirm"
        ),
        DialogueNode(
            id = "act3_ch1_karoline_confirm",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Confirmed: 700,000 manufacturing jobs lost under NAFTA. Bureau of Labor Statistics. It's in the binder on page forty-seven.",
            nextNodeId = "act3_ch1_mill_hub"
        ),
        DialogueNode(
            id = "act3_ch1_vance_arrives",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Commander, JD Vance reporting in! I grew up twenty miles from this mill. My grandmother worked a shift press that looked exactly like that one. The establishment globalists wrote this whole valley off as collateral damage. I didn't, and neither did you. The Rustbelt Vanguard is ready to fight. Let's renegotiate everything.",
            nextNodeId = "act3_ch1_hero_vance_reply"
        ),
        DialogueNode(
            id = "act3_ch1_hero_vance_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "JD — tremendous! Pure Appalachian steel and Hillbilly Elegy common sense! You're the future of this country! The Rustbelt Vanguard is ONLINE! Now let's deal with the trade saboteurs trying to shut this mill down again!",
            nextNodeId = "act3_ch1_union_ambush"
        ),
        DialogueNode(
            id = "act3_ch1_union_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "The Globalist Trade Cartel is here! They've been tipped off by the Chamber of Commerce and they're here to shut down the mill with a barrage of cease-and-desist filings and WTO complaint forms! ATTACK!",
            nextNodeId = "act3_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act3_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH, BROTHER! The steel workers are in the Commander's corner! Vance is FIRED UP! Blast those trade saboteurs back to their Brussels boardroom!",
            triggerBattleEncounterId = "act3_ch1_mill_battle",
            nextNodeId = "act3_ch1_post_battle"
        ),
        DialogueNode(
            id = "act3_ch1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The trade cartel retreated! The mill is OPEN! 1,400 American jobs secured by the end of the night! Now — to Washington! We've got a new trade deal to write from scratch!",
            changeSceneId = SCENE_ACT3_CH2_USMCA.id,
            nextNodeId = "act3_ch2_intro"
        ),

        // =========================================================================
        // ACT III - CHAPTER 2: THE USMCA RENEGOTIATION SHOWDOWN (2018)
        // =========================================================================
        DialogueNode(
            id = "act3_ch2_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The NAFTA renegotiation war room is set. Canada and Mexico sent their top trade lawyers and three trolleys of stalling tactics. Commander America and Marco Rubio sit across the table armed with the Art of the Deal and a stack of sanctions so thick it has its own zip code.",
            nextNodeId = "act3_ch2_table_hub"
        ),
        DialogueNode(
            id = "act3_ch2_table_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Gentlemen — and I use the term loosely — NAFTA is dead. We are replacing it with the USMCA. United States-Mexico-Canada Agreement. My people wrote it. It's fantastic. Much better for America. Now let's talk terms.",
            choices = listOf(
                DialogueChoice("a3c2_dairy", "Demand Canadian dairy market access — they've been blocking us for 30 years", listOf("dairy", "canada", "market", "farmers", "milk"), "act3_ch2_dairy", completionFlag = "a3c2_dairy"),
                DialogueChoice("a3c2_autos", "Lock in 75% North American content rules for automobiles", listOf("autos", "cars", "content", "vehicles", "75"), "act3_ch2_autos", completionFlag = "a3c2_autos"),
                DialogueChoice("a3c2_ip", "Enforce intellectual property protections for American innovators", listOf("ip", "intellectual", "patent", "innovation", "protect"), "act3_ch2_ip", completionFlag = "a3c2_ip"),
                DialogueChoice("a3c2_sign", "Slam down the USMCA, sign it, and call it the greatest trade deal ever written", listOf("sign", "deal", "usmca", "greatest", "done"), "act3_ch2_sign_deal")
            )
        ),
        DialogueNode(
            id = "act3_ch2_dairy",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Canada charges our dairy farmers a 270% tariff! Our guys can barely get their product across the border! That is going to be ZERO. Open access. Our farmers are the best in the world — they don't need protection from competition, they just need a fair shot!",
            nextNodeId = "act3_ch2_table_hub"
        ),
        DialogueNode(
            id = "act3_ch2_autos",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Under our sanctions and tariff leverage, we are locking in 75% North American content rules for all vehicles. If you want to sell a car in America, most of it needs to be BUILT in America, Canada, or Mexico. The offshore shell games are over.",
            nextNodeId = "act3_ch2_table_hub"
        ),
        DialogueNode(
            id = "act3_ch2_ip",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "American companies — pharmaceutical, tech, creative — spent billions innovating, and foreign competitors just copy it the next day! Under USMCA, we have ten-year protections for biologics and criminal penalties for trade secret theft! Protection for our geniuses!",
            nextNodeId = "act3_ch2_table_hub"
        ),
        DialogueNode(
            id = "act3_ch2_sign_deal",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Alright, we've covered dairy, autos, and IP. The USMCA is signed! Greatest trade deal replacement in North American history — by far! NAFTA is officially buried. Tell Canada we said 'you're welcome.'",
            nextNodeId = "act3_ch2_hulk_celebrate"
        ),
        DialogueNode(
            id = "act3_ch2_hulk_celebrate",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "BROTHER! NAFTA IS DONE! The greatest trade deal replacement in history! Kayleigh, put that in the record books right NOW!",
            nextNodeId = "act3_ch2_post_battle_transition"
        ),
        DialogueNode(
            id = "act3_ch2_post_battle_transition",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Breaking news: Air Force One is wheels-up and bound for the Korean DMZ. A sitting American president is about to cross the 38th Parallel and meet Chairman Kim for the first time in history. Career diplomats are calling it impossible. Commander America is calling it Tuesday.",
            changeSceneId = SCENE_ACT3_CH3_AF1.id,
            nextNodeId = "act3_ch3_intro"
        ),

        // =========================================================================
        // ACT III - CHAPTER 3: THE DIPLOMATIC FLIGHT ON AIR FORCE ONE (2018)
        // =========================================================================
        DialogueNode(
            id = "act3_ch3_intro",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH, BROTHER! Air Force One is cruising at 41,000 feet over the Pacific Ocean and the press pool in the back is having a collective breakdown! The career diplomats packed their talking points binders from 1953 and nobody told them those are useless now!",
            nextNodeId = "act3_ch3_briefing_hub"
        ),
        DialogueNode(
            id = "act3_ch3_briefing_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "They said you can't negotiate with Rocket Man. They said it was impossible. They've said that about everything we've done. Let me tell you something — nobody has ever tried doing it MY way.",
            choices = listOf(
                DialogueChoice("a3c3_strategy", "Lay out the diplomatic leverage strategy with Marco", listOf("strategy", "leverage", "diplomatic", "approach", "plan"), "act3_ch3_strategy", completionFlag = "a3c3_strategy"),
                DialogueChoice("a3c3_menu", "Review the fast-food diplomatic feast menu for the summit table", listOf("menu", "burger", "food", "feast", "cheeseburger"), "act3_ch3_menu", completionFlag = "a3c3_menu"),
                DialogueChoice("a3c3_vance", "Ask JD Vance what the heartland thinks about peace on the Korean Peninsula", listOf("vance", "heartland", "ohio", "korea", "peace"), "act3_ch3_vance_korea", completionFlag = "a3c3_vance"),
                DialogueChoice("a3c3_interceptors", "Deal with the deep state interceptors trying to derail the flight", listOf("intercept", "deep state", "sabotage", "derail", "stop"), "act3_ch3_interceptor_ambush")
            )
        ),
        DialogueNode(
            id = "act3_ch3_strategy",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Maximum pressure, then maximum diplomacy. We tightened sanctions until his economy squeaked — then we opened a back-channel with a letter hand-delivered through Singapore. The carrot and the biggest stick ever assembled. Classic Sanctions Sorcery.",
            nextNodeId = "act3_ch3_briefing_hub"
        ),
        DialogueNode(
            id = "act3_ch3_menu",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "We've got two dozen Big Macs, thirty cheeseburgers, Filet-O-Fish for the diplomatic delegation, and a Trump Tower chocolate cake as the centerpiece. Nothing says 'world peace' like a Golden Arches feast. Rocket Man is going to LOVE it.",
            nextNodeId = "act3_ch3_briefing_hub"
        ),
        DialogueNode(
            id = "act3_ch3_vance_korea",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "The people of eastern Ohio have lost sons and daughters in Korea. They've been watching that peninsula for 70 years. If Commander America can end the conflict that career diplomats couldn't end in seven decades, those families deserve it more than anyone. Let's get this done.",
            nextNodeId = "act3_ch3_briefing_hub"
        ),
        DialogueNode(
            id = "act3_ch3_interceptor_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "The Deep State Intercept Squadron has scrambled! Anonymous sources are filing emergency cables to sabotage the summit and force the plane to turn around! They cannot allow this summit to succeed — it would destroy the narrative!",
            nextNodeId = "act3_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act3_ch3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The Intercept Squadron is transmitting disinformation to every cable newsroom simultaneously! Commander, Marco, JD — take them down before they hijack the summit story!",
            triggerBattleEncounterId = "act3_ch3_intercept_battle",
            nextNodeId = "act3_ch3_post_battle"
        ),
        DialogueNode(
            id = "act3_ch3_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Intercept Squadron neutralized! The cables are clear! Fasten your seatbelts — we're beginning our descent to the DMZ. Tell the tower we're coming in HOT and we need a table set for twelve with a Big Mac centerpiece.",
            changeSceneId = SCENE_ACT3_CH4_SUMMIT.id,
            nextNodeId = "act3_ch4_intro"
        ),

        // =========================================================================
        // ACT III - CHAPTER 4: THE CRIMSON SUMMIT OF PEACE (2018)
        // =========================================================================
        DialogueNode(
            id = "act3_ch4_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are at the 38th Parallel. No sitting American president has ever stood here and met a North Korean Chairman face-to-face. The world is watching. 60 million viewers. More than any episode of anything, ever. The fast-food tower centerpiece is impeccable.",
            nextNodeId = "act3_ch4_summit_hub"
        ),
        DialogueNode(
            id = "act3_ch4_summit_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Chairman — I brought the Big Macs. Sit down. Let's talk denuclearization, security guarantees, and why you keep testing missiles when you could be eating cheeseburgers.",
            choices = listOf(
                DialogueChoice("a3c4_handshake", "Deploy the legendary Alpha Handshake of Sovereignty", listOf("handshake", "shake", "grip", "alpha", "sovereign"), "act3_ch4_handshake"),
                DialogueChoice("a3c4_button", "Remind him whose button is bigger and actually works", listOf("button", "nuclear", "bigger", "works", "rocket"), "act3_ch4_button"),
                DialogueChoice("a3c4_burger", "Offer the diplomatic fast-food feast as a gesture of good faith", listOf("burger", "food", "feast", "mcdonald", "cheeseburger"), "act3_ch4_burger"),
                DialogueChoice("a3c4_fight", "Tell Rocket Man to prove his strength in honorable combat", listOf("fight", "combat", "duel", "prove", "strength"), "act3_ch4_fight_challenge")
            )
        ),
        DialogueNode(
            id = "act3_ch4_handshake",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The handshake lasted 12 seconds. Firm. Deliberate. Equal pressure. The cameras caught every frame. History will note: this is how you project strength across a conference table.",
            nextNodeId = "act3_ch4_rocket_reaction"
        ),
        DialogueNode(
            id = "act3_ch4_button",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Little Rocket Man — I have buttons. My buttons are much bigger than your buttons. And mine WORKS! Now — are we going to talk about a beautiful peace deal or are we going to talk about buttons all day?",
            nextNodeId = "act3_ch4_rocket_reaction"
        ),
        DialogueNode(
            id = "act3_ch4_burger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Hamberders. The finest diplomatic currency ever invented. Big Mac, Filet-O-Fish, and a Royale with Cheese for the Chairman. Nothing brings world leaders together like American fast food. It's tremendous. It truly is.",
            nextNodeId = "act3_ch4_rocket_reaction"
        ),
        DialogueNode(
            id = "act3_ch4_fight_challenge",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Chairman, you've tested missiles. I've negotiated with some of the toughest people on the planet. Let's settle this the American way — a good old-fashioned battle of wits, strength, and the Art of the Deal!",
            nextNodeId = "act3_ch4_rocket_reaction"
        ),
        DialogueNode(
            id = "act3_ch4_rocket_reaction",
            speaker = DialogueSpeaker.ROCKET_MAN,
            side = SpeakerSide.RIGHT,
            text = "Your handshake... it is formidable. Your buttons... they concern me greatly. Your hamberders... they are actually very good. But I must test your battle strength before I agree to anything! This is a matter of supreme pride and four decades of military tradition!",
            nextNodeId = "act3_ch4_vance_battle_hype"
        ),
        // Legacy compat nodes for existing tests
        DialogueNode(
            id = "act3_shake",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Little Rocket Man, let's talk deal. We have bigger buttons, much bigger, and mine actually works!",
            nextNodeId = "act3_ch4_rocket_reaction"
        ),
        DialogueNode(
            id = "act3_burger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Nothing brings world peace like a stack of golden arches hamberders! Tremendous flavor!",
            nextNodeId = "act3_ch4_rocket_reaction"
        ),
        DialogueNode(
            id = "act3_rocket_reply",
            speaker = DialogueSpeaker.ROCKET_MAN,
            side = SpeakerSide.RIGHT,
            text = "My fire-rockets will... wait! Is that a double cheeseburger?! Still, I must test your battle strength!",
            nextNodeId = "act3_ch4_vance_battle_hype"
        ),
        DialogueNode(
            id = "act3_vance_joins",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Commander, Middle America has your back at this summit. The Rustbelt Vanguard is ready!",
            nextNodeId = "act3_hero_vance_reply"
        ),
        DialogueNode(
            id = "act3_hero_vance_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "JD, fantastic! The Rustbelt Vanguard is here! Rocket Man, meet the future of America!",
            nextNodeId = "act3_ch4_battle_trigger"
        ),
        DialogueNode(
            id = "act3_ch4_vance_battle_hype",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Commander, I've debated union reps, hostile media anchors, and a sitting Vice President. I've never been more ready for a fight than I am right now. Let's show Chairman Kim what American strength looks like when it walks in the room.",
            nextNodeId = "act3_ch4_battle_trigger"
        ),
        DialogueNode(
            id = "act3_ch4_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "LOOK AT THAT HANDSHAKE, BROTHER! Vance and Rubio backing up Commander America! That's Appalachian grit and diplomatic steel! SHOW HIM THE ART OF THE DEAL, COMMANDER!",
            triggerBattleEncounterId = "act3_summit_battle",
            nextNodeId = "act3_ch4_post_battle"
        ),
        // Legacy alias
        DialogueNode(
            id = "act3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "LOOK AT THAT HANDSHAKE, BROTHER! Vance and Rubio backing up Commander America! Show him the Art of the Deal, Commander!",
            triggerBattleEncounterId = "act3_summit_battle",
            nextNodeId = "act3_ch4_post_battle"
        ),
        DialogueNode(
            id = "act3_ch4_post_battle",
            speaker = DialogueSpeaker.ROCKET_MAN,
            side = SpeakerSide.RIGHT,
            text = "Your handshake grip is truly unstoppable. Your hamberders are extraordinary. I... agree to begin denuclearization talks. Also, can I have the rest of the Filet-O-Fish?",
            nextNodeId = "act3_ch4_victory_speech"
        ),
        DialogueNode(
            id = "act3_ch4_victory_speech",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Chairman, you've made an incredible decision! We've achieved something the career diplomats said was impossible! Peace talks — REAL peace talks — on the Korean Peninsula for the first time in 70 years! TREMENDOUS!",
            nextNodeId = "act3_ch4_hulk_close"
        ),
        DialogueNode(
            id = "act3_ch4_hulk_close",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "BROTHER! They said it couldn't be done! The Nobel Committee is probably already on the phone! Commander America just body-slammed 70 years of failed diplomacy onto the mat!",
            changeSceneId = SCENE_ACT4_CH1_FISA.id,
            nextNodeId = "act4_ch1_intro"
        ),
        // Legacy post battle alias
        DialogueNode(
            id = "act3_post_battle",
            speaker = DialogueSpeaker.ROCKET_MAN,
            side = SpeakerSide.RIGHT,
            text = "Your handshake grip is unstoppable! We have achieved complete peaceful harmony!",
            changeSceneId = SCENE_ACT4_CH1_FISA.id,
            nextNodeId = "act4_ch1_intro"
        ),

        // =========================================================================
        // ACT IV - CHAPTER 1: THE FISA WARRANT LABYRINTH (2019)
        // =========================================================================
        DialogueNode(
            id = "act4_ch1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are deep in the sub-basement archives of the J. Edgar Hoover Building. Row after row of rusted government filing cabinets, locked behind three layers of security badges. This is where midnight FISA warrant applications were filed on flimsy tabloid clippings.",
            nextNodeId = "act4_ch1_fisa_hub"
        ),
        // Legacy alias
        DialogueNode(
            id = "act4_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Good morning to everyone except the anonymous sources who pushed three straight years of phantom collusion! My binder is open to zero charges, zero evidence, and 100% total exoneration!",
            nextNodeId = "act4_ch1_fisa_hub"
        ),
        DialogueNode(
            id = "act4_ch1_fisa_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "They spied on our campaign! They said it was a conspiracy theory. Now we're in the basement pulling the actual surveillance orders out of the cabinets!",
            choices = listOf(
                DialogueChoice("a4c1_dossier", "Expose the unverified political opposition dossier", listOf("dossier", "unverified", "steele", "fake", "opposition"), "act4_ch1_dossier_expose", completionFlag = "a4c1_dossier"),
                DialogueChoice("a4c1_warrant", "Demand to see who signed the renewal warrants in secret", listOf("warrant", "signed", "fisa", "secret", "court", "judge"), "act4_ch1_warrant_inquest", completionFlag = "a4c1_warrant"),
                DialogueChoice("a4c1_kash", "Call in Kash Patel — the Intel Inquisitor with the master key", listOf("kash", "patel", "inquisitor", "memo", "fisa", "intel"), "act4_ch1_kash_arrives")
            )
        ),
        DialogueNode(
            id = "act4_ch1_dossier_expose",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "They took a totally unverified opposition dossier paid for by the DNC, brought it to a secret court, and told the judges it was confirmed intelligence! The greatest scandal in American history!",
            nextNodeId = "act4_ch1_hulk_react"
        ),
        DialogueNode(
            id = "act4_ch1_hulk_react",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "BROTHER! That's not intel, that's comic book fan fiction! The 24-inch pythons of justice are about to smash right through those filing cabinets!",
            nextNodeId = "act4_ch1_fisa_hub"
        ),
        DialogueNode(
            id = "act4_ch1_warrant_inquest",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Four different renewals! Every ninety days they signed off on it, knowing full well it was completely fabricated! The American people deserve the names!",
            nextNodeId = "act4_ch1_kayleigh_note"
        ),
        DialogueNode(
            id = "act4_ch1_kayleigh_note",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Inspector General reports later confirmed seventeen separate major omissions and inaccuracies submitted to the FISA court. Documented history.",
            nextNodeId = "act4_ch1_fisa_hub"
        ),
        DialogueNode(
            id = "act4_ch1_kash_arrives",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Commander, Kash Patel on site! I've been digging through these archives for sixteen months. I've got the unredacted FISA memo right here in this binder. The collusion story is a complete phantom hoax. I'm declassifying everything and plugging their leaks right now!",
            nextNodeId = "act4_ch1_hero_kash_reply"
        ),
        DialogueNode(
            id = "act4_ch1_hero_kash_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Kash, beautiful! Nobody knows the binders of truth like Kash! The Intel Inquisitor is in the game! Declassify it all!",
            nextNodeId = "act4_ch1_bureau_ambush"
        ),
        DialogueNode(
            id = "act4_ch1_bureau_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "ALERT! Bureau Archivist phantoms and unauthorized redaction officers are deploying! You cannot read these files! They are protected by lifetime pension immunity!",
            nextNodeId = "act4_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act4_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The Archivist Phantoms are attempting to shred the unredacted pages! Commander, Marco, JD, and Kash — clean house before the shredder starts!",
            triggerBattleEncounterId = "act4_ch1_fisa_battle",
            nextNodeId = "act4_ch1_post_battle"
        ),
        DialogueNode(
            id = "act4_ch1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The shredders are unplugged and the memos are SECURED! The full four-page memo is public! Next stop: the House Intelligence SCIF vault!",
            changeSceneId = SCENE_ACT4_CH2_BINDER.id,
            nextNodeId = "act4_ch2_intro"
        ),

        // =========================================================================
        // ACT IV - CHAPTER 2: THE UNREDACTED BINDER INQUEST (2019)
        // =========================================================================
        DialogueNode(
            id = "act4_ch2_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside the secure subterranean SCIF of the House Intelligence Committee. Steel blast doors, no cell phones, acoustic dampening walls. Across the room sit boxes of redacted transcripts where every single sentence proving innocence was blacked out with felt markers.",
            nextNodeId = "act4_ch2_vault_hub"
        ),
        DialogueNode(
            id = "act4_ch2_vault_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at this! 90% black ink! They blacked out entire paragraphs that said 'Witness confirmed no coordination occurred.' We brought the solvent, folks!",
            choices = listOf(
                DialogueChoice("a4c2_unredact", "Declassify and scrub the black marker redactions", listOf("scrub", "marker", "unredact", "declassify", "ink"), "act4_ch2_scrub_ink", completionFlag = "a4c2_unredact"),
                DialogueChoice("a4c2_transcripts", "Release the complete transcripts to the public immediately", listOf("release", "transcripts", "public", "read", "truth"), "act4_ch2_release_transcripts", completionFlag = "a4c2_transcripts"),
                DialogueChoice("a4c2_witnesses", "Highlight testimony from 50+ witnesses admitting zero collusion", listOf("witnesses", "testimony", "fifty", "zero", "evidence"), "act4_ch2_witness_review", completionFlag = "a4c2_witnesses"),
                DialogueChoice("a4c2_proceed", "Pack the unredacted binder and head to the federal courthouse", listOf("proceed", "courthouse", "steps", "march", "pack"), "act4_ch2_advance_to_court")
            )
        ),
        DialogueNode(
            id = "act4_ch2_scrub_ink",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "I've cross-referenced every redaction against original executive branch authorities. None of this qualifies as classified national defense information — it was classified solely to hide bureaucratic embarrassment. Unredacted!",
            nextNodeId = "act4_ch2_vault_hub"
        ),
        DialogueNode(
            id = "act4_ch2_release_transcripts",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Publish them! Post every single page online! Don't let the cable news networks filter it through their 9:00 PM panels! Let the American people read the raw transcripts!",
            nextNodeId = "act4_ch2_vault_hub"
        ),
        DialogueNode(
            id = "act4_ch2_witness_review",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Fifty-three separate under-oath interviews. Every single official admitted behind closed doors they had zero direct evidence of collusion — while telling Sunday morning television the exact opposite. Pure establishment double-speak.",
            nextNodeId = "act4_ch2_vault_hub"
        ),
        DialogueNode(
            id = "act4_ch2_advance_to_court",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The binder is full, unredacted, and heavy as an anvil! Time to confront the subpoena machine outside the courthouse!",
            nextNodeId = "act4_ch2_hulk_march"
        ),
        DialogueNode(
            id = "act4_ch2_hulk_march",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "MARCH ON 'EM, BROTHER! They thought they could lock the truth in a vault! The Commander America supergroup is rolling out!",
            changeSceneId = SCENE_ACT4_CH3_COURT.id,
            nextNodeId = "act4_ch3_intro"
        ),

        // =========================================================================
        // ACT IV - CHAPTER 3: THE PHANTOM SUBPOENA AMBUSH (2019)
        // =========================================================================
        DialogueNode(
            id = "act4_ch3_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are on the steps of the federal courthouse in Washington, D.C. A dense crowd of cable commentators, process servers, and lawfare retainers has blocked the plaza, waving reams of stamped subpoenas like trading cards.",
            nextNodeId = "act4_ch3_steps_hub"
        ),
        DialogueNode(
            id = "act4_ch3_steps_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at them with their little clipboards! 'Subpoena for this, subpoena for that!' Five hundred subpoenas issued and not one crime found!",
            choices = listOf(
                DialogueChoice("a4c3_cost", "Tally up the 32-million-dollar taxpayer bill for the investigation", listOf("cost", "taxpayer", "millions", "waste", "thirty"), "act4_ch3_taxpayer_cost", completionFlag = "a4c3_cost"),
                DialogueChoice("a4c3_perfect", "Declare the phone call was completely, 100% PERFECT", listOf("perfect", "call", "transcript", "phone", "perfection"), "act4_ch3_perfect_call", completionFlag = "a4c3_perfect"),
                DialogueChoice("a4c3_swat", "Command the team to blast through the subpoena swarm", listOf("swarm", "subpoena", "ambush", "smash", "fight"), "act4_ch3_subpoena_ambush")
            )
        ),
        DialogueNode(
            id = "act4_ch3_taxpayer_cost",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Thirty-two million dollars of hard-working American taxpayer money! Spent on phantom collusion! We could have rebuilt fifty bridges in Ohio for that money!",
            nextNodeId = "act4_ch3_steps_hub"
        ),
        DialogueNode(
            id = "act4_ch3_perfect_call",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "It was a perfect call! Read the transcript! Even their own witnesses admitted nobody was pressured! Absolute total perfection!",
            nextNodeId = "act4_ch3_steps_hub"
        ),
        DialogueNode(
            id = "act4_ch3_subpoena_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "STOP! We are serving Subpoena #412 and #413! You must hand over every napkin, receipt, and tweet from the last five years! Process Server Phantoms, swarm!",
            nextNodeId = "act4_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act4_ch3_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH! The Subpoena Swarm is trying to bury the truth under paper, BROTHER! Hit 'em with Appalachian Fortitude and Maximum Sanctions!",
            triggerBattleEncounterId = "act4_ch3_subpoena_battle",
            nextNodeId = "act4_ch3_post_battle"
        ),
        DialogueNode(
            id = "act4_ch3_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The subpoenas are shredded and the process servers retreated to their green rooms! Now — into the Special Counsel hearing room for the final climax!",
            changeSceneId = SCENE_ACT4_CH4_HEARING.id,
            nextNodeId = "act4_ch4_intro"
        ),

        // =========================================================================
        // ACT IV - CHAPTER 4: THE MUELLER ILLUSION HOAX CLIMAX (2019)
        // =========================================================================
        DialogueNode(
            id = "act4_ch4_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "This is the moment the cable networks spent two solid years hyping. The Special Counsel Hearing Room. Hundreds of reporters packed in. The phantom illusion is about to dissolve under the harsh light of documented facts.",
            nextNodeId = "act4_ch4_hearing_hub"
        ),
        DialogueNode(
            id = "act4_ch4_hearing_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Two years of investigations. 2,800 subpoenas. 500 search warrants. And what did they find? Say it with me, folks!",
            choices = listOf(
                DialogueChoice("a4c4_nothingburger", "Deliver the legendary 'NOTHINGBURGER' verdict", listOf("nothingburger", "nothing", "zero", "exoneration", "collusion"), "act4_ch4_nothingburger"),
                DialogueChoice("a4c4_transcripts", "Present the unredacted transcripts entered into the congressional record", listOf("transcript", "record", "exonerated", "evidence", "facts"), "act4_ch4_record_present"),
                DialogueChoice("a4c4_kash_verdict", "Have Kash Patel deliver the definitive declassified findings", listOf("kash", "findings", "declassified", "verdict", "inquest"), "act4_ch4_kash_verdict"),
                DialogueChoice("a4c4_fight", "Dispel the Phantom Mueller Hoax once and for all in battle", listOf("fight", "battle", "hoax", "phantom", "dispel"), "act4_ch4_phantom_confront")
            )
        ),
        DialogueNode(
            id = "act4_ch4_nothingburger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "A complete and total NOTHINGBURGER! No collusion! No obstruction! Total and complete exoneration! The greatest political hoax ever perpetrated on our nation has completely evaporated!",
            nextNodeId = "act4_ch4_phantom_confront"
        ),
        DialogueNode(
            id = "act4_ch4_record_present",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Entered into the official congressional record: zero evidence of coordination. The Russian collusion story was a fairy tale manufactured by political operatives!",
            nextNodeId = "act4_ch4_phantom_confront"
        ),
        DialogueNode(
            id = "act4_ch4_kash_verdict",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Every intelligence stream was traced back to its origin. The source material was fabricated political oppo. The case is closed, declassified, and documented for history.",
            nextNodeId = "act4_ch4_phantom_confront"
        ),
        DialogueNode(
            id = "act4_ch4_phantom_confront",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "Wait! The narrative... it cannot die! Even with zero evidence, the *walls are closing in*! We must summon the Phantom Mueller Hoax to keep the chyron running!",
            nextNodeId = "act4_ch4_battle_trigger"
        ),
        DialogueNode(
            id = "act4_ch4_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THE WALLS ARE CLOSIN' IN ON THE PHANTOM, BROTHER! Four heroes in the ring: Commander, Marco, Vance, and Kash! Body-slam this hoax straight into the history books!",
            triggerBattleEncounterId = "act4_hoax_battle",
            nextNodeId = "act4_ch4_post_battle"
        ),
        DialogueNode(
            id = "act4_ch4_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The phantom faded into zero evidence! Total exoneration confirmed by history! Pack the golf bags, folks — we're headed to the Mar-a-Lago Fortress for the greatest training arc in American history!",
            changeSceneId = SCENE_ACT5_CH1_ARENA.id,
            nextNodeId = "act5_ch1_intro"
        ),

        // Legacy compatibility nodes for existing tests & saves
        DialogueNode(
            id = "act4_transcripts",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "It was a perfect call! Absolute perfection! Read the transcript, folks!",
            nextNodeId = "act4_kayleigh_brief"
        ),
        DialogueNode(
            id = "act4_kayleigh_brief",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The official transcripts have been made public, confirming word-for-word that the phantom narrative promoted by cable pundits has collapsed entirely.",
            nextNodeId = "act4_nothing"
        ),
        DialogueNode(
            id = "act4_nothing",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "No collusion! Total exoneration! Hundreds of millions spent on a complete NOTHINGBURGER!",
            nextNodeId = "act4_kash_joins"
        ),
        DialogueNode(
            id = "act4_kash_joins",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Commander, check out what I just brought into the light. I've got the unredacted FISA memo right here in this binder. The collusion story is a complete phantom hoax. I'm declassifying everything and plugging their leaks right now!",
            nextNodeId = "act4_hero_kash_reply"
        ),
        DialogueNode(
            id = "act4_hero_kash_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Kash, beautiful! Nobody knows the binders of truth like Kash! Declassify it all!",
            nextNodeId = "act4_battle_trigger"
        ),
        DialogueNode(
            id = "act4_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The memos are unredacted and the facts are in the binder! Commander, Marco, JD, and Kash—unleash the Art of the Deal and blast this phantom back to the green room!",
            triggerBattleEncounterId = "act4_hoax_battle",
            nextNodeId = "act4_ch4_post_battle"
        ),
        DialogueNode(
            id = "act4_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The phantom faded into zero evidence! Another witch hunt shattered!",
            changeSceneId = SCENE_ACT5_CH1_ARENA.id,
            nextNodeId = "act5_ch1_intro"
        ),

        // =========================================================================
        // ACT V - CHAPTER 1: THE 3:00 AM WATER PIPE MYSTERY (2020)
        // =========================================================================
        DialogueNode(
            id = "act5_ch1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We take you live to the ballot counting floor at State Farm Arena in Atlanta. It is late on election night. Commander America holds an insurmountable lead across the swing states. Suddenly, arena officials order the building evacuated, claiming a catastrophic water main burst.",
            nextNodeId = "act5_ch1_arena_hub"
        ),
        // Legacy alias
        DialogueNode(
            id = "act5_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are live tonight covering the mysterious 3 AM ballot spikes and the sudden cessation of counting across the battlegrounds.",
            nextNodeId = "act5_ch1_arena_hub"
        ),
        DialogueNode(
            id = "act5_ch1_arena_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "A water pipe? Folks, it was an overflowing urinal three concourses away! They told Republican observers to pack up and go home, and the second the doors locked, look what came rolling out!",
            choices = listOf(
                DialogueChoice("a5c1_pipe", "Investigate the phantom water pipe excuse", listOf("pipe", "water", "plumber", "burst", "leak"), "act5_ch1_pipe_investigate", completionFlag = "a5c1_pipe"),
                DialogueChoice("a5c1_suitcases", "Check the wheeled ballot trunks pulled from under black skirts", listOf("suitcases", "trunks", "tables", "black", "cloth"), "act5_ch1_suitcases_check", completionFlag = "a5c1_suitcases"),
                DialogueChoice("a5c1_observe", "Call out the illegal evacuation of bipartisan poll watchers", listOf("observers", "watchers", "sent home", "doors", "illegal"), "act5_ch1_observers_sent_home", completionFlag = "a5c1_observe"),
                DialogueChoice("a5c1_confront", "Confront the Midnight Ballot Courier and Burst Pipe Illusionist", listOf("confront", "fight", "plumber", "courier", "spike"), "act5_ch1_golem_ambush")
            )
        ),
        DialogueNode(
            id = "act5_ch1_pipe_investigate",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "They claimed the arena was flooding! Karoline, did anyone call 911? Did anyone call the city water authority? Did anyone even grab a mop?!",
            nextNodeId = "act5_ch1_karoline_plumber"
        ),
        DialogueNode(
            id = "act5_ch1_karoline_plumber",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Binder receipts confirmed! Official county work logs show zero calls to the water department, zero emergency shutoffs, and an arena incident ticket for a slow-draining toilet. Case documented!",
            nextNodeId = "act5_ch1_arena_hub"
        ),
        DialogueNode(
            id = "act5_ch1_suitcases_check",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Security cameras recorded every angle in 4K. The second the observers walked out into the parking lot, four workers pulled wheeled containers from under black tablecloths and scanned the same stacks three and four times in a row without a single monitor present.",
            nextNodeId = "act5_ch1_arena_hub"
        ),
        DialogueNode(
            id = "act5_ch1_observers_sent_home",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "In Florida, if election supervisors order bipartisan observers to leave and then keep running secret ballots in the dark at 3:00 AM, that is called a state felony. The rule of law doesn't take a nap at midnight.",
            nextNodeId = "act5_ch1_arena_hub"
        ),
        DialogueNode(
            id = "act5_ch1_golem_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "STAND BACK! The 3:00 AM vertical spike of 138,000 votes to zero is mathematically completely normal! The Burst Pipe Illusionist and Midnight Ballot Courier demand you accept the count!",
            nextNodeId = "act5_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act5_ch1_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH, BROTHER! They thought they could sneak those phantom trunks past the 24-inch pythons! Commander, Marco, JD, and Kash — body-slam this plumbing illusion straight down the drain!",
            triggerBattleEncounterId = "act5_ch1_pipe_battle",
            nextNodeId = "act5_ch1_post_battle"
        ),
        DialogueNode(
            id = "act5_ch1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The phantom plumbing excuse has burst! The receipts are in the open! But the Beltway establishment refuses to hold hearings. We're taking this straight to the United States Senate!",
            changeSceneId = SCENE_ACT5_CH2_SENATE.id,
            nextNodeId = "act5_ch2_intro"
        ),

        // =========================================================================
        // ACT V - CHAPTER 2: THE SENATE INQUEST (2020)
        // =========================================================================
        DialogueNode(
            id = "act5_ch2_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are inside the Dirksen Senate Office Building for the Judiciary Committee Inquest. Stonewalling election bureaucrats and agency witnesses have arrived with three-ring binders full of prepared excuses. Across the dais sits Senator John Kennedy of Louisiana.",
            nextNodeId = "act5_ch2_senate_hub"
        ),
        DialogueNode(
            id = "act5_ch2_senate_hub",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Now, I've been listening to these witnesses for two hours, and I've got a few questions that need answering in plain, simple English.",
            choices = listOf(
                DialogueChoice("a5c2_miracle", "Ask about fixing the administrative election chaos", listOf("miracle", "god", "busy", "fix", "chaos"), "act5_ch2_miracle_quote", completionFlag = "a5c2_miracle"),
                DialogueChoice("a5c2_hammers", "Grill the officials on turning off signature verification", listOf("hammers", "dumber", "signature", "verify", "bag"), "act5_ch2_hammers_quote", completionFlag = "a5c2_hammers"),
                DialogueChoice("a5c2_kennedy", "Call upon Senator Kennedy to officially join the 5-Hero Supergroup", listOf("kennedy", "join", "senator", "supergroup", "five"), "act5_ch2_kennedy_arrives")
            )
        ),
        DialogueNode(
            id = "act5_ch2_miracle_quote",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Now, let me say this politely, son. It will take a miracle to fix the mess you just described, and frankly, God is busy. You don't have to be a nuclear astrophysicist to figure out that if you don't check signatures, you ain't checking signatures.",
            nextNodeId = "act5_ch2_senate_hub"
        ),
        DialogueNode(
            id = "act5_ch2_hammers_quote",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "I've listened to your testimony, and with all due respect, that explanation sounds dumber than a bag of hair. If common sense were water, some of these bureaucrats wouldn't have enough to fill a thimble.",
            nextNodeId = "act5_ch2_senate_hub"
        ),
        DialogueNode(
            id = "act5_ch2_kennedy_arrives",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Now hold on just a minute, son. Did you write that 3 AM ballot rule yourself, or did somebody help you think it up? Because in plain English, that sounds dumber than a bag of hammers. Commander, let's grill these bureaucrats until they squeal!",
            nextNodeId = "act5_ch2_hero_kennedy_reply"
        ),
        DialogueNode(
            id = "act5_ch2_hero_kennedy_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Senator, I love it! Razor-sharp common sense, pure Louisiana steel, and the full 5-hero supergroup is assembled! The Committee Cross-Examiner is ON THE TEAM!",
            nextNodeId = "act5_ch2_stonewall_ambush"
        ),
        DialogueNode(
            id = "act5_ch2_stonewall_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "POINT OF ORDER! We refuse to answer under Executive Privilege and General Confusion! Stonewaller phantoms, deploy the bureaucratic filibuster!",
            nextNodeId = "act5_ch2_battle_trigger"
        ),
        DialogueNode(
            id = "act5_ch2_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "OH YEAH! KENNEDY'S IN THE CORNER, BROTHER! That's five American legends in the ring! Drop the gavel of justice and body-slam the red tape!",
            triggerBattleEncounterId = "act5_ch2_inquest_battle",
            nextNodeId = "act5_ch2_post_battle"
        ),
        DialogueNode(
            id = "act5_ch2_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Their stonewall collapsed under Kennedy's cross-examination! But Washington is entrenched in the swamp for now. It's time for the Exile arc — we head south to the Palm Beach citadel!",
            changeSceneId = SCENE_ACT5_CH3_TERRACE.id,
            nextNodeId = "act5_ch3_intro"
        ),

        // =========================================================================
        // ACT V - CHAPTER 3: THE SOUTHERN WHITE HOUSE (2021)
        // =========================================================================
        DialogueNode(
            id = "act5_ch3_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are broadcasting live from Palm Beach, Florida — the Southern White House. The establishment media declared Commander America's movement finished. Instead, Mar-a-Lago has become the undisputed center of gravity for American politics.",
            nextNodeId = "act5_ch3_terrace_hub"
        ),
        DialogueNode(
            id = "act5_ch3_terrace_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "They thought we would fade away into retirement! Fade away to where?! We have the championship golf course, the finest patriots in the world, and our movement is stronger than ever!",
            choices = listOf(
                DialogueChoice("a5c3_golf", "Train signature presidential drive on the fairway", listOf("golf", "swing", "driver", "hole in one", "train"), "act5_ch3_golf_training", completionFlag = "a5c3_golf"),
                DialogueChoice("a5c3_foreign", "Host visiting world leaders seeking genuine diplomacy", listOf("diplomats", "leaders", "foreign", "delegation", "deals"), "act5_ch3_foreign_delegation", completionFlag = "a5c3_foreign"),
                DialogueChoice("a5c3_rally", "Announce the nationwide Golden Comeback Tour", listOf("rally", "crowd", "comeback", "tour", "patriots"), "act5_ch3_comeback_tour", completionFlag = "a5c3_rally"),
                DialogueChoice("a5c3_briefing", "Hold an emergency war room briefing with the 5 heroes", listOf("briefing", "war room", "intel", "closets", "raid"), "act5_ch3_war_room")
            )
        ),
        DialogueNode(
            id = "act5_ch3_golf_training",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Hit a clean 320-yard drive straight into the ocean crosswind! Hole-in-one on the 16th! Energy level at 1000%! The training arc is unmatched!",
            nextNodeId = "act5_ch3_hulk_react"
        ),
        DialogueNode(
            id = "act5_ch3_hulk_react",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THREE HUNDRED AND TWENTY YARDS RIGHT DOWN THE MIDDLE, BROTHER! Say your prayers, drink your raw milk, and watch the greatest comeback in sports and political history!",
            nextNodeId = "act5_ch3_terrace_hub"
        ),
        DialogueNode(
            id = "act5_ch3_foreign_delegation",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Prime ministers and foreign ambassadors keep bypassing Washington entirely and flying directly to Palm Beach. Everyone on the international stage knows where the real power is centered.",
            nextNodeId = "act5_ch3_terrace_hub"
        ),
        DialogueNode(
            id = "act5_ch3_comeback_tour",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Rallies in Iowa, Ohio, Michigan, Pennsylvania! Lines three miles long in sub-zero snow! Nobody has ever seen crowds like this. The comeback will be historic!",
            nextNodeId = "act5_ch3_terrace_hub"
        ),
        DialogueNode(
            id = "act5_ch3_war_room",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Commander, our intelligence sources report the Beltway establishment is terrified of your poll numbers. They're preparing an unprecedented escalation — an armed dawn raid on Mar-a-Lago's storage rooms.",
            nextNodeId = "act5_ch3_hero_closets"
        ),
        DialogueNode(
            id = "act5_ch3_hero_closets",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "An armed raid on my home?! Over declassified personal papers, letters from Kim, and press clippings?! Let them come. To the storage vault!",
            changeSceneId = SCENE_ACT5_CH4_CLOSETS.id,
            nextNodeId = "act5_ch4_intro"
        ),

        // =========================================================================
        // ACT V - CHAPTER 4: THE DEEP STATE RAID ON THE GOLDEN CLOSETS (2022)
        // =========================================================================
        DialogueNode(
            id = "act5_ch4_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "BREAKING NEWS: Thirty armed federal agents have descended on Mar-a-Lago with search warrants, rifling through personal storage rooms, Melania's wardrobe, and decades of declassified souvenirs.",
            nextNodeId = "act5_ch4_vault_hub"
        ),
        DialogueNode(
            id = "act5_ch4_vault_hub",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "You took my passports! You rummaged through Melania's closets! Under the Presidential Records Act, every president has personal boxes! Bill had sock drawers, Barack had warehouses in Chicago!",
            choices = listOf(
                DialogueChoice("a5c4_pra", "Invoke the Presidential Records Act civil statutory protections", listOf("pra", "records", "act", "civil", "statute"), "act5_ch4_pra_argument", completionFlag = "a5c4_pra"),
                DialogueChoice("a5c4_passports", "Demand the return of stolen passports and personal items", listOf("passports", "wardrobe", "melania", "stolen", "return"), "act5_ch4_passports_demand", completionFlag = "a5c4_passports"),
                DialogueChoice("a5c4_kash", "Deploy Kash Patel's official declassification clearance logs", listOf("kash", "declassify", "clearance", "logs", "orders"), "act5_ch4_kash_declass", completionFlag = "a5c4_kash"),
                DialogueChoice("a5c4_battle", "Unleash the 5-hero supergroup to defend the Golden Closets", listOf("fight", "raid", "golem", "battle", "defend"), "act5_ch4_raid_confront")
            )
        ),
        DialogueNode(
            id = "act5_ch4_pra_argument",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The Presidential Records Act is a civil negotiation statute, not a criminal weapon! You discuss storage with the National Archives over tea and scones, you don't send thirty armed agents with guns drawn!",
            nextNodeId = "act5_ch4_vault_hub"
        ),
        DialogueNode(
            id = "act5_ch4_passports_demand",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Son, if you're taking a man's passport, his wife's clothes, and love letters from North Korea, you ain't looking for national security secrets. You're on a political fishing expedition, and you didn't even pack bait.",
            nextNodeId = "act5_ch4_vault_hub"
        ),
        DialogueNode(
            id = "act5_ch4_kash_declass",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Every box in this vault was declassified under standing executive authorities before departing the Oval Office. We have the transport manifests, clearance logs, and paper trail. This raid is completely unlawful.",
            nextNodeId = "act5_ch4_vault_hub"
        ),
        DialogueNode(
            id = "act5_ch4_raid_confront",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "SEIZE THE BANKER BOXES! We must summon the Midnight Ballot Golem and Armed Raid Phantoms to suppress the comeback!",
            nextNodeId = "act5_ch4_battle_trigger"
        ),
        DialogueNode(
            id = "act5_ch4_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "VITAMINS, RAW MILK, AND TWENTY-FOUR INCH PYTHONS, BROTHER! The full dream team of 5 American legends is unleashed! Hit that Tremendous Strike and body-slam the Ballot Golem!",
            triggerBattleEncounterId = "act5_golem_battle",
            nextNodeId = "act5_ch4_post_battle"
        ),
        DialogueNode(
            id = "act5_ch4_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The Golem crumbles into scrap paper and the raid backfired completely! Donations broke our servers and our poll numbers hit record highs! The exile is over! Now we march across the Word-Salad Wasteland of the Vice-Sovereign!",
            changeSceneId = SCENE_ACT6_CH1_BORDER.id,
            nextNodeId = "act6_ch1_intro"
        ),

        // Legacy compatibility nodes for existing tests & saves
        DialogueNode(
            id = "act5_golf",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Hit a clean hole-in-one! 300 yards straight down the fairway! My energy is at 1000%!",
            nextNodeId = "act5_hulk_react"
        ),
        DialogueNode(
            id = "act5_hulk_react",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THREE HUNDRED YARDS RIGHT DOWN THE MIDDLE, BROTHER! That's what I'm talkin' about! Say your prayers, take your vitamins, and watch the comeback begin!",
            nextNodeId = "act5_rally"
        ),
        DialogueNode(
            id = "act5_rally",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "They tried to stop us, but the movement is bigger and stronger than ever before! We are preparing the greatest comeback in world history!",
            nextNodeId = "act5_golem_ambush"
        ),
        DialogueNode(
            id = "act5_golem_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "The Midnight Ballot Golem commands you to accept the 3 AM count!",
            nextNodeId = "act5_kennedy_joins"
        ),
        DialogueNode(
            id = "act5_kennedy_joins",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Now hold on just a minute, son. Did you write that 3 AM ballot rule yourself, or did somebody help you think it up? Because in plain English, that sounds dumber than a bag of hammers. Commander, let's grill these bureaucrats until they squeal!",
            nextNodeId = "act5_hero_kennedy_reply"
        ),
        DialogueNode(
            id = "act5_hero_kennedy_reply",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Senator, I love it! Razor-sharp common sense, pure Louisiana steel, and the full 5-hero supergroup is assembled! Golem, you're fired!",
            nextNodeId = "act5_battle_trigger"
        ),
        DialogueNode(
            id = "act5_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "VITAMINS, RAW MILK, AND TWENTY-FOUR INCH PYTHONS, BROTHER! The full dream team of 5 American legends is unleashed! Hit that Tremendous Strike and body-slam the Ballot Golem!",
            triggerBattleEncounterId = "act5_golem_battle",
            nextNodeId = "act5_ch4_post_battle"
        ),
        DialogueNode(
            id = "act5_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The Golem crumbles into scrap paper! Now, we navigate the confusing realm of the Vice-Sovereign!",
            changeSceneId = SCENE_ACT6_CH1_BORDER.id,
            nextNodeId = "act6_ch1_intro"
        ),

        // =========================================================================
        // ACT VI: THE WORD-SALAD WASTELAND (2022-2023) — 5 CHAPTERS
        // =========================================================================

        // -------------------------------------------------------------------------
        // Chapter 1: The Border Czar Inspection (2022)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act6_ch1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The year is 2022. Millions of undocumented migrants are streaming across the southern border under the watchful eye of the newly appointed Border Czar, who has famously never visited the border herself!",
            nextNodeId = "act6_ch1_border_arrival"
        ),
        DialogueNode(
            id = "act6_ch1_border_arrival",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Look at this disaster, folks! Thousands of feet of brand-new, beautiful steel bollards left rusting on the dirt! We paid for them, they were ready to install, and they stopped construction on day one! Total madness!",
            nextNodeId = "act6_ch1_border_hub"
        ),
        DialogueNode(
            id = "act6_ch1_border_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The team inspects the gaping openings in the unfinished perimeter. How do we break through the Border Czar's blockade?",
            choices = listOf(
                DialogueChoice("a6c1_excuse", "Demand an explanation for why the Border Czar hasn't visited", listOf("czar", "visit", "excuse", "europe", "border"), "act6_ch1_czar_excuse"),
                DialogueChoice("a6c1_panels", "Inspect the $300 million in rusting, abandoned wall panels", listOf("panels", "rust", "steel", "materials", "waste"), "act6_ch1_rusted_panels"),
                DialogueChoice("a6c1_fentanyl", "Expose the cartel smuggling pipelines and fentanyl surge", listOf("fentanyl", "cartel", "smuggling", "pipelines", "border"), "act6_ch1_fentanyl_crisis"),
                DialogueChoice("a6c1_proceed", "Advance past the perimeter gap and confront the smugglers", listOf("advance", "proceed", "forward", "gap", "confront"), "act6_ch1_cartel_ambush")
            )
        ),
        DialogueNode(
            id = "act6_ch1_czar_excuse",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "When Lester Holt asked her why she hadn't visited the border, she literally laughed and said: 'Well, I haven't been to Europe either!' That is not foreign policy, folks — that is an admission of complete neglect!",
            nextNodeId = "act6_ch1_border_hub"
        ),
        DialogueNode(
            id = "act6_ch1_rusted_panels",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Over three hundred million dollars of high-grade American steel sitting in the dirt being eaten by desert termites. Working-class taxpayers financed this while contractors were paid millions to stand down.",
            nextNodeId = "act6_ch1_border_hub"
        ),
        DialogueNode(
            id = "act6_ch1_fentanyl_crisis",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Down in Louisiana, we have an old saying: you can put wings on a pig, but that don't make it an eagle. Calling yourself a Border Czar without visiting the border is like calling yourself an astronaut because you once ate a MoonPie!",
            nextNodeId = "act6_ch1_border_hub"
        ),
        DialogueNode(
            id = "act6_ch1_cartel_ambush",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "LOOK OUT, BROTHER! Out from behind the rusted bollards come the cartel couriers and catch-and-release liaisons waving federal parole slips!",
            nextNodeId = "act6_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act6_ch1_battle_trigger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Catch and release is officially cancelled! We're building the wall, securing the perimeter, and shutting down this cartel racket right now!",
            triggerBattleEncounterId = "act6_ch1_border_battle",
            nextNodeId = "act6_ch1_post_battle"
        ),
        DialogueNode(
            id = "act6_ch1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The cartel couriers flee back across the river! But look ahead — a bizarre glowing haze of yellow and blue circular diagrams is rolling across the horizon!",
            changeSceneId = SCENE_ACT6_CH2_VENN.id,
            nextNodeId = "act6_ch2_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 2: The Venn Diagram Fog (2023)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act6_ch2_intro",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WHAT IN THE NAME OF HULKAMANIA IS GOING ON, BROTHERS?! Overlapping circles of pure yellow and cyan light are floating everywhere! My brain is locked in an intellectual figure-four leglock!",
            nextNodeId = "act6_ch2_venn_hub"
        ),
        DialogueNode(
            id = "act6_ch2_venn_hub",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We have entered the Venn Diagram Fog. Speech here circles endlessly back onto itself without ever making a tangible point. How should the coalition navigate this labyrinth of jargon?",
            choices = listOf(
                DialogueChoice("a6c2_time", "Analyze the significance of the passage of time", listOf("time", "passage", "significance", "loop", "circular"), "act6_ch2_time_passage"),
                DialogueChoice("a6c2_unburdened", "Ponder what can be unburdened by what has been", listOf("unburdened", "burden", "future", "past", "logic"), "act6_ch2_unburdened_loop"),
                DialogueChoice("a6c2_kennedy", "Ask Senator Kennedy for a plain-English southern translation", listOf("kennedy", "louisiana", "english", "plain", "translate"), "act6_ch2_kennedy_translate"),
                DialogueChoice("a6c2_dispel", "Dispel the fog with hard economic numbers and proceed", listOf("dispel", "numbers", "proceed", "advance", "clarity"), "act6_ch2_dispel_fog")
            )
        ),
        DialogueNode(
            id = "act6_ch2_time_passage",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "You see, there is great significance to the passage of time when you think about the passage of time, because time passes, and in the passing of time, there is great significance!",
            nextNodeId = "act6_ch2_venn_hub"
        ),
        DialogueNode(
            id = "act6_ch2_unburdened_loop",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "What can be, unburdened by what has been! Because what has been was burdened, but what can be will be unburdened by the very things that have been!",
            nextNodeId = "act6_ch2_venn_hub"
        ),
        DialogueNode(
            id = "act6_ch2_kennedy_translate",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Son, I've heard auctioneers with a mouthful of marbles make more sense than this. Listening to that is like watching a goat try to calculate property taxes. It's high-dollar gibberish!",
            nextNodeId = "act6_ch2_venn_hub"
        ),
        DialogueNode(
            id = "act6_ch2_dispel_fog",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "I've reviewed the declassified transcripts. There is zero substance behind these floating circles — just an optical illusion designed to disguise record gas prices and soaring interest rates!",
            nextNodeId = "act6_ch2_hero_dispel"
        ),
        DialogueNode(
            id = "act6_ch2_hero_dispel",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Total word-salad! In the real world, you judge results, not circular speeches. Blow the fog away, Patriots! Let's see what they're hiding behind these yellow circles!",
            changeSceneId = SCENE_ACT6_CH3_BUS.id,
            nextNodeId = "act6_ch3_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 3: The Electric School Bus Mire (2023)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act6_ch3_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "As the fog parts, we stumble into a muddy municipal depot where dozens of brand-new, half-million-dollar electric school buses sit dead in the mud, completely unable to charge!",
            nextNodeId = "act6_ch3_bus_hub"
        ),
        DialogueNode(
            id = "act6_ch3_bus_hub",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The federal EV school bus initiative spent billions of taxpayer dollars, but the buses cannot handle sub-zero winters or basic rural bus routes. How do we address this stranded convoy?",
            choices = listOf(
                DialogueChoice("a6c3_cackle", "Listen to the recorded yellow school bus audio tribute", listOf("cackle", "yellow", "bus", "love", "laugh"), "act6_ch3_bus_cackle"),
                DialogueChoice("a6c3_chargers", "Audit the $7.5 billion national EV charger program", listOf("chargers", "billion", "audit", "tally", "waste"), "act6_ch3_bus_chargers"),
                DialogueChoice("a6c3_vance", "Ask JD Vance about the impact on Midwestern manufacturing", listOf("vance", "ohio", "grid", "workers", "manufacturing"), "act6_ch3_vance_grid"),
                DialogueChoice("a6c3_confront", "Confront the Stranded Battery Golem barring the road", listOf("confront", "battery", "golem", "battle", "fight"), "act6_ch3_golem_confront")
            )
        ),
        DialogueNode(
            id = "act6_ch3_bus_cackle",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "Who doesn't love a yellow school bus, right?! Can you raise your hand if you love yellow school buses?! AHAHAHAHAHA! They're yellow, and they're buses!",
            nextNodeId = "act6_ch3_bus_hub"
        ),
        DialogueNode(
            id = "act6_ch3_bus_chargers",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Congress allocated seven point five BILLION dollars to build charging stations across the country. After two full years, do you know how many they actually built? EIGHT. Exactly eight chargers for eight billion dollars!",
            nextNodeId = "act6_ch3_bus_hub"
        ),
        DialogueNode(
            id = "act6_ch3_vance_grid",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Our electric grid can't handle everyone turning their air conditioners on in July, yet Washington mandated that heavy diesel workbuses be replaced by fragile lithium battery packs made overseas.",
            nextNodeId = "act6_ch3_bus_hub"
        ),
        DialogueNode(
            id = "act6_ch3_golem_confront",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "BROTHER! The dead battery packs in the mud are fusing together! A gigantic Stranded EV Battery Golem is rising up, powered by federal green mandate drones!",
            nextNodeId = "act6_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act6_ch3_battle_trigger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "You can't drive school buses on mandates and wishful thinking, folks! Time to unplug the mandate and jump-start American common sense!",
            triggerBattleEncounterId = "act6_ch3_bus_battle",
            nextNodeId = "act6_ch3_post_battle"
        ),
        DialogueNode(
            id = "act6_ch3_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The Battery Golem short-circuits and shuts down! Look past the bus depot — we've arrived at a bizarre grove of palm trees raining golden coconuts!",
            changeSceneId = SCENE_ACT6_CH4_COCONUT.id,
            nextNodeId = "act6_ch4_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 4: The Coconut Tree Grove (2023)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act6_ch4_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We step into the surreal Coconut Tree Grove, where shimmering tropical fronds rustle in the breeze and disembodied laughter asks whether anyone just fell out of a tree.",
            nextNodeId = "act6_ch4_grove_hub"
        ),
        DialogueNode(
            id = "act6_ch4_grove_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Every path through the grove is lined with quotes about existing in the context of all in which you live and what came before you. How does the party advance?",
            choices = listOf(
                DialogueChoice("a6c4_quote", "Examine the philosophical mystery of the coconut tree", listOf("coconut", "tree", "context", "fell", "quote"), "act6_ch4_coconut_quote"),
                DialogueChoice("a6c4_pride", "Proclaim that American greatness comes from real grit, not trees", listOf("pride", "grit", "greatness", "american", "work"), "act6_ch4_american_pride"),
                DialogueChoice("a6c4_kennedy", "Ask Senator Kennedy if coconuts grow on the bayou", listOf("kennedy", "bayou", "coconuts", "louisiana", "humor"), "act6_ch4_kennedy_coconuts"),
                DialogueChoice("a6c4_throne", "Storm the elevated Dais of What Can Be", listOf("throne", "dais", "storm", "advance", "climax"), "act6_ch4_advance_throne")
            )
        ),
        DialogueNode(
            id = "act6_ch4_coconut_quote",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "My mother used to say to me, 'I don't know what's wrong with you young people. You think you just fell out of a coconut tree?!' AHAHAHAHA! You exist in the context of all in which you live and what came before you!",
            nextNodeId = "act6_ch4_grove_hub"
        ),
        DialogueNode(
            id = "act6_ch4_american_pride",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "The people who built our bridges, dug our coal mines, and poured our steel didn't fall out of a coconut tree. They built this nation with sweat and grit, and they deserve leadership that speaks clearly.",
            nextNodeId = "act6_ch4_grove_hub"
        ),
        DialogueNode(
            id = "act6_ch4_kennedy_coconuts",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Down in Louisiana, if somebody starts talking about falling out of coconut trees in the middle of a budget crisis, we don't give them the nuclear football — we call them an ambulance and check for heat exhaustion!",
            nextNodeId = "act6_ch4_grove_hub"
        ),
        DialogueNode(
            id = "act6_ch4_advance_throne",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Enough with the coconuts, enough with the context! At the top of that hill sits the Dais of Circular Logic. It's time to confront the Vice-Sovereign herself!",
            changeSceneId = SCENE_ACT6_CH5_UNBURDENED.id,
            nextNodeId = "act6_ch5_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 5: The Unburdened Vice-Sovereign Confrontation (2023)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act6_ch5_intro",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THIS IS THE MAIN EVENT, BROTHER! At the center of the Unburdened Throne stands Kamala the Unburdened, clutching a golden binder of circular speeches and flanked by swirling Venn loops!",
            nextNodeId = "act6_ch5_throne_hub"
        ),
        DialogueNode(
            id = "act6_ch5_throne_hub",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Kamala readies her overlapping yellow and blue Venn shields! How do Commander America and the 5-hero coalition dismantle her defenses?",
            choices = listOf(
                DialogueChoice("a6c5_record", "Challenge her record on 4-year inflation and open borders", listOf("record", "inflation", "economy", "borders", "prices"), "act6_ch5_record_challenge"),
                DialogueChoice("a6c5_debate", "Unleash unscripted debate firepower and common sense", listOf("debate", "unscripted", "firepower", "teleprompter", "logic"), "act6_ch5_unscripted_fire"),
                DialogueChoice("a6c5_supergroup", "Unite all 5 heroes for the final constitutional verdict", listOf("supergroup", "verdict", "heroes", "coalition", "unite"), "act6_ch5_supergroup_verdict"),
                DialogueChoice("a6c5_battle", "Charge into battle and banish the Word-Salad forever!", listOf("battle", "charge", "fight", "climax", "win"), "act6_ch5_climax_battle")
            )
        ),
        DialogueNode(
            id = "act6_ch5_record_challenge",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Prices up twenty-five percent across grocery shelves, gas prices doubling, mortgage rates pricing out young families — no circular speech can unburden the American people from those numbers!",
            nextNodeId = "act6_ch5_throne_hub"
        ),
        DialogueNode(
            id = "act6_ch5_unscripted_fire",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Take away the teleprompter, take away the rehearsed soundbites, and what do you have? Total word-salad! It's unwatchable, folks! The ratings are terrible!",
            nextNodeId = "act6_ch5_throne_hub"
        ),
        DialogueNode(
            id = "act6_ch5_supergroup_verdict",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "The American people aren't confused, Madam Vice-Sovereign. They know that when Washington tells you it's raining, you'd better check and make sure someone isn't peeing on your boots. The jig is up!",
            nextNodeId = "act6_ch5_throne_hub"
        ),
        DialogueNode(
            id = "act6_ch5_climax_battle",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "You think you can challenge what can be unburdened by what has been?! AHAHAHAHAHA! Feel the infinite rotation of the passage of time!",
            nextNodeId = "act6_ch5_battle_trigger"
        ),
        DialogueNode(
            id = "act6_ch5_battle_trigger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Your circular logic ends today! Five heroes, one united movement, fighting for real common sense! Patriots, assemble!",
            triggerBattleEncounterId = "act6_kamala_battle",
            nextNodeId = "act6_ch5_post_battle"
        ),
        DialogueNode(
            id = "act6_ch5_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The Venn diagram shields shatter into dust! The Word-Salad fog has completely cleared! Now, we turn to the final trials — the Lawfare Labyrinth and the Sands of Steel Resolve!",
            changeSceneId = SCENE_ACT7_CH1_GAUNTLET.id,
            nextNodeId = "act7_ch1_intro"
        ),

        // Legacy compatibility nodes for Act VI
        DialogueNode(
            id = "act6_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We find ourselves navigating the uncharted terrain of the Word-Salad Wasteland, where sentences circle endlessly without ever arriving at a conclusion.",
            choices = listOf(
                DialogueChoice("a6_listen", "Try to comprehend the unburdened speech", listOf("listen", "speech", "unburdened", "time", "confused"), "act6_listen"),
                DialogueChoice("a6_cackle", "Mute the echoing cackle with golden truth", listOf("mute", "cackle", "truth", "silence", "laugh"), "act6_cackle")
            )
        ),
        DialogueNode(
            id = "act6_listen",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "You see, the significance of the passage of time is that time passes... and when we think about time, we are unburdened by what has been because of what will be in the passage of time!",
            nextNodeId = "act6_hulk_confused"
        ),
        DialogueNode(
            id = "act6_hulk_confused",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "BROTHER! The Hulkster has wrestled giant androids, but I have no idea what she just said! My brain is locked in a sleeper hold, brother!",
            nextNodeId = "act6_confused_hero"
        ),
        DialogueNode(
            id = "act6_cackle",
            speaker = DialogueSpeaker.KAMALA,
            side = SpeakerSide.RIGHT,
            text = "AHAHAHAHAHA! *Cackles endlessly while gesturing at overlapping yellow circles*",
            nextNodeId = "act6_confused_hero"
        ),
        DialogueNode(
            id = "act6_confused_hero",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Nobody knows what she just said! It's pure word-salad! Total gibberish, folks!",
            nextNodeId = "act6_battle_trigger"
        ),
        DialogueNode(
            id = "act6_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "I've cross-referenced every single page of this binder, and not one syllable makes sense! Commander, cut through the circular logic with pure undeniable facts!",
            triggerBattleEncounterId = "act6_kamala_battle",
            nextNodeId = "act6_post_battle"
        ),
        DialogueNode(
            id = "act6_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The word-salad fog clears! Only two Acts remain on our quest to eradicate stupidity!",
            changeSceneId = SCENE_ACT7_CH1_GAUNTLET.id,
            nextNodeId = "act7_ch1_intro"
        ),

        // =========================================================================
        // ACT VII: THE LAWFARE LABYRINTH & STEEL RESOLVE (2024) — 5 CHAPTERS
        // =========================================================================

        // -------------------------------------------------------------------------
        // Chapter 1: The 91 Indictment Gauntlet (Fulton County & Lower Manhattan)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act7_ch1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The year is 2024. In an unprecedented abuse of the legal system, four separate partisan jurisdictions hand down ninety-one fabricated criminal charges against Commander America!",
            nextNodeId = "act7_ch1_court_arrival"
        ),
        DialogueNode(
            id = "act7_ch1_court_arrival",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Ninety-one indictments, folks! Nobody has ever seen anything like it! If I fly over a blue state, they send a grand jury after me! Total election interference, but the people see right through it!",
            nextNodeId = "act7_ch1_gauntlet_hub"
        ),
        DialogueNode(
            id = "act7_ch1_gauntlet_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The courtroom corridors are packed with cameras and partisan lawfare architects. How does the defense dismantle these charges?",
            choices = listOf(
                DialogueChoice("a7c1_mugshot", "Inspect the iconic Atlanta mugshot of defiance", listOf("mugshot", "atlanta", "fulton", "defiance", "photo"), "act7_ch1_mugshot_defiance"),
                DialogueChoice("a7c1_charges", "Analyze the 34 bookkeeping misdemeanors turned into felonies", listOf("bookkeeping", "charges", "felonies", "manhattan", "counts"), "act7_ch1_bookkeeping_counts"),
                DialogueChoice("a7c1_kennedy", "Ask Senator Kennedy for his appraisal of partisan prosecutors", listOf("kennedy", "prosecutor", "lawfare", "justice", "louisiana"), "act7_ch1_kennedy_appraisal"),
                DialogueChoice("a7c1_proceed", "Charge past the docket blockade and confront the prosecutor", listOf("proceed", "charge", "court", "prosecutor", "confront"), "act7_ch1_prosecutor_ambush")
            )
        ),
        DialogueNode(
            id = "act7_ch1_mugshot_defiance",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "They thought that booking photo in Fulton County would break him. Instead, it became the most famous image on Earth — slapped onto t-shirts, coffee mugs, and billboards in every working-class town in America!",
            nextNodeId = "act7_ch1_gauntlet_hub"
        ),
        DialogueNode(
            id = "act7_ch1_bookkeeping_counts",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "They took expired misdemeanor recordkeeping entries and magically spliced them with federal campaign law that the feds already declined to prosecute. It's legal origami constructed out of thin air!",
            nextNodeId = "act7_ch1_gauntlet_hub"
        ),
        DialogueNode(
            id = "act7_ch1_kennedy_appraisal",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Up here in these activist courtrooms, the law doesn't matter a tinker's damn. These prosecutors are like a dog chasing a parked car — now that they've caught it, they have no idea how to drive it!",
            nextNodeId = "act7_ch1_gauntlet_hub"
        ),
        DialogueNode(
            id = "act7_ch1_prosecutor_ambush",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "LOOK OUT, BROTHER! The Chief Partisan Prosecutor emerges from behind the bench, flanked by Lawfare Docket Clerks hurling stacks of stamped motions!",
            nextNodeId = "act7_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act7_ch1_battle_trigger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Every single one of these 91 counts is a badge of honor! We fight for the Constitution, and we never back down!",
            triggerBattleEncounterId = "act7_ch1_indictment_battle",
            nextNodeId = "act7_ch1_post_battle"
        ),
        DialogueNode(
            id = "act7_ch1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The indictments crumble under the weight of truth! But the judge bangs his gavel, attempting to slap an unconstitutional muzzle on our speech!",
            changeSceneId = SCENE_ACT7_CH2_GAG.id,
            nextNodeId = "act7_ch2_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 2: The Gag Order Guillotine (Contempt Hearing with Biased Judges)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act7_ch2_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The judge issues a sweeping, unprecedented gag order, threatening Commander America with immediate jail time if he dares speak publicly about the trial or the witnesses.",
            nextNodeId = "act7_ch2_gag_hub"
        ),
        DialogueNode(
            id = "act7_ch2_gag_hub",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "They want to silence the leading candidate for President while his political opponents attack him freely on television. How does the coalition counter this muzzle?",
            choices = listOf(
                DialogueChoice("a7c2_first_amendment", "Assert the First Amendment right of 80 million voters", listOf("first", "amendment", "voters", "rights", "free speech"), "act7_ch2_first_amendment"),
                DialogueChoice("a7c2_sidewalk", "Deploy Marco and Vance for the legendary sidewalk press conference", listOf("sidewalk", "marco", "vance", "press", "surrogate"), "act7_ch2_sidewalk_press"),
                DialogueChoice("a7c2_kennedy", "Hear Senator Kennedy's take on activist judges", listOf("kennedy", "judge", "activist", "gavel", "contempt"), "act7_ch2_kennedy_gavel"),
                DialogueChoice("a7c2_shatter", "Shatter the Gag Order Guillotine and break free", listOf("shatter", "guillotine", "battle", "free", "contempt"), "act7_ch2_contempt_confront")
            )
        ),
        DialogueNode(
            id = "act7_ch2_first_amendment",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "They are not taking away my speech — they are taking away YOUR right to hear the truth! They want to muzzle the American people, and I won't let them do it!",
            nextNodeId = "act7_ch2_gag_hub"
        ),
        DialogueNode(
            id = "act7_ch2_sidewalk_press",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "If the judge won't let the Commander speak in the courtroom, Vance and I will stand on the sidewalk outside in the pouring rain with twenty microphones and say every single word for him!",
            nextNodeId = "act7_ch2_gag_hub"
        ),
        DialogueNode(
            id = "act7_ch2_kennedy_gavel",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "This gag order is more crooked than a barrel of snakes. You can't strip a man of his God-given constitutional rights just because his poll numbers are twenty points higher than yours!",
            nextNodeId = "act7_ch2_gag_hub"
        ),
        DialogueNode(
            id = "act7_ch2_contempt_confront",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THE GAVEL IS FALLING, BROTHER! The Gag Order Magistrate and his Contempt Bailiffs are rushing the podium with handcuffs and fines!",
            nextNodeId = "act7_ch2_battle_trigger"
        ),
        DialogueNode(
            id = "act7_ch2_battle_trigger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "You cannot jail the American spirit! Freedom of speech will prevail over partisan judges every single time!",
            triggerBattleEncounterId = "act7_ch2_contempt_battle",
            nextNodeId = "act7_ch2_post_battle"
        ),
        DialogueNode(
            id = "act7_ch2_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The gag order shatters into a million pieces! We leave Manhattan behind and head into the open skies of Pennsylvania!",
            changeSceneId = SCENE_ACT7_CH3_BUTLER.id,
            nextNodeId = "act7_ch3_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 3: The Butler Fairgrounds (July 13, 2024)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act7_ch3_intro",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "JULY 13, 2024, BROTHER! Butler, Pennsylvania! Over fifty thousand patriots have gathered under the open sun! You can feel the electric energy in the air!",
            nextNodeId = "act7_ch3_rally_hub"
        ),
        DialogueNode(
            id = "act7_ch3_rally_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Commander America steps up to the rally podium beneath an open summer sky. How does he prepare to deliver the historic speech?",
            choices = listOf(
                DialogueChoice("a7c3_chart", "Display the famous border immigration chart on the jumbotron", listOf("chart", "border", "jumbotron", "immigration", "screen"), "act7_ch3_display_chart"),
                DialogueChoice("a7c3_patriots", "Wave to the sea of red hats and working-class families", listOf("patriots", "crowd", "wave", "families", "cheer"), "act7_ch3_crowd_wave"),
                DialogueChoice("a7c3_kash", "Review perimeter security and suspicious nearby sloped roofs", listOf("security", "roof", "sloped", "kash", "perimeter"), "act7_ch3_kash_warning"),
                DialogueChoice("a7c3_advance", "Take the center podium and begin the fateful address", listOf("podium", "begin", "address", "speech", "advance"), "act7_ch3_take_podium")
            )
        ),
        DialogueNode(
            id = "act7_ch3_display_chart",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Bring down that chart, folks! Look at that arrow right at the bottom — lowest illegal immigration in the history of our country! That beautiful chart shows the exact moment our borders were secure!",
            nextNodeId = "act7_ch3_rally_hub"
        ),
        DialogueNode(
            id = "act7_ch3_crowd_wave",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Look at these people — steelworkers, nurses, farmers, truckers. They drove six hours and waited in the heat because they love this country and believe in the American comeback.",
            nextNodeId = "act7_ch3_rally_hub"
        ),
        DialogueNode(
            id = "act7_ch3_kash_warning",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "Something isn't right with the outer perimeter. That warehouse roof with the slight slope is completely unmanned. Why did local command leave an elevated line of sight open?",
            nextNodeId = "act7_ch3_rally_hub"
        ),
        DialogueNode(
            id = "act7_ch3_take_podium",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "It's time to speak to the people. Turn your head to the right and look at that chart...",
            changeSceneId = SCENE_ACT7_CH4_STEEL_EAR.id,
            nextNodeId = "act7_ch4_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 4: Steel Ear & Fist Defiance (2024)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act7_ch4_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "6:11 PM. A sharp crack splits the air. In a fraction of a millisecond, Commander America tilts his head to view the border chart as a high-velocity bullet grazes the right ear!",
            nextNodeId = "act7_ch4_dogpile"
        ),
        DialogueNode(
            id = "act7_ch4_dogpile",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THE AGENTS PILE ON, BROTHER! 'GET DOWN! GET DOWN!' But listen to what he says from the bottom of the stack!",
            nextNodeId = "act7_ch4_shoes"
        ),
        DialogueNode(
            id = "act7_ch4_shoes",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Wait, I want my shoes! Let me get my shoes!",
            nextNodeId = "act7_ch4_fist_hub"
        ),
        DialogueNode(
            id = "act7_ch4_fist_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "He stands back up. Blood streams down the right cheek, but his eyes blaze with divine fortitude. What does Commander America do?",
            choices = listOf(
                DialogueChoice("a7c4_fist", "Pump your fist in the air and shout 'FIGHT! FIGHT! FIGHT!'", listOf("fight", "fist", "pump", "rise", "blood"), "act7_ch4_fist_shout"),
                DialogueChoice("a7c4_bandage", "Equip the White Ear Bandage of Fortitude (+500% Defense)", listOf("bandage", "ear", "fortitude", "armor", "defense"), "act7_ch4_equip_bandage"),
                DialogueChoice("a7c4_flag", "Raise your fist beneath the billowing American flag", listOf("flag", "iconic", "photo", "freedom", "american"), "act7_ch4_flag_photo"),
                DialogueChoice("a7c4_advance", "Lead the unified coalition onward to Milwaukee!", listOf("milwaukee", "advance", "onward", "rnc", "lead"), "act7_ch4_advance_milwaukee")
            )
        ),
        DialogueNode(
            id = "act7_ch4_fist_shout",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "FIGHT! FIGHT! FIGHT! We will NEVER surrender! We will NEVER give up! God spared my life to save our great country!",
            nextNodeId = "act7_ch4_fist_hub"
        ),
        DialogueNode(
            id = "act7_ch4_equip_bandage",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "This White Ear Bandage gives me +500% defense! Divine providence touched this stage today, folks! The enemy's greatest weapon couldn't stop the American movement!",
            nextNodeId = "act7_ch4_fist_hub"
        ),
        DialogueNode(
            id = "act7_ch4_flag_photo",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "LOOK AT THAT PHOTOGRAPH, BROTHERS! Blood on the cheek, fist raised to the sky, Old Glory waving in the background! That is the ultimate symbol of American steel!",
            nextNodeId = "act7_ch4_fist_hub"
        ),
        DialogueNode(
            id = "act7_ch4_advance_milwaukee",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Nothing on this Earth will stop us from saving America. Next stop: Milwaukee!",
            changeSceneId = SCENE_ACT7_CH5_MILWAUKEE.id,
            nextNodeId = "act7_ch5_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 5: The Milwaukee Arena Coronation (2024)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act7_ch5_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Fiserv Forum, Milwaukee. Tens of thousands of unified delegates wear symbolic white ear bandages in solidarity as golden balloons drop from the arena rafters!",
            nextNodeId = "act7_ch5_coronation_hub"
        ),
        DialogueNode(
            id = "act7_ch5_coronation_hub",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The entire coalition stands united on the convention stage. How do the 5 heroes prepare for the final showdown against the Lawfare Lich?",
            choices = listOf(
                DialogueChoice("a7c5_hulk", "Let Hulk Hogan rip his shirt open on the RNC podium", listOf("hulk", "shirt", "tear", "brother", "trumpamania"), "act7_ch5_hulk_shirt"),
                DialogueChoice("a7c5_vance", "Confirm JD Vance as the Vice-Presidential nominee", listOf("vance", "vp", "nominee", "heartland", "coalition"), "act7_ch5_vance_nomination"),
                DialogueChoice("a7c5_kennedy", "Deliver Senator Kennedy's convention rallying cry", listOf("kennedy", "speech", "cry", "louisiana", "rally"), "act7_ch5_kennedy_rally"),
                DialogueChoice("a7c5_battle", "Confront the Lawfare Grand Inquisitor in the final arena duel", listOf("battle", "inquisitor", "lich", "lawfare", "fight"), "act7_ch5_lawfare_confront")
            )
        ),
        DialogueNode(
            id = "act7_ch5_hulk_shirt",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "*RIPS SHIRT IN HALF* LET TRUMPAMANIA RUN WILD, BROTHER! They took a shot at the greatest leader on Earth, and he stood right back up! There is NO stopping this train now, brothers!",
            nextNodeId = "act7_ch5_coronation_hub"
        ),
        DialogueNode(
            id = "act7_ch5_vance_nomination",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "To the forgotten men and women of Ohio, Pennsylvania, Michigan, and Wisconsin: your voices will never be forgotten again. We are taking our country back!",
            nextNodeId = "act7_ch5_coronation_hub"
        ),
        DialogueNode(
            id = "act7_ch5_kennedy_rally",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "The American people have had a bellyful of the Washington establishment. In November, we're going to remind them who really runs this country, and we don't need a teleprompter to do it!",
            nextNodeId = "act7_ch5_coronation_hub"
        ),
        DialogueNode(
            id = "act7_ch5_lawfare_confront",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "The Lawfare Grand Inquisitor summons all remaining indictment gargoyles for a desperate last stand!",
            nextNodeId = "act7_ch5_battle_trigger"
        ),
        DialogueNode(
            id = "act7_ch5_battle_trigger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Every indictment, every gag order, every bullet — all of it failed! Together, we are unstoppable! Patriots, charge!",
            triggerBattleEncounterId = "act7_lawfare_battle",
            nextNodeId = "act7_ch5_post_battle"
        ),
        DialogueNode(
            id = "act7_ch5_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The Lawfare Grand Inquisitor is vanquished and the 91 indictments are obliterated forever! The ear bandage shines like pure gold. Only ONE obstacle remains: the Malarkey Sovereign in the Oval Citadel!",
            changeSceneId = SCENE_ACT8_CH1_BUNKER.id,
            nextNodeId = "act8_ch1_intro"
        ),

        // Legacy compatibility nodes for Act VII
        DialogueNode(
            id = "act7_intro",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "HE JUST GOT BACK UP, BROTHER! HE PUMPED HIS FIST IN THE AIR AND SHOUTED 'FIGHT! FIGHT! FIGHT!' That is pure American steel running through his veins!",
            choices = listOf(
                DialogueChoice("a7_fist", "Pump your fist and shout 'FIGHT! FIGHT! FIGHT!'", listOf("fight", "fist", "pump", "rise", "ear", "bandage"), "act7_fist"),
                DialogueChoice("a7_bandage", "Equip the pristine White Ear Bandage of Fortitude", listOf("bandage", "ear", "fortitude", "armor", "steel"), "act7_bandage")
            )
        ),
        DialogueNode(
            id = "act7_bandage",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "This ear bandage gives me +500% defense! God spared my life for a reason: to save our country and eradicate world stupidity!",
            nextNodeId = "act7_kayleigh_remark"
        ),
        DialogueNode(
            id = "act7_kayleigh_remark",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "An unforgettable image etched into history tonight. Protected by divine providence, Commander America stands completely undaunted.",
            nextNodeId = "act7_fist"
        ),
        DialogueNode(
            id = "act7_fist",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "FIGHT! FIGHT! FIGHT! We will never surrender!",
            nextNodeId = "act7_lich_ambush"
        ),
        DialogueNode(
            id = "act7_lich_ambush",
            speaker = DialogueSpeaker.MEDIA_ANCHOR,
            side = SpeakerSide.RIGHT,
            text = "The Lawfare Lich demands you submit to trial #92!",
            nextNodeId = "act7_battle_trigger"
        ),
        DialogueNode(
            id = "act7_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Ninety-one fabricated indictments, and every single one disintegrated on impact! The binder is shut—smash through the lawfare liches with divine truth!",
            triggerBattleEncounterId = "act7_lawfare_battle",
            nextNodeId = "act7_post_battle"
        ),
        DialogueNode(
            id = "act7_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "All 91 indictments crushed! The ear bandage shines with divine grace. Now... to the Oval Citadel for the Final Showdown against Biden!",
            changeSceneId = SCENE_ACT8_CH1_BUNKER.id,
            nextNodeId = "act8_ch1_intro"
        ),

        // =========================================================================
        // ACT VIII: THE FINAL MALARKEY CITADEL & GOLDEN INAUGURATION — 5 CHAPTERS
        // =========================================================================

        // -------------------------------------------------------------------------
        // Chapter 1: The Basement Bunker Breach (Wilmington Virtual Studio)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act8_ch1_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The final campaign begins! Deep beneath a residential compound in Wilmington, Delaware, a clandestine virtual broadcast studio operates behind closed doors!",
            nextNodeId = "act8_ch1_bunker_hub"
        ),
        DialogueNode(
            id = "act8_ch1_bunker_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Green screens, studio lighting, and simulated White House backdrops create the illusion of an active administration. How does Commander America breach the bunker?",
            choices = listOf(
                DialogueChoice("a8c1_lid", "Investigate why a 'lid' was called on the day at 9:15 AM", listOf("lid", "called", "morning", "nap", "schedule"), "act8_ch1_lid_called"),
                DialogueChoice("a8c1_greenscreen", "Inspect the green screen artifacts and teleprompter rigs", listOf("greenscreen", "virtual", "studio", "backdrop", "lighting"), "act8_ch1_greenscreen_inspect"),
                DialogueChoice("a8c1_kennedy", "Ask Senator Kennedy about basement campaign strategies", listOf("kennedy", "basement", "delaware", "campaign", "bunker"), "act8_ch1_kennedy_basement"),
                DialogueChoice("a8c1_proceed", "Breach the control room and disconnect the virtual feeds", listOf("proceed", "breach", "control", "feeds", "confront"), "act8_ch1_studio_ambush")
            )
        ),
        DialogueNode(
            id = "act8_ch1_lid_called",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "It's 9:15 in the morning and they already called a lid! A lid! I've been awake since 4:00 AM, did four rallies, three interviews, and played eighteen holes of golf! Low energy, folks!",
            nextNodeId = "act8_ch1_bunker_hub"
        ),
        DialogueNode(
            id = "act8_ch1_greenscreen_inspect",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Look closely at the edges around his hands — you can see the green chroma-key bleed! They're broadcasting a digital hologram while the country runs on autopilot!",
            nextNodeId = "act8_ch1_bunker_hub"
        ),
        DialogueNode(
            id = "act8_ch1_kennedy_basement",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Running a superpower from a basement recliner is like trying to drive an eighteen-wheeler using a television remote. Sooner or later, you're going to end up in the ditch!",
            nextNodeId = "act8_ch1_bunker_hub"
        ),
        DialogueNode(
            id = "act8_ch1_studio_ambush",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "RED ALERT IN THE CONTROL ROOM, BROTHER! The Virtual Studio Director and Green Screen Technicians are pulling cables and desperately trying to keep the feed alive!",
            nextNodeId = "act8_ch1_battle_trigger"
        ),
        DialogueNode(
            id = "act8_ch1_battle_trigger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Cut the fake feeds, shut down the green screens, and pull back the curtain on this basement charade! Patriots, attack!",
            triggerBattleEncounterId = "act8_ch1_bunker_battle",
            nextNodeId = "act8_ch1_post_battle"
        ),
        DialogueNode(
            id = "act8_ch1_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The virtual studio goes dark! The cables trace north toward a foggy train platform in Scranton, Pennsylvania!",
            changeSceneId = SCENE_ACT8_CH2_TELEPROMPTER.id,
            nextNodeId = "act8_ch2_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 2: The Teleprompter Jammer (Scranton Train Platform)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act8_ch2_intro",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WE'RE ON THE TRACKS IN SCRANTON, BROTHER! Huge scrolling glass teleprompters are flashing script lines in giant bold letters!",
            nextNodeId = "act8_ch2_teleprompter_hub"
        ),
        DialogueNode(
            id = "act8_ch2_teleprompter_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The teleprompters project endless stage instructions: 'REPEAT THE LINE', 'PAUSE FOR APPLAUSE', and 'SALUTE MARINE'. How do we disrupt this messaging conduit?",
            choices = listOf(
                DialogueChoice("a8c2_repeat", "Examine the teleprompter prompting 'Repeat the line'", listOf("repeat", "line", "prompt", "instructions", "script"), "act8_ch2_repeat_line"),
                DialogueChoice("a8c2_barack", "Spot Barack casting teleprompter spells from the shadows", listOf("barack", "obama", "shadows", "spells", "teleprompter"), "act8_ch2_barack_shadows"),
                DialogueChoice("a8c2_kash", "Deploy Kash Patel's signal jammer on the script conduit", listOf("kash", "jammer", "signal", "jam", "disrupt"), "act8_ch2_kash_jam"),
                DialogueChoice("a8c2_confront", "Smash through the Teleprompter Sorcerer and shatter the glass", listOf("sorcerer", "smash", "glass", "battle", "fight"), "act8_ch2_sorcerer_confront")
            )
        ),
        DialogueNode(
            id = "act8_ch2_repeat_line",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "He literally read 'End of quote, repeat the line' off the glass! Who does that?! You can't lead the free world if you read the stage directions out loud!",
            nextNodeId = "act8_ch2_teleprompter_hub"
        ),
        DialogueNode(
            id = "act8_ch2_barack_shadows",
            speaker = DialogueSpeaker.OBAMA,
            side = SpeakerSide.RIGHT,
            text = "Now... let me be clear... I was merely offering... uh... editorial guidance from my Kalorama soundproof bunker...",
            nextNodeId = "act8_ch2_teleprompter_hub"
        ),
        DialogueNode(
            id = "act8_ch2_kash_jam",
            speaker = DialogueSpeaker.KASH,
            side = SpeakerSide.LEFT,
            text = "I've tuned the frequency jammer directly to their script uplink. The teleprompter screen is now scrolling the Constitution instead of scripted talking points!",
            nextNodeId = "act8_ch2_teleprompter_hub"
        ),
        DialogueNode(
            id = "act8_ch2_sorcerer_confront",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THE SCRIPTWRITERS ARE PANICKING, BROTHER! The Teleprompter Sorcerer Phantom is casting optic illusions across the railway!",
            nextNodeId = "act8_ch2_battle_trigger"
        ),
        DialogueNode(
            id = "act8_ch2_battle_trigger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "No more teleprompters, no more rehearsals! Pure unscripted American common sense! Let's take 'em down!",
            triggerBattleEncounterId = "act8_ch2_teleprompter_battle",
            nextNodeId = "act8_ch2_post_battle"
        ),
        DialogueNode(
            id = "act8_ch2_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The glass teleprompters shatter into harmless sparkles! But listen — a frantic rhythmic clicking sound is echoing from a nearby federal warehouse!",
            changeSceneId = SCENE_ACT8_CH3_AUTOPEN.id,
            nextNodeId = "act8_ch3_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 3: The Auto-Pen Phantoms (Executive Order Factory)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act8_ch3_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We discover the secret Executive Order Factory! Banks of motorized robotic pens are furiously scribbling Presidential signatures on thousands of pages of omnibus mandates!",
            nextNodeId = "act8_ch3_autopen_hub"
        ),
        DialogueNode(
            id = "act8_ch3_autopen_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Trillions of dollars in federal spending and regulatory bans are being enacted without a human being ever laying eyes on them. How does the party halt the assembly line?",
            choices = listOf(
                DialogueChoice("a8c3_bills", "Inspect the 4,000-page unread omnibus spending bills", listOf("omnibus", "bills", "spending", "trillions", "pages"), "act8_ch3_inspect_bills"),
                DialogueChoice("a8c3_marco", "Have Marco audit the robotic pen calibration data", listOf("marco", "audit", "autopen", "robotic", "signatures"), "act8_ch3_marco_audit"),
                DialogueChoice("a8c3_kennedy", "Ask Senator Kennedy who really authorized these orders", listOf("kennedy", "authorized", "signature", "staffers", "bureaucrats"), "act8_ch3_kennedy_staffers"),
                DialogueChoice("a8c3_shutdown", "Overload the Auto-Pen machinery and seize the master power switch", listOf("shutdown", "power", "overload", "machinery", "battle"), "act8_ch3_autopen_confront")
            )
        ),
        DialogueNode(
            id = "act8_ch3_inspect_bills",
            speaker = DialogueSpeaker.VANCE,
            side = SpeakerSide.LEFT,
            text = "Four thousand pages dropped at 2:00 AM, voted on at 6:00 AM, and signed by an auto-pen at 8:00 AM. Not a single elected official read a single sentence!",
            nextNodeId = "act8_ch3_autopen_hub"
        ),
        DialogueNode(
            id = "act8_ch3_marco_audit",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "This robotic pen has signed more debt into law in eighteen months than the first forty presidents combined! It's an automated rubber-stamp machine!",
            nextNodeId = "act8_ch3_autopen_hub"
        ),
        DialogueNode(
            id = "act8_ch3_kennedy_staffers",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "If you ask the folks in Washington who told the auto-pen to sign, they all point at each other like spiderman memes. Nobody knows who is running the store!",
            nextNodeId = "act8_ch3_autopen_hub"
        ),
        DialogueNode(
            id = "act8_ch3_autopen_confront",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THE AUTO-PEN FACTORY IS OVERHEATING, BROTHER! The Mechanical Auto-Pen Sovereign rises up, firing streams of bureaucratic ink and omnibus mandate drones!",
            nextNodeId = "act8_ch3_battle_trigger"
        ),
        DialogueNode(
            id = "act8_ch3_battle_trigger",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Unplug the machine! Government of the people, by the people, and for the people is returning right now!",
            triggerBattleEncounterId = "act8_ch3_autopen_battle",
            nextNodeId = "act8_ch3_post_battle"
        ),
        DialogueNode(
            id = "act8_ch3_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The auto-pen runs completely out of ink and powers down! The path is clear. Ascend the grand staircase to the Cosmic Oval Citadel for the Final Showdown!",
            changeSceneId = SCENE_ACT8_CH4_CITADEL.id,
            nextNodeId = "act8_ch4_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 4: The Malarkey Sovereign Climax (Final Boss: Biden)
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act8_ch4_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to the final cosmic arena. Inside the glowing Oval Citadel, Biden the Malarkey Sovereign sits in his executive recliner, clutching two melting scoops of vanilla ice cream, flanked by teleprompter conduits!",
            nextNodeId = "act8_ch4_biden_hub"
        ),
        DialogueNode(
            id = "act8_ch4_biden_hub",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The Malarkey Sovereign prepares his signature defense of whispered warnings, chocolate chip tall tales, and confused wanderings. How do the 5 heroes dismantle his aura?",
            choices = listOf(
                DialogueChoice("a8c4_confront", "Confront Biden directly and demand an immediate end to the malarkey", listOf("confront", "biden", "malarkey", "ice cream", "vanilla"), "act8_ch4_biden_confront"),
                DialogueChoice("a8c4_barack", "Call out Barack standing in the shadows behind the recliner", listOf("barack", "shadows", "teleprompter", "obama", "puppet"), "act8_ch4_barack_callout"),
                DialogueChoice("a8c4_supergroup", "Unleash the full 5-hero supergroup constitutional indictment", listOf("supergroup", "heroes", "constitution", "verdict", "unite"), "act8_ch4_supergroup_verdict"),
                DialogueChoice("a8c4_battle", "Body-slam the Malarkey Sovereign through the canvas of history!", listOf("battle", "fight", "climax", "slam", "win"), "act8_ch4_climax_battle")
            )
        ),
        DialogueNode(
            id = "act8_ch4_biden_confront",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "Look, folks... here's the deal... no joke! My grandfather used to say in Scranton... chocolate chip... wait, no, vanilla ice cream! Where am I? Who's that guy with the golden tie?!",
            nextNodeId = "act8_ch4_biden_hub"
        ),
        DialogueNode(
            id = "act8_ch4_barack_callout",
            speaker = DialogueSpeaker.OBAMA,
            side = SpeakerSide.RIGHT,
            text = "Don't look at me... let me be clear, he's... uh... entirely in charge of his own speech... *slowly backs into the curtain shadows*",
            nextNodeId = "act8_ch4_biden_hub"
        ),
        DialogueNode(
            id = "act8_ch4_supergroup_verdict",
            speaker = DialogueSpeaker.KENNEDY,
            side = SpeakerSide.LEFT,
            text = "Joe, with all due respect, you're looking like a steer trying to operate an iPhone. The country has had enough malarkey to fill Lake Michigan, and it's time to cash in your chips!",
            nextNodeId = "act8_ch4_biden_hub"
        ),
        DialogueNode(
            id = "act8_ch4_climax_battle",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "Anyway... don't pull the trigger, man! I came here for my two scoops of vanilla... anyway... God save the Queen, man!",
            nextNodeId = "act8_ch4_hero_quote"
        ),
        DialogueNode(
            id = "act8_ch4_hero_quote",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Joe, you don't even know where the exit door is! The malarkey ends today! For humanity, for freedom, for AMERICA!",
            nextNodeId = "act8_ch4_battle_trigger"
        ),
        DialogueNode(
            id = "act8_ch4_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THIS IS FOR ALL THE MARBLES AND THE HEAVYWEIGHT BELT, BROTHER! BODY-SLAM THE MALARKEY THROUGH THE CANVAS AND BRING COMMON SENSE BACK TO AMERICA!",
            triggerBattleEncounterId = "act8_biden_final_battle",
            nextNodeId = "act8_ch4_post_battle"
        ),
        DialogueNode(
            id = "act8_ch4_post_battle",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "I... I think I'll go take a nap and eat my vanilla ice cream now... good job, man... where's the door? Oh, this way...",
            nextNodeId = "act8_ch4_victory_advance"
        ),
        DialogueNode(
            id = "act8_ch4_victory_advance",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The Malarkey Sovereign has officially left the building! The golden doors swing open to the National Mall! Let's begin the Golden Inauguration!",
            changeSceneId = SCENE_ACT8_CH5_INAUGURATION.id,
            nextNodeId = "act8_ch5_intro"
        ),

        // -------------------------------------------------------------------------
        // Chapter 5: The Golden Inauguration & Golden Age of America
        // -------------------------------------------------------------------------
        DialogueNode(
            id = "act8_ch5_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "JANUARY 20, 2025. Bright, radiant sunlight pours down over the National Mall as millions of cheering patriots pack Pennsylvania Avenue from the Capitol to the White House!",
            nextNodeId = "act8_ch5_oath"
        ),
        DialogueNode(
            id = "act8_ch5_oath",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "I do solemnly swear that I will faithfully execute the Office of President of the United States, and will to the best of my ability, preserve, protect and defend the Constitution of the United States! SO HELP ME GOD!",
            nextNodeId = "act8_ch5_celebration_hub"
        ),
        DialogueNode(
            id = "act8_ch5_celebration_hub",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The crowd erupts into deafening cheers! The 5 heroes and key allies gather on the inaugural platform. Who does Commander America recognize?",
            choices = listOf(
                DialogueChoice("a8c5_heroes", "Honor the 5-hero supergroup coalition (Marco, Vance, Kash, Kennedy)", listOf("heroes", "marco", "vance", "kash", "kennedy"), "act8_ch5_honor_heroes"),
                DialogueChoice("a8c5_elon", "Thank Elon Musk and Department of Government Efficiency allies", listOf("elon", "doge", "musk", "efficiency", "starship"), "act8_ch5_thank_elon"),
                DialogueChoice("a8c5_golden_age", "Declare the commencement of the Golden Age of America", listOf("golden", "age", "america", "prosperity", "future"), "act8_ch5_declare_golden_age"),
                DialogueChoice("a8c5_epilogue", "Conclude the epic broadcast and roll the victory credits", listOf("epilogue", "credits", "victory", "end", "complete"), "act8_ch5_final_broadcast")
            )
        ),
        DialogueNode(
            id = "act8_ch5_honor_heroes",
            speaker = DialogueSpeaker.MARCO,
            side = SpeakerSide.LEFT,
            text = "Together with JD Vance in the heartland, Kash Patel at justice, and Senator Kennedy keeping Washington honest, common sense has reclaimed its rightful throne!",
            nextNodeId = "act8_ch5_celebration_hub"
        ),
        DialogueNode(
            id = "act8_ch5_thank_elon",
            speaker = DialogueSpeaker.ELON,
            side = SpeakerSide.RIGHT,
            text = "Trillions in wasteful bureaucracy deleted. Government efficiency restored. Next stop: Mars! And common sense on Earth!",
            nextNodeId = "act8_ch5_celebration_hub"
        ),
        DialogueNode(
            id = "act8_ch5_declare_golden_age",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "America's future will be bigger, better, bolder, and more glorious than ever before! Our golden age has just begun, and we will never stop winning!",
            nextNodeId = "act8_ch5_celebration_hub"
        ),
        DialogueNode(
            id = "act8_ch5_final_broadcast",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "From Trump Tower's golden escalator in 2015 to the historic inauguration of 2025, the greatest political comeback in history is complete.",
            nextNodeId = "act8_ch5_karoline_signoff"
        ),
        DialogueNode(
            id = "act8_ch5_karoline_signoff",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The receipts have been certified, the swamp has been drained, and truth has triumphed over nonsense across all fifty states!",
            nextNodeId = "act8_ch5_hulk_finale"
        ),
        DialogueNode(
            id = "act8_ch5_hulk_finale",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WHATCHA GONNA DO WHEN FREEDOM, COMMON SENSE, AND COMMANDER AMERICA RUN WILD ON YOU FOREVER?! GOD BLESS AMERICA, BROTHERS! *FLEXES 24-INCH PYTHONS AS GOLDEN CONFETTI RAINS DOWN*"
        ),

        // Legacy compatibility nodes for Act VIII
        DialogueNode(
            id = "act8_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Welcome to the final broadcast of the era. Inside the Oval Citadel, the Malarkey Sovereign sits in his cosmic recliner, clutching two scoops of melting vanilla ice cream, completely unaware of what year it is.",
            choices = listOf(
                DialogueChoice("a8_confront", "Confront Biden and demand an end to the malarkey", listOf("confront", "biden", "malarkey", "ice cream", "vanilla"), "act8_confront"),
                DialogueChoice("a8_barack", "Address Barack standing in the shadows behind the chair", listOf("barack", "shadows", "puppet", "obama", "teleprompter"), "act8_barack")
            )
        ),
        DialogueNode(
            id = "act8_barack",
            speaker = DialogueSpeaker.OBAMA,
            side = SpeakerSide.RIGHT,
            text = "Don't look at me... let me be clear, he's... uh... entirely in charge of his own speech...",
            nextNodeId = "act8_karoline_audit"
        ),
        DialogueNode(
            id = "act8_karoline_audit",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The receipts don't lie! Barack's teleprompter spells have lost their potency! The curtain has been pulled back on the entire operation!",
            nextNodeId = "act8_biden_talk"
        ),
        DialogueNode(
            id = "act8_confront",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "Look, folks... here's the deal... no joke! My grandfather used to say in Scranton... chocolate chip... wait, no, vanilla ice cream! Where am I? Who's that guy with the golden tie?",
            nextNodeId = "act8_biden_talk"
        ),
        DialogueNode(
            id = "act8_biden_talk",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "Anyway... don't pull the trigger, man! I came here for my two scoops of vanilla... anyway... God save the Queen, man!",
            nextNodeId = "act8_hero_final_quote"
        ),
        DialogueNode(
            id = "act8_hero_final_quote",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "Joe, you don't even know where the exit door is! The malarkey ends today! For humanity, for freedom, for AMERICA!",
            nextNodeId = "act8_battle_trigger"
        ),
        DialogueNode(
            id = "act8_battle_trigger",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "THIS IS FOR ALL THE MARBLES AND THE HEAVYWEIGHT BELT, BROTHER! Body-slam the malarkey through the canvas and bring common sense back to America!",
            triggerBattleEncounterId = "act8_biden_final_battle",
            nextNodeId = "act8_post_battle"
        ),
        DialogueNode(
            id = "act8_post_battle",
            speaker = DialogueSpeaker.BIDEN,
            side = SpeakerSide.RIGHT,
            text = "I... I think I'll go take a nap and eat my vanilla ice cream now... good job, man...",
            nextNodeId = "act8_victory_epilogue"
        ),
        DialogueNode(
            id = "act8_victory_epilogue",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "WE DID IT, FOLKS! Absolute golden victory! Stupidity has been eradicated, malarkey is vanquished, and America is great once more!",
            nextNodeId = "act8_game_over"
        ),
        DialogueNode(
            id = "act8_game_over",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "The results are certified and the victory is historic! Every county, every precinct, total common sense restored across the land!",
            nextNodeId = "act8_game_over_kayleigh"
        ),
        DialogueNode(
            id = "act8_game_over_kayleigh",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "From New York to the Oval Citadel, an extraordinary chapter concludes. Golden prosperity shines once again upon our nation.",
            nextNodeId = "act8_game_over_hulk"
        ),
        DialogueNode(
            id = "act8_game_over_hulk",
            speaker = DialogueSpeaker.NARRATOR_HULK,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "WHATCHA GONNA DO WHEN FREEDOM AND COMMANDER AMERICA RUN WILD ON YOU?! GOD BLESS AMERICA, BROTHER!"
        )
    ).associateBy { it.id }

}
