package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.CreditCardAdapter
import kotlinx.android.synthetic.main.activity_payment_card.*

class PaymentCardActivity : AppCompatActivity() {

    lateinit var mCreditCardAdapter: CreditCardAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, PaymentCardActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_card)

        setUpListener()
        setUpCarouselCreditCardRecyclerView()
    }

    private fun setUpCarouselCreditCardRecyclerView() {
        mCreditCardAdapter = CreditCardAdapter()
        rvCarouselCreditCard.adapter = mCreditCardAdapter
        rvCarouselCreditCard.setInfinite(true)
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