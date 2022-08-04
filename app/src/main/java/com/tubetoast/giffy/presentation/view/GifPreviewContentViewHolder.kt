package com.tubetoast.giffy.presentation.view

import com.bumptech.glide.Glide
import com.tubetoast.giffy.R
import com.tubetoast.giffy.databinding.ItemGifPreviewContentBinding
import com.tubetoast.giffy.models.presentation.GifPreview
import com.tubetoast.giffy.models.presentation.UIItem

class GifPreviewContentViewHolder(
    private val binding: ItemGifPreviewContentBinding,
) : BaseViewHolder(binding.root) {
    override fun setContent(uiItem: UIItem) {
        check(uiItem is GifPreview.Content) { "Incompatible content" }
        Glide.with(itemView)
            .load(uiItem.gif.url)
            .placeholder(R.drawable.shimmer)
            .into(binding.image)
    }
}