package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.CarrierSnackVO

data class CheckOutRequest(
    val cinemaDayTimeslotId: Int,
    val row: String,
    val seatNumber: String,
    val bookingDate: String,
    val totalPrice: String,
    val movieId: String,
    val cardId: String,
    val cinemaId: String,
    val snacks: List<CarrierSnackVO>
)
