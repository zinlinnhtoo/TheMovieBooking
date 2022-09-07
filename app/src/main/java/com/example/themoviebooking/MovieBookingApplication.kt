package com.example.themoviebooking

import android.app.Application
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.data.models.TheMovieDBModelImpl

class MovieBookingApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MovieBookingModelImpl.initDatabase(applicationContext)
        TheMovieDBModelImpl.initDatabase(applicationContext)
    }
}