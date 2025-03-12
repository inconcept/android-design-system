package com.inconceptlabs.designsystem.components.input.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.components.InputFormState
import com.inconceptlabs.designsystem.components.input.InputFormSize
import com.inconceptlabs.designsystem.components.input.InputFormType
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

typealias State = InputFormState
typealias Type = InputFormType

val LocalInputFormTokens = compositionLocalOf { InputFormTokens.Default }

interface InputFormTokens {

    val horizontalPadding: Dp

    val startIconPadding: Dp

    val endIconPadding: Dp

    /**
     * Compose currently doesn't provide a way of customizing the cursor's height
     * So we need to create a custom cursor brush to achieve the desired effect
     */
    val cursorBrush: Brush

    fun height(size: InputFormSize): Dp

    fun cornerRadius(size: InputFormSize): Dp

    fun titleIconSize(size: InputFormSize): Dp

    fun formIconSize(size: InputFormSize): Dp

    @Composable
    fun titleTypography(size: InputFormSize): TextStyle

    @Composable
    fun hintTypography(size: InputFormSize): TextStyle

    @Composable
    fun inputTypography(size: InputFormSize): TextStyle

    @Composable
    fun strokeColor(type: Type): Color

    @Composable
    fun backgroundColor(type: Type): Color

    @Composable
    fun inputColor(): Color

    @Composable
    fun hintColor(): Color

    @Composable
    fun startIconColor(palette: PaletteColors): Color

    @Composable
    fun endIconColor(): Color

    @Composable
    fun additionalTextColor(): Color

    companion object {

        val Default: InputFormTokens = InputFormTokensImpl
    }
}