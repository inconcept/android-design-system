package com.inconceptlabs.designsystem.components.bottomnavigation

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationItem(
    val icon: Painter,
    val selectedIcon: Painter = icon,
    val text: String? = null,
    val isSelected: Boolean = false,
    val onClick: () -> Unit = {},
)