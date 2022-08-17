package com.tubetoast.giffy.presentation.fragments.content

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.tubetoast.giffy.R
import com.tubetoast.giffy.presentation.fragments.RecyclerFragment
import com.tubetoast.giffy.presentation.view.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContentFragment : RecyclerFragment() {

    private val viewModel: ContentFragmentViewModel by viewModel()
    private val searchViewModel: SearchViewModel by activityViewModels<SearchViewModel>()
    private val contentAdapter: ContentAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        recycler?.apply {
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
        lifecycleScope.launchWhenStarted {
            viewModel.bannerActions.focusOnSearch.collectLatest {
                searchViewModel.startFormingRequest(needsReset = true)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.bannerActions.doSearch.collectLatest {
                searchViewModel.search()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.bannerActions.finish.collectLatest {
                activity?.finish()
            }
        }
    }

    companion object {
        const val TAG = "ContentFragment"

        fun newInstance() = ContentFragment()
    }
}