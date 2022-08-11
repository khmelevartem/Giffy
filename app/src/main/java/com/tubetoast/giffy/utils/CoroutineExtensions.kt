package com.tubetoast.giffy.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@Suppress("FunctionName")
fun SupervisorScope(context: CoroutineContext) =
    CoroutineScope(context + SupervisorJob())