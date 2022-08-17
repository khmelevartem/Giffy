package com.tubetoast.giffy.data.saved

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tubetoast.giffy.models.data.SavedGifRoomEntity
import com.tubetoast.giffy.models.data.SavedGifRoomEntity.Companion.TABLE_NAME

@Database(entities = [SavedGifRoomEntity::class], version = 1)
abstract class SavedGifsDatabase : RoomDatabase() {

    abstract fun savedGifsDao(): SavedGifsDao

    companion object {
        fun create(context: Context) =
            Room.databaseBuilder(context, SavedGifsDatabase::class.java, TABLE_NAME)
                .build()
    }
}