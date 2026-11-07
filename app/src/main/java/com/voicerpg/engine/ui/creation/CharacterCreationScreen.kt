package com.voicerpg.engine.ui.creation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voicerpg.engine.audio.CombatNarrator
import com.voicerpg.engine.audio.SpeechManager
import com.voicerpg.engine.audio.SpeechState
import com.voicerpg.engine.engine.ClassSpellLibrary
import com.voicerpg.engine.model.AuraColor
import com.voicerpg.engine.model.HeroClass
import com.voicerpg.engine.model.PlayerCustomization
import com.voicerpg.engine.model.Spell
import com.voicerpg.engine.model.SpellSchool
import com.voicerpg.engine.ui.story.StoryAssetLoader
import com.voicerpg.engine.ui.theme.LogosGlow
import com.voicerpg.engine.ui.theme.LogosGold
import com.voicerpg.engine.ui.theme.RetroBlack
import com.voicerpg.engine.ui.theme.RetroBorder
import com.voicerpg.engine.ui.theme.RetroPanel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CharacterCreationScreen(
    initialCustomization: PlayerCustomization = PlayerCustomization(),
    speechManager: SpeechManager? = null,
    combatNarrator: CombatNarrator? = null,
    onBack: (() -> Unit)? = null,
    onConfirmCharacter: (PlayerCustomization) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf(initialCustomization.name) }
    var selectedTitle by remember { mutableStateOf(initialCustomization.title) }
    var selectedAura by remember { mutableStateOf(initialCustomization.auraColor) }
    var selectedTrack by remember { mutableStateOf(initialCustomization.campaignTrack) }

    val isAutoListen = speechManager?.isAutoListen?.collectAsState()?.value ?: false
    val speechState = speechManager?.speechState?.collectAsState()?.value ?: SpeechState.Idle
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val commanderBitmap = remember {
        StoryAssetLoader.loadBitmap(context, "portraits/commander_america.jpg")
    }

    val titles = listOf(
        "Commander-in-Chief",
        "Master of the Deal",
        "Patriotic Sovereign",
        "The Indomitable",
        "Defender of Freedom"
    )

    val quickNames = listOf("Commander America", "The Boss", "Donald", "Patriot 47")

    fun confirmAndAwaken() {
        speechManager?.cancel()
        val finalCustomization = PlayerCustomization(
            name = if (name.isBlank()) "Commander America" else name.trim(),
            heroClass = HeroClass.COMMANDER,
            title = selectedTitle,
            auraColor = selectedAura,
            voiceAffinityBonusSchool = SpellSchool.PHYSICAL,
            campaignTrack = selectedTrack
        )
        onConfirmCharacter(finalCustomization)
    }

    fun processCreationVoiceInput(utterance: String) {
        val lower = utterance.lowercase().trim()
        if (lower.isBlank()) return

        // Track selection voice commands
        if (lower.contains("track b") || lower.contains("standard bearer") || lower.contains("relay") || lower.contains("hill rod")) {
            selectedTrack = com.voicerpg.engine.model.CampaignTrack.TRACK_B
            combatNarrator?.speak("Track B selected: The Standard-Bearer Relay.", force = true)
            return
        } else if (lower.contains("track a") || lower.contains("commander") || lower.contains("coalition")) {
            selectedTrack = com.voicerpg.engine.model.CampaignTrack.TRACK_A
            combatNarrator?.speak("Track A selected: Commander's Coalition.", force = true)
            return
        }

        // 1. Embark / Start command
        val embarkKeywords = listOf(
            "awaken", "embark", "begin", "start", "enter", "let us begin", "ready",
            "venture", "let's go", "golden escalator", "make america great", "begin campaign", "start game",
            "play", "lets go", "confirm", "forward", "onward", "descend"
        )
        if (embarkKeywords.any { lower.contains(it) }) {
            confirmAndAwaken()
            return
        }

        // 1b. Back command
        if (lower == "back" || lower.contains("go back") || lower.contains("audio setup") || lower == "return") {
            speechManager?.cancel()
            onBack?.invoke()
            return
        }

        // 2. Aura selection commands
        when {
            lower.contains("gold") || lower.contains("solar") || lower.contains("yellow") -> {
                selectedAura = AuraColor.SOLAR_GOLD
                combatNarrator?.speak("Solar Gold aura selected. Radiant presidential brilliance.", force = true)
            }
            lower.contains("crimson") || lower.contains("red") || lower.contains("pyre") || lower.contains("fire") -> {
                selectedAura = AuraColor.CRIMSON_PYRE
                combatNarrator?.speak("Crimson Pyre aura selected. Fierce patriotic flame.", force = true)
            }
            lower.contains("sapphire") || lower.contains("blue") || lower.contains("frost") || lower.contains("cyan") -> {
                selectedAura = AuraColor.SAPPHIRE_FROST
                combatNarrator?.speak("Sapphire Frost aura selected. Cool tactical composure.", force = true)
            }
            lower.contains("emerald") || lower.contains("green") || lower.contains("grove") -> {
                selectedAura = AuraColor.EMERALD_GROVE
                combatNarrator?.speak("Emerald Grove aura selected. Prosperous economic vitality.", force = true)
            }
            lower.contains("amethyst") || lower.contains("purple") || lower.contains("void") || lower.contains("shadow") -> {
                selectedAura = AuraColor.AMETHYST_VOID
                combatNarrator?.speak("Amethyst Void aura selected. Sovereign presidential authority.", force = true)
            }
        }

        // 3. Title selection
        titles.firstOrNull { lower.contains(it.lowercase()) }?.let {
            selectedTitle = it
            combatNarrator?.speak("$it title confirmed.", force = true)
        }

        // 4. Quick name selection
        quickNames.firstOrNull { lower.contains(it.lowercase()) }?.let {
            name = it
            combatNarrator?.speak("Welcome, $it.", force = true)
        }

        // Re-listen if auto-listen is enabled
        if (speechManager != null && speechManager.isAutoListen.value) {
            coroutineScope.launch {
                delay(200)
                speechManager.startListening { next -> processCreationVoiceInput(next) }
            }
        }
    }

    LaunchedEffect(isAutoListen) {
        if (isAutoListen) {
            delay(300)
            speechManager?.startListening { utterance -> processCreationVoiceInput(utterance) }
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
            // Optional Back navigation
            if (onBack != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(RetroPanel)
                            .border(1.dp, LogosGold, RoundedCornerShape(6.dp))
                            .clickable {
                                speechManager?.cancel()
                                onBack()
                            }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "◀ BACK TO AUDIO SETUP",
                            color = LogosGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            // Header
            Text(
                text = "🇺🇸 PRESIDENTIAL CAMPAIGN CUSTOMIZATION 🇺🇸",
                color = LogosGold,
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = "Establish Your Identity & Presidential Aura",
                color = Color.LightGray,
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Character Preview Card with chosen Aura & Name
            val auraColorVal = Color(android.graphics.Color.parseColor(selectedAura.hexColor))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(RetroPanel.copy(alpha = 0.9f))
                    .border(2.dp, auraColorVal, RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Portrait Bust
                    Box(
                        modifier = Modifier
                            .size(84.dp)
                            .shadow(8.dp, RoundedCornerShape(10.dp), spotColor = auraColorVal)
                            .clip(RoundedCornerShape(10.dp))
                            .border(2.dp, auraColorVal, RoundedCornerShape(10.dp))
                    ) {
                        if (commanderBitmap != null) {
                            Image(
                                bitmap = commanderBitmap,
                                contentDescription = name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(auraColorVal.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = name.firstOrNull()?.toString() ?: "C",
                                    color = auraColorVal,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (name.isBlank()) "Nameless Leader" else name,
                            color = LogosGlow,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = selectedTitle,
                            color = auraColorVal,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Role: Commander-in-Chief • HP: 5000 • MP: 1776",
                            color = Color.LightGray,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Section 1: Campaign Name Input
            Text(
                text = "1. PRESIDENTIAL CAMPAIGN NAME",
                color = LogosGold,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Candidate Name", fontFamily = FontFamily.Monospace) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = LogosGold,
                    unfocusedBorderColor = RetroBorder,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = RetroPanel,
                    unfocusedContainerColor = RetroPanel
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Quick Name Suggestions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                quickNames.forEach { qn ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (name == qn) LogosGold.copy(alpha = 0.2f) else RetroPanel)
                            .border(1.dp, if (name == qn) LogosGold else RetroBorder, RoundedCornerShape(6.dp))
                            .clickable { name = qn }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = qn,
                            color = if (name == qn) LogosGold else Color.LightGray,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Section 2: Presidential Arsenal & Loadout
            Text(
                text = "2. PRESIDENTIAL ARSENAL & LOADOUT",
                color = LogosGold,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            PresidentialArsenalCard(
                spells = ClassSpellLibrary.COMMANDER_SPELLS
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Section 3: Presidential Aura (Aesthetics)
            Text(
                text = "3. PRESIDENTIAL AURA & TIE COLOR",
                color = LogosGold,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AuraColor.entries.forEach { aura ->
                    val isSelected = aura == selectedAura
                    val col = Color(android.graphics.Color.parseColor(aura.hexColor))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { selectedAura = aura }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .shadow(if (isSelected) 10.dp else 2.dp, CircleShape, spotColor = col)
                                .clip(CircleShape)
                                .background(col)
                                .border(if (isSelected) 3.dp else 1.dp, if (isSelected) Color.White else RetroBorder, CircleShape)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = aura.displayName.split(" ").first(),
                            color = if (isSelected) col else Color.Gray,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Section 4: Honorific Title Selector
            Text(
                text = "4. HONORIFIC TITLE",
                color = LogosGold,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                titles.forEach { t ->
                    val isSelected = t == selectedTitle
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSelected) LogosGold.copy(alpha = 0.2f) else RetroPanel)
                            .border(1.dp, if (isSelected) LogosGold else RetroBorder, RoundedCornerShape(6.dp))
                            .clickable { selectedTitle = t }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = t,
                            color = if (isSelected) LogosGold else Color.LightGray,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Section 5: Campaign Track Selector
            Text(
                text = "5. CHOOSE YOUR CAMPAIGN TRACK",
                color = LogosGold,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                com.voicerpg.engine.model.CampaignTrack.entries.forEach { track ->
                    val isSelected = track == selectedTrack
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) LogosGold.copy(alpha = 0.2f) else RetroPanel)
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) LogosGold else RetroBorder,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedTrack = track }
                            .padding(10.dp)
                    ) {
                        Column {
                            Text(
                                text = if (track == com.voicerpg.engine.model.CampaignTrack.TRACK_A) "🇺🇸 TRACK A" else "📜 TRACK B",
                                color = if (isSelected) LogosGold else Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.Monospace
                            )
                            Text(
                                text = track.displayName.substringAfter(": "),
                                color = if (isSelected) LogosGlow else Color.LightGray,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = track.subtitle,
                                color = Color.Gray,
                                fontSize = 8.sp,
                                lineHeight = 11.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Confirm & Enter Adventure Button
            Button(
                onClick = { confirmAndAwaken() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = LogosGold,
                    contentColor = RetroBlack
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = if (selectedTrack == com.voicerpg.engine.model.CampaignTrack.TRACK_B) "ENTER CHAPPAQUA BASEMENT ➔" else "DESCEND GOLDEN ESCALATOR ➔",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Voice Prompt Bar for Hands-free Interaction
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🎤 Speak aura, title, or say 'Start' to begin",
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                )

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
                                    speechManager.startListening { utterance -> processCreationVoiceInput(utterance) }
                                }
                            }
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = if (speechState is SpeechState.Listening) "🎙️ LISTENING..." else "🎤 SPEAK",
                            color = LogosGold,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PresidentialArsenalCard(
    spells: List<Spell>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(RetroPanel)
            .border(
                width = 2.dp,
                color = LogosGold,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "COMMANDER-IN-CHIEF",
                        color = LogosGold,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "Patriotic Sovereign & Master of the Deal",
                        color = LogosGlow,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                // Stats Pills
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    StatPill("HP", "5000", Color(0xFF4CAF50))
                    StatPill("MP", "1776", Color(0xFF00B0FF))
                    StatPill("SPD", "70", Color(0xFFFFB74D))
                    StatPill("ATK", "50", LogosGold)
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Equipped with 8 signature presidential abilities to obliterate political obstruction, restore economic sovereignty, and lead America into a golden age.",
                color = Color.LightGray,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                lineHeight = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Signature Spells
            @OptIn(ExperimentalLayoutApi::class)
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                spells.forEach { sp ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF222234))
                            .border(1.dp, LogosGold.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "⚡ ${sp.name}",
                            color = LogosGold,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatPill(label: String, value: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.18f))
            .border(1.dp, color.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp, vertical = 2.dp)
    ) {
        Text(
            text = "$label:$value",
            color = color,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}
