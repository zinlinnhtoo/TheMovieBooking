package com.example.themoviebooking.data.models

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
}