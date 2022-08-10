package com.tubetoast.giffy.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tubetoast.giffy.models.data.SearchResultRoomEntity

@Dao
interface SearchResultsDao {
    @Query("SELECT * FROM ${SearchResultRoomEntity.TABLE_NAME}")
    fun getAll(): List<SearchResultRoomEntity>

    @Query("SELECT * FROM ${SearchResultRoomEntity.TABLE_NAME} WHERE ${SearchResultRoomEntity.PARAM_QUERY} LIKE :query LIMIT 1")
    fun getByQuery(query: String): SearchResultRoomEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchResult: SearchResultRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg searchResults: SearchResultRoomEntity)

    @Delete
    fun delete(searchResult: SearchResultRoomEntity)
}