package com.tubetoast.giffy.presentation.fragments.content

import com.bumptech.glide.Glide
import com.tubetoast.giffy.R
import com.tubetoast.giffy.databinding.ItemGifPreviewContentBinding
import com.tubetoast.giffy.models.presentation.ContentItem
import com.tubetoast.giffy.models.presentation.GifPreview

class GifPreviewContentViewHolder(
    private val binding: ItemGifPreviewContentBinding,
    private val gifPreviewActions: GifPreviewActions,
) : BaseContentViewHolder(binding.root) {
    override fun setContent(contentItem: ContentItem) {
        check(contentItem is GifPreview.Content) { "Incompatible content" }
        binding.buttonOptions.setOnClickListener {
            gifPreviewActions.showOptions(it, contentItem.gif)
        }
        Glide.with(itemView)
            .load(contentItem.gif.url)
            .placeholder(R.drawable.shimmer)
            .error(R.drawable.error)
            .into(binding.image)
    }
}