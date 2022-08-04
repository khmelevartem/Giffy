package com.tubetoast.giffy.models.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class MetaEntity @JsonCreator constructor(

    @param:JsonProperty("msg")
    val message: String,

    @param:JsonProperty("status")
    val status: Int,

    @param:JsonProperty("response_id")
    val response_id: String,
)