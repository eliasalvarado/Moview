package com.example.moview.data.repository.auth.user

import com.example.moview.data.local.entity.User

interface UserRepository {
    suspend fun createUser(user : User)
    suspend fun getUser(user: String): List<User>?
    suspend fun getUserByEmail(email: String) : List<User>?
}