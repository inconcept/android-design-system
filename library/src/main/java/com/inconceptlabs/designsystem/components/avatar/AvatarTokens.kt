package com.inconceptlabs.designsystem.components.avatar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.theme.attributes.Size

val LocalAvatarTokens = compositionLocalOf { AvatarTokens.Default }

interface AvatarTokens {

    fun size(size: Size): Dp

    fun fallbackIconSize(size: Size): Dp

    fun fallbackText(value: String): String

    fun resolvedContentDescription(
        providedValue: String?,
        fullName: String?,
    ): String

    @Composable
    fun fallbackIcon(): Painter

    @Composable
    fun contentColor(): Color

    @Composable
    fun backgroundColor(): Color

    @Composable
    fun fallbackTextStyle(size: Size): TextStyle

    companion object {

        val Default: AvatarTokens = AvatarTokensImpl
    }
}