package com.tubetoast.giffy.presentation.utils

import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.ext.android.viewModel

fun View.updateVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

inline fun <reified T : ViewModel> View.activityViewModel(): Lazy<T> =
    context.getFragmentActivity().viewModel()

inline fun <reified T : ViewModel> View.viewModel(): Lazy<T> =
    viewModelStoreOwner.viewModel()

fun Context.findFragmentActivity(): FragmentActivity? =
    (this as? ContextWrapper)?.let {
        this as? FragmentActivity
            ?: baseContext.findFragmentActivity()
    }

fun Context.getFragmentActivity(): FragmentActivity = findFragmentActivity()
    ?: throw IllegalStateException("Cannot find fragment activity from $this. " +
        "Probably, you are calling this function at inappropriate time or in inappropriate place. " +
        "Use only after attached to activity/window")

val View.viewModelStoreOwner: ViewModelStoreOwner
    get() = ViewTreeViewModelStoreOwner.get(this)
        ?: throw IllegalStateException("Cannot find viewModelStoreOwner from $this. " +
            "Probably, you are calling this function at inappropriate time or in inappropriate place. " +
            "Use only after attached to window")

fun View.hideKeyboard() {
    val imm = rootView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(rootView.windowToken, 0)
}

fun View.launchWhenCreated(block: suspend CoroutineScope.() -> Unit) =
    findViewTreeLifecycleOwner()?.lifecycleScope?.launchWhenCreated(block)

fun View.launchWhenStarted(block: suspend CoroutineScope.() -> Unit) =
    findViewTreeLifecycleOwner()?.lifecycleScope?.launchWhenStarted(block)

fun View.launchWhenResumed(block: suspend CoroutineScope.() -> Unit) =
    findViewTreeLifecycleOwner()?.lifecycleScope?.launchWhenResumed(block)
