package com.tubetoast.giffy.presentation.fragments.searchdetails

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.tubetoast.giffy.R
import com.tubetoast.giffy.presentation.fragments.RecyclerFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchDetailsFragment : RecyclerFragment() {

    private val viewModel: SearchDetailsFragmentViewModel by viewModel()
    private val searchDetailsAdapter: SearchDetailsAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        recycler?.apply {
            adapter = searchDetailsAdapter
            addItemDecoration(
                //todo()
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    ResourcesCompat.getDrawable(resources, R.drawable.spacer, null)?.let { setDrawable(it) }
                }
            )
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.history.collectLatest { requests ->
                searchDetailsAdapter.setHistory(requests)
            }
        }
        // lifecycleScope.launchWhenStarted {
        //     viewModel.filters.collectLatest { filters ->
        //         searchDetailsAdapter.setFilters(filters)
        //     }
        // }
    }

    companion object {
        const val TAG = "SearchDetailsFragment"
        fun newInstance() = SearchDetailsFragment()
    }
}