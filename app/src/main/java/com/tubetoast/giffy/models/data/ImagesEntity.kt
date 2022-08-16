package com.tubetoast.giffy.models.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ImagesEntity @JsonCreator constructor(

    @param:JsonProperty("original")
    val original: GifEntity,

    @param:JsonProperty("preview_gif")
    val preview: GifEntity?,

    @param:JsonProperty("fixed_height")
    val fixedHeight: GifEntity,

    @param:JsonProperty("fixed_height_downsampled")
    val fixedHeightDownsample: GifEntity,

    @param:JsonProperty("fixed_width")
    val fixedWidth: GifEntity,

    @param:JsonProperty("fixed_width_downsampled")
    val fixedWidthDownsample: GifEntity,
)
