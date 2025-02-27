package com.inconceptlabs.designsystem.components.input.tokens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.attributes.Size

object InputFormTokensImpl : InputFormTokens {

    override val horizontalPadding: Dp
        get() = 16.dp

    override val startIconPadding: Dp
        get() = 10.dp

    override val endIconPadding: Dp
        get() = 8.dp

    override fun cornerRadius(size: Size): Dp {
        return when (size) {
            Size.XXS,
            Size.XS -> 12.dp
            Size.S -> 14.dp
            Size.M -> 16.dp
            Size.L -> 18.dp
        }
    }

    override fun titleIconSize(size: Size): Dp {
        return when (size) {
            Size.XXS,
            Size.XS,
            Size.S -> 16.dp
            Size.M,
            Size.L -> 18.dp
        }
    }

    override fun formIconSize(size: Size): Dp {
        return when (size) {
            Size.XXS,
            Size.XS -> 16.dp
            Size.S -> 18.dp
            Size.M -> 20.dp
            Size.L -> 22.dp
        }
    }
}