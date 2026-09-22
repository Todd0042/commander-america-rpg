#!/usr/bin/env python3
"""
Generates high-definition, stylized 1376x768 retro-cinematic RPG backgrounds for Commander America RPG.
Uses PIL and NumPy to compose multi-layered scenes with rich palettes, architectural perspective,
lighting gradients, and thematic set pieces.
"""

import math
import os
import numpy as np
from PIL import Image, ImageDraw, ImageFilter

OUTPUT_DIR = "app/src/main/assets/story"
WIDTH = 1376
HEIGHT = 768

def create_gradient(w, h, top_color, bottom_color):
    """Creates a vertical linear gradient."""
    top = np.array(top_color, dtype=np.float32)
    bot = np.array(bottom_color, dtype=np.float32)
    t = np.linspace(0, 1, h)[:, None, None]
    img_data = (top * (1 - t) + bot * t).astype(np.uint8)
    img_data = np.repeat(img_data, w, axis=1)
    return Image.fromarray(img_data, mode='RGB')

def create_horizontal_gradient(w, h, left_color, right_color):
    """Creates a horizontal linear gradient."""
    left = np.array(left_color, dtype=np.float32)
    right = np.array(right_color, dtype=np.float32)
    t = np.linspace(0, 1, w)[None, :, None]
    img_data = (left * (1 - t) + right * t).astype(np.uint8)
    img_data = np.repeat(img_data, h, axis=0)
    return Image.fromarray(img_data, mode='RGB')

def apply_radial_vignette(img, center_x, center_y, radius, inner_alpha=0, outer_alpha=180, color=(0,0,0)):
    """Applies a smooth radial vignette or lighting effect."""
    w, h = img.size
    y, x = np.ogrid[:h, :w]
    dist_from_center = np.sqrt((x - center_x)**2 + (y - center_y)**2)
    t = np.clip((dist_from_center) / radius, 0, 1)
    alpha = (inner_alpha * (1 - t) + outer_alpha * t).astype(np.uint8)
    
    overlay = Image.new('RGBA', (w, h), color + (0,))
    overlay_arr = np.zeros((h, w, 4), dtype=np.uint8)
    overlay_arr[..., 0] = color[0]
    overlay_arr[..., 1] = color[1]
    overlay_arr[..., 2] = color[2]
    overlay_arr[..., 3] = alpha
    vignette = Image.fromarray(overlay_arr, mode='RGBA')
    
    res = img.convert('RGBA')
    res = Image.alpha_composite(res, vignette)
    return res.convert('RGB')

def draw_us_flag(draw, x, y, w, h):
    """Draws a crisp American flag."""
    stripe_h = h / 13.0
    for i in range(13):
        color = (190, 30, 45) if i % 2 == 0 else (240, 240, 245)
        draw.rectangle([x, y + i * stripe_h, x + w, y + (i + 1) * stripe_h], fill=color)
    
    canton_w = w * 0.45
    canton_h = stripe_h * 7
    draw.rectangle([x, y, x + canton_w, y + canton_h], fill=(20, 35, 95))
    
    # Gold stars grid
    rows, cols = 5, 6
    for r in range(rows):
        for c in range(cols):
            sx = x + 10 + c * (canton_w - 20) / (cols - 1)
            sy = y + 8 + r * (canton_h - 16) / (rows - 1)
            draw.ellipse([sx - 2, sy - 2, sx + 2, sy + 2], fill=(255, 220, 120))

# -----------------------------------------------------------------------------
# 1. Primary Debate Stage (Quicken Loans Arena 2015)
# -----------------------------------------------------------------------------
def gen_primary_debate():
    img = create_gradient(WIDTH, HEIGHT, (12, 18, 40), (28, 40, 80))
    draw = ImageDraw.Draw(img)
    
    # Backlit curved LED screen grid
    for i in range(20):
        x = i * 70
        draw.line([x, 60, x, 460], fill=(25, 45, 100), width=2)
    for j in range(10):
        y = 60 + j * 40
        draw.line([0, y, WIDTH, y], fill=(20, 38, 90), width=2)
    
    # Central illuminated debate banner
    draw.rectangle([WIDTH*0.25, 120, WIDTH*0.75, 220], fill=(18, 28, 65), outline=(220, 180, 80), width=3)
    # American crest bar
    draw.rectangle([WIDTH*0.27, 135, WIDTH*0.73, 145], fill=(200, 30, 40))
    draw.rectangle([WIDTH*0.27, 155, WIDTH*0.73, 165], fill=(240, 240, 255))
    draw.rectangle([WIDTH*0.27, 175, WIDTH*0.73, 185], fill=(30, 60, 160))
    
    # Stage floor (high-gloss reflective dark floor with red carpet stripe)
    draw.polygon([(0, 460), (WIDTH, 460), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(15, 18, 28))
    draw.polygon([(WIDTH*0.35, 460), (WIDTH*0.65, 460), (WIDTH*0.75, HEIGHT), (WIDTH*0.25, HEIGHT)], fill=(140, 20, 30))
    
    # 16 Podiums arranged in an arc
    podium_count = 11  # Main stage arc
    for i in range(podium_count):
        px = 120 + i * (WIDTH - 240) / (podium_count - 1)
        py = 430 + 35 * math.sin(math.pi * i / (podium_count - 1))
        pw, ph = 48, 120
        # Dark wood podium with brass trim
        draw.rectangle([px - pw/2, py, px + pw/2, py + ph], fill=(38, 22, 15), outline=(210, 170, 70), width=2)
        # Seal / mic
        draw.ellipse([px - 8, py + 20, px + 8, py + 36], fill=(220, 180, 70))
        draw.line([px - 6, py, px - 10, py - 20], fill=(180, 180, 180), width=2)
        draw.line([px + 6, py, px + 10, py - 20], fill=(180, 180, 180), width=2)
    
    # Spotlights beaming down
    img = apply_radial_vignette(img, WIDTH*0.5, 420, 500, inner_alpha=0, outer_alpha=160, color=(10, 12, 25))
    return img

# -----------------------------------------------------------------------------
# 2. Airport Rally (Manchester NH Hangar with Trump Force One)
# -----------------------------------------------------------------------------
def gen_airport_rally():
    img = create_gradient(WIDTH, HEIGHT, (15, 20, 35), (35, 45, 65))
    draw = ImageDraw.Draw(img)
    
    # Hangar structural steel trusses
    for i in range(7):
        hx = i * 230
        draw.line([hx, 0, hx + 100, 320], fill=(60, 70, 90), width=5)
        draw.line([hx + 100, 320, hx + 200, 0], fill=(50, 60, 80), width=5)
    draw.line([0, 140, WIDTH, 140], fill=(80, 90, 110), width=4)
    draw.line([0, 240, WIDTH, 240], fill=(70, 80, 100), width=3)
    
    # Massive Boeing 757 Trump Force One parked inside
    # Deep blue bottom, white top, red cheatline
    draw.polygon([(WIDTH*0.1, 180), (WIDTH*0.9, 140), (WIDTH*0.95, 340), (WIDTH*0.1, 380)], fill=(22, 28, 48))
    draw.polygon([(WIDTH*0.1, 180), (WIDTH*0.9, 140), (WIDTH*0.9, 230), (WIDTH*0.1, 260)], fill=(230, 235, 245))
    draw.line([(WIDTH*0.1, 260), (WIDTH*0.9, 230)], fill=(200, 25, 35), width=6)
    
    # Tail fin with T
    draw.polygon([(WIDTH*0.75, 145), (WIDTH*0.92, 40), (WIDTH*0.97, 40), (WIDTH*0.9, 160)], fill=(20, 25, 42), outline=(210, 175, 60), width=2)
    draw.rectangle([WIDTH*0.84, 75, WIDTH*0.88, 110], fill=(220, 180, 60))
    
    # Colossal American Flag suspended in hangar
    draw_us_flag(draw, WIDTH*0.32, 70, 320, 170)
    
    # Concrete hangar floor with yellow caution lines
    draw.rectangle([0, 420, WIDTH, HEIGHT], fill=(45, 48, 55))
    for k in range(8):
        draw.line([k*200, 420, k*200 - 150, HEIGHT], fill=(190, 160, 40), width=4)
    
    # Rally staging & sea of red MAGA hats
    draw.rectangle([WIDTH*0.25, 450, WIDTH*0.75, 530], fill=(25, 30, 50), outline=(200, 170, 60), width=3)
    # Crowd silhouettes with red hats
    np.random.seed(42)
    for _ in range(350):
        cx = np.random.randint(0, WIDTH)
        cy = np.random.randint(520, HEIGHT)
        draw.ellipse([cx - 10, cy - 12, cx + 10, cy + 12], fill=(20, 22, 30))
        draw.ellipse([cx - 8, cy - 12, cx + 8, cy - 2], fill=(200, 25, 30)) # Red cap
    
    img = apply_radial_vignette(img, WIDTH*0.5, 460, 600, inner_alpha=0, outer_alpha=140, color=(10, 12, 20))
    return img

# -----------------------------------------------------------------------------
# 3. National Mall & Capitol Steps (Jan 20, 2017)
# -----------------------------------------------------------------------------
def gen_national_mall():
    # Crisp winter sky
    img = create_gradient(WIDTH, HEIGHT, (100, 140, 195), (200, 215, 235))
    draw = ImageDraw.Draw(img)
    
    # US Capitol building on horizon
    # Central Dome
    draw.ellipse([WIDTH*0.42, 140, WIDTH*0.58, 300], fill=(235, 240, 248), outline=(180, 190, 205), width=2)
    draw.rectangle([WIDTH*0.44, 210, WIDTH*0.56, 320], fill=(240, 242, 248))
    # Columns on rotunda drum
    for i in range(12):
        col_x = WIDTH*0.45 + i * (WIDTH*0.1) / 11
        draw.line([col_x, 230, col_x, 320], fill=(170, 180, 195), width=3)
    # Dome cupola and Statue of Freedom
    draw.rectangle([WIDTH*0.485, 95, WIDTH*0.515, 145], fill=(225, 230, 240))
    draw.line([WIDTH*0.5, 75, WIDTH*0.5, 95], fill=(220, 180, 50), width=4)
    
    # East and West Capitol Wings
    draw.rectangle([WIDTH*0.2, 280, WIDTH*0.8, 380], fill=(240, 244, 250), outline=(170, 180, 195), width=2)
    for i in range(26):
        cx = WIDTH*0.22 + i * (WIDTH*0.56) / 25
        draw.line([cx, 290, cx, 380], fill=(185, 195, 205), width=4)
    
    # Inaugural Bunting (Red, white, and blue swags on balustrades)
    for i in range(10):
        bx = WIDTH*0.22 + i * (WIDTH*0.56) / 9
        draw.arc([bx - 30, 360, bx + 30, 410], 0, 180, fill=(200, 30, 40), width=4)
        draw.arc([bx - 24, 365, bx + 24, 405], 0, 180, fill=(245, 245, 250), width=4)
        draw.arc([bx - 18, 370, bx + 18, 400], 0, 180, fill=(30, 60, 160), width=4)
    
    # Presidential Reviewing Stand
    draw.rectangle([WIDTH*0.36, 380, WIDTH*0.64, 470], fill=(25, 35, 65), outline=(210, 180, 60), width=3)
    draw_us_flag(draw, WIDTH*0.38, 395, 80, 45)
    draw_us_flag(draw, WIDTH*0.56, 395, 80, 45)
    
    # Capitol steps cascading down to the Mall
    steps = 8
    for s in range(steps):
        sy = 470 + s * 16
        draw.rectangle([0, sy, WIDTH, sy + 16], fill=(215 - s*6, 220 - s*6, 228 - s*6), outline=(180, 185, 195))
    
    # Sea of American flags stretching across the National Mall
    draw.rectangle([0, 598, WIDTH, HEIGHT], fill=(80, 95, 75)) # Winter grass
    np.random.seed(101)
    for _ in range(450):
        fx = np.random.randint(20, WIDTH - 20)
        fy = np.random.randint(600, HEIGHT)
        draw.line([fx, fy, fx, fy - 18], fill=(210, 190, 80), width=1)
        draw.rectangle([fx, fy - 18, fx + 14, fy - 8], fill=(200, 30, 40))
        draw.rectangle([fx, fy - 18, fx + 6, fy - 12], fill=(20, 40, 120))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 300, 700, inner_alpha=0, outer_alpha=120, color=(30, 40, 60))
    return img

