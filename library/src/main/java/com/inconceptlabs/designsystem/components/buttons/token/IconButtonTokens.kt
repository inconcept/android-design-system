package com.inconceptlabs.designsystem.components.buttons.token

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.attributes.Size

object IconButtonTokens : ButtonTokens by ButtonTokensImpl {

    override fun contentPadding(size: Size): PaddingValues {
        return when (size) {
            Size.XXS -> PaddingValues(4.dp)
            Size.XS -> PaddingValues(9.dp)
            Size.S -> PaddingValues(10.dp)
            Size.M -> PaddingValues(13.dp)
            Size.L -> PaddingValues(16.dp)
        }
    }
}