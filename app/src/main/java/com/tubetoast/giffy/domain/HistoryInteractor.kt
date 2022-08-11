package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState
import kotlinx.coroutines.flow.Flow

interface HistoryInteractor {

    /**
     * Получить последние запросы
     *
     * @param number количество запросов, [UNLIMITED] - все доступные
     */
    fun getLast(number: Int = 5): Flow<List<SearchRequest>>

    fun addToHistory(request: SearchRequest, state: SearchState)

    fun deleteFromHistory(request: SearchRequest)

    companion object {
        const val UNLIMITED = 0
    }
}