# -----------------------------------------------------------------------------
# 4. Capitol Rotunda (Neoclassical Dome & Columned Hall)
# -----------------------------------------------------------------------------
def gen_capitol_rotunda():
    img = create_gradient(WIDTH, HEIGHT, (35, 38, 48), (75, 70, 65))
    draw = ImageDraw.Draw(img)
    
    # Grand coffered dome ceiling
    draw.ellipse([WIDTH*0.2, -180, WIDTH*0.8, 380], fill=(225, 220, 210), outline=(140, 130, 110), width=4)
    # Oculus center skylight
    draw.ellipse([WIDTH*0.42, 20, WIDTH*0.58, 160], fill=(250, 248, 235), outline=(180, 160, 110), width=5)
    # Dome rib arches
    for i in range(16):
        angle = i * (math.pi / 15)
        rx = WIDTH*0.5 + 380 * math.cos(angle)
        ry = 100 + 260 * math.sin(angle)
        draw.line([WIDTH*0.5, 90, rx, ry], fill=(160, 150, 130), width=3)
    
    # Corinthian marble colonnade
    cols = 10
    for c in range(cols):
        cx = 60 + c * (WIDTH - 120) / (cols - 1)
        cw = 55
        # Marble pillar with fluting
        draw.rectangle([cx - cw/2, 260, cx + cw/2, 590], fill=(215, 210, 200), outline=(130, 120, 110), width=2)
        for f in range(4):
            fx = cx - cw/2 + 10 + f * 11
            draw.line([fx, 270, fx, 580], fill=(185, 180, 170), width=2)
        # Ornate capital & base
        draw.rectangle([cx - cw/2 - 8, 250, cx + cw/2 + 8, 270], fill=(230, 220, 180), outline=(120, 110, 90), width=2)
        draw.rectangle([cx - cw/2 - 10, 580, cx + cw/2 + 10, 600], fill=(200, 195, 185), outline=(110, 105, 95), width=2)
    
    # Rotunda Frieze of American History (gold band)
    draw.rectangle([0, 230, WIDTH, 250], fill=(200, 170, 70), outline=(130, 100, 40), width=2)
    
    # Polished checkered marble floor
    draw.polygon([(0, 590), (WIDTH, 590), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(170, 165, 160))
    for t in range(12):
        ty = 590 + t * 15
        draw.line([0, ty, WIDTH, ty], fill=(110, 105, 100), width=2)
    for tx in range(24):
        draw.line([tx * 70 - 100, 590, tx * 110 - 200, HEIGHT], fill=(120, 115, 110), width=2)
    
    # Committee table with modern trading screens
    draw.rectangle([WIDTH*0.28, 550, WIDTH*0.72, 640], fill=(55, 30, 20), outline=(190, 160, 60), width=3)
    draw.rectangle([WIDTH*0.35, 520, WIDTH*0.45, 550], fill=(25, 40, 65), outline=(120, 160, 220), width=2)
    draw.rectangle([WIDTH*0.55, 520, WIDTH*0.65, 550], fill=(20, 50, 30), outline=(100, 220, 120), width=2)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 360, 550, inner_alpha=0, outer_alpha=150, color=(15, 12, 10))
    return img

# -----------------------------------------------------------------------------
# 5. Lordstown Ohio Steel Mill (Act III)
# -----------------------------------------------------------------------------
def gen_steel_mill():
    img = create_gradient(WIDTH, HEIGHT, (20, 12, 10), (45, 22, 15))
    draw = ImageDraw.Draw(img)
    
    # Dark industrial factory corrugated steel walls and steel trusses
    for i in range(12):
        x = i * 125
        draw.line([x, 0, x, 500], fill=(35, 25, 25), width=3)
        draw.line([x, 80, x + 125, 160], fill=(55, 35, 30), width=4)
        draw.line([x + 125, 80, x, 160], fill=(50, 30, 25), width=3)
    
    # Giant American flag draped proudly across the brick wall
    draw_us_flag(draw, WIDTH*0.35, 60, 380, 200)
    
    # Overhead heavy gantry crane with warning stripes
    draw.rectangle([0, 240, WIDTH, 280], fill=(70, 65, 50), outline=(30, 25, 20), width=2)
    for k in range(25):
        draw.polygon([(k*60, 240), (k*60 + 25, 240), (k*60 + 5, 280), (k*60 - 20, 280)], fill=(210, 170, 30))
    
    # Massive glowing blast furnace cauldron on left
    draw.rectangle([WIDTH*0.08, 320, WIDTH*0.35, 620], fill=(40, 32, 30), outline=(90, 60, 40), width=5)
    # Molten steel stream pouring into casting molds
    draw.polygon([(WIDTH*0.28, 480), (WIDTH*0.34, 480), (WIDTH*0.42, 680), (WIDTH*0.32, 680)], fill=(255, 170, 30))
    draw.polygon([(WIDTH*0.30, 480), (WIDTH*0.33, 480), (WIDTH*0.38, 680), (WIDTH*0.34, 680)], fill=(255, 240, 180)) # White-hot core
    
    # Factory concrete floor with glowing reflections
    draw.rectangle([0, 560, WIDTH, HEIGHT], fill=(28, 22, 20))
    draw.ellipse([WIDTH*0.25, 580, WIDTH*0.55, 720], fill=(180, 80, 20))
    
    # Steel coils stacked on right
    for s in range(4):
        cx = WIDTH*0.68 + s * 65
        cy = 550
        draw.ellipse([cx - 40, cy - 35, cx + 40, cy + 35], fill=(75, 80, 88), outline=(130, 135, 145), width=4)
        draw.ellipse([cx - 15, cy - 12, cx + 15, cy + 12], fill=(25, 28, 32))
    
    # Sparks showering
    np.random.seed(333)
    for _ in range(120):
        sx = np.random.randint(int(WIDTH*0.25), int(WIDTH*0.55))
        sy = np.random.randint(450, 720)
        draw.line([sx, sy, sx + np.random.randint(-12, 12), sy + np.random.randint(4, 18)], fill=(255, 220, 90), width=2)
    
    img = apply_radial_vignette(img, WIDTH*0.35, 560, 480, inner_alpha=0, outer_alpha=170, color=(15, 8, 5))
    return img

