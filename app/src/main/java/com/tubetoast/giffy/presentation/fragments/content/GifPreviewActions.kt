package com.tubetoast.giffy.presentation.fragments.content

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tubetoast.giffy.R
import com.tubetoast.giffy.data.saved.SavedGifsCache
import com.tubetoast.giffy.models.domain.Gif
import com.tubetoast.giffy.utils.CoroutineDispatchers
import com.tubetoast.giffy.utils.SupervisorScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileInputStream

class GifPreviewActions(
    private val dispatchers: CoroutineDispatchers,
    private val savedGifsCache: SavedGifsCache,
) {

    private val uiScope = SupervisorScope(dispatchers.main)

    fun showOptions(view: View, gif: Gif) {
        val menu = PopupMenu(view.context, view)
        menu.inflate(R.menu.gif_preview_action_options_menu)
        menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.option_gif_save -> uiScope.launch { save(gif, view.context) }
                R.id.option_gif_share_url -> uiScope.launch { shareAsUrl(gif, view.context) }
                R.id.option_gif_share_file -> uiScope.launch { shareAsFile(gif, view.context) }
                else -> return@setOnMenuItemClickListener false
            }
            return@setOnMenuItemClickListener true
        }
        menu.show()
    }

    private fun shareAsUrl(gif: Gif, context: Context) {
        val intent = Intent().run {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, gif.url.toString())
            Intent.createChooser(this, "share as url").apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }
        context.startActivity(intent)
    }

    private suspend fun shareAsFile(gif: Gif, context: Context) {
        val localUrl = save(gif, context)
        val intent = Intent().run {
            action = Intent.ACTION_SEND
            type = "content/unknown"
            putExtra(Intent.EXTRA_STREAM, localUrl)
            Intent.createChooser(this, "share as file").apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }
        context.startActivity(intent)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun save(gif: Gif, context: Context): Uri = withContext(dispatchers.io) {
        savedGifsCache.getOrCreate(gif.url) {
            val imageDetails = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, gif.title)
                put(MediaStore.Images.Media.TITLE, "${gif.title} ${gif.source}")
                put(MediaStore.Images.Media.MIME_TYPE, "image/gif") //todo
            }
            val resolver = context.contentResolver
            val imageContentUri = resolver.insert(imagesCollection(), imageDetails)
                ?: throw IllegalStateException("Can't create file")
            resolver.openOutputStream(imageContentUri, "w")?.use { output ->
                FileInputStream(getAsFile(context, gif)).use { input ->
                    val outputByte = ByteArray(1024)
                    var readLength = input.read(outputByte)
                    while (readLength != -1) {
                        output.write(outputByte, 0, readLength)
                        readLength = input.read(outputByte)
                    }
                }
            }
            imageDetails.clear()
            uiScope.launch { Toast.makeText(context, "Saved to gallery", Toast.LENGTH_SHORT).show() }
            imageContentUri
        }
    }

    private fun imagesCollection() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

    private fun getAsFile(context: Context, gif: Gif) =
        Glide.with(context)
            .asFile()
            .load(gif.url)
            .submit()
            .get()
}