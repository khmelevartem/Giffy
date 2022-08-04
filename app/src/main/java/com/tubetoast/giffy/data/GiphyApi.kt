package com.tubetoast.giffy.data

import com.tubetoast.giffy.models.data.SearchResultEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("gifs/search")
    suspend fun searchGiffs(
        @Query("q") query: String,
        @Query("limit") limit: Int,
    ): Response<SearchResultEntity?>
}