package com.example.moview.data.dataSource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersTable (
    @PrimaryKey val id:Int,
    val nameUser: String,
    val pasword: String,
    val critico: Boolean,
    val activo: Boolean,
    val imagen: String
    )


