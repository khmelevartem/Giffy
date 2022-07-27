package com.example.giffy.models.domain

sealed class SearchResult {
    object EMPTY : SearchResult()

    data class SearchError(val exception: Exception) : SearchResult()

    data class ListSearchResult(val images: List<String>) : SearchResult() {
        val mostRelevant: SingleSearchResult?
            get() = images.firstOrNull()?.let { SingleSearchResult(it) }
    }

    data class SingleSearchResult(val image: String) : SearchResult()
}