package com.example.themoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebooking.data.vos.TimeSlotVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TimeslotListTypeConverter {
    @TypeConverter
    fun toString(timeslotList: List<TimeSlotVO>): String {
        return Gson().toJson(timeslotList)
    }

    @TypeConverter
    fun toTimeslotList(timeslotListJsonString: String): List<TimeSlotVO> {
        val timeslotListType = object : TypeToken<List<TimeSlotVO>>() {}.type
        return Gson().fromJson(timeslotListJsonString, timeslotListType)
    }
}