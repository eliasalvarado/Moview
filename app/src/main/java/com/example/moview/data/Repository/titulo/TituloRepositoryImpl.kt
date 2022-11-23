package com.example.moview.data.Repository.titulo

import com.example.moview.data.local.entity.Titulo
import com.example.moview.data.remote.api.TituloApi
import com.example.moview.data.remote.dto.TituloDto
import com.example.moview.data.remote.dto.maptoEntity
import java.util.*

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

    override suspend fun actualizarTitulo(id: String, nuevosDatos: Map<String, Objects>): String {
        return api.actualizarTitulo(id, nuevosDatos)
    }



}