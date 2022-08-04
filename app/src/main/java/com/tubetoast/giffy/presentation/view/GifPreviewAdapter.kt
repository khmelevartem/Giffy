package com.tubetoast.giffy.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.databinding.ItemBannerBinding
import com.tubetoast.giffy.databinding.ItemGifPreviewContentBinding
import com.tubetoast.giffy.databinding.ItemGifPreviewShimmerBinding
import com.tubetoast.giffy.models.presentation.UIItem
import com.tubetoast.giffy.models.presentation.ViewTypes

class GifPreviewAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val content: MutableList<UIItem> = mutableListOf()

    override fun getItemViewType(position: Int) = content[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            ViewTypes.CONTENT -> GifPreviewContentViewHolder(
                ItemGifPreviewContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ViewTypes.SHIMMER -> GifPreviewShimmerViewHolder(
                ItemGifPreviewShimmerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ViewTypes.BANNER -> BannerViewHolder(
                ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("unknown view type")
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setContent(content[position])
    }

    override fun getItemCount() = content.size

    fun setContent(uiItems: List<UIItem>) {
        content.clear()
        content.addAll(uiItems)
        notifyDataSetChanged() //TODO("DiffUtil")
    }
}