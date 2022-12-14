package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {

    fun startFormingSearch(cancelPrevious: Boolean = false)

    fun initSearch(request: SearchRequest)

    fun reset()

    val searchState: Flow<SearchState>
}