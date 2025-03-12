package com.inconceptlabs.designsystem.components.label

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.KeyColor

@ExperimentalLayoutApi
@Preview
@Composable
private fun LabelPreview() {
    val labelVariants = remember {
        LabelType.entries.flatMap { type ->
            LabelSize.entries.map { size -> type to size }
        }
    }

    AppTheme {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = 2,
            modifier = Modifier
                .background(AppTheme.colorScheme.BG1)
                .padding(16.dp),
        ) {
            labelVariants.forEach { (type, size) ->
                Label(
                    text = "New",
                    type = type,
                    size = size,
                    modifier = Modifier.align(Alignment.Bottom)
                )
            }

            LabelCorner.entries.reversed().forEach {
                Label(
                    text = "Live",
                    corner = it,
                    keyColor = KeyColor.Premium,
                )
            }
        }
    }
}