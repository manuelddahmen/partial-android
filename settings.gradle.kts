pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://repo.maven.apache.org/maven2/") }
        maven { url = java.net.URI("https://s01.oss.sonatype.org/content/repositories/snapshots/") }
        maven { url = java.net.URI("https://repository.sonatype.org/content/groups/public/") }
        maven { url = java.net.URI("https://mvn.jzy3d.org/releases/") }
    }
}

rootProject.name = "partial-android"
include(":partialandroid")
