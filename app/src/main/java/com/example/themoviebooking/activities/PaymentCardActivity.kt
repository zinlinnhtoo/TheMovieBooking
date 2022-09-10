package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.themoviebooking.R
import com.example.themoviebooking.activities.MovieDetailActivity.Companion.EXTRA_MOVIE_ID
import com.example.themoviebooking.activities.MovieSeatActivity.Companion.EXTRA_CINEMA_DAY_TIMESLOT_ID
import com.example.themoviebooking.activities.MovieSeatActivity.Companion.EXTRA_CINEMA_ID
import com.example.themoviebooking.activities.MovieSeatActivity.Companion.EXTRA_DATE
import com.example.themoviebooking.activities.MovieSeatActivity.Companion.EXTRA_MOVIE_TIME
import com.example.themoviebooking.activities.SnackActivity.Companion.EXTRA_CINEMA_LIST
import com.example.themoviebooking.activities.SnackActivity.Companion.EXTRA_SEAT_NAME
import com.example.themoviebooking.activities.SnackActivity.Companion.EXTRA_SNACK_JSON
import com.example.themoviebooking.adapters.CreditCardAdapter
import com.example.themoviebooking.data.models.MovieBookingModel
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.data.vos.CardVO
import com.example.themoviebooking.data.vos.CarrierSnackList
import com.example.themoviebooking.data.vos.CarrierSnackVO
import com.example.themoviebooking.utils.showErrorToast
import com.google.gson.Gson
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import kotlinx.android.synthetic.main.activity_payment_card.*
import kotlin.math.log

class PaymentCardActivity : AppCompatActivity() {

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    lateinit var mCreditCardAdapter: CreditCardAdapter
    lateinit var carouselLayoutManager: CarouselLayoutManager

    private val NEW_PAYMENT_CARD_REQUEST_CODE = 0

    //field from snack activity
    private var mTotalPrice: Double? = null
    private var mCinemaDayTimeslotId: Int? = null
    private var mRow: String? = null
    private var mSeatName: String? = null
    private var mDate: String? = null
    private var mMovieId: Int? = null
    private var mCinemaId: Int? = null
    private var mSnackJson: String? = null
    private var mMovieTime: String? = null
    private var mMovieDay: String? = null
    private var mMovieWeekdayForVoucher: String? = null
    private var mCinemaName: String? = null
    private var mMovieTitle: String? = null
    private var mMoviePosterPath: String? = null

    private var cinemaDayTimeslotId: String = ""
    private var row: String = ""
    private var seatNumber: String = ""
    private var bookingDate: String = ""
    private var totalPrice: String = ""
    private var movieId: String = ""
    private var cardId: String = ""
    private var cinemaId: String = ""
    private var snack: MutableList<CarrierSnackVO> = mutableListOf()

    private var mCardList: MutableList<CardVO> = mutableListOf()
    private var mCardId: Int? = 0
    private var mMovieBookingNumber: String = ""

    companion object {
        const val EXTRA_TOTAL_PRICE = "EXTRA_TOTAL_PRICE"
        fun newIntentWithPrice(
            context: Context,
            price: Double,
            cinemaDayTimeslotId: Int,
            row: String,
            seatName: String,
            date: String,
            movieId: Int,
            cinemaId: Int,
            snackJson: String,
            movieTime: String,
            movieDay: String,
            movieWeekdayForVoucher: String,
            cinemaName: String,
            movieTitle: String,
            moviePosterPath: String
        ): Intent {
            return Intent(context, PaymentCardActivity::class.java)
                .putExtra(EXTRA_TOTAL_PRICE, price)
                .putExtra(EXTRA_CINEMA_DAY_TIMESLOT_ID, cinemaDayTimeslotId)
                .putExtra(EXTRA_CINEMA_LIST, row)
                .putExtra(EXTRA_SEAT_NAME, seatName)
                .putExtra(EXTRA_DATE, date)
                .putExtra(EXTRA_MOVIE_ID, movieId)
                .putExtra(EXTRA_CINEMA_ID, cinemaId)
                .putExtra(EXTRA_SNACK_JSON, snackJson)
                .putExtra(EXTRA_MOVIE_TIME, movieTime)
                .putExtra(MovieSeatActivity.EXTRA_MOVIE_DAY, movieDay)
                .putExtra(MovieSeatActivity.EXTRA_MOVIE_WEEKDAY_FOR_VOUCHER, movieWeekdayForVoucher)
                .putExtra(MovieSeatActivity.EXTRA_CINEMA_NAME, cinemaName)
                .putExtra(MovieDetailActivity.EXTRA_MOVIE_TITLE, movieTitle)
                .putExtra(MovieDetailActivity.EXTRA_MOVIE_POSTER_PATH, moviePosterPath)
        }

        fun newIntent(context: Context): Intent {
            return Intent(context, PaymentCardActivity::class.java)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_card)

        getExtraFromSnackActivity()
        toCallNetworkWithExtraData()
        setUpListener()
        setUpCarouselCreditCardRecyclerView()
        requestData()
    }

