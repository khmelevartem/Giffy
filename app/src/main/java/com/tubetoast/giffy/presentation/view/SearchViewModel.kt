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
                when (it) {
                    is SearchState.Loading -> _loadingQuery.emit(it.request.query)
                    is SearchState.Forming -> _formingQuery.emit(it.needsReset)
                    else -> Unit
                }
            }
        }
    }

    val loadingQuery get() = _loadingQuery.asSharedFlow()
    private val _loadingQuery = MutableSharedFlow<String>()

    val formingQuery get() = _formingQuery.asSharedFlow()
    private val _formingQuery = MutableSharedFlow<Boolean>()

    private var query = ""

    fun setCurrentQuery(newQuery: String) {
        query = newQuery
    }

    fun startFormingRequest(needsReset: Boolean = false) {
        interactor.startFormingSearch(needsReset)
    }

    fun search() {
        interactor.initSearch(SearchRequest(query, DEFAULT_LIMIT))
    }

    companion object {
        const val DEFAULT_LIMIT = 10
    }
}