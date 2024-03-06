package com.inconceptlabs.designsystem.components.input

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalContentColor
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.colors.PaletteColors
import com.inconceptlabs.designsystem.theme.colors.paletteColors
import com.inconceptlabs.designsystem.theme.tokens.InputFormTokens
import com.inconceptlabs.designsystem.theme.tokens.InputFormTokensImpl
import com.inconceptlabs.designsystem.utils.clickable
import com.inconceptlabs.designsystem.utils.getStrokeWidth

/**
 * Compose currently doesn't provide a way of customizing the cursor's height
 * So we need to create a custom cursor brush to achieve the desired effect
 */
private val cursorBrush = Brush.verticalGradient(
    0.00f to Color.Transparent,
    0.10f to Color.Transparent,
    0.11f to Color.Black,
    0.89f to Color.Black,
    0.90f to Color.Transparent,
    1.00f to Color.Transparent,
)

@Preview(
    showBackground = true,
    heightDp = 90
)
@Composable
private fun ComponentPreview() {
    AppTheme {
        InputForm(
            title = "Fill your email here",
            hint = "Email",
            startIcon = painterResource(id = R.drawable.ic_lock_outline),
            endIcon = painterResource(id = R.drawable.ic_lock_outline),
        )
    }
}

@Composable
fun InputForm(
    modifier: Modifier = Modifier,
    size: Size = Size.M,
    type: InputFormType = InputFormType.FILLED,
    keyColor: KeyColor = KeyColor.PRIMARY,
    maxCharacters: Int? = null,
    onInputChange: (String) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {},
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
    tokens: InputFormTokens = InputFormTokensImpl,
) {
    require(title == null || title.isNotBlank()) { "Parameter `title` must not be blank!" }
    require(size != Size.XXS) { "`Size.XXS` not supported for `InputForm`" }

    val isFocused by interactionSource.collectIsFocusedAsState()

    var input by remember { mutableStateOf("") }

    var state by remember {
        val value = when {
            isFocused -> InputFormState.FOCUSED
            else -> InputFormState.EMPTY
        }
        mutableStateOf(value)
    }

    LaunchedEffect(errorMessageRes, isFocused) {
        state = when {
            errorMessageRes != null -> InputFormState.ERROR
            isFocused -> InputFormState.FOCUSED
            input.isEmpty() -> InputFormState.EMPTY
            else -> InputFormState.ACTIVE
        }
    }

    BasicTextField(
        value = input,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        cursorBrush = cursorBrush,
        visualTransformation = visualTransformation,
        onValueChange = {
            if (maxCharacters != null && it.length > maxCharacters) return@BasicTextField

            input = it
            onInputChange(it)
        },
        modifier = modifier
            .onFocusChanged(onFocusChange),
        textStyle = inputTypography(size, state),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Column {
                if (title != null) {
                    InputHeader(
                        title = title,
                        titleIcon = titleIcon,
                        size = size,
                        tokens = tokens
                    )
                }

                InputRow(
                    size = size,
                    startIcon = startIcon,
                    endIcon = endIcon,
                    onEndIconClick = onEndIconClick,
                    innerTextField = innerTextField,
                    type = type,
                    state = state,
                    keyColor = keyColor,
                    hint = hint,
                    isHintVisible = input.isEmpty(),
                    tokens = tokens
                )

                InputFooter(
                    state = state,
                    additionalInfo = errorMessageRes ?: additionalInfo,
                    isCharacterCounterVisible = isCharacterCounterVisible,
                    maxCharacters = maxCharacters,
                    input = input
                )
            }
        }
    )
}

@Composable
private fun InputHeader(
    title: String,
    titleIcon: Painter? = null,
    size: Size,
    tokens: InputFormTokens,
) = with(tokens) {
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
                size = size.titleIconSize
            )
        }
    }
}

@Composable
private fun InputRow(
    isHintVisible: Boolean,
    hint: String? = null,
    size: Size,
    startIcon: Painter?,
    endIcon: Painter?,
    onEndIconClick: () -> Unit,
    type: InputFormType,
    state: InputFormState,
    keyColor: KeyColor,
    innerTextField: @Composable () -> Unit,
    tokens: InputFormTokens,
) = with(tokens) {
    val shape = RoundedCornerShape(size.cornerRadius)

    val palette = keyColor.paletteColors()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = backgroundColor(type, state),
                shape = shape
            )
            .border(
                width = getStrokeWidth(size = size),
                color = strokeColor(type, state),
                shape = shape
            )
            .padding(horizontal = horizontalPadding)
            .height(height = height(size))
    ) {
        if (startIcon != null) {
            InputIcon(
                painter = startIcon,
                tint = startIconColor(state = state, paletteColors = palette),
                size = size.formIconSize,
                modifier = Modifier
                    .padding(end = startIconPadding)
            )
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            innerTextField()

            if (isHintVisible && !hint.isNullOrBlank()) {
                InputHint(hint, size, state)
            }
        }

        if (endIcon != null) {
            InputIcon(
                painter = endIcon,
                tint = endIconColor(state = state),
                size = size.formIconSize,
                onClick = onEndIconClick,
                modifier = Modifier
                    .padding(start = endIconPadding)
            )
        }
    }
}

