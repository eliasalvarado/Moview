package com.example.moview.datasource.api

import com.example.moview.datasource.model.APIresponse
import com.example.moview.datasource.model.APIresponseTitle
import retrofit2.http.GET
import retrofit2.http.Path

interface WatchmodeAPI {

    @GET("/v1/list-titles/?apiKey={apiKey}&types=movie")
    fun getMovies(
        @Path("apiKey") apiKey: String
    ): APIresponse

    @GET("/v1/list-titles/?apiKey={apiKey}&types=tv_series")
    fun getSeries(
        @Path("apiKey") apiKey: String
    ): APIresponse

    @GET("/v1/title/{title_id}/details/?apiKey={apiKey}&language=es")
    fun getTitle(
        @Path("title_id") idTitle: Int,
        @Path("apiKey") apiKey: String
    ): APIresponseTitle

}