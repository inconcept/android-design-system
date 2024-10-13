package com.inconceptlabs.designsystem.components.emptyitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.buttons.TextButton
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.theme.LocalEmptyItemTokens
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.colors.paletteColors
import com.inconceptlabs.designsystem.utils.dashedBorder

@Composable
fun EmptyItem(
    data: EmptyItemData,
    modifier: Modifier = Modifier,
) {
    with(data) {
        EmptyItem(
            icon = icon(),
            title = title(),
            description = description(),
            modifier = modifier,
            keyColor = keyColor,
            buttonText = buttonText?.invoke(),
            onButtonClick = onButtonClick,
        )
    }
}

/**
 * Displays a placeholder for empty states in list
 * views, search results, or any other place where a
 * lack of content needs to be communicated to the user.
 *
 * See [ComponentPreview] for usage examples and customization options.
 */
@Composable
fun EmptyItem(
    icon: Painter,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    keyColor: KeyColor = KeyColor.PRIMARY,
    buttonText: String? = null,
    onButtonClick: () -> Unit = { },
) = with(LocalEmptyItemTokens.current) {
    require(title.isNotBlank()) {
        "`title` must not be blank!"
    }
    require(description.isNotBlank()) {
        "`description` must not be blank!"
    }
    require(buttonText == null || buttonText.isNotBlank()) {
        "`buttonText` must not be blank!"
    }

    val paletteColors = keyColor.paletteColors()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .dashedBorder(
                color = paletteColors.alpha50,
                cornerRadius = cornerRadius,
                dashWidth = dashWidth,
                dashGap = dashGap,
            )
            .padding(paddingValues),
    ) {
        Icon(
            painter = icon,
            tint = iconTint(),
            modifier = Modifier.size(iconSize),
        )

        Text(
            text = title,
            style = titleStyle(),
            color = titleColor(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
        )

        Text(
            text = description,
            style = descriptionStyle(),
            color = descriptionColor(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
        )

        if (buttonText != null) {
            TextButton(
                text = buttonText,
                keyColor = keyColor,
                size = buttonSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
                onClick = onButtonClick,
            )
        }
    }
}