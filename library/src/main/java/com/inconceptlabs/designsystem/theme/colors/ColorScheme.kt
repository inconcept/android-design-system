package com.inconceptlabs.designsystem.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Represents the brand's color palette, forming a fundamental part of the design system.
 * Contains various color representations utilized throughout the application.
 */
@Suppress("PropertyName")
@Immutable
sealed class ColorScheme(
    val primary: PaletteColors = PaletteColors(),
    val secondary: PaletteColors = PaletteColors(),
    val tertiary: PaletteColors = PaletteColors(),

    val success: PaletteColors = PaletteColors(),
    val error: PaletteColors = PaletteColors(),
    val warning: PaletteColors = PaletteColors(),
    val info: PaletteColors = PaletteColors(),
    val premium: PaletteColors = PaletteColors(),
    val new: PaletteColors = PaletteColors(),

    val BG1: Color = Color.Unspecified,
    val BG2: Color = Color.Unspecified,
    val BG3: Color = Color.Unspecified,
    val BG4: Color = Color.Unspecified,
    val BG5: Color = Color.Unspecified,
    val BG6: Color = Color.Unspecified,
    val BG7: Color = Color.Unspecified,
    val BG8: Color = Color.Unspecified,
    val BG9: Color = Color.Unspecified,
    val BG10: Color = Color.Unspecified,

    val T1: Color = Color.Unspecified,
    val T2: Color = Color.Unspecified,
    val T3: Color = Color.Unspecified,
    val T4: Color = Color.Unspecified,
    val T5: Color = Color.Unspecified,
    val T6: Color = Color.Unspecified,
    val T7: Color = Color.Unspecified,
    val T8: Color = Color.Unspecified,
    val T9: Color = Color.Unspecified,
    val T10: Color = Color.Unspecified,
    val T11: Color = Color.Unspecified,
    val T12: Color = Color.Unspecified,
) {

    data object Default : ColorScheme()

    data object Light : ColorScheme(
        primary = PaletteColors(Green100, Green200, Green300, Green400, Green500, Green600),
        secondary = PaletteColors(Orange100, Orange200, Orange300, Orange400, Orange500, Orange600),
        tertiary = PaletteColors(Yellow100, Yellow200, Yellow300, Yellow400, Yellow500, Yellow600),
        success = PaletteColors(Green150a, Green300a, Green370a, Green450a, Green500a, Green650a),
        error = PaletteColors(Red150a, Red250a, Red350a, Red450a, Red500a, Red700a),
        warning = PaletteColors(Yellow150a, Yellow300a, Yellow350a, Yellow450a, Yellow500a, Yellow700a),
        info = PaletteColors(Gray110a, Gray130a, Gray150a, Gray200a, Gray220a, Gray240a),
        premium = PaletteColors(Blue150a, Blue300a, Blue350a, Blue450a, Blue500a, Blue700a),
        new = PaletteColors(Red100, Red200, Red300, Red400, Red500, Red600),

        BG1 = Gray50,
        BG2 = Gray100,
        BG3 = Gray140,
        BG4 = Gray160,
        BG5 = Gray190,
        BG6 = Gray250,
        BG7 = Gray280,
        BG8 = Navy200,
        BG9 = Navy300,
        BG10 = Navy700,

        T1 = Color.White,
        T2 = Gray180,
        T3 = Gray210,
        T4 = Gray230,
        T5 = Gray260,
        T6 = Gray270,
        T7 = Gray480,
        T8 = Navy100,
        T9 = Indigo800,
        T10 = Gray700,
        T11 = Gray800,
        T12 = Gray970,
    )
}