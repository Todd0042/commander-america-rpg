#!/usr/bin/env python3
"""
generate_gemini_backgrounds.py

Generates high-quality 16-bit / 32-bit retro JRPG background artworks using Google Gemini's Imagen 3 API.
Allows generating backgrounds ONE BY ONE (or interactively via menu), saving directly into:
  app/src/main/assets/story/<filename>
at the exact game target resolution (1376x768).

Requirements:
  pip install requests pillow

Usage:
  # Set your Gemini API key in the environment or pass --api-key
  export GEMINI_API_KEY="your-gemini-api-key-here"

  # 1. Interactive Menu (pick which background to generate one by one)
  python3 scripts/generate_gemini_backgrounds.py

  # 2. List all available backgrounds
  python3 scripts/generate_gemini_backgrounds.py --list

  # 3. Generate a specific background by filename
  python3 scripts/generate_gemini_backgrounds.py --scene primary_debate.jpg
  python3 scripts/generate_gemini_backgrounds.py --scene steel_mill.jpg
  python3 scripts/generate_gemini_backgrounds.py --scene golden_inauguration.jpg

  # 4. Preview the prompt for a scene without calling the API
  python3 scripts/generate_gemini_backgrounds.py --dry-run --scene butler_fairgrounds.jpg
"""

import argparse
import base64
import json
import os
import sys
import time
from io import BytesIO

try:
    import requests
except ImportError:
    print("Error: 'requests' library not found. Install it with: pip install requests")
    sys.exit(1)

try:
    from PIL import Image
except ImportError:
    print("Error: 'Pillow' library not found. Install it with: pip install pillow")
    sys.exit(1)

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
PROJECT_ROOT = os.path.abspath(os.path.join(SCRIPT_DIR, ".."))
OUTPUT_DIR = os.path.join(PROJECT_ROOT, "app", "src", "main", "assets", "story")
TARGET_WIDTH = 1376
TARGET_HEIGHT = 768

# Style template for authentic 16/32-bit SNES / PS1 JRPG background aesthetic
JRPG_STYLE_PREFIX = (
    "16-bit 32-bit retro JRPG background scenery artwork, classic SNES Chrono Trigger, "
    "Final Fantasy VI, and Suikoden II aesthetic. Crisp hand-crafted pixel art textures, "
    "dithered shading, nostalgic 90s Japanese role-playing game environment, panoramic 16:9 view, "
    "vibrant saturated retro palette, rich atmospheric lighting, no characters in foreground, "
)

JRPG_STYLE_SUFFIX = (
    ", retro pixel game background art, high-detail pixel art masterpiece, retro CRT aesthetic, "
    "cinematic lighting, no blur, no modern 3D CGI rendering, pure pixel artwork."
)

