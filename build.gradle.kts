import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.plugins.AppPlugin
import com.android.build.gradle.internal.plugins.DynamicFeaturePlugin
import com.android.build.gradle.internal.plugins.LibraryPlugin

plugins {
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlintPlug
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
    id("com.github.ben-manes.versions") version "0.28.0"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    ktlint {
        debug.set(false)
        version.set(Versions.ktlint)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
    plugins.matching { it is AppPlugin || it is DynamicFeaturePlugin || it is LibraryPlugin }.whenPluginAdded {
        configure<BaseExtension> {
            compileSdkVersion(Apps.compileSdk)

            defaultConfig {
                minSdkVersion(Apps.minSdk)
                targetSdkVersion(Apps.targetSdk)
            }

        }
    }

    // plugins..whenPluginAdded {
    //     configure<com.android.build.gradle.BaseExtension> {
    //         setCompileSdkVersion(Apps.compileSdk)
    //     }
    // }
}

buildscript {
    repositories {
        maven("https://maven.google.com")
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradlePlugin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}")
        classpath("androidx.benchmark:benchmark-gradle-plugin:${Versions.benchmark}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
