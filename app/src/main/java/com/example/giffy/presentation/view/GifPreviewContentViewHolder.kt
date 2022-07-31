package com.example.giffy.presentation.view

import com.bumptech.glide.Glide
import com.example.giffy.R
import com.example.giffy.databinding.ItemGifPreviewContentBinding
import com.example.giffy.models.presentation.Preview

class GifPreviewContentViewHolder(
    private val binding: ItemGifPreviewContentBinding,
) : BaseGifPreviewViewHolder(binding.root) {
    override fun setContent(preview: Preview) {
        check(preview is Preview.Content) { "Incompatible content" }
        Glide.with(itemView)
            .load(preview.gif.url)
            .placeholder(R.drawable.shimmer)
            .into(binding.image)
    }
}