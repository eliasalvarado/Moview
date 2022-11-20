package com.example.moview.data.local.entity

import com.example.moview.data.remote.dto.TituloDto

data class Titulo(
    var banner: String,
    var comentarios: List<Comentario>,
    var fecha_estreno: String,
    var genero: String,
    var poster: String,
    var puntaje: List<Boolean>,
    var reparto: List<Persona>,
    var sinopsis: String,
    var title: String,
    var trailer: String,
    var year: String,
)

fun Titulo.maptoDto(): TituloDto = TituloDto(
    banner = banner,
    comentarios = comentarios,
    fecha_estreno = fecha_estreno,
    genero = genero,
    poster = poster,
    puntaje = puntaje,
    reparto = reparto,
    sinopsis = sinopsis,
    title = title,
    trailer = trailer,
    year = year
)
