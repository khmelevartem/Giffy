package com.tubetoast.giffy.data

import com.tubetoast.giffy.domain.SearchRepository
import com.tubetoast.giffy.models.data.NoContentException
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import com.tubetoast.giffy.utils.CoroutineDispatchers
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(
    private val networkSource: DataSource<SearchRequest, SearchState>,
    private val localCache: CachedDataSource<SearchRequest, SearchState>,
    private val dispatchers: CoroutineDispatchers,
) : SearchRepository {

    override suspend fun search(request: SearchRequest): SearchState = try {
        withContext(dispatchers.io) {
            localCache.getOrCreate(request) {
                networkSource.get(request) ?: throw NoContentException
            }
        }
    } catch (e: Exception) {
        SearchState.Error(e)
    }
}