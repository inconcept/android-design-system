package com.inconceptlabs.designsystem.components.core

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.utils.TAG_CLICKABLE

@Preview
@Composable
private fun ComponentPreview() {
    AppTheme {
        Text(
            text = "Hello World",
        )
    }
}

/**
 * High level element that displays text
 *
 * For ease of use, commonly used parameters from [TextStyle] are
 * also present here. The order of precedence is as follows:
 * - If a parameter is explicitly set here (i.e, it is _not_ `null`
 * or [TextUnit.Unspecified]),then this parameter will always be used.
 * - If a parameter is _not_ set, (`null` or [TextUnit.Unspecified]),
 * then the corresponding value from [style] will be used instead.
 */
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colorScheme.T8,
    style: TextStyle = AppTheme.typography.default,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val mergedStyle = style.copy(
        color = style.color.takeOrElse { color },
        textAlign = textAlign
    )

    BasicText(
        text = text,
        modifier = modifier,
        style = mergedStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines
    )
}

@Composable
fun ClickableText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    onClick: ((Int) -> Unit)? = null,
    color: Color = AppTheme.colorScheme.T8,
    style: TextStyle = AppTheme.typography.default,
    align: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val mergedStyle = style.copy(
        color = color,
        textAlign = align
    )

    val uriHandler = LocalUriHandler.current
    val defaultImpl = { offset: Int ->
        val stringRange = text.getStringAnnotations(TAG_CLICKABLE, offset, offset).firstOrNull()
        val uri = stringRange?.item
        openUri(uriHandler, uri)
    }

    ClickableText(
        text = text,
        onClick = onClick ?: defaultImpl,
        modifier = modifier,
        style = mergedStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
    )
}

private fun openUri(uriHandler: UriHandler, uri: String?) {
    uri ?: return
    uriHandler.openUri(uri)
}