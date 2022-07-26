package com.example.themoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.viewholders.CreditCardViewHolder

class CreditCardAdapter: RecyclerView.Adapter<CreditCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_credit_card, parent, false)
        return CreditCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreditCardViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}