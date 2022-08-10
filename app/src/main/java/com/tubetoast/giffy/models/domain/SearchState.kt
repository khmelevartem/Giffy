package com.tubetoast.giffy.models.domain

sealed class SearchState {
    object Loading : SearchState()

    data class Error(val exception: Exception) : SearchState()

    data class Success(val images: List<Gif>) : SearchState()
}