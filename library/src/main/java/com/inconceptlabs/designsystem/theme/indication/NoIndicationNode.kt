package com.inconceptlabs.designsystem.theme.indication

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode

class NoIndicationNode : Modifier.Node(), DrawModifierNode {

    override fun ContentDrawScope.draw() {
        drawContent()
    }
}