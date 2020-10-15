plugins {
    dynamicFeature()
    kotlinAndroid()
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
}

tasks.register("hello") {
    doLast {
        println("Hello world!")
    }
}