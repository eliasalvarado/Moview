package com.example.moview.data.remote.dto

import com.example.moview.data.local.entity.Persona

data class PersonaDto(
    var foto: String = "",
    var nombre: String = "",
    var rol: String = ""
)

fun PersonaDto.maptoEntity(): Persona = Persona(
    foto = foto,
    nombre = nombre,
    rol = rol
)
