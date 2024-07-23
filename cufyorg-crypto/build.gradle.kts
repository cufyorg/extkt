plugins {
    `maven-publish`

    kotlin("multiplatform")
}

kotlin {
    jvm {
        withJava()
    }
    js {
        binaries.library()
        browser()
        nodejs()
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
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.tink)
                implementation(libs.favre.bcrypt)
            }
        }
    }
}
