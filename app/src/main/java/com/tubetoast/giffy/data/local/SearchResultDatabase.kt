package com.tubetoast.giffy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tubetoast.giffy.models.data.SearchResultRoomEntity
import com.tubetoast.giffy.models.data.SearchResultRoomEntity.Companion.TABLE_NAME

@Database(entities = [SearchResultRoomEntity::class], version = 1, exportSchema = false)
abstract class SearchResultDatabase : RoomDatabase() {

    abstract fun searchResults(): SearchResultsDao

    companion object {

        fun create(context: Context) =
            Room.databaseBuilder(context, SearchResultDatabase::class.java, TABLE_NAME)
                .build()
    }
}