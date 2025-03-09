package com.inconceptlabs.designsystem.components.notification.tokens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

object NotificationTokensImpl : NotificationTokens {

    override val paddingValues = PaddingValues(
        horizontal = 12.dp,
        vertical = 12.dp
    )

    override val startIconSize = 20.dp
    override val endIconSize = 16.dp

    override val titleMaxLines = 1
    override val descriptionMaxLines = 2

    override val titleStartPadding = 8.dp
    override val titleEndPadding = 12.dp

    override val buttonEndPadding = 12.dp

    override val buttonSize = Size.XXS

    override fun backgroundColor(palette: PaletteColors): Color {
        return palette.alpha10
    }

    @Composable
    override fun startIconColor(palette: PaletteColors): Color {
        return palette.main
    }

    @Composable
    override fun endIconColor(palette: PaletteColors): Color {
        return startIconColor(palette)
    }

    @Composable
    override fun titleTextStyle(): TextStyle {
        return AppTheme.typography.P5
    }

    @Composable
    override fun titleColor(palette: PaletteColors): Color {
        return palette.main
    }

    @Composable
    override fun descriptionTextStyle(): TextStyle {
        return AppTheme.typography.P5
    }

    @Composable
    override fun descriptionColor(palette: PaletteColors): Color {
        return AppTheme.colorScheme.T8
    }
}