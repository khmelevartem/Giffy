package com.tubetoast.giffy.presentation.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.models.presentation.UIItem

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun setContent(uiItem: UIItem)
}