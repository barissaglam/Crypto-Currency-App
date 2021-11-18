plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

object Versions {
    const val GRADLE = "7.1.0-alpha02"
    const val KOTLIN = "1.5.31"
    const val HILT = "2.37"

    const val DETEKT = "1.18.1"
    const val VERSION_CHECKER = "0.39.0"
    const val KTLINT = "10.2.0"
}

object Dependencies {
    const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val HILT_GRADLE = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"

    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.DETEKT}"
    const val VERSION_CHECKER = "com.github.ben-manes:gradle-versions-plugin:${Versions.VERSION_CHECKER}"
    const val KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.KTLINT}"
}

dependencies {
    implementation(Dependencies.GRADLE)
    implementation(Dependencies.KOTLIN_GRADLE)
    implementation(Dependencies.HILT_GRADLE)

    implementation(Dependencies.DETEKT)
    implementation(Dependencies.VERSION_CHECKER)
    implementation(Dependencies.KTLINT)
}