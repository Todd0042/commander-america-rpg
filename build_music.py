#!/usr/bin/env python3
"""
Synthesizes powerful, high-energy, patriotic 'America Wins Everything' background music (BGM)
tracks for Commander America RPG.
Uses multi-track polyphonic synthesis:
  - Fanfare Trumpets & French Horns
  - Arena Rock Distorted Power Chords
  - Driving Basslines
  - Marching & Rock Drum Kits (Punchy Kicks, Snare Cracks, Hi-Hats, Crash Cymbals, Timpani)
Encodes to Vorbis OGG at 44.1 kHz via ffmpeg.
"""

import os
import subprocess
import wave
import numpy as np

SAMPLE_RATE = 44100
OUTPUT_DIR = "app/src/main/assets/audio/music"

def note_freq(note_name):
    """Converts note name like 'C4', 'A#3', 'Eb5' to frequency in Hz."""
    notes = {'C': 0, 'C#': 1, 'Db': 1, 'D': 2, 'D#': 3, 'Eb': 3, 'E': 4,
             'F': 5, 'F#': 6, 'Gb': 6, 'G': 7, 'G#': 8, 'Ab': 8, 'A': 9,
             'A#': 10, 'Bb': 10, 'B': 11}
    name = note_name[:-1]
    octave = int(note_name[-1])
    semitone = notes[name] + (octave - 4) * 12 - 9  # relative to A4 (440Hz)
    return 440.0 * (2.0 ** (semitone / 12.0))

def synth_brass_note(freq, duration, velocity=0.8):
    """Synthesizes a warm, triumphant brass horn note with subtle vibrato and envelope."""
    n_samples = int(duration * SAMPLE_RATE)
    t = np.linspace(0, duration, n_samples, False)
    
    # 5 Hz vibrato
    vibrato = 1.0 + 0.008 * np.sin(2 * np.pi * 5.2 * t)
    phase = 2 * np.pi * freq * np.cumsum(vibrato) / SAMPLE_RATE
    
    # Rich brass harmonics (sawtooth-like blend of fundamental + overtones)
    wave_data = (
        1.00 * np.sin(phase) +
        0.65 * np.sin(2 * phase) +
        0.45 * np.sin(3 * phase) +
        0.28 * np.sin(4 * phase) +
        0.18 * np.sin(5 * phase) +
        0.10 * np.sin(6 * phase)
    )
    
    # ADSR envelope
    attack_samples = int(min(0.04, duration * 0.2) * SAMPLE_RATE)
    decay_samples = int(min(0.06, duration * 0.2) * SAMPLE_RATE)
    release_samples = int(min(0.08, duration * 0.3) * SAMPLE_RATE)
    sustain_samples = max(0, n_samples - attack_samples - decay_samples - release_samples)
    
    env = np.ones(n_samples, dtype=np.float32)
    if attack_samples > 0:
        env[:attack_samples] = np.linspace(0, 1.1, attack_samples)
    if decay_samples > 0:
        env[attack_samples:attack_samples+decay_samples] = np.linspace(1.1, 0.85, decay_samples)
    env[attack_samples+decay_samples:attack_samples+decay_samples+sustain_samples] = 0.85
    if release_samples > 0:
        env[-release_samples:] = np.linspace(0.85, 0, release_samples)
    
    return wave_data * env * velocity

def synth_guitar_power_chord(root_freq, duration, velocity=0.7):
    """Synthesizes a driving rock guitar power chord (Root + 5th + Octave) with overdrive."""
    freqs = [root_freq, root_freq * 1.5, root_freq * 2.0]
    n_samples = int(duration * SAMPLE_RATE)
    t = np.linspace(0, duration, n_samples, False)
    
    chord = np.zeros(n_samples, dtype=np.float32)
    for f in freqs:
        # Detuned unison oscillators for fat arena sound
        s1 = np.sin(2 * np.pi * f * t)
        s2 = np.sin(2 * np.pi * (f * 1.003) * t)
        s3 = np.sin(2 * np.pi * (f * 0.997) * t)
        saw = 0.5 * (s1 + s2 + s3) + 0.3 * np.sin(4 * np.pi * f * t)
        chord += saw
    
    # Tube overdrive / distortion saturation
    distorted = np.tanh(chord * 2.2)
    
    # Decay envelope
    env = np.exp(-t * (1.5 / duration))
    return distorted * env * velocity

