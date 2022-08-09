package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
) : HistoryInteractor {
    override fun addToHistory(request: SearchRequest, result: SearchResult) {
        if (result !is SearchResult.SearchError) {
            historyRepository.addToHistory(request)
        }
    }

    override suspend fun getLast(number: Int): List<SearchRequest> =
        historyRepository.getLast(number)
}