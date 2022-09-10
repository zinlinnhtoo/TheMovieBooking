package com.example.themoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebooking.data.vos.DateCinemaAndTimeslotVO

@Dao
interface DateCinemaAndTimeslotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemaListWithDate(dateCinemaAndTimeslot: DateCinemaAndTimeslotVO)

    @Query("SELECT * FROM date_cinema_and_timeslots WHERE date = :date")
    fun getCinemaListByDate(date: String): List<DateCinemaAndTimeslotVO>

    @Query("DELETE FROM date_cinema_and_timeslots")
    fun deleteAllDateCinemaAndTimeslot()
}