package com.example.themoviebooking.data.models

import com.example.themoviebooking.data.vos.CinemaVO
import com.example.themoviebooking.data.vos.MovieSeatVO
import com.example.themoviebooking.data.vos.UserVO
import com.example.themoviebooking.network.dataagents.MovieBookingDataAgent
import com.example.themoviebooking.network.dataagents.MovieBookingRetrofitDataAgentImpl

object MovieBookingModelImpl: MovieBookingModel {

    private val mMovieBookingDataAgent: MovieBookingDataAgent = MovieBookingRetrofitDataAgentImpl

    var userToken: String? = null

    override fun getLoginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getLoginUser(
            email = email,
            password = password,
            onSuccess = {
                val userVO = it.first
                val token = it.second

                this.userToken = token

                onSuccess()
            },
            onFailure = onFailure
        )
    }

    override fun getRegisterUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getRegisterUser(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = {
                val userVO = it.first
                val token = it.second

                this.userToken = token

                onSuccess()
            },
            onFailure = onFailure
        )
    }

    override fun getUser(onSuccess: (UserVO) -> Unit, onFailure: (String) -> Unit) {
        mMovieBookingDataAgent.getUser(
            token = userToken.orEmpty(),
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getLogout(onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        mMovieBookingDataAgent.getLogout(
            token = userToken.orEmpty(),
            onSuccess = {
                this.userToken = null
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getCinemaDayTimeslot(
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getCinemaDayTimeslot(
            token = userToken.orEmpty(),
            movieId = movieId,
            date = date,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getCinemaSeatingPlan(
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getCinemaSeatingPlan(
            token = userToken.orEmpty(),
            cinemaDayTimeslotId = cinemaDayTimeslotId,
            bookingDate = bookingDate,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}