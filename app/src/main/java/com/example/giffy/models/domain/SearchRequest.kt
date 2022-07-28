package com.example.giffy.models.domain

data class SearchRequest(
    val query: String,
    val limit: Int = NO_LIMIT,
) {

    companion object {
        const val NO_LIMIT = 0
    }
}