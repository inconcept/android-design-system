package com.inconceptlabs.designsystem.components.avatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.inconceptlabs.designsystem.components.core.Icon
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.theme.attributes.Size

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    fullName: String? = null,
    fallbackIcon: Painter? = null,
    contentDescription: String? = null,
    size: Size = Size.M,
) = with(LocalAvatarTokens.current) {
    val resolvedContentDescription = resolvedContentDescription(
        providedValue = contentDescription,
        fullName = fullName,
    )

    when {
        painter != null -> {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = resolvedContentDescription,
                modifier = modifier
                    .requiredSize(size = size(size))
                    .clip(shape = CircleShape),
            )
        }
        fullName != null -> {
            val backgroundColor = backgroundColor()
            Text(
                text = fallbackText(value = fullName),
                style = fallbackTextStyle(size = size),
                color = contentColor(),
                modifier = modifier
                    .drawBehind { drawCircle(color = backgroundColor) }
                    .requiredSize(size = size(size))
                    .wrapContentSize(),
            )
        }
        else -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .requiredSize(size = size(size))
                    .background(
                        color = backgroundColor(),
                        shape = CircleShape,
                    )
            ) {
                Icon(
                    painter = fallbackIcon ?: fallbackIcon(),
                    tint = contentColor(),
                    contentDescription = resolvedContentDescription,
                    modifier = modifier
                        .size(size = fallbackIconSize(size))
                )
            }
        }
    }
}