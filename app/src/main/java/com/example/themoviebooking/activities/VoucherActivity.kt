package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.themoviebooking.R
import com.example.themoviebooking.activities.SnackActivity.Companion.EXTRA_CINEMA_LIST
import com.example.themoviebooking.activities.SnackActivity.Companion.EXTRA_SEAT_NAME
import com.example.themoviebooking.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_voucher.*

class VoucherActivity : AppCompatActivity() {

    private var mBookingNumber: String? = null
    private var mMovieTime: String? = null
    private var mMovieDay: String? = null
    private var mMovieWeekdayForVoucher: String? = null
    private var mCinemaName: String? = null
    private var mRow: String? = null
    private var mSeatName: String? = null
    private var mTotalPrice: Double? = null
    private var mMovieTitle: String? = null
    private var mMoviePosterPath: String? = null
    
    companion object {
        const val EXTRA_BOOKING_NUMBER = "EXTRA_BOOKING_NUMBER"

        fun newIntent(
            context: Context,
            movieNumber: String,
            movieTime: String,
            movieDay: String,
            movieWeekdayForVoucher: String,
            cinemaName: String,
            row: String,
            seatName: String,
            totalPrice: Double,
            movieTitle: String,
            moviePosterPath: String
        ): Intent {
            return Intent(context, VoucherActivity::class.java)
                .putExtra(EXTRA_BOOKING_NUMBER, movieNumber)
                .putExtra(MovieSeatActivity.EXTRA_MOVIE_TIME, movieTime)
                .putExtra(MovieSeatActivity.EXTRA_MOVIE_DAY, movieDay)
                .putExtra(MovieSeatActivity.EXTRA_MOVIE_WEEKDAY_FOR_VOUCHER, movieWeekdayForVoucher)
                .putExtra(MovieSeatActivity.EXTRA_CINEMA_NAME, cinemaName)
                .putExtra(EXTRA_CINEMA_LIST, row)
                .putExtra(EXTRA_SEAT_NAME, seatName)
                .putExtra(PaymentCardActivity.EXTRA_TOTAL_PRICE, totalPrice)
                .putExtra(MovieDetailActivity.EXTRA_MOVIE_TITLE, movieTitle)
                .putExtra(MovieDetailActivity.EXTRA_MOVIE_POSTER_PATH, moviePosterPath)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)

        getExtraFromPaymentActivity()
        bindUiData()
        setUpListener()
    }

    private fun bindUiData() {
        mBookingNumber?.let {
            tvBookingNumber.text = it
        }

        var showTimeDate = ""
        mMovieTime?.let { time ->
            mMovieDay?.let { day ->
                mMovieWeekdayForVoucher?.let { week ->
                    showTimeDate = "$time - $day $week"
                }
            }
        }
        tvShowTime.text = showTimeDate


        mCinemaName?.let {
            tvTheater.text = it
        }


        mRow?.let {
            tvRow.text = it
        }


        mSeatName?.let {
            tvSeat.text = it
        }


        mTotalPrice?.let {
            tvPrice.text = "$ $it"
        }


        mMovieTitle?.let {
            tvMovieName.text = it
        }


        mMoviePosterPath?.let {
            Glide.with(this)
                .load("$IMAGE_BASE_URL$it")
                .into(ivMovieImage)
        }
    }

    private fun getExtraFromPaymentActivity() {
        mBookingNumber = intent?.getStringExtra(EXTRA_BOOKING_NUMBER)
        mMovieWeekdayForVoucher =
            intent?.getStringExtra(MovieSeatActivity.EXTRA_MOVIE_WEEKDAY_FOR_VOUCHER)
        mMovieDay = intent?.getStringExtra(MovieSeatActivity.EXTRA_MOVIE_DAY)
        mMovieTime = intent?.getStringExtra(MovieSeatActivity.EXTRA_MOVIE_TIME)
        mCinemaName = intent?.getStringExtra(MovieSeatActivity.EXTRA_CINEMA_NAME)
        mRow = intent?.getStringExtra(EXTRA_CINEMA_LIST)
        mSeatName = intent?.getStringExtra(EXTRA_SEAT_NAME)
        mTotalPrice = intent?.getDoubleExtra(PaymentCardActivity.EXTRA_TOTAL_PRICE, 0.0)
        mMovieTitle = intent?.getStringExtra(MovieDetailActivity.EXTRA_MOVIE_TITLE)
        mMoviePosterPath = intent?.getStringExtra(MovieDetailActivity.EXTRA_MOVIE_POSTER_PATH)
    }


    private fun setUpListener() {
        btnClose.setOnClickListener {
            startActivity(HomeActivity.newIntent(this))
            finish()
        }
    }
}