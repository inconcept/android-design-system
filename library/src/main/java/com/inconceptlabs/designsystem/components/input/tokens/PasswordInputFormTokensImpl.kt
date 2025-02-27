package com.inconceptlabs.designsystem.components.input.tokens

import androidx.annotation.DrawableRes
import com.inconceptlabs.designsystem.R

object PasswordInputFormTokensImpl : PasswordInputFormTokens {

    @DrawableRes
    override fun startIconRes(): Int {
        return R.drawable.ic_lock_outline
    }

    @DrawableRes
    override fun endIconRes(isPasswordVisible: Boolean): Int {
        return if (isPasswordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed
    }
}