package me.chernakov.blueprint.arch.di

import me.chernakov.blueprint.arch.di.exception.HolderNotFoundException
import me.chernakov.blueprint.injector.ComponentHolder

class FeatureHolderManager(
    private val featureHolders: Map<Class<*>, ComponentHolder<*, *>>
) {

    @Suppress("Unchecked_cast")
    fun <T> getFeature(key: Class<*>): T {
        val featureApiHolder = featureHolders[key] ?: throw HolderNotFoundException(key)
        return featureApiHolder.get() as T
    }

    fun releaseFeature(key: Class<*>) {
        val featureApiHolder = featureHolders[key] ?: throw HolderNotFoundException(key)
        featureApiHolder.reset()
    }
}