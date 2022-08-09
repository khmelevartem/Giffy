package com.tubetoast.giffy.data.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tubetoast.giffy.models.data.SearchRequestRoomEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM ${SearchRequestRoomEntity.TABLE_NAME}")
    fun getAll() : List<SearchRequestRoomEntity>

    @Query("SELECT * FROM ${SearchRequestRoomEntity.TABLE_NAME} LIMIT :limit")
    fun get(limit: Int): List<SearchRequestRoomEntity>

    @Insert
    fun insert(requests: List<SearchRequestRoomEntity>)
}