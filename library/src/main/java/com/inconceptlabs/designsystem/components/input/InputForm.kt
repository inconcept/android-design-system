package com.inconceptlabs.designsystem.components.input

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.InputFormState
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.components.core.LocalCoreTokens
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.components.input.tokens.InputFormTokens
import com.inconceptlabs.designsystem.components.input.tokens.LocalInputFormTokens
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalComponentState
import com.inconceptlabs.designsystem.theme.LocalContentColor
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.paletteColors

@Composable
fun InputForm(
    input: String,
    onInputChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    size: InputFormSize = InputFormSize.M,
    type: InputFormType = InputFormType.Filled,
    keyColor: KeyColor = KeyColor.PRIMARY,
    maxCharacters: Int? = null,
    onEndIconClick: () -> Unit = {},
    hint: String? = null,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember(::MutableInteractionSource),
    singleLine: Boolean = true,
    isCharacterCounterVisible: Boolean = false,
    title: String? = null,
    @StringRes errorMessageRes: Int? = null,
    @StringRes additionalInfo: Int? = null,
    titleIcon: Painter? = null,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
) = with(LocalInputFormTokens.current) {
    require(title == null || title.isNotBlank()) { "Parameter `title` must not be blank!" }

    val isFocused by interactionSource.collectIsFocusedAsState()

    var state by remember {
        val value = when {
            isFocused -> InputFormState.Focused
            else -> InputFormState.Empty
        }
        mutableStateOf(value)
    }

    LaunchedEffect(errorMessageRes, isFocused) {
        state = when {
            errorMessageRes != null -> InputFormState.Error
            isFocused -> InputFormState.Focused
            input.isEmpty() -> InputFormState.Empty
            else -> InputFormState.Active
        }
    }

    CompositionLocalProvider(
        LocalComponentState provides state
    ) {
        BasicTextField(
            value = input,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            cursorBrush = cursorBrush,
            visualTransformation = visualTransformation,
            onValueChange = {
                if (maxCharacters == null || it.length <= maxCharacters) {
                    onInputChange(it)
                }
            },
            modifier = modifier,
            textStyle = inputTypography(size),
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                Column {
                    if (title != null) {
                        InputHeader(
                            title = title,
                            titleIcon = titleIcon,
                            size = size,
                        )
                    }

                    InputRow(
                        size = size,
                        startIcon = startIcon,
                        endIcon = endIcon,
                        onEndIconClick = onEndIconClick,
                        innerTextField = innerTextField,
                        type = type,
                        keyColor = keyColor,
                        hint = hint,
                        isHintVisible = input.isEmpty(),
                    )

                    InputFooter(
                        additionalInfo = errorMessageRes ?: additionalInfo,
                        isCharacterCounterVisible = isCharacterCounterVisible,
                        maxCharacters = maxCharacters,
                        input = input,
                    )
                }
            }
        )
    }
}

@Composable
private fun InputFormTokens.InputHeader(
    title: String,
    titleIcon: Painter? = null,
    size: InputFormSize,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = titleTypography(size)
        )

        if (titleIcon != null) {
            InputIcon(
                painter = titleIcon,
                size = titleIconSize(size),
            )
        }
    }
}

@Composable
private fun InputFormTokens.InputRow(
    isHintVisible: Boolean,
    hint: String? = null,
    size: InputFormSize,
    startIcon: Painter?,
    endIcon: Painter?,
    onEndIconClick: () -> Unit,
    type: InputFormType,
    keyColor: KeyColor,
    innerTextField: @Composable () -> Unit,
) {
    val shape = RoundedCornerShape(cornerRadius(size))

    val palette = keyColor.paletteColors()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = backgroundColor(type),
                shape = shape
            )
            .border(
                width = LocalCoreTokens.current.strokeWidthBySize(
                    size = Size.valueOf(size.name)
                ),
                color = strokeColor(type),
                shape = shape
            )
            .padding(horizontal = horizontalPadding)
            .height(height = height(size))
    ) {
        if (startIcon != null) {
            InputIcon(
                painter = startIcon,
                tint = startIconColor(palette = palette),
                size = formIconSize(size),
                modifier = Modifier
                    .padding(end = startIconPadding)
            )
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            innerTextField()

            if (isHintVisible && !hint.isNullOrBlank()) {
                InputHint(hint, size)
            }
        }

        if (endIcon != null) {
            InputIcon(
                painter = endIcon,
                tint = endIconColor(),
                size = formIconSize(size),
                onClick = onEndIconClick,
                modifier = Modifier
                    .padding(start = endIconPadding)
            )
        }
    }
}

@Composable
private fun InputFormTokens.InputFooter(
    @StringRes additionalInfo: Int? = null,
    isCharacterCounterVisible: Boolean = false,
    maxCharacters: Int? = null,
    input: String? = null,
) {
    if (additionalInfo == null && !isCharacterCounterVisible) return

    Row(
        modifier = Modifier
            .padding(top = 4.dp)
    ) {
        if (additionalInfo != null) {
            Text(
                text = stringResource(id = additionalInfo),
                style = AppTheme.typography.P6,
                color = additionalTextColor(),
                modifier = Modifier.weight(1f)
            )
        }

        if (maxCharacters != null && input != null && isCharacterCounterVisible) {
            Text(
                text = "${input.length}/$maxCharacters",
                style = AppTheme.typography.P6
            )
        }
    }
}

@Composable
private fun InputIcon(
    painter: Painter,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit = {},
) {
    Icon(
        painter = painter,
        tint = tint,
        modifier = Modifier
            .defaultMinSize(size, size)
            .clickable(onClick = onClick)
            .then(modifier)
    )
}

@Composable
private fun InputFormTokens.InputHint(
    hint: String,
    size: InputFormSize,
) {
    Text(
        text = hint,
        style = hintTypography(size),
    )
}