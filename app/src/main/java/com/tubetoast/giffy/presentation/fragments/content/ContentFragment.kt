package com.tubetoast.giffy.presentation.fragments.content

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContentFragment : Fragment(R.layout.recycler_vertical) {

    private val viewModel: ContentFragmentViewModel by viewModel()
    private val contentAdapter: ContentAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        view?.findViewById<RecyclerView>(R.id.recycler)?.apply {
            adapter = contentAdapter
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    ResourcesCompat.getDrawable(resources, R.drawable.spacer, null)?.let { setDrawable(it) }
                }
            )
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.content.collectLatest { previews ->
                contentAdapter.setContent(previews)
            }
        }
    }

    companion object {
        fun newInstance() = ContentFragment()
    }
}