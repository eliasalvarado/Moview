package com.example.moview.data.Repository.Search

import com.example.moview.data.local.entity.TituloSearch

interface SearchRepository {
    suspend fun getSearch(): Resource<List<TituloSearch>>
}