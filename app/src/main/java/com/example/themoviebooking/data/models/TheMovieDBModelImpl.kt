package com.example.themoviebooking.data.models

import android.content.Context
import com.example.themoviebooking.data.vos.ActorVO
import com.example.themoviebooking.data.vos.MovieVO
import com.example.themoviebooking.network.dataagents.TheMovieDBDataAgent
import com.example.themoviebooking.network.dataagents.TheMovieDBRetrofitDataAgentImpl
import com.example.themoviebooking.persistence.MovieBookingDatabase

object TheMovieDBModelImpl : TheMovieDBModel {

    private val mTheMovieDBDataAgent: TheMovieDBDataAgent = TheMovieDBRetrofitDataAgentImpl
    private var mMovieDatabase: MovieBookingDatabase? = null

    fun initDatabase(context: Context) {
        mMovieDatabase = MovieBookingDatabase.getDBInstance(context)
    }

    override fun getNowShowingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mMovieDatabase?.movieDao()?.getAllMovies() ?: listOf())
        mTheMovieDBDataAgent.getNowShowingMovie(
            onSuccess = {
                mMovieDatabase?.movieDao()?.insertMovies(it)
                onSuccess(it)
            },
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
        val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
        movieFromDatabase?.let(onSuccess)
        mTheMovieDBDataAgent.getMovieDetail(
            movieId = movieId,
            onSuccess = {
                val movieFromDatabase =
                    mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
                mMovieDatabase?.movieDao()?.insertSingleMovie(movieFromDatabase)
                onSuccess(it)
            },
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