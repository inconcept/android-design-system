package com.inconceptlabs.designsystem.components.buttons.token

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.components.buttons.ButtonType
import com.inconceptlabs.designsystem.components.tabitem.TabItemTokens
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

val LocalButtonTokens = compositionLocalOf { ButtonTokens.Default }
val LocalIconButtonTokens = compositionLocalOf { ButtonTokens.Icon }

interface ButtonTokens {

    fun height(size: Size): Dp

    fun contentPadding(size: Size): PaddingValues

    fun cornerRadius(size: Size): Dp

    fun minWidth(size: Size): Dp

    fun iconSize(size: Size): Dp

    @Composable
    fun strokeWidth(size: Size): Dp

    @Composable
    fun textStyle(size: Size): TextStyle

    @Composable
    fun containerColor(type: ButtonType, palette: PaletteColors): Color

    @Composable
    fun strokeColor(type: ButtonType, palette: PaletteColors): Color

    @Composable
    fun contentColor(type: ButtonType, palette: PaletteColors): Color

    companion object {

        val Default: ButtonTokens = ButtonTokensImpl
        val Icon: ButtonTokens = IconButtonTokens
        val TabItem: ButtonTokens = TabItemTokens()
    }
}