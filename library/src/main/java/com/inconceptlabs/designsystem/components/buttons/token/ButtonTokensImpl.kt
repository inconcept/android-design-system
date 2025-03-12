package com.inconceptlabs.designsystem.components.buttons.token

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.ButtonState
import com.inconceptlabs.designsystem.components.buttons.ButtonType
import com.inconceptlabs.designsystem.components.core.LocalCoreTokens
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

object ButtonTokensImpl : ButtonTokens {

    override fun height(size: Size): Dp {
        return when (size) {
            Size.XXS -> 24.dp
            Size.XS -> 36.dp
            Size.S -> 40.dp
            Size.M -> 48.dp
            Size.L -> 56.dp
        }
    }

    override fun contentPadding(size: Size): PaddingValues {
        return when (size) {
            Size.XXS -> PaddingValues(horizontal = 8.dp)
            Size.XS -> PaddingValues(horizontal = 14.dp)
            Size.S -> PaddingValues(horizontal = 16.dp)
            Size.M -> PaddingValues(horizontal = 18.dp)
            Size.L -> PaddingValues(horizontal = 20.dp)
        }
    }

    override fun cornerRadius(size: Size): Dp {
        return when (size) {
            Size.XXS -> 10.dp
            Size.XS -> 12.dp
            Size.S -> 14.dp
            Size.M -> 16.dp
            Size.L -> 18.dp
        }
    }

    override fun minWidth(size: Size): Dp {
        return when (size) {
            Size.XXS -> 70.dp
            Size.XS -> 80.dp
            Size.S -> 90.dp
            Size.M -> 100.dp
            Size.L -> 120.dp
        }
    }

    override fun iconSize(size: Size): Dp {
        return when (size) {
            Size.XXS -> 16.dp
            Size.XS -> 18.dp
            Size.S -> 20.dp
            Size.M -> 22.dp
            Size.L -> 24.dp
        }
    }

    @Composable
    override fun strokeWidth(size: Size): Dp {
        return LocalCoreTokens.current.strokeWidthBySize(size)
    }

    @Composable
    override fun textStyle(size: Size): TextStyle {
        return when (size) {
            Size.XXS -> AppTheme.typography.B6
            Size.XS -> AppTheme.typography.B5
            Size.S -> AppTheme.typography.B4
            Size.M -> AppTheme.typography.B3
            Size.L -> AppTheme.typography.B2
        }
    }

    @Composable
    override fun containerColor(type: ButtonType, palette: PaletteColors): Color {
        return when (LocalComponentState.current) {
            ButtonState.Default -> {
                when (type) {
                    ButtonType.Primary -> palette.main
                    ButtonType.Secondary -> AppTheme.colorScheme.BG4
                    ButtonType.Text -> Color.Transparent
                    ButtonType.Outline -> Color.Transparent
                }
            }
            ButtonState.Pressed -> {
                when (type) {
                    ButtonType.Primary -> palette.dark5
                    else -> palette.alpha10
                }
            }
            ButtonState.Disabled -> {
                when (type) {
                    ButtonType.Primary,
                    ButtonType.Secondary -> AppTheme.colorScheme.BG2
                    ButtonType.Text,
                    ButtonType.Outline -> Color.Transparent
                }
            }
            else -> Color.Unspecified
        }
    }

    @Composable
    override fun strokeColor(type: ButtonType, palette: PaletteColors): Color {
        return when (LocalComponentState.current) {
            ButtonState.Default -> {
                when (type) {
                    ButtonType.Primary -> palette.main
                    ButtonType.Secondary -> AppTheme.colorScheme.BG4
                    ButtonType.Text -> Color.Transparent
                    ButtonType.Outline -> palette.alpha50
                }
            }
            ButtonState.Pressed -> {
                when (type) {
                    ButtonType.Primary -> palette.dark5
                    else -> palette.alpha10
                }
            }
            ButtonState.Disabled -> {
                when (type) {
                    ButtonType.Primary,
                    ButtonType.Secondary -> AppTheme.colorScheme.BG2
                    ButtonType.Text -> Color.Transparent
                    ButtonType.Outline -> AppTheme.colorScheme.BG4
                }
            }
            else -> Color.Unspecified
        }
    }

    @Composable
    override fun contentColor(type: ButtonType, palette: PaletteColors): Color {
        return when (LocalComponentState.current) {
            ButtonState.Default -> {
                when (type) {
                    ButtonType.Primary -> Color.White
                    ButtonType.Secondary -> AppTheme.colorScheme.T8
                    ButtonType.Text -> AppTheme.colorScheme.T8
                    ButtonType.Outline -> AppTheme.colorScheme.T8
                }
            }
            ButtonState.Pressed -> {
                when (type) {
                    ButtonType.Primary -> AppTheme.colorScheme.T1
                    else -> palette.dark5
                }
            }
            ButtonState.Disabled -> AppTheme.colorScheme.T4
            else -> Color.Unspecified
        }
    }
}