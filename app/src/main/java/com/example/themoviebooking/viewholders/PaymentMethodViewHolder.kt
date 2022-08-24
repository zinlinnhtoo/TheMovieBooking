package com.example.themoviebooking.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.PaymentCardVO
import com.example.themoviebooking.delegates.PaymentMethodDelegate
import kotlinx.android.synthetic.main.view_holder_payment_method.view.*

class PaymentMethodViewHolder(
    itemView: View,
    private val mDelegate: PaymentMethodDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var mPaymentMethod: PaymentCardVO? = null

    init {
        itemView.setOnClickListener {
            mPaymentMethod?.let {
                mDelegate.onTapPaymentMethod(it)
            }
        }
    }

    fun bindData(paymentCardVO: PaymentCardVO) {
        mPaymentMethod = paymentCardVO
        itemView.tvCardTitle.text = paymentCardVO.name
        itemView.tvCardDescription.text = paymentCardVO.description

        if (paymentCardVO.isSelected == true) {
            itemView.tvCardTitle.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.colorPrimary
                )
            )
            itemView.tvCardDescription.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.colorPrimary
                )
            )
            itemView.ivPaymentCard.imageTintList = ContextCompat.getColorStateList(
                itemView.context,
                R.color.colorPrimary
            )
        } else {
            itemView.tvCardTitle.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.black
                )
            )
            itemView.tvCardDescription.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.black
                )
            )
            itemView.ivPaymentCard.imageTintList = ContextCompat.getColorStateList(
                itemView.context,
                R.color.black
            )
        }
    }
}