package com.tubetoast.giffy.data

interface CachedDataSource<K, V> : DataSource<K, V> {

    suspend fun getOrCreate(request: K, creator: suspend (K) -> V): V
}