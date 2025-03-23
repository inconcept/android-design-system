package com.inconceptlabs.designsystem.components.switch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.ToggleState
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalComponentState

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
private fun SwitchPreview() {
    var isChecked by remember { mutableStateOf(false) }

    AppTheme {
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = 3,
            modifier = Modifier
                .background(AppTheme.colorScheme.BG1)
                .padding(16.dp)
        ) {
            Text(
                text = "Interactive Switch",
                style = AppTheme.typography.S1,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = "Dark Mode",
                style = AppTheme.typography.B4,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Switch(
                isChecked = isChecked,
                onCheckedChange = { isChecked = it },
            )

            Text(
                text = "Switch States",
                style = AppTheme.typography.S1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            SingleRow(isChecked = false)

            SingleRow(isChecked = true)
        }
    }
}

@Composable
private fun SingleRow(isChecked: Boolean) {
    Switch(
        isChecked = isChecked,
        isEnabled = false,
        onCheckedChange = { },
    )

    CompositionLocalProvider(
        LocalComponentState provides ToggleState.Pressed
    ) {
        Switch(
            isChecked = isChecked,
            modifier = Modifier,
            onCheckedChange = { },
        )
    }

    Switch(
        isChecked = isChecked,
        onCheckedChange = { },
    )
}