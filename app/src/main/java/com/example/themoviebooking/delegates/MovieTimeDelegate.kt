package com.example.themoviebooking.delegates

import com.example.themoviebooking.data.vos.CinemaVO
import com.example.themoviebooking.data.vos.TimeSlotVO

interface MovieTimeDelegate {
    fun onTapTime(
        cinema: CinemaVO,
        timeslot: TimeSlotVO
    )
}