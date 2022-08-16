package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import com.tubetoast.giffy.utils.CoroutineDispatchers
import com.tubetoast.giffy.utils.SupervisorScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchInteractorImpl(
    private val historyInteractor: HistoryInteractor,
    private val repository: SearchRepository,
    dispatchers: CoroutineDispatchers,
) : SearchInteractor {

    private val scope = SupervisorScope(dispatchers.default)

    override val searchState: SharedFlow<SearchState> get() = _searchState.asSharedFlow()
    private val _searchState =
        MutableSharedFlow<SearchState>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun startFormingSearch() {
        scope.launch {
            _searchState.emit(SearchState.Forming)
        }
    }

    override fun initSearch(request: SearchRequest) {
        scope.coroutineContext.cancelChildren()
        scope.launch {
            _searchState.emit(SearchState.Loading(request))
            val result = repository.search(request)
            _searchState.emit(result)
            historyInteractor.addToHistory(request, result)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun reset() {
        scope.coroutineContext.cancelChildren()
        _searchState.resetReplayCache()
    }
}