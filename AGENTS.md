# Commander America: Quest for the Golden Tower
## Agent Guidelines, Architectural Rules & Non-Subservience Policy

This repository contains the standalone satirical retro voice/tactile JRPG *Commander America: Quest for the Golden Tower*. When pair programming on this codebase, adhere strictly to the following architectural guidelines and core project rules.

---

## 1. Absolute Independence & Non-Subservience Policy
* **Engine vs. Game Content:** This game is built on the VoiceRPG engine, but is **NOT in any way subservient to the original game (*Echoes of the Logos*)**.
* **Zero Legacy Fantasy Relics:** Never import, reintroduce, or reference characters, locations, or lore from the original fantasy template:
  * NO "Aethel", "Sir Cedric", "Lyra", "Zephyr", "Malakor", "Vaelor", "Galahault", "Nocturne", or "Aethelgard".
  * NO "Echoes of the Aether", "Awaken in Aethelgard", or medieval fantasy relics.
* All narrative scripts, bosses, spells, voice assignments, and UI strings belong exclusively to the *Commander America* satirical universe.

---

## 2. Character Roster & Voice System
* **Active Cast:**
  * **Commander America** (Donald J. Trump) - The Golden Sovereign & Protagonist.
  * **Elon Musk** ("The Chief Doge & Starship Sovereign") - Tech ally, Cyber Command, Starlink, Cybertrucks.
  * **Chronicle Narrator** - Epic presidential chronicle narrator.
  * **Madam Speaker Pelosi** - The Stock-Trading Shadow Lich.
  * **Kamala** - The Unburdened Vice-Sovereign.
  * **Barack** - The Teleprompter Sorcerer.
  * **Final Boss Biden** - The Malarkey Sovereign.
  * **Chairman Rocket Man** - Chairman of the Crimson Spire.
  * **Media Pundit Spectre** - Fact-Checker Ghoul / Cable News Pundit.
  * **Marco Rubio** ("Little Marco" / "The Bottled Orator") & Primary Debate Rivals.
* Every character must have their own unique voice profile, TTS pitch, preview phrase, and voice command recognition in `AudioSetupScreen` and `CombatNarrator`.

---

## 3. Combat Mechanics & Story Synchronization
* **Combat Spells:** The protagonist uses Commander spells (`TREMENDOUS_STRIKE`, `ART_OF_THE_DEAL`, `OBAMA_DID_IT_FIRST`, `BUILD_THE_WALL`, `RALLY_THE_PATRIOTS`).
* **No Pre-Battle Dialogue Loops:** Winning an encounter must always transition directly to that Act's post-battle dialogue node (e.g., `act1_lobby_battle` -> `act1_post_battle`).
* Encounters must never loop back to the pre-battle challenge node.

---

## 4. Screenless & Audio-First Playability (Pocket Mode)
* The entire game must remain 100% playable eyes-free / hands-free:
  * Full TTS narration of all dialogue and battle actions.
  * Spoken choices aloud via `isReadChoicesEnabled`.
  * Hands-free speech recognition for all combat moves, dialogue choices, and system menus.

---

## 5. Verification & Deployment Protocol
* After any change, verify compilation with `./gradlew assembleDebug`.
* Install the resulting APK to connected ADB devices.
