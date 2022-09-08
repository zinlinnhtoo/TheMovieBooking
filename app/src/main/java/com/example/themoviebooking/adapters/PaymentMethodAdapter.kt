package com.example.themoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.PaymentMethodVO
import com.example.themoviebooking.delegates.PaymentMethodDelegate
import com.example.themoviebooking.viewholders.PaymentMethodViewHolder

class PaymentMethodAdapter(
    private val mDelegate: PaymentMethodDelegate
): RecyclerView.Adapter<PaymentMethodViewHolder>() {

    private var mPaymentCardList: List<PaymentMethodVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_payment_method, parent, false)
        return PaymentMethodViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        holder.bindData(mPaymentCardList[position])
    }

    override fun getItemCount(): Int {
        return mPaymentCardList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(paymentCardList: List<PaymentMethodVO>) {
        mPaymentCardList = paymentCardList
        notifyDataSetChanged()
    }
}