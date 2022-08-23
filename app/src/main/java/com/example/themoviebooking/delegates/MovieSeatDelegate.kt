package com.example.themoviebooking.delegates

interface MovieSeatDelegate {
    fun onTapMovieSeat(
        takenSeatName: String,
        removeSeatName: String,
        totalSeat: Int,
        ticketPrice: Double
    )
}