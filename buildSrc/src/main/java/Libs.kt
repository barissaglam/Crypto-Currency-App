object Libs {

    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.moshi}"
        const val chuck = "com.readystatesoftware.chuck:library:${Versions.chuck}"
    }

    object Coroutines {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object Kotlin {
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStdLib}"
    }

    object Image {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
        const val customView = "androidx.customview:customview:${Versions.customView}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
        const val jUnitKtx = "androidx.test.ext:junit-ktx:${Versions.jUnitKtx}"

        object Navigation {
            const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        }
    }

    object Others {
        const val androidSvg = "com.caverock:androidsvg-aar:${Versions.androidSvg}"
        const val facebookShimmer = "com.facebook.shimmer:shimmer:${Versions.facebookShimmer}"
        const val mpChart = "com.github.PhilJay:MPAndroidChart:${Versions.mpChart}"
        const val javaxInject = "javax.inject:javax.inject:${Versions.javaxInjection}"
        const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
        const val detektFormatter = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
    }

    object Testing {
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val truth = "com.google.truth:truth:${Versions.truth}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
        const val jUnitJupiter = "org.junit.jupiter:junit-jupiter:${Versions.jUnit5}"
        const val jUnitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.jUnit5}"
        const val jUnitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jUnit5}"
        const val jUnitJupiterVintageEngine = "org.junit.vintage:junit-vintage-engine:${Versions.jUnit5}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
        const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"
    }


}