package com.inconceptlabs.designsystem.components.input

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import com.inconceptlabs.designsystem.theme.attributes.Size
import com.inconceptlabs.designsystem.theme.tokens.PasswordInputFormTokens
import com.inconceptlabs.designsystem.theme.tokens.PasswordInputFormTokensImpl

@Preview(
    showBackground = true,
    heightDp = 90
)
@Composable
private fun ComponentPreview() {
    AppTheme {
        PasswordInputForm(
            title = "Fill your password here",
            hint = "Password",
        )
    }
}

@Composable
fun PasswordInputForm(
    modifier: Modifier = Modifier,
    size: Size = Size.M,
    type: InputFormType = InputFormType.FILLED,
    keyColor: KeyColor = KeyColor.PRIMARY,
    maxCharacters: Int? = null,
    onInputChange: (String) -> Unit = {},
    onEndIconClick: () -> Unit = {},
    hint: String? = null,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    singleLine: Boolean = true,
    isCharacterCounterVisible: Boolean = false,
    title: String? = null,
    @StringRes errorMessageRes: Int? = null,
    @StringRes additionalInfo: Int? = null,
    titleIcon: Painter? = null,
    tokens: PasswordInputFormTokens = PasswordInputFormTokensImpl,
) = with (tokens) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation = if (passwordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    InputForm(
        modifier = modifier,
        size = size,
        type = type,
        keyColor = keyColor,
        maxCharacters = maxCharacters,
        onInputChange = onInputChange,
        onEndIconClick = {
            passwordVisible = !passwordVisible

            onEndIconClick()
        },
        hint = hint,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        isCharacterCounterVisible = isCharacterCounterVisible,
        title = title,
        errorMessageRes = errorMessageRes,
        additionalInfo = additionalInfo,
        titleIcon = titleIcon,
        startIcon = painterResource(startIconRes),
        endIcon = painterResource(id = getEndIconRes(passwordVisible)),
    )
}