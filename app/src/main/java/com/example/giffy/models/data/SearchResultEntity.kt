package com.example.giffy.models.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchResultEntity @JsonCreator() constructor(
    @param:JsonProperty("data")
    val data: Any,
)