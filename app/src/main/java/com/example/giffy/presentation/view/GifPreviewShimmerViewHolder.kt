package com.example.giffy.presentation.view

import com.example.giffy.databinding.ItemGifPreviewShimmerBinding
import com.example.giffy.models.presentation.Preview

class GifPreviewShimmerViewHolder(
    binding: ItemGifPreviewShimmerBinding,
) : BaseViewHolder(binding.root) {
    override fun setContent(preview: Preview) {
        check(preview is Preview.Shimmer) { "Incompatible content" }
    }
}