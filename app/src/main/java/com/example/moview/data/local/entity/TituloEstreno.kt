package com.example.moview.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TituloEstreno(
    @PrimaryKey var id: String,
    var title: String,
    var fecha: String,
    var imagen: String
)
