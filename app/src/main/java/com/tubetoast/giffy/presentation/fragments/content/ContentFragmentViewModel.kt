package com.tubetoast.giffy.presentation.fragments.content

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubetoast.giffy.domain.SearchInteractor
import com.tubetoast.giffy.models.data.NoContentException
import com.tubetoast.giffy.models.data.NoInternetException
import com.tubetoast.giffy.models.domain.SearchState
import com.tubetoast.giffy.models.presentation.Banners
import com.tubetoast.giffy.models.presentation.ContentItem
import com.tubetoast.giffy.models.presentation.GifPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContentFragmentViewModel(
    private val interactor: SearchInteractor,
    val bannerActions: BannerActions,
) : ViewModel() {

    val content: StateFlow<List<ContentItem>>
        get() = _content.asStateFlow().also {
            observeInteractor()
        }
    private val _content = MutableStateFlow<List<ContentItem>>(
        listOf(Banners.NotStarted(bannerActions))
    )

    private fun observeInteractor() {
        viewModelScope.launch {
            interactor.searchState.collectLatest { result ->
                when (result) {
                    is SearchState.Loading -> showShimmers()
                    is SearchState.Success -> showContent(result)
                    is SearchState.Error -> showBanner(result)
                    is SearchState.Forming -> Unit
                }
            }
        }
    }

    private fun showShimmers() {
        _content.value = List(DEFAULT_SHIMMERS_COUNT) { GifPreview.Shimmer }
    }

    private fun showContent(state: SearchState.Success) {
        _content.value = state.images.map { GifPreview.Content(it) }
    }

    private fun showBanner(state: SearchState.Error) {
        Log.e("Content fragment", state.exception.message.toString())
        when (state.exception) {
            is NoInternetException -> _content.value = listOf(
                Banners.NoInternet(bannerActions)
            )
            is NoContentException -> _content.value = listOf(
                Banners.NoContent(bannerActions)
            )
            else -> _content.value = listOf(
                Banners.UnknownError()
            )
        }
    }

    companion object {
        const val DEFAULT_SHIMMERS_COUNT = 3
    }
}
