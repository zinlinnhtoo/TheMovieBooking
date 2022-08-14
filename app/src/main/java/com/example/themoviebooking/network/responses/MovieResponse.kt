package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.DateVO
import com.example.themoviebooking.data.vos.MovieVO
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("dates")
    val dates: DateVO?,

    @SerializedName("results")
    val results: List<MovieVO>?
)
