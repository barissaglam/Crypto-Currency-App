

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

    plugins.apply(Plugins.KTLINT)
    plugins.apply(Plugins.DETEKT)
    plugins.apply(Plugins.VERSION_CHECK)
    plugins.apply(Plugins.TASKS)
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
