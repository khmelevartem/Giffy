package com.tubetoast.giffy.presentation.fragments.content

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.PopupMenu
import com.tubetoast.giffy.R
import com.tubetoast.giffy.models.domain.Gif

class GifPreviewActions {

    fun showOptions(view: View, gif: Gif) {
        val menu = PopupMenu(view.context, view)
        menu.inflate(R.menu.gif_preview_action_options_menu)
        menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.option_gif_save -> save(gif, view.context)
                R.id.option_gif_share_url -> shareAsUrl(gif, view.context)
                R.id.option_gif_share_file -> shareAsFile(gif, view.context)
                else -> Unit
            }
            true
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

    private fun shareAsFile(gif: Gif, context: Context) {
        val localUrl = save(gif, context)
        val intent = Intent().run {
            action = Intent.ACTION_SEND
            type = "image/gif"
            putExtra(Intent.EXTRA_STREAM, localUrl)
            Intent.createChooser(this, "share as file").apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }
        context.startActivity(intent)
    }

    private fun save(gif: Gif, context: Context): Uri = TODO()
}