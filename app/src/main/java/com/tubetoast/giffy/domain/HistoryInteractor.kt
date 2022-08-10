package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState

interface HistoryInteractor {

    fun addToHistory(request: SearchRequest, state: SearchState)

    /**
     * Получить последние запросы
     *
     * @param number количество запросов, 0 - все доступные
     */
    suspend fun getLast(number: Int = 0): List<SearchRequest>
}