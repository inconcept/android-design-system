package com.inconceptlabs.designsystem.components.input.tokens

import androidx.annotation.DrawableRes
import androidx.compose.runtime.compositionLocalOf

val LocalPasswordInputFormTokens = compositionLocalOf { PasswordInputFormTokens.Default }

interface PasswordInputFormTokens {

    @DrawableRes
    fun startIconRes(): Int

    @DrawableRes
    fun endIconRes(isPasswordVisible: Boolean): Int

    companion object {

        val Default: PasswordInputFormTokens = PasswordInputFormTokensImpl
    }
}