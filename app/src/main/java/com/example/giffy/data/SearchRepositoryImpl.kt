package com.example.giffy.data

import com.example.giffy.domain.SearchRepository
import com.example.giffy.models.domain.SearchRequest
import com.example.giffy.models.domain.SearchResult
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(
    private val networkSource: DataSource,
    private val localSource: DataSource,
    private val converter: SearchResultEntityConverter,
    private val dispatchers: CoroutineDispatchers,
) : SearchRepository {

    override suspend fun search(query: String, single: Boolean): SearchResult = try {
        searchInternal(query, single)?.let { result ->
            converter.convert(result, single)
        } ?: SearchResult.EMPTY
    } catch (e: Exception) {
        SearchResult.SearchError(e)
    }

    private suspend fun searchInternal(query: String, single: Boolean) =
        withContext(dispatchers.io) {
            val request = SearchRequest(
                query = query,
                limit = if (single) 1 else DEFAULT_LIMIT
            )
            localSource.searchGiffs(request) ?: networkSource.searchGiffs(request)
        }

    private companion object {
        const val DEFAULT_LIMIT = 10
    }
}