package com.example.themoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebooking.data.vos.SnackVO

@Dao
interface SnackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSnacks(snacks: List<SnackVO>)

    @Query("SELECT * FROM snacks")
    fun getAllSnacks(): List<SnackVO>

    @Query("DELETE FROM snacks")
    fun deleteSnacks()

}