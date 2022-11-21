package com.example.moview.data.remote.firebase.image

import com.example.moview.data.local.entity.Image

interface ImageRepository {
    suspend fun getImage(id: String) : Image?
}