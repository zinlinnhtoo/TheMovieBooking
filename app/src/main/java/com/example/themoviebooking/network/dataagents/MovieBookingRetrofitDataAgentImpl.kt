package com.example.themoviebooking.network.dataagents

import com.example.themoviebooking.data.vos.UserVO
import com.example.themoviebooking.network.TheMovieBookingApi
import com.example.themoviebooking.network.responses.LoginUserResponse
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
}