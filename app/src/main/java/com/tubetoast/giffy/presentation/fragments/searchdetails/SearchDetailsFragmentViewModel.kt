package com.tubetoast.giffy.presentation.fragments.searchdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubetoast.giffy.domain.HistoryInteractor
import com.tubetoast.giffy.domain.SearchInteractor
import com.tubetoast.giffy.models.presentation.SearchDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SearchDetailsFragmentViewModel(
    private val searchInteractor: SearchInteractor,
    private val historyInteractor: HistoryInteractor,
) : ViewModel() {

    private val _filters = MutableStateFlow<List<String>>(emptyList())
    val filters get() = _filters.asStateFlow()

    suspend fun getHistory(): StateFlow<List<SearchDetail>> =
        historyInteractor.getLast()
            .map { it.map { request -> SearchDetail.Request(request) } }
            .stateIn(viewModelScope)
}