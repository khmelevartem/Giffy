package com.tubetoast.giffy.domain

import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchState

interface HistoryInteractor {

    fun addToHistory(request: SearchRequest, state: SearchState)

    /**
     * Получить последние запросы
     *
     * @param number количество запросов, [UNLIMITED] - все доступные
     */
    suspend fun getLast(number: Int = 5): List<SearchRequest>

    companion object {
        const val UNLIMITED = 0
    }
}