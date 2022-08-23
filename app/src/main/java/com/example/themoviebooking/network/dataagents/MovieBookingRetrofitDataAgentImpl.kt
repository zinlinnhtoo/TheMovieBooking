package com.example.themoviebooking.network.dataagents

import com.example.themoviebooking.data.vos.*
import com.example.themoviebooking.network.TheMovieBookingApi
import com.example.themoviebooking.network.responses.*
import com.example.themoviebooking.utils.BEARER
import com.example.themoviebooking.utils.THE_MOVIE_BOOKING_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieBookingRetrofitDataAgentImpl: MovieBookingDataAgent {

    private var mTheMovieBookingApi: TheMovieBookingApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(THE_MOVIE_BOOKING_BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieBookingApi = retrofit.create(TheMovieBookingApi::class.java)
    }

    override fun getLoginUser(
        email: String,
        password: String,
        onSuccess: (Pair<UserVO, String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.loginUser(
            email = email,
            password = password
        )?.enqueue(
            object : Callback<LoginUserResponse> {
                override fun onResponse(
                    call: Call<LoginUserResponse>,
                    response: Response<LoginUserResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            if (it.data != null && it.token != null) {
                                onSuccess(it.data to it.token)
                            } else {
                                onFailure("Something's wrong")
                            }
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getRegisterUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<UserVO, String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.registerUser(
            name = name,
            email = email,
            phone = phone,
            password = password
        )?.enqueue(
            object : Callback<LoginUserResponse> {
                override fun onResponse(
                    call: Call<LoginUserResponse>,
                    response: Response<LoginUserResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            if (it.data != null && it.token != null) {
                                onSuccess(it.data to it.token)
                            } else {
                                onFailure("Something's Wrong")
                            }
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getUser(token: String, onSuccess: (UserVO) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieBookingApi?.getUser(
            token = BEARER + token
        )?.enqueue(
            object : Callback<LoginUserResponse> {
                override fun onResponse(
                    call: Call<LoginUserResponse>,
                    response: Response<LoginUserResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getLogout(
        token: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getLogout(
            token = BEARER + token
        )?.enqueue(
            object : Callback<LoginUserResponse> {
                override fun onResponse(
                    call: Call<LoginUserResponse>,
                    response: Response<LoginUserResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.message?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getCinemaDayTimeslot(
        token: String,
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getCinemaDayTimeslot(
            token = BEARER + token,
            movieId = movieId,
            date = date
        )?.enqueue(
            object : Callback<CinemaDayTimeslotResponse> {
                override fun onResponse(
                    call: Call<CinemaDayTimeslotResponse>,
                    response: Response<CinemaDayTimeslotResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CinemaDayTimeslotResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getCinemaSeatingPlan(
        token: String,
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getCinemaSeatingPlan(
            token = BEARER + token,
            cinemaDayTimeslotId = cinemaDayTimeslotId,
            bookingDate = bookingDate,
        )?.enqueue(
            object : Callback<CinemaSeatingPlanResponse> {
                override fun onResponse(
                    call: Call<CinemaSeatingPlanResponse>,
                    response: Response<CinemaSeatingPlanResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let {
                            onSuccess(it.flatten())
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CinemaSeatingPlanResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getSnack(
        token: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getSnack(
            token = BEARER + token
        )?.enqueue(
            object : Callback<SnackResponse> {
                override fun onResponse(
                    call: Call<SnackResponse>,
                    response: Response<SnackResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let(onSuccess)
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<SnackResponse>, t: Throwable) {
                    onFailure(t.message.orEmpty())
                }
            }
        )
    }

    override fun getPaymentMethod(
        token: String,
        onSuccess: (List<PaymentCardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getPaymentMethod(
            token = BEARER + token
        )?.enqueue(
            object : Callback<PaymentCardResponse> {
                override fun onResponse(
                    call: Call<PaymentCardResponse>,
                    response: Response<PaymentCardResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let(onSuccess)
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<PaymentCardResponse>, t: Throwable) {
                    onFailure(t.message.orEmpty())
                }
            }
        )
    }
}