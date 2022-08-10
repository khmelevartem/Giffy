package com.tubetoast.giffy.models.presentation

import com.tubetoast.giffy.models.domain.Gif

sealed class GifPreview(viewType: Int) : ContentItem(viewType) {

    data class Content(val gif: Gif) : GifPreview(ContentViewTypes.CONTENT)

    object Shimmer : GifPreview(ContentViewTypes.SHIMMER)
}