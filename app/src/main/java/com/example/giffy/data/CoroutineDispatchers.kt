package com.example.giffy.data

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {

    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher

}