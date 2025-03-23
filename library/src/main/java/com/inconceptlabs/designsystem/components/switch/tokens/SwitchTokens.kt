package com.inconceptlabs.designsystem.components.switch.tokens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.inconceptlabs.designsystem.components.toggle.ToggleSize
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

interface SwitchTokens {

    val thumbShape: Shape
    val containerShape: Shape

    fun alignment(isChecked: Boolean): Alignment

    fun requiredSize(size: ToggleSize): DpSize

    @Composable
    fun contentPadding(isChecked: Boolean, size: ToggleSize): PaddingValues

    @Composable
    fun thumbSize(isChecked: Boolean, size: ToggleSize): Dp

    @Composable
    fun thumbColor(isChecked: Boolean, palette: PaletteColors): Color

    @Composable
    fun containerColor(isChecked: Boolean, palette: PaletteColors): Color

    @Composable
    fun borderColor(isChecked: Boolean, palette: PaletteColors): Color

    @Composable
    fun borderWidth(size: ToggleSize): Dp

    companion object {

        val Default: SwitchTokens = SwitchTokensInstance
    }
}