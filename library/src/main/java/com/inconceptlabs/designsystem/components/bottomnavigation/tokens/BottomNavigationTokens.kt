package com.inconceptlabs.designsystem.components.bottomnavigation.tokens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

interface BottomNavigationTokens {

    val height: Dp
    val paddingValues: PaddingValues

    @get:Composable val backgroundColor: Color

    @get:Composable val topBorderWidth: Dp
    @get:Composable val topBorderColor: Color
}