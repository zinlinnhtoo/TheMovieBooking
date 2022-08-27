package com.example.themoviebooking.network

import com.example.themoviebooking.data.vos.UserVO
import com.example.themoviebooking.network.responses.*
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

    @GET(API_CINEMA_SEATING_PLAN)
    fun getCinemaSeatingPlan(
        @Header("Authorization") token: String,
        @Query(PARAM_CINEMA_DAY_TIMESLOT_ID) cinemaDayTimeslotId: String,
        @Query(PARAM_BOOKING_DATE) bookingDate: String
    ): Call<CinemaSeatingPlanResponse>

    @GET(API_SNACK)
    fun getSnack(
        @Header("Authorization") token: String
    ): Call<SnackResponse>

    @GET(API_PAYMENT_METHOD)
    fun getPaymentMethod(
        @Header("Authorization") token: String
    ): Call<PaymentCardResponse>

    @POST(API_CREATE_CARD)
    @FormUrlEncoded
    fun createCard(
        @Header("Authorization") token: String,
        @Field("card_number") cardNumber: String,
        @Field("card_holder") cardHolder: String,
        @Field("expiration_date") expirationDate: String,
        @Field("cvc") cvc: String
    ): Call<CardResponse>

    @GET(API_USER_PROFILE)
    fun getCard(
        @Header("Authorization") token: String
    ): Call<LoginUserResponse>

    @POST(API_CHECKOUT)
    fun checkOut(
        @Header("Authorization") token: String,
        @Body checkOutRequest: CheckOutRequest
    ): Call<CheckOutResponse>
}