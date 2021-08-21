package me.chernakov.blueprint.app.di.module

import dagger.Binds
import dagger.Module
import me.chernakov.blueprint.app.di.component.AppFeaturesContainer
import me.chernakov.blueprint.injector.FeaturesContainer

@Module
abstract class AppModule {

    @Binds
    abstract fun bindFeaturesContainer(container: AppFeaturesContainer): FeaturesContainer
}