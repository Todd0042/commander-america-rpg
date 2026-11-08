<p align="center">
  <a href="https://ko-fi.com/todd0042">
    <img src="https://raw.githubusercontent.com/Todd0042/creator-assets/main/banners/todd_creator_banner_v1.0.png" alt="Support Todd on Ko-fi" width="100%" />
  </a>
</p>

---

# 🇺🇸 Commander America RPG: The 2024 Political Satire Voice JRPG

[![Version](https://img.shields.io/badge/Version-1.6.0-ff2a85.svg)](https://github.com/Todd0042/commander-america-rpg/releases)
[![License: PolyForm Noncommercial](https://img.shields.io/badge/License-PolyForm%20Noncommercial%201.0.0-00f0ff.svg)](LICENSE)
[![Platform: Android 8.0+](https://img.shields.io/badge/Platform-Android%208.0%2B%20(API%2026%2B)-3DDC84.svg?logo=android&logoColor=white)](https://developer.android.com)
[![16 KB Page Size](https://img.shields.io/badge/16%20KB-Compatible-success.svg)](https://developer.android.com/16kb-page-size)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-7F52FF.svg?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![UI: Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%202024.10.01-4285F4.svg?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Unit Tests](https://img.shields.io/badge/Unit%20Tests-232%20Passing-39ff14.svg)](app/src/test/java/com/voicerpg/engine/CommanderAmericaGameTest.kt)
[![Engine: VoiceRPG](https://img.shields.io/badge/Engine-VoiceRPG%20v2.0.0-teal.svg)](https://github.com/Todd0042/voice-rpg-engine)
[![Support on Ko-fi](https://img.shields.io/badge/Ko--fi-Support%20Creator-F16061?style=flat&logo=ko-fi&logoColor=white)](https://ko-fi.com/todd0042)

> **An audio-first, voice-commanded political satire JRPG for Android where hands-free incantations, tactical ATB combat, and real-life political history collide — featuring a 2-Track campaign (Track A: "Paint it Red" Coalition of Rebels vs. Track B: "Bewilder with Blue" Standard-Bearer Relay), Zero RPG Noise philosophy, multi-voice Android TTS character binding, and 100% screenless Pocket Walk mode.**

```
       _________________________________________________________________________
      |  COMMUNITY NOTES INQUISITOR                 HP [████████████░░░]  78%   |
      |  "Context is required for this action!"     MP [███████████████] 100%   |
      |_________________________________________________________________________|
      |  COMMANDER AMERICA: "By the Art of the Deal, we impose tariffs now!"    |
      |  RESONANCE: MYTHIC (134%) >>> TARIFF STRIKE DEALS 2.25x BASE DAMAGE!     |
      |_________________________________________________________________________|
```

---

## 📖 Table of Contents

* **Part 1 — Consumer Overview & Quick Start**
  * [🎙️ What Is Commander America RPG?](#-what-is-commander-america-rpg)
  * [🏛️ How a Campaign Rally Works in 3 Steps](#-how-a-campaign-rally-works-in-3-steps)
  * [🗳️ Dual Campaign Tracks: "Paint it Red" & "Bewilder with Blue"](#-dual-campaign-tracks-paint-it-red--bewilder-with-blue)
  * [🇺🇸 The 8-Act Story & Iconic Roster](#-the-8-act-story--iconic-roster)
  * [🚶 100% Screenless Pocket Walk Mode](#-100-screenless-pocket-walk-mode)
  * [🚀 Quick Download & Installation (Android APK)](#-quick-download--installation-android-apk)
* **Part 2 — Technical Architecture & Engine Integration**
  * [1. Powered by VoiceRPG Engine](#1-powered-by-voicerpg-engine)
  * [2. How Commander America Configures the Engine (`GameContent.kt`, `GameManifest`)](#2-how-commander-america-configures-the-engine)
  * [3. Custom Satirical Spells & Thesaurus Roots (`MovesetTable.kt`, `SpellThesaurus.kt`)](#3-custom-satirical-spells--thesaurus-roots)
  * [4. Procedural 8-Act Script & Non-Destructive Choice Elimination (`StoryScript.kt`)](#4-procedural-8-act-script--non-destructive-choice-elimination)
  * [5. Satirical Soundbite Audio Routing (`SoundbitePlayer.kt`, `SpeechManager.kt`)](#5-satirical-soundbite-audio-routing)
  * [6. Multi-Repo Synchronization Workflow (`sync_engine_to_games.sh`)](#6-multi-repo-synchronization-workflow)
  * [7. Automated Unit Verification Suite (`tests/`)](#7-automated-unit-verification-suite)
  * [8. Provenance, Political Satire & Non-Commercial Licensing](#8-provenance-political-satire--non-commercial-licensing)

---

# Part 1 — Consumer Overview & Quick Start

## 🎙️ What Is Commander America RPG?

**Commander America RPG** is a full-length, satirical comedy JRPG developed natively for Android. 

Players step into the shoes of **Commander America** and his ragtag coalition of political warriors as they battle their way through the bureaucratic labyrinth of modern Washington, corporate lobbyists, teleprompter wizards, and media fact-checkers.

Instead of navigating clunky touch interfaces, you issue commands with your **actual voice**:
- **🗣️ Speak Real Political Catchphrases**: Unleash iconic abilities like *"Art of the Deal"*, *"Big Beautiful Wall"*, *"Tariff Strike"*, or *"Covfefe Blast"* by chanting into your phone.
- **⚡ Resonance Acoustic Scoring**: Yell with rally-level inflection, confidence, and cadence to score **MYTHIC (100%–150%)** and **TRANSCENDENTAL ($\ge 155\%$)** resonance multipliers that supercharge your spells up to 3.0x base effectiveness.
- **👑 Legendary Companion Fellowship**: Command allies like JD of the Hills, Chief Elon, Tulsi of the Pacific, Vivek the Disruptor, and Bobby the Healthy.

---

## 🏛️ How a Campaign Rally Works in 3 Steps

1. **Speak to Command**: On your turn, call out a spell or party member name (*"Commander America tariff strike"*, *"Elon rocket volley"*, *"Tulsi surf ward"*).
2. **Inflect with Conviction**: Speak with rhythmic, deliberate rally phrasing. The on-device Resonance Engine evaluates your decibel volume, pitch contour, and thesaurus keywords.
3. **Listen to the Narrative Firestorm**: The engine's multi-voice TTS and authentic historical soundbites react instantly to your choices and combat victories.

---

## 🗳️ Dual Campaign Tracks: "Paint it Red" & "Bewilder with Blue"

From the Title Screen, players can choose their journey:
- **Track A ("Paint it Red")**: Lead the Coalition of Rebels through 8 explosive Acts, storming debate stages, disrupting donor summits, and taking down bureaucratic bosses.
- **Track B ("Bewilder with Blue")**: The Standard-Bearer Relay alternate perspective, featuring teleprompter gauntlets, committee hearings, and political spin maneuvers.

---

## 🇺🇸 The 8-Act Story & Iconic Roster

- **Act 1: The Escalator Descent & The Rust Belt Rumbles** (Battle: Low-Energy Dynast & Debate Moderatrons).
- **Act 2: The Deep Swamp & The Fog of Litigation** (Battle: Subpoena Golem & Bureaucratic Paperwork Swarms).
- **Act 3: Silicon Valley Summits & The Free Speech Frontier** (Recruitment: Chief Elon & The Algorithmic Overlords).
- **Act 4: The MAHA Health Odyssey** (Recruitment: Bobby the Healthy & Ultra-Processed Food Titans).
- **Act 5: The War Room of the Pacific** (Recruitment: Tulsi the Free & The Warhawk Syndicate).
- **Act 6: The Midnight Ballot Vault** (Confrontation: Counting Machine Glitches & The Mail-in Phantom).
- **Act 7: The Teleprompter Citadel** (Showdown: Barry the Teleprompter & Madame Vice's Word Salad Golem).
- **Act 8: The Inaugural Summit** (Climax: The Beltway Leviathan).

---

## 🚶 100% Screenless Pocket Walk Mode

Put your phone in your pocket and play hands-free while walking or exercising! Pocket Mode engages an OLED battery-saving touch-guard, narrates all story beats via distinct Android TTS voice profiles, and lets you query status hands-free (*"Status"*, *"Enemies"*, *"Fellowship"*, *"Help"*).

---

## 🚀 Quick Download & Installation (Android APK)

1. Grab **`commander-america-v1.6.0-debug.apk`** from [Releases](https://github.com/Todd0042/commander-america-rpg/releases).
2. Install the APK on any Android 8.0+ device (API 26 or higher).
3. Tap **Allow** when prompted for Microphone access to enable voice incantations.

---

# Part 2 — Technical Architecture & Engine Integration

## 1. Powered by VoiceRPG Engine

Commander America RPG is built on the open-source **[VoiceRPG Engine](https://github.com/Todd0042/voice-rpg-engine)** framework. 

Rather than reimplementing core audio pipelines, speech recognition loops, and turn-based battle mechanics from scratch, Commander America imports the modular engine foundation and focuses purely on satirical campaign assets, bespoke political movesets, and character dialogue.

For complete engine architectural specifications, resonance math formulas, and framework source code, visit:  
👉 **[https://github.com/Todd0042/voice-rpg-engine](https://github.com/Todd0042/voice-rpg-engine)**

---

## 2. How Commander America Configures the Engine

Commander America configures the engine dynamically through [`GameContent.kt`](app/src/main/java/com/voicerpg/engine/content/GameContent.kt):
- **Campaign Manifest (`GameManifest`):** Registers the dual campaign tracks (`track_a` "Paint it Red" and `track_b` "Bewilder with Blue") along with starting scene pointers (`scene_act1_intro`, `scene_act1_b_intro`).
- **Speaker TTS Voice Bindings (`preferredVoiceIds`):** Maps each political caricature (Commander America, Elon, Tulsi, Vivek, Bobby, Barry, Kamala, Joe) to distinct Android TTS pitch, language, and voice model profiles for immersive, distinct vocal dialogue.

---

## 3. Custom Satirical Spells & Thesaurus Roots

All satirical abilities are registered into [`MovesetTable.kt`](app/src/main/java/com/voicerpg/engine/combat/MovesetTable.kt) with custom thesaurus root mappings in [`SpellThesaurus.kt`](app/src/main/java/com/voicerpg/engine/engine/SpellThesaurus.kt):
- **Commander America:** `art_of_deal` (`DEFENSE`), `wall` (`DEFENSE`), `tariff_strike` (`ATTACK`), `covfefe_blast` (`AOE_ATTACK`).
- **Chief Elon:** `x_post` (`ATTACK`), `rocket_volley` (`AOE_ATTACK`), `cybertruck_slam` (`DEFENSE`).
- **Thesaurus Merging:** Game-specific chant keywords (*"deal"*, *"wall"*, *"tariff"*, *"covfefe"*, *"rocket"*, *"maha"*) merge seamlessly into the core engine's phonetic dictionary.

---

## 4. Procedural 8-Act Script & Non-Destructive Choice Elimination

The story script in [`StoryScript.kt`](app/src/main/java/com/voicerpg/engine/engine/StoryScript.kt) utilizes the engine's choice elimination mechanics:
- When looping back to dialogue hub nodes, previously selected objectives display a `✓ DONE` badge and are rejected with helpful voice advice (*"That objective has already been completed..."*).
- State is preserved atomically in `SaveManager` without altering the core story tree structure.

---

## 5. Satirical Soundbite Audio Routing

In addition to the engine's Android TTS narrator, Commander America integrates an authentic real-audio soundbite player powered by 100% legally legitimate, public domain official U.S. government recordings (White House archives and official presidential addresses):
- **Commander America (Donald Trump):**
  - `"Nothing will sway us... greatness is our birthright, and the future belongs to us."` (`trump_greatness_birthright.ogg`)
  - `"If you count the legal votes, I easily win."` (`trump_easily_win.ogg`)
- **Joe Naptime (Joe Biden):**
  - `"Truinanashabbadepressure"` (`biden_truinanashabbadepressure.ogg`)
  - `"Badakathcare"` (`biden_badakathcare.ogg`)
- **Barry Teleprompter (Barack Obama):**
  - `"Don't boo, vote!"` (`obama_dont_boo_vote.ogg`)
- **Spicy Sean (Sean Spicer):**
  - `"This was the largest audience to ever witness an inauguration, period, both in person and around the globe."` (`spicer_period.ogg`)
- **Hill-Rod (Hillary Clinton):**
  - `"Last night, I congratulated Donald Trump and offered to work with him on behalf of our country."` (`hillary_congratulated_trump.ogg`)
- **Madame Vice (Kamala Harris):**
  - `"When people can ride a bus because it has a lift..."` (`kamala_ride_a_bus.ogg`)
- **White House Press Secretaries (Jen Psaki & Kayleigh McEnany):**
  - `"The preference is of course legislative action because it's more permanent, it's more lasting."` (`psaki_legislative_action.ogg`)
  - `"Our healthcare providers, as President Trump has repeatedly acknowledged, are our heroes..."` (`kayleigh_heroes.ogg`)

- Audio clips are normalized to -16 LUFS (44.1 kHz Vorbis) with automatic graceful fallback in headless test environments.
- Audio focus arbitration cleanly ducks background music and pauses microphone listening while clips play.

---

## 6. Multi-Repo Synchronization Workflow

Engine updates are synchronized seamlessly from upstream `voice-rpg-engine` using the automated script:
```bash
# Pull latest engine improvements while preserving custom campaign scripts:
/path/to/voice-rpg-engine/scripts/sync_engine_to_games.sh /path/to/commander-america-rpg
```

---

## 7. Automated Unit Verification Suite

Commander America includes **232 automated unit tests** verifying intent parsing, satirical chant resonance, multi-track save stability, and combat progression:
```bash
# Run test suite:
./gradlew testDebugUnitTest

# Build APK:
./gradlew assembleDebug
```

---

## 8. Provenance, Political Satire & Non-Commercial Licensing

- **First Amendment Parody & Satire:** Commander America RPG is a work of political parody and satirical commentary protected under Fair Use. All character caricatures and dialogue are satirical exaggerations.
- **PolyForm Noncommercial 1.0.0:** Free for personal, educational, and hobbyist enjoyment. For commercial inquiries, reach out to **[todd.0042.git@gmail.com](mailto:todd.0042.git@gmail.com)**.

---

<p align="center">
  If you enjoy playing Commander America RPG, consider supporting open-source development:<br/><br/>
  <a href="https://ko-fi.com/todd0042" target="_blank">
    <img src="https://img.shields.io/badge/☕_Support_Commander_America_on_Ko--fi-F16061?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Support on Ko-fi">
  </a>
</p>

---

<p align="center">
  <a href="https://ko-fi.com/todd0042">
    <img src="https://raw.githubusercontent.com/Todd0042/creator-assets/main/banners/todd_creator_banner_v2.0.png" alt="Support Todd on Ko-fi" width="100%" />
  </a>
</p>
