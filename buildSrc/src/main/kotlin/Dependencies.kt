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
    const val gradle   = "3.5.3"
    const val kotlin   = "1.3.60"

    const val safeArgs = "2.2.0-alpha03"
}

object Lib {
    const val appcompat        = "androidx.appcompat:appcompat:1.1.0" // Degrade gracefully on older versions of Android.
    const val coreKtx          = "androidx.core:core-ktx:1.2.0" // Write more concise, idiomatic Kotlin code.
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val material         = "com.google.android.material:material:1.2.0-alpha05" // Build beautiful products, faster.
    const val coil             = "io.coil-kt:coil:0.8.0"

    // --- Preference ---
    const val preference       = "androidx.preference:preference:1.1.0"
    const val preferenceKtx    = "androidx.preference:preference-ktx:1.1.0"

    // --- Coroutines ---
    const val coroutines          = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2"
    const val coroutinesAndroid   = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.2" // for testing coroutines
    const val coroutinesLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0" // lifecycleScope + launchWhenResumed and ets.
    const val coroutinesLivedata  = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0" // liveData (LiveData + coroutines)
    const val coroutinesViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0" // viewModelScope + savedStateHandle
    const val lifecycleAnnotation = "androidx.lifecycle:lifecycle-common-java8:2.2.0" // that's only needed if you have lifecycle-related annotations in your code, specifically @OnLifecycleEvent
    const val fragmentKtx         = "androidx.fragment:fragment-ktx:1.2.4" // easy fragment transaction + by viewModels()
    const val activityKtx         = "androidx.activity:activity-ktx:1.1.0" // on BackPress support for Fragment

    // --- Firebase Coroutines ---
    const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.2"

    // --- Dagger ---
    const val dagger                   = "com.google.dagger:dagger:2.25.2"
    const val daggerAnnotation         = "com.google.dagger:dagger-compiler:2.25.2"
    const val daggerAssisted           = "com.squareup.inject:assisted-inject-annotations-dagger2:0.5.2"
    const val daggerAssistedAnnotation = "com.squareup.inject:assisted-inject-processor-dagger2:0.5.2"

    //  --- Navigation ---
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:2.2.1" // Fragment.findNavController + Fragment.navArgs
    const val navigationUiKtx       = "androidx.navigation:navigation-ui-ktx:2.2.1" // setupActionBarWithNavController + setupWithNavController
    const val navigationRuntimeKtx  = "androidx.navigation:navigation-runtime-ktx:2.2.1" // Activity.findNavController + Activity.navArgs + View.findNavController

    // --- Retrofit ---
    const val retrofit               = "com.squareup.retrofit2:retrofit:2.7.1"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:2.7.1"
    const val retrofitInterceptor    = "com.squareup.okhttp3:logging-interceptor:4.3.1"

    // --- Moshi ---
    const val moshi        = "com.squareup.moshi:moshi:1.9.2" // It makes it easy to parse JSON into Kotlin objects
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.9.2" // Add codegen to moshi (generating by using @JsonClass(generateAdapter = true))
    const val moshiKotlin  = "com.squareup.moshi:moshi-kotlin:1.9.2" // Add reflection to moshi (better not to use : 2.5 MB)
    const val moshiAdapter = "com.squareup.moshi:moshi-adapters:1.9.2"

    // --- Room ---
    const val room           = "androidx.room:room-runtime:2.2.4"
    const val roomAnnotation = "androidx.room:room-compiler:2.2.4"
    const val roomKtx        = "androidx.room:room-ktx:2.2.4" // kotlin Extensions and Coroutines support for Room

    object Test {
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2"
        const val junit          = "junit:junit:4.13" // Required -- JUnit 4 framework
        const val testCore       = "androidx.test:core:1.2.0" // Core library
        const val testCoreKtx    = "androidx.test:core-ktx:1.2.0"
        const val testRunner     = "androidx.test:runner:1.2.0" // AndroidJUnitRunner
        const val testRules      = "androidx.test:rules:1.2.0" // JUnit Rules
        const val testJunit      = "androidx.test.ext:junit:1.1.1" // Assertions
        const val testJunitKtx   = "androidx.test.ext:junit-ktx:1.1.1" // Assertions
        const val testTruth2     = "com.google.truth:truth:0.44"
        const val testTruth      = "androidx.test.ext:truth:1.2.0"
        const val coreTesting    = "androidx.arch.core:core-testing:2.1.0"
        const val mockitoWeb     = "com.squareup.okhttp3:mockwebserver:4.4.0"
        const val mockito        = "org.mockito:mockito-core:3.2.4"
        const val mockitokotlin  = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0" // A small library that provides helper functions to work with Mockito in Kotlin.

        const val espresso        = "androidx.test.espresso:espresso-core:3.2.0"
        const val espressoIntents = "androidx.test.espresso:espresso-intents:3.2.0"


    }

    object AndroidTest {

    }
}
