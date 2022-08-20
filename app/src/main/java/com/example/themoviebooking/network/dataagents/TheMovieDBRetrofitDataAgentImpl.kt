package com.example.themoviebooking.network.dataagents

import com.example.themoviebooking.data.vos.ActorVO
import com.example.themoviebooking.data.vos.MovieVO
import com.example.themoviebooking.network.TheMovieDBApi
import com.example.themoviebooking.network.responses.ActorResponse
import com.example.themoviebooking.network.responses.MovieResponse
import com.example.themoviebooking.utils.THE_MOVIE_DB_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object TheMovieDBRetrofitDataAgentImpl: TheMovieDBDataAgent {

    private var mTheMovieDBApi: TheMovieDBApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(THE_MOVIE_DB_BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieDBApi = retrofit.create(TheMovieDBApi::class.java)
    }


    override fun getNowShowingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBApi?.getNowShowingMovie()?.enqueue(
            object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()?.results ?: listOf())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getComingSoonMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBApi?.getComingSoonMovie()?.enqueue(
            object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()?.results ?: listOf())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBApi?.getMovieDetail(
            movieId = movieId
        )?.enqueue(
            object : Callback<MovieVO> {
                override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
                    if (response.isSuccessful) {
                        response.body()?.let(onSuccess)
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBApi?.getCreditsByMovie(
            movieId = movieId
        )?.enqueue(
            object : Callback<ActorResponse> {
                override fun onResponse(
                    call: Call<ActorResponse>,
                    response: Response<ActorResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.cast?.let(onSuccess)
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<ActorResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }
}