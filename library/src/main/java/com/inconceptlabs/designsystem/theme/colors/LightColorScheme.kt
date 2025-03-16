package com.inconceptlabs.designsystem.theme.colors

import androidx.compose.ui.graphics.Color

object LightColorScheme : ColorScheme {

    override val primary = PaletteColors(
        alpha10 = Green100,
        alpha20 = Green200,
        alpha30 = Green300,
        alpha50 = Green400,
        main = Green500,
        dark5 = Green600
    )

    override val secondary = PaletteColors(
        alpha10 = Orange100,
        alpha20 = Orange200,
        alpha30 = Orange300,
        alpha50 = Orange400,
        main = Orange500,
        dark5 = Orange600
    )

    override val tertiary = PaletteColors(
        alpha10 = Yellow100,
        alpha20 = Yellow200,
        alpha30 = Yellow300,
        alpha50 = Yellow400,
        main = Yellow500,
        dark5 = Yellow600
    )

    override val success = PaletteColors(
        alpha10 = Green150a,
        alpha20 = Green300a,
        alpha30 = Green370a,
        alpha50 = Green450a,
        main = Green500a,
        dark5 = Green650a
    )

    override val error = PaletteColors(
        alpha10 = Red150a,
        alpha20 = Red250a,
        alpha30 = Red350a,
        alpha50 = Red450a,
        main = Red500a,
        dark5 = Red700a
    )

    override val warning = PaletteColors(
        alpha10 = Yellow150a,
        alpha20 = Yellow300a,
        alpha30 = Yellow350a,
        alpha50 = Yellow450a,
        main = Yellow500a,
        dark5 = Yellow700a
    )

    override val info = PaletteColors(
        alpha10 = Gray110a,
        alpha20 = Gray130a,
        alpha30 = Gray150a,
        alpha50 = Gray200a,
        main = Gray220a,
        dark5 = Gray240a
    )

    override val premium = PaletteColors(
        alpha10 = Blue150a,
        alpha20 = Blue300a,
        alpha30 = Blue350a,
        alpha50 = Blue450a,
        main = Blue500a,
        dark5 = Blue700a
    )

    override val new = PaletteColors(
        alpha10 = Red100,
        alpha20 = Red200,
        alpha30 = Red300,
        alpha50 = Red400,
        main = Red500,
        dark5 = Red600
    )

    override val BG1 = Gray50
    override val BG2 = Gray100
    override val BG3 = Gray140
    override val BG4 = Gray160
    override val BG5 = Gray190
    override val BG6 = Gray250
    override val BG7 = Gray280
    override val BG8 = Navy200
    override val BG9 = Navy300
    override val BG10 = Navy700

    override val T1 = Color.White
    override val T2 = Gray180
    override val T3 = Gray210
    override val T4 = Gray230
    override val T5 = Gray260
    override val T6 = Gray270
    override val T7 = Gray480
    override val T8 = Navy100
    override val T9 = Indigo800
    override val T10 = Gray700
    override val T11 = Gray800
    override val T12 = Gray970
}