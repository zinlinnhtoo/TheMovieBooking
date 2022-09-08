package com.example.themoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebooking.data.vos.CinemaVO

@Dao
interface CinemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemas(cinemas: List<CinemaVO>)

    @Query("SELECT * FROM cinemas")
    fun getAllCinemas(): List<CinemaVO>

    @Query("DELETE FROM cinemas")
    fun deleteAllCinemas()
}