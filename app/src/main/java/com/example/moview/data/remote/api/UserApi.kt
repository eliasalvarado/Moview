package com.example.moview.data.remote.api

import com.example.moview.data.remote.dto.UserDto

interface UserApi {
    suspend fun insert(userDto: UserDto)
    suspend fun getByUser(user: String): List<UserDto>?
    suspend fun getByEmail(user: String): List<UserDto>?
    suspend fun deleteUser(email: String)
}

