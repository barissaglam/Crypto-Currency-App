plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.HILT_ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
}

android {
    compileSdk = Configs.COMPILE_SDK_VERSION
    buildToolsVersion = Configs.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = Configs.APPLICATION_ID
        minSdk = Configs.MIN_SDK_VERSION
        targetSdk = Configs.TARGET_SDK_VERSION
        versionCode = Configs.VERSION_CODE
        versionName = Configs.VERSION_NAME

        testInstrumentationRunner = Configs.TEST_INSTRUMENTATION_RUNNER

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

    implementation(project(Modules.CORE))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.DATA))

    implementation(Libs.Google.MATERIAL)
    implementation(Libs.Google.HILT)
    kapt(Libs.Google.HILT_COMPILER)

    implementation(Libs.Network.RETROFIT)
    implementation(Libs.Network.OKHTTP)
    implementation(Libs.Network.MOSHI)
    debugImplementation(Libs.Network.CHUCK)

    implementation(Libs.Coroutines.CORE)

    implementation(Libs.Kotlin.STDLIB)

    implementation(Libs.Image.GLIDE)
    kapt(Libs.Image.GLIDE_COMPILER)

    implementation(Libs.AndroidX.SPLASH_SCREEN)
    implementation(Libs.AndroidX.JUNIT_KTX)
    implementation(Libs.AndroidX.CORE_KTX)
    implementation(Libs.AndroidX.APPCOMPAT)
    implementation(Libs.AndroidX.LEGACY_SUPPORT)
    implementation(Libs.AndroidX.Navigation.FRAGMENT)
    implementation(Libs.AndroidX.Navigation.UI)

    implementation(Libs.Others.ANDROID_SVG)
    implementation(Libs.Others.FACEBOOK_SHIMMER)
    implementation(Libs.Others.MP_CHART)

    testImplementation(Libs.Testing.TRUTH)
    testImplementation(Libs.Testing.MOCKK)
    testImplementation(Libs.Testing.ROBOLECTRIC)
    testImplementation(Libs.Testing.JUnit5.JUPITER)
    testImplementation(Libs.Testing.JUnit5.JUPITER_API)
    testImplementation(Libs.Testing.COROUTINES)
    testImplementation(Libs.Testing.ARCH_CORE)
    testRuntimeOnly(Libs.Testing.JUnit5.JUPITER_ENGINE)
    testRuntimeOnly(Libs.Testing.JUnit5.JUPITER_VINTAGE_ENGINE)
}
