package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.CheckOutVO
import com.google.gson.annotations.SerializedName

data class CheckOutResponse(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: CheckOutVO?
)
