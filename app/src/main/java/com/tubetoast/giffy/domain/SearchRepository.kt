package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState

interface SearchRepository {

    suspend fun search(request: SearchRequest): SearchState

}