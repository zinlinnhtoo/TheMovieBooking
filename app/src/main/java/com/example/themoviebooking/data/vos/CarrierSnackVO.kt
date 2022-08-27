package com.example.themoviebooking.data.vos

import com.google.gson.annotations.SerializedName

data class CarrierSnackVO(
    @SerializedName("id")
    val id: String?,

    @SerializedName("quantity")
    val quantity: String?
)

class CarrierSnackList: ArrayList<CarrierSnackVO>()
