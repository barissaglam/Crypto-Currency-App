plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
}

android {
    compileSdk = Configs.compileSdkVersion
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion

        testInstrumentationRunner = Configs.testInstrumentationRunner
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

    api(project(Modules.extensions))
    api(Libs.Others.javaxInject)

    implementation(Libs.AndroidX.fragmentKtx)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.lifecycleRuntime)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.customView)
    implementation(Libs.AndroidX.recyclerView)

    implementation(Libs.Coroutines.coroutines)

    implementation(Libs.Google.material)
}
