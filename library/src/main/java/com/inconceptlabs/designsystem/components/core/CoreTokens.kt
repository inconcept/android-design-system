package com.inconceptlabs.designsystem.components.core

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.theme.attributes.Size

val LocalCoreTokens = compositionLocalOf { CoreTokens.Default }

data class CoreTokens(
    val strokeWidthThin: Dp = 1.dp,
    val strokeWidthThick: Dp = 2.dp,
) {

    fun strokeWidthBySize(size: Size): Dp {
        return when (size) {
            Size.XXS,
            Size.XS -> strokeWidthThin
            Size.S,
            Size.M,
            Size.L -> strokeWidthThick
        }
    }

    companion object {

        internal val Default = CoreTokens()
    }
}