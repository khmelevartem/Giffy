package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {

    fun startFormingSearch()

    fun initSearch(request: SearchRequest)

    val searchState: Flow<SearchState>
}