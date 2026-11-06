/*
 * Licensed to End Users from Google Play Store and Apple App Store under the following terms:
 *
 * This app is provided "as is" without any warranties or guarantees of any kind, either express or implied. The developers and contributors of this app are not responsible for any damages, losses, or issues that may arise from the use of this app.
 * By downloading, installing, or using this app, you agree to the following terms:
 * 1. You may use this app for personal, or non-commercial purposes only or commercial purposes.
 * 2. You may not modify, distribute, or create derivative works of this app without
 *     prior written permission from the developers.
 * 3. You may not reverse engineer, decompile, or disassemble this app.
 *
 * Copyright 2026-2026 Empty3 Benelux Trademark.
 */

import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
    signing
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

group = "one.empty3.libs"
version = findProperty("version")?.toString() ?: "0.0.18"

android {
    namespace = "one.empty3.libs"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    packaging {
        jniLibs {
            useLegacyPackaging = false
            excludes += listOf(
                "**/libtensorflowlite_jni.so"
            )
        }
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "mozilla/public-suffix-list.txt"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
            excludes += "META-INF/*.kotlin_module"
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/spring.factories"
            excludes += "META-INF/spring/aot.factories"
            excludes += "META-INF/spring.schemas"
            excludes += "META-INF/spring.tooling"
            excludes += "META-INF/spring.handlers"
            excludes += "META-INF/spring-configuration-metadata.json"
            excludes += "META-INF/additional-spring-configuration-metadata.json"
        }
    }

    lint {
        targetSdk = 36
        abortOnError = false
        checkReleaseBuilds = false
        disable += listOf("AccidentalOctal", "AndroidGradlePluginVersion", "AnnotationProcessorOnCompilePath", "BomWithoutPlatform", "UseOfBundledGooglePlayServices", "ChromeOsAbiSupport", "GradleCompatible", "CoreLibDesugaringV1", "DataBindingWithoutKapt", "GradleDependency", "GradleDeprecated", "GradleDeprecatedConfiguration", "OutdatedLibrary", "DevModeObsolete", "DuplicatePlatformClasses", "EditedTargetSdkVersion", "ExpiredTargetSdkVersion", "ExpiringTargetSdkVersion", "GradleGetter", "GradlePluginVersion", "HighAppVersionCode", "GradleIdeError", "InstantAppDeprecation", "JavaPluginLanguageLevel", "JcenterRepositoryObsolete", "KaptUsageInsteadOfKsp", "KtxExtensionAvailable", "LifecycleAnnotationProcessorWithJava8", "MinSdkTooLow", "SimilarGradleDependency", "NotInterpolated", "GradlePath", "PlaySdkIndexDeprecated", "PlaySdkIndexGenericIssues", "PlaySdkIndexNonCompliant", "PlaySdkIndexVulnerability", "GradleDynamicVersion", "NewerVersionAvailable", "R8GradualApi", "RiskyLibrary", "StringShouldBeInt", "UseTomlInstead", "OldTargetApi", "WrongThread", "WrongThreadInterprocedural")
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.isFork = true
    options.forkOptions.executable = "C:/Users/manue/.jdks/openjdk-25.0.2/bin/javac.exe"
}

