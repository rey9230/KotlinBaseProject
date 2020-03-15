import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

fun PluginDependenciesSpec.androidApp(): PluginDependencySpec = id("com.android.application")

fun PluginDependenciesSpec.safeargs(): PluginDependencySpec = id("androidx.navigation.safeargs.kotlin")

fun PluginDependenciesSpec.junit5(): PluginDependencySpec = id("de.mannodermaus.android-junit5")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec = kotlin("android")

fun PluginDependenciesSpec.kotlinAndroidExt(): PluginDependencySpec = kotlin("android.extensions")

fun PluginDependenciesSpec.kotlinKapt(): PluginDependencySpec = kotlin("kapt")