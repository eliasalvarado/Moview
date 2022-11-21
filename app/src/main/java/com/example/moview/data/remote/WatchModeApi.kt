package com.example.moview.data.remote

import com.example.moview.data.remote.dto.ResponseDto
import retrofit2.http.GET

interface WatchModeApi {
    @GET("/list-titles/?apiKey=NJvzEz4BNnsCPqmLwIiNtXjuW1jfLW4hTYYr0Prq&types=movie&sort_by=release_date_desc&sort_by=release_date_desc")
    suspend fun getEstrenos(): ResponseDto
}