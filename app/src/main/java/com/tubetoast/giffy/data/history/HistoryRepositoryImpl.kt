package com.tubetoast.giffy.data.history

import com.tubetoast.giffy.data.StackDataStorage
import com.tubetoast.giffy.domain.HistoryRepository
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.utils.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryRepositoryImpl(
    private val historyStorage: StackDataStorage<SearchRequest>,
    dispatchers: CoroutineDispatchers,
) : HistoryRepository {

    private val scope = CoroutineScope(SupervisorJob() + dispatchers.io)

    override fun addToHistory(request: SearchRequest) {
        scope.launch {
            historyStorage.put(request)
        }
    }

    override suspend fun getLast(number: Int): List<SearchRequest> =
        withContext(scope.coroutineContext) {
            historyStorage.get(number).orEmpty()
        }
}