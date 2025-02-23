package com.inconceptlabs.designsystem.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import com.inconceptlabs.designsystem.components.buttons.ButtonType
import com.inconceptlabs.designsystem.components.buttons.token.ButtonTokens
import com.inconceptlabs.designsystem.components.buttons.token.LocalButtonTokens
import com.inconceptlabs.designsystem.components.core.CoreTokens
import com.inconceptlabs.designsystem.components.emptyitem.EmptyItem
import com.inconceptlabs.designsystem.components.emptyitem.EmptyItemTokens
import com.inconceptlabs.designsystem.components.input.InputFormTokens
import com.inconceptlabs.designsystem.components.input.PasswordInputFormTokens
import com.inconceptlabs.designsystem.theme.attributes.KeyColor

/**
 * In any design system, certain properties are designed to be
 * customizable, such as - [KeyColor] or [ButtonType], while others,
 * like the dash width of [EmptyItem], remain static. Customizable
 * properties are presented via function parameters, while static
 * properties are defined via tokens in [ThemeTokens]
 *
 * [ThemeTokens] is a wrapper class that contains *design-system*
 * specific *tokens*. These tokens configure component properties,
 * which otherwise would have been constants.
 *
 * Each of the containing tokens has a [ProvidableCompositionLocal]
 * with a "Local" prefix (for example [LocalButtonTokens]), is
 * provided within `AppTheme,` and used by library components via
 * `Local*Tokens.current`. We can change these tokens at theme-level
 * or for a smaller scope,  like for a specific screen.
 * Examples are provided below.
 *
 * Example of configuring [CoreTokens] for an entire application
 * ```kotlin
 * @Composable
 * fun ApplicationContent() {
 *     AppTheme(
 *         appTokens = AppTokens(
 *             coreTokens = CoreTokens(
 *                 strokeWidthThin = 1.5.dp,
 *                 strokeWidthThick = 3.dp,
 *             )
 *         ),
 *         content = { ... }
 *     )
 * }
 * ```
 *
 * Example of configuring [EmptyItemTokens] for smaller scope
 * ```kotlin
 * @Composable
 * fun SomeScreen() {
 *     val customTokens = EmptyItemTokens(
 *         cornerRadius = 16.dp,
 *     )
 *
 *     CompositionLocalProvider(
 *         LocalEmptyItemTokens provides customTokens,
 *     ) {
 *         ...
 *     }
 * }
 * ```
 */
data class ThemeTokens(
    val core: CoreTokens = CoreTokens.Default,
    val button: ButtonTokens = ButtonTokens.Default,
    val iconButton: ButtonTokens = ButtonTokens.Icon,
    val inputForm: InputFormTokens = InputFormTokens.Default,
    val passwordInputForm: PasswordInputFormTokens = PasswordInputFormTokens.Default,
    val emptyItem: EmptyItemTokens = EmptyItemTokens.Default,
) {

    companion object {

        internal val Default = ThemeTokens()
    }
}