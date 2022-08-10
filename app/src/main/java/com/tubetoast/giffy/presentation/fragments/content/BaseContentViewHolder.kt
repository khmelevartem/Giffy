package com.tubetoast.giffy.presentation.fragments.content

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.models.presentation.ContentItem

abstract class BaseContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun setContent(contentItem: ContentItem)
}