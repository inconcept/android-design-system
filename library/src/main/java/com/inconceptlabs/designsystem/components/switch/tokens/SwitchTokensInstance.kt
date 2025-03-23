package com.inconceptlabs.designsystem.components.switch.tokens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.ToggleState
import com.inconceptlabs.designsystem.components.core.LocalCoreTokens
import com.inconceptlabs.designsystem.components.toggle.ToggleSize
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

object SwitchTokensInstance : SwitchTokens {

    private val state: ToggleState
        @Composable
        get() = LocalComponentState.current as ToggleState

    override val thumbShape: Shape
        get() = CircleShape

    override val containerShape: Shape
        get() = CircleShape

    override fun alignment(isChecked: Boolean): Alignment {
        return if (isChecked) Alignment.CenterEnd else Alignment.CenterStart
    }

    override fun requiredSize(size: ToggleSize): DpSize {
        return when (size) {
            ToggleSize.XS -> DpSize(width = 52.dp, height = 32.dp)
            ToggleSize.S -> DpSize(width = 58.dp, height = 36.dp)
        }
    }

    @Composable
    override fun contentPadding(isChecked: Boolean, size: ToggleSize): PaddingValues {
        val targetValue = when {
            state == ToggleState.Pressed -> 2.dp
            size == ToggleSize.XS && isChecked -> 4.dp
            size == ToggleSize.XS && !isChecked -> 6.dp
            size == ToggleSize.S && isChecked -> 4.dp
            size == ToggleSize.S && !isChecked -> 8.dp
            else -> Dp.Unspecified
        }

        val animatedState = animateDpAsState(
            targetValue = targetValue,
            animationSpec = animationSpec(),
            label = "ContentPadding",
        )
        return PaddingValues(horizontal = animatedState.value)
    }

    @Composable
    override fun thumbSize(isChecked: Boolean, size: ToggleSize): Dp {
        val targetValue = when {
            size == ToggleSize.XS && isChecked -> 24.dp
            size == ToggleSize.XS && !isChecked -> 16.dp
            state == ToggleState.Pressed -> 32.dp
            size == ToggleSize.S && isChecked -> 28.dp
            size == ToggleSize.S && !isChecked -> 20.dp
            else -> Dp.Unspecified
        }

        return animateDpAsState(
            targetValue = targetValue,
            animationSpec = tween(durationMillis = 200),
            label = "ThumbSize",
        ).value
    }

    @Composable
    override fun thumbColor(isChecked: Boolean, palette: PaletteColors): Color {
        val targetValue = when {
            state == ToggleState.Default && isChecked -> AppTheme.colorScheme.T1
            state == ToggleState.Pressed && isChecked -> palette.alpha20
            state == ToggleState.Disabled && isChecked -> AppTheme.colorScheme.BG1
            state == ToggleState.Default && !isChecked -> AppTheme.colorScheme.T7
            state == ToggleState.Pressed && !isChecked -> AppTheme.colorScheme.T10
            state == ToggleState.Disabled && !isChecked -> AppTheme.colorScheme.T6
            else -> Color.Unspecified
        }

        return animateColorAsState(
            targetValue = targetValue,
            animationSpec = animationSpec(),
            label = "ThumbColor",
        ).value
    }

    @Composable
    override fun containerColor(isChecked: Boolean, palette: PaletteColors): Color {
        val targetValue = when {
            state == ToggleState.Disabled && isChecked -> AppTheme.colorScheme.T4
            state != ToggleState.Disabled && isChecked -> palette.dark5
            state == ToggleState.Disabled && !isChecked -> AppTheme.colorScheme.BG1
            state != ToggleState.Disabled && !isChecked -> AppTheme.colorScheme.BG3
            else -> Color.Unspecified
        }

        return animateColorAsState(
            targetValue = targetValue,
            animationSpec = animationSpec(),
            label = "ContainerColor",
        ).value
    }

    @Composable
    override fun borderColor(isChecked: Boolean, palette: PaletteColors): Color {
        if (isChecked) return Color.Unspecified

        val targetValue = when (state) {
            ToggleState.Disabled -> AppTheme.colorScheme.BG4
            else -> AppTheme.colorScheme.BG7
        }

        return animateColorAsState(
            targetValue = targetValue,
            animationSpec = animationSpec(),
            label = "BorderColor",
        ).value
    }

    @Composable
    override fun borderWidth(size: ToggleSize): Dp {
        return LocalCoreTokens.current.strokeWidthThick
    }

    private fun <T> animationSpec(): AnimationSpec<T> {
        return tween(durationMillis = 200)
    }
}