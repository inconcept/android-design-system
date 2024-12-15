package com.inconceptlabs.designsystem.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalContentColor
import com.inconceptlabs.designsystem.theme.LocalIconSize
import com.inconceptlabs.designsystem.theme.attributes.CornerType
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size

@Preview
@Composable
private fun ComponentPreview() {
    AppTheme {
        IconButton(
            icon = painterResource(id = R.drawable.ic_lock_outline),
            type = ButtonType.PRIMARY,
            onClick = {}
        )
    }
}

@Composable
fun IconButton(
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    size: Size = Size.M,
    type: ButtonType = ButtonType.TEXT,
    keyColor: KeyColor = KeyColor.PRIMARY,
    cornerType: CornerType = CornerType.ROUNDED,
) {
    CompositionLocalProvider(
        LocalButtonTokens provides LocalIconButtonTokens.current
    ) {
        Button(
            modifier = modifier,
            isEnabled = isEnabled,
            size = size,
            type = type,
            keyColor = keyColor,
            onClick = onClick,
            hasMinWidth = false,
            cornerType = cornerType,
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = LocalContentColor.current,
                modifier = Modifier
                    .size(LocalIconSize.current)
            )
        }
    }
}