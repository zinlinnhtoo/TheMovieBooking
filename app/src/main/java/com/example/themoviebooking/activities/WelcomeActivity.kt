package com.example.themoviebooking.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.themoviebooking.R
import com.example.themoviebooking.data.models.MovieBookingModel
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private var mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnGetStarted.setOnClickListener {
            mMovieBookingModel.getTokenFromWelcome(
                onSuccess = {
                    if (it) {
                        startActivity(HomeActivity.newIntent(this))
                    } else {
                        startActivity(LoginActivity.newIntent(this))
                    }
                }
            )

        }


    }
}