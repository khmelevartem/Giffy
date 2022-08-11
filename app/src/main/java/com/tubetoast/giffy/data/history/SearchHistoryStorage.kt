package com.tubetoast.giffy.data.history

import com.tubetoast.giffy.data.StackDataStorage
import com.tubetoast.giffy.models.domain.SearchRequest

class SearchHistoryStorage(
    private val historyDatabase: SearchHistoryDatabase,
    private val converter: SearchHistoryRoomConverter,
) : StackDataStorage<SearchRequest>() {

    override suspend fun peek(limit: Int): List<SearchRequest> =
        historyDatabase.historyDao().run {
            if (limit == UNLIMITED) getAll() else get(limit)
        }.map { converter.reverse(it) }.reversed()

    override suspend fun push(vararg values: SearchRequest) {
        values.forEach {
            val converted = converter.convert(it)
            historyDatabase.historyDao().insert(converted)
        }
    }

    override suspend fun pushAll(values: List<SearchRequest>) {
        historyDatabase.historyDao().insert(values.map { converter.convert(it) })
    }

    override suspend fun delete(value: SearchRequest) {
        historyDatabase.historyDao().delete(converter.convert(value))
    }
}