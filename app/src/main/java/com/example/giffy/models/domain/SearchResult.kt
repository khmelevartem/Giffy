package com.example.giffy.models.domain

sealed class SearchResult {
    data class SearchError(val exception: Exception) : SearchResult()

    data class ListSearchResult(val images: List<Gif>) : SearchResult()
}