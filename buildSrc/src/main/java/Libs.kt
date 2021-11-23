object Libs {

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.Google.MATERIAL}"
        const val HILT = "com.google.dagger:hilt-android:${Versions.Google.HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.Google.HILT}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.Network.OKHTTP}"
        const val MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.Network.MOSHI}"
        const val CHUCK = "com.readystatesoftware.chuck:library:${Versions.Network.CHUCK}"
    }

    object Coroutines {
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines.CORE}"
    }

    object Kotlin {
        const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.STDLIB}"
    }

    object Image {
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.Image.GLIDE}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.Image.GLIDE}"
    }

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APPCOMPAT}"
        const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Versions.AndroidX.LEGACY_SUPPORT}"
        const val CUSTOM_VIEW = "androidx.customview:customview:${Versions.AndroidX.CUSTOM_VIEW}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.AndroidX.FRAGMENT_KTX}"
        const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.LIFECYCLE_RUNTIME}"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.AndroidX.RECYCLER_VIEW}"
        const val SPLASH_SCREEN = "androidx.core:core-splashscreen:${Versions.AndroidX.SPLASH_SCREEN}"
        const val JUNIT_KTX = "androidx.test.ext:junit-ktx:${Versions.AndroidX.JUNIT_KTX}"

        object Navigation {
            const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.NAVIGATION}"
            const val UI = "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.NAVIGATION}"
        }
    }

    object Others {
        const val ANDROID_SVG = "com.caverock:androidsvg-aar:${Versions.Others.ANDROID_SVG}"
        const val FACEBOOK_SHIMMER = "com.facebook.shimmer:shimmer:${Versions.Others.FACEBOOK_SHIMMER}"
        const val MP_CHART = "com.github.PhilJay:MPAndroidChart:${Versions.Others.MP_CHART}"
        const val JAVAX_INJECT = "javax.inject:javax.inject:${Versions.Others.JAVAX_INJECT}"
        const val KTLINT = "com.pinterest:ktlint:${Versions.Others.KTLINT}"
        const val DETEKT_FORMATTER = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.Others.DETEKT}"
    }

    object Testing {
        const val MOCKK = "io.mockk:mockk:${Versions.Testing.MOCKK}"
        const val TRUTH = "com.google.truth:truth:${Versions.Testing.TRUTH}"
        const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.Testing.ROBOLECTRIC}"
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Testing.COROUTINES}"
        const val ARCH_CORE = "androidx.arch.core:core-testing:${Versions.Testing.ARCH_CORE}"
        const val TURBINE = "app.cash.turbine:turbine:${Versions.Testing.TURBINE}"
        const val MOCK_WEBSERVER = "com.squareup.okhttp3:mockwebserver:${Versions.Testing.MOCK_WEBSERVER}"

        object JUnit5 {
            const val JUPITER = "org.junit.jupiter:junit-jupiter:${Versions.Testing.JUNIT5}"
            const val JUPITER_API = "org.junit.jupiter:junit-jupiter-api:${Versions.Testing.JUNIT5}"
            const val JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${Versions.Testing.JUNIT5}"
            const val JUPITER_VINTAGE_ENGINE = "org.junit.vintage:junit-vintage-engine:${Versions.Testing.JUNIT5}"
        }
    }
}