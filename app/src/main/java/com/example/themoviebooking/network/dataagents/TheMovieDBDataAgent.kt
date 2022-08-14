package com.example.themoviebooking.network.dataagents

import com.example.themoviebooking.data.vos.MovieVO

interface TheMovieDBDataAgent {

    fun getNowShowingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getComingSoonMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}