package com.tubetoast.giffy.data

interface DataSource<K, V> {

    suspend fun get(request: K): V?
}