SCENE_PROMPTS = {
    # -------------------------------------------------------------------------
    # Act I: The Rise of Commander America
    # -------------------------------------------------------------------------
    "primary_debate.jpg": {
        "title": "Act I Ch 2: Quicken Loans Arena Debate Stage",
        "description": "Blinding studio spotlights, 16 podiums arranged in an arc, red and blue debate backdrop",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a grand presidential primary debate stage inside a massive arena. Sixteen sleek wooden debate "
            "podiums arranged in an imposing arc, each with microphones and gold seals. High-gloss dark reflective floor "
            "with a vibrant red carpet runner. In the background, a giant glowing high-tech blue and red curved video wall "
            "with American flag stripes and stars. Powerful white spotlights beaming down through studio haze from ceiling trusses" +
            JRPG_STYLE_SUFFIX
        )
    },
    "airport_rally.jpg": {
        "title": "Act I Ch 3: Manchester NH Airport Hangar Rally",
        "description": "Massive private Boeing 757 parked in hangar, giant US flag, podium, crowd silhouettes",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a massive heated airport hangar during a snowy night rally. Parked prominently in the center is a luxurious "
            "Boeing 757 jet airliner with navy blue and white fuselage and gold lettering. Suspended from the dark industrial "
            "steel ceiling rafters is an enormous illuminated American flag. In the foreground, a decorated campaign podium "
            "with microphones and blue bunting. In the distance, silhouettes of an enthusiastic crowd wearing red baseball caps" +
            JRPG_STYLE_SUFFIX
        )
    },

    # -------------------------------------------------------------------------
    # Act II: The Inaugural Siege & Media Wars
    # -------------------------------------------------------------------------
    "national_mall.jpg": {
        "title": "Act II Ch 1: National Mall & Capitol Steps (Jan 20, 2017)",
        "description": "Crisp winter sky, Capitol dome, reviewing stand, sea of waving American flags",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the National Mall and the United States Capitol steps on Inauguration Day. Clear crisp winter sky over the majestic "
            "white marble Capitol dome. Red, white, and blue ceremonial bunting draped over neoclassical balustrades. A grand presidential "
            "reviewing stand in the center flanked by large American flags. Looking out toward a vast sprawling sea of thousands of waving "
            "American flags stretching all the way to the Washington Monument on the horizon" +
            JRPG_STYLE_SUFFIX
        )
    },
    "capitol_rotunda.jpg": {
        "title": "Act II Ch 3: US Capitol Rotunda & Committee Hall",
        "description": "Soaring neoclassical dome, marble columns, stock trading terminals, committee table",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "inside the iconic US Capitol Rotunda. Soaring neoclassical vaulted dome ceiling with classical frescoes and an oculus "
            "letting down soft golden sunlight. Magnificent tall fluted marble Corinthian columns and historic statues in wall alcoves. "
            "In the center, an elegant dark mahogany committee table juxtaposed with modern glowing multi-monitor stock trading terminals "
            "showing green and red market candlesticks. Checkered marble floor with gleaming reflections" +
            JRPG_STYLE_SUFFIX
        )
    },

    # -------------------------------------------------------------------------
    # Act III: The Rustbelt Summit & Summit of Peace
    # -------------------------------------------------------------------------
    "steel_mill.jpg": {
        "title": "Act III Ch 1: Lordstown Ohio Steel Mill",
        "description": "Roaring blast furnaces, molten orange steel glow, industrial overhead cranes, flag on brick",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the roaring interior of an active heavy industrial steel mill in Ohio. Massive dark steel blast furnaces blazing with bright "
            "orange and golden molten metal pouring into casting vats. Showers of glowing sparks flying through the dusky air. Enormous yellow "
            "overhead gantry crane tracks above dark iron catwalks. Draped prominently across a weathered red brick wall is a huge, proud American flag. "
            "Heavy coils of newly rolled steel stacked on the reinforced concrete floor" +
            JRPG_STYLE_SUFFIX
        )
    },
    "trade_room.jpg": {
        "title": "Act III Ch 2: USMCA Trade Drafting Room",
        "description": "Mahogany conference table, leather chairs, stacks of treaty binders, Presidential seal",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "an opulent executive diplomatic drafting conference room in Washington D.C. Rich dark walnut and mahogany wood wall wainscoting. "
            "A massive polished conference table reflecting chandeliers, lined with navy blue leather chairs. Neatly organized stacks of thick "
            "USMCA trade agreement binders and fountain pens on the table. A large carved golden Presidential Seal mounted on the center wall, "
            "flanked by the flags of the United States, Mexico, and Canada on polished brass flagpoles" +
            JRPG_STYLE_SUFFIX
        )
    },
    "air_force_one.jpg": {
        "title": "Act III Ch 3: Air Force One Presidential Cabin",
        "description": "Executive blue leather seats, presidential seal on bulkhead, oval windows at 41,000 feet",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the luxurious presidential executive cabin of Air Force One flying at high altitude. Curved aircraft interior walls lined with warm "
            "recessed LED lighting and dark wood trim. Deep blue executive leather armchairs surrounding a polished conference table with briefing folders. "
            "Embroidered Presidential Seal on the forward bulkhead. Row of oval aircraft windows revealing a breathtaking sea of fluffy white and golden "
            "clouds during a dramatic high-altitude sunset" +
            JRPG_STYLE_SUFFIX
        )
    },
    "summit_table.jpg": {
        "title": "Act III Ch 4: DMZ 38th Parallel Summit Table",
        "description": "Panmunjom blue conference barracks, concrete border line, table with fast food feast",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the historic 38th Parallel Demilitarized Zone (DMZ) at Panmunjom. Iconic vibrant blue conference building barracks in the background. "
            "A concrete threshold line dividing the gravel courtyard. Exactly straddling the border line is a formal mahogany summit table with "
            "microphones, audio cables, and humorously piled high with branded fast-food burger bags and golden takeout feasts under an overcast sky" +
            JRPG_STYLE_SUFFIX
        )
    },

    # -------------------------------------------------------------------------
    # Act IV: The Intelligence Vaults & Declassification
    # -------------------------------------------------------------------------
    "fisa_vault.jpg": {
        "title": "Act IV Ch 1: FBI Hoover Sub-Basement (FISA Vault)",
        "description": "Subterranean archives, olive steel filing cabinets, fluorescent lighting, classified dossiers",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a secretive underground government archive vault in a sub-basement. Ceilings with exposed industrial air ducts and heavy conduit pipes. "
            "Flickering green-tinted fluorescent tube lights humming overhead. Endless labyrinthine rows of tall olive-drab steel filing cabinets. "
            "Desks littered with manila document folders stamped with bold red 'CLASSIFIED // FISA' stamps and thick black redaction felt markers" +
            JRPG_STYLE_SUFFIX
        )
    },
    "scif_vault.jpg": {
        "title": "Act IV Ch 2: House Intelligence SCIF Secure Vault",
        "description": "Reinforced bank-style vault door, acoustic foam wall tiles, declassification computer terminals",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a high-security Sensitive Compartmented Information Facility (SCIF). A massive open bank-vault style reinforced steel circular door "
            "with polished chrome locking wheel spokes and glowing keypads. Walls lined with dark charcoal acoustic soundproofing foam pyramid baffles. "
            "Inside, glowing dual computer monitors displaying secure terminals, and a heavy black leather binder labeled 'UNREDACTED' resting on the desk" +
            JRPG_STYLE_SUFFIX
        )
    },
    "courtroom_steps.jpg": {
        "title": "Act IV Ch 3 / Act VII Ch 1: Federal Courtroom Steps",
        "description": "Neoclassical marble courthouse facade, soaring Corinthian columns, forest of media microphones",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the imposing grand marble steps of a historic federal courthouse. Towering classical Corinthian columns supporting an engraved pediment. "
            "Broad stone steps cascading downward to a plaza. Clustered densely at the base of the steps is a dense forest of dozens of television news "
            "microphones with colorful network channel cubes, tripods, broadcast television cameras, and flashbulb glare" +
            JRPG_STYLE_SUFFIX
        )
    },
    "hearing_room.jpg": {
        "title": "Act IV Ch 4: Special Counsel Hearing Room",
        "description": "Curved congressional dais, member desks, red leather witness chairs, gavel block",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a grand congressional committee hearing room. A high, tiered semicircular oak dais where lawmakers sit, equipped with goose-neck microphones "
            "and brass nameplates. In the well of the room, a lone witness table with water pitchers, white legal pads, and high-backed burgundy leather chairs. "
            "On the center dais rests a heavy dark wooden gavel on a brass sounding block. Velvet navy drapery framing the tall oak-paneled back wall" +
            JRPG_STYLE_SUFFIX
        )
    },

    # -------------------------------------------------------------------------
    # Act V: Midnight Sorcery & The Exile
    # -------------------------------------------------------------------------
    "ballot_arena.jpg": {
        "title": "Act V Ch 1: State Farm Arena Ballot Floor (3 AM Water Pipe)",
        "description": "Empty basketball arena, folding banquet tables with ballot bins, burst water pipe puddle",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "an eerie, dimly lit professional basketball arena floor late at night. Empty tiered stadium seats rising into darkness. Overhead digital "
            "scoreboard frozen at 3:00 AM. On the hardwood basketball court, long folding banquet tables draped with black skirts, covered in plastic tubs "
            "full of paper ballots. In the center of the court is a large reflective water puddle from a supposed broken water pipe, reflecting arena lights" +
            JRPG_STYLE_SUFFIX
        )
    },
    "senate_hearing.jpg": {
        "title": "Act V Ch 2: Dirksen Senate Committee Hearing Room",
        "description": "Dark wood paneling, elevated senators' rostrum, microphones, folksy legal pad",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the historic Dirksen Senate Office Building committee hearing room. Imposing dark mahogany wall paneling with ornate carved mouldings. "
            "An elevated curved rostrum with microphone stands and leather chairs. Facing the committee is a formal witness table with water carafes "
            "and yellow legal pads filled with folksy notes. Deep crimson carpeting and soft warm recessed lighting casting dramatic shadows" +
            JRPG_STYLE_SUFFIX
        )
    },
    "maralago_terrace.jpg": {
        "title": "Act V Ch 3: Mar-a-Lago Palm Beach Terrace",
        "description": "South Florida sunset, Spanish-Moorish arches, royal palm trees, Atlantic ocean horizon",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a lavish Mediterranean Spanish-Moorish estate terrace in Palm Beach, Florida during a breathtaking golden-pink sunset. Elegant cream-colored "
            "stone arches, wrought-iron lanterns with glowing warm amber light, and terracotta tile floors. Tall majestic royal palm trees swaying gently "
            "against a turquoise Atlantic Ocean horizon. Striped resort cabanas and lush tropical flowering gardens" +
            JRPG_STYLE_SUFFIX
        )
    },
    "storage_vault.jpg": {
        "title": "Act V Ch 4: Mar-a-Lago Storage Room & Closet",
        "description": "Gilded storage room, sparkling crystal chandelier overhead, neat stacks of Bankers boxes",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "an ornate, gilded private storage room inside a Palm Beach mansion. A brilliant crystal chandelier hanging from a gilded ceiling medallion, "
            "casting sparkling facets of light. Gilded rococo gold-framed mirrors on the walls and golden marble tile floor. Stacked neatly along the walls "
            "are dozens of cardboard Bankers Boxes filled with presidential files, newspaper clippings, framed photographs, and historic souvenirs" +
            JRPG_STYLE_SUFFIX
        )
    },

    # -------------------------------------------------------------------------
    # Act VI: The Word-Salad Wasteland
    # -------------------------------------------------------------------------
    "border_wall.jpg": {
        "title": "Act VI Ch 1: Southern Border Wall Gap",
        "description": "30-foot tall rust-colored steel bollards, Sonoran desert, saguaro cactuses, patrol road",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the Southern border wall stretching across the sun-drenched Sonoran Desert. Tall 30-foot rust-brown steel bollard vertical fence posts "
            "with anti-climb plates at the top, marching into distant purple desert mountain peaks. Giant saguaro cactuses standing among golden sand dunes "
            "and desert scrub. In the foreground, an unfinished gap in the bollard wall beside stacks of unused steel panels along a dusty patrol road" +
            JRPG_STYLE_SUFFIX
        )
    },
    "venn_fog.jpg": {
        "title": "Act VI Ch 2: The Venn Diagram Fog",
        "description": "Glowing yellow and cyan holographic Venn rings floating in mystical purple fog",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a surreal, dreamlike fantasy landscape cloaked in swirling ethereal purple and indigo fog. Floating mysteriously above the misty ground are two "
            "gigantic glowing holographic Venn diagram rings—one vibrant fluorescent yellow, the other electric cyan—overlapping in the center to produce "
            "a glowing golden portal. Soft circular ripples pulsing across the ground as cosmic mist drifts through the air" +
            JRPG_STYLE_SUFFIX
        )
    },
    "electric_bus.jpg": {
        "title": "Act VI Ch 3: Stranded Electric School Bus Depot",
        "description": "Bright yellow electric school buses stuck in deep mud, unplugged charging pedestals",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "an overcast, dreary industrial transport depot. A fleet of brand-new, bright yellow electric school buses stuck deeply in thick brown mud "
            "and deep tire ruts. Green battery packs mounted on the bus roofs. High-tech white EV charging pedestals standing nearby with heavy unplugged "
            "cables drooping into puddles, their indicator lights flashing red under a gloomy gray sky" +
            JRPG_STYLE_SUFFIX
        )
    },
    "coconut_grove.jpg": {
        "title": "Act VI Ch 4: The Coconut Tree Grove",
        "description": "Tropical palm trees, falling coconuts, mystical sunbeams of existential context",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a whimsical tropical coconut grove bathed in surreal pastel sunset light. Towering coconut palm trees with curved trunks and lush green fronds "
            "against a peach and lavender sky. Large brown coconuts hanging from the canopy and scattered on the sandy forest floor. Ethereal, circular rainbow "
            "sunbeams radiating through the leaves, creating mystical glowing rings of light filtering down to the ground" +
            JRPG_STYLE_SUFFIX
        )
    },
    "unburdened_throne.jpg": {
        "title": "Act VI Ch 5: The Unburdened Throne",
        "description": "Shimmering circular dais, spinning time rings, cosmic starlit sky",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a celestial, surreal circular dais elevated above a sea of stars and glowing cosmic nebulae. Ascending translucent marble steps leading to an "
            "ornate throne framed by glowing gold and turquoise concentric rings representing the passage of time. Holographic teleprompters displaying glowing "
            "circular text scripts floating beside the throne in the quiet expanse of space" +
            JRPG_STYLE_SUFFIX
        )
    },

    # -------------------------------------------------------------------------
    # Act VII: The Lawfare Labyrinth & Steel Resolve
    # -------------------------------------------------------------------------
    "gag_hearing.jpg": {
        "title": "Act VII Ch 2: Silenced Manhattan Courtroom (Gag Order)",
        "description": "Dim somber courtroom, oversized judge's gavel casting long shadow, gag order binders",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a dim, somber Manhattan courtroom interior. Dark mahogany paneled walls and high ceilings. An imposing elevated judge's bench casting a heavy "
            "shadow over the courtroom floor. On the bench, an oversized polished wooden gavel rests beside legal binders stamped with bold red 'GAG ORDER' "
            "injunction seals. A single dramatic spotlight illuminating the empty defense table in the center" +
            JRPG_STYLE_SUFFIX
        )
    },
    "butler_fairgrounds.jpg": {
        "title": "Act VII Ch 3: Butler PA Fairgrounds (July 13, 2024)",
        "description": "Outdoor rally stage under open blue sky, colossal US flag hoisted between crane booms",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "an expansive outdoor agricultural fairgrounds in rural Pennsylvania under a hot, brilliant summer afternoon sky. A large outdoor rally stage "
            "flanked by red agricultural tractor trailers. Suspended high above the stage between two massive yellow construction crane booms is an enormous, "
            "billowing American flag. To the side, a large jumbotron video screen displays a colorful border immigration chart" +
            JRPG_STYLE_SUFFIX
        )
    },
    "steel_ear_miracle.jpg": {
        "title": "Act VII Ch 4: Stage of Steel Resolve (Fight! Fight! Fight!)",
        "description": "Iconic heroic silhouette with raised fist, white ear bandage, billowing American flag",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a historic, heroic scene on a sunlit rally stage. A colossal American flag rippling dynamically in the blue sky behind a heroic figure "
            "rising in defiance. Golden sunbeams piercing through clouds, illuminating a raised clenched fist held high into the air. A protective phalanx "
            "of dark-suited secret service agents, a glimpse of a white bandage on the ear, surrounded by pure heroic determination and golden rim light" +
            JRPG_STYLE_SUFFIX
        )
    },
    "rnc_milwaukee.jpg": {
        "title": "Act VII Ch 5: Fiserv Forum RNC Arena Convention",
        "description": "Vibrant arena convention floor, red white and blue balloons and gold confetti falling",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the electric interior of the Fiserv Forum arena during a national political convention. Massive high-definition LED stage screens glowing with "
            "patriotic red, white, and blue graphics. Thousands of arena seats packed with delegates holding state signs. Tens of thousands of red, white, "
            "and blue balloons and sparkling gold confetti raining down from the high ceiling rafters onto the red-carpeted stage" +
            JRPG_STYLE_SUFFIX
        )
    },

    # -------------------------------------------------------------------------
    # Act VIII: The Final Malarkey Citadel & Golden Inauguration
    # -------------------------------------------------------------------------
    "basement_bunker.jpg": {
        "title": "Act VIII Ch 1: Wilmington Virtual Broadcast Studio",
        "description": "Basement studio, seamless curved green screen, bright studio ring lights, teleprompters",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a high-tech virtual broadcast studio set inside a climate-controlled basement. A seamless, curved bright green chroma-key screen covering "
            "the walls and floor. Studio camera tripods with large glowing teleprompters displaying scrolling text. Bright white circular ring lights "
            "and softbox lighting umbrellas on metal stands, with cardboard storage boxes in the background" +
            JRPG_STYLE_SUFFIX
        )
    },
    "scranton_platform.jpg": {
        "title": "Act VIII Ch 2: Scranton Train Station Platform",
        "description": "Windy brick train depot platform, railway tracks, dual glass teleprompters",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a cold, windy vintage red-brick commuter passenger train station in Pennsylvania. Overhanging corrugated steel canopy with dark iron pillars. "
            "Gleaming steel railway tracks running parallel to the concrete platform with yellow safety edge lines. Standing on the platform are two high-tech "
            "transparent glass teleprompters on metal poles, reflecting the blustery gray commuter sky" +
            JRPG_STYLE_SUFFIX
        )
    },
    "autopen_factory.jpg": {
        "title": "Act VIII Ch 3: Auto-Pen Executive Order Factory",
        "description": "Futuristic factory assembly line, robotic mechanical arms signing endless rolls of orders",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "a futuristic industrial assembly line warehouse. A long conveyor belt carrying endless stacks and scrolls of 4,000-page government spending "
            "bills. Overhead, an array of articulated robotic mechanical arms holding gold fountain pens, systematically signing signatures on documents. "
            "Glowing neon green digital status readout boards on the steel walls displaying rising numbers" +
            JRPG_STYLE_SUFFIX
        )
    },
    "golden_inauguration.jpg": {
        "title": "Act VIII Ch 5: The Golden Inauguration 2025 (Golden Dawn)",
        "description": "Radiant golden sunrise over Capitol dome, millions cheering, golden dawn of America",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the ultimate triumphant golden dawn over the United States Capitol and National Mall. Spectacular golden sunrise rays bursting over the "
            "gleaming Capitol dome, bathing the entire marble city in warm radiant amber and gold. Thousands of American flags waving proudly. An ornate "
            "presidential inaugural platform covered in patriotic bunting and gold eagles. Swirling gold confetti filling the brilliant morning sky" +
            JRPG_STYLE_SUFFIX
        )
    },

    # -------------------------------------------------------------------------
    # Optional Re-Rolls for Existing Originals
    # -------------------------------------------------------------------------
    "golden_escalator.jpg": {
        "title": "Act I Ch 1: Trump Tower Golden Escalator (Optional Re-roll)",
        "description": "Polished brass and gold escalators descending through marble atrium",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the opulent golden atrium of a luxury Fifth Avenue tower. Brilliant polished brass and gold escalator descending through a soaring "
            "multi-story atrium with pink marble walls, indoor waterfalls, and sparkling chandeliers. Red carpet at the base of the escalator with "
            "a microphone podium surrounded by cheering crowds and American flags" +
            JRPG_STYLE_SUFFIX
        )
    },
    "oval_citadel.jpg": {
        "title": "Act II Ch 2 / Act VIII Ch 4: The Oval Office (Optional Re-roll)",
        "description": "The Oval Office, Resolute Desk, golden drapes, presidential seal carpet",
        "prompt": (
            JRPG_STYLE_PREFIX +
            "the iconic Oval Office inside the White House. The historic Resolute Desk made of dark carved oak in the center, cleared and ready for "
            "executive action. Towering curved windows framed by lavish, heavy golden presidential curtains. Plush oval presidential seal rug on the "
            "polished hardwood floor, with soft morning sunlight streaming across the desk" +
            JRPG_STYLE_SUFFIX
        )
    }
}


