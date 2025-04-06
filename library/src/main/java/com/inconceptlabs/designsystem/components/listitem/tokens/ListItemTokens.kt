package com.inconceptlabs.designsystem.components.listitem.tokens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.components.ListItemState

interface ListItemTokens {

    val contentHorizontalArrangement: Arrangement.Horizontal
    val contentVerticalAlignment: Alignment.Vertical
    val titleSubtitleAlignment: Alignment.Horizontal

    @get:Composable
    val padding: PaddingValues

    @get:Composable
    val shape: Shape

    @get:Composable
    val titleTextStyle: TextStyle

    @get:Composable
    val subtitleTextStyle: TextStyle

    @get:Composable
    val startIconSize: Dp

    @get:Composable
    val endIconSize: Dp

    @get:Composable
    val statusDotSize: Dp

    @get:Composable
    val titleSubtitleSpacing: Dp

    @get:Composable
    val containerColor: Color

    @get:Composable
    val contentColor: Color

    @get:Composable
    val titleColor: Color

    @get:Composable
    val subtitleColor: Color

    @get:Composable
    val startIconTint: Color

    @get:Composable
    val endIconTint: Color

    suspend fun PointerInputScope.updateStateOnPointerInput(
        state: MutableState<ListItemState>,
        hasSelectedState: Boolean,
    )

    companion object {

        val Default: ListItemTokens = ListItemTokensImpl
    }
}