package com.example.themoviebooking.delegates

interface LoginFormResultDelegate {
    fun onLogin(
        email: String,
        password: String
    )
}