package com.tubetoast.giffy.data.local

import android.net.Uri
import androidx.room.TypeConverter
import com.tubetoast.giffy.models.data.SearchResultRoomEntity
import com.tubetoast.giffy.models.domain.Gif
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.domain.SearchResult

class SearchResultRoomConverter {

    fun convert(request: SearchRequest, result: SearchResult): SearchResultRoomEntity? =
        if (result is SearchResult.ListSearchResult) {
            SearchResultRoomEntity(
                request.query,
                result.images.map { it.url }
            )
        } else {
            null
        }

    fun reverse(roomEntity: SearchResultRoomEntity): SearchResult =
        SearchResult.ListSearchResult(
            roomEntity.gifs?.map { Gif(it) }.orEmpty()
        )

    @TypeConverter
    fun urisToString(uris: List<Uri>): String =
        uris.joinToString(separator = SEPARATOR)

    @TypeConverter
    fun stringToUris(uris: String): List<Uri> =
        uris.split(SEPARATOR).map { Uri.parse(it) }

    private companion object {
        const val SEPARATOR = "±"
    }
}