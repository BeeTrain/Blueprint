package me.chernakov.blueprint

import android.app.Application
import me.chernakov.blueprint.app.di.component.AppComponent
import me.chernakov.blueprint.app.di.component.AppComponent.Companion.init
import me.chernakov.blueprint.arch.di.FeaturesContainerHolder
import me.chernakov.blueprint.injector.FeaturesContainer
import javax.inject.Inject

class AppDelegate : Application(), FeaturesContainerHolder {

    @Inject
    override lateinit var featuresContainer: FeaturesContainer

    val appComponent: AppComponent by lazy { init(this) }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }
}