package com.inconceptlabs.designsystem.components.label.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.components.label.LabelCorner
import com.inconceptlabs.designsystem.components.label.LabelSize
import com.inconceptlabs.designsystem.components.label.LabelType
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

val LocalLabelTokens = compositionLocalOf { LabelTokens.Default }

interface LabelTokens {

    val cornerRadius: Dp

    fun height(size: LabelSize): Dp

    fun horizontalPadding(size: LabelSize): Dp

    fun shape(corner: LabelCorner): Shape

    @Composable
    fun Modifier.background(
        type: LabelType,
        corner: LabelCorner,
        palette: PaletteColors,
    ): Modifier

    @Composable
    fun textStyle(size: LabelSize): TextStyle

    @Composable
    fun textColor(type: LabelType, palette: PaletteColors): Color

    companion object {

        val Default: LabelTokens = LabelTokensImpl
    }
}