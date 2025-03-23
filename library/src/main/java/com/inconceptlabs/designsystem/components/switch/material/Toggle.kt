package com.inconceptlabs.designsystem.components.switch.material

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.SnapSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateMeasurement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.inconceptlabs.designsystem.components.toggle.ToggleSize
import com.inconceptlabs.designsystem.components.switch.material.ToggleTokens.TrackOutlineWidth
import com.inconceptlabs.designsystem.components.switch.tokens.SwitchTokensInstance.requiredSize
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.theme.attributes.KeyColor
import kotlinx.coroutines.launch

@Composable
fun Toggle(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: ToggleSize = ToggleSize.S,
    keyColor: KeyColor = KeyColor.Primary,
    interactionSource: MutableInteractionSource = remember(::MutableInteractionSource),
) {
    val trackColor = AppTheme.colorScheme.BG3 //colors.trackColor(enabled, checked)
    val resolvedThumbColor = AppTheme.colorScheme.T8 //colors.thumbColor(enabled, checked)

    val toggleableModifier =
        if (onCheckedChange == null) {
            Modifier
        } else {
            Modifier
//                .minimumInteractiveComponentSize()
                .toggleable(
                    value = checked,
                    onValueChange = onCheckedChange,
                    enabled = enabled,
                    role = Role.Switch,
                    interactionSource = interactionSource,
                    indication = null
                )
        }

    Box(
        modifier = modifier
            .then(toggleableModifier)
            .wrapContentSize(Alignment.Center)
            .requiredSize(size = requiredSize(size))
            .border(
                width = TrackOutlineWidth,
                color = AppTheme.colorScheme.BG4, //colors.borderColor(enabled, checked),
                shape = CircleShape
            )
            .background(
                color = trackColor,
                shape = CircleShape
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .then(ThumbElement(interactionSource, checked))
                .indication(
                    interactionSource = interactionSource,
                    indication = LocalIndication.current,
                )
                .background(
                    color = resolvedThumbColor,
                    shape = CircleShape
                )
        )
    }
}

private data class ThumbElement(
    val interactionSource: InteractionSource,
    val checked: Boolean,
) : ModifierNodeElement<ThumbNode>() {

    override fun create() = ThumbNode(interactionSource, checked)

    override fun update(node: ThumbNode) {
        node.interactionSource = interactionSource
        if (node.checked != checked) {
            node.invalidateMeasurement()
        }
        node.checked = checked
        node.update()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "switchThumb"
        properties["interactionSource"] = interactionSource
        properties["checked"] = checked
    }
}

private class ThumbNode(
    var interactionSource: InteractionSource,
    var checked: Boolean,
) : Modifier.Node(), LayoutModifierNode {

    override val shouldAutoInvalidate: Boolean
        get() = false

    private var isPressed = false
    private var offsetAnim: Animatable<Float, AnimationVector1D>? = null
    private var sizeAnim: Animatable<Float, AnimationVector1D>? = null
    private var initialOffset: Float = Float.NaN
    private var initialSize: Float = Float.NaN

    override fun onAttach() {
        coroutineScope.launch {
            var pressCount = 0
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> pressCount++
                    is PressInteraction.Release -> pressCount--
                    is PressInteraction.Cancel -> pressCount--
                }
                val pressed = pressCount > 0
                if (isPressed != pressed) {
                    isPressed = pressed
                    invalidateMeasurement()
                }
            }
        }
    }

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val hasContent =
            measurable.maxIntrinsicHeight(constraints.maxWidth) != 0 &&
                measurable.maxIntrinsicWidth(constraints.maxHeight) != 0
        val size = when {
            isPressed -> ToggleTokens.PressedHandleWidth
            hasContent || checked -> ThumbDiameter
            else -> UncheckedThumbDiameter
        }.toPx()

        val actualSize = (sizeAnim?.value ?: size).toInt()
        val placeable = measurable.measure(Constraints.fixed(actualSize, actualSize))
        val thumbPaddingStart = (ToggleHeight - size.toDp()) / 2f
        val minBound = thumbPaddingStart.toPx()
        val thumbPathLength = (ToggleWidth - ThumbDiameter) - ThumbPadding
        val maxBound = thumbPathLength.toPx()
        val offset = when {
            isPressed && checked -> maxBound - TrackOutlineWidth.toPx()
            isPressed && !checked -> TrackOutlineWidth.toPx()
            checked -> maxBound
            else -> minBound
        }

        if (sizeAnim?.targetValue != size) {
            coroutineScope.launch {
                sizeAnim?.animateTo(size, if (isPressed) SnapSpec else AnimationSpec)
            }
        }

        if (offsetAnim?.targetValue != offset) {
            coroutineScope.launch {
                offsetAnim?.animateTo(offset, if (isPressed) SnapSpec else AnimationSpec)
            }
        }

        if (initialSize.isNaN() && initialOffset.isNaN()) {
            initialSize = size
            initialOffset = offset
        }

        return layout(actualSize, actualSize) {
            placeable.placeRelative(offsetAnim?.value?.toInt() ?: offset.toInt(), 0)
        }
    }

    fun update() {
        if (sizeAnim == null && !initialSize.isNaN()) {
            sizeAnim = Animatable(initialSize)
        }

        if (offsetAnim == null && !initialOffset.isNaN()) offsetAnim = Animatable(initialOffset)
    }
}

internal val ThumbDiameter = ToggleTokens.SelectedHandleWidth
internal val UncheckedThumbDiameter = ToggleTokens.UnselectedHandleWidth

private val ToggleWidth = 52.dp
private val ToggleHeight = 32.dp
private val ThumbPadding = (ToggleHeight - ThumbDiameter) / 2
private val SnapSpec = SnapSpec<Float>()
private val AnimationSpec = TweenSpec<Float>(durationMillis = 100)