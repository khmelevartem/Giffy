package com.example.giffy.models.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class PaginationEntity @JsonCreator constructor(

    @param:JsonProperty("offset")
    val offset: Int,

    @param:JsonProperty("total_count")
    val totalCount: Int,

    @param:JsonProperty("count")
    val count: Int,
)