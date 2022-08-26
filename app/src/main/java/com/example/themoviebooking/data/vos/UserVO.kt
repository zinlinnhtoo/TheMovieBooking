package com.example.themoviebooking.data.vos

import com.google.gson.annotations.SerializedName

data class UserVO(
    @SerializedName("id")
    val id: Int? = 0,

    @SerializedName("name")
    val name: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("total_expense")
    val totalExpense: Int? = 0,

    @SerializedName("profile_image")
    val profileImage: String?,

    @SerializedName("cards")
    val cards: List<CardVO>?

)