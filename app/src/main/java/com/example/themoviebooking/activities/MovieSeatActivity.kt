package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.activities.MovieDetailActivity.Companion.EXTRA_MOVIE_TITLE
import com.example.themoviebooking.adapters.MovieSeatAdapter
import com.example.themoviebooking.dummy.DUMMY_SEATS
import kotlinx.android.synthetic.main.activity_movie_seat.*

class MovieSeatActivity : AppCompatActivity() {

    private val mAdapter: MovieSeatAdapter = MovieSeatAdapter()

    private var mMovieTitle: String? = null
    private var mMovieWeekDay: String? = null
    private var mMovieDay: String? = null
    private var mMovieMonth: String? = null
    private var mMovieTime: String? = null
    private var mCinemaName: String? = null

    companion object {
        const val EXTRA_MOVIE_WEEK_DAY = "EXTRA_MOVIE_WEEKDAY"
        const val EXTRA_MOVIE_DAY = "EXTRA_MOVIE_DAY"
        const val EXTRA_MOVIE_MONTH = "EXTRA_MOVIE_MONTH"
        const val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        fun newIntent(context: Context,
                      movieTitle: String,
                      movieWeekday: String,
                      movieDay: String,
                      movieMonth: String,
                      movieTime: String,
                      cinemaName: String
        ): Intent {
            val intent = Intent(context, MovieSeatActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            intent.putExtra(EXTRA_MOVIE_WEEK_DAY, movieWeekday)
            intent.putExtra(EXTRA_MOVIE_DAY, movieDay)
            intent.putExtra(EXTRA_MOVIE_MONTH, movieMonth)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat)

        mMovieTitle = intent?.getStringExtra(EXTRA_MOVIE_TITLE)
        mMovieWeekDay = intent?.getStringExtra(EXTRA_MOVIE_WEEK_DAY)
        mMovieDay = intent?.getStringExtra(EXTRA_MOVIE_DAY)
        mMovieMonth = intent?.getStringExtra(EXTRA_MOVIE_MONTH)
        mMovieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME)
        mCinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME)


        setUpListener()
        setUpMovieRecyclerView()
        setUpDataInActivity()

    }

    private fun setUpMovieRecyclerView() {
        rvMovieSeat.adapter = mAdapter
        rvMovieSeat.layoutManager = GridLayoutManager(applicationContext, 10)
        mAdapter.setNewData(DUMMY_SEATS)
    }

    private fun setUpListener() {
        btnGotoSnack.setOnClickListener {
            startActivity(SnackActivity.newIntent(this))
        }

        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpDataInActivity() {
        tvMovieName.text = mMovieTitle
        tvCinema.text = mCinemaName
        tvMovieDateTime.text = "$mMovieWeekDay, $mMovieDay $mMovieMonth, $mMovieTime"
    }
}