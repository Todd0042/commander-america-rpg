# Data taken out of the engine for Commander America

While making the VoiceRPG engine content-neutral (voice-rpg-engine `docs/ENGINE_CONVERGENCE_PLAN.md`,
step 2), these pieces of Commander America content were found hard-coded in engine code. They go into
this game's JSON during its content extraction (step 5b):

| File | Was in (engine) | Goes to |
|---|---|---|
| `preset_chants.json` | `SpellChantPresets.HANDCRAFTED_CHANTS` (24 `ca_*` spells) | `presetChants` on each spell in `spells/spells.json` |
| `preferred_voice_ids.json` | `CombatNarrator.DEFAULT_BUILTIN_PREFERRED_VOICE_IDS` | `preferredVoiceId` in `characters/<id>.json` |
| `thesaurus_words.json` | `SpellThesaurus` built-in roots (per school) | `spells/thesaurus.json` |

Also removed from the engine: the voice targets for Marco, Vance, Kash and Kennedy
(`TargetSelection`); give those characters `voiceAliases` in their character files.
