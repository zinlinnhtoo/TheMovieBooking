package com.example.themoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.viewholders.MovieTimeChipViewHolder

class TimeChipAdapter: RecyclerView.Adapter<MovieTimeChipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeChipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_time_chip, parent, false)
        return MovieTimeChipViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieTimeChipViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }
}