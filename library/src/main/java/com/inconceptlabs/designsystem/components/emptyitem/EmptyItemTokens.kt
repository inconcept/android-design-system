package com.inconceptlabs.designsystem.components.emptyitem

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.Size

data class EmptyItemTokens(
    val paddingValues: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 36.dp
    ),
    val cornerRadius: Dp = 20.dp,

    val dashWidth: Dp = 5.dp,
    val dashGap: Dp = dashWidth,

    val iconSize: Dp = 56.dp,
    val iconTint: @Composable () -> Color = { AppTheme.colorScheme.BG6 },

    val buttonSize: Size = Size.M,

    val titleStyle: @Composable () -> TextStyle = { AppTheme.typography.S2 },
    val titleColor: @Composable () -> Color = { AppTheme.colorScheme.T8 },

    val descriptionStyle: @Composable () -> TextStyle = { AppTheme.typography.P5 },
    val descriptionColor: @Composable () -> Color = { AppTheme.colorScheme.T8 },
) {

    companion object {

        internal val Default = EmptyItemTokens()
    }
}