plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    // Android Gradle Plugin version + 23
    val lintVersion = "26.5.3"
    compileOnly("com.android.tools.lint:lint-api:$lintVersion")
    compileOnly("com.android.tools.lint:lint-checks:$lintVersion")
}
