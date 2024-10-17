# Design System

A collection of reusable UI components built with **Jetpack Compose** to streamline the development process for Android applications.

## Get Started

> This installation guide uses *Gradle Kotlin DSL*. For the 
> legacy *Groovy* version, the overall setup is the same, except for 
> few syntax differences, which can be found [here](https://docs.gradle.org/current/userguide/migrating_from_groovy_to_kotlin_dsl.html)

1. Add the *Jitpack* repository to your project level `build.gradle` file, at the end of `repositories` block
```kotlin
repositories {
    // ...
    maven("https://jitpack.io")
}
```

or, if a newer *Gradle* setup is used, inside `settings.gradle` file
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // ...
        maven("https://jitpack.io")
    }
}
```
2. Add the dependency
```kotlin
dependencies {
    implementation("com.github.inconcept:android-design-system:$version")
}
```
That's it!

## Usage

There's a utility function `ProvideThemedContent` for both *Fragment* and `ComponentActivity`, which provides `AppTheme` context inside of it, where you can put your screen/view/content. This function must then be returned as the *Fragment's View*.

> *MyFragment.kt*
```kotlin
override fun onCreateView(  
    inflater: LayoutInflater,  
    container: ViewGroup?,  
    savedInstanceState: Bundle?  
): View {  
    return ProvideThemedContent {
        MyScreen()
    }
}
```

> *MyActivity.kt*
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {  
    super.onCreate(savedInstanceState)

    ProvideThemedContent {
        MyScreen()
    }
}
```

## Customization

Customization was one of the key properties we kept in mind while building this library. It allows you to define **your own** *Typography*, *Color Scheme* and *Components*, the key components of a design system and theming in general. You can also change the components’ current implementation using *Tokens*.

#### Typography
The default implementation uses *Barlow* font-family, but imagine we want to use *Poppins* for our app. For that, we'll need to implement the `Typography` interface and override all of its properties.
```kotlin
object Poppins : Typography {  
  
    private val fontFamily = FontFamily(  
        Font(resId = R.font.poppins_medium, weight = FontWeight.Medium),  
        Font(resId = R.font.poppins_semi_bold, weight = FontWeight.SemiBold),
        Font(resId = R.font.poppins_bold, weight = FontWeight.Bold)  
    )  

    override val default = TextStyle(  
        fontFamily = fontFamily,  
        platformStyle = platformStyle,  
        lineHeightStyle = lineHeightStyle,  
        fontWeight = FontWeight.Medium,  
    )
    
    override val H1 = default.copy(fontSize = 60.sp, lineHeight = 72.sp, fontWeight = FontWeight.Bold)  
    override val H2 = // ...
    override val H3 = // ...
    override val H4 = // ...
    override val H5 = // ...
    // Don't want to define a style? 
    // No worries, just make it to use the default one
    override val H6 = default

    // ...
}
```

Just like this, we've implemented our own *Typography*. To use it across the application, we need to apply it to the `AppTheme` composable.
```kotlin
AppTheme(
    typography = Poppins
) {
    // Everything in this scope now uses Poppins typography!
}
```

And to access it we need to use `AppTheme.typography`
```kotlin
Text(
    style = AppTheme.typography.P5
)
```

#### Color Scheme

The same principles apply to defining our custom *Color Scheme*, dark mode, for example. We first implement the `ColorScheme` interface, then apply it to `AppTheme`

#### Tokens
In any design system, certain properties are designed to be customizable, such as - `KeyColor` or `ButtonType`, while others, like the dash width of `EmptyItem`, remain static. Customizable properties are presented via function parameters, while static properties are defined via tokens in `ThemeTokens`

`ThemeTokens` is a wrapper class that contains *design-system* specific *tokens*. These tokens configure component properties, which otherwise would have been constants.

Each of the containing tokens has a `ProvidableCompositionLocal` with a "Local" prefix (for example `LocalButtonTokens`), is provided within `AppTheme,` and used by library components via `Local*Tokens.current`. We can change these tokens at theme-level or for a smaller scope,  like for a specific screen. Examples are provided below.

Example of configuring `CoreTokens` for the entire application
```Kotlin
@Composable
fun ApplicationContent() {
    AppTheme(
        appTokens = AppTokens(
            coreTokens = CoreTokens(
                strokeWidthThin = 1.5.dp,
                strokeWidthThick = 3.dp,
            )
        ),
        content = { /*...*/ }
    )
}
```

Example of configuring `EmptyItemTokens` for a smaller scope
```kotlin
@Composable
fun SomeScreen() {
    val customTokens = EmptyItemTokens(
        cornerRadius = 16.dp,
    )

    CompositionLocalProvider(
        LocalEmptyItemTokens provides customTokens,
    ) {
        //...
    }
}
```