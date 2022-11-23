package com.example.moview.data.remote.api

import com.example.moview.data.remote.dto.TituloDto
import java.util.*

interface TituloApi {
    suspend fun getById(id: String): TituloDto?
    suspend fun getByType(type: String): List<TituloDto>?
    suspend fun getPeliculasByGender(genero: String): List<TituloDto>?
    suspend fun getSeriesByGender(genero: String): List<TituloDto>?
    suspend fun actualizarTitulo(id:String, nuevosDatos: Map<String, Objects>): String
    suspend fun getAllPeliculas(): List<TituloDto>?
    suspend fun getAllSeries(): List<TituloDto>?

}