package com.inconceptlabs.designsystem.theme.tokens

import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.theme.attributes.Size

interface InputFormTokens {

    val horizontalPadding: Dp

    val startIconPadding: Dp
    val endIconPadding: Dp

    val Size.cornerRadius: Dp

    val Size.titleIconSize: Dp
    val Size.formIconSize: Dp
}