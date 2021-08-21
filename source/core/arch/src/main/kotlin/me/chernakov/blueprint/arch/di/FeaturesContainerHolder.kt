package me.chernakov.blueprint.arch.di

import android.content.Context
import androidx.fragment.app.Fragment
import me.chernakov.blueprint.injector.FeaturesContainer

interface FeaturesContainerHolder {
    val featuresContainer: FeaturesContainer
}

fun Context.getFeaturesContainerHolder(): FeaturesContainer {
    return (this.applicationContext as? FeaturesContainerHolder)?.featuresContainer
        ?: throw IllegalArgumentException("Application must implement FeaturesContainerHolder")
}

fun Fragment.getFeaturesContainerHolder(): FeaturesContainer {
    return this.requireActivity().getFeaturesContainerHolder()
}