def synth_bass_note(freq, duration, velocity=0.85):
    """Synthesizes punchy electric bass."""
    n_samples = int(duration * SAMPLE_RATE)
    t = np.linspace(0, duration, n_samples, False)
    
    # Fundamental + 2nd harmonic + slight 3rd
    sig = (np.sin(2 * np.pi * freq * t) +
           0.45 * np.sin(4 * np.pi * freq * t) +
           0.15 * np.sin(6 * np.pi * freq * t))
    
    # Punchy attack envelope
    env = np.exp(-t * (2.8 / duration))
    return sig * env * velocity

def synth_kick_drum(duration=0.25):
    """Heavy arena kick drum."""
    n_samples = int(duration * SAMPLE_RATE)
    t = np.linspace(0, duration, n_samples, False)
    # Pitch drop from 140 Hz to 45 Hz
    pitch = 45 + (140 - 45) * np.exp(-t * 28)
    phase = 2 * np.pi * np.cumsum(pitch) / SAMPLE_RATE
    sig = np.sin(phase) + 0.3 * np.tanh(np.sin(phase) * 3)
    env = np.exp(-t * 18)
    return sig * env * 0.95

def synth_snare_drum(duration=0.22):
    """Crisp, punchy rock/march snare."""
    n_samples = int(duration * SAMPLE_RATE)
    t = np.linspace(0, duration, n_samples, False)
    # Tonal body (185 Hz)
    tone = np.sin(2 * np.pi * 185 * t) * np.exp(-t * 22)
    # Noise snap
    noise = np.random.uniform(-1, 1, n_samples) * np.exp(-t * 16)
    sig = 0.4 * tone + 0.6 * noise
    return sig * 0.9

def synth_hihat(duration=0.08, open_hat=False):
    """Hi-hat cymbal tap."""
    dur = 0.25 if open_hat else duration
    n_samples = int(dur * SAMPLE_RATE)
    t = np.linspace(0, dur, n_samples, False)
    decay = 12 if open_hat else 45
    noise = np.random.uniform(-1, 1, n_samples) * np.exp(-t * decay)
    return noise * 0.35

def synth_timpani(freq=90, duration=0.8):
    """Deep orchestral timpani roll / hit."""
    n_samples = int(duration * SAMPLE_RATE)
    t = np.linspace(0, duration, n_samples, False)
    pitch = freq + 30 * np.exp(-t * 30)
    sig = np.sin(2 * np.pi * np.cumsum(pitch) / SAMPLE_RATE)
    env = np.exp(-t * 3.5)
    return sig * env * 0.85

def synth_crash_cymbal(duration=1.4):
    """Dramatic crash cymbal."""
    n_samples = int(duration * SAMPLE_RATE)
    t = np.linspace(0, duration, n_samples, False)
    noise = np.random.uniform(-1, 1, n_samples) * np.exp(-t * 3.2)
    return noise * 0.65

def overlay_sound(track, sound, start_time):
    """Overlays a sound array onto the target track at start_time in seconds."""
    start_idx = int(start_time * SAMPLE_RATE)
    end_idx = start_idx + len(sound)
    if end_idx > len(track):
        sound = sound[:len(track) - start_idx]
        end_idx = len(track)
    track[start_idx:end_idx] += sound

