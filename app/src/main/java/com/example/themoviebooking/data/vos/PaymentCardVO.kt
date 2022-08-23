package com.example.themoviebooking.data.vos

import com.google.gson.annotations.SerializedName

data class PaymentCardVO(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?
)
