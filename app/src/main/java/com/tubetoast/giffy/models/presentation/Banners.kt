package com.tubetoast.giffy.models.presentation

import com.tubetoast.giffy.R

class Banners {

    class NotStarted(listener: BannerListener?) : Banner(
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

    class NoInternet(listener: BannerListener?) : Banner(
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

    class NoContent(listener: BannerListener?) : Banner(
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

    class UnknownError(listener: BannerListener? = null) : Banner(
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