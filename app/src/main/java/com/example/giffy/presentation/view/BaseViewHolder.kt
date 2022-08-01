package com.example.giffy.presentation.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.giffy.models.presentation.Preview

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun setContent(preview: Preview)
}