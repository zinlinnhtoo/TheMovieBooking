package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.data.vos.TimeSlotVO
import com.example.themoviebooking.delegates.MovieTimeDelegate
import kotlinx.android.synthetic.main.view_holder_movie_time_chip.view.*

class MovieTimeslotViewHolder(itemView: View, private val mDelegate: MovieTimeDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mTimeslot : TimeSlotVO? = null

    init {
        itemView.setOnClickListener {
            mDelegate.onTapTime()
        }
    }

    fun bindData(timeSlot: TimeSlotVO) {
        mTimeslot = timeSlot
        itemView.tvTimeChip.text = timeSlot.startTime
    }
}