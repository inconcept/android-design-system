package com.inconceptlabs.designsystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.components.ButtonState
import com.inconceptlabs.designsystem.components.buttons.token.LocalButtonTokens
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.LocalContentColor
import com.inconceptlabs.designsystem.theme.LocalIconSize
import com.inconceptlabs.designsystem.theme.LocalTextStyle
import com.inconceptlabs.designsystem.theme.attributes.CornerType
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.paletteColors

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
    content: @Composable RowScope.() -> Unit,
) = with(LocalButtonTokens.current) {
    val isPressed by interactionSource.collectIsPressedAsState()

    val shape = when (cornerType) {
        CornerType.CIRCULAR -> CircleShape
        CornerType.ROUNDED -> RoundedCornerShape(cornerRadius(size))
    }

    val palette = keyColor.paletteColors()

    val state = when {
        !isEnabled -> ButtonState.Disabled
        isPressed -> ButtonState.Pressed
        else -> ButtonState.Default
    }

    CompositionLocalProvider(
        LocalComponentState providesDefault state,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .background(
                    color = containerColor(type, palette),
                    shape = shape
                )
                .border(
                    border = BorderStroke(
                        width = strokeWidth(size),
                        color = strokeColor(type, palette),
                    ),
                    shape = shape,
                )
                .clip(shape)
                .clickable(
                    enabled = isEnabled,
                    interactionSource = interactionSource,
                    indication = LocalIndication.current,
                    onClick = onClick
                )
                .defaultMinSize(
                    minWidth = minWidth(size).takeIf { hasMinWidth } ?: Dp.Unspecified,
                    minHeight = height(size)
                ),
        ) {
            Row(
                modifier = Modifier
                    .padding(paddingValues = contentPadding(size)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    CompositionLocalProvider(
                        LocalContentColor provides contentColor(type, palette),
                        LocalTextStyle provides textStyle(size),
                        LocalIconSize provides iconSize(size),
                    ) {
                        content()
                    }
                }
            )
        }
    }
}