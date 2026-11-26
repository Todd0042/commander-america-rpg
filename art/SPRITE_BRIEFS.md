# Commander America RPG: battle sprite brief

Generated from `art/sprite_roster.json` by `voice-rpg-engine/scripts/sprite_roster.py`; edit the roster, not this file.
Every fighter, enemy and boss is mapped to a sprite key below. A sheet named after its key, dropped into
`app/src/main/assets/game/sprites/sheets/`, appears in battle as soon as the game runs the sprite-ready engine.

A political parody JRPG: public figures as a JRPG party, their famous gaffes and props as spells, and the bosses mostly earlier or rival versions of the party. Draw everyone as affectionate 16-bit caricatures that match the portraits in `app/src/main/assets/portraits/` (big recognisable hair, signature suits and props); the satire is in the props and poses, never in mocking anyone's body, ethnicity or religion. The regular enemies are mostly animated objects and media types (potted ferns, freezers, teleprompters, a riding mower) and should be as funny as their names.

**To draw: 54 sheets** (11 party fighters, 24 enemy families, 19 bosses). Everything else is a recolour of one of those.

## Style (every sprite)

- 16-bit SNES-era JRPG battle sprite, side view, facing RIGHT (the engine mirrors enemies to face the party).
- Limited palette (4-8 colours per sprite), 1 px dark outline, no anti-aliasing, transparent background.
- Cell 64x64 for party fighters and regular enemies (the body about 40-56 px tall); bosses 96x96 or 128x128 as listed.
- Soles on the same row in every frame (row 58 of a 64 cell, 90 of 96, 122 of 128); floating things put their lowest point or hover shadow there. The feet slice's bottom edge is the sole line.
- Nothing but the character in a frame: no labels, captions, frame names or text.
- Frame duration 190 ms unless a move needs faster frames; events in the tag user data, counted from 1 within the tag.
- Bright, saturated palette like the portraits (navy suits, red ties, gold, neon studio light); one oversized signature prop per character, readable at 64 px.
- Every party member is also fought as a boss or enemy in another storyline, so each party sheet carries the boss tags too (windup, charge_ultimate, ultimate, death).

## Animations (Aseprite tags)

- **Party fighters**: idle (4f loop), ready (4f loop: turn ready, weapon up), charge (4f loop: speaking a chant), cast (5f, release=4), attack (5f, impact=4), hurt (4f), ko (6f, ends lying down), victory (4f loop). A fighter who is also fought as a boss adds windup, charge_ultimate, ultimate (and death).
- **Regular enemies**: required idle (4f loop), attack (5f, impact=4), hurt (4f); recommended windup (4f loop), cast (5f, release=4), death (6f; else the engine shatters it).
- **Bosses**: required idle (4f loop), windup (4f loop), attack (5f, impact=4), cast (5f, release=4), hurt (4f), charge_ultimate (4f loop), ultimate (6-8f, release=5); recommended death (6-8f), taunt (4f), p2_idle, p2_attack... (second phase).

## Delivering a sprite

1. Save `<key>.png` and its Aseprite JSON export `<key>.json` in `app/src/main/assets/game/sprites/sheets/`, with the tags above and a `feet` slice.
   Previews go in `art/sprite-previews/`, never in the sheets folder.
2. Run `python3 ../voice-rpg-engine/scripts/sprite_lint.py . --fix`, then `../voice-rpg-engine/scripts/sprite_coverage.sh <game>`.
3. Compare it with everyone else in the game's gallery, **SPRITES** tab.
4. A recolour (listed under *Recolours*) needs no art; a sheet named after its own id replaces the recolour if you want it to differ.

## Party fighters (11, 64 px)

### `hero`: Commander America

> **Deliver:** `app/src/main/assets/game/sprites/sheets/hero.png` + `hero.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/commander_america.jpg`.

From portraits/commander_america.jpg: swept golden hair, navy suit, long red tie, flag pin, an orange tan; a golden escalator brawler. Signature prop: a black Sharpie and a can of Diet Coke.

