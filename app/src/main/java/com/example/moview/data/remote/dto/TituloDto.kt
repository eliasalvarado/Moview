package com.example.moview.data.remote.dto

import com.example.moview.data.local.entity.Comentario
import com.example.moview.data.local.entity.Persona
import com.example.moview.data.local.entity.Reparto
import com.example.moview.data.local.entity.Titulo

data class TituloDto(
    var id: String = "",
    var banner: String = "",
    var comentarios: MutableList<Comentario> = ArrayList(),
    var fecha_estreno: String = "",
    var generos: MutableList<String> = ArrayList(),
    var poster: String = "",
    var puntaje: MutableList<Boolean> = ArrayList(),
    var reparto: MutableList<Reparto> = ArrayList(),
    var sinopsis: String = "",
    var title: String = "",
    var trailer: String = "",
    var year: String = ""
)

fun TituloDto.maptoEntity(): Titulo = Titulo(
    id = id,
    banner = banner,
    comentarios = comentarios,
    fecha_estreno = fecha_estreno,
    generos = generos,
    poster = poster,
    puntaje = puntaje,
    reparto = reparto,
    sinopsis = sinopsis,
    title = title,
    trailer = trailer,
    year = year
)
