package com.inconceptlabs.designsystem.components

sealed interface ComponentState {
    data object Default : ComponentState
}

sealed interface ButtonState : ComponentState {
    data object Default : ButtonState
    data object Pressed : ButtonState
    data object Disabled : ButtonState
}

sealed interface TabItemState : ComponentState {
    data object Default : TabItemState
    data object Pressed : TabItemState
    data object Active : TabItemState
}