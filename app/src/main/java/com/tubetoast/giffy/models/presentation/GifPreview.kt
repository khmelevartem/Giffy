package com.tubetoast.giffy.models.presentation

import com.tubetoast.giffy.models.domain.Gif

sealed class GifPreview(viewType: ContentViewType) : ContentItem(viewType) {

    data class Content(val gif: Gif) : GifPreview(ContentViewType.CONTENT)

    object Shimmer : GifPreview(ContentViewType.SHIMMER)
}