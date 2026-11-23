#!/usr/bin/env python3
"""
badge_app_icon.py
Composites the Voice-RPG-Engine family golden microphone badge onto the top-right
corner of the game's app icon, then generates all Android mipmap densities and playstore icon.
"""
import os
import sys
from PIL import Image, ImageChops, ImageDraw, ImageFilter

NAVY_BG = (14, 23, 44, 255)


def strip_white_exterior_corners(img: Image.Image, thresh: int = 40) -> Image.Image:
    """Make near-white exterior corners transparent without touching interior highlights."""
    rgba = img.convert("RGBA")
    w, h = rgba.size
    orig = rgba.convert("RGB")
    work = orig.copy()
    filled = False
    for pt in ((0, 0), (w - 1, 0), (0, h - 1), (w - 1, h - 1)):
        r, g, b = orig.getpixel(pt)
        if r > 235 and g > 235 and b > 235:
            ImageDraw.floodfill(work, pt, (255, 0, 255), thresh=thresh)
            filled = True
    if not filled:
        return rgba
    keep = ImageChops.difference(work, orig).convert("L").point(lambda v: 0 if v else 255)
    rgba.putalpha(ImageChops.darker(rgba.getchannel("A"), keep))
    return rgba


def flatten_navy(rgba: Image.Image) -> Image.Image:
    """Composite an RGBA icon over dark navy matching ic_launcher_background."""
    bg = Image.new("RGBA", rgba.size, NAVY_BG)
    return Image.alpha_composite(bg, rgba.convert("RGBA"))

def make_circular_badge(engine_icon_path, badge_diameter):
    # Load engine icon
    engine_img = Image.open(engine_icon_path).convert("RGBA")
    w, h = engine_img.size
    
    # Crop central circular medallion (the golden microphone emblem)
    # The microphone is centered, so a center square crop captures it perfectly
    crop_size = int(min(w, h) * 0.72)
    left = (w - crop_size) // 2
    top = (h - crop_size) // 2
    cropped = engine_img.crop((left, top, left + crop_size, top + crop_size))
    
    # Resize to target diameter
    badge = cropped.resize((badge_diameter, badge_diameter), Image.Resampling.LANCZOS)
    
    # Create circular alpha mask with slight gold outer ring
    mask = Image.new("L", (badge_diameter, badge_diameter), 0)
    draw = ImageDraw.Draw(mask)
    draw.ellipse((2, 2, badge_diameter - 3, badge_diameter - 3), fill=255)
    
    # Apply mask
    badge.putalpha(mask)
    
    # Add gold border ring
    ring = Image.new("RGBA", (badge_diameter, badge_diameter), (0, 0, 0, 0))
    r_draw = ImageDraw.Draw(ring)
    r_draw.ellipse((1, 1, badge_diameter - 2, badge_diameter - 2), outline=(230, 185, 60, 255), width=3)
    
    # Composite badge with ring
    final_badge = Image.alpha_composite(badge, ring)
    return final_badge

def apply_badge_to_icon(base_icon_path, engine_icon_path, output_icon_path):
    base = Image.open(base_icon_path).convert("RGBA")
    base_w, base_h = base.size
    
    # Scale base if needed to 512x512, then strip any white outer corners
    if (base_w, base_h) != (512, 512):
        base = base.resize((512, 512), Image.Resampling.LANCZOS)
        base_w, base_h = 512, 512
    base = strip_white_exterior_corners(base)
    
    # Badge size: ~26% of icon width
    badge_size = int(base_w * 0.26)
    badge = make_circular_badge(engine_icon_path, badge_size)
    
    # Badge position: top-right corner with 16px padding
    padding = int(base_w * 0.035)
    x = base_w - badge_size - padding
    y = padding
    
    # Drop shadow behind badge
    shadow = Image.new("RGBA", (base_w, base_h), (0, 0, 0, 0))
    s_mask = Image.new("L", (badge_size + 8, badge_size + 8), 0)
    s_draw = ImageDraw.Draw(s_mask)
    s_draw.ellipse((4, 4, badge_size + 4, badge_size + 4), fill=180)
    s_mask = s_mask.filter(ImageFilter.GaussianBlur(4))
    
    shadow_badge = Image.new("RGBA", (badge_size + 8, badge_size + 8), (0, 0, 0, 255))
    shadow_badge.putalpha(s_mask)
    shadow.paste(shadow_badge, (x - 4, y - 2), shadow_badge)
    
    # Composite shadow then badge (keeping transparent outer corners in returned RGBA)
    composited = Image.alpha_composite(base, shadow)
    composited.paste(badge, (x, y), badge)
    
    # Save flattened Play Store output over dark navy
    flatten_navy(composited).convert("RGB").save(output_icon_path, quality=95)
    print(f"Badged icon generated at: {output_icon_path}")
    return composited

def generate_mipmaps(badged_icon_512, res_dir):
    densities = {
        "mipmap-xxxhdpi": 192,
        "mipmap-xxhdpi": 144,
        "mipmap-xhdpi": 96,
        "mipmap-hdpi": 72,
        "mipmap-mdpi": 48
    }
    
    flat_512 = flatten_navy(badged_icon_512)
    for folder, size in densities.items():
        target_dir = os.path.join(res_dir, folder)
        os.makedirs(target_dir, exist_ok=True)
        resized = flat_512.resize((size, size), Image.Resampling.LANCZOS)
        
        # Standard icon
        resized.convert("RGBA").save(os.path.join(target_dir, "ic_launcher.png"))
        
        # Round icon (circle mask)
        round_mask = Image.new("L", (size, size), 0)
        draw = ImageDraw.Draw(round_mask)
        draw.ellipse((0, 0, size - 1, size - 1), fill=255)
        round_icon = resized.copy()
        round_icon.putalpha(round_mask)
        round_icon.save(os.path.join(target_dir, "ic_launcher_round.png"))
        print(f"Saved {folder} ({size}x{size})")

def generate_adaptive_foregrounds(badged_icon_512, res_dir):
    """Generate Android 8+ adaptive foreground layers sized to the safe zone."""
    densities = {
        "drawable-xxxhdpi": 432,
        "drawable-xxhdpi": 324,
        "drawable-xhdpi": 216,
        "drawable-hdpi": 162,
        "drawable-mdpi": 108,
    }
    for folder, size in densities.items():
        target_dir = os.path.join(res_dir, folder)
        os.makedirs(target_dir, exist_ok=True)
        inner = round(size * 0.74)
        scaled = badged_icon_512.convert("RGBA").resize((inner, inner), Image.Resampling.LANCZOS)
        canvas = Image.new("RGBA", (size, size), (0, 0, 0, 0))
        offset = (size - inner) // 2
        canvas.paste(scaled, (offset, offset), scaled)
        canvas.save(os.path.join(target_dir, "ic_launcher_foreground.png"))
        print(f"Saved {folder}/ic_launcher_foreground.png ({size}x{size}, inner {inner}x{inner})")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python3 badge_app_icon.py <base_icon_image>")
        sys.exit(1)
        
    base_path = sys.argv[1]
    engine_icon = "/home/todd/Documents/GitHub/voice-rpg-engine/app/src/main/res/ic_launcher-playstore.png"
    repo_res = "/home/todd/Documents/GitHub/commander-america-rpg/app/src/main/res"
    playstore_out = os.path.join(repo_res, "ic_launcher-playstore.png")
    
    result = apply_badge_to_icon(base_path, engine_icon, playstore_out)
    generate_mipmaps(result, repo_res)
    generate_adaptive_foregrounds(result, repo_res)
    print("All app icons and densities updated successfully!")
