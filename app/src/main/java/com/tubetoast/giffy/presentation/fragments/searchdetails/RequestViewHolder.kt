package com.tubetoast.giffy.presentation.fragments.searchdetails

import com.tubetoast.giffy.databinding.ItemRequestBinding
import com.tubetoast.giffy.models.domain.SearchRequest

class RequestViewHolder(
    private val binding: ItemRequestBinding,
) : BaseSearchDetailsViewHolder(binding.root) {
    override fun setDetail(request: SearchRequest) {
        binding.requestText.text = request.query
    }
}