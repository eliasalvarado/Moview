package com.example.moview.data.remote.dto

import com.example.moview.data.local.entity.Comentario

data class ComentarioDto(
    var autor: String = "",
    var comentario: String = "",
    var critico: Boolean = false
)

fun ComentarioDto.maptoEntity(): Comentario = Comentario(
    autor = autor,
    comentario = comentario,
    critico = critico
)
