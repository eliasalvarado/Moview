package com.example.moview.data.local.entity

import com.example.moview.data.remote.dto.UserDto

data class User(
    val user: String,
    val email: String,
    val pasword: String,
    val critico: Boolean,
    val perfil: String
)

fun User.maoToDto() : UserDto = UserDto(
    user = user,
    email = email,
    pasword = pasword,
    critico = critico,
    perfil = perfil
)