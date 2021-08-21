package plugin

import AndroidXDependencies
import BuildTypes
import Config
import FirebaseDependencies
import Plugins
import SourceSets
import internal.applicationExtension
import internal.getGitVersionCode
import internal.getGitVersionName
import javaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ApplicationModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applyApplicationConfig()
            applyDependencies()
        }
    }

    private fun Project.applyPlugins() {
        plugins.apply {
            apply(plugin = Plugins.androidApplication)
            apply(plugin = Plugins.kotlinModule)
            apply(plugin = Plugins.googleServices)
        }
    }

    private fun Project.applyApplicationConfig() {
        applicationExtension.apply {
            compileSdk = Config.compileSdkVersion
            buildToolsVersion = Config.buildToolsVersion

            defaultConfig {
                applicationId = Config.applicationId
                minSdk = Config.minSdkVersion
                targetSdk = Config.targetSdkVersion
                versionCode = getGitVersionCode()
                versionName = getGitVersionName()
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

    private fun Project.applyDependencies() {
        dependencies {
            FirebaseDependencies.applyAll(this)
            AndroidXDependencies.applyAll(this)
        }
    }
}