    private fun toCallNetworkWithExtraData() {
        mSnackJson?.let {
            val carrierSnackObj = Gson().fromJson(it, CarrierSnackList::class.java)
    //            snack.add(carrierSnackObj)
            carrierSnackObj.forEach { carrierSnackObj ->
                snack.add(carrierSnackObj)
            }
        }

        mCinemaId?.let {
            cinemaId = it.toString()
        }

        mMovieId?.let {
            movieId = it.toString()
        }

        mDate?.let {
            bookingDate = it
        }

        mSeatName?.let {
            seatNumber = it
        }

        mRow?.let {
            row = it
        }

        mCinemaDayTimeslotId?.let {
            cinemaDayTimeslotId = it.toString()
        }

        mTotalPrice?.let {
            totalPrice = it.toString()
            tvPaymentAmount.text = "$ $it"
        }
    }

    private fun getExtraFromSnackActivity() {
        mMoviePosterPath = intent?.getStringExtra(MovieDetailActivity.EXTRA_MOVIE_POSTER_PATH)
        mMovieTitle = intent?.getStringExtra(MovieDetailActivity.EXTRA_MOVIE_TITLE)
        mCinemaName = intent?.getStringExtra(MovieSeatActivity.EXTRA_CINEMA_NAME)
        mMovieWeekdayForVoucher =
            intent?.getStringExtra(MovieSeatActivity.EXTRA_MOVIE_WEEKDAY_FOR_VOUCHER)
        mMovieDay = intent?.getStringExtra(MovieSeatActivity.EXTRA_MOVIE_DAY)
        mMovieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME)
        mSnackJson = intent?.getStringExtra(EXTRA_SNACK_JSON)
        mCinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0)
        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        mDate = intent?.getStringExtra(EXTRA_DATE)
        mSeatName = intent?.getStringExtra(EXTRA_SEAT_NAME)
        mRow = intent?.getStringExtra(EXTRA_CINEMA_LIST)
        mCinemaDayTimeslotId = intent?.getIntExtra(EXTRA_CINEMA_DAY_TIMESLOT_ID, 0)
        mTotalPrice = intent?.getDoubleExtra(EXTRA_TOTAL_PRICE, 0.0)
    }

    private fun requestData() {
        mMovieBookingModel.getUser(
            onSuccess = {},
            onFailure = {
                showErrorToast(it, this)
            }
        )
        mMovieBookingModel.getCard(
            onSuccess = {
                mCardList = it.toMutableList()
                mCreditCardAdapter.setNewData(it)
                carouselLayoutManager.scrollToPosition(mCardList.lastIndex)
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_PAYMENT_CARD_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                mMovieBookingModel.getCard(
                    onSuccess = {
                        mCreditCardAdapter.setNewData(it)
                        carouselLayoutManager.scrollToPosition(it.lastIndex)
                        cardId = data?.getStringExtra("message").orEmpty()
                    },
                    onFailure = {
                        showErrorToast(it, this)
                    }
                )
            }
        }
    }

    private fun setUpCarouselCreditCardRecyclerView() {
        mCreditCardAdapter = CreditCardAdapter()
        rvCarouselCreditCard.adapter = mCreditCardAdapter
        rvCarouselCreditCard.setInfinite(true)
        rvCarouselCreditCard.setIntervalRatio(0.75f)
        carouselLayoutManager = rvCarouselCreditCard.getCarouselLayoutManager()
        rvCarouselCreditCard.setItemSelectListener(
            object : CarouselLayoutManager.OnSelected {
                override fun onItemSelected(position: Int) {
                    mCardId = mCardList.getOrNull(position)?.id
                    cardId = mCardId.toString()
                }
            }
        )
    }

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnAddNewCard.setOnClickListener {
            startActivityForResult(NewPaymentCardActivity.newIntent(this), NEW_PAYMENT_CARD_REQUEST_CODE)
        }

        btnGotoVoucher.setOnClickListener {
            mMovieBookingModel.checkOut(
                cinemaDayTimeslotId = cinemaDayTimeslotId,
                row = row,
                seatNumber = seatNumber,
                bookingDate = bookingDate,
                totalPrice = totalPrice,
                movieId = movieId,
                cardId = cardId,
                cinemaId = cinemaId,
                snack = snack,
                onSuccess = {
                    mMovieBookingNumber = it.bookingNumber.orEmpty()
                    startActivity(VoucherActivity
                        .newIntent(this,
                            mMovieBookingNumber,
                            mMovieTime.orEmpty(),
                            mMovieDay.orEmpty(),
                            mMovieWeekdayForVoucher.orEmpty(),
                            mCinemaName.orEmpty(),
                            mRow.orEmpty(),
                            mSeatName.orEmpty(),
                            mTotalPrice ?: 0.0,
                            mMovieTitle.orEmpty(),
                            mMoviePosterPath.orEmpty()
                        )
                    )
                    Log.i("CheckOut", mMovieBookingNumber)
                },
                onFailure = {
                    showErrorToast(it, this)
                }
            )
        }
    }
}