package com.example.moview.data.local.entity

import com.example.moview.data.remote.dto.RepartoDto

data class Reparto(
    val nombre: String,
    val rol: String,
    val foto: String
)

fun Reparto.mapToDto(): RepartoDto = RepartoDto(
    nombre = nombre,
    rol = rol,
    foto = foto
)