package com.inconceptlabs.designsystem.utils

import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Indication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.Fragment
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.NoIndication
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.ColorScheme
import com.inconceptlabs.designsystem.theme.tokens.strokeWidthThick
import com.inconceptlabs.designsystem.theme.tokens.strokeWidthThin
import com.inconceptlabs.designsystem.theme.typography.Barlow
import com.inconceptlabs.designsystem.theme.typography.Typography

@Suppress("FunctionName")
fun Fragment.ProvideThemedContent(
    colorScheme: ColorScheme = ColorScheme.Light,
    typography: Typography = Barlow,
    indication: Indication = NoIndication,
    content: @Composable () -> Unit,
): View {
    return ComposeView(requireContext()).apply {
        setContent {
            AppTheme(
                colorScheme = colorScheme,
                typography = typography,
                indication = indication,
                content = content
            )
        }
    }
}

@Suppress("FunctionName")
fun ComponentActivity.ProvideThemedContent(
    colorScheme: ColorScheme = ColorScheme.Light,
    typography: Typography = Barlow,
    indication: Indication = NoIndication,
    content: @Composable () -> Unit,
) {
    setContent {
        AppTheme(
            colorScheme = colorScheme,
            typography = typography,
            indication = indication,
            content = content
        )
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