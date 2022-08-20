package com.example.themoviebooking.network

import com.example.themoviebooking.data.vos.MovieVO
import com.example.themoviebooking.network.responses.ActorResponse
import com.example.themoviebooking.network.responses.MovieResponse
import com.example.themoviebooking.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBApi {

    @GET(API_GET_NOW_SHOWING)
    fun getNowShowingMovie(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Call<MovieResponse>

    @GET(API_GET_COMING_SOON)
    fun getComingSoonMovie(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Call<MovieResponse>

    @GET("$API_GET_MOVIE_DETAIL/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ): Call<MovieVO>

    @GET("$API_GET_CREDIT_BY_MOVIE/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ): Call<ActorResponse>
}