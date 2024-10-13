package com.inconceptlabs.designsystem.components.emptyitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalEmptyItemTokens
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size

@Preview(
    showBackground = true,
    widthDp = 460,
    heightDp = 1200,
)
@Composable
private fun ComponentPreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            EmptyItem(
                icon = painterResource(R.drawable.ic_lock_outline),
                title = "Basic EmptyItem",
                description = "Short description indicating action",
            )

            EmptyItem(
                icon = painterResource(R.drawable.ic_lock_outline),
                title = "EmptyItem with long text and button",
                description = "A little longer description taking one more line for demonstration purposes",
                buttonText = "Try Again"
            )

            EmptyItem(
                icon = painterResource(R.drawable.ic_lock_outline),
                title = "EmptyItem with alternate keyColor",
                description = "A little longer description taking one more line for demonstration purposes",
                buttonText = "Try Again",
                keyColor = KeyColor.SECONDARY,
            )

            CompositionLocalProvider(
                LocalEmptyItemTokens provides EmptyItemTokens(
                    paddingValues = PaddingValues(10.dp),
                    cornerRadius = 10.dp,
                    dashWidth = 20.dp,
                    dashGap = 5.dp,
                    iconSize = 48.dp,
                    iconTint = { AppTheme.colorScheme.warning.main },
                    buttonSize = Size.XS,
                    titleStyle = { AppTheme.typography.H5 },
                    titleColor = { AppTheme.colorScheme.error.main },
                )
            ) {
                EmptyItem(
                    icon = painterResource(R.drawable.ic_lock_outline),
                    title = "EmptyItem with alternate tokens",
                    description = "A little longer description taking one more line for demonstration purposes",
                    buttonText = "Try Again",
                )
            }
        }
    }
}