@Composable
private fun InputFooter(
    state: InputFormState,
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
                color = additionalTextColor(state),
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
            .clickable(action = onClick)
            .then(modifier)
    )
}

@Composable
private fun InputHint(
    hint: String,
    size: Size,
    state: InputFormState,
) {
    Text(
        text = hint,
        style = hintTypography(size, state),
    )
}

@Composable
private fun height(size: Size): Dp {
    return when (size) {
        Size.XXS,
        Size.XS -> 36.dp
        Size.S -> 40.dp
        Size.M -> 48.dp
        Size.L -> 56.dp
    }
}

@Composable
private fun titleTypography(size: Size): TextStyle {
    return when (size) {
        Size.XXS,
        Size.XS,
        Size.S -> AppTheme.typography.P5
        Size.M,
        Size.L -> AppTheme.typography.P4
    }
}

@Composable
private fun hintTypography(size: Size, state: InputFormState): TextStyle {
    return inputTypography(size, state).copy(
        color = hintColor(state)
    )
}

@Composable
private fun inputTypography(size: Size, state: InputFormState): TextStyle {
    val typography = when (size) {
        Size.XXS,
        Size.XS -> AppTheme.typography.P5
        Size.S -> AppTheme.typography.P4
        Size.M -> AppTheme.typography.P3
        Size.L -> AppTheme.typography.P3
    }

    return typography.copy(
        color = inputColor(state)
    )
}

@Composable
private fun strokeColor(
    type: InputFormType,
    state: InputFormState,
): Color {
    return when {
        state == InputFormState.SUCCESS -> AppTheme.colorScheme.success.main
        state == InputFormState.ERROR -> AppTheme.colorScheme.error.main
        type == InputFormType.FILLED -> Color.Unspecified
        state == InputFormState.DISABLED -> AppTheme.colorScheme.BG5
        state == InputFormState.EMPTY -> AppTheme.colorScheme.BG6
        state == InputFormState.FOCUSED -> AppTheme.colorScheme.BG6
        state == InputFormState.ACTIVE -> AppTheme.colorScheme.BG6
        else -> Color.Unspecified
    }
}

@Composable
private fun backgroundColor(
    type: InputFormType,
    state: InputFormState,
): Color {
    // Previous implementation suggested colors BG5, BG4 for focused and
    // other states, whereas in Figma they're BG3 and BG2 respectably
    return when {
        type == InputFormType.OUTLINE -> Color.Transparent
        state == InputFormState.FOCUSED -> AppTheme.colorScheme.BG5
        else -> AppTheme.colorScheme.BG4
    }
}

@Composable
private fun inputColor(state: InputFormState): Color {
    return when (state) {
        InputFormState.DISABLED -> AppTheme.colorScheme.T3
        InputFormState.EMPTY -> AppTheme.colorScheme.T6
        else -> AppTheme.colorScheme.T8
    }
}

@Composable
private fun hintColor(state: InputFormState): Color {
    return when (state) {
        InputFormState.DISABLED -> AppTheme.colorScheme.T3
        else -> AppTheme.colorScheme.T6
    }
}

@Composable
private fun startIconColor(
    state: InputFormState,
    paletteColors: PaletteColors,
): Color {
    return when (state) {
        InputFormState.DISABLED -> AppTheme.colorScheme.T3
        InputFormState.EMPTY -> paletteColors.alpha50
        InputFormState.FOCUSED -> paletteColors.main
        InputFormState.ACTIVE -> paletteColors.main
        InputFormState.SUCCESS -> AppTheme.colorScheme.success.main
        InputFormState.ERROR -> AppTheme.colorScheme.error.main
    }
}

@Composable
private fun endIconColor(
    state: InputFormState,
): Color {
    return when (state) {
        InputFormState.DISABLED -> AppTheme.colorScheme.T3
        InputFormState.EMPTY -> AppTheme.colorScheme.T6
        else -> AppTheme.colorScheme.T8
    }
}

@Composable
private fun additionalTextColor(
    state: InputFormState,
): Color {
    return when (state) {
        InputFormState.SUCCESS -> AppTheme.colorScheme.success.main
        InputFormState.ERROR -> AppTheme.colorScheme.error.main
        InputFormState.DISABLED -> AppTheme.colorScheme.T3
        else -> AppTheme.colorScheme.T8
    }
}