package com.example.giffy.domain

import com.example.giffy.models.domain.SearchResult

interface SearchRepository {

    suspend fun search(query: String, single: Boolean = false): SearchResult
}