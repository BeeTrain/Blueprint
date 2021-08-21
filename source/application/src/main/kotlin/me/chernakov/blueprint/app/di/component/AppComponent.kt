package me.chernakov.blueprint.app.di.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.chernakov.blueprint.AppDelegate
import me.chernakov.blueprint.app.di.module.AppModule
import me.chernakov.blueprint.app.di.module.ComponentHolderModule
import me.chernakov.blueprint.app.di.module.ContextModule
import me.chernakov.blueprint.app.di.module.FeatureManagerModule
import me.chernakov.blueprint.arch.di.scope.ApplicationScope

@Component(
    modules = [
        AppModule::class,
        ContextModule::class,
        FeatureManagerModule::class,
        ComponentHolderModule::class,
    ]
)
@ApplicationScope
abstract class AppComponent : AppProvider {

    companion object {

        fun init(application: Application): AppComponent {
            return DaggerAppComponent.builder().application(application).build()
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    abstract fun inject(application: Application)
}

@Suppress("Useless_cast")
fun Context.asAppProvider(): AppProvider {
    return (applicationContext as AppDelegate).appComponent as AppComponent
}