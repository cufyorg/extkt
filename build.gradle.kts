plugins {
    kotlin("multiplatform") version libs.versions.kotlin apply false
    kotlin("plugin.serialization") version libs.versions.kotlin apply false
}

group = "org.cufy"
version = "1.0.0"

tasks.wrapper {
    gradleVersion = "8.2.1"
}

subprojects {
    group = "org.cufy.kped"

    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
