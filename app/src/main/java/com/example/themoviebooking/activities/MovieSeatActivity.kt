package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.activities.MovieDetailActivity.Companion.EXTRA_MOVIE_ID
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

    //field from cinema view holder
    private var mMovieSeatList: List<MovieSeatVO> = listOf()
    private var mTotalSeats: Int = 0
    private var mTakenSeatNames: MutableList<String> = mutableListOf()
    private var mTicketPrice: Double = 0.0
    private var mRow: String = ""

    //field from date time activity
    private var mMovieTitle: String? = null
    private var mMovieWeekDay: String? = null
    private var mMovieDay: String? = null
    private var mMovieMonth: String? = null
    private var mMovieTime: String? = null
    private var mCinemaName: String? = null
    private var mCinemaDayTimeslotId: Int? = null
    private var mDate: String? = null
    private var mMovieId: Int? = null
    private var mCinemaId: Int? = null
    private var mMovieWeekdayForVoucher: String? = null
    private var mMoviePosterPath: String? = null

    //field for snack activity
    private var mPrice: Double? = 0.0
    private var mSeatName: String = ""

    companion object {
        const val EXTRA_MOVIE_WEEK_DAY = "EXTRA_MOVIE_WEEKDAY"
        const val EXTRA_MOVIE_DAY = "EXTRA_MOVIE_DAY"
        const val EXTRA_MOVIE_MONTH = "EXTRA_MOVIE_MONTH"
        const val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        const val EXTRA_DATE = "EXTRA_DATE"
        const val EXTRA_CINEMA_DAY_TIMESLOT_ID = "EXTRA_CINEMA_DAY_TIMESLOT_ID"
        const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        const val EXTRA_MOVIE_WEEKDAY_FOR_VOUCHER = "EXTRA_MOVIE_WEEKDAY_FOR_VOUCHER"
        fun newIntent(context: Context,
                      movieTitle: String,
                      movieWeekday: String,
                      movieDay: String,
                      movieMonth: String,
                      movieTime: String,
                      cinemaName: String,
                      cinemaDayTimeslotId: Int,
                      date: String,
                      movieId: Int,
                      cinemaId: Int,
                      movieWeekdayForVoucher: String,
                      moviePosterPath: String
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
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_MOVIE_WEEKDAY_FOR_VOUCHER, movieWeekdayForVoucher)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_POSTER_PATH, moviePosterPath)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat)

        getExtraFromMovieDateTimeActivity()
        setUpListener()
        setUpMovieRecyclerView()
        setUpDataInActivity()
        mCinemaDayTimeslotId?.let { id ->
            mDate?.let { date ->
                requestData(id, date)
            }
        }

    }

    private fun getExtraFromMovieDateTimeActivity() {
        mMoviePosterPath = intent?.getStringExtra(MovieDetailActivity.EXTRA_MOVIE_POSTER_PATH)
        mMovieWeekdayForVoucher = intent?.getStringExtra(EXTRA_MOVIE_WEEKDAY_FOR_VOUCHER)
        mCinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0)
        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        mMovieTitle = intent?.getStringExtra(EXTRA_MOVIE_TITLE)
        mMovieWeekDay = intent?.getStringExtra(EXTRA_MOVIE_WEEK_DAY)
        mMovieDay = intent?.getStringExtra(EXTRA_MOVIE_DAY)
        mMovieMonth = intent?.getStringExtra(EXTRA_MOVIE_MONTH)
        mMovieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME)
        mCinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME)
        mCinemaDayTimeslotId = intent?.getIntExtra(EXTRA_CINEMA_DAY_TIMESLOT_ID, 0)
        mDate = intent?.getStringExtra(EXTRA_DATE)
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
            mPrice?.let {
                startActivity(SnackActivity
                    .newIntent(
                        this,
                        it,
                        mCinemaDayTimeslotId ?: 0,
                        mRow,
                        mSeatName,
                        mDate.orEmpty(),
                        mMovieId ?: 0,
                        mCinemaId ?: 0,
                        mMovieTime.orEmpty(),
                        mMovieDay.orEmpty(),
                        mMovieWeekdayForVoucher.orEmpty(),
                        mCinemaName.orEmpty(),
                        mMovieTitle.orEmpty(),
                        mMoviePosterPath.orEmpty()
                    )
                )
            }
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

    @SuppressLint("SetTextI18n")
    override fun onTapMovieSeat(
        takenSeatName: String,
        removeSeatName: String,
        totalSeat: Int,
        ticketPrice: Double,
        row: String
    ) {
        mTotalSeats += totalSeat
        mTicketPrice += ticketPrice
        mPrice = mTicketPrice
        mRow = row

        if (takenSeatName != removeSeatName) {
            mTakenSeatNames.add(takenSeatName)
        } else {
            mTakenSeatNames.remove(removeSeatName)
        }

        tvTicket.text = mTotalSeats.toString()
        tvSeat.text = mTakenSeatNames.joinToString(", ")
        mSeatName = mTakenSeatNames.joinToString(",")
        btnGotoSnack.text = "Buy Ticket for $$mTicketPrice"
    }
}