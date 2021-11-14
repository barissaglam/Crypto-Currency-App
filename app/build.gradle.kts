plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Configs.compileSdkVersion
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = Configs.testInstrumentationRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = freeCompilerArgs + arrayOf(
            "-Xuse-experimental=androidx.compose.ui.ExperimentalComposeUiApi",
            "-Xuse-experimental=com.google.accompanist.pager.ExperimentalPagerApi"
        )
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.legacySupport)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUI)
    implementation(Dependencies.hilt)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.moshi)
    implementation(Dependencies.glide)
    implementation(Dependencies.androidSvg)
    implementation(Dependencies.facebookShimmer)
    implementation(Dependencies.mpChart)
    implementation(Dependencies.splashScreen)
    implementation(Dependencies.jUnitKtx)

    kapt(Dependencies.glideCompiler)
    kapt(Dependencies.hiltCompiler)

    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.jUnitJupiter)
    testImplementation(Dependencies.jUnitJupiterApi)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.archCoreTesting)

    testRuntimeOnly(Dependencies.jUnitJupiterEngine)
    testRuntimeOnly(Dependencies.jUnitJupiterVintageEngine)

    debugImplementation(Dependencies.chuck)
}