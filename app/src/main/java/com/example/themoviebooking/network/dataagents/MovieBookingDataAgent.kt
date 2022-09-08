package com.example.themoviebooking.network.dataagents

import com.example.themoviebooking.data.vos.*
import com.example.themoviebooking.network.responses.CheckOutRequest

interface MovieBookingDataAgent {

    fun getLoginUser(
        email: String,
        password: String,
        onSuccess: (Pair<UserVO, String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getRegisterUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<UserVO, String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUser(
        token: String,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getLogout(
        token: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaDayTimeslot(
        token: String,
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaSeatingPlan(
        token: String,
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnack(
        token: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethod(
        token: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        token: String,
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCard(
        token: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkOut(
        token: String,
        checkOutRequest: CheckOutRequest,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    )
}