package com.voicerpg.engine.ui.combat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.voicerpg.engine.model.PartyMember
import com.voicerpg.engine.ui.sprites.PixelCharacterView

@Composable
fun PartyFlank(
    party: List<PartyMember>,
    activePartyMemberId: String?,
    onSelectHero: (PartyMember) -> Unit,
    onPositionHero: ((String, Offset) -> Unit)? = null,
    arenaCoordinates: LayoutCoordinates? = null,
    modifier: Modifier = Modifier
) {
    // 2-Row Tactical Formation:
    // Front Row (Vanguard): Commander America & JD Vance (positioned between back row and enemies)
    // Back Row (Support): Marco Rubio, Kash Patel, Senator John Kennedy (positioned on the left)
    val (backRowMembers, frontRowMembers) = remember(party) {
        val hasVanguard = party.any { it.id == "hero" || it.id == "commander_america" || it.id == "vance" }
        if (hasVanguard) {
            val front = party.filter { it.id == "hero" || it.id == "commander_america" || it.id == "vance" }
                .sortedBy { if (it.id == "hero" || it.id == "commander_america") 0 else 1 }
            val back = party.filterNot { it.id == "hero" || it.id == "commander_america" || it.id == "vance" }
            Pair(back, front)
        } else {
            if (party.size >= 4) {
                Pair(party.drop(2), party.take(2))
            } else {
                Pair(emptyList(), party)
            }
        }
    }

    val verticalSpacing = when {
        backRowMembers.size >= 3 -> 6.dp
        party.size >= 4 -> 8.dp
        else -> 12.dp
    }
    val frontVerticalSpacing = when {
        backRowMembers.size >= 3 -> 24.dp
        backRowMembers.size == 2 -> 16.dp
        else -> verticalSpacing
    }
    val scaledPixelSize = when {
        party.size >= 5 -> 1.7.dp
        party.size >= 4 -> 1.8.dp
        else -> 2.0.dp
    }

    Row(
        modifier = modifier.padding(start = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Row: left side of screen (Support heroes: Marco, Kash, Kennedy)
        if (backRowMembers.isNotEmpty()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(verticalSpacing),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                backRowMembers.forEach { member ->
                    PartyFlankMemberItem(
                        member = member,
                        isActiveTurn = member.id == activePartyMemberId,
                        scaledPixelSize = scaledPixelSize,
                        arenaCoordinates = arenaCoordinates,
                        onSelectHero = onSelectHero,
                        onPositionHero = onPositionHero
                    )
                }
            }
        }

        // Front Row: in front of the 3 (between back row and enemies: Commander America, Vance)
        if (frontRowMembers.isNotEmpty()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(frontVerticalSpacing),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                frontRowMembers.forEach { member ->
                    PartyFlankMemberItem(
                        member = member,
                        isActiveTurn = member.id == activePartyMemberId,
                        scaledPixelSize = scaledPixelSize,
                        arenaCoordinates = arenaCoordinates,
                        onSelectHero = onSelectHero,
                        onPositionHero = onPositionHero
                    )
                }
            }
        }
    }
}

@Composable
private fun PartyFlankMemberItem(
    member: PartyMember,
    isActiveTurn: Boolean,
    scaledPixelSize: Dp,
    arenaCoordinates: LayoutCoordinates?,
    onSelectHero: (PartyMember) -> Unit,
    onPositionHero: ((String, Offset) -> Unit)?
) {
    var cardCoords by remember { mutableStateOf<LayoutCoordinates?>(null) }

    LaunchedEffect(cardCoords, arenaCoordinates) {
        val card = cardCoords
        val arena = arenaCoordinates
        if (card != null && arena != null && card.isAttached && arena.isAttached) {
            val pos = arena.localPositionOf(card, Offset.Zero)
            val center = Offset(
                pos.x + card.size.width / 2f,
                pos.y + card.size.height / 2f
            )
            onPositionHero?.invoke(member.id, center)
        }
    }

    PixelCharacterView(
        characterId = member.id,
        name = member.name,
        subtitle = member.loreClass,
        hpRatio = member.hpRatio,
        currentHp = member.currentHp,
        maxHp = member.maxHp,
        mpRatio = member.mpRatio,
        atbRatio = member.atbRatio,
        isTurnReady = member.isTurnReady,
        isActiveTurn = isActiveTurn,
        stance = member.stance,
        isFlippedHorizontally = false,
        pixelSize = scaledPixelSize,
        onClick = { onSelectHero(member) },
        modifier = Modifier.onGloballyPositioned { cardCoords = it }
    )
}
