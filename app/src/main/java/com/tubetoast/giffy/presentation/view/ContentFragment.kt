package com.tubetoast.giffy.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.R
import com.tubetoast.giffy.presentation.viewmodel.ContentFragmentViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContentFragment : Fragment() {

    private val viewModel: ContentFragmentViewModel by viewModel()
    private val previewsAdapter: GifPreviewAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.fragment_content, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        view?.findViewById<RecyclerView>(R.id.result_images)?.apply {
            adapter = previewsAdapter
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
                previewsAdapter.setContent(previews)
            }
        }
    }

    companion object {
        fun newInstance() = ContentFragment()
    }
}