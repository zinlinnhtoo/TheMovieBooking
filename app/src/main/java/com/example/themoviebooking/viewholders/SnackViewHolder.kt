package com.example.themoviebooking.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.data.vos.SnackVO
import com.example.themoviebooking.delegates.SnackToggleButtonDelegate
import kotlinx.android.synthetic.main.view_holder_snack.view.*

class SnackViewHolder(
    itemView: View,
    private val mDelegate: SnackToggleButtonDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var mSnackQuantity: Int = 0

    @SuppressLint("SetTextI18n")
    fun bindData(snack: SnackVO) {

        itemView.btnPlusToggle.setOnClickListener {
            snack.quantity += 1
            mDelegate.onTapSnackToggleButton(snack)
            itemView.btnSnackCountToggle.text = snack.quantity.toString()
        }

        itemView.btnMinusToggle.setOnClickListener {
            snack.isSelectedMinusButton = true
            if (snack.quantity > 0 && snack.isSelectedMinusButton == true) {
                snack.quantity -= 1
                mDelegate.onTapSnackToggleButton(snack)
                snack.isSelectedMinusButton = false
            }
            itemView.btnSnackCountToggle.text = snack.quantity.toString()
        }


        itemView.tvSnackTitle.text = snack.name
        itemView.tvSnackDetail.text = snack.description
        itemView.tvSnackPrice.text = "$ ${snack.price}"
        itemView.btnSnackCountToggle.isEnabled = false
        itemView.btnSnackCountToggle.text = mSnackQuantity.toString()

    }
}