package com.tubetoast.giffy.presentation.view

import com.tubetoast.giffy.databinding.ItemGifPreviewShimmerBinding
import com.tubetoast.giffy.models.presentation.GifPreview
import com.tubetoast.giffy.models.presentation.UIItem

class GifPreviewShimmerViewHolder(
    binding: ItemGifPreviewShimmerBinding,
) : BaseViewHolder(binding.root) {
    override fun setContent(uiItem: UIItem) {
        check(uiItem is GifPreview.Shimmer) { "Incompatible content" }
    }
}