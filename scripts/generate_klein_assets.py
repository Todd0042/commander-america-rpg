#!/usr/bin/env python3
"""Generate all assets from new-asset-generation.md using local FLUX.2 Klein 4B + 2x pixelation.

Walks sequentially through new-asset-generation.md (#01 through #53), generates each
asset via /home/todd/graphical-studio's ComfyUI + FLUX.2 Klein 4B pipeline, applies
2x pixelation (2x BOX downscale -> palette quantization -> 2x NEAREST upscale), and
atomically moves/copies each rendered file into its exact in-repo asset destination.

Background naming & aspect-ratio variants (for #29-#52):
  --bg-variant portrait   (default) 720x1280  -> story/<name>_p.jpg  (typical 9:16 phone)
  --bg-variant square               1024x1024 -> story/<name>_s.jpg  (1:1 unfolded foldable)
  --bg-variant landscape            1376x768  -> story/<name>.jpg    (16:9 widescreen)

Examples:
  # Preview all 53 parsed prompts, dimensions, and destination paths without rendering:
  python3 scripts/generate_klein_assets.py --dry-run

  # Generate all portrait (_p) story backgrounds (#29-#52) sequentially:
  python3 scripts/generate_klein_assets.py --section backgrounds --bg-variant portrait

  # Generate only square (_s) story backgrounds (#29-#52) for unfolded foldables:
  python3 scripts/generate_klein_assets.py --section backgrounds --bg-variant square

  # Generate the entire file (#01-#53) sequentially, skipping already-rendered targets:
  python3 scripts/generate_klein_assets.py --skip-existing

  # Test two items (#01 portrait and #29 _p background) into a staging dir first:
  python3 scripts/generate_klein_assets.py --ids 1,29 --staging-only
"""

from __future__ import annotations

import argparse
import gc
import importlib.util
import os
import re
import shutil
import subprocess
import sys
import tempfile
import time
from dataclasses import dataclass
from pathlib import Path

PROJECT_ROOT = Path(__file__).resolve().parent.parent
PROMPT_DOC = PROJECT_ROOT / "new-asset-generation.md"
ASSETS_DIR = PROJECT_ROOT / "app" / "src" / "main" / "assets"
RES_DIR = PROJECT_ROOT / "app" / "src" / "main" / "res"

STUDIO_ROOT = Path(os.environ.get("GRAPHICAL_STUDIO_DIR", "/home/todd/graphical-studio"))
STUDIO_VENV_PY = STUDIO_ROOT / ".venv" / "bin" / "python"
COMFY_DIR = STUDIO_ROOT / "ComfyUI"

KLEIN_UNET = "flux-2-klein-4b-Q8_0.gguf"
KLEIN_TE = "qwen_3_4b.safetensors"
KLEIN_VAE = "flux2-vae.safetensors"

ENGINE_BADGE_CANDIDATES = (
    Path("/home/todd/GitHub/voice-rpg-engine/app/src/main/res/ic_launcher-playstore.png"),
    Path("/home/todd/Documents/GitHub/voice-rpg-engine/app/src/main/res/ic_launcher-playstore.png"),
)


@dataclass(frozen=True)
class AssetTarget:
    item_id: int
    section: str  # "portraits" | "backgrounds" | "icon"
    rel_paths: tuple[str, ...]  # relative to PROJECT_ROOT; for backgrounds, stem before suffix+ext


