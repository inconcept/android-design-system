package com.inconceptlabs.designsystem.components.notification.tokens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

val LocalNotificationTokens = compositionLocalOf { NotificationTokens.Default }

interface NotificationTokens {

    val paddingValues: PaddingValues

    val startIconSize: Dp
    val endIconSize: Dp

    val titleMaxLines: Int
    val descriptionMaxLines: Int

    val titleStartPadding: Dp
    val titleEndPadding: Dp

    val buttonEndPadding: Dp
    val buttonSize: Size

    fun backgroundColor(palette: PaletteColors): Color

    @Composable
    fun startIconColor(palette: PaletteColors): Color

    @Composable
    fun endIconColor(palette: PaletteColors): Color

    @Composable
    fun titleTextStyle(): TextStyle

    @Composable
    fun titleColor(palette: PaletteColors): Color

    @Composable
    fun descriptionTextStyle(): TextStyle

    @Composable
    fun descriptionColor(palette: PaletteColors): Color

    companion object {

        val Default: NotificationTokens = NotificationTokensImpl
    }
}