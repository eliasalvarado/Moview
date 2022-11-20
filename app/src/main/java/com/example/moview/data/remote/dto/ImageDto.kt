package com.example.moview.data.remote.dto

import com.example.moview.data.local.entity.Image

data class ImageDto(
    val imagen: String = ""
)

fun ImageDto.maptoEntity(): Image = Image(
    imagen = imagen
)