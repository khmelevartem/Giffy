package com.tubetoast.giffy.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tubetoast.giffy.models.data.SearchResultRoomEntity
import com.tubetoast.giffy.models.data.SearchResultRoomEntity.Companion.PARAM_QUERY
import com.tubetoast.giffy.models.data.SearchResultRoomEntity.Companion.TABLE_NAME

@Dao
interface SearchResultsDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<SearchResultRoomEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE $PARAM_QUERY LIKE :query")
    fun getByQuery(query: String): List<SearchResultRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchResult: SearchResultRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(searchResults: List<SearchResultRoomEntity>)

    @Delete
    fun delete(searchResult: SearchResultRoomEntity)
}