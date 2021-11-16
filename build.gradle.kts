buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        mavenCentral {
            content {
                includeModule("org.jetbrains.kotlinx", "kotlinx-html-jvm")
            }
        }
    }

    plugins.apply(Plugins.ktlint)
    plugins.apply(Plugins.detekt)
    plugins.apply(Plugins.versionCheck)
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
