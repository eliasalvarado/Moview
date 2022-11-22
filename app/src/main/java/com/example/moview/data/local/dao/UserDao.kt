package com.example.moview.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moview.datasource.model.UserTable

@Dao
interface UserDao {

    @Query("SELECT * FROM UserTable")
    suspend fun getTheUser():List<UserTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userTable: UserTable)

    @Query("DELETE FROM UserTable")
    suspend fun deleteAll()
}