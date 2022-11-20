package com.example.moview.data.Repository.titulo

import com.example.moview.data.local.entity.Titulo
import com.example.moview.data.remote.api.TituloApi
import com.example.moview.data.remote.dto.maptoEntity

class TituloRepositoryImpl(
    private val api: TituloApi
): TituloRepository {
    override suspend fun getById(id: String): Titulo? {
        val dto = api.getById(id)
        return dto?.maptoEntity()
    }

    override suspend fun getByType(type: String): List<Titulo>? {
        val filteredPlaces = api.getByType(type)
        filteredPlaces?.apply {
            return filteredPlaces.map { TituloDto -> TituloDto.maptoEntity() }
        }
        return null
    }
}