def export_to_ogg(track, output_ogg_path):
    """Normalizes and encodes track to Vorbis OGG via ffmpeg."""
    # Peak normalization to -1.0 dB
    peak = np.max(np.abs(track))
    if peak > 0:
        track = track / peak * 0.88
    
    # 16-bit PCM integer conversion
    pcm_data = (track * 32767).astype(np.int16)
    
    wav_path = output_ogg_path.replace('.ogg', '.wav')
    with wave.open(wav_path, 'wb') as wf:
        wf.setnchannels(1)  # Mono
        wf.setsampwidth(2)  # 16-bit
        wf.setframerate(SAMPLE_RATE)
        wf.writeframes(pcm_data.tobytes())
    
    # Encode to OGG Vorbis
    cmd = [
        "ffmpeg", "-y", "-i", wav_path,
        "-c:a", "libvorbis", "-b:a", "96k",
        output_ogg_path
    ]
    subprocess.run(cmd, stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL, check=True)
    os.remove(wav_path)
    print(f"  [OK] Rendered {os.path.basename(output_ogg_path)} ({len(track)/SAMPLE_RATE:.1f}s)")

# -----------------------------------------------------------------------------
# 1. BGM TITLE: Patriotic Fanfare / Stars & Stripes March
# -----------------------------------------------------------------------------
def build_title_theme():
    bpm = 120
    beat = 60.0 / bpm
    total_beats = 32  # 16.0 seconds loop
    duration = total_beats * beat
    track = np.zeros(int(duration * SAMPLE_RATE), dtype=np.float32)
    
    # Brass Fanfare Melody (Triumphant, Soaring Bb Major)
    # Bb4, D5, F5, Bb5 fanfare cadence
    melody = [
        # Measure 1
        ("Bb4", 0.0, beat*0.75), ("D5", beat*0.75, beat*0.25), ("F5", beat*1.0, beat*1.0),
        ("Bb5", beat*2.0, beat*1.5), ("F5", beat*3.5, beat*0.5),
        # Measure 2
        ("G5", beat*4.0, beat*1.0), ("F5", beat*5.0, beat*1.0),
        ("Eb5", beat*6.0, beat*0.75), ("D5", beat*6.75, beat*0.25), ("C5", beat*7.0, beat*1.0),
        # Measure 3
        ("F4", beat*8.0, beat*0.75), ("A4", beat*8.75, beat*0.25), ("C5", beat*9.0, beat*1.0),
        ("F5", beat*10.0, beat*1.5), ("D5", beat*11.5, beat*0.5),
        # Measure 4
        ("Bb4", beat*12.0, beat*2.0), ("D5", beat*14.0, beat*1.0), ("F5", beat*15.0, beat*1.0),
        # Repeat with climax
        # Measure 5
        ("Bb5", beat*16.0, beat*1.5), ("A5", beat*17.5, beat*0.5),
        ("G5", beat*18.0, beat*1.0), ("Bb5", beat*19.0, beat*1.0),
        # Measure 6
        ("F5", beat*20.0, beat*2.0), ("D5", beat*22.0, beat*1.0), ("Bb4", beat*23.0, beat*1.0),
        # Measure 7
        ("C5", beat*24.0, beat*1.0), ("D5", beat*25.0, beat*1.0),
        ("Eb5", beat*26.0, beat*1.0), ("F5", beat*27.0, beat*1.0),
        # Measure 8: Grand Climax
        ("Bb5", beat*28.0, beat*3.5)
    ]
    
    for note, start, dur in melody:
        f = note_freq(note)
        # Lead trumpet
        lead = synth_brass_note(f, dur, velocity=0.85)
        overlay_sound(track, lead, start)
        # Harmony horn (major third/sixth below)
        harmony = synth_brass_note(f * 0.75, dur, velocity=0.45)
        overlay_sound(track, harmony, start)
    
    # Marching drums & timpani
    for b in range(total_beats):
        t = b * beat
        # Bass drum on beats 1 and 3
        if b % 2 == 0:
            overlay_sound(track, synth_kick_drum(beat), t)
        # Marching snare cadence
        overlay_sound(track, synth_snare_drum(beat*0.4), t + (beat * 0.5 if b % 2 == 1 else 0))
        # Snare roll on beat 4 of phrases
        if (b + 1) % 4 == 0:
            for s in range(4):
                overlay_sound(track, synth_snare_drum(beat*0.25)*0.5, t + s * beat * 0.25)
        # Hi-hat on every eighth note
        overlay_sound(track, synth_hihat(), t)
        overlay_sound(track, synth_hihat(), t + beat * 0.5)
    
    # Timpani & Cymbals on accents
    for bar in [0, 4, 8, 12, 16, 20, 24, 28]:
        overlay_sound(track, synth_crash_cymbal(), bar * beat)
        overlay_sound(track, synth_timpani(82), bar * beat)
    
    return track

