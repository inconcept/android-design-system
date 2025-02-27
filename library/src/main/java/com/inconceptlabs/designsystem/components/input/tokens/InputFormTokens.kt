package com.inconceptlabs.designsystem.components.input.tokens

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.theme.attributes.Size

val LocalInputFormTokens = compositionLocalOf { InputFormTokens.Default }

interface InputFormTokens {

    val horizontalPadding: Dp

    val startIconPadding: Dp

    val endIconPadding: Dp

    fun cornerRadius(size: Size): Dp

    fun titleIconSize(size: Size): Dp

    fun formIconSize(size: Size): Dp

    companion object {

        val Default: InputFormTokens = InputFormTokensImpl
    }
}