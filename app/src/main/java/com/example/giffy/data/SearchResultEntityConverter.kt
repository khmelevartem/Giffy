package com.example.giffy.data

import com.example.giffy.models.data.SearchResultEntity
import com.example.giffy.models.domain.Gif
import com.example.giffy.models.domain.SearchResult
import java.net.URI

class SearchResultEntityConverter {

    fun convert(from: SearchResultEntity?): SearchResult =
        from?.data?.takeIf { it.isNotEmpty() }?.let { data ->
            val images = data.map { Gif(URI(it.images.original.gifUrl)) }
            SearchResult.ListSearchResult(images)
        } ?: SearchResult.EMPTY
}