buildscript {
    repositories {
        maven("https://maven.google.com")
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.3")
        classpath(kotlin("gradle-plugin", version = "1.3.70"))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.2.2")
        classpath("androidx.benchmark:benchmark-gradle-plugin:1.0.0")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.5.2.0")
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
