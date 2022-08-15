package com.tubetoast.giffy.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Suppress("FunctionName")
fun SupervisorScope(context: CoroutineContext = EmptyCoroutineContext) =
    CoroutineScope(context + SupervisorJob())