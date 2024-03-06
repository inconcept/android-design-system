package com.inconceptlabs.designsystem.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Represents the brand's color palette, forming a fundamental part of the design system.
 * Contains various color representations utilized throughout the application.
 */
@Suppress("PropertyName")
@Immutable
interface ColorScheme {

    val primary: PaletteColors
    val secondary: PaletteColors
    val tertiary: PaletteColors

    val success: PaletteColors
    val error: PaletteColors
    val warning: PaletteColors
    val info: PaletteColors
    val premium: PaletteColors
    val new: PaletteColors

    val BG1: Color
    val BG2: Color
    val BG3: Color
    val BG4: Color
    val BG5: Color
    val BG6: Color
    val BG7: Color
    val BG8: Color
    val BG9: Color
    val BG10: Color

    val T1: Color
    val T2: Color
    val T3: Color
    val T4: Color
    val T5: Color
    val T6: Color
    val T7: Color
    val T8: Color
    val T9: Color
    val T10: Color
    val T11: Color
    val T12: Color

    companion object {

        val Default = object : ColorScheme {

            override val primary = PaletteColors()
            override val secondary = PaletteColors()
            override val tertiary = PaletteColors()
            override val success = PaletteColors()
            override val error = PaletteColors()
            override val warning = PaletteColors()
            override val info = PaletteColors()
            override val premium = PaletteColors()
            override val new = PaletteColors()

            override val BG1 = Color.Unspecified
            override val BG2 = Color.Unspecified
            override val BG3 = Color.Unspecified
            override val BG4 = Color.Unspecified
            override val BG5 = Color.Unspecified
            override val BG6 = Color.Unspecified
            override val BG7 = Color.Unspecified
            override val BG8 = Color.Unspecified
            override val BG9 = Color.Unspecified
            override val BG10 = Color.Unspecified

            override val T1 = Color.Unspecified
            override val T2 = Color.Unspecified
            override val T3 = Color.Unspecified
            override val T4 = Color.Unspecified
            override val T5 = Color.Unspecified
            override val T6 = Color.Unspecified
            override val T7 = Color.Unspecified
            override val T8 = Color.Unspecified
            override val T9 = Color.Unspecified
            override val T10 = Color.Unspecified
            override val T11 = Color.Unspecified
            override val T12 = Color.Unspecified
        }

        val Light = object : ColorScheme {

            override val primary = PaletteColors(Green100, Green200, Green300, Green400, Green500, Green600)
            override val secondary = PaletteColors(Orange100, Orange200, Orange300, Orange400, Orange500, Orange600)
            override val tertiary = PaletteColors(Yellow100, Yellow200, Yellow300, Yellow400, Yellow500, Yellow600)
            override val success = PaletteColors(Green150a, Green300a, Green370a, Green450a, Green500a, Green650a)
            override val error = PaletteColors(Red150a, Red250a, Red350a, Red450a, Red500a, Red700a)
            override val warning = PaletteColors(Yellow150a, Yellow300a, Yellow350a, Yellow450a, Yellow500a, Yellow700a)
            override val info = PaletteColors(Gray110a, Gray130a, Gray150a, Gray200a, Gray220a, Gray240a)
            override val premium = PaletteColors(Blue150a, Blue300a, Blue350a, Blue450a, Blue500a, Blue700a)
            override val new = PaletteColors(Red100, Red200, Red300, Red400, Red500, Red600)

            override val BG1 = Gray50
            override val BG2 = Gray100
            override val BG3 = Gray140
            override val BG4 = Gray160
            override val BG5 = Gray190
            override val BG6 = Gray250
            override val BG7 = Gray280
            override val BG8 = Navy200
            override val BG9 = Navy300
            override val BG10 = Navy700

            override val T1 = Color.White
            override val T2 = Gray180
            override val T3 = Gray210
            override val T4 = Gray230
            override val T5 = Gray260
            override val T6 = Gray270
            override val T7 = Gray480
            override val T8 = Navy100
            override val T9 = Indigo800
            override val T10 = Gray700
            override val T11 = Gray800
            override val T12 = Gray970
        }
    }
}