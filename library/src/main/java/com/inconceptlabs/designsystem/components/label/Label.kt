package com.inconceptlabs.designsystem.components.label

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.components.label.tokens.LocalLabelTokens
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.colors.paletteColors

@Composable
fun Label(
    text: String,
    modifier: Modifier = Modifier,
    type: LabelType = LabelType.Filled,
    corner: LabelCorner = LabelCorner.BottomStart,
    size: LabelSize = LabelSize.M,
    keyColor: KeyColor = KeyColor.ColorNew,
) {
    with(LocalLabelTokens.current) {
        val paletteColors = keyColor.paletteColors()

        Text(
            text = text,
            style = textStyle(size),
            color = textColor(type, paletteColors),
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = modifier
                .wrapContentSize()
                .requiredHeight(height = height(size))
                .background(
                    type = type,
                    corner = corner,
                    palette = paletteColors,
                )
                .padding(horizontal = horizontalPadding(size))
        )
    }
}