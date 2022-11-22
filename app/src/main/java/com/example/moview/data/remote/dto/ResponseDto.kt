package com.example.moview.data.remote.dto

import com.google.gson.annotations.SerializedName


data class ResponseDto(
    val page: Int,
    @SerializedName("titles") val titleDtos: List<TitleDto>,
    val total_pages: Int,
    val total_results: Int
)

