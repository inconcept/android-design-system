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
import com.inconceptlabs.designsystem.components.input.tokens.LocalPasswordInputFormTokens
import com.inconceptlabs.designsystem.theme.attributes.KeyColor

@Composable
fun PasswordInputForm(
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
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    singleLine: Boolean = true,
    isCharacterCounterVisible: Boolean = false,
    title: String? = null,
    @StringRes errorMessageRes: Int? = null,
    @StringRes additionalInfo: Int? = null,
    titleIcon: Painter? = null,
) = with (LocalPasswordInputFormTokens.current) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation = if (passwordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    InputForm(
        input = input,
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
        startIcon = painterResource(startIconRes()),
        endIcon = painterResource(id = endIconRes(passwordVisible)),
    )
}