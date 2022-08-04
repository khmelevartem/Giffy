package com.tubetoast.giffy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubetoast.giffy.domain.SearchInteractor
import com.tubetoast.giffy.models.data.NoContentException
import com.tubetoast.giffy.models.data.NoInternetException
import com.tubetoast.giffy.models.domain.SearchResult
import com.tubetoast.giffy.models.presentation.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchFragmentViewModel(private val interactor: SearchInteractor) : ViewModel(), Preview.Banner.BannerListener {

    val previews: StateFlow<List<Preview>> get() = _previews.asStateFlow()
    private val _previews = MutableStateFlow<List<Preview>>(
        listOf(Preview.NotStartedBanner(this))
    )

    private var query = ""

    fun search() {
        showShimmers()
        viewModelScope.launch {
            val result = interactor.search(query)
            when (result) {
                is SearchResult.ListSearchResult -> showGifs(result)
                is SearchResult.SearchError -> showBanner(result)
            }
        }
    }

    private fun showShimmers() {
        _previews.value = List(DEFAULT_SHIMMERS_COUNT) { Preview.Shimmer }
    }

    private fun showGifs(result: SearchResult.ListSearchResult) {
        _previews.value = result.images.map { Preview.Content(it) }
    }

    private fun showBanner(result: SearchResult.SearchError) {
        when (result.exception) {
            is NoInternetException -> _previews.value = listOf(
                Preview.NoInternetBanner(this)
            )
            is NoContentException -> _previews.value = listOf(
                Preview.NoContentBanner(this)
            )
            else -> _previews.value = listOf(
                Preview.UnknownErrorBanner()
            )
        }
    }

    fun setCurrentQuery(newQuery: String) {
        query = newQuery
    }

    override fun onAction(type: String) {
        when (type) {
            Preview.NoContentBanner.ACTION_TYPE -> Unit // TODO(focus on search input field)
            Preview.NotStartedBanner.ACTION_TYPE -> Unit // TODO(focus on search input field)
            Preview.NoInternetBanner.ACTION_TYPE -> search()
            Preview.UnknownErrorBanner.ACTION_TYPE -> Unit //TODO(finish activity)
        }
    }

    companion object {
        const val DEFAULT_SHIMMERS_COUNT = 3
    }
}
