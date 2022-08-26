package com.example.themoviebooking.delegates

import com.example.themoviebooking.data.vos.SnackVO

interface SnackToggleButtonDelegate {
    fun onTapSnackToggleButton(
        snack: SnackVO
    )
}