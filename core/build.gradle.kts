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
    api(Dependencies.javaxInject)

    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.customView)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.material)
}
