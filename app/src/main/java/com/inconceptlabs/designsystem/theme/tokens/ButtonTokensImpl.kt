package com.inconceptlabs.designsystem.theme.tokens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.attributes.Size

internal object ButtonTokensImpl : ButtonTokens {

    override fun contentPadding(size: Size): PaddingValues = when (size) {
        Size.XXS -> PaddingValues(horizontal = 8.dp)
        Size.XS -> PaddingValues(horizontal = 14.dp)
        Size.S -> PaddingValues(horizontal = 16.dp)
        Size.M -> PaddingValues(horizontal = 18.dp)
        Size.L -> PaddingValues(horizontal = 20.dp)
    }

    override fun cornerRadius(size: Size): Dp = when (size) {
        Size.XXS -> 10.dp
        Size.XS -> 12.dp
        Size.S -> 14.dp
        Size.M -> 16.dp
        Size.L -> 18.dp
    }

    override fun minWidth(size: Size): Dp = when (size) {
        Size.XXS -> 70.dp
        Size.XS -> 80.dp
        Size.S -> 90.dp
        Size.M -> 100.dp
        Size.L -> 120.dp
    }

    override fun iconSize(size: Size): Dp = when (size) {
        Size.XXS -> 16.dp
        Size.XS -> 18.dp
        Size.S -> 20.dp
        Size.M -> 22.dp
        Size.L -> 24.dp
    }
}

internal object IconButtonTokensImpl : ButtonTokens by ButtonTokensImpl {

    override fun contentPadding(size: Size): PaddingValues = when (size) {
        Size.XXS -> PaddingValues(4.dp)
        Size.XS -> PaddingValues(9.dp)
        Size.S -> PaddingValues(10.dp)
        Size.M -> PaddingValues(13.dp)
        Size.L -> PaddingValues(16.dp)
    }
}