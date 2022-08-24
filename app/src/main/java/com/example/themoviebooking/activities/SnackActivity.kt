package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.PaymentMethodAdapter
import com.example.themoviebooking.adapters.SnackAdapter
import com.example.themoviebooking.data.models.MovieBookingModel
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.data.vos.PaymentCardVO
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

    private var mPaymentMethodList: MutableList<PaymentCardVO> = mutableListOf()
    private var mSnackPrice: Double = 0.0
    private var mTotalPrice: Double = 0.0

    companion object {
        const val EXTRA_PRICE_IN_SNACK_BUTTON = "EXTRA_PRICE_IN_SNACK_BUTTON"

        fun newIntent(context: Context, price: Double): Intent {
            val intent = Intent(context, SnackActivity::class.java)
            intent.putExtra(EXTRA_PRICE_IN_SNACK_BUTTON, price)
            return intent
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)

        mPrice = intent?.getDoubleExtra(EXTRA_PRICE_IN_SNACK_BUTTON, 0.0)
        btnGotoPaymentCard.text = "Pay $$mPrice"
        tvSubTotal.text = "$mPrice$"

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
            startActivity(PaymentCardActivity.newIntentWithPrice(this, mTotalPrice))
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
    override fun onTapSnackToggleButton(price: Double) {
        mSnackPrice += price
        mPrice?.let {
            mTotalPrice = it.plus(mSnackPrice)
        }
        btnGotoPaymentCard.text = "Pay $$mTotalPrice"
        tvSubTotal.text = "$mTotalPrice"
    }
    
}