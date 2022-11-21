package com.example.moview.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moview.data.local.entity.TituloEstreno

@Dao
interface TituloDao {

    @Query("SELECT * FROM TituloEstreno")
    suspend fun getTitulos(): List<TituloEstreno>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(titulo: List<TituloEstreno>)
}