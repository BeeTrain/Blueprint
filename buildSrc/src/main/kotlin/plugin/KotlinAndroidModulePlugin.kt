package plugin

import KotlinDependencies
import Plugins
import internal.implementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinAndroidModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            setupTasks()
            applyDependencies()
        }
    }

    private fun Project.applyPlugins() {
        plugins.run {
            apply(Plugins.androidKotlin)
            apply(Plugins.kotlinKapt)
        }
    }

    private fun Project.setupTasks() {
        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            implementation(KotlinDependencies.stdLib)
        }
    }
}