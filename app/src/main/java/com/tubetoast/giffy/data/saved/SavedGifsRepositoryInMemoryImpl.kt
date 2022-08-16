package com.tubetoast.giffy.data.saved

import android.net.Uri

@Deprecated("Временно")
class SavedGifsRepositoryInMemoryImpl : SavedGifsRepository {
    private val savedGifs = mutableMapOf<Uri, Uri>()

    override suspend fun getOrCreate(request: Uri, creator: suspend (Uri) -> Uri): Uri =
        get(request) ?: creator(request).also {
            savedGifs[request] = it
        }

    @Deprecated("Не гарантиует, что файл существует на самом деле")
    override suspend fun get(request: Uri): Uri? = savedGifs[request]
}