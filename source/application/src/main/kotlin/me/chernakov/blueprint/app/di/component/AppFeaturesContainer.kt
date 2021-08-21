package me.chernakov.blueprint.app.di.component

import me.chernakov.blueprint.arch.di.FeatureHolderManager
import me.chernakov.blueprint.arch.di.scope.ApplicationScope
import me.chernakov.blueprint.injector.FeaturesContainer
import javax.inject.Inject

@ApplicationScope
class AppFeaturesContainer @Inject constructor(
    private val featureHolderManager: FeatureHolderManager
) : FeaturesContainer {

    @Suppress("Useless_cast")
    override fun <T> getFeature(key: Class<*>): T {
        return featureHolderManager.getFeature<T>(key) as T
    }

    override fun releaseFeature(key: Class<*>) {
        featureHolderManager.releaseFeature(key)
    }
}