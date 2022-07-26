package com.example.themoviebooking.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviebooking.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnGetStarted.setOnClickListener {
            startActivity(LoginActivity.newIntent(this))
        }
    }
}