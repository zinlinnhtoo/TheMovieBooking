package com.example.themoviebooking.delegates

import com.example.themoviebooking.data.vos.PaymentCardVO

interface PaymentMethodDelegate {
    fun onTapPaymentMethod(
       paymentMethod: PaymentCardVO
    )
}