package com.example.themoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.TimeSlotVO
import com.example.themoviebooking.viewholders.MovieTimeslotViewHolder

class TimeslotAdapter: RecyclerView.Adapter<MovieTimeslotViewHolder>() {

    private var mTimeSlotList : List<TimeSlotVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeslotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_time_chip, parent, false)
        return MovieTimeslotViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieTimeslotViewHolder, position: Int) {
        if (mTimeSlotList.isNotEmpty()) {
            holder.bindData(mTimeSlotList[position])
        }
    }

    override fun getItemCount(): Int {
        return mTimeSlotList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(timeSlotList: List<TimeSlotVO>) {
        mTimeSlotList = timeSlotList
        notifyDataSetChanged()
    }
}