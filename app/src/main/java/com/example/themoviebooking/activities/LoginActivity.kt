package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.LoginViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.view_pod_login_button.*

class LoginActivity : AppCompatActivity() {

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
}