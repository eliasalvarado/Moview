package com.example.moview.datasource.api

import com.example.moview.datasource.model.APIresponse
import com.example.moview.datasource.model.APIresponseTitle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchmodeAPI {

    @GET("/v1/list-titles/?apiKey=NJvzEz4BNnsCPqmLwIiNtXjuW1jfLW4hTYYr0Prq&types=movie")
    fun getMovies(

    ): Call<APIresponse>

    @GET("/v1/list-titles/?apiKey={apiKey}&types=tv_series")
    fun getSeries(
        @Query("apiKey") apiKey: String
    ): Call<APIresponse>

    @GET("/v1/title/{title_id}/details/?apiKey={apiKey}&language=es")
    fun getTitle(
        @Query("title_id") idTitle: Int,
        @Query("apiKey") apiKey: String
    ): Call<APIresponseTitle>

}