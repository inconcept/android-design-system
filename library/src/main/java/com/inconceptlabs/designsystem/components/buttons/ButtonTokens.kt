package com.inconceptlabs.designsystem.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.attributes.Size

val LocalButtonTokens = compositionLocalOf { ButtonTokens.Default }
val LocalIconButtonTokens = compositionLocalOf { ButtonTokens.Icon }

data class ButtonTokens(
    val contentPadding: (Size) -> PaddingValues = Companion::contentPadding,
    val cornerRadius: (Size) -> Dp = Companion::cornerRadius,
    val minWidth: (Size) -> Dp = Companion::minWidth,
    val iconSize: (Size) -> Dp = Companion::iconSize,
) {

    companion object {

        internal val Default = ButtonTokens()

        internal val Icon = Default.copy(
            contentPadding = {
                when (it) {
                    Size.XXS -> PaddingValues(4.dp)
                    Size.XS -> PaddingValues(9.dp)
                    Size.S -> PaddingValues(10.dp)
                    Size.M -> PaddingValues(13.dp)
                    Size.L -> PaddingValues(16.dp)
                }
            }
        )

        private fun contentPadding(size: Size): PaddingValues = when (size) {
            Size.XXS -> PaddingValues(horizontal = 8.dp)
            Size.XS -> PaddingValues(horizontal = 14.dp)
            Size.S -> PaddingValues(horizontal = 16.dp)
            Size.M -> PaddingValues(horizontal = 18.dp)
            Size.L -> PaddingValues(horizontal = 20.dp)
        }

        private fun cornerRadius(size: Size): Dp = when (size) {
            Size.XXS -> 10.dp
            Size.XS -> 12.dp
            Size.S -> 14.dp
            Size.M -> 16.dp
            Size.L -> 18.dp
        }

        private fun minWidth(size: Size): Dp = when (size) {
            Size.XXS -> 70.dp
            Size.XS -> 80.dp
            Size.S -> 90.dp
            Size.M -> 100.dp
            Size.L -> 120.dp
        }

        private fun iconSize(size: Size): Dp = when (size) {
            Size.XXS -> 16.dp
            Size.XS -> 18.dp
            Size.S -> 20.dp
            Size.M -> 22.dp
            Size.L -> 24.dp
        }
    }
}