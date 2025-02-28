package com.inconceptlabs.designsystem.components.input.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

object InputFormTokensImpl : InputFormTokens {

    override val horizontalPadding: Dp
        get() = 16.dp

    override val startIconPadding: Dp
        get() = 10.dp

    override val endIconPadding: Dp
        get() = 8.dp

    /**
     * Compose currently doesn't provide a way of customizing the cursor's height
     * So we need to create a custom cursor brush to achieve the desired effect
     */
    override val cursorBrush: Brush
        get() = Brush.verticalGradient(
            0.00f to Color.Transparent,
            0.10f to Color.Transparent,
            0.11f to Color.Black,
            0.89f to Color.Black,
            0.90f to Color.Transparent,
            1.00f to Color.Transparent,
        )

    override fun height(size: Size): Dp {
        return when (size) {
            Size.XXS,
            Size.XS -> 36.dp
            Size.S -> 40.dp
            Size.M -> 48.dp
            Size.L -> 56.dp
        }
    }

    override fun cornerRadius(size: Size): Dp {
        return when (size) {
            Size.XXS,
            Size.XS -> 12.dp
            Size.S -> 14.dp
            Size.M -> 16.dp
            Size.L -> 18.dp
        }
    }

    override fun titleIconSize(size: Size): Dp {
        return when (size) {
            Size.XXS,
            Size.XS,
            Size.S -> 16.dp
            Size.M,
            Size.L -> 18.dp
        }
    }

    override fun formIconSize(size: Size): Dp {
        return when (size) {
            Size.XXS,
            Size.XS -> 16.dp
            Size.S -> 18.dp
            Size.M -> 20.dp
            Size.L -> 22.dp
        }
    }

    @Composable
    override fun titleTypography(size: Size): TextStyle {
        return when (size) {
            Size.XXS,
            Size.XS,
            Size.S -> AppTheme.typography.P5
            Size.M,
            Size.L -> AppTheme.typography.P4
        }
    }

    @Composable
    override fun hintTypography(size: Size): TextStyle {
        return inputTypography(size).copy(
            color = hintColor()
        )
    }

    @Composable
    override fun inputTypography(size: Size): TextStyle {
        val typography = when (size) {
            Size.XXS,
            Size.XS -> AppTheme.typography.P5
            Size.S -> AppTheme.typography.P4
            Size.M -> AppTheme.typography.P3
            Size.L -> AppTheme.typography.P3
        }

        return typography.copy(
            color = inputColor()
        )
    }

    @Composable
    override fun strokeColor(type: Type): Color {
        val state = LocalComponentState.current
        return when {
            state == State.Success -> AppTheme.colorScheme.success.main
            state == State.Error -> AppTheme.colorScheme.error.main
            type == Type.Filled -> Color.Unspecified
            state == State.Disabled -> AppTheme.colorScheme.BG5
            state == State.Empty -> AppTheme.colorScheme.BG6
            state == State.Focused -> AppTheme.colorScheme.BG6
            state == State.Active -> AppTheme.colorScheme.BG6
            else -> Color.Unspecified
        }
    }

    @Composable
    override fun backgroundColor(type: Type): Color {
        val state = LocalComponentState.current
        return when {
            type == Type.Outlined -> Color.Transparent
            state == State.Focused -> AppTheme.colorScheme.BG5
            else -> AppTheme.colorScheme.BG4
        }
    }

    @Composable
    override fun inputColor(): Color {
        val state = LocalComponentState.current
        return when (state) {
            State.Disabled -> AppTheme.colorScheme.T3
            State.Empty -> AppTheme.colorScheme.T6
            else -> AppTheme.colorScheme.T8
        }
    }

    @Composable
    override fun hintColor(): Color {
        val state = LocalComponentState.current
        return when (state) {
            State.Disabled -> AppTheme.colorScheme.T3
            else -> AppTheme.colorScheme.T6
        }
    }

    @Composable
    override fun startIconColor(palette: PaletteColors): Color {
        val state = LocalComponentState.current
        return when (state) {
            State.Disabled -> AppTheme.colorScheme.T3
            State.Empty -> palette.alpha50
            State.Focused -> palette.main
            State.Active -> palette.main
            State.Success -> AppTheme.colorScheme.success.main
            State.Error -> AppTheme.colorScheme.error.main
            else -> Color.Unspecified
        }
    }

    @Composable
    override fun endIconColor(): Color {
        val state = LocalComponentState.current
        return when (state) {
            State.Disabled -> AppTheme.colorScheme.T3
            State.Empty -> AppTheme.colorScheme.T6
            else -> AppTheme.colorScheme.T8
        }
    }

    @Composable
    override fun additionalTextColor(): Color {
        val state = LocalComponentState.current
        return when (state) {
            State.Success -> AppTheme.colorScheme.success.main
            State.Error -> AppTheme.colorScheme.error.main
            State.Disabled -> AppTheme.colorScheme.T3
            else -> AppTheme.colorScheme.T8
        }
    }
}