package com.example.moview.data.remote.dto

import com.example.moview.data.local.entity.Comentario
import com.example.moview.data.local.entity.Persona
import com.example.moview.data.local.entity.Titulo

data class TituloDto(
    var banner: String = "",
    var comentarios: List<Comentario> = listOf(),
    var fecha_estreno: String = "",
    var genero: String = "",
    var poster: String = "",
    var puntaje: List<Boolean> = listOf(),
    var reparto: List<Persona> = listOf(),
    var sinopsis: String = "",
    var title: String = "",
    var trailer: String = "",
    var year: String = ""
)

fun TituloDto.maptoEntity(): Titulo = Titulo(
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
