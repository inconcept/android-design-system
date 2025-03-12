package com.inconceptlabs.designsystem.components.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.inconceptlabs.designsystem.components.buttons.TextButton
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.components.notification.tokens.LocalNotificationTokens
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.colors.paletteColors

@Composable
fun Notification(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    keyColor: KeyColor = KeyColor.Primary,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
    buttonText: String? = null,
    isButtonEnabled: Boolean = true,
    onButtonClick: () -> Unit = { },
    onEndIconClick: () -> Unit = { },
) = with(LocalNotificationTokens.current) {
    val palette = keyColor.paletteColors()

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor(palette))
            .statusBarsPadding()
            .padding(paddingValues)
    ) {
        val (
            titleRef,
            descriptionRef,
            startIconRef,
            endIconRef,
            buttonRef,
        ) = createRefs()

        if (startIcon != null) {
            Icon(
                painter = startIcon,
                tint = startIconColor(palette),
                modifier = Modifier
                    .constrainAs(startIconRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .size(startIconSize)
            )
        }

        Text(
            text = title,
            style = titleTextStyle(),
            color = titleColor(palette),
            maxLines = titleMaxLines,
            modifier = Modifier.constrainAs(titleRef) {
                if (startIcon != null) {
                    start.linkTo(startIconRef.end, margin = titleStartPadding)
                } else {
                    start.linkTo(parent.start)
                }

                val endAnchor = when {
                    buttonText != null && description.lines().size < 2 -> buttonRef.start
                    endIcon != null -> endIconRef.start
                    else -> parent.end
                }

                end.linkTo(endAnchor, titleEndPadding)
                top.linkTo(parent.top)

                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = description,
            style = descriptionTextStyle(),
            color = descriptionColor(palette),
            maxLines = descriptionMaxLines,
            modifier = Modifier.constrainAs(descriptionRef) {
                start.linkTo(titleRef.start)
                end.linkTo(titleRef.end)
                top.linkTo(titleRef.bottom)
                bottom.linkTo(parent.bottom)

                width = Dimension.fillToConstraints
            }
        )

        if (buttonText != null) {
            TextButton(
                text = buttonText,
                size = buttonSize,
                keyColor = keyColor,
                isEnabled = isButtonEnabled,
                onClick = onButtonClick,
                modifier = Modifier.constrainAs(buttonRef) {
                    if (endIcon != null) {
                        end.linkTo(endIconRef.start, margin = buttonEndPadding)
                    } else {
                        end.linkTo(parent.end)
                    }

                    top.linkTo(descriptionRef.top)
                    bottom.linkTo(descriptionRef.bottom)
                }
            )
        }

        if (endIcon != null) {
            Icon(
                painter = endIcon,
                tint = endIconColor(palette),
                modifier = Modifier
                    .constrainAs(endIconRef) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable(onClick = onEndIconClick)
                    .size(endIconSize)
            )
        }
    }
}