# -----------------------------------------------------------------------------
# 6. USMCA Trade Drafting Room (Act III)
# -----------------------------------------------------------------------------
def gen_trade_room():
    img = create_gradient(WIDTH, HEIGHT, (25, 18, 14), (55, 38, 28))
    draw = ImageDraw.Draw(img)
    
    # Grand mahogany wainscoting and wall panels
    for i in range(8):
        px = i * 180
        draw.rectangle([px + 15, 40, px + 165, 380], fill=(42, 26, 18), outline=(90, 60, 40), width=3)
    
    # Gilded Presidential Seal in carved wood on wall
    draw.ellipse([WIDTH*0.43, 80, WIDTH*0.57, 240], fill=(50, 32, 22), outline=(215, 175, 60), width=4)
    draw.ellipse([WIDTH*0.45, 100, WIDTH*0.55, 220], fill=(25, 40, 75), outline=(200, 160, 50), width=2)
    
    # Flags flanking the room (US, Canada, Mexico)
    draw.rectangle([WIDTH*0.12, 100, WIDTH*0.13, 440], fill=(190, 160, 60)) # Pole
    draw_us_flag(draw, WIDTH*0.13, 110, 140, 80)
    
    draw.rectangle([WIDTH*0.86, 100, WIDTH*0.87, 440], fill=(190, 160, 60))
    # Mexican flag (Green, White, Red)
    draw.rectangle([WIDTH*0.75, 110, WIDTH*0.79, 190], fill=(20, 110, 50))
    draw.rectangle([WIDTH*0.79, 110, WIDTH*0.83, 190], fill=(245, 245, 245))
    draw.rectangle([WIDTH*0.83, 110, WIDTH*0.86, 190], fill=(200, 30, 40))
    
    # Large executive conference table with leather chairs
    draw.polygon([(WIDTH*0.15, 430), (WIDTH*0.85, 430), (WIDTH*0.95, 680), (WIDTH*0.05, 680)], fill=(65, 35, 22), outline=(160, 110, 55), width=4)
    # Stacks of USMCA binders on the table
    for b in range(5):
        bx = WIDTH*0.25 + b * 130
        draw.rectangle([bx, 480, bx + 70, 530], fill=(25, 35, 75), outline=(220, 180, 60), width=2)
        draw.rectangle([bx + 8, 488, bx + 62, 500], fill=(220, 220, 230))
    
    # Navy blue carpet
    draw.rectangle([0, 640, WIDTH, HEIGHT], fill=(18, 25, 45))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 400, 500, inner_alpha=0, outer_alpha=140, color=(10, 8, 8))
    return img

# -----------------------------------------------------------------------------
# 7. Air Force One Presidential Cabin (Act III)
# -----------------------------------------------------------------------------
def gen_air_force_one():
    img = create_gradient(WIDTH, HEIGHT, (30, 40, 60), (60, 75, 105))
    draw = ImageDraw.Draw(img)
    
    # Curved aircraft fuselage ceiling with warm LED strip lighting
    draw.line([0, 60, WIDTH, 60], fill=(255, 230, 170), width=4)
    draw.rectangle([0, 0, WIDTH, 58], fill=(235, 238, 245))
    
    # Oval aircraft cabin windows showing sunny cloud tops outside
    windows = 6
    for w in range(windows):
        wx = 100 + w * (WIDTH - 200) / (windows - 1)
        wy = 180
        # Window frame
        draw.ellipse([wx - 45, wy - 70, wx + 45, wy + 70], fill=(220, 225, 235), outline=(160, 170, 185), width=5)
        # Sky and puffy clouds inside window
        draw.ellipse([wx - 36, wy - 60, wx + 36, wy + 60], fill=(90, 150, 230))
        draw.ellipse([wx - 30, wy + 10, wx + 35, wy + 58], fill=(245, 248, 255))
    
    # Presidential Seal embroidered on bulkhead
    draw.ellipse([WIDTH*0.44, 110, WIDTH*0.56, 250], fill=(25, 45, 85), outline=(215, 175, 55), width=4)
    
    # Executive blue leather armchairs
    draw.rectangle([WIDTH*0.18, 380, WIDTH*0.38, 560], fill=(28, 42, 75), outline=(180, 150, 60), width=3)
    draw.rectangle([WIDTH*0.62, 380, WIDTH*0.82, 560], fill=(28, 42, 75), outline=(180, 150, 60), width=3)
    
    # Polished walnut table between chairs with phones and diplomatic folders
    draw.polygon([(WIDTH*0.32, 480), (WIDTH*0.68, 480), (WIDTH*0.75, HEIGHT), (WIDTH*0.25, HEIGHT)], fill=(50, 30, 18), outline=(190, 150, 60), width=3)
    draw.rectangle([WIDTH*0.45, 520, WIDTH*0.55, 580], fill=(180, 140, 40), outline=(240, 240, 240), width=2) # "TOP SECRET" briefing binder
    
    # Deep navy carpet
    draw.rectangle([0, 620, WIDTH, HEIGHT], fill=(16, 24, 45))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 380, 550, inner_alpha=0, outer_alpha=130, color=(10, 15, 25))
    return img

# -----------------------------------------------------------------------------
# 8. DMZ 38th Parallel Summit Table (Act III)
# -----------------------------------------------------------------------------
def gen_summit_table():
    img = create_gradient(WIDTH, HEIGHT, (85, 120, 165), (170, 190, 215))
    draw = ImageDraw.Draw(img)
    
    # Iconic Panmunjom Blue conference buildings (T2/T3) in background
    draw.rectangle([WIDTH*0.05, 160, WIDTH*0.45, 420], fill=(45, 110, 190), outline=(25, 65, 120), width=3)
    draw.rectangle([WIDTH*0.55, 160, WIDTH*0.95, 420], fill=(45, 110, 190), outline=(25, 65, 120), width=3)
    # Windows on blue buildings
    for i in range(5):
        draw.rectangle([WIDTH*0.1 + i*80, 220, WIDTH*0.1 + i*80 + 45, 280], fill=(190, 220, 245), outline=(30, 70, 130), width=2)
        draw.rectangle([WIDTH*0.6 + i*80, 220, WIDTH*0.6 + i*80 + 45, 280], fill=(190, 220, 245), outline=(30, 70, 130), width=2)
    
    # Concrete border threshold slab (The Demarcation Line)
    draw.polygon([(0, 420), (WIDTH, 420), (WIDTH, 480), (0, 480)], fill=(155, 158, 162), outline=(100, 105, 110), width=2)
    # The 38th Parallel raised line
    draw.line([WIDTH*0.5, 420, WIDTH*0.5, HEIGHT], fill=(210, 30, 40), width=6)
    
    # Gravel courtyard
    draw.rectangle([0, 480, WIDTH, HEIGHT], fill=(175, 172, 165))
    
    # Conference summit table directly over the border line
    draw.polygon([(WIDTH*0.25, 460), (WIDTH*0.75, 460), (WIDTH*0.82, 660), (WIDTH*0.18, 660)], fill=(65, 38, 25), outline=(190, 150, 60), width=4)
    
    # Microphone cluster in center
    draw.rectangle([WIDTH*0.48, 510, WIDTH*0.52, 540], fill=(30, 30, 30))
    for m in range(5):
        draw.line([WIDTH*0.47 + m*7, 510, WIDTH*0.45 + m*8, 480], fill=(180, 180, 180), width=2)
        draw.ellipse([WIDTH*0.44 + m*8 - 3, 475, WIDTH*0.44 + m*8 + 3, 483], fill=(40, 40, 40))
    
    # Fast food summit feast bags & burger towers on the table!
    for f in range(4):
        bx = WIDTH*0.32 + f * 95
        if f == 2: continue # skip center for mics
        draw.rectangle([bx, 510, bx + 35, 560], fill=(195, 30, 35), outline=(240, 200, 40), width=2)
        draw.ellipse([bx + 10, 525, bx + 25, 540], fill=(240, 200, 40)) # Golden arches tribute
    
    img = apply_radial_vignette(img, WIDTH*0.5, 380, 600, inner_alpha=0, outer_alpha=120, color=(20, 25, 35))
    return img

# -----------------------------------------------------------------------------
# 9. FBI Hoover Building Sub-Basement (FISA Vault) (Act IV)
# -----------------------------------------------------------------------------
def gen_fisa_vault():
    img = create_gradient(WIDTH, HEIGHT, (15, 20, 18), (35, 42, 36))
    draw = ImageDraw.Draw(img)
    
    # Sub-basement industrial pipes and electrical conduits
    draw.line([0, 40, WIDTH, 40], fill=(60, 68, 62), width=8)
    draw.line([0, 65, WIDTH, 65], fill=(90, 75, 45), width=6)
    
    # Flickering fluorescent ceiling fixtures with green-yellow cast
    for f in range(4):
        fx = 150 + f * 340
        draw.rectangle([fx, 75, fx + 160, 90], fill=(210, 235, 190), outline=(50, 60, 50), width=2)
    
    # Endless rows of olive drab steel filing cabinets
    cabinets = 8
    for c in range(cabinets):
        cx = 50 + c * 165
        draw.rectangle([cx, 160, cx + 145, 580], fill=(48, 58, 48), outline=(25, 35, 25), width=3)
        # Draw 5 drawers per cabinet
        for d in range(5):
            dy = 175 + d * 78
            draw.rectangle([cx + 10, dy, cx + 135, dy + 68], fill=(55, 68, 55), outline=(32, 42, 32), width=2)
            # Brass card holder and pull handle
            draw.rectangle([cx + 55, dy + 15, cx + 90, dy + 32], fill=(190, 165, 75))
            draw.line([cx + 50, dy + 45, cx + 95, dy + 45], fill=(180, 180, 180), width=3)
    
    # Concrete basement floor with expansion joints
    draw.rectangle([0, 580, WIDTH, HEIGHT], fill=(38, 44, 40))
    draw.line([0, 660, WIDTH, 660], fill=(25, 30, 28), width=3)
    
    # Desks with open manila folders stamped "TOP SECRET / FISA"
    draw.rectangle([WIDTH*0.35, 520, WIDTH*0.65, 640], fill=(70, 75, 78), outline=(30, 35, 38), width=3)
    draw.rectangle([WIDTH*0.42, 540, WIDTH*0.58, 590], fill=(215, 195, 140), outline=(180, 30, 30), width=2)
    # Red classified stamp bar
    draw.rectangle([WIDTH*0.45, 555, WIDTH*0.55, 570], fill=(190, 30, 35))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 360, 480, inner_alpha=0, outer_alpha=160, color=(10, 15, 12))
    return img

