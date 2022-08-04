package com.tubetoast.giffy.presentation.view

import com.bumptech.glide.Glide
import com.tubetoast.giffy.R
import com.tubetoast.giffy.databinding.ItemGifPreviewContentBinding
import com.tubetoast.giffy.models.presentation.Preview

class GifPreviewContentViewHolder(
    private val binding: ItemGifPreviewContentBinding,
) : BaseViewHolder(binding.root) {
    override fun setContent(preview: Preview) {
        check(preview is Preview.Content) { "Incompatible content" }
        Glide.with(itemView)
            .load(preview.gif.url)
            .placeholder(R.drawable.shimmer)
            .into(binding.image)
    }
}