package com.example.themoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.CardVO
import com.example.themoviebooking.viewholders.CreditCardViewHolder

class CreditCardAdapter: RecyclerView.Adapter<CreditCardViewHolder>() {

    private var mCardList: List<CardVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_credit_card, parent, false)
        return CreditCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreditCardViewHolder, position: Int) {
        holder.bindData(mCardList[position])
    }

    override fun getItemCount(): Int {
        return mCardList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cardList: List<CardVO>) {
        mCardList = cardList
        notifyDataSetChanged()
    }
}