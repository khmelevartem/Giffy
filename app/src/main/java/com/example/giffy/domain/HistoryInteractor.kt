package com.example.giffy.domain

import com.example.giffy.models.domain.SearchResult

interface HistoryInteractor {

    fun addToHistory(query: String, result: SearchResult)

    /**
     * Получить последние запросы
     *
     * @param number количество запросов, 0 - все доступные
     */
    fun getLast(number: Int = 0): List<String>
}