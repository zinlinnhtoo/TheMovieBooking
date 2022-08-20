package com.example.themoviebooking.delegates

interface RegisterFormResultDelegate {
    fun onRegister(
        email: String,
        password: String,
        name: String,
        phone: String
    )
}