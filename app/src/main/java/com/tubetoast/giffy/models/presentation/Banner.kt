package com.tubetoast.giffy.models.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class Banner(
    @DrawableRes val resId: Int,
    @StringRes val description: Int,
    @StringRes val buttonTitle: Int? = null,
    private val listener: BannerListener? = null,
    actionType: String? = null,
) : UIItem(ViewTypes.BANNER) {

    val action: (() -> Unit)? =
        if (actionType.isNullOrBlank() || listener == null) {
            null
        } else {
            { listener.onBannerAction(actionType) }
        }

    interface BannerListener {
        fun onBannerAction(type: String)
    }
}