*Moves*: charge: accordion hands, pumping; cast: points with both index fingers, gold sparks (release=4); attack: a two-handed 'You're fired' chop; ko: topples backward stiffly; victory: double thumbs-up and a fist pump.

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `commander_boss_act1`: Commander America (2016) (Vanguard, flesh): 2016 version: the same suit, a red campaign cap
- `commander_sharpie`: Commander America (Sharpie Map) (Vanguard, flesh): holding a weather map with a hand-drawn Sharpie bubble
- `commander_mugshot_boss`: Commander America (215-Lb Mugshot) (Vanguard, flesh): the mugshot glare: a scowl, a booking-photo flash behind (a separate sheet with the pose is worth it)
- `concepts_of_plan_boss`: Commander America ('Concepts of a Plan') (Vanguard, flesh): debate podium version, holding a blank sheet ('concepts of a plan')
- `commander_fry_apron`: Commander America (Fry-Cook Apron) (Vanguard, flesh): a fry-cook apron over the shirt and tie, a fry basket; or the neon-orange vest (costume change: own sheet recommended)
- `commander_finale_boss`: Commander America (Golden Podium) (Vanguard, flesh): the finale: behind a golden podium, gold trim on the suit (own 96 px sheet recommended)

### `hill_rod`: Hill-Rod

> **Deliver:** `app/src/main/assets/game/sprites/sheets/hill_rod.png` + `hill_rod.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/template/hill_rod_template.jpg`.

A pantsuit politician (portraits/template/hill_rod_template.jpg): blonde bob, a bright pantsuit, a smartphone in each hand, a basement server rack's blue glow; the Chappaqua server technomancer. Props: a cloth wipe and a bottle of hot sauce from her purse.

*Moves*: cast: wipes the air with a cloth and data dissolves (release=4); attack: hot-sauce splash; victory: a wave to the crowd.

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `hill_rod_boss`: Hill-Rod (Server Sovereign) (Occultist, flesh): Server Sovereign: a blue server-light aura

### `joe_naptime`: Joe Naptime

> **Deliver:** `app/src/main/assets/game/sprites/sheets/joe_naptime.png` + `joe_naptime.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/biden.jpg`.

From portraits/biden.jpg: white hair, aviator sunglasses, a navy suit, an ice-cream cone in hand; the Grand Ice-Cream Cryomancer.

*Moves*: cast: lifts the cone and fires a frosty scoop (release=4); attack: an aviators-on finger point; idle: a gentle doze-and-wake; ko: nods off standing.

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `joe_naptime_baskin_boss`: Joe Naptime (Baskin-Robbins Cryomancer) (Occultist, flesh): a double-scoop chocolate-chip waffle cone, a teleprompter behind
- `joe_naptime_ally`: Joe Naptime (2-Scoop Waffle Cone) (Vanguard, flesh): the same with a two-scoop waffle cone

### `kamala_coconut`: Kamala Coconut

> **Deliver:** `app/src/main/assets/game/sprites/sheets/kamala_coconut.png` + `kamala_coconut.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/kamala.jpg`.

From portraits/kamala.jpg: shoulder-length dark wavy hair, a pearl necklace, a bright suit, glowing overlapping Venn-diagram rings floating at her hands, a big laugh.

*Moves*: cast: throws Venn rings that overlap and pulse (release=4); attack: a yellow school-bus charge (a little bus sprite); victory: laughs, rings spinning.

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `kamala_venn_ally`: Kamala Coconut (3-Circle Venn) (Occultist, flesh): 3-Circle Venn: three rings orbiting
- `kamala`: Kamala the Unburdened (Occultist, flesh): Kamala the Unburdened (the Act 6 boss): the rings looping endlessly, a dreamy glow

### `kash`: Kash Subpoena

> **Deliver:** `app/src/main/assets/game/sprites/sheets/kash.png` + `kash.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/kash.jpg`.

From portraits/kash.jpg: short dark hair, trimmed beard, a dark suit, a red book titled like a children's tale held up like a spellbook (no readable text), a declassified folder.

*Moves*: cast: opens the book and pages fly out as bolts (release=4); attack: slams the folder; charge: reads aloud, glowing.

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `kash_wizard_boss`: Kash Subpoena (Wizard Kash) (Occultist, flesh): Wizard Kash: a purple wizard hat and a 4-page FISA scroll
- `kash_doge_boss`: Wizard Kash ($19.99 King Book) (Occultist, flesh): the $19.99 King Book held high, a price tag

### `kennedy`: Foghorn Kennedy

> **Deliver:** `app/src/main/assets/game/sprites/sheets/kennedy.png` + `kennedy.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/kennedy.jpg`.

From portraits/kennedy.jpg: silver hair, reading glasses on the nose, a tan seersucker suit, a yellow legal pad and a fountain pen; folksy country-fried cross-examiner.

