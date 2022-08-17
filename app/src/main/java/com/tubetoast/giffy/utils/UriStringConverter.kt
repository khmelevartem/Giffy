package com.tubetoast.giffy.utils

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.TypeConverter

object UriStringConverter {
    @TypeConverter
    fun uriToString(uri: Uri): String = uri.toString()

    @TypeConverter
    fun stringToUri(string: String): Uri = string.toUri()
}