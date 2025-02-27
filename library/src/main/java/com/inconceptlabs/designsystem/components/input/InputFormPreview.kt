package com.inconceptlabs.designsystem.components.input

import androidx.compose.runtime.Composable
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
        InputForm(
            title = "Fill your email here",
            hint = "Email",
            startIcon = painterResource(
                id = android.R.drawable.ic_menu_search
            ),
            endIcon = painterResource(
                id = android.R.drawable.ic_menu_close_clear_cancel
            ),
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
        PasswordInputForm(
            title = "Fill your password here",
            hint = "Password",
        )
    }
}