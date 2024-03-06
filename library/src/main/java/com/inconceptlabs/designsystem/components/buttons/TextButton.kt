package com.inconceptlabs.designsystem.components.buttons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalContentColor
import com.inconceptlabs.designsystem.theme.LocalIconSize
import com.inconceptlabs.designsystem.theme.LocalTextStyle
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size

@Preview
@Composable
private fun ComponentPreview() {
    AppTheme {
        TextButton(
            text = "Text Button",
            startIcon = painterResource(id = R.drawable.ic_lock_outline),
            onClick = {}
        )
    }
}

/**
 * Composable that displays a clickable button with optional start and end icons, supporting various sizes, types, and key colors.
 *
 * @param text The text displayed on the button.
 * @param onClick Callback triggered when the button is clicked.
 * @param modifier Modifier for composable positioning and sizing only.
 * @param isEnabled Flag indicating whether the button is enabled for interaction.
 * @param size Size of the button (default: [Size.M]).
 * @param type Type of the button (default: [ButtonType.PRIMARY]).
 * @param keyColor Key color of the button (default: [KeyColor.PRIMARY]).
 * @param hasMinWidth Flag indicating whether the button has minimum width (default: true).
 * @param startIcon Optional icon at the start position of the button.
 * @param endIcon Optional icon at the end position of the button.
 */
@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    size: Size = Size.M,
    type: ButtonType = ButtonType.PRIMARY,
    keyColor: KeyColor = KeyColor.PRIMARY,
    hasMinWidth: Boolean = true,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
    interactionSource: MutableInteractionSource = remember(::MutableInteractionSource),
) {
    require(text.isNotBlank()) { "Parameter `text` is required for TextButton" }

    Button(
        onClick = onClick,
        modifier = modifier,
        size = size,
        type = type,
        keyColor = keyColor,
        hasMinWidth = hasMinWidth,
        isEnabled = isEnabled,
        interactionSource = interactionSource
    ) {
        if (startIcon != null) {
            OptionalIcon(
                painter = startIcon,
                paddingValues = PaddingValues(end = 4.dp)
            )
        }

        Text(
            text = text,
            style = LocalTextStyle.current,
            color = LocalContentColor.current,
        )

        if (endIcon != null) {
            OptionalIcon(
                painter = endIcon,
                paddingValues = PaddingValues(start = 4.dp)
            )
        }
    }
}

@Composable
private fun OptionalIcon(
    painter: Painter,
    paddingValues: PaddingValues,
) {
    Icon(
        painter = painter,
        modifier = Modifier
            .size(LocalIconSize.current)
            .padding(paddingValues),
    )
}