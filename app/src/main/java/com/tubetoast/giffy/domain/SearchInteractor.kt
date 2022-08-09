package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {

    suspend fun initSearch(query: String)

    val searchResult: Flow<SearchResult>
}