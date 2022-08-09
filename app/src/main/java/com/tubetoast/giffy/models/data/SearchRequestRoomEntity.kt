package com.tubetoast.giffy.models.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SearchRequestRoomEntity.TABLE_NAME)
data class SearchRequestRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val query: String,
    val limit: Int,
) {
    companion object {
        const val TABLE_NAME = "search_history"
    }
}
