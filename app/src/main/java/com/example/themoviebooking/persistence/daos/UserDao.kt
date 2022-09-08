package com.example.themoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebooking.data.vos.UserVO

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserVO?)

    @Query("SELECT * FROM users")
    fun getUser(): UserVO?

    @Query("SELECT token FROM users")
    fun getToken(): String?

    @Query("DELETE FROM users")
    fun deleteUser()
}