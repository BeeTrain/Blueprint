package me.chernakov.blueprint.arch.di.exception

class HolderNotFoundException(key: Class<*>) : Exception("Holder with key ${key.simpleName} not found")