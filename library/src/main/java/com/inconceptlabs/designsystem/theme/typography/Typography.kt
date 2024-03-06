package com.inconceptlabs.designsystem.theme.typography

import androidx.compose.ui.text.TextStyle

/**
 * Interface defining typography styles used within the application's design system.
 * Includes various text styles, including headlines, sub-headlines, etc.
 */
@Suppress("PropertyName")
interface Typography {

    val default: TextStyle

    val H1: TextStyle
    val H2: TextStyle
    val H3: TextStyle
    val H4: TextStyle
    val H5: TextStyle
    val H6: TextStyle

    val S1: TextStyle
    val S2: TextStyle
    val S3: TextStyle
    val S4: TextStyle

    val P1: TextStyle
    val P2: TextStyle
    val P3: TextStyle
    val P4: TextStyle
    val P5: TextStyle
    val P6: TextStyle

    val B1: TextStyle
    val B2: TextStyle
    val B3: TextStyle
    val B4: TextStyle
    val B5: TextStyle
    val B6: TextStyle

    val L1: TextStyle
    val L2: TextStyle
    val L3: TextStyle

    companion object {

        val Default = object : Typography {

            override val default = TextStyle.Default

            override val H1 = TextStyle.Default
            override val H2 = TextStyle.Default
            override val H3 = TextStyle.Default
            override val H4 = TextStyle.Default
            override val H5 = TextStyle.Default
            override val H6 = TextStyle.Default

            override val S1 = TextStyle.Default
            override val S2 = TextStyle.Default
            override val S3 = TextStyle.Default
            override val S4 = TextStyle.Default

            override val P1 = TextStyle.Default
            override val P2 = TextStyle.Default
            override val P3 = TextStyle.Default
            override val P4 = TextStyle.Default
            override val P5 = TextStyle.Default
            override val P6 = TextStyle.Default

            override val B1 = TextStyle.Default
            override val B2 = TextStyle.Default
            override val B3 = TextStyle.Default
            override val B4 = TextStyle.Default
            override val B5 = TextStyle.Default
            override val B6 = TextStyle.Default

            override val L1 = TextStyle.Default
            override val L2 = TextStyle.Default
            override val L3 = TextStyle.Default
        }
    }
}