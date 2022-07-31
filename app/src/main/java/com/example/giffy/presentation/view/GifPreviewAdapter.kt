package com.example.giffy.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giffy.databinding.ItemGifPreviewContentBinding
import com.example.giffy.databinding.ItemGifPreviewShimmerBinding
import com.example.giffy.models.presentation.Preview
import com.example.giffy.models.presentation.PreviewViewTypes

class GifPreviewAdapter : RecyclerView.Adapter<BaseGifPreviewViewHolder>() {

    private val content: MutableList<Preview> = mutableListOf()

    override fun getItemViewType(position: Int) = content[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            PreviewViewTypes.CONTENT -> GifPreviewContentViewHolder(
                ItemGifPreviewContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            PreviewViewTypes.SHIMMER -> GifPreviewShimmerViewHolder(
                ItemGifPreviewShimmerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("unknown view type")
        }

    override fun onBindViewHolder(holder: BaseGifPreviewViewHolder, position: Int) {
        holder.setContent(content[position])
    }

    override fun getItemCount() = content.size

    fun setContent(previews: List<Preview>) {
        content.clear()
        content.addAll(previews)
        notifyDataSetChanged() //TODO("DiffUtil")
    }
}