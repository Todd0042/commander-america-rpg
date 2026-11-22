# Engine Convergence Plan: One Engine, Four Games

**Goal:** every game runs the *identical* `com.voicerpg.engine` code. All differences between games
live in JSON and assets (plus opt-in capability flags with neutral defaults). When this is done,
`sync_engine_to_games.sh --check` is clean for all four games, and it **stays** clean because drift
is blocked mechanically, not by asking agents nicely.

This comes before the combat feel overhaul (`COMBAT_FEEL_PLAN.md`). Plan only; nothing is implemented yet.

---

## 1. Audit findings (2026-11-22)

| Game | Finding | What convergence means for it |
|---|---|---|
| **superstore-saga** | Identical to the engine (every shared package and `MainActivity.kt`). | Nothing. It's the control case for every step. |
| **echoes-of-logos** | `main` is the old pre-engine app (`com.voicerpg.android`). Branch `feature/engine-migration` (79 commits ahead) has engine code **identical** to the engine's and the whole game in JSON: 356 nodes, 25 scenes, 32 encounters, 26 spells, 79 enemies. `main` has only 1 commit not on that branch (a README update). | Merge the branch into `main`. |
| **wallys-world** | **Only behind, never forked.** Each of its 8 differing engine files matches an older engine commit exactly (2026-09-25 to 2026-11-08). No Wally's-only engine code. | Normal sync, then fix any content it relies on that newer engine code handles differently. Those fixes go in the engine. |
| **commander-america-rpg** | **A real fork of an older engine generation.** 19 shared files match no engine version, plus 2 extra files. Its story and combat content are Kotlin, not JSON: `StoryScript.kt` (3,967 lines), `StoryEncounters.kt` (2,011), `ClassSpellLibrary.kt`, speaker/class/candidate enums, hard-coded music tracks and movesets, and boss scripts inside `CombatViewModel`. Its `assets/game/**` JSON is still the engine template. `MainActivity.kt` differs by 136 lines. It also carries Echoes leftovers (Aethel/Cedric/Lyra/Zephyr spell sets, Malakor). | Two jobs: (1) take its **generic** features into the engine; (2) **extract its content into JSON**, then take the engine as-is. |

### 1.1 Real features in Commander that the engine lacks (take them upstream)
1. **Recorded audio clips** (`SfxManager.playAssetAudio`): character quote clips played on combat
   and story events. Becomes a generic, data-driven `soundBites` feature (§3, step 5a).
2. **Preset character select** (`ArcadeCandidate` in `CharacterCreationScreen`): pick from preset
   characters with portraits, chosen by campaign track. Becomes a generic creation mode in JSON.
3. **Crash-safe saves** (`SaveManager` using `androidx.core.util.AtomicFile`): a better write path
   than the engine's temp-file-and-rename. Take it as-is.
4. **Randomized voice profiles** (`AcousticProfile.rollOrganicProfile`): varied, realistic simulated
   voice data for button taps and the simulator. Take it as an option.
5. **`StoryViewModel`** (+920/−514 lines) and **`AssetViewerScreen`** (scans unreferenced assets):
   audit them function by function. Take generic behaviour (choice locking, auto-play choice
   heuristics, asset scanning) where the engine lacks an equivalent. Everything else is content.

### 1.2 Game content baked into the engine (the reason games "needed" their own copies)

| Where | Leak | Replacement |
|---|---|---|
| `engine/IntentParser.kt` ~L140–227 | Hard-coded targets: "cedric/paladin/templar" → `CEDRIC`, "orc/warrior" → `ORC`, "archer/skeleton" → `ARCHER`, "shaman/caster/mage" → `SHAMAN` (Echoes) | Resolve targets from `voiceAliases` on characters and enemies in JSON. Remove the named values from `TargetSelection`. Add those aliases to Echoes' JSON. |
| `engine/SpellChantPresets.kt` `HANDCRAFTED_CHANTS` | Echoes chants (fireball/archer, lay_on_hands/Cedric, …) | Optional `presetChants` per tier on each spell in `spells.json`. Generic chants are the fallback. |
| `audio/CombatNarrator.kt` L72 | Voice map `"cedric" to "en-gb-x-gbd-local"` | The existing `preferredVoiceId` in `characters/*.json` |
| `MainActivity.kt` L627 | `"Echoes of the Logos"` | `manifest.json` `title` |
| `ui/combat/RetroBattleScreen.kt` `BattleConclusionOverlay` | Wally's World / retail defeat flow ("PRESS ON (STILL ON THE CLOCK)", "Clocked out", the retail defeat line) | Manifest fields `victoryLine`, `defeatLine`, `victoryContinueLabel`, `defeatContinueLabel`, `defeatTitle`, and `defeatContinuesStory` (bool, default false). Wally's sets them. |
| `ui/story/StoryAssetLoader.kt` ~L75–78 | Path guesses (`contains("hero")`, `contains("applicant")`) | Decide from the asset's role in JSON (a portrait referenced by characters or classes), not from its name. |
| `viewmodel/TutorialBattleViewModel.kt` L230 | "The Logos echoes…" | Tutorial text in `manifest.json` or localization strings |
| `ui/creation/AvatarSynthesizer.kt` | "Logos Sky Blue", "(Cedric)" labels | Neutral labels in localization strings |
| Display text "Logos" (banner, fanfare) | Echoes branding shown in every game | `manifest.json` `resonanceBannerTitle` (default "RESONANCE"). Internal names can stay. |

