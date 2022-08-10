package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SearchInteractorImpl(
    private val historyInteractor: HistoryInteractor,
    private val repository: SearchRepository,
) : SearchInteractor {

    override val searchState: SharedFlow<SearchState> get() = _searchResult.asSharedFlow()
    private val _searchResult =
        MutableSharedFlow<SearchState>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun initSearch(query: String) {
        _searchResult.emit(SearchState.Loading)
        val request = SearchRequest(query, DEFAULT_LIMIT)
        val result = repository.search(request)
        _searchResult.emit(result)
        historyInteractor.addToHistory(request, result)
    }

    companion object {
        const val DEFAULT_LIMIT = 10
    }
}