package com.example.giffy.models.presentation

import androidx.annotation.DrawableRes
import com.example.giffy.R
import com.example.giffy.models.domain.Gif

sealed class Preview(val viewType: Int) {

    class Content(val gif: Gif) : Preview(PreviewViewTypes.CONTENT)

    class Shimmer(@DrawableRes val resId: Int = R.drawable.shimmer) : Preview(PreviewViewTypes.SHIMMER)
}