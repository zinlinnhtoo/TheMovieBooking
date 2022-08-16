package com.example.themoviebooking.network.responses

import com.example.themoviebooking.data.vos.ActorVO
import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("cast")
    val cast: List<ActorVO>?
)
