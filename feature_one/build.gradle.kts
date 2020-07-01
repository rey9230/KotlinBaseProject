plugins {
    dynamicFeature()
    kotlinAndroid()
    kotlinAndroidExt()
}

android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
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