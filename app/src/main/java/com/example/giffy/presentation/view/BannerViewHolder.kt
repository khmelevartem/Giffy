package com.example.giffy.presentation.view

import com.example.giffy.databinding.ItemBannerBinding
import com.example.giffy.models.presentation.Preview
import com.example.giffy.presentation.utils.updateVisibility

class BannerViewHolder(
    private val binding: ItemBannerBinding,
) : BaseViewHolder(binding.root) {

    override fun setContent(preview: Preview) {
        check(preview is Preview.Banner) { "Incompatible content" }
        binding.apply {
            title.setText(preview.description)
            image.setImageResource(preview.resId)
            buttonAction.updateVisibility(preview.action != null)
            preview.action?.let { action ->
                buttonAction.setOnClickListener { action() }
            }
            preview.buttonTitle?.let {
                buttonAction.setText(it)
            }
        }
    }
}