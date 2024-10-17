package com.inconceptlabs.designsystem.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.components.buttons.LocalButtonTokens
import com.inconceptlabs.designsystem.components.buttons.LocalIconButtonTokens
import com.inconceptlabs.designsystem.components.core.LocalCoreTokens
import com.inconceptlabs.designsystem.components.emptyitem.LocalEmptyItemTokens
import com.inconceptlabs.designsystem.components.input.LocalInputFormTokens
import com.inconceptlabs.designsystem.components.input.LocalPasswordInputFormTokens
import com.inconceptlabs.designsystem.theme.colors.ColorScheme
import com.inconceptlabs.designsystem.theme.indication.NoIndication
import com.inconceptlabs.designsystem.theme.typography.Barlow
import com.inconceptlabs.designsystem.theme.typography.Typography

private val LocalColorScheme = compositionLocalOf { ColorScheme.Default }
private val LocalTypography = compositionLocalOf { Typography.Default }

val LocalTextStyle = compositionLocalOf { TextStyle.Default }
val LocalContentColor = compositionLocalOf { Color.White }
val LocalIconSize = compositionLocalOf { Dp.Unspecified }

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
        get() = LocalColorScheme.current


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
    tokens: ThemeTokens = ThemeTokens.Default,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography,
        LocalIndication provides indication,

        LocalCoreTokens provides tokens.core,
        LocalButtonTokens provides tokens.button,
        LocalIconButtonTokens provides tokens.iconButton,
        LocalInputFormTokens provides tokens.inputForm,
        LocalPasswordInputFormTokens provides tokens.passwordInputForm,
        LocalEmptyItemTokens provides tokens.emptyItem,
    ) {
        content()
    }
}