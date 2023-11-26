plugins {
    kotlin("multiplatform") version libs.versions.kotlin apply false
    kotlin("plugin.serialization") version libs.versions.kotlin apply false
}

group = "org.cufy"
version = "1.0.0-snapshot"

tasks.wrapper {
    gradleVersion = "8.2.1"
}

subprojects {
    group = "org.cufy.codecs"

    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
