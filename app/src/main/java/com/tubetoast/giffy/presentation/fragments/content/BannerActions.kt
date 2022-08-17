package com.tubetoast.giffy.presentation.fragments.content

import com.tubetoast.giffy.models.presentation.Banner
import com.tubetoast.giffy.models.presentation.Banners
import com.tubetoast.giffy.utils.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BannerActions(dispatchers: CoroutineDispatchers) : Banner.BannerListener {

    val focusOnSearch: SharedFlow<Unit> get() = _focusOnSearch.asSharedFlow()
    private val _focusOnSearch = MutableSharedFlow<Unit>(replay = 0)

    val doSearch: SharedFlow<Unit> get() = _doSearch.asSharedFlow()
    private val _doSearch = MutableSharedFlow<Unit>(replay = 0)

    val finish: SharedFlow<Unit> get() = _finish.asSharedFlow()
    private val _finish = MutableSharedFlow<Unit>(replay = 0)

    private val scope = CoroutineScope(Job() + dispatchers.default)

    override fun onBannerAction(type: String) {
        scope.launch {
            when (type) {
                Banners.NoContent.ACTION_TYPE -> _focusOnSearch.emit(Unit)
                Banners.NotStarted.ACTION_TYPE -> _focusOnSearch.emit(Unit)
                Banners.NoInternet.ACTION_TYPE -> _doSearch.emit(Unit)
                Banners.UnknownError.ACTION_TYPE -> _finish.emit(Unit)
            }
        }
    }
}