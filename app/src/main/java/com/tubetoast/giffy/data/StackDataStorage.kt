package com.tubetoast.giffy.data

abstract class StackDataStorage<V> : DataStorage<Unit, List<V>> {

    final override suspend fun get(request: Unit): List<V>? = get(UNLIMITED)

    final override suspend fun put(request: Unit, value: List<V>) = putAll(value)

    abstract suspend fun get(limit: Int): List<V>?

    abstract suspend fun put(vararg values: V)

    abstract suspend fun putAll(values: List<V>)

    companion object {
        const val UNLIMITED = 0
    }
}