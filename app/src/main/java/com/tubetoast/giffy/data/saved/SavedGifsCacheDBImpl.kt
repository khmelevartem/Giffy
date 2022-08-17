package com.tubetoast.giffy.data.saved

import android.content.Context
import android.net.Uri
import com.tubetoast.giffy.models.data.SavedGifRoomEntity

class SavedGifsCacheDBImpl(
    private val database: SavedGifsDatabase,
    private val context: Context,
) : SavedGifsCache {
    override suspend fun getOrCreate(request: Uri, creator: suspend (Uri) -> Uri): Uri =
        get(request) ?: creator(request).also {
            database.savedGifsDao().insert(SavedGifRoomEntity(request, it))
        }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun get(request: Uri): Uri? =
        database.savedGifsDao().get(request)?.localUrl?.takeIf { localUri ->
            context.contentResolver.openFileDescriptor(localUri, "r", null)?.use {
                it.fileDescriptor.valid()
            } ?: false
        }
}