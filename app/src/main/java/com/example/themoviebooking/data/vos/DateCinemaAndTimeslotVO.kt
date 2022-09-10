package com.example.themoviebooking.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.themoviebooking.persistence.typeconverters.CinemaListTypeConverter

@Entity(tableName = "date_cinema_and_timeslots")
@TypeConverters(
    CinemaListTypeConverter::class
)
data class DateCinemaAndTimeslotVO(
    @PrimaryKey
    var date: String = "",

    @ColumnInfo(name = "cinemas")
    var cinemas: List<CinemaVO>?
)
