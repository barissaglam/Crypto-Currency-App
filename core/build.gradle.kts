plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
}

android {
    compileSdk = Configs.COMPILE_SDK_VERSION
    buildToolsVersion = Configs.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = Configs.MIN_SDK_VERSION
        targetSdk = Configs.TARGET_SDK_VERSION

        testInstrumentationRunner = Configs.TEST_INSTRUMENTATION_RUNNER
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
}

dependencies {

    api(project(Modules.EXTENSIONS))
    api(Libs.Others.JAVAX_INJECT)

    implementation(Libs.AndroidX.FRAGMENT_KTX)
    implementation(Libs.AndroidX.APPCOMPAT)
    implementation(Libs.AndroidX.LIFECYCLE_RUNTIME)
    implementation(Libs.AndroidX.CORE_KTX)
    implementation(Libs.AndroidX.CUSTOM_VIEW)
    implementation(Libs.AndroidX.RECYCLER_VIEW)

    implementation(Libs.Coroutines.CORE)
    implementation(Libs.Google.MATERIAL)
}
