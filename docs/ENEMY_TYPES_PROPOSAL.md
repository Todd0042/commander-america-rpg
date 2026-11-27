# Enemy Types Proposal: Commander America RPG

Status: **proposal for Todd's approval** (2026-11-27). Nothing in the game's JSON has changed. It covers
`docs/COMBAT_PROGRESSION_PLAN.md` (engine repo) decision 18 (type names, proposed by Claude and approved by Todd) and the
Commander item in section 4: "new enemy types for all 83 enemies (today all FLESH)".

**What this changes:** today 82 of 83 enemies are `FLESH` with no element, and every party spell is neutral against
every enemy (0 of 82 enemy slots in encounters has a reachable weakness). With the types below, 72 of 82 slots have a
weakness the party fighting them can reach (81 of 82 once Blue's party grows as planned), and no encounter leaves the party
without a neutral-or-better option.

**Assumed rules** (E6 isn't built yet; confirm when it is):
- Damage multiplier = family value from the matrix below; a school in the enemy's `weak` list becomes **2.0** and a school
  in its `resist` list becomes **0.8** (they override the family value; floor 0.8, ceiling 2.0, never immune).
- `element` keeps today's meaning (same-school hits capped at 0.8). This proposal uses it only where it says the same
  thing as `resist`.
- `armor`: none / light / heavy = flat reduction per hit (amounts are for the balance pass); piercing spells ignore it.
- Engine ids stay; only the display and spoken names change (in `combat/affinity_matrix.json`).

---

## 1. Display names

The party's "spells" are stunts, receipts and talking points, so the schools are **political tactics** and the families are
**the kinds of opposition** both tracks run into.

### Schools

| Engine id | Display name | Why |
|---|---|---|
| PHYSICAL | **Strongarm** | Tremendous Strike, binders across the room, ripping the speech, the stampede: force and intimidation. |
| HOLY | **Mandate** | Art of the Deal, Executive Action, sanctions, the "vindication" receipts and the heals: power that claims legitimacy. |
| SHADOW | **Scandal** | Obama Did It First, Declassify Now, the cloth wipe, contraband in a doll's head: dirt, leaks and cover-ups. |
| ELECTROMANCY | **Viral** | Pokemon Go to the polls, the gibberish clips, the Venn diagram, the debate on live TV: tech, clips and screens. |
| CRYOMANCY | **Gridlock** | Filibusters, vetoes, rent freezes, the 9 AM lid and the freezer full of ice cream: stalling and freezing. (Alternative if Todd wants Joe's kit literal: **Brain Freeze**.) |
| PYROMANCY | **Hot Takes** | Hot sauce in the purse, the sizzling chapati, scathing one-liners: heat for its own sake. |
| NATURE | **Grassroots** | The coconut tree, city-owned groceries, plain-English folk wisdom: "real people" politics. |

### Families

| Engine id | Display name | Who |
|---|---|---|
| FLESH | **Politicians** | Every candidate, officeholder and party lead fought as a boss (Hill-Rod, Nancy, Joe, Kamala, Commander, Kash, Kennedy, JD...). Weak to Scandal and Grassroots; shrugs off Mandate (they all claim one). |
| UNDEAD | **Has-Beens** | The low-energy dynasty, Rudy Ghoul, the Mooch, Corn Pop, napping Joe: careers that won't stay down. Old clips (Viral) finish them; stalling is their natural state. |
| CONSTRUCT | **Props** | Merch, teleprompters, server racks, the Cybertruck, the porcelain sink, the Diet Coke button: photo-op objects. Smash them (Strongarm); a sink has no shame (resists Scandal). |
| VERDANT | **The Swamp** | Ferns, K-Street, 4,000 pages of pork, the Venn loops: things that grow in DC. Burn it (Hot Takes) or drain it by order (Mandate); scandal is its habitat. |
| VOID | **Pundits** | Anchors, producers, fact-checkers, critics, hot mics, the "people on television": talking heads. Dead air (Gridlock) and real voters (Grassroots) beat them; they *are* the clip (resist Viral). |
| RADIANT | **Fat Cats** | The Dijon sommelier, ad-boycott executives, the rent-hike lobbyist, Musk-X, lie-flat luxury: money. Populism (Grassroots) and regulation (Mandate) hurt; money insulates them from a shove. |
| SILENCE | **Bureaucrats** | Archivists, redaction markers, inspectors, auditors, mandates, fare checkers: red tape. Only an order (Mandate) cuts it; immune to outrage clips and buried scandals. |

All seven families are used. Spoken checks: "weak to Scandal", "Pundits resist Viral", "Gridlock devastates it" all read
naturally. The `narrations` templates should be reworded to fit (for example `"{enemyName} reels - {school} lands!"`
instead of "devastates it").

---

## 2. Proposed affinity matrix

Rows are schools, columns families. **Bold** = 1.5 or more (weakness), _italic_ = 0.8 (resisted).

| School \ Family | Politicians (FLESH) | Has-Beens (UNDEAD) | Props (CONSTRUCT) | The Swamp (VERDANT) | Pundits (VOID) | Fat Cats (RADIANT) | Bureaucrats (SILENCE) |
|---|---|---|---|---|---|---|---|
| **Hot Takes** (PYROMANCY) | 1.2 | 1.0 | 1.2 | **2.0** | 1.0 | _0.8_ | 1.2 |
| **Gridlock** (CRYOMANCY) | 1.0 | _0.8_ | 1.2 | 1.2 | **1.5** | 1.0 | 1.0 |
| **Viral** (ELECTROMANCY) | 1.2 | **2.0** | 1.0 | 1.0 | _0.8_ | 1.0 | _0.8_ |
| **Grassroots** (NATURE) | **1.5** | 1.0 | 1.0 | _0.8_ | **1.5** | **2.0** | 1.0 |
| **Mandate** (HOLY) | _0.8_ | 1.0 | 1.0 | **1.5** | 1.0 | **1.5** | **2.0** |
| **Scandal** (SHADOW) | **1.5** | 1.2 | _0.8_ | _0.8_ | 1.2 | 1.0 | _0.8_ |
| **Strongarm** (PHYSICAL) | 1.0 | 1.2 | **1.5** | 1.0 | 1.2 | _0.8_ | 1.0 |

Every family has one or two clear weaknesses and one or two resistances; every school is the strong answer to at least one
family. No single school wins everything: Scandal is resisted by three families, Strongarm by Fat Cats, Viral by Pundits and Bureaucrats.

---

## 3. Every enemy

Bosses (`isBoss`) all get an explicit `weak` and `resist`. A real person fought more than once keeps the same weakness and
resistance every time (Kash, Kennedy, JD, Kamala, Joe), so what the player learns stays true. Commander America's resistance changes
over the story on purpose: in 2016 Scandal is his weakness, from the mugshot on he resists it.

| id | name | boss | family | element | weak | resist | armor | rationale |
|---|---|---|---|---|---|---|---|---|
| `pundit_producer` | Cable News Producer |  | Pundits | - | - | - | none | Books the segment; dead air (Gridlock) or real voters sink it. |
| `lobbyist` | K-Street Retainer Lobbyist |  | The Swamp | - | - | - | light | K-Street is the Swamp's root system; an order to drain it (Mandate) hurts. |
| `dynasty_candidate` | Low-Energy Dynasty Candidate | yes | Has-Beens | - | Strongarm | Gridlock | none | "Low energy": one Tremendous Strike folds him; stalling is his natural state (first boss, solo Commander). |
| `pac_strategist` | Super PAC Media Strategist |  | Pundits | - | - | - | none | Super PAC spin doctor: a talking head. |
| `consultant_checker` | Consultant Fact-Checker |  | Pundits | - | - | - | none | Fact-checker on a cable panel. |
| `barry_tan_suit` | Barry Teleprompter (Tan Suit) | yes | Politicians | - | Scandal | Viral | none | "Obama Did It First" is aimed squarely at him; the teleprompter-era pro owns the medium. |
| `dijon_waiter` | Poupon Mustard Sommelier |  | Fat Cats | - | - | - | none | Grey Poupon sommelier: elite luxury, money insulates him from a shove. |
| `hill_rod_boss` | Hill-Rod (Server Sovereign) | yes | Politicians | - | Scandal | Mandate | light | 33,000 emails: Scandal is her weak spot; survived an 11-hour hearing, so authority bounces off. |
| `bleachbit_server` | Chappaqua Basement Server Rack |  | Props | - | - | Scandal | heavy | A wiped server rack: nothing left to expose; break it by hand. |
| `fern_intern` | Potted Fern Intern |  | The Swamp | - | - | - | none | Literally a potted plant on staff: Swamp flora, burns on a hot take. |
| `zach_ferns_boss` | Zach Two-Ferns | yes | Pundits | - | Hot Takes | Viral | none | Deadpan host can't deadpan through hot sauce; he already roasted the Pokemon Go stunt on his show. |
| `potted_fern` | Hostile Studio Fern Left |  | The Swamp | - | - | - | none | Studio fern: Swamp flora. |
| `commander_boss_act1` | Commander America (2016) | yes | Politicians | - | Scandal | Viral | light | 2016: the hot-mic tape is still a weakness; he owns the viral cycle. |
| `billy_bus_minion` | Billy Access-Bus Hot Mic |  | Pundits | - | - | - | none | Hot-mic entertainment host. |
| `escalator_extra` | $50 FEC Casting Extra |  | Props | - | - | - | none | A paid $50 crowd extra: a stage prop. |
| `aerial_spin_anchor` | Cable News Aerial Spin Anchor | yes | Pundits | - | Gridlock | Scandal | none | Crowd-size spin: a vetoed segment (dead air) wrecks it; spin soaks up scandal. |
| `hostile_fact_checker` | Hostile Fisheye Fact-Checker |  | Pundits | - | - | - | none | Fisheye-lens fact-checker: pundit. |
| `pelosi` | Nancy Palooza (Sub-Zero Oracle) | yes | Politicians | - | Scandal | Strongarm | light | Sub-Zero Oracle: the stock trades are the dirt; she is the one who rips the speech, so a shove won't move her. |
| `fact_checker` | Sub-Zero Jeni's Freezer Golem |  | Props | Gridlock | Hot Takes | Gridlock | heavy | A Sub-Zero freezer golem: melts to heat, shrugs off cold. |
| `anonymous_source` | eSalon Blowout Stylist |  | Pundits | - | - | - | none | The salon stylist who leaked the video: a source, i.e. media. |
| `noaa_meteorologist` | NOAA Cone-of-Uncertainty Chief | yes | Bureaucrats | - | Mandate | Strongarm | none | Sharpie-map forecast: overridden by order; you can't punch a cone of uncertainty. |
| `plumbing_inspector` | Low-Flow 15-Flush Regulator |  | Bureaucrats | - | - | - | light | 15-flush low-flow regulator: red tape. |
| `spicy_sean_salsa` | Spicy Sean (Neon Salsa Shirt) | yes | Pundits | - | Mandate | Viral | none | Press secretary in the bushes: a condescending clap (Mandate) undoes him; podium TV is his turf. |
| `the_mooch_boss` | The 11-Day Mooch Whirlwind |  | Has-Beens | - | - | - | none | Eleven days and done: a has-been; old clips (Viral) finish him. |
| `kash_wizard_boss` | Kash Subpoena (Wizard Kash) | yes | Politicians | - | Mandate | Scandal | light | Conspiracy wizard folds to a paper receipt; declassifying dirt is his own trade. |
| `brave_book_golem` | Plot Against the King Merch Box |  | Props | - | - | - | light | Merch box of his book. |
| `rudy_ghoul_boss` | Rudy Ghoul (Melting Hair Dye) | yes | Has-Beens | - | Viral | Strongarm | none | The viral hair-dye press conference; already melting, so blows slide off. |
| `commander_sharpie` | Commander America (Sharpie Map) | yes | Politicians | - | Strongarm | Viral | light | Ripped like the State of the Union (Nancy's Strongarm); he owns the feed. |
| `landscaping_mower` | Four Seasons Riding Mower |  | Props | - | - | - | heavy | Four Seasons Total Landscaping riding mower. |
| `af1_staircase_gust` | Air Force One Triple-Stumble Staircase | yes | Props | - | Strongarm | Gridlock | heavy | The stairs are beaten by force; a gust never stalls. |
| `rehoboth_toe_cage_bike` | Stationary Toe-Cage Trek Bicycle |  | Props | - | - | - | light | The bike fall: a prop. |
| `chandelier_archivist` | Bathroom Chandelier Box Inspector | yes | Bureaucrats | - | Mandate | Scandal | none | Boxes by the bathroom chandelier: declassified "just by thinking it" (Mandate); scandal is filed away. |
| `corvette_garage_drone` | Wilmington Corvette Garage Sentinel |  | Props | - | - | - | heavy | The garage Corvette guarding the documents. |
| `joe_naptime_baskin_boss` | Joe Naptime (Baskin-Robbins Cryomancer) | yes | Has-Beens | Gridlock | Hot Takes | Gridlock | none | Baskin-Robbins cryomancer: a hot take melts the cone; the cold is his element (Red's party here has no Viral). |
| `repeat_line_prompter` | 'End of Quote, Repeat the Line' Teleprompter |  | Props | - | - | - | light | "End of quote, repeat the line": a teleprompter. |
| `two_scoop_golem` | Jeni's Double-Scoop Freezer Golem |  | Props | Gridlock | Hot Takes | - | light | Ice cream golem: melts. |
| `corn_pop_boss` | Corn Pop (Rusty Rain-Barrel Dude) | yes | Has-Beens | - | Viral | Gridlock | none | A 1960s pool-side legend only alive in a viral clip; Joe's ice cream won't touch him (teaches Joe to use Viral). |
| `toe_cage_pedal` | Rehoboth Metal Toe-Cage Bike |  | Props | - | - | - | light | Toe-cage bike: prop. |
| `foghorn_kennedy_boss` | Foghorn Kennedy (Oxford Drawl) | yes | Politicians | - | Gridlock | Grassroots | none | The Oxford drawl stalls in a freezer (Gridlock); folksy plain English is his own Grassroots. |
| `article_five_binder` | Constitutional Article V Pop-Quiz |  | Bureaucrats | - | - | - | light | Constitutional pop quiz: red tape. |
| `commander_mugshot_boss` | Commander America (215-Lb Mugshot) | yes | Politicians | - | Viral | Scandal | light | The mugshot went viral against him; indictments only raise his numbers. |
| `bathroom_chandelier_golem` | Mar-a-Lago Chandelier Box Stack |  | Bureaucrats | - | - | - | heavy | Stacked document boxes: bureaucracy made solid. |
| `never_surrender_merch` | $7.1M 'Never Surrender' Mug Press |  | Props | - | - | - | light | Mug press: merch. |
| `archivist_boss` | Chief Bureau Archivist | yes | Bureaucrats | - | Mandate | Viral | light | Chief archivist of the FISA files: an order cuts through the archive; surveillance-proof against clips. |
| `redaction_guard` | Redaction Marker Sentinel |  | Bureaucrats | - | - | - | light | Redaction marker: red tape. |
| `donut_shop_case` | 'Whatever Makes Sense' Glazed Case | yes | Props | - | Grassroots | Scandal | heavy | The awkward donut-shop visit: the real locals (Grassroots) break the glass case; a pastry case has no shame. |
| `swiss_cheese_purist` | South Philly Swiss-Cheese Heckler |  | Pundits | - | - | - | none | South Philly heckler: a loud opinion, i.e. a pundit. |
| `word_salad_critic` | Uncontextualized Pundit | yes | Pundits | - | Grassroots | Viral | none | Critic of context: the coconut-tree "context" answer flattens him; he lives on clips. |
| `teleprompter_glitch` | 32-Day Teleprompter Glitch |  | Props | - | - | - | light | A glitching teleprompter: prop. |
| `jd_pants_boss` | JD Pants (Donut Interrogator) | yes | Politicians | - | Viral | Strongarm | light | The viral donut-shop clip haunts him; an ex-Marine shrugs off blows. |
| `sprinkle_stuff_box` | 'Some Sprinkle Stuff' Donut Box |  | Props | - | - | - | light | Donut box: prop. |
| `concepts_of_plan_boss` | Commander America ('Concepts of a Plan') | yes | Politicians | - | Mandate | Scandal | light | "Concepts of a plan" crumble against an actual policy receipt; scandal-proof. |
| `people_on_tv_set` | 'The People on Television' Monitor |  | Pundits | - | - | - | light | "The people on television": literally pundits on a monitor. |
| `commander_fry_apron` | Commander America (Fry-Cook Apron) | yes | Politicians | - | Grassroots | Viral | light | The staged fry-cook shift: real workers (Grassroots) see through it; it was staged for cameras. |
| `garbage_truck_vest` | Neon-Orange Mack Garbage Truck |  | Props | - | - | - | heavy | Garbage truck photo-op: a heavy prop. |
| `vance_diet_dew` | JD Pants (Diet Mountain Dew) |  | Politicians | - | - | - | none | JD Pants with a Diet Dew: a politician minion. |
| `shattered_cybertruck` | Shattered Armor-Glass Cybertruck | yes | Props | - | Strongarm | Viral | heavy | The steel-ball window test: smash it; it is electric. |
| `sec_420_auditor` | SEC '$420 Funding Secured' Auditor |  | Bureaucrats | - | - | - | none | SEC auditor: bureaucrat. |
| `omnibus_pork_golem` | 4,000-Page Omnibus Pork Golem | yes | The Swamp | - | Hot Takes | Grassroots | light | 4,000 pages of pork: burns on a hot take; "it's for the folks back home" shrugs off Grassroots. |
| `dealbook_boycott_exec` | DealBook Ad-Boycott Executive |  | Fat Cats | - | - | - | none | Ad-boycott executive: a fat cat. |
| `mayor_swagger_boss` | Mayor Swagger (Istanbul Envoy) | yes | Politicians | - | Scandal | Grassroots | light | Turkish upgrades scandal; the street-smart mayor owns the block (Grassroots). |
| `turkish_lie_flat_seat` | Turkish Airlines Lie-Flat Pod |  | Fat Cats | - | - | - | light | Luxury lie-flat pod: the perk, a fat cat thing. |
| `zohran_cardamom_boss` | Zohran Cardamom ('Mr. Cardamom') | yes | Politicians | - | Scandal | Grassroots | none | Trust-fund exposés sting; the grassroots darling shrugs off Grassroots. |
| `kamala_venn_ally` | Kamala Coconut (3-Circle Venn) | yes | Politicians | - | Hot Takes | Viral | none | Hot takes cut through word salad; the Venn circles absorb clips. |
| `joe_naptime_ally` | Joe Naptime (2-Scoop Waffle Cone) | yes | Has-Beens | Gridlock | Hot Takes | Gridlock | none | Same Joe as Act III: a hot take melts the cone, cold is his element; Viral (JD's debate) also hits his family. |
| `rent_board_lobbyist` | Luxury Condo Rent-Hike Lobbyist | yes | Fat Cats | - | Gridlock | Scandal | light | Rent freeze is his nightmare; scandal is a business expense. |
| `mta_turnstile_drone` | Q70 Bus Fare Inspector |  | Bureaucrats | - | - | - | light | Fare inspector: bureaucrat. |
| `passcode_cracker` | 6-Digit iPhone Passcode Cracker | yes | Bureaucrats | - | Mandate | Viral | light | The subpoena (Mandate) cracks it; a phone shrugs off clips. |
| `rat_summit_inspector` | Brooklyn Townhouse Rat Inspector |  | Bureaucrats | - | - | - | none | Rat-summit inspector: bureaucrat. |
| `musk_x_starjump_boss` | Musk-X (Mid-Air 'X' Star-Jump) | yes | Fat Cats | - | Grassroots | Viral | light | Ratioed by real people; he owns the platform. |
| `porcelain_sink_golem` | 'Let That Sink In' Porcelain Basin |  | Props | - | - | - | heavy | "Let that sink in": a porcelain prop. |
| `vance_doge_boss` | JD Pants ('Whatever Makes Sense') | yes | Politicians | - | Viral | Strongarm | light | Same JD as Act IV: viral clip weakness, ex-Marine resistance. |
| `kash_doge_boss` | Wizard Kash ($19.99 King Book) | yes | Politicians | - | Mandate | Scandal | light | Same Kash: receipts beat him, dirt is his trade. |
| `kennedy_doge_boss` | Foghorn Kennedy (Oxford Drawl) | yes | Politicians | - | Gridlock | Grassroots | none | Same Kennedy: the drawl stalls; folksy is his own turf. |
| `commander_finale_boss` | Commander America (Golden Podium) | yes | Politicians | - | Grassroots | Scandal | heavy | Final form: only the voters (Grassroots) beat him; scandal no longer sticks. |
| `musk_x_finale_ally` | Musk-X (Dark Gothic Star-Jump) | yes | Fat Cats | - | Mandate | Viral | light | Dark-gothic Musk: the SEC order (Mandate); still owns the platform. |
| `diet_coke_mecha` | Red Wooden Diet Coke Valet Turret |  | Props | - | - | - | heavy | The Diet Coke button turret: prop. |
| `battery_golem` | Stranded EV Battery Golem | yes | Props | - | Gridlock | Viral | heavy | Stranded EV: batteries die in the cold; it is electric. |
| `mandate_drone` | Federal Green Mandate Drone |  | Bureaucrats | - | - | - | light | Federal green mandate: red tape, overridden by an order. |
| `kamala` | Kamala the Unburdened | yes | Politicians | - | Hot Takes | Viral | light | Same Kamala: hot takes cut the word salad; Venn circles absorb clips. |
| `yellow_venn` | Yellow Venn Loop |  | The Swamp | - | - | - | none | A word-salad loop: Swamp greens, burns on a hot take. |
| `blue_venn` | Blue Venn Loop |  | The Swamp | - | - | - | none | A word-salad loop: Swamp greens. |
| `practice_dummy` | Rally Cut-Out |  | Props | - | - | - | none | Cardboard cut-out for drills; element removed so every school reads neutral-or-better. |

---

## 4. Coverage check

Kits below use the **proposed** school changes from section 5. "Attack" = a spell with power that isn't a heal or guard.
Mana spells (Tired of Winning, Tariff Dividend, Plug the Leaks, One-Liner, Common Sense, Forgotten Passcode, Attune) go
away with `combatResource: "none"` and don't count.

### What each fighter is good against

| Fighter | Attack schools (proposed) | Strong (1.5+) against | Resisted (0.8) by |
|---|---|---|---|
| Red: Commander America | Gridlock, Mandate, Strongarm, Scandal | Gridlock: Pundits; Mandate: The Swamp, Fat Cats, Bureaucrats; Strongarm: Props; Scandal: Politicians | Gridlock: Has-Beens; Mandate: Politicians; Strongarm: Fat Cats; Scandal: Props, The Swamp, Bureaucrats |
| Red: Little Marco | Gridlock, Mandate | Gridlock: Pundits; Mandate: The Swamp, Fat Cats, Bureaucrats | Gridlock: Has-Beens; Mandate: Politicians |
| Red: Kash Subpoena | Strongarm, Scandal | Strongarm: Props; Scandal: Politicians | Strongarm: Fat Cats; Scandal: Props, The Swamp, Bureaucrats |
| Red: Foghorn Kennedy | Grassroots, Hot Takes | Grassroots: Politicians, Pundits, Fat Cats; Hot Takes: The Swamp | Grassroots: The Swamp; Hot Takes: Fat Cats |
| Red: JD Pants | Viral, Strongarm | Viral: Has-Beens; Strongarm: Props | Viral: Pundits, Bureaucrats; Strongarm: Fat Cats |
| Blue: Hill-Rod | Viral, Strongarm, Hot Takes, Scandal | Viral: Has-Beens; Strongarm: Props; Hot Takes: The Swamp; Scandal: Politicians | Viral: Pundits, Bureaucrats; Strongarm: Fat Cats; Hot Takes: Fat Cats; Scandal: Props, The Swamp, Bureaucrats |
| Blue: Nancy Palooza | Viral, Mandate, Strongarm, Scandal | Viral: Has-Beens; Mandate: The Swamp, Fat Cats, Bureaucrats; Strongarm: Props; Scandal: Politicians | Viral: Pundits, Bureaucrats; Mandate: Politicians; Strongarm: Fat Cats; Scandal: Props, The Swamp, Bureaucrats |
| Blue: Joe Naptime | Gridlock, Viral | Gridlock: Pundits; Viral: Has-Beens | Gridlock: Has-Beens; Viral: Pundits, Bureaucrats |
| Blue: Kamala Coconut | Gridlock, Viral, Mandate, Grassroots | Gridlock: Pundits; Viral: Has-Beens; Mandate: The Swamp, Fat Cats, Bureaucrats; Grassroots: Politicians, Pundits, Fat Cats | Gridlock: Has-Beens; Viral: Pundits, Bureaucrats; Mandate: Politicians; Grassroots: The Swamp |
| Blue: Zohran Cardamom | Gridlock, Viral, Mandate, Hot Takes | Gridlock: Pundits; Viral: Has-Beens; Mandate: The Swamp, Fat Cats, Bureaucrats; Hot Takes: The Swamp | Gridlock: Has-Beens; Viral: Pundits, Bureaucrats; Mandate: Politicians; Hot Takes: Fat Cats |
| Blue: Mayor Swagger | Grassroots, Scandal | Grassroots: Politicians, Pundits, Fat Cats; Scandal: Politicians | Grassroots: The Swamp; Scandal: Props, The Swamp, Bureaucrats |

Red has all seven schools across its five fighters (Commander 4, Marco 2, Kash 2, Kennedy 2, JD 2). Blue has all seven
too (Kamala and Zohran carry four each).

### Results

"Fails" = an enemy against which nobody in the party has a neutral-or-better school. "Members with no neutral-or-better
school" lists individual fighters who are stuck at 0.8 against an enemy while the party is still covered.

### Today (current schools, all FLESH, current matrix)
Fails (best party school < 1.0): 0
Members with no neutral-or-better school vs an enemy (party still covered): 0
No reachable weakness: (82 slots)
Enemy slots with a reachable weakness (>=1.5): 0 of 82
Member schools never strictly best in any fight they are in: hero:Mandate, hero:Scandal, hill_rod:Scandal, joe_naptime:Gridlock, kamala_coconut:Mandate, kamala_coconut:Scandal, kash:Scandal, kennedy:Scandal, marco:Scandal, mayor_swagger:Scandal, nancy_palooza:Mandate, nancy_palooza:Scandal, vance:Viral, vance:Strongarm, zohran_cardamom:Gridlock, zohran_cardamom:Mandate

### Proposed types + proposed school changes, encounter parties as listed today
Fails (best party school < 1.0): 0
Members with no neutral-or-better school vs an enemy (party still covered): 0
No reachable weakness: `billy_bus_minion` (act1_track_b_commander_finale), `toe_cage_pedal` (act3_track_b_corn_pop_battle), `article_five_binder` (act3_track_b_foghorn_battle), `bathroom_chandelier_golem` (act3_track_b_mugshot_finale), `never_surrender_merch` (act3_track_b_mugshot_finale), `teleprompter_glitch` (act4_track_b_venn_critics_battle), `sprinkle_stuff_box` (act4_track_b_vance_donut_battle), `garbage_truck_vest` (act4_track_b_mcdonalds_finale), `porcelain_sink_golem` (act5_track_b_musk_starjump_battle), `diet_coke_mecha` (act5_track_b_grand_finale)
Enemy slots with a reachable weakness (>=1.5): 72 of 82
Member schools never strictly best in any fight they are in: hill_rod:Viral, nancy_palooza:Scandal

### Proposed, with Blue's planned growing party (Hill-Rod; +Nancy; +Joe; +Kamala; +Zohran, Swagger)
Fails (best party school < 1.0): 0
Members with no neutral-or-better school vs an enemy (party still covered): 0
No reachable weakness: `billy_bus_minion` (act1_track_b_commander_finale)
Enemy slots with a reachable weakness (>=1.5): 81 of 82
Member schools never strictly best in any fight they are in: none

**Findings**
- **No encounter fails** today, with the proposal and parties as listed, or with Blue's planned growing party.
- The only enemy with no reachable weakness under the planned parties is `billy_bus_minion` (a Pundit in Hill-Rod's solo
  Act I finale; Hill-Rod has no Gridlock or Grassroots). Fine for a minion.
- With today's encounter lists (Blue leads fighting alone), 10 Blue minions have no reachable weakness, mostly Props
  and Bureaucrats faced by Joe or Kamala alone, or by Zohran and Swagger. Blue's growing party fixes all but one.
- **Schools that are never the best choice:** under today's parties, Hill-Rod's Viral (she fights no Has-Beens in Act I)
  and Nancy's Scandal (Strongarm or Mandate always ties or beats it in her three fights). Both are used once Blue's party
  grows. Today, with everything FLESH, Scandal and Mandate are never the best choice for anyone (FLESH gives 1.0 to both
  and 1.2 to Strongarm, Viral, Hot Takes and Grassroots).
- **Solo Commander (Act I, Act II ch. 1):** with Presidential Veto moved to Gridlock, he has four schools, and every boss he faces alone has
  a weakness he carries (Dynasty: Strongarm; Barry and Hill-Rod: Scandal; Spin Anchor: Gridlock).
- **Joe solo (Act III Blue):** his kit is three Gridlock attacks plus one Viral. Corn Pop and the Mugshot are weak to
  Viral and resist Gridlock or Scandal, so his fights teach "switch to the gibberish clip". This is deliberate; if Todd
  finds it too narrow, his first spell grant should be a non-Gridlock attack.

---

## 5. Party spells whose school looks wrong

| Spell | Who | Today | Proposed | Why |
|---|---|---|---|---|
| Filibuster Strike (`ca_marco_filibuster`) | Red: Little Marco | Strongarm (PHYSICAL) | **Gridlock** (CRYOMANCY) | A filibuster is the definition of gridlock (it stuns: OVERLOAD). |
| Maximum Sanctions (`ca_marco_sanction`) | Red: Little Marco | Scandal (SHADOW) | **Mandate** (HOLY) | Sanctions are state power, not scandal. |
| Presidential Veto (`ca_presidential_veto`) | Red: Commander America | Strongarm (PHYSICAL) | **Gridlock** (CRYOMANCY) | A veto blocks and stalls (it stuns); gives solo Commander a fourth school in Acts I-II. |
| Plain English Cross-Exam (`ca_kennedy_english`) | Red: Foghorn Kennedy | Strongarm (PHYSICAL) | **Grassroots** (NATURE) | Plain-English folk cross-exam is the folksy, grassroots voice. |
| Call an Officer (`ca_kennedy_officer`) | Red: Foghorn Kennedy | Scandal (SHADOW) | **Hot Takes** (PYROMANCY) | "Scathing Southern humor" exposing waste: a hot take, and Red's only Hot Takes. |
| Significance of the Passage of Time (4x) (`kamala_passage_of_time`) | Blue: Kamala Coconut | Scandal (SHADOW) | **Gridlock** (CRYOMANCY) | It freezes ATB gauges (CHILL): a stall, not a scandal. |

Left alone on purpose: Tremendous Strike, Binder of Truth, Appalachian Slam, SOTU Speech Rip and Cattle Futures
(Strongarm); Obama Did It First, Declassify Now, Cloth Wipe, Pass the Bill, Contraband Doll (Scandal); Pokemon Go,
Gibberish Audio, Venn Diagram, Yellow School Bus, Debate Dismantle, Deep-ITM Calls, Bowdoin Crit (Viral); all three
"vindication" receipts (Mandate); Vegan Sea Bass and Coconut Tree (Grassroots); Hot Sauce and Chapati (Hot Takes).
Heals keep their school (it doesn't affect damage).

**Piercing candidates** (ignore armor; useful against heavy Props): Declassify Now, Plain English Cross-Exam, Debate
Dismantle, Pokemon Go-To-The-Polls. One per track early, more as grants.

---

## 6. For Todd to decide

1. Names: **Gridlock** or **Brain Freeze** for CRYOMANCY; **Strongarm** for PHYSICAL (alternative **Bluster**, which
   also covers the speeches).
2. The weak = 2.0 / resist = 0.8 override rule (alternative: multiply by 1.5 / 0.67 on top of the family value).
3. Recurring characters keep one weakness and resistance per person (proposed), or change by act.
4. The six school moves in section 5 (Filibuster, Sanctions, Veto, Cross-Exam, Call an Officer, Passage of Time).
5. `practice_dummy`'s PHYSICAL element is dropped so the tutorial dummy doesn't make Strongarm look resisted.
