package com.example.themoviebooking.network

import com.example.themoviebooking.network.responses.LoginUserResponse
import com.example.themoviebooking.utils.API_LOGIN_USER
import com.example.themoviebooking.utils.API_REGISTER_USER
import com.example.themoviebooking.utils.API_USER_PROFILE
import retrofit2.Call
import retrofit2.http.*

interface TheMovieBookingApi {

    @POST(API_LOGIN_USER)
    @FormUrlEncoded
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginUserResponse>

    @POST(API_REGISTER_USER)
    @FormUrlEncoded
    fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Call<LoginUserResponse>

    @GET(API_USER_PROFILE)
    fun getUser(
        @Header("Authorization") token: String
    ): Call<LoginUserResponse>

}