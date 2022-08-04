package com.tubetoast.giffy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubetoast.giffy.domain.SearchInteractor
import com.tubetoast.giffy.models.data.NoContentException
import com.tubetoast.giffy.models.data.NoInternetException
import com.tubetoast.giffy.models.domain.SearchResult
import com.tubetoast.giffy.models.presentation.Banner
import com.tubetoast.giffy.models.presentation.Banners
import com.tubetoast.giffy.models.presentation.GifPreview
import com.tubetoast.giffy.models.presentation.UIItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchFragmentViewModel(private val interactor: SearchInteractor) : ViewModel(), Banner.BannerListener {

    val previews: StateFlow<List<UIItem>> get() = _previews.asStateFlow()
    private val _previews = MutableStateFlow<List<UIItem>>(
        listOf(Banners.NotStarted(this))
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
        _previews.value = List(DEFAULT_SHIMMERS_COUNT) { GifPreview.Shimmer }
    }

    private fun showGifs(result: SearchResult.ListSearchResult) {
        _previews.value = result.images.map { GifPreview.Content(it) }
    }

    private fun showBanner(result: SearchResult.SearchError) {
        when (result.exception) {
            is NoInternetException -> _previews.value = listOf(
                Banners.NoInternet(this)
            )
            is NoContentException -> _previews.value = listOf(
                Banners.NoContent(this)
            )
            else -> _previews.value = listOf(
                Banners.UnknownError()
            )
        }
    }

    fun setCurrentQuery(newQuery: String) {
        query = newQuery
    }

    override fun onAction(type: String) {
        when (type) {
            Banners.NoContent.ACTION_TYPE -> Unit // TODO(focus on search input field)
            Banners.NotStarted.ACTION_TYPE -> Unit // TODO(focus on search input field)
            Banners.NoInternet.ACTION_TYPE -> search()
            Banners.UnknownError.ACTION_TYPE -> Unit //TODO(finish activity)
        }
    }

    companion object {
        const val DEFAULT_SHIMMERS_COUNT = 3
    }
}
