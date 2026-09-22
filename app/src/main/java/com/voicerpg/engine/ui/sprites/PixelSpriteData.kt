package com.voicerpg.engine.ui.sprites

import androidx.compose.ui.graphics.Color

enum class SpriteFrame {
    IDLE_UPRIGHT,
    IDLE_CROUCH,
    ACTION_CAST,
    DAMAGED
}

/**
 * 32-bit Enhanced Pixel Sprites (20x26 grid) with rich multi-tone shading,
 * detailed silhouettes, gear articulation, and breathing/knee-buckle animation frames.
 *
 * Fully themed for Commander America: Quest for the Golden Tower:
 * - Commander America: Blonde sweep, aviator sunglasses, navy suit, flag tie
 * - K-Street Lobbyist / Deep State Suit: Charcoal pinstripe suit, briefcase with cash
 * - Cable Pundit / Fact-Checker: Studio blazer, headset, glasses, microphone
 * - Madam Pelosi: Orange power suit, styled bob, pearls, golden gavel
 * - Chairman Rocket Man: Box-fade hairstyle, black Mao tunic, missile remote
 * - Kamala the Unburdened: Lavender pantsuit, pearls, floating Venn diagram circles
 * - Biden the Malarkey Sovereign: Aviator shades, silver hair, suit, vanilla ice cream cone
 * - 3 AM Ballot Golem: Stacks of stamped 3:00 AM ballot boxes with glowing red eyes
 */
object PixelSpriteData {

    // Common Base Colors
    val Transparent = Color.Transparent
    val DarkOutline = Color(0xFF0C0E14)
    val WhiteSpec = Color(0xFFFFFFFF)

