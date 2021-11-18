package plugins

import Libs
import Versions
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

apply<DetektPlugin>()

plugins {
    id("io.gitlab.arturbosch.detekt")
}

configure<DetektExtension> {
    autoCorrect = true
    toolVersion = Versions.Others.DETEKT
    parallel = false
    source = files(
        "src/main/kotlin",
        "src/main/java"
    )
    config = files("${project.rootDir}/detekt/default-detekt-config.yml")

    reports {
        xml {
            enabled = true
            destination = file("${project.buildDir}/detekt/reports/detekt/detekt-report.xml")
        }
        html {
            enabled = true
            destination = file("${project.buildDir}/detekt/reports/detekt/detekt-report.html")
        }
    }
}

dependencies {
    detektPlugins(Libs.Others.DETEKT_FORMATTER)
}

tasks.withType<Detekt>().configureEach {
    include("**/*.kt", "**/*.kts")
    exclude("**/build/**", ".*/resources/.*", ".*test.*,.*/resources/.*,.*/tmp/.*")

    jvmTarget = "1.8"
}
