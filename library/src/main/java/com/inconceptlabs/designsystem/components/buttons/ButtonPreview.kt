package com.inconceptlabs.designsystem.components.buttons

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.buttons.token.ButtonTokens
import com.inconceptlabs.designsystem.components.buttons.token.ButtonTokensImpl
import com.inconceptlabs.designsystem.components.buttons.token.LocalButtonTokens
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.CornerType
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 1200
)
@Composable
private fun ButtonPreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                    vertical = 24.dp
                )
        ) {
            Column {
                Text(
                    text = "Buttons",
                    style = AppTheme.typography.H3,
                )
                Text(
                    text = "Essentially Buttons are just clickable surfaces",
                    style = AppTheme.typography.P3,
                )
            }

            Section(
                title = "Buttons by Types",
                subtitle = "`TEXT` type has no background in default state"
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ButtonType.entries.forEach {
                        Button(
                            type = it,
                            size = Size.S,
                            hasMinWidth = false,
                            modifier = Modifier.weight(1f),
                            onClick = {},
                            content = {},
                        )
                    }
                }
            }

            Section(
                title = "Buttons by Sizes",
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Size.entries.forEach {
                        Button(
                            size = it,
                            hasMinWidth = false,
                            modifier = Modifier.weight(1f),
                            onClick = {},
                            content = {},
                        )
                    }
                }
            }

            Section(title = "Buttons by KeyColors") {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    KeyColor.entries.forEach {
                        Button(
                            modifier = Modifier.weight(1f),
                            size = Size.S,
                            hasMinWidth = false,
                            keyColor = it,
                            onClick = {},
                            content = {},
                        )
                    }
                }
            }

            Section(title = "Buttons by CornerTypes") {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    CornerType.entries.forEach {
                        Button(
                            modifier = Modifier.weight(1f),
                            hasMinWidth = false,
                            cornerType = it,
                            onClick = {},
                            content = {},
                        )
                    }
                }
            }

            Section("Buttons by `hasMinWidth`") {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Button(
                        hasMinWidth = true,
                        onClick = {},
                        content = {},
                    )
                    Button(
                        hasMinWidth = false,
                        onClick = {},
                        content = {},
                    )
                }
            }

            Section(
                title = "Buttons by enabled state",
                subtitle = "Background was added for better visibility"
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.LightGray)
                        .padding(8.dp)
                ) {
                    Button(
                        isEnabled = false,
                        onClick = {},
                        content = {},
                    )
                    Button(
                        isEnabled = true,
                        onClick = {},
                        content = {},
                    )
                }
            }

            Section(
                title = "Custom Buttons (with tokens)",
                subtitle = "Here we are adjusting corner radius and height"
            ) {
                CompositionLocalProvider(
                    LocalButtonTokens provides object : ButtonTokens by ButtonTokensImpl {
                        override fun height(size: Size): Dp {
                            return when (size) {
                                Size.XXS -> 24.dp
                                Size.XS -> 28.dp
                                Size.S -> 32.dp
                                Size.M -> 40.dp
                                Size.L -> 48.dp
                            }
                        }

                        override fun cornerRadius(size: Size): Dp {
                            return when (size) {
                                Size.XXS -> 4.dp
                                Size.XS -> 6.dp
                                Size.S -> 8.dp
                                Size.M -> 10.dp
                                Size.L -> 12.dp
                            }
                        }
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Size.entries.forEach {
                            Button(
                                size = it,
                                hasMinWidth = false,
                                modifier = Modifier.weight(1f),
                                onClick = {},
                                content = {},
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Section(
    title: String,
    subtitle: String? = null,
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = AppTheme.typography.S1,
        )

        if (!subtitle.isNullOrBlank()) {
            Text(
                text = subtitle,
                style = AppTheme.typography.P4,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        content()
    }
}