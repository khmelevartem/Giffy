package com.tubetoast.giffy.presentation.fragments.searchdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.databinding.ItemRequestBinding
import com.tubetoast.giffy.domain.RequestActions
import com.tubetoast.giffy.models.domain.SearchRequest
import com.tubetoast.giffy.models.presentation.SearchDetailsViewTypes

class SearchDetailsAdapter(
    private val requestActions: RequestActions
) : RecyclerView.Adapter<BaseSearchDetailsViewHolder>() {

    private val history: MutableList<SearchRequest> = mutableListOf()
    private val filters: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSearchDetailsViewHolder =
        when (viewType) {
            SearchDetailsViewTypes.REQUEST -> RequestViewHolder(
                ItemRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                requestActions
            )
            else -> throw IllegalArgumentException("unknown view type")
        }

    override fun getItemViewType(position: Int): Int {
        return SearchDetailsViewTypes.REQUEST
    }

    override fun onBindViewHolder(holder: BaseSearchDetailsViewHolder, position: Int) {
        holder.setDetail(history[position])
    }

    override fun getItemCount() = history.size + filters.size

    fun setHistory(requests: List<SearchRequest>) {
        history.clear()
        history.addAll(requests)
        //TODO()
        notifyDataSetChanged()
    }

    fun setHFilters(availableFilters: List<String>) {
        filters.clear()
        filters.addAll(availableFilters)
        //TODO()
        notifyDataSetChanged()
    }
}