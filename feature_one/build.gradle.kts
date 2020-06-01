plugins {
    dynamicFeature()
    kotlinAndroid()
    kotlinAndroidExt()
}

android {
    compileSdkVersion(Apps.compileSdk)

    defaultConfig {
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            noStdlib = true
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = listOf("-Xallow-result-return-type")
        }
    }
}

dependencies {
    implementation(project(":app"))
    implementation(Lib.coreKtx)
    implementation(kotlin("stdlib-jdk7"))
}

tasks.register("hello") {
    doLast {
        println("Hello world!")
    }
}