package com.inconceptlabs.designsystem.components.switch

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntOffset
import com.inconceptlabs.designsystem.components.ToggleState
import com.inconceptlabs.designsystem.components.switch.tokens.SwitchTokens
import com.inconceptlabs.designsystem.components.toggle.ToggleSize
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.colors.paletteColors

val LocalSwitchTokens = compositionLocalOf { SwitchTokens.Default }

@Composable
fun Switch(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    keyColor: KeyColor = KeyColor.Primary,
    size: ToggleSize = ToggleSize.S,
    onCheckedChange: (Boolean) -> Unit,
) {
    LocalSwitchTokens.current.Switch(
        isChecked = isChecked,
        modifier = modifier,
        isEnabled = isEnabled,
        keyColor = keyColor,
        size = size,
        onCheckedChange = onCheckedChange,
    )
}

@Composable
fun SwitchTokens.Switch(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    keyColor: KeyColor = KeyColor.Primary,
    size: ToggleSize = ToggleSize.S,
    onCheckedChange: (Boolean) -> Unit,
) {
    val initialState = if (isEnabled) ToggleState.Default else ToggleState.Disabled
    var state by remember {
        mutableStateOf(initialState)
    }

    val paletteColors = keyColor.paletteColors()

    CompositionLocalProvider(
        LocalComponentState providesDefault state,
    ) {
        val switchSize = requiredSize(size)
        val thumbSizeDp = thumbSize(isChecked = isChecked, size = size)
        val horizontalPadding = contentPadding(isChecked = isChecked, size = size)
            .calculateLeftPadding(LocalLayoutDirection.current)

        val endPosition = switchSize.width - thumbSizeDp - horizontalPadding

        val thumbPositionDp by animateDpAsState(
            targetValue = if (isChecked) endPosition else horizontalPadding,
            animationSpec = tween(durationMillis = 200),
            label = "ThumbPosition"
        )

        Box(
            modifier = modifier
                .requiredSize(
                    size = requiredSize(size)
                )
                .background(
                    color = containerColor(isChecked, paletteColors),
                    shape = containerShape,
                )
                .border(
                    width = borderWidth(size),
                    color = borderColor(isChecked, paletteColors),
                    shape = containerShape,
                )
        ) {
            Box(
                modifier = Modifier
                    .size(
                        size = thumbSize(isChecked, size)
                    )
                    .offset {
                        IntOffset(
                            x = thumbPositionDp.roundToPx(),
                            y = ((switchSize.height - thumbSizeDp) / 2).roundToPx(),
                        )
                    }
                    .background(
                        color = thumbColor(isChecked, paletteColors),
                        shape = thumbShape,
                    )
                    .pointerInput(isChecked, isEnabled) {
                        if (!isEnabled) return@pointerInput

                        detectTapGestures(
                            onPress = {
                                state = ToggleState.Pressed
                                tryAwaitRelease()
                                state = initialState
                                onCheckedChange(!isChecked)
                            },
                        )
                    }
            )
        }
    }
}