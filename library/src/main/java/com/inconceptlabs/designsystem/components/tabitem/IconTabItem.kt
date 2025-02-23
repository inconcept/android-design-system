package com.inconceptlabs.designsystem.components.tabitem

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import com.inconceptlabs.designsystem.components.TabItemState
import com.inconceptlabs.designsystem.components.buttons.ButtonType
import com.inconceptlabs.designsystem.components.buttons.IconButton
import com.inconceptlabs.designsystem.components.buttons.token.LocalIconButtonTokens
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.attributes.CornerType
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.utils.waitForUpOrCancellationIgnoringBounds

@Composable
fun IconTabItem(
    icon: Painter,
    modifier: Modifier = Modifier,
    size: TabItemSize = TabItemSize.S,
    keyColor: KeyColor = KeyColor.PRIMARY,
    initialState: TabItemState = TabItemState.Default,
    onClick: () -> Unit = { },
) {
    var state by remember { mutableStateOf(initialState) }

    CompositionLocalProvider(
        LocalComponentState provides state,
        LocalIconButtonTokens provides LocalTabItemTokens.current,
    ) {
        IconButton(
            icon = icon,
            modifier = modifier
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        awaitFirstDown(requireUnconsumed = false)
                        state = TabItemState.Pressed
                        waitForUpOrCancellationIgnoringBounds()
                        state = TabItemState.Active
                    }
                },
            isEnabled = true,
            size = when (size) {
                TabItemSize.XS -> Size.XS
                TabItemSize.S -> Size.S
            },
            type = ButtonType.OUTLINE,
            keyColor = keyColor,
            cornerType = CornerType.ROUNDED,
            onClick = onClick,
        )
    }
}