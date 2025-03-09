package com.inconceptlabs.designsystem.components.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.notification.tokens.LocalNotificationTokens
import com.inconceptlabs.designsystem.components.notification.tokens.NotificationTokens
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

@Preview(showBackground = true)
@Composable
private fun NotificationPreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val warningIcon = painterResource(id = android.R.drawable.stat_sys_warning)
            val closeIcon = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel)

            Notification(
                startIcon = warningIcon,
                title = "Account Blocked",
                description = "Account blocked due to suspicious activity",
                keyColor = KeyColor.ERROR,
            )
            Notification(
                startIcon = warningIcon,
                endIcon = closeIcon,
                title = "Please verify your account",
                description = "If you have not received the email",
                buttonText = "Resend",
                isButtonEnabled = false,
                keyColor = KeyColor.WARNING,
            )
            Notification(
                startIcon = warningIcon,
                title = "Premium Account. You are missing all the fun!",
                description = """
                    Activate to enjoy all the benefits!
                    Get 50% off on your first purchase.
                """.trimIndent(),
                buttonText = "Upgrade",
                keyColor = KeyColor.PREMIUM,
            )

            val customTokens = object : NotificationTokens by NotificationTokens.Default {
                @Composable
                override fun startIconColor(
                    palette: PaletteColors
                ) = AppTheme.colorScheme.T9

                @Composable
                override fun titleColor(
                    palette: PaletteColors
                ) = AppTheme.colorScheme.T9

                @Composable
                override fun descriptionColor(
                    palette: PaletteColors
                ) = AppTheme.colorScheme.T7
            }

            CompositionLocalProvider(
                LocalNotificationTokens provides customTokens,
            ) {
                Notification(
                    startIcon = painterResource(id = android.R.drawable.ic_menu_info_details),
                    endIcon = closeIcon,
                    title = "Upcoming Maintenance",
                    description = """
                        Please be informed that maintenance is at 4:00 AM
                        Sorry for any inconvenience caused.
                        Third line won't be visible.
                    """.trimIndent(),
                    keyColor = KeyColor.INFO,
                )
            }
        }
    }
}