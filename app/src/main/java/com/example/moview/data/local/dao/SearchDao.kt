package com.example.moview.data.local.dao

import androidx.room.*
import com.example.moview.data.local.entity.TituloEstreno
import com.example.moview.data.local.entity.TituloSearch

@Dao
interface SearchDao {
    @Query("SELECT * FROM TituloSearch")
    suspend fun getTitulos(): List<TituloSearch>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(titulo: List<TituloSearch>)

    @Update
    suspend fun update(titulo: TituloSearch)
}