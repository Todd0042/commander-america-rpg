# VoiceRPG Engine: One Engine, Every Game

This repository is the single source of the VoiceRPG engine. Every game runs the **identical** engine:
`echoes-of-logos`, `wallys-world`, `commander-america-rpg`, `superstore-saga`. The full rules are in
AGENTS.md §2 (tests) and §9 (sync protocol). In short:

1. **Engine changes are made here, never in a game repo.** In games the engine paths are read-only and
   guarded (pre-commit hook + CI check of `.engine-sync/MANIFEST.sha256`).
2. **Content-neutral.** No game's names, story, spells or assets in engine code. A game needs different
   behaviour → add a JSON field with a default that keeps every other game unchanged. Games are never
   forced to adopt a feature.
3. **Every game must pass.** Before committing: `./gradlew testDebugUnitTest` and
   `./scripts/test_content_packs.sh` (the engine's tests against each game's content). A failure on a
   game's content is either a content bug in that game (fix its JSON) or a test that assumes template
   data (make it a contract test, or a mechanics test with `TemplateFixture`).
4. **Distribute every change:** commit, then `./scripts/sync_engine_to_games.sh --verify`, then commit
   the sync in each game. `--check` must show every game in sync.
5. **Engine code found changed in a game:** review with `--diff <game>`; take only what belongs upstream
   with `--pull-from <game> <path>...`, make it content-neutral, then distribute it. Never bulk-import a
   game's files.
