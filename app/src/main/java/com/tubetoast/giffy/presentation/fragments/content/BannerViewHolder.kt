package com.tubetoast.giffy.presentation.fragments.content

import com.tubetoast.giffy.databinding.ItemBannerBinding
import com.tubetoast.giffy.models.presentation.Banner
import com.tubetoast.giffy.models.presentation.ContentItem
import com.tubetoast.giffy.presentation.utils.updateVisibility

class BannerViewHolder(
    private val binding: ItemBannerBinding,
) : BaseContentViewHolder(binding.root) {

    override fun setContent(contentItem: ContentItem) {
        check(contentItem is Banner) { "Incompatible content" }
        binding.apply {
            title.setText(contentItem.description)
            image.setImageResource(contentItem.resId)
            buttonAction.updateVisibility(contentItem.action != null)
            contentItem.action?.let { action ->
                buttonAction.setOnClickListener { action() }
            }
            contentItem.buttonTitle?.let { title ->
                buttonAction.setText(title)
            }
        }
    }
}