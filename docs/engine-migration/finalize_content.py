#!/usr/bin/env python3
"""Engine convergence, step 5b: install the content extracted by ExtractContentTest into assets/game.

  ./gradlew testDebugUnitTest --tests '*ExtractContentTest*'   # writes app/build/extracted/game
  python3 docs/engine-migration/finalize_content.py            # installs it

Replaces the engine-template JSON that was still in assets/game with Commander America's content, writes
the manifest (two campaign tracks, each with its preset hero, picked on an arcade-style select screen)
and merges the vocabulary taken out of the engine into spells/thesaurus.json.
"""
import json
import os
import shutil
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
EXTRACTED = os.path.join(ROOT, "app/build/extracted/game")
GAME = os.path.join(ROOT, "app/src/main/assets/game")
MIGRATION = os.path.join(ROOT, "docs/engine-migration")

if not os.path.isdir(EXTRACTED):
    sys.exit(f"Run the extractor first: {EXTRACTED} is missing")


def dump(path, data):
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2, ensure_ascii=False)
        f.write("\n")


# 1. Content folders the extractor owns: replace them wholesale (they held the engine template).
for sub in ("story", "characters", "classes", "encounters"):
    shutil.rmtree(os.path.join(GAME, sub), ignore_errors=True)
    shutil.copytree(os.path.join(EXTRACTED, sub), os.path.join(GAME, sub))
shutil.copy(os.path.join(EXTRACTED, "spells/spells.json"), os.path.join(GAME, "spells/spells.json"))
for f in ("enemies.json", "enemy_spells.json"):
    shutil.copy(os.path.join(EXTRACTED, "enemies", f), os.path.join(GAME, "enemies", f))

# The hero keeps the sprite it had: the engine template hero's embedded sprite (from git history).
import subprocess
template_hero = json.loads(subprocess.run(
    ["git", "-C", ROOT, "show", "618f66c:app/src/main/assets/game/characters/hero.json"],
    check=True, capture_output=True, text=True).stdout)
hero_path = os.path.join(GAME, "characters/hero.json")
hero = json.load(open(hero_path, encoding="utf-8"))
hero["sprite"] = template_hero["sprite"]
hero.setdefault("spriteId", template_hero.get("spriteId", "hero"))
dump(hero_path, hero)

# Preset chants recalibrated for the shared engine (Commander's old AcousticProfile scored differently).
# calibration_fixes.json comes from a diagnostic run of the engine's ResonanceEngine on this content:
# null = drop the hand-written chant for that tier (the engine's generic chant lands correctly),
# text = the closest chant built from the spell's own name that lands in the tier.
fixes = json.load(open(os.path.join(MIGRATION, "calibration_fixes.json"), encoding="utf-8"))["fixes"]
spells_path = os.path.join(GAME, "spells/spells.json")
spells = json.load(open(spells_path, encoding="utf-8"))
for sp in spells["spells"]:
    for tier, text in fixes.get(sp["id"], {}).items():
        chants = sp.setdefault("presetChants", {})
        if text is None:
            chants.pop(tier, None)
        else:
            chants[tier] = text
    if not sp.get("presetChants"):
        sp.pop("presetChants", None)
dump(spells_path, spells)

# 2. Thesaurus: the template's lexicon plus Commander's words that used to be hard-coded in the engine.
thesaurus_path = os.path.join(GAME, "spells/thesaurus.json")
thesaurus = json.load(open(thesaurus_path, encoding="utf-8"))
moved = json.load(open(os.path.join(MIGRATION, "thesaurus_words.json"), encoding="utf-8"))
for school, words in moved.items():
    roots = thesaurus["schools"].setdefault(school, [])
    roots.extend(w for w in words if w not in roots)
# Calibration: the engine needs each spell name to hit exactly one root word of its school.
# Names with none get a distinctive word from the name; Commander-only words that gave a name two roots go.
ROOTS_ADD = {
    "ELECTROMANCY": ["polls", "option", "gibberish", "venn", "bowdoin", "passcode"],
    "SHADOW": ["cloth", "bill", "significance", "contraband"],
    "CRYOMANCY": ["scoop", "baskin", "sundae", "fudge"],
    "NATURE": ["coconut", "grocery", "vegan"],
}
ROOTS_REMOVE = {
    "HOLY": ["action", "sip"],
    "PHYSICAL": ["filibuster", "exam", "appalachian", "common"],
    "SHADOW": ["plug"],
    "ELECTROMANCY": ["dismantle"],
}
for school, words in ROOTS_ADD.items():
    roots = thesaurus["schools"].setdefault(school, [])
    roots.extend(w for w in words if w not in roots)
for school, words in ROOTS_REMOVE.items():
    thesaurus["schools"][school] = [w for w in thesaurus["schools"].get(school, []) if w not in words]
