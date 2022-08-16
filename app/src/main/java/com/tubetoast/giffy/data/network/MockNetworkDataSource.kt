package com.tubetoast.giffy.data.network

import androidx.core.net.toUri
import com.tubetoast.giffy.data.DataSource
import com.tubetoast.giffy.models.domain.Gif
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState

class MockNetworkDataSource : DataSource<SearchRequest, SearchState> {
    override suspend fun get(request: SearchRequest): SearchState =
        SearchState.Success(
            listOf(
                Gif(
                    "https://media4.giphy.com/media/l2JhFVCxiG7doPIdy/giphy.gif?cid=185361cf38rj9b7r9q9d7qrzyhwk2tca4zspdc17vfnsplvo&rid=giphy.gif&ct=g".toUri(),
                    "Asian American Smile GIF by Identity",
                    "gif",
                    "www.youtube.com"
                )
            )
        )
}