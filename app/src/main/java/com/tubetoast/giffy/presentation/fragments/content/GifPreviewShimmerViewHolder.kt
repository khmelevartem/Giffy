package com.tubetoast.giffy.presentation.fragments.content

import com.tubetoast.giffy.databinding.ItemGifPreviewShimmerBinding
import com.tubetoast.giffy.models.presentation.GifPreview
import com.tubetoast.giffy.models.presentation.ContentItem

class GifPreviewShimmerViewHolder(
    binding: ItemGifPreviewShimmerBinding,
) : BaseContentViewHolder(binding.root) {
    override fun setContent(contentItem: ContentItem) {
        check(contentItem is GifPreview.Shimmer) { "Incompatible content" }
    }
}