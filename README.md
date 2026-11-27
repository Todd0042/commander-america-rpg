<p align="center">
  <a href="https://ko-fi.com/todd0042">
    <img src="https://raw.githubusercontent.com/Todd0042/creator-assets/main/banners/todd_creator_banner_v1.0.png" alt="Support Todd on Ko-fi" width="100%" />
  </a>
</p>

---

# 🇺🇸 Commander America RPG: The 2024 Political Satire Voice JRPG

[![Version](https://img.shields.io/badge/Version-1.6.0-ff2a85.svg)](https://github.com/Todd0042/commander-america-rpg/releases)
[![License: PolyForm Noncommercial 1.0.0](https://img.shields.io/badge/License-PolyForm%20Noncommercial%201.0.0-00f0ff.svg)](LICENSE)
[![Platform: Android 8.0+](https://img.shields.io/badge/Platform-Android%208.0%2B%20(API%2026%2B)-3DDC84.svg?logo=android&logoColor=white)](https://developer.android.com)
[![16 KB Page Size](https://img.shields.io/badge/16%20KB-Compatible-success.svg)](https://developer.android.com/16kb-page-size)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-7F52FF.svg?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![UI: Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%202024.10.01-4285F4.svg?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Voice Control: Hands-Free](https://img.shields.io/badge/Controls-Hands--Free%20Voice-orange.svg)](app/src/main/java/com/voicerpg/engine/audio/SpeechManager.kt)
[![Speech: Android Offline STT](https://img.shields.io/badge/Speech-Android%20Offline%20STT-3DDC84.svg)](https://developer.android.com/reference/android/speech/SpeechRecognizer)
[![Audio: Public Domain Soundbites](https://img.shields.io/badge/Audio-Historical%20Soundbites-yellow.svg)](app/src/main/java/com/voicerpg/engine/audio/SoundbitePlayer.kt)
[![Audio: Dynamic Ducking](https://img.shields.io/badge/Audio-Dynamic%20Ducking-green.svg)](app/src/main/java/com/voicerpg/engine/audio/)
[![Unit Tests](https://img.shields.io/badge/Unit%20Tests-347%20Passing-39ff14.svg)](app/src/test/java/com/voicerpg/engine/)
[![Engine: VoiceRPG](https://img.shields.io/badge/Engine-VoiceRPG%20v2.0.0-teal.svg)](https://github.com/Todd0042/voice-rpg-engine)
[![Support on Ko-fi](https://img.shields.io/badge/Ko--fi-Support%20Creator-F16061?style=flat&logo=ko-fi&logoColor=white)](https://ko-fi.com/todd0042)

> **An audio-first, voice-commanded political satire JRPG for Android where hands-free incantations, tactical ATB combat, and real-life political history collide — featuring a 2-Track campaign (Track A: "Paint it Red" Coalition of Rebels vs. Track B: "Bewilder with Blue" Standard-Bearer Relay), Zero RPG Noise philosophy, multi-voice Android TTS character binding, real-time public domain soundbites, and 100% screenless Pocket Walk mode.**

```
       _________________________________________________________________________
      |  COMMUNITY NOTES INQUISITOR                 HP [████████████░░░]  78%   |
      |  "Context is required for this action!"     MP [███████████████] 100%   |
      |_________________________________________________________________________|
      |  COMMANDER AMERICA: "By the Art of the Deal, we impose tariffs now!"    |
      |  RESONANCE: MYTHIC (134%) >>> TARIFF STRIKE DEALS 2.25x BASE DAMAGE!     |
      |  ACTIONS: [TARIFF STRIKE] [BIG WALL] [COVFEFE BLAST] [ART OF THE DEAL]  |
      |_________________________________________________________________________|
```

---

## 📖 Table of Contents

* **Part 1 — Consumer Overview & Quick Start**
  * [🎙️ What Is Commander America RPG?](#-what-is-commander-america-rpg)
  * [🏛️ How a Campaign Rally Works in 3 Steps](#-how-a-campaign-rally-works-in-3-steps)
  * [🗳️ Dual Campaign Tracks: "Paint it Red" & "Bewilder with Blue"](#-dual-campaign-tracks-paint-it-red--bewilder-with-blue)
  * [🇺🇸 Who Fights, and When They Join](#-who-fights-and-when-they-join)
  * [🚶 100% Screenless Pocket Walk Mode](#-100-screenless-pocket-walk-mode)
  * [🚀 Quick Download & Installation (Android APK)](#-quick-download--installation-android-apk)
* **Part 2 — Consolidated Technical Documentation & Architecture Reference**
  * [1. Clean Architecture & Framework Integration (`com.voicerpg.engine.*`)](#1-clean-architecture--framework-integration)
  * [2. How Commander America Configures the Engine (`GameContent.kt`, `GameManifest`)](#2-how-commander-america-configures-the-engine)
  * [3. Speech Recognition & Continuous Audio Arbitration Loop (`SpeechManager.kt`)](#3-speech-recognition--continuous-audio-arbitration-loop)
  * [4. Resonance Engine & Acoustic-Poetic Scoring Math (`ResonanceEngine.kt`, `SpellThesaurus.kt`)](#4-resonance-engine--acoustic-poetic-scoring-math)
  * [5. Real-Time Audio DSP & Public Domain Soundbite Pipeline (`SoundbitePlayer.kt`)](#5-real-time-audio-dsp--public-domain-soundbite-pipeline)
  * [6. Procedural 8-Act Script & Non-Destructive Choice Elimination (`StoryScript.kt`)](#6-procedural-8-act-script--non-destructive-choice-elimination)
  * [7. Multi-Repo Synchronization Workflow (`sync_engine_to_games.sh`)](#7-multi-repo-synchronization-workflow)
  * [8. Automated Unit Verification Suite (`tests/`)](#8-automated-unit-verification-suite)
  * [9. Provenance, Political Satire & Non-Commercial Licensing](#9-provenance-political-satire--non-commercial-licensing)

---

# Part 1 — Consumer Overview & Quick Start

## 🎙️ What Is Commander America RPG?

**Commander America RPG** is a full-length, satirical comedy JRPG developed natively for Android. 

Players step into the shoes of **Commander America** and his ragtag coalition of political warriors as they battle their way through the bureaucratic labyrinth of modern Washington, corporate lobbyists, teleprompter wizards, and media fact-checkers.

Instead of navigating clunky touch interfaces, you issue commands with your **actual voice**:
- **🗣️ Speak Real Political Catchphrases**: Unleash iconic abilities like *"Art of the Deal"*, *"Big Beautiful Wall"*, *"Tariff Strike"*, or *"Covfefe Blast"* by chanting into your phone.
- **⚡ Resonance Acoustic Scoring**: Speak with rally-level inflection, confidence, and cadence to score **MYTHIC (100%–150%)** and **TRANSCENDENTAL ($\ge 155\%$)** resonance multipliers that supercharge your spells up to 3.0x base effectiveness.
- **👑 A Growing Party**: Red's Commander America is joined by **Little Marco**, **Wizard Kash**, **Foghorn Kennedy** and **JD Pants**; Blue's relay keeps every earlier lead on as **Nancy Palooza**, **Joe Naptime**, **Kamala Coconut**, **Zohran Cardamom** and **Mayor Swagger** join.
- **📜 Spells Learned From the Story**: everyone starts small (one attack, plus a heal for the main characters and healers) and learns the rest at story milestones; late joiners arrive already knowing what the story has passed.
- **🎧 Real Historical Audio**: Trigger legally sound, public domain soundbites from official Presidential and White House archives directly on the battle line.

---

## 🏛️ How a Campaign Rally Works in 3 Steps

1. **Speak to Command**: On your turn, call out a spell or party member name (*"Tremendous strike the lobbyist"*, *"Rally the patriots, heal Marco"*, *"Cloth wipe"*).
2. **Inflect with Conviction**: Speak with rhythmic, deliberate rally phrasing. The on-device Resonance Engine evaluates your decibel volume, pitch contour, and thesaurus keywords.
3. **Listen to the Narrative Firestorm**: The engine's multi-voice TTS and authentic historical soundbites react instantly to your choices and combat victories.

---

## 🗳️ Dual Campaign Tracks: "Paint it Red" & "Bewilder with Blue"

From the Title Screen, players pick a candidate, and with it a track. Both run through **five Acts (2015–2026)** and meet in a bipartisan curtain call:
- **Track A ("Paint it Red")**: **Commander America** from the golden escalator to the 2026 grand finale, building a five-hero coalition along the way.
- **Track B ("Bewilder with Blue")**: the **Standard-Bearer Relay**. Each Act hands the spotlight to a new lead, and the earlier leads stay in the party, so the team grows from one fighter to six.

---

## 🇺🇸 Who Fights, and When They Join

| Track | Fighter | Joins | Role |
|---|---|---|---|
| Red | **Commander America** | Act I | Main character: attacks + targetable heal (*Rally The Patriots*) |
| Red | **Little Marco** | Act II, after the inauguration fight | Striker and guard, no heal |
| Red | **Wizard Kash** | Act II, Chapter 4 (the SCIF) | Large targetable heal (*Security Clearance*) |
| Red | **Foghorn Kennedy** | Act III, Chapter 2 (Senate Judiciary) | Healer: party heal (*Common Sense Remedy*) and strong single heal (*Bless Your Heart*) |
| Red | **JD Pants** | Act IV, Chapter 1 (the donut shop) | Striker and guard, no heal |
| Blue | **Hill-Rod** | Act I | Main character: attacks + targetable heal (*11-Hour Marathon Shield*) |
| Blue | **Nancy Palooza** | Act II | Striker, no heal |
| Blue | **Joe Naptime** | Act III | Healer: party heal (*Baskin-Robbins Lick*) and strong single heal (*9:00 AM Lid & Sundae Nap*) |
| Blue | **Kamala Coconut** | Act IV | Striker and guard, no heal |
| Blue | **Zohran Cardamom** & **Mayor Swagger** | Act V | Strikers and guards, no heal |

Every fighter starts with **one single-target attack** (plus their role's heals) and **learns the rest of their kit at story milestones**: chapter victories, Act finales and the openings of their own chapters. A spoken line announces it (*"Little Marco learns Maximum Sanctions"*). Fighters who join later arrive already knowing the spells tied to milestones they missed, and still learn more after joining; on Blue, earlier leads keep learning after they hand over. Spells are only ever added, never swapped out.

---

## 🚶 100% Screenless Pocket Walk Mode

Put your phone in your pocket and play hands-free while walking or exercising! Pocket Mode engages an OLED battery-saving touch-guard, narrates all story beats via distinct Android TTS voice profiles, and lets you query status hands-free (*"Status"*, *"Enemies"*, *"Fellowship"*, *"Help"*).

---

## 🚀 Quick Download & Installation (Android APK)

1. Grab **`commander-america-v1.6.0-debug.apk`** from [Releases](https://github.com/Todd0042/commander-america-rpg/releases).
2. Install the APK on any Android 8.0+ device (API 26 or higher).
3. Tap **Allow** when prompted for Microphone access to enable voice incantations.

---

# Part 2 — Consolidated Technical Documentation & Architecture Reference

## 1. Clean Architecture & Framework Integration

Commander America RPG is built on the source-available **[VoiceRPG Engine](https://github.com/Todd0042/voice-rpg-engine)** framework, strictly separating reusable game engine infrastructure from political campaign assets:

```
+-----------------------------------------------------------------------+
|                    COMMANDER AMERICA RPG CLIENT                       |
|         (Jetpack Compose UI, Material 3, Soundbite Dispatcher)        |
+-----------------------------------------------------------------------+
                                   |
                                   v
+-----------------------------------------------------------------------+
|                         VOICERPG CORE ENGINE                          |
|  +--------------------+  +--------------------+  +-----------------+  |
|  |   SpeechManager    |  |  ResonanceEngine   |  |   CombatEngine  |  |
|  | (Audio Arbitration)|  | (Acoustic Scoring) |  |   (ATB Matrix)  |  |
|  +--------------------+  +--------------------+  +-----------------+  |
+-----------------------------------------------------------------------+
                                   |
                                   v
+-----------------------------------------------------------------------+
|                      POLITICAL CONTENT LAYER                          |
|  GameManifest | GameContent.kt | MovesetTable.kt | StoryScript.kt    |
+-----------------------------------------------------------------------+
```

Core framework guarantees:
- **Zero Cloud Latency & Total Privacy**: 100% on-device speech processing using `EXTRA_PREFER_OFFLINE` with hardware acceleration (`SpeechRecognizer.createOnDeviceSpeechRecognizer`) on Android 12+ (API 31+).
- **16 KB Page Alignment**: Fully compliant with Android 15's 16 KB page-size architecture requirements.

---

## 2. How Commander America Configures the Engine

Commander America configures the engine dynamically through [`GameContent.kt`](app/src/main/java/com/voicerpg/engine/content/GameContent.kt):
- **Campaign Manifest (`GameManifest`)**: Registers the dual campaign tracks (`track_a` "Paint it Red" and `track_b` "Bewilder with Blue") along with starting scene pointers (`scene_act1_intro`, `scene_act1_b_intro`).
- **Speaker TTS Voice Bindings (`preferredVoiceIds`)**: Maps each political caricature (Commander America, Elon, Tulsi, Vivek, Bobby, Barry, Kamala, Joe) to distinct Android TTS pitch, language, and voice model profiles for immersive, distinct vocal dialogue.
- **Battle Sprites (`game/sprites/sheets/`)**: **54** animated side-view Aseprite sheets (PNG + JSON): 11 party fighters, 24 enemy families and 19 bosses, at 64 px (bosses at 96 or 128 px), each with `feet`/`hit`/`cast`/`head` slices and `release=`/`impact=` events. Bosses that are recolours of a party fighter are listed in [`sprites/sprites.json`](app/src/main/assets/game/sprites/sprites.json). The roster behind them, with the artist's brief and a ✅ or ⚠️ REDO per sheet, is [`art/sprite_roster.json`](art/sprite_roster.json) / [`art/SPRITE_BRIEFS.md`](art/SPRITE_BRIEFS.md); `../voice-rpg-engine/scripts/sprite_coverage.sh commander-america-rpg` lists what the sprite contract still needs before a release build is allowed.
- **Battle Backdrops (`game/environments/environments.json`)**: five pixel-art backdrops (`assets/environments/{castle,cave,dungeon,forest,swamp}.png`, 1344x768), each with its painted floor line (`artHorizon`) so combatants stand on the floor; the selfie creator offers the same five as backgrounds.

---

## 3. Speech Recognition & Continuous Audio Arbitration Loop

The conversational loop is managed by [`SpeechManager.kt`](app/src/main/java/com/voicerpg/engine/audio/SpeechManager.kt) and [`IntentParser.kt`](app/src/main/java/com/voicerpg/engine/engine/IntentParser.kt).

### Audio Arbitration State Machine

```
  +--------------+   Narration Begins    +-------------------+
  |     IDLE     | --------------------> |   TTS_NARRATING   |
  +--------------+                       +-------------------+
         ^                                         |
         | Intent Dispatched                       | Synthesis Complete
         |                                         v
  +------------------+   Utterance Ready +-------------------+
  |  INTENT_DISPATCH | <---------------- |     MIC_ARMED     |
  +------------------+                   +-------------------+
         |                                         |
         v                                         | Voice Energy Detected
  +------------------+                             v
  |  RESONANCE_EVAL  | <----------------- +-------------------+
  +------------------+    RMS Threshold   |    RECOGNIZING    |
                                          +-------------------+
```

- **Audio Chime Suppression**: Notification audio streams are muted during microphone arming to prevent repetitive system tones.
- **Dynamic Entity Matching**: Utterances are mapped to party companions, satirical abilities, or dialogue choices with zero cloud roundtrips.

---

## 4. Resonance Engine & Acoustic-Poetic Scoring Math

Spoken spell incantations and rally shouts are evaluated by [`ResonanceEngine.kt`](app/src/main/java/com/voicerpg/engine/engine/ResonanceEngine.kt):

$$R = S_{\text{acoustic}} + S_{\text{cadence}} + S_{\text{poetic}} + S_{\text{novelty}}$$

Where:

1. **Acoustic Energy ($S_{\text{acoustic}}$)**:
   Measures raw vocal energy and dynamic pitch variation $\Delta f_0$:
   $$S_{\text{acoustic}} = \min\left(30, \max\left(0, \frac{\text{dB}_{\text{peak}} - 45}{40} \times 30\right)\right) + S_{\text{pitch}}$$

2. **Vocal Cadence ($S_{\text{cadence}}$)**:
   Measures rally orator pacing and syllabic rhythm:
   $$S_{\text{cadence}} = 25 \times \exp\left(-\frac{(T_{\text{utterance}} - T_{\text{optimal}})^2}{2\sigma^2}\right)$$

3. **Phonetic Thesaurus Matching ($S_{\text{poetic}}$)**:
   Abilities registered in [`MovesetTable.kt`](app/src/main/java/com/voicerpg/engine/combat/MovesetTable.kt) map to custom thesaurus roots in [`SpellThesaurus.kt`](app/src/main/java/com/voicerpg/engine/engine/SpellThesaurus.kt). Phrasing is evaluated via normalized Levenshtein distance:
   $$\text{Sim}(u, t) = 1 - \frac{\text{lev}(u, t)}{\max(|u|, |t|)}$$
   Keywords matching political slogans (*"deal"*, *"wall"*, *"tariff"*, *"covfefe"*, *"rocket"*, *"maha"*) award up to 35 points to $S_{\text{poetic}}$.

4. **Novelty Bonus ($S_{\text{novelty}}$)**:
   Rewards diverse strategic command variations (up to 10 points), preventing players from spamming a single voice command.

### Resonance Multiplier Tiers

| Tier Name | Score Range | Damage / Recovery Multiplier | Visual & Audio Flair |
| :--- | :--- | :--- | :--- |
| **Novice** | $0 \le R < 40$ | **1.00x** (Standard effect) | Subdued press room flash |
| **Apprentice** | $40 \le R < 70$ | **1.15x** Base Boost | Blue campaign banner pulse |
| **Adept** | $70 \le R < 100$ | **1.35x** High Efficiency | Red rally spark effect |
| **Master** | $100 \le R < 130$ | **1.85x** Critical Impact | Dual red-white-blue burst |
| **Mythic** | $130 \le R < 155$ | **2.25x** Electoral Landslide | Stadium roar & camera flashes |
| **Transcendental** | $R \ge 155$ | **3.00x** (Absolute Cap) | Golden fireworks & ticker-tape |

---

## 5. Real-Time Audio DSP & Public Domain Soundbite Pipeline

In addition to Android TTS, Commander America integrates an authentic real-audio soundbite player powered by 100% legal, public domain official U.S. government recordings (White House archives and official presidential addresses):

- **Commander America (Donald Trump)**:
  - `"Nothing will sway us... greatness is our birthright, and the future belongs to us."` (`trump_greatness_birthright.ogg`)
  - `"If you count the legal votes, I easily win."` (`trump_easily_win.ogg`)
- **Joe Naptime (Joe Biden)**:
  - `"Truinanashabbadepressure"` (`biden_truinanashabbadepressure.ogg`)
  - `"Badakathcare"` (`biden_badakathcare.ogg`)
- **Barry Teleprompter (Barack Obama)**:
  - `"Don't boo, vote!"` (`obama_dont_boo_vote.ogg`)
- **Spicy Sean (Sean Spicer)**:
  - `"This was the largest audience to ever witness an inauguration, period, both in person and around the globe."` (`spicer_period.ogg`)
- **Hill-Rod (Hillary Clinton)**:
  - `"Last night, I congratulated Donald Trump and offered to work with him on behalf of our country."` (`hillary_congratulated_trump.ogg`)
- **Madame Vice (Kamala Harris)**:
  - `"When people can ride a bus because it has a lift..."` (`kamala_ride_a_bus.ogg`)
- **White House Press Secretaries (Jen Psaki & Kayleigh McEnany)**:
  - `"The preference is of course legislative action because it's more permanent, it's more lasting."` (`psaki_legislative_action.ogg`)
  - `"Our healthcare providers, as President Trump has repeatedly acknowledged, are our heroes..."` (`kayleigh_heroes.ogg`)

### Audio DSP Pipeline

- Audio clips are normalized to **$-16\text{ LUFS}$** (44.1 kHz Vorbis `.ogg`) with dynamic speech ducking:
  $$V_{\text{bgm}}(t) = V_{\text{base}} \cdot \Big(1 - \alpha \cdot \mathbb{I}_{\text{soundbite}}(t)\Big)$$
  Where $\alpha \approx 0.70$ ducks background rally music by $-10.5\text{ dB}$.
- Audio focus arbitration cleanly pauses microphone listening while historical soundbites play.

---

## 6. Procedural 8-Act Script & Non-Destructive Choice Elimination

The story script in [`StoryScript.kt`](app/src/main/java/com/voicerpg/engine/engine/StoryScript.kt) utilizes the engine's choice elimination mechanics:
- When looping back to dialogue hub nodes, previously selected objectives display a `✓ DONE` badge and are rejected with helpful voice advice (*"That objective has already been completed..."*).
- State is preserved atomically in `SaveManager` without altering the core story tree structure.

---

## 7. Multi-Repo Synchronization Workflow

Engine updates are synchronized seamlessly from upstream `voice-rpg-engine` using the automated script:

```bash
# Pull latest engine improvements while preserving custom campaign scripts:
cd ../voice-rpg-engine && ./scripts/sync_engine_to_games.sh commander-america-rpg

# Audit sync status:
cd ../voice-rpg-engine && ./scripts/sync_engine_to_games.sh --check
```

---

## 8. Automated Unit Verification Suite

Commander America runs **347 automated unit tests** (the engine's suite against this game's content) verifying intent parsing, satirical chant resonance, multi-track save stability, and combat progression:

```bash
# Run unit test suite:
./gradlew testDebugUnitTest

# Build debug APK:
./gradlew assembleDebug
```

Test coverage includes:
- Intent parsing accuracy across 500+ political speech variations.
- Audio soundbite asset integrity and duration validation.
- Multi-track branching state preservation across save/load cycles.

---

## 9. Provenance, Political Satire & Non-Commercial Licensing

- **First Amendment Parody & Satire**: Commander America RPG is a work of political parody and satirical commentary protected under Fair Use. All character caricatures and dialogue are satirical exaggerations.
- **AI-Assisted Battle Art**: the battle sprite sheets and backdrops are AI-assisted (generated with Antigravity's art pipeline from the game's portraits and sprite briefs, then checked with the engine's sprite tools).
- **PolyForm Noncommercial 1.0.0**: Free for personal, educational, and hobbyist enjoyment. For commercial inquiries, reach out to **[Todd.0042.git@gmail.com](mailto:Todd.0042.git@gmail.com)**.
- **Media License**: the images, artwork and audio made for this project (paths listed in [LICENSE-MEDIA](LICENSE-MEDIA)) are © Todd0042 under **[CC BY-NC 4.0](LICENSE-MEDIA)**: free to share and adapt with credit for non-commercial purposes; commercial use requires permission.

---

<p align="center">
  If you enjoy playing Commander America RPG, consider supporting its development:<br/><br/>
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
