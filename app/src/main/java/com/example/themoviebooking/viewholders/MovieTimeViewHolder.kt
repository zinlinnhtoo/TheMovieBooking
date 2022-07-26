package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.adapters.TimeChipAdapter
import kotlinx.android.synthetic.main.view_holder_movie_time.view.*

class MovieTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var mMovieTimeChipAdapter: TimeChipAdapter

    var rvTimeChip: RecyclerView = itemView.rvTimeChip


    fun setUpTimeChipRecyclerView() {
        mMovieTimeChipAdapter = TimeChipAdapter()
        rvTimeChip.adapter = mMovieTimeChipAdapter
        rvTimeChip.layoutManager = GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL, false)
    }
}