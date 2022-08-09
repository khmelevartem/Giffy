package com.tubetoast.giffy.data.history

import com.tubetoast.giffy.data.StackDataStorage
import com.tubetoast.giffy.models.domain.SearchRequest

class SearchHistoryStorage(
    private val historyDatabase: SearchHistoryDatabase,
    private val converter: SearchHistoryRoomConverter
) : StackDataStorage<SearchRequest>() {

    override suspend fun get(limit: Int): List<SearchRequest>? =
        historyDatabase.historyDao().run {
            if (limit == UNLIMITED) getAll() else get(limit)
        }.map { converter.reverse(it) }

    override suspend fun put(vararg values: SearchRequest) {
        putAll(values.asList())
    }

    override suspend fun putAll(values: List<SearchRequest>) {
        historyDatabase.historyDao().insert(values.map { converter.convert(it) })
    }
}