# -----------------------------------------------------------------------------
# 10. House Intelligence SCIF Secure Vault (Act IV)
# -----------------------------------------------------------------------------
def gen_scif_vault():
    img = create_gradient(WIDTH, HEIGHT, (22, 28, 38), (40, 50, 68))
    draw = ImageDraw.Draw(img)
    
    # Acoustic sound-absorbing pyramid foam baffles on walls
    for y in range(4):
        for x in range(16):
            bx = x * 88
            by = 40 + y * 70
            draw.polygon([(bx, by), (bx + 44, by - 20), (bx + 88, by), (bx + 44, by + 20)], fill=(32, 40, 55), outline=(18, 24, 34))
    
    # Giant circular vault door on the left (reinforced steel bank-style door)
    draw.ellipse([WIDTH*0.05, 180, WIDTH*0.35, 580], fill=(85, 95, 110), outline=(160, 175, 195), width=8)
    draw.ellipse([WIDTH*0.12, 270, WIDTH*0.28, 490], fill=(60, 70, 85), outline=(130, 140, 160), width=5)
    # Chrome locking spokes
    for a in range(6):
        ang = a * (math.pi / 3)
        sx = WIDTH*0.20 + 90 * math.cos(ang)
        sy = 380 + 90 * math.sin(ang)
        draw.line([WIDTH*0.20, 380, sx, sy], fill=(210, 215, 225), width=6)
    
    # High-security digital keypad with glowing green LCD
    draw.rectangle([WIDTH*0.37, 340, WIDTH*0.42, 430], fill=(30, 35, 42), outline=(150, 150, 160), width=2)
    draw.rectangle([WIDTH*0.38, 350, WIDTH*0.41, 370], fill=(40, 190, 80)) # glowing green LCD
    
    # SCIF secure workstation on right with illuminated declassification screens
    draw.rectangle([WIDTH*0.55, 460, WIDTH*0.92, 600], fill=(45, 52, 65), outline=(90, 105, 130), width=3)
    # Dual monitors glowing cyan and amber
    draw.rectangle([WIDTH*0.58, 360, WIDTH*0.72, 460], fill=(15, 35, 55), outline=(60, 160, 220), width=3)
    draw.rectangle([WIDTH*0.74, 360, WIDTH*0.88, 460], fill=(45, 30, 15), outline=(220, 160, 50), width=3)
    
    # The legendary "Unredacted Binder" on the desk
    draw.rectangle([WIDTH*0.65, 490, WIDTH*0.76, 550], fill=(20, 20, 22), outline=(210, 180, 60), width=3)
    
    img = apply_radial_vignette(img, WIDTH*0.65, 420, 500, inner_alpha=0, outer_alpha=160, color=(10, 12, 20))
    return img

# -----------------------------------------------------------------------------
# 11. Federal Courtroom Steps (Act IV / Act VII)
# -----------------------------------------------------------------------------
def gen_courtroom_steps():
    img = create_gradient(WIDTH, HEIGHT, (90, 125, 175), (180, 200, 225))
    draw = ImageDraw.Draw(img)
    
    # Imposing classical marble temple facade / pediment
    draw.polygon([(0, 180), (WIDTH*0.5, 40), (WIDTH, 180)], fill=(230, 232, 238), outline=(160, 165, 175), width=3)
    # "EQUAL JUSTICE UNDER LAW" frieze
    draw.rectangle([WIDTH*0.1, 180, WIDTH*0.9, 230], fill=(240, 242, 246), outline=(170, 175, 185), width=2)
    
    # Massive Corinthian fluted columns
    cols = 8
    for c in range(cols):
        cx = 100 + c * (WIDTH - 200) / (cols - 1)
        cw = 68
        draw.rectangle([cx - cw/2, 230, cx + cw/2, 520], fill=(235, 238, 244), outline=(165, 170, 180), width=2)
        for f in range(4):
            fx = cx - cw/2 + 12 + f * 14
            draw.line([fx, 240, fx, 510], fill=(200, 205, 215), width=2)
    
    # Cascading broad marble steps
    steps = 9
    for s in range(steps):
        sy = 520 + s * 22
        draw.rectangle([0, sy, WIDTH, sy + 22], fill=(225 - s*7, 228 - s*7, 235 - s*7), outline=(170, 175, 185))
    
    # Press forest: microphones and television cameras clustered at bottom
    np.random.seed(777)
    for m in range(28):
        mx = 120 + m * (WIDTH - 240) / 27 + np.random.randint(-10, 10)
        my = 640 + np.random.randint(-15, 25)
        # Mic stand
        draw.line([mx, my, mx, my - 60], fill=(30, 30, 35), width=3)
        # Foam mic head with channel logo color
        mic_color = [(220, 30, 40), (30, 60, 180), (220, 180, 40), (230, 230, 230)][m % 4]
        draw.rectangle([mx - 6, my - 72, mx + 6, my - 60], fill=mic_color)
    
    # Yellow police tape barrier
    draw.line([0, 660, WIDTH, 640], fill=(230, 210, 40), width=6)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 380, 650, inner_alpha=0, outer_alpha=120, color=(15, 20, 30))
    return img

# -----------------------------------------------------------------------------
# 12. Congressional Hearing Room (Special Counsel Climax) (Act IV)
# -----------------------------------------------------------------------------
def gen_hearing_room():
    img = create_gradient(WIDTH, HEIGHT, (30, 22, 18), (65, 45, 32))
    draw = ImageDraw.Draw(img)
    
    # Curved elevated oak dais for lawmakers
    draw.rectangle([0, 180, WIDTH, 360], fill=(55, 35, 24), outline=(140, 95, 55), width=3)
    # Individual member desks with microphones
    for m in range(11):
        mx = 80 + m * (WIDTH - 160) / 10
        draw.rectangle([mx - 35, 220, mx + 35, 280], fill=(42, 26, 18), outline=(160, 110, 65), width=2)
        draw.line([mx, 220, mx - 6, 190], fill=(160, 160, 160), width=2)
        draw.ellipse([mx - 8, 186, mx - 4, 194], fill=(30, 30, 30))
    
    # Great Seal of the United States on wall
    draw.ellipse([WIDTH*0.44, 40, WIDTH*0.56, 170], fill=(35, 25, 18), outline=(210, 175, 60), width=4)
    draw_us_flag(draw, WIDTH*0.08, 60, 150, 90)
    draw_us_flag(draw, WIDTH*0.80, 60, 150, 90)
    
    # Witness table facing the dais
    draw.polygon([(WIDTH*0.25, 480), (WIDTH*0.75, 480), (WIDTH*0.82, 640), (WIDTH*0.18, 640)], fill=(75, 45, 28), outline=(180, 120, 65), width=4)
    # High-backed red leather witness chairs
    for c in range(3):
        cx = WIDTH*0.35 + c * 140
        draw.rectangle([cx - 30, 420, cx + 30, 480], fill=(140, 25, 30), outline=(80, 15, 18), width=3)
    
    # Carpet floor
    draw.rectangle([0, 600, WIDTH, HEIGHT], fill=(22, 28, 48))
    
    # Wooden gavel on sounding block labeled "EXONERATION"
    draw.rectangle([WIDTH*0.46, 520, WIDTH*0.54, 545], fill=(110, 65, 35), outline=(220, 180, 70), width=2)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 400, 520, inner_alpha=0, outer_alpha=140, color=(12, 10, 8))
    return img

# -----------------------------------------------------------------------------
# 13. State Farm Arena Ballot Floor (3 AM Water Pipe) (Act V)
# -----------------------------------------------------------------------------
def gen_ballot_arena():
    img = create_gradient(WIDTH, HEIGHT, (15, 18, 25), (30, 35, 50))
    draw = ImageDraw.Draw(img)
    
    # Empty basketball arena stadium seating rising into darkness
    for s in range(8):
        sy = 100 + s * 35
        draw.rectangle([0, sy, WIDTH, sy + 30], fill=(25 + s*3, 30 + s*3, 42 + s*4))
        # Empty seat rows
        for seat in range(25):
            sx = seat * 55
            draw.rectangle([sx, sy + 5, sx + 30, sy + 25], fill=(18, 22, 32))
    
    # Arena scoreboard reading "03:00 AM - EMERGENCY EVACUATION"
    draw.rectangle([WIDTH*0.36, 40, WIDTH*0.64, 110], fill=(10, 12, 16), outline=(180, 150, 40), width=3)
    draw.rectangle([WIDTH*0.42, 60, WIDTH*0.58, 90], fill=(190, 25, 30)) # Glowing red LED alert
    
    # Polished hardwood basketball court floor
    draw.polygon([(0, 380), (WIDTH, 380), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(185, 140, 85))
    # Basketball court key lines
    draw.ellipse([WIDTH*0.35, 400, WIDTH*0.65, 600], outline=(240, 240, 245), width=3)
    draw.line([0, 520, WIDTH, 520], fill=(240, 240, 245), width=3)
    
    # Rows of folding banquet tables with plastic ballot tubs
    for r in range(4):
        tx = 120 + r * 280
        ty = 440
        draw.rectangle([tx, ty, tx + 200, ty + 70], fill=(210, 215, 220), outline=(130, 135, 140), width=2)
        # Black table skirts pulled back exposing mysterious suitcases!
        draw.rectangle([tx + 10, ty + 70, tx + 190, ty + 120], fill=(20, 22, 28))
        # Suitcase underneath
        draw.rectangle([tx + 40, ty + 85, tx + 160, ty + 120], fill=(65, 65, 75), outline=(200, 180, 50), width=2)
    
    # Big water puddle from the infamous "burst pipe" with glowing reflections
    draw.ellipse([WIDTH*0.35, 580, WIDTH*0.72, 710], fill=(90, 130, 180, 160), outline=(130, 180, 230), width=2)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 460, 500, inner_alpha=0, outer_alpha=160, color=(10, 12, 18))
    return img

