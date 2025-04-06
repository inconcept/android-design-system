@file:SuppressLint("ModifierParameter")

package com.inconceptlabs.designsystem.components.core

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.colors.paletteColors

@Composable
fun StatusDot(
    size: Dp = 8.dp,
    keyColor: KeyColor = KeyColor.ColorNew,
    modifier: Modifier = Modifier,
) {
    val palette = keyColor.paletteColors()
    val backgroundColor = palette.main

    Box(
        modifier = modifier
            .size(size)
            .background(
                color = backgroundColor,
                shape = CircleShape,
            )
    )
}