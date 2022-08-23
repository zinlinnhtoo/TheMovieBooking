package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.data.vos.SnackVO
import kotlinx.android.synthetic.main.view_holder_snack.view.*

class SnackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindData(snack: SnackVO) {
        itemView.tvSnackTitle.text = snack.name
        itemView.tvSnackDetail.text = snack.description
        itemView.tvSnackPrice.text = snack.price.toString()
        itemView.btnSnackCountToggle.isEnabled = false
    }
}