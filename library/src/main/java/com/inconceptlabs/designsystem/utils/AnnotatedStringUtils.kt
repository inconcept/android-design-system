package com.inconceptlabs.designsystem.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration

const val TAG_CLICKABLE = "Clickable"

data class AnnotatedPart(
    val text: String,
    val style: TextStyle = TextStyle(textDecoration = TextDecoration.Underline),
    val color: Color = Color.Unspecified,
    val url: String? = null,
    val tag: String = TAG_CLICKABLE
) {

    fun getIndexes(fullText: String): Pair<Int, Int> {
        val startIndex = fullText.indexOf(text)
        val endIndex = startIndex + text.length

        return startIndex to endIndex
    }
}

fun buildAnnotatedString(
    fullText: String,
    annotatedParts: List<AnnotatedPart>,
): AnnotatedString {
    val builder = AnnotatedString.Builder(fullText)

    annotatedParts.forEach { part ->
        val mergedStyle = part.style.copy(color = part.color)
        val (startIndex, endIndex) = part.getIndexes(fullText)

        builder.addStyle(
            style = mergedStyle.toSpanStyle(),
            start = startIndex,
            end = endIndex
        )

        builder.addStringAnnotation(
            tag = part.tag,
            annotation = part.url.orEmpty(),
            start = startIndex,
            end = endIndex
        )
    }

    return builder.toAnnotatedString()
}