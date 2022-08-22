package com.example.themoviebooking.delegates

interface MovieViewHolderDelegate {
    fun onTapMovie(
        movieId: Int,
        movieTitle: String
    )
}