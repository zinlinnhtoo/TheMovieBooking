package com.example.themoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.SnackVO
import com.example.themoviebooking.delegates.SnackToggleButtonDelegate
import com.example.themoviebooking.viewholders.SnackViewHolder

class SnackAdapter(
    private val mDelegate: SnackToggleButtonDelegate
): RecyclerView.Adapter<SnackViewHolder>() {

    private var mSnackList: List<SnackVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_snack, parent, false)
        return SnackViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: SnackViewHolder, position: Int) {
        holder.bindData(mSnackList[position])
    }

    override fun getItemCount(): Int {
        return mSnackList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(snackList: List<SnackVO>) {
        mSnackList = snackList
        notifyDataSetChanged()
    }
}