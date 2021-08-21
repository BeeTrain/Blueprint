package me.chernakov.blueprint.injector

interface FeaturesContainer {

    fun <T> getFeature(key: Class<*>): T

    fun releaseFeature(key: Class<*>)
}