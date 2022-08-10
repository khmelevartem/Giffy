package com.tubetoast.giffy.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubetoast.giffy.domain.SearchInteractor
import kotlinx.coroutines.launch

class SearchViewModel(private val interactor: SearchInteractor) : ViewModel() {
    private var query = ""

    fun setCurrentQuery(newQuery: String) {
        query = newQuery
    }

    fun search() {
        viewModelScope.launch {
            interactor.initSearch(query)
        }
    }
}