package com.example.themoviebooking.viewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.MovieSeatVO
import com.example.themoviebooking.delegates.MovieSeatDelegate
import kotlinx.android.synthetic.main.view_holder_movie_seat.view.*

class MovieSeatViewHolder(
    itemView: View,
    private val mDelegate: MovieSeatDelegate
) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("ResourceAsColor")
    fun bindData(data: MovieSeatVO) {
        when {
            data.isMovieSeatAvailable() -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_available
                )
                itemView.setOnClickListener {
                    data.isSelected = data.isSelected != true
                    itemView.tvMovieSeatTitle.apply {
                        visibility = View.VISIBLE
                        text = data.seatName
                        setTextColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.white
                            )
                        )
                    }
                    itemView.flMovieSeat.backgroundTintList = ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.colorPrimary
                    )

                    mDelegate.onTapMovieSeat("${data.isSelected}")
                }
            }
            data.isMovieSeatTaken() -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_taken
                )
                itemView.setOnClickListener {
                    mDelegate.onTapMovieSeat("Tap Taken Seat")
                }
            }
            data.isMovieSeatRowTitle() -> {
                itemView.tvMovieSeatTitle.visibility = View.VISIBLE
                itemView.tvMovieSeatTitle.text = data.symbol
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
                itemView.setOnClickListener {
                    mDelegate.onTapMovieSeat("Don't Tap")
                }
            }
            else -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }
        }
    }
}