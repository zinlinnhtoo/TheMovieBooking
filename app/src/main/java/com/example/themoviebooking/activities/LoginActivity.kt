package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.LoginViewPagerAdapter
import com.example.themoviebooking.data.models.MovieBookingModel
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.delegates.LoginFormResultDelegate
import com.example.themoviebooking.delegates.RegisterFormResultDelegate
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginFormResultDelegate, RegisterFormResultDelegate {

    private var mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginViewpagerAdapter = LoginViewPagerAdapter(this)
        viewPagerLogin.adapter = loginViewpagerAdapter

        TabLayoutMediator(tlLogin, viewPagerLogin) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = getString(R.string.lbl_login_tab)
                }
                1 -> {
                    tab.text = getString(R.string.lbl_register_tab)
                }
            }
        }.attach()

    }

    override fun onLogin(email: String, password: String) {
        mMovieBookingModel.getLoginUser(
            email = email,
            password = password,
            onSuccess = {
                startActivity(HomeActivity.newIntent(this))
                Log.i("Login", "Network onSuccess worked")
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_LONG).show()
            }
        )
    }

    override fun onRegister(email: String, password: String, name: String, phone: String) {
        mMovieBookingModel.getRegisterUser(
            email = email,
            name = name,
            phone = phone,
            password = password,
            onSuccess = {
                startActivity(HomeActivity.newIntent(this))
                Log.i("Register", "Network onSuccess worked")
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_LONG).show()
            }
        )
    }
}