package com.tubetoast.giffy.data.history

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tubetoast.giffy.models.data.SearchRequestRoomEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM ${SearchRequestRoomEntity.TABLE_NAME}")
    fun getAll() : List<SearchRequestRoomEntity>

    @Query("SELECT * FROM ${SearchRequestRoomEntity.TABLE_NAME} LIMIT :limit")
    fun get(limit: Int): List<SearchRequestRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(requests: List<SearchRequestRoomEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(request: SearchRequestRoomEntity)

    @Delete
    fun delete(request: SearchRequestRoomEntity)
}