# -----------------------------------------------------------------------------
# 2. BGM COMBAT: High-Octane Arena Rock & Victory Battle March
# -----------------------------------------------------------------------------
def build_combat_theme():
    bpm = 132
    beat = 60.0 / bpm
    total_beats = 32  # ~14.5 seconds loop
    duration = total_beats * beat
    track = np.zeros(int(duration * SAMPLE_RATE), dtype=np.float32)
    
    # Driving Heavy Rock Rhythm: E Power Chords & A Power Chords
    chord_progression = [
        ("E2", 0, beat*4), ("G2", beat*4, beat*2), ("A2", beat*6, beat*2),
        ("E2", beat*8, beat*4), ("D2", beat*12, beat*2), ("B2", beat*14, beat*2),
        ("E2", beat*16, beat*4), ("G2", beat*20, beat*2), ("A2", beat*22, beat*2),
        ("C3", beat*24, beat*2), ("D3", beat*26, beat*2), ("E3", beat*28, beat*4)
    ]
    
    for note, start, dur in chord_progression:
        f = note_freq(note)
        # Chugging rhythm guitar on eighth notes
        n_hits = int(dur / (beat * 0.5))
        for h in range(n_hits):
            overlay_sound(track, synth_guitar_power_chord(f, beat*0.45, velocity=0.75), start + h * beat * 0.5)
        # Deep bass guitar backing
        overlay_sound(track, synth_bass_note(f, dur, velocity=0.85), start)
    
    # Heroic Victory Lead Riff (High energy brass / electric lead)
    riff = [
        ("E4", 0.0, beat*0.5), ("E4", beat*0.5, beat*0.5), ("G4", beat*1.0, beat*1.0),
        ("A4", beat*2.0, beat*1.5), ("B4", beat*3.5, beat*0.5),
        ("E5", beat*4.0, beat*2.0), ("D5", beat*6.0, beat*1.0), ("B4", beat*7.0, beat*1.0),
        ("A4", beat*8.0, beat*1.5), ("G4", beat*9.5, beat*0.5), ("E4", beat*10.0, beat*2.0),
        ("G4", beat*12.0, beat*1.0), ("A4", beat*13.0, beat*1.0), ("B4", beat*14.0, beat*2.0),
        # Climax
        ("E5", beat*16.0, beat*1.0), ("G5", beat*17.0, beat*1.0), ("E5", beat*18.0, beat*1.5),
        ("D5", beat*19.5, beat*0.5), ("B4", beat*20.0, beat*2.0),
        ("C5", beat*22.0, beat*1.0), ("D5", beat*23.0, beat*1.0),
        ("E5", beat*24.0, beat*2.0), ("G5", beat*26.0, beat*2.0),
        ("E5", beat*28.0, beat*4.0)
    ]
    
    for note, start, dur in riff:
        f = note_freq(note)
        overlay_sound(track, synth_brass_note(f, dur, velocity=0.9), start)
        overlay_sound(track, synth_brass_note(f*0.5, dur, velocity=0.6), start) # Sub octave punch
    
    # Heavy Arena Drums: Four on the floor + massive snare backbeat
    for b in range(total_beats):
        t = b * beat
        # Driving Kick on 1, 2, 3, 4
        overlay_sound(track, synth_kick_drum(beat*0.8), t)
        # Huge Snare on 2 and 4
        if b % 2 == 1:
            overlay_sound(track, synth_snare_drum(beat*0.6), t)
        # Sizzling Hi-hat eighth notes
        overlay_sound(track, synth_hihat(open_hat=(b % 2 == 1)), t)
        overlay_sound(track, synth_hihat(), t + beat * 0.5)
    
    # Crash on bars 1, 9, 17, 25
    for bar in [0, 8, 16, 24]:
        overlay_sound(track, synth_crash_cymbal(), bar * beat)
    
    return track

