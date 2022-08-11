package com.tubetoast.giffy.presentation.fragments.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.databinding.ItemBannerBinding
import com.tubetoast.giffy.databinding.ItemGifPreviewContentBinding
import com.tubetoast.giffy.databinding.ItemGifPreviewShimmerBinding
import com.tubetoast.giffy.models.presentation.ContentItem
import com.tubetoast.giffy.models.presentation.ContentViewTypes

class ContentAdapter : RecyclerView.Adapter<BaseContentViewHolder>() {

    private val content: MutableList<ContentItem> = mutableListOf()

    override fun getItemViewType(position: Int) = content[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            ContentViewTypes.CONTENT -> GifPreviewContentViewHolder(
                ItemGifPreviewContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ContentViewTypes.SHIMMER -> GifPreviewShimmerViewHolder(
                ItemGifPreviewShimmerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ContentViewTypes.BANNER -> BannerViewHolder(
                ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("unknown view type")
        }

    override fun onBindViewHolder(holder: BaseContentViewHolder, position: Int) {
        holder.setContent(content[position])
    }

    override fun getItemCount() = content.size

    fun setContent(contentItems: List<ContentItem>) {
        if (content != contentItems) {
            content.clear()
            content.addAll(contentItems)
            notifyDataSetChanged()
        }
    }
}