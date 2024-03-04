package com.inconceptlabs.designsystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalContentColor
import com.inconceptlabs.designsystem.theme.LocalIconSize
import com.inconceptlabs.designsystem.theme.LocalTextStyle
import com.inconceptlabs.designsystem.theme.attributes.CornerType
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.PaletteColors
import com.inconceptlabs.designsystem.theme.colors.paletteColors
import com.inconceptlabs.designsystem.theme.tokens.ButtonTokens
import com.inconceptlabs.designsystem.utils.clickable
import com.inconceptlabs.designsystem.utils.getStrokeWidth

@Preview
@Composable
private fun ComponentPreview() {
    AppTheme {
        Button(
            onClick = {},
            content = {}
        )
    }
}

/**
 * Composable that creates a clickable button with customizable size, appearance, and content.
 *
 * @param onClick Callback triggered when the button is clicked.
 * @param modifier Modifier for positioning and sizing the button.
 * @param size Size of the button (default: [Size.M]).
 * @param isEnabled Flag indicating whether the button is enabled for interaction.
 * @param keyColor Key color of the button (default: [KeyColor.PRIMARY]).
 * @param type Type of the button (default: [ButtonType.PRIMARY]).
 * @param hasMinWidth Flag indicating whether the button has minimum width (default: true).
 * @param cornerType Type of corner shape for the button (default: [CornerType.ROUNDED]).
 * @param interactionSource the [MutableInteractionSource] representing the stream of [Interaction]s
 * for this button. You can create and pass in your own `remember`ed instance to observe
 * [Interaction]s and customize the appearance / behavior of this button in different states.
 * @param tokens [ButtonTokens] for customizing the button's appearance.
 * @param content Content block for the button's internal content layout.
 */
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Size = Size.M,
    isEnabled: Boolean = true,
    keyColor: KeyColor = KeyColor.PRIMARY,
    type: ButtonType = ButtonType.PRIMARY,
    hasMinWidth: Boolean = true,
    cornerType: CornerType = CornerType.ROUNDED,
    interactionSource: MutableInteractionSource = remember(::MutableInteractionSource),
    tokens: ButtonTokens = ButtonTokens.default,
    content: @Composable (RowScope.() -> Unit),
) = with(tokens) {
    val isPressed by interactionSource.collectIsPressedAsState()

    val shape = when (cornerType) {
        CornerType.CIRCULAR -> CircleShape
        CornerType.ROUNDED -> RoundedCornerShape(cornerRadius(size))
    }

    val palette = keyColor.paletteColors()

    val colors = ButtonColors(
        containerColor = containerColor(type, palette),
        strokeColor = strokeColor(type, palette),
        contentColor = contentColor(type, palette),
    )

    val containerColor = colors.containerColor.forState(isEnabled, isPressed)
    val contentColor = colors.contentColor.forState(isEnabled, isPressed)
    val textStyle = textStyle(size)

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides textStyle,
        LocalIconSize provides iconSize(size),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .background(containerColor, shape)
                .border(
                    border = BorderStroke(
                        width = getStrokeWidth(size),
                        color = colors.strokeColor.forState(isEnabled, isPressed)
                    ),
                    shape = shape
                )
                .clip(shape)
                .clickable(
                    enabled = isEnabled,
                    interactionSource = interactionSource,
                    action = onClick
                )
                .defaultMinSize(
                    minWidth = if (hasMinWidth) minWidth(size) else Dp.Unspecified,
                    minHeight = height(size)
                ),
        ) {
            Row(
                modifier = Modifier
                    .padding(paddingValues = contentPadding(size)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

@Composable
private fun height(size: Size): Dp {
    return when (size) {
        Size.XXS -> 24.dp
        Size.XS -> 36.dp
        Size.S -> 40.dp
        Size.M -> 48.dp
        Size.L -> 56.dp
    }
}

@Composable
private fun containerColor(type: ButtonType, palette: PaletteColors): ButtonColorState {
    return ButtonColorState(
        default = when (type) {
            ButtonType.PRIMARY -> palette.main
            ButtonType.SECONDARY -> AppTheme.colorScheme.BG4
            ButtonType.TEXT -> Color.Transparent
            ButtonType.OUTLINE -> Color.Transparent
        },
        pressed = when (type) {
            ButtonType.PRIMARY -> palette.dark5
            else -> palette.alpha10
        },
        disabled = when (type) {
            ButtonType.PRIMARY,
            ButtonType.SECONDARY -> AppTheme.colorScheme.BG2
            ButtonType.TEXT,
            ButtonType.OUTLINE -> Color.Transparent
        }
    )
}

@Composable
private fun strokeColor(type: ButtonType, palette: PaletteColors): ButtonColorState {
    return ButtonColorState(
        default = when (type) {
            ButtonType.PRIMARY -> palette.main
            ButtonType.SECONDARY -> AppTheme.colorScheme.BG4
            ButtonType.TEXT -> Color.Transparent
            ButtonType.OUTLINE -> palette.alpha50
        },
        pressed = when (type) {
            ButtonType.PRIMARY -> palette.dark5
            else -> palette.alpha10
        },
        disabled = when (type) {
            ButtonType.PRIMARY,
            ButtonType.SECONDARY -> AppTheme.colorScheme.BG2
            ButtonType.TEXT -> Color.Transparent
            ButtonType.OUTLINE -> AppTheme.colorScheme.BG4
        }
    )
}

@Composable
private fun contentColor(type: ButtonType, palette: PaletteColors): ButtonColorState {
    return ButtonColorState(
        default = when (type) {
            ButtonType.PRIMARY -> Color.White
            ButtonType.SECONDARY -> AppTheme.colorScheme.T8
            ButtonType.TEXT -> AppTheme.colorScheme.T8
            ButtonType.OUTLINE -> AppTheme.colorScheme.T8
        },
        pressed = when (type) {
            ButtonType.PRIMARY -> AppTheme.colorScheme.T1
            else -> palette.dark5
        },
        disabled = AppTheme.colorScheme.T4
    )
}

@Composable
private fun textStyle(size: Size): TextStyle {
    return when (size) {
        Size.XXS -> AppTheme.typography.B6
        Size.XS -> AppTheme.typography.B5
        Size.S -> AppTheme.typography.B4
        Size.M -> AppTheme.typography.B3
        Size.L -> AppTheme.typography.B2
    }
}