# -----------------------------------------------------------------------------
# 3. BGM RALLY: Stadium Arena Anthem (126 BPM, Driving, Unstoppable Win Energy)
# -----------------------------------------------------------------------------
def build_rally_theme():
    bpm = 126
    beat = 60.0 / bpm
    total_beats = 32
    duration = total_beats * beat
    track = np.zeros(int(duration * SAMPLE_RATE), dtype=np.float32)
    
    # Celebratory Major Progression: C - G - Am - F
    prog = [
        ("C3", 0, beat*8),
        ("G2", beat*8, beat*8),
        ("A2", beat*16, beat*8),
        ("F2", beat*24, beat*8)
    ]
    
    for note, start, dur in prog:
        f = note_freq(note)
        # Pulsing synth bass
        for h in range(int(dur / (beat * 0.5))):
            overlay_sound(track, synth_bass_note(f, beat*0.4, velocity=0.8), start + h * beat * 0.5)
            overlay_sound(track, synth_guitar_power_chord(f*2, beat*0.4, velocity=0.55), start + h * beat * 0.5)
    
    # Upbeat stadium fanfare melody
    melody = [
        ("C5", 0.0, beat*1.0), ("E5", beat*1.0, beat*1.0), ("G5", beat*2.0, beat*1.5),
        ("E5", beat*3.5, beat*0.5), ("C5", beat*4.0, beat*2.0),
        ("D5", beat*6.0, beat*1.0), ("E5", beat*7.0, beat*1.0),
        ("D5", beat*8.0, beat*2.0), ("B4", beat*10.0, beat*1.5),
        ("G4", beat*11.5, beat*0.5), ("B4", beat*12.0, beat*2.0),
        ("C5", beat*14.0, beat*1.0), ("D5", beat*15.0, beat*1.0),
        ("E5", beat*16.0, beat*2.0), ("A5", beat*18.0, beat*1.5),
        ("G5", beat*19.5, beat*0.5), ("E5", beat*20.0, beat*2.0),
        ("F5", beat*22.0, beat*1.0), ("G5", beat*23.0, beat*1.0),
        ("A5", beat*24.0, beat*2.0), ("C6", beat*26.0, beat*2.0),
        ("C6", beat*28.0, beat*3.5)
    ]
    
    for note, start, dur in melody:
        f = note_freq(note)
        overlay_sound(track, synth_brass_note(f, dur, velocity=0.85), start)
    
    # Stadium four-on-the-floor kick + snare + open hat
    for b in range(total_beats):
        t = b * beat
        overlay_sound(track, synth_kick_drum(), t)
        if b % 2 == 1:
            overlay_sound(track, synth_snare_drum(), t)
        overlay_sound(track, synth_hihat(open_hat=(b % 2 == 1)), t + beat * 0.5)
    
    for bar in [0, 8, 16, 24]:
        overlay_sound(track, synth_crash_cymbal(), bar * beat)
    
    return track