# -----------------------------------------------------------------------------
# 14. Senate Hearing Chamber (Dirksen Inquest) (Act V)
# -----------------------------------------------------------------------------
def gen_senate_hearing():
    img = create_gradient(WIDTH, HEIGHT, (35, 25, 20), (65, 45, 35))
    draw = ImageDraw.Draw(img)
    
    # High wood-paneled walls with acoustic bronze grilles
    for i in range(10):
        px = i * 140
        draw.rectangle([px + 10, 30, px + 130, 360], fill=(48, 30, 20), outline=(100, 68, 42), width=3)
        draw.rectangle([px + 30, 60, px + 110, 180], fill=(36, 22, 14), outline=(190, 150, 60), width=2)
    
    # Elevated Tiered Dais with Senator Nametags
    draw.rectangle([0, 260, WIDTH, 440], fill=(62, 38, 25), outline=(160, 110, 60), width=4)
    for s in range(9):
        sx = 100 + s * (WIDTH - 200) / 8
        draw.rectangle([sx - 40, 290, sx + 40, 350], fill=(45, 26, 16), outline=(210, 175, 60), width=2)
        draw.rectangle([sx - 25, 360, sx + 25, 375], fill=(240, 240, 245)) # White nametag
    
    # Red carpet floor
    draw.rectangle([0, 520, WIDTH, HEIGHT], fill=(130, 25, 32))
    
    # Witness table with water pitchers and glasses
    draw.polygon([(WIDTH*0.22, 480), (WIDTH*0.78, 480), (WIDTH*0.86, 680), (WIDTH*0.14, 680)], fill=(80, 48, 30), outline=(190, 130, 70), width=4)
    # Senator Kennedy's folksy legal pad
    draw.rectangle([WIDTH*0.45, 520, WIDTH*0.55, 580], fill=(245, 240, 160), outline=(80, 80, 80), width=2)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 380, 550, inner_alpha=0, outer_alpha=140, color=(15, 10, 8))
    return img

# -----------------------------------------------------------------------------
# 15. Mar-a-Lago Palm Beach Terrace (Act V)
# -----------------------------------------------------------------------------
def gen_maralago_terrace():
    # Warm golden/pink South Florida sunset
    img = create_gradient(WIDTH, HEIGHT, (230, 110, 80), (255, 210, 130))
    draw = ImageDraw.Draw(img)
    
    # Deep turquoise Atlantic Ocean on horizon
    draw.rectangle([0, 320, WIDTH, 440], fill=(30, 110, 140))
    draw.line([0, 320, WIDTH, 320], fill=(255, 235, 180), width=3) # Sun reflection
    
    # Spanish-Moorish architectural arcade (arches and stone balustrades)
    draw.rectangle([0, 180, WIDTH, 240], fill=(245, 230, 205), outline=(190, 165, 130), width=3)
    arches = 7
    for a in range(arches):
        ax = 100 + a * (WIDTH - 200) / (arches - 1)
        draw.arc([ax - 70, 180, ax + 70, 360], 180, 360, fill=(245, 230, 205), width=20)
        # Stone pillar
        draw.rectangle([ax - 80, 270, ax - 60, 460], fill=(240, 225, 195), outline=(170, 150, 120), width=2)
    
    # Swaying royal palm trees silhouetted against sunset
    palms = [WIDTH*0.12, WIDTH*0.28, WIDTH*0.72, WIDTH*0.88]
    for px in palms:
        # Curved trunk
        draw.line([px, 480, px + 20, 200], fill=(95, 75, 55), width=12)
        # Palm fronds
        for f in range(8):
            fa = f * (math.pi / 4)
            fx = px + 20 + 90 * math.cos(fa)
            fy = 200 + 40 * math.sin(fa)
            draw.line([px + 20, 200, fx, fy], fill=(45, 75, 35), width=6)
    
    # Terracotta tile terrace floor
    draw.polygon([(0, 440), (WIDTH, 440), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(195, 95, 60))
    for t in range(12):
        ty = 440 + t * 28
        draw.line([0, ty, WIDTH, ty], fill=(140, 65, 40), width=2)
    
    # Yellow-and-white striped resort cabanas & golden lanterns
    draw.rectangle([WIDTH*0.42, 430, WIDTH*0.58, 520], fill=(245, 245, 250), outline=(235, 190, 40), width=3)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 320, 650, inner_alpha=0, outer_alpha=110, color=(30, 20, 15))
    return img

# -----------------------------------------------------------------------------
# 16. Mar-a-Lago Storage Room & Closet (Act V)
# -----------------------------------------------------------------------------
def gen_storage_vault():
    img = create_gradient(WIDTH, HEIGHT, (45, 35, 25), (85, 65, 45))
    draw = ImageDraw.Draw(img)
    
    # Gilded wall sconces and ornate crown molding
    draw.rectangle([0, 0, WIDTH, 50], fill=(215, 175, 65), outline=(130, 95, 30), width=3)
    
    # Sparkling crystal chandelier in center
    draw.line([WIDTH*0.5, 0, WIDTH*0.5, 110], fill=(210, 180, 70), width=4)
    draw.ellipse([WIDTH*0.42, 90, WIDTH*0.58, 160], fill=(255, 245, 200), outline=(220, 185, 80), width=3)
    for c in range(12):
        cx = WIDTH*0.43 + c * 16
        draw.line([cx, 150, cx, 190], fill=(255, 255, 240), width=3)
    
    # Marble floor with golden diamond inlay
    draw.polygon([(0, 480), (WIDTH, 480), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(225, 220, 210))
    for d in range(8):
        dx = d * 180
        draw.polygon([(dx, 540), (dx + 60, 480), (dx + 120, 540), (dx + 60, 600)], fill=(210, 170, 60))
    
    # Stacks of cardboard Bankers Boxes labeled "MEMORABILIA"
    for col in range(7):
        bx = 80 + col * 175
        for row in range(4):
            by = 420 - row * 65
            draw.rectangle([bx, by, bx + 140, by + 58], fill=(215, 190, 140), outline=(130, 105, 65), width=3)
            # White label on box
            draw.rectangle([bx + 30, by + 18, bx + 110, by + 40], fill=(245, 245, 245), outline=(160, 160, 160))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 340, 500, inner_alpha=0, outer_alpha=150, color=(15, 10, 8))
    return img

# -----------------------------------------------------------------------------
# 17. Southern Border Wall (Act VI)
# -----------------------------------------------------------------------------
def gen_border_wall():
    # Blazing hot Sonoran Desert sky
    img = create_gradient(WIDTH, HEIGHT, (55, 120, 205), (230, 195, 140))
    draw = ImageDraw.Draw(img)
    
    # Distant purple desert mountains
    draw.polygon([(0, 380), (WIDTH*0.2, 280), (WIDTH*0.45, 360), (WIDTH*0.7, 260), (WIDTH, 370), (WIDTH, 450), (0, 450)], fill=(125, 95, 120))
    
    # Desert ground (sand, scrub, cactus)
    draw.rectangle([0, 420, WIDTH, HEIGHT], fill=(210, 165, 105))
    # Saguaro cactuses
    for cx in [WIDTH*0.15, WIDTH*0.85]:
        draw.line([cx, 440, cx, 320], fill=(55, 95, 45), width=14)
        draw.line([cx - 25, 380, cx, 380], fill=(55, 95, 45), width=10)
        draw.line([cx - 25, 380, cx - 25, 340], fill=(55, 95, 45), width=10)
        draw.line([cx, 360, cx + 25, 360], fill=(55, 95, 45), width=10)
        draw.line([cx + 25, 360, cx + 25, 320], fill=(55, 95, 45), width=10)
    
    # 30-foot tall rust-colored steel bollard wall stretching across the frame
    bollards = 45
    for b in range(bollards):
        bx = b * 32
        # Steel bollard post with rust color
        draw.line([bx, 140, bx, 490], fill=(135, 55, 30), width=14)
        # Anti-climb steel plate at top
        draw.rectangle([bx - 8, 140, bx + 8, 195], fill=(110, 42, 22))
    
    # Concrete base foundation
    draw.rectangle([0, 485, WIDTH, 525], fill=(175, 170, 160), outline=(110, 105, 98), width=3)
    
    # Border patrol dirt road in foreground
    draw.polygon([(0, 525), (WIDTH, 525), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(185, 145, 90))
    draw.line([0, 620, WIDTH, 620], fill=(160, 125, 75), width=6)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 340, 650, inner_alpha=0, outer_alpha=110, color=(25, 20, 15))
    return img

# -----------------------------------------------------------------------------
# 18. Venn Diagram Fog (Act VI)
# -----------------------------------------------------------------------------
def gen_venn_fog():
    # Ethereal surreal violet/cyan gradient
    img = create_gradient(WIDTH, HEIGHT, (25, 15, 45), (45, 25, 65))
    draw = ImageDraw.Draw(img)
    
    # Glowing yellow and cyan holographic Venn diagram circles floating in cosmic fog
    # Circle A: Yellow ("What Has Been")
    draw.ellipse([WIDTH*0.28, 160, WIDTH*0.56, 520], outline=(250, 225, 60), width=10)
    # Circle B: Cyan ("What Can Be")
    draw.ellipse([WIDTH*0.44, 160, WIDTH*0.72, 520], outline=(40, 220, 240), width=10)
    
    # Overlapping intersection glow (Greenish-gold)
    draw.ellipse([WIDTH*0.44, 160, WIDTH*0.56, 520], fill=(180, 240, 120, 60))
    
    # Surreal swirling mist banks
    for m in range(6):
        my = 380 + m * 45
        draw.ellipse([-100, my, WIDTH + 100, my + 140], fill=(70, 50, 95, 90))
    
    # Ground of endless circular ripple rings
    draw.polygon([(0, 560), (WIDTH, 560), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(20, 15, 35))
    for r in range(6):
        draw.ellipse([WIDTH*0.5 - r*100, 560 + r*25, WIDTH*0.5 + r*100, 580 + r*45], outline=(180, 140, 220), width=2)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 340, 500, inner_alpha=0, outer_alpha=160, color=(12, 8, 20))
    return img

