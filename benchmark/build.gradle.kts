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

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.70")

    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.benchmark:benchmark-junit4:1.0.0-alpha04")
}
