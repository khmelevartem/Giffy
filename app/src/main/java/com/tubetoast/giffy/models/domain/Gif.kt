package com.tubetoast.giffy.models.domain

import android.net.Uri

data class Gif(
    val url: Uri,
    val title: String,
    val type: String,
    val source: String,
)