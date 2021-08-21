package plugin

import AndroidXDependencies
import BuildTypes
import Config
import DIDependencies.applyDagger
import FirebaseDependencies
import Plugins
import SourceSets
import internal.applicationExtension
import internal.coreModulesDirectory
import internal.featureModulesDirectory
import internal.getGitVersionCode
import internal.getGitVersionName
import internal.implementation
import internal.isGradleProjectDir
import javaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import java.io.File

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
            apply(plugin = Plugins.kotlinAndroidModule)
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
            // Base modules
            File(coreModulesDirectory).listFiles()?.forEach { baseModule ->
                if (baseModule.isDirectory && baseModule.isGradleProjectDir) {
                    implementation(project(":${baseModule.name}"))
                }
            }

            // Feature modules
            File(featureModulesDirectory).listFiles()?.forEach { featureModule ->
                if (featureModule.isDirectory && featureModule.isGradleProjectDir) {
                    implementation(project(":${featureModule.name}"))
                }
            }

            FirebaseDependencies.applyAll(this)
            AndroidXDependencies.applyAll(this)
            applyDagger()
        }
    }
}