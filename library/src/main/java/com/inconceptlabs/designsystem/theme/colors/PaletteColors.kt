package com.inconceptlabs.designsystem.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.KeyColor

data class PaletteColors(
    val alpha10: Color = Color.Unspecified,
    val alpha20: Color = Color.Unspecified,
    val alpha30: Color = Color.Unspecified,
    val alpha50: Color = Color.Unspecified,
    val main: Color = Color.Unspecified,
    val dark5: Color = Color.Unspecified,
)

@Composable
fun KeyColor.paletteColors(): PaletteColors {
    return when (this) {
        KeyColor.Primary -> AppTheme.colorScheme.primary
        KeyColor.Secondary -> AppTheme.colorScheme.secondary
        KeyColor.Tertiary -> AppTheme.colorScheme.tertiary
        KeyColor.Success -> AppTheme.colorScheme.success
        KeyColor.Error -> AppTheme.colorScheme.error
        KeyColor.Warning -> AppTheme.colorScheme.warning
        KeyColor.Info -> AppTheme.colorScheme.info
        KeyColor.Premium -> AppTheme.colorScheme.premium
        KeyColor.ColorNew -> AppTheme.colorScheme.new
    }
}