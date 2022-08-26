package com.example.themoviebooking.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviebooking.R
import com.example.themoviebooking.data.models.MovieBookingModel
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.utils.showErrorToast
import kotlinx.android.synthetic.main.activity_new_payment_card.*

class NewPaymentCardActivity : AppCompatActivity() {

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, NewPaymentCardActivity::class.java)
        }
    }

    private var mCardNumber: String = ""
    private var mCardHolder: String = ""
    private var mExpirationDate: String = ""
    private var mCvc: String = ""

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

            mCardNumber = etCardNumber.text.toString()
            mCardHolder = etCardHolder.text.toString()
            mExpirationDate = etExpirationDate.text.toString()
            mCvc = etCvc.text.toString()

            mMovieBookingModel.createCard(
                cardNumber = mCardNumber,
                cardHolder = mCardHolder,
                expirationDate = mExpirationDate,
                cvc = mCvc,
                onSuccess = {
                    val intent = Intent()
                        .putExtra("message", it)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                },
                onFailure = {
                    showErrorToast(it, this)
                }
            )
        }
    }
}