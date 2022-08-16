package com.example.themoviebooking.network

import com.example.themoviebooking.network.responses.CinemaDayTimeslotResponse
import com.example.themoviebooking.network.responses.LoginUserResponse
import com.example.themoviebooking.utils.*
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

    @POST(API_LOG_OUT)
    fun getLogout(
        @Header("Authorization") token: String
    ): Call<LoginUserResponse>

    @GET(API_CINEMA_DAY_TIMESLOT)
    fun getCinemaDayTimeslot(
        @Header("Authorization") token: String,
        @Query(PARAM_MOVIE_ID) movieId: String,
        @Query(PARAM_DATE) date: String
    ): Call<CinemaDayTimeslotResponse>

}