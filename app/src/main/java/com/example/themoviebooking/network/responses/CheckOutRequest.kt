package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.CarrierSnackVO
import com.google.gson.annotations.SerializedName

data class CheckOutRequest(
    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId: String?,

    @SerializedName("row")
    val row: String?,

    @SerializedName("seat_number")
    val seatNumber: String?,

    @SerializedName("booking_date")
    val bookingDate: String?,

    @SerializedName("total_price")
    val totalPrice: String?,

    @SerializedName("movie_id")
    val movieId: String?,

    @SerializedName("card_id")
    val cardId: String?,

    @SerializedName("cinema_id")
    val cinemaId: String?,

    @SerializedName("snacks")
    val snack: List<CarrierSnackVO>?
)
