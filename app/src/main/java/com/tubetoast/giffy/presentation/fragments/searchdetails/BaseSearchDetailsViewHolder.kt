package com.tubetoast.giffy.presentation.fragments.searchdetails

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.models.domain.SearchRequest

abstract class BaseSearchDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun setDetail(request: SearchRequest)
}