*Moves*: cast: peers over the glasses and a folksy one-liner blasts out as a shockwave (release=4); attack: jabs with the pen; victory: hooks thumbs in his lapels.

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `foghorn_kennedy_boss`: Foghorn Kennedy (Oxford Drawl) (Occultist, flesh): Oxford drawl: an Oxford scarf
- `kennedy_doge_boss`: Foghorn Kennedy (Oxford Drawl) (Sniper, flesh): the same, at a hearing-room desk

### `marco`: Little Marco

> **Deliver:** `app/src/main/assets/game/sprites/sheets/marco.png` + `marco.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory

Portrait: `portraits/marco.jpg`.

From portraits/marco.jpg: neat dark hair, a navy suit and blue tie, an emergency water bottle; a slight, earnest Sanctions Sorcerer.

*Moves*: cast: takes a sip and sprays a sanctions scroll (release=4); attack: hurls the water bottle; charge: sips nervously.

### `mayor_swagger`: Mayor Swagger

> **Deliver:** `app/src/main/assets/game/sprites/sheets/mayor_swagger.png` + `mayor_swagger.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/mayor_swagger.jpg`.

From portraits/mayor_swagger.jpg: bald, a big grin, a royal-blue suit with a gold-striped tie, a gold VIP card and a nightlife-czar swagger.

*Moves*: cast: flicks the VIP card like a throwing star (release=4); attack: a shoulder-shimmy bump; victory: pops champagne (sparkling water).

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `mayor_swagger_boss`: Mayor Swagger (Istanbul Envoy) (Occultist, flesh): Istanbul Envoy: a Turkish Airlines amenity kit and eye mask on the forehead

### `nancy_palooza`: Nancy Palooza

> **Deliver:** `app/src/main/assets/game/sprites/sheets/nancy_palooza.png` + `nancy_palooza.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/pelosi.jpg`.

From portraits/pelosi.jpg: voluminous brown hair, a hot-pink suit, an orange pearl necklace, a gavel, and a pint of premium ice cream; Sub-Zero Call-Option Oracle with a stock chart glowing behind.

*Moves*: cast: the sideways seal clap, a ripple of light (release=4); attack: gavel strike; ultimate (boss): two Sub-Zero freezers open and blast frost.

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `pelosi`: Nancy Palooza (Sub-Zero Oracle) (Occultist, flesh): Sub-Zero Oracle: frost on the suit, a speech torn in half

### `vance`: JD Pants

> **Deliver:** `app/src/main/assets/game/sprites/sheets/vance.png` + `vance.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/vance.jpg`.

From portraits/vance.jpg: full beard, swept dark hair, a navy suit and tie, a bakery box of donuts ('whatever makes sense').

*Moves*: cast: hurls a sprinkle donut like a discus (release=4); attack: box-shove; idle: peers into the box.

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `jd_pants_boss`: JD Pants (Donut Interrogator) (Vanguard, flesh): Donut Interrogator: leaning over a counter
- `vance_doge_boss`: JD Pants ('Whatever Makes Sense') (Vanguard, flesh): 'Whatever Makes Sense': arms spread
- `vance_diet_dew`: JD Pants (Diet Mountain Dew) (Sniper, flesh): Diet Mountain Dew (a regular enemy): a green soda can in hand

### `zohran_cardamom`: Zohran Cardamom

> **Deliver:** `app/src/main/assets/game/sprites/sheets/zohran_cardamom.png` + `zohran_cardamom.json` · **party** · **64x64** cells · tags: idle, ready, charge, cast (release=4), attack (impact=4), hurt, ko, victory · also the boss tags

Portrait: `portraits/mamdani.jpg`.

From portraits/mamdani.jpg: tousled dark hair, short beard, a red hoodie under a denim jacket, a rolled chapati in hand, a transit map behind him; the fare-free chapati rap mayor.

*Moves*: cast: flips a sizzling chapati like a frisbee (release=4); attack: a mic-drop shockwave; victory: rap pose.

Also fought as a boss, so this sheet also needs windup, charge_ultimate, ultimate (and death). Boss versions (recolours of this sheet; draw a separate sheet named after the id only for a costume change):
- `zohran_cardamom_boss`: Zohran Cardamom ('Mr. Cardamom') (Occultist, flesh): 'Mr. Cardamom': a food-truck spatula, spices swirling

## Enemy families (24, 64 px)

### `practice_dummy`: Rally Cut-Out

> **Deliver:** `app/src/main/assets/game/sprites/sheets/practice_dummy.png` + `practice_dummy.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A life-size cardboard cut-out of a generic rally heckler on a wooden A-frame stand (no real person): a painted-on scowl, a megaphone, a blank protest sign. Used by the voice tutorial.

