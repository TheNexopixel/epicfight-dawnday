pluginManagement {
    repositories {
        mavenLocal()
        fun RepositoryHandler.strictMaven(url: String, vararg groups: String) {
            exclusiveContent {
                forRepository { maven(url) }
                filter {
                    groups.forEach { includeGroupAndSubgroups(it) }
                }
            }
        }
        strictMaven("https://maven.neoforged.net/releases", "net.neoforged")
        gradlePluginPortal()
    }
    includeBuild("gradle/build-logic")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "Epic Fight Dawn Day"
