package com.example.themoviebooking.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.themoviebooking.R
import com.example.themoviebooking.delegates.LoginButtonDelegate
import com.example.themoviebooking.delegates.RegisterFormResultDelegate
import com.example.themoviebooking.viewpods.LoginButtonViewPod
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.etEmail
import kotlinx.android.synthetic.main.fragment_register.etPassword

class RegisterFragment : Fragment(), LoginButtonDelegate {

    private lateinit var mDelegate: RegisterFormResultDelegate
    lateinit var loginButtonViewPod: LoginButtonViewPod

    override fun onAttach(context: Context) {
        mDelegate = context as RegisterFormResultDelegate
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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButtonViewPod = vpRegisterButton as LoginButtonViewPod
        loginButtonViewPod.setDelegate(this)
    }

    override fun onTapConfirm() {
        mDelegate.onRegister(
            email = etEmail.text.toString(),
            password = etPassword.text.toString(),
            name = etName.text.toString(),
            phone = etPhone.text.toString()
        )
    }

}