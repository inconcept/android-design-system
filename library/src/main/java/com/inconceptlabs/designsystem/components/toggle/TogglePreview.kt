package com.inconceptlabs.designsystem.components.toggle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.AppTheme

@Preview(showBackground = true)
@Composable
private fun TogglePreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            val isChecked = remember { mutableStateOf(true) }

            Toggle(
                checked = false,
                onCheckedChange = null
            )

            Toggle(
                checked = true,
                onCheckedChange = null
            )

            Toggle(
                checked = isChecked.value,
                onCheckedChange = { isChecked.value = it }
            )
        }
    }
}