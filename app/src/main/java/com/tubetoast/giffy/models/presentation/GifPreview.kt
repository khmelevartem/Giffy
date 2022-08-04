package com.tubetoast.giffy.models.presentation

import com.tubetoast.giffy.models.domain.Gif

sealed class GifPreview(viewType: Int) : UIItem(viewType) {

    data class Content(val gif: Gif) : GifPreview(ViewTypes.CONTENT)

    object Shimmer : GifPreview(ViewTypes.SHIMMER)
}