package com.inconceptlabs.designsystem.components.buttons

import androidx.compose.ui.graphics.Color

data class ButtonColorState(
    val default: Color = Color.Unspecified,
    val pressed: Color = Color.Unspecified,
    val disabled: Color = Color.Unspecified,
) {

    fun forState(isEnabled: Boolean, isPressed: Boolean): Color {
        return when {
            !isEnabled -> disabled
            isPressed -> pressed
            else -> default
        }
    }
}

data class ButtonColors(
    val containerColor: ButtonColorState,
    val strokeColor: ButtonColorState,
    val contentColor: ButtonColorState,
) {

    companion object {

        val default: ButtonColors
            get() = ButtonColors(
                containerColor = ButtonColorState(),
                strokeColor = ButtonColorState(),
                contentColor = ButtonColorState(),
            )
    }
}