# -----------------------------------------------------------------------------
# 4. BGM OVAL: Regal Presidential Sovereign March
# -----------------------------------------------------------------------------
def build_oval_theme():
    bpm = 104
    beat = 60.0 / bpm
    total_beats = 32
    duration = total_beats * beat
    track = np.zeros(int(duration * SAMPLE_RATE), dtype=np.float32)
    
    # Noble, stately Eb Major / Ab Major cadence
    melody = [
        ("Eb4", 0.0, beat*1.5), ("G4", beat*1.5, beat*0.5), ("Bb4", beat*2.0, beat*2.0),
        ("Eb5", beat*4.0, beat*2.0), ("D5", beat*6.0, beat*1.0), ("C5", beat*7.0, beat*1.0),
        ("Bb4", beat*8.0, beat*3.0), ("G4", beat*11.0, beat*1.0),
        ("Ab4", beat*12.0, beat*2.0), ("Bb4", beat*14.0, beat*2.0),
        ("C5", beat*16.0, beat*2.0), ("Eb5", beat*18.0, beat*1.5), ("D5", beat*19.5, beat*0.5),
        ("C5", beat*20.0, beat*2.0), ("Bb4", beat*22.0, beat*2.0),
        ("Ab4", beat*24.0, beat*2.0), ("F4", beat*26.0, beat*2.0),
        ("Eb4", beat*28.0, beat*4.0)
    ]
    
    for note, start, dur in melody:
        f = note_freq(note)
        # French horn / trumpet lead
        overlay_sound(track, synth_brass_note(f, dur, velocity=0.8), start)
        # Warm string pad harmony
        overlay_sound(track, synth_brass_note(f*0.5, dur, velocity=0.5), start)
    
    # Stately marching snare and timpani
    for b in range(total_beats):
        t = b * beat
        if b % 2 == 0:
            overlay_sound(track, synth_kick_drum(beat*0.6)*0.7, t)
            overlay_sound(track, synth_timpani(92)*0.6, t)
        # Crisp military snare taps
        overlay_sound(track, synth_snare_drum(beat*0.3)*0.5, t)
        overlay_sound(track, synth_snare_drum(beat*0.2)*0.4, t + beat*0.75)
    
    for bar in [0, 8, 16, 24]:
        overlay_sound(track, synth_crash_cymbal()*0.6, bar * beat)
    
    return track

# -----------------------------------------------------------------------------
# 5. BGM INTRIGUE: Suspenseful Espionage / Swamp Draining Thriller
# -----------------------------------------------------------------------------
def build_intrigue_theme():
    bpm = 128
    beat = 60.0 / bpm
    total_beats = 32
    duration = total_beats * beat
    track = np.zeros(int(duration * SAMPLE_RATE), dtype=np.float32)
    
    # Driving D-minor thriller bassline
    bass_notes = ["D2", "F2", "D2", "G2", "D2", "Ab2", "G2", "F2"]
    for b in range(total_beats):
        note = bass_notes[b % len(bass_notes)]
        f = note_freq(note)
        overlay_sound(track, synth_bass_note(f, beat*0.8, velocity=0.85), b * beat)
        # Ticking hi-hat
        overlay_sound(track, synth_hihat(), b * beat)
        overlay_sound(track, synth_hihat(), b * beat + beat*0.5)
        # Heavy kick on 1 and 3
        if b % 2 == 0:
            overlay_sound(track, synth_kick_drum(), b * beat)
        else:
            overlay_sound(track, synth_snare_drum()*0.8, b * beat)
    
    # Suspenseful dramatic brass stabs & declassification motives
    stabs = [
        ("D4", beat*0.0, beat*0.5), ("F4", beat*2.0, beat*0.5), ("Ab4", beat*3.0, beat*1.0),
        ("G4", beat*4.0, beat*0.5), ("F4", beat*6.0, beat*0.5), ("D4", beat*7.0, beat*1.0),
        ("D5", beat*8.0, beat*1.0), ("C5", beat*10.0, beat*1.0), ("Ab4", beat*12.0, beat*1.5),
        ("A4", beat*14.0, beat*2.0),
        ("D4", beat*16.0, beat*0.5), ("F4", beat*18.0, beat*0.5), ("Ab4", beat*19.0, beat*1.0),
        ("D5", beat*24.0, beat*2.0), ("Eb5", beat*26.0, beat*2.0), ("D5", beat*28.0, beat*3.5)
    ]
    for note, start, dur in stabs:
        f = note_freq(note)
        overlay_sound(track, synth_brass_note(f, dur, velocity=0.85), start)
    
    return track

