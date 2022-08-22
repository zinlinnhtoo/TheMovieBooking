package com.example.themoviebooking.viewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.CinemaVO
import com.example.themoviebooking.data.vos.TimeSlotVO
import com.example.themoviebooking.delegates.MovieTimeDelegate
import kotlinx.android.synthetic.main.view_holder_movie_time_chip.view.*

class MovieTimeslotViewHolder(itemView: View, private val mDelegate: MovieTimeDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mTimeslot : TimeSlotVO? = null
    private var mCinema: CinemaVO? = null

    init {
        itemView.tvTimeChip.setOnClickListener {
            mCinema?.let { cinema ->
                mTimeslot?.let { timeslot ->
                    mDelegate.onTapTime(
                        cinema = cinema,
                        timeslot = timeslot
                    )
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    fun bindData(timeSlot: TimeSlotVO, cinema: CinemaVO) {
        mCinema = cinema
        mTimeslot = timeSlot
        itemView.tvTimeChip.text = timeSlot.startTime

        if (timeSlot.isSelected == true) {
            itemView.tvTimeChip.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.white
                )
            )
            itemView.tvTimeChip.backgroundTintList = ContextCompat.getColorStateList(
                itemView.context,
                R.color.colorPrimary
            )
        } else {
            itemView.tvTimeChip.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.black
                )
            )
        }
    }
}