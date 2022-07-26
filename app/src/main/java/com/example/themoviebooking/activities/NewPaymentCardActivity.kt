package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviebooking.R
import kotlinx.android.synthetic.main.activity_new_payment_card.*

class NewPaymentCardActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, NewPaymentCardActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_payment_card)

        setUpListener()
    }

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnGoBackToCard.setOnClickListener {
            startActivity(PaymentCardActivity.newIntent(this))
        }
    }
}