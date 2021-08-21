package me.chernakov.blueprint.injector

interface ComponentHolder<C : BaseApi, D : BaseDependencies> {

    fun init(dependencies: D)

    fun init()

    fun get(): C

    fun reset()
}

interface BaseDependencies

interface BaseApi