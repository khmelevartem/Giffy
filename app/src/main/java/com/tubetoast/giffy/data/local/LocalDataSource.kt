package com.tubetoast.giffy.data.local

import com.tubetoast.giffy.data.CachedDataSource
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult
import com.tubetoast.giffy.utils.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LocalDataSource(
    private val database: SearchResultDatabase,
    private val converter: SearchResultRoomConverter,
    dispatchers: CoroutineDispatchers
) : CachedDataSource<SearchRequest, SearchResult> {

    private val scope = CoroutineScope(SupervisorJob() + dispatchers.io)

    override suspend fun getOrCreate(request: SearchRequest, creator: suspend () -> SearchResult): SearchResult =
        get(request) ?: creator().also { result ->
            converter.convert(request, result)?.let { dbEntity ->
                scope.launch { database.searchResults().insert(dbEntity) }
            }
        }

    override suspend fun get(request: SearchRequest): SearchResult? =
        database.searchResults().getByQuery(request.query)?.let {
            converter.reverse(it)
        }
}