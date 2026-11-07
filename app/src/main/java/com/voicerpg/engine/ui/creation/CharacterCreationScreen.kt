package com.voicerpg.engine.ui.creation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voicerpg.engine.audio.CombatNarrator
import com.voicerpg.engine.audio.SpeechManager
import com.voicerpg.engine.audio.SpeechState
import com.voicerpg.engine.model.AuraColor
import com.voicerpg.engine.model.CampaignTrack
import com.voicerpg.engine.model.DialogueSpeaker
import com.voicerpg.engine.model.HeroClass
import com.voicerpg.engine.model.PlayerCustomization
import com.voicerpg.engine.model.SpellSchool
import com.voicerpg.engine.ui.story.StoryAssetLoader
import com.voicerpg.engine.ui.theme.LogosGlow
import com.voicerpg.engine.ui.theme.LogosGold
import com.voicerpg.engine.ui.theme.RetroBlack
import com.voicerpg.engine.ui.theme.RetroBorder
import com.voicerpg.engine.ui.theme.RetroPanel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class ArcadeCandidate(
    val id: String,
    val candidateName: String,
    val alias: String,
    val title: String,
    val track: CampaignTrack,
    val portraitAsset: String,
    val speaker: DialogueSpeaker,
    val neonColor: Color,
    val secondaryColor: Color,
    val slogan: String,
    val startingScene: String,
    val playstyle: String,
    val coalitionRoster: String,
    val moves: List<String>,
    val soundbiteLabel: String
) {
    COMMANDER_AMERICA(
        id = "commander",
        candidateName = "COMMANDER AMERICA",
        alias = "THE GOLDEN SOVEREIGN",
        title = "Commander-in-Chief",
        track = CampaignTrack.TRACK_A,
        portraitAsset = "portraits/commander_america.jpg",
        speaker = DialogueSpeaker.COMMANDER_AMERICA,
        neonColor = Color(0xFFFFD700),
        secondaryColor = Color(0xFFD32F2F),
        slogan = "MAKE AMERICA GREAT AGAIN • WE NEVER STOP WINNING",
        startingScene = "Act I: The Golden Escalator",
        playstyle = "5-Hero Supergroup Coalition",
        coalitionRoster = "Rubio • Vance • Patel • Kennedy",
        moves = listOf("Tremendous Strike", "Art of the Deal", "Presidential Veto", "Build The Wall"),
        soundbiteLabel = "WINNING!"
    ),
    HILL_ROD(
        id = "hill_rod",
        candidateName = "HILL-ROD",
        alias = "CHAPPAQUA TECHNOMANCER",
        title = "Standard-Bearer & Server Specialist",
        track = CampaignTrack.TRACK_B,
        portraitAsset = "portraits/template/hill_rod_template.jpg",
        speaker = DialogueSpeaker.HILL_ROD,
        neonColor = Color(0xFF00E5FF),
        secondaryColor = Color(0xFF7C4DFF),
        slogan = "IT'S HER TURN • WIPE IT WITH A CLOTH",
        startingScene = "Act I: Chappaqua Server Room",
        playstyle = "4-Hero Standard-Bearer Relay",
        coalitionRoster = "Hill-Rod ➔ Joe Naptime ➔ Kamala ➔ Barry",
        moves = listOf("Cloth Wipe", "BleachBit Protocol", "SuperPAC Surge", "Reset Button"),
        soundbiteLabel = "EMAILS!"
    )
}

