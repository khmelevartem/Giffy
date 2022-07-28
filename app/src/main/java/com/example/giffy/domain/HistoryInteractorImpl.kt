package com.example.giffy.domain

import com.example.giffy.models.domain.SearchResult

class HistoryInteractorImpl : HistoryInteractor {
    override fun addToHistory(query: String, result: SearchResult) {
        if (result !is SearchResult.SearchError) {
            // TODO("addToHistory(query)")
        }
    }

    override fun getLast(number: Int): List<String> {
        // TODO("Not yet implemented")
        return emptyList()
    }
}