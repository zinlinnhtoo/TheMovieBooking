package com.example.themoviebooking.delegates

import com.example.themoviebooking.data.vos.DateVO

interface MovieDateDelegate {
    fun onTapMovieDate(date: DateVO)
}