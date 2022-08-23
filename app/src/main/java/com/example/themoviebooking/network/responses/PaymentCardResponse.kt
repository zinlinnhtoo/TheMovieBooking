package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.PaymentCardVO
import com.google.gson.annotations.SerializedName

data class PaymentCardResponse(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: List<PaymentCardVO>?
)
