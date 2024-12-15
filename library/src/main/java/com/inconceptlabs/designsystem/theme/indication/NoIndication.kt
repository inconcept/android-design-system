package com.inconceptlabs.designsystem.theme.indication

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.ui.node.DelegatableNode

object NoIndication : IndicationNodeFactory {

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return NoIndicationNode()
    }

    override fun equals(other: Any?): Boolean = other === this

    override fun hashCode(): Int = -1
}