# Exact mapping from prompt #01-#53 in new-asset-generation.md to repo asset paths.
# For section == "backgrounds", entries store the base path without variant suffix or ".jpg"
# (e.g. "app/src/main/assets/story/chappaqua_server_room" -> "..._p.jpg", "..._s.jpg", or ".jpg").
ASSET_TARGETS: dict[int, AssetTarget] = {
    # Section 1: Character Portraits (1:1 -> 1024x1024)
    1: AssetTarget(1, "portraits", ("app/src/main/assets/portraits/commander_america.jpg",)),
    2: AssetTarget(2, "portraits", ("app/src/main/assets/portraits/elon.jpg",)),
    3: AssetTarget(3, "portraits", ("app/src/main/assets/portraits/tulsi.jpg",)),
    4: AssetTarget(4, "portraits", ("app/src/main/assets/portraits/rfk.jpg",)),
    5: AssetTarget(5, "portraits", ("app/src/main/assets/portraits/vivek.jpg",)),
    6: AssetTarget(6, "portraits", ("app/src/main/assets/portraits/marco.jpg",)),
    7: AssetTarget(7, "portraits", ("app/src/main/assets/portraits/vance.jpg",)),
    8: AssetTarget(8, "portraits", ("app/src/main/assets/portraits/kash.jpg",)),
    9: AssetTarget(9, "portraits", ("app/src/main/assets/portraits/kennedy.jpg",)),
    10: AssetTarget(10, "portraits", ("app/src/main/assets/portraits/template/hill_rod_template.jpg",)),
    11: AssetTarget(11, "portraits", ("app/src/main/assets/portraits/biden.jpg",)),
    12: AssetTarget(12, "portraits", ("app/src/main/assets/portraits/kamala.jpg",)),
    13: AssetTarget(13, "portraits", ("app/src/main/assets/portraits/obama.jpg",)),
    14: AssetTarget(14, "portraits", ("app/src/main/assets/portraits/pelosi.jpg",)),
    15: AssetTarget(15, "portraits", ("app/src/main/assets/portraits/jeb.jpg",)),
    16: AssetTarget(
        16,
        "portraits",
        (
            "app/src/main/assets/portraits/spicy_sean.jpg",
            "app/src/main/assets/portraits/template/spicy_sean_template.jpg",
        ),
    ),
    17: AssetTarget(
        17,
        "portraits",
        (
            "app/src/main/assets/portraits/mooch.jpg",
            "app/src/main/assets/portraits/template/mooch_template.jpg",
        ),
    ),
    18: AssetTarget(
        18,
        "portraits",
        (
            "app/src/main/assets/portraits/rudy_ghoul.jpg",
            "app/src/main/assets/portraits/template/rudy_ghoul_template.jpg",
        ),
    ),
    19: AssetTarget(
        19,
        "portraits",
        (
            "app/src/main/assets/portraits/billy_bus.jpg",
            "app/src/main/assets/portraits/template/billy_bus_template.jpg",
        ),
    ),
    20: AssetTarget(
        20,
        "portraits",
        (
            "app/src/main/assets/portraits/comey.jpg",
            "app/src/main/assets/portraits/template/comey_template.jpg",
        ),
    ),
    21: AssetTarget(
        21,
        "portraits",
        (
            "app/src/main/assets/portraits/zach_ferns.jpg",
            "app/src/main/assets/portraits/template/zach_ferns_template.jpg",
        ),
    ),
    22: AssetTarget(
        22,
        "portraits",
        (
            "app/src/main/assets/portraits/mamdani.jpg",
            "app/src/main/assets/portraits/template/mamdani_template.jpg",
        ),
    ),
    23: AssetTarget(
        23,
        "portraits",
        (
            "app/src/main/assets/portraits/mayor_swagger.jpg",
            "app/src/main/assets/portraits/template/mayor_swagger_template.jpg",
        ),
    ),
    24: AssetTarget(
        24,
        "portraits",
        (
            "app/src/main/assets/portraits/kayleigh.jpg",
            "app/src/main/assets/portraits/narrator_kayleigh.jpg",
        ),
    ),
    25: AssetTarget(
        25,
        "portraits",
        (
            "app/src/main/assets/portraits/karoline.jpg",
            "app/src/main/assets/portraits/narrator_karoline.jpg",
        ),
    ),
    26: AssetTarget(
        26,
        "portraits",
        (
            "app/src/main/assets/portraits/psaki.jpg",
            "app/src/main/assets/portraits/narrator_psaki.jpg",
            "app/src/main/assets/portraits/template/narrator_psaki_template.jpg",
        ),
    ),
    27: AssetTarget(
        27,
        "portraits",
        (
            "app/src/main/assets/portraits/kjp.jpg",
            "app/src/main/assets/portraits/narrator_kjp.jpg",
            "app/src/main/assets/portraits/template/narrator_kjp_template.jpg",
        ),
    ),
    28: AssetTarget(
        28,
        "portraits",
        (
            "app/src/main/assets/portraits/hulk.jpg",
            "app/src/main/assets/portraits/narrator_hulk.jpg",
        ),
    ),
    # Section 2: Story Backgrounds (#29-#52)
    29: AssetTarget(29, "backgrounds", ("app/src/main/assets/story/chappaqua_server_room",)),
    30: AssetTarget(30, "backgrounds", ("app/src/main/assets/story/access_bus",)),
    31: AssetTarget(31, "backgrounds", ("app/src/main/assets/story/bronx_subway_turnstile",)),
    32: AssetTarget(32, "backgrounds", ("app/src/main/assets/story/breakfast_club_studio",)),
    33: AssetTarget(33, "backgrounds", ("app/src/main/assets/story/javits_glass_ceiling",)),
    34: AssetTarget(34, "backgrounds", ("app/src/main/assets/story/between_two_ferns",)),
    35: AssetTarget(35, "backgrounds", ("app/src/main/assets/story/trump_tower_taco_bowl",)),
    36: AssetTarget(36, "backgrounds", ("app/src/main/assets/story/spicer_bushes",)),
    37: AssetTarget(37, "backgrounds", ("app/src/main/assets/story/sharpie_hurricane",)),
    38: AssetTarget(38, "backgrounds", ("app/src/main/assets/story/subzero_kitchen",)),
    39: AssetTarget(39, "backgrounds", ("app/src/main/assets/story/four_seasons_landscaping",)),
    40: AssetTarget(40, "backgrounds", ("app/src/main/assets/story/basement_bunker",)),
    41: AssetTarget(41, "backgrounds", ("app/src/main/assets/story/rehoboth_bike_trail",)),
    42: AssetTarget(42, "backgrounds", ("app/src/main/assets/story/baskin_robbins_portland",)),
    43: AssetTarget(43, "backgrounds", ("app/src/main/assets/story/fulton_county_booking",)),
    44: AssetTarget(44, "backgrounds", ("app/src/main/assets/story/mcdonalds_fry_station",)),
    45: AssetTarget(45, "backgrounds", ("app/src/main/assets/story/holt_donut_shop",)),
    46: AssetTarget(46, "backgrounds", ("app/src/main/assets/story/chapati_food_truck",)),
    47: AssetTarget(47, "backgrounds", ("app/src/main/assets/story/butler_fairgrounds",)),
    48: AssetTarget(48, "backgrounds", ("app/src/main/assets/story/steel_ear_miracle",)),
    49: AssetTarget(49, "backgrounds", ("app/src/main/assets/story/golden_inauguration",)),
    50: AssetTarget(50, "backgrounds", ("app/src/main/assets/story/venn_fog",)),
    51: AssetTarget(51, "backgrounds", ("app/src/main/assets/story/coconut_grove",)),
    52: AssetTarget(52, "backgrounds", ("app/src/main/assets/story/unburdened_throne",)),
    # Section 3: Official App Icon (#53)
    53: AssetTarget(53, "icon", ("app/src/main/res/drawable/app_icon_art.jpg",)),
}

