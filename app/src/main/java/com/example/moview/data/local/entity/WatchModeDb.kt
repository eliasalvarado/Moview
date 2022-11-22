package com.example.moview.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moview.data.local.dao.TituloDao

@Database(
    entities = [TituloEstreno::class],
    version = 1
)
abstract class WatchModeDb : RoomDatabase() {
    abstract fun tituloDao(): TituloDao
}