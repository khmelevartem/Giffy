package com.tubetoast.giffy.models.presentation

import com.tubetoast.giffy.models.domain.SearchRequest

sealed class SearchDetail(val viewType: SearchDetailsViewType) {
    data class Request(val request: SearchRequest) : SearchDetail(SearchDetailsViewType.REQUEST)
    data class Filter(val filter: String) : SearchDetail(SearchDetailsViewType.FILTER)
}
