package com.inconceptlabs.designsystem.components.avatar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.components.core.Divider
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.Size

@Preview(showBackground = true)
@Composable
private fun AvatarPreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Size.entries.forEach {
                    Avatar(
                        painter = painterResource(R.drawable.ic_profile_random),
                        fallbackIcon = painterResource(R.drawable.ic_profile_default),
                        size = it
                    )
                }
            }

            Divider()

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Size.entries.forEach {
                    Avatar(
                        fullName = "Sarah Tyrell",
                        fallbackIcon = painterResource(R.drawable.ic_profile_default),
                        size = it
                    )
                }
            }

            Divider()

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Size.entries.forEach {
                    Avatar(
                        fallbackIcon = painterResource(R.drawable.ic_profile_default),
                        size = it
                    )
                }
            }
        }
    }
}