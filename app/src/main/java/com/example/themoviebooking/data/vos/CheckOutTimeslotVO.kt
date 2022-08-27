package com.example.themoviebooking.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutTimeslotVO(
    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId: String?,

    @SerializedName("start_time")
    val startTime: String?
)
