package com.example.themoviebooking.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutVO(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("booking_no")
    val bookingNumber: String?,

    @SerializedName("booking_date")
    val bookingDate: String?,

    @SerializedName("row")
    val row: String?,

    @SerializedName("seat")
    val seat: String?,

    @SerializedName("total_seat")
    val totalSeat: String?,

    @SerializedName("total")
    val total: String?,

    @SerializedName("movie_id")
    val movieId: String?,

    @SerializedName("cinema_id")
    val cinemaId: String?,

    @SerializedName("username")
    val userName: String?,

    @SerializedName("timeslot")
    val timeslot: CheckOutTimeslotVO,

    @SerializedName("snacks")
)
