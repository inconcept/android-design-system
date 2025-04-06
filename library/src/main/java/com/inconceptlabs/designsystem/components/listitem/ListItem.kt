@file:SuppressLint("ModifierParameter")

package com.inconceptlabs.designsystem.components.listitem

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import com.inconceptlabs.designsystem.components.ListItemState
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.components.core.StatusDot
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.components.listitem.tokens.ListItemTokens
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.LocalPaletteColors
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.colors.paletteColors

val LocalListItemTokens = compositionLocalOf { ListItemTokens.Default }

@Composable
fun ListItem(
    keyColor: KeyColor = KeyColor.Primary,
    isSelected: Boolean = false,
    hasSelectedState: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {},
) {
    with(LocalListItemTokens.current) {
        ListItem(
            keyColor = keyColor,
            isSelected = isSelected,
            hasSelectedState = hasSelectedState,
            modifier = modifier,
            onClick = onClick,
            content = content,
        )
    }
}

@Composable
fun ListItem(
    title: String,
    subtitle: String? = null,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
    showStatusDot: Boolean = false,
    keyColor: KeyColor = KeyColor.Primary,
    itemKeyColor: KeyColor = KeyColor.ColorNew,
    isSelected: Boolean = false,
    hasSelectedState: Boolean = false,
    modifier: Modifier = Modifier,
    avatar: (@Composable () -> Unit)? = null,
    label: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {},
) {
    with(LocalListItemTokens.current) {
        ListItem(
            title = title,
            subtitle = subtitle,
            startIcon = startIcon,
            endIcon = endIcon,
            showStatusDot = showStatusDot,
            keyColor = keyColor,
            itemKeyColor = itemKeyColor,
            isSelected = isSelected,
            hasSelectedState = hasSelectedState,
            modifier = modifier,
            avatar = avatar,
            label = label,
            onClick = onClick,
        )
    }
}

@Composable
fun ListItemTokens.ListItem(
    keyColor: KeyColor = KeyColor.Primary,
    isSelected: Boolean = false,
    hasSelectedState: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {},
) {
    val palette = keyColor.paletteColors()
    val state = remember(isSelected) {
        val initialValue = if (isSelected) ListItemState.Selected else ListItemState.Default
        mutableStateOf(initialValue)
    }

    CompositionLocalProvider(
        LocalComponentState providesDefault state.value,
        LocalPaletteColors provides palette,
    ) {
        Row(
            horizontalArrangement = contentHorizontalArrangement,
            verticalAlignment = contentVerticalAlignment,
            modifier = modifier
                .background(
                    color = containerColor,
                    shape = shape,
                )
                .padding(
                    paddingValues = padding,
                )
                .pointerInput(hasSelectedState) {
                    updateStateOnPointerInput(state, hasSelectedState)
                }
                .clickable(onClick = onClick),
            content = content,
        )
    }
}

@Composable
fun ListItemTokens.ListItem(
    title: String,
    subtitle: String? = null,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
    showStatusDot: Boolean = false,
    keyColor: KeyColor = KeyColor.Primary,
    itemKeyColor: KeyColor = KeyColor.ColorNew,
    isSelected: Boolean = false,
    hasSelectedState: Boolean = false,
    modifier: Modifier = Modifier,
    avatar: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {},
) {
    ListItem(
        keyColor = keyColor,
        isSelected = isSelected,
        hasSelectedState = hasSelectedState,
        modifier = modifier,
        onClick = onClick,
    ) {
        StartIcon(startIcon)

        avatar?.invoke()

        Column(
            verticalArrangement = Arrangement.spacedBy(titleSubtitleSpacing),
            horizontalAlignment = titleSubtitleAlignment,
            modifier = Modifier.weight(1f)
        ) {
            Title(title)

            Subtitle(subtitle)
        }

        StatusDot(
            isVisible = showStatusDot,
            keyColor = itemKeyColor
        )

        label?.invoke()

        EndIcon(endIcon)
    }
}

@Composable
private fun ListItemTokens.StartIcon(
    startIcon: Painter?,
) {
    if (startIcon != null) {
        Icon(
            painter = startIcon,
            tint = startIconTint,
            modifier = Modifier
                .size(startIconSize)
        )
    }
}

@Composable
private fun ListItemTokens.EndIcon(endIcon: Painter?) {
    if (endIcon == null) return

    Icon(
        painter = endIcon,
        tint = endIconTint,
        modifier = Modifier
            .size(endIconSize)
    )
}

@Composable
private fun ListItemTokens.Title(text: String) {
    Text(
        text = text,
        style = titleTextStyle,
        color = titleColor,
    )
}

@Composable
private fun ListItemTokens.Subtitle(subtitle: String?) {
    if (subtitle.isNullOrBlank()) return

    Text(
        text = subtitle,
        style = subtitleTextStyle,
        color = subtitleColor,
    )
}

@Composable
private fun ListItemTokens.StatusDot(isVisible: Boolean, keyColor: KeyColor) {
    if (!isVisible) return

    StatusDot(
        keyColor = keyColor,
        size = statusDotSize,
    )
}