def get_api_key(args_key=None):
    """Retrieves API key from argument, environment, or user prompt."""
    if args_key:
        return args_key
    env_key = os.environ.get("GEMINI_API_KEY") or os.environ.get("GOOGLE_API_KEY")
    if env_key:
        return env_key
    print("\n[!] No GEMINI_API_KEY found in environment.")
    entered = input("Enter your Gemini API key: ").strip()
    if not entered:
        print("Error: API key is required to generate images.")
        sys.exit(1)
    return entered


def call_gemini_imagen(api_key, prompt, output_path, dry_run=False):
    """Calls Gemini Imagen API and saves the resulting image resized to TARGET_WIDTH x TARGET_HEIGHT."""
    if dry_run:
        print(f"\n[DRY RUN] Target: {output_path}")
        print(f"[DRY RUN] Prompt ({len(prompt)} chars):\n{prompt}\n")
        return True

    # Google Imagen 3 generateImages REST API endpoint
    endpoint = f"https://generativelanguage.googleapis.com/v1beta/models/imagen-3.0-generate-002:predict?key={api_key}"
    
    headers = {
        "Content-Type": "application/json"
    }
    
    payload = {
        "instances": [
            {
                "prompt": prompt
            }
        ],
        "parameters": {
            "sampleCount": 1,
            "aspectRatio": "16:9",
            "outputMimeType": "image/jpeg"
        }
    }

    print(f"  --> Calling Gemini Imagen 3 API...")
    try:
        response = requests.post(endpoint, headers=headers, json=payload, timeout=90)
    except Exception as e:
        print(f"  [ERROR] Network request failed: {e}")
        return False

    if response.status_code != 200:
        print(f"  [ERROR] API returned HTTP {response.status_code}:")
        try:
            err_data = response.json()
            print(json.dumps(err_data, indent=2))
        except Exception:
            print(response.text)
        return False

    data = response.json()
    predictions = data.get("predictions")
    if not predictions or len(predictions) == 0:
        print(f"  [ERROR] No image predictions in response: {data}")
        return False

    b64_bytes = predictions[0].get("bytesBase64Encoded")
    if not b64_bytes:
        print(f"  [ERROR] Missing 'bytesBase64Encoded' in prediction: {predictions[0].keys()}")
        return False

    image_data = base64.b64decode(b64_bytes)
    img = Image.open(BytesIO(image_data))

    # Resize to exact target game resolution (1376x768) if needed
    if img.size != (TARGET_WIDTH, TARGET_HEIGHT):
        print(f"  --> Resizing from {img.size} to ({TARGET_WIDTH}, {TARGET_HEIGHT})...")
        img = img.resize((TARGET_WIDTH, TARGET_HEIGHT), Image.Resampling.LANCZOS)

    # Save JPEG directly to destination
    os.makedirs(os.path.dirname(output_path), exist_ok=True)
    img.save(output_path, "JPEG", quality=95)
    print(f"  [SUCCESS] Saved: {output_path} ({TARGET_WIDTH}x{TARGET_HEIGHT})")
    return True


