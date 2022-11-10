package com.example.moview.data.repository.auth.user

import com.example.moview.data.local.entity.User
import com.example.moview.data.local.entity.maoToDto
import com.example.moview.data.remote.api.UserApi
import com.example.moview.data.remote.dto.mapToEntity

class UserRepositoryImpl(
    private val api: UserApi
) : UserRepository {
    override suspend fun createUser(user: User) {
        val dtoInsert = user.maoToDto()
        api.insert(dtoInsert)
    }

    override suspend fun getUser(user: String): List<User>? {
        val dto = api.getByUser(user)
        return dto?.map{ dto -> dto.mapToEntity()}
    }

    override suspend fun getUserByEmail(email: String): List<User>? {
        val dto = api.getByEmail(email)
        return dto?.map{ dto -> dto.mapToEntity()}
    }
}