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

    private var mCardList: MutableList<CardVO> = mutableListOf()
    private var mCardId: Int? = 0

    companion object {
        const val EXTRA_TOTAL_PRICE = "EXTRA_TOTAL_PRICE"
        fun newIntentWithPrice(context: Context, price: Double): Intent {
            return Intent(context, PaymentCardActivity::class.java)
                .putExtra(EXTRA_TOTAL_PRICE, price)
        }

        fun newIntent(context: Context): Intent {
            return Intent(context, PaymentCardActivity::class.java)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_card)

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