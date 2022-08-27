package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.themoviebooking.R
import kotlinx.android.synthetic.main.activity_voucher.*

class VoucherActivity : AppCompatActivity() {

    private var mBookingNumber: String? = null
    
    companion object {
        const val EXTRA_BOOKING_NUMBER = "EXTRA_BOOKING_NUMBER"
        fun newIntent(context: Context, movieNumber: String): Intent {
            return Intent(context, VoucherActivity::class.java)
                .putExtra(EXTRA_BOOKING_NUMBER, movieNumber)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)
        
        mBookingNumber = intent?.getStringExtra(EXTRA_BOOKING_NUMBER)
        mBookingNumber?.let {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }

        setUpListener()
    }

    private fun setUpListener() {
        btnClose.setOnClickListener {
            finish()
        }
    }
}