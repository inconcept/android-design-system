package com.inconceptlabs.designsystem.components.tabitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.components.TabItemState
import com.inconceptlabs.designsystem.components.core.Divider
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.KeyColor

@Preview(showBackground = true)
@Composable
private fun IconTabItemPreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                IconTabItem(
                    icon = painterResource(R.drawable.ic_lock_outline),
                    size = TabItemSize.XS,
                )
                IconTabItem(
                    icon = painterResource(R.drawable.ic_lock_outline),
                    size = TabItemSize.XS,
                    initialState = TabItemState.Pressed,
                )
                IconTabItem(
                    icon = painterResource(R.drawable.ic_lock_outline),
                    size = TabItemSize.XS,
                    initialState = TabItemState.Active,
                )
            }

            Divider()

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                IconTabItem(
                    icon = painterResource(R.drawable.ic_lock_outline),
                    keyColor = KeyColor.PREMIUM,
                )
                IconTabItem(
                    icon = painterResource(R.drawable.ic_lock_outline),
                    keyColor = KeyColor.PREMIUM,
                    initialState = TabItemState.Pressed,
                )
                IconTabItem(
                    icon = painterResource(R.drawable.ic_lock_outline),
                    keyColor = KeyColor.PREMIUM,
                    initialState = TabItemState.Active,
                )
            }
        }
    }
}