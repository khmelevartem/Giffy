package com.example.giffy.domain

import com.example.giffy.models.domain.SearchResult

interface SearchInteractor {

    suspend fun search(query: String): SearchResult
}