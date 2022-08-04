package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchResult

interface SearchInteractor {

    suspend fun search(query: String): SearchResult
}