BG_VARIANTS = {
    "portrait": {"suffix": "_p", "width": 720, "height": 1280},
    "square": {"suffix": "_s", "width": 1024, "height": 1024},
    "landscape": {"suffix": "", "width": 1376, "height": 768},
}


@dataclass(frozen=True)
class PromptEntry:
    item_id: int
    title: str
    raw_prompt: str


def maybe_reexec_studio_venv() -> None:
    """Re-exec inside graphical-studio's ComfyUI venv unless --dry-run is requested."""
    if "--dry-run" in sys.argv or "-h" in sys.argv or "--help" in sys.argv:
        return
    if not STUDIO_VENV_PY.is_file():
        return
    if Path(sys.executable).resolve() == STUDIO_VENV_PY.resolve():
        return
    os.execv(str(STUDIO_VENV_PY), [str(STUDIO_VENV_PY), *sys.argv])


def parse_prompt_doc(doc_path: Path) -> list[PromptEntry]:
    """Parse numbered ### XX. Title + ```text ... ``` prompt blocks from new-asset-generation.md."""
    text = doc_path.read_text(encoding="utf-8")
    pattern = re.compile(
        r"^###\s+(\d+)\.\s+([^\n]+?)\s*\n```text\s*\n(.*?)\n```",
        re.MULTILINE | re.DOTALL,
    )
    entries: list[PromptEntry] = []
    for match in pattern.finditer(text):
        item_id = int(match.group(1))
        title = match.group(2).strip()
        raw_prompt = " ".join(match.group(3).split())
        entries.append(PromptEntry(item_id=item_id, title=title, raw_prompt=raw_prompt))
    entries.sort(key=lambda e: e.item_id)
    if not entries:
        raise RuntimeError(f"No prompts parsed from {doc_path}")
    return entries


