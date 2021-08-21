import internal.implementation
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.dsl.DependencyHandler

val javaVersion = JavaVersion.VERSION_1_8
const val kotlinVersion = "1.5.21"

object Plugins {
    const val applicationModule = "application-module"
    const val androidModule = "android-module"
    const val kotlinModule = "kotlin-module"

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val androidKotlin = "kotlin-android"
    const val googleServices = "com.google.gms.google-services"
}

object FirebaseDependencies {
    object Versions {
        const val firebaseBom = "28.4.0"
    }

    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val analyticsKtx = "com.google.firebase:firebase-analytics-ktx"

    fun applyAll(dependencyHandler: DependencyHandler) = dependencyHandler.apply {
        implementation(platform(firebaseBom))
        implementation(analyticsKtx)
    }
}

object AndroidXDependencies {
    object Versions {
        const val coreKtx = "1.6.0"
        const val material = "1.4.0"
        const val constraint = "2.1.0"
    }

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"

    fun applyAll(dependencyHandler: DependencyHandler) = dependencyHandler.apply {
        implementation(coreKtx)
        implementation(material)
        implementation(constraint)
    }
}

object KotlinDependencies {

    const val stdLib =  "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"

    fun DependencyHandler.defaultModuleLibs() = apply {
        implementation(stdLib)
    }
}