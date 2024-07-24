package com.inconceptlabs.designsystem.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

object NoIndication : Indication {

    @Composable
    override fun rememberUpdatedInstance(
        interactionSource: InteractionSource
    ): IndicationInstance {
        return NoIndicationInstance
    }

    object NoIndicationInstance : IndicationInstance {

        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }
}