# Story background variants: what's missing

Scan of `app/src/main/assets/story/` on branch `engine-convergence` (2026-11-23).

Each story background is a base image (`name.jpg`) plus two variants the engine picks by screen shape:

- `name_p.jpg`: portrait (phones held upright)
- `name_s.jpg`: square (unfolded foldables; engine support added in voice-rpg-engine `66b4424`)

Scene backgrounds are wired in `app/src/main/assets/game/story/scenes.json` (`backgroundAsset`, base name only).
The engine finds the `_p` / `_s` files by name, so adding a variant needs no JSON change.

## Summary

| | Count |
|---|---|
| Images in `assets/story/` | 96 |
| Base images (no `_p` / `_s` suffix) | 48 |
| Base images with both variants | 24 |
| Base images with neither variant | 24 (17 used by scenes, 7 unused) |
| Base images with only one variant | 0 |
| Scene backgrounds in use (`scenes.json`) | 37 |

## To generate: used by a scene (priority)

Each needs `<name>_p.jpg` and `<name>_s.jpg`:

- [ ] air_force_one
- [ ] airport_rally
- [ ] border_wall
- [ ] capitol_rotunda
- [ ] electric_bus
- [ ] gag_hearing
- [ ] golden_escalator
- [ ] hearing_room
- [ ] maralago_terrace
- [ ] national_mall
- [ ] oval_citadel
- [ ] primary_debate
- [ ] rnc_milwaukee
- [ ] scif_vault
- [ ] scranton_platform
- [ ] senate_hearing
- [ ] storage_vault

## Not used by any scene (decide: generate, wire in, or delete)

- [ ] autopen_factory
- [ ] ballot_arena
- [ ] courtroom_steps
- [ ] fisa_vault
- [ ] steel_mill
- [ ] summit_table
- [ ] trade_room

## Already complete (base + `_p` + `_s`)

access_bus, basement_bunker, baskin_robbins_portland, between_two_ferns, breakfast_club_studio,
bronx_subway_turnstile, butler_fairgrounds, chapati_food_truck, chappaqua_server_room, coconut_grove,
four_seasons_landscaping, fulton_county_booking, golden_inauguration, holt_donut_shop,
javits_glass_ceiling, mcdonalds_fry_station, rehoboth_bike_trail, sharpie_hurricane, spicer_bushes,
steel_ear_miracle, subzero_kitchen, trump_tower_taco_bowl, unburdened_throne, venn_fog
(`access_bus`, `between_two_ferns`, `steel_ear_miracle` and `venn_fog` aren't used by a scene either.)

## Re-run the scan

From the repo root:

```bash
python3 - <<'EOF'
import os, re, json
root = 'app/src/main/assets/story'
files = sorted(os.path.relpath(os.path.join(d, f), root) for d, _, fs in os.walk(root)
               for f in fs if f.lower().endswith(('.jpg', '.jpeg', '.png', '.webp')))
plain = [f for f in files if not re.search(r'_[ps]\.[^.]+$', f)]
used = {s['backgroundAsset'].removeprefix('story/')
        for s in json.load(open('app/src/main/assets/game/story/scenes.json'))['scenes']
        if s.get('backgroundAsset')}
for f in plain:
    stem = os.path.splitext(f)[0]
    miss = [v for v in ('_p', '_s') if not any(x.startswith(stem + v + '.') for x in files)]
    if miss:
        print(('* ' if f in used else '  ') + f, 'missing', ', '.join(miss))
EOF
```

`*` marks images used by a scene.

## Notes

- This work is on the `engine-convergence` branch, which isn't merged into `main` yet (device check
  pending; see voice-rpg-engine `docs/ENGINE_CONVERGENCE_PLAN.md`, step 6). Check out that branch on
  the desktop before adding files.
- The story is JSON on this branch: `StoryScript.kt` no longer holds the scenes, so background paths
  are edited in `scenes.json`, never in Kotlin.
- `.klein_staging/app/src/main/assets/story/chappaqua_server_room_p.jpg` exists only on the laptop
  (untracked); the committed `story/chappaqua_server_room_p.jpg` is the one in use.
