package me.chernakov.blueprint.app.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ContextModule {

    @Binds
    abstract fun bindApplicationContext(application: Application): Context
}