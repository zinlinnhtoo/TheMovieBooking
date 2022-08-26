package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.data.vos.CardVO
import kotlinx.android.synthetic.main.view_holder_credit_card.view.*

class CreditCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(card: CardVO) {
        itemView.tvCardNumber.text = card.cardNumber
        itemView.tvCardHolderName.text = card.cardHolder
        itemView.tvCardExpire.text = card.expirationDate
        itemView.tvCardName.text = card.cardType
    }
}