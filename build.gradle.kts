plugins {
    alias(libs.plugins.core)
    alias(libs.plugins.moddevgradle)
    alias(libs.plugins.mcSafeResources)

}

val neoforgeVersion: String = libs.versions.neoforge.get()

configureBaseArchive("neoforge")

neoForge {
    version = neoforgeVersion

    parchment {
        mappingsVersion.set(libs.versions.parchmentMappings.get())
        minecraftVersion.set(mcVersion)
    }
    runs {
        create("client") {
            client()
            devLogin.set(true)
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        create("clientNoAuth") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        create("server") {
            server()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        register(modId) {
            sourceSet(sourceSets.main.get())
        }
    }
}

java.sourceSets.main.get().java.srcDirs(
    tasks.generateLangKeys.map { it.outputs.files.singleFile },
    tasks.generateSoundKeys.map { it.outputs.files.singleFile }
)

tasks.compileJava { dependsOn(tasks.generateLangKeys, tasks.generateSoundKeys) }

sourceSets.main {
    resources.srcDir(layout.projectDirectory.dir("src/generated/resources"))
}

configurations {
    val localRuntime by creating

    runtimeClasspath {
        extendsFrom(localRuntime)
    }
}

sourceSets.main {
    resources {
        srcDir("src/generated/resources")
    }
}

dependencies {
    // Custom Mod Dependencies
    implementation(libs.epicFight)
    compileOnly("curse.maven:combat-evolution-1377795:7765635")
    compileOnly("curse.maven:wom-918614:7517612")
    compileOnly("curse.maven:timeless-and-classics-zero-1028108:7401617")
    compileOnly("curse.maven:epicfight-nightfall-1307848:7670480")
    compileOnly("curse.maven:epicfight-extra-1434276:7961244")
    runtimeOnly(libs.bundles.dummyBundle)
}

mcSafeResources {
    namespace.set(modId)
    outputPackage.set("$groupId.$modId.generated")
}

tasks.processResources {
    val replaceProperties = modPlatformMetadataReplaceProperties

    inputs.properties(replaceProperties)

    filesMatching("META-INF/*.mods.toml") {
        expand(replaceProperties)
    }
}

val groupPath = group.toString().replace('.', '/')

val apiPackage = "$groupPath/mana_arts/api/**"
val apiJarClassifier = "api"

val apiJar by tasks.registering(Jar::class) {
    group = "build"
    archiveClassifier.set(apiJarClassifier)

    from(sourceSets.main.get().output) { include(apiPackage) }
}

val apiSourcesJar by tasks.registering(Jar::class) {
    group = "build"
    archiveClassifier.set("$apiJarClassifier-sources") // Important to use this suffix, to follow standards

    from(sourceSets.main.get().allSource) { include(apiPackage) }
}

artifacts {
    archives(apiJar)
    archives(apiSourcesJar)
}

configureModPublish(ModLoader.NeoForge) { tasks.jar.get().archiveFile }