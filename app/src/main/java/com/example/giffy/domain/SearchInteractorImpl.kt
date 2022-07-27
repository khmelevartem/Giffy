package com.example.giffy.domain

import com.example.giffy.models.domain.SearchResult

class SearchInteractorImpl(
    private val historyInteractor: HistoryInteractor,
    private val repository: SearchRepository,
) : SearchInteractor {

    override suspend fun searchMostRelevant(query: String): SearchResult =
        repository.search(query, single = true).also { result ->
            addToHistoryIfNoErrors(result, query)
        }

    override suspend fun searchAll(query: String): SearchResult =
        repository.search(query).also { result ->
            addToHistoryIfNoErrors(result, query)
        }

    private fun addToHistoryIfNoErrors(it: SearchResult, query: String) {
        if (it !is SearchResult.SearchError) historyInteractor.addToHistory(query)
    }
}