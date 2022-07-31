package com.example.giffy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giffy.domain.SearchInteractor
import com.example.giffy.models.domain.SearchResult
import com.example.giffy.models.presentation.Preview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchFragmentViewModel(private val interactor: SearchInteractor) : ViewModel() {

    val previews: StateFlow<List<Preview>> get() = _previews.asStateFlow()
    private val _previews = MutableStateFlow<List<Preview>>(emptyList())

    val alert: SharedFlow<String> get() = _alert.asSharedFlow()
    private val _alert = MutableSharedFlow<String>()

    private var query = ""

    fun search() {
        showShimmers()
        viewModelScope.launch {
            val result = interactor.search(query)
            when (result) {
                is SearchResult.ListSearchResult -> showGifs(result)
                is SearchResult.EMPTY -> showAlert("Empty result")
                is SearchResult.SearchError -> showAlert(result.exception.message)
            }
        }
    }

    private fun showShimmers() {
        _previews.value = List(DEFAULT_SHIMMERS_COUNT) { Preview.Shimmer() }
    }

    private fun showGifs(result: SearchResult.ListSearchResult) {
        _previews.value = result.images.map { Preview.Content(it) }
    }

    private fun showAlert(string: String?) {
        viewModelScope.launch {
            _alert.emit(string ?: DEFAULT_ERROR_MESSAGE)
        }
    }

    fun setCurrentQuery(newQuery: String) {
        query = newQuery
    }

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "some error"
        const val DEFAULT_SHIMMERS_COUNT = 3
    }
}
