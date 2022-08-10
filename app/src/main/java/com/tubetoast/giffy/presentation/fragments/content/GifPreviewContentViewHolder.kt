package com.tubetoast.giffy.presentation.fragments.content

import com.bumptech.glide.Glide
import com.tubetoast.giffy.R
import com.tubetoast.giffy.databinding.ItemGifPreviewContentBinding
import com.tubetoast.giffy.models.presentation.GifPreview
import com.tubetoast.giffy.models.presentation.ContentItem

class GifPreviewContentViewHolder(
    private val binding: ItemGifPreviewContentBinding,
) : BaseContentViewHolder(binding.root) {
    override fun setContent(contentItem: ContentItem) {
        check(contentItem is GifPreview.Content) { "Incompatible content" }
        Glide.with(itemView)
            .load(contentItem.gif.url)
            .placeholder(R.drawable.shimmer)
            .into(binding.image)
    }
}