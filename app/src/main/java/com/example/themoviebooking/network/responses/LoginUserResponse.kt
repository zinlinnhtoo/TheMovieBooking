package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.UserVO
import com.google.gson.annotations.SerializedName

data class LoginUserResponse(

    @SerializedName("code")
    val status: String?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: UserVO?,

    @SerializedName("token")
    val token: String?
)