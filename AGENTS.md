<!-- voicerpg-engine-rule:start -->
# VoiceRPG Engine Rule (installed by voice-rpg-engine; do not edit in the game)

This game runs on the shared **VoiceRPG engine** (`../voice-rpg-engine`, next to this repo). The same
engine code runs in every VoiceRPG game: echoes-of-logos, wallys-world, commander-america-rpg,
superstore-saga.

## The engine is read-only in this repo
These paths are exact copies of the engine and are listed in `.engine-sync/OWNED_PATHS`:
- `app/src/main/java/com/voicerpg/engine/**` (all engine code, including `MainActivity.kt`)
- `app/src/main/assets/game/commands/**` and `app/src/main/assets/game/localization/**`
- `app/src/test/java/com/voicerpg/engine/**` (engine tests) and `app/src/test/fixtures/template-pack/**`

**Do not edit, add or delete files there.** A pre-commit hook and the game's CI
(`.engine-sync/guard.sh`) reject any change that doesn't match the synced engine version, and
editing `.engine-sync/` by hand is detected too. Do not use `--no-verify`.

## What belongs in this repo
- Game content: `app/src/main/assets/game/**` (except `commands/` and `localization/`), plus portraits,
  story art, environments and audio under `app/src/main/assets/`.
- App identity: `applicationId`, app name, icon and other resources in `app/build.gradle.kts` and `app/src/main/res/`.
- Game-specific tests: `app/src/test/java/com/voicerpg/game/**`.

## When the game needs something the engine can't do
1. Make the change in `../voice-rpg-engine`, **content-neutral**: no names, story, spells or assets
   from any game in engine code. Per-game behaviour is a JSON field with a default that leaves the
   other games unchanged.
