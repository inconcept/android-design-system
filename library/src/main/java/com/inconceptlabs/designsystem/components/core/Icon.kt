package com.inconceptlabs.designsystem.components.core

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.inconceptlabs.designsystem.R
import com.inconceptlabs.designsystem.components.buttons.IconButton
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.LocalContentColor

@Preview
@Composable
private fun ComponentPreview() {
    AppTheme {
        Icon(
            painter = painterResource(id = R.drawable.ic_lock_outline),
        )
    }
}

/**
 * Composable function for displaying icons. It draws the specified [painter]
 * using the provided [tint] color. The `Icon` composable is optimized for
 * single-color icons, allowing easy tinting. If no tint is required, set [tint]
 * to [Color.Unspecified]. When dealing with more generic images, consider using
 * the generic [Image] instead. For clickable icons, refer to [IconButton].
 *
 * @param painter The graphic representation of the icon to be drawn.
 * @param modifier Optional modifier to adjust the layout or appearance of the icon.
 * @param tint Color used to tint the icon. Defaults to [LocalContentColor]. Set to
 * [Color.Unspecified] when should not be tinted.
 * @param contentDescription An optional description of the icon for accessibility purposes.
 */
@Composable
fun Icon(
    painter: Painter,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    contentDescription: String? = null,
) {
    val colorFilter = if (tint == Color.Unspecified) null else ColorFilter.tint(tint)

    Image(
        painter = painter,
        contentDescription = contentDescription,
        colorFilter = colorFilter,
        modifier = modifier
    )
}

@Suppress("unused")
@Composable
fun Icon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Suppress("unused")
@Composable
fun Icon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    val painter = remember(bitmap) { BitmapPainter(bitmap) }
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}