package com.example.themoviebooking.delegates

import com.example.themoviebooking.data.vos.PaymentMethodVO

interface PaymentMethodDelegate {
    fun onTapPaymentMethod(
       paymentMethod: PaymentMethodVO
    )
}