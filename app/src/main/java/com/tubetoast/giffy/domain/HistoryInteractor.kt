package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult

interface HistoryInteractor {

    fun addToHistory(request: SearchRequest, result: SearchResult)

    /**
     * Получить последние запросы
     *
     * @param number количество запросов, 0 - все доступные
     */
    suspend fun getLast(number: Int = 0): List<SearchRequest>
}