package com.example.themoviebooking.data.models

import android.content.Context
import com.example.themoviebooking.data.vos.ActorVO
import com.example.themoviebooking.data.vos.COMING_SOON
import com.example.themoviebooking.data.vos.MovieVO
import com.example.themoviebooking.data.vos.NOW_SHOWING
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
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_SHOWING) ?: listOf())
        mTheMovieDBDataAgent.getNowShowingMovie(
            onSuccess = {
                it.forEach { movie ->
                    movie.type = NOW_SHOWING
                }
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
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = COMING_SOON) ?: listOf())
        mTheMovieDBDataAgent.getComingSoonMovie(
            onSuccess = {
                    it.forEach { movie ->
                        movie.type = COMING_SOON
                    }
                    mMovieDatabase?.movieDao()?.insertMovies(it)
                    onSuccess(it)
            },
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
                it.type = movieFromDatabase?.type
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