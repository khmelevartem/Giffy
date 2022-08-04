package com.tubetoast.giffy.utils

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {

    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher

}