package com.tubetoast.giffy.data.network

import android.net.Uri
import com.tubetoast.giffy.data.DataSource
import com.tubetoast.giffy.models.domain.Gif
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState

class MockNetworkDataSource : DataSource<SearchRequest, SearchState> {
    override suspend fun get(request: SearchRequest): SearchState? =
        SearchState.Success(
            listOf(Gif(Uri.parse(
                "https://giphy.com/gifs/minecraft-microsoft-builder-minecraft-1oKQqphQDlpb2rHUpZ"
            )))
        )
}