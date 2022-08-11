package com.tubetoast.giffy.data

abstract class StackDataStorage<V> : DataStorage<Unit, List<V>> {

    final override suspend fun get(request: Unit): List<V> = peek(UNLIMITED)

    final override suspend fun put(request: Unit, value: List<V>) = pushAll(value)

    abstract suspend fun peek(limit: Int): List<V>

    abstract suspend fun push(vararg values: V)

    abstract suspend fun pushAll(values: List<V>)

    abstract suspend fun delete(value: V)

    companion object {
        const val UNLIMITED = 0
    }
}