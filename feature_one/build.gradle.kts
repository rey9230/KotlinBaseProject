plugins {
    dynamicFeature()
    kotlinAndroid()
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