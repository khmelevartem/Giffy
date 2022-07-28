package com.example.giffy.data

import com.example.giffy.models.domain.SearchRequest
import com.example.giffy.models.data.SearchResultEntity

class NetworkDataSource(private val api: GiphyApi) : DataSource {

    override suspend fun searchGiffs(request: SearchRequest): SearchResultEntity? =
        request.run { api.searchGiffs(query, limit) }.body()
}