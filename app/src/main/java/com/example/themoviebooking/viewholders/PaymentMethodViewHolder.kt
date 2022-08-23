package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.data.vos.PaymentCardVO
import kotlinx.android.synthetic.main.view_holder_payment_method.view.*

class PaymentMethodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(paymentCardVO: PaymentCardVO) {
        itemView.tvCardTitle.text = paymentCardVO.name
        itemView.tvCardDescription.text = paymentCardVO.description
    }
}