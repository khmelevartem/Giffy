package com.tubetoast.giffy.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.tubetoast.giffy.databinding.SearchViewBinding
import com.tubetoast.giffy.presentation.utils.activityViewModel
import com.tubetoast.giffy.presentation.utils.hideKeyboard

class SearchView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    @AttrRes styleRes: Int = 0,
) : LinearLayout(context, attributeSet, styleRes) {

    private val viewModel: SearchViewModel by activityViewModel()
    private val binding: SearchViewBinding =
        SearchViewBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        findViewTreeLifecycleOwner()?.lifecycleScope?.launchWhenResumed {
            viewModel.loadingQuery.collect { query ->
                binding.queryInput.apply {
                    if(text.toString() != query) setText(query)
                    clearFocus()
                    hideKeyboard()
                }
            }
        }
    }

    private fun initListeners() {
        binding.queryInput.apply {
            onFocusChangeListener = OnFocusChangeListener { view, focused ->
                if (focused) viewModel.startFormingRequest()
            }
            doAfterTextChanged {
                viewModel.setCurrentQuery(it.toString())
            }
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH)  viewModel.search()
                actionId == EditorInfo.IME_ACTION_SEARCH
            }
        }
        binding.buttonSearch.setOnClickListener {
            viewModel.search()
        }
    }
}