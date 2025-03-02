package com.inconceptlabs.designsystem.theme.typography

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.inconceptlabs.designsystem.R

internal object Barlow : Typography {

    private val fontFamily = FontFamily(
        Font(resId = R.font.barlow_medium, weight = FontWeight.Medium),
        Font(resId = R.font.barlow_semi_bold, weight = FontWeight.SemiBold),
        Font(resId = R.font.barlow_bold, weight = FontWeight.Bold)
    )

    private val platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )

    private val lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )

    override val default = TextStyle(
        fontFamily = fontFamily,
        platformStyle = platformStyle,
        lineHeightStyle = lineHeightStyle,
        fontWeight = FontWeight.Medium,
    )

    /**
     * The [LineHeight][TextStyle.lineHeight] is unified across all series
     * as per the designer's decision. This ensures consistent pairing of [TextStyle]
     * with specific [FontSize][TextStyle.fontSize] and [LineHeight][TextStyle.lineHeight].
     *
     * This intentional alignment might differ from *Figma*'s typography section.
     * Adjustments were made to line height during *Jetpack Compose* migration.
     */

    override val H1 = default.copy(fontSize = 60.sp, lineHeight = 72.sp, fontWeight = FontWeight.Bold)
    override val H2 = default.copy(fontSize = 48.sp, lineHeight = 64.sp, fontWeight = FontWeight.Bold)
    override val H3 = default.copy(fontSize = 36.sp, lineHeight = 48.sp, fontWeight = FontWeight.Bold)
    override val H4 = default.copy(fontSize = 30.sp, lineHeight = 40.sp, fontWeight = FontWeight.Bold)
    override val H5 = default.copy(fontSize = 24.sp, lineHeight = 32.sp, fontWeight = FontWeight.SemiBold)
    override val H6 = default.copy(fontSize = 20.sp, lineHeight = 30.sp, fontWeight = FontWeight.Bold)

    override val S1 = default.copy(fontSize = 20.sp, lineHeight = 30.sp, fontWeight = FontWeight.SemiBold)
    override val S2 = default.copy(fontSize = 18.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold)
    override val S3 = default.copy(fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.SemiBold)
    override val S4 = default.copy(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold)

    override val P1 = default.copy(fontSize = 24.sp, lineHeight = 32.sp, fontWeight = FontWeight.Medium)
    override val P2 = default.copy(fontSize = 20.sp, lineHeight = 30.sp, fontWeight = FontWeight.Medium)
    override val P3 = default.copy(fontSize = 18.sp, lineHeight = 28.sp, fontWeight = FontWeight.Medium)
    override val P4 = default.copy(fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Medium)
    override val P5 = default.copy(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium)
    override val P6 = default.copy(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.SemiBold)

    override val B1 = default.copy(fontSize = 20.sp, lineHeight = 30.sp, fontWeight = FontWeight.Bold)
    override val B2 = default.copy(fontSize = 18.sp, lineHeight = 28.sp, fontWeight = FontWeight.Bold)
    override val B3 = B2.copy(fontWeight = FontWeight.SemiBold)
    override val B4 = default.copy(fontSize = 16.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold)
    override val B5 = default.copy(fontSize = 14.sp, lineHeight = 18.sp, fontWeight = FontWeight.SemiBold)
    override val B6 = default.copy(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.SemiBold)

    override val L1 = default.copy(fontSize = 20.sp, lineHeight = 30.sp, fontWeight = FontWeight.Bold)
    override val L2 = default.copy(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium)
    override val L3 = default.copy(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.Medium)
}