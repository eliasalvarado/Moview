package com.example.moview.data.localSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.moview.data.dataSource.model.UsersTable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(UsersTable: UsersTable)

    @Update
    suspend fun updateCharacter(UsersTable: UsersTable)
}