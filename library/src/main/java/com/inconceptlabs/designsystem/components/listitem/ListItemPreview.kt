@file:OptIn(ExperimentalLayoutApi::class)

package com.inconceptlabs.designsystem.components.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.components.ListItemState
import com.inconceptlabs.designsystem.components.avatar.Avatar
import com.inconceptlabs.designsystem.components.buttons.ButtonType
import com.inconceptlabs.designsystem.components.buttons.TextButton
import com.inconceptlabs.designsystem.components.buttons.token.ButtonTokens
import com.inconceptlabs.designsystem.components.buttons.token.ButtonTokensImpl
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.components.label.Label
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.ThemeTokens
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.utils.componentGroupModifier

private const val startIconId = android.R.drawable.ic_input_add
private const val endIconId = android.R.drawable.ic_menu_close_clear_cancel

private object CircularButtonTokens : ButtonTokens by ButtonTokensImpl {
    override fun cornerRadius(size: Size): Dp = 99.dp
}

private val avatar = @Composable {
    Avatar(painter = painterResource(R.drawable.ic_profile_random))
}

private val label = @Composable {
    Label(text = "5")
}

@Preview
@Composable
private fun ListItemPreview() {
    AppTheme(
        tokens = ThemeTokens(
            button = CircularButtonTokens
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.BG1)
                .padding(16.dp)
        ) {
            InteractableListItem()

            BasicListItems()

            ComplexListItems()
        }
    }
}

@Composable
private fun InteractableListItem() {
    val showAvatar = remember { mutableStateOf(true) }
    val showSubtitle = remember { mutableStateOf(true) }
    val showStartIcon = remember { mutableStateOf(false) }
    val showEndIcon = remember { mutableStateOf(true) }
    val showStatusDot = remember { mutableStateOf(false) }
    val showLabelText = remember { mutableStateOf(false) }
    val isSelected = remember { mutableStateOf(false) }
    val hasSelectedState = remember { mutableStateOf(false) }

    val buttonType: (Boolean) -> ButtonType = { isChecked ->
        if (isChecked) ButtonType.Primary else ButtonType.Outline
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = componentGroupModifier,
    ) {
        Text(
            text = "Interactable List Item",
            style = AppTheme.typography.S3,
        )

        ListItem(
            title = "List Item",
            subtitle = "List Item".takeIf { showSubtitle.value },
            startIcon = painterResource(startIconId).takeIf { showStartIcon.value },
            endIcon = painterResource(endIconId).takeIf { showEndIcon.value },
            showStatusDot = showStatusDot.value,
            isSelected = isSelected.value,
            hasSelectedState = hasSelectedState.value,
            avatar = avatar.takeIf { showAvatar.value },
            label = label.takeIf { showLabelText.value },
            onClick = { println("BasicListItem clicked.") }
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            mapOf(
                "Avatar" to showAvatar,
                "Subtitle" to showSubtitle,
                "Start icon" to showStartIcon,
                "End icon" to showEndIcon,
                "Status dot" to showStatusDot,
                "Label" to showLabelText,
                "Selected" to isSelected,
                "Selectable" to hasSelectedState,
            ).forEach { (text, isChecked) ->
                TextButton(
                    text = text,
                    type = buttonType(isChecked.value),
                    size = Size.XS,
                    onClick = { isChecked.value = !isChecked.value }
                )
            }
        }
    }
}

@Composable
private fun BasicListItems() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = componentGroupModifier,
    ) {
        ListItemState.entries.forEach { state ->
            CompositionLocalProvider(
                LocalComponentState provides state,
            ) {
                ListItem(
                    title = "List Item",
                    startIcon = painterResource(startIconId),
                    endIcon = painterResource(endIconId),
                    showStatusDot = true,
                    hasSelectedState = true,
                    onClick = { println("BasicListItem clicked.") }
                )
            }
        }
    }
}

@Composable
private fun ComplexListItems() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = componentGroupModifier
    ) {
        ListItemState.entries.forEach { state ->
            CompositionLocalProvider(
                LocalComponentState provides state,
            ) {
                ListItem(
                    title = "List Item",
                    subtitle = "List Item",
                    startIcon = painterResource(startIconId),
                    endIcon = painterResource(endIconId),
                    avatar = avatar,
                    label = label,
                    onClick = { println("ComplexListItem clicked.") }
                )
            }
        }
    }
}