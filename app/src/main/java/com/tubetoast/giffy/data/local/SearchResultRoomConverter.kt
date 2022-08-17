package com.tubetoast.giffy.data.local

import com.tubetoast.giffy.models.data.SearchResultRoomEntity
import com.tubetoast.giffy.models.domain.Gif
import com.tubetoast.giffy.models.domain.SearchRequest

class SearchResultRoomConverter {

    fun convert(request: SearchRequest, gif: Gif): SearchResultRoomEntity =
        SearchResultRoomEntity(
            request.query,
            gif.url,
            gif.title,
            gif.type,
            gif.source

        )

    fun reverse(roomEntity: SearchResultRoomEntity): Gif =
        roomEntity.let {
            Gif(
                it.url,
                it.title,
                it.type,
                it.source
            )
        }
}