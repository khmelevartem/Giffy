package com.example.giffy.models.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.giffy.R
import com.example.giffy.models.domain.Gif

abstract class Preview(val viewType: Int) {

    data class Content(val gif: Gif) : Preview(PreviewViewTypes.CONTENT)

    object Shimmer : Preview(PreviewViewTypes.SHIMMER)

    abstract class Banner(
        @DrawableRes val resId: Int,
        @StringRes val description: Int,
        @StringRes val buttonTitle: Int? = null,
        private val listener: BannerListener? = null,
        actionType: String? = null,
    ) : Preview(PreviewViewTypes.BANNER) {

        val action: (() -> Unit)? =
            if (actionType.isNullOrBlank() || listener == null) {
                null
            } else {
                { listener.onAction(actionType) }
            }

        interface BannerListener {
            fun onAction(type: String)
        }
    }

    class NotStartedBanner(listener: BannerListener?) : Banner(
        R.drawable.new_search,
        R.string.description_not_started,
        R.string.button_not_started,
        listener,
        ACTION_TYPE
    ) {
        companion object {
            const val ACTION_TYPE = "not_started"
        }
    }

    class NoInternetBanner(listener: BannerListener?) : Banner(
        R.drawable.no_internet,
        R.string.description_no_internet,
        R.string.button_no_internet,
        listener,
        ACTION_TYPE
    ) {
        companion object {
            const val ACTION_TYPE = "no_internet"
        }
    }

    class NoContentBanner(listener: BannerListener?) : Banner(
        R.drawable.nothing_found,
        R.string.description_nothing_found,
        R.string.button_nothing_found,
        listener,
        ACTION_TYPE
    ) {
        companion object {
            const val ACTION_TYPE = "nothing_found"
        }
    }

    class UnknownErrorBanner(listener: BannerListener? = null) : Banner(
        R.drawable.error,
        R.string.description_unknown_error,
        R.string.button_unknown_error,
        listener,
        ACTION_TYPE
    ) {
        companion object {
            const val ACTION_TYPE = "unknown"
        }
    }
}