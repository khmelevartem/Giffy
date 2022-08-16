package com.tubetoast.giffy.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubetoast.giffy.domain.SearchInteractor
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val interactor: SearchInteractor) : ViewModel() {

    init {
        viewModelScope.launch {
            interactor.searchState.collect {
                if (it is SearchState.Loading) loadingQuery(it.request)
            }
        }
    }

    val loadingQuery get() = _loadingQuery.asSharedFlow()
    private val _loadingQuery = MutableSharedFlow<String>()

    private var query = ""

    fun startFormingRequest() {
        interactor.startFormingSearch()
    }

    fun setCurrentQuery(newQuery: String) {
        query = newQuery
    }

    fun search(request: SearchRequest = SearchRequest(query, DEFAULT_LIMIT)) {
        interactor.initSearch(request)
    }

    private fun loadingQuery(request: SearchRequest) {
        viewModelScope.launch {
            _loadingQuery.emit(request.query)
        }
    }

    companion object {
        const val DEFAULT_LIMIT = 10
    }
}