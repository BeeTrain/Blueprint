buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

val kotlinVersion = "1.5.21"
val googleServicesVersion = "4.3.10"
val buildToolsVersion = "7.0.1"

dependencies {
    compileOnly(gradleApi())

    implementation("com.google.gms:google-services:$googleServicesVersion")
    implementation("com.android.tools.build:gradle:$buildToolsVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
}

gradlePlugin {
    plugins {
        register("kotlin-module") {
            id = "kotlin-module"
            description = "Gradle plugin for kotlin module."
            implementationClass = "plugin.KotlinModulePlugin"
        }
        register("application-module") {
            id = "application-module"
            description = "Gradle plugin for android application module."
            implementationClass = "plugin.ApplicationModulePlugin"
        }
        register("android-module") {
            id = "android-module"
            description = "Gradle plugin for android library module."
            implementationClass = "plugin.AndroidModulePlugin"
        }
    }
}