*Moves*: attack: wobbles forward on its stand; hurt: flaps backward; death: folds flat and topples.

Draws: Rally Cut-Out (Cardboard Heckler for Voice Drills, construct/physical)

### `newsroom`: Cable News Producer

> **Deliver:** `app/src/main/assets/game/sprites/sheets/newsroom.png` + `newsroom.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A frazzled cable news producer: headset, rolled-up sleeves, a clipboard and a stopwatch, a 'LIVE' tally light on the shoulder (no text).

*Moves*: attack: Aimed Shot, flings the clipboard; cast: the tally light flashes like a camera strobe.

Draws: Cable News Producer (Sniper, flesh)

Recolours:
- `hostile_fact_checker`: Hostile Fisheye Fact-Checker (Occultist, flesh): Hostile Fisheye Fact-Checker: big fisheye glasses, red stamp in hand

### `suit`: K-Street Lobbyist

> **Deliver:** `app/src/main/assets/game/sprites/sheets/suit.png` + `suit.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A slick lobbyist in a charcoal pinstripe suit, slicked hair, a briefcase overflowing with cash and talking points.

*Moves*: attack: swings the briefcase; cast: tosses a fan of cash like throwing cards.

Draws: K-Street Retainer Lobbyist (Vanguard, flesh)

Recolours:
- `pac_strategist`: Super PAC Media Strategist (Sniper, flesh): Super PAC strategist: rolled sleeves, focus-group charts
- `consultant_checker`: Consultant Fact-Checker (Occultist, flesh): Consultant fact-checker: grey cardigan, red pen
- `dealbook_boycott_exec`: DealBook Ad-Boycott Executive (Sniper, flesh): DealBook executive: a lanyard and a tablet
- `sec_420_auditor`: SEC '$420 Funding Secured' Auditor (Occultist, flesh): SEC auditor: a navy windbreaker

### `inspector`: Low-Flow Regulator

> **Deliver:** `app/src/main/assets/game/sprites/sheets/inspector.png` + `inspector.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A municipal inspector: short-sleeve dress shirt, pocket protector, clipboard, a toilet plunger holstered like a sword.

*Moves*: attack: plunger thrust; cast: writes a ticket that flies out.

Draws: Low-Flow 15-Flush Regulator (Vanguard, flesh)

Recolours:
- `rat_summit_inspector`: Brooklyn Townhouse Rat Inspector (Vanguard, flesh): Brooklyn rat inspector: a hard hat and a rat trap
- `mta_turnstile_drone`: Q70 Bus Fare Inspector (Sniper, flesh): bus fare inspector: transit uniform and cap
- `redaction_guard`: Redaction Marker Sentinel (Vanguard, flesh): Redaction sentinel: a navy windbreaker and a giant black marker

### `waiter`: Poupon Mustard Sommelier

> **Deliver:** `app/src/main/assets/game/sprites/sheets/waiter.png` + `waiter.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A tuxedoed waiter with a towel over the arm, presenting a jar of Dijon mustard on a silver tray like a fine wine.

*Moves*: cast: pops the jar and fires a mustard bolt (release=4); attack: tray swipe.

Draws: Poupon Mustard Sommelier (Sniper, flesh)

### `server_rack`: Chappaqua Basement Server Rack

> **Deliver:** `app/src/main/assets/game/sprites/sheets/server_rack.png` + `server_rack.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A basement server rack on stubby legs, blinking blue and green lights, cables like dreadlocks, a cloth wipe hanging from it.

*Moves*: attack: Iron Slam with a drive-bay arm; cast: blinks and sprays data bits.

Draws: Chappaqua Basement Server Rack (Vanguard, flesh)

### `fern`: Hostile Studio Fern

> **Deliver:** `app/src/main/assets/game/sprites/sheets/fern.png` + `fern.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A potted fern from a talk-show set that walks on its pot, fronds lashing like arms, a little scowl among the leaves.

*Moves*: attack: frond whip; cast: shakes and sheds a volley of leaves.

Draws: Hostile Studio Fern Left (Vanguard, flesh)

Recolours:
- `fern_intern`: Potted Fern Intern (Sniper, flesh): Potted Fern Intern: a tiny lanyard, a lighter green

