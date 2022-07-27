package com.example.giffy.data

import com.example.giffy.models.data.SearchResultEntity

interface DataSource {

    fun get(query: String) : SearchResultEntity?
}