2. In the engine: `./gradlew testDebugUnitTest`, then `./scripts/test_content_packs.sh` (runs the
   engine tests against every game's content), then commit.
3. `./scripts/sync_engine_to_games.sh --verify` sends it to every game. Commit the sync in each game.

An engine test failing on this game's content means either the content breaks an engine contract
(fix the content) or the test assumes template data (fix the test in the engine, e.g. with
`TemplateFixture`). Never change or delete an engine test inside a game.

## 16 KB page size (release blocker)
Every APK must pass `bash ../voice-rpg-engine/scripts/check_16kb.sh app/build/outputs/apk/debug/app-debug.apk`
(or `check-16kb` if installed). Dependencies live in this game's `app/build.gradle.kts`: when adding or
upgrading one, rebuild and re-check. A failing `.so` means that dependency needs a 16 KB-aligned version
(e.g. TensorFlow Lite -> LiteRT `com.google.ai.edge.litert:litert`).
<!-- voicerpg-engine-rule:end -->

# Commander America: Quest for the Golden Tower
## Agent Guidelines, Architectural Rules & Non-Subservience Policy

This repository contains the standalone satirical retro voice/tactile JRPG *Commander America: Quest for the Golden Tower* (Android / Kotlin / Jetpack Compose / Custom Voice RPG Engine). When pair programming on this codebase or scanning with AI agents, adhere strictly to the following architectural guidelines, roster specifications, and verification protocols.

---

## 1. Absolute Independence & Non-Subservience Policy
* **Engine vs. Game Content:** This game is built on the VoiceRPG engine, but is **NOT in any way subservient to the original game (*Echoes of the Logos*)**.
* **Zero Legacy Fantasy Relics:** Never import, reintroduce, or reference characters, locations, or lore from the original fantasy template:
  * NO "Aethel", "Sir Cedric", "Lyra", "Zephyr", "Malakor", "Vaelor", "Galahault", "Nocturne", or "Aethelgard".
  * NO "Echoes of the Aether", "Awaken in Aethelgard", or medieval fantasy relics.
* **Authentic Political Satire:** There are NO mystical ghouls, green slime monsters, or fantasy warlocks. The "Swamp" refers strictly to unelected bureaucrats, corporate lobbyists, focus-grouped dynasty candidates, super PAC consultants, and biased media pundits.

---

## 2. Character & Narrator Roster Directory

### A. Protagonist
* **`COMMANDER_AMERICA` (`hero`):** Commander-in-Chief of Freedom (inspired by Donald J. Trump).
  * Spells: *Tremendous Strike*, *Art of the Deal*, *Obama Did It First!*, *Presidential Veto*, *Executive Action*, *Build The Wall*, *Rally The Patriots*, *Tired of Winning!*

### B. The 4 Playable Companions (5-Hero Supergroup)
* **`MARCO` (`marco`):** Marco Rubio — Secretary of State / *Sanctions Sorcerer* (Recruited in Act II).
  * Spells: *Filibuster Strike*, *Maximum Sanctions*, *Water Sip Restore*, *Tariff Dividend*.
* **`VANCE` (`vance`):** JD Vance — The Vice President / *Rustbelt Vanguard* (Recruited in Act III).
  * Spells: *Hillbilly Debate Strike*, *Midwest Factory Slam*, *Heartland Fortitude*, *Common Sense Strike*.
* **`KASH` (`kash`):** Kash Patel — FBI Director / *Intel Inquisitor* (Recruited in Act IV).
  * Spells: *Declassify Document*, *The Government Gangster Binder*, *Security Clearance Revocation*, *Plug the Leaks*.
* **`KENNEDY` (`kennedy`):** Senator John Kennedy — *Committee Cross-Examiner* (Recruited in Act V).
  * Spells: *Plain English Cross-Examination*, *Call a Cop Strike*, *Metaphorical Remedy*, *Folksy One-Liner*.

### C. The Broadcast Narrator Trio (Rotating Newsroom & Arena Anchors)
* **`NARRATOR_KAYLEIGH` (`narrator_kayleigh`):** Anchor Kayleigh McEnany — Primetime Desk Anchor (Cool, sharp, facts-driven).
* **`NARRATOR_KAROLINE` (`narrator_karoline`):** Karoline Leavitt — Press Secretary of Truth (Fast, punchy, unyielding binder of receipts).
* **`NARRATOR_HULK` (`narrator_hulk`):** The Hulkster — 24-Inch Pythons / Ringside Hype ("OH YEAH, BROTHER!").

### D. Primary Antagonists & Bosses
* **Hill-Rod** (Hillary Clinton), **Joe Naptime** (Joe Biden), **Madame Vice** (Kamala Harris), **Barry Teleprompter** (Barack Obama), **Nancy Palooza** (Nancy Pelosi), **Low-Energy Jeb** (Jeb Bush), **Spicy Sean** (Sean Spicer), and **The 11-Day Mooch** (Anthony Scaramucci).

---

## 3. Combat Mechanics & Story Synchronization
* **ATB Turn-Based Battle System:** Active Time Battle with speed gauges, stamina/MP affordability gates, multi-target AOE with dynamic projectile animations, floating per-target damage text, and defensive mitigation.
* **No Pre-Battle Dialogue Loops:** Winning an encounter must always transition directly to that chapter's post-battle dialogue node (e.g., `act1_lobby_battle` -> `act1_post_battle`). Encounters must never loop back to the pre-battle challenge node.
* **Atomic State Mutation:** All combat state mutations use atomic `MutableStateFlow.update {}` to prevent race conditions during ATB ticks and delayed coroutine spell animations.

---

## 4. Screenless & Audio-First Playability (Pocket Mode)
* The entire game must remain 100% playable eyes-free / hands-free:
  * Full TTS narration of all dialogue and battle actions.
  * Spoken choices aloud via `isReadChoicesEnabled`.
  * Hands-free speech recognition for all combat moves, dialogue choices, and system menus.
  * Mastered real-audio quote hooks (e.g. Joe Naptime's gibberish quotes) with silent fallback safety if assets are not present.

---

## 5. Verification & Testing Protocols
* **Unit Tests:** Always run `./gradlew testDebugUnitTest` after any gameplay or engine modification. All 219+ unit tests must pass green.
* **Build Verification:** Verify compilation and packaging with `./gradlew assembleDebug`.