    // 0. Commander America Palette
    private val COMMANDER_AMERICA_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFFFFEE58), // Blonde hair highlight
        'h' to Color(0xFFFFD54F), // Blonde hair mid
        'y' to Color(0xFFFFB300), // Blonde hair shadow
        'S' to Color(0xFFFFCC80), // Healthy tanned skin
        's' to Color(0xFFFFA726), // Skin shadow
        'G' to Color(0xFF212121), // Aviator sunglasses dark lens
        'g' to Color(0xFF37474F), // Aviator sunglasses frame
        'P' to WhiteSpec,         // Specular sheen / white shirt collar
        'N' to Color(0xFF1A237E), // Navy Blue suit
        'n' to Color(0xFF283593), // Navy Blue highlight fold
        'B' to Color(0xFF0D1B2A), // Navy Blue deep shadow
        'R' to Color(0xFFD32F2F), // Flag tie red
        'r' to Color(0xFFB71C1C), // Deep tie shadow
        'W' to WhiteSpec,         // Tie white stripes / cuff
        'X' to Color(0xFFFFD700)  // Golden cufflink / belt buckle
    )

    private val MARCO_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFF5D4037), // Dark brown hair highlight
        'h' to Color(0xFF4E342E), // Brown hair mid
        'y' to Color(0xFF3E2723), // Dark hair shadow
        'S' to Color(0xFFFFCC80), // Skin
        's' to Color(0xFFFFA726), // Skin shadow
        'G' to Color(0xFF212121), // Eyes
        'g' to Color(0xFF37474F), // Shadow
        'P' to WhiteSpec,         // White collar
        'N' to Color(0xFF1565C0), // Diplomatic Royal Blue suit
        'n' to Color(0xFF1E88E5), // Blue fold highlight
        'B' to Color(0xFF0D47A1), // Deep suit shadow
        'R' to Color(0xFFE53935), // Red tie
        'r' to Color(0xFFB71C1C), // Tie shadow
        'W' to WhiteSpec,         // Collar / cuffs
        'X' to Color(0xFF42A5F5)  // Diplomatic pin
    )

    private val VANCE_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFF4E342E), // Dark brown hair/beard
        'h' to Color(0xFF3E2723), // Dark brown mid
        'y' to Color(0xFF271915), // Deep brown beard shadow
        'S' to Color(0xFFFFCC80), // Skin
        's' to Color(0xFFFFA726), // Skin shadow
        'G' to Color(0xFF212121), // Focused gaze
        'g' to Color(0xFF37474F), // Shadow
        'P' to WhiteSpec,         // White collar
        'N' to Color(0xFF1A237E), // Navy Blue Vanguard suit
        'n' to Color(0xFF283593), // Suit highlight
        'B' to Color(0xFF0D1B2A), // Suit deep shadow
        'R' to Color(0xFFC62828), // Crimson tie
        'r' to Color(0xFF8E0000), // Crimson tie shadow
        'W' to WhiteSpec,         // Shirt cuffs
        'X' to Color(0xFF5C6BC0)  // Heart of Ohio badge
    )

    private val KASH_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFF37474F), // Jet black hair highlight
        'h' to Color(0xFF212121), // Jet black hair mid
        'y' to Color(0xFF101416), // Jet black hair shadow
        'S' to Color(0xFFFFCC80), // Skin
        's' to Color(0xFFE09A46), // Skin shadow
        'G' to Color(0xFF111111), // Aviator dark sunglasses
        'g' to Color(0xFF90A4AE), // Aviator silver frame
        'P' to Color(0xFF263238), // Dark charcoal open-collar shirt
        'N' to Color(0xFF37474F), // Charcoal suit
        'n' to Color(0xFF455A64), // Charcoal fold
        'B' to Color(0xFF1A242A), // Deep charcoal shadow
        'R' to Color(0xFF7E57C2), // Classified dossier accent
        'r' to Color(0xFF512DA8), // Deep purple accent
        'W' to WhiteSpec,         // Document cuffs
        'X' to Color(0xFFFFB300)  // Gold intelligence badge
    )

    private val KENNEDY_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFFECEFF1), // Silver hair highlight
        'h' to Color(0xFFCFD8DC), // Silver hair mid
        'y' to Color(0xFF90A4AE), // Silver hair shadow
        'S' to Color(0xFFFFCC80), // Friendly grandpa skin tone
        's' to Color(0xFFFFA726), // Soft cheek shadow
        'G' to Color(0xFF37474F), // Kind dark eyes
        'g' to Color(0xFF546E7A), // Eye shadow
        'P' to Color(0xFF263238), // Dark navy committee suit
        'N' to Color(0xFF37474F), // Navy jacket highlight
        'n' to Color(0xFF455A64), // Navy jacket mid
        'B' to Color(0xFF1C313A), // Suit shadow
        'R' to Color(0xFFC62828), // Crimson Louisiana tie
        'r' to Color(0xFF8E0000), // Tie shadow
        'W' to WhiteSpec,         // Crisp white dress shirt
        'X' to Color(0xFF66BB6A)  // MAHA health badge
    )

    // 1. K-Street Lobbyist / Deep State Suit Palette
    private val LOBBYIST_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFFB0BEC5), // Silver-grey hair
        'h' to Color(0xFF546E7A), // Hair shadow
        'S' to Color(0xFFFFCC80), // Skin
        's' to Color(0xFFFFA726), // Skin shadow
        'G' to Color(0xFF212121), // Dark sunglasses lens
        'g' to Color(0xFF616161), // Sunglasses frame
        'P' to WhiteSpec,         // White dress shirt / cuffs
        'C' to Color(0xFF37474F), // Charcoal pinstripe suit
        'c' to Color(0xFF455A64), // Charcoal highlight
        'b' to Color(0xFF212121), // Deep suit shadow
        'R' to Color(0xFFE53935), // Red power tie
        'r' to Color(0xFFB71C1C), // Deep tie shadow
        'L' to Color(0xFF8D6E63), // Leather briefcase brown
        'l' to Color(0xFF5D4037), // Dark briefcase leather
        'X' to Color(0xFFFFD54F)  // Brass briefcase lock
    )

    // 2. Cable Pundit / Biased Fact-Checker Palette
    private val PUNDIT_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFF5D4037), // Dark brown hair
        'h' to Color(0xFF3E2723), // Hair shadow
        'S' to Color(0xFFFFCC80), // Skin
        's' to Color(0xFFFFA726), // Skin shadow
        'O' to Color(0xFF80D8FF), // Fact-checker glasses lens
        'o' to Color(0xFF263238), // Glasses dark frame
        'P' to WhiteSpec,         // White shirt collar
        'B' to Color(0xFF1565C0), // Cable news royal blue blazer
        'b' to Color(0xFF1E88E5), // Blazer highlight fold
        'd' to Color(0xFF0D47A1), // Deep blazer shadow
        'Y' to Color(0xFFFFD600), // Breaking news yellow tie
        'y' to Color(0xFFFFAB00), // Tie shadow
        'M' to Color(0xFFCFD8DC), // Microphone metal grille
        'm' to Color(0xFF37474F), // Microphone windscreen foam
        'E' to Color(0xFF00E5FF)  // Studio earpiece wire
    )

    // 3. Madam Pelosi (Stock Lich) Palette
    private val PELOSI_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFF6D4C41), // Coiffed brunette bob
        'h' to Color(0xFF8D6E63), // Bob highlight
        'y' to Color(0xFF3E2723), // Deep hair shadow
        'S' to Color(0xFFFFE0B2), // Fair skin
        's' to Color(0xFFFFCC80), // Skin shadow
        'E' to Color(0xFF4E342E), // Dark eyeshadow
        'P' to WhiteSpec,         // Pearl necklace
        'O' to Color(0xFFFF6D00), // Bright orange power suit
        'o' to Color(0xFFFF9E80), // Suit highlight fold
        'd' to Color(0xFFD84315), // Deep orange shadow
        'G' to Color(0xFFFFD700), // Golden gavel head
        'g' to Color(0xFF8D6E63), // Gavel handle
        'X' to Color(0xFF00E676)  // Glowing green stock ticker
    )

    // 4. Chairman Rocket Man Palette
    private val ROCKET_MAN_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFF212121), // High-top flat black hair
        'h' to Color(0xFF424242), // Hair sheen
        'S' to Color(0xFFFFE082), // Face skin
        's' to Color(0xFFFFCA28), // Skin shadow
        'E' to Color(0xFF212121), // Squinting eyes
        'N' to Color(0xFF263238), // Black Mao safari tunic
        'n' to Color(0xFF37474F), // Tunic highlight fold
        'B' to Color(0xFF102027), // Deep tunic shadow
        'b' to Color(0xFF102027), // Pant shadow
        'R' to Color(0xFFFF1744), // Red star lapel pin
        'M' to Color(0xFF546E7A), // Rocket launcher remote casing
        'F' to Color(0xFFFF5252), // Rocket nose cone
        'Y' to Color(0xFFFFEA00)  // Launch button
    )

    // 5. Kamala the Unburdened Palette
    private val KAMALA_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFF3E2723), // Brunette wavy hair
        'h' to Color(0xFF5D4037), // Hair highlight
        'S' to Color(0xFFFFCC80), // Skin
        's' to Color(0xFFFFA726), // Skin shadow
        'E' to Color(0xFF212121), // Laughing eyes
        'P' to WhiteSpec,         // Pearl necklace
        'V' to Color(0xFF7B1FA2), // Vibrant purple pantsuit
        'v' to Color(0xFF9C27B0), // Purple highlight fold
        'd' to Color(0xFF4A148C), // Deep purple shadow
        'Y' to Color(0xFFFFD600), // Glowing yellow Venn diagram
        'B' to Color(0xFF00E5FF), // Glowing cyan Venn diagram
        'W' to WhiteSpec          // Pearly smile
    )

    // 6. Biden the Malarkey Sovereign Palette
    private val BIDEN_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFFECEFF1), // Silver-white hair
        'h' to Color(0xFFB0BEC5), // Hair shadow
        'S' to Color(0xFFFFE0B2), // Fair skin
        's' to Color(0xFFFFCC80), // Skin shadow
        'G' to Color(0xFF212121), // Dark aviator sunglasses lens
        'g' to Color(0xFF78909C), // Aviator metal frame
        'P' to WhiteSpec,         // White dress collar
        'N' to Color(0xFF1A237E), // Dark navy suit
        'n' to Color(0xFF283593), // Navy highlight fold
        'b' to Color(0xFF0D1B2A), // Deep navy shadow
        'T' to Color(0xFF42A5F5), // Light blue presidential tie
        't' to Color(0xFF1E88E5), // Blue tie shadow
        'V' to Color(0xFFFFF9C4), // Vanilla ice cream scoops
        'v' to Color(0xFFFFF59D), // Ice cream melting drip
        'C' to Color(0xFFFFB74D), // Waffle cone
        'c' to Color(0xFFF57C00)  // Waffle cone shadow
    )

    // 7. 3 AM Ballot / Ice Cream Cone Golem Palette
    private val BALLOT_GOLEM_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'C' to Color(0xFFD7CCC8), // Cardboard box tan
        'c' to Color(0xFFA1887F), // Cardboard shadow
        'd' to Color(0xFF6D4C41), // Deep box seam
        'T' to Color(0xFFBCAAA4), // Packing tape
        'W' to WhiteSpec,         // White ballot sheet
        'w' to Color(0xFFCFD8DC), // Paper shadow
        'R' to Color(0xFFFF1744), // Red 3:00 AM digital stamp
        'r' to Color(0xFFB71C1C), // Dark stamp ink
        'B' to Color(0xFF1976D2), // Blue certification seal
        'E' to Color(0xFFFF5252)  // Glowing evil red box eyes
    )

