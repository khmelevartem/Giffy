package com.example.giffy.data

import com.example.giffy.models.domain.SearchRequest
import com.example.giffy.models.data.SearchResultEntity

class LocalDataSource : DataSource {
    override suspend fun searchGiffs(request: SearchRequest): SearchResultEntity? {
        TODO("Not yet implemented")
    }
}