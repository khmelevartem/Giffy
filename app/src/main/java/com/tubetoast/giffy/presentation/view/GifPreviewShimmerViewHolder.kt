package com.tubetoast.giffy.presentation.view

import com.tubetoast.giffy.databinding.ItemGifPreviewShimmerBinding
import com.tubetoast.giffy.models.presentation.Preview

class GifPreviewShimmerViewHolder(
    binding: ItemGifPreviewShimmerBinding,
) : BaseViewHolder(binding.root) {
    override fun setContent(preview: Preview) {
        check(preview is Preview.Shimmer) { "Incompatible content" }
    }
}