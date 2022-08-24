package com.example.themoviebooking.data.vos

import com.google.gson.annotations.SerializedName

data class SnackVO(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("price")
    val price: Double?,

    @SerializedName("image")
    val image: String?,

    val quantity: Int? = 0
)
