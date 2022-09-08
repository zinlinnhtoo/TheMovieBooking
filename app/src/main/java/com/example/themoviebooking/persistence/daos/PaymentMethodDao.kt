package com.example.themoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebooking.data.vos.PaymentMethodVO

@Dao
interface PaymentMethodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaymentMethod(paymentMethod: List<PaymentMethodVO>)

    @Query("SELECT * FROM payment_methods")
    fun getAllPaymentMethod(): List<PaymentMethodVO>

    @Query("DELETE FROM payment_methods")
    fun deletePaymentMethod()
}