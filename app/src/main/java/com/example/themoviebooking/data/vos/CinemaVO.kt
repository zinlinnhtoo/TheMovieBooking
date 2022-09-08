package com.example.themoviebooking.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.themoviebooking.persistence.typeconverters.TimeslotListTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cinemas")
@TypeConverters(
    TimeslotListTypeConverter::class
)
data class CinemaVO(
    @SerializedName("cinema_id")
    @PrimaryKey
    val cinemaId: Int?,

    @SerializedName("cinema")
    @ColumnInfo(name = "cinema")
    val cinema: String?,

    @SerializedName("timeslots")
    @ColumnInfo(name = "timeslots")
    val timeSlots: List<TimeSlotVO>,
)