### `billy_bus`: Billy Access-Bus Hot Mic

> **Deliver:** `app/src/main/assets/game/sprites/sheets/billy_bus.png` + `billy_bus.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

From portraits/billy_bus.jpg: spiky blond hair, leather jacket over a band tee, a VIP lanyard and a headset hot mic.

*Moves*: cast: taps the hot mic and a feedback wave shoots out (release=4); attack: a cheesy finger-gun.

Draws: Billy Access-Bus Hot Mic (Sniper, flesh)

### `casting_extra`: $50 Casting Extra

> **Deliver:** `app/src/main/assets/game/sprites/sheets/casting_extra.png` + `casting_extra.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A paid crowd extra in a campaign T-shirt and ball cap, waving a sign on a stick (blank sign, no text).

*Moves*: attack: bonks with the sign; cast: cheers, a wave of confetti.

Draws: $50 FEC Casting Extra (Vanguard, flesh)

Recolours:
- `swiss_cheese_purist`: South Philly Swiss-Cheese Heckler (Sniper, flesh): South Philly heckler: kelly-green team jacket, a cheesesteak

### `freezer_golem`: Sub-Zero Freezer Golem

> **Deliver:** `app/src/main/assets/game/sprites/sheets/freezer_golem.png` + `freezer_golem.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A stainless designer freezer come alive, doors as a mouth, frost breath, ice-cream pints for fists.

*Moves*: attack: slams a pint fist; cast: doors swing open in a frost blast.

Draws: Sub-Zero Jeni's Freezer Golem (Vanguard, flesh)

Recolours:
- `two_scoop_golem`: Jeni's Double-Scoop Freezer Golem (Vanguard, flesh): a double scoop of chocolate chip on its head

### `stylist`: eSalon Blowout Stylist

> **Deliver:** `app/src/main/assets/game/sprites/sheets/stylist.png` + `stylist.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A celebrity hairstylist with a huge blowout, a hair dryer drawn like a pistol and a round brush in the other hand.

*Moves*: attack and cast: fires a hot-air blast from the dryer (release=4).

Draws: eSalon Blowout Stylist (Sniper, flesh)

### `the_mooch`: The 11-Day Mooch

> **Deliver:** `app/src/main/assets/game/sprites/sheets/the_mooch.png` + `the_mooch.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

From portraits/mooch.jpg: slicked black hair, sunglasses on the head, a navy pinstripe suit, a little tornado spinning around his feet.

*Moves*: attack: spins into a whirlwind dash; cast: the tornado grows and flings papers.

Draws: The 11-Day Mooch Whirlwind (Sniper, flesh)

### `box_golem`: Merch Box Golem

> **Deliver:** `app/src/main/assets/game/sprites/sheets/box_golem.png` + `box_golem.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A golem of stacked cardboard merchandise boxes, packing tape for joints, a box-flap mouth, packing peanuts spilling out.

*Moves*: attack: box-stack slam; cast: fires packing peanuts.

Draws: Plot Against the King Merch Box (Vanguard, flesh)

Recolours:
- `bathroom_chandelier_golem`: Mar-a-Lago Chandelier Box Stack (Occultist, flesh): bankers boxes with a small gilded chandelier on top
- `sprinkle_stuff_box`: 'Some Sprinkle Stuff' Donut Box (Sniper, flesh): a pink bakery box with sprinkles
- `article_five_binder`: Constitutional Article V Pop-Quiz (Sniper, flesh): a giant three-ring binder version, pop-quiz pages flapping

### `riding_mower`: Four Seasons Riding Mower

> **Deliver:** `app/src/main/assets/game/sprites/sheets/riding_mower.png` + `riding_mower.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A green riding lawn mower with no rider, headlights for eyes, the cutter deck grinning, grass clippings spraying.

*Moves*: attack: charges forward; cast: sprays clippings.

Draws: Four Seasons Riding Mower (Sniper, flesh)

### `trek_bike`: Toe-Cage Trek Bicycle

> **Deliver:** `app/src/main/assets/game/sprites/sheets/trek_bike.png` + `trek_bike.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A stationary beach bicycle on a stand with metal pedal toe-cages snapping like jaws, the handlebars as horns.

*Moves*: attack: the pedals spin and snap; cast: rings its bell (a shockwave).

Draws: Stationary Toe-Cage Trek Bicycle (Sniper, flesh)

