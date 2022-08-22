package com.example.themoviebooking.viewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.TimeSlotVO
import com.example.themoviebooking.delegates.MovieTimeDelegate
import kotlinx.android.synthetic.main.view_holder_movie_time_chip.view.*

class MovieTimeslotViewHolder(itemView: View, private val mDelegate: MovieTimeDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mTimeslot : TimeSlotVO? = null
    private var mCinemaId: Int? = null

    init {
        itemView.tvTimeChip.setOnClickListener {
            mDelegate.onTapTime(
                cinemaId = mCinemaId,
                timeslotId = mTimeslot?.cinemaDayTimeslotId
            )
        }
    }

    @SuppressLint("ResourceAsColor")
    fun bindData(timeSlot: TimeSlotVO, cinemaId: Int) {
        mCinemaId = cinemaId
        mTimeslot = timeSlot
        itemView.tvTimeChip.text = timeSlot.startTime

        if (timeSlot.isSelected == true) {
            itemView.tvTimeChip.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.white
                )
            )
            itemView.tvTimeChip.setBackgroundColor(R.color.colorAccent)
        } else {
            itemView.tvTimeChip.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.colorAccent
                )
            )
            itemView.tvTimeChip.setBackgroundColor(R.color.white)
        }
    }
}