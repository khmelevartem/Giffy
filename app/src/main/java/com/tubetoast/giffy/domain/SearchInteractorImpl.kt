package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult

class SearchInteractorImpl(
    private val historyInteractor: HistoryInteractor,
    private val repository: SearchRepository,
) : SearchInteractor {

    override suspend fun search(query: String): SearchResult =
        SearchRequest(query, DEFAULT_LIMIT).let {
            repository.search(it).also { result ->
                historyInteractor.addToHistory(it, result)
            }
        }

    companion object {
        const val DEFAULT_LIMIT = 10
    }
}