package com.tubetoast.giffy.data

import com.tubetoast.giffy.domain.SearchRepository
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult
import com.tubetoast.giffy.utils.CoroutineDispatchers
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(
    private val networkSource: DataSource,
    private val localSource: DataSource,
    private val converter: SearchResultEntityConverter,
    private val dispatchers: CoroutineDispatchers,
) : SearchRepository {

    override suspend fun search(request: SearchRequest): SearchResult = try {
        withContext(dispatchers.io) {
            val result = localSource.searchGiffs(request) ?: networkSource.searchGiffs(request)
            converter.convert(result)
        }
    } catch (e: Exception) {
        SearchResult.SearchError(e)
    }
}