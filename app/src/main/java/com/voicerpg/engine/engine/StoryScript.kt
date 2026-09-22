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
        backgroundAsset = "story/clockwork_bastion.jpg",
        initialNodeId = "act1_ch2_intro",
        ambientDescription = "Blinding studio spotlights illuminate sixteen rival podiums and a hostile press panel."
    )

    val SCENE_ACT1_CH3_RALLY = StoryScene(
        id = "scene_act1_ch3_rally",
        name = "Manchester Airport Hangar",
        chapterTitle = "Act I - Ch 3: The Primary Sweep (2016)",
        backgroundAsset = "story/final_summit.jpg",
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
        ambientDescription = "The Capitol steps gleam under a crisp January sky as tens of thousands of American patriots pack the National Mall."
    )

    // Act II - Chapter 2: Entering the Oval Citadel (The Oval Office)
    val SCENE_ACT2_CH2_OVAL = StoryScene(
        id = "scene_act2_ch2_oval",
        name = "The Oval Office — Resolute Desk",
        chapterTitle = "Act II - Ch 2: The Executive Pen (Jan 2017)",
        backgroundAsset = "story/oval_citadel.jpg",
        initialNodeId = "act2_ch2_intro",
        ambientDescription = "Gold drapes replace the old burgundy curtains. The Resolute Desk is cleared of bobbleheads and ready for historic executive action."
    )

    // Act II - Chapter 3: Confronting Madam Speaker (Capitol Rotunda)
    val SCENE_ACT2_CH3_ROTUNDA = StoryScene(
        id = "scene_act2_ch3_rotunda",
        name = "Capitol Rotunda & Committee Hall",
        chapterTitle = "Act II - Ch 3: The Stock-Trading Lich (2017)",
        backgroundAsset = "story/capitol_rotunda.jpg",
        initialNodeId = "act2_ch3_intro",
        ambientDescription = "Marble columns tower above as Pelosi's microchip options spike on the trading terminal — right before the regulatory vote."
    )

    // Legacy alias: the existing act2_oval still points to Ch1 (backward compat)
    val SCENE_ACT2_OVAL = StoryScene(
        id = "scene_act2_oval",
        name = "The Oval Citadel",
        chapterTitle = "Act II: The Inaugural Siege & Media Wars (2017)",
        backgroundAsset = "story/oval_citadel.jpg",
        initialNodeId = "act2_ch1_intro",
        ambientDescription = "Gilded presidential drapes frame the Oval Office where the Deep State swamp still lurks."
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
        backgroundAsset = "story/clockwork_bastion.jpg",
        initialNodeId = "act3_ch1_intro",
        ambientDescription = "High above the demilitarized zone, red flags ripple in the wind near a table set with fast-food feasts."
    )

    val SCENE_ACT4_MUELLER = StoryScene(
        id = "scene_act4_mueller",
        name = "The Chamber of Phantom Hoaxes",
        chapterTitle = "Act IV: The Mueller Illusion Hoax (2019)",
        backgroundAsset = "story/obsidian_vaults.jpg",
        initialNodeId = "act4_intro",
        ambientDescription = "Spectral paperwork and phantom subpoenas drift through the foggy tribunal hall."
    )

    val SCENE_ACT5_MARALAGO = StoryScene(
        id = "scene_act5_maralago",
        name = "The Mar-a-Lago Fortress",
        chapterTitle = "Act V: Midnight Sorcery & The Exile (2020-2021)",
        backgroundAsset = "story/mausoleum_sun.jpg",
        initialNodeId = "act5_intro",
        ambientDescription = "Ocean breezes sweep across golden palm trees surrounding Commander America's exile retreat."
    )

    val SCENE_ACT6_WASTELAND = StoryScene(
        id = "scene_act6_wasteland",
        name = "The Word-Salad Wasteland",
        chapterTitle = "Act VI: The Word-Salad Wasteland (2022-2023)",
        backgroundAsset = "story/rotting_marsh.jpg",
        initialNodeId = "act6_intro",
        ambientDescription = "Floating venn diagrams and endless loops of circular logic drift over a misty swamp."
    )

    val SCENE_ACT7_SANDS = StoryScene(
        id = "scene_act7_sands",
        name = "The Sands of Resolve",
        chapterTitle = "Act VII: Sands of Resolve & Steel Ear (2024)",
        backgroundAsset = "story/shadowed_crags.jpg",
        initialNodeId = "act7_intro",
        ambientDescription = "Desert winds howl across ancient ruins as legal writs float like cursed ravens."
    )

    val SCENE_ACT8_CITADEL_FINAL = StoryScene(
        id = "scene_act8_citadel_final",
        name = "The Final Malarkey Citadel",
        chapterTitle = "Act VIII: Eradicating Malarkey - The Final Showdown",
        backgroundAsset = "story/final_summit.jpg",
        initialNodeId = "act8_intro",
        ambientDescription = "A cosmic arena powered by infinite scoops of vanilla ice cream and stumbles."
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
        SCENE_ACT4_MUELLER.id to SCENE_ACT4_MUELLER,
        SCENE_ACT5_MARALAGO.id to SCENE_ACT5_MARALAGO,
        SCENE_ACT6_WASTELAND.id to SCENE_ACT6_WASTELAND,
        SCENE_ACT7_SANDS.id to SCENE_ACT7_SANDS,
        SCENE_ACT8_CITADEL_FINAL.id to SCENE_ACT8_CITADEL_FINAL
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
            changeSceneId = SCENE_ACT4_MUELLER.id,
            nextNodeId = "act4_intro"
        ),
        // Legacy post battle alias
        DialogueNode(
            id = "act3_post_battle",
            speaker = DialogueSpeaker.ROCKET_MAN,
            side = SpeakerSide.RIGHT,
            text = "Your handshake grip is unstoppable! We have achieved complete peaceful harmony!",
            changeSceneId = SCENE_ACT4_MUELLER.id,
            nextNodeId = "act4_intro"
        ),

        // =========================================================================
        // ACT IV: THE MUELLER ILLUSION HOAX (2019)
        // =========================================================================
        DialogueNode(
            id = "act4_intro",
            speaker = DialogueSpeaker.NARRATOR_KAROLINE,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "Good morning to everyone except the anonymous sources who pushed three straight years of phantom collusion! My binder is open to zero charges, zero evidence, and 100% total exoneration!",
            choices = listOf(
                DialogueChoice("a4_nothing", "Expose the Nothingburger Reveal", listOf("nothing", "nothingburger", "hoax", "no collusion", "witch hunt"), "act4_nothing"),
                DialogueChoice("a4_transcripts", "Release the Perfect Phone Call transcript", listOf("transcript", "perfect", "call", "phone", "read"), "act4_transcripts")
            )
        ),
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
            nextNodeId = "act4_post_battle"
        ),
        DialogueNode(
            id = "act4_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The phantom faded into zero evidence! Another witch hunt shattered!",
            changeSceneId = SCENE_ACT5_MARALAGO.id,
            nextNodeId = "act5_intro"
        ),

        // =========================================================================
        // ACT V: MIDNIGHT SORCERY & THE EXILE (2020-2021)
        // =========================================================================
        DialogueNode(
            id = "act5_intro",
            speaker = DialogueSpeaker.NARRATOR_KAYLEIGH,
            side = SpeakerSide.CENTER_NARRATOR,
            text = "We are live tonight from Mar-a-Lago Fortress. Following the mysterious 3 AM ballot spikes, Commander America has commenced the greatest training arc in modern history on the championship fairway.",
            choices = listOf(
                DialogueChoice("a5_golf", "Train on the championship golf course", listOf("golf", "swing", "driver", "hole in one", "train"), "act5_golf"),
                DialogueChoice("a5_rally", "Hold massive rallies across the lands", listOf("rally", "crowd", "maga", "patriots", "comeback"), "act5_rally")
            )
        ),
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
            nextNodeId = "act5_post_battle"
        ),
        DialogueNode(
            id = "act5_post_battle",
            speaker = DialogueSpeaker.COMMANDER_AMERICA,
            side = SpeakerSide.LEFT,
            text = "The Golem crumbles into scrap paper! Now, we navigate the confusing realm of the Vice-Sovereign!",
            changeSceneId = SCENE_ACT6_WASTELAND.id,
            nextNodeId = "act6_intro"
        ),

        // =========================================================================
        // ACT VI: THE WORD-SALAD WASTELAND (2022-2023)
        // =========================================================================
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
            changeSceneId = SCENE_ACT7_SANDS.id,
            nextNodeId = "act7_intro"
        ),

        // =========================================================================
        // ACT VII: SANDS OF RESOLVE & STEEL EAR (2024)
        // =========================================================================
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
            changeSceneId = SCENE_ACT8_CITADEL_FINAL.id,
            nextNodeId = "act8_intro"
        ),

        // =========================================================================
        // ACT VIII: ERADICATING MALARKEY - THE FINAL BOSS (BIDEN)
        // =========================================================================
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
