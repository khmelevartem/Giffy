package com.tubetoast.giffy.data.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tubetoast.giffy.models.data.SearchRequestRoomEntity
import com.tubetoast.giffy.models.data.SearchRequestRoomEntity.Companion.PARAM_ID
import com.tubetoast.giffy.models.data.SearchRequestRoomEntity.Companion.PARAM_QUERY
import com.tubetoast.giffy.models.data.SearchRequestRoomEntity.Companion.TABLE_NAME

@Dao
interface HistoryDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $PARAM_ID DESC")
    fun getAll() : List<SearchRequestRoomEntity>

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $PARAM_ID DESC LIMIT :limit")
    fun get(limit: Int): List<SearchRequestRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(requests: List<SearchRequestRoomEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(request: SearchRequestRoomEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE $PARAM_QUERY LIKE :query")
    fun delete(query: String)
}