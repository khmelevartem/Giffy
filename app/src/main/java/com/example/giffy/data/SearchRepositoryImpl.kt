package com.example.giffy.data

import com.example.giffy.domain.SearchRepository
import com.example.giffy.models.domain.SearchRequest
import com.example.giffy.models.domain.SearchResult
import com.example.giffy.utils.CoroutineDispatchers
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(
    private val networkSource: DataSource,
    private val localSource: DataSource,
    private val converter: SearchResultEntityConverter,
    private val dispatchers: CoroutineDispatchers,
) : SearchRepository {

    override suspend fun search(query: String, limit: Int): SearchResult = try {
        withContext(dispatchers.io) {
            val request = SearchRequest(query, limit)
            val result = localSource.searchGiffs(request) ?: networkSource.searchGiffs(request)
            converter.convert(result)
        }
    } catch (e: Exception) {
        SearchResult.SearchError(e)
    }
}