plugins {
    androidLibrary()
    benchmark()
    kotlinAndroid()
    kotlinAndroidExt()
}

android {

    defaultConfig {
        versionCode = 1
        versionName =  "1.0"
        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
    }

    buildTypes {
        getByName("debug") {
            // Since debuggable can't be modified by gradle for library modules,
            // it must be done in a manifest - see src/androidTest/AndroidManifest.xml
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "benchmark-proguard-rules.pro")
        }
    }
}

dependencies {
    // Add your dependencies here. Note that you cannot benchmark code
    // in an app module this way - you will need to move any code you
    // want to benchmark to a library module:
    // https://developer.android.com/studio/projects/android-library#Convert
    implementation(kotlin("stdlib-jdk7"))

    androidTestImplementation(Lib.Test.testRunner)
    androidTestImplementation(Lib.Test.testJunit)
    androidTestImplementation("androidx.benchmark:benchmark-junit4:1.1.0-alpha01")
}
