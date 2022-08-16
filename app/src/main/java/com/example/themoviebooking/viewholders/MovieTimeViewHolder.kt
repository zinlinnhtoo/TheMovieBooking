package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.adapters.TimeslotAdapter
import com.example.themoviebooking.data.vos.CinemaVO
import com.example.themoviebooking.data.vos.TimeSlotVO
import kotlinx.android.synthetic.main.view_holder_movie_time.view.*

class MovieTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var mMovieTimeSlotAdapter: TimeslotAdapter

    var rvTimeChip: RecyclerView = itemView.rvTimeChip


    fun setUpTimeChipRecyclerView(timeSlotList: List<TimeSlotVO>) {
        mMovieTimeSlotAdapter = TimeslotAdapter()
        rvTimeChip.adapter = mMovieTimeSlotAdapter
        rvTimeChip.layoutManager = GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL, false)
        mMovieTimeSlotAdapter.setNewData(timeSlotList)
    }

    fun bindData(cinema: CinemaVO) {
        itemView.tvTitle.text = cinema.cinema
    }
}