# -----------------------------------------------------------------------------
# 19. Stranded Electric School Bus Depot (Act VI)
# -----------------------------------------------------------------------------
def gen_electric_bus():
    # Cold overcast industrial sky
    img = create_gradient(WIDTH, HEIGHT, (110, 120, 130), (170, 180, 190))
    draw = ImageDraw.Draw(img)
    
    # Chain-link fence and electric power transmission towers in distance
    for t in [WIDTH*0.2, WIDTH*0.8]:
        draw.line([t, 120, t - 40, 360], fill=(80, 90, 100), width=4)
        draw.line([t, 120, t + 40, 360], fill=(80, 90, 100), width=4)
        draw.line([t - 30, 200, t + 30, 200], fill=(80, 90, 100), width=3)
    
    # Muddy parking lot depot
    draw.rectangle([0, 360, WIDTH, HEIGHT], fill=(95, 78, 62)) # Deep Georgia mud
    
    # Three bright yellow electric school buses bogged down in mud ruts
    buses = [WIDTH*0.12, WIDTH*0.42, WIDTH*0.72]
    for bx in buses:
        # Yellow bus body
        draw.rectangle([bx, 310, bx + 220, 480], fill=(245, 190, 25), outline=(30, 30, 35), width=4)
        # Flat bus roof with battery pack hump
        draw.rectangle([bx + 40, 290, bx + 180, 310], fill=(50, 140, 80), outline=(25, 70, 40), width=3)
        # Windows
        for w in range(5):
            wx = bx + 18 + w * 40
            draw.rectangle([wx, 330, wx + 30, 380], fill=(60, 90, 120), outline=(20, 20, 20), width=2)
        # Black rubber tires stuck in mud
        draw.ellipse([bx + 25, 450, bx + 75, 520], fill=(30, 30, 30))
        draw.ellipse([bx + 145, 450, bx + 195, 520], fill=(30, 30, 30))
    
    # High-tech EV charging stations with dangling, unplugged charging cords
    for cx in [WIDTH*0.35, WIDTH*0.65]:
        draw.rectangle([cx, 390, cx + 35, 520], fill=(220, 225, 235), outline=(50, 140, 80), width=3)
        draw.rectangle([cx + 5, 410, cx + 30, 440], fill=(20, 20, 25))
        # Red flashing warning light
        draw.ellipse([cx + 12, 395, cx + 22, 405], fill=(230, 30, 30))
        # Dangling cable in mud
        draw.arc([cx + 25, 460, cx + 75, 540], 0, 180, fill=(35, 35, 40), width=5)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 420, 600, inner_alpha=0, outer_alpha=130, color=(20, 22, 25))
    return img

# -----------------------------------------------------------------------------
# 20. The Coconut Tree Grove (Act VI)
# -----------------------------------------------------------------------------
def gen_coconut_grove():
    # Dreamy pastel tropical sunset
    img = create_gradient(WIDTH, HEIGHT, (245, 140, 110), (255, 220, 160))
    draw = ImageDraw.Draw(img)
    
    # Mystical concentric sunbeams radiating outward ("existential context")
    for r in range(8):
        draw.ellipse([WIDTH*0.5 - r*80, 200 - r*80, WIDTH*0.5 + r*80, 200 + r*80], outline=(255, 245, 200), width=2)
    
    # Sandy tropical clearing
    draw.polygon([(0, 480), (WIDTH, 480), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(235, 195, 135))
    
    # Lush towering coconut palm trees
    palms = [WIDTH*0.15, WIDTH*0.35, WIDTH*0.65, WIDTH*0.85]
    for px in palms:
        # Curved textured trunk
        draw.line([px, 560, px - 15, 160], fill=(120, 85, 55), width=16)
        # Coconut bunches
        for c in range(5):
            ca = c * (math.pi / 2.5)
            draw.ellipse([px - 15 + 14*math.cos(ca) - 8, 160 + 14*math.sin(ca) - 8, px - 15 + 14*math.cos(ca) + 8, 160 + 14*math.sin(ca) + 8], fill=(70, 45, 25))
        # Large radiating fronds
        for f in range(10):
            fa = f * (math.pi / 5)
            fx = px - 15 + 120 * math.cos(fa)
            fy = 160 + 60 * math.sin(fa)
            draw.line([px - 15, 160, fx, fy], fill=(45, 125, 45), width=7)
    
    # Fallen coconuts scattered on the ground
    for fc in [WIDTH*0.25, WIDTH*0.48, WIDTH*0.75]:
        draw.ellipse([fc - 12, 540, fc + 12, 564], fill=(65, 40, 20), outline=(35, 20, 10), width=2)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 360, 580, inner_alpha=0, outer_alpha=120, color=(30, 20, 15))
    return img

# -----------------------------------------------------------------------------
# 21. The Unburdened Throne (Kamala Climax) (Act VI)
# -----------------------------------------------------------------------------
def gen_unburdened_throne():
    img = create_gradient(WIDTH, HEIGHT, (20, 15, 40), (50, 30, 75))
    draw = ImageDraw.Draw(img)
    
    # Shimmering celestial halo / portal of What Can Be
    draw.ellipse([WIDTH*0.35, 60, WIDTH*0.65, 360], fill=(60, 25, 90), outline=(245, 210, 60), width=6)
    draw.ellipse([WIDTH*0.38, 90, WIDTH*0.62, 330], fill=(30, 50, 110), outline=(40, 220, 240), width=4)
    
    # Ascending marble dais steps
    steps = 6
    for s in range(steps):
        sy = 360 + s * 30
        sw = WIDTH*0.5 + s * 80
        draw.rectangle([WIDTH*0.5 - sw/2, sy, WIDTH*0.5 + sw/2, sy + 30], fill=(210 - s*10, 195 - s*10, 225 - s*10), outline=(130, 100, 160), width=3)
    
    # Elevated ornate throne flanked by golden Venn rings
    draw.rectangle([WIDTH*0.45, 260, WIDTH*0.55, 360], fill=(85, 30, 95), outline=(225, 185, 55), width=4)
    # The Teleprompter of Circular Logic
    draw.rectangle([WIDTH*0.43, 220, WIDTH*0.57, 250], fill=(20, 25, 35), outline=(40, 220, 240), width=2)
    
    # Cosmic starry floor
    draw.rectangle([0, 540, WIDTH, HEIGHT], fill=(15, 12, 28))
    np.random.seed(999)
    for _ in range(80):
        sx = np.random.randint(0, WIDTH)
        sy = np.random.randint(540, HEIGHT)
        draw.ellipse([sx - 1, sy - 1, sx + 1, sy + 1], fill=(220, 220, 255))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 320, 520, inner_alpha=0, outer_alpha=160, color=(10, 8, 20))
    return img

# -----------------------------------------------------------------------------
# 22. Gag Order Hearing (Manhattan Courthouse) (Act VII)
# -----------------------------------------------------------------------------
def gen_gag_hearing():
    img = create_gradient(WIDTH, HEIGHT, (22, 16, 14), (48, 32, 24))
    draw = ImageDraw.Draw(img)
    
    # Dark oppressive judicial chamber with mahogany paneling
    for i in range(8):
        draw.rectangle([i*180 + 10, 30, i*180 + 170, 360], fill=(36, 22, 16), outline=(75, 48, 30), width=3)
    
    # High judicial bench casting a dark shadow forward
    draw.rectangle([0, 240, WIDTH, 420], fill=(50, 28, 18), outline=(120, 75, 40), width=4)
    # Massive menacing wooden gavel resting on brass block
    draw.rectangle([WIDTH*0.44, 310, WIDTH*0.56, 335], fill=(185, 140, 50))
    draw.rectangle([WIDTH*0.48, 260, WIDTH*0.52, 310], fill=(85, 48, 28), outline=(180, 130, 50), width=2)
    
    # Stack of "UNCONSTITUTIONAL GAG ORDER" documents with red seal
    draw.rectangle([WIDTH*0.32, 330, WIDTH*0.42, 380], fill=(240, 235, 225), outline=(180, 30, 30), width=2)
    draw.ellipse([WIDTH*0.35, 345, WIDTH*0.39, 365], fill=(190, 25, 30)) # Red wax seal
    
    # Dim courtroom floor
    draw.rectangle([0, 420, WIDTH, HEIGHT], fill=(24, 18, 16))
    
    # Defense table in foreground with microphone
    draw.polygon([(WIDTH*0.22, 500), (WIDTH*0.78, 500), (WIDTH*0.86, HEIGHT), (WIDTH*0.14, HEIGHT)], fill=(65, 38, 24), outline=(140, 90, 50), width=4)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 340, 480, inner_alpha=0, outer_alpha=170, color=(12, 8, 6))
    return img

# -----------------------------------------------------------------------------
# 23. Butler PA Fairgrounds (July 13, 2024) (Act VII)
# -----------------------------------------------------------------------------
def gen_butler_fairgrounds():
    # Hot, crystal clear July summer sky
    img = create_gradient(WIDTH, HEIGHT, (55, 130, 220), (160, 205, 245))
    draw = ImageDraw.Draw(img)
    
    # Open rolling green Pennsylvania countryside horizon
    draw.rectangle([0, 360, WIDTH, 480], fill=(95, 145, 65))
    
    # Two massive construction cranes hoisting a gigantic American Flag
    # Left crane boom
    draw.line([WIDTH*0.18, 440, WIDTH*0.32, 60], fill=(225, 170, 25), width=8)
    # Right crane boom
    draw.line([WIDTH*0.82, 440, WIDTH*0.68, 60], fill=(225, 170, 25), width=8)
    # Cable between booms
    draw.line([WIDTH*0.32, 60, WIDTH*0.68, 60], fill=(80, 80, 85), width=4)
    
    # Colossal American Flag suspended high in the center
    draw_us_flag(draw, WIDTH*0.32, 70, 495, 260)
    
    # Red agricultural tractor trailers parked as stage perimeter
    draw.rectangle([WIDTH*0.08, 380, WIDTH*0.25, 460], fill=(195, 30, 35), outline=(40, 40, 40), width=3)
    draw.rectangle([WIDTH*0.75, 380, WIDTH*0.92, 460], fill=(195, 30, 35), outline=(40, 40, 40), width=3)
    
    # The outdoor rally stage
    draw.rectangle([WIDTH*0.22, 430, WIDTH*0.78, 530], fill=(30, 45, 80), outline=(210, 175, 60), width=4)
    
    # The legendary Immigration Chart on the Jumbotron screen on the right
    draw.rectangle([WIDTH*0.72, 240, WIDTH*0.88, 370], fill=(245, 245, 248), outline=(25, 35, 55), width=4)
    # Red arrow and graph line on chart
    draw.line([WIDTH*0.74, 340, WIDTH*0.80, 340, WIDTH*0.86, 260], fill=(210, 30, 40), width=4)
    
    # Rally crowd field in foreground with red caps
    draw.rectangle([0, 520, WIDTH, HEIGHT], fill=(75, 110, 55))
    np.random.seed(555)
    for _ in range(400):
        cx = np.random.randint(0, WIDTH)
        cy = np.random.randint(520, HEIGHT)
        draw.ellipse([cx - 8, cy - 10, cx + 8, cy + 10], fill=(25, 30, 40))
        draw.ellipse([cx - 6, cy - 10, cx + 6, cy - 2], fill=(210, 30, 35))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 300, 680, inner_alpha=0, outer_alpha=110, color=(15, 25, 40))
    return img

