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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContentFragmentViewModel(private val interactor: SearchInteractor) : ViewModel(), Banner.BannerListener {

    val content: StateFlow<List<UIItem>>
        get() = _content.asStateFlow().also {
            observeInteractor()
        }
    private val _content = MutableStateFlow<List<UIItem>>(
        listOf(Banners.NotStarted(this))
    )

    private fun observeInteractor() {
        viewModelScope.launch {
            interactor.searchResult.collectLatest { result ->
                when (result) {
                    is SearchResult.Loading -> showShimmers()
                    is SearchResult.ListSearchResult -> showContent(result)
                    is SearchResult.SearchError -> showBanner(result)
                }
            }
        }
    }

    private fun showShimmers() {
        _content.value = List(DEFAULT_SHIMMERS_COUNT) { GifPreview.Shimmer }
    }

    private fun showContent(result: SearchResult.ListSearchResult) {
        _content.value = result.images.map { GifPreview.Content(it) }
    }

    private fun showBanner(result: SearchResult.SearchError) {
        when (result.exception) {
            is NoInternetException -> _content.value = listOf(
                Banners.NoInternet(this)
            )
            is NoContentException -> _content.value = listOf(
                Banners.NoContent(this)
            )
            else -> _content.value = listOf(
                Banners.UnknownError()
            )
        }
    }

    override fun onBannerAction(type: String) {
        when (type) {
            Banners.NoContent.ACTION_TYPE -> Unit // TODO(focus on search input field)
            Banners.NotStarted.ACTION_TYPE -> Unit // TODO(focus on search input field)
            Banners.NoInternet.ACTION_TYPE -> Unit //TODO(search)
            Banners.UnknownError.ACTION_TYPE -> Unit //TODO(finish activity)
        }
    }

    companion object {
        const val DEFAULT_SHIMMERS_COUNT = 3
    }
}
