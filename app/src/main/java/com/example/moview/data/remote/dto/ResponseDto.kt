package com.example.moview.data.remote.dto


data class ResponseDto(
    val page: Int,
    val titleDtos: List<TitleDto>,
    val total_pages: Int,
    val total_results: Int
)