def adapt_prompt_for_klein(raw_prompt: str, section: str, bg_variant: str) -> str:
    """Adapt Midjourney-style prompt paragraphs into clean natural language for FLUX.2 Klein."""
    # Remove Midjourney --ar flags (including inline `--ar 1:1.` in #53)
    cleaned = re.sub(r"\s*--ar\s+\d+:\d+\.?", "", raw_prompt).strip()
    cleaned = re.sub(r"\s{2,}", " ", cleaned)
    cleaned = cleaned.rstrip(" ,")

    if section != "backgrounds":
        return cleaned

    if bg_variant == "portrait":
        cleaned = re.sub(r"\bwide establishing shot\b", "vertical portrait establishing shot", cleaned, flags=re.IGNORECASE)
        cleaned = re.sub(r"\bpanoramic\b", "vertical", cleaned, flags=re.IGNORECASE)
        return (
            f"{cleaned}, vertical 9:16 portrait composition, tall architectural framing "
            "with sky or ceiling space above and foreground detail below, suited for mobile portrait screen"
        )
    if bg_variant == "square":
        cleaned = re.sub(r"\bwide establishing shot\b", "balanced square establishing shot", cleaned, flags=re.IGNORECASE)
        return f"{cleaned}, balanced 1:1 square composition suited for unfolded tablet screen"
    return cleaned


def resolve_destinations(target: AssetTarget, bg_variant: str, staging_dir: Path | None) -> list[Path]:
    """Resolve destination paths for an item, applying _p/_s suffix for backgrounds."""
    if target.section == "backgrounds":
        suffix = BG_VARIANTS[bg_variant]["suffix"]
        rel_files = [f"{stem}{suffix}.jpg" for stem in target.rel_paths]
    else:
        rel_files = list(target.rel_paths)

    if staging_dir is not None:
        return [staging_dir / rel for rel in rel_files]
    return [PROJECT_ROOT / rel for rel in rel_files]


def resolve_dimensions(target: AssetTarget, bg_variant: str) -> tuple[int, int]:
    if target.section == "backgrounds":
        spec = BG_VARIANTS[bg_variant]
        return spec["width"], spec["height"]
    return 1024, 1024


def stop_competing_gpu_servers() -> None:
    """Stop llama-server if running so all 8 GB VRAM is available for FLUX.2 Klein."""
    try:
        res = subprocess.run(
            ["pkill", "-f", "/home/todd/ai/llama.cpp/build/bin/llama-server"],
            capture_output=True,
            text=True,
            check=False,
        )
        if res.returncode == 0:
            print("[VRAM] Stopped llama-server to free GPU VRAM.", flush=True)
            time.sleep(1.0)
    except Exception:
        pass


