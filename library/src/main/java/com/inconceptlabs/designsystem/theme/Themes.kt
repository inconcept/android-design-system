package com.inconceptlabs.designsystem.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.inconceptlabs.designsystem.theme.colors.ColorScheme
import com.inconceptlabs.designsystem.theme.typography.Barlow
import com.inconceptlabs.designsystem.theme.typography.Typography

private val LocalColors = staticCompositionLocalOf<ColorScheme> { ColorScheme.Default }

private val LocalTypography = staticCompositionLocalOf { Typography.Default }

val LocalTextStyle = staticCompositionLocalOf { TextStyle.Default }

val LocalContentColor = staticCompositionLocalOf { Color.White }

val LocalIconSize = staticCompositionLocalOf { Dp.Unspecified }

/**
 * Contains functions to access the current theme values provided
 * at the call site's position in the hierarchy.
 */
object AppTheme {

    /**
     * Retrieves the current [ColorScheme][ColorScheme]
     * at the call site's position in the hierarchy.
     */
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current


    /**
     * Retrieves the current [Typography][Typography]
     * at the call site's position in the hierarchy.
     */
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

/**
 * Application theme configuration that sets up the color scheme, typography, and system UI for the app.
 *
 * @param colorScheme [ColorScheme] defining the color scheme for the app.
 * @param typography [Typography] defining the typography for the app.
 * @param indication [Indication] type for the theme.
 * @param content The content block to be displayed with the defined theme.
 */
@Composable
fun AppTheme(
    colorScheme: ColorScheme = ColorScheme.Light,
    typography: Typography = Barlow,
    indication: Indication = NoIndication,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colorScheme,
        LocalTypography provides typography,
        LocalIndication provides indication,
    ) {
        content()
    }
}

/**
 * Composable that adjusts the system status bar color to the specified color.
 *
 * @param color Color to set for the system status bar.
 */
@Composable
fun StatusBarColor(color: Color = Color.White) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color = color)
    }
}