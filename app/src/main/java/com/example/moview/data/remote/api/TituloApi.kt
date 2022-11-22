package com.example.moview.data.remote.api

import com.example.moview.data.remote.dto.ComentarioDto
import com.example.moview.data.remote.dto.TituloDto

interface TituloApi {
    suspend fun getById(id: String): TituloDto?
    suspend fun getByType(type: String): List<TituloDto>?
    suspend fun getPeliculasByGender(genero: String): List<TituloDto>?
    suspend fun getSeriesByGender(genero: String): List<TituloDto>?
    suspend fun actualizarPuntajeTitulo(id:String, nuevosDatos: Map<String, MutableList<Boolean>>): Boolean
    suspend fun actualizarComentariosTitulo(id: String, nuevoComentario: ComentarioDto): Boolean
}