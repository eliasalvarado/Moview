package com.example.moview.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserTable (
    @PrimaryKey val id: Int,
    val user: String,
    val email: String,
    val pasword: String,
    val critico: Boolean,
    val perfil: String
    )