def list_scenes():
    """Prints a formatted table of all scenes and their current status on disk."""
    print("\n" + "=" * 90)
    print(f"{'#':<3} {'Filename':<28} {'Status':<10} {'Scene Title'}")
    print("=" * 90)
    for idx, (filename, info) in enumerate(SCENE_PROMPTS.items(), 1):
        target = os.path.join(OUTPUT_DIR, filename)
        status = "[EXISTS]" if os.path.exists(target) else "[MISSING]"
        print(f"{idx:<3} {filename:<28} {status:<10} {info['title']}")
    print("=" * 90 + "\n")


def interactive_menu(api_key, dry_run=False):
    """Allows selecting a scene one by one via terminal prompt."""
    scenes_list = list(SCENE_PROMPTS.items())
    while True:
        list_scenes()
        print("Options:")
        print("  1-31  : Generate that specific background image")
        print("  all   : Generate ALL backgrounds sequentially (with 15s delay)")
        print("  q     : Quit")
        choice = input("\nSelect an option: ").strip().lower()

        if choice in ['q', 'quit', 'exit']:
            print("Exiting.")
            break

        if choice == 'all':
            delay = 15
            print(f"\n[!] Generating all {len(scenes_list)} backgrounds with {delay}s delay between requests...")
            for idx, (fn, info) in enumerate(scenes_list, 1):
                print(f"\n[{idx}/{len(scenes_list)}] Generating {fn} ({info['title']})...")
                target = os.path.join(OUTPUT_DIR, fn)
                success = call_gemini_imagen(api_key, info['prompt'], target, dry_run=dry_run)
                if not success:
                    print(f"[!] Pausing or stopping on error for {fn}.")
                    retry = input("Continue to next? (y/n): ").strip().lower()
                    if retry != 'y':
                        break
                if idx < len(scenes_list) and not dry_run:
                    print(f"Waiting {delay}s for rate-limit cooldown...")
                    time.sleep(delay)
            continue

        try:
            idx = int(choice)
            if 1 <= idx <= len(scenes_list):
                fn, info = scenes_list[idx - 1]
                print(f"\n[+] Selected #{idx}: {fn}")
                print(f"    Title: {info['title']}")
                print(f"    Description: {info['description']}")
                confirm = input("Generate this image now? (Y/n): ").strip().lower()
                if confirm in ['', 'y', 'yes']:
                    target = os.path.join(OUTPUT_DIR, fn)
                    call_gemini_imagen(api_key, info['prompt'], target, dry_run=dry_run)
                    input("\nPress Enter to return to menu...")
            else:
                print("Invalid number.")
        except ValueError:
            print("Invalid input.")


