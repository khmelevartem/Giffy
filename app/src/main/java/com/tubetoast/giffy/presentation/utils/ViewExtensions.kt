package com.tubetoast.giffy.presentation.utils

import android.view.View

fun View.updateVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}