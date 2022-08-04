package com.tubetoast.giffy.models.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DataItemEntity @JsonCreator constructor(

    @param:JsonProperty("type")
    val type: String,

    @param:JsonProperty("id")
    val id: String,

    @param:JsonProperty("url")
    val url: String,

    @param:JsonProperty("title")
    val title: String,

    @param:JsonProperty("images")
    val images: ImagesEntity,
)