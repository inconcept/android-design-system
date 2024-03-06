package com.inconceptlabs.designsystem.theme.tokens

import androidx.annotation.DrawableRes

interface PasswordInputFormTokens : InputFormTokens {

    @get:DrawableRes
    val startIconRes: Int

    @DrawableRes
    fun getEndIconRes(isPasswordVisible: Boolean): Int
}