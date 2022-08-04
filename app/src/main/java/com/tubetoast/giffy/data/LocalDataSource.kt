package com.tubetoast.giffy.data

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.data.SearchResultEntity

class LocalDataSource : DataSource {
    override suspend fun searchGiffs(request: SearchRequest): SearchResultEntity? {
        //TODO("Not yet implemented")
        return null
    }
}