package com.example.giffy.domain

import com.example.giffy.models.domain.SearchResult

interface SearchInteractor {

    suspend fun searchMostRelevant(query: String): SearchResult

    suspend fun searchAll(query: String): SearchResult
}