configurations.configureEach {
    exclude(group = "org.jdom", module = "jdom2")
    exclude(group = "com.formdev", module = "flatlaf")
    exclude(group = "xpp3", module = "xpp3")
    exclude(group = "one.empty3.libs", module = "partial-desktop")
//    exclude(group = "one.empty3.libs", module = "partial-android")
    exclude(group = "com.sun.activation", module = "javax.activation")
    exclude(group = "com.google.api.grpc", module = "proto-google-common-protos")
    exclude(module = "protobuf-java")
    exclude(group = "org.springframework", module = "spring-jcl")
    exclude(group = "commons-logging", module = "commons-logging")
    exclude(group = "org.apache.servicemix.bundles", module = "org.apache.servicemix.bundles.jdom")
    exclude(group = "org.bytedeco", module = "javacv-platform")
    exclude(group = "org.bytedeco", module = "javacpp")
    exclude(group = "org.bytedeco", module = "leptonica")
    exclude(group = "org.bytedeco", module = "opencv")
    exclude(group = "org.bytedeco", module = "tesseract")
    exclude(group = "org.bytedeco", module = "openblas")
    exclude(group = "org.bytedeco", module = "librealsense2")
    exclude(group = "org.bytedeco", module = "ffmpeg")
    exclude(group = "org.bytedeco", module = "videoinput")
    exclude(group = "com.fasterxml.jackson.core", module = "jackson-core")
    exclude(group = "com.google.cloud.functions.invoker", module = "java-function-invoker")
    exclude(group = "com.google.cloud.functions.invoker", module = "java-function-invoker:1.3.0")
    exclude(group = "com.google.firebase", module = "protolite-well-known-types")
    exclude(group = "com.google.firebase", module = "firebase-admin")
}

dependencies {
    constraints {
        implementation("io.netty:netty-codec-http:4.2.17.Final")
        implementation("com.fasterxml.jackson.core:jackson-core:2.21.4")
        implementation("io.grpc:grpc-netty-shaded:1.75.0")
        implementation("com.google.protobuf:protobuf-java:4.28.2")
        implementation("com.google.android.gms:play-services-basement:18.12.0")
    }
    implementation(libs.androidx.multidex)
    implementation(libs.empty3.library.mp) {
        exclude(group = "one.empty3.libs", module = "partial-desktop")
        exclude(group = "io.grpc")
    }
    implementation(libs.commons.mp) {
        exclude(group = "io.grpc")
    }
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.test.rules)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = project.group.toString()
            artifactId = "partial-android"
            version = project.version.toString()

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("partial-android")
                description.set("Android library for Empty3 feature processing and Android utilities")
                url.set("https://github.com/manuelddahmen/partial-android")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("manuelddahmen")
                        name.set("Manuel Daniel Dahmen")
                        email.set("manuel.dahmen@gmx.com")
                        url.set("https://empty3.one")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/manuelddahmen/partial-android.git")
                    developerConnection.set("scm:git:https://github.com/manuelddahmen/partial-android.git")
                    url.set("https://github.com/manuelddahmen/partial-android")
                }
            }
        }
    }

    repositories {
        mavenLocal()
        maven {
            name = "staging"
            url = uri(layout.buildDirectory.dir("staging-deploy"))
        }
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = privateProperties.getProperty("ossrhUsername") ?: privateProperties.getProperty("mavenCentralUsername")
                password = privateProperties.getProperty("ossrhPassword") ?: privateProperties.getProperty("mavenCentralPassword")
            }
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/manuelddahmen/partial-android")
            credentials {
                username = privateProperties.getProperty("GITHUB_ACTOR") ?: "manuelddahmen"
                password = privateProperties.getProperty("release.github.token") ?: privateProperties.getProperty("GITHUB_TOKEN")
            }
        }
        maven {
            name = "JetBrainsSpace"
            url = uri("https://maven.pkg.jetbrains.space/empty3/p/empty3/maven/maven")
            credentials {
                username = privateProperties.getProperty("spaceUsername")
                password = privateProperties.getProperty("spacePassword")
            }
        }
    }
}

signing {
    val keyId = privateProperties.getProperty("signing.keyId")
    val password = privateProperties.getProperty("signing.password") ?: privateProperties.getProperty("signing.passphrase")
    val secretKeyFile = privateProperties.getProperty("signing.secretKey")

    if (!secretKeyFile.isNullOrEmpty() && File(secretKeyFile).exists() && !password.isNullOrEmpty()) {
        val secretKeyText = File(secretKeyFile).readText()
        useInMemoryPgpKeys(keyId, secretKeyText, password)
        sign(publishing.publications)
    } else if (!keyId.isNullOrEmpty()) {
        useGpgCmd()
        sign(publishing.publications)
    }
}


