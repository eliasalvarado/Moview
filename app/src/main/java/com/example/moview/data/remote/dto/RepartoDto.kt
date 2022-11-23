package com.example.moview.data.remote.dto

import com.example.moview.data.local.entity.Reparto

data class RepartoDto(
    val nombre: String ="",
    val rol: String="",
    val foto: String=""
)

fun RepartoDto.mapToEntity(): Reparto = Reparto(
    nombre = nombre,
    rol = rol,
    foto = foto
)
