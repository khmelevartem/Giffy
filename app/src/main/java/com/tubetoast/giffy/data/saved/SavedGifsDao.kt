package com.tubetoast.giffy.data.saved

import android.net.Uri
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.tubetoast.giffy.models.data.SavedGifRoomEntity
import com.tubetoast.giffy.models.data.SavedGifRoomEntity.Companion.PARAM_NET_URL
import com.tubetoast.giffy.models.data.SavedGifRoomEntity.Companion.TABLE_NAME
import com.tubetoast.giffy.utils.UriStringConverter

@Dao
@TypeConverters(UriStringConverter::class)
interface SavedGifsDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<SavedGifRoomEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE $PARAM_NET_URL LIKE :netUrl")
    fun get(netUrl: Uri): SavedGifRoomEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(savedGif: SavedGifRoomEntity)

    @Delete
    fun delete(savedGif: SavedGifRoomEntity)
}