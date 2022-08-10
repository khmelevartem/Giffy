package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
) : HistoryInteractor {
    override fun addToHistory(request: SearchRequest, state: SearchState) {
        if (state is SearchState.Success) {
            historyRepository.addToHistory(request)
        }
    }

    override suspend fun getLast(number: Int): List<SearchRequest> =
        historyRepository.getLast(number)
}