package com.example.themoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.viewholders.MovieDateViewHolder

class MovieDateAdapter: RecyclerView.Adapter<MovieDateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_date, parent, false)
        return MovieDateViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieDateViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}