package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.PaymentMethodAdapter
import com.example.themoviebooking.adapters.SnackAdapter
import kotlinx.android.synthetic.main.activity_snack.*

class SnackActivity : AppCompatActivity() {

    lateinit var mSnackAdapter: SnackAdapter
    lateinit var mPaymentMethodAdapter: PaymentMethodAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SnackActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)

        setUpListener()
        setUpSnackRecyclerView()
        setUpPaymentMethodRecyclerView()
    }

    private fun setUpPaymentMethodRecyclerView() {
        mPaymentMethodAdapter = PaymentMethodAdapter()
        rvPaymentMethod.adapter = mPaymentMethodAdapter
        rvPaymentMethod.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnGotoPaymentCard.setOnClickListener {
            startActivity(PaymentCardActivity.newIntent(this))
        }
    }

    private fun setUpSnackRecyclerView() {
        mSnackAdapter = SnackAdapter()
        rvSnack.adapter = mSnackAdapter
        rvSnack.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}