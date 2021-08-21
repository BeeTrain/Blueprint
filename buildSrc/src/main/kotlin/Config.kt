object Config {
    const val applicationId = "me.chernakov.blueprint"

    const val compileSdkVersion = 30
    const val minSdkVersion = 24
    const val targetSdkVersion = 30

    const val buildToolsVersion = "30.0.2"

}

enum class BuildTypes(val title: String, val isMinifyEnabled: Boolean) {
    DEBUG(title = "debug", isMinifyEnabled = false),
    RELEASE(title = "release", isMinifyEnabled = true)
}

enum class SourceSets(val title: String, val path: String) {
    MAIN(title = "main", path = "src/main/kotlin")
}