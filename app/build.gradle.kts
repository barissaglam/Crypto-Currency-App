plugins {
    id(Plugins.androidApplication)
    id(Plugins.hiltAndroid)
    id(Plugins.kotlinParcelize)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
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

    implementation(Libs.Google.material)
    implementation(Libs.Google.hilt)
    kapt(Libs.Google.hiltCompiler)

    implementation(Libs.Network.retrofit)
    implementation(Libs.Network.okHttp)
    implementation(Libs.Network.moshi)
    debugImplementation(Libs.Network.chuck)

    implementation(Libs.Coroutines.coroutines)

    implementation(Libs.Kotlin.kotlinStdLib)

    implementation(Libs.Image.glide)
    kapt(Libs.Image.glideCompiler)

    implementation(Libs.AndroidX.splashScreen)
    implementation(Libs.AndroidX.jUnitKtx)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.legacySupport)
    implementation(Libs.AndroidX.Navigation.navigationFragment)
    implementation(Libs.AndroidX.Navigation.navigationUI)

    implementation(Libs.Others.androidSvg)
    implementation(Libs.Others.facebookShimmer)
    implementation(Libs.Others.mpChart)

    testImplementation(Libs.Testing.truth)
    testImplementation(Libs.Testing.mockk)
    testImplementation(Libs.Testing.robolectric)
    testImplementation(Libs.Testing.jUnitJupiter)
    testImplementation(Libs.Testing.jUnitJupiterApi)
    testImplementation(Libs.Testing.coroutinesTest)
    testImplementation(Libs.Testing.archCoreTesting)
    testRuntimeOnly(Libs.Testing.jUnitJupiterEngine)
    testRuntimeOnly(Libs.Testing.jUnitJupiterVintageEngine)
}
