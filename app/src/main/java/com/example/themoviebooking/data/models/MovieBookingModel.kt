package com.example.themoviebooking.data.models

import com.example.themoviebooking.data.vos.*
import com.example.themoviebooking.network.responses.CheckOutResponse

interface MovieBookingModel {

    fun getLoginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getRegisterUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUser(
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getLogout(
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaDayTimeslot(
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaSeatingPlan(
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnack(
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethod(
        onSuccess: (List<PaymentCardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCard(
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkOut(
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
    )
}