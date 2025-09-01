import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    `maven-publish`

    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()
    js {
        binaries.library()
        browser()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        binaries.library()
        browser()
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(libs.kotlinx.serialization.json)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
