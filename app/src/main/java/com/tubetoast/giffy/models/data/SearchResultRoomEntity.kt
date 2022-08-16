package com.tubetoast.giffy.models.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tubetoast.giffy.data.local.SearchResultRoomConverter

@Entity(tableName = SearchResultRoomEntity.TABLE_NAME)
@TypeConverters(SearchResultRoomConverter::class)
data class SearchResultRoomEntity(

    @ColumnInfo(name = PARAM_QUERY)
    val searchQuery: String,

    val url: Uri,
    val title: String,
    val type: String,
    val source: String,

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
) {
    companion object {
        const val TABLE_NAME = "search_results"
        const val PARAM_QUERY = "search_query"
    }
}