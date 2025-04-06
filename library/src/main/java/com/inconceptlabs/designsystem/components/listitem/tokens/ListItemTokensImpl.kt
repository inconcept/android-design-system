package com.inconceptlabs.designsystem.components.listitem.tokens

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.ListItemState
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.LocalPaletteColors
import com.inconceptlabs.designsystem.theme.colors.PaletteColors
import com.inconceptlabs.designsystem.utils.waitForUpOrCancellationIgnoringBounds

object ListItemTokensImpl : ListItemTokens {

    private val state: ListItemState
        @Composable
        get() = LocalComponentState.current as ListItemState

    private val palette: PaletteColors
        @Composable
        get() = LocalPaletteColors.current

    override val contentHorizontalArrangement: Arrangement.Horizontal
        get() = Arrangement.spacedBy(10.dp)

    override val contentVerticalAlignment: Alignment.Vertical
        get() = Alignment.CenterVertically

    override val titleSubtitleAlignment: Alignment.Horizontal
        get() = Alignment.Start

    override val padding: PaddingValues
        @Composable
        get() = PaddingValues(horizontal = 16.dp, vertical = 12.dp)

    override val shape: Shape
        @Composable
        get() = RoundedCornerShape(8.dp)

    override val titleTextStyle: TextStyle
        @Composable
        get() = AppTheme.typography.P4

    override val subtitleTextStyle: TextStyle
        @Composable
        get() = AppTheme.typography.P5

    override val startIconSize: Dp
        @Composable
        get() = 22.dp

    override val endIconSize: Dp
        @Composable
        get() = 22.dp

    override val statusDotSize: Dp
        @Composable
        get() = 8.dp

    override val titleSubtitleSpacing: Dp
        @Composable
        get() = 4.dp

    override val titleColor: Color
        @Composable
        get() = contentColor

    override val subtitleColor: Color
        @Composable
        get() = AppTheme.colorScheme.T6

    override val startIconTint: Color
        @Composable
        get() = contentColor

    override val endIconTint: Color
        @Composable
        get() = contentColor

    override val containerColor: Color
        @Composable
        get() = when (state) {
            ListItemState.Selected -> palette.alpha10
            ListItemState.Pressed -> AppTheme.colorScheme.BG3
            ListItemState.Default -> Color.Transparent
        }

    override val contentColor: Color
        @Composable
        get() = when (state) {
            ListItemState.Default -> AppTheme.colorScheme.T8
            ListItemState.Pressed -> palette.main
            ListItemState.Selected -> palette.dark5
        }

    override suspend fun PointerInputScope.updateStateOnPointerInput(
        state: MutableState<ListItemState>,
        hasSelectedState: Boolean,
    ) {
        awaitEachGesture {
            awaitFirstDown(requireUnconsumed = false)
            val isSelected = state.value == ListItemState.Selected

            if (!isSelected && !hasSelectedState) {
                state.value = ListItemState.Pressed
            }

            waitForUpOrCancellationIgnoringBounds()

            state.value = if (!isSelected && hasSelectedState) {
                ListItemState.Selected
            } else {
                ListItemState.Default
            }
        }
    }
}