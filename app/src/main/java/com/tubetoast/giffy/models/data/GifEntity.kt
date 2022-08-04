package com.tubetoast.giffy.models.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GifEntity @JsonCreator constructor(

    @param:JsonProperty("url")
    val gifUrl: String? = null,

    @param:JsonProperty("mp4")
    val mp4Url: String? = null,

    @param:JsonProperty("webp")
    val webpUrl: String? = null,

    @param:JsonProperty("height")
    val height: String? = null,

    @param:JsonProperty("width")
    val width: String? = null,

    @param:JsonProperty("frames")
    val frames: String? = null,
)