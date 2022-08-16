package com.tubetoast.giffy.models.data

abstract class DataException(message: String? = null) : RuntimeException(message)

object NoInternetException : DataException()

object NoContentException : DataException()

data class NetworkException(val statusCode: Int) : DataException()