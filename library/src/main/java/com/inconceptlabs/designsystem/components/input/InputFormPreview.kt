package com.inconceptlabs.designsystem.components.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.inconceptlabs.designsystem.theme.AppTheme

@Preview(
    showBackground = true,
    heightDp = 90
)
@Composable
private fun InputFormPreview() {
    AppTheme {
        var input by remember { mutableStateOf("") }
        InputForm(
            input = input,
            title = "Fill your email here",
            hint = "Email",
            startIcon = painterResource(
                id = android.R.drawable.ic_menu_search
            ),
            endIcon = painterResource(
                id = android.R.drawable.ic_menu_close_clear_cancel
            ),
            onInputChange = { input = it },
            onEndIconClick = { input = "" }
        )
    }
}

@Preview(
    showBackground = true,
    heightDp = 90
)
@Composable
private fun PasswordInputFormPreview() {
    AppTheme {
        var input by remember { mutableStateOf("") }
        PasswordInputForm(
            input = input,
            title = "Fill your password here",
            hint = "Password",
            onInputChange = { input = it },
        )
    }
}