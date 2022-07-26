package com.example.themoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.delegates.MovieViewHolderDelegate
import com.example.themoviebooking.viewholders.MovieViewHolder

class MovieListAdapter(private val mDelegate: MovieViewHolderDelegate): RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}