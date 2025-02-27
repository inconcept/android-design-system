package com.inconceptlabs.designsystem.components.input.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.components.InputFormState
import com.inconceptlabs.designsystem.components.input.InputFormType
import com.inconceptlabs.designsystem.theme.attributes.Size
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

    fun height(size: Size): Dp

    fun cornerRadius(size: Size): Dp

    fun titleIconSize(size: Size): Dp

    fun formIconSize(size: Size): Dp

    @Composable
    fun titleTypography(size: Size): TextStyle

    @Composable
    fun hintTypography(size: Size, state: State): TextStyle

    @Composable
    fun inputTypography(size: Size, state: State): TextStyle

    @Composable
    fun strokeColor(type: Type, state: State): Color

    @Composable
    fun backgroundColor(type: Type, state: State): Color

    @Composable
    fun inputColor(state: State): Color

    @Composable
    fun hintColor(state: State): Color

    @Composable
    fun startIconColor(state: State, palette: PaletteColors): Color

    @Composable
    fun endIconColor(state: State): Color

    @Composable
    fun additionalTextColor(state: State): Color

    companion object {

        val Default: InputFormTokens = InputFormTokensImpl
    }
}