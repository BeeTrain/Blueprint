/* Project Settings */

rootProject.name = "Blueprint"
val moduleConfiguratorPath = "$rootDir/gradle/modules-configurator.gradle.kts"

// Attach modules
apply(from = File(moduleConfiguratorPath))

// Enable features
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Resolve dependencies
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        google()
        mavenCentral()
    }
}