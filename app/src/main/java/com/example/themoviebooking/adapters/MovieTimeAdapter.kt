package com.example.themoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.CinemaVO
import com.example.themoviebooking.viewholders.MovieTimeViewHolder

class MovieTimeAdapter: RecyclerView.Adapter<MovieTimeViewHolder>() {

    private var mCinemaList: List<CinemaVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_time, parent, false)
        return MovieTimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieTimeViewHolder, position: Int) {
        if (mCinemaList.isNotEmpty()) {
            holder.setUpTimeChipRecyclerView(mCinemaList[position].timeSlots)
            holder.bindData(mCinemaList[position])
        }
    }

    override fun getItemCount(): Int {
        return mCinemaList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cinemaList: List<CinemaVO>) {
        mCinemaList = cinemaList
        notifyDataSetChanged()
    }

}