@Composable
fun CharacterCreationScreen(
    initialCustomization: PlayerCustomization = PlayerCustomization(),
    speechManager: SpeechManager? = null,
    combatNarrator: CombatNarrator? = null,
    onBack: (() -> Unit)? = null,
    onConfirmCharacter: (PlayerCustomization) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCandidate by remember {
        mutableStateOf(
            if (initialCustomization.campaignTrack == CampaignTrack.TRACK_B) ArcadeCandidate.HILL_ROD
            else ArcadeCandidate.COMMANDER_AMERICA
        )
    }

    val isAutoListen = speechManager?.isAutoListen?.collectAsState()?.value ?: false
    val speechState = speechManager?.speechState?.collectAsState()?.value ?: SpeechState.Idle
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Load portraits
    val commanderBitmap = remember {
        StoryAssetLoader.loadBitmap(context, ArcadeCandidate.COMMANDER_AMERICA.portraitAsset)
    }
    val hillRodBitmap = remember {
        StoryAssetLoader.loadBitmap(context, ArcadeCandidate.HILL_ROD.portraitAsset)
    }

    // Arcade pulsing light animation
    val infiniteTransition = rememberInfiniteTransition(label = "ArcadePulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseAlpha"
    )

    fun confirmCandidate(candidate: ArcadeCandidate) {
        speechManager?.cancel()
        val custom = PlayerCustomization(
            name = if (candidate == ArcadeCandidate.COMMANDER_AMERICA) "Commander America" else "Hill-Rod",
            heroClass = HeroClass.COMMANDER,
            title = candidate.title,
            auraColor = if (candidate == ArcadeCandidate.COMMANDER_AMERICA) AuraColor.SOLAR_GOLD else AuraColor.SAPPHIRE_FROST,
            voiceAffinityBonusSchool = SpellSchool.PHYSICAL,
            campaignTrack = candidate.track
        )
        onConfirmCharacter(custom)
    }

    fun playCandidateSoundbite(candidate: ArcadeCandidate) {
        combatNarrator?.previewSpeakerVoice(candidate.speaker)
    }

    fun processVoiceInput(utterance: String) {
        val lower = utterance.lowercase().trim()
        if (lower.isBlank()) return

        // 1. Candidate selection
        if (lower.contains("trump") || lower.contains("commander") || lower.contains("track a") || lower == "one" || lower.contains("america")) {
            selectedCandidate = ArcadeCandidate.COMMANDER_AMERICA
            combatNarrator?.speak("Commander America selected. The Golden Sovereign.", force = true)
            return
        } else if (lower.contains("hillary") || lower.contains("hill rod") || lower.contains("hillrod") || lower.contains("track b") || lower == "two" || lower.contains("relay")) {
            selectedCandidate = ArcadeCandidate.HILL_ROD
            combatNarrator?.speak("Hill-Rod selected. The Standard-Bearer Relay.", force = true)
            return
        }

        // 2. Soundbite audition
        if (lower.contains("soundbite") || lower.contains("quote") || lower.contains("audition") || lower.contains("voice") || lower.contains("speak")) {
            playCandidateSoundbite(selectedCandidate)
            return
        }

        // 3. Lock-in / Start game
        val startKeywords = listOf(
            "start", "lock in", "lock", "select", "begin", "play", "enter", "ready",
            "fight", "let's go", "lets go", "embark", "confirm", "descend", "chappaqua", "escalator"
        )
        if (startKeywords.any { lower.contains(it) }) {
            confirmCandidate(selectedCandidate)
            return
        }

        // 4. Return to title
        if (lower == "back" || lower.contains("go back") || lower.contains("title") || lower == "return") {
            speechManager?.cancel()
            onBack?.invoke()
            return
        }

        // Re-listen if auto-listen is active
        if (speechManager != null && speechManager.isAutoListen.value) {
            coroutineScope.launch {
                delay(200)
                speechManager.startListening { next -> processVoiceInput(next) }
            }
        }
    }

    LaunchedEffect(isAutoListen) {
        if (isAutoListen) {
            delay(300)
            speechManager?.startListening { utterance -> processVoiceInput(utterance) }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = RetroBlack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Arcade Header Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back button
                if (onBack != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(RetroPanel)
                            .border(1.dp, LogosGold, RoundedCornerShape(6.dp))
                            .clickable {
                                speechManager?.cancel()
                                onBack()
                            }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "◀ TITLE",
                            color = LogosGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(60.dp))
                }

                // Voice Mic Button
                if (speechManager != null) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (speechState is SpeechState.Listening) Color(0xFFEF5350) else RetroPanel)
                            .border(1.dp, LogosGold, CircleShape)
                            .clickable {
                                if (speechState is SpeechState.Listening) {
                                    speechManager.stopListening()
                                } else {
                                    speechManager.startListening { utterance -> processVoiceInput(utterance) }
                                }
                            }
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = if (speechState is SpeechState.Listening) "🎙️ LISTENING..." else "🎤 VOICE READY",
                            color = LogosGold,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Flashing 90s Arcade Marquee
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF2E1C00), Color(0xFF140D00), Color(0xFF2E1C00))
                        )
                    )
                    .border(
                        width = 2.dp,
                        color = LogosGold.copy(alpha = pulseAlpha),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 10.dp, horizontal = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "★ SELECT YOUR CANDIDATE ★",
                        color = LogosGold,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "90S RETRO ARCADE CAMPAIGN SELECT • 1P VS ESTABLISHMENT",
                        color = Color(0xFFFFD54F).copy(alpha = 0.9f),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Candidate Cards: 2 Fighters Side-by-Side (or stacked on small screens)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Card 1: Commander America
                ArcadeCandidateCard(
                    candidate = ArcadeCandidate.COMMANDER_AMERICA,
                    isSelected = selectedCandidate == ArcadeCandidate.COMMANDER_AMERICA,
                    portraitBitmap = commanderBitmap,
                    pulseAlpha = if (selectedCandidate == ArcadeCandidate.COMMANDER_AMERICA) pulseAlpha else 1f,
                    onSelect = {
                        selectedCandidate = ArcadeCandidate.COMMANDER_AMERICA
                        playCandidateSoundbite(ArcadeCandidate.COMMANDER_AMERICA)
                    },
                    modifier = Modifier.weight(1f)
                )

                // Card 2: Hill-Rod
                ArcadeCandidateCard(
                    candidate = ArcadeCandidate.HILL_ROD,
                    isSelected = selectedCandidate == ArcadeCandidate.HILL_ROD,
                    portraitBitmap = hillRodBitmap,
                    pulseAlpha = if (selectedCandidate == ArcadeCandidate.HILL_ROD) pulseAlpha else 1f,
                    onSelect = {
                        selectedCandidate = ArcadeCandidate.HILL_ROD
                        playCandidateSoundbite(ArcadeCandidate.HILL_ROD)
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Selected Candidate Dossier & Campaign Preview
            SelectedCandidateDossier(
                candidate = selectedCandidate,
                onAuditionSoundbite = { playCandidateSoundbite(selectedCandidate) }
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Giant Arcade "LOCK IN / PRESS START" Button
            val buttonColor by animateColorAsState(
                targetValue = selectedCandidate.neonColor,
                animationSpec = tween(300),
                label = "ButtonColor"
            )

            Button(
                onClick = { confirmCandidate(selectedCandidate) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(12.dp, RoundedCornerShape(10.dp), spotColor = buttonColor)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (selectedCandidate == ArcadeCandidate.COMMANDER_AMERICA)
                            "★ LOCK IN COMMANDER AMERICA ★"
                        else
                            "★ LOCK IN HILL-ROD ★",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Black,
                        fontSize = 15.sp,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "PRESS TO EMBARK OR SAY \"LOCK IN\"",
                        color = Color.Black.copy(alpha = 0.75f),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Voice Hint Footer
            Text(
                text = "🎤 Voice Enabled: Say \"Trump\", \"Hillary\", \"Soundbite\", or \"Start\"",
                color = Color.Gray,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ArcadeCandidateCard(
    candidate: ArcadeCandidate,
    isSelected: Boolean,
    portraitBitmap: androidx.compose.ui.graphics.ImageBitmap?,
    pulseAlpha: Float,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) candidate.neonColor.copy(alpha = pulseAlpha) else RetroBorder
    val borderWidth = if (isSelected) 3.dp else 1.dp
    val bgColor = if (isSelected) Color(0xFF1E1E2E) else RetroPanel

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor)
            .border(borderWidth, borderColor, RoundedCornerShape(10.dp))
            .clickable { onSelect() }
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Track & Slot Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (isSelected) candidate.neonColor else Color.DarkGray)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = if (candidate.track == CampaignTrack.TRACK_A) "TRACK A • 1P" else "TRACK B • 2P",
                    color = if (isSelected) Color.Black else Color.LightGray,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Big 1:1 Portrait Box
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(2.dp, if (isSelected) candidate.neonColor else Color.Gray, RoundedCornerShape(8.dp))
                    .background(Color.Black)
            ) {
                if (portraitBitmap != null) {
                    Image(
                        bitmap = portraitBitmap,
                        contentDescription = candidate.candidateName,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(candidate.neonColor.copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = candidate.candidateName.first().toString(),
                            color = candidate.neonColor,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }

                // Selected Checkmark Overlay
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(candidate.neonColor)
                            .size(18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✓",
                            color = Color.Black,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Candidate Name
            Text(
                text = candidate.candidateName,
                color = if (isSelected) candidate.neonColor else Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            // Callsign
            Text(
                text = candidate.alias,
                color = if (isSelected) LogosGlow else Color.Gray,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun SelectedCandidateDossier(
    candidate: ArcadeCandidate,
    onAuditionSoundbite: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF14141F))
            .border(1.5.dp, candidate.neonColor.copy(alpha = 0.7f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Column {
            // Header Row with Soundbite Audition Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = candidate.candidateName,
                        color = candidate.neonColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = candidate.slogan,
                        color = Color.LightGray,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace
                    )
                }

                // Soundbite Button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(candidate.neonColor.copy(alpha = 0.2f))
                        .border(1.dp, candidate.neonColor, RoundedCornerShape(6.dp))
                        .clickable { onAuditionSoundbite() }
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "🔊 ${candidate.soundbiteLabel}",
                        color = candidate.neonColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Campaign Dossier Grid
            Row(modifier = Modifier.fillMaxWidth()) {
                // Left Column: Campaign Type & Start
                Column(modifier = Modifier.weight(1f)) {
                    DossierField(label = "CAMPAIGN FORMAT", value = candidate.playstyle, accent = candidate.neonColor)
                    Spacer(modifier = Modifier.height(4.dp))
                    DossierField(label = "STARTING CHAPTER", value = candidate.startingScene, accent = Color.White)
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Right Column: Party Lineup
                Column(modifier = Modifier.weight(1f)) {
                    DossierField(label = "COALITION ROSTER", value = candidate.coalitionRoster, accent = LogosGlow)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Signature Moves Banner
            Text(
                text = "SIGNATURE ARSENAL & ABILITIES:",
                color = Color.Gray,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )

            Spacer(modifier = Modifier.height(4.dp))

            @OptIn(ExperimentalLayoutApi::class)
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                candidate.moves.forEach { move ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF222238))
                            .border(1.dp, candidate.neonColor.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "⚡ $move",
                            color = candidate.neonColor,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DossierField(
    label: String,
    value: String,
    accent: Color
) {
    Column {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = value,
            color = accent,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace
        )
    }
}
