package com.example.themoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.viewholders.MovieTimeViewHolder

class MovieTimeAdapter: RecyclerView.Adapter<MovieTimeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_time, parent, false)
        return MovieTimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieTimeViewHolder, position: Int) {
        holder.setUpTimeChipRecyclerView()
    }

    override fun getItemCount(): Int {
        return 3
    }

}