# -----------------------------------------------------------------------------
# 6. BGM INAUGURATION: Golden Age Climax & Supreme Victory Fanfare
# -----------------------------------------------------------------------------
def build_inauguration_theme():
    bpm = 120
    beat = 60.0 / bpm
    total_beats = 32
    duration = total_beats * beat
    track = np.zeros(int(duration * SAMPLE_RATE), dtype=np.float32)
    
    # Glorious Grand Climax Fanfare (F Major / Bb Major / C Major)
    melody = [
        ("F4", 0.0, beat*1.0), ("A4", beat*1.0, beat*1.0), ("C5", beat*2.0, beat*1.5),
        ("F5", beat*3.5, beat*0.5), ("E5", beat*4.0, beat*1.0), ("D5", beat*5.0, beat*1.0),
        ("C5", beat*6.0, beat*2.0),
        ("D5", beat*8.0, beat*1.0), ("E5", beat*9.0, beat*1.0), ("F5", beat*10.0, beat*2.0),
        ("G5", beat*12.0, beat*2.0), ("C5", beat*14.0, beat*2.0),
        # Crescendo
        ("A5", beat*16.0, beat*1.5), ("G5", beat*17.5, beat*0.5), ("F5", beat*18.0, beat*1.0),
        ("E5", beat*19.0, beat*1.0), ("D5", beat*20.0, beat*2.0), ("C5", beat*22.0, beat*2.0),
        ("Bb5", beat*24.0, beat*2.0), ("A5", beat*26.0, beat*2.0),
        ("F5", beat*28.0, beat*4.0)
    ]
    
    for note, start, dur in melody:
        f = note_freq(note)
        overlay_sound(track, synth_brass_note(f, dur, velocity=0.9), start)
        overlay_sound(track, synth_brass_note(f*0.75, dur, velocity=0.6), start) # Harmonized fifth
        overlay_sound(track, synth_brass_note(f*0.5, dur, velocity=0.4), start)  # Sub octave
    
    # Triumphant Marching Drums, Timpani, Cymbals
    for b in range(total_beats):
        t = b * beat
        if b % 2 == 0:
            overlay_sound(track, synth_kick_drum(beat), t)
            overlay_sound(track, synth_timpani(87), t)
        overlay_sound(track, synth_snare_drum(beat*0.4), t)
        overlay_sound(track, synth_hihat(), t)
        overlay_sound(track, synth_hihat(), t + beat * 0.5)
    
    for bar in [0, 4, 8, 12, 16, 20, 24, 28]:
        overlay_sound(track, synth_crash_cymbal(), bar * beat)
    
    return track


def main():
    os.makedirs(OUTPUT_DIR, exist_ok=True)
    print(f"Generating patriotic music tracks in {OUTPUT_DIR}...")
    
    tracks_to_generate = {
        "bgm_title.ogg": build_title_theme,
        "bgm_combat.ogg": build_combat_theme,
        "bgm_rally.ogg": build_rally_theme,
        "bgm_oval.ogg": build_oval_theme,
        "bgm_intrigue.ogg": build_intrigue_theme,
        "bgm_inauguration.ogg": build_inauguration_theme,
        
        # Overwrite legacy files with the new patriotic tracks
        "bgm_act1_forest.ogg": build_rally_theme,
        "bgm_act2_marsh.ogg": build_oval_theme,
        "bgm_act3_bastion.ogg": build_intrigue_theme,
        "bgm_act4_celestial.ogg": build_inauguration_theme,
    }
    
    for filename, builder in tracks_to_generate.items():
        out_path = os.path.join(OUTPUT_DIR, filename)
        track = builder()
        export_to_ogg(track, out_path)
    
    print("All patriotic music tracks successfully synthesized and encoded!")

if __name__ == "__main__":
    main()
