package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviebooking.R
import kotlinx.android.synthetic.main.activity_voucher.*

class VoucherActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, VoucherActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)

        setUpListener()
    }

    private fun setUpListener() {
        btnClose.setOnClickListener {
            finish()
        }
    }
}