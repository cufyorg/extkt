plugins {
    kotlin("multiplatform") version libs.versions.kotlin apply false
    kotlin("plugin.serialization") version libs.versions.kotlin apply false
}

group = "org.cufy"

tasks.wrapper {
    gradleVersion = "8.14"
}

subprojects {
    group = "org.cufy.extkt"

    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
