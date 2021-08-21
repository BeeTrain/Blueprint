package me.chernakov.blueprint.app.di.module

import dagger.Module
import dagger.Provides
import me.chernakov.blueprint.arch.di.FeatureHolderManager
import me.chernakov.blueprint.arch.di.scope.ApplicationScope
import me.chernakov.blueprint.injector.ComponentHolder

@Module
class FeatureManagerModule {

    @ApplicationScope
    @Provides
    fun provideFeatureHolderManager(
        featureApiHolderMap: @JvmSuppressWildcards Map<Class<*>, ComponentHolder<*, *>>
    ): FeatureHolderManager {
        return FeatureHolderManager(featureApiHolderMap)
    }
}