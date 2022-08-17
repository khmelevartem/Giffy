package com.tubetoast.giffy.data.history

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tubetoast.giffy.models.data.SearchRequestRoomEntity
import com.tubetoast.giffy.models.data.SearchRequestRoomEntity.Companion.TABLE_NAME

@Database(entities = [SearchRequestRoomEntity::class], version = 1)
abstract class SearchHistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        fun create(context: Context) =
            Room.databaseBuilder(context, SearchHistoryDatabase::class.java, TABLE_NAME)
                .build()
    }
}