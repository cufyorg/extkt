plugins {
    `maven-publish`

    kotlin("multiplatform")
}

kotlin {
    jvm()
    js {
        binaries.library()
        nodejs()
        useEsModules()
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.tink)
                implementation(libs.favre.bcrypt)
            }
        }
        jsMain {
            dependencies {
                implementation(npm("bcryptjs", "^3.0.2"))
            }
        }
    }
}
