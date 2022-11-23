package com.example.moview.data.Repository.titulo

import com.example.moview.data.local.entity.Comentario
import com.example.moview.data.local.entity.Reparto
import com.example.moview.data.local.entity.Titulo
import com.example.moview.data.local.entity.maptoEntity
import com.example.moview.data.remote.api.TituloApi
import com.example.moview.data.remote.dto.ComentarioDto
import com.example.moview.data.remote.dto.RepartoDto
import com.example.moview.data.remote.dto.mapToEntity
import com.example.moview.data.remote.dto.maptoEntity

class TituloRepositoryImpl(
    private val api: TituloApi
): TituloRepository {
    override suspend fun getById(id: String): Titulo? {
        val dto = api.getById(id)
        return dto?.maptoEntity()
    }

    override suspend fun getByType(type: String): List<Titulo>? {
        val filteredTitles = api.getByType(type)
        filteredTitles?.apply {
            return filteredTitles.map { TituloDto -> TituloDto.maptoEntity() }
        }
        return null
    }

    override suspend fun getPeliculasByGender(genero: String): List<Titulo>? {
        val filteredTitles = api.getPeliculasByGender(genero)
        filteredTitles?.apply {
            return filteredTitles.map { TituloDto -> TituloDto.maptoEntity() }
        }
        return null
    }

    override suspend fun getSeriesByGender(genero: String): List<Titulo>? {
        val filteredTitles = api.getSeriesByGender(genero)
        filteredTitles?.apply {
            return filteredTitles.map { TituloDto -> TituloDto.maptoEntity() }
        }
        return null
    }

    override suspend fun actualizarPuntajeTitulo(
        id: String,
        nuevosDatos: Map<String, MutableList<Boolean>>
    ): Boolean {
        return api.actualizarPuntajeTitulo(id, nuevosDatos)
    }

    override suspend fun actualizarComentariosTitulo(
        id: String,
        nuevoComentario: Comentario
    ): Boolean {
        return api.actualizarComentariosTitulo(id, nuevoComentario.maptoEntity())
    }

    override suspend fun getReparto(id: String): List<Reparto>? {
        return api.getReparto(id)?.map { RepartoDto -> RepartoDto.mapToEntity() }
    }

    override suspend fun getComentarios(id: String): List<Comentario>? {
        return api.getComentarios(id)?.map { ComentarioDto -> ComentarioDto.maptoEntity() }
    }
}