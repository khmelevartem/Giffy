package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult

interface SearchRepository {

    suspend fun search(request: SearchRequest): SearchResult

}