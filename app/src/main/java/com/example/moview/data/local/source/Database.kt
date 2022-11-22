package com.example.moview.data.local.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moview.data.local.dao.UserDao
import com.example.moview.datasource.model.UserTable

@Database(entities = [UserTable::class],version=1)
abstract class Database : RoomDatabase(){
    abstract fun userDao(): UserDao
}