package com.example.moview.data.remote.dto

data class ResponseDetailsDto(
    val backdrop: Any,
    val critic_score: Any,
    val end_year: Any,
    val english_title: String,
    val genre_names: List<String>,
    val genres: List<Int>,
    val id: Int,
    val imdb_id: String,
    val network_names: List<Any>,
    val networks: Any,
    val original_language: String,
    val original_title: String,
    val plot_overview: Any,
    val poster: String,
    val release_date: String,
    val relevance_percentile: Double,
    val runtime_minutes: Any,
    val similar_titles: List<Int>,
    val title: String,
    val tmdb_id: Int,
    val tmdb_type: String,
    val trailer: String,
    val trailer_thumbnail: String,
    val type: String,
    val us_rating: Any,
    val user_rating: Any,
    val year: Int
)