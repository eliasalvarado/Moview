package com.example.moview.data.remote.dto

import com.example.moview.data.local.entity.TituloEstreno

data class TitleDto(
    val id: Int,
    val imdb_id: String,
    val title: String,
    val tmdb_id: Int,
    val tmdb_type: String,
    val type: String,
    val year: Int
)

fun TitleDto.mapToEntity(): TituloEstreno = TituloEstreno(
    id = id.toString(),
    title = title,
    fecha = year.toString(),
    imagen = ""
)