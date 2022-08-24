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
    private var mSnackPrice: Double = 0.0

    @SuppressLint("SetTextI18n")
    fun bindData(snack: SnackVO) {

        snack.price?.let {
            mSnackPrice = it
        }

        itemView.btnPlusToggle.setOnClickListener {
            mSnackQuantity += 1
            mDelegate.onTapSnackToggleButton(mSnackPrice)
            itemView.btnSnackCountToggle.text = mSnackQuantity.toString()
        }

        itemView.btnMinusToggle.setOnClickListener {
            if (mSnackQuantity > 0) {
                mSnackQuantity -= 1
                mDelegate.onTapSnackToggleButton(-mSnackPrice)
            }
            itemView.btnSnackCountToggle.text = mSnackQuantity.toString()
        }


        itemView.tvSnackTitle.text = snack.name
        itemView.tvSnackDetail.text = snack.description
        itemView.tvSnackPrice.text = "$ ${snack.price}"
        itemView.btnSnackCountToggle.isEnabled = false
        itemView.btnSnackCountToggle.text = mSnackQuantity.toString()

    }
}