import DIDependencies.applyDagger

plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(projects.moduleInjector)

    applyDagger()
    AndroidXDependencies.applyAll(this)
}