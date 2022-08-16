package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.data.vos.TimeSlotVO
import kotlinx.android.synthetic.main.view_holder_movie_time_chip.view.*

class MovieTimeslotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(timeSlot: TimeSlotVO) {
        itemView.tvTimeChip.text = timeSlot.startTime
    }
}