Recolours:
- `toe_cage_pedal`: Rehoboth Metal Toe-Cage Bike (Sniper, flesh): rustier, a single giant toe-cage pedal for a head

### `corvette`: 1967 Corvette Sentinel

> **Deliver:** `app/src/main/assets/game/sprites/sheets/corvette.png` + `corvette.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A classic 1967 Corvette Stingray in green with headlight eyes popping up, guarding a garage, exhaust puffing.

*Moves*: attack: revs and rams; cast: headlights flash.

Draws: Wilmington Corvette Garage Sentinel (Vanguard, flesh)

### `teleprompter`: Teleprompter

> **Deliver:** `app/src/main/assets/game/sprites/sheets/teleprompter.png` + `teleprompter.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A glass teleprompter on a stand, scrolling glowing lines (no readable text), a little face reflected in the glass.

*Moves*: cast: the scroll fires a beam of lines (release=4); attack: tips forward.

Draws: 'End of Quote, Repeat the Line' Teleprompter (Sniper, flesh)

Recolours:
- `teleprompter_glitch`: 32-Day Teleprompter Glitch (Sniper, flesh): glitching red, static lines
- `people_on_tv_set`: 'The People on Television' Monitor (Occultist, flesh): an old CRT television on legs instead of the glass (own sheet would sell it better)

### `merch_press`: 'Never Surrender' Mug Press

> **Deliver:** `app/src/main/assets/game/sprites/sheets/merch_press.png` + `merch_press.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A mug-printing press machine stamping out mugs with a glaring face printed on them (no readable text), mugs piling up.

*Moves*: attack: stamps down; cast: fires mugs.

Draws: $7.1M 'Never Surrender' Mug Press (Sniper, flesh)

### `garbage_truck`: Neon-Orange Garbage Truck

> **Deliver:** `app/src/main/assets/game/sprites/sheets/garbage_truck.png` + `garbage_truck.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A white garbage truck with a neon-orange safety vest stripe, the hopper as a mouth, rolling on chunky tyres.

*Moves*: attack: hopper chomp; cast: honks (shockwave).

Draws: Neon-Orange Mack Garbage Truck (Vanguard, flesh)

### `lie_flat_pod`: Turkish Airlines Lie-Flat Pod

> **Deliver:** `app/src/main/assets/game/sprites/sheets/lie_flat_pod.png` + `lie_flat_pod.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A first-class airline lie-flat seat pod come alive, the seat back flapping like a mouth, a tray table arm, a little amenity kit.

*Moves*: attack: reclines suddenly into you; cast: fires warm towels.

Draws: Turkish Airlines Lie-Flat Pod (Vanguard, flesh)

### `sink_golem`: Porcelain Basin

> **Deliver:** `app/src/main/assets/game/sprites/sheets/sink_golem.png` + `sink_golem.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A white porcelain bathroom sink on a pedestal, faucet as a nose, spraying water, the drain an eye.

*Moves*: attack: lunges; cast: sprays water.

Draws: 'Let That Sink In' Porcelain Basin (Vanguard, flesh)

### `valet_button`: Diet Coke Valet Turret

> **Deliver:** `app/src/main/assets/game/sprites/sheets/valet_button.png` + `valet_button.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A red wooden desk box with a single big button on top, a small turret barrel that pops out to fire soda cans.

*Moves*: cast: the button is pressed and a can fires (release=4); attack: the lid snaps.

Draws: Red Wooden Diet Coke Valet Turret (Sniper, flesh)

### `green_drone`: Federal Green Mandate Drone

> **Deliver:** `app/src/main/assets/game/sprites/sheets/green_drone.png` + `green_drone.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A quadcopter drone painted green with a leaf emblem and a dangling charging cable like a tail. Hovers; its shadow marks the floor.

*Moves*: attack: dives; cast: zaps with the charging cable.

Draws: Federal Green Mandate Drone (Sniper, flesh)

### `venn_loop`: Venn Loop

> **Deliver:** `app/src/main/assets/game/sprites/sheets/venn_loop.png` + `venn_loop.json` · **enemy** · **64x64** cells · tags: idle, attack (impact=4), hurt (+ windup, cast (release=4), death)

A living glowing ring (one circle of a Venn diagram) rolling and pulsing, overlapping its own afterimage.

*Moves*: attack: rolls through; cast: pulses outward.

Draws: Yellow Venn Loop (Sniper, flesh)

Recolours:
- `blue_venn`: Blue Venn Loop (Vanguard, flesh): blue

## Bosses (19 to draw, each its own sprite)