def pixelate_2x(img, cell: int = 2, colors: int = 48):
    """Apply crisp 2x pixelation matching graphical-studio's generate_asset.py pipeline."""
    from PIL import Image

    if cell <= 1:
        return img
    small_w = max(1, img.width // cell)
    small_h = max(1, img.height // cell)
    small = img.resize((small_w, small_h), Image.Resampling.BOX)
    if colors > 0:
        small = small.quantize(colors=colors, method=Image.Quantize.MEDIANCUT).convert(
            img.mode if img.mode == "RGBA" else "RGB"
        )
    out = small.resize((small_w * cell, small_h * cell), Image.Resampling.NEAREST)
    if out.size != img.size:
        out = out.resize(img.size, Image.Resampling.NEAREST)
    return out


def init_comfy():
    """Initialize ComfyUI runtime and return (nodes, gguf_nodes, comfy_sample, comfy_samplers, get_schedule)."""
    os.environ.setdefault("HF_HOME", str(STUDIO_ROOT / ".cache" / "huggingface"))
    os.environ.setdefault("TORCH_HOME", str(STUDIO_ROOT / ".cache" / "torch"))
    os.environ.setdefault("TMPDIR", str(STUDIO_ROOT / ".cache" / "tmp"))
    os.environ.setdefault("PYTORCH_CUDA_ALLOC_CONF", "expandable_segments:True")

    for p in (str(STUDIO_ROOT), str(COMFY_DIR)):
        if p not in sys.path:
            sys.path.insert(0, p)

    # Clear CLI args so ComfyUI's arg parser doesn't reject our script's flags
    sys.argv = [sys.argv[0], "--reserve-vram", "0.6"]
    os.chdir(COMFY_DIR)

    import comfy.options
    comfy.options.enable_args_parsing()

    import comfy.model_management
    import comfy.sample
    import comfy.samplers
    import nodes
    from comfy_extras.nodes_flux import get_schedule

    gguf_dir = COMFY_DIR / "custom_nodes" / "ComfyUI-GGUF"
    if not (gguf_dir / "__init__.py").is_file():
        raise SystemExit(f"Missing ComfyUI-GGUF at {gguf_dir}")
    spec = importlib.util.spec_from_file_location(
        "comfyui_gguf",
        str(gguf_dir / "__init__.py"),
        submodule_search_locations=[str(gguf_dir)],
    )
    mod = importlib.util.module_from_spec(spec)
    sys.modules["comfyui_gguf"] = mod
    spec.loader.exec_module(mod)

    return nodes, mod.NODE_CLASS_MAPPINGS, comfy.sample, comfy.samplers, get_schedule, comfy.model_management


def free_vram(model_management) -> None:
    import torch

    gc.collect()
    model_management.unload_all_models()
    model_management.soft_empty_cache()
    if torch.cuda.is_available():
        torch.cuda.empty_cache()


def save_atomic_jpeg(img, destinations: list[Path], quality: int = 94) -> None:
    """Write JPEG to a staging temp file, then atomically move/copy to every destination."""
    rgb = img.convert("RGB")
    first_dest = destinations[0]
    first_dest.parent.mkdir(parents=True, exist_ok=True)
    fd, tmp_name = tempfile.mkstemp(prefix=".klein_", suffix=".jpg", dir=str(first_dest.parent))
    os.close(fd)
    tmp_path = Path(tmp_name)
    try:
        rgb.save(tmp_path, "JPEG", quality=quality, optimize=True)
        os.replace(tmp_path, first_dest)
    except Exception:
        if tmp_path.exists():
            tmp_path.unlink()
        raise

    for extra_dest in destinations[1:]:
        extra_dest.parent.mkdir(parents=True, exist_ok=True)
        fd2, tmp2_name = tempfile.mkstemp(prefix=".klein_", suffix=".jpg", dir=str(extra_dest.parent))
        os.close(fd2)
        tmp2_path = Path(tmp2_name)
        try:
            shutil.copy2(first_dest, tmp2_path)
            os.replace(tmp2_path, extra_dest)
        except Exception:
            if tmp2_path.exists():
                tmp2_path.unlink()
            raise


def update_app_icon_badges(base_icon_path: Path) -> None:
    """Re-run badge_app_icon helpers so mipmaps and Play Store icon match the newly generated app_icon_art.jpg."""
    engine_icon = next((p for p in ENGINE_BADGE_CANDIDATES if p.is_file()), None)
    if engine_icon is None:
        print("[Icon] Skipping mipmap badging: voice-rpg-engine Play Store icon not found.", flush=True)
        return
    badge_script = PROJECT_ROOT / "scripts" / "badge_app_icon.py"
    spec = importlib.util.spec_from_file_location("badge_app_icon", str(badge_script))
    mod = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(mod)

    playstore_out = RES_DIR / "ic_launcher-playstore.png"
    result = mod.apply_badge_to_icon(str(base_icon_path), str(engine_icon), str(playstore_out))
    mod.generate_mipmaps(result, str(RES_DIR))
    mod.generate_adaptive_foregrounds(result, str(RES_DIR))


def parse_args(argv=None):
    p = argparse.ArgumentParser(description="Generate new-asset-generation.md assets via FLUX.2 Klein 4B + 2x pixelation.")
    p.add_argument(
        "--section",
        choices=["backgrounds", "all", "portraits", "icon"],
        default="backgrounds",
        help="Which section of new-asset-generation.md to generate (default: backgrounds)",
    )
    p.add_argument(
        "--bg-variant",
        choices=sorted(BG_VARIANTS),
        default="portrait",
        help="Background target aspect/suffix: portrait (_p, 720x1280), square (_s, 1024x1024), or landscape (1376x768)",
    )
    p.add_argument("--ids", default="", help="Comma-separated item numbers to run (e.g. 1,29,30)")
    p.add_argument("--steps", type=int, default=4, help="FLUX.2 Klein sampling steps (default: 4)")
    p.add_argument("--cfg", type=float, default=1.0, help="CFG scale (default: 1.0)")
    p.add_argument("--sampler", default="euler", help="Sampler name (default: euler)")
    p.add_argument("--seed-base", type=int, default=42026, help="Base seed; each asset uses seed_base + item_id")
    p.add_argument("--pixelate", type=int, default=2, help="Pixel cell size (default: 2 for 2x pixelation)")
    p.add_argument("--colors", type=int, default=48, help="Palette size for quantization (0 disables quantization; default: 48)")
    p.add_argument("--quality", type=int, default=94, help="JPEG quality (default: 94)")
    p.add_argument("--skip-existing", action="store_true", help="Skip items whose primary destination already exists")
    p.add_argument("--keep-raw", default="", help="Optional directory to also save pre-pixelation PNGs")
    p.add_argument("--staging-only", action="store_true", help="Write into .klein_staging/ instead of overwriting repo assets")
    p.add_argument("--no-badge-icon", action="store_true", help="Do not regenerate Android mipmaps after #53")
    p.add_argument("--dry-run", action="store_true", help="Print resolved jobs, sizes, prompts, and paths without running GPU")
    return p.parse_args(argv)


def main(argv=None) -> int:
    maybe_reexec_studio_venv()
    args = parse_args(argv)

    entries = parse_prompt_doc(PROMPT_DOC)
    id_filter = {int(x.strip()) for x in args.ids.split(",") if x.strip().isdigit()} if args.ids else None

    jobs = []
    staging_dir = (PROJECT_ROOT / ".klein_staging") if args.staging_only else None
    raw_dir = Path(args.keep_raw).resolve() if args.keep_raw else None

    for entry in entries:
        target = ASSET_TARGETS.get(entry.item_id)
        if target is None:
            raise RuntimeError(f"Unmapped item_id #{entry.item_id} ({entry.title}) in {PROMPT_DOC}")
        if args.section != "all" and target.section != args.section:
            continue
        if id_filter is not None and entry.item_id not in id_filter:
            continue

        dests = resolve_destinations(target, args.bg_variant, staging_dir)
        if args.skip_existing and all(d.is_file() for d in dests):
            print(f"[SKIP #{entry.item_id:02d}] Exists: {dests[0].relative_to(PROJECT_ROOT)}", flush=True)
            continue

        w, h = resolve_dimensions(target, args.bg_variant)
        if w % 16 or h % 16:
            raise SystemExit(f"Dimensions {w}x{h} for #{entry.item_id} must be multiples of 16 for FLUX.2 Klein")
        prompt = adapt_prompt_for_klein(entry.raw_prompt, target.section, args.bg_variant)
        jobs.append((entry, target, dests, w, h, prompt))

    if not jobs:
        print("No matching jobs to run.")
        return 0

    print(
        f"Prepared {len(jobs)} Klein job(s) | bg_variant={args.bg_variant} | pixelate={args.pixelate}x | colors={args.colors}",
        flush=True,
    )

    if args.dry_run:
        for entry, target, dests, w, h, prompt in jobs:
            rel_list = ", ".join(str(d.relative_to(PROJECT_ROOT)) for d in dests)
            print(f"\n#{entry.item_id:02d} [{target.section}] {w}x{h} -> {rel_list}")
            print(f"  title:  {entry.title}")
            print(f"  prompt: {prompt[:220]}{'...' if len(prompt) > 220 else ''}")
        return 0

    stop_competing_gpu_servers()

    import numpy as np
    import torch
    from PIL import Image

    nodes, gguf_nodes, comfy_sample, comfy_samplers, get_schedule, model_management = init_comfy()

    # Phase 1: Encode all prompts once with qwen_3_4b, keeping tiny conditioning tensors in CPU RAM.
    # This avoids reloading the 8 GB text encoder + 4.3 GB Klein UNet 53 times on an 8 GB VRAM GPU.
    print(f"[Phase 1/2] Loading text encoder ({KLEIN_TE}) to encode {len(jobs)} prompt(s)...", flush=True)
    clip = nodes.CLIPLoader().load_clip(KLEIN_TE, type="flux2")[0]
    enc = nodes.CLIPTextEncode()
    encoded_jobs = []
    with torch.inference_mode():
        empty_neg = enc.encode(clip, "")[0]
        for entry, target, dests, w, h, prompt in jobs:
            pos = enc.encode(clip, prompt)[0]
            encoded_jobs.append((entry, target, dests, w, h, pos))
    del clip, enc
    free_vram(model_management)

    # Phase 2: Load Klein UNet + VAE once and render/pixelate/move each asset one after another.
    print(f"[Phase 2/2] Loading {KLEIN_UNET} + {KLEIN_VAE}...", flush=True)
    model = gguf_nodes["UnetLoaderGGUF"]().load_unet(KLEIN_UNET)[0]
    vae = nodes.VAELoader().load_vae(KLEIN_VAE)[0]

    t_batch = time.time()
    for idx, (entry, target, dests, w, h, pos) in enumerate(encoded_jobs, start=1):
        t0 = time.time()
        seed = args.seed_base + entry.item_id
        print(f"[{idx}/{len(encoded_jobs)}] Rendering #{entry.item_id:02d} ({entry.title}) @ {w}x{h} seed={seed}...", flush=True)
        with torch.inference_mode():
            latent = torch.zeros([1, 128, h // 16, w // 16])
            sigmas = get_schedule(args.steps, round(w * h / 256))
            lat = comfy_sample.fix_empty_latent_channels(model, latent)
            noise = comfy_sample.prepare_noise(lat, seed)
            out = comfy_sample.sample_custom(
                model,
                noise,
                args.cfg,
                comfy_samplers.sampler_object(args.sampler),
                sigmas,
                pos,
                empty_neg,
                lat,
                seed=seed,
            )
            decoded = nodes.VAEDecode().decode(vae=vae, samples={"samples": out})[0]
            while decoded.dim() > 4:
                decoded = decoded[0]
            arr = (np.clip(decoded[0].detach().float().cpu().numpy(), 0.0, 1.0) * 255.0).round().astype(np.uint8)
        del latent, lat, noise, out, decoded

        img = Image.fromarray(arr)
        if raw_dir is not None:
            raw_dir.mkdir(parents=True, exist_ok=True)
            img.save(raw_dir / f"{entry.item_id:02d}_{dests[0].stem}.png", "PNG", optimize=True)

        img = pixelate_2x(img, cell=args.pixelate, colors=args.colors)
        save_atomic_jpeg(img, dests, quality=args.quality)
        rel_primary = dests[0].relative_to(PROJECT_ROOT)
        print(f"  -> Saved {rel_primary} (+{len(dests) - 1} alias copy/copies) in {time.time() - t0:.1f}s", flush=True)

        if target.section == "icon" and not args.staging_only and not args.no_badge_icon:
            update_app_icon_badges(dests[0])

    del model, vae, empty_neg, encoded_jobs
    free_vram(model_management)
    print(f"[Done] Generated {len(jobs)} asset(s) in {(time.time() - t_batch) / 60:.1f} min.", flush=True)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
