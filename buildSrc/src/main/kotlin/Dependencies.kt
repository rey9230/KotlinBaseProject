import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

// when we have all version's here how can we know there is new version available for our lib?
object Apps {
    const val compileSdk    = 29
    const val buildToolsSdk = "29.0.2"
    const val applicationId = "n7.myperfectemptyproject"
    const val minSdk        = 23
    const val targetSdk     = 29
    const val versionCode   = 1
    const val versionName   = "1.0"
}

object Versions {
    const val lifecycle    = "2.2.0"
    const val gradle       = "3.5.3"
    const val gradlePlugin = "4.1.1"
    const val lint         = "27.1.0" // gradlePlugin + 23
    const val kotlin       = "1.4.0"
    const val moshi        = "1.9.2"
    const val room         = "2.2.4"
    const val navigation   = "2.3.0"
    const val dagger       = "2.25.2"
    const val retrofit     = "2.7.1"
    const val daggerAssist = "0.5.2"
    const val coroutines   = "1.3.6"
    const val safeArgs     = "2.3.0-beta01"
    const val ktlint       = "0.36.0"
    const val ktlintPlug   = "9.2.1"
    const val detekt       = "1.10.0-RC1"
    const val benchmark    = "1.0.0"
    const val hilt         = "2.28-alpha"
}

object Lib {
    const val appcompat           = "androidx.appcompat:appcompat:1.1.0" // Degrade gracefully on older versions of Android.
    const val coreKtx             = "androidx.core:core-ktx:1.2.0" // Write more concise, idiomatic Kotlin code.
    const val constraintLayout    = "androidx.constraintlayout:constraintlayout:2.0.0"
    const val material            = "com.google.android.material:material:1.2.0" // Build beautiful products, faster.
    const val coil                = "io.coil-kt:coil:0.13.0"
    const val springAnimation     = "androidx.dynamicanimation:dynamicanimation:1.0.0"
    const val springAnimationKtx  = "androidx.dynamicanimation:dynamicanimation-ktx:1.0.0-alpha03"
    const val recyclerView        =  "androidx.recyclerview:recyclerview:1.2.0-alpha03"
    const val playCore            = "com.google.android.play:core:1.7.3"
    const val playCoreKtx         = "com.google.android.play:core-ktx:1.7.0"

