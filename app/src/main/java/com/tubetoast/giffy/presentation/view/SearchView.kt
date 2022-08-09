package com.tubetoast.giffy.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.widget.doAfterTextChanged
import com.tubetoast.giffy.databinding.SearchViewBinding
import com.tubetoast.giffy.presentation.utils.activityViewModel
import com.tubetoast.giffy.presentation.viewmodel.SearchViewModel

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
    }

    private fun initListeners() {
        binding.queryInput.doAfterTextChanged {
            viewModel.setCurrentQuery(it.toString())
        }
        binding.buttonSearch.setOnClickListener {
            viewModel.search()
        }
    }
}