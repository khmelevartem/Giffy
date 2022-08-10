package com.tubetoast.giffy.data.history

import com.tubetoast.giffy.models.data.SearchRequestRoomEntity
import com.tubetoast.giffy.models.domain.SearchRequest

class SearchHistoryRoomConverter {

    fun convert(from: SearchRequest) : SearchRequestRoomEntity =
        SearchRequestRoomEntity(from.query, from.limit)

    fun reverse(entity: SearchRequestRoomEntity) : SearchRequest =
        SearchRequest(entity.query, entity.limit)
}