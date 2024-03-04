package com.inconceptlabs.designsystem.theme.tokens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.theme.attributes.Size

interface ButtonTokens : Tokens {

    fun contentPadding(size: Size): PaddingValues

    fun cornerRadius(size: Size): Dp

    fun minWidth(size: Size): Dp

    fun iconSize(size: Size): Dp

    companion object {

        val default: ButtonTokens = ButtonTokensImpl
    }
}