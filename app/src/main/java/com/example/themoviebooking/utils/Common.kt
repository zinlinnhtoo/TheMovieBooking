package com.example.themoviebooking.utils

import android.content.Context
import android.widget.Toast

fun showErrorToast(error: String, context: Context) {
    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
}