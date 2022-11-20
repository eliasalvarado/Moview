package com.example.moview.data.local.entity

import com.example.moview.data.remote.dto.ImageDto

data class Image(
    val imagen: String
)

fun Image.maptoDto(): ImageDto = ImageDto(
    imagen = imagen
)