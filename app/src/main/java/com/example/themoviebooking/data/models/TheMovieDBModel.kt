package com.example.themoviebooking.data.models

import com.example.themoviebooking.data.vos.ActorVO
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

    fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}