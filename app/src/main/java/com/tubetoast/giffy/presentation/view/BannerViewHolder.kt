package com.tubetoast.giffy.presentation.view

import com.tubetoast.giffy.databinding.ItemBannerBinding
import com.tubetoast.giffy.models.presentation.Preview
import com.tubetoast.giffy.presentation.utils.updateVisibility

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
            preview.buttonTitle?.let { title ->
                buttonAction.setText(title)
            }
        }
    }
}