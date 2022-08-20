package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.CinemaVO
import com.google.gson.annotations.SerializedName

data class CinemaDayTimeslotResponse(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<CinemaVO>

)