### `dynasty_candidate`: Low-Energy Dynasty Candidate (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/dynasty_candidate.png` + `dynasty_candidate.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

From portraits/jeb.jpg: glasses, a tan button-down, a bowl of guacamole held up; a gentle, deflated stance.

*Moves*: attack: a polite podium shove; cast: holds up the guacamole and asks the crowd to clap (a sad wave); ultimate: a campaign-fund money rain.

Element: flesh.

### `barry_tan_suit`: Barry Teleprompter (Tan Suit) (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/barry_tan_suit.png` + `barry_tan_suit.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

From portraits/obama.jpg: short grey hair, the famous tan summer suit, a microphone in one hand and a Dijon burger in the other.

*Moves*: attack: the mic drop (a shockwave); cast: teleprompter beams; ultimate: a giant mic falls from above.

Element: flesh.

### `zach_ferns_boss`: Zach Two-Ferns (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/zach_ferns_boss.png` + `zach_ferns_boss.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

From portraits/zach_ferns.jpg: shaggy hair and beard, a corduroy jacket, two potted ferns flanking him as if they were bodyguards, cue cards in hand.

*Moves*: attack: flicks a cue card; cast: the ferns lash; ultimate: an awkward silence that freezes the stage.

Element: flesh.

### `aerial_spin_anchor`: Cable News Aerial Spin Anchor (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/aerial_spin_anchor.png` + `aerial_spin_anchor.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

From portraits/media_anchor.jpg: a news anchor in glasses and a blue suit with a headset, a breaking-news chyron wrapped around him like a ribbon (no text), spinning on a camera crane.

*Moves*: attack: swoops on the crane; cast: fires 'UNVERIFIED' stamps (blank stamps); ultimate: a spin cycle of headlines.

Element: flesh.

### `noaa_meteorologist`: NOAA Cone-of-Uncertainty Chief (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/noaa_meteorologist.png` + `noaa_meteorologist.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

A meteorologist in a NOAA windbreaker before a hurricane map, a cone of uncertainty projected from a remote clicker.

*Moves*: attack: clicker zap; cast: summons a little storm; ultimate: the cone of uncertainty sweeps the arena.

Element: flesh.

### `spicy_sean_salsa`: Spicy Sean (Neon Salsa Shirt) (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/spicy_sean_salsa.png` + `spicy_sean_salsa.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

From portraits/spicy_sean.jpg: a press secretary bursting out of the hedges in a neon-green ruffled dance shirt, chewing gum, a podium on wheels.

*Moves*: attack: salsa spin kick; cast: shoots gum; ultimate: a full dance-floor spotlight number.

Element: flesh.

### `rudy_ghoul_boss`: Rudy Ghoul (Melting Hair Dye) (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/rudy_ghoul_boss.png` + `rudy_ghoul_boss.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

From portraits/rudy_ghoul.jpg: a ghoulish lawyer in a navy suit, hair dye dripping down both cheeks, a briefcase of 'evidence', a cold-open courtroom glare.

*Moves*: attack: briefcase slam; cast: shouts an objection that bursts out as a dark wave; ultimate: drips of dye swirl into a storm.

Element: flesh.

### `af1_staircase_gust`: Air Force One Triple-Stumble Staircase (128 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/af1_staircase_gust.png` + `af1_staircase_gust.json` · **boss** · **128x128** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

The rolling airstairs of a presidential jet come alive, steps as teeth, the handrails as arms, wind gusts swirling around it.

*Moves*: attack: the steps fold and snap; cast: a gust of wind; ultimate: a triple stumble shockwave.

Element: flesh.

### `chandelier_archivist`: Bathroom Chandelier Box Inspector (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/chandelier_archivist.png` + `chandelier_archivist.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

A subpoena archivist in a navy windbreaker with a magnifying glass, standing on stacked bankers boxes under a gilded chandelier.

*Moves*: attack: slams a box lid; cast: the magnifier focuses a beam; ultimate: the boxes topple in an avalanche.

Element: flesh.

### `corn_pop_boss`: Corn Pop (Rusty Rain-Barrel Dude) (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/corn_pop_boss.png` + `corn_pop_boss.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

A 1960s swimming-pool tough from the old anecdote: slicked pompadour, sleeveless tee, a rusty chain, a dented rain barrel beside him (no ethnicity cues: the gag is the barrel and the chain).

*Moves*: attack: swings the chain; cast: kicks the barrel and rusty water sprays; ultimate: barrel-roll charge.

