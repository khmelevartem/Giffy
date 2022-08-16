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

    @PrimaryKey
    @ColumnInfo(name = PARAM_QUERY)
    val searchQuery: String,

    val gifs: List<Uri>?,

    ) {
    companion object {
        const val TABLE_NAME = "search_results"
        const val PARAM_QUERY = "search_query"
    }
}