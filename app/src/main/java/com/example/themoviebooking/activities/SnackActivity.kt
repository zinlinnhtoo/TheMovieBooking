package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.activities.MovieDetailActivity.Companion.EXTRA_MOVIE_ID
import com.example.themoviebooking.activities.MovieSeatActivity.Companion.EXTRA_CINEMA_DAY_TIMESLOT_ID
import com.example.themoviebooking.activities.MovieSeatActivity.Companion.EXTRA_DATE
import com.example.themoviebooking.adapters.PaymentMethodAdapter
import com.example.themoviebooking.adapters.SnackAdapter
import com.example.themoviebooking.data.models.MovieBookingModel
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.data.vos.PaymentCardVO
import com.example.themoviebooking.data.vos.SnackVO
import com.example.themoviebooking.delegates.PaymentMethodDelegate
import com.example.themoviebooking.delegates.SnackToggleButtonDelegate
import com.example.themoviebooking.utils.showErrorToast
import kotlinx.android.synthetic.main.activity_snack.*

class SnackActivity : AppCompatActivity(), PaymentMethodDelegate, SnackToggleButtonDelegate {

    lateinit var mSnackAdapter: SnackAdapter
    lateinit var mPaymentMethodAdapter: PaymentMethodAdapter

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    //field from seat activity
    private var mPrice: Double? = null
    private var mCinemaDayTimeslotId: Int? = null
    private var mRow: String? = null
    private var mSeatName: String? = null
    private var mDate: String? = null
    private var mMovieId: Int? = null

    private var mPaymentMethodList: MutableList<PaymentCardVO> = mutableListOf()
    private var mSnackList: MutableList<SnackVO> = mutableListOf()
    private var mTotalPrice: Double = 0.0
    private var mTotalSnackPrice: Double = 0.0

    companion object {
        const val EXTRA_PRICE_IN_SNACK_BUTTON = "EXTRA_PRICE_IN_SNACK_BUTTON"
        const val EXTRA_CINEMA_LIST = "EXTRA_CINEMA_LIST"
        const val EXTRA_SEAT_NAME = "EXTRA_SEAT_NAME"

        fun newIntent(
            context: Context,
            price: Double,
            cinemaDayTimeslotId: Int,
            row: String,
            seatName: String,
            date: String,
            movieId: Int
        ): Intent {
            val intent = Intent(context, SnackActivity::class.java)
            intent.putExtra(EXTRA_PRICE_IN_SNACK_BUTTON, price)
            intent.putExtra(EXTRA_CINEMA_DAY_TIMESLOT_ID, cinemaDayTimeslotId)
            intent.putExtra(EXTRA_CINEMA_LIST, row)
            intent.putExtra(EXTRA_SEAT_NAME, seatName)
            intent.putExtra(EXTRA_DATE, date)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)

        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        mDate = intent?.getStringExtra(EXTRA_DATE)
        mSeatName = intent?.getStringExtra(EXTRA_SEAT_NAME)
        mRow = intent?.getStringExtra(EXTRA_CINEMA_LIST)
        mCinemaDayTimeslotId = intent?.getIntExtra(EXTRA_CINEMA_DAY_TIMESLOT_ID, 0)
        mPrice = intent?.getDoubleExtra(EXTRA_PRICE_IN_SNACK_BUTTON, 0.0)
        mPrice?.let {
            mTotalPrice = it
            btnGotoPaymentCard.text = "Pay $$it"
            tvSubTotal.text = "$it$"
        }



        setUpListener()
        setUpSnackRecyclerView()
        setUpPaymentMethodRecyclerView()
        requestData()

    }

    private fun setUpPaymentMethodRecyclerView() {
        mPaymentMethodAdapter = PaymentMethodAdapter(this)
        rvPaymentMethod.adapter = mPaymentMethodAdapter
        rvPaymentMethod.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnGotoPaymentCard.setOnClickListener {
            startActivity(PaymentCardActivity.newIntentWithPrice(this, mTotalPrice, mCinemaDayTimeslotId ?: 0, mRow.orEmpty(), mSeatName.orEmpty(), mDate.orEmpty(), mMovieId ?: 0))
        }
    }

    private fun setUpSnackRecyclerView() {
        mSnackAdapter = SnackAdapter(this)
        rvSnack.adapter = mSnackAdapter
        rvSnack.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun requestData() {
        mMovieBookingModel.getSnack(
            onSuccess = {
                mSnackList = it.toMutableList()
                mSnackAdapter.setNewData(it)
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )

        mMovieBookingModel.getPaymentMethod(
            onSuccess = {
                mPaymentMethodList = it.toMutableList()
                mPaymentMethodAdapter.setNewData(it)
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )
    }

    override fun onTapPaymentMethod(paymentMethod: PaymentCardVO) {
        mPaymentMethodList.forEach{
            it.isSelected = it.id == paymentMethod.id
        }
        mPaymentMethodAdapter.setNewData(mPaymentMethodList)
    }

    @SuppressLint("SetTextI18n")
    override fun onTapSnackToggleButton(snack: SnackVO) {

        mSnackList.forEach { mSnack ->
            if (mSnack.id == snack.id) {
                if (snack.isSelectedMinusButton == false) {
                    snack.price?.let { price ->
                        snack.totalPrice = price * snack.quantity
                        mSnack.totalPrice = snack.totalPrice
                    }
                } else {
                    snack.price?.let { price ->
                        snack.totalPrice = price * snack.quantity
                        mSnack.totalPrice = snack.totalPrice
                    }
                }
            }
        }

        var totalSnackPrice = 0.0
        mSnackList.forEach {
            totalSnackPrice += it.totalPrice
            mTotalSnackPrice = totalSnackPrice
        }

        mTotalPrice = mPrice?.plus(mTotalSnackPrice) ?: 0.0

        btnGotoPaymentCard.text = "Pay $$mTotalPrice"
        tvSubTotal.text = "$mTotalPrice$"
    }
    
}