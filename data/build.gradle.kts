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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.CORE))

    implementation(Libs.Network.RETROFIT)
    implementation(Libs.Network.OKHTTP)
    implementation(Libs.Network.MOSHI)

    implementation(Libs.Google.HILT)
    kapt(Libs.Google.HILT_COMPILER)
}
