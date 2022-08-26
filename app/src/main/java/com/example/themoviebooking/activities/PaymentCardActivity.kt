package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.CreditCardAdapter
import kotlinx.android.synthetic.main.activity_payment_card.*

class PaymentCardActivity : AppCompatActivity() {

    lateinit var mCreditCardAdapter: CreditCardAdapter

    //field from snack activity
    private var mTotalPrice: Double? = null

    companion object {
        const val EXTRA_TOTAL_PRICE = "EXTRA_TOTAL_PRICE"
        fun newIntentWithPrice(context: Context, price: Double): Intent {
            val intent = Intent(context, PaymentCardActivity::class.java)
                .putExtra(EXTRA_TOTAL_PRICE, price)
            return intent
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
        setUpCarouselCreditCardRecyclerView()
    }

    private fun setUpCarouselCreditCardRecyclerView() {
        mCreditCardAdapter = CreditCardAdapter()
        rvCarouselCreditCard.adapter = mCreditCardAdapter
        rvCarouselCreditCard.setInfinite(true)
        rvCarouselCreditCard.setIntervalRatio(0.75f)
    }

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnAddNewCard.setOnClickListener {
            startActivity(NewPaymentCardActivity.newIntent(this))
        }

        btnGotoVoucher.setOnClickListener {
            startActivity(VoucherActivity.newIntent(this))
        }
    }
}