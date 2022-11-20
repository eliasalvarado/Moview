package com.example.moview.data.remote.api

import com.example.moview.data.remote.dto.ImageDto

interface ImageApi {
    suspend fun getById(id: String): ImageDto
}