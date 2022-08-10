package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchState
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {

    suspend fun initSearch(query: String)

    val searchState: Flow<SearchState>
}