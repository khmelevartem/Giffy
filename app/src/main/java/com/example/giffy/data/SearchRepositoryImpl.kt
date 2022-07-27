package com.example.giffy.data

import com.example.giffy.domain.SearchRepository
import com.example.giffy.models.domain.SearchResult
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(
    private val networkSource: DataSource,
    private val localSource: DataSource,
    private val converter: SearchResultEntityConverter,
    private val dispatchers: CoroutineDispatchers,
) : SearchRepository {

    override suspend fun search(query: String, single: Boolean): SearchResult = try {
        searchInternal(query)?.let {
            converter.convert(it, single)
        } ?: SearchResult.EMPTY
    } catch (e: Exception) {
        SearchResult.SearchError(e)
    }

    private suspend fun searchInternal(query: String) =
        withContext(dispatchers.io) { localSource.get(query) ?: networkSource.get(query) }
}