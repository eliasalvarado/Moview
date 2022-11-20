package com.example.moview.data.local.entity

import com.example.moview.data.remote.dto.ComentarioDto

data class Comentario(
    var autor: String,
    var comentario: String,
    var critico: Boolean
)

fun Comentario.maptoEntity(): ComentarioDto = ComentarioDto(
    autor = autor,
    comentario = comentario,
    critico = critico
)
