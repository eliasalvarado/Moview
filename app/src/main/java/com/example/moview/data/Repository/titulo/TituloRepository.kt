package com.example.moview.data.Repository.titulo

import com.example.moview.data.local.entity.Titulo
import java.util.Objects

interface TituloRepository {
    suspend fun getById(id: String): Titulo?
    suspend fun getByType(type: String): List<Titulo>?
    suspend fun getPeliculasByGender(genero: String): List<Titulo>?
    suspend fun getSeriesByGender(genero: String): List<Titulo>?
    suspend fun actualizarTitulo(id:String, nuevosDatos: Map<String, Objects>): String
}