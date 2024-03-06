package com.inconceptlabs.designsystem.theme.tokens

import androidx.compose.ui.unit.Dp
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.theme.attributes.Size

object PasswordInputFormTokensImpl : PasswordInputFormTokens {

    private val impl = InputFormTokensImpl

    override val horizontalPadding = impl.horizontalPadding
    override val startIconPadding = impl.startIconPadding
    override val endIconPadding = impl.endIconPadding

    override val Size.cornerRadius: Dp
        get() = with(impl) { cornerRadius }

    override val Size.titleIconSize
        get() = with(impl) { titleIconSize }

    override val Size.formIconSize
        get() = with(impl) { formIconSize }

    override val startIconRes: Int
        get() = R.drawable.ic_lock_outline

    override fun getEndIconRes(isPasswordVisible: Boolean): Int =
        if (isPasswordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed
}