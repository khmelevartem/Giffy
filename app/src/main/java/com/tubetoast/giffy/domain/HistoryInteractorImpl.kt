package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
) : HistoryInteractor {

    override fun getLast(number: Int): Flow<List<SearchRequest>> =
        historyRepository.getLast(number)

    override fun addToHistory(request: SearchRequest, state: SearchState) {
        if (state is SearchState.Success) {
            historyRepository.addToHistory(request)
        }
    }

    override fun deleteFromHistory(request: SearchRequest) {
        historyRepository.deleteFromHistory(request)
    }
}