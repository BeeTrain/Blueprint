package internal

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

@Suppress("UnstableApiUsage")
internal val Project.applicationExtension: ApplicationExtension
    get() = extensions.findByName("android") as ApplicationExtension

@Suppress("UnstableApiUsage")
internal val Project.libraryExtension: LibraryExtension
    get() = extensions.findByName("android") as LibraryExtension

internal val Project.coreModulesDirectory
    get() = "$rootDir/source/core"

internal val Project.featureModulesDirectory
    get() = "$rootDir/source/feature"