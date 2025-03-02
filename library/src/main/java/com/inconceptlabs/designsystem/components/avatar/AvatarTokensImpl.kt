package com.inconceptlabs.designsystem.components.avatar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.Size

object AvatarTokensImpl : AvatarTokens {

    override fun size(size: Size): Dp {
        return when (size) {
            Size.XXS -> 24.dp
            Size.XS -> 32.dp
            Size.S -> 36.dp
            Size.M -> 40.dp
            Size.L -> 48.dp
        }
    }

    override fun fallbackIconSize(size: Size): Dp {
        return when (size) {
            Size.XXS -> 16.dp
            Size.XS -> 18.dp
            Size.S -> 20.dp
            Size.M -> 24.dp
            Size.L -> 28.dp
        }
    }

    /**
     * @return The initials of person
     */
    override fun fallbackText(value: String): String {
        return value.split(" ")
            .mapNotNull(String::firstOrNull)
            .joinToString("", transform = Char::uppercase)
            .take(2)
    }

    override fun resolvedContentDescription(
        providedValue: String?,
        fullName: String?
    ): String {
        return when {
            !providedValue.isNullOrBlank() -> providedValue
            !fullName.isNullOrBlank() -> "$fullName's avatar"
            else -> "Avatar"
        }
    }

    @Composable
    override fun fallbackIcon(): Painter {
        return painterResource(R.drawable.ic_profile_default)
    }

    @Composable
    override fun contentColor(): Color {
        return AppTheme.colorScheme.T8
    }

    @Composable
    override fun backgroundColor(): Color {
        return AppTheme.colorScheme.BG4
    }

    @Composable
    override fun fallbackTextStyle(size: Size): TextStyle {
        return when (size) {
            Size.XXS -> AppTheme.typography.P6
            Size.XS -> AppTheme.typography.P5
            Size.S -> AppTheme.typography.P4
            Size.M -> AppTheme.typography.P3
            Size.L -> AppTheme.typography.P2
        }
    }
}