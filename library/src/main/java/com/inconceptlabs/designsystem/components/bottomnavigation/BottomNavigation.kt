package com.inconceptlabs.designsystem.components.bottomnavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionOnScreen
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.components.core.LocalCoreTokens
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.utils.topBorder
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun BottomNavigation(
    items: List<BottomNavigationItem>,
    modifier: Modifier = Modifier,
    isLabeled: Boolean = false,
) {
    if (items.isEmpty()) return

    var showHint by remember {
        mutableStateOf(false)
    }
    val hintPosition = remember {
        mutableStateOf<Int?>(null)
    }
    val itemOffsets by remember {
        val default = Array(items.size) { Offset.Zero }
        mutableStateOf(default)
    }

    LaunchedEffect(hintPosition.value) {
        if (isLabeled) return@LaunchedEffect
        if (hintPosition.value == null) return@LaunchedEffect

        showHint = true
        delay(1.5.seconds)
        showHint = false
        delay(0.3.seconds)
        hintPosition.value = null
    }

    Box(
        modifier = Modifier.background(Color.Transparent)
    ) {
        NavigationItemHint(
            items = items,
            itemOffsets = itemOffsets,
            showHint = showHint,
            hintPosition = hintPosition.value,
        )

        NavigationItems(
            modifier = modifier,
            items = items,
            itemOffsets = itemOffsets,
            hintPosition = hintPosition,
            isLabeled = isLabeled,
        )
    }
}

@Composable
private fun NavigationItems(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    itemOffsets: Array<Offset>,
    hintPosition: MutableState<Int?>,
    isLabeled: Boolean,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .defaultMinSize(minHeight = 56.dp)
            .topBorder(
                color = AppTheme.colorScheme.BG5,
                strokeWidth = LocalCoreTokens.current.strokeWidthThick,
            )
            .background(AppTheme.colorScheme.BG2)
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        items.forEachIndexed { index, item ->
            NavigationItem(
                index = index,
                item = item,
                itemOffsets = itemOffsets,
                hintPosition = hintPosition,
                isLabeled = isLabeled,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RowScope.NavigationItem(
    index: Int,
    item: BottomNavigationItem,
    itemOffsets: Array<Offset>,
    hintPosition: MutableState<Int?>,
    isLabeled: Boolean,
) {
    val hapticFeedback = LocalHapticFeedback.current

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .weight(1f)
            .updateOffsets(index, itemOffsets)
            .combinedClickable(
                interactionSource = remember(::MutableInteractionSource),
                indication = LocalIndication.current,
                onClick = { item.onClick() },
                onLongClick = {
                    if (isLabeled) return@combinedClickable
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    hintPosition.value = index
                }
            )
    ) {
        val itemColor = if (item.isSelected) {
            AppTheme.colorScheme.secondary.dark5
        } else {
            AppTheme.colorScheme.T8
        }

        Icon(
            painter = if (item.isSelected) item.selectedIcon else item.icon,
            tint = itemColor,
        )

        if (isLabeled && !item.text.isNullOrBlank()) {
            Text(
                text = item.text,
                style = AppTheme.typography.B6,
                color = itemColor,
            )
        }
    }
}

@Composable
private fun NavigationItemHint(
    items: List<BottomNavigationItem>,
    itemOffsets: Array<Offset>,
    showHint: Boolean,
    hintPosition: Int? = null,
) {
    if (hintPosition == null) return

    var layoutSize by remember {
        mutableStateOf(IntSize.Zero)
    }

    AnimatedVisibility(
        visible = showHint,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier
            .offset {
                val pressedItemX = itemOffsets[hintPosition].x.toInt()

                IntOffset(
                    x = pressedItemX - (layoutSize.width / 2),
                    y = -(layoutSize.height + 16.dp.roundToPx()),
                )
            }
            .onSizeChanged { layoutSize = it }
    ) {
        val text = items[hintPosition].text
        if (text.isNullOrBlank()) return@AnimatedVisibility

        Text(
            text = text,
            style = AppTheme.typography.P5,
            color = AppTheme.colorScheme.T1,
            modifier = Modifier
                .zIndex(Float.MAX_VALUE)
                .background(
                    color = Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 16.dp, vertical = 6.dp)
        )
    }
}

private fun Modifier.updateOffsets(
    index: Int,
    itemOffsets: Array<Offset>,
): Modifier {
    return onGloballyPositioned {
        val x = it.positionOnScreen().x + it.size.width / 2
        itemOffsets[index] = it
            .positionOnScreen()
            .copy(x = x)
    }
}