package com.example.themoviebooking.data.vos

import com.google.gson.annotations.SerializedName

data class MovieVO(
    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("genre_ids")
    val genreId: List<Int>?,

    @SerializedName("budget")
    val budget: Double?,

    @SerializedName("genres")
    val genres: List<GenreVO>?,

    @SerializedName("homepage")
    val homePage: String?,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("imdb_id")
    val imdbId: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overView: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("revenue")
    val revenue: Int?,

    @SerializedName("runtime")
    val runTime: Int?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<MovieVO>?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("tagline")
    val tagLine: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?

) {
    fun getRatingBasedOnFiveStars(): Float {
        return voteAverage?.div(2)?.toFloat() ?: 0.0f
    }
}
