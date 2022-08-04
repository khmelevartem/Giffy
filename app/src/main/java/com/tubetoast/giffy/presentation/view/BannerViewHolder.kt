package com.tubetoast.giffy.presentation.view

import com.tubetoast.giffy.databinding.ItemBannerBinding
import com.tubetoast.giffy.models.presentation.Banner
import com.tubetoast.giffy.models.presentation.UIItem
import com.tubetoast.giffy.presentation.utils.updateVisibility

class BannerViewHolder(
    private val binding: ItemBannerBinding,
) : BaseViewHolder(binding.root) {

    override fun setContent(uiItem: UIItem) {
        check(uiItem is Banner) { "Incompatible content" }
        binding.apply {
            title.setText(uiItem.description)
            image.setImageResource(uiItem.resId)
            buttonAction.updateVisibility(uiItem.action != null)
            uiItem.action?.let { action ->
                buttonAction.setOnClickListener { action() }
            }
            uiItem.buttonTitle?.let { title ->
                buttonAction.setText(title)
            }
        }
    }
}