# -----------------------------------------------------------------------------
# 24. Stage of Steel Resolve & Raised Fist (Act VII)
# -----------------------------------------------------------------------------
def gen_steel_ear_miracle():
    # Dramatic cinematic sky with golden sun rays piercing clouds
    img = create_gradient(WIDTH, HEIGHT, (35, 75, 145), (240, 180, 110))
    draw = ImageDraw.Draw(img)
    
    # Billowing American Flag behind the central iconic silhouette
    draw_us_flag(draw, WIDTH*0.22, 100, 750, 420)
    
    # Radiant golden halo / rim light in center
    draw.ellipse([WIDTH*0.35, 140, WIDTH*0.65, 480], fill=(255, 240, 180, 70))
    
    # Heroic silhouette: Secret service protective phalanx & raised defiant fist
    # Secret service agent shoulders
    draw.polygon([(WIDTH*0.36, 520), (WIDTH*0.44, 420), (WIDTH*0.50, 520)], fill=(18, 20, 26))
    draw.polygon([(WIDTH*0.50, 520), (WIDTH*0.56, 420), (WIDTH*0.64, 520)], fill=(18, 20, 26))
    
    # Commander America rising tall: suit, collar, red tie
    draw.polygon([(WIDTH*0.42, 540), (WIDTH*0.47, 360), (WIDTH*0.53, 360), (WIDTH*0.58, 540)], fill=(22, 28, 48))
    # Red tie
    draw.polygon([(WIDTH*0.49, 390), (WIDTH*0.51, 390), (WIDTH*0.52, 490), (WIDTH*0.48, 490)], fill=(210, 25, 35))
    # White collar
    draw.polygon([(WIDTH*0.48, 370), (WIDTH*0.52, 370), (WIDTH*0.50, 400)], fill=(245, 245, 250))
    
    # Head with iconic hair and white bandage on right ear
    draw.ellipse([WIDTH*0.47, 320, WIDTH*0.53, 370], fill=(235, 180, 140))
    draw.arc([WIDTH*0.46, 310, WIDTH*0.54, 345], 160, 360, fill=(230, 195, 75), width=10) # Golden hair
    draw.rectangle([WIDTH*0.52, 335, WIDTH*0.54, 350], fill=(255, 255, 255)) # White ear bandage!
    
    # The Raised Fist reaching high into the sky!
    draw.line([WIDTH*0.48, 360, WIDTH*0.48, 220], fill=(22, 28, 48), width=16)
    draw.ellipse([WIDTH*0.45, 195, WIDTH*0.51, 235], fill=(235, 180, 140)) # Clenched fist
    
    # Rally podium in foreground with presidential seal
    draw.polygon([(WIDTH*0.28, 540), (WIDTH*0.72, 540), (WIDTH*0.78, HEIGHT), (WIDTH*0.22, HEIGHT)], fill=(25, 32, 52), outline=(215, 175, 60), width=4)
    draw.ellipse([WIDTH*0.46, 580, WIDTH*0.54, 660], fill=(30, 45, 80), outline=(225, 185, 60), width=3)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 320, 550, inner_alpha=0, outer_alpha=140, color=(15, 15, 22))
    return img

# -----------------------------------------------------------------------------
# 25. Milwaukee Fiserv Forum RNC Convention (Act VII)
# -----------------------------------------------------------------------------
def gen_rnc_milwaukee():
    img = create_gradient(WIDTH, HEIGHT, (15, 22, 45), (35, 45, 80))
    draw = ImageDraw.Draw(img)
    
    # Huge LED video walls flashing red, white, and blue graphics
    draw.rectangle([WIDTH*0.15, 80, WIDTH*0.85, 260], fill=(20, 30, 65), outline=(220, 180, 60), width=4)
    draw.rectangle([WIDTH*0.2, 100, WIDTH*0.4, 240], fill=(195, 25, 35))
    draw.rectangle([WIDTH*0.4, 100, WIDTH*0.6, 240], fill=(245, 245, 250))
    draw.rectangle([WIDTH*0.6, 100, WIDTH*0.8, 240], fill=(25, 55, 160))
    
    # Arena upper seating bowl packed with delegates
    for r in range(6):
        ry = 260 + r * 25
        draw.rectangle([0, ry, WIDTH, ry + 22], fill=(28 + r*4, 35 + r*4, 60 + r*5))
    
    # Convention floor with state delegation standard signs
    draw.polygon([(0, 410), (WIDTH, 410), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(120, 20, 30)) # Red arena carpet
    
    # 100,000 Balloons and gold confetti cascading from rafters!
    np.random.seed(888)
    for _ in range(350):
        bx = np.random.randint(20, WIDTH - 20)
        by = np.random.randint(40, 560)
        b_color = [(220, 30, 40), (245, 245, 250), (30, 70, 190), (235, 195, 50)][np.random.randint(0, 4)]
        draw.ellipse([bx - 8, by - 10, bx + 8, by + 10], fill=b_color)
    
    # Golden confetti specks
    for _ in range(500):
        cx = np.random.randint(0, WIDTH)
        cy = np.random.randint(0, HEIGHT)
        draw.rectangle([cx, cy, cx + 3, cy + 3], fill=(255, 215, 75))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 320, 600, inner_alpha=0, outer_alpha=120, color=(12, 16, 30))
    return img

# -----------------------------------------------------------------------------
# 26. Wilmington Basement Virtual Studio (Act VIII)
# -----------------------------------------------------------------------------
def gen_basement_bunker():
    img = create_gradient(WIDTH, HEIGHT, (25, 30, 28), (45, 50, 46))
    draw = ImageDraw.Draw(img)
    
    # Concrete basement wall & low suspended drop ceiling
    draw.rectangle([0, 0, WIDTH, 60], fill=(50, 52, 50), outline=(30, 30, 30), width=2)
    
    # Giant curved bright green chroma-key screen (Green Screen)
    draw.polygon([(WIDTH*0.12, 100), (WIDTH*0.88, 100), (WIDTH*0.92, 520), (WIDTH*0.08, 520)], fill=(0, 185, 45), outline=(0, 130, 30), width=4)
    
    # Professional studio ring lights and softboxes on metal tripods
    for lx in [WIDTH*0.18, WIDTH*0.82]:
        # Light tripod
        draw.line([lx, 160, lx - 30, 480], fill=(40, 40, 42), width=3)
        draw.line([lx, 160, lx + 30, 480], fill=(40, 40, 42), width=3)
        # Glowing white circular ring light
        draw.ellipse([lx - 35, 125, lx + 35, 195], fill=(255, 255, 240), outline=(80, 80, 80), width=5)
    
    # Studio camera dolly & teleprompter rig in center
    draw.rectangle([WIDTH*0.44, 380, WIDTH*0.56, 520], fill=(35, 38, 42), outline=(15, 15, 18), width=3)
    # Teleprompter screen flashing "PAUSE FOR APPLAUSE"
    draw.rectangle([WIDTH*0.42, 320, WIDTH*0.58, 380], fill=(10, 15, 12), outline=(0, 200, 60), width=3)
    
    # Basement grey linoleum floor
    draw.rectangle([0, 520, WIDTH, HEIGHT], fill=(65, 68, 70))
    
    # Cardboard box labeled "CALL A LID AT 9:15 AM"
    draw.rectangle([WIDTH*0.75, 550, WIDTH*0.88, 650], fill=(190, 160, 110), outline=(120, 95, 60), width=3)
    
    img = apply_radial_vignette(img, WIDTH*0.5, 380, 500, inner_alpha=0, outer_alpha=150, color=(12, 15, 12))
    return img

