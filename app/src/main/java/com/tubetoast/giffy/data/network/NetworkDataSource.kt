package com.tubetoast.giffy.data.network

import com.tubetoast.giffy.data.DataSource
import com.tubetoast.giffy.models.data.NetworkException
import com.tubetoast.giffy.models.data.NoContentException
import com.tubetoast.giffy.models.data.NoInternetException
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import com.tubetoast.giffy.utils.ConnectionChecker

class NetworkDataSource(
    private val api: GiphyApi,
    private val connectionChecker: ConnectionChecker,
    private val converter: SearchResultApiConverter,
) : DataSource<SearchRequest, SearchState> {

    override suspend fun get(request: SearchRequest): SearchState {
        if (!connectionChecker.isConnected()) throw NoInternetException
        val response = api.search(request.query, request.limit)
        if (response.code() != STATUS_CODE_OK) throw NetworkException(response.code())
        return converter.convert(response.body()) ?: throw NoContentException
    }

    private companion object {
        const val STATUS_CODE_OK = 200
    }
}