package com.example.themoviebooking.data.vos

data class CarrierSnackVO(
    val id: Int,
    val quantity: Int
)

class CarrierSnackList: ArrayList<CarrierSnackVO>()