# -----------------------------------------------------------------------------
# 27. Scranton Train Platform (Act VIII)
# -----------------------------------------------------------------------------
def gen_scranton_platform():
    # Cold blustery Pennsylvania commuter sky
    img = create_gradient(WIDTH, HEIGHT, (85, 95, 110), (145, 155, 170))
    draw = ImageDraw.Draw(img)
    
    # Vintage red-brick Scranton passenger depot in background
    draw.rectangle([WIDTH*0.05, 160, WIDTH*0.55, 380], fill=(145, 55, 45), outline=(75, 28, 22), width=3)
    for w in range(6):
        draw.rectangle([WIDTH*0.08 + w*80, 220, WIDTH*0.08 + w*80 + 45, 320], fill=(220, 225, 235), outline=(50, 20, 15), width=2)
    
    # Overhanging train shed corrugated steel canopy
    draw.polygon([(0, 120), (WIDTH, 120), (WIDTH, 220), (0, 260)], fill=(55, 60, 65), outline=(30, 32, 35), width=3)
    # Heavy steel support posts
    for p in range(5):
        px = 120 + p * 280
        draw.line([px, 180, px, 460], fill=(70, 75, 82), width=8)
    
    # Commuter train tracks on the right side
    draw.rectangle([WIDTH*0.65, 420, WIDTH, HEIGHT], fill=(85, 75, 65)) # Ballast rock
    draw.line([WIDTH*0.72, 420, WIDTH*0.78, HEIGHT], fill=(175, 180, 185), width=6) # Steel rails
    draw.line([WIDTH*0.88, 420, WIDTH*0.96, HEIGHT], fill=(175, 180, 185), width=6)
    
    # Concrete train platform
    draw.polygon([(0, 440), (WIDTH*0.65, 420), (WIDTH*0.75, HEIGHT), (0, HEIGHT)], fill=(165, 170, 175), outline=(110, 115, 120), width=2)
    # Yellow safety edge stripe
    draw.line([WIDTH*0.64, 420, WIDTH*0.74, HEIGHT], fill=(225, 195, 35), width=8)
    
    # Dual glass teleprompters flashing in the wind on the platform
    for tx in [WIDTH*0.25, WIDTH*0.48]:
        draw.line([tx, 320, tx, 480], fill=(30, 30, 30), width=3) # Pole
        draw.rectangle([tx - 35, 250, tx + 35, 320], fill=(180, 220, 240, 80), outline=(200, 230, 255), width=3) # Angled glass
    
    img = apply_radial_vignette(img, WIDTH*0.5, 380, 600, inner_alpha=0, outer_alpha=130, color=(15, 18, 25))
    return img

# -----------------------------------------------------------------------------
# 28. Auto-Pen Executive Order Factory (Act VIII)
# -----------------------------------------------------------------------------
def gen_autopen_factory():
    img = create_gradient(WIDTH, HEIGHT, (20, 22, 35), (42, 48, 70))
    draw = ImageDraw.Draw(img)
    
    # High-tech clean-room automated industrial facility
    for i in range(12):
        draw.line([i*120, 0, i*120, 380], fill=(35, 40, 58), width=2)
    
    # Conveyor belt assembly line running down the center
    draw.polygon([(WIDTH*0.15, 420), (WIDTH*0.85, 420), (WIDTH*0.95, HEIGHT), (WIDTH*0.05, HEIGHT)], fill=(55, 62, 75), outline=(130, 145, 170), width=4)
    # Endless reams of 4,000-page omnibus bills rolling down the belt
    for b in range(6):
        by = 440 + b * 45
        draw.rectangle([WIDTH*0.35, by, WIDTH*0.65, by + 30], fill=(245, 245, 248), outline=(180, 180, 190), width=2)
    
    # Array of mechanical robotic arms holding gold fountain pens
    pens = 5
    for p in range(pens):
        px = 180 + p * 240
        py = 340
        # Articulated robotic arm
        draw.line([px, 120, px + 40, 240], fill=(120, 130, 145), width=8)
        draw.line([px + 40, 240, px, py], fill=(150, 160, 175), width=6)
        # Gold fountain pen tip scribbling signature
        draw.line([px, py, px - 10, py + 45], fill=(225, 185, 55), width=4)
        draw.ellipse([px - 14, py + 42, px - 6, py + 48], fill=(20, 30, 90)) # Blue ink puddle
    
    # Neon green status monitors reading "DEFICIT: +$34 TRILLION"
    draw.rectangle([WIDTH*0.38, 60, WIDTH*0.62, 140], fill=(10, 15, 20), outline=(0, 220, 80), width=3)
    draw.rectangle([WIDTH*0.42, 85, WIDTH*0.58, 115], fill=(0, 200, 60))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 360, 520, inner_alpha=0, outer_alpha=150, color=(10, 12, 20))
    return img

# -----------------------------------------------------------------------------
# 29. Golden Inauguration 2025 (Act VIII Finale)
# -----------------------------------------------------------------------------
def gen_golden_inauguration():
    # Glorious golden radiant sunrise over the Nation's Capital
    img = create_gradient(WIDTH, HEIGHT, (255, 170, 70), (255, 235, 160))
    draw = ImageDraw.Draw(img)
    
    # Heavenly golden sunrays bursting over the Capitol Dome
    for a in range(24):
        ang = a * (math.pi / 12)
        rx = WIDTH*0.5 + 900 * math.cos(ang)
        ry = 220 + 900 * math.sin(ang)
        draw.line([WIDTH*0.5, 220, rx, ry], fill=(255, 245, 190, 80), width=4)
    
    # US Capitol Dome bathed in brilliant morning sunlight
    draw.ellipse([WIDTH*0.43, 110, WIDTH*0.57, 260], fill=(255, 252, 240), outline=(225, 185, 70), width=4)
    draw.rectangle([WIDTH*0.45, 180, WIDTH*0.55, 280], fill=(255, 250, 240))
    # Columns on drum
    for i in range(10):
        col_x = WIDTH*0.46 + i * (WIDTH*0.08) / 9
        draw.line([col_x, 195, col_x, 280], fill=(210, 175, 80), width=3)
    # Cupola & Statue of Freedom with golden aura
    draw.rectangle([WIDTH*0.49, 70, WIDTH*0.51, 115], fill=(245, 235, 190))
    draw.ellipse([WIDTH*0.485, 50, WIDTH*0.515, 70], fill=(255, 225, 75))
    
    # Capitol Building wings with grand red/white/blue celebratory draping
    draw.rectangle([WIDTH*0.18, 250, WIDTH*0.82, 360], fill=(255, 252, 245), outline=(210, 180, 90), width=3)
    for b in range(12):
        bx = WIDTH*0.2 + b * (WIDTH*0.6) / 11
        draw_us_flag(draw, bx - 15, 290, 30, 50)
    
    # Grand Inaugural Platform decorated with gold eagles and floral wreaths
    draw.rectangle([WIDTH*0.3, 350, WIDTH*0.7, 460], fill=(28, 42, 85), outline=(235, 195, 65), width=4)
    draw_us_flag(draw, WIDTH*0.33, 370, 90, 50)
    draw_us_flag(draw, WIDTH*0.58, 370, 90, 50)
    
    # The Presidential Oath of Office Lectern
    draw.rectangle([WIDTH*0.47, 390, WIDTH*0.53, 460], fill=(55, 32, 18), outline=(235, 195, 65), width=3)
    draw.ellipse([WIDTH*0.485, 410, WIDTH*0.515, 435], fill=(235, 195, 65))
    
    # National Mall and Pennsylvania Avenue filled with millions celebrating
    draw.polygon([(0, 460), (WIDTH, 460), (WIDTH, HEIGHT), (0, HEIGHT)], fill=(65, 85, 60))
    
    # Sea of waving flags & gold confetti filling the sky
    np.random.seed(1776)
    for _ in range(600):
        fx = np.random.randint(0, WIDTH)
        fy = np.random.randint(480, HEIGHT)
        draw.line([fx, fy, fx, fy - 16], fill=(225, 200, 80), width=1)
        draw.rectangle([fx, fy - 16, fx + 12, fy - 7], fill=(210, 25, 35))
        draw.rectangle([fx, fy - 16, fx + 5, fy - 11], fill=(25, 45, 120))
    
    # Cascading golden confetti celebrating the Golden Age of America!
    for _ in range(750):
        cx = np.random.randint(0, WIDTH)
        cy = np.random.randint(0, HEIGHT)
        draw.ellipse([cx, cy, cx + 4, cy + 4], fill=(255, 225, 90))
    
    img = apply_radial_vignette(img, WIDTH*0.5, 260, 650, inner_alpha=0, outer_alpha=90, color=(50, 30, 10))
    return img


# -----------------------------------------------------------------------------
# Main Generation Runner
# -----------------------------------------------------------------------------
GENERATORS = {
    "primary_debate.jpg": gen_primary_debate,
    "airport_rally.jpg": gen_airport_rally,
    "national_mall.jpg": gen_national_mall,
    "capitol_rotunda.jpg": gen_capitol_rotunda,
    "steel_mill.jpg": gen_steel_mill,
    "trade_room.jpg": gen_trade_room,
    "air_force_one.jpg": gen_air_force_one,
    "summit_table.jpg": gen_summit_table,
    "fisa_vault.jpg": gen_fisa_vault,
    "scif_vault.jpg": gen_scif_vault,
    "courtroom_steps.jpg": gen_courtroom_steps,
    "hearing_room.jpg": gen_hearing_room,
    "ballot_arena.jpg": gen_ballot_arena,
    "senate_hearing.jpg": gen_senate_hearing,
    "maralago_terrace.jpg": gen_maralago_terrace,
    "storage_vault.jpg": gen_storage_vault,
    "border_wall.jpg": gen_border_wall,
    "venn_fog.jpg": gen_venn_fog,
    "electric_bus.jpg": gen_electric_bus,
    "coconut_grove.jpg": gen_coconut_grove,
    "unburdened_throne.jpg": gen_unburdened_throne,
    "gag_hearing.jpg": gen_gag_hearing,
    "butler_fairgrounds.jpg": gen_butler_fairgrounds,
    "steel_ear_miracle.jpg": gen_steel_ear_miracle,
    "rnc_milwaukee.jpg": gen_rnc_milwaukee,
    "basement_bunker.jpg": gen_basement_bunker,
    "scranton_platform.jpg": gen_scranton_platform,
    "autopen_factory.jpg": gen_autopen_factory,
    "golden_inauguration.jpg": gen_golden_inauguration,
}

def main():
    os.makedirs(OUTPUT_DIR, exist_ok=True)
    print(f"Generating {len(GENERATORS)} custom backgrounds in {OUTPUT_DIR}...")
    for filename, gen_fn in GENERATORS.items():
        path = os.path.join(OUTPUT_DIR, filename)
        img = gen_fn()
        img.save(path, "JPEG", quality=92)
        print(f"  [OK] Saved {filename} ({WIDTH}x{HEIGHT})")
    print("All backgrounds generated successfully!")

if __name__ == "__main__":
    main()