private val COMMANDER_AMERICA_UPRIGHT = listOf(
        "......KKHHHHHK......",
        ".....KHHHHhhhhK.....",
        "....KHHHhhhhhyyK....",
        "....KHHhhhhhhyyK....",
        "....KKhSSSSShhhK....",
        "....KhSGGKGGShhK....",
        "....KhSGPKGPShhK....",
        ".....KhSSSSShhK.....",
        "......KsssssK.......",
        "....KKKPNPNPKKK.....",
        "...KNNKPNRPNKNNK....",
        "..KNNNKPNWRPKNNNK...",
        "..KNNNKPNRWPKNNNK...",
        "..KNNNKPNWRPKNNNK...",
        "...KNNKPNRWPKNNK....",
        "...KNNKPNWRPKNNK....",
        "....KNKPNRWPKNK.....",
        "....KNKKXKXKKNK.....",
        "....KNNKKKKKNNK.....",
        "....KNNK...KNNK.....",
        "....KNNK...KNNK.....",
        "....KnNK...KnNK.....",
        "....KBBK...KBBK.....",
        "....KBBK...KBBK.....",
        "...KKKKK...KKKKK....",
        "...................."
    )

    private val COMMANDER_AMERICA_CROUCH = listOf(
        "....................",
        "......KKHHHHHK......",
        ".....KHHHHhhhhK.....",
        "....KHHHhhhhhyyK....",
        "....KHHhhhhhhyyK....",
        "....KKhSSSSShhhK....",
        "....KhSGGKGGShhK....",
        "....KhSGPKGPShhK....",
        ".....KhSSSSShhK.....",
        "......KsssssK.......",
        "....KKKPNPNPKKK.....",
        "...KNNKPNRPNKNNK....",
        "..KNNNKPNWRPKNNNK...",
        "..KNNNKPNRWPKNNNK...",
        "..KNNNKPNWRPKNNNK...",
        "...KNNKPNRWPKNNK....",
        "....KNKPNWRPKNK.....",
        "....KNKKXKXKKNK.....",
        "....KNNKKKKKNNK.....",
        "...KNNNK...KNNNK....",
        "...KnNNK...KnNNK....",
        "...KBBBK...KBBBK....",
        "...KKKKK...KKKKK....",
        "....................",
        "....................",
        "...................."
    )

    private val COMMANDER_AMERICA_CAST = listOf(
        "............KSSK....",
        "............KSSK....",
        "...........KNNNK....",
        "......KKHHHHHKNK....",
        ".....KHHHHhhhhKK....",
        "....KHHHhhhhhyyK....",
        "....KHHhhhhhhyyK....",
        "....KKhSSSSShhhK....",
        "....KhSGGKGGShhK....",
        "....KhSGPKGPShhK....",
        ".....KhSSSSShhK.....",
        "......KsssssK.......",
        "....KKKPNPNPKKK.....",
        "...KNNKPNRPNKNNK....",
        "..KNNNKPNWRPKNNNK...",
        "..KNNNKPNRWPKNNNK...",
        "..KNNNKPNWRPKNNNK...",
        "...KNNKPNRWPKNNK....",
        "....KNKPNWRPKNK.....",
        "....KNKKXKXKKNK.....",
        "....KNNKKKKKNNK.....",
        "....KNNK...KNNK.....",
        "....KNNK...KNNK.....",
        "....KBBK...KBBK.....",
        "...KKKKK...KKKKK....",
        "...................."
    )


    // 8. Barack Obama Palette
    private val OBAMA_PALETTE = mapOf(
        '.' to Transparent,
        'K' to DarkOutline,
        'H' to Color(0xFF263238), // Cropped dark hair
        'h' to Color(0xFF37474F), // Hair sheen
        'g' to Color(0xFFB0BEC5), // Silver-grey temples
        'S' to Color(0xFFBCAAA4), // Skin tone
        's' to Color(0xFFA1887F), // Skin shadow
        'E' to Color(0xFF00E5FF), // Glowing blue teleprompter screen
        'P' to WhiteSpec,         // White dress collar
        'C' to Color(0xFF263238), // Tailored presidential charcoal suit
        'c' to Color(0xFF37474F), // Suit highlight fold
        'b' to Color(0xFF102027), // Deep suit shadow
        'T' to Color(0xFF42A5F5), // Light blue tie
        't' to Color(0xFF1E88E5)  // Tie shadow
    )

    private val LOBBYIST_UPRIGHT = listOf(
        "......KKHHHHHK......",
        ".....KHHhhhhhHK.....",
        "....KHHhSSSSShHK....",
        "....KHHSSGGKGShK....",
        "....KHSSGgKGgShK....",
        ".....KHsSSSSshK.....",
        "......KssssssK......",
        "....KKKPPCCPPKKK....",
        "...KCCKPCRRCPKCCK...",
        "..KCCCKPCCrCPKCCCK..",
        "..KCCCKPCRRCPKCCCK..",
        "..KCcCKPCCrCPKCcCK..",
        "..KcCCKPCRRCPKCcCK..",
        "..KCCCKPCCrCPKCCCK..",
        "..KCCCKPCRRCPKCCCK..",
        "..KCcCKPCCrCPKCcCK..",
        "..KCCCKKKKKKKKCCCK..",
        "..KcCKLLLLLLKKCcCK..",
        "..KCCKLXXXXLKKCCCK..",
        "..KCcCKLllllKKCcCK..",
        "....KbbK...KbbK.....",
        "....KbbK...KbbK.....",
        "....KbbK...KbbK.....",
        "....KCCK...KCCK.....",
        "...KKKKK...KKKKK....",
        "...................."
    )

    private val LOBBYIST_CROUCH = listOf(
        ".......KKHHHHHK.....",
        "......KHHhhhhhHK....",
        ".....KHHhSSSSShHK...",
        ".....KHHSSGGKGShK...",
        ".....KHSSGgKGgShK...",
        "......KHsSSSSshK....",
        ".......KssssssK.....",
        ".....KKKPPCCPPKKK...",
        "....KCCKPCRRCPKCCK..",
        "...KCCCKPCCrCPKCCCK.",
        "...KCCCKPCRRCPKCCCK.",
        "...KCcCKPCCrCPKCcCK.",
        "...KcCCKPCRRCPKCcCK.",
        "...KCCCKPCCrCPKCCCK.",
        "...KCCCKPCRRCPKCCCK.",
        "...KCcCKPCCrCPKCcCK.",
        "...KCCCKKKKKKKKCCCK.",
        "...KcCKLLLLLLKKCcCK.",
        "...KCCKLXXXXLKKCCCK.",
        "...KCcCKLllllKKCcCK.",
        ".....KbbK..KbbK.....",
        ".....KbbK..KbbK.....",
        ".....KbbK..KbbK.....",
        ".....KCCK..KCCK.....",
        "....KKKKK..KKKKK....",
        "...................."
    )

    private val PUNDIT_UPRIGHT = listOf(
        ".....EKHHHHHK.......",
        "....EKKHHhhhhK......",
        "...EKHHhSSSSShK.....",
        "...EKHHSOOoOOshK....",
        "...EKHHSOoPOoshK....",
        "....KKHsSSSSshK.....",
        "......KssssssK......",
        "....KKKPPBBPKKK.....",
        "...KBBKPBYYBPKBBK...",
        "..KBBBKPBBYBPKBBBK..",
        "..KBBBKPBBYBPKBBBK..",
        "..KBbBKPBBYBPKBbBK..",
        "..KbBBKPBBYBPKbBBK..",
        "..KBBBKPBBYBPKBBBK..",
        "..KBBBKPBBYBPKBBBK..",
        "..KBbBKKKKKKKBbBK...",
        "..KBBK..KmmK.KBBK...",
        "..KbBK.KMMMMKKbBK...",
        "..KBBK.KMMMMKKBBK...",
        "..KbBK..KMMK.KbBK...",
        "....KdKdK..KdKdK....",
        "....KdKdK..KdKdK....",
        "....KdKdK..KdKdK....",
        "....KBBBK..KBBBK....",
        "...KKKKKK..KKKKKK...",
        "...................."
    )

    private val PUNDIT_CROUCH = listOf(
        "......EKHHHHHK......",
        ".....EKKHHhhhhK.....",
        "....EKHHhSSSSShK....",
        "....EKHHSOOoOOshK...",
        "....EKHHSOoPOoshK...",
        ".....KKHsSSSSshK....",
        ".......KssssssK.....",
        ".....KKKPPBBPKKK....",
        "....KBBKPBYYBPKBBK..",
        "...KBBBKPBBYBPKBBBK.",
        "...KBBBKPBBYBPKBBBK.",
        "...KBbBKPBBYBPKBbBK.",
        "...KbBBKPBBYBPKbBBK.",
        "...KBBBKPBBYBPKBBBK.",
        "...KBBBKPBBYBPKBBBK.",
        "...KBbBKKKKKKKBbBK..",
        "...KBBK..KmmK.KBBK..",
        "...KbBK.KMMMMKKbBK..",
        "...KBBK.KMMMMKKBBK..",
        "...KbBK..KMMK.KbBK..",
        ".....KdKdK.KdKdK....",
        ".....KdKdK.KdKdK....",
        ".....KdKdK.KdKdK....",
        ".....KBBBK.KBBBK....",
        "....KKKKKK.KKKKKK...",
        "...................."
    )

    private val PELOSI_UPRIGHT = listOf(
        "......KHHHHHHK......",
        ".....KHHhhhhhHK.....",
        "....KHHhyyyyyhHK....",
        "....KHHSSSSSSShK....",
        "....KHHSEEKSSEhK....",
        "....KHhSSSSSSShK....",
        ".....KHsPPPPsshK....",
        "......KssssssK.KGgK.",
        "....KKKPOOOOPKKGGGK.",
        "...KOOKPOooOPKOGGGK.",
        "..KOOOKPOddOPKOOgKK.",
        "..KOOOKPOOOOPKOOK...",
        "..KOoOKPOooOPKOoOK..",
        "..KoOOKPOddOPKoOOK..",
        "..KOOOKPOOOOPKOOOK..",
        "..KOoOKKKKKKKOoOK...",
        "..KOOK..KXXK..KOOK..",
        "..KoOK.KXXXXK.KoOK..",
        "..KOOK..KXXK..KOOK..",
        "..KoOK........KoOK..",
        "....KdKdK..KdKdK....",
        "....KdKdK..KdKdK....",
        "....KdKdK..KdKdK....",
        "....KOOOK..KOOOK....",
        "...KKKKKK..KKKKKK...",
        "...................."
    )

    private val PELOSI_CROUCH = listOf(
        ".......KHHHHHHK.....",
        "......KHHhhhhhHK....",
        ".....KHHhyyyyyhHK...",
        ".....KHHSSSSSSShK...",
        ".....KHHSEEKSSEhK...",
        ".....KHhSSSSSSShK...",
        "......KHsPPPPsshK...",
        ".......KssssssKKGgK.",
        ".....KKKPOOOOPKGGGK.",
        "....KOOKPOooOPOGGGK.",
        "...KOOOKPOddOPOOgKK.",
        "...KOOOKPOOOOPKOOK..",
        "...KOoOKPOooOPKOoOK.",
        "...KoOOKPOddOPKoOOK.",
        "...KOOOKPOOOOPKOOOK.",
        "...KOoOKKKKKKKOoOK..",
        "...KOOK..KXXK..KOOK.",
        "...KoOK.KXXXXK.KoOK.",
        "...KOOK..KXXK..KOOK.",
        "...KoOK........KoOK.",
        ".....KdKdK.KdKdK....",
        ".....KdKdK.KdKdK....",
        ".....KdKdK.KdKdK....",
        ".....KOOOK.KOOOK....",
        "....KKKKKK.KKKKKK...",
        "...................."
    )

    private val ROCKET_MAN_UPRIGHT = listOf(
        ".....KHHHHHHHHHK....",
        ".....KHHHHHHHHHK....",
        "....KhhhhhhhhhhhK...",
        "....KhhSSSSSSShhK...",
        "....KhsSEEKSSEshK...",
        "....KhSSSSSSSSShK...",
        ".....KhssssssshK....",
        "......KsssssssK.KFK.",
        "....KKKNNRRNKKKKMFK.",
        "...KNNKNNnnNNKNNYKK.",
        "..KNNNKNNBBNNKNNNK..",
        "..KNNNKNNNNNNKNNNK..",
        "..KNnNKNNnnNNKNnNK..",
        "..KnNNKNNBBNNKnNNK..",
        "..KNNNKNNNNNNKNNNK..",
        "..KNnNKKKKKKKKNnNK..",
        "..KNNK........KNNK..",
        "..KnNK........KnNK..",
        "..KNNK........KNNK..",
        "..KnNK........KnNK..",
        "....KBbBK..KBbBK....",
        "....KBbBK..KBbBK....",
        "....KBbBK..KBbBK....",
        "....KNNNK..KNNNK....",
        "...KKKKKK..KKKKKK...",
        "...................."
    )

    private val ROCKET_MAN_CROUCH = listOf(
        "......KHHHHHHHHHK...",
        "......KHHHHHHHHHK...",
        ".....KhhhhhhhhhhhK..",
        ".....KhhSSSSSSShhK..",
        ".....KhsSEEKSSEshK..",
        ".....KhSSSSSSSSShK..",
        "......KhssssssshKFK.",
        ".......KsssssssKMFK.",
        ".....KKKNNRRNKKNYKK.",
        "....KNNKNNnnNNKNNNK.",
        "...KNNNKNNBBNNKNNNK.",
        "...KNNNKNNNNNNKNNNK.",
        "...KNnNKNNnnNNKNnNK.",
        "...KnNNKNNBBNNKnNNK.",
        "...KNNNKNNNNNNKNNNK.",
        "...KNnNKKKKKKKKNnNK.",
        "...KNNK........KNNK.",
        "...KnNK........KnNK.",
        "...KNNK........KNNK.",
        "...KnNK........KnNK.",
        ".....KBbBK.KBbBK....",
        ".....KBbBK.KBbBK....",
        ".....KBbBK.KBbBK....",
        ".....KNNNK.KNNNK....",
        "....KKKKKK.KKKKKK...",
        "...................."
    )

    private val KAMALA_UPRIGHT = listOf(
        "...KYK..KHHHHHK..KBK",
        "..KYYYK.KHHhhhhKKBBK",
        "...KYK.KHHhSSSSShKBK",
        ".....KHHhSEEKSEhK...",
        ".....KHHSSSSSSSWhK..",
        "......KHsPPPPsshK...",
        ".......KssssssK.....",
        "....KKKPVVVVPKKK....",
        "...KVVKPVvvVPKVVK...",
        "..KVVVKPVddVPKVVVK..",
        "..KVVVKPVVVVPKVVVK..",
        "..KVvVKPVvvVPKVvVK..",
        "..KvVVKPVddVPKvVVK..",
        "..KVVVKPVVVVPKVVVK..",
        "..KVvVKKKKKKKKVvVK..",
        "..KVVK..KYBK..KVVK..",
        "..KvVK.KYYBBK.KvVK..",
        "..KVVK..KYBK..KVVK..",
        "..KvVK........KvVK..",
        "....KdKdK..KdKdK....",
        "....KdKdK..KdKdK....",
        "....KdKdK..KdKdK....",
        "....KVVVK..KVVVK....",
        "...KKKKKK..KKKKKK...",
        "....................",
        "...................."
    )

    private val KAMALA_CROUCH = listOf(
        "...KYK..KHHHHHK..KBK",
        "..KYYYKKHHhhhhKKBBK.",
        "...KYK.KHhSSSSShKBK.",
        ".....KHHhSEEKSEhK...",
        ".....KHHSSSSSSSWhK..",
        "......KHsPPPPsshK...",
        ".......KssssssK.....",
        ".....KKKPVVVVPKKK...",
        "....KVVKPVvvVPKVVK..",
        "...KVVVKPVddVPKVVVK.",
        "...KVVVKPVVVVPKVVVK.",
        "...KVvVKPVvvVPKVvVK.",
        "...KvVVKPVddVPKvVVK.",
        "...KVVVKPVVVVPKVVVK.",
        "...KVvVKKKKKKKKVvVK.",
        "...KVVK..KYBK..KVVK.",
        "...KvVK.KYYBBK.KvVK.",
        "...KVVK..KYBK..KVVK.",
        "...KvVK........KvVK.",
        ".....KdKdK.KdKdK....",
        ".....KdKdK.KdKdK....",
        ".....KdKdK.KdKdK....",
        ".....KVVVK.KVVVK....",
        "....KKKKKK.KKKKKK...",
        "....................",
        "...................."
    )

    private val BIDEN_UPRIGHT = listOf(
        "......KKHHHHHK......",
        ".....KHHHHhhhhK.KVvK",
        "....KHHhSSSSShHKVVVK",
        "....KHHSSGGKGShKVvVK",
        "....KHSSGgKGgShK.KcK",
        ".....KHsSSSSshK..KcK",
        "......KssssssK...KCK",
        "....KKKPNPNPKKK..KcK",
        "...KNNKPNTTPKNNK.KCK",
        "..KNNNKPNNtPKNNNK...",
        "..KNNNKPNTTPKNNNK...",
        "..KNnNKPNNtPKNnNK...",
        "..KnNNKPNTTPKnNNK...",
        "..KNNNKPNNtPKNNNK...",
        "..KNNNKPNTTPKNNNK...",
        "..KNnNKKKKKKKKNnNK..",
        "..KNNK........KNNK..",
        "..KnNK........KnNK..",
        "..KNNK........KNNK..",
        "..KnNK........KnNK..",
        "....KbbK...KbbK.....",
        "....KbbK...KbbK.....",
        "....KbbK...KbbK.....",
        "....KNNK...KNNK.....",
        "...KKKKK...KKKKK....",
        "...................."
    )

    private val BIDEN_CROUCH = listOf(
        ".......KKHHHHHK.KVvK",
        "......KHHHHhhhhKVVVK",
        ".....KHHhSSSSShKVvVK",
        ".....KHHSSGGKGShKKcK",
        ".....KHSSGgKGgShKKcK",
        "......KHsSSSSshK.KCK",
        ".......KssssssK..KcK",
        ".....KKKPNPNPKKK.KCK",
        "....KNNKPNTTPKNNKK..",
        "...KNNNKPNNtPKNNNK..",
        "...KNNNKPNTTPKNNNK..",
        "...KNnNKPNNtPKNnNK..",
        "...KnNNKPNTTPKnNNK..",
        "...KNNNKPNNtPKNNNK..",
        "...KNNNKPNTTPKNNNK..",
        "...KNnNKKKKKKKKNnNK.",
        "...KNNK........KNNK.",
        "...KnNK........KnNK.",
        "...KNNK........KNNK.",
        "...KnNK........KnNK.",
        ".....KbbK..KbbK.....",
        ".....KbbK..KbbK.....",
        ".....KbbK..KbbK.....",
        ".....KNNK..KNNK.....",
        "....KKKKK..KKKKK....",
        "...................."
    )

    private val BALLOT_UPRIGHT = listOf(
        ".....KKKKKKKKKK.....",
        "....KCCCCCCCCCCK....",
        "...KCCCCCCCCCCCCK...",
        "..KCCKKKKKKKKKKCCK..",
        "..KCCK..KE..KEKCCK..",
        "..KCCTTTTTTTTTTCcK..",
        "..KCCK.KR.RR.K.CCcK.",
        "..KcCK.KRrrR.K.CcCK.",
        "..KcCK.KRrrR.K.CCcK.",
        "...KCCKKKKKKKKCCCK..",
        "..KKCCCCCCCCCCCCKK..",
        ".KCCCCCCCCCCCCCCCCK.",
        ".KCCTTTTTTTTTTTTCCK.",
        ".KCCK..KW.BW..K.CcK.",
        ".KcCK..KWWWW..K.CcK.",
        ".KcCK..KWWWW..K.CCcK",
        ".KCcK..KWWWW..K.CCcK",
        ".KCCKKKKKKKKKKKKCCCK",
        "..KKCCCCCCCCCCCCKK..",
        "...KCCCCCCCCCCCCK...",
        "...KCCTTTTTTTTCCK...",
        "...KcCK......KCcK...",
        "...KcCK......KCcK...",
        "...KCCK......KCCK...",
        "..KKKKK......KKKKK..",
        "...................."
    )

    private val BALLOT_CROUCH = listOf(
        "......KKKKKKKKKK....",
        ".....KCCCCCCCCCCK...",
        "....KCCCCCCCCCCCCK..",
        "...KCCKKKKKKKKKKCCK.",
        "...KCCK..KE..KEKCCK.",
        "...KCCTTTTTTTTTTCcK.",
        "...KCCK.KR.RR.K.CCcK",
        "...KcCK.KRrrR.K.CcCK",
        "...KcCK.KRrrR.K.CCcK",
        "....KCCKKKKKKKKCCCK.",
        "...KKCCCCCCCCCCCCKK.",
        "..KCCCCCCCCCCCCCCCCK",
        "..KCCTTTTTTTTTTTTCCK",
        "..KCCK..KW.BW..K.CcK",
        "..KcCK..KWWWW..K.CcK",
        "...KcCK..KWWWW..KCcK",
        "...KCcK..KWWWW..KCcK",
        "...KCCKKKKKKKKKKCCCK",
        "...KKCCCCCCCCCCCCKK.",
        "....KCCCCCCCCCCCCK..",
        "....KCCTTTTTTTTCCK..",
        "....KcCK.....KCcK...",
        "....KcCK.....KCcK...",
        "....KCCK.....KCCK...",
        "...KKKKK.....KKKKK..",
        "...................."
    )

    private val OBAMA_UPRIGHT = listOf(
        ".KEK..KKHHHHHK...KEK",
        ".KEK.KHHhhhhhHK..KEK",
        ".....KHHhggghhHK....",
        "....KHHhSSSSShHK....",
        "....KHHsSEEKSshK....",
        ".....KHSSSSSSshK....",
        "......KHsSSSSshK....",
        ".......KssssssK.....",
        "....KKKPPCCPPKKK....",
        "...KCCKPCRTCPKCCK...",
        "..KCCCKPCCtCPKCCCK..",
        "..KCCCKPCRTCPKCCCK..",
        "..KCcCKPCCtCPKCcCK..",
        "..KcCCKPCRTCPKCcCK..",
        "..KCCCKPCCtCPKCCCK..",
        "..KCcCKKKKKKKKCcCK..",
        "..KCCCK......KCCCK..",
        "..KcCK........KcCK..",
        "..KCCK........KCCK..",
        "..KcCK........KcCK..",
        "....KbbK...KbbK.....",
        "....KbbK...KbbK.....",
        "....KbbK...KbbK.....",
        "....KCCK...KCCK.....",
        "...KKKKK...KKKKK....",
        "...................."
    )

    private val OBAMA_CROUCH = listOf(
        "..KEK.KKHHHHHK..KEK.",
        "..KEKKHHhhhhhHK.KEK.",
        "....KHHhggghhHK.....",
        "....KHHhSSSSShHK....",
        "....KHHsSEEKSshK....",
        ".....KHSSSSSSshK....",
        "......KHsSSSSshK....",
        ".......KssssssK.....",
        ".....KKKPPCCPPKKK...",
        "....KCCKPCRTCPKCCK..",
        "...KCCCKPCCtCPKCCCK.",
        "...KCCCKPCRTCPKCCCK.",
        "...KCcCKPCCtCPKCcCK.",
        "...KcCCKPCRTCPKCcCK.",
        "...KCCCKPCCtCPKCCCK.",
        "...KCcCKKKKKKKKCcCK.",
        "...KCCCK......KCCCK.",
        "...KcCK........KcCK.",
        "...KCCK........KCCK.",
        "...KcCK........KcCK.",
        ".....KbbK..KbbK.....",
        ".....KbbK..KbbK.....",
        ".....KbbK..KbbK.....",
        ".....KCCK..KCCK.....",
        "....KKKKK..KKKKK....",
        "...................."
    )

    fun getPixelMatrix(
        characterId: String,
        frame: SpriteFrame
    ): Pair<List<String>, Map<Char, Color>> {
        val lowerId = characterId.lowercase()
        return when {
            // Hero / Commander America
            lowerId == "hero" || lowerId.contains("commander") || lowerId.contains("america") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> COMMANDER_AMERICA_UPRIGHT
                    SpriteFrame.IDLE_CROUCH -> COMMANDER_AMERICA_CROUCH
                    SpriteFrame.ACTION_CAST -> COMMANDER_AMERICA_CAST
                    SpriteFrame.DAMAGED -> COMMANDER_AMERICA_CROUCH
                }
                grid to COMMANDER_AMERICA_PALETTE
            }

            // Ally Elon
            lowerId.contains("elon") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> COMMANDER_AMERICA_UPRIGHT
                    SpriteFrame.IDLE_CROUCH -> COMMANDER_AMERICA_CROUCH
                    SpriteFrame.ACTION_CAST -> COMMANDER_AMERICA_CAST
                    SpriteFrame.DAMAGED -> COMMANDER_AMERICA_CROUCH
                }
                grid to COMMANDER_AMERICA_PALETTE
            }

            // Companion: Marco Rubio (Sanctions Sorcerer)
            lowerId.contains("marco") || lowerId.contains("rubio") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> COMMANDER_AMERICA_UPRIGHT
                    SpriteFrame.IDLE_CROUCH -> COMMANDER_AMERICA_CROUCH
                    SpriteFrame.ACTION_CAST -> COMMANDER_AMERICA_CAST
                    SpriteFrame.DAMAGED -> COMMANDER_AMERICA_CROUCH
                }
                grid to MARCO_PALETTE
            }

            // Companion: JD Vance (Rustbelt Vanguard)
            lowerId.contains("vance") || lowerId.contains("jd") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> COMMANDER_AMERICA_UPRIGHT
                    SpriteFrame.IDLE_CROUCH -> COMMANDER_AMERICA_CROUCH
                    SpriteFrame.ACTION_CAST -> COMMANDER_AMERICA_CAST
                    SpriteFrame.DAMAGED -> COMMANDER_AMERICA_CROUCH
                }
                grid to VANCE_PALETTE
            }

            // Companion: Kash Patel (Intel Inquisitor)
            lowerId.contains("kash") || lowerId.contains("patel") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> COMMANDER_AMERICA_UPRIGHT
                    SpriteFrame.IDLE_CROUCH -> COMMANDER_AMERICA_CROUCH
                    SpriteFrame.ACTION_CAST -> COMMANDER_AMERICA_CAST
                    SpriteFrame.DAMAGED -> COMMANDER_AMERICA_CROUCH
                }
                grid to KASH_PALETTE
            }

            // Companion: Senator John Kennedy (Committee Cross-Examiner)
            lowerId.contains("kennedy") || lowerId.contains("john_kennedy") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> COMMANDER_AMERICA_UPRIGHT
                    SpriteFrame.IDLE_CROUCH -> COMMANDER_AMERICA_CROUCH
                    SpriteFrame.ACTION_CAST -> COMMANDER_AMERICA_CAST
                    SpriteFrame.DAMAGED -> COMMANDER_AMERICA_CROUCH
                }
                grid to KENNEDY_PALETTE
            }


            // Barack Obama (The Teleprompter Sorcerer)
            lowerId.contains("obama") || lowerId.contains("barack") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> OBAMA_UPRIGHT
                    else -> OBAMA_CROUCH
                }
                grid to OBAMA_PALETTE
            }

            // Boss 1: Madam Pelosi (The Stock Lich)
            lowerId.contains("pelosi") || (lowerId.contains("lich") && lowerId.contains("stock")) -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> PELOSI_UPRIGHT
                    else -> PELOSI_CROUCH
                }
                grid to PELOSI_PALETTE
            }

            // Boss 2: Chairman Rocket Man / Missile Battery
            lowerId.contains("rocket") || lowerId.contains("missile") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> ROCKET_MAN_UPRIGHT
                    else -> ROCKET_MAN_CROUCH
                }
                grid to ROCKET_MAN_PALETTE
            }

            // Boss 3: Kamala the Unburdened / Venn Loops
            lowerId.contains("kamala") || lowerId.contains("unburdened") || lowerId.contains("venn") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> KAMALA_UPRIGHT
                    else -> KAMALA_CROUCH
                }
                grid to KAMALA_PALETTE
            }

            // Final Boss: Biden the Malarkey Sovereign
            lowerId.contains("biden") || lowerId.contains("malarkey") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> BIDEN_UPRIGHT
                    else -> BIDEN_CROUCH
                }
                grid to BIDEN_PALETTE
            }

            // Golems / Bureaucratic Monsters: 3 AM Ballot Golem / Ice Cream Golem / Subpoena Swarm / Phantom Mueller
            lowerId.contains("ballot") || lowerId.contains("golem") || lowerId.contains("gargoyle") ||
                    lowerId.contains("subpoena") || lowerId.contains("mueller") || lowerId.contains("teleprompter") ||
                    lowerId.contains("pipe") || lowerId.contains("cone") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> BALLOT_UPRIGHT
                    else -> BALLOT_CROUCH
                }
                grid to BALLOT_GOLEM_PALETTE
            }

            // Cable News / Fact-Checkers / Media Pundits / Spectres
            lowerId.contains("pundit") || lowerId.contains("fact") || lowerId.contains("checker") ||
                    lowerId.contains("anchor") || lowerId.contains("media") || lowerId.contains("news") ||
                    lowerId.contains("spectre") -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> PUNDIT_UPRIGHT
                    else -> PUNDIT_CROUCH
                }
                grid to PUNDIT_PALETTE
            }

            // K-Street Lobbyists / Lawfare Inquisitors / Deep State Bureaucrats / Anonymous Sources (Default Bad Guys)
            else -> {
                val grid = when (frame) {
                    SpriteFrame.IDLE_UPRIGHT -> LOBBYIST_UPRIGHT
                    else -> LOBBYIST_CROUCH
                }
                grid to LOBBYIST_PALETTE
            }
        }
    }
}
