package com.tubetoast.giffy.data

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.data.SearchResultEntity

interface DataSource {

    suspend fun searchGiffs(request: SearchRequest): SearchResultEntity?
}