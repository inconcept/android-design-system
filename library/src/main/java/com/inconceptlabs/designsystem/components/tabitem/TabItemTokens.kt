package com.inconceptlabs.designsystem.components.tabitem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.components.TabItemState
import com.inconceptlabs.designsystem.components.buttons.ButtonType
import com.inconceptlabs.designsystem.components.buttons.token.ButtonTokens
import com.inconceptlabs.designsystem.components.buttons.token.IconButtonTokens
import com.inconceptlabs.designsystem.components.core.LocalCoreTokens
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.PaletteColors

val LocalTabItemTokens = compositionLocalOf { ButtonTokens.TabItem }

class TabItemTokens : ButtonTokens by IconButtonTokens {

    @Composable
    override fun borderWidth(size: Size): Dp {
        return LocalCoreTokens.current.strokeWidthThin
    }

    @Composable
    override fun borderColor(type: ButtonType, palette: PaletteColors): Color {
        return when (LocalComponentState.current) {
            TabItemState.Default -> palette.alpha20
            TabItemState.Pressed -> palette.alpha20
            TabItemState.Active -> Color.Unspecified
            else -> Color.Unspecified
        }
    }

    @Composable
    override fun contentColor(type: ButtonType, palette: PaletteColors): Color {
        return when (LocalComponentState.current) {
            TabItemState.Default -> AppTheme.colorScheme.T8
            TabItemState.Pressed -> palette.main
            TabItemState.Active -> palette.dark5
            else -> Color.Unspecified
        }
    }

    @Composable
    override fun containerColor(type: ButtonType, palette: PaletteColors): Color {
        return when (LocalComponentState.current) {
            TabItemState.Default -> Color.Transparent
            TabItemState.Pressed -> Color.Transparent
            TabItemState.Active -> palette.alpha10
            else -> Color.Unspecified
        }
    }
}