import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    androidApp()
    kotlinAndroid()
    kotlinKapt()
    safeargs()
    hilt()
}

val stringFromGradleProperties = project.findProperty("key") as String // get string from gradle.properties
val stringFromLocalProperties: String = gradleLocalProperties(rootDir).getProperty("key") // get string from local.properties

android {

    defaultConfig {
        applicationId = Apps.applicationId
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // base runner that - handles loading test package and app to device - running tests - reporting tests results (for androidTestImplementation )
        setProperty("archivesBaseName", "$applicationId-v$versionName.$versionCode") // name for generated apk file
        javaCompileOptions {
            annotationProcessorOptions {
                // arguments = arguments + mapOf(
                    //             "room.schemaLocation" to "$projectDir/schemas",
                    // "room.incremental" to "true"
                    //             "room.expandProjection" to "true"
                // )
            }
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin") // Changes the directory for Java sources. The default directory is 'src/main/java'.
        getByName("test").java.srcDirs("src/test/kotlin")
    }
    signingConfigs {
        getByName("debug") {
            // automatic signs with debug key
        }
        create("release") {
            keyAlias = "key"
            keyPassword = "111111"
            storePassword = "111111"
            storeFile = file("C:\\Users\\i30mb1\\Google Диск\\disk_G\\wm7167\\key111111.jks")
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false // R8 turn off
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name", "(debug)") // change app name for debug version
            buildConfigField("String", "key", stringFromLocalProperties) // write custom field in BuildConfig file
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = true // ProGuard turn on
            isDebuggable = true
            isShrinkResources = false // delete unused resources
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            resValue("string", "app_name", "App name") // Adds a new generated resource
            signingConfig = signingConfigs.getByName("release")
        }
    }
//    productFlavors {
//            create("full") {
//            }
//            create("trial") {
//            }
//    }

    buildFeatures {
        dataBinding = true
    }

    lintOptions {
        isAbortOnError = true // if set to true (default), stops the build if errors are found.
        isIgnoreWarnings = false // if true, only report errors.
//        isQuiet = true // If set to true, turns off analysis progress reporting by lint.
    }

    packagingOptions {
        exclude("META-INF/LICENSE") // ???
    }

    testOptions {
        animationsDisabled = true
        // Encapsulates options for local unit tests.
        unitTests.apply {
            // By default, local unit tests throw an exception any time the code you are testing tries to access
            // Android platform APIs (unless you mock Android dependencies yourself or with a testing
            // framework like Mockito). However, you can enable the following property so that the test
            // returns either null or zero when accessing platform APIs, rather than throwing an exception.
            isReturnDefaultValues = true
            // for robolectic
            isIncludeAndroidResources = true
        }
        // setExecution("ANDROID_TEST_ORCHESTRATOR")
    }
    dynamicFeatures = mutableSetOf(":feature_one")

    kapt {
        javacOptions {
            option("-Adagger.fastInit=enabled")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Lib.appcompat)
    implementation(Lib.coreKtx)
    implementation(Lib.constraintLayout)
    implementation(Lib.playCore)
    implementation(Lib.playCoreKtx)
    implementation(Lib.material)
    implementation(Lib.coroutines)
    implementation(Lib.coroutinesAndroid)
    implementation(Lib.coroutinesLifecycle)
    implementation(Lib.coroutinesLivedata)
    implementation(Lib.coroutinesViewmodel)
    implementation(Lib.coroutinesPlayServices)
    implementation(Lib.lifecycleAnnotation)
    implementation(Lib.hilt)
    // implementation(Lib.hiltViewModel)
    kapt(Lib.hiltAnnotation)
    kapt(Lib.hiltAnnotationX)
    implementation(Lib.coil)
    implementation(Lib.navigationFragmentKtx)
    implementation(Lib.navigationUiKtx)
    implementation(Lib.navigationRuntimeKtx)
    implementation(Lib.navigationDynamicFeatures)
    api(Lib.fragmentKtx)
    implementation(Lib.activityKtx)
    implementation(Lib.retrofit)
    implementation(Lib.retrofitMoshiConverter)
    implementation(Lib.retrofitInterceptor)
    implementation(Lib.moshi)
    implementation(Lib.moshiKotlin)
    implementation(Lib.moshiAdapter)
    kapt(Lib.moshiCodegen)
    implementation(Lib.room)
    implementation(Lib.roomKtx)
    kapt(Lib.roomAnnotation)
    implementation(Lib.preference)
    implementation(Lib.preferenceKtx)
    implementation(Lib.springAnimation)
    implementation(Lib.springAnimationKtx)
    implementation(Lib.recyclerView)

    // LintRules
    lintChecks(project(":rules"))

    addTestDependencies()
    addAndroidTestDependencies()
}
