# Project Independence & Non-Subservience Rule

## 1. Absolute Independence from Reference/Template Games
* This game is **Commander America: Quest for the Golden Tower** — a standalone, satirical retro 32-bit voice/tactile JRPG.
* It is **NOT in any way subservient to *Echoes of the Logos*, *Aethelgard*, or any fantasy template**.
* **Zero Legacy Relics:** Under no circumstances should any names, lore, characters, spells, portraits, dialog lines, or strings from the fantasy template appear in this project:
  * No "Aethel", "Sir Cedric", "Lyra", "Zephyr", "Malakor", "Vaelor", "Galahault", "Nocturne", or "Aethelgard".
  * No "In Echoes of the Aether...", "Awaken in Aethelgard", or templar/grove fantasy themes.
* All characters, party members, bosses, spells, voice assignments, story nodes, dialogue options, encounters, and UI assets must remain 100% bespoke to the Commander America satirical political universe.

## 2. Engine Functionality vs. Game Content
* The underlying engine exists solely to provide:
  1. Voice recognition and tactile input processing.
  2. ATB (Active Time Battle) turn-based combat mechanics.
  3. Branching dialogue trees with speech recognition and choice elimination.
  4. TTS (Text-to-Speech) multi-voice narration and pitch modulation.
  5. Atomic file save and load persistence.
* Game content (characters, storylines, enemies, spell books, visual palettes, sprite matrices, music, and sound effects) belongs entirely to *Commander America*.

## 3. Strict Verification & Deployment Protocol
* Run `./gradlew testDebugUnitTest` and ensure all 219+ unit tests pass cleanly.
* Compile and package with `./gradlew assembleDebug` and verify exit code 0.
* Deploy updated builds to connected ADB devices.
