package com.example.moview.data.Repository.Estreno

import com.example.moview.data.local.entity.TituloEstreno

interface EstrenoRepository {
    suspend fun getEstrenos(): Resource<List<TituloEstreno>>
}