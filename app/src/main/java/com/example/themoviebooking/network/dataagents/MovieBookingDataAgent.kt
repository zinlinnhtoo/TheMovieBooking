package com.example.themoviebooking.network.dataagents

import com.example.themoviebooking.data.vos.UserVO

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
}