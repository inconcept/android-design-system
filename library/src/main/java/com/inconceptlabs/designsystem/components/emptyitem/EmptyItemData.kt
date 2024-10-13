package com.inconceptlabs.designsystem.components.emptyitem

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.inconceptlabs.designsystem.theme.attributes.KeyColor

data class EmptyItemData(
    val icon: @Composable () -> Painter,
    val title: @Composable () -> String,
    val description: @Composable () -> String,
    val keyColor: KeyColor = KeyColor.PRIMARY,
    val buttonText: @Composable (() -> String)? = null,
    val onButtonClick: () -> Unit = { },
) {

    constructor(
        @DrawableRes iconId: Int,
        @StringRes titleId: Int,
        @StringRes descriptionId: Int,
        keyColor: KeyColor = KeyColor.PRIMARY,
        @StringRes buttonTextId: Int? = null,
        onButtonClick: () -> Unit = { },
    ) : this(
        icon = { painterResource(iconId) },
        title = { stringResource(titleId) },
        description = { stringResource(descriptionId) },
        keyColor = keyColor,
        buttonText = buttonTextId?.let { { stringResource(it) } },
        onButtonClick = onButtonClick
    )
}