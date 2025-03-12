package com.inconceptlabs.designsystem.components.label.tokens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.core.LocalCoreTokens
import com.inconceptlabs.designsystem.components.label.LabelCorner
import com.inconceptlabs.designsystem.components.label.LabelSize
import com.inconceptlabs.designsystem.components.label.LabelType
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

object LabelTokensImpl : LabelTokens {

    override val cornerRadius: Dp
        get() = 8.dp

    override fun height(size: LabelSize): Dp {
        return when (size) {
            LabelSize.S -> 16.dp
            LabelSize.M -> 18.dp
        }
    }

    override fun horizontalPadding(size: LabelSize): Dp {
        return when (size) {
            LabelSize.S -> 4.dp
            LabelSize.M -> 5.dp
        }
    }

    override fun shape(corner: LabelCorner): Shape {
        return RoundedCornerShape(
            topStart = if (corner == LabelCorner.TopStart) 0.dp else cornerRadius,
            topEnd = if (corner == LabelCorner.TopEnd) 0.dp else cornerRadius,
            bottomStart = if (corner == LabelCorner.BottomStart) 0.dp else cornerRadius,
            bottomEnd = if (corner == LabelCorner.BottomEnd) 0.dp else cornerRadius,
        )
    }

    @Composable
    override fun Modifier.background(
        type: LabelType,
        corner: LabelCorner,
        palette: PaletteColors,
    ): Modifier {
        return when (type) {
            LabelType.Filled -> background(
                shape = shape(corner),
                color = palette.main,
            )
            LabelType.Outlined -> border(
                width = LocalCoreTokens.current.strokeWidthThin,
                shape = shape(corner),
                color = palette.main,
            )
        }
    }

    @Composable
    override fun textStyle(size: LabelSize): TextStyle {
        return AppTheme.typography.P6
    }

    @Composable
    override fun textColor(type: LabelType, palette: PaletteColors): Color {
        return when (type) {
            LabelType.Filled -> AppTheme.colorScheme.T1
            LabelType.Outlined -> palette.main
        }
    }
}