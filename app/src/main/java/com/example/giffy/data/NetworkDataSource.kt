package com.example.giffy.data

import com.example.giffy.models.data.SearchResultEntity
import com.example.giffy.models.domain.SearchRequest

class NetworkDataSource(private val api: GiphyApi) : DataSource {

    override suspend fun searchGiffs(request: SearchRequest): SearchResultEntity? =
        request.run { api.searchGiffs(query, limit) }.run {
            if (code() == 200) body()
            else throw RuntimeException(code().toString())
        }
    // TODO(адекватная обработка результатов)
}