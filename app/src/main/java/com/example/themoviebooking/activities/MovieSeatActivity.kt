package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.activities.MovieDetailActivity.Companion.EXTRA_MOVIE_TITLE
import com.example.themoviebooking.adapters.MovieSeatAdapter
import com.example.themoviebooking.data.models.MovieBookingModel
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.data.vos.MovieSeatVO
import com.example.themoviebooking.delegates.MovieSeatDelegate
import com.example.themoviebooking.utils.showErrorToast
import kotlinx.android.synthetic.main.activity_movie_seat.*

class MovieSeatActivity : AppCompatActivity(), MovieSeatDelegate {

    lateinit var mMovieSeatAdapter: MovieSeatAdapter
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    private var mMovieSeatList: List<MovieSeatVO> = listOf()

    private var mMovieTitle: String? = null
    private var mMovieWeekDay: String? = null
    private var mMovieDay: String? = null
    private var mMovieMonth: String? = null
    private var mMovieTime: String? = null
    private var mCinemaName: String? = null
    private var mCinemaDayTimeslotId: Int? = null
    private var mDate: String? = null

    companion object {
        const val EXTRA_MOVIE_WEEK_DAY = "EXTRA_MOVIE_WEEKDAY"
        const val EXTRA_MOVIE_DAY = "EXTRA_MOVIE_DAY"
        const val EXTRA_MOVIE_MONTH = "EXTRA_MOVIE_MONTH"
        const val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        const val EXTRA_DATE = "EXTRA_DATE"
        const val EXTRA_CINEMA_DAY_TIMESLOT_ID = "EXTRA_CINEMA_DAY_TIMESLOT_ID"
        fun newIntent(context: Context,
                      movieTitle: String,
                      movieWeekday: String,
                      movieDay: String,
                      movieMonth: String,
                      movieTime: String,
                      cinemaName: String,
                      cinemaDayTimeslotId: Int,
                      date: String
        ): Intent {
            val intent = Intent(context, MovieSeatActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            intent.putExtra(EXTRA_MOVIE_WEEK_DAY, movieWeekday)
            intent.putExtra(EXTRA_MOVIE_DAY, movieDay)
            intent.putExtra(EXTRA_MOVIE_MONTH, movieMonth)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
            intent.putExtra(EXTRA_CINEMA_DAY_TIMESLOT_ID, cinemaDayTimeslotId)
            intent.putExtra(EXTRA_DATE, date)
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
        mCinemaDayTimeslotId = intent?.getIntExtra(EXTRA_CINEMA_DAY_TIMESLOT_ID, 0)
        mDate = intent?.getStringExtra(EXTRA_DATE)


        setUpListener()
        setUpMovieRecyclerView()
        setUpDataInActivity()
        mCinemaDayTimeslotId?.let { id ->
            mDate?.let { date ->
                requestData(id, date)
            }
        }

    }

    private fun requestData(
        cinemaDayTimeslotId: Int,
        bookingDate: String
    ) {
        mMovieBookingModel.getCinemaSeatingPlan(
            cinemaDayTimeslotId = cinemaDayTimeslotId.toString(),
            bookingDate = bookingDate,
            onSuccess = {
                mMovieSeatList = it
                mMovieSeatAdapter.setNewData(it)
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )
    }

    private fun setUpMovieRecyclerView() {
        mMovieSeatAdapter = MovieSeatAdapter(mMovieSeatList, this)
        rvMovieSeat.adapter = mMovieSeatAdapter
        rvMovieSeat.layoutManager = GridLayoutManager(applicationContext, 14)
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

    override fun onTapMovieSeat(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}