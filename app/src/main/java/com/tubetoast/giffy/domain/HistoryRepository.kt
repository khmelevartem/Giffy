package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getLast(number: Int): Flow<List<SearchRequest>>

    fun addToHistory(request: SearchRequest)

    fun deleteFromHistory(request: SearchRequest)
}