package com.tubetoast.giffy.data.local

import com.tubetoast.giffy.data.CachedDataSource
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult

class LocalDataSource(
    private val database: SearchResultDatabase,
    private val converter: SearchResultRoomConverter,
) : CachedDataSource<SearchRequest, SearchResult> {

    override suspend fun getOrCreate(request: SearchRequest, creator: suspend () -> SearchResult): SearchResult =
        get(request) ?: creator().also { result ->
            converter.convert(request, result)?.let { dbEntity ->
                database.searchResults().insert(dbEntity)
            }
        }

    override suspend fun get(request: SearchRequest): SearchResult? =
        database.searchResults().getByQuery(request.query)?.let {
            converter.reverse(it)
        }
}