    const val lifecycleService    = "androidx.lifecycle:lifecycle-service:${Versions.lifecycle}" // helpers for implementing LifecycleOwner in a Service
    const val lifecycleProcess    = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}" // ProcessLifecycleOwner provides a lifecycle for the whole application process


    // --- Preference ---
    const val preference       = "androidx.preference:preference:1.1.0"
    const val preferenceKtx    = "androidx.preference:preference-ktx:1.1.0"

    // --- Coroutines ---
    const val coroutines          = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid   = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" // for testing coroutines
    const val coroutinesLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}" // lifecycleScope + launchWhenResumed and ets.
    const val coroutinesLivedata  = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" // liveData (LiveData + coroutines) + lambda for livedata
    const val coroutinesViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" // viewModelScope + savedStateHandle
    const val lifecycleAnnotation = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}" // that's only needed if you have lifecycle-related annotations in your code, specifically @OnLifecycleEvent
    const val fragmentKtx         = "androidx.fragment:fragment-ktx:1.3.0-alpha04" // easy fragment transaction + by viewModels()
    const val activityKtx         = "androidx.activity:activity-ktx:1.1.0" // on BackPress support for Fragment

    // --- Firebase Coroutines ---
    const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.2"

    // --- Hilt ---
    const val hilt            = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAnnotation  = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAnnotationX = "androidx.hilt:hilt-compiler:1.0.0-alpha02"
    const val hiltViewModel   = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02"

    // --- Dagger ---
    const val dagger                   = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAnnotation         = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAssisted           = "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.daggerAssist}"
    const val daggerAssistedAnnotation = "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.daggerAssist}"

    //  --- Navigation ---
    const val navigationFragmentKtx     = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" // Fragment.findNavController + Fragment.navArgs
    const val navigationUiKtx           = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" // setupActionBarWithNavController + setupWithNavController
    const val navigationRuntimeKtx      = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}" // Activity.findNavController + Activity.navArgs + View.findNavController
    const val navigationDynamicFeatures = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"

    // --- Retrofit ---
    const val retrofit               = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofitInterceptor    = "com.squareup.okhttp3:logging-interceptor:4.3.1"

    // --- Moshi ---
    const val moshi        = "com.squareup.moshi:moshi:${Versions.moshi}" // It makes it easy to parse JSON into Kotlin objects
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}" // Add codegen to moshi (generating by using @JsonClass(generateAdapter = true))
    const val moshiKotlin  = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}" // Add reflection to moshi (better not to use : 2.5 MB)
    const val moshiAdapter = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"

    // --- Room ---
    const val room           = "androidx.room:room-runtime:${Versions.room}"
    const val roomAnnotation = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx        = "androidx.room:room-ktx:${Versions.room}" // kotlin Extensions and Coroutines support for Room

    object Test {
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2"
        const val testCore       = "androidx.test:core:1.2.0" // Core library
        const val testCoreKtx    = "androidx.test:core-ktx:1.2.0"
        const val testRules      = "androidx.test:rules:1.2.0" // JUnit Rules
        const val testJunit      = "androidx.test.ext:junit:1.1.1" // Assertions and JUnit 4 framework
        const val testJunitKtx   = "androidx.test.ext:junit-ktx:1.1.1" // Assertions
        const val testTruth2     = "com.google.truth:truth:0.44"
        const val testTruth      = "androidx.test.ext:truth:1.2.0"
        const val coreTesting    = "androidx.arch.core:core-testing:2.1.0"
        const val mockitoWeb     = "com.squareup.okhttp3:mockwebserver:4.4.0"
        const val mockito        = "org.mockito:mockito-core:3.2.4"
        const val mockitokotlin  = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0" // A small library that provides helper functions to work with Mockito in Kotlin.

        const val benchmarkJUnit4       = "androidx.benchmark:benchmark-junit4:${Versions.benchmark}"
        const val testRunner            = "androidx.test:runner:1.2.0" // AndroidJUnitRunner
        const val espresso              = "androidx.test.espresso:espresso-core:3.2.0"
        const val espressoContrib       = "androidx.test.espresso:espresso-contrib:3.1.0"
        const val espressoWeb           = "androidx.test.espresso:espresso-web:3.1.0"
        const val espressoIdling        = "androidx.test.espresso.idling:idling-concurrent:3.1.0" // this dependency an be implementation
        const val espressoAccessibility = "androidx.test.espresso:espresso-accessibility:3.1.0"
        const val espressoIntents       = "androidx.test.espresso:espresso-intents:3.2.0"

    }

    object AndroidTest {
        const val navigation = "androidx.navigation:navigation-testing:${Versions.navigation}"
    }
}

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.addAndroidTestDependencies() {
    androidTestImplementation(Lib.Test.testCore)
    androidTestImplementation(Lib.Test.testCoreKtx)
    androidTestImplementation(Lib.Test.testRules)
    androidTestImplementation(Lib.Test.testRunner)
    androidTestImplementation(Lib.Test.testJunit)
    androidTestImplementation(Lib.Test.testJunitKtx)
    androidTestImplementation(Lib.Test.coreTesting)
    androidTestImplementation(Lib.Test.espresso)
    androidTestImplementation(Lib.Test.espressoIntents)
}

fun DependencyHandler.addTestDependencies() {
    testImplementation(Lib.Test.testCore)
    testImplementation(Lib.Test.testCoreKtx)
    testImplementation(Lib.Test.testRunner)
    testImplementation(Lib.Test.testRules)
    testImplementation(Lib.Test.testJunit)
    testImplementation(Lib.Test.testJunitKtx)
    testImplementation(Lib.Test.testTruth)
    testImplementation(Lib.Test.testTruth2)
    testImplementation(Lib.Test.coreTesting)
    testImplementation(Lib.Test.mockitoWeb)
    testImplementation(Lib.Test.mockito)
    testImplementation(Lib.Test.mockitokotlin)
    testImplementation(Lib.Test.coroutinesTest)
}