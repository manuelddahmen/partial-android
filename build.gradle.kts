import java.util.Properties

plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("org.jreleaser") version "1.24.0"
}

val privateProperties = Properties().apply {
    val propFile = File(rootDir, "../private.properties/gradle.properties").let {
        if (it.exists()) it else File("D:/current/apps/private.properties/gradle.properties")
    }
    if (propFile.exists()) {
        propFile.inputStream().use { load(it) }
    }
}

val propertiesReleaseConfig = Properties().apply {
    val propFile = File("C:\\Users\\manue\\.jreleaser\\config.properties")
    if (propFile.exists()) {
        propFile.inputStream().use { load(it) }
    }
}

allprojects {
    group = "one.empty3.libs"
    version = findProperty("version")?.toString() ?: "0.0.18"
}

jreleaser {
    signing {
        active.set(org.jreleaser.model.Active.ALWAYS)
        armored.set(true)
        mode.set(org.jreleaser.model.Signing.Mode.FILE)
        publicKey.set(privateProperties.getProperty("signing.publicKeyPath"))
        passphrase.set(privateProperties.getProperty("signing.passphrase"))
        secretKey.set(privateProperties.getProperty("signing.secretKey"))
    }
    release {
        github {
            token.set(
                propertiesReleaseConfig.getProperty("JRELEASER_GITHUB_TOKEN")
                    ?: privateProperties.getProperty("release.github.token")
                    ?: privateProperties.getProperty("GITHUB_TOKEN")
            )
        }
    }
    project {
        name.set("partial-android")
        version.set("0.0.18")
        description.set("Android library for Empty3 feature processing")
        authors.set(listOf("Manuel D. Dahmen"))
        license.set("Apache-2.0")
        copyright.set("Copyright 2026 Manuel Daniel Dahmen")
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active.set(org.jreleaser.model.Active.ALWAYS)
                    url.set("https://central.sonatype.com/api/v1/publisher")
                    stagingRepository("partialandroid/build/staging-deploy")
                    username.set(
                        propertiesReleaseConfig.getProperty("JRELEASER_MAVENCENTRAL_USERNAME")
                            ?: privateProperties.getProperty("mavenCentralUsername")
                    )
                    password.set(
                        propertiesReleaseConfig.getProperty("JRELEASER_MAVENCENTRAL_PASSWORD")
                            ?: privateProperties.getProperty("mavenCentralPassword")
                    )
                    namespace.set("one.empty3.libs")
                }
            }
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
