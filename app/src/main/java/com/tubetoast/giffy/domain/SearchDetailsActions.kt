package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest

sealed class SearchDetailsActions

class RequestActions(
    private val historyInteractor: HistoryInteractor,
    private val searchInteractor: SearchInteractor,
) {

    fun repeatRequest(request: SearchRequest) {
        searchInteractor.initSearch(request)
    }

    fun deleteRequest(request: SearchRequest) {
        historyInteractor.deleteFromHistory(request)
    }
}