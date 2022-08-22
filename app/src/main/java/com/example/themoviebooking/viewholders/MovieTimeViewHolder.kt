package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.adapters.TimeslotAdapter
import com.example.themoviebooking.data.vos.CinemaVO
import com.example.themoviebooking.data.vos.TimeSlotVO
import com.example.themoviebooking.delegates.MovieTimeDelegate
import kotlinx.android.synthetic.main.view_holder_movie_time.view.*

class MovieTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var mMovieTimeSlotAdapter: TimeslotAdapter

    private var mCinema: CinemaVO? = null


    var rvTimeChip: RecyclerView = itemView.rvTimeChip


    fun setUpTimeChipRecyclerView(timeSlotList: List<TimeSlotVO>, delegate: MovieTimeDelegate, cinemaId: Int) {
        mMovieTimeSlotAdapter = TimeslotAdapter(delegate)
        rvTimeChip.adapter = mMovieTimeSlotAdapter
        rvTimeChip.layoutManager = GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL, false)
        mMovieTimeSlotAdapter.setNewData(timeSlotList, cinemaId)
    }

    fun bindData(cinema: CinemaVO) {
        mCinema = cinema
        itemView.tvTitle.text = cinema.cinema
    }
}