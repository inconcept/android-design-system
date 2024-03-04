package com.inconceptlabs.designsystem.theme.tokens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.attributes.Size

internal object InputFormTokensImpl : InputFormTokens {

    override val horizontalPadding = 16.dp

    override val startIconPadding = 10.dp
    override val endIconPadding = 8.dp

    override val Size.cornerRadius: Dp
        get() = when (this) {
            Size.XXS,
            Size.XS -> 12.dp
            Size.S -> 14.dp
            Size.M -> 16.dp
            Size.L -> 18.dp
        }

    override val Size.titleIconSize: Dp
        get() = when (this) {
            Size.XXS,
            Size.XS,
            Size.S -> 16.dp
            Size.M -> 18.dp
            Size.L -> 18.dp
        }

    override val Size.formIconSize: Dp
        get() = when (this) {
            Size.XXS,
            Size.XS -> 16.dp
            Size.S -> 18.dp
            Size.M -> 20.dp
            Size.L -> 22.dp
        }
}