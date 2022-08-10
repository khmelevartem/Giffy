package com.tubetoast.giffy.presentation.fragments.searchdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubetoast.giffy.domain.HistoryInteractor
import com.tubetoast.giffy.domain.SearchInteractor
import com.tubetoast.giffy.models.domain.SearchRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchDetailsFragmentViewModel(
    private val searchInteractor: SearchInteractor,
    private val historyInteractor: HistoryInteractor,
) : ViewModel() {

    private val _history = MutableStateFlow<List<SearchRequest>>(emptyList())
    val history get() = _history.asStateFlow()

    private val _filters = MutableStateFlow<List<String>>(emptyList())
    val filters get() = _filters.asStateFlow()

    init {
        viewModelScope.launch {
            _history.value = historyInteractor.getLast()
        }
    }

}