def main():
    parser = argparse.ArgumentParser(description="Generate 16/32-bit JRPG background artworks using Gemini Imagen 3.")
    parser.add_argument("--scene", type=str, help="Filename of the scene to generate (e.g. primary_debate.jpg)")
    parser.add_argument("--api-key", type=str, help="Gemini API Key (defaults to GEMINI_API_KEY env var)")
    parser.add_argument("--list", action="store_true", help="List all 31 scene backgrounds")
    parser.add_argument("--dry-run", action="store_true", help="Print prompt and target path without calling the API")
    parser.add_argument("--all", action="store_true", help="Generate all backgrounds sequentially")
    parser.add_argument("--delay", type=int, default=15, help="Delay in seconds between requests when generating all (default 15)")

    args = parser.parse_args()

    if args.list:
        list_scenes()
        return

    # Specific scene generation
    if args.scene:
        filename = args.scene
        if not filename.endswith(".jpg"):
            filename += ".jpg"
        if filename not in SCENE_PROMPTS:
            print(f"Error: Unknown scene '{filename}'. Run with --list to see valid filenames.")
            sys.exit(1)

        info = SCENE_PROMPTS[filename]
        target_path = os.path.join(OUTPUT_DIR, filename)
        api_key = None if args.dry_run else get_api_key(args.api_key)
        
        print(f"\nGenerating: {filename} ({info['title']})")
        print(f"Target file: {target_path}")
        call_gemini_imagen(api_key, info["prompt"], target_path, dry_run=args.dry_run)
        return

    # Batch generation
    if args.all:
        api_key = None if args.dry_run else get_api_key(args.api_key)
        scenes = list(SCENE_PROMPTS.items())
        print(f"Starting batch generation of {len(scenes)} scenes with {args.delay}s delay...")
        for i, (fn, info) in enumerate(scenes, 1):
            print(f"\n[{i}/{len(scenes)}] {fn}: {info['title']}")
            target = os.path.join(OUTPUT_DIR, fn)
            call_gemini_imagen(api_key, info["prompt"], target, dry_run=args.dry_run)
            if i < len(scenes) and not args.dry_run:
                print(f"Sleeping {args.delay}s to respect rate limits...")
                time.sleep(args.delay)
        return

    # Default to interactive menu
    api_key = None if args.dry_run else get_api_key(args.api_key)
    interactive_menu(api_key, dry_run=args.dry_run)


if __name__ == "__main__":
    main()
