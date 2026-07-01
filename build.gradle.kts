plugins {
    alias(libs.plugins.eclipse)
    alias(libs.plugins.idea)
    alias(libs.plugins.moddevgradle)
    alias(libs.plugins.publisher)
    alias(libs.plugins.mcSafeResources)
}

val mod_group_id: String by project
val mod_version: String by project
val mod_id: String by project
val minecraft_version: String by project
val forge_version: String by project
val parchment_version: String by project

val minecraft_version_range: String by project
val forge_version_range: String by project
val loader_version_range: String by project
val mod_name: String by project
val mod_license: String by project
val mod_authors: String by project
val mod_description: String by project

group = mod_group_id
version = mod_version

val mixinConfigDirectory: String = "mixins.$mod_id.json"
val mixinArgument: String = "-mixin.config=$mixinConfigDirectory"

val modPascalCase: String = mod_id.split('_').joinToString("") { it.replaceFirstChar { char -> char.uppercase() } }

base {
    archivesName.set(modPascalCase)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

legacyForge {
    version = ("$minecraft_version-$forge_version")
    //accessTransformers.from(file("src/main/resources/META-INF/accesstransformer.cfg"))

    parchment {
        mappingsVersion.set(parchment_version)
        minecraftVersion.set(minecraft_version)
    }


    runs {

        configureEach {
            programArgument(mixinArgument)
        }

        create("clientProduction") {
            client()
            systemProperty("net.minecraftforge.gradle.GradleStart.srg.srg-mcp", "false")
            devLogin.set(true)
        }

        create("client") {
            client()
            devLogin.set(true)
            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("forge.enabledGameTestNamespaces", mod_id)
        }

        create("clientNoAuth") {
            client()
            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("forge.enabledGameTestNamespaces", mod_id)
        }

        create("server") {
            server()
            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("forge.enabledGameTestNamespaces", mod_id)
        }

        create("data") {
            data()
            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("forge.enabledGameTestNamespaces", mod_id)
        }
    }

    mods {
        create(mod_id) {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets.main {
    resources {
        srcDir("src/generated/resources")
    }
}

repositories {
    fun RepositoryHandler.strictMaven(url: String, vararg groups: String) {
        exclusiveContent {
            forRepository { maven(url) }
            filter {
                groups.forEach { includeGroupAndSubgroups(it) }
            }
        }
    }

    strictMaven("https://cursemaven.com", "curse.maven")
    strictMaven("https://api.modrinth.com/maven", "maven.modrinth")
    strictMaven("https://maven.bawnorton.com/releases",  "com.github.bawnorton")

    flatDir {
        dir("./libs")
    }
    mavenCentral()
}



dependencies {
    compileOnly(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-common:0.3.7-beta.1")!!)
    implementation("com.github.bawnorton.mixinsquared:mixinsquared-forge:0.3.7-beta.1")
    jarJar("com.github.bawnorton.mixinsquared:mixinsquared-forge:[0.3.7-beta.1,)")
}

dependencies {
    // Custom Mod Dependencies
    modImplementation("curse.maven:epic-fight-mod-405076:8049910")
    modImplementation("curse.maven:combat-evolution-1377795:8164342")
    modImplementation("curse.maven:wom-918614:8195403")
    modCompileOnly("curse.maven:timeless-and-classics-zero-1028108:7401617")
    modCompileOnly("curse.maven:epicfight-nightfall-1307848:7670480")
    modCompileOnly("curse.maven:epicfight-extra-1434276:7961244")
    modImplementation("curse.maven:dummmmmmy-multi-225738:6860192")
    modImplementation("curse.maven:moonlight-499980:7664875")

    // Performance & Core Tweaks
    modRuntimeOnly("curse.maven:embeddium-908741:5681725")
    modRuntimeOnly("curse.maven:epic-fight-weapons-of-miracles-expansion-wom-plus-1587633:8333358")
    modRuntimeOnly("curse.maven:ferritecore-429235:4810975")
    modRuntimeOnly("curse.maven:canary-665658:5089991")


}

tasks.named<ProcessResources>("processResources").configure {
    val replaceProperties = mapOf(
        "minecraft_version" to minecraft_version,
        "minecraft_version_range" to minecraft_version_range,
        "forge_version" to forge_version,
        "forge_version_range" to forge_version_range,
        "loader_version_range" to loader_version_range,
        "mod_id" to mod_id,
        "mod_name" to mod_name,
        "mod_license" to mod_license,
        "mod_version" to mod_version,
        "mod_authors" to mod_authors,
        "mod_description" to mod_description
    )

    inputs.properties(replaceProperties)

    filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) {
        expand(replaceProperties + mapOf("project" to project))
    }
}

tasks.named<Jar>("jar").configure {
    manifest {
        attributes("MixinConfigs" to mixinConfigDirectory)
    }
    finalizedBy(tasks.named("reobfJar"))
}

tasks.named<Jar>("jar").configure {
    finalizedBy(tasks.named("reobfJar"))
}



tasks.named("jarJar").configure {
    finalizedBy(tasks.named("reobfJar"))
}

tasks.named("assemble").configure {
    dependsOn(tasks.named("jarJar"))
}

val TaskContainer.jar: TaskProvider<Jar>
get() = named<Jar>("jar")


tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

