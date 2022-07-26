package com.example.themoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.CinemaVO
import com.example.themoviebooking.data.vos.TimeSlotVO
import com.example.themoviebooking.delegates.MovieTimeDelegate
import com.example.themoviebooking.viewholders.MovieTimeslotViewHolder

class TimeslotAdapter(private val mDelegate: MovieTimeDelegate): RecyclerView.Adapter<MovieTimeslotViewHolder>() {

    private var mTimeSlotList : List<TimeSlotVO> = listOf()
    private var mCinema: CinemaVO? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeslotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_time_chip, parent, false)
        return MovieTimeslotViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieTimeslotViewHolder, position: Int) {
        if (mTimeSlotList.isNotEmpty()) {
            mCinema?.let { holder.bindData(mTimeSlotList[position], it) }
        }
    }

    override fun getItemCount(): Int {
        return mTimeSlotList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(timeSlotList: List<TimeSlotVO>, cinema: CinemaVO) {
        mCinema = cinema
        mTimeSlotList = timeSlotList
        notifyDataSetChanged()
    }
}