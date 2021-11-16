plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

object Versions {
    const val gradle = "7.1.0-alpha02"
    const val kotlin = "1.5.31"
    const val hilt = "2.37"

    const val detekt = "1.18.1"
    const val versionChecker = "0.39.0"
    const val ktlint = "10.2.0"
}

object Dependencies {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt}"
    const val versionChecker = "com.github.ben-manes:gradle-versions-plugin:${Versions.versionChecker}"
    const val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"
}

dependencies {
    implementation(Dependencies.gradle)
    implementation(Dependencies.kotlinGradle)
    implementation(Dependencies.hiltGradle)

    implementation(Dependencies.detekt)
    implementation(Dependencies.versionChecker)
    implementation(Dependencies.ktlint)
}