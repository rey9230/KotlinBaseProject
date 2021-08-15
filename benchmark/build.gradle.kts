plugins {
    androidLibrary()
    benchmark()
    kotlinAndroid()
}

android {

    defaultConfig {
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

    androidTestImplementation(Lib.Test.testRunner)
    androidTestImplementation(Lib.Test.testRules)
    androidTestImplementation(Lib.Test.testJunit)
    androidTestImplementation(Lib.Test.benchmarkJUnit4)
    androidTestImplementation(Lib.Test.testFragment)
}