dump(thesaurus_path, thesaurus)

# 3. Manifest. Hero presets carry over Commander's former ArcadeCandidate cards.
manifest_path = os.path.join(GAME, "manifest.json")
manifest = json.load(open(manifest_path, encoding="utf-8"))
manifest.update({
    "_doc": "Commander America: two campaigns told from opposite perspectives. creationMode preset_select "
            "makes character creation a pick between the two tracks' heroes.",
    "gameTitle": "Commander America",
    "gameSubtitle": "Paint it Red • Bewilder with Blue",
    "worldName": "the campaign trail",
    "initialSceneId": "scene_act1_ch1_escalator",
    "initialNodeId": "act1_intro",
    "defaultEncounterId": "act1_ch1_press_battle",
    "startingAchievementId": "AWAKENED_THE_LOGOS",
    "titleBackgroundAsset": "story/golden_escalator.jpg",
    "defaultHeroName": "Commander America",
    "defaultHeroTitle": "Commander-in-Chief",
    "defaultHeroPortrait": "portraits/commander_america.jpg",
    "quickNames": ["Commander America", "Hill-Rod"],
    "characterTitles": ["Commander-in-Chief", "Standard-Bearer & Server Specialist"],
    "tutorialCompletionText": "Coalition assembled. Resonance mechanics understood. The campaign trail awaits.",
    "creationStatusSubtitle": "CHOOSE YOUR CANDIDATE",
    "creationMode": "preset_select",
    "titleMusic": "audio/music/bgm_title.ogg",
    "explorationMusic": "audio/music/bgm_rally.ogg",
    "combatMusic": "audio/music/bgm_combat.ogg",
    "campaignTracks": [
        {
            "id": "track_a",
            "displayName": "Paint it Red",
            "subtitle": "Commander America + Little Marco + Wizard Kash + Foghorn Kennedy + JD Pants + Musk-X",
            "startNodeId": "act1_intro",
            "startSceneId": "scene_act1_ch1_escalator",
            "rosterDescription": "Rubio • Vance • Patel • Kennedy",
            "badgeText": "1P",
            "voiceKeywords": ["trump", "commander", "america", "track a", "paint it red", "red"],
            "hero": {
                "name": "Commander America",
                "title": "Commander-in-Chief",
                "heroClassId": "commander",
                "portraitAsset": "portraits/commander_america.jpg",
                "auraColor": "SOLAR_GOLD",
                "alias": "THE GOLDEN SOVEREIGN",
                "slogan": "MAKE AMERICA GREAT AGAIN • WE NEVER STOP WINNING",
                "playstyle": "5-Hero Supergroup Coalition",
                "featuredMoves": ["Tremendous Strike", "Art of the Deal", "Presidential Veto", "Build The Wall"],
                "accentColor": "#FFD700",
                "secondaryColor": "#D32F2F",
                "soundBites": ["audio/quotes/trump/trump_greatness_birthright.ogg", "audio/quotes/trump/trump_easily_win.ogg"],
                "soundBiteLabel": "WINNING!"
            }
        },
        {
            "id": "track_b",
            "displayName": "Bewilder with Blue",
            "subtitle": "Hill-Rod ➔ Nancy Palooza ➔ Joe Naptime ➔ Kamala Coconut ➔ Zohran Cardamom & Mayor Swagger",
            "startNodeId": "act1_tb_ch1_intro",
            "startSceneId": "scene_act1_tb_ch1_server",
            "rosterDescription": "Hill-Rod ➔ Joe Naptime ➔ Kamala ➔ Barry",
            "badgeText": "2P",
            "voiceKeywords": ["hillary", "hill rod", "hillrod", "track b", "bewilder with blue", "blue", "relay"],
            "hero": {
                "name": "Hill-Rod",
                "title": "Standard-Bearer & Server Specialist",
                "heroClassId": "commander",
                "portraitAsset": "portraits/template/hill_rod_template.jpg",
                "auraColor": "SAPPHIRE_FROST",
                "alias": "CHAPPAQUA TECHNOMANCER",
                "slogan": "IT'S HER TURN • WIPE IT WITH A CLOTH",
                "playstyle": "4-Hero Standard-Bearer Relay",
                "featuredMoves": ["Cloth Wipe", "BleachBit Protocol", "SuperPAC Surge", "Reset Button"],
                "accentColor": "#00E5FF",
                "secondaryColor": "#7C4DFF",
                "soundBites": ["audio/quotes/hillary/hillary_congratulated_trump.ogg"],
                "soundBiteLabel": "EMAILS!"
            }
        }
    ]
})
dump(manifest_path, manifest)
print("Installed extracted content into", GAME)
