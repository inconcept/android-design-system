plugins {
    id("com.android.library") version "8.9.0" apply false

    val kotlinVersion = "2.1.10"
    kotlin("android") version kotlinVersion apply false
    kotlin("plugin.compose") version kotlinVersion apply false
}