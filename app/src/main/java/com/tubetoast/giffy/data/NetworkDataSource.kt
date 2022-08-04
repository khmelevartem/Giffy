package com.tubetoast.giffy.data

import com.tubetoast.giffy.models.data.NoInternetException
import com.tubetoast.giffy.models.data.SearchResultEntity
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.utils.ConnectionChecker

class NetworkDataSource(
    private val api: GiphyApi,
    private val connectionChecker: ConnectionChecker,
) : DataSource {

    override suspend fun searchGiffs(request: SearchRequest): SearchResultEntity? =
        if (!connectionChecker.isConnected()) {
            throw NoInternetException
        } else {
            // TODO(адекватная обработка результатов)
            api.searchGiffs(request.query, request.limit).run {
                if (code() == STATUS_CODE_OK) body()
                else throw RuntimeException(code().toString())
            }
        }

    private companion object {
        const val STATUS_CODE_OK = 200
    }
}