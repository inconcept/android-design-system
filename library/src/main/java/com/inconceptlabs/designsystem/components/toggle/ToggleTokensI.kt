package com.inconceptlabs.designsystem.components.toggle

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.core.LocalCoreTokens
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

val LocalToggleTokens = compositionLocalOf { ToggleTokensI.Default }

interface ToggleTokensI {

    fun requiredSize(size: ToggleSize): DpSize
    fun contentPadding(size: ToggleSize): PaddingValues

    fun cornerRadius(size: ToggleSize): Dp

    fun iconSize(size: ToggleSize): Dp

    @Composable
    fun strokeWidth(size: ToggleSize): Dp

    @Composable
    fun textStyle(size: ToggleSize): TextStyle

    @Composable
    fun containerColor(palette: PaletteColors): Color

    @Composable
    fun strokeColor(palette: PaletteColors): Color

    @Composable
    fun contentColor(palette: PaletteColors): Color

    companion object {

        val Default: ToggleTokensI = ToggleTokensImpl
    }

    object ToggleTokensImpl : ToggleTokensI {

        override fun requiredSize(size: ToggleSize): DpSize {
            return when (size) {
                ToggleSize.XS -> DpSize(width = 52.dp, height = 32.dp)
                ToggleSize.S -> DpSize(width = 58.dp, height = 36.dp)
            }
        }

        override fun contentPadding(size: ToggleSize): PaddingValues {
            return when (size) {
                ToggleSize.XS -> PaddingValues(horizontal = 14.dp)
                ToggleSize.S -> PaddingValues(horizontal = 16.dp)
            }
        }

        override fun cornerRadius(size: ToggleSize): Dp {
            return when (size) {
                ToggleSize.XS -> 12.dp
                ToggleSize.S -> 14.dp
            }
        }

        override fun iconSize(size: ToggleSize): Dp {
            return when (size) {
                ToggleSize.XS -> 18.dp
                ToggleSize.S -> 20.dp
            }
        }

        @Composable
        override fun strokeWidth(size: ToggleSize): Dp {
            return LocalCoreTokens.current.strokeWidthThick
        }

        @Composable
        override fun textStyle(size: ToggleSize): TextStyle {
            return when (size) {
                ToggleSize.XS -> AppTheme.typography.B5
                ToggleSize.S -> AppTheme.typography.B4
            }
        }

        @Composable
        override fun containerColor(palette: PaletteColors): Color {
            LocalComponentState.current
            return Color.Unspecified
        }

        @Composable
        override fun strokeColor(palette: PaletteColors): Color {
            LocalComponentState.current
            return Color.Unspecified
        }

        @Composable
        override fun contentColor(palette: PaletteColors): Color {
            LocalComponentState.current
            return Color.Unspecified
        }
    }
}