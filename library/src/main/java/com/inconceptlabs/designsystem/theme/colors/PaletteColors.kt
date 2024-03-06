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
        KeyColor.PRIMARY -> AppTheme.colorScheme.primary
        KeyColor.SECONDARY -> AppTheme.colorScheme.secondary
        KeyColor.TERTIARY -> AppTheme.colorScheme.tertiary
        KeyColor.SUCCESS -> AppTheme.colorScheme.success
        KeyColor.ERROR -> AppTheme.colorScheme.error
        KeyColor.WARNING -> AppTheme.colorScheme.warning
        KeyColor.INFO -> AppTheme.colorScheme.info
        KeyColor.PREMIUM -> AppTheme.colorScheme.premium
        KeyColor.COLOR_NEW -> AppTheme.colorScheme.new
    }
}