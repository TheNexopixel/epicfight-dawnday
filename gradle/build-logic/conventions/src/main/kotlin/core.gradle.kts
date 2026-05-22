// Configures shared Gradle setup for the Epic Fight project.
//
// Consumers of this Gradle plugin are expected to manually call the following methods:
//
// * configureBaseArchive: with the Gradle project name to produce unique JAR file names.

plugins {
    `java-library`
    idea
    id("me.modmuss50.mod-publish-plugin")
}

version = modVersion
group = groupId

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(javaVersion)
}

repositories {
    mavenCentral()

    flatDir { dir(rootProject.file("libs")) }

    // Modrinth maven: https://support.modrinth.com/en/articles/8801191-modrinth-maven
    // Note: Prefer Maven repositories provided by the mod dependencies over Modrinth.
    strictMaven(
        "Modrinth",
        "https://api.modrinth.com/maven",
        "maven.modrinth",
    )

    strictMaven("MixinSquared", "https://maven.bawnorton.com/releases",  "com.github.bawnorton")

    // Curse maven: https://cursemaven.com
    // Note: Prefer Modrinth or Maven repositories provided by the mod dependencies over Curse Maven.
    strictMaven(
        "CurseMaven",
        "https://cursemaven.com",
        "curse.maven",
    )

    strictMaven(
        "Fuzs Mod Resources (Forge Config API)",
        "https://raw.githubusercontent.com/Fuzss/modresources/main/maven/",
        "fuzs.forgeconfigapiport",
    )

    // JEI
    strictMaven("Jared", "https://maven.blamejared.com", "mezz.jei")
    strictMaven("Architectury", "https://maven.architectury.dev", "dev.architectury")

    // KubeJS
    strictMaven("Latvian", "https://maven.latvian.dev/releases", "dev.latvian.mods")

    // Controlify, YACL
    strictMaven("IsXander", "https://maven.isxander.dev/releases", "dev.isxander")

    strictMaven("AzureDoom Mods", "https://maven.azuredoom.com/mods", "mod.azure.azurelib", "mod.azure.azurelibarmor")
    strictMaven("GeckoLib", "https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/", "software.bernie.geckolib")
    strictMaven("Illusive Soulworks (Curios API)", "https://maven.theillusivec4.top/", "top.theillusivec4.curios")
    strictMaven("playerAnimator", "https://maven.kosmx.dev", "dev.kosmx.player-anim")
}

val jar = tasks.named<Jar>("jar")

tasks.register<SignJar>("signJar") {

    // Pass the archive file directly from the jar task
    jarFile.set(jar.flatMap { it.archiveFile })

    // Set up configuration paths safely
    configFile.set(layout.projectDirectory.file("yubikey.conf"))
    iconFile.set(layout.projectDirectory.file("icon.png"))

    // Fallback logic for properties
    val alias = project.findProperty("yubiAlias")?.toString()
        ?: "X.509 Certificate for Digital Signature"
    aliasName.set(alias)

    // Ensure it runs after the jar is built
    dependsOn(jar)
}

jar {
    from(rootProject.file("LICENSE"))
    from(rootProject.file("LICENSE-ASSETS"))
    from(rootProject.file("LICENSE-ASSETS")) { into("assets/$modId") }
    from(rootProject.file("LICENSE-ASSETS")) { into("assets/minecraft") }
}
