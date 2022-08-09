package com.tubetoast.giffy.data

import com.tubetoast.giffy.domain.SearchRepository
import com.tubetoast.giffy.models.data.NoContentException
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult
import com.tubetoast.giffy.utils.CoroutineDispatchers
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(
    private val networkSource: DataSource<SearchRequest, SearchResult>,
    private val localCache: CachedDataSource<SearchRequest, SearchResult>,
    private val dispatchers: CoroutineDispatchers,
) : SearchRepository {

    override suspend fun search(request: SearchRequest): SearchResult = try {
        withContext(dispatchers.io) {
            localCache.getOrCreate(request) {
                networkSource.get(request) ?: throw NoContentException
            }
        }
    } catch (e: Exception) {
        SearchResult.SearchError(e)
    }
}