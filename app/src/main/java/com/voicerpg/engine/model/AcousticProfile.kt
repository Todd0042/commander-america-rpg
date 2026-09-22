package com.voicerpg.engine.model

/**
 * Acoustic and vocal delivery analysis extracted in real-time during an incantation.
 * Measures volume projection, dynamic range (crescendo), pitch inflection, and sustained duration.
 */
data class AcousticProfile(
    val peakVolumeDb: Float = 0f,         // 0.0 to 10.0+ dB
    val averageVolumeDb: Float = 0f,      // Overall projection level
    val volumeDynamicRange: Float = 0f,   // Peak - Minimum (Whisper-to-Roar contrast)
    val volumeCrescendoSlope: Float = 0f, // Positive when volume swells dramatically toward the climax
    val pitchVarianceHz: Float = 0f,      // Pitch inflection / melodic cadence variance
    val estimatedPitchHz: Float = 0f,     // Fundamental vocal pitch
    val durationMs: Long = 0L,            // Duration of the vocal performance
    val sampleCount: Int = 0
) {
    /**
     * Volume Score (0.0 to 0.40):
     * Rewards confident vocal projection over timid mumbling with smooth, responsive scaling.
     */
    val volumeScore: Float
        get() {
            return when {
                peakVolumeDb >= 8.5f -> 0.40f // Booming battle cry / command
                peakVolumeDb >= 7.0f -> 0.30f // Strong heroic projection
                peakVolumeDb >= 5.0f -> 0.18f // Clear conversational speech
                peakVolumeDb >= 3.0f -> 0.08f
                else -> 0.0f                  // Silent / default unmeasured
            }
        }

    /**
     * Inflection & Dynamics Score (0.0 to 0.40):
     * Rewards dynamic volume swells (crescendo from whisper to roar) and pitch variation
     * over a monotone delivery.
     */
    val inflectionScore: Float
        get() {
            var score = 0f

            // Dynamic volume range (did they vary their loudness dynamically?)
            if (volumeDynamicRange >= 6.0f) {
                score += 0.20f // Dramatic dynamic contrast
            } else if (volumeDynamicRange >= 3.5f) {
                score += 0.10f
            }

            // Crescendo bonus: volume built up toward the end
            if (volumeCrescendoSlope > 1.5f) {
                score += 0.10f
            }

            // Pitch variance / inflection modulation
            if (pitchVarianceHz >= 25f) {
                score += 0.10f
            } else if (pitchVarianceHz >= 12f) {
                score += 0.05f
            }

            return score.coerceIn(0.0f, 0.40f)
        }

    companion object {
        val BASIC = AcousticProfile(
            peakVolumeDb = 2.5f,
            averageVolumeDb = 2.0f,
            volumeDynamicRange = 1.0f,
            volumeCrescendoSlope = 0.0f,
            pitchVarianceHz = 5f,
            durationMs = 800L
        )

        val ADEPT = AcousticProfile(
            peakVolumeDb = 4.0f,
            averageVolumeDb = 3.5f,
            volumeDynamicRange = 3.6f,
            volumeCrescendoSlope = 0.4f,
            pitchVarianceHz = 8f,
            durationMs = 1500L
        )

        val MASTER = AcousticProfile(
            peakVolumeDb = 6.0f,
            averageVolumeDb = 5.2f,
            volumeDynamicRange = 4.0f,
            volumeCrescendoSlope = 1.0f,
            pitchVarianceHz = 16f,
            durationMs = 2600L
        )

        val MYTHIC = AcousticProfile(
            peakVolumeDb = 7.6f,
            averageVolumeDb = 6.5f,
            volumeDynamicRange = 4.6f,
            volumeCrescendoSlope = 1.8f,
            pitchVarianceHz = 20f,
            durationMs = 3800L
        )

        val TRANSCENDENTAL = AcousticProfile(
            peakVolumeDb = 9.8f,
            averageVolumeDb = 7.8f,
            volumeDynamicRange = 8.4f,
            volumeCrescendoSlope = 2.5f,
            pitchVarianceHz = 38f,
            estimatedPitchHz = 195f,
            durationMs = 5200L
        )

        /**
         * Generates an organically randomized acoustic profile for simulator and button taps,
         * producing varying, realistic vocal delivery telemetry that prioritizes lower ranges.
         */
        fun rollOrganicProfile(tier: ResonanceTier): AcousticProfile {
            val rng = kotlin.random.Random.Default
            return when (tier) {
                ResonanceTier.BASIC -> AcousticProfile(
                    peakVolumeDb = 1.6f + rng.nextFloat() * 1.5f,        // 1.6 - 3.1 dB
                    averageVolumeDb = 1.3f + rng.nextFloat() * 0.9f,
                    volumeDynamicRange = 0.6f + rng.nextFloat() * 1.2f,
                    volumeCrescendoSlope = rng.nextFloat() * 0.25f,
                    pitchVarianceHz = 2f + rng.nextFloat() * 5f,
                    durationMs = 600L + rng.nextLong(400L)
                )
                ResonanceTier.ADEPT -> AcousticProfile(
                    peakVolumeDb = 4.2f + rng.nextFloat() * 1.6f,        // 4.2 - 5.8 dB
                    averageVolumeDb = 3.3f + rng.nextFloat() * 1.1f,
                    volumeDynamicRange = 2.6f + rng.nextFloat() * 2.0f,
                    volumeCrescendoSlope = 0.3f + rng.nextFloat() * 0.5f,
                    pitchVarianceHz = 8f + rng.nextFloat() * 9f,
                    durationMs = 1200L + rng.nextLong(600L)
                )
                ResonanceTier.MASTER -> AcousticProfile(
                    peakVolumeDb = 6.2f + rng.nextFloat() * 1.4f,        // 6.2 - 7.6 dB
                    averageVolumeDb = 5.0f + rng.nextFloat() * 1.1f,
                    volumeDynamicRange = 4.4f + rng.nextFloat() * 1.8f,
                    volumeCrescendoSlope = 0.8f + rng.nextFloat() * 0.8f,
                    pitchVarianceHz = 16f + rng.nextFloat() * 9f,
                    durationMs = 2200L + rng.nextLong(800L)
                )
                ResonanceTier.MYTHIC -> AcousticProfile(
                    peakVolumeDb = 7.8f + rng.nextFloat() * 1.3f,        // 7.8 - 9.1 dB
                    averageVolumeDb = 6.4f + rng.nextFloat() * 0.9f,
                    volumeDynamicRange = 6.0f + rng.nextFloat() * 2.0f,
                    volumeCrescendoSlope = 1.5f + rng.nextFloat() * 0.8f,
                    pitchVarianceHz = 24f + rng.nextFloat() * 10f,
                    durationMs = 3400L + rng.nextLong(1000L)
                )
                ResonanceTier.TRANSCENDENTAL -> AcousticProfile(
                    peakVolumeDb = 9.4f + rng.nextFloat() * 1.2f,        // 9.4 - 10.6 dB
                    averageVolumeDb = 7.8f + rng.nextFloat() * 1.1f,
                    volumeDynamicRange = 8.2f + rng.nextFloat() * 1.6f,
                    volumeCrescendoSlope = 2.2f + rng.nextFloat() * 0.8f,
                    pitchVarianceHz = 34f + rng.nextFloat() * 14f,
                    estimatedPitchHz = 190f + rng.nextFloat() * 20f,
                    durationMs = 4800L + rng.nextLong(1200L)
                )
            }
        }
    }
}
