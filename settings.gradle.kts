dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "codecs"

// include directories that starts with "meemer-"
for (file in rootDir.listFiles().orEmpty()) {
    if (file.isDirectory && file.name.startsWith("codecs-")) {
        include(":${file.name}")
    }
}
