package com.example.giffy.domain

interface HistoryInteractor {


    fun addToHistory(query: String)

    /**
     * Получить последние запросы
     *
     * @param number количество запросов, 0 - все доступные
     */
    fun getLast(number: Int = 0): List<String>
}