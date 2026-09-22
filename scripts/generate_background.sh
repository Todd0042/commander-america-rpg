#!/usr/bin/env bash
# Quick launcher script for generating Gemini 16/32-bit JRPG backgrounds

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ROOT_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"

if [ -z "$GEMINI_API_KEY" ] && [ -z "$GOOGLE_API_KEY" ]; then
    echo "================================================================="
    echo " TIP: You can set your Gemini API key beforehand by running:"
    echo "   export GEMINI_API_KEY=\"your_key_here\""
    echo "================================================================="
fi

python3 "$SCRIPT_DIR/generate_gemini_backgrounds.py" "$@"
