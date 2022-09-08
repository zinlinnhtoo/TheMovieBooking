package com.example.themoviebooking.data.models

import android.content.Context
import com.example.themoviebooking.data.vos.*
import com.example.themoviebooking.network.dataagents.MovieBookingDataAgent
import com.example.themoviebooking.network.dataagents.MovieBookingRetrofitDataAgentImpl
import com.example.themoviebooking.network.responses.CheckOutRequest
import com.example.themoviebooking.persistence.MovieBookingDatabase
import com.google.gson.Gson

object MovieBookingModelImpl : MovieBookingModel {

    private val mMovieBookingDataAgent: MovieBookingDataAgent = MovieBookingRetrofitDataAgentImpl
    private var mMovieDatabase: MovieBookingDatabase? = null

    fun initDatabase(context: Context) {
        mMovieDatabase = MovieBookingDatabase.getDBInstance(context)
    }

    var userToken: String? = null

    override fun getTokenFromWelcome(onSuccess: (Boolean) -> Unit) {
        val tokenFromDatabase = mMovieDatabase?.userDao()?.getToken()
        if (tokenFromDatabase != null) {
            onSuccess(true)
        } else {
            onSuccess(false)
        }

    }

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
                userVO.token = token
                mMovieDatabase?.userDao()?.insertUser(userVO)
                this.userToken = mMovieDatabase?.userDao()?.getToken()
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
        userToken = mMovieDatabase?.userDao()?.getToken()
        mMovieDatabase?.userDao()?.getUser()?.let { onSuccess(it) }
        mMovieBookingDataAgent.getUser(
            token = userToken.orEmpty(),
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getLogout(onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        userToken = mMovieDatabase?.userDao()?.getToken()
        mMovieBookingDataAgent.getLogout(
            token = userToken.orEmpty(),
            onSuccess = {
                this.userToken = null
                mMovieDatabase?.userDao()?.deleteUser()
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
        onSuccess(mMovieDatabase?.cinemaDao()?.getAllCinemas() ?: listOf())
        mMovieBookingDataAgent.getCinemaDayTimeslot(
            token = userToken.orEmpty(),
            movieId = movieId,
            date = date,
            onSuccess = {
                mMovieDatabase?.cinemaDao()?.insertCinemas(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getCinemaSeatingPlan(
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        userToken = mMovieDatabase?.userDao()?.getToken()
        mMovieBookingDataAgent.getCinemaSeatingPlan(
            token = userToken.orEmpty(),
            cinemaDayTimeslotId = cinemaDayTimeslotId,
            bookingDate = bookingDate,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getSnack(onSuccess: (List<SnackVO>) -> Unit, onFailure: (String) -> Unit) {
        userToken = mMovieDatabase?.userDao()?.getToken()
        onSuccess(mMovieDatabase?.snackDao()?.getAllSnacks() ?: listOf())
        mMovieBookingDataAgent.getSnack(
            token = userToken.orEmpty(),
            onSuccess = {
                mMovieDatabase?.snackDao()?.insertSnacks(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getPaymentMethod(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        userToken = mMovieDatabase?.userDao()?.getToken()
        onSuccess(mMovieDatabase?.paymentMethodDao()?.getAllPaymentMethod() ?: listOf())
        mMovieBookingDataAgent.getPaymentMethod(
            token = userToken.orEmpty(),
            onSuccess = {
                mMovieDatabase?.paymentMethodDao()?.insertPaymentMethod(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.createCard(
            token = userToken.orEmpty(),
            cardNumber = cardNumber,
            cardHolder = cardHolder,
            expirationDate = expirationDate,
            cvc = cvc,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getCard(onSuccess: (List<CardVO>) -> Unit, onFailure: (String) -> Unit) {
        userToken = mMovieDatabase?.userDao()?.getToken()
        val cardListFromDatabase = mMovieDatabase?.userDao()?.getCard()
        val cardArrayList = Gson().fromJson(cardListFromDatabase, CardList::class.java)
        val cardList: MutableList<CardVO> = mutableListOf()
        cardArrayList.forEach {
            cardList.add(it)
        }
//        onSuccess(cardList)
        mMovieBookingDataAgent.getCard(
            token = userToken.orEmpty(),
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun checkOut(
        cinemaDayTimeslotId: String,
        row: String,
        seatNumber: String,
        bookingDate: String,
        totalPrice: String,
        movieId: String,
        cardId: String,
        cinemaId: String,
        snack: List<CarrierSnackVO>,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val mCheckOutRequest = CheckOutRequest(
            cinemaDayTimeslotId = cinemaDayTimeslotId,
            row = row,
            seatNumber = seatNumber,
            bookingDate = bookingDate,
            totalPrice = totalPrice,
            movieId = movieId,
            cardId = cardId,
            cinemaId = cinemaId,
            snack = snack
        )
        mMovieBookingDataAgent.checkOut(
            token = userToken.orEmpty(),
            checkOutRequest = mCheckOutRequest,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}