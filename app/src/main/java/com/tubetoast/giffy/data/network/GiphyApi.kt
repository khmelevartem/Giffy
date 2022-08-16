package com.tubetoast.giffy.data.network

import com.tubetoast.giffy.models.data.SearchResultEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("gifs/search")
    suspend fun search(
        @Query("q") query: String,
        @Query("limit") limit: Int,
    ): Response<SearchResultEntity?>
}