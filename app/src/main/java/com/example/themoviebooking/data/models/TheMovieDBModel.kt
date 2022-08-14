package com.example.themoviebooking.data.models

import com.example.themoviebooking.data.vos.MovieVO

interface TheMovieDBModel {

    fun getNowShowingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getComingSoonMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}