package com.tubetoast.giffy.data.history

import com.tubetoast.giffy.data.StackDataStorage
import com.tubetoast.giffy.domain.HistoryRepository
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.utils.CoroutineDispatchers
import com.tubetoast.giffy.utils.SupervisorScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HistoryRepositoryImpl(
    private val historyStorage: StackDataStorage<SearchRequest>,
    dispatchers: CoroutineDispatchers,
) : HistoryRepository {

    private val scope = SupervisorScope(dispatchers.io)
    private val lastRequests = MutableStateFlow<List<SearchRequest>>(emptyList())

    override fun getLast(number: Int): Flow<List<SearchRequest>> =
        lastRequests.asSharedFlow().also {
            scope.launch {
                lastRequests.emit(historyStorage.peek(number))
            }
        }

    override fun addToHistory(request: SearchRequest) {
        scope.launch {
            historyStorage.push(request)
            lastRequests.emit(historyStorage.peek(getLimit()))
        }
    }

    override fun deleteFromHistory(request: SearchRequest) {
        scope.launch {
            historyStorage.delete(request)
            lastRequests.emit(historyStorage.peek(getLimit()))
        }
    }

    private fun getLimit() =
        if (lastRequests.value.isEmpty()) StackDataStorage.UNLIMITED else lastRequests.value.size
}