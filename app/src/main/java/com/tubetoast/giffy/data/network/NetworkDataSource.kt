package com.tubetoast.giffy.data.network

import com.tubetoast.giffy.data.DataSource
import com.tubetoast.giffy.models.data.NoContentException
import com.tubetoast.giffy.models.data.NoInternetException
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult
import com.tubetoast.giffy.utils.ConnectionChecker

class NetworkDataSource(
    private val api: GiphyApi,
    private val connectionChecker: ConnectionChecker,
    private val converter: SearchResultApiConverter,
) : DataSource<SearchRequest, SearchResult> {

    override suspend fun get(request: SearchRequest): SearchResult? =
        if (!connectionChecker.isConnected()) {
            throw NoInternetException
        } else {
            // TODO(адекватная обработка результатов)
            api.searchGiffs(request.query, request.limit).run {
                if (code() == STATUS_CODE_OK) converter.convert(body())
                else throw RuntimeException(code().toString())
            } ?: throw NoContentException
        }

    private companion object {
        const val STATUS_CODE_OK = 200
    }
}