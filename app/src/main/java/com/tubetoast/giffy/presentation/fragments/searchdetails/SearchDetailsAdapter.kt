package com.tubetoast.giffy.presentation.fragments.searchdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.databinding.ItemRequestBinding
import com.tubetoast.giffy.domain.RequestActions
import com.tubetoast.giffy.models.presentation.SearchDetail
import com.tubetoast.giffy.models.presentation.SearchDetailsViewType

class SearchDetailsAdapter(
    private val requestActions: RequestActions,
) : RecyclerView.Adapter<BaseSearchDetailsViewHolder>() {

    private val details: MutableList<SearchDetail> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSearchDetailsViewHolder =
        when (viewType) {
            SearchDetailsViewType.REQUEST.ordinal -> RequestViewHolder(
                ItemRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                requestActions
            )
            else -> throw IllegalArgumentException("unknown view type")
        }

    override fun getItemViewType(position: Int): Int = details[position].viewType.ordinal

    override fun onBindViewHolder(holder: BaseSearchDetailsViewHolder, position: Int) {
        holder.setDetail(details[position])
    }

    override fun getItemCount() = details.size

    fun setDetails(requests: List<SearchDetail>) {
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = details.size
            override fun getNewListSize() = requests.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                details[oldItemPosition] == requests[newItemPosition]

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                details[oldItemPosition] == requests[newItemPosition]
        }).dispatchUpdatesTo(this)
        details.clear()
        details.addAll(requests)
    }
}