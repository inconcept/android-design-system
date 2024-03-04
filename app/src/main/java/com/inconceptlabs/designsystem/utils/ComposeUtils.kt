package com.inconceptlabs.designsystem.utils

import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.Fragment
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.tokens.strokeWidthThick
import com.inconceptlabs.designsystem.theme.tokens.strokeWidthThin

@Suppress("FunctionName")
fun Fragment.ComposeViewWithTheme(
    content: @Composable () -> Unit
): View {
    return ComposeView(requireContext()).apply {
        setContent {
            AppTheme(content = content)
        }
    }
}

fun getStrokeWidth(size: Size): Dp {
    return when (size) {
        Size.XXS,
        Size.XS -> strokeWidthThin
        Size.S,
        Size.M,
        Size.L -> strokeWidthThick
    }
}

fun Modifier.clickable(
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    action: () -> Unit,
): Modifier {
    return this.clickable(
        enabled = enabled,
        indication = null,
        interactionSource = interactionSource,
        onClick = action
    )
}

fun Modifier.clearFocusOnGesture(): Modifier {
    return this.composed {
        val focusManager = LocalFocusManager.current

        pointerInput(Unit) {
            detectVerticalDragGestures { _, _ ->
                focusManager.clearFocus()
            }
        }.pointerInput(Unit) {
            detectTapGestures {
                focusManager.clearFocus()
            }
        }
    }
}