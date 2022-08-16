package com.example.themoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.DateVO
import com.example.themoviebooking.delegates.MovieDateDelegate
import com.example.themoviebooking.viewholders.MovieDateViewHolder

class MovieDateAdapter(private val mDelegate: MovieDateDelegate): RecyclerView.Adapter<MovieDateViewHolder>() {

    private var mDateList : List<DateVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_date, parent, false)
        return MovieDateViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieDateViewHolder, position: Int) {
        if (mDateList.isNotEmpty()) {
            holder.bindData(mDateList[position])
        }
    }

    override fun getItemCount(): Int {
        return mDateList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(dateList: List<DateVO>) {
        mDateList = dateList
        notifyDataSetChanged()
    }

}