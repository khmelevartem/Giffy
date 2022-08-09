package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest

interface HistoryRepository {
    fun addToHistory(request: SearchRequest)

    suspend fun getLast(number: Int): List<SearchRequest>
}