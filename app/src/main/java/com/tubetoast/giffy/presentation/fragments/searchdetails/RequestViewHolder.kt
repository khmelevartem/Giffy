package com.tubetoast.giffy.presentation.fragments.searchdetails

import com.tubetoast.giffy.databinding.ItemRequestBinding
import com.tubetoast.giffy.domain.RequestActions
import com.tubetoast.giffy.models.presentation.SearchDetail
import com.tubetoast.giffy.presentation.utils.hideKeyboard

class RequestViewHolder(
    private val binding: ItemRequestBinding,
    private val requestActions: RequestActions,
) : BaseSearchDetailsViewHolder(binding.root) {
    override fun setDetail(detail: SearchDetail) {
        check(detail is SearchDetail.Request) { "incompatible detail" }
        val request = detail.request
        binding.requestText.text = request.query
        binding.requestDelete.setOnClickListener {
            requestActions.deleteRequest(request)
        }
        binding.root.setOnClickListener {
            requestActions.repeatRequest(request)
            it.hideKeyboard()
        }
    }
}