Element: flesh.

### `archivist_boss`: Chief Bureau Archivist (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/archivist_boss.png` + `archivist_boss.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

From portraits/comey.jpg: a very tall man in a navy suit, a stack of memos under one arm and a shredder-ghost hovering behind him.

*Moves*: attack: memo slap; cast: shredded strips fly; ultimate: the archive phantoms rise.

Element: flesh.

### `donut_shop_case`: 'Whatever Makes Sense' Glazed Case (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/donut_shop_case.png` + `donut_shop_case.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

An unimpressed bakery display case on legs, rows of donuts as teeth, a sliding glass door mouth.

*Moves*: attack: the door slams; cast: fires glazed donuts; ultimate: the whole case empties in a donut storm.

Element: flesh.

### `word_salad_critic`: Uncontextualized Pundit (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/word_salad_critic.png` + `word_salad_critic.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

A pundit at a news desk with a bowl of literal word salad (lettuce with letter shapes, no readable words), tossing it angrily.

*Moves*: attack: flings salad; cast: the desk monitors flash; ultimate: a salad tornado.

Element: flesh.

### `shattered_cybertruck`: Shattered Armor-Glass Cybertruck (128 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/shattered_cybertruck.png` + `shattered_cybertruck.json` · **boss** · **128x128** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

An angular stainless-steel electric pickup come alive, its 'armor glass' windows spidered with cracks, headlight bar as a single glowing eye.

*Moves*: attack: rams; cast: the window glass shatters outward; ultimate: full-throttle charge.

Element: flesh.

### `omnibus_pork_golem`: 4,000-Page Omnibus Pork Golem (128 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/omnibus_pork_golem.png` + `omnibus_pork_golem.json` · **boss** · **128x128** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

A towering golem of stacked bill pages bound with red tape, pork chops and earmark tags sticking out, a gavel fist.

*Moves*: attack: gavel fist; cast: pages blast out; ultimate: the whole bill collapses onto the arena.

Element: flesh.

### `rent_board_lobbyist`: Luxury Condo Rent-Hike Lobbyist (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/rent_board_lobbyist.png` + `rent_board_lobbyist.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

A real-estate lobbyist in a camel overcoat with a tiny condo tower model under one arm and a rent-increase notice (blank) like a fan.

*Moves*: attack: swats with the notice; cast: the condo model grows and shoots up; ultimate: a skyline of condos rises.

Element: flesh.

### `passcode_cracker`: 6-Digit iPhone Passcode Cracker (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/passcode_cracker.png` + `passcode_cracker.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

A hooded forensic hacker with a laptop and a phone on a cable, glowing keypad digits floating around (no readable digits needed).

*Moves*: attack: cable whip; cast: a burst of digits; ultimate: the phone unlocks in a flash.

Element: flesh.

### `musk_x_starjump_boss`: Musk-X (Mid-Air 'X' Star-Jump) (96 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/musk_x_starjump_boss.png` + `musk_x_starjump_boss.json` · **boss** · **96x96** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

From portraits/elon.jpg: dark hair, black X T-shirt and jacket, mid-air in an X-shaped star jump, carrying a porcelain bathroom sink.

*Moves*: attack: hurls the sink; cast: star-jump shockwave; ultimate: launches like a rocket.

Element: flesh.

### `musk_x_finale_ally`: Musk-X (Dark Gothic Star-Jump) (96 px)

The finale version: an all-black gothic outfit and black cap; a darker recolour of the star-jump sheet.

Element: flesh. A recolour of `musk_x_starjump_boss` (no art needed unless it should differ).

### `battery_golem`: Stranded EV Battery Golem (128 px)

> **Deliver:** `app/src/main/assets/game/sprites/sheets/battery_golem.png` + `battery_golem.json` · **boss** · **128x128** cells · tags: idle, windup, attack (impact=4), cast (release=4), hurt, charge_ultimate, ultimate (release=5) (+ death)

A hulking golem of stacked EV battery packs, charging cables as dangling arms, a dim low-battery glow in its chest.

*Moves*: attack: cable whip; cast: siphons a stream of energy; ultimate: a power surge that drains the party.

Element: flesh.

## Notes for the game

- Boss versions of party members are recolours of the party sheet by default; draw a sheet named after the boss id where the costume changes (fry apron, golden podium, mugshot, wizard hat).
- Enemies have no family/selfElement in the data; the brief can't show elements for this game.
