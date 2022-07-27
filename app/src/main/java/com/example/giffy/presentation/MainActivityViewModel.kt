package com.example.giffy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giffy.domain.SearchInteractor
import com.example.giffy.models.domain.SearchResult
import kotlinx.coroutines.launch

class MainActivityViewModel(private val interactor: SearchInteractor) : ViewModel() {

    val result: LiveData<SearchResult> get() = _result
    private val _result = MutableLiveData<SearchResult>()

    fun search(query: String) {
        viewModelScope.launch {
            _result.value = interactor.searchMostRelevant(query)
        }
    }
}
