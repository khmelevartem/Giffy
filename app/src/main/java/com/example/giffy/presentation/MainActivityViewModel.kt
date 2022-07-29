package com.example.giffy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giffy.domain.SearchInteractor
import com.example.giffy.models.domain.Gif
import com.example.giffy.models.domain.SearchResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val interactor: SearchInteractor) : ViewModel() {

    val result: StateFlow<List<Gif>> get() = _result.asStateFlow()
    private val _result = MutableStateFlow<List<Gif>>(emptyList())

    val alert: SharedFlow<String> get() = _alert.asSharedFlow()
    private val _alert = MutableSharedFlow<String>()

    fun search(query: String) {
        viewModelScope.launch {
            val result = interactor.search(query)
            when (result) {
                is SearchResult.ListSearchResult -> showGifs(result)
                is SearchResult.EMPTY -> showAlert("Empty result")
                is SearchResult.SearchError -> showAlert(result.exception.message)
            }
        }
    }

    private fun showGifs(result: SearchResult.ListSearchResult) {
        _result.value = result.images
    }

    private fun showAlert(string: String?) {
        viewModelScope.launch {
            _alert.emit(string ?: DEFAULT_ERROR_MESSAGE)
        }
    }

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "some error"
    }
}
