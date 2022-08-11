package com.tubetoast.giffy.data.local

import com.tubetoast.giffy.data.CachedDataSource
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import com.tubetoast.giffy.utils.CoroutineDispatchers
import com.tubetoast.giffy.utils.SupervisorScope
import kotlinx.coroutines.launch

class LocalDataSource(
    private val database: SearchResultDatabase,
    private val converter: SearchResultRoomConverter,
    dispatchers: CoroutineDispatchers
) : CachedDataSource<SearchRequest, SearchState> {

    private val scope = SupervisorScope(dispatchers.io)

    override suspend fun getOrCreate(request: SearchRequest, creator: suspend () -> SearchState): SearchState =
        get(request) ?: creator().also { result ->
            converter.convert(request, result)?.let { dbEntity ->
                scope.launch { database.searchResults().insert(dbEntity) }
            }
        }

    override suspend fun get(request: SearchRequest): SearchState? =
        database.searchResults().getByQuery(request.query)?.let {
            converter.reverse(it)
        }
}