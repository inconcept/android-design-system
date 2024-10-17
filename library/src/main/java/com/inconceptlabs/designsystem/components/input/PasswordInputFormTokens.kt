package com.inconceptlabs.designsystem.components.input

import androidx.annotation.DrawableRes
import androidx.compose.runtime.compositionLocalOf
import com.inconceptlabs.designsystem.R

val LocalPasswordInputFormTokens = compositionLocalOf { PasswordInputFormTokens.Default }

data class PasswordInputFormTokens(
    @DrawableRes val startIconRes: Int = R.drawable.ic_lock_outline,
    val endIconRes: (isPasswordVisible: Boolean) -> Int = ::endIconRes,
) {

    companion object {

        internal val Default = PasswordInputFormTokens()

        @DrawableRes
        private fun endIconRes(isPasswordVisible: Boolean): Int {
            return if (isPasswordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed
        }
    }
}