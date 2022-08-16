package com.example.themoviebooking.data.models

import com.example.themoviebooking.data.vos.ActorVO
import com.example.themoviebooking.data.vos.MovieVO
import com.example.themoviebooking.network.dataagents.TheMovieDBDataAgent
import com.example.themoviebooking.network.dataagents.TheMovieDBRetrofitDataAgentImpl

object TheMovieDBModelImpl: TheMovieDBModel {

    private val mTheMovieDBDataAgent: TheMovieDBDataAgent = TheMovieDBRetrofitDataAgentImpl

    override fun getNowShowingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBDataAgent.getNowShowingMovie(
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getComingSoonMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBDataAgent.getComingSoonMovie(
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBDataAgent.getMovieDetail(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBDataAgent.getCreditsByMovie(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}