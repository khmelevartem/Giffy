package com.tubetoast.giffy.models.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tubetoast.giffy.models.data.SavedGifRoomEntity.Companion.TABLE_NAME
import com.tubetoast.giffy.utils.UriStringConverter

@Entity(tableName = TABLE_NAME)
@TypeConverters(UriStringConverter::class)
data class SavedGifRoomEntity(
    @PrimaryKey
    @ColumnInfo(name = PARAM_NET_URL)
    val networkUrl: Uri,
    val localUrl: Uri,
) {
    companion object {
        const val TABLE_NAME = "saved_gifs"
        const val PARAM_NET_URL = "net_url"
    }
}