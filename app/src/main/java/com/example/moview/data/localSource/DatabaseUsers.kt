package com.example.moview.data.localSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moview.data.dataSource.model.UsersTable

@Database(entities = [UsersTable::class], version = 1)
abstract class DatabaseUsers : RoomDatabase(){
    abstract fun UserDao(): UserDao
}