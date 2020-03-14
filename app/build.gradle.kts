import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("de.mannodermaus.android-junit5")
}

val stringFromGradleProperties: String by project // get string from gradle.properties
val stringFromLocalProperties: String = gradleLocalProperties(rootDir).getProperty("key") // get string from local.properties

android {
    compileSdkVersion(Apps.compileSdk)
    defaultConfig {
        applicationId = Apps.applicationId
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
//        versionCode = ext.get("gitCommitCount") as? Int
        versionCode = Apps.versionCode
        versionName = Apps.versionName
//        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner" // specify a test runner in the same module-level
        setProperty("archivesBaseName", "$applicationId-v$versionName.$versionCode") // name for generated apk file
        // javaCompileOptions {
        //     annotationProcessorOptions {
        //         arguments = mapOf(
        //             "room.schemaLocation" to "$projectDir/schemas",
        //             "room.incremental" to "true",
        //             "room.expandProjection" to "true"
        //         )
        //     }
        // }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin") // Changes the directory for Java sources. The default directory is 'src/main/java'.
        getByName("test").java.srcDirs("src/test/kotlin")
    }
    signingConfigs {
        getByName("debug") {
        }
        create("release") {
            keyAlias = "key"
            keyPassword = "111111"
            storePassword = "111111"
            storeFile = file("/Users/i30mb1/Android/Key/keystore")
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false // ProGuard turn off
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name", "(debug)") // change app name for debug version

            buildConfigField("String", "key", stringFromLocalProperties) // write custom field in BuildConfig file
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = true // ProGuard turn on
            isDebuggable = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            resValue("string", "app_name", "App name") // app name
            signingConfig = signingConfigs.getByName("release")
        }
    }
//    productFlavors {
//            create("full") {
//            }
//            create("trial") {
//            }
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            noStdlib = true
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = listOf("-Xallow-result-return-type")
        }
    }
    dataBinding {
        isEnabled = true
    }
    lintOptions {
        isAbortOnError = true // if set to true (default), stops the build if errors are found.
        isIgnoreWarnings = false // if true, only report errors.
//        isQuiet = true // If set to true, turns off analysis progress reporting by lint.
    }

    androidExtensions {
        isExperimental = true // In order to have parcelize annotation
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
            isIncludeAndroidResources = true
        }
        setExecution("ANDROID_TEST_ORCHESTRATOR")
    }

    // for Roboelectric
//    testOptions.unitTests.isIncludeAndroidResources(true)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // Starting with Kotlin 1.1.2, the dependencies with group org.jetbrains.kotlin are by default resolved with the version taken from the applied plugin
    implementation(kotlin("stdlib-jdk7")) // in old projects we should write in this way - implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.60")
    implementation(Lib.appcompat)
    implementation(Lib.coreKtx)

    implementation(Lib.constraintLayout)
    implementation(Lib.material)

    implementation(Lib.coroutines)
    implementation(Lib.coroutinesAndroid)
    implementation(Lib.coroutinesLifecycle)
    implementation(Lib.coroutinesLivedata)
    implementation(Lib.coroutinesViewmodel)
    implementation(Lib.coroutinesPlayServices)

    implementation (Lib.lifecycleAnnotation)

    implementation(Lib.dagger)
    kapt(Lib.daggerAnnotation)
    compileOnly(Lib.daggerAssisted)
    kapt(Lib.daggerAssistedAnnotation)

    implementation(Lib.coil)

    implementation(Lib.navigationFragmentKtx)
    implementation(Lib.navigationUiKtx)

    implementation(Lib.navigationRuntimeKtx)
    implementation(Lib.fragmentKtx)
    implementation(Lib.ActivityKtx)

    implementation(Lib.retrofit)
    implementation(Lib.retrofitMoshiConverter)
    implementation(Lib.retrofitInterceptor)

    implementation(Lib.moshi)
    implementation(Lib.moshiKotlin)
    implementation(Lib.moshiAdapter)
    kapt(Lib.moshiCodegen)

    // --- Room ---
    implementation(Lib.room)
    implementation(Lib.roomKtx)
    kapt(Lib.roomAnnotation)

    implementation(Lib.sharedPreference)

    // LintRules
    lintChecks(project(":rules"))

    // Tests
    // Required -- JUnit 4 framework
    testImplementation("junit:junit:4.12")
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    // Core library
    testImplementation("androidx.test:core:1.2.0")
    testImplementation("androidx.test:core-ktx:1.2.0")
    // AndroidJUnitRunner and JUnit Rules
    testImplementation("androidx.test:runner:1.2.0")
    testImplementation("androidx.test:rules:1.2.0")
    // Assertions
    testImplementation("androidx.test.ext:junit:1.1.1")
    testImplementation("androidx.test.ext:junit-ktx:1.1.1")
    testImplementation("androidx.test.ext:truth:1.2.0")
    testImplementation("com.google.truth:truth:0.42")

    testImplementation("androidx.arch.core:core-testing:2.1.0")

    testImplementation("com.squareup.okhttp3:mockwebserver:4.4.0")
    testImplementation("org.mockito:mockito-core:3.2.4")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0") // A small library that provides helper functions to work with Mockito in Kotlin.

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2")

    // UI Test
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.2.0")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test:rules:1.2.0")
}
