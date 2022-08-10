package com.tubetoast.giffy.data.network

import android.net.Uri
import com.tubetoast.giffy.models.data.SearchResultEntity
import com.tubetoast.giffy.models.domain.Gif
import com.tubetoast.giffy.models.domain.SearchState

class SearchResultApiConverter {

    fun convert(from: SearchResultEntity?): SearchState? =
        from?.data?.takeIf { it.isNotEmpty() }?.let { data ->
            val images = data.map { Gif(Uri.parse(it.images.original.gifUrl)) }
            SearchState.Success(images)
        }
}