package com.example.giffy.data

import com.example.giffy.models.domain.SearchRequest
import com.example.giffy.models.data.SearchResultEntity

interface DataSource {

    suspend fun searchGiffs(request: SearchRequest): SearchResultEntity?
}