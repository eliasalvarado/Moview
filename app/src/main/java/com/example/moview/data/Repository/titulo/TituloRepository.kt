package com.example.moview.data.Repository.titulo

import com.example.moview.data.local.entity.Comentario
import com.example.moview.data.local.entity.Reparto
import com.example.moview.data.local.entity.Titulo

interface TituloRepository {
    suspend fun getById(id: String): Titulo?
    suspend fun getByType(type: String): List<Titulo>?
    suspend fun getPeliculasByGender(genero: String): List<Titulo>?
    suspend fun getSeriesByGender(genero: String): List<Titulo>?
    suspend fun actualizarPuntajeTitulo(id:String, nuevosDatos: Map<String, MutableList<Boolean>>): Boolean
    suspend fun actualizarComentariosTitulo(id: String, nuevoComentario: Comentario): Boolean
    suspend fun getReparto(id: String): List<Reparto>?
}