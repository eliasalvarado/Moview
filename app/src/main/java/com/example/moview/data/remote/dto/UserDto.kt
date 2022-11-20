package com.example.moview.data.remote.dto

import com.example.moview.data.local.entity.User

data class UserDto(
    val user: String = "",
    val email: String = "",
    val pasword: String = "",
    val critico: Boolean = false,
    val perfil: String = ""
)

fun UserDto.mapToEntity() : User = User(
    user = user,
    email = email,
    pasword = pasword,
    critico = critico,
    perfil = perfil
)