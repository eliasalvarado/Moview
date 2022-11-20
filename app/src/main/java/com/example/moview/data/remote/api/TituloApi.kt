package com.example.moview.data.remote.api

import com.example.moview.data.remote.dto.TituloDto

interface TituloApi {
    suspend fun getById(id: String): TituloDto?
    suspend fun getByType(type: String): List<TituloDto>?
}