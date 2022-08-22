package com.example.themoviebooking.delegates

interface MovieTimeDelegate {
    fun onTapTime(
        cinemaId: Int?,
        timeslotId: Int?
    )
}