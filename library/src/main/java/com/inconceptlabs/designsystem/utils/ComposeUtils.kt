package com.inconceptlabs.designsystem.utils

import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Indication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.fastAll
import androidx.compose.ui.util.fastAny
import androidx.fragment.app.Fragment
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.ThemeTokens
import com.inconceptlabs.designsystem.theme.colors.ColorScheme
import com.inconceptlabs.designsystem.theme.indication.NoIndication
import com.inconceptlabs.designsystem.theme.typography.Barlow
import com.inconceptlabs.designsystem.theme.typography.Typography

@Suppress("FunctionName")
fun Fragment.ProvideThemedContent(
    colorScheme: ColorScheme = ColorScheme.Light,
    typography: Typography = Barlow,
    indication: Indication = NoIndication,
    themeTokens: ThemeTokens = ThemeTokens.Default,
    content: @Composable () -> Unit,
): View {
    return ComposeView(requireContext()).apply {
        setContent {
            AppTheme(
                colorScheme = colorScheme,
                typography = typography,
                indication = indication,
                tokens = themeTokens,
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
    themeTokens: ThemeTokens = ThemeTokens.Default,
    content: @Composable () -> Unit,
) {
    setContent {
        AppTheme(
            colorScheme = colorScheme,
            typography = typography,
            indication = indication,
            tokens = themeTokens,
            content = content,
        )
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

/**
 * Custom pointer event handler that ignores out-of-bounds events.
 *
 * Unlike the standard [waitForUpOrCancellation], this function maintains
 * the pressed state even when the pointer moves outside the composable's bounds.
 * It mimics traditional Android touch listener behavior, only changing state
 */
suspend fun AwaitPointerEventScope.waitForUpOrCancellationIgnoringBounds(
    pass: PointerEventPass = PointerEventPass.Main,
): PointerInputChange? {
    while (true) {
        val event = awaitPointerEvent(pass)
        if (event.changes.fastAll { it.changedToUp() }) {
            return event.changes[0]
        }

        if (event.changes.fastAny { it.isConsumed }) {
            return null
        }

        val consumeCheck = awaitPointerEvent(PointerEventPass.Final)
        if (consumeCheck.changes.fastAny { it.isConsumed }) {
            return null
        }
    }
}

fun Modifier.dashedBorder(
    color: Color,
    cornerRadius: Dp,
    strokeWidth: Dp,
    dashWidth: Dp,
    dashGap: Dp = dashWidth,
): Modifier {
    return drawWithCache {
        onDrawBehind {
            drawRoundRect(
                color = color,
                cornerRadius = CornerRadius(
                    x = cornerRadius.toPx()
                ),
                style = Stroke(
                    width = strokeWidth.toPx(),
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(
                            dashWidth.toPx(),
                            dashGap.toPx()
                        ),
                    )
                )
            )
        }
    }
}

fun Modifier.topBorder(
    color: Color,
    strokeWidth: Dp,
): Modifier {
    return drawWithContent {
        drawContent()
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = strokeWidth.toPx()
        )
    }
}