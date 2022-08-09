package com.tubetoast.giffy.models.domain

sealed class SearchResult {
    object Loading : SearchResult()

    data class SearchError(val exception: Exception) : SearchResult()

    data class ListSearchResult(val images: List<Gif>) : SearchResult()
}