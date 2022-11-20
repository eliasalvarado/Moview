package com.example.moview.data.Repository.image

import com.example.moview.data.local.entity.Image
import com.example.moview.data.remote.api.ImageApi
import com.example.moview.data.remote.dto.maptoEntity
import com.example.moview.data.remote.firebase.image.ImageRepository

class ImageRepositoryImpl(
    private val api: ImageApi
) :ImageRepository{
    override suspend fun getImage(id: String): Image {
        val dto = api.getById(id)
        return dto.maptoEntity()
    }
}