package extensions

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementTestDependencies() {
    add("testImplementation", Libs.Testing.TRUTH)
    add("testImplementation", Libs.Testing.MOCKK)
    add("testImplementation", Libs.Testing.ROBOLECTRIC)
    add("testImplementation", Libs.Testing.JUnit5.JUPITER)
    add("testImplementation", Libs.Testing.JUnit5.JUPITER_API)
    add("testImplementation", Libs.Testing.COROUTINES)
    add("testImplementation", Libs.Testing.ARCH_CORE)
    add("testImplementation", Libs.Testing.TURBINE)
    add("testImplementation", Libs.Testing.MOCK_WEBSERVER)

    add("testRuntimeOnly", Libs.Testing.JUnit5.JUPITER_ENGINE)
    add("testRuntimeOnly", Libs.Testing.JUnit5.JUPITER_VINTAGE_ENGINE)
}