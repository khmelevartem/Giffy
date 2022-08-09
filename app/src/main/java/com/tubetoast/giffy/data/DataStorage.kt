package com.tubetoast.giffy.data

interface DataStorage<K, V> : DataSource<K, V> {
    suspend fun put(request: K, value: V)
}