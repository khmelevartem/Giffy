package com.tubetoast.giffy.models.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = SearchRequestRoomEntity.TABLE_NAME, indices = [Index(value = ["query"], unique = true)])
data class SearchRequestRoomEntity(
    @ColumnInfo(name = PARAM_QUERY)
    val query: String,
    val limit: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
) {
    companion object {
        const val TABLE_NAME = "search_history"
        const val PARAM_ID = "id"
        const val PARAM_QUERY = "query"
    }
}
