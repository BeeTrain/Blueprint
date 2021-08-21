package plugin

import BuildTypes
import Config
import Plugins
import SourceSets
import internal.libraryExtension
import javaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class AndroidModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applyLibraryConfig()
        }
    }

    private fun Project.applyPlugins() {
        apply(plugin = Plugins.androidLibrary)
        apply(plugin = Plugins.kotlinAndroidModule)
    }

    private fun Project.applyLibraryConfig() {
        libraryExtension.apply {
            compileSdk = Config.compileSdkVersion

            defaultConfig {
                minSdk = Config.minSdkVersion
                targetSdk = Config.targetSdkVersion
            }

            buildTypes {
                BuildTypes.values().forEach { buildType ->
                    getByName(buildType.title) {
                        isMinifyEnabled = buildType.isMinifyEnabled
                    }
                }
            }

            SourceSets.values().forEach { sourceSet ->
                sourceSets.named(sourceSet.title).configure {
                    java.srcDirs(sourceSet.path)
                }
            }

            compileOptions {
                sourceCompatibility = javaVersion
                targetCompatibility = javaVersion
            }
        }
    }
}