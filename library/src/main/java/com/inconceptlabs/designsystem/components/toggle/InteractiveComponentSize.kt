package com.inconceptlabs.designsystem.components.toggle

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.inconceptlabs.designsystem.components.buttons.Button
import kotlin.math.roundToInt

/**
 * Reserves at least 48.dp in size to disambiguate touch interactions if the element would measure
 * smaller.
 *
 * This uses the Material recommended minimum size of 48.dp x 48.dp, which may not the same as the
 * system enforced minimum size. The minimum clickable / touch target size (48.dp by default) is
 * controlled by the system via ViewConfiguration` and automatically expanded at the touch input
 * layer.
 *
 * This modifier is not needed for touch target expansion to happen. It only affects layout, to make
 * sure there is adequate space for touch target expansion.
 */
@Stable
fun Modifier.minimumInteractiveComponentSize(): Modifier = this then MinimumInteractiveModifier

internal object MinimumInteractiveModifier : ModifierNodeElement<MinimumInteractiveModifierNode>() {

    override fun create(): MinimumInteractiveModifierNode = MinimumInteractiveModifierNode()

    override fun update(node: MinimumInteractiveModifierNode) {}

    override fun InspectorInfo.inspectableProperties() {
        name = "minimumInteractiveComponentSize"
        properties["README"] = "Reserves at least 48.dp in size to disambiguate touch " +
                "interactions if the element would measure smaller"
    }

    override fun hashCode(): Int = System.identityHashCode(this)

    override fun equals(other: Any?) = (other === this)
}

internal class MinimumInteractiveModifierNode :
    Modifier.Node(), CompositionLocalConsumerModifierNode, LayoutModifierNode {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val size = currentValueOf(LocalMinimumInteractiveComponentSize).coerceAtLeast(0.dp)
        val placeable = measurable.measure(constraints)
        val enforcement = isAttached && (size.isSpecified && size > 0.dp)

        val sizePx = if (size.isSpecified) size.roundToPx() else 0
        // Be at least as big as the minimum dimension in both dimensions
        val width =
            if (enforcement) {
                maxOf(placeable.width, sizePx)
            } else {
                placeable.width
            }
        val height =
            if (enforcement) {
                maxOf(placeable.height, sizePx)
            } else {
                placeable.height
            }

        return layout(width, height) {
            val centerX = ((width - placeable.width) / 2f).roundToInt()
            val centerY = ((height - placeable.height) / 2f).roundToInt()
            placeable.place(centerX, centerY)
        }
    }
}

/**
 * CompositionLocal that configures the minimum touch target size for components
 * (such as [Button]) to ensure they are accessible. If a component has a visual
 * size that is lower than the minimum touch target size, extra space outside the
 * component will be included. If set to [Dp.Unspecified] there will be no extra
 * space, and so it is possible that if the component is placed near the edge of
 * a layout / near to another component without any padding, there will not be
 * enough space for an accessible touch target.
 */
val LocalMinimumInteractiveComponentSize = staticCompositionLocalOf { 48.dp }