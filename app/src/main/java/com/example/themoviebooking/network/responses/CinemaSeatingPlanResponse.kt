package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.MovieSeatVO
import com.google.gson.annotations.SerializedName

data class CinemaSeatingPlanResponse(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: List<List<MovieSeatVO>>

)
