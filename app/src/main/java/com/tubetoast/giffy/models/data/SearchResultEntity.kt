package com.tubetoast.giffy.models.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

// https://developers.giphy.com/docs/api/schema
data class SearchResultEntity @JsonCreator constructor(
    @param:JsonProperty("data")
    val data: List<DataItemEntity>?,

    @param:JsonProperty("pagination")
    val pagination: PaginationEntity? = null,

    @param:JsonProperty("meta")
    val meta: MetaEntity?,
)