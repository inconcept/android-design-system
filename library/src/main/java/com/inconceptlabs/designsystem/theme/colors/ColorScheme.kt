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

        val Light: ColorScheme = LightColorScheme
    }
}