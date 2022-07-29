package com.example.giffy.domain

import com.example.giffy.models.domain.SearchResult

interface SearchRepository {

    suspend fun search(query: String, limit: Int = DEFAULT_LIMIT): SearchResult

    companion object {
        const val DEFAULT_LIMIT = 10
    }
}