Also check `localization/BuiltInDictionaries.kt`, `model/CombatModels.kt` and `model/MetricModels.kt`,
which matched the same scan. Some matches are just words ("caster"), so review them one by one.

### 1.3 Tooling gaps
- `--check` reports a game with **no** engine package as "in sync". That's how Echoes `main` looks fine.
- `MainActivity.kt` isn't in the sync list, so it drifts freely (Commander: 136 lines).
- Nothing stops an agent from editing engine files inside a game repo, and nothing records which
  engine version a game is on.
- The engine tests only run against the engine's template content, never against the four real
  content packs, so "compatible with every game" is never actually checked.

---

## 2. Target end state
1. The `com.voicerpg.engine.*` packages are byte-identical in all four games.
2. Each game's own code is just a thin `MainActivity` shell (app id, theme, icon) that calls an engine
   entry point, plus JSON and assets.
3. Each game has an `ENGINE_VERSION` file (engine commit hash). Its CI fails if any engine file
   differs from that commit.
4. Engine CI runs the full content-agnostic test suite against **all four content packs**.
5. Every per-game behaviour is a JSON field or opt-in flag with a neutral default.

---

## 3. Steps

Each step ends with engine tests green, a sync to the converged games, their tests green, and a
device check of the affected game(s).

### Step 1: Guardrails first (stops new drift while the rest happens)
- `sync_engine_to_games.sh`:
  - `--check` reports **NOT MIGRATED** for a game with no engine package, and **EXTRA FILES**
    for engine-package files the engine doesn't have (Commander's `StoryEncounters.kt`, `ClassSpellLibrary.kt`).
  - Push writes `ENGINE_VERSION` (engine `git rev-parse HEAD`) into each game.
  - Add `superstore-saga` to the protocol file (`.agy/rules/00_engine_sync_protocol.md`) and to AGENTS.md §9.
- **Game CI guard** (a step in each game's `android_ci.yml`): fetch the engine at `ENGINE_VERSION` and
  `diff -r` the engine packages. Any difference fails the build, with a message saying to make the
  change upstream. A local **pre-commit hook** in each game (installed by the sync script) blocks
  commits that touch `app/src/main/java/com/voicerpg/engine/**`, unless the commit is a sync commit.
- **Agent rules** in every game repo (`AGENTS.md`, `GEMINI.md`, `.agy/rules/`): the engine package is
  read-only here, with the exact upstream workflow. The CI guard makes this enforceable; the rule
  just explains it.
- **Content-pack test matrix** in engine CI: a job that checks out each game's
  `app/src/main/assets/game/**` (and the assets the JSON references) into the engine's test resources and runs
  `testDebugUnitTest` once per pack. Commander joins this matrix in Step 5, once its content is JSON.
- **MainActivity**: move its logic into the engine (e.g. `com.voicerpg.engine.app.VoiceRpgApp`
  composable plus `EngineActivity` base class). Each game's `MainActivity` becomes about 10 lines
  (extends `EngineActivity`), keeping per-game app identity (AGENTS.md §8).

### Step 2: Remove the content leaks from the engine (§1.2)
- Add the replacement JSON fields with defaults, switch the engine to read them, and delete the
  hard-coded content.
- In the same change, add the moved data to the games that used it:
  - Echoes (on `feature/engine-migration`): `voiceAliases` for Cedric/orc/archer/shaman,
    `presetChants`, `preferredVoiceId` for Cedric, `title`.
  - Wally's: the defeat-flow manifest fields.
  - Superstore: whatever the asset-loader change needs (the "applicant" art).
- New engine test: **no content strings in engine code**. A scan of `app/src/main/java` against a
  denylist built from all four packs' character, enemy and spell names. Fails CI if one appears.
- Verify: Superstore and Echoes (branch) tests and a device check. Voice targeting by name still works
  in Echoes, now through aliases.

### Step 3: Wally's World catch-up
- `sync_engine_to_games.sh --dry-run wallys-world` and review it. Then sync with `--verify`.
- The arena changes it never received (SNES multi-layer, floor grounding, new environment canvas)
  will look different. Check every Wally's environment id renders, and that sprite positions and the
  dark overlay look right. If Wally's needs its 25% darkening, add `arenaDim` to
  `environments.json` (default 0) and set it for Wally's.
- Fix every content incompatibility **in the engine**, with a default that keeps the other games unchanged.
- Add Wally's pack to the engine CI matrix.

### Step 4: Echoes of Logos onto `main`
- Device playthrough check of the `feature/engine-migration` build (title, a story chapter, a
  battle, a save/load).
- Merge the branch into `main`, then bring the one README commit across.
- Delete the old `com.voicerpg.android` code (it leaves with the merge if the branch already
  removed it; otherwise remove it).
- Its `applicationId` and save files: check that an existing install upgrades cleanly (see §5).
- Add Echoes' pack to the engine CI matrix (it's already identical, so this should just be green).

