package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.data.vos.DateVO
import com.example.themoviebooking.delegates.MovieDateDelegate
import kotlinx.android.synthetic.main.view_holder_movie_date.view.*

class MovieDateViewHolder(itemView: View, private val mDelegate: MovieDateDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mDate: DateVO? = null

    init {
        itemView.setOnClickListener{
            mDate?.let {
                mDelegate.onTapMovieDate(it)
            }
        }
    }

    fun bindData(date: DateVO) {
        mDate = date

        itemView.tvWeek.text = date.weekday
        itemView.tvDate.text = date.day
    }
}