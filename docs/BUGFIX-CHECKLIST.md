# VoiceRPG Engine — Bug Fix Checklist

Portable fixes made while building Commander America that apply to the original
VoiceRPG engine game (Echoes of the Logos). Fixes live in the shared engine package
`com.voicerpg.engine.*` (CombatViewModel, the pure `combat/` package, SaveManager,
StoryViewModel, StoryChoiceMatcher), so they port directly.

Legend: [ ] = not yet applied to the old game.

## A. Combat bugs

- [ ] **1. AOE spells only hit one target.** AOE spells must resolve against every
      target. Per-enemy numbers, and if the chosen target dies mid-application the
      attack auto-retargets the next living enemy.
- [ ] **2. False "all foes defeated" victory.** Victory must be derived from the
      authoritative living-enemy list at resolution time, never a stale/cached snapshot.
- [ ] **3. MP is never spent / no affordability gate.** Spells carry `mpCost`; casting
      checks affordability and spends MP. On insufficient MP, soft-fail with a hint to
      use a free recovery action — never silently proceed.
- [ ] **4. Mana-recovery spells leaking into auto-pick fallback.** Recovery spells are
      excluded from ALL auto-pick spell fallbacks and from turn steering, and sorted
      LAST in every kit — garbled utterances fall back to the first real spell, never
      the recovery/breath action.
- [ ] **5. Defend/Guard is a no-op.** Guard must apply real mitigation (-50% incoming),
      set an `isGuarding` flag that expires next turn, support taunt/target override,
      and must NOT speed the guard's ATB up.
- [ ] **6. Status spells (stun/poison/haste/slow) do nothing.** Implement a real status
      system: apply with tier-gated potency/duration, advance every turn (DoT ticks,
      stun skips turn), never block — resistance reduces but keeps a minimum 1-turn
      landing.
- [ ] **7. Drain spells heal but never damage the enemy.** A drain must deal damage to
      the target AND heal the caster from that damage, not be coded as a plain heal.
- [ ] **8. Flat deterministic damage.** Replace `basePower x multiplier` with a resolver:
      (base + resonance/level power) x affinity x variance (0.9–1.1) [x crit 1.5]
      minus target defense, floored at 1.
- [ ] **9. AOE damage numbers all stack at one coordinate.** Give each target its own
      floating-text slot so per-target numbers display separately.
- [ ] **10. No elemental weakness/resistance.** Add an affinity/effectiveness table.
      Nothing is ever immune/blocked; worst case is a hard floor (0.8x) applied to base
      damage only — the resonance/chant multiplier is never discounted.
- [ ] **11. Blanket boss phase multiplier never cleared.** Replace global multipliers
      (e.g., 3x on low HP) with scripted one-time boss phase moves unlocked at HP
      thresholds.
- [ ] **12. State read-modify-write races (lost updates).** All combat-state mutations
      must use atomic `MutableStateFlow.update {}` (or equivalent CAS) — especially ATB
      ticks, summon refresh, and target updates fired from delayed coroutines.
- [ ] **13. `roundNumber` never increments.** The round counter must actually advance
      (UI was stuck on "ROUND 1").
- [ ] **14. Three divergent duplicate spell tables.** Consolidate to one source-of-truth
      spell catalog loaded from data; delete the other tables (powers had drifted apart).
- [ ] **15. Enemy identity via brittle string match.** Replace `subtitle.contains(...)`
      / `id == "shaman"` sniffing with explicit enemy data (family, self-element, role,
      stats, defense, xp, moveset).
- [ ] **16. Enemy AI targets a random living hero.** Give enemies a move-selection brain:
      weighted moves with cooldowns and target rules (single / lowest-HP / marked /
      party-AOE / self), taunt override.
- [ ] **17. Every enemy has exactly one attack.** Enemies need a real moveset; bosses get
      phase scripts (new moves below HP thresholds) instead of a blanket multiplier.
- [ ] **18. No XP / leveling / stat growth (dead `level`/`xp` fields).** Wire up real
      progression: XP from defeated enemies, level-ups written into saved character
      stats, per-class stat growth; `level`/`xp` must actually feed combat.

## B. Engine / save & state fixes

- [ ] **19. Multi-slot save slot sanitization + atomic writes.** Slot indices must be
      clamped consistently in every path (save/load/delete/summary), including a
      dedicated story-mode slot; use atomic file writes to avoid corrupt saves.
- [ ] **20. Story choice/completion state not persisted.** Narrative flags, decisions,
      and completion markers must be written through on every choice and survive
      save -> reload, not just live in memory.
- [ ] **21. Choice matcher drops completed choices.** Completed choices were filtered
      out entirely, so re-speaking them silently did nothing. Keep matching them so the
      caller can give "already completed" feedback.
- [ ] **22. ATB tick / summon lost-update race.** Delayed-coroutine ATB tick + summon
      refresh path must not lose writes (same fix as #12, called out separately because
      it was a pre-existing bug).
- [ ] **23. Recovery action ordering.** Free recovery/breath actions belong to whoever's
      turn it is, are never spoken by other characters, and are sorted last in every kit.

## Not included (Commander America-specific, do not port)

- Character roster, kits, and Commander America spell names (TREMENDOUS_STRIKE, etc.)
- Story/dialogue/boss content and hub-dialogue choice graph
- Political/present-day content, assets, background image sets
- "Say attune" pocket-mode voice prompts (if custom to this build)