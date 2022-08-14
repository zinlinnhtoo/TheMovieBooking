package com.example.themoviebooking.data.models

import com.example.themoviebooking.data.vos.UserVO

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
}