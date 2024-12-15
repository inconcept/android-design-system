package com.inconceptlabs.designsystem.components.input

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.attributes.Size

val LocalInputFormTokens = compositionLocalOf { InputFormTokens.Default }

data class InputFormTokens(
    val horizontalPadding: Dp = 16.dp,

    val startIconPadding: Dp = 10.dp,
    val endIconPadding: Dp = 8.dp,

    val cornerRadius: (Size) -> Dp = Companion::cornerRadius,

    val titleIconSize: (Size) -> Dp = Companion::titleIconSize,
    val formIconSize: (Size) -> Dp = Companion::formIconSize,
) {

    companion object {

        internal val Default = InputFormTokens()

        private fun cornerRadius(size: Size): Dp {
            return when (size) {
                Size.XXS,
                Size.XS -> 12.dp
                Size.S -> 14.dp
                Size.M -> 16.dp
                Size.L -> 18.dp
            }
        }

        private fun titleIconSize(size: Size): Dp {
            return when (size) {
                Size.XXS,
                Size.XS,
                Size.S -> 16.dp
                Size.M,
                Size.L -> 18.dp
            }
        }

        private fun formIconSize(size: Size): Dp {
            return when (size) {
                Size.XXS,
                Size.XS -> 16.dp
                Size.S -> 18.dp
                Size.M -> 20.dp
                Size.L -> 22.dp
            }
        }
    }
}