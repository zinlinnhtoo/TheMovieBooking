package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.MovieVO
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<MovieVO>?
)
