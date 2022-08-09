package com.tubetoast.giffy.data

import com.tubetoast.giffy.domain.HistoryRepository
import com.tubetoast.giffy.models.domain.SearchRequest

class HistoryRepositoryImpl : HistoryRepository {
    override fun addToHistory(request: SearchRequest) {
        // TODO("Not yet implemented")
    }

    override fun getLast(number: Int): List<SearchRequest> {
        //TODO("Not yet implemented")
        return emptyList()
    }
}