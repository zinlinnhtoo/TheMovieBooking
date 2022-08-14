package com.example.themoviebooking.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.themoviebooking.R
import com.example.themoviebooking.delegates.LoginButtonDelegate
import com.example.themoviebooking.delegates.LoginFormResultDelegate
import com.example.themoviebooking.viewpods.LoginButtonViewPod
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), LoginButtonDelegate {

    lateinit var mDelegate: LoginFormResultDelegate
    lateinit var loginButtonViewPod: LoginButtonViewPod

    override fun onAttach(context: Context) {
        mDelegate = context as LoginFormResultDelegate
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginButtonViewPod = vpLoginButton as LoginButtonViewPod
        loginButtonViewPod.setDelegate(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onTapConfirm() {
        mDelegate.onLogin(etEmail.text.toString(), etPassword.text.toString())
    }

}