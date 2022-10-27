package com.example.moview.datasource.model

data class APIresponse(
    val page: Int,
    val titles: List<Title>,
    val total_pages: Int,
    val total_results: Int
)