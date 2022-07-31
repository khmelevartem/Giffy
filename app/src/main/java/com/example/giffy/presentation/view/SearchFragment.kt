package com.example.giffy.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.giffy.R
import com.example.giffy.databinding.FragmentSearchBinding
import com.example.giffy.models.presentation.Preview
import com.example.giffy.presentation.viewmodel.SearchFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchFragmentViewModel by viewModel()
    private val binding: FragmentSearchBinding get() = _binding!!
    private var _binding: FragmentSearchBinding? = null
    private val previewsAdapter: GifPreviewAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentSearchBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initRecycler() {
        binding.resultImages.apply {
            adapter = previewsAdapter
            itemAnimator = null
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    ResourcesCompat.getDrawable(resources, R.drawable.spacer, null)?.let { setDrawable(it) }
                }
            )
        }
    }

    private fun initListeners() {
        binding.queryInput.doAfterTextChanged {
            viewModel.setCurrentQuery(it.toString())
        }
        binding.buttonSearch.setOnClickListener {
            viewModel.search()
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.previews.collectLatest {
                setItems(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.alert.collectLatest {
                showAlert(it)
            }
        }
    }

    private fun showAlert(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setItems(previews: List<Preview>) {
        previewsAdapter.setContent(previews)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}