### Step 5: Commander America
**5a. Take its generic features into the engine first** (§1.1), each with neutral defaults:
- `soundBites`: on characters and enemies, `combat.soundBites: { "onCast": [], "onHit": [],
  "onDefeat": [], "onVictory": [] }`, plus `audioClip` on story nodes. The engine picks one at
  random and ducks music and TTS while it plays (`playAssetAudio`). Pocket mode: clips play
  between narration lines.
- Preset character select: `creation.mode = "preset_select"` in the manifest, with
  `creation.presets[]` (character id, portrait, label, `campaignTrack`). Commander's two candidates
  become data.
- `AtomicFile` saves (with a test for an interrupted write).
- `rollOrganicProfile` behind an option for simulated chants.
- `StoryViewModel` and `AssetViewerScreen`: take the generic parts found in the function-by-function
  audit. Each one gets an engine test.

**5b. Extract its content into JSON** (tool-assisted, not translated by hand):
- A one-off **extractor** in Commander's JVM test source set. It loads the Kotlin content objects
  (`StoryScript`, `StoryEncounters`, `ClassSpellLibrary`, the enums, movesets, music tracks) and
  writes `story/nodes.json`, `story/scenes.json`, `characters/*.json`, `classes/*.json`,
  `spells/spells.json`, `enemies/*.json`, `encounters/encounters.json` in the engine's schema.
- Boss scripts in `CombatViewModel` (`triggerPhase3DeathOfVoice`, `recruitZephyrMidBattle`,
  `triggerMalakorCordsOfSeverance`, the name-matched special cases) map onto `bossPhases` /
  `midBattleJoin`. Anything the schema can't express becomes a small **generic** phase action in the
  engine (done in 5a, with a test), never a special case.
  - Note: "Death of Voice" applies a blanket 3× that was never cleared. Commander's own combat spec
    lists it as bug #8 and it breaks invariant I-1, so it's rebuilt as a proper phase, not copied.
- Drop the Echoes leftovers rather than extracting them.
- **Parity test**: before deleting the Kotlin, a test compares old and new: every node id, text,
  speaker, choice, link and flag; every encounter roster and boss phase; every spell's numbers. It
  must match 100%, except for deliberate changes listed in the test.

**5c. Switch over:**
- Sync the engine over Commander's engine package (`--verify`), delete `StoryEncounters.kt`,
  `ClassSpellLibrary.kt` and the Kotlin content, and switch to the thin `MainActivity`.
- Port Commander's tests that assert Kotlin content into JSON-based tests (or the engine's
  content-agnostic suite).
- Device playthrough: candidate select on both tracks, each act's boss fights, sound bites, save/load.
- Add Commander's pack to the engine CI matrix.

### Step 6: Lock it in
- `--check` is clean for all four games. Engine CI passes against all four packs. Each game's CI
  guard is green at its `ENGINE_VERSION`.
- Tag the engine (e.g. `engine-v3.0.0`). From here, every change follows the protocol:
  engine first, then sync to all four, and CI blocks anything else.
- Then start `COMBAT_FEEL_PLAN.md`.

---

## 4. Order and size

| Step | Size | Depends on |
|---|---|---|
| 1 Guardrails | Medium | none |
| 2 Content leaks out | Medium | 1 |
| 3 Wally's catch-up | Small–Medium | 2 |
| 4 Echoes merge | Small | 2 |
| 5a Commander features upstream | Medium | 2 |
| 5b Commander extraction | Large (about 6,000 lines of Kotlin content; the extractor and parity test make it mechanical) | 5a |
| 5c Commander switch-over | Medium | 5b |
| 6 Lock-in | Small | all |

Steps 3, 4 and 5a can run in parallel after step 2.

## 5. Open questions
1. **Existing saves**: Commander's save format changes (its `SaveModels` uses its own `HeroClass` enum)
   and Echoes moves from the old app to the engine. Do installed copies (yours and any testers') need
   their saves migrated, or is a fresh start acceptable?
2. **Wally's look**: once it's on the new arena, keep its darker overlay (as `arenaDim`) or take the
   engine's look as-is?
3. **Commander's "Death of Voice"**: rebuild it as a proper boss phase within I-1 (recommended), or
   keep the old 3× behaviour as an explicit, documented exception?
