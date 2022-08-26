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
import com.example.themoviebooking.activities.MovieSeatActivity.Companion.EXTRA_DATE
import com.example.themoviebooking.activities.SnackActivity.Companion.EXTRA_CINEMA_LIST
import com.example.themoviebooking.activities.SnackActivity.Companion.EXTRA_SEAT_NAME
import com.example.themoviebooking.adapters.CreditCardAdapter
import com.example.themoviebooking.data.models.MovieBookingModel
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.data.vos.CardVO
import com.example.themoviebooking.utils.showErrorToast
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import kotlinx.android.synthetic.main.activity_payment_card.*

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

    private var mCardList: MutableList<CardVO> = mutableListOf()
    private var mCardId: Int? = 0

    companion object {
        const val EXTRA_TOTAL_PRICE = "EXTRA_TOTAL_PRICE"
        fun newIntentWithPrice(
            context: Context,
            price: Double,
            cinemaDayTimeslotId: Int,
            row: String,
            seatName: String,
            date: String,
            movieId: Int
        ): Intent {
            return Intent(context, PaymentCardActivity::class.java)
                .putExtra(EXTRA_TOTAL_PRICE, price)
                .putExtra(EXTRA_CINEMA_DAY_TIMESLOT_ID, cinemaDayTimeslotId)
                .putExtra(EXTRA_CINEMA_LIST, row)
                .putExtra(EXTRA_SEAT_NAME, seatName)
                .putExtra(EXTRA_DATE, date)
                .putExtra(EXTRA_MOVIE_ID, movieId)
        }

        fun newIntent(context: Context): Intent {
            return Intent(context, PaymentCardActivity::class.java)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_card)

        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        mMovieId?.let {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
        mDate = intent?.getStringExtra(EXTRA_DATE)
        mDate?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        mSeatName = intent?.getStringExtra(EXTRA_SEAT_NAME)
        mSeatName?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        mRow = intent?.getStringExtra(EXTRA_CINEMA_LIST)
        mRow?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        mCinemaDayTimeslotId = intent?.getIntExtra(EXTRA_CINEMA_DAY_TIMESLOT_ID, 0)
        mCinemaDayTimeslotId?.let {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
        mTotalPrice = intent?.getDoubleExtra(EXTRA_TOTAL_PRICE, 0.0)
        mTotalPrice?.let {
            tvPaymentAmount.text = "$ $it"
        }

        setUpListener()
        requestData()
        setUpCarouselCreditCardRecyclerView()
    }

    private fun requestData() {
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
                        val messageFromNewPaymentCard = data!!.getStringExtra("message")
                        Toast.makeText(this, "$messageFromNewPaymentCard", Toast.LENGTH_SHORT).show()
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
                    mCardId = mCardList[position].id
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
            startActivity(VoucherActivity.newIntent(this))
        }
    }
}