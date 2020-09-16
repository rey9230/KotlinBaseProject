plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    // lint rule version should be = Android Gradle Plugin version + 23
    compileOnly("com.android.tools.lint:lint-api:${Versions.lint}")
    compileOnly("com.android.tools.lint:lint-checks:${Versions.lint}")
}
