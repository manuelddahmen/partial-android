import java.util.Properties

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.sun.activation:javax.activation:1.2.0")
        classpath("jakarta.activation:jakarta.activation-api:2.1.3")
    }
}

plugins {
    alias(libs.plugins.android.library) apply false
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

val defaultVersion = "0.0.19"
val buildVersion = findProperty("version")?.toString().let {
    if (it.isNullOrEmpty() || it == "unspecified") defaultVersion else it
}

allprojects {
    group = "one.empty3.libs"
    version = buildVersion
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
        version.set(buildVersion)
        description.set("Android library for Empty3 feature processing")
        authors.set(listOf("Manuel D. Dahmen"))
        license.set("Apache-2.0")
        copyright.set("Copyright 2026 Manuel Daniel Dahmen")
    }
    deploy {
        maven {
            pomchecker {
                failOnError.set(false)
                failOnWarning.set(false)
                strict.set(false)
            }
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
                    verifyPom.set(false